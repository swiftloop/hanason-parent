package com.framework.hanason.web.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.WebSocketContainerFactoryBean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sorata 2020-04-30 11:20
 */
@Configuration
@EnableWebSocket
@ConditionalOnClass(ExceptionWebSocketHandlerDecorator.class)
public class WebSocketConfig implements WebSocketConfigurer {

    private static List<AbstractMessageHandler> handlers = new ArrayList<>();


    @Autowired(required = false)
    public void setHandlers(List<AbstractMessageHandler> abstractMessageHandlers) {
        handlers.addAll(abstractMessageHandlers);
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageHandler(), "/socket").addInterceptors(webSocketInterceptor())
                .setAllowedOrigins("*");
        registry.addHandler(messageHandler(), "/socket-js").addInterceptors(webSocketInterceptor())
                .setAllowedOrigins("*").withSockJS();
    }


    @Bean
    public MessageHandler messageHandler() {
        MessageHandler messageHandler = new MessageHandler();
        messageHandler.setWebSocketHandlers(handlers);
        return messageHandler;
    }

    @Bean
    public WebSocketInterceptor webSocketInterceptor() {
        return new WebSocketInterceptor();
    }


    @Bean
    public WebSocketContainerFactoryBean containerFactoryBean() {
        WebSocketContainerFactoryBean factoryBean = new WebSocketContainerFactoryBean();
        factoryBean.setAsyncSendTimeout(TimeUnit.SECONDS.toSeconds(20));
        factoryBean.setMaxBinaryMessageBufferSize(65535);
        factoryBean.setMaxTextMessageBufferSize(65535);
        factoryBean.setMaxSessionIdleTimeout(TimeUnit.MINUTES.toMillis(1));
        return factoryBean;
    }


}
