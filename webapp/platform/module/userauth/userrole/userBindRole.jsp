<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.userrole.userBindRole');
com.ue.platform.module.userrole.userBindRole={
	pageId:"${pageId}",
	ok:function(){
	    var formData="model.id=${userId}";
	    var checkedRoles=$("input[type='checkbox'][name='roles']:checked",$("#${pageId}Content"));
		if(checkedRoles.length>0){
			$.each(checkedRoles,function(i){formData+='&model.roleIdList['+i+']='+(this.value);});
		}
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/userrole/saveUserRoles",
		  	processData:true,
		  	data:formData,
		  	success: function(){
		  		$.successTips();
		  		com.ue.platform.module.user.detail.buildRoleInfo();
		   		art.dialog.list[com.ue.platform.module.user.detail.pageId].close();
		  	}
		 });
	},
	checkedRole:function(){
		var roleIds="${roleIds}";
		var roleIdArray=roleIds.split(",");
		$.each(roleIdArray,function(){
			if(this=="")return;
			$("input[type='checkbox'][name='roles'][value='"+this+"']",$("#${pageId}Content")).attr('checked',true)
		});
	},
	init:function(){
		this.checkedRole();
	}
};
$(document).ready(function() {
	com.ue.platform.module.userrole.userBindRole.init();
})

</script>
		<div id="${pageId}Content" style="position: absolute; top: 0;left: 0;right: 0;bottom: 0;">
			<table class="cssTableBody">
				<tr>
					<c:forEach items="${roleListTypeMap}" var="map">
						<th class="w20">${map['key']}角色</th>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach items="${roleListTypeMap}" var="map">
						<td class="w20">
							<uc:checkboxlist list="${map['value']}" name="roles" listKey="id" listValue="name" cols="1"></uc:checkboxlist>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
