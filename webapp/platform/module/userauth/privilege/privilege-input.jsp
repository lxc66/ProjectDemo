<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.privilege.input');
com.ue.platform.module.privilege.input={
	pageId:"${pageId}",
	doSave:function(){
		if(!$("#${pageId}theForm").valid()){
			return;
		}
// 		var checkedIds=com.ue.cmpt.tree.getCheckNodeIds("${pageId}operationTree", true);
// 		if(checkedIds&& checkedIds!=""){
// 			$.each($(checkedIds.split(",")),function(i){formData+='&model.operationList['+i+'].id='+this;});
// 		}

		var formData=$("#${pageId}theForm").serialize();
		$("body").mask('正在保存数据……');
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/privilege/save",
		  	processData:true,
		  	data:formData,
		  	success: function(id){
		  		$("body").unmask();
		  		com.ue.platform.module.privilege.index.refresh(id);
		  	}
		 });
	},
	changeOperation:function(){
		return;
		var moduleId=$("#${pageId}moduleId").val();
		if(moduleId==""){
			$("#${pageId}operationTree").html('');
			return;
		}
		$.getJSON("${path}/sys/privilege/ajaxOperationTreeData?moduleId="+moduleId, function(jsondata){
			com.ue.cmpt.tree.buildTree("${pageId}operationTree",jsondata,"check","checkbox");
			com.ue.cmpt.tree.setCascade("${pageId}operationTree");
   			com.ue.cmpt.tree.expandNodeByLevel("${pageId}operationTree",1);
			com.ue.cmpt.tree.setNodeClick("${pageId}operationTree" ,function(event, treeId, treeNode){
				treeNode.checked=!treeNode.checked;
				com.ue.cmpt.tree.getTree(treeId).updateNode(treeNode);
			});
			
			com.ue.cmpt.tree.setNodeChange("${pageId}operationTree",function(treeId, treeNode){
				
			});
   			<c:if test="${'edit'==option}">
   				var operationIds="${operationsIds}";
    			$.each($(operationIds.split(",")),function(i){
	     			if(this=="")return;
					com.ue.cmpt.tree.expandNodeById("${pageId}operationTree",this,true,true);
					com.ue.cmpt.tree.checkNode("${pageId}operationTree",this)
    			});
   			</c:if>
		});
	},
	buildParentTree:function(){
		$.fn.zTree.destroy("${pageId}parentTree");
		$("#${pageId}parentTreeContainer").empty().append("<ul id='${pageId}parentTree'></ul>");
		var moduleId=$("#${pageId}moduleId").val();
		if(moduleId==""){
			return;
		}
		var checkValue = "${model.moduleId}"==moduleId?"${model.parentId}":"";
		$.getJSON("${path}/sys/privilege/getParentTreeJson?moduleId="+moduleId+"&excludeId=${model.id}", function(jsondata){
			$.hz.ztree.init("${pageId}parentTree",{
                nodes:jsondata,
                expandLevel:"1",
                isDropdown:true,    //下拉树
                name:"model.parentId",
                checkValue:checkValue,  //回显值
                showParentName:true,//下拉树显示名称是否包含父级
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType: "all"
                },
                view: {
            		selectedMulti: false
            	},
                callback:{
                	onClick:function(event,treeId,treeNode){
                		$.hz.ztree.checkNodeWithId(treeId,treeNode.id);
                	},
                	onCheck:function(event,treeId,treeNode){
               			$.hz.ztree.selectNode(treeId,treeNode,treeNode.checked,false);
                	}
                }
            });
		});
	},
	init:function(){
// 		com.ue.platform.module.privilege.input.changeOperation();
		var self=this;
		var validateOptions = {
			rules: {
				"model.name" : {
					required:true,
					maxlength:25,
					remote:{
						type:"POST",
						url:"${path}/sys/privilege/ajaxCheckName",
						data:{
							name:function(){return $("#${pageId}name").val();},
							excludeName:"${model.name}"
		                } 
					}
				},
				"model.code" : {
					required:true,
					maxlength:25,
					remote:{
						type:"POST",
						url:"${path}/sys/privilege/ajaxCheckCode",
						data:{
							code:function(){return $("#${pageId}code").val();},
							excludeCode:"${model.code}"
		                } 
					}
				}
			},
			messages : {
				"model.name" : {
					remote : "权限名称已存在，请重新输入！"
				},
				"model.code" : {
					remote : "权限编码已存在，请重新输入！"
				}
			}
		};
		$.hz.validate.init("${pageId}theForm",validateOptions);
		
		this.buildParentTree();
		$("#${pageId}moduleId").bind("change",function(){
			self.buildParentTree();
		})
	}
};

