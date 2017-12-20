define(["./Expert-index.js"],function(indexPage) {


function toDetails(userId){
	var url = "/houtai/consultation/ExpertDetails";
	var title = "详情";

		url+="?userId="+userId;
		

	$.loadPage({
  		 url:url	
  	    ,title: title
  	    ,fix: true
  	    ,maxmin: false
  	    ,shadeClose: false
  	    ,resize:false
  	    ,area: ['1000px', '80%']
  	});
}






$("#ExportImageBtn").click(function(){
	var url = "/houtai/consultation/toUploadImageExport";
	$.loadPage({
  		 url:url
  	    ,title: "批量上传图片"
  	    ,fix: true
  	    ,maxmin: false
  	    ,shadeClose: false
  	    ,resize:false
  	    ,area: ['800px', '100%']
  	});
});







var obj = {
	init:function(){
		obj.event();
	},
	event:function(){
        $("#addBtn").click(function(){
        	toEdit();
        });
            
        
        $('.Expert-details').click(function () {
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var userId = $(this).val();
        			
        			toDetails(userId);
        		})
        	}
        	
        	
        	
        });
        
        
        
        
        
        
      
        $(".Expert-del").click(function(){
        	var id = $(this).attr("modelId");
        	
			confirm('确认删除？', function(){
				$.ajax({
				 	type: "POST",
				  	url: '/houtai/consultation/deleteEcpert?id='+id,
				  	processData:true,
				  	success: function(data){
				  		indexPage.refresh();
				  		layer.msg('删除成功', {icon: 1 });
				  	}
				 });
			});
        });
        
        
        
        
        
        
      
        
       
        
        
	}
};
	return obj;
});