package com.yingzixiyin.consultant.chat.config;

import com.yingzixiyin.consultant.chat.handler.ChatHandler;
import com.yingzixiyin.consultant.chat.handler.EchoHandler;
import com.yingzixiyin.consultant.chat.interceptor.ChatHandshakeInterceptor;
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

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.info("consultant注册webconfig");
        registry.addHandler(chatHandler(), "/chat");
        registry.addHandler(chatHandler(), "/sockjs/chat").withSockJS();

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
