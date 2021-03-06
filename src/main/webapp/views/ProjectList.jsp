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
<script src="${APP_PATH}/static/wangEditor.js"></script>
<script src="${APP_PATH}/static/wangEditor.min.js"></script>
</head>
<body>
<!-- 任务详情模态框 -->
<div class="modal fade" id="prjDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="prjId"></h4>
      </div>
       <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
           <!--  <dl class="dl-horizontal">
			  <dt>ModuleName:</dt>
			  <dd id="moduleName_detail"></dd>
			</dl> -->
		     <label class="col-sm-2 control-label">ProjectName</label>
		    <div class="col-sm-5">
		       <input class="form-control" type="text" id="prjName_detail" readonly>
		    </div> 
		  </div>
		   <div class="form-group">
		    <label class="col-sm-2 control-label">Progress</label>
		    <div class="col-sm-5">
		       <input class="form-control" type="text" id="process_detail" readonly>
		    </div> 
		  </div>
		  <div class="form-group">
		   <label class="col-sm-2 control-label">Creator</label>
		    <div class="col-sm-5">
		       <input class="form-control" type="text" id="creator_detail" readonly>
		    </div> 
		  </div>
		  <div class="form-group">
		   <label class="col-sm-2 control-label">Assign</label>
		    <div class="col-sm-5">
		       <input class="form-control" type="text" id="assign_detail" readonly>
		    </div>
		  </div>
		  <div class="form-group">
		   <label class="col-sm-2 control-label">CreateTime</label>
		    <div class="col-sm-5">
		       <input class="form-control" type="text" id="createTime_detail" readonly>
		    </div>
		  </div>
		  <div class="form-group">
		   <label class="col-sm-2 control-label">FinishTime</label>
		    <div class="col-sm-5">
		       <input class="form-control" type="text" id="finishTime_detail" readonly>
		    </div>
		  </div>
		  <div class="form-group">
		   <label class="col-sm-2 control-label">Status</label>
		    <div class="col-sm-5">
		       <input class="form-control" type="text" id="status_detail" readonly>
		    </div>
		  </div>
		  <div class="form-group">
		   <label class="col-sm-2 control-label">项目描述</label>
		    <div class="col-sm-10" id="editor_detail">
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 指派模态框 -->
<div class="modal fade" id="empAssignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">请选择指派人：</h4>
      </div>
       <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-4 control-label">Department</label>
		    <div class="col-sm-8">
		    	<!-- 提交id即可 -->
		     	<select class="form-control" name="id" id="dept_assign_select" onclick="selectOnchangAssign(this)"> 
				</select>
		    </div>
		  </div>
		   <div class="form-group">
		    <label class="col-sm-4 control-label">Assignment</label>
		    <div class="col-sm-8">
		      <!-- 提交id即可 -->
		     	<select class="form-control" name="headerid" id="emp_assign_select">
				 	
				</select>
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="emp_assign_btn">指派</button>
      </div>
    </div>
  </div>
</div>
<!-- 项目修改模态框 -->
<div class="modal fade" id="prjUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改项目</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">ProjectName</label>
		    <div class="col-sm-5">
		      <input type="text" name="projectname" class="form-control" id="projectName_update_input" readonly>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Assign</label>
		    <div class="col-sm-5">
		      <input type="text" name="headerid" class="form-control" id="prjLeader_update_input" readonly>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">项目描述</label>
		    <div class="col-sm-10" id="editorUpdate">
		      <!-- <input type="text" name="headerid" class="form-control" id="empName_add_input" placeholder="headerid"> -->
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="prj_update_btn">更新</button>
      </div>
    </div>
  </div>
