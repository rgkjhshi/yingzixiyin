package com.yingzixiyin.consultant.chat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String reply = "received:" + message.getPayload();
        session.sendMessage(new TextMessage(reply));
	}

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
