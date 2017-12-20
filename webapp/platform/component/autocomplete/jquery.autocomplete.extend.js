(function($){
	$.hz.autocomplate = {
		init:function(domId,param){
			var defaultOptions = {
				//自定义属性
				data:[],
				url:"",
				urlParams:"",
				inputType:"text",
				inputId:"input"+domId,
				inputName:"",
				inputValue:"",
				hiddenId:"hidden"+domId,
				hiddenName:"",
				hiddenValue:"",
				inputClass:"",
				inputStyle:"",
				rows:"3",
				cols:"50",
				placeholder:"",
				//组件原生属性
				minChars: 1,
				max:10,
				width: 150,
				scroll: false,//当结果集大于默认高度时是否使用卷轴显示 Default: true
				mustMatch : true,
		 		multiple : false,
		 		multipleSeparator : ";",
				scrollHeight: 300,//自动完成提示的卷轴高度用像素大小表示 Default: 180  
				matchContains: true,
				formatItem: function(row, i, max) {
					return row.formatItem;
				},
				formatMatch: function(row, i, max) {
					return row.formatMatch;
				},
				formatResult: function(row) {
					return row.formatResult;
				}
			};
			var options =  $.extend(defaultOptions, param);
			if(options.url!=""){
				$.ajax({
				  type: "POST",
				  url: options.url,
				  data: options.urlParams,
				  dataType: "json",
				  async: false,
				  success: function(data){
					  options.data=data;
				  }
				});
			}
			
			var $autoComplete = $("#"+domId);
			var html = [];
			var onblur = "'$.hz.autocomplate.onChange(this.id,\""+options.hiddenId+"\",\""+options.multipleSeparator+"\")'";
			var inputValue=getInputValueWithDefault(options);
			if("textarea"==options.inputType){
				html.push("<textarea id='"+options.inputId+"' name='"+options.inputName+"' rows='"+options.rows+"' cols='"+options.cols+"' onblur="+onblur);
				html.push(" class=\""+options.inputClass+"\" style=\""+options.inputStyle+"\">"+inputValue+"</textarea>");
			}else{
				html.push("<input type='text' id='"+options.inputId+"' name='"+options.inputName+"' onblur="+onblur);
				html.push(" value='"+inputValue+"' class=\""+options.inputClass+"\" style=\""+options.inputStyle+"\"/>");
			}
			html.push("<input type='hidden' id='"+options.hiddenId+"' name='"+options.hiddenName+"' value='"+options.value+"'/>");
			$autoComplete.append(html.join(''));
			
			if(options.placeholder!=""){
				$("#"+options.inputId).attr("placeholder",options.placeholder);
			}
			$("#"+options.inputId).data("options",options);
			$("#"+options.inputId).autocomplete(options.data,options).result(function(event, data, formatted) {
//	 			 alert(data);
			});
		},
		onChange : function(inputId,hiddenId,multipleSeparator){
			var options = $("#"+inputId).data("options");
			var inputValue = $("#"+inputId).val();
			var correctHiddenValue = getHiddenValues(inputValue,options.data,multipleSeparator);
			$("#"+hiddenId).val(correctHiddenValue);//赋值
//			alert($("#"+hiddenId).val()+":"+$("#"+inputId).val());
			var isMouseDownOnSelect = $("#"+inputId).data("mouseDownOnSelect");
			if(options.multiple && inputValue.lastIndexOf(multipleSeparator)!=inputValue.length-1){
				$("#"+inputId).val(inputValue+multipleSeparator);
			}
			if(!isMouseDownOnSelect && options.blurCallback){
				var correctInputValue = getInputValues(inputValue,options.data,multipleSeparator);
				options.blurCallback(correctInputValue,correctHiddenValue);
			}
		},
	};
	
	function getHiddenValues(inputValue,data,multipleSeparator){
		var values = '';
		var vals = inputValue.split(multipleSeparator);
		for(var j = 0;j < vals.length;j++){
			if(""==vals[j])continue;
			for(var i=0;i<data.length;i++){
				if(data[i].formatResult == vals[j].trim() && values.indexOf(data[i].id) == -1){
					values += data[i].id+',';
					flag = true;
				}
			}
		}
		if(values.length>0){
			values = values.substring(0,values.length-1);
		}
		return values;
	}
	
	function getInputValues(inputValue,data,multipleSeparator){
		var values = '';
		var vals = inputValue.split(multipleSeparator);
		for(var j = 0;j < vals.length;j++){
			if(""==vals[j])continue;
			for(var i=0;i<data.length;i++){
				if(data[i].formatResult == vals[j].trim() && values.indexOf(data[i].formatResult) == -1){
					values += data[i].formatResult+multipleSeparator;
				}
			}
		}
		if(values.length>0){
			values = values.substring(0,values.length-1);
		}
		return values;
	}
	
	function getInputValueWithDefault(options){
		var inputValue = options.inputValue;
		if(!options.hiddenValue || options.hiddenValue=="" || inputValue&&inputValue!=""){
			return inputValue;
		}
		var valueStr = options.hiddenValue;
		var datas = options.data;
		if(!options.multiple){
			for(var i=0 ; i<datas.length ; i++){
				var data = datas[i];
				if(valueStr.indexOf(data.id)>-1){
					return data.formatResult;
				}
			}
		}else{
			$.each(options.data,function(){
				if(valueStr.indexOf(this.id)>-1){
					inputValue+=this.formatResult+options.multipleSeparator;
				}
			});
		}
		return inputValue;
	}
	
})(jQuery);

