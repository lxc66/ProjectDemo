define([ "./wenda-index2.js" ], function(indexPage) {

	var obj = {
		layerIndex : -1,
		init : function() {
			this.layerIndex = $.layerIndex();
			obj.event();
			obj.formValidate();
		},
		event : function() {
			var self = this;
			$("#submitBtnExcel").click(function() {
//				var formData = new FormData($("#toForm")[0]);
//				alert(formData.length);
				var formData = new FormData($("#toForm")[0]);
//				if(formData.length != null && formData != "" && formData.length != 0){
//					
//					if (!$("#toForm").valid()) {
//						return;
//					}
//					// var formData=$("#theForm").serialize();
////				var formData = new FormData($("#toForm")[0]);
//					$.ajax({
//						type : "POST",
//						url : "/houtai/wenda/SaveExcel",
//						async : false,
//						cache : false,
//						contentType : false,
//						processData : false,
//						data : formData,
//						success : function() {
//							indexPage.refresh();
//							$.layerClose(self.layerIndex);
//							$.msg("操作成功", 6)
//						}
//					});
//				}
//				if(formData1.length != null && formData1 != "" && formData1.length != 0){
					/**
					 * 多问题Excel
					 */
					//	    		 
					if (!$("#toForm").valid()) {
						return;
					}
					// var formData=$("#theForm").serialize();
					
					$.ajax({
						type : "POST",
						url : "/houtai/wenda/SaveExcel",
						async : false,
						cache : false,
						contentType : false,
						processData : false,
						data : formData,
						success : function() {
							indexPage.refresh();
							$.layerClose(self.layerIndex);
							$.msg("操作成功", 6)
						}
					});
					//	    		 

//				}

				
			});

			$("#cancelBtn").click(function() {
				$.layerClose(self.layerIndex);
			});
		},
		formValidate : function() {
			var icon = "<i class='fa fa-times-circle'></i> ";
			$("#toForm").validate({
			 rules: {
			 "upExcel": {
					            	
			 required: true
			 }
			 },
			 messages: {
			 "upExcel": {
			 required: icon + "请选择Excel"
			 }
			 }
			});
		}
	};
	return obj;
});