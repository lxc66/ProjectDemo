define(["jquery"],function($) {
//bootstrap_table
var listUrl = "/demo2/dmText/list";
var obj = {
	init:function(){
		this.event();
		this.refresh();
	},
	refresh:function(){
		var params=$("#queryForm").serialize();
		$("#listContent").load(listUrl+"?"+params)
	},
	query:function(){
		$.loading();
		var params=$("#queryForm").serialize();
		$("#listContent").load(listUrl+"?"+params,function(){
			$.loadingClose();
		});
	},
	event:function(){
		var self = this;
        $("#queryBtn").click(function(){
        	self.query();
        });
	}
};
	return obj;
});