<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
    <title>平台</title>
<%--     <%@ include file="/platform/common/jsp/common-miniJs.jsp"%> --%>
<%--     <link rel="stylesheet" href="${path}/platform/theme/default/style.css"/> --%>
<script>
$.hz.component.include("jqueryui,tree,validate,layout,popupLayer,mask,tabs",false);
Namespace.register('com.ue.platform.module.userauth.index');
com.ue.platform.module.userauth.index={
	pageId:"${pageId}",
	loadContent:function(obj){
		var self = this;
		$("#${pageId}wrap").empty();
		if($(obj).attr("url")){
			$("#containers").mask('加载内容……');
			$("#${pageId}wrap").load($(obj).attr("url"),function(){
				$("#containers").unmask();
			});
		}
	},
	bindEvent:function(){
		var self = this;
		$("#${pageId}containers .platform_title a").bind("click",function(){
			self.loadContent(this);
		});
	},
	loadDefault:function(){
		var aItem = $("#${pageId}containers .platform_title a").eq(0);
		if(aItem){
			aItem.click();
		}
	},
	init:function(){
		this.bindEvent();
		this.loadDefault();
	}
};

$(document).ready(function() {
	$('#${pageId}containers').layout({
		north__size:50
	});
	com.ue.platform.module.userauth.index.init();
})
</script>
	</head>
	<body>
		<div class="platform_main" id="${pageId}containers">
			<div class="platform_top">
			    <h3 class="fl mr15">用户权限</h3>
				<div class="platform_title">
					<a href="javascript:;" original-title="用户管理" url="${path}/sys/user/index">用户管理</a> |
					<a href="javascript:;" original-title="角色管理" url="${path}/sys/role/index">角色管理</a> |
					<a href="javascript:;" original-title="权限管理" url="${path}/sys/privilege/index">权限管理</a>
				</div>
			    </div>
			</div>
			<div id="${pageId}wrap" class="platform_content abs0 t50">
			
			</div>
		</div>
	</body>
</html>
