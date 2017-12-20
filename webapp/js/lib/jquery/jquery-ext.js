//对jquery的扩展
$.extend({
	browser:{//jqeury1.9去除了browser属性
		mozilla : /firefox/.test(navigator.userAgent.toLowerCase()),
		webkit : /webkit/.test(navigator.userAgent.toLowerCase()),
		opera : /opera/.test(navigator.userAgent.toLowerCase()),
		msie : /msie/.test(navigator.userAgent.toLowerCase()),
		version : (/(webkit)[ \/]([\w.]+)/.exec(navigator.userAgent.toLowerCase()) || /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(navigator.userAgent.toLowerCase()) || /(msie) ([\w.]+)/.exec(navigator.userAgent.toLowerCase()) || navigator.userAgent.toLowerCase().indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+))?/.exec( navigator.userAgent.toLowerCase() ) || [])[2] || "0",
		isMobilePlatforms:(function(){
			var userAgent = navigator["userAgent"]["toLowerCase"]();
			if (userAgent["indexOf"]('iphone') > 0 
				|| userAgent["indexOf"]('ipod') > 0 
				|| userAgent["indexOf"]('ipad') > 0          
				|| userAgent["indexOf"]('Android') > 0 
				|| userAgent["indexOf"]('ios') > 0
				|| userAgent["indexOf"]('symbianos') > 0){
				return true; 
			}
			return false;
		})()
	},
	uuid:function(){
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		    var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
		    return v.toString(16);
		});
	},
	isEmpty:function(value){
        var type;
        if(value == null) {
            return true;
        }
        type = Object.prototype.toString.call(value).slice(8, -1);
        switch(type) {
            case 'String':
                return !$.trim(value);
            case 'Array':
                return !value.length;
            case 'Object':
                return $.isEmptyObject(value);
            default:
                return false;
        }
	},
	htmlEncode:function(value){
		return !value ? value : String(value).replace(/&/g, "&amp;").replace(/\"/g, "&quot;").replace(/\'/g, "&#39;").replace(/ /g, "&nbsp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
	},
	htmlDecode:function(value){
		return !value ? value : String(value).replace(/&amp;/g, "&").replace(/&quot;/g, "\"").replace(/&#39;/g, "\'").replace(/&nbsp;/g, " ").replace(/&lt;/g, "<").replace(/&gt;/g, ">");
	},
	nano:function(template,data){//模板
		return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
			var keys = key.split("."), value = data[keys.shift()];
			$.each(keys, function() {
				value = value[this];
			});
			return (value === null || value === undefined) ? "" : value;
		});
	},
	confirm:function(title,func){
		if(!$.isFunction(func))func=null;
		layer.confirm(title, {
		  btn: ['确定','取消'] //按钮
		},func);
	},
	alert:function(title,func){
		if(!$.isFunction(func))func=null;
		layer.alert(title, {
		  skin: 'layui-layer-molv' //样式类名
		  ,closeBtn: 0
		}, func);
	},
	msg:function(msg,icon){
		layer.msg(msg, {
		    icon: icon||1, // 默认1; 1 成功   2 失败   共0-6
		    time: 2000, //1秒关闭（如果不配置，默认是3秒）
//		    shade:0.3,
		    offset: '250px'
		}, function(){
		    //do something
		});
	},
	loadPage:function(config){
		if(!config || !config.url){
			return;
		}
		var contentLoadId = "content_load_"+$.uuid();
		$("body").append("<div style='display: none;' id='"+contentLoadId+"'></div>");
		var defaultConfig = {
				url:config.url,
				type: 1,
				title: config.title || '弹出层',
				fix: config.fix || false,
				maxmin: false,
				shadeClose: false,
				resize:false,
//  	    	    move:false,
				btnAlign: 'c',
				area: config.area || ['1100px', '600px'],
				content: $("#"+contentLoadId),
				end: function(){
					$("#"+contentLoadId).remove();
				}
		};
		var theConfig = $.extend(defaultConfig, config);
		$.loading();
  		$("#"+contentLoadId).load(config.url,function(){
  			$.loadingClose();
  	    	layer.open(theConfig);
    	});
	},
	loadingIndexs:[],
	loading:function(config){
        if(this.isEmpty(config)){
            config = {};
            config.time = 60000;
          }
          var layerIndex = layer.load(1, {time: config.time});
          $.loadingIndexs.push(layerIndex);
  	},
  	loadingClose:function(){
  	    layer.close($.loadingIndexs.pop());
  	},
  	layerIndex:function(index){
  		return layer.index;
  	},
  	layerClose:function(index){
  		layer.close(index);
  	},
  	tips:function(content,objId,config){
        if(this.isEmpty(config)){
	        config = {};
	    }
  		layer.tips(content, objId, config);
  	}
});

$.ajaxSetup({
	cache: false,
	complete: function(xhr,status){
		var sessionstatus = xhr.getResponseHeader("sessionstatus");
//		console.log("sessionstatus:"+sessionstatus);
		if(sessionstatus=="timeout"){   
            //如果超时就处理 ，指定要跳转的页面  
            window.location.replace("/");   
        }   
	},
	success: function(result,status,xhr){

	},
	error: function(XMLHttpRequest, textStatus, errorThrown){
		if(XMLHttpRequest.status==403){
			alert('您没有权限进行此操作!');
			return false;
		}
		if(XMLHttpRequest.status==400){
			alert(XMLHttpRequest.responseText);
			return false;
		}
	}
});

