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
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 部门修改模态框 -->
<div class="modal fade" id="deptUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">部门修改</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		 <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-10">
		      <input type="text" name="departmentname" class="form-control" id="deptName_update_input" placeholder="departmentname">
		    </div>
		  </div>
		   <div class="form-group">
		    <label class="col-sm-2 control-label">Manager</label>
		    <div class="col-sm-10">
		      <input type="text" name="managerid" class="form-control" id="deptManager_update_input" placeholder="managerid">
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="dept_update_btn">更新</button>
      </div>
    </div>
  </div>
</div>
<!-- 员工添加模态框 -->
<div class="modal fade" id="deptAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">设立新部门</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-10">
		      <input type="text" name="departmentname" class="form-control" id="deptName_add_input" placeholder="departmentname">
		    </div>
		  </div>
		   <div class="form-group">
		    <label class="col-sm-2 control-label">Manager</label>
		    <div class="col-sm-10">
		      <input type="text" name="managerid" class="form-control" id="deptManager_add_input" placeholder="managerid">
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="dept_save_btn">保存</button>
      </div>
    </div>
  </div>
</div>
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
			<jsp:include page="/views/smallEventList.jsp"></jsp:include>
			<jsp:include page="/Navigation.jsp"></jsp:include>
			<div class="col-md-3">
				<ul class="nav nav-pills nav-justified">
				  <li role="presentation"><a href="${APP_PATH}/Org/EmpList">员工管理</a></li>
				   <li role="presentation" class="active"><a href="${APP_PATH}/Org/DeptList">部门管理</a></li>
				</ul>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-10">
				<button class="btn btn-primary" id="dept_add_modal_btn">新增</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="depts_table">
					<thead>
						<tr>
							<th>#</th>
							<th>deptName</th>
							<th>managerId</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6" id="page_info_area">
				
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area">
				
			</div>
		</div>
	
	
	</div>
	<script type="text/javascript">
		var totalPages;
		var currentPage;
		//1、页面加载完成以后，直接去发送一个Ajax请求，要到分页数据
		$(function(){
			//去首页
			to_page();
		});
		
		function to_page(){
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"GET",
				success:function(result){
					//console.log(result);
					//1、解析并显示部门数据
					build_depts_table(result);
					//2、解析并显示分页信息
					//build_page_info(result);
					//3、解析显示分页条
					//build_page_nav(result);
				}
			});
		}
		
		function build_depts_table(result){
			//清空table表
			$("#depts_table tbody").empty();
			var depts = result.extend.depts;
			$.each(depts,function(index,item){
				var deptIdTd = $("<td></td>").append(item.id);
				var deptNameTd = $("<td></td>").append(item.departmentname);
				var managerIdTd = $("<td></td>").append(item.managerid);
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm dept_edit_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个自定义的属性，表示当前部门id
				editBtn.attr("dept_edit-id",item.id);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm dept_delete_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个自定义属性，表示当前删除部门id
				delBtn.attr("dept_del-id",item.id);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(deptIdTd)
					.append(deptNameTd)
					.append(managerIdTd)
					.append(btnTd)
					.appendTo("#depts_table tbody");
			});
		}
		//点击新增按钮弹出模态框
	 	$("#dept_add_modal_btn").click(function(){
			//弹出模态框
			$("#deptAddModal").modal({
				backdrop:"static"
			});
		}); 
		$("#dept_save_btn").click(function(){ 
			//1、模态框中填写的表单数据提交给服务器进行保存
			//2、发送ajax请求保存部门
			//alert($("#deptAddModal form").serialize()); 
			 $.ajax({
				
				url:"${APP_PATH}/dept",
				type:"POST",
				data:$("#deptAddModal form").serialize(),
				success:function(result){
					if(result.code == 100){
						//部门保存成功
						//1、关闭模态框
						 $("#deptAddModal").modal('hide');
						 to_page();
					}else{
						alert(result.extend.error);
					}
					
				 }  
			}); 
		}); 
		//1、我们是按钮创建之前绑定的click，所以绑定不上
		//1）可以在创建按钮时绑定。2）绑定单击.live()
		//jquery新版没有live，改用on
		$(document).on("click",".dept_edit_btn",function(){
			//alert("edit"); 
			//查出部门信息，显示部门信息
			getDept($(this).attr("dept_edit-id"));
			//把员工id传递给模态框更新按钮
			$("#dept_update_btn").attr("dept_edit-id",$(this).attr("dept_edit-id"));
			$("#deptUpdateModal").modal({
				backdrop:"static"
			});
		}); 
		function getDept(id){
			$.ajax({
				url:"${APP_PATH}/dept/"+id,
				type:"GET",
				success:function(result){
					//console.log(result);
					var deptData = result.extend.dept;
					$("#deptName_update_input").val(deptData.departmentname);
					$("#deptManager_update_input").val(deptData.managerid);
				}
			})
		}
		//点击更新，更新部门信息
		$("#dept_update_btn").click(function(){
			//验证（后面再写）
			//发送ajax请求保存更新的部门数据
			$.ajax({
				url:"${APP_PATH}/dept/"+$(this).attr("dept_edit-id"),
				type:"PUT",
				data:$("#deptUpdateModal form").serialize(),
				success:function(result){
					if(result.code == 100){
						//alert(result.msg);
						//1、关闭模态框
						 $("#deptUpdateModal").modal('hide')
						//回到本页
						 to_page();
					}else{
						alert(result.extend.error);
					}
					
				}
				
			})
		})
		//点击删除，单个删除
		$(document).on("click",".dept_delete_btn",function(){
			//1、弹出是否确认删除对话框
			var deptName = $(this).parents("tr").find("td:eq(1)").text();
			var id = $(this).parents("tr").find("td:eq(0)").text();
			//var accountid = $(this).attr("del-id");
			//alert($(this).parents("tr").find("td:eq(0)").text());
			if(confirm("确认删除【"+deptName+"】吗？")){
				//确认，发送ajax请求删除
				$.ajax({
					url:"${APP_PATH}/dept/"+id,
					type:"DELETE",
					success:function(result){
						if(result.code == 100){
							alert(result.msg);
							to_page();
						}else{
							alert(result.extend.error);
						}
						
					}
				});
			}
		});
	</script>

</body>
</html>