define(["./Expert-index.js"],function(indexPage) {
var obj = {
	init:function(){
		layerIndex:-1,
		this.layerIndex = $.layerIndex();
		obj.event();
	},
	event:function(){
		var self = this;
		$("#cancelBtn").click(function(){
			var id = $(this).attr("modelIdUs");
			
			$.ajax({
			 	type: "POST",
			  	url: '/houtai/consultation/successExportUser?id='+id,
			  	processData:true,
			  	success: function(data){
			  		
			  		layer.msg('审核完成', {icon: 1 });
			  		
			  	    $.layerClose(self.layerIndex);
					indexPage.refresh();
			  		
			  		
			  	}
			 });
			
			
			
			
		})
		

		$(".Expert-tongg").click(function(){
        	var id = $(this).attr("modelId");
        	var ids = $(this).attr("modelId1");
        	
			confirm('确认通过？', function(){
				$.ajax({
				 	type: "POST",
				  	url: '/houtai/consultation/successExport?id='+id+'&autograph='+ids,
				  	processData:true,
				  	success: function(data){
				  		
//				  		indexPage.refresh();
				  		layer.msg('审核成功', {icon: 1 });
//				  		alert(ids);
				  		if(ids == 'zhukuai'){
				  			$("#zhukuai").hide();
				  			$("#zhukuaiSuccess").show();
				  		}
				  		if(ids == 'pinggu'){
				  			$("#pinggu").hide();
				  			$("#pingguSuccess").show();
				  		}
				  		if(ids == 'shuiwu'){
				  			$("#shuiwu").hide();
				  			$("#shuiwuSuccess").show();
				  		}
				  		if(ids == 'caiwu'){
				  			$("#caiwu").hide();
				  			$("#caiwuSuccess").show();
				  		}
				  		if(ids == 'lvshi'){
				  			$("#lvshi").hide();
				  			$("#lvshiSuccess").show();
				  		}
				  		
//				  	$("#modelId").hide();
//				  	$("#modelId1").show();
				  		
				  		
				  	}
				 });
			});
        });
	}
};
	return obj;
});