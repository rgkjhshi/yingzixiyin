package com.yingzixiyin.websocket.service;

import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.MessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author song.shi
 * @date 2015-08-03
 */

@Service("chatService")
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Resource
    private MessageFacade messageFacade;

    /**
     * 发送消息并入库
     * @param session 当前用户
     * @param toSession 目标用户
     * @param message 待发消息
     */
    public void sendAndSaveMessage(WebSocketSession session, WebSocketSession toSession, TextMessage message) {
        logger.info("session phone={}", session.getAttributes().get("phone"));
        String toPhone = (String) session.getAttributes().get("toPhone");
        // 创建消息
        MessageInfo messageInfo = createMessage(session, message);
        // to在线，发送
        if (null != toSession && toSession.isOpen()) {
            logger.info("toSession phone={}", toSession.getAttributes().get("phone"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TextMessage messageWithTime = new TextMessage(sdf.format(messageInfo.getCreateTime()) + ";" + message.getPayload());
            Boolean result = sendMessageToUser(toSession, messageWithTime);
            if (result) {
                messageInfo.setIsRead(YesOrNoEnum.YES);
            }
        } else {
            logger.info("{} offline, saved message into db", toPhone);
        }
        // 消息入库
        messageFacade.add(messageInfo);
    }

    // 组装消息实体
    private MessageInfo createMessage(WebSocketSession session, TextMessage message) {
        String phone = (String) session.getAttributes().get("phone");
        String toPhone = (String) session.getAttributes().get("toPhone");
        Long recordId = (Long) session.getAttributes().get("recordId");
        // 组装消息
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setRecordId(recordId);
        messageInfo.setMessage(message.getPayload());
        messageInfo.setFromPhone(phone);
        messageInfo.setToPhone(toPhone);
        messageInfo.setCreateTime(new Date());
        messageInfo.setIsRead(YesOrNoEnum.NO);
        return messageInfo;
    }

    /**
     * 给某个用户发送消息
     * @param toSession 目标用户
     * @param message 待发消息
     * @return true:发送成功
     */
    public Boolean sendMessageToUser(WebSocketSession toSession, TextMessage message) {
        Boolean result = false;
        try {
            if (toSession.isOpen()) {
                String phone = (String) toSession.getAttributes().get("phone");
                logger.info("send message to user:{}", phone);
                toSession.sendMessage(message);
                result = true;
            }
        } catch (IOException e) {
            logger.error("send message error:", e);
        }
        return result;
    }

    /**
     * 发送未读消息
     * @param session  连接
     * @throws java.io.IOException
     */
    public void sendUnReadMessage(WebSocketSession session) throws IOException {
        logger.info("push unread message...");
        String phone = (String) session.getAttributes().get("phone");
        String toPhone = (String) session.getAttributes().get("toPhone");
        Long recordId = (Long) session.getAttributes().get("recordId");
        // 查询该用户的所有未读消息
        MessageQueryRequestDto requestDto = new MessageQueryRequestDto();
        requestDto.setRecordId(recordId);
        requestDto.setFromPhone(toPhone); //这个用户发来的消息
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
        logger.info("push unread message success...");
    }
}
