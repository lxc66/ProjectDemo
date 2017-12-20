/**
 * 动态加载组件。
 */
(function($){
	$.hz.component = {
		include:function(params){
			var names,sync,isLoadCss;
			if(typeof params == "string"){
				names = params;sync=false;isLoadCss=true;
			}else{
				names = params.names;
				sync = params.sync==undefined?false:params.sync==true;
				isLoadCss = params.isLoadCss==undefined?true:!params.isLoadCss==false;
			}
			
			var jsMark = this.debug?"js":"miniJs";
			var nameArray = names.split(",");
			for(var i=0 ; i<nameArray.length ; i++){
				var name = nameArray[i];
				var component = components[name];
				if(component&&loaded.indexOf(name)<0){
					$.include(component[jsMark],sync);
					if(isLoadCss && component.css){
						$.include(component.css,sync);
					}
					loaded+=name+" ";
				}
			}
		},
		includeAll:function(isLoadCss){
			isLoadCss = isLoadCss==undefined?true:!isLoadCss==false;
			var jsMark = this.debug?"js":"miniJs";
			for(var name in components){
				var component = components[name];
				if(component&&loaded.indexOf(name)<0){
					$.include(component[jsMark]);
					if(isLoadCss && component.css){
						$.include(component.css);
					}
					loaded+=name+" ";
				}
			}
		},
		debug:false,
		get:function(){
			return components;
		}
	};
var loaded="";
var components = {
	jqueryui:{
		js: [com.ue.global.path+'/platform/component/jqueryui/jquery-ui.js'],
		miniJs:com.ue.global.path+'/platform/component/jqueryui/jqueryui-mini.js',
	    css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/jqueryui/jquery-ui.css']
	},
	tree:{
		js: [com.ue.global.path+'/platform/component/ztree/jquery.ztree.all-3.5.js',
		     com.ue.global.path+'/platform/component/ztree/jquery.ztree.js'],
		     miniJs:com.ue.global.path+'/platform/component/ztree/tree-mini.js',
		     css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/ztree/zTreeStyle.css']
	},
	layout:{
		js: [com.ue.global.path+'/platform/component/layout/jquery.layout.js'],
		miniJs:com.ue.global.path+'/platform/component/layout/layout-mini.js',
	},
	ueditor:{
		js: [com.ue.global.path+'/platform/component/ueditor/ueditor.config.js',
		     com.ue.global.path+'/platform/component/ueditor/ueditor.all.js',
		     com.ue.global.path+'/platform/component/ueditor/lang/zh-cn/zh-cn.js',
		     com.ue.global.path+'/platform/component/ueditor/jquery.ueditor.js'],
		miniJs:com.ue.global.path+'/platform/component/ueditor/ueditor-mini.js',
	},
	validate:{
		js: [com.ue.global.path+'/platform/component/validate/jquery.validate.js',
		     com.ue.global.path+'/platform/component/validate/jquery.validate.extend.js',
		     com.ue.global.path+'/platform/component/validate/jquery.validate.messages_zh.js'],
        miniJs:com.ue.global.path+'/platform/component/validate/validate-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/validate/css/validate.css']
	},
	upload:{
		js: [com.ue.global.path+'/platform/component/swfupload/js/swfupload.js',
		     com.ue.global.path+'/platform/component/swfupload/js/swfupload.queue.js',
		     com.ue.global.path+'/platform/component/swfupload/js/jquery.swfupload.js'],
	    miniJs:com.ue.global.path+'/platform/component/swfupload/upload-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/swfupload/swfupload.css']
	},
	progressBar:{
		js: [com.ue.global.path+'/platform/component/progressbar/jquery.progressbar.js'],
		miniJs:com.ue.global.path+'/platform/component/progressbar/progressBar-mini.js'
	},
	page:{
		js: [com.ue.global.path+'/platform/component/page/jquery.page.js'],
		miniJs:com.ue.global.path+'/platform/component/page/page-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/page/css/pageBar.css']
	},
	mask:{
		js: [com.ue.global.path+'/platform/component/mask/jquery.mask.js'],
		miniJs:com.ue.global.path+'/platform/component/mask/mask-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/mask/mask.css']
	},
	box:{
		js: [com.ue.global.path+'/platform/component/box/jquery.box.js'],
		miniJs:com.ue.global.path+'/platform/component/box/box-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/box/css/box.css']
	},
	datePicker:{
		js: [com.ue.global.path+'/platform/component/my97-date/WdatePicker.js'],
		miniJs:com.ue.global.path+'/platform/component/my97-date/datePicker-mini.js'
	},
	accordion:{
		js: [com.ue.global.path+'/platform/component/accordion/jquery-ui-accordion.js'],
		miniJs:com.ue.global.path+'/platform/component/accordion/accordion-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/accordion/accordion.css']
	},
	tabs:{
		js: [com.ue.global.path+'/platform/component/tabs/jquery.tabs.js'],
		miniJs:com.ue.global.path+'/platform/component/tabs/tabs-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/tabs/css/tabs.css']
	},
	popupLayer:{
		js: [com.ue.global.path+'/platform/component/popupLayer/jquery.artDialog.4.1.7.js',
		     com.ue.global.path+'/platform/component/popupLayer/jquery.artDialog.iframeTools.4.1.7.js',
		     com.ue.global.path+'/platform/component/popupLayer/jquery.artDialog.js'],
		miniJs:com.ue.global.path+'/platform/component/popupLayer/popupLayer-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/popupLayer/css/default.css']
	},
	jqgrid:{
		js: [com.ue.global.path+'/platform/component/jqGrid/jquery.jqGrid.js',
		     com.ue.global.path+'/platform/component/jqGrid/grid.locale.js',
		     com.ue.global.path+'/platform/component/jqGrid/jquery.ui.jqGrid.js'],
		miniJs:com.ue.global.path+'/platform/component/jqGrid/jqgrid-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/jqGrid/css/jqgrid.css']
	},
	autocomplete:{
		js: [com.ue.global.path+'/platform/component/autocomplete/jquery.autocomplete.js',
		     com.ue.global.path+'/platform/component/autocomplete/jquery.autocomplete.extend.js'],
		miniJs:com.ue.global.path+'/platform/component/autocomplete/autocomplete-mini.js',
		css:[com.ue.global.path+'/platform/theme/'+com.ue.global.theme+'/component/autocomplete/css/autocomplete.css']
	}
};

})(jQuery);

