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
<!-- 员工修改模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">员工修改</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">accountId</label>
		    <div class="col-sm-10">
		      <p class="form-control-static" id="accountid_update_static"></p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">password</label>
		    <div class="col-sm-10">
		      <input type="text" name="password" class="form-control" id="password_update_input" placeholder="password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      <input type="text" name="ename" class="form-control" id="empName_update_input" placeholder="ename">
		    </div>
		  </div>
		   <div class="form-group">
		    <label class="col-sm-2 control-label">Job</label>
		    <div class="col-sm-10">
		      <input type="text" name="empJob" class="form-control" id="empJob_update_input" placeholder="empJob">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">power</label>
		    <div class="col-sm-4">
		    	<!-- 权限 -->
		     	<select class="form-control" name="power" id="power_update_select">
				 	<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		     	<select class="form-control" name="departmentid" id="dept_update_select">
				 	
				</select>
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
      </div>
    </div>
  </div>
</div>
<!-- 员工添加模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">accountId</label>
		    <div class="col-sm-10">
		      <input type="text" name="accountid" class="form-control" id="accountid_add_input" placeholder="输入员工账号ID">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      <input type="text" name="ename" class="form-control" id="empName_add_input" placeholder="员工姓名">
		    </div>
		  </div>
		   <div class="form-group">
		    <label class="col-sm-2 control-label">Job</label>
		    <div class="col-sm-10">
		      <input type="text" name="empJob" class="form-control" id="empJob_add_input" placeholder="担任职位">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">power</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		     	<select class="form-control" name="power" id="power_add_select">
				 	<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		     	<select class="form-control" name="departmentid" id="dept_add_select">
				 	
				</select>
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
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
				  <li role="presentation" class="active"><a href="${APP_PATH}/Org/EmpList">员工管理</a></li>
				  <li role="presentation"><a href="${APP_PATH}/Org/DeptList">部门管理</a></li>
				</ul>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-10">
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>accountId</th>
							<th>empName</th>
							<th>job</th>
							<th>power</th>
							<th>deptName</th>
							<th>lastLogin</th>
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
			to_page(1);
		});
		
		function to_page(pn){
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn="+pn,
				type:"GET",
				success:function(result){
					//console.log(result);
					//1、解析并显示员工数据
					build_emps_table(result);
					//2、解析并显示分页信息
					build_page_info(result);
					//3、解析显示分页条
					build_page_nav(result);
				}
			});
		}
		
		function build_emps_table(result){
			//清空table表
			$("#emps_table tbody").empty();
			var emps = result.extend.pageInfo.list;
			$.each(emps,function(index,item){
				var accountidTd = $("<td></td>").append(item.accountid);
				var nameTd = $("<td></td>").append(item.ename);
				var jobTd = $("<td></td>").append(item.empJob);
				var powerTd = $("<td></td>").append(item.power);
				var deptNameTd = $("<td></td>").append(item.department==null?null:item.department.departmentname);
				var lastLoginTd = $("<td></td>").append(timestampToTime(item.lastlogin));
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个自定义的属性，表示当前员工id
				editBtn.attr("edit-id",item.accountid);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个自定义属性，表示当前删除员工id
				delBtn.attr("del-id",item.accountid);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(accountidTd)
					.append(nameTd)
					.append(jobTd)
					.append(powerTd)
					.append(deptNameTd)
					.append(lastLoginTd)
					.append(btnTd)
					.appendTo("#emps_table tbody");
			});
		}
		//时间转换函数
		function timestampToTime(timestamp) {
		   if(timestamp == null) return null;  
		   var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
		   Y = date.getFullYear() + '-';
		   M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		   D = date.getDate() + ' ';
		   h = date.getHours() + ':';
		   m = date.getMinutes() + ':';
		   s = date.getSeconds();
		   return Y+M+D;
		}
		//解析显示分页信息
		function build_page_info(result){
			$("#page_info_area").empty();
			$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页，总"+result.extend.pageInfo.pages
										+"页，总"+result.extend.pageInfo.total+"条记录");
			totalPages = result.extend.pageInfo.pages;
			currentPage = result.extend.pageInfo.pageNum;
		}
		//解析显示分页条
		function build_page_nav(result){
			//page_nav_area
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			//构建元素
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var perPageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			/* if(result.extend.pageInfo.hasPreviousPage == true) */
			//为元素添加点击翻页事件
			firstPageLi.click(function(){
				to_page(1);
			});
			perPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum -1);
			});
			var nextPageLi= $("<li></li>").append($("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
			});
			nextPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum +1);
			});
			//添加首页和前一页的提示
			ul.append(firstPageLi).append(perPageLi);
			//1，2，3，。。。遍历ul中添加页码提示
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
					
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if(result.extend.pageInfo.pageNum == item){
					numLi.addClass("active");
				}
				numLi.click(function(){
					to_page(item);
				});
				ul.append(numLi);
			});
			//添加下一页和末页的提示
			ul.append(nextPageLi).append(lastPageLi);
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		}
		//点击新增按钮弹出模态框
		$("#emp_add_modal_btn").click(function(){
			$("#accountid_add_input").parent().removeClass("has-success has-error");
			$("#accountid_add_input").next("span").text("");
			//发ajax请求，查出部门信息，显示在下拉列表
			getDepts("#dept_add_select");
			//弹出模态框
			$("#empAddModal").modal({
				backdrop:"static"
			});
		});
		//发ajax请求，查出部门信息，显示在下拉列表
		function getDepts(ele){
			$(ele).empty();
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"GET",
				success:function(result){
					//显示部门信息在下拉列表中
					//$("#dept_add_select").append("")
					$.each(result.extend.depts,function(){
						var optionEle = $("<option></option>").append(this.departmentname).attr("value",this.id);
						optionEle.appendTo(ele);
					});
				}
			});
		}
		$("#emp_save_btn").click(function(){
			//1、模态框中填写的表单数据提交给服务器进行保存
			//2、发送ajax请求保存员工
			/* alert($("#empAddModal form").serialize()); */
			$.ajax({
				
				url:"${APP_PATH}/emp",
				type:"POST",
				data:$("#empAddModal form").serialize(),
				success:function(result){
					if(result.code == 100){
						//员工保存成功
						//1、关闭模态框
						 $("#empAddModal").modal('hide');
						//2、调整最后一页，显示保存的数据
						//发送ajax请求显示最后一页数据
						to_page(totalPages +1); 
					}
					else{
						//显示错误信息
						//console.log(result);
						if(result.extend.error == "账号ID已存在！"){
							show_validate_msg("#accountid_add_input","error",result.extend.error);
						}else{
							alert(result.extend.error);
						}
						
						
					}
					
				 } 
			}); 
		});
		function show_validate_msg(ele,status,msg){
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if("success"==status){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}
			else if("error"==status){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		//1、我们是按钮创建之前绑定的click，所以绑定不上
		//1）可以在创建按钮时绑定。2）绑定单击.live()
		//jquery新版没有live，改用on
		$(document).on("click",".edit_btn",function(){
			//alert("edit"); 
					
			//查出部门信息，并显示部门列表
			getDepts("#dept_update_select")
			//查出员工信息，显示员工信息
			getEmp($(this).attr("edit-id"));
			//把员工id传递给模态框更新按钮
			$("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
			$("#empUpdateModal").modal({
				backdrop:"static"
			});
		});
		function getEmp(accountid){
			$.ajax({
				url:"${APP_PATH}/emp/"+accountid,
				type:"GET",
				success:function(result){
					//console.log(result);
					if(result.code == 100){
						var empData = result.extend.emp;
						$("#accountid_update_static").text(empData.accountid);
						$("#password_update_input").val(empData.password);
						$("#empName_update_input").val(empData.ename);
						$("#empJob_update_input").val(empData.empJob);
						$("#power_update_select").val([empData.power]);
						$("#dept_update_select").val([empData.departmentid]);
					}
					else{
						alert(result.extend.error);
						 $("#empUpdateModal").modal('hide')
					}
				}
			})
		}
		//点击更新，更新员工信息
		$("#emp_update_btn").click(function(){
			//验证（后面再写）
			//发送ajax请求保存更新的员工数据
			$.ajax({
				url:"${APP_PATH}/emp/"+$(this).attr("edit-id"),
				type:"PUT",
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
					if(result.code == 100){
						//alert(result.msg);
						//1、关闭模态框
						 $("#empUpdateModal").modal('hide')
						//回到本页
						 to_page(currentPage);
					}
					else{
						alert(result.extend.error);
						
					}
					
				}
				
			})
		})
		//点击删除，单个删除
		$(document).on("click",".delete_btn",function(){
			//1、弹出是否确认删除对话框
			var accountid = $(this).parents("tr").find("td:eq(0)").text();
			//var accountid = $(this).attr("del-id");
			//alert($(this).parents("tr").find("td:eq(0)").text());
			if(confirm("确认删除【"+accountid+"】吗？")){
				//确认，发送ajax请求删除
				$.ajax({
					url:"${APP_PATH}/emp/"+accountid,
					type:"DELETE",
					success:function(result){
						if(result.code == 100){
							alert(result.msg);
							to_page(currentPage);
						}
						else{
							alert(result.extend.error);
						}
						
					}
				});
			}
		});
		
	</script>

</body>
</html>