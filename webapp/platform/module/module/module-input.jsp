<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.module.input');
com.ue.platform.module.module.input={
	pageId:"${pageId}",
	doSave:function(){
		if(!$("#${pageId}theForm").valid()){
			return;
		}
		var formData=$("#${pageId}theForm").serialize();
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/module/save",
		  	processData:true,
		  	data:formData,
		  	success: function(id){
				com.ue.platform.module.module.list.refresh();
		   		art.dialog.list[com.ue.platform.module.module.list.pageId].close();
		  	}
		 });
	},
	init:function(){
		var validateOptions = {
			rules: {
				"model.code" : {
					required:true,
					maxlength:25,
					remote:{
						type:"POST",
						url:"${path}/sys/module/ajaxCheckCode",
						data:{
							code:function(){return $("#${pageId}code").val();},
							excludeCode:"${model.code}"
		                } 
					}
				}
			},
			messages : {
				"model.code" : {
					remote : "模块编码存在，请重新输入！"
				}
			}
		};
		$.hz.validate.init("${pageId}theForm",validateOptions);
	}
};

$(document).ready(function() {
	com.ue.platform.module.module.input.init();
})
</script>
		<uc:form  action="" id="${pageId}theForm" method="post">
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"/>
			</c:if>
			<div class="formLayout01">
		
				<div class="formItem">
				     <label class="tit"><em>*</em>名称</label>
				     <div class="bdmain">
			              <uc:textfield name="model.name" value="${model.name}" maxlength="10" required="true" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
		
				<div class="formItem">
				     <label class="tit"><em>*</em>名称i18n</label>
				     <div class="bdmain">
			              <uc:textfield name="model.nameI18n" value="${model.nameI18n}" maxlength="25" required="true" cssClass="inputStyle  clearable"/>
				     </div>
				</div>
	       
				<div class="formItem">
				     <label class="tit"><em>*</em>编码</label>
				     <div class="bdmain">
				         <uc:textfield id="${pageId}code" name="model.code" value="${model.code}" maxlength="20" required="true" cssClass="inputStyle"/>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit"><em></em>图标</label>
				     <div class="bdmain">
				         <uc:textfield name="model.icon" value="${model.icon}" maxlength="100" cssClass="inputStyle"/>
				     </div>
				</div>
	       
				<div class="formItem">
				     <label class="tit"><em>*</em>部署状态</label>
				     <div class="bdmain pt10">
				         <uc:radiolist list="${deployFlagMap}" name="model.deployFlag" required="true" value="${model.deployFlag}" cssClass="radioStyle"/>
				     </div>
				</div>
			
			</div>
		</uc:form>
