<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.job.schedulerJob.list');
com.ue.platform.module.job.schedulerJob.list={
	pageId:"${pageId}",
	refresh:function(){
		$.hz.jqGrid.refreshTable("${pageId}list");
	},
	toAdd:function(){
		$.dialog.load("${path}/sys/schedulerJob/input",{
			title : '任务新增',
			width: 600,
			height: 300,
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.platform.module.job.schedulerJob.input.doSave();
				return false;
		    },
			cancel:function(){}
		});
	},
	toEdit:function(id){
		$.dialog.load("${path}/sys/schedulerJob/input?id="+id,{
					title : '任务修改',
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.job.schedulerJob.input.doSave();
						return false;
				    },
					cancel:function(){}
		});
	},
	doDel:function(id){
		var self = this;
		confirm("确定要删除吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/schedulerJob/delete?id='+id,
			  	processData:true,
			  	success: function(data){
			  		self.refresh();
			  	}
			 });
		});
	},
	triggerJob:function(id){
		var self = this;
		confirm("确定要触发吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/schedulerJob/triggerJob?id='+id,
			  	processData:true,
			  	success: function(data){
			  		$.successTips();
			  	}
			 });
		});
	},
	bindTable:function(){
		$.hz.jqGrid.init("${pageId}list","${path}/sys/schedulerJob/listData");
		var opHtml=[];
		opHtml.push("<div class='fl ml10'>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='com.ue.platform.module.job.schedulerJob.list.toAdd()'><span class='fm f8 mr5'>+</span>添加</a>");
		opHtml.push("</div>");
		$.hz.jqGrid.appendToolBarBtn("${pageId}list",opHtml);
	},
	tableCellRedrawWithOP:function(cellvalue,options,rowObject){
		var opHtml=[];
		opHtml.push("<div class='tc'>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.job.schedulerJob.list.toEdit(\""+rowObject.id+"\");'><span class='fm f8 mr5'>f</span>修改</a>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.job.schedulerJob.list.doDel(\""+rowObject.id+"\");'><span class='fm f8 mr5'>-</span>删除</a>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.job.schedulerJob.list.triggerJob(\""+rowObject.id+"\");'><span class='fm f8 mr5'>p</span>触发一次</a>");
		opHtml.push("</div>");
		return opHtml.join("");
	},
	init:function(){
		this.bindTable();
	}
};

$(document).ready(function() {
	com.ue.platform.module.job.schedulerJob.list.init();
});

</script>
        <table id="${pageId}list" multiselect="false" pager="pager" toolbar="true" class="jqgrid">
			<thead>
				<tr>
					<th name="id" hide="true">
	
					</th>
					<th name="jobGroup" sort='true'>
						jobGroup
					</th>
					<th name="jobName" sort='true'>
						jobName
					</th>
					<th name="jobClass" sort='true'>
						jobClass
					</th>
<!-- 					<th name="status" sort='true'> -->
<!-- 						状态 -->
<!-- 					</th> -->
					<th name="op" thWidth="400" formatter="com.ue.platform.module.job.schedulerJob.list.tableCellRedrawWithOP">
						 操作
					</th>
				</tr>
			</thead>
		</table>
