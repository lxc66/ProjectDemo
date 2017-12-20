define(["./case-index.js"],function(indexPage) {
var obj = {
	init:function(){
		obj.event();
	},
	event:function(){
		var contionid = $("#id").val();
//		alert(contionid);
		$(".PingLun-del").click(function(){
        	var id = $(this).attr("modelId");
        	
			confirm('确认删除？', function(){
				$.ajax({
				 	type: "POST",
				  	url: '/houtai/case/deleteCasePingLun?id='+id,
				  	processData:true,
				  	success: function(data){
				  		indexPage.refresh();
				  		layer.msg('删除成功', {icon: 1 });
				  		
				  		var ll = "/houtai/case/detailsPingLunCase?caseid="+contionid;
				  		$("#sss").load(ll);
				  		
				  	}
				 });
			});
        });
		
	}
};
	return obj;
});