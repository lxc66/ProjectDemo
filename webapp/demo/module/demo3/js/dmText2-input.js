define(["./dmText2-index.js"],function(indexPage) {
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
	    		var formData=$("#theForm").serialize();
	    		 $.ajax({
	    		 	type: "POST",
	    		  	url: "/demo3/dmText2/save",
	    		  	processData:true,
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
	            	,"type": {
	                    required: true
						,maxlength: 200
	                }
	            	,"email": {
						maxlength: 200
	                }
	            	,"comments": {
						maxlength: 500
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
	            	,"type": {
	                    required: icon + "请输入分类"
						,maxlength: icon + "分类字数限制为200"
	                }
	            	,"email": {
						maxlength: icon + "E-mail字数限制为200"
	                }
	            	,"comments": {
						maxlength: icon + "描述字数限制为500"
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