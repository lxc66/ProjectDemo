<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.module.list');
com.ue.platform.module.module.list={
	pageId:"${pageId}",
	refresh:function(){
		com.ue.platform.module.module.index.refresh();
	},
	toAdd:function(){
		$.dialog.load("${path}/sys/module/input",{
			title : '模块新增',
			width: 600,
			height: 300,
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.platform.module.module.input.doSave();
				return false;
		    },
			cancel:function(){}
		});
	},
	toEdit:function(id){
		$.dialog.load("${path}/sys/module/input?id="+id,{
					title : '模块修改',
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.module.input.doSave();
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
			  	url: '${path}/sys/module/delete?id='+id,
			  	processData:true,
			  	success: function(data){
			  		self.refresh();
			  	}
			 });
		});
	},
	toSort:function(){
		var self=this;
		$.getJSON("${path}/sys/module/getDataJson", function(jsondata){
			com.ue.sort.dataSort(jsondata,function(ids){
				var formData="";
				$.each(ids,function(){formData+="sels="+this+"&"});
				 $.ajax({
				 	type: "POST",
				  	url: "${path}/sys/module/sort",
				  	processData:true,
				  	data:formData,
				  	success: function(data){
				  		self.refresh();
				   		$.successTips();
				  	}
				 });
			},"系统模块排序");
		});
	}
};

$(document).ready(function() {
	
});

</script>
		<div class="mb10">
			<a class='button' href='javascript:void(0);' onclick='com.ue.platform.module.module.list.toAdd()'><span class='fm f8 mr5'></span>添加</a>
			<a class='button' href='javascript:void(0);' onclick='com.ue.platform.module.list.toSort()'><span class='fm f8 mr5'></span>排序</a>
		</div>
		
		<table class="cssTableBody">	
			<thead>
				<tr>
					<th width="3%" align="center">
						序号
					</th>
					<th nowrap="nowrap">
						名称
					</th>
					<th nowrap="nowrap">
						名称i18n
					</th>
					<th nowrap="nowrap">
						编码
					</th>
					<th nowrap="nowrap">
						状态
					</th>
					<th nowrap="nowrap">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pageObj}">
					<c:forEach items="${pageObj.list}" var="model" varStatus="st">
					<tr>
						<td field="1" class="num">${st.index + 1}</td>
						<td>${model.name }</td>
						<td>${model.nameI18n }</td>
						<td>${model.code }</td>
						<td>${model.deployFlagView }</td>
						<td>
							<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.module.list.toEdit("${model.id}");'><span class='fm f8 mr5'>p</span>修改</a>
							<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.module.list.doDel("${model.id}");'><span class='fm f8 mr5'>-</span>删除</a>
						</td>
					</tr>
					</c:forEach>
				</c:if>	
			</tbody>
		</table>
		<c:if test="${not empty pageObj}">
			<uc:pageBar pageInfo="${pageObj.info}" formId="${param.parentPageId}theForm" changePageSize="true" changePageSizeNumber="10,20,30" containerId="${param.parentPageId}list"></uc:pageBar>
		</c:if>
