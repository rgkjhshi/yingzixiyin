package com.yingzixiyin.consultant.chat.config;

import com.yingzixiyin.consultant.chat.handler.ChatHandler;
import com.yingzixiyin.consultant.chat.handler.EchoHandler;
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

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(), "/chat");
        registry.addHandler(chatHandler(), "/sockjs/chat").withSockJS();
        registry.addHandler(echoHandler(), "/echo").addInterceptors(new HttpSessionHandshakeInterceptor());
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
