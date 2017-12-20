<!DOCTYPE html>
<%@page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<!--     <link rel="icon" href="/favicon.ico"> -->
    <link rel="icon" href="images/huiwenTitle.png">
	<title>会问后台系统</title>
<!-- 	<script src="/js/require.js"></script> -->
<!-- 	<script src="/js/main.js"></script> -->
	<script data-main="js/main" src="/js/require.js"></script>
	<script type="text/javascript" src="/js/jquery-2.2.1.js"></script>
    <link rel="stylesheet" href="${path}/js/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/css/font-awesome.css?v=4.4.0">
    <link rel="stylesheet" href="${path}/css/animate.css">
    <link rel="stylesheet" href="${path}/css/style.css">
    <link rel="stylesheet" href="${path}/css/awesome-bootstrap-checkbox.css">
    <link rel="stylesheet" href="${path}/css/common.css">
    <style type="text/css">
    	.panel-body{overflow:auto}
    </style>
</head>
<body>
		<div id="wrap">
			<!-- 左侧菜单栏目块 -->
			<div class="leftmenu" id="leftmenu">
				<div id="logoDiv">
					<p id="logoP"><img id="logo" alt="后台管理" src="images/huiwen.png"><span>后台管理</span></p>
<!-- 					<p id="logoP"><img id="logo" alt="后台管理" src="images/logo.png"><span>后台管理</span></p> -->
				</div>
<!-- 				<div id="personInfor"> -->
<%-- 					<shiro:hasRole name="admin"> --%>
<!-- 					   <p id="userName">管理员</p> -->
<!-- 					   <p><span><a href="javascript:;" id="changePasswordBtn">修改密码</a></span></p> -->
<%-- 					</shiro:hasRole> --%>
					
<%-- 					<shiro:hasRole name="user"> --%>
<!-- 					   <p id="userName">操作员</p> -->
<%-- 					</shiro:hasRole> --%>
					
<!-- 					<p> -->
<!-- 						<a href="javascript:;" id="logoutBtn">退出登录</a> -->
<!-- 					</p> -->
<!-- 				</div> -->


<!-- 				<div class="menu-item" href="javascript:;" url="/houtai/wenda/index"><img src="images/icon_card_grey.png">问答管理</div> -->
				
<!-- 				<div class="menu-item" href="javascript:;" url="/houtai/consultation/index"><img src="images/icon_card_grey.png">资讯管理</div> -->
<!-- 				<div class="menu-item" href="javascript:;" url="/houtai/case/index"><img src="images/icon_card_grey.png">案例管理</div> -->
				
<%-- 				<shiro:hasRole name="admin"> --%>
<!-- 				    <div class="menu-item" href="javascript:;" url="/notoken/index"><img src="images/icon_card_grey.png">专家认证</div> -->
<!-- 				    <div class="menu-item" href="javascript:;" url="/notoken/UserIndex"><img src="images/icon_card_grey.png">用户管理</div> -->
<%-- 				</shiro:hasRole> --%>
			
			
				
			<shiro:hasRole name="admin">
			 <div id="personInfor">
			  <p id="userName">超级管理员</p>
			  <p><span><a href="javascript:;" id="changePasswordBtn">修改密码</a></span></p>
			        <p>
						<a href="javascript:;" id="logoutBtn">退出登录</a>
					</p>
			 </div>
			  <div class="menu-item" href="javascript:;" url="/houtai/wenda/index"><img src="images/icon_card_grey.png">问答管理</div>
			  <div class="menu-item" href="javascript:;" url="/houtai/consultation/index"><img src="images/icon_card_grey.png">资讯管理</div>
			  <div class="menu-item" href="javascript:;" url="/houtai/case/index"><img src="images/icon_card_grey.png">案例管理</div>
			  <div class="menu-item" href="javascript:;" url="/houtai/consultation/index1"><img src="images/icon_card_grey.png">专家认证</div>
			  <div class="menu-item" href="javascript:;" url="/houtai/consultation/UserIndex"><img src="images/icon_card_grey.png">用户管理</div>
			</shiro:hasRole>	
			
			<shiro:hasRole name="user">
			  <div id="personInfor">
			  <p id="userName">操作员</p>
			  <p>
					<a href="javascript:;" id="logoutBtn">退出登录</a>
			  </p>
			  </div>
			  <div class="menu-item" href="javascript:;" url="/houtai/wenda/index"><img src="images/icon_card_grey.png">问答管理</div>
			  <div class="menu-item" href="javascript:;" url="/houtai/consultation/index"><img src="images/icon_card_grey.png">资讯管理</div>
			  <div class="menu-item" href="javascript:;" url="/houtai/case/index"><img src="images/icon_card_grey.png">案例管理</div>
			</shiro:hasRole>
				
			<shiro:hasRole name="expert">
			   <div id="personInfor">
			   <p id="userName">专家</p>
			   <p>
						<a href="javascript:;" id="logoutBtn">退出登录</a>
			   </p>
			   </div>
			   <div class="menu-item" href="javascript:;" url="/houtai/consultation/index"><img src="images/icon_card_grey.png">资讯管理</div>
			   <div class="menu-item" href="javascript:;" url="/houtai/case/index"><img src="images/icon_card_grey.png">案例管理</div>
			</shiro:hasRole>	
				
				
				
				
			</div>
			<!-- 右侧具体内容栏目 -->
			<div id="rightContent">
				<a class="leftmenu-toggle-btn">
					<i class="glyphicon glyphicon-align-justify"></i>
				</a>
				<div id="right-container">
	<!-- 资源管理模块 -->
	
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function(){
			
		    $(window).resize(function(){
		    	var width=$(document).width();//浏览器当前窗口文档对象宽度
				var Wid=width-200;	
		    	$("#right-container").css("width",Wid);
		    });   
		    $(window).resize();
		});
			
		</script>
</body>
</html>
