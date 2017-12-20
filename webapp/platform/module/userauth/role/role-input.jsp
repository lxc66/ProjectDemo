<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.role.input');
com.ue.platform.module.role.input={
	pageId:"${pageId}",
	doSave:function(){
		if(!$("#${pageId}theForm").valid()){
			return;
		}

		var formData=$("#${pageId}theForm").serialize();
		var privilegeIds = $.hz.ztree.getCheckedNodeIds("${pageId}privilegeTree");
		if(privilegeIds&& privilegeIds!=""){
			$.each($(privilegeIds.split(",")),function(i){formData+='&model.privilegeIdList['+i+']='+this;});
		}
		$("body").mask('正在保存数据……');
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/role/save",
		  	processData:true,
		  	data:formData,
		  	success: function(id){
		  		var roleType=$("input[type='radio'][name='model.type']:checked").val();
		  		$("body").unmask();
		  		com.ue.platform.module.role.index.refresh(roleType,id);
		  	}
		 });
	},
	checkedPrivilege:function(){
		var privilegeIds="${model_privilegeIds}";
		var privilegeIdArray=privilegeIds.split(",");
		$.each(privilegeIdArray,function(){
			if(this=="")return;
			$("#${pageId}privilege_"+this).attr('checked',true)
		});
	},
	buildPrivilegeTree:function(){
		$.hz.ztree.init("${pageId}privilegeTree",{
            nodes:${privilegeTreeJson},
            expandLevel:"2",
            checkValue:"${model.privilegeIds}",  
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType:{ "Y": "p", "N": "s" }
            },
            callback:{
            	beforeClick:function(treeId,treeNode){
            		$.hz.ztree.checkNode(treeId,treeNode,!treeNode.checked,true);
            		return false;
            	}
            }
        });
	},
	init:function(){
		var validateOptions = {
			rules: {
				"model.name" : {
					required:true,
					maxlength:50,
					remote:{
						type:"POST",
						url:"${path}/sys/role/ajaxCheckName",
						data:{
							name:function(){return $("#${pageId}name").val();},
							excludeName:"${model.name}"
		                } 
					}
				},
				"model.code" : {
					required:true,
					maxlength:100,
					remote:{
						type:"POST",
						url:"${path}/sys/role/ajaxCheckCode",
						data:{
							code:function(){return $("#${pageId}code").val();},
							excludeCode:"${model.code}"
		                } 
					}
				}
			},
			messages : {
				"model.name" : {
					remote : "角色名称已存在，请重新输入！"
				},
				"model.code" : {
					remote : "角色编码已存在，请重新输入！"
				}
			}
		};
		$.hz.validate.init("${pageId}theForm",validateOptions);
		this.buildPrivilegeTree();
		
		
		<c:if test="${'edit'==option}">
			com.ue.platform.module.role.input.checkedPrivilege();
		</c:if>
	}
};

$(document).ready(function() {
	com.ue.platform.module.role.input.init();
})
</script>
		<uc:form  action="" id="${pageId}theForm" method="post">
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"/>
			  	<uc:hidden name="model.systemFlag" value="${model.systemFlag}"/>
			  	<uc:hidden name="model.num" value="${model.num}"/>
			</c:if>
			<div class="formLayout01">
				<fieldset>
					<legend>
						<b>职务信息</b>
					</legend>
					<div class="formLayout01">
						<div class="formItem">
						     <label class="tit w2"><em>*</em>名称：</label><%-- 名称 --%>
						     <div class="bdmain w6">
						     	<uc:textfield id="${pageId}name" name="model.name" value="${model.name}" maxlength="50"  required="true" cssClass="inputStyle w8 clearable"/>
						     </div>
						</div>
						
						<div class="formItem">
						     <label class="tit w2"><em>*</em>编码：</label><%-- 编码 --%>
						     <div class="bdmain w6">
							  	<uc:textfield id="${pageId}code" name="model.code" value="${model.code}" maxlength="100"  required="true" cssClass="inputStyle w8 clearable"/>
						     </div>
						</div>
						
						<div class="formItem">
						     <label class="tit w2"><em>*</em>职务类型：</label><%-- 职务类型 --%>
						     <div class="bdmain w6">
						     	<div class="selStyle fl h30 lh30 w8">
						     		<uc:radiolist id="${pageId}type" list="${positionTypeList}" listKey="code" listValue="value" name="model.type" required="true" value="${model.type}" cssClass="radioStyle"/>
						     	</div>
						     </div>
						</div>
					</div>
	       		</fieldset>
	       		<fieldset>
					<legend>
						<b>分配权限</b><%-- 分配权限 --%>
					</legend>
<!-- 					<div class="control control_down"></div> -->
					<div class="form_content">
			       		<div class="formItem">
						     <div class="bdmain w p0">
						     	<ul id="${pageId}privilegeTree"></ul>
<!-- 								<table class="cssTableBody w9 auto"> -->
<!-- 									<tr> -->
<!-- 									   <th class="w150">模块</th> -->
<!-- 									   <th class="w150">角色权限</th> -->
<!-- 									   <th>权限操作</th> -->
<!-- 									 </tr> -->
<!-- 								</table> -->
						     </div>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="formLayout01">
			     <label class="tit w2"></label>
			     <div class="bdmain w6"><a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.role.input.doSave();">保存 <%-- 保存 --%></a></div>
			</div>
		</uc:form>
		<div class="abs b0 w l0 h50 lh50 tc">
			
		</div>
