<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SSM-项目信息管理系统</title>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
</head>
<body>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-项目信息管理系统</h1>
			</div>
		</div>
		<div class="form row">	
			<form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form">
			  <div class="form-group">
				<h3>请登陆</h3>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">员工ID</label>
			    <div class="col-sm-6">
			      <input name="accountid" type="text" class="form-control" id="input_accountid" placeholder="请输入员工ID">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input name="password" type="password" class="form-control" id="input_password" placeholder="password">
			      <span class="help-block"></span>
			    </div>
			  </div>
			</form>
			<div class="form-group">
			    <div class="col-sm-offset-4 col-sm-2">
			      <button type="login" class="btn btn-default" id="login_btn">登录</button>
			    </div>
		  </div>
		</div>

	</div>
	<script type="text/javascript">
	$("#login_btn").click(function(){ 
		//1、先进行校验
		//2、发送ajax请求登陆
		$.ajax({
			url:"${APP_PATH}/login",
			type:"POST",
			data:$("#login_form").serialize(),
			success:function(result){
				if(result.code==200){
					show_validate_msg("#input_accountid","error","用户名或密码错误！");
				} else if(result.code == 100){
					window.location="${APP_PATH}/My/MyList";
				}
			 }  
		});  
	});
	function show_validate_msg(ele,status,msg){
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text(" ");
		if("success"==status){
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		}
		else if("error"==status){
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	}
	</script>
	
</body>
</html> 