package com.framework.hanason.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.List;

/**
 * @author sorata 2020-04-30 11:20
 * <p>
 * 不需要自己去处理异常 见{@link org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator}
 */
@Slf4j
public class MessageHandler extends AbstractWebSocketHandler {

    private List<AbstractMessageHandler> webSocketHandlers;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (webSocketHandlers != null) {
            for (AbstractMessageHandler handler : webSocketHandlers) {
                handler.afterConnectionEstablished(session);
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (webSocketHandlers != null) {
            for (AbstractMessageHandler handler : webSocketHandlers) {
                handler.handleTextMessage(session, message);
            }
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        if (webSocketHandlers != null) {
            for (AbstractMessageHandler handler : webSocketHandlers) {
                handler.handleBinaryMessage(session, message);
            }
        }
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) {
        //当需要服务器去ping客户端 客户端回pong消息
        if (Heartbeats.isEnable()) {
            session.getAttributes().put(Heartbeats.LAST_PONG_TIME, System.currentTimeMillis());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (webSocketHandlers != null) {
            for (AbstractMessageHandler handler : webSocketHandlers) {
                handler.handleTransportError(session, exception);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (webSocketHandlers != null) {
            for (AbstractMessageHandler handler : webSocketHandlers) {
                handler.afterConnectionClosed(session, status);
            }
        }
    }


    void setWebSocketHandlers(List<AbstractMessageHandler> webSocketHandlers) {
        this.webSocketHandlers = webSocketHandlers;
    }


}
