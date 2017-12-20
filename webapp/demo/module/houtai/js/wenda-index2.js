define(["jquery"],function($) {
//bootstrap_table
var listUrl = "/houtai/wenda/list";
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
		
		var params=$("#queryForm").serialize();
		var sub = $("#in").val();
		var regular = /\s+/g;
		var re =/[`~!@#$%^&*_+<>{}\/'[\]]/im;
		if(re.test(sub)){
			alert("请输入正确的搜索的内容");
			$.loadingClose();
		}else{
			$("#listContent").load(listUrl+"?"+params,function(){
				$.loadingClose();
			});
		}
		
//		$.loading();
//		var params=$("#queryForm").serialize();
//		$("#listContent").load(listUrl+"?"+params,function(){
//			$.loadingClose();
//		});
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