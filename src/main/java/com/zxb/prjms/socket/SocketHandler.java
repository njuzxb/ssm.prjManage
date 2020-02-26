package com.zxb.prjms.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.zxb.prjms.bean.Employee;
@Service
public class SocketHandler implements WebSocketHandler {
	
	private final static Logger log=Logger.getLogger(SocketHandler.class); 
	private final static Map<String,WebSocketSession> userMap; 
	//在线用户
	static {
		userMap = new HashMap<String,WebSocketSession>(); 
		
	}
	
	/**
     * 关闭连接后
     */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		log.info("Websocket："+session.getId()+"已经关闭！");
		String userId=this.getUserId(session); 
		if(userMap.get(userId) != null){ 
			userMap.remove(userId); 
		log.info("Websocket："+userId+"用户已经移除！"); 
		
		}

	}
	  /**
     * 连接成功后
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		 String userId=this.getUserId(session); 
		 if(userMap.get(userId) == null){
			 userMap.put(userId, session); 
			 log.info("Websocket："+userId+"用户建立连接成功！");
		}else{ 
			log.info("Websocket："+userId+"用户已经连接！"); 
			}
	
	}
	 /**
     * 发送信息时， 调用此方法传输数据
     */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		session.sendMessage(message);  
        log.info("Websocket：handleMessage send message："+message);
	}
	 /**
     * 发送异常时，调用此方法
     */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable error) throws Exception {
		// TODO Auto-generated method stub
		log.error("Websocket：handleMessage send message："+error);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * @Title: sendMessageToUser 
     * @Description: 给某个用户发送信息
     * @param @param user_id
     * @param @param message
     * @return void
     * @date createTime：2019年4月19
     */
	 public void sendMessageToUser(String user_id, TextMessage message) {
	        WebSocketSession session = userMap.get(user_id);  
	        if(session !=null && session.isOpen()) {  
	            try {  
	                session.sendMessage(message);  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }
	 }
	 /**
     * @Title: sendMessageToUsers 
     * @Description: 给所有用户发送信息
     * @param @param message
     * @return void
     * @date createTime：2019年4月19
     */
	 public void sendMessageToUsers(TextMessage message) {
	        Set<String> userIds = userMap.keySet();  
	        for(String userId: userIds) {  
	            this.sendMessageToUser(userId, message);  
	        }  
	 }
	 /**
     * @Title: getUserId 
     * @Description: 获取用户id
     * @param @param session
     * @param @return
     * @return String
     * @date createTime：2019年4月19
     */
	 public String getUserId(WebSocketSession session){  
	        try {  
	        	return ((Employee)session.getAttributes().get("LOGIN_EMP")).getAccountid();
	        }catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	 }
}
