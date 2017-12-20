<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.user.input');
com.ue.platform.module.user.input={
	pageId:"${pageId}",
	doSave:function(){
		if(!$("#${pageId}theForm").valid()){
			return;
		}
		$("body").mask('正在保存数据……');
		var formData=$("#${pageId}theForm").serialize();
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/user/save",
		  	processData:true,
		  	data:formData,
		  	success: function(id){
		  		com.ue.platform.module.user.list.refresh();
				$("body").unmask();
// 				$.successTips();
				art.dialog.list[com.ue.platform.module.user.list.pageId].close();
		  	}
		 });
	},
	init:function(){
		var validateOptions = {
			rules: {
				"model.loginName" : {
					required:true,
					maxlength:25,
					remote:{
						type:"POST",
						url:"${path}/sys/user/ajaxCheckLoginName",
						data:{
							loginName:function(){return $("#${pageId}loginName").val();},
							excludeLoginName:"${model.loginName}"
		                } 
					}
				},
				"model.email" : {
					maxlength:25,
					email:true
				}
			},
			messages : {
				"model.loginName" : {
					remote : "用户名已存在，请重新输入！"
				}
			}
		};
		$.hz.validate.init("${pageId}theForm",validateOptions);
	}
};

$(document).ready(function() {
	com.ue.platform.module.user.input.init();
})
</script>
		<uc:form action="" id="${pageId}theForm" method="post">
		  	<uc:hidden name="model.type" value="${model.type}"/>
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"/>
			  	<uc:hidden name="model.displayName" value="${model.displayName}"/>
			  	<uc:hidden name="model.pinyin" value="${model.pinyin}"/>
			  	<uc:hidden name="model.num" value="${model.num}"/>
			</c:if>
			<div class="formLayout01">
				<div class="formItem">
				     <label class="tit"><em>*</em>用户姓名</label>
				     <div class="bdmain">
				     	<uc:textfield name="model.name" value="${model.name}" maxlength="25" required="true" cssClass="inputStyle  clearable"/>
				     </div>
<!-- 					 <div class="bdmain detail_right w200 f14"> -->
<%-- 				     	${model.name} --%>
<!-- 				     </div> -->
				</div>
				
				<div class="formItem">
				     <label class="tit"><em>*</em>登录名</label>
				     <div class="bdmain">
				     	<uc:textfield id="${pageId}loginName" name="model.loginName" value="${model.loginName}" maxlength="25" required="true" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit">邮箱</label>
				     <div class="bdmain">
				     	<uc:textfield name="model.email" value="${model.email}" maxlength="25" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit"><em>*</em>状态</label>
				     <div class="bdmain">
				     	<div class="rcWrap">
				     		<uc:radiolist list="${enableFlagMap}" name="model.enableFlag" required="true" value="${model.enableFlag}" cssClass="radioStyle"/>
				     	</div>
				     </div>
				</div>
			</div>
		</uc:form>
