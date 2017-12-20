<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.config.list');
com.ue.platform.module.config.list = {
	pageId : "${pageId}",
	contendDiv : "${pageId}" + 'rightContent',
	goHref : function(url) {
		$("#${pageId}theForm")[0].action = url;
		$("#${pageId}theForm")[0].submit();
	},
	refresh : function() {
		$.hz.jqGrid.refreshTable("${pageId}list");
	},
	search : function() {
		var params = {
			"model.name" : $("#${pageId}name").val(),
			"model.code" : $("#${pageId}code").val()
		};
		com.ue.datagrid.searchTable("${pageId}list", params);
	},
	optionOfRow : function(cellvalue, options, rowObject) {
		var opHtml=[];
		opHtml.push("<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.config.list.toEdit(\""+rowObject.id+"\");'><span class='fm f8 mr5'>p</span>修改</a>");
		opHtml.push("&nbsp;&nbsp;&nbsp;");
		opHtml.push("<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.config.list.doDel(\""+rowObject.id+"\");'><span class='fm f8 mr5'>-</span>删除</a>");
		return opHtml.join('');
	},
	doDel : function(id) {
		var self=this;
		confirm("确定要删除吗？", function() {
			$.ajax( {
				type : "POST",
				url : '${path}/sys/config/delete?id=' + id,
				processData : true,
				success : function(data) {
					self.refresh();
				}
			});
		});
	},
	toAdd : function() {
		this.toInput("${path}/sys/config/input","配置添加");
	},
	toEdit : function(id) {
		this.toInput("${path}/sys/config/input?id="+id,"配置修改");
	},
	toInput:function(url,title){
		$.dialog.load(url,{
			title : title,
			width: "100%",
			height: "100%",
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.platform.module.config.input.doSave();
				return false;
		    },
			cancel:function(){}
		});
	},
	detailOfConfigurationItem:function(id){
		var url = "${path}/bd/configuration!detailOfConfiguration.action?id=" + id;
		$.dialog.load(url, {
			title : '配置详细信息',
			width : 800,
			height : 500,
			fixed : true,
			id : '${pageId}configurationdetail',
			lock : true
		});
	},
	detail:function(cellvalue, options, rowObject){
		return  "<a href='javascript:void(0);'  onclick='com.ue.platform.module.config.list.detailOfConfigurationItem(\""
			+ rowObject.id
			+ "\");' ><font color='blue'>"
// 			+ $.jgrid.htmlEncode(cellvalue)
			+ cellvalue
			+ "</font></a>";
	},
	bindTable:function(){
		$.hz.jqGrid.init("${pageId}list","${path}/sys/config/listData");
		var opHtml=[];
		opHtml.push("<div class='fl ml10'>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='com.ue.platform.module.config.list.toAdd();'><span class='fm f8 mr5'>+</span>添加</a>");
		opHtml.push("</div>");
		$.hz.jqGrid.appendToolBarBtn("${pageId}list",opHtml);
	},
	init:function(){
		this.bindTable();
	}
};

$(document).ready(function() {
	com.ue.platform.module.config.list.init();
});
</script>
</head>
<body>
	<!-- Begin search -->
	<div class="searchBox clearfix">
		<div class="fl">
			名称：<uc:textfield id="${pageId}name" name="model.name" size="30"
				maxlength="50" cssClass="inputStyle mr5"/>
			编码：<uc:textfield id="${pageId}code" name="model.code" size="30"
				maxlength="50" cssClass="inputStyle mr5" />
			<a class="button btnWhite" href="javascript:void(0);" onclick="com.ue.platform.module.config.list.search()">查询</a>
		</div>
	</div>
	<!-- End search -->
	<table id="${pageId}list" class='jqgrid' multiselect=true toolbar=true autowidth="true" pager="pager">
		<thead>
			<tr>
				<th name="id" hide="true">

				</th>
				<th name="name" sort="true" formatter="com.ue.platform.module.config.list.detail">
					名称
				</th>
				<th name="code" sort="true">
					编码
				</th>
				<th name="kindView" index="kind" sort="true">
					类型
				</th>
				<th name="modeView" index="mode" sort="true">
					配置模式
				</th>
				<th name="visibleFlagView" index="visibleFlag" sort="true">
					用户是否可见
				</th>
				<th name="act" thWidth="120"
					formatter="com.ue.platform.module.config.list.optionOfRow">
					操作
				</th>
			</tr>
		</thead>
	</table>
</body>
</html>
