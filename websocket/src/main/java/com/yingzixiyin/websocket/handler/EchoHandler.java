package com.yingzixiyin.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("getAttributes={}", session.getAttributes());
        logger.info("getId={}", session.getId());
        logger.info("getAcceptedProtocol={}", session.getAcceptedProtocol());
        logger.info("getExtensions={}", session.getExtensions());
        logger.info("getUri={}", session.getUri());
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("attributes:{}", session.getAttributes());
        String reply = "received:" + message.getPayload();
        session.sendMessage(new TextMessage(reply));
	}

}