</div>
<!-- 新建项目模态框 -->
<div class="modal fade" id="prjAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新建项目</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">ProjectName</label>
		    <div class="col-sm-5">
		      <input type="text" name="projectname" class="form-control" id="projectName_add_input" placeholder="projectname">
		    </div>
		  </div>
		 <!--  <div class="form-group">
		    <label class="col-sm-2 control-label">Assign</label>
		    <div class="col-sm-5">
		      <input type="text" name="headerid" class="form-control" id="prjLeader_add_input" placeholder="headerid">
		    </div>
		  </div> -->
		  <div class="form-group">
		    <label class="col-sm-2 control-label">项目描述</label>
		    <div class="col-sm-10" id="editorAdd">
		      <!-- <input type="text" name="headerid" class="form-control" id="empName_add_input" placeholder="headerid"> -->
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="prj_save_btn">保存</button>
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
			<div class="col-md-6">
				<ul class="nav nav-pills nav-justified">
				 <li role="presentation" class="active"><a href="${APP_PATH}/Prj/PrjList">项目总览</a></li>
				  <li role="presentation"><a href="${APP_PATH}/Prj/ModList">模块</a></li>
				  <li role="presentation"><a href="${APP_PATH}/Prj/TaskList">任务</a></li>
				  <li role="presentation"><a href="${APP_PATH}/Prj/EventList">动态</a></li>
				</ul>
			</div>
			
		</div>

		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-6">
				<button class="btn btn-primary" id="prj_all_btn">全部项目</button>
				<button class="btn btn-primary" id="prj_doing_btn">未完成</button>
				<button class="btn btn-primary" id="prj_wait_btn">待审核</button>
				<button class="btn btn-primary" id="prj_finish_btn">审核通过</button>
				<button class="btn btn-primary" id="prj_allfinish_btn">已完成</button>
			</div>
			<div class="col-md-4 col-md-offset-10">
				<button class="btn btn-primary" id="prj_add_modal_btn">新建项目</button>
				<!-- <button class="btn btn-danger">删除</button> -->
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="prjs_table">
					<thead>
						<tr>
							<th>#</th>
							<th>projectName</th>
							<th>Assign</th>
							<th>createTime</th>
							<th>finishTime</th>
							<th>status</th>
							<th>progress</th>
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
 		var E = window.wangEditor;
 		var editorAdd = new E('#editorAdd');
		var editorUpdate = new E('#editorUpdate');
		var editorDetail = new E('#editor_detail');
		editorAdd.customConfig.uploadImgShowBase64 = true 
		editorUpdate.customConfig.uploadImgShowBase64 = true 
		editorAdd.create();
		editorUpdate.create();
		editorDetail.create();
		
		//1、页面加载完成以后，直接去发送一个Ajax请求，要到分页数据
		$(function(){
			//去首页
			to_page(1);
		});
		//查询所有项目
		function to_page(pn){
			$.ajax({
				url:"${APP_PATH}/prjs",
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
		//查询未完成项目
		function to_pageDoing(pn){
			$.ajax({
				url:"${APP_PATH}/prjs",
				data:"pn="+pn+"&status=未完成",
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
		//查询未完成项目
		function to_pageWait(pn){
			$.ajax({
				url:"${APP_PATH}/prjs",
				data:"pn="+pn+"&status=待审核",
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
		//查询审核通过项目
		function to_pageFinish(pn){
			$.ajax({
				url:"${APP_PATH}/prjs",
				data:"pn="+pn+"&status=审核通过",
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
		//查询已完成项目
		function to_pageAllFinish(pn){
			$.ajax({
				url:"${APP_PATH}/prjs",
				data:"pn="+pn+"&status=已完成",
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
			$("#prjs_table tbody").empty();
			var prjs = result.extend.pageInfo.list;
			$.each(prjs,function(index,item){
				var prjIdTd = $("<td></td>").append(item.id);
				var prjNameTd = $("<td></td>").append($("<button></button>").append(item.projectname).addClass("btn btn-link detail_btn").attr("detail-id",item.id));
				var leaderTd = $("<td></td>").append(item.headerid);
				var createTimeTd = $("<td></td>").append(timestampToTime(item.createtime));
				var finishTimeTd = $("<td></td>").append(timestampToTime(item.finishtime));
				var statusTd = $("<td></td>").append(item.status);
				var processTd = $("<td></td>").append($("<div></div>").addClass("progress").append($("<div></div>").addClass("progress-bar").attr("role","progressbar").attr("aria-valuenow",item.process).attr("aria-valuemin","0").attr("aria-valuemax","100").attr("style","width: "+item.process+"%;").append(item.process+"%")));
				var returnBtn = $("<button></button>").addClass("btn btn-danger btn-sm return_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("驳回");
				//为编辑按钮添加一个自定义的属性，表示当前项目id
				returnBtn.attr("return-id",item.id);
				var okBtn = $("<button></button>").addClass("btn btn-success btn-sm ok_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-ok")).append("通过");
				//为编辑按钮添加一个自定义的属性，表示当前项目id
				okBtn.attr("ok-id",item.id);
				var finishBtn = $("<button></button>").addClass("btn btn-success btn-sm finish_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-ok")).append("完成");
				//为编辑按钮添加一个自定义的属性，表示当前项目id
				finishBtn.attr("finish-id",item.id);
				var assignBtn = $("<button></button>").addClass("btn btn-info btn-sm assign_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-hand-right")).append("指派");
				//为指派按钮添加一个自定义的属性，表示当前模块id
				assignBtn.attr("assign-id",item.id);
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个自定义的属性，表示当前项目id
				editBtn.attr("edit-id",item.id);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个自定义属性，表示当前删除项目id
				delBtn.attr("del-id",item.id);
				if(item.status == "已完成"||item.status == "未完成"){
					finishBtn.attr("disabled","disabled");
					editBtn.attr("disabled","disabled");
					okBtn.attr("disabled","disabled");
					returnBtn.attr("disabled","disabled");
				}
				if(item.status == "待审核"){
					finishBtn.attr("disabled","disabled");
					assignBtn.attr("disabled","disabled");
				}
				if(item.status == "审核通过"){
					okBtn.attr("disabled","disabled");
					returnBtn.attr("disabled","disabled");
					editBtn.attr("disabled","disabled");
					assignBtn.attr("disabled","disabled");
				}
				var btnTd = $("<td></td>").append(returnBtn).append(" ").append(okBtn).append(" ").append(finishBtn).append(" ").append(assignBtn).append(" ").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(prjIdTd)
					.append(prjNameTd)
					.append(leaderTd)
					.append(createTimeTd)
					.append(finishTimeTd)
					.append(statusTd)
					.append(processTd)
					.append(btnTd)
					.appendTo("#prjs_table tbody");
			});
		}
		//详情页
		$(document).on("click",".detail_btn",function(){
			 $.ajax({
					url:"${APP_PATH}/prj/"+$(this).attr("detail-id"),
					type:"GET",
					success:function(result){
						//console.log(result);
						var prjData = result.extend.prj;
						$("#prjId").text(prjData.id);
						$("#prjName_detail").val(prjData.projectname);
						$("#process_detail").val(prjData.process);
						$("#creator_detail").val(prjData.creatorid);
						$("#assign_detail").val(prjData.headerid);
						$("#createTime_detail").val(timestampToTime(prjData.createtime));
						$("#finishTime_detail").val(timestampToTime(prjData.finishtime));
						$("#status_detail").val(prjData.status);
						document.getElementById("editor_detail").innerHTML=prjData.content;
						//$("#editor_detail").val(editorDetail.txt.html(modData.content));
						//editorDetail.txt.html(taskData.content);
					}
				}) 
				$("#prjDetailModal").modal({
					backdrop:"static"
				});
			});
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
		// 显示所有项目
		$("#prj_all_btn").click(function(){
			to_page(1);
		}); 
		// 显示未完成项目
		$("#prj_doing_btn").click(function(){
			to_pageDoing(1);
		}); 
		// 显示待审核项目
		$("#prj_wait_btn").click(function(){
			to_pageWait(1);
		}); 
		// 显示未完成项目
		$("#prj_finish_btn").click(function(){
			to_pageFinish(1);
		}); 
		// 显示未完成项目
		$("#prj_allfinish_btn").click(function(){
			to_pageAllFinish(1);
		}); 
		//点击新建项目按钮弹出模态框
	 	 $("#prj_add_modal_btn").click(function(){
			//弹出模态框
			$("#prjAddModal").modal({
				backdrop:"static"
			});
		}); 
	 	$("#prj_save_btn").click(function(){
			//1、模态框中填写的表单数据提交给服务器进行保存
			//2、发送ajax请求保存员工
			//document.getElementById('editor').addEventListener('click', function () {
		        // 读取 text
		       // alert(editor.txt.text())
		    //}, false)
			//alert($("#prjAddModal form").serialize()+"&content="+editor.txt.html());
			$.ajax({
				
				url:"${APP_PATH}/prj",
				type:"POST",
				data:$("#prjAddModal form").serialize()+"&content="+encodeURIComponent(editorAdd.txt.html()),
				success:function(result){
					if(result.code == 100){
						//项目保存成功
						//1、关闭模态框
						 $("#prjAddModal").modal('hide');
						//2、调整最后一页，显示保存的数据
						//发送ajax请求显示最后一页数据
						to_page(totalPages +1); 
					}else{
						alert(result.extend.error);
					}
					
				 }  
			});
		}); 
		//1、我们是按钮创建之前绑定的click，所以绑定不上
		//1）可以在创建按钮时绑定。2）绑定单击.live()
		//jquery新版没有live，改用on
		 $(document).on("click",".edit_btn",function(){
			//查出项目信息，显示项目信息
			getPrj($(this).attr("edit-id"));
			//把项目id传递给模态框更新按钮
			$("#prj_update_btn").attr("edit-id",$(this).attr("edit-id"));
			$("#prjUpdateModal").modal({
				backdrop:"static"
			});
		});
		function getPrj(id){
			$.ajax({
				url:"${APP_PATH}/prj/"+id,
				type:"GET",
				success:function(result){
					//console.log(result);
					var prjData = result.extend.prj;
					$("#projectName_update_input").val(prjData.projectname);
					$("#prjLeader_update_input").val(prjData.headerid);
					editorUpdate.txt.html(prjData.content);
				}
			})
		} 
		//点击更新，更新项目信息
		$("#prj_update_btn").click(function(){
			//alert($("#prjUpdateModal form").serialize()+"&content="+editorUpdate.txt.html());
			//验证（后面再写）
			//发送ajax请求保存更新的员工数据
			 $.ajax({
				url:"${APP_PATH}/prj/"+$(this).attr("edit-id"),
				type:"PUT",
				data:"content="+encodeURIComponent(editorUpdate.txt.html()),
				success:function(result){
					if(result.code == 100){
						//alert(result.msg);
						//1、关闭模态框
						 $("#prjUpdateModal").modal('hide')
						//回到本页
						 to_page(currentPage);
					}else{
						alert(result.extend.error);
					}
					
				} 
				
			}) 
		})
		//点击删除，单个删除
		 $(document).on("click",".delete_btn",function(){
			//1、弹出是否确认删除对话框
			var prjName = $(this).parents("tr").find("td:eq(1)").text();
			//var accountid = $(this).attr("del-id");
			//alert($(this).parents("tr").find("td:eq(0)").text());
			 if(confirm("确认删除【"+prjName+"】吗？")){
				//确认，发送ajax请求删除
				$.ajax({
					url:"${APP_PATH}/prj/"+$(this).parents("tr").find("td:eq(0)").text(),
					type:"DELETE",
					success:function(result){
						if(result.code == 100){
							//alert(result.msg);
							to_page(currentPage);
						}else{
							alert(result.extend.error);
						}
						
					}
				});
			} 
		});
		//点击完成，把项目置为完成状态
		 $(document).on("click",".finish_btn",function(){
			//1、弹出是否确认完成对话框
			var prjName = $(this).parents("tr").find("td:eq(1)").text();
			//var accountid = $(this).attr("del-id");
			//alert($(this).parents("tr").find("td:eq(0)").text());
			 if(confirm("确认把【"+prjName+"】项目置为完成状态？")){
				//确认，发送ajax请求修改
				$.ajax({
					url:"${APP_PATH}/prj/"+$(this).parents("tr").find("td:eq(0)").text(),
					type:"PUT",
					data:"status=已完成",
					success:function(result){
						if(result.code == 100){
							//alert(result.msg);
							to_page(currentPage);
						}else{
							alert(result.extend.error);
						}
						
					}
				});
			} 
		});
		//点击指派，弹出模态框选择指派人
	 		$(document).on("click",".assign_btn",function(){
				//查出信息，并显示部门列表
				getDepts("#dept_assign_select")
				//把模块id传递给模态框更新按钮
				$("#emp_assign_btn").attr("assign-id",$(this).attr("assign-id"));
				$("#empAssignModal").modal({
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
						var optionEle = $("<option></option>").attr("value",0).appendTo(ele);
						$.each(result.extend.depts,function(){
							optionEle = $("<option></option>").append(this.departmentname).attr("value",this.id);
							optionEle.appendTo(ele);
						});
					}
				});
			}
	 		//选择项目后触发事件，查出项目所属模块信息显示在父模块下拉表上
			 function selectOnchangAssign(obj){  
		 		 var value = obj.options[obj.selectedIndex].value;
				// if(value!=0){
					 getEmps("#emp_assign_select",value);
				// }
				} 
			//发ajax请求，查出员工信息，显示在下拉列表
				function getEmps(ele,departmentid){
				//alert(parentid);
					 $(ele).empty();
					$.ajax({
						url:"${APP_PATH}/empWherePrj/"+departmentid,
						type:"GET",
						success:function(result){
							//显示部门信息在下拉列表中
							//$("#dept_add_select").append("")
							var optionEle = $("<option></option>").appendTo(ele);
							$.each(result.extend.emps,function(){
								optionEle = $("<option></option>").append(this.accountid +"*姓名："+ this.ename).attr("value",this.accountid);
								optionEle.appendTo(ele);
							});
						}
					}); 
				}
				//点击指派，指派模块负责人
		 		$("#emp_assign_btn").click(function(){
					//alert($("#prjUpdateModal form").serialize()+"&content="+editorUpdate.txt.html());
					//验证（后面再写）
					//发送ajax请求保存更新的员工数据
					//var a = $("#emp_assign_select").serialize().split("*");
					//alert($("#emp_assign_select").serialize().split("*")[0]);
					$.ajax({
						url:"${APP_PATH}/AssignPrj/"+$(this).attr("assign-id"),
						type:"PUT",
						data:$("#emp_assign_select").serialize().split("*")[0],
						success:function(result){
							if(result.code == 100){
								//alert(result.msg);
								//1、关闭模态框
								 $("#empAssignModal").modal('hide')
								//回到本页
								 to_page(currentPage);
							}else{
								alert(result.extend.error);
							}
						} 
					
					})  
				}) 
			//驳回
		 $(document).on("click",".return_btn",function(){
			//1、弹出是否确认完成对话框
			var prjName = $(this).parents("tr").find("td:eq(1)").text();
			//var accountid = $(this).attr("del-id");
			//alert($(this).parents("tr").find("td:eq(0)").text());
			 if(confirm("确认把【"+prjName+"】项目驳回？")){
				//确认，发送ajax请求修改
				$.ajax({
					url:"${APP_PATH}/prj/"+$(this).parents("tr").find("td:eq(0)").text(),
					type:"PUT",
					data:"status=未完成",
					success:function(result){
						if(result.code == 100){
							//alert(result.msg);
							to_page(currentPage);
						}else{
							alert(result.extend.error);
						}
						
					}
				});
			} 
		});
			//通过
		 $(document).on("click",".ok_btn",function(){
			//1、弹出是否确认完成对话框
			var prjName = $(this).parents("tr").find("td:eq(1)").text();
			//var accountid = $(this).attr("del-id");
			//alert($(this).parents("tr").find("td:eq(0)").text());
			 if(confirm("确认把【"+prjName+"】项目审核通过？")){
				//确认，发送ajax请求修改
				$.ajax({
					url:"${APP_PATH}/prj/"+$(this).parents("tr").find("td:eq(0)").text(),
					type:"PUT",
					data:"status=审核通过",
					success:function(result){
						if(result.code == 100){
							//alert(result.msg);
							to_page(currentPage);
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