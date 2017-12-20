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
Namespace.register('com.ue.platform.module.user.index');
com.ue.platform.module.user.index={
	pageId:"${pageId}",
	loadContent:function(obj){
		var self = this;
		$("#${pageId}wrap").empty();
		$(".current").removeClass("current");
		$(obj).addClass("current");
		if($(obj).attr("type")){
			$("#containers").mask('加载内容……');
			var url = "${path}/sys/user/list?userType="+$(obj).attr("type");
			$("#${pageId}wrap").load(url,function(){
				$("#containers").unmask();
			});
		}
	},
	bindEvent:function(){
		var self = this;
		$("#${pageId}containers .box_nav a").bind("click",function(){
			self.loadContent(this);
		});
	},
	loadDefault:function(){
		var userType = $("#${pageId}containers .box_nav a").eq(0);
		if(userType){
			userType.click();
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
	com.ue.platform.module.user.index.init();
})
</script>
	</head>
	<body>
		<div class="platform_main" id="${pageId}containers">
		    <div class="box_nav">
				<ul>
					<c:if test="${not empty userTypeList}">
						<c:forEach items="${userTypeList}" var="userType">
						<li style="float: left;margin:0 4px;">
							<a class="button" href="javascript:;" original-title="${userType.value}" type="${userType.code}">${userType.value}</a>
						</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty userTypeList}">
						请新建用户类型字典
					</c:if>
				</ul>
		    </div>
			<div id="${pageId}wrap" class="platform_content abs0 t40">
				
			</div>
		</div>
	</body>
</html>
