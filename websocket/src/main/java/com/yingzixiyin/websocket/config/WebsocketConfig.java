package com.yingzixiyin.websocket.config;

import com.yingzixiyin.websocket.handler.ChatHandler;
import com.yingzixiyin.websocket.handler.EchoHandler;
import com.yingzixiyin.websocket.interceptor.ChatHandshakeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebsocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketConfig.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.info("websocket注册webconfig...");
        registry.addHandler(chatHandler(), "/chat.do").addInterceptors(new ChatHandshakeInterceptor());
        registry.addHandler(chatHandler(), "/sockjs/chat.do").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();

        registry.addHandler(echoHandler(), "/echo").addInterceptors(new HttpSessionHandshakeInterceptor());
        registry.addHandler(echoHandler(), "/sockjs/echo").addInterceptors(new HttpSessionHandshakeInterceptor()).withSockJS();
    }

    @Bean
    public WebSocketHandler echoHandler() {
        return new EchoHandler();
    }

    @Bean
    public WebSocketHandler chatHandler(){
        return new ChatHandler();
    }

}
