package com.yingzixiyin.websocket.handler;

import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.MessageFacade;
import com.yingzixiyin.websocket.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-22
 */

public class ChatHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);
    // 所有在线用户的map
    private static final Map<String, WebSocketSession> userMap = new HashMap<String, WebSocketSession>();

    @Resource
    private ChatService chatService;
    @Resource
    private MessageFacade messageFacade;


    public ChatHandler() {
    }

    /**
     * 连接成功时候，会触发onOpen方法
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("connect to the websocket success......");
        logger.info("session attributes: {}", session.getAttributes());
        String phone = (String) session.getAttributes().get("phone");
        String toPhone = (String) session.getAttributes().get("toPhone");
        if (null == phone || null == toPhone) {
            logger.error("未获取到session中的phone...");
            session.close();
            return;
        }
        userMap.put(phone, session);
        // 当用户登录后，把离线消息推送给用户
        try {
            sendUnReadMessage(session);
        } catch (IOException e) {
            logger.error("发送离线消息出错, {}", e);
        }

    }

    /**
     * 在UI在用js调用websocket.send()时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String phone = (String) session.getAttributes().get("phone");
        String toPhone = (String) session.getAttributes().get("toPhone");
        Long recordId = (Long) session.getAttributes().get("recordId");
        // 找to
        WebSocketSession toSession = userMap.get(toPhone);
        // 消息
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setRecordId(recordId);
        messageInfo.setMessage(message.getPayload());
        messageInfo.setFromPhone(phone);
        messageInfo.setToPhone(toPhone);
        messageInfo.setCreateTime(new Date());
        messageInfo.setIsRead(YesOrNoEnum.NO);
        // to在线，发送
        if (null != toSession && toSession.isOpen()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TextMessage messageWithTime = new TextMessage(sdf.format(messageInfo.getCreateTime()) + ";" + message.getPayload());
            Boolean result = sendMessageToUser(toSession, messageWithTime);
            if (result) {
                messageInfo.setIsRead(YesOrNoEnum.YES);
            }
        } else {
            logger.info("{}离线，消息已经保存到数据库", toPhone);
        }
        // 消息入库
        messageFacade.add(messageInfo);
    }

    /**
     * 给某个用户发送消息
     */
    private Boolean sendMessageToUser(WebSocketSession toSession, TextMessage message) {
        Boolean result = false;
        try {
            if (toSession.isOpen()) {
                String phone = (String) toSession.getAttributes().get("phone");
                logger.info("发送消息给用户:{}", phone);
                toSession.sendMessage(message);
                result = true;
            }
        } catch (IOException e) {
            logger.error("发送消息失败:", e);
        }
        return result;
    }

    /**
     * 发送未读消息
     * @param session  连接
     * @throws IOException
     */
    private void sendUnReadMessage(WebSocketSession session) throws IOException {
        logger.info("推送离线消息...");
        String phone = (String) session.getAttributes().get("phone");
        Long recordId = (Long) session.getAttributes().get("recordId");
        // 查询该用户的所有未读消息
        MessageQueryRequestDto requestDto = new MessageQueryRequestDto();
        requestDto.setRecordId(recordId);
        requestDto.setToPhone(phone);
        requestDto.setIsRead(YesOrNoEnum.NO);
        MessageQueryResponseDto responseDto = messageFacade.query(requestDto);
        List<MessageInfo> messageInfoList = responseDto.getMessageInfoList();
        if (null != messageInfoList) {
            for (MessageInfo messageInfo : responseDto.getMessageInfoList()) {
                // 发送
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                TextMessage messageWithTime = new TextMessage(sdf.format(messageInfo.getCreateTime()) + ";" + messageInfo.getMessage());
                sendMessageToUser(session, messageWithTime);
                // 更新为已读
                MessageInfo info = new MessageInfo();
                info.setId(messageInfo.getId());
                info.setIsRead(YesOrNoEnum.YES);
                messageFacade.update(info);
            }
        }
        logger.info("离线消息推送完毕...");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            String phone = (String) session.getAttributes().get("phone");
            userMap.remove(phone);
            session.close();
        }
        logger.info("websocket connection closed because of error......");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String phone = (String) session.getAttributes().get("phone");
        logger.info("websocket connection closed, remove {}", phone);
        userMap.remove(phone);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
