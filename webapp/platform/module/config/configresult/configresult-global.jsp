<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
<head>
	<script>
		Namespace.register('com.ue.platform.module.config.configresult.global');
		com.ue.platform.module.config.configresult.global = {
			pageId : "${pageId}",
			doSave : function() {
				var self = this;
				$("body").mask('正在保存数据……');
				var formData = "";
				$("input[type='hidden'][name='configs']").each(function(i){
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
// 				alert(formData);
// 				return;
				$.ajax({
					type : "POST",
					url : '${path}/sys/configresult/saveGlobalConfigResult',
					processData : true,
					data : formData,
					async : false,
					success : function(data) {
						$.successTips();
						self.toView();
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
			toEdit:function(){
				$("#${pageId}theForm input").removeAttr("disabled"); 
				$("#${pageId}btn_toEdit").hide();
				$("#${pageId}btn_doSave").show();
			},
			toView:function(){
				$("#${pageId}theForm input").attr("disabled","disabled"); 
				$("#${pageId}btn_toEdit").show();
				$("#${pageId}btn_doSave").hide();
			},
			init:function(){
				$("#${pageId}theForm input").attr("disabled","disabled"); 
			}
		};
		$(document).ready(function() {
			com.ue.platform.module.config.configresult.global.init();
		});
	</script>
</head>
<body>
	<uc:form id="${pageId}theForm" method="post">
			<div class="formLayout01 mw500">
				<c:if test="${not empty globalConfigResultVO}">
					<c:forEach items="${globalConfigResultVO.configs}" var="config">
							<div class="formItem">
								<label class="tit w2"> ${config.name}: </label>
								<input type="hidden" value="${config.id}" name="configs" mode="${config.mode}">
								<div class="bdmain detail_right w7">
									<c:if test="${config.modeRadio}">
										 <c:forEach items="${globalConfigResultVO.configItemMap[config.id]}" var="item">
										 	<label><input type="radio" id="${item.id}" value="${item.value}" name="${config.id}Name" 
										 	<c:if test="${item.value==globalConfigResultVO.configResultValueMap[config.id]}">checked="checked"</c:if>/>${item.name}</label>
										 </c:forEach>
									</c:if>
									<c:if test="${config.modeCheckbox}">
										<c:forEach items="${globalConfigResultVO.configItemMap[config.id]}" var="item">
										 	<label><input type="checkbox" id="${item.id}" value="${item.value}" name="${config.id}Name" 
										 	<c:if test="${fn:contains(globalConfigResultVO.configResultValueMap[config.id],item.value)}">checked="checked"</c:if>/>${item.name}</label>
										 </c:forEach>
									</c:if>
									<c:if test="${config.modeInput}">
										<uc:textfield name="${config.id}Name" value="${globalConfigResultVO.configResultValueMap[config.id]}"></uc:textfield>
									</c:if>
								</div>
							</div>	
					</c:forEach>
				</c:if>
			</div>
			<div class="formButton">
				<div id="${pageId}btn_toEdit">
					<a href="javascript:;" onclick="com.ue.platform.module.config.configresult.global.toEdit();">编辑</a>
				</div>
				<div id="${pageId}btn_doSave" style="display: none">
					<a href="javascript:;" onclick="com.ue.platform.module.config.configresult.global.doSave();">保存</a>
					<a href="javascript:;" onclick="com.ue.platform.module.config.configresult.global.toView();">取消</a>
				</div>
			</div>
	</uc:form>
</body>
</html>
