<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*,com.jzsoft.platform.module.dictionary.model.Dictionary" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<script type="text/javascript">
Namespace.register('com.ue.platform.module.dict');
com.ue.platform.module.dict={
	pageId:"${pageId}",
	type_general:"<%=Dictionary.DICT_KIND_GENERAL%>",
	type_tree:"<%=Dictionary.DICT_KIND_TREE%>",
	selectedTab:function(title){
		var selectedLi = $('li[container="${pageId}tab"].tabs-selected');
		if (selectedLi && selectedLi.length == 1){
			var tabAttr = $.data(selectedLi[0], 'tabs.tab');
			if($(tabAttr).attr("title")!=title){
				$('#${pageId}tab').tabs('select',title);
			}
		}
	},
	refresh:function(type,id){
		var space=this;
		if(type==this.type_general){
			this.selectedTab("普通字典");
			this.loadGeneralDictionaryList(id)
		}
		if(type==this.type_tree){
			this.selectedTab("树型字典");
			this.loadTreeDictionaryList(id)
		}
	},
	loadGeneralDictionaryList:function(id){
		var space=this;
		$("#${pageId}rightContent").html('');
		$.getJSON("${path}/sys/dictionary/listDataWithGeneral", function(jsondata){
			$("#${pageId}dictListWithGeneral").html('');
			space.buildDictList(jsondata,"${pageId}dictListWithGeneral");
			if(id){space.toInfo(id);}
		});
	},
	loadTreeDictionaryList:function(id){
		var space=this;
		$("#${pageId}rightContent").html('');
		$.getJSON("${path}/sys/dictionary/listDataWithTree", function(jsondata){
			$("#${pageId}dictListWithTree").html('');
			space.buildDictList(jsondata,"${pageId}dictListWithTree");
			if(id){space.toInfo(id);}
		});
	},
	buildDictList:function(jsondata,positionListId){
		$.each(jsondata,function(){
				var potision_li=[];
				potision_li.push("<li>");
				potision_li.push("<a onclick='com.ue.platform.module.dict.toInfo(\""+this.id+"\")' href='javascript:void(0);' class='nav_icon' id='${pageId}listNode_"+this.id+"'>");
				potision_li.push(this.name);
				potision_li.push("</a>");
				potision_li.push("</li>");
				$("#"+positionListId).append(potision_li.join(''));
		});
	},
	toInfo:function(id){
		<%--设置职务列表节点选中--%>
		$(".nav_icon").removeClass("current");
		$("#${pageId}listNode_"+id).addClass("current");
		<%--加载职务信息--%>
		$("body").mask('正在加载数据……');
		$("#${pageId}rightContent").load("${path}/sys/dictionary/info.action?id="+id,function(){
			$("body").unmask();
		});
	},
	toAdd:function(){
		$("body").mask('正在加载数据……');
		$('.current').removeClass("current");
		$("#${pageId}rightContent").load("${path}/sys/dictionary/input.action",function(){
			$("body").unmask();
		});
	},
	toEdit:function(id){
		$("body").mask('正在加载数据……');
		$("#${pageId}rightContent").load("${path}/sys/dictionary/input.action?id="+id, 
			function(){
				$("body").unmask();
			}
		);
	},
	doDel:function(dictType,id){
		var space=this;
		confirm("确定要删除吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/dictionary/delete?id='+id,
			  	processData:true,
			  	success: function(data){
			   		space.refresh(dictType);
			  	}
			 });
		});
	}
};

$(document).ready(function() {
	$('#${pageId}Content').layout({west__size:220,west__togglerLength_open:0,resizable:false});

	
	$('#${pageId}tab').tabs({
		onSelect: function(){
			var id = $(this).attr("id");
			var type = $(this).attr("type");
			com.ue.platform.module.dict.refresh(type);
		}
	});
	
});
</script>
		<div id="${pageId}Content" class="abs0">
			<div class="rightPanel abs t0 b0 r5" id="${pageId}rightContent">
				
			</div>
				<div class="leftPanel abs t0 l0 b0">
					<div class="navTop">
						<a href="#" class="button" onclick="com.ue.platform.module.dict.toAdd()">新建字典</a>
					</div>
					<div class="navMid noBot">
						<div class="tabs-container t40" id="${pageId}tab">
							<div id="${pageId}generalDict" title="普通字典" type="<%=Dictionary.DICT_KIND_GENERAL%>">
								<ul class="navMain" id="${pageId}dictListWithGeneral">
						
								</ul>	
							</div>
							<div id="${pageId}treeDict" title="树型字典" type="<%=Dictionary.DICT_KIND_TREE%>">
								<ul class="navMain" id="${pageId}dictListWithTree">
						
								</ul>	
							</div>
						</div>	
					</div>
				</div>
			</div> 
