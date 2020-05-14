package com.framework.hanason.web.websocket;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author sorata 2020-04-30 13:55
 *
 *
 * 不需要自己去处理异常 见{@link org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator}
 *
 */
public abstract class AbstractMessageHandler {

    /**
     * 登入
     * @param session socket
     * @throws Exception 异常
     */
    protected  void afterConnectionEstablished(WebSocketSession session) throws Exception{

    }


    /**
     * 收到文本消息
     * @param session socket
     * @param message 文本消息
     * @throws Exception 异常
     */
    protected  void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{

    }

    /**
     * 收到二进制消息
     * @param session socket
     * @param message 消息
     * @throws Exception 异常
     */
    protected  void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception{

    }

    /**
     * 出现传输异常 可能是发送超时异常
     * @param session socket
     * @param exception 异常
     * @throws Exception 异常
     */
    protected  void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{

    }

    /**
     * socket关闭
     * @param session socket关闭
     * @param status 关闭的状态
     * @throws Exception 异常
     */
    protected  void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{}

}
