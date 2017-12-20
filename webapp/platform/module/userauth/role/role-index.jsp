<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.role.index');
com.ue.platform.module.role.index={
	pageId:"${pageId}",
	current_type:"",
	refresh:function(roleType,id){
		this.current_type=roleType;
		$.hz.tabs.selectWithUid("${pageId}tab",roleType);
		this.loadRoleListWithType(roleType,id);
	},
	loadRoleListWithType:function(type,id){
		var self=this;
		$("#${pageId}rightContent").html('');
		$.getJSON("${path}/sys/role/getDataJsonWithType?type="+type, function(jsondata){
			var roleListId = "${pageId}roleListWith"+type;
			$("#${pageId}Content ul[id^='${pageId}roleListWith']").html('');
			self.buildPositionList(jsondata,roleListId);
			if(id){
				self.toInfo(id);
			}else{
				self.loadDefault();
			}
		});
	},
	loadDefault:function(){
		var roleItem = $("#${pageId}roleListWith"+this.current_type+" li a").eq(0);
		if(roleItem&&roleItem.length==1){
			roleItem.click();
		}
	},
	buildPositionList:function(jsondata,roleListId){
		$.each(jsondata,function(){
				var potision_li=[];
				potision_li.push("<li>");
				potision_li.push("<a onclick='com.ue.platform.module.role.index.toInfo(\""+this.id+"\")' href='javascript:void(0);' class='nav_icon' id='${pageId}listNode_"+this.id+"'>");
				potision_li.push(this.name);
				potision_li.push("</a>");
				potision_li.push("</li>");
				$("#"+roleListId).append(potision_li.join(''));
		});
	},
	toInfo:function(id){
		<%--设置职务列表节点选中--%>
		$(".nav_icon").removeClass("current");
		$("#${pageId}listNode_"+id).addClass("current");
		<%--加载职务信息--%>
		$("body").mask('正在加载信息……');
		$("#${pageId}rightContent").load("${path}/sys/role/info?id="+id,function(){
			$("body").unmask();
		});
	},
	toAdd:function(){
		$("body").mask('正在加载信息……');
		$('.current').removeClass("current");
		$("#${pageId}rightContent").load("${path}/sys/role/input",function(){
			$("body").unmask();
		});
	},
	toEdit:function(id){
		$("body").mask('正在加载信息……');
		$("#${pageId}rightContent").load("${path}/sys/role/input?option=edit&id="+id, 
			function(){
				$("body").unmask();
			}
		);
	},
	doDel:function(positionType,id){
		var space = this;
		confirm("确定要删除吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/role/delete?id='+id,
			  	processData:true,
			  	success: function(data){
			   		space.refresh(positionType);
			  	}
			 });
		});
	},
	toSort:function(){
		var space=this;
		$.getJSON("${path}/sys/role/getDataJsonWithType?type="+space.current_type, function(jsondata){
			com.ue.sort.dataSort(jsondata,function(ids){
				var formData="";
				$.each(ids,function(){formData+="sels="+this+"&"});
				 $.ajax({
				 	type: "POST",
				  	url: "${path}/sys/role/sort",
				  	processData:true,
				  	data:formData,
				  	success: function(data){
				   		space.refresh(space.current_type);
				   		$.successTips();
				  	}
				 });
			},"系统职务排序");
		});
	},
	init:function(){
		var self = this;
		$('#${pageId}Content').layout({west__size:220,west__togglerLength_open:0,resizable:false});
		 $.hz.tabs.init("${pageId}tab",{
            onSelect:function(title,uid){
            	if(self.current_type!=uid){
            		com.ue.platform.module.role.index.refresh(uid);
            	}
            }
        });
	}
};

$(document).ready(function() {
	com.ue.platform.module.role.index.init();
});
</script>
		<div id="${pageId}Content" class="abs0">
			<div class="rightPanel abs t0 b0 r5 ovauto" id="${pageId}rightContent">
				
			</div>
			<div class="leftPanel abs t0 l0 b0">
				<div class="leftPanel">
					<div class="navTop">
						<c:if test="${not empty positionTypeList}">
							<a href="javascript:;" class="button" onclick="com.ue.platform.module.role.index.toSort();">排序</a>
							<a href="javascript:;" class="button" onclick="com.ue.platform.module.role.index.toAdd()">新建</a>
						</c:if>
						<c:if test="${empty positionTypeList}">
							请新建角色类型字典
						</c:if>
					</div>
					<div class="navMid noBot">
						<div class="abs0 t40" id="${pageId}tab">
							<c:forEach items="${positionTypeList}" var="positionType">
								<div id="${pageId}${positionType.code}" title="${positionType.value}" uid="${positionType.code}">
									<ul class="navMain" id="${pageId}roleListWith${positionType.code}">
							
									</ul>	
								</div>
							</c:forEach>
						</div>	
					</div>
				</div>
			</div> 
		</div>
		
