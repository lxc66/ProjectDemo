define(["./dmText-index.js"],function(indexPage) {
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
	    		var formData = new FormData($( "#theForm" )[0]); 
	    		 $.ajax({
		    		 	type: "POST",
		    		  	url: "/demo2/dmText/save",
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
		            "name": {
	                    required: true
						,maxlength: 200
	                }
	            	,"email": {
	                    required: true
						,maxlength: 200
	                }
	            	,"comments": {
						maxlength: 500
	                }
	            	,"remarks": {
						maxlength: 255
	                }
	            	,"num": {
						digits: true
	                }
	            },
	            messages: {
		            "name": {
	                    required: icon + "请输入名称"
						,maxlength: icon + "名称字数限制为200"
	                }
	            	,"email": {
	                    required: icon + "请输入E-mail"
						,maxlength: icon + "E-mail字数限制为200"
	                }
	            	,"comments": {
						maxlength: icon + "描述字数限制为500"
	                }
	            	,"remarks": {
						maxlength: icon + "备注信息字数限制为255"
	                }
	            	,"num": {
						digits: icon + "请输入数字"
	                }
	            }
	        });
		}
	};
	return obj;
});