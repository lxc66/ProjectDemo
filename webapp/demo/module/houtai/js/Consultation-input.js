define(["./Consultation-index.js"],function(indexPage) {
	
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
	    		  	url: "/houtai/consultation/save",
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
		            "subtype": {
		            	
		            		required: true
	                }
	            	,"titleLabel": {
	                    required: true
						
	                }
	            	,"Csource": {
	            		required: true
	                }
	            	,"contentLabel": {
	            		required: true
	                }
	            	,"Otime": {
	            		required: true
	                }
	            },
	            messages: {
		            "subtype": {
	                    required: icon + "请选择分类"
	                }
	            	,"titleLabel": {
	                    required: icon + "请输入标题"
						
	                }
	            	,"Csource": {
						maxlength: icon + "请输入来源"
	                }
	            	,"contentLabel": {
						maxlength: icon + "请输入具体内容"
	                }
	            	,"Otime": {
						digits: icon + "请输入结贴时间"
	                }
	            }
	        });
		}
	};
	return obj;
});