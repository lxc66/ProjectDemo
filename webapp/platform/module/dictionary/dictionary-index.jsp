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
Namespace.register('com.ue.platform.module.dictIndex');
com.ue.platform.module.dictIndex={
	pageId:"${pageId}",
	loadContent:function(url){
		$("#${pageId}wrap").load(url,function(){
			$("body").unmask();
		});
	},
	loadDictWithDict:function(){
		this.loadContent("${path}/sys/dictionary/list");
	},
	loadDictWithDictValue:function(){
		this.loadContent("${path}/sys/dictionaryValue/index");
	}
};

$(document).ready(function() {
	com.ue.platform.module.dictIndex.loadDictWithDict();
	$('#${pageId}containers').layout({
		north__size:50
	});
})
</script>
	</head>
	<body>
		<div class="platform_main" id="${pageId}containers">
			<div class="platform_top mb15">
			    <h3 class="box_title fl mr20">数据字典</h3>
			    <a href="javascript:void(0);" original-title="字典条目" onclick="com.ue.platform.module.dictIndex.loadDictWithDict();">字典条目</a> |
			    <a href="javascript:void(0);" original-title="字典值" onclick="com.ue.platform.module.dictIndex.loadDictWithDictValue();">字典值</a>
			</div>
			<div id="${pageId}wrap" class="platform_content abs0 t50">
				
			</div>
		</div>
	</body>
</html>
