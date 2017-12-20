<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.job.schedulerJob.input');
com.ue.platform.module.job.schedulerJob.input={
	pageId:"${pageId}",
	doSave:function(){
		if(!$("#${pageId}theForm").valid()){
			return;
		}
		$("body").mask('正在保存数据……');
		var formData=$("#${pageId}theForm").serialize();
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/schedulerJob/save",
		  	processData:true,
		  	data:formData,
		  	success: function(id){
		  		com.ue.platform.module.job.schedulerJob.list.refresh();
				$("body").unmask();
// 				$.successTips();
				art.dialog.list[com.ue.platform.module.job.schedulerJob.list.pageId].close();
		  	}
		 });
	},
	init:function(){
		var validateOptions = {
			rules: {
				"model.jobName" : {
					required:true,
					maxlength:25,
					remote:{
						type:"POST",
						url:"${path}/sys/schedulerJob/ajaxCheckGroupAndName",
						data:{
							jobGroup:function(){return $("#${pageId}jobGroup").val();},
							jobName:function(){return $("#${pageId}jobName").val();},
							excludeId:"${model.id}"
		                } 
					}
				},
			},
			messages : {
				"model.jobName" : {
					remote : "jobGroup与jobName的组合已被占用，请重新输入！"
				}
			}
		};
		$.hz.validate.init("${pageId}theForm",validateOptions);
	}
};

$(document).ready(function() {
	com.ue.platform.module.job.schedulerJob.input.init();
})
</script>
		<uc:form action="" id="${pageId}theForm" method="post">
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"/>
			  	<uc:hidden name="model.jobGroup" value="${model.jobGroup}"/>
			  	<uc:hidden name="model.jobName" value="${model.jobName}"/>
			  	<uc:hidden name="model.num" value="${model.num}"/>
			</c:if>
			<div class="formLayout01">
				<div class="formItem">
				     <label class="tit"><em>*</em>jobGroup</label>
				     <div class="bdmain">
				     	<c:if test="${empty model.id}">
				     		<uc:textfield id="${pageId}jobGroup" name="model.jobGroup" value="${model.jobGroup}" maxlength="100" required="true" cssClass="inputStyle  clearable"/>
				     	</c:if>
				     	<c:if test="${not empty model.id}">
				     		${model.jobGroup}
				     	</c:if>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit"><em>*</em>jobName</label>
				     <div class="bdmain">
				     	<c:if test="${empty model.id}">
				     		<uc:textfield id="${pageId}jobName" name="model.jobName" value="${model.jobName}" maxlength="100" required="true" cssClass="inputStyle  clearable"/>
				     	</c:if>
				     	<c:if test="${not empty model.id}">
				     		${model.jobName}
				     	</c:if>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit"><em>*</em>jobClass</label>
				     <div class="bdmain">
				     	<uc:textfield name="model.jobClass" value="${model.jobClass}" maxlength="200" required="true" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
			</div>
		</uc:form>
