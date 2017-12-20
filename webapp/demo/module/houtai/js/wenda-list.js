define(["./wenda-index2.js"],function(indexPage) {
function toEdit(problemId){
	var url = "/houtai/wenda/input";
	var title = "问答";
	if(problemId){
		url+="?problemId="+problemId;
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
 	    ,area: ['800px', '60%']
 	});
}

//function toDetails(caseid){
//	var url = "/houtai/wenda/details";	
//	var title = "详情";
//
//		url+="?caseid="+caseid;
//		
//
//	$.loadPage({
//  		 url:url	
//  	    ,title: title
//  	    ,fix: true
//  	    ,maxmin: false
//  	    ,shadeClose: false
//  	    ,resize:false
//  	    ,area: ['800px', '100%']
//  	});
//}


var obj = {
	init:function(){
		obj.event();
	},
	event:function(){
        $("#addBtn").click(function(){
        	toEdit();
        });
        
        
        
        
        
        $("#loadImageBtn").click(function(){
        	var url = "/houtai/wenda/toUploadImage";
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
        
        //多问题
        $("#loadExcelBtn").click(function(){
        	var url = "/houtai/wenda/toUploadExcel";
        	$.loadPage({
          		 url:url
          	    ,title: "批量上传Excel(多问题)"
          	    ,fix: true
          	    ,maxmin: false
          	    ,shadeClose: false
          	    ,resize:false
          	    ,area: ['400px', '30%']
          	});
        });
        
        //单问题
        $("#loadExcelBtn1").click(function(){
        	var url = "/houtai/wenda/toUploadExcel1";
        	$.loadPage({
          		 url:url
          	    ,title: "批量上传Excel(单问题)"
          	    ,fix: true
          	    ,maxmin: false
          	    ,shadeClose: false
          	    ,resize:false
          	    ,area: ['400px', '30%']
          	});
        });
        
        
        
        
        
        
        
        $(".model-edit").click(function(){
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var problemId = $(this).val();
        			toEdit(problemId);
        		})
        	}
        	
        	
        	
        });
        
//        $('.model-details').click(function () {
//        	var ids = $(this).attr("modelId");
//        	toDetails(ids);
//        });
        
        
      
//        $(".model-del").click(function(){
//        	var id = $(this).attr("modelId");
//        	
//			confirm('确认删除？', function(){
//				$.ajax({
//				 	type: "POST",
//				  	url: '/houtai/case/delete?caseid='+id,
//				  	processData:true,
//				  	success: function(data){
//				  		indexPage.refresh();
//				  		layer.msg('删除成功', {icon: 1});
//				  	}
//				 });
//			});
//        });
        
//        $(".model-fabu").click(function(){
//        	var aa = $(this).attr("modelId");
//        	confirm('确认发布？', function(){
//				$.ajax({
//				 	type: "POST",
//				  	url: '/houtai/case/fabu?caseid='+aa+'&cancel=0',
//				  	processData:true,
//				  	success: function(data){
//				  		indexPage.refresh();
//				  		layer.msg('发布成功', {icon: 1});
//				  	}
//				 });
//			});
//        });
        
        
        
//        $(".case_cancel").click(function(){
//        	var aa = $(this).attr("modelId");
//        	confirm('取消发布？', function(){
//				$.ajax({
//				 	type: "POST",
//				  	url: '/houtai/case/fabu?caseid='+aa+'&cancel=1',
//				  	processData:true,
//				  	success: function(data){
//				  		indexPage.refresh();
//				  		layer.msg('取消成功', {icon: 1});
//				  	}
//				 });
//			});
//        });
        
        
        
        
//        $("#toImage").click(function(){
////     		alert(22);
//        	console.log("----"+$( "#toImage" )[0]);
//    		var formData = new FormData($( "#theForm" )[0]); 
//	   		 $.ajax({
//	   		 	type: "POST",
//	   		  	url: "/houtai/consultation/shangChuanListImage",
//	   		  	async: false,  
//	   	        cache: false,  
//	   	        contentType: false,  
//	   	        processData: false,  
//	   		  	data:formData,
//	   		  	success: function(){
////	   		  		indexPage.refresh();
////	   		  		$.layerClose(self.layerIndex);
////	   		  		$.msg("操作成功",6)
//	   		  		alert("导入图片成功");
//	   		  	}
//	   		 });
	   		
//    	});
        
//        $("#toExcel").click(function(){
////     		alert(22);
//    		var formData = new FormData($( "#toExcel" )[0]); 
//	   		 $.ajax({
//	   		 	type: "POST",
//	   		  	url: "/houtai/consultation/SaveExcel",
//	   		  	async: false,  
//	   	        cache: false,  
//	   	        contentType: false,  
//	   	        processData: false,  
//	   		  	data:formData,
//	   		  	success: function(){
////	   		  		indexPage.refresh();
////	   		  		$.layerClose(self.layerIndex);
////	   		  		$.msg("操作成功",6)
//	   		  		alert("导入图片成功");
//	   		  	}
//	   		 });
//	   		
//    	});
        
        
	}
}; 
	return obj;
});