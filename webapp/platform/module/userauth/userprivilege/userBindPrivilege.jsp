<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
Namespace.register('com.ue.platform.module.userprivilege.userBindPrivilege');
com.ue.platform.module.userprivilege.userBindPrivilege={
	pageId:"${pageId}",
	ok:function(){
	    var formData="model.id=${userId}";
	    var dataTbody = $("#${pageId}privilegeTableData");
	    var checkedPrivilege=$("input[type='checkbox'][name='privilege']:checked",dataTbody);
		if(checkedPrivilege.length>0){
			$.each(checkedPrivilege,function(i){formData+='&model.privilegeIdList['+i+']='+(this.value);});
		}
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/userprivilege/saveUserPrivileges",
		  	processData:true,
		  	data:formData,
		  	success: function(){
		  		$.successTips();
		  		com.ue.platform.module.user.detail.buildPrivilegeTree();
		   		art.dialog.list[com.ue.platform.module.user.detail.pageId].close();
		  	}
		 });
	},
	checkedPrivilege:function(){
		var privilegeIds="${privilegeIds}";
		var privilegeIdArray=privilegeIds.split(",");
		$.each(privilegeIdArray,function(){
			if(this=="")return;
			$("input[type='checkbox'][name='privilege'][value='"+this+"']",$("#${pageId}privilegeTableData")).attr('checked',true)
		});
	},
	data:null,
	<%--根据顶级文件夹构建统计表--%>
	buildPrivilegeTable:function(){
		var self = this;
		var dataTbody = $("#${pageId}privilegeTableData");
		$(this.data.tops).each(function(){
			var tr = $("<tr id='"+this.id+"'></tr>");
			var td = $("<td voId='"+this.id+"'>"+this.name+"</td>");
			td.attr("rowspan",self.data.lastNodeNumMap[this.id]);
			tr.append(td)
			dataTbody.append(tr);
			self.buildPrivilegeTableWithParent(this.id,this.id);
		});
		this.buildPrivilegeTableRepair();
	},
	<%--根据父级文件夹ID构建统计表数据--%>
	buildPrivilegeTableWithParent:function(parentTrId , parentVoId){
		var self = this;
		var dataTbody = $("#${pageId}privilegeTableData");
		var children = this.data.childMap[parentVoId];
		if(!children || children.length==0){
			$("td[voId="+parentVoId+"]" , dataTbody).attr("leaf",true);
			return;
		}
		$(children).each(function(i){
			var checkbox="<label style='cursor: pointer;'><input type='checkbox' name='privilege' value='"+this.id+"'>"+this.name+"</input></label>"
			var td = $("<td parentVoId='"+parentVoId+"' voId='"+this.id+"'>"+checkbox+"</td>");
			td.attr("rowspan",self.data.lastNodeNumMap[this.id]);
			
			if(i==0){
				$("#"+parentTrId , dataTbody).append(td);
				self.buildPrivilegeTableWithParent(parentTrId,this.id);
			}else{
				var tr = $("<tr id='"+this.id+"'></tr>");
				tr.append(td);
				dataTbody.append(tr);
				self.buildPrivilegeTableWithParent(this.id,this.id);
			}
		});
	},
	<%--补全统计表头部及空单元格--%>
	buildPrivilegeTableRepair:function(){
		var leafTdList = $("#${pageId}privilegeTableData td[leaf=true]");
		var maxDepth=this.data.maxDepth;
		
		<%--补全统计表头--%>
		var trHead=$("<tr></tr>");
		trHead.append("<th width='100'>模块</th>");
		trHead.append("<th colspan='"+(maxDepth-1)+"'>权限</th>");
		$("#${pageId}privilegeTableHead").append(trHead);
		
		<%--补全空单元格--%>
		var lastDepthMap = this.data.lastDepthMap;
		$(leafTdList).each(function(){
			var depth = lastDepthMap[$(this).attr("voId")];
			var parentTr = $(this).parent();
			for(var i=depth ; i<maxDepth ; i++){
				parentTr.append("<td></td>");
			}
		});
		
	},
	init:function(){
		this.data = $.parseJSON('${privilegeTableVOJson}');
		this.buildPrivilegeTable();
		this.checkedPrivilege();
	}
};
$(document).ready(function() {
	com.ue.platform.module.userprivilege.userBindPrivilege.init();
})

</script>
	<table class="cssTableBody border_gray">
		<thead id="${pageId}privilegeTableHead">
		
		</thead>
		<tbody id="${pageId}privilegeTableData">
		
		</tbody>
	</table>
