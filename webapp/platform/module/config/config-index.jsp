<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
$.hz.component.include("jqueryui,tree,validate,layout,popupLayer,mask,tabs",false);
Namespace.register('com.ue.platform.module.config.index');
com.ue.platform.module.config.index = {
	pageId : "${pageId}",
	contentDiv : null,
	loadContent : function(obj) {
		$(obj).closest("ul").find(".nav_icon").removeClass("current");
		$(obj).addClass("current");
		$("body").mask('加载内容……');
		$("#${pageId}rightContent").load($(obj).attr("url"),function(){
			$("body").unmask();
		});
	},
	bindEvent:function(){
		var self = this;
		$("#${pageId}content .navMain a").bind("click",function(){
			self.loadContent(this);
		});
	},
	loadDefault:function(){
		var item = $("#${pageId}content .navMain a").eq(0);
		if(item){
			item.click();
		}
	},
	init:function(){
		this.bindEvent();
		this.loadDefault();
	}
};
$().ready(function() {
	$('#${pageId}content').layout({west__size:180,west__togglerLength_open:0,resizable:false});
	com.ue.platform.module.config.index.init();
// 	com.ue.platform.module.config.index.loadUrl('${path}/bd/configuration!listOfConfiguration.action');
// 	com.ue.platform.module.config.index.contentDiv = $("#${pageId}rightContent");
});
</script>
<div id="${pageId}content" style="position: absolute; top: 0;left: 0;right: 0;bottom: 0;">
	<div class="ui-layout-center" id="${pageId}rightContent" style="overflow:auto"></div>
	<div class="ui-layout-west inLayout-west">
		<div class="leftPanel">
			<div class="navMid noTop noBot">
				<ul class="navMain">
						<li class="first">
							<a href="javascript:;" class="nav_icon current" url="${path}/sys/config/list"><em></em>配置管理</a>
						</li>
						<li>
							<a href="javascript:;" class="nav_icon current" url="${path}/sys/configresult/toGlobalConfigResult"><em></em>全局配置</a>
						</li>
						<li>
							<a href="javascript:;" class="nav_icon current" url="${path}/sys/configresult/toModuleConfigResult"><em></em>模块配置</a>
						</li>
						<c:if test="${isLogin}">
						<li>
							<a href="javascript:;" class="nav_icon current" url="${path}/sys/configresult/toUserConfigResult"><em></em>用户配置</a>
						</li>
						</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
