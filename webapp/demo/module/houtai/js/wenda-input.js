define(["./wenda-index2.js"],function(indexPage) {
	
    $(".model-add").click(function(){
    	var id = $(this).attr("modelId");	
    	toEdit(id);
    });
    
   
    
   
	var obj = {
		layerIndex:-1,
		init:function(){
			this.layerIndex = $.layerIndex();
			obj.event();
			//obj.formValidate();
		},
		event:function(){
			
			
			var self = this;
			 $(".model-fabu").click(function(){
			    	var bb=$(this).attr("problemId");
			    	var url1='/houtai/wenda/input?problemId='+bb
//			    	alert(bb);
			    	var aa = $(this).attr("modelId");
			    	confirm('确认隐藏？', function(){
						$.ajax({
						 	type: "POST",
						  	url: '/houtai/wenda/answer?id='+aa+'&cancel=2',
						  	processData:true,
						  	success: function(data){
						  		
//						  		alert(data);
						  		if(data == 2){
						  			
						  			$(".sb").html("隐藏");
						  		    $(".fabu").show();
						  		    $(".yingcang").hide();
						  		    $(".model-fabu").hide();
						  		    
						  		}
						  		 
						  		layer.msg('隐藏成功', {icon: 1});
						  	}
						 });
					});
			    });
			    
			    
			    
			    $(".case_cancel").click(function(){
			    	var aa = $(this).attr("modelId");
			    	var bb=$(this).attr("problemId");
			    	var url1='/houtai/wenda/input?problemId='+bb
			    	confirm('确认发布？', function(){
						$.ajax({
						 	type: "POST",
						  	url: '/houtai/wenda/answer?id='+aa+'&cancel=1',
						  	processData:true,
						  	success: function(data){
						  		
						  		if(data == 1){
						  			
						  			$(".sb").html("可见");
						  			$(".yingcang").show();
						  			$(".fabu").hide();
						  			$(".case_cancel").hide();
						  			
						  		}
						  		layer.msg('发布成功', {icon: 1});
						  		
						  		
						  	}
						 });
					});
			    });
			    
			    
			    
			    
			    
			    
			    $(".yingcang").click(function(){
			    	var bb=$(this).attr("problemId");
			    	var url1='/houtai/wenda/input?problemId='+bb
//			    	alert(bb);
			    	var aa = $(this).attr("modelId");
			    	confirm('确认隐藏？', function(){
						$.ajax({
						 	type: "POST",
						  	url: '/houtai/wenda/answer?id='+aa+'&cancel=2',
						  	processData:true,
						  	success: function(data){
						  		
//						  		alert(data);
						  		if(data == 2){
						  			
						  			$(".sb").html("隐藏");
						  		    $(".fabu").show();
						  		    $(".yingcang").hide();
						  		    $(".model-fabu").hide();
						  		    
						  		}
						  		 
						  		layer.msg('隐藏成功', {icon: 1});
						  	}
						 });
					});
			    });
			    
			    
			    
			    $(".fabu").click(function(){
			    	var aa = $(this).attr("modelId");
			    	var bb=$(this).attr("problemId");
			    	var url1='/houtai/wenda/input?problemId='+bb
			    	confirm('确认发布？', function(){
						$.ajax({
						 	type: "POST",
						  	url: '/houtai/wenda/answer?id='+aa+'&cancel=1',
						  	processData:true,
						  	success: function(data){
						  		
						  		if(data == 1){
						  			
						  			$(".sb").html("可见");
						  			$(".yingcang").show();
						  			$(".fabu").hide();
						  			$(".case_cancel").hide();
						  			
						  		}
						  		layer.msg('发布成功', {icon: 1});
						  		
						  		
						  	}
						 });
					});
			    });
			    
			    
			    
			    
			
	        $("#submitBtn").click(function(){
	    		if(!$("#theForm").valid()){
	    			return;
	    		}
//	    		var formData=$("#theForm").serialize();
	    		var formData = new FormData($( "#theForm" )[0]); 
	    		 $.ajax({
	    		 	type: "POST",
	    		  	url: "/houtai/wenda/update",
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
		}
//		formValidate:function(){
//	        var icon = "<i class='fa fa-times-circle'></i> ";
//	        $("#theForm").validate({
//	            rules: {
//		            "Typecase": {
//		            	
//		            	required: true
//	                }
//	            	,"Tcase": {
//	                    required: true
//						
//	                }
//	            	,"ynMoney":{
//	            	    required: true
//	            	}
////	            	,"Csource": {
////	            		required: true
////	                }
////	            	,"Description": {
////	            		required: true
////	                }
////	            	,"Otime": {
////	            		required: true
////	                }
//	            },
//	            messages: {
//		            "Typecase": {
//	                    required: icon + "请选择分类"
//	                }
//	            	,"Tcase": {
//	                    required: icon + "请输入标题"
//						
//	                }
//	            	,"ynMoney":{
//	            		required: icon + "请选择是否收费"
//	            	}
////	            	,"Csource": {
////						maxlength: icon + "请输入来源"
////	                }
////	            	,"Description": {
////						maxlength: icon + "请输入具体内容"
////	                }
////	            	,"Otime": {
////						digits: icon + "请输入结贴时间"
////	                }
//	            }
//	        });
//		}
	};
	return obj;
});