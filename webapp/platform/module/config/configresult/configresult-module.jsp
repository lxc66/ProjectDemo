<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
<head>
<script>
	Namespace.register('com.ue.platform.module.config.configresult.module');
	com.ue.platform.module.config.configresult.module = {
		pageId : "${pageId}",
		doSave : function(moduleId) {
			var self = this;
			$("body").mask('正在保存数据……');
			var formData = "moduleId="+moduleId;
			$("input[type='hidden'][name='configs']",$("#"+moduleId)).each(function(i){
				var configId=$(this).val();
				var mode=$(this).attr("mode");
				formData+="&vos["+i+"].configId="+configId;
				formData+="&vos["+i+"].mode="+mode;
				var value = "";
				if(mode=="1" || mode=="2"){
					$("input[name='"+configId+"Name']:checked").each(function(){
						value += this.id+",";
					});
				}else if(mode=="3"){
					value = $("input[type='text'][name='"+configId+"Name']").val();
				}
				$.each(value.split(','),function(j){
					if(this=="")return;
					formData+="&vos["+i+"].values["+j+"]="+this;
				})
			});
// 			alert(formData);return;
			$.ajax({
				type : "POST",
				url : '${path}/sys/configresult/saveModuleConfigResult',
				processData : true,
				data : formData,
				async : false,
				success : function(data) {
					$.successTips();
					self.toView(moduleId);
					$("body").unmask();
				}
			});
		},
		reset : function(id, defalutValue, mode) {
			if (mode == '0') {
				$("input[name='" + id + "Name']").each(function() {
					$(this).val(defalutValue);
				});
			} else if (mode == '1') {
				$("input[name='" + id + "Name']").attr("checked", false);
				$("input[name='" + id + "Name']").each(function() {
					if ($(this).val() == defalutValue) {
						$(this).attr("checked", true);
					}
				});
			} else if (mode == '2') {
				$("input[name='" + id + "Name']").attr("checked", false);
				$("input[name='" + id + "Name']").each(function() {
					if ($(this).val() == defalutValue) {
						$(this).attr("checked", true);
					}
				});
			}
		},
		toEdit:function(moduleId){
			$("#"+moduleId+" input").removeAttr("disabled"); 
			$("#"+moduleId+"btn_toEdit").hide();
			$("#"+moduleId+"btn_doSave").show();
		},
		toView:function(moduleId){
			$("#"+moduleId+" input").attr("disabled","disabled"); 
			$("#"+moduleId+"btn_toEdit").show();
			$("#"+moduleId+"btn_doSave").hide();
		},
		init:function(){
			$("#${pageId}theForm input").attr("disabled","disabled"); 
		}
	};
	$(document).ready(function() {
		com.ue.platform.module.config.configresult.module.init();
	});
</script>
</head>
<body>
	<uc:form id="${pageId}theForm" method="post">
		<div class="formLayout03 mw500 m5">
			<c:if test="${not empty moduleConfigResultVO}">
				<c:forEach items="${moduleConfigResultVO.modules}" var="module">
					<fieldset id="${module.id}">
						<legend> ${module.name} </legend>
						<div class="control control_down"></div>
						<div class="form_content">
							<c:forEach items="${moduleConfigResultVO.moduleConfigMap[module.id]}" var="config">
									<div class="formItem">
										<label class="tit w2"> ${config.name}: </label>
										<input type="hidden" value="${config.id}" name="configs" mode="${config.mode}">
										<div class="bdmain detail_right w7">
											<c:if test="${config.modeRadio}">
												 <c:forEach items="${moduleConfigResultVO.configItemMap[config.id]}" var="item">
												 	<label><input type="radio" id="${item.id}" value="${item.value}" name="${config.id}Name" 
												 	<c:if test="${item.value==moduleConfigResultVO.configResultValueMap[config.id]}">checked="checked"</c:if>/>${item.name}</label>
												 </c:forEach>
											</c:if>
											<c:if test="${config.modeCheckbox}">
												<c:forEach items="${moduleConfigResultVO.configItemMap[config.id]}" var="item">
												 	<label><input type="checkbox" id="${item.id}" value="${item.value}" name="${config.id}Name" 
												 	<c:if test="${fn:contains(moduleConfigResultVO.configResultValueMap[config.id],item.value)}">checked="checked"</c:if>/>${item.name}</label>
												 </c:forEach>
											</c:if>
											<c:if test="${config.modeInput}">
												<uc:textfield name="${config.id}Name" value="${moduleConfigResultVO.configResultValueMap[config.id]}"></uc:textfield>
											</c:if>
										</div>
									</div>	
							</c:forEach>
						</div>
						<div>
							<div id="${module.id}btn_toEdit">
								<a href="javascript:;" onclick="com.ue.platform.module.config.configresult.module.toEdit('${module.id}');">编辑</a>
							</div>
							<div id="${module.id}btn_doSave" style="display: none">
								<a href="javascript:;" onclick="com.ue.platform.module.config.configresult.module.doSave('${module.id}');">保存</a>
								<a href="javascript:;" onclick="com.ue.platform.module.config.configresult.module.toView('${module.id}');">取消</a>
							</div>
						</div>
					</fieldset>
				</c:forEach>
			</c:if>
		</div>
	</uc:form>
</body>
</html>
