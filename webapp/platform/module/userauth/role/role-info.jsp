<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.role.info');
com.ue.platform.module.role.info={
	pageId:"${pageId}",
	checkedPrivilegeIds:"${model.privilegeIds}",
	toEdit:function(){
		com.ue.platform.module.role.index.toEdit('${model.id}');
	},
	doDel:function(){
		com.ue.platform.module.role.index.doDel('${model.type}','${model.id}');
	},
	forbidden:function(){
		confirm("确定要禁用吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/role/forbidden?id=${model.id}',
			  	processData:true,
			  	success: function(data){
			   		com.ue.platform.module.role.index.toInfo('${model.id}');
			  	}
		 	});
		});
	},
	enabled:function(){
		confirm("确定要启用吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/role/enabled?id=${model.id}',
			  	processData:true,
			  	success: function(data){
			   		com.ue.platform.module.role.index.toInfo('${model.id}');
			  	}
		 	});
		});
	},
	setInternal:function(){
		confirm("确定要设置为系统内置职务吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/role/settingSystemInternal?id=${model.id}',
			  	processData:true,
			  	success: function(data){
			   		com.ue.platform.module.role.index.toInfo('${model.id}');
			  	}
		 	});
		});
		
	},
	cancelInternal:function(){
		confirm("确定取消系统内置职务吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/role/cancelSystemInternal?id=${model.id}',
			  	processData:true,
			  	success: function(data){
			   		com.ue.platform.module.role.index.toInfo('${model.id}');
			  	}
		 	});
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
		$.getJSON("${path}/sys/role/getRolePrivilegesTreeJson?id=${model.id}", function(jsondata){
			$.hz.ztree.init("${pageId}privilegeTree",{
		        nodes:jsondata,
		        expandLevel:"2"
		    });
		});
	},
	buildPrivilegeEditTree:function(){
		$.hz.ztree.init("${pageId}privilegeTreeEdit",{
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
	updatePrivilege:function(){
		var self=this;
		confirm("确认更新分配权限？",function(){
			$("body").mask('正在保存数据……');
			var formData="model.id=${model.id}";
			var privilegeIds = $.hz.ztree.getCheckedNodeIds("${pageId}privilegeTreeEdit");
			if(privilegeIds&& privilegeIds!=""){
				$.each($(privilegeIds.split(",")),function(i){formData+='&model.privilegeIdList['+i+']='+this;});
			}
			
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/sys/role/updateRolePrivilege",
			  	processData:true,
			  	data:formData,
			  	success: function(privilegeIds){
			  		$("body").unmask();
			  		self.checkedPrivilegeIds = privilegeIds;
			  		$.successTips();
			  		com.ue.platform.module.role.info.buildPrivilegeTree();
			  		com.ue.platform.module.role.info.backToInfo();
			  	}
			 });
		});
	},
	backToInfo:function(){
		var self=this;
		$("#${pageId}privilegeEditTree").hide();
		$("#${pageId}privilegeToSaveBtn").hide();
		$("#${pageId}privilegeTree").show();
		$("#${pageId}privilegeToEditBtn").show();
		
		//--纠正权限编辑树的勾选值
		var treeObj = $.hz.ztree.getZTreeObj("${pageId}privilegeTreeEdit");
		$.hz.ztree.checkAllNodes("${pageId}privilegeTreeEdit",false); 
		$.each($(self.checkedPrivilegeIds.split(",")),function(i){
			if(this=="")return;
			$.hz.ztree.checkNodeWithId("${pageId}privilegeTreeEdit",this); 
		});
	},
	toEditPrivilege:function(){
		$("#${pageId}privilegeTree").hide();
		$("#${pageId}privilegeToEditBtn").hide();
		$("#${pageId}privilegeEditTree").show();
		$("#${pageId}privilegeToSaveBtn").show();
	},
	init:function(){
		$.hz.tabs.init("${pageId}tabs");
		$("#${pageId}tabs").closest("div.ui-layout-pane").removeClass("inLayout-center");
		this.buildPrivilegeTree();
		this.buildPrivilegeEditTree();
// 		com.ue.platform.module.role.info.checkedPrivilege();
	}
};

