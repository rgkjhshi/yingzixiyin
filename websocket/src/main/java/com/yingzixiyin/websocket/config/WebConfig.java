package com.yingzixiyin.websocket.config;

import com.yingzixiyin.websocket.handler.ChatHandler;
import com.yingzixiyin.websocket.handler.EchoHandler;
import com.yingzixiyin.websocket.interceptor.ChatHandshakeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
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
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.info("websocket注册webconfig");
        registry.addHandler(chatHandler(), "/chat").addInterceptors(new ChatHandshakeInterceptor());
        registry.addHandler(chatHandler(), "/sockjs/chat").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();

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
	// Allow serving HTML files through the default Servlet
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
