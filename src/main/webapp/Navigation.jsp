<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.zxb.prjms.bean.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
	Employee emp = (Employee)request.getSession().getAttribute("LOGIN_EMP");
%>
</head>
<body>

	<div class="col-md-7">
		<ul class="nav nav-tabs nav-justified" style="font-size:18px;">
		  <li role="presentation" id="Mytask_nav"><a href="${APP_PATH}/My/MyList">我的地盘</a></li>
		  <li role="presentation" id="Prj_nav"><a href="${APP_PATH}/Prj/PrjList">项目信息管理</a></li>
		  <li role="presentation" id="Test_nav"><a href="${APP_PATH}/Test/BugList">测试管理</a></li>
		  <li role="presentation" id="Org_nav"><a href="${APP_PATH}/Org/EmpList">组织管理</a></li>
		</ul>
		
	</div>
	<script type="text/javascript">
		$(function(){
			var url = window.location.toString();
		    var arrUrl = url.split("/");
		    var len = arrUrl.length;
			if(arrUrl[len-2]=="Org"){
				$("#Org_nav").addClass("active");
			}
			else if(arrUrl[len-2]=="Prj"){
				$("#Prj_nav").addClass("active");
			}
			else if(arrUrl[len-2]=="Test"){
				$("#Test_nav").addClass("active");
			}
			else{
				$("#Mytask_nav").addClass("active");
			}
			if(<%=emp.getPower()%> == 2 ||<%=emp.getPower()%> == 3 ||<%=emp.getPower()%> == 4){
				$("#Prj_nav").addClass("disabled");
				$("#Org_nav").addClass("disabled");
				 $("#Test_nav").addClass("disabled"); 
			}
			if(<%=emp.getPower()%> == 5){
				$("#Prj_nav").addClass("disabled");
				$("#Org_nav").addClass("disabled");
			} 
		});
	</script>
</body>
</html>