define(["./Consultation-index.js"],function(indexPage) {
function toEdit(consultationid){
	var url = "/houtai/consultation/input";
	var title = "资讯";
	if(consultationid){
		url+="?consultationid="+consultationid;
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

function toDetails(consultationid){
	var url = "/houtai/consultation/details";
	var title = "详情";

		url+="?consultationid="+consultationid;
		

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
function toDetailsPingLun(consultationid){
//	alert(consultationid);
	var url = "/houtai/consultation/detailsPingLun";
	var title = "评论";

		url+="?consultationid="+consultationid;
		

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
        	var url = "/houtai/consultation/toUploadImage";
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
        	var url = "/houtai/consultation/toUploadxcel";
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

        	
        	var radio_ary = document.getElementsByName("chk_uid");
        	
//        	  for (var i = 0; i < radio_ary.length; i++) {
//                  if (radio_ary[i].checked == true) { //得到选中的<a href="https://www.baidu.com/s?wd=%E5%8D%95%E9%80%89%E6%8C%89%E9%92%AE&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1dhPHw-PvDLnW7-PvDkPjc0IAYqnWm3PW64rj0d0AP8IA3qPjfsn1bkrjKxmLKz0ZNzUjdCIZwsrBtEXh9GuA7EQhF9pywdQhPEUiqkIyN1IA-EUBtkn1m3nHRLnjnkrHcknjT4rHn4" target="_blank" class="baidu-highlight">单选按钮</a>状态（判断是否被选中）
//                      alert(radio_ary[i].value + " 被选中"); //弹出选中<a href="https://www.baidu.com/s?wd=%E5%8D%95%E9%80%89%E6%8C%89%E9%92%AE&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1dhPHw-PvDLnW7-PvDkPjc0IAYqnWm3PW64rj0d0AP8IA3qPjfsn1bkrjKxmLKz0ZNzUjdCIZwsrBtEXh9GuA7EQhF9pywdQhPEUiqkIyN1IA-EUBtkn1m3nHRLnjnkrHcknjT4rHn4" target="_blank" class="baidu-highlight">单选按钮</a>的值
//                  }else{
//                	  alert("请选择编号");
//                  }
//              }
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
        
        $('.consultation-pinglun').click(function () {
//        	alert();
//        	var ids1 = $(this).attr("modelId");
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var ids1 = $(this).val();
        			
        			
        			
        			$.ajax({
        				type: "POST",
        				url: '/houtai/consultation/detailsPingLun?consultationid='+ids1,
        				processData:true,
        				success: function(data){
        					
        					
        					$.ajax({
        						type: "POST",
        						url: '/houtai/consultation/detailsPingLuntest?consultationid='+ids1,
        						processData:true,
        						success: function(data){
        							if(data == 1){
        								alert("对不起，该资讯暂时没有评论");
        							}else if(data == 2){
        								
        								toDetailsPingLun(ids1);
        							}
        							
        							
        						}
        					});
        					
        				}
        			});
        			
        		})	
        	}
        	

        });
        
        
      
        $(".model-del").click(function(){
        	
        	var rideo = $("input[name='chk_uid']:checked").val(); 
        	
        	if(rideo == "" || rideo == null){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var id = $(this).val();
        			
        			
        			confirm('确认删除？', function(){
        				$.ajax({
        					type: "POST",
        					url: '/houtai/consultation/delete?consultationid='+id,
        					processData:true,
        					success: function(data){
        						indexPage.refresh();
        						layer.msg('删除成功', {icon: 1 });
        					}
        				});
        			});
        			
        		})
        	}
        	
        });
        
        $(".model-fabu").click(function(){
//        	var aa = $(this).attr("modelId");
//        	var aa = $(".number").val();
        	
        	var rideo = $("input[name='chk_uid']:checked").val();
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var aa = $(this).val();
        			
        			confirm('确认发布？', function(){
        				$.ajax({
        					type: "POST",
        					url: '/houtai/consultation/fabu?consultationid='+aa+'&cancel=0',
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
        
        
        $(".consultation_cancel").click(function(){
//        	var aa = $(this).attr("modelId");
//        	var aa = $(".number").val();
        	
        	var rideo = $("input[name='chk_uid']:checked").val(); 
        	
        	if(rideo == null || rideo == ""){
        		alert("请选择编号");
        	}else{
        		
        		$("input[name='chk_uid']:checked").each(function() { 
        			var aa = $(this).val();
        			
        			
        			
        			confirm('确认取消？', function(){
        				$.ajax({
        					type: "POST",
        					url: '/houtai/consultation/fabu?consultationid='+aa+'&cancel=1',
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
        
        
        
        
        
        
        
        
        $("#submit").click(function(){
//     		alert(22);
        	console.log("----"+$( "#theForm" )[0]);
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
        
        $("#submitExcel").click(function(){
//     		alert(22);
    		var formData = new FormData($( "#toForm" )[0]); 
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