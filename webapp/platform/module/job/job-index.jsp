<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
    <title>平台</title>
<script>
$.hz.component.include("jqueryui,tree,validate,layout,popupLayer,mask,tabs",false);
Namespace.register('com.ue.platform.module.job.index');
com.ue.platform.module.job.index={
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
	com.ue.platform.module.job.index.init();
})
</script>
</head>
	<body>
		<div class="platform_main" id="${pageId}containers">
			<div class="platform_top mb15">
			    <div class="fr btn-group" id="${pageId}platformTopBtn">
					<a class="btn btn-sm yellow-crusta" href="javascript:;" original-title="调度工作" url="${path}/sys/schedulerJob/list">可调度的任务</a>
					<a class="btn btn-sm btn-primary" href="javascript:;" original-title="调度触发" url="${path}/sys/schedulerTrigger/list">待触发的任务</a>
				</div>
			    <h3>计划任务</h3>
			</div>
			<div id="${pageId}wrap" class="platform_content abs0 t40">
			</div>
		</div>
	</body>
</html>
