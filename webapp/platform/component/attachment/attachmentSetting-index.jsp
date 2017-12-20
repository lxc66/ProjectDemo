<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
    <title>平台</title>
<script>
$.hz.component.include("jqueryui,tree,validate,layout,popupLayer,mask,tabs",false);
Namespace.register('com.ue.platform.module.attachmentSetting.index');
com.ue.platform.module.attachmentSetting.index={
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
		$("#${pageId}platformTopBtn a").bind("click",function(){
			self.loadContent(this);
		});
	},
	loadDefault:function(){
		var aItem = $("#${pageId}platformTopBtn a").eq(0);
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
	com.ue.platform.module.attachmentSetting.index.init();
})
</script>
</head>
	<body>
		<div class="platform_main" id="${pageId}containers">
			<div class="platform_top mb15" id="${pageId}platformTopBtn">
			    <div class="fr btn-group" id="${pageId}platformTopBtn">
					<a class="btn btn-sm yellow-crusta" href="javascript:;" original-title="模块配置" url="${path}/sys/attachmentConfig/list">模块配置</a>
					<a class="btn btn-sm btn-primary" href="javascript:;" original-title="全局配置" url="${path}/sys/attachmentSetting/input">全局配置</a>
				</div>
			    <h3>附件配置</h3>
			</div>
			<div id="${pageId}wrap" class="ui-layout-center centerStyle t50">
			</div>
		</div>
	</body>
</html>
