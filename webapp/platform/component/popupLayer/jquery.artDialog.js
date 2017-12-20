$.extend({
	successTips:function(){//操作成功提示
		$.dialog({
			id: 'success',
			title: false,
			cancel: false,
			drag: false,
			resize: false,
			width: 231,
			height: 59,
			fixed: true,
			lock: false
		})
		.content('<div class="operation_success">操作成功！</div>')
		.time(1.5);
	}
});

var origAlert = window.alert;
//替换默认alert警告
window.alert = function(msg){
	$.dialog({
		content:"" + msg,
		fixed: true,
		padding:'10px',
		lock: true,
		width: 248,
		height: 100,
		drag: false,
		resize: false,
		icon: 'warning',
		ok:function(){}
	}).show().focus();
};

//替换默认的confirm
window.confirm = function(msg,fun){
	var defaults = {
		msg :"",
		title : "提示",
		ok:function(){},
		okVal: "确定",
		cancel : function(){},
		cancelVal: "取消"
	};
	if(arguments.length == 1){//传入配置
		var config = arguments[0];
		for (var i in defaults) {
			if (config[i] !== undefined)  defaults[i] = config[i];		
		};
	}
	if(arguments.length == 2){//使用简单方法调用
		defaults.msg = arguments[0];
		defaults.ok = arguments[1];
	}
	$.dialog({
		title:defaults.title,
		content:"" + defaults.msg,
		ok:defaults.ok,
		cancel:defaults.cancel,
		okVal:defaults.okVal,
		cancelVal:defaults.cancelVal,
		padding: '20px 20px',
		width: 200,
		height: 50,
		drag: false,
		resize: false,
		lock: true,
		icon: 'question'
	}).show();
	
};