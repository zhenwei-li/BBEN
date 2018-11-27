package com.bben.infusion.transmission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/WebRTC/acgist.video/{useid}")
@Component
public class VideoVoiceSocket {
    private static Logger log = LoggerFactory.getLogger(VideoVoiceSocket.class);
	// 最大通话数量
	private static final int MAX_COUNT = 20;
	private static final long MAX_TIME_OUT = 2 * 60 * 1000;
	// 用户和用户的对话映射
	private static final Map<String, String> user_user = Collections.synchronizedMap(new HashMap<String, String>()); 
	// 用户和websocket的session映射
	private static final Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());

    private static final AtomicInteger OnlineCount = new AtomicInteger(0);

	/**
	 * 打开websocket
	 * @param session websocket的session
	 * @param useid 打开用户的useid
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("useid")String useid) {
		session.setMaxIdleTimeout(MAX_TIME_OUT);
        System.out.println("useid:"+useid);
		sessions.put(useid, session);

        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
	}

	/**
	 * websocket关闭
	 * @param session 关闭的session
	 * @param useid 关闭的用户标识
	 */
	@OnClose
	public void onClose(Session session, @PathParam("useid")String useid) {
		remove(session, useid);
        int cnt = OnlineCount.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
	}

	/**
	 * 收到消息
	 * @param message 消息内容
	 * @param session 发送消息的session
	 * @param useid
	 */
	@OnMessage
	public void onMessage(String message, Session session, @PathParam("useid")String useid) {
        log.info("来自客户端的消息：{}", message);
		try {
			if(useid != null && user_user.get(useid) != null && VideoVoiceSocket.sessions.get(user_user.get(useid)) != null) {
				Session osession = sessions.get(user_user.get(useid)); // 被呼叫的session
				if(osession.isOpen()) {
					osession.getAsyncRemote().sendText(new String(message.getBytes("utf-8")));
				}else {
					this.nowaiting(osession);
				}
			} else {
				this.nowaiting(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

	/**
	 * 没有人在等待
	 * @param session 发送消息的session
	 */
	private void nowaiting(Session session) {
		session.getAsyncRemote().sendText("{\"type\" : \"nowaiting\"}");
	}
	
	/**
	 * 是否可以继续创建通话房间
	 * @return 可以：true；不可以false；
	 */
	public static boolean canCreate() {
		return sessions.size() <= MAX_COUNT;
	}
	
	/**
	 * 判断是否可以加入
	 * @param oid 被申请对话的ID
	 * @return 如果能加入返回：true；否则返回false；
	 */
	public static boolean canJoin(String oid) {
		return !(oid != null && user_user.containsKey(oid) && user_user.get(oid) != null);
	}
	
	/**
	 * 添加视频对象
	 * @param useid 申请对话的ID
	 * @param oid 被申请对话的ID
	 * @return 是否是创建者：如果没有申请对话ID为创建者，否则为为加入者。创建者返回：true；加入者返回：false；
	 */
	public static boolean addUser(String useid, String oid) {
		if(oid != null && !oid.isEmpty()) {
			VideoVoiceSocket.user_user.put(useid, oid);
			VideoVoiceSocket.user_user.put(oid, useid);
			
			return false;
		} else {
			VideoVoiceSocket.user_user.put(useid, null);
			
			return true;
		}
	}
	
	/**
	 * 移除聊天用户
	 * @param session 移除的session
	 * @param useid 移除的useid
	 */
	private static void remove(Session session, String useid) {
		String oid = user_user.get(useid);
		
		if(oid != null){
		    user_user.put(oid, null); // 设置对方无人聊天}
        }
		
		sessions.remove(useid); // 异常session
		user_user.remove(useid); // 移除自己
		
		try {
			if(session != null && session.isOpen()){
			    session.close(); // 关闭session}
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}