$(document).ready(function() {
	com.ue.platform.module.privilege.input.init();
})
</script>
		<uc:form  action="" id="${pageId}theForm" method="post">
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"/>
			  	<uc:hidden name="model.num" value="${model.num}"/>
			</c:if>
			<div class="formLayout01">
				<fieldset>
					<legend>
						<b>权限信息</b> <%-- 权限信息 --%>
					</legend>
					<div class="control control_down"></div>
					<div class="form_content">
						<div class="formItem">
						     <label class="tit w2"><em>*</em>名称：</label><%-- 名称 --%>
						     <div class="bdmain w7">
						     	<uc:textfield id="${pageId}name" name="model.name" value="${model.name}" maxlength="50" required="true" cssClass="inputStyle w clearable"/>
						     </div>
						</div>
						
						<div class="formItem">
						     <label class="tit w2"><em>*</em>编码：</label><%-- 名称 --%>
						     <div class="bdmain w7">
						     	<uc:textfield id="${pageId}code" name="model.code" value="${model.code}" maxlength="50" required="true" cssClass="inputStyle w clearable"/>
						     </div>
						</div>
			       
						<div class="formItem">
						     <label class="tit w2"><em>*</em>所属模块：</label><%-- 所属子系统 --%>
						     <div class="bdmain w7">
						         <uc:select id="${pageId}moduleId" name="model.moduleId" value="${model.moduleId}" list="${moduleList}" listKey="id" listValue="name" required="true" cssClass="selectStyle" 
						         onchange="com.ue.platform.module.privilege.input.changeOperation();"/>
						     </div>
						</div>
					<c:if test="${isUseParent}">
						<div class="formItem">
						     <label class="tit w2"><em></em>父级权限：</label><%-- 所属子系统 --%>
						     <div class="bdmain w7" id="${pageId}parentTreeContainer">
						     	<ul id="${pageId}parentTree"></ul>
						     </div>
						</div>
					</c:if>
						
					<c:if test="${isUseUrl}">
						<div class="formItem">
						     <label class="tit w2"><em></em>Url：</label><%-- 名称 --%>
						     <div class="bdmain w7">
						     	<uc:textfield name="model.url" value="${model.url}" maxlength="100" required="true" cssClass="inputStyle w clearable"/>
						     </div>
						</div>
					</c:if>
						
						<div class="formItem">
						     <label class="tit w2">描述：</label><%-- 描述 --%>
						     <div class="bdmain w7">
						     	<uc:textarea name="model.description" cssClass="inputStyle h100 w" value="${model.description}" maxlength="100" cols="30"/>
						     </div>
						</div>
						
					</div>
				</fieldset>
			</div>
			<div class="formLayout01">
				<fieldset>
					<legend>
						<b>分配操作</b>
					</legend>
<!-- 					<div class="control control_down"></div> -->
					<div class="form_content" >
						<div class="formItem">
						     <div class="bdmain w p0 pl100">
						     	<div id="${pageId}operationTree" class="w9 auto"></div>
						     </div>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="formLayout01">
			  <label class="tit w2"></label>
			  <div class="bdmain w7">
			  	<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.privilege.input.doSave();">保存</a><%-- 保存 --%>
			  </div>
			</div>
		</uc:form>
		<div class="abs b0 w l0 h50 lh50 tc">
			
		</div>
