define(["./dmText-index.js"],function(indexPage) {
function toEdit(id){
	var url = "/demo2/dmText/input";
	var title = "示例功能";
	if(id){
		url+="?id="+id;
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
        
        $(".model-edit").click(function(){
        	var id = $(this).attr("modelId");
        	toEdit(id);
        });
        
        $(".model-del").click(function(){
        	var id = $(this).attr("modelId");
			confirm('确认删除？', function(){
				$.ajax({
				 	type: "POST",
				  	url: '/demo2/dmText/delete?id='+id,
				  	processData:true,
				  	success: function(data){
				  		indexPage.refresh();
				  		layer.msg('删除成功', {icon: 1});
				  	}
				 });
			});
        });
	}
};
	return obj;
});