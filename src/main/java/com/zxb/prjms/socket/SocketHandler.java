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
	//�����û�
	static {
		userMap = new HashMap<String,WebSocketSession>(); 
		
	}
	
	/**
     * �ر����Ӻ�
     */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		log.info("Websocket��"+session.getId()+"�Ѿ��رգ�");
		String userId=this.getUserId(session); 
		if(userMap.get(userId) != null){ 
			userMap.remove(userId); 
		log.info("Websocket��"+userId+"�û��Ѿ��Ƴ���"); 
		
		}

	}
	  /**
     * ���ӳɹ���
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		 String userId=this.getUserId(session); 
		 if(userMap.get(userId) == null){
			 userMap.put(userId, session); 
			 log.info("Websocket��"+userId+"�û��������ӳɹ���");
		}else{ 
			log.info("Websocket��"+userId+"�û��Ѿ����ӣ�"); 
			}
	
	}
	 /**
     * ������Ϣʱ�� ���ô˷�����������
     */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		session.sendMessage(message);  
        log.info("Websocket��handleMessage send message��"+message);
	}
	 /**
     * �����쳣ʱ�����ô˷���
     */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable error) throws Exception {
		// TODO Auto-generated method stub
		log.error("Websocket��handleMessage send message��"+error);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * @Title: sendMessageToUser 
     * @Description: ��ĳ���û�������Ϣ
     * @param @param user_id
     * @param @param message
     * @return void
     * @date createTime��2019��4��19
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
     * @Description: �������û�������Ϣ
     * @param @param message
     * @return void
     * @date createTime��2019��4��19
     */
	 public void sendMessageToUsers(TextMessage message) {
	        Set<String> userIds = userMap.keySet();  
	        for(String userId: userIds) {  
	            this.sendMessageToUser(userId, message);  
	        }  
	 }
	 /**
     * @Title: getUserId 
     * @Description: ��ȡ�û�id
     * @param @param session
     * @param @return
     * @return String
     * @date createTime��2019��4��19
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
