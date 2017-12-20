<!DOCTYPE html>
<%@page import="com.jzsoft.platform.util.UUID"%>
<%@page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
    <title>平台</title>
    <%@ include file="/platform/common/jsp/commonJs.jsp"%>
<script src="${path}/demo/common/js/jquery-syntax/public/jquery.syntax.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${path}/demo/common/css/style.css"/>
    <link rel="stylesheet" href="${path}/platform/theme/default/style.css"/>
	<script>
		$.hz.component.debug=true;
		$.hz.component.includeAll(false);
// 		$.hz.component.include("jqueryui,layout,ueditor,mask,box,accordion,popupLayer");
		Namespace.register('com.ue.platform.module.index');
		com.ue.platform.module.index={
			loadContent:function(obj){
				var that = this;
				$("#rightContent").empty();
				$(".current").removeClass("current");
				$(obj).addClass("current");
				if($(obj).attr("url")){
					$("#containers").mask('加载内容……');
					$("#rightContent").load($(obj).attr("url"),function(){
						$("#containers").unmask();
					});
				}
			},
			init:function(){
				var that = this;
				$("#containers .navMain a").bind("click",function(){
					that.loadContent(this);
				});
			}
		}
	
		$(document).ready( function(){ 
			$('#containers').layout({
				 	west__size:				220
				,	spacing_open:			5
				,	spacing_closed: 		5
				,	resizable:				true  
			});
			
			$("#accordion").accordion();
			
			com.ue.platform.module.index.init();
		});						
	</script>
	</head>
	<body>
	<div id="containers"  class="container">
		<div class="ui-layout-center centerStyle" id="rightContent" style="overflow:auto;">
		
		</div>
		<div class="ui-layout-west westStyle">
			<div id="accordion">
				<h3>系统模块</h3>
				<div>
					<ul class="navMain">
						<li>
							<a href="javascript:;" url="${path}/sys/module/index">
								<span>功能模块</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/sys/dictionary/index">
								<span>数据字典</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/sys/config/index">
								<span>系统配置</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/sys/userauth/index">
								<span>用户权限</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/sys/attachmentSetting/index">
								<span>附件配置</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/sys/schedulerJob/index">
								<span>计划任务</span>
							</a>
						</li>
					</ul>
				</div>
				<h3>其他</h3>
				<div>
					<ul class="navMain">
						<li>
							<a href="javascript:;" url="${path}/demo/component/other-include.jsp">
								<span>JS及组件引入</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
