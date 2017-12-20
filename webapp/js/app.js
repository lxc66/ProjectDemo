define(["jquery","bootstrap","commonUtil","validate"],function(jquery) {
var obj = {
	init:function(){
		this.bindBtnEvent();
		this.bindMenuEvent();
		this.loadMenuActive();
	},
	bindBtnEvent:function(){
		$("#changePasswordBtn").click(function() {
        	$.loadPage({
	       		 url:"/sys/user/toChangePassword"
	       	    ,title: '修改密码'
	       	    ,fix: true
	       	    ,maxmin: false
	       	    ,shadeClose: false
	       	    ,resize:false
	       	    ,skin: 'layui-layer-rim'
	       	    ,area: ['600px', '330px']
	       	});
		});
		$("#logoutBtn").click(function() {
			confirm('确认退出后台管理系统？', function(){
				window.location="/logout";
			});
		});
	},
	bindMenuEvent:function(){
		var self = this;
		$(".menu-item").click(function() {
			$(".menu-item").removeClass("menu-item-active");
			$(this).addClass("menu-item-active");
			var itmeObj = $(".menu-item").find("img");
			itmeObj.each(function() {
				var items = $(this).attr("src");
				items = items.replace("_grey.png", ".png");
				items = items.replace(".png", "_grey.png")
				$(this).attr("src", items);
			});
			var attrObj = $(this).find("img").attr("src");;
			attrObj = attrObj.replace("_grey.png", ".png");
			$(this).find("img").attr("src", attrObj);
			self.loadMenuActive();
		});
		
		$(".leftmenu-toggle-btn").click(function() {
			$("#leftmenu").toggleClass("show");
			$("#rightContent").toggleClass("pd200");
		})
	},
	loadMenuActive:function(){
		var url = $(".menu-item-active").attr("url");
		//console.log(url);
		if(!url)return;
		$.loading();
		$("#right-container").load(url,function(){
			$.loadingClose();
		});
	}
};

return obj;
});