/**
 * 把page分页包装为jquery插件，调用插件中的方法。
 */
(function($) {
	$.hz.jqGrid = {
		init:function(tableId,url, callback){
			var me = $("#"+tableId);
			var multiselect = me.attr("multiselect") == "true";
			var title = me.attr("title");
			var isToolbar = me.attr("toolbar") == "true";
			var isAutowidth = me.attr("autowidth") != "true";
			var isShrinkToFit = me.attr("shrinkToFit") == "true";
			var pagerId = me.attr("pager");
			var scrollNum = me.attr("scroll");
			var dataroot = me.attr("dataroot") || "gridModel";
			var tableWidth = me.attr("tableWidth");
			var tableHeight = me.attr("tableHeight");
			var dataEditUrl = me.attr("dataEditUrl");
			var isRownumbers = me.attr("rownumbers") != "false";
			var userPageNo = me.attr("userPageNo");
			var userRowNum = me.attr("userRowNum");
			if (pagerId) {
				me.after("<div id='" + pagerId + "'></div> ");
			}
			var colNames = [];
			var colModels = [];
			var urlparams = '';
			$("thead th", me)
					.each(
							function() {
								var th = $(this);
								var thName = th.attr("name");
								if (thName.indexOf('.') > 0) {
									urlparams += thName + ",";
								}

								var cols = {
									name : thName,
									width : parseInt(th.attr("thWidth")) || 0,
									sortable : th.attr("sort") == "true",
									title : th.attr("showtitle") == "false",
									type : th.attr("type") || "string",
									frozen : th.attr("frozen") == "true",
									hidedlg : th.attr("hide") == "true",
									hidden : th.attr("hide") == "true",
									index : th.attr("index"),
									editable : th.attr("thEditable") == "true",
									edittype : th.attr("edittype"),
									editrules : eval("(" + th.attr("editrules")
											+ ")")

								}

								if (!!th.attr("formatter")) {
									cols.formatter = function(cellvalue, options,
											rowObject) {
										return eval(th.attr("formatter")
												+ "(cellvalue,options,rowObject)");

									}
								}
								if (!!th.attr("edittype")
										&& !!th.attr("editoptions")) {
									cols.editoptions = eval("("
											+ th.attr("editoptions") + ")");
								}
								colModels.push(cols);

								colNames.push(th.text());

							});

			if (!pagerId) {
				me.jqGrid( {
					datatype : "clientside",
					autowidth : isAutowidth,
					width : tableWidth,
					height : tableHeight,
					mtype : "POST",
					viewrecords : true,
					colNames : colNames,
					colModel : colModels,
					multiselect : multiselect,
					forceFit : true,
					hidegrid : true,
					rownumbers : isRownumbers,
					rownumWidth:'50px',
					toolbar : [ isToolbar, "top" ],
					gridview : true,
					shrinkToFit : isShrinkToFit,
					altRows : false,
					rowNum : 1000,
					editurl : dataEditUrl,
					caption : title,
					scroll : scrollNum,//用垂直滚动条来动态加载数据
					viewsortcols : [ true, 'horizontal', true ],
					autoencode : true,
					loadComplete : function(data) {
						$.hz.jqGrid.resizeGrid(me);//重置表格寬和高
				}

				});
				$.getJSON(url + (url.indexOf('?') != -1 ? "&" : "?") + "urlparams="
						+ urlparams, function(data) {
					$.each(data, function(i) {
						me.jqGrid('addRowData', data[i].id, data[i]);
					});
					if (!!callback) {
						callback(data, me);
					}
				});
				me.setGridParam( {
					dataurl : url,
					dataparams : urlparams,
					tableKind : 'localtable'
				});

			} else {
				var postData = {};
				if (url.indexOf('?') != -1) {
					var par = url.substring(url.indexOf('?') + 1, url.length);
					var pars = par.split("&");
					for ( var i = 0; i < pars.length; i++) {
						postData[pars[i].split("=")[0]] = pars[i].split("=")[1];
					}
					url = url.substring(0, url.indexOf('?'));
				}
				me.jqGrid( {
					url : url + "?urlparams=" + urlparams,
					datatype : "json",
					postData : postData,
					autowidth : isAutowidth,
					width : tableWidth,
					height : tableHeight,
					mtype : "POST",
					viewrecords : true,
					colNames : colNames,
					colModel : colModels,
					page:userPageNo,
					rowNum : userRowNum,
					rowList : [ 10, 20, 50 ],
					forceFit : true,
					prmNames : {
						search : "search",
						page : "page.pageNo", // 表示请求页码的参数名称   
						rows : "page.pageSize"
					// 表示请求行数的参数名称   
					},
					pager : pagerId,
					multiselect : multiselect,
					hidegrid : true,
					rownumbers : isRownumbers,
					rownumWidth:'50px',
					toolbar : [ isToolbar, "top" ],
					autoencode : true,
					gridview : true,
					shrinkToFit : isShrinkToFit,
					altRows : false,
					jsonReader : {
						root : dataroot,
						records : "record",
						repeatitems : false
					},
					editurl : dataEditUrl,
					caption : title,
					recordtext : "{0}-{1} " + jQuery.i18n.prop("dataGrid.page.all")
							+ "{2} " + jQuery.i18n.prop("dataGrid.page.record"),
					scroll : scrollNum,//用垂直滚动条来动态加载数据
					viewsortcols : [ true, 'horizontal', true ],//排序图标的不同的外观和行为,是否所有的图标都能同时看到,图标的位(vertical/horizontal),确定鼠标点击事件功能(true表示点击列标题可排序；false表示只能点击图标才能排序)
					pgtext : jQuery.i18n.prop("dataGrid.page.current") + "{0}/{1}"
							+ jQuery.i18n.prop("dataGrid.page.pageText"),
					pageFirst : jQuery.i18n.prop("dataGrid.page.first"),
					pagePre : jQuery.i18n.prop("dataGrid.page.previous"),
					pageNext : jQuery.i18n.prop("dataGrid.page.next"),
					pageLast : jQuery.i18n.prop("dataGrid.page.last"),
					rowListText : jQuery.i18n.prop("dataGrid.page.rowListText"),
					gridComplete : function(data) {
						$.hz.jqGrid.resizeGrid(me);//重置表格寬和高
					if (!!callback) {
						callback(data, me);
					}
				}

				});
				me.setGridParam( {
					tableKind : 'jsontable'
				});
			}

			me.jqGrid('setFrozenColumns');
		},
		//查询
		searchTable : function(tableId, postData, callback) {
			var gridObj = $("#" + tableId);
			var tableKind = gridObj.jqGrid("getGridParam", "tableKind");
			if (tableKind == 'jsontable') {
				gridObj.jqGrid('setGridParam', {
					postData : postData
				});
				$.hz.jqGrid.refreshTable(tableId);
			} else if (tableKind == 'localtable') {
				var dataurl = gridObj.jqGrid("getGridParam", "dataurl");
				var dataparams = gridObj.jqGrid("getGridParam", "dataparams");
				if (dataurl.indexOf('?') != -1) {
					dataurl = dataurl.substring(0, dataurl.indexOf('?'));
				}
				$.post(dataurl + "?urlparams=" + dataparams, postData, function(
						data) {
					var dataObj = $.parseJSON(data);
					gridObj.jqGrid("clearGridData");
					$.each(dataObj, function(i) {
						gridObj.jqGrid('addRowData', dataObj[i].id, dataObj[i]);
					});
					if (!!callback) {
						callback(data, gridObj);
					}
				});
			}

		},
		//刷新
		refreshTable : function(tableId) {
			var gridObj = $("#" + tableId);
			var tableKind = gridObj.jqGrid("getGridParam", "tableKind");
			if (tableKind == 'jsontable') {
				gridObj.trigger("reloadGrid", [ {
					page : 1
				} ]);
			} else if (tableKind == 'localtable') {
				var dataurl = gridObj.jqGrid("getGridParam", "dataurl");
				var dataparams = gridObj.jqGrid("getGridParam", "dataparams");

				$.getJSON(dataurl + (dataurl.indexOf('?') != -1 ? "&" : "?")
						+ "urlparams=" + dataparams, function(data) {
					gridObj.jqGrid("clearGridData");
					$.each(data, function(i) {
						gridObj.jqGrid('addRowData', data[i].id, data[i]);
					});
				});
			}

		},
		//删除表格数据
		batchDel : function(tableId, actionUrl) {
			var selectIds = $("#" + tableId).jqGrid("getGridParam", "selarrrow");
			if (selectIds.length < 1) {
				alert("请选择要删除的项！");
				return;
			}
			confirm("确定要删除吗？", function() {
				var delparam = "";
				$.each($(selectIds), function(i) {
					delparam += 'sels=' + this + '&';
				});
				$.ajax( {
					type : "POST",
					url : actionUrl,
					processData : true,
					data : delparam,
					success : function(data) {
						$.hz.jqGrid.refreshTable(tableId);

					}
				});
			});

		},
		//数据表格选中单行行返回选中则返回选中的id值
		singleSelectValue : function(tableId) {
			var returnValue = "";
			var selectedRowIds = $("#" + tableId).jqGrid("getGridParam",
					"selarrrow");
			if (selectedRowIds.length > 1) {
				alert("请最多选择一个选项！");
			} else if (selectedRowIds.length == 0) {
				alert("请至少选择一个选项！");
			} else {
				returnValue = selectedRowIds[0];
			}
			return returnValue;
		},
		//数据表格选中多行返回选中则返回选中的id数组
		multiSelectValue : function(tableId) {
			var returnValue = [];
			var selectedRowIds = $("#" + tableId).jqGrid("getGridParam",
					"selarrrow");
			if (selectedRowIds.length < 1) {
				alert("请至少选择一个选项！");
			} else {
				$.each($(selectedRowIds), function(i) {
					returnValue.push(this);
				});

			}
			return returnValue;
		},
		appendToolBarBtn :function(tableId,optionHtml){
			$("#t_"+tableId).append(optionHtml.join(''));
		},
		/**
		 * 重置数据表格尺寸
		 * 
		 */	
		resizeGrid:function(gridObj,options){
			if($(gridObj).size() == 0){//判断没有数据表格是返回，iframe页面不使用数据表格时没有引jquery.jqGrid.js，导致setGridHeight报错
				return;
			}
			var gridWrap = gridObj.closest('.ui-layout-center');//.aui_content
			
			var formWrap = gridWrap.children('form');
			if(formWrap.size()>0){
				formWrap.addClass('abs5 ovauto');
			}
			
			var parWrap = gridObj.parents().map(function(){
				if($(this).css('position') == 'absolute'){
					return $(this);
				}
			})[0];
			
			options = options || {};
			var defaults = {
				width:parseInt(gridObj.attr('bodyWidth')) || 0,
				height:parseInt(gridObj.attr('bodyHeight')) || 0,
				h1:gridWrap.find('.box_header').outerHeight(true) || 0,
				h2:gridWrap.find('.searchBox').outerHeight(true) || 0,
				h3:gridWrap.find('.send_optArea').outerHeight(true) || 0,
				h4:gridObj.closest('.ui-jqgrid').find('.t_toolbar').outerHeight(true) || 0,
				h5:gridObj.closest('.ui-jqgrid').find('.ui-jqgrid-hdiv').outerHeight(true) || 0,
				h6:gridObj.closest('.ui-jqgrid').find('.ui-jqgrid-pager').outerHeight(true) || 0,
				wp:$(parWrap).size()>0 ? $(parWrap).outerWidth()-$(parWrap).width() : 0,
				hp:$(parWrap).size()>0 ? $(parWrap).outerHeight()-$(parWrap).height() : 0,
				w:2,
				h:2
			};
			// 合并默认配置
			for (var i in defaults) {
				if (options[i] === undefined) options[i] = defaults[i];		
			};
			if(gridWrap.size()>0){
				if(gridWrap.find('.ui-layout-pane').find('.jqgrid').size()>0){
					if(options.width !='' && options.width>0){
						gridObj.setGridWidth(options.width,true);
					}else{
						gridObj.setGridWidth(gridObj.closest('.ui-layout-pane').width()-"2",true);
					}
					if(options.height !='' && options.height>0){
						gridObj.setGridHeight(options.height,true);
					}else{
						var otherHeight = options.h2+options.h3+options.h4+options.h5+options.h6+options.h+2;
						gridObj.setGridHeight(gridObj.closest('.ui-layout-pane').height()-otherHeight,true);
					}
				}
				else{
					if(options.width !='' && options.width>0){
						gridObj.setGridWidth(options.width,true);
					}else{
						gridObj.setGridWidth($(parWrap).outerWidth()-options.wp-options.w,true);
					}
					if(options.height !='' && options.height>0){
						gridObj.setGridHeight(options.height,true);
					}else{
						var otherHeight = options.h2+options.h3+options.h4+options.h5+options.h6+options.hp+options.h;
						gridObj.setGridHeight($(parWrap).outerHeight()-otherHeight,true);
					}
				}
			}else{
				gridObj.setGridWidth(options.width,true);
				gridObj.setGridHeight(options.height,true);
			}
			
		},
	};

})(jQuery);

////////////////////////////////////////

$.i18n.properties({
    name:['jqGrid'],
    path: com.ue.global.path+'/sys/i18n/ajaxGetI18nValues.do?bundleName=', 
    mode:'map',
    callback: function() {
    	
    }
});


