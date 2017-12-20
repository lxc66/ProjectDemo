define(["./case-index.js"],function(indexPage) {
function toEdit(caseid){
	var url = "/houtai/case/input";
	var title = "资讯";
	if(caseid){
		url+="?caseid="+caseid;
		title+= "-编辑";
	}else{
		title+= "-添加";
	}
	$.loadPage({
  		 url:url
  	    ,title: title
  	    ,fix: true
  	    ,maxmin: false
  	    ,shadeClose: false
  	    ,resize:false
  	    ,area: ['800px', '80%']
  	});
}

function toDetails(caseid){
	var url = "/houtai/case/details";
	var title = "详情";

		url+="?caseid="+caseid;
		

	$.loadPage({
  		 url:url	
  	    ,title: title
  	    ,fix: true
  	    ,maxmin: false
  	    ,shadeClose: false
  	    ,resize:false
  	    ,area: ['800px', '60%']
  	});
}




//评论管理
function toDetailsPingLun(caseid){
//	alert(consultationid);
	var url = "/houtai/case/detailsPingLunCase";
	var title = "评论";

		url+="?caseid="+caseid;
		

	$.loadPage({
  		 url:url	
  	    ,title: title
  	    ,fix: true
  	    ,maxmin: false
  	    ,shadeClose: false
  	    ,resize:false
  	    ,area: ['450px', '60%']
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
        
        
        
        
        
        $("#loadImageBtn").click(function(){
        	var url = "/houtai/case/toUploadImage";
        	$.loadPage({
          		 url:url
          	    ,title: "批量上传图片"
          	    ,fix: true
          	    ,maxmin: false
          	    ,shadeClose: false
          	    ,resize:false
          	    ,area: ['400px', '30%']
          	});
        });
        
        
        $("#loadExcelBtn").click(function(){
        	var url = "/houtai/case/toUploadExcel";
        	$.loadPage({
          		 url:url
          	    ,title: "批量上传Excel"
          	    ,fix: true
          	    ,maxmin: false
          	    ,shadeClose: false
          	    ,resize:false
          	    ,area: ['400px', '30%']
          	});
        });
        
        
        
        
        
        
        
        $(".model-edit").click(function(){
//        	var id = $(this).attr("modelId");	
//        	toEdit(id);
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var id = $(this).val();
        			
        			toEdit(id);
        		})
        	}
        	
        	
        });
        
        $('.model-details').click(function () {
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var ids = $(this).val();
        			
        			toDetails(ids);
        		})
        	}
        	
        	
        });
        
        $('.PingLun-delete').click(function () {
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var ids1 = $(this).val();
        			
        			
        			
        			$.ajax({
        				type: "POST",
        				url: '/houtai/case/detailsPingLunCasetest?caseid='+ids1,
        				processData:true,
        				success: function(data){
        					if(data == 1){
        						alert("对不起，该案例暂时没有评论");
        					}else if(data == 2){
        						
        						toDetailsPingLun(ids1);
        					}
        					
        					
        				}
        			});
        		})
        	}
        	
        	
        });
      
        $(".model-del").click(function(){

        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var id = $(this).val();
        			
        			
        			
        			
        			confirm('确认删除？', function(){
        				$.ajax({
        					type: "POST",
        					url: '/houtai/case/delete?id='+id,
        					processData:true,
        					success: function(data){
        						indexPage.refresh();
        						layer.msg('删除成功', {icon: 1});
        					}
        				});
        			});
        			
        		})	
        	}
        	
        	
        });
        
        $(".model-fabu").click(function(){
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var aa = $(this).val();
        			
        			confirm('确认发布？', function(){
        				$.ajax({
        					type: "POST",
        					url: '/houtai/case/fabu?caseid='+aa+'&cancel=0',
        					processData:true,
        					success: function(data){
        						indexPage.refresh();
        						layer.msg('发布成功', {icon: 1});
        					}
        				});
        			});
        			
        			
        		})	
        	}
        	
        });
        
        
        
        $(".case_cancel").click(function(){
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var aa = $(this).val();
        			
        			
        			
        			
        			confirm('取消发布？', function(){
        				$.ajax({
        					type: "POST",
        					url: '/houtai/case/fabu?caseid='+aa+'&cancel=1',
        					processData:true,
        					success: function(data){
        						indexPage.refresh();
        						layer.msg('取消成功', {icon: 1});
        					}
        				});
        			});
        			
        		})
        	}
        	
        	
        	
        	
        });
        
        
        
        
        $("#toImage").click(function(){
//     		alert(22);
        	console.log("----"+$( "#toImage" )[0]);
    		var formData = new FormData($( "#theForm" )[0]); 
	   		 $.ajax({
	   		 	type: "POST",
	   		  	url: "/houtai/consultation/shangChuanListImage",
	   		  	async: false,  
	   	        cache: false,  
	   	        contentType: false,  
	   	        processData: false,  
	   		  	data:formData,
	   		  	success: function(){
//	   		  		indexPage.refresh();
//	   		  		$.layerClose(self.layerIndex);
//	   		  		$.msg("操作成功",6)
	   		  		alert("导入图片成功");
	   		  	}
	   		 });
	   		
    	});
        
        $("#toExcel").click(function(){
//     		alert(22);
    		var formData = new FormData($( "#toExcel" )[0]); 
	   		 $.ajax({
	   		 	type: "POST",
	   		  	url: "/houtai/consultation/SaveExcel",
	   		  	async: false,  
	   	        cache: false,  
	   	        contentType: false,  
	   	        processData: false,  
	   		  	data:formData,
	   		  	success: function(){
//	   		  		indexPage.refresh();
//	   		  		$.layerClose(self.layerIndex);
//	   		  		$.msg("操作成功",6)
	   		  		alert("导入图片成功");
	   		  	}
	   		 });
	   		
    	});
        
        
	}
};
	return obj;
});