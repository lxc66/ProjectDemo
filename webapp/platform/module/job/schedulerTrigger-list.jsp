<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.job.schedulerTrigger.list');
com.ue.platform.module.job.schedulerTrigger.list={
	pageId:"${pageId}",
	refresh:function(){
		$.hz.jqGrid.refreshTable("${pageId}list");
	},
	toAdd:function(){
		$.dialog.load("${path}/sys/schedulerTrigger/input",{
			title : '触发器新增',
			width: 600,
			height: 300,
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.platform.module.job.schedulerTrigger.input.doSave();
				return false;
		    },
			cancel:function(){}
		});
	},
	toEdit:function(id){
		$.dialog.load("${path}/sys/schedulerTrigger/input?id="+id,{
					title : '触发器修改',
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.job.schedulerTrigger.input.doSave();
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
			  	url: '${path}/sys/schedulerTrigger/delete?id='+id,
			  	processData:true,
			  	success: function(data){
			  		self.refresh();
			  	}
			 });
		});
	},
	pause:function(id){
		var self = this;
		confirm("确定要暂停吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/schedulerTrigger/pause?id='+id,
			  	processData:true,
			  	success: function(data){
			  		self.refresh();
			  	}
			 });
		});
	},
	resume:function(id){
		var self = this;
		confirm("确定要恢复吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/schedulerTrigger/resume?id='+id,
			  	processData:true,
			  	success: function(data){
			  		self.refresh();
			  	}
			 });
		});
	},
	bindTable:function(){
		$.hz.jqGrid.init("${pageId}list","${path}/sys/schedulerTrigger/listData");
		var opHtml=[];
		opHtml.push("<div class='fl ml10'>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='com.ue.platform.module.job.schedulerTrigger.list.toAdd()'><span class='fm f8 mr5'>+</span>添加</a>");
		opHtml.push("</div>");
		$.hz.jqGrid.appendToolBarBtn("${pageId}list",opHtml);
	},
	tableCellRedrawWithOP:function(cellvalue,options,rowObject){
		var opHtml=[];
		opHtml.push("<div class='tc'>");
		var triggerStatus = rowObject.qrtzTriggers.status;
		if(!triggerStatus || triggerStatus && triggerStatus!="ACQUIRED"){
			opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.job.schedulerTrigger.list.toEdit(\""+rowObject.id+"\");'><span class='fm f8 mr5'>f</span>修改</a>");
			opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.job.schedulerTrigger.list.doDel(\""+rowObject.id+"\");'><span class='fm f8 mr5'>-</span>删除</a>");
		}
		if(triggerStatus){
			if(triggerStatus=="WAITING" && triggerStatus!="ACQUIRED"){
				opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.job.schedulerTrigger.list.pause(\""+rowObject.id+"\");'><span class='fm f8 mr5'>-</span>暂停</a>");
			}
			if(triggerStatus=="PAUSED"){
				opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.job.schedulerTrigger.list.resume(\""+rowObject.id+"\");'><span class='fm f8 mr5'>-</span>恢复</a>");
			}
		}
		opHtml.push("</div>");
		return opHtml.join("");
	},
	init:function(){
		this.bindTable();
	}
};

$(document).ready(function() {
	com.ue.platform.module.job.schedulerTrigger.list.init();
});

</script>
        <table id="${pageId}list" multiselect="false" pager="pager" toolbar="true" class="jqgrid">
			<thead>
				<tr>
					<th name="id" hide="true">
						
					</th>
					<th name="job.jobGroup" sort='true'>
						jobGroup
					</th>
					<th name="job.jobName" sort='true'>
						jobName
					</th>
					<th name="cronExpression" sort='true'>
						cron表达式
					</th>
					<th name="qrtzTriggers.startTime" sort='true'>
						开始时间
					</th>
					<th name="qrtzTriggers.endTime" sort='true'>
						结束时间
					</th>
					<th name="qrtzTriggers.nextFireTime" >
						下次触发时间
					</th>
					<th name="qrtzTriggers.prevFireTime" >
						上次触发时间
					</th>
					<th name="qrtzTriggers.status"  hide="true">
						状态值
					</th>
					<th name="triggerStatus" >
						状态
					</th>
					<th name="op" thWidth="400" formatter="com.ue.platform.module.job.schedulerTrigger.list.tableCellRedrawWithOP">
						 操作
					</th>
				</tr>
			</thead>
		</table>
