<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.zxb.prjms.bean.Employee" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SSM-项目信息管理系统</title>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
	Employee emp = (Employee)request.getSession().getAttribute("LOGIN_EMP");
%>
<!-- web路径，
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题
以/开始的相对路径，找资源，以服务器的路径为基准，(http://localhost:3306),需要加上项目名才能找到
 -->
 <script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/static/wangEditor.js"></script>
<script src="${APP_PATH}/static/wangEditor.min.js"></script>
</head>
<body>
<!-- 搭建显示页面 -->
	<div class="container">
		<div class="row">
			<div class="col-md-7">
				<h1>SSM-项目信息管理系统</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 pull-right" >
			<%=emp.getAccountid()%>
			<a href="${APP_PATH}/outLogin" class="btn btn-warning btn-lg" role="button">退出登陆</a>
			</div>
		</div>
		<!-- 二级导航 -->
		<div class="row">
			<jsp:include page="/Navigation.jsp"></jsp:include>
			<div class="col-md-6">
				<ul class="nav nav-pills nav-justified">
				 <li role="presentation"><a href="${APP_PATH}/Prj/PrjList">项目总览</a></li>
				  <li role="presentation"><a href="${APP_PATH}/Prj/ModList">模块</a></li>
				  <li role="presentation"><a href="${APP_PATH}/Prj/TaskList">任务</a></li>
				  <li role="presentation" class="active"><a href="${APP_PATH}/Prj/EventList">动态</a></li>
				</ul>
			</div>
		</div>
		<!-- 按钮 -->
		<!-- <div class="row">
			
		</div> -->
		<div class="row">
			<div class="col-md-2">
				<button class="btn btn-primary" id="event_all_btn">全部信息</button>
				<button class="btn btn-primary" id="event_doing_btn">最近</button>
			</div>
			<div class="col-md-2">
				<form id="event_form">
				<input type="date" name="date"/>
				</form>
				<button id="event_date_btn" class="btn btn-default">查询</button>
			</div>
			
		</div>
		<!-- 显示动态信息 -->
		<div class="row">
			<h2>动态信息：</h2>
		</div>
		<div class="row">
		<div class="panel panel-default">
		  <div class="panel-body">
		    <div class="col-md-4 col-md-offset-1" style="width:1050px;height:1150px;overflow-y:auto;overflow-x:hidden;">
				<ul id="event_list">
				  
				</ul>
			</div>
		  </div>
		</div>
			
		</div>
	
	
	</div>
	<script type="text/javascript">
	$(function(){
		to_event();
	    var wsPath=wsPath();
	function wsPath(){
		var local=window.location;
	    var contextPath=local.pathname.split("/")[1];
	    return "ws://"+local.host+"/"+contextPath+"/";
	};
		//建立socket连接
		var sock;
		var lockReconnect = false,count=0;
		createWebSocket();
		
		 function createWebSocket(){
		        try {
		        	if ('WebSocket' in window) {
		                sock = new WebSocket(wsPath+"socketServer"); 
		        	}else if ('MozWebSocket' in window) {
		                sock = new MozWebSocket(wsPath+"socketServer");
		        	} else {
		                sock = new SockJS(wsPath+"sockjs/socketServer");
		        	 }
		            init();
		        } catch (e) {
		        	console.log('Ceate WebSocket Error ! Tring To RestConnection !'+e);
		            restConnect();
		        }
		 }
		 
		 function init(){
		        sock.onopen = function (e) {
		        	 heartCheck.start();
		             console.debug(" WebSocket Connection Success ! ");
		        };
		        sock.onmessage = function (e) {
		            heartCheck.reset();
		            if(e.data==""){
		                return false;
		            }
		            var socketMSG=JSON.parse(e.data);
		            //socketMSG.MSG 我这里后端传的是个json 所以这么写的
		            if(socketMSG.MSG!=undefined){
		                //e.data是获取后端传送的消息，我这里的操作时把信息拿出来放到消息栏并提示， 这里根据自己的需求修改吧。
		                //alert(socketMSG.MSG.eventtype);
		                $("<li></li>").append(socketMSG.MSG.operatetime+":")
						.append(socketMSG.MSG.employeeid+" ")
						.append(socketMSG.MSG.eventtype+" ")
						.append(socketMSG.MSG.eventcontent)
						.prependTo("#event_list");
		            }
		        };
		        sock.onerror = function (e) {
		        	console.error(" WebSocket Connection Failure ! Tring To RestConnect !"+e);
		            restConnect();
		        };
		        sock.onclose = function (e) {
		        	console.warn(" WebSocket Connection Close ! Tring To RestConnect !"+e);
		            restConnect();
		        }
		 }
		 
		 function restConnect(){
		        if(lockReconnect){
		        	 return;
		        }
		        if(count<=3){
		            createWebSocket();
		            lockReconnect=true;
		        }else{
		        	 console.error('WebSocket Connection Timeout!');
		        }
		 }
		 var heartCheck = {
	        timeout: 300000,//60ms
	        timeoutObj: null,
	        serverTimeoutObj: null,
	        reset: function(){
	            clearTimeout(this.timeoutObj);
	            clearTimeout(this.serverTimeoutObj);
	       this.start();
	        },
	        start: function(){
	            var _this = this;
	            this.timeoutObj = setTimeout(function(){
	                sock.send("");
	                _this.serverTimeoutObj = setTimeout(function(){
	                    sock.close();
	                }, _this.timeout)
	            }, this.timeout)
	        }
		 };
		//窗口关闭前,主动关闭websocket连接 
		    window.onbeforeunload = function () { 
		    	sock.close(); 
		};
	});
	function to_event(){
		$.ajax({
			url:"${APP_PATH}/newEvent",
			type:"GET",
			success:function(result){
				$("#event_list").empty();
				var newEvent = result.extend.newEvent;
				$.each(newEvent,function(index,item){
					 $("<li></li>").append(timestampToTime_Event(item.operatetime)+":")
						.append(item.employeeid+" ")
						.append(item.eventtype+" ")
						.append(item.eventcontent)
						.prependTo("#event_list");
				});
			}
		});
	}
	//时间转换函数
	function timestampToTime_Event(timestamp) {
	   if(timestamp == null) return null;  
	   var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
	   Y = date.getFullYear() + '-';
	   M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	   D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate()) + ' ';
	   h = (date.getHours() < 10 ? '0'+(date.getHours()) : date.getHours()) + ':';
	   m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
	   s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
	   return Y+M+D+h+m+s;
	}
	$("#event_date_btn").click(function(){
		
		//alert($("#event_form").serialize());
		 $.ajax({
			url:"${APP_PATH}/dayEvent",
			data:$("#event_form").serialize(),
			type:"GET",
			success:function(result){
				$("#event_list").empty();
				 var dayEvent = result.extend.dayEvent;
				$.each(dayEvent,function(index,item){
					 $("<li></li>").append(timestampToTime_Event(item.operatetime)+":")
						.append(item.employeeid+" ")
						.append(item.eventtype+" ")
						.append(item.eventcontent)
						.prependTo("#event_list");
				}); 
			} 
		});
	}); 
	$("#event_doing_btn").click(function(){
		to_event();
	}); 

	$("#event_all_btn").click(function(){
		 $.ajax({
				url:"${APP_PATH}/allEvent",
				type:"GET",
				success:function(result){
					$("#event_list").empty();
					 var allEvent = result.extend.allEvent;
					$.each(allEvent,function(index,item){
						 $("<li></li>").append(timestampToTime_Event(item.operatetime)+":")
							.append(item.employeeid+" ")
							.append(item.eventtype+" ")
							.append(item.eventcontent)
							.prependTo("#event_list");
					}); 
				} 
			});
	});
	</script>

</body>
</html>