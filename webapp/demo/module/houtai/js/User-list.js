define(["./User-index.js"],function(indexPage) {


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
  	    ,area: ['800px', '100%']
  	});
}







var obj = {
	init:function(){
		obj.event();
	},
	event:function(){
        $("#addBtn").click(function(){
        	toEdit();
        });
            
        
        $('.Expert-details').click(function () {
        	var userId = $(this).attr("modelId");
        	toDetails(userId);
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