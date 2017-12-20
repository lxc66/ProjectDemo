define(["jquery"],function($) {
//bootstrap_table
var listUrl = "/houtai/consultation/list";
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
			var sub = $("#in").val();
			 var regular = /\s+/g;
			 var re =/[`~!@#$%^&*_+<>{}\/'[\]]/im;
			 
//			if(regular.test(sub)){
//				alert("请输入要搜索的内容");
//				$.loadingClose();
//			}else 
				if(re.test(sub)){
				alert("请输入正确的搜索的内容");
				$.loadingClose();
			}else{
				$("#listContent").load(listUrl+"?"+params,function(){
					$.loadingClose();
				});
			}
			
		},
		event:function(){
			
		
//	        $(".form-control").keydown(function(event) {
//	             if (event.keyCode == "13") {//keyCode=13是回车键
//	            	 alert(58);
//	                 $('#queryBtn').click();
//	             }
//	         })

	        
	       
	        var self = this;
	        $("#queryBtn").click(function(){
	        	
	        	self.query();
	        	
	        });
	        
	        
//	        $(".but").click(function(){
//	        	//具体功能代码略
//	        	})
//	        $(".form-control").keydown(function(event) {
//	             if (event.keyCode == "13") {//keyCode=13是回车键
//	            	 alert(555);
////	                 $('#queryBtn').click();
//	             }
//	         });
	        
	        
	        
		}
	
	
	
};
	return obj;
});


