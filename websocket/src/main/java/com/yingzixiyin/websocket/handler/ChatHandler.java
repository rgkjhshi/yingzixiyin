package com.yingzixiyin.websocket.handler;

import com.yingzixiyin.websocket.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
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

    public ChatHandler() {
    }

    /**
     * 连接成功时候，会触发onOpen方法
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("connect to the websocket success......");
        logger.info("session attributes: {}", session.getAttributes());
        Long recordId = (Long) session.getAttributes().get("recordId");
        String phone = (String) session.getAttributes().get("phone");
        String toPhone = (String) session.getAttributes().get("toPhone");
        if (null == phone || null == toPhone) {
            logger.error("no phone in session...");
            session.close();
            return;
        }
        String fromKey = phone + "#" + recordId + "#" + toPhone;
        userMap.put(fromKey, session);
        logger.info("{} login in", fromKey);
        // 当用户登录后，把离线消息推送给用户
        try {
            chatService.sendUnReadMessage(session);
        } catch (IOException e) {
            logger.error("send unReadMessage error", e);
        }

    }

    /**
     * 在UI在用js调用websocket.send()时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String toKey = getToKey(session);
        // 找to
        logger.info("找to:{}", toKey);
        WebSocketSession toSession = userMap.get(toKey);
        chatService.sendAndSaveMessage(session, toSession, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            String fromKey = getFromKey(session);
            userMap.remove(fromKey);
            session.close();
        }
        logger.info("websocket connection closed because of error......");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String fromKey = getFromKey(session);
        userMap.remove(fromKey);
        logger.info("websocket connection closed, remove {}", fromKey);
        userMap.remove(fromKey);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private String getFromKey(WebSocketSession session){
        Long recordId = (Long)session.getAttributes().get("recordId");
        String phone = (String) session.getAttributes().get("phone");
        String toPhone = (String) session.getAttributes().get("toPhone");
        if (null == phone || null == toPhone || null == recordId) {
            logger.error("从session中获取phone和recordId失败...");
        }
        return phone + "#" + recordId + "#" + toPhone;
    }

    private String getToKey(WebSocketSession session) {
        Long recordId = (Long)session.getAttributes().get("recordId");
        String phone = (String) session.getAttributes().get("phone");
        String toPhone = (String) session.getAttributes().get("toPhone");
        if (null == phone || null == toPhone || null == recordId) {
            logger.error("从session中获取phone和recordId失败...");
        }
        return toPhone + "#" + recordId + "#" + phone;
    }
}
