<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.job.schedulerTrigger.input');
com.ue.platform.module.job.schedulerTrigger.input={
	pageId:"${pageId}",
	doSave:function(){
		if(!$("#${pageId}theForm").valid()){
			return;
		}
		$("body").mask('正在保存数据……');
		var formData=$("#${pageId}theForm").serialize();
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/schedulerTrigger/save",
		  	processData:true,
		  	data:formData,
		  	success: function(id){
		  		com.ue.platform.module.job.schedulerTrigger.list.refresh();
				$("body").unmask();
// 				$.successTips();
				art.dialog.list[com.ue.platform.module.job.schedulerTrigger.list.pageId].close();
		  	}
		 });
	},
	init:function(){
		$.hz.validate.init("${pageId}theForm");
	}
};

$(document).ready(function() {
	com.ue.platform.module.job.schedulerTrigger.input.init();
})
</script>
		<uc:form action="" id="${pageId}theForm" method="post">
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"/>
			  	<uc:hidden name="model.jobId" value="${model.jobId}"/>
			  	<uc:hidden name="model.num" value="${model.num}"/>
			</c:if>
			<div class="formLayout01">
				<div class="formItem">
				     <label class="tit"><em>*</em>触发任务</label>
				     <div class="bdmain">
				     	<c:if test="${empty model.id}">
					     	<uc:select  name="model.jobId" list="${schedulerJobs}" listKey="id" listValue="jobName" value="${model.jobId}"></uc:select>
				     	</c:if>
				     	<c:if test="${not empty model.id}">
				     		${model.job.jobName}
				     	</c:if>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit"><em>*</em>cron表达式</label>
				     <div class="bdmain">
				     	<uc:textfield name="model.cronExpression" value="${model.cronExpression}" maxlength="50" required="true" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit">开始时间</label>
				     <div class="bdmain">
				     	<uc:dateSelect id="${pageId}startTime" styleClass="calendar" dateFmt="yyyy-MM-dd HH:mm" name="model.startTime" dateValue="${model.startTime}"></uc:dateSelect>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit">结束时间</label>
				     <div class="bdmain">
				     	<uc:dateSelect id="${pageId}endTime" styleClass="calendar" dateFmt="yyyy-MM-dd HH:mm" name="model.endTime" dateValue="${model.endTime}"></uc:dateSelect>
				     </div>
				</div>
				<div class="formItem">
				     <label class="tit">描述</label>
				     <div class="bdmain">
				     	<uc:textfield name="model.description" value="${model.description}" maxlength="100" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
			</div>
		</uc:form>
