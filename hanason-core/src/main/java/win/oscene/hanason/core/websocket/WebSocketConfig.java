package win.oscene.hanason.core.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.WebSocketContainerFactoryBean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageHandler(), "/socket").addInterceptors(webSocketInterceptor())
                .setAllowedOrigins("*");
    }


    @Bean
    public MessageHandler messageHandler() {
        return new MessageHandler();
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
