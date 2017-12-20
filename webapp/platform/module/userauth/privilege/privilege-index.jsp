<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
$.hz.component.include("jqueryui,tree,validate,layout,popupLayer,mask,tabs",false);
Namespace.register('com.ue.platform.module.privilege.index');
com.ue.platform.module.privilege.index={
	pageId:"${pageId}",
	refresh:function(id){
		var self = this;
		$("#${pageId}rightContent").html('');
		$.getJSON("${path}/sys/privilege/listTreeData", function(jsondata){
			$.hz.ztree.init("${pageId}privilegeTree",{
                nodes:jsondata,
                expandLevel:"1",
                view: {
            		selectedMulti: false
            	},
                callback:{
                	onClick:function(event,treeId,treeNode){
                		if(treeNode.nocheck)return;
                		self.toInfo(treeNode.id);
                	},
                	beforeClick:function(treeId,treeNode){
        				if(treeNode.nocheck) return false;
                	}
                }
            });
			if(id){
				$.hz.ztree.expandNodeWithId("${pageId}privilegeTree",id);
				$.hz.ztree.selectNodeWithId("${pageId}privilegeTree",id);
				self.toInfo(id);
			}else{
				self.loadDefault();
			}
		});
		
	},
	loadDefault:function(){
		var treeObj = $.hz.ztree.getZTreeObj("${pageId}privilegeTree");
		var filter = function(node){
			return (!node.nocheck && node.level == 1);
		}
		var defaultNode = treeObj.getNodesByFilter(filter, true); // 仅查找一个节点
		if(defaultNode){
			$.hz.ztree.selectNode("${pageId}privilegeTree",defaultNode);
			this.toInfo(defaultNode.id);
		}
	},
	toInfo:function(id){
		$("body").mask('正在加载数据……');
		$("#${pageId}rightContent").load("${path}/sys/privilege/info?id="+id,function(){
			$("body").unmask();
		});
	},
	toAdd:function(){
		$("body").mask('正在加载数据……');
		var selectNodes = $.hz.ztree.getSelectedNodes("${pageId}privilegeTree");
		$.each(selectNodes,function(){
			$.hz.ztree.selectNode("${pageId}privilegeTree",this,false,false);
		});
		$("#${pageId}rightContent").load("${path}/sys/privilege/input",function(){
			$("body").unmask();
		});
	},
	toEdit:function(id){
		$("body").mask('正在加载数据……');
		$("#${pageId}rightContent").load("${path}/sys/privilege/input?id="+id, 
			function(){
				$("body").unmask();
			}
		);
	},
	doDel:function(id){
		var space = this;
		confirm("确定要删除吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/privilege/delete?id='+id,
			  	processData:true,
			  	success: function(data){
			   		space.refresh();
			  	}
			 });
		});
	},
	init:function(){
		this.refresh();
	}
};

$(document).ready(function() {
	com.ue.platform.module.privilege.index.init();
	$('#${pageId}Content').layout({west__size:180,west__togglerLength_open:0,resizable:false});
});
</script>
<style>
.moduleIcon_open,.moduleIcon_close,.moduleIcon_docu{
	background:url('${path}/base/module/userauthorize/module.png') no-repeat center center  !important;
}
.privilegeIcon_open,.privilegeIcon_close,.privilegeIcon_docu{
	background:url('${path}/base/module/userauthorize/privilege.png') no-repeat center center  !important;
}
</style>
		<div id="${pageId}Content" class="abs0">
			<div class="rightPanel abs t0 b0 r5 ovauto" id="${pageId}rightContent">
			 
			</div>
			<div class="leftPanel abs t0 l0 b0">
				<div class="leftPanel">
					<div class="navTop">
						<a href="#" class="button ml30 mt15" onclick="com.ue.platform.module.privilege.index.toAdd()"><span class="fm f8 mr5"></span>新建权限</a><%-- 新建权限 --%>
					</div>
					<div class="navMid noBot">
						<div id="${pageId}privilegeTree">
				          
				    	</div>  		
					</div>
				</div>
			</div> 
		</div>
