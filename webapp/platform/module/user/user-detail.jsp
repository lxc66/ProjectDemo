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
Namespace.register('com.ue.platform.module.user.detail');
com.ue.platform.module.user.detail = {
	pageId : "${pageId}",
	toEditUserPrivileges:function(){
		$.dialog.load("${path}/bd/userPrivilege!toEdit.action?userId=${model.id}",{
			title : '用户单独授权',
			width: '100%',
			height: '100%',
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.base.module.userPrivilege.edit.save();
				return false;
		    },
			cancel:function(){}
		});
	},
	buildRoleInfo:function(){
		$.getJSON("${path}/sys/userrole/getRolesJson?userId=${model.id}", function(jsondata){
			$("#${pageId}roleInfo").empty();
			var items=[];
			$.each(jsondata,function(){
				items.push("<li class='tag'>"+this.name+"</li>")
			});
			$("#${pageId}roleInfo").append(items.join(''));
		});
	},
	buildPrivilegeTree:function(){
		$("#${pageId}privilegeTree").empty();
		$.getJSON("${path}/sys/userprivilege/getPrivilegesTreeJson?userId=${model.id}", function(jsondata){
			$.hz.ztree.init("${pageId}privilegeTree",{
		        nodes:jsondata,
		        expandLevel:"3"
		    });
		});
	},
	toUserBindRole:function(){
		$.dialog.load("${path}/sys/userrole/toUserBindRole?userId=${model.id}",{
					title : "用户分配角色",
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.userrole.userBindRole.ok();
						return false;
				    },
					cancel:function(){}
		});
	},
	toUserBindPrivilege:function(){
		$.dialog.load("${path}/sys/userprivilege/toUserBindPrivilege?userId=${model.id}",{
					title : "用户分配权限",
					width: "80%",
					height: "80%",
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.userprivilege.userBindPrivilege.ok();
						return false;
				    },
					cancel:function(){}
		});
	},
	init:function(){
		this.buildPrivilegeTree();
	}
	
};

$(document).ready(function(){
	$('#${pageId}tab').tabs();
	com.ue.platform.module.user.detail.init();
});
</script>
<div id="${pageId}tab" class="tabs-container">
	<div id="${pageId}edit" title='用户信息'>
		<div class="formItem">
		     <label class="tit">姓名</label>
	     	${model.name}
		</div>
		<div class="formItem">
		     <label class="tit">登录名</label>
	     	${model.loginName}
		</div>
	</div>
	<div title='分配角色'>
		<div class="tip-ul" style="position: absolute; top: 0;left: 0;right: 0;bottom: 50px;overflow: auto;border: 1px solid;border-color:#bebebe;">
			<ul id="${pageId}roleInfo">
				<c:forEach items="${roles}" var="role">
					<li class="tag">
						${role.name}
					</li>
				</c:forEach>
			</ul>
		</div>
		<div style="position: absolute; left: 0;right: 0;bottom: 0px;height: 50px;text-align: center;">
			<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.user.detail.toUserBindRole();">分配角色</a>
		</div>
	</div>
	<div title='单独授权'>
		<div style="position: absolute; top: 0;left: 0;right: 0;bottom: 50px;overflow: auto;border: 1px solid;border-color:#bebebe;">
			<ul id="${pageId}privilegeTree"></ul>
		</div>
		<div style="position: absolute; left: 0;right: 0;bottom: 0px;height: 50px;text-align: center;">
			<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.user.detail.toUserBindPrivilege();">单独授权</a>
		</div>
	</div>
</div>