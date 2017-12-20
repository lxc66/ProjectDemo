define(["./wenda-index2.js"],function(indexPage) {
	
	var obj = {
		layerIndex:-1,
		init:function(){
			this.layerIndex = $.layerIndex();
			obj.event();
			obj.formValidate();
		},
		event:function(){
			var self = this;
	        $("#submitBtn").click(function(){
	    		if(!$("#theForm").valid()){
	    			return;
	    		}
//	    		var formData=$("#theForm").serialize();
	    		var formData = new FormData($( "#theForm" )[0]); 
	    		 $.ajax({
	    		 	type: "POST",
	    		  	url: "/houtai/wenda/shangChuanListImage",
	    		  	async: false,  
	    	        cache: false,  
	    	        contentType: false,  
	    	        processData: false,  
	    		  	data:formData,
	    		  	success: function(){
	    		  		indexPage.refresh();
	    		  		$.layerClose(self.layerIndex);
	    		  		$.msg("操作成功",6)
	    		  	}
	    		 });
	        });
	        
	        $("#cancelBtn").click(function(){
	        	$.layerClose(self.layerIndex);
	        });
		},
		formValidate:function(){
	        var icon = "<i class='fa fa-times-circle'></i> ";
	        $("#theForm").validate({
	            rules: {
		            "upFiles": {
		            	
		            		required: true
	                }
	            },
	            messages: {
		            "upFiles": {
	                    required: icon + "请选择图片"
	                }
	            }
	        });
		}
	};
	return obj;
});