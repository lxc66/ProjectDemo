<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.attachment.attachmentConfig.list');
com.ue.platform.module.attachment.attachmentConfig.list = {
	pageId:"${pageId}",
	refresh:function(){
		$.hz.jqGrid.refreshTable("${pageId}list");
	},
	toEdit:function(id){
		$.dialog.load("${path}/sys/attachmentConfig/input.do?id=" + id,{
			title : '修改配置',
			width : 800,
			height : 400,
			id : "${pageId}Dia",
			lock: true,
			ok : function(){
				com.ue.platform.module.attachment.attachmentConfig.input.doAdd("${pageId}Dia");
				return false;
		    }
		});
	},
	toAdd:function(){
		$.dialog.load("${path}/sys/attachmentConfig/input.do",{
			title : '添加配置',
			width : 800,
			height : 400,
			id : "${pageId}Dia",
			lock: true,
			ok : function(){
				com.ue.platform.module.attachment.attachmentConfig.input.doAdd("${pageId}Dia");
				return false;
		    }
		});
	},
	toDele:function(id){
		var self = this;
		confirm("是否确定删除？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/attachmentConfig/ajaxDele.do?id='+id,
			  	success: function(data){
			  		self.refresh();
			  		$.successTips();
			  	}
			 });
		});
	},
	bindTable:function(){
		$.hz.jqGrid.init("${pageId}list","${path}/sys/attachmentConfig/listData");
		var opHtml=[];
		opHtml.push("<div class='fl ml10'>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='com.ue.platform.module.attachment.attachmentConfig.list.toAdd()'><span class='fm f8 mr5'></span>添加</a>");
		opHtml.push("</div>");
		$.hz.jqGrid.appendToolBarBtn("${pageId}list",opHtml);
	},
	tableCellRedrawWithOP:function(cellvalue,options,rowObject){
		var opHtml=[];
		opHtml.push("<div class='tc'>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.attachment.attachmentConfig.list.toEdit(\""+rowObject.id+"\");'><span class='fm f8 mr5'></span>修改</a>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.attachment.attachmentConfig.list.toDele(\""+rowObject.id+"\");'><span class='fm f8 mr5'></span>删除</a>");
		opHtml.push("</div>");
		return opHtml.join("");
	},
	init:function(){
		this.bindTable();
	}
}

$(document).ready(function() {
	com.ue.platform.module.attachment.attachmentConfig.list.init();
});
</script>
        <table id="${pageId}list" multiselect="false" pager="pager" toolbar="true" class="jqgrid">
			<thead>
				<tr>
					<th name="id" hide="true">
	
					</th>
					<th name="code" sort='true'>
						编码<%-- 编码 --%>
					</th>
					<th name="saveDir" sort='true'>
						保存目录<%-- 保存目录 --%>
					</th>
					<th name="dirLevel" sort='true'>
						目录层级<%-- 目录层级 --%>
					</th>
					<th name="fileSize" >
						文件大小<%-- 文件大小 --%>
					</th>
					<th name="fileCount" >
						文件个数<%-- 文件个数 --%>
					</th>
					<th name="extension" >
						扩展名<%-- 状态 --%>
					</th>
					<th name="description" >
						文件描述<%-- 文件描述 --%>
					</th>
					<th name="op" thWidth="400" formatter="com.ue.platform.module.attachment.attachmentConfig.list.tableCellRedrawWithOP">
						 操作 <%-- 操作 --%>
					</th>
				</tr>
			</thead>
		</table>
