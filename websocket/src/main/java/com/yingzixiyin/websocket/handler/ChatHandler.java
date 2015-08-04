package com.yingzixiyin.websocket.handler;

import com.yingzixiyin.api.dto.*;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.MessageFacade;
import com.yingzixiyin.api.facade.UserFacade;
import com.yingzixiyin.websocket.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
    @Resource
    private UserFacade userFacade;


    public ChatHandler() {
    }

    /**
     * 连接成功时候，会触发onOpen方法
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("connect to the websocket success......");
        logger.info("session attributes: {}", session.getAttributes());
        String phone = (String) session.getAttributes().get("session_phone");
        if (null == phone) {
            logger.error("未获取到session_phone...");
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
        String phone = (String) session.getAttributes().get("session_phone");
        Long recordId = (Long) session.getAttributes().get("session_recordId");
        // 查数据库，找to
        String toPhone = chatService.getToUserPhoneByRecordId(recordId);
        WebSocketSession toSession = userMap.get(toPhone);
        // 存消息
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setRecordId(recordId);
        messageInfo.setMessage(message.toString());
        messageInfo.setFromPhone(phone);
        messageInfo.setToPhone(toPhone);
        messageInfo.setCreateTime(new Date());
        messageInfo.setIsRead(YesOrNoEnum.NO);
        // to在线，发送
        if (toSession.isOpen()) {
            Boolean result = sendMessageToUser(toSession, message);
            if (result) {
                messageInfo.setIsRead(YesOrNoEnum.YES);
            }
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
                toSession.sendMessage(message);
                result = true;
            }
        } catch (IOException e) {
            logger.error("发送消息失败:{}", e);
        }
        return result;
    }

    private void sendUnReadMessage(WebSocketSession session) throws IOException {
        String phone = (String) session.getAttributes().get("session_phone");
        Long recordId = (Long) session.getAttributes().get("session_recordId");
        // 查询该用户的所有未读消息
        MessageQueryRequestDto requestDto = new MessageQueryRequestDto();
        requestDto.setToPhone(phone);
        requestDto.setIsRead(YesOrNoEnum.NO);
        MessageQueryResponseDto responseDto = messageFacade.query(requestDto);
        for (MessageInfo messageInfo : responseDto.getMessageInfoList()) {
            // 发送
            session.sendMessage(new TextMessage(messageInfo.getMessage()));
            // 更新为已读
            MessageInfo info = new MessageInfo();
            info.setIsRead(YesOrNoEnum.YES);
            messageFacade.update(info);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            String phone = (String) session.getAttributes().get("session_phone");
            userMap.remove(phone);
            session.close();
        }
        logger.info("websocket connection closed......");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("websocket connection closed......");
        String phone = (String) session.getAttributes().get("session_phone");
        userMap.remove(phone);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