</script>
		<uc:form  action="" id="${pageId}theForm" method="post">
			<div class="tabs-container abs5" id="${pageId}tabs">
				<div title="角色信息"><%-- 职务信息 --%>
					<div style="position: absolute; top: 0;left: 0;right: 0;bottom: 50px;overflow: auto;">
						<table class="cssTableBody">
							<tr>
								<td class="w100">名称</td><%-- 名称 --%>
								<td class="detail">${model.name}</td>
							</tr>
							<tr>
								<td>编码</td><%-- 职务编码 --%>
								<td class="detail">${model.code}</td>
							</tr>	
	<%--						<tr>--%>
	<%--							<td><s:text name="bd_position_status"/></td> 状态 --%>
	<%--							<td class="detail">${model.enableFlagView}</td>--%>
	<%--						</tr>--%>
						</table>
					</div>
					<div style="position: absolute; left: 0;right: 0;bottom: 0px;height: 50px; line-height:50px;text-align: center;">
							<ub:authorize ifAnyGranted="SUPERADMIN-hidden">
								<c:if test="${model.systemInternal}">
									<a href="javascript:void(0);" class="button btnPink" onclick="com.ue.platform.module.role.info.cancelInternal();"><span class="fm f8">x</span>取消内置</a>
								</c:if>
								<c:if test="${not model.systemInternal}">
									<a href="javascript:void(0);" class="button btnDeepblue" onclick="com.ue.platform.module.role.info.setInternal();"><span class="fm f8">y</span>设置为内置</a>
								</c:if>
							</ub:authorize>
							<c:if test="${not model.systemInternal}">
								<a href="javascript:void(0);" class="button btnRed" onclick="com.ue.platform.module.role.info.doDel();">删除</a>
								<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.role.info.toEdit();">编辑</a>
							</c:if>
							<c:if test="${model.systemInternal}">
								<!-- 页面提示信息 -->
								<div class="warning-small corners m5">
									<p>
										<b>提示信息!&nbsp;</b> 此职务为[系统内置]不能编辑.
									</p>
									<div class="delx-box"></div>
								</div>
							</c:if>
					</div>
				</div>
				<div title="分配权限"><%-- 分配权限--%>
					<div style="position: absolute; top: 0;left: 0;right: 0;bottom: 50px;overflow: auto;border: 1px solid;border-color:#bebebe;">
						<div class="w400 ml100 mt10">
							<ul id="${pageId}privilegeTree"></ul>
						</div>
						
						<div class="w400 ml100 mt10" id="${pageId}privilegeEditTree" style="display: none;">
							<ul id="${pageId}privilegeTreeEdit"></ul>
						</div>
<%-- 				     	<table id="${pageId}privilegeTable" class="cssTableBody " style="display: none;"> --%>
<!-- 							<tr> -->
<%-- 							   <th class="w200">子系统</th>子系统 --%>
<%-- 							   <th class="w200">权限</th>权限 --%>
<%-- 							   <th>操作</th>操作 --%>
<!-- 							 </tr> -->
<!-- 						</table> -->
					</div>
					<div style="position: absolute; left: 0;right: 0;bottom: 0px;height: 50px;text-align: center;">
						<div class="mt10" id="${pageId}privilegeToSaveBtn" style="display: none;">
							<a href="javascript:void(0);" id="${pageId}btn_operationBindUpdate" class="button btnDeepblue" onclick="com.ue.platform.module.role.info.updatePrivilege();">
								<span class="fm f8 mr5"></span>更新分配权限<%-- 更新分配权限 --%>
							</a>
							<a href="javascript:void(0);" id="${pageId}backToInfo" class="button" onclick="com.ue.platform.module.role.info.backToInfo();">
								返回
							</a>
						</div>
						<div class="mt10" id="${pageId}privilegeToEditBtn">
							<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.role.info.toEditPrivilege();">更新权限</a>
						</div>
					</div>
				</div>
				<div title="查看用户 "><%-- 查看用户 --%>
					<div style="position: absolute; top: 0;left: 0;right: 0;bottom: 50px;overflow: auto;border: 1px solid;border-color:#bebebe;padding: 5px;">
						<table class="cssTableBody">
							<tr>
								<th width="20">序号</th><%-- 序号 --%>
								<th>用户</th><%-- 教师 --%>
							</tr>
							<c:if test="${not empty userList}">
								<c:forEach items="${userList}" var="user" varStatus="userIndex">
									<tr>
										<td>${userIndex.count}</td><td>${user.name}</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>					
					</div>
					<div style="position: absolute; left: 0;right: 0;bottom: 0px;height: 50px;text-align: center;">
<!-- 						<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.role.info.toEditPrivilege();">分配用户</a> -->
					</div>
				</div>
			</div>
		</uc:form>
<script>
$(document).ready(function() {
	com.ue.platform.module.role.info.init();
})
</script>