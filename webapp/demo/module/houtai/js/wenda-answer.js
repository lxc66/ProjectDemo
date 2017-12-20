define(["./wenda-answer.js"],function(indexPage) {
	
   
    
    
	var obj = {
		layerIndex:-1,
		init:function(){
			this.layerIndex = $.layerIndex();
			obj.event();
			//obj.formValidate();
		},
		event:function(){
			var self = this;
	        $("#submitBetn").click(function(){
	    		if(!$("#theForme").valid()){
	    			return;
	    		}
	    		var formData = new FormData($( "#theForme" )[0]); 
	    		 $.ajax({
	    		 	type: "POST",
	    		  	url: "/houtai/wenda/updateAnswer",
	    		  	async: false,  
	    	        cache: false,  
	    	        contentType: false,  
	    	        processData: false,  
	    		  	data:formData,
	    		  	success: function(){
	    		  		
	    		  		$("#theForm").load("/houtai/wenda/answer");
	    		  		alert(1);
	    		  		$.layerClose(self.layerIndex);
	    		  		$.msg("操作成功",6)
	    		  	}
	    		 });
	        });
	        
	        $("#cancelBetn").click(function(){
	        	$.layerClose(self.layerIndex);
	        });
		},
	};
	return obj;
});