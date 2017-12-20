define(["jquery","validate"],function($) {
        //return a function to define "foo/title".
        //It gets or sets the window title.
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
	    		var userId = $("#userId").val();
	    		var password = $("#password").val();
	    	    var formData={
	    	    	"model.id":userId,
	        		"model.password":password
	    	    }
	    		 $.ajax({
	    		 	type: "POST",
	    		  	url: "/sys/user/changePassword",
	    		  	processData:true,
	    		  	data:formData,
	    		  	success: function(){
	    		  		$.layerClose(self.layerIndex);
	    		  		$.msg("密码修改成功",6)
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
	                original_password: {
	                    required: true,
	                    minlength: 5,
	                    remote:{
							type:"POST",
							url:"/sys/user/checkOriginalPassword",
							data:{
								userId:function(){return $("#userId").val();},
								password:function(){return $("#original_password").val();}
			                } 
						}
	                },
	                password: {
	                    required: true,
	                    minlength: 5
	                },
	                confirm_password: {
	                    required: true,
	                    minlength: 5,
	                    equalTo: "#password"
	                }
	            },
	            messages: {
	            	original_password: {
	                    required: icon + "请输入您的原始密码",
	                    minlength: icon + "原始密码必须5个字符以上",
	                    remote: icon + "原始密码错误",
	                },
	                password: {
	                    required: icon + "请输入您的密码",
	                    minlength: icon + "密码必须5个字符以上"
	                },
	                confirm_password: {
	                    required: icon + "请再次输入密码",
	                    minlength: icon + "密码必须5个字符以上",
	                    equalTo: icon + "两次输入的密码不一致"
	                }
	            }
	        });
		}
	};
	return obj;
});