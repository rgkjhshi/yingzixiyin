package com.yingzixiyin.websocket.config;

import com.yingzixiyin.websocket.handler.ChatHandler;
import com.yingzixiyin.websocket.interceptor.ChatHandshakeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author song.shi
 * @date 2015-07-24
 */

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.info("register...");
        registry.addHandler(chatHandler(), "/chatServer.do").addInterceptors(new ChatHandshakeInterceptor());
        registry.addHandler(chatHandler(), "/sockjs/chatServer.do").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();
    }

    @Bean
    public TextWebSocketHandler chatHandler() {
        return new ChatHandler();
    }

}
