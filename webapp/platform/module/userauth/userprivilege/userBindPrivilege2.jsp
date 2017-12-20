<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.userprivilege.userBindPrivilege');
com.ue.platform.module.userprivilege.userBindPrivilege={
	pageId:"${pageId}",
	ok:function(){
	    var formData="model.id=${userId}";
		var privilegeIds = $.hz.ztree.getCheckedNodeIds("${pageId}privilegeTreeEdit");
		if(privilegeIds&& privilegeIds!=""){
			$.each($(privilegeIds.split(",")),function(i){formData+='&model.privilegeIdList['+i+']='+this;});
		}
		
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/userprivilege/saveUserPrivileges",
		  	processData:true,
		  	data:formData,
		  	success: function(){
		  		$.successTips();
		   		art.dialog.list[com.ue.platform.module.user.list.pageId].close();
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
// 		this.checkedRole();
	}
};
$(document).ready(function() {
	com.ue.platform.module.userprivilege.userBindPrivilege.init();
})

</script>
		<div id="${pageId}Content" style="position: absolute; top: 0;left: 0;right: 0;bottom: 0;">
			<table class="cssTableBody">
				<tr>
					<th>权限</th>
				</tr>
				<tr>
					<td>
						<uc:tree id="${pageId}privilegeTreeEdit" nodes="${privilegeTreeJson}" checkEnable="true" checkStyle="checkbox" expandLevel="2" checkValue="${privilegeIds}"></uc:tree>
					</td>
				</tr>
			</table>		
		</div>
