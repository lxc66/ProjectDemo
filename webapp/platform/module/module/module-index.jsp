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
Namespace.register('com.ue.platform.module.module.index');
com.ue.platform.module.module.index={
	pageId:"${pageId}",
	loadContent:function(url){
		$(".container").mask('正在加载数据……');
		$("#${pageId}list").load(url,function(){
			$("body").unmask();
		});
	},
	refresh:function(){
		this.loadContent("${path}/sys/module/list?parentPageId=${pageId}");
	},
	init:function(){
		this.refresh();
	}
};

$(document).ready(function() {
	com.ue.platform.module.module.index.init();
	$('#${pageId}containers').layout({
		north__size:50
	});
})
</script>
	</head>
	<body>
		<uc:form id="${pageId}theForm" method="post" action="${path}/sys/module/list?parentPageId=${pageId}">
			<div class="platform_main" id="${pageId}containers">
				<div class="platform_top mb15">
				    <h3>查询条件</h3>
				</div>
				<div id="${pageId}list" class="platform_content">
					 
				</div>
			</div>
		</uc:form>
	</body>
</html>
