package com.framework.hanason.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sorata 2020-04-30 11:20
 *
 *
 * 创建一个实例 调用 {@code setWebSocketSessionGroup} 设置session的存储器 从而获取到session
 *
 * 调用 {@code exec}执行定时器
 *
 *
 *
 */
@Slf4j
public class Heartbeats {

    /**
     * 上一次收到pong帧的时间
     */
    static final String LAST_PONG_TIME = "LAST_PONG_TIME";

    private static final String FAILURE_COUNT_KEY = "FAILURE_COUNT";

    private static final int MAX_FAILURE_COUNT = 1;

    private static final int INIT_DELAY = 60;
    private static final int PERIOD = 30;

    private static boolean enable = false;

    /**
     * session超时的时间
     */
    private static final long SESSION_TIME_OUT = TimeUnit.MINUTES.toMillis(2);


    private WebSocketSessionGroup webSocketSessionGroup;


    private static final ScheduledThreadPoolExecutor POOL_EXECUTOR = new ScheduledThreadPoolExecutor(1,
            new CustomizableThreadFactory("Heartbeats-"));


    public void exec(){
        POOL_EXECUTOR.scheduleAtFixedRate(this::run,INIT_DELAY,PERIOD,TimeUnit.SECONDS);
    }


    private void run(){
        if (webSocketSessionGroup == null){
            return;
        }

        List<WebSocketSession> sessions = webSocketSessionGroup.getSessions();
        long count = sessions.stream().filter(x->{
            if (x.getAttributes().get(LAST_PONG_TIME) == null){
                return false;
            }
            if (System.currentTimeMillis() - Long.parseLong(x.getAttributes().get(LAST_PONG_TIME)+"") > SESSION_TIME_OUT){
                if (x.getAttributes().get(FAILURE_COUNT_KEY) == null){
                    x.getAttributes().put(FAILURE_COUNT_KEY,1);
                    return false;
                }
                return Integer.parseInt(x.getAttributes().get(FAILURE_COUNT_KEY).toString()) >= MAX_FAILURE_COUNT;
            }else {
                x.getAttributes().remove(FAILURE_COUNT_KEY);
            }
            return false;
        }).peek(s->{
            try {
                s.close();
            } catch (IOException e) {
                //time out
            }
        }).count();
        if (log.isInfoEnabled()){
            log.debug("清理了{}过期的session",count);
        }
    }


    public void setWebSocketSessionGroup(WebSocketSessionGroup webSocketSessionGroup) {
        this.webSocketSessionGroup = webSocketSessionGroup;
    }

    public static void setEnable(boolean enable) {
        Heartbeats.enable = enable;
    }

    public static boolean isEnable() {
        return enable;
    }

    public interface WebSocketSessionGroup{

        /**
         * 获取session
         * @return List<WebSocketSession>
         */
        List<WebSocketSession> getSessions();

    }


}
