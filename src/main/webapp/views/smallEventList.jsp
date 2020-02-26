<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="col-md-5 pull-right" >
<!-- 	<h3>动态信息</h3>
	<div style="width:550px;height:150px;overflow-y:auto;overflow-x:hidden;">
	<ul id="event_ul">
	  
	</ul>
	</div>
</div> -->
<div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">动态信息</h3>
  </div>
  <div class="panel-body">
    <div style="width:420px;height:150px;overflow-y:auto;overflow-x:hidden;">
	<ul id="event_ul">
	  
	</ul>
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
					.prependTo("#event_ul");
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
			var newEvent = result.extend.newEvent;
			$.each(newEvent,function(index,item){
				 $("<li></li>").append(timestampToTime_Event(item.operatetime)+":")
					.append(item.employeeid+" ")
					.append(item.eventtype+" ")
					.append(item.eventcontent)
					.prependTo("#event_ul");
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


</script>
</body>
</html>