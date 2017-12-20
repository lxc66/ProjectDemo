<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.user.resetPassword');
com.ue.platform.module.user.resetPassword={
	pageId:"${pageId}",
	doResetPassword:function(){
		if(!$("#${pageId}theForm").valid()){
			return;
		}
	    var formData=$("#${pageId}theForm").serialize();
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/user/resetPassword",
		  	processData:true,
		  	data:formData,
		  	success: function(){
		  		$.successTips();
		   		art.dialog.list[com.ue.platform.module.user.list.pageId].close();
		  	}
		 });
	}
};

$(document).ready(function() {
	$.hz.validate.init("${pageId}theForm");
})
</script>
		<uc:form action="" id="${pageId}theForm" method="post">
		  	<uc:hidden name="model.id" value="${model.id}"/>
			<div class="formLayout01">
				<div class="formItem">
				     <label class="tit">用户姓名</label><%-- 用户姓名 --%>
				     <div class="bdmain detail_right w200 f14">
				     	${model.name}
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit"><em>*</em>用户密码</label><%-- 密码 --%>
				     <div class="bdmain">
				     	<uc:password id="${pageId}password" name="model.password" maxlength="20" required="true" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
	       
	       		<div class="formItem">
				     <label class="tit"><em>*</em>确认密码</label><%-- 确认密码 --%>
				     <div class="bdmain">
				     	<uc:password  name="" maxlength="20" required="true" equalto="#${pageId}password" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
			</div>
		</uc:form>
