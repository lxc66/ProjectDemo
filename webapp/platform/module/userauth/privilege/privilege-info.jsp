<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<style>
.tip-wrap {
	display: block;
	margin:11px 0;
}

.tip-wrap .tip-wrap-c {
	margin-left: 20px;
}

.tip-ul {
	overflow:hidden;
}
.tip-ul ul {margin-right:200px;}

.tip-ul li {
	display:inline-block;
	margin: 5px 10px;
	padding: 3px 15px;
	line-height: 27px;
	background: #fffaa1;
	position: relative;
	color:#d09b42;
}
</style>
<script>
Namespace.register('com.ue.platform.module.privilege.info');
com.ue.platform.module.privilege.info={
	pageId:"${pageId}",
	isExistsOperationUpdate:false,
	toEdit:function(){
		com.ue.platform.module.privilege.index.toEdit('${model.id}');
	},
	doDel:function(){
		com.ue.platform.module.privilege.index.doDel('${model.id}');
	},
	buildOperationTree:function(){
		var space = this;
		$.getJSON("${path}/bd/privilege!ajaxOperationTreeData.action?moduleId=${model.moduleId}", function(jsondata){
			com.ue.cmpt.tree.buildTree("${pageId}operationTree",jsondata,"check","checkbox");
			com.ue.cmpt.tree.setCascade("${pageId}operationTree");
     		com.ue.cmpt.tree.expandNodeByLevel("${pageId}operationTree",1);
			com.ue.cmpt.tree.setNodeClick("${pageId}operationTree" ,function(event, treeId, treeNode){
				treeNode.checked=!treeNode.checked;
				com.ue.cmpt.tree.getTree(treeId).updateNode(treeNode);
				space.isExistsOperationUpdate=true;
			});
			
			com.ue.cmpt.tree.setNodeChange("${pageId}operationTree",function(treeId, treeNode){
				space.isExistsOperationUpdate=true;
			});
			
   			var operationIds="${operationsIds}";
			$.each($(operationIds.split(",")),function(i){
				if(this=="")return;
				com.ue.cmpt.tree.expandNodeById("${pageId}operationTree",this,true,true);
				com.ue.cmpt.tree.checkNode("${pageId}operationTree",this)
			});
		});
	},
	updateOperationBind:function(){
		var space = this;
		if(!this.isExistsOperationUpdate){
<%--			alert("操作绑定无变更");--%>
			return;
		}
		
		confirm("<s:text name='bd_privilege_ensure_assign_operations'/>",function(){
			$("body").mask('<s:text name="message_saving"/>');
			var checkedIds=com.ue.cmpt.tree.getCheckNodeIds("${pageId}operationTree", true);
			var formData="model.id=${model.id}";
			if(checkedIds&& checkedIds!=""){
				$.each($(checkedIds.split(",")),function(i){formData+='&model.operationList['+i+'].id='+this;});
			}
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/bd/privilege!ajaxUpdateOperationBind.action",
			  	processData:true,
			  	data:formData,
			  	success: function(){
			  		space.isExistsOperationUpdate=false;
			  		$("body").unmask();
			  		$.successTips();
			  	}
			 });
		});
	},
	toSort:function(){
		var space=this;
		$.getJSON("${path}/bd/privilege!getDataJsonWithSameModule.action?id=${model.id}", function(jsondata){
			com.ue.sort.dataSort(jsondata,function(ids){
				var formData="";
				$.each(ids,function(){formData+="sels="+this+"&"});
				 $.ajax({
				 	type: "POST",
				  	url: "${path}/bd/privilege!sort.action",
				  	processData:true,
				  	data:formData,
				  	success: function(data){
				   		com.ue.platform.module.privilege.index.refresh('${model.id}');
				   		$.successTips();
				  	}
				 });
			},"系统权限排序");
		});
	},
	init:function(){
// 		$('#${pageId}theForm').validationEngine();
		$.hz.tabs.init("${pageId}tabs");
		$("#${pageId}tabs").closest("div.ui-layout-pane").removeClass("inLayout-center");
// 		com.ue.platform.module.privilege.info.buildOperationTree();
	}
};

$(document).ready(function() {
	com.ue.platform.module.privilege.info.init();
});
</script>
<div id="${pageId}tabs" class="tabs-container">
	<div title='权限信息'>
		<uc:form  action="" id="${pageId}theForm" method="post">
		<table class="cssTableBody">
			<tr>
				<td class="w2">名称</td><%-- 名称 --%>
				<td class="detail">${model.name}</td>
			</tr>
			<tr>
				<td>所属模块 </td><%-- 所属模块 --%>
				<td class="detail">${model.module.name}</td>
			</tr>	
		<c:if test="${isUseUrl}">
			<tr>
				<td>Url </td>
				<td class="detail">${model.url}</td>
			</tr>	
		</c:if>
			<tr>
				<td>描述</td><%-- 描述 --%>
				<td class="detail">${model.description}</td>
			</tr>
		</table>
		</uc:form>
		<div class="formItem">
			<a href="javascript:void(0);" class="button btnRed" onclick="com.ue.platform.module.privilege.info.doDel();">删除</a><%-- 删除--%>
			<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.privilege.info.toEdit();">编辑</a><%-- 编辑--%>
			<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.privilege.info.toSort();">排序</a><%-- 排序--%>
		</div>
	</div>
	<div title='拥有此权限的角色'>
		<div class="tip-wrap">
		<div class="tip-wrap-c">
			<div class="tip-ul">
				<ul>
					<c:if test="${not empty roles}">
						<c:forEach var="role" items="${roles}">
							<li class="tag">
								${role.name}
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
		</div>
	</div>
	<div title='拥有此权限的用户'>
		<table class="cssTableBody">
			<tr>
				<th class="w20">序号</th><%-- 序号 --%>
				<th>教师</th><%-- 教师 --%>
			</tr>
			<c:if test="${not empty users}">
				<c:forEach items="${users}" var="user" varStatus="userIndex">
					<tr>
						<td>${userIndex.count}</td><td>${user.name}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
	<%--
	<div title='可访问的操作' style="display: none;">
		<div class="abs0 b50 tc ovauto">
			<div id="${pageId}operationTree" class="w400 ml100 mt10"></div>
		</div>
		<div class="abs h50 w tc lh50 b0 l0">
			<a href="javascript:void(0);" id="${pageId}btn_operationBindUpdate" class="button btnDeepblue mt10" onclick="com.ue.platform.module.privilege.info.updateOperationBind();"><span class="fm f8 mr5"></span>更新分配操作</a>
		</div>
	</div>
	--%> 
</div>