package com.zxb.prjms.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.zxb.prjms.bean.Employee;
/** 
*@Title WebSocketInterceptor.java 
*@description:  WebSocket ÊÊÅäÆ÷ À¹½ØÆ÷
* 
**/
public class WebSocketInterceptor implements HandshakeInterceptor {
	
	private final static Logger log=Logger.getLogger(WebSocketInterceptor.class);
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception exceptions) {
		// TODO Auto-generated method stub
		log.info("=================Ö´ÐÐ afterHandshake £ºhandler: "+handler+"exceptions: "+exceptions);
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		log.info("==================Ö´ÐÐ beforeHandshake £ºhandler: "+handler+"map: "+map.values());
		if(request instanceof ServerHttpRequest){
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request; 
            HttpSession session = servletRequest.getServletRequest().getSession();
            if(session!=null){
            	Employee user=(Employee)session.getAttribute("LOGIN_EMP");
                map.put(user.getAccountid(), user);
            }
		}
		return true;
	}

}
