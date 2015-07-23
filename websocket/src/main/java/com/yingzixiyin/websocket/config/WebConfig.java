package com.yingzixiyin.websocket.config;

import com.yingzixiyin.websocket.handler.ChatHandler;
import com.yingzixiyin.websocket.handler.EchoHandler;
import com.yingzixiyin.websocket.interceptor.ChatHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(),"/chat").addInterceptors(new ChatHandshakeInterceptor());
        registry.addHandler(chatHandler(), "/sockjs/chat").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();
        registry.addHandler(echoHandler(), "/echo").addInterceptors(new ChatHandshakeInterceptor());
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
