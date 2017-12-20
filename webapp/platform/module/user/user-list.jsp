<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.user.list');
com.ue.platform.module.user.list={
	pageId:"${pageId}",
	refresh:function(){
		$.hz.jqGrid.refreshTable("${pageId}list");
	},
	toAdd:function(){
		$.dialog.load("${path}/sys/user/input?userType=${userType}",{
			title : '用户新增',
			width: 600,
			height: 300,
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.platform.module.user.input.doSave();
				return false;
		    },
			cancel:function(){}
		});
	},
	toEdit:function(id){
		$.dialog.load("${path}/sys/user/input?id="+id,{
					title : '用户修改',
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.user.input.doSave();
						return false;
				    },
					cancel:function(){}
		});
	},
	doDel:function(id){
		var self = this;
		confirm("确定要删除吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/user/delete?id='+id,
			  	processData:true,
			  	success: function(data){
			  		self.refresh();
			  	}
			 });
		});
	},
	forbidden:function(id){
		var space = this;
		confirm("确定要禁用?",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/user/forbidden?id='+id,
			  	processData:true,
			  	success: function(data){
			  		com.ue.platform.module.user.list.refresh();
			  	}
		 	});
		});
	},
	enabled:function(id){
		var space = this;
		confirm("确定要启用?",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/user/enabled?id='+id,
			  	processData:true,
			  	success: function(data){
			  		com.ue.platform.module.user.list.refresh();
			  	}
		 	});
		});
	},
	toResetPassword:function(id){
		$.dialog.load("${path}/sys/user/toResetPassword?id="+id,{
					title : "重置用户密码",
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.user.resetPassword.doResetPassword();
						return false;
				    },
					cancel:function(){}
		});
	},
	toDetail:function(id){
		$.dialog.load("${path}/sys/user/detail?id="+id,{
					title : "用户信息",
					width: "100%",
					height: "100%",
					fixed: true,
					id: '${pageId}',
					lock: true
		});
	},
	bindTable:function(){
		$.hz.jqGrid.init("${pageId}list","${path}/sys/user/listData?model.type=${userType}");
		var opHtml=[];
		opHtml.push("<div class='fl ml10'>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='com.ue.platform.module.user.list.toAdd()'><span class='fm f8 mr5'>+</span>添加</a>");
		opHtml.push("</div>");
		$.hz.jqGrid.appendToolBarBtn("${pageId}list",opHtml);
	},
	tableCellRedrawWithOP:function(cellvalue,options,rowObject){
		var opHtml=[];
		opHtml.push("<div class='tc'>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.user.list.toEdit(\""+rowObject.id+"\");'><span class='fm f8 mr5'>f</span>修改</a>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.user.list.doDel(\""+rowObject.id+"\");'><span class='fm f8 mr5'>-</span>删除</a>");
		if(rowObject.enableFlag=='1'){
			opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.user.list.forbidden(\""+rowObject.id+"\");'><span class='fm f8'>x</span>禁用</a>");
		}
		if(rowObject.enableFlag=='0'){
			opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.user.list.enabled(\""+rowObject.id+"\");'><span class='fm f8'>y</span>启用</a>");
		}
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.user.list.toResetPassword(\""+rowObject.id+"\");'><span class='fm f8 mr5'>p</span>重置密码</a>");
		opHtml.push("<a href='javascript:void(0);' class='button medium' onclick='com.ue.platform.module.user.list.toDetail(\""+rowObject.id+"\");'><span class='fm f8 mr5'>p</span>查看详情</a>");
		opHtml.push("</div>");
		return opHtml.join("");
	},
	init:function(){
		this.bindTable();
	}
};

$(document).ready(function() {
	com.ue.platform.module.user.list.init();
});

</script>
        <table id="${pageId}list" multiselect="false" pager="pager" toolbar="true" class="jqgrid">
			<thead>
				<tr>
					<th name="id" hide="true">
	
					</th>
					<th name="name" sort='true'>
						姓名 <%-- 用户姓名 --%>
					</th>
					<th name="loginName" sort='true'>
						登陆名<%-- 登陆名 --%>
					</th>
					<th name="email" sort='true'>
						Email<%-- Email --%>
					</th>
					<th name="enableFlagView" index="enableFlag" sort='true'>
						状态<%-- 状态 --%>
					</th>
					<th name="op" thWidth="400" formatter="com.ue.platform.module.user.list.tableCellRedrawWithOP">
						 操作 <%-- 操作 --%>
					</th>
				</tr>
			</thead>
		</table>
