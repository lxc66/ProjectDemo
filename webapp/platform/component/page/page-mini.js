///platform/component/page/jquery.page.js
!function(a){function e(e,t){a("#"+e).data("settings",t)}function t(e){return a("#"+e).data("settings")}function p(e){var t=[],p="";return a.each(e,function(){var a=parseInt(this);!isNaN(a)&&p.indexOf(""+a)<0&&(p+=a,t.push(a))}),t}a.hz.page={init:function(t,p){var s=a("#"+p.formId).attr("action");if(!s||""==s.trim())return!1;var r=-1==s.indexOf("?")?"?":"&",o={type:"normal",showSwitchSize:!1,dataUrl:s,urlSplit:r};return a.extend(o,p),e(t,o),a("#"+t).addClass("pageBar"),n[o.type]&&n[o.type].init(t,o),!0},goUrl:function(a){a&&(window.location=a)},gotoFirst:function(a){this.gotoPage(a,1)},gotoLast:function(a){var e=t(a);this.gotoPage(a,e.pageInfo.totalPages)},gotoPrevious:function(a){var e=t(a);this.gotoPage(a,e.pageInfo.currentPage-1)},gotoNext:function(a){var e=t(a);this.gotoPage(a,e.pageInfo.currentPage+1)},gotoPage:function(e,p){var n=t(e),s=n.containerId,r=n.formId,o=n.dataUrl+n.urlSplit+"page.pageSize="+n.pageInfo.pageSize+"&page.pageNo="+p,i={};paramsStr=a("#"+r).serializeArray(),""!=paramsStr&&a.each(paramsStr,function(a,e){i[e.name]=e.value}),null!=s&&""!=s?a("#"+s).load(o,i):this.goUrl(o)},jumpPage:function(e){var p=t(e),n=a("#pn_"+e).val();return/^[0-9]*[1-9][0-9]*$/.test(n)?1>n?void this.gotoFirst(e):n>p.pageInfo.totalPages?void this.gotoLast(e):void this.gotoPage(e,n):void 0},changePageSize:function(a,p){var n=t(a);n.pageInfo.pageSize=p,e(a,n),this.gotoFirst(a)},getPageSize:function(a){var e=t(a);return e?e.pageInfo.pageSize:0},getCurrentPage:function(a){var e=t(a);return e?e.pageInfo.currentPage:0},refresh:function(a){this.gotoPage(a,1)}};var n={normal:{init:function(e,t){var n=e,s=t.pageInfo,r=s.pageSize,o=t.changePageSize,i=[];if(i.push('<table width="100%" cellspacing="0" cellpadding="0" border="0">'),i.push('	<tbody><tr><td class="w20">&nbsp;</td>'),i.push('	<td align="left" nowrap="nowrap">'+a.i18n.prop("pagebar.totalRecords",s.totalRecords)+"</td>"),o){var g;if(t.changePageSizeNumber&&"string"==typeof t.changePageSizeNumber){var u=p(t.changePageSizeNumber.split(","));g=u.length>1?u:p([10,20,50])}else g=p([10,20,50]);g=g.sort(function(a,e){return a>e?1:-1}),i.push('	<td class="w70" nowrap="nowrap">'+a.i18n.prop("pagebar.pageShow")+"</td>"),i.push('	<td class="w100" nowrap="nowrap"><ul class="pageNum">'),a.each(g,function(a,e){var t=0==a?"top":a+1==g.length?"bottom":"center";i.push('	<li class="'+t+'"><p '+(r==e?'class="select"':"")+'><a href="javascript:void(0);" onclick="$.hz.page.changePageSize(\''+n+"',"+e+');">'+e+"</a></p></li>")}),i.push("	</ul></td>")}i.push('	<td class="w20">&nbsp;</td>'),i.push('	<td class="w70 tc" nowrap="nowrap">'),i.push(s.isFirstPage?'<span class="gray">'+a.i18n.prop("pagebar.home")+"</span>":'<a href="javascript:void(0);" onclick="$.hz.page.gotoFirst(\''+n+"');return false;\">"+a.i18n.prop("pagebar.home")+"</a></td>"),i.push("	</td>"),i.push('	<td class="w70 tc" nowrap="nowrap">'),i.push(s.isHavePrePage?'<a href="javascript:void(0);" onclick="$.hz.page.gotoPrevious(\''+n+"');return false;\">"+a.i18n.prop("pagebar.previous")+"</a></td>":'<span class="gray">'+a.i18n.prop("pagebar.previous")+"</span>"),i.push("	</td>"),i.push('	<td class="w70 tc" nowrap="nowrap">'),i.push(s.isHaveNextPage?'<a href="javascript:void(0);" onclick="$.hz.page.gotoNext(\''+n+"');return false;\">"+a.i18n.prop("pagebar.next")+"</a></td>":'<span class="gray">'+a.i18n.prop("pagebar.next")+"</span>"),i.push("	</td>"),i.push('	<td class="w70 tc" nowrap="nowrap">'),i.push(s.isLastPage?'<span class="gray">'+a.i18n.prop("pagebar.last")+"</span>":'<a href="javascript:void(0);" onclick="$.hz.page.gotoLast(\''+n+"');return false;\">"+a.i18n.prop("pagebar.last")+"</a></td>"),i.push("	</td>"),i.push('	<td class="w20">&nbsp;</td>'),i.push('	<td class="w170" nowrap="nowrap"><span style="padding-left: 6px;">'+a.i18n.prop("pagebar.current")+'</span><input type="text" id="pn_'+n+'" size="2" value="'+s.currentPage+'" class="page-num" onkeydown="if(event.keyCode==13){$.hz.page.jumpPage(\''+n+'\');return false;}" value="'+s.currentPage+'">'),i.push('	<span style="padding-right: 6px;">/'+a.i18n.prop("pagebar.totalPage",s.totalPages)+"</span></td>"),i.push('	<td class="w20">&nbsp;</td>'),i.push("	</tr></tbody></table>"),a("#"+n).append(i.join(""))}},simple:{init:function(e,t){var n=e,s=t.pageInfo,r=s.pageSize,o=t.changePageSize,i=[];if(i.push('<table width="100%" cellspacing="0" cellpadding="0" border="0">'),i.push("	<tbody><tr>"),o){var g;if(t.changePageSizeNumber&&"string"==typeof t.changePageSizeNumber){var u=p(t.changePageSizeNumber.split(","));g=u.length>1?u:p([10,20,50])}else g=p([10,20,50]);g=g.sort(function(a,e){return a>e?1:-1}),i.push('	<td class="w70" nowrap="nowrap">'+a.i18n.prop("pagebar.pageShow")+"</td>"),i.push('	<td class="w100" nowrap="nowrap"><ul class="pageNum">'),a.each(g,function(a,e){var t=0==a?"top":a+1==g.length?"bottom":"center";i.push('	<li class="'+t+'"><p '+(r==e?'class="select"':"")+'><a href="javascript:void(0);" onclick="$.hz.page.changePageSize(\''+n+"',"+e+');">'+e+"</a></p></li>")}),i.push("	</ul></td>")}i.push('	<td class="w20">&nbsp;</td>'),i.push('	<td class="w70 tc" nowrap="nowrap">'),i.push(s.isHavePrePage?'<a href="javascript:void(0);" onclick="$.hz.page.gotoPrevious(\''+n+"');return false;\">"+a.i18n.prop("pagebar.previous")+"</a></td>":'<span class="gray">'+a.i18n.prop("pagebar.previous")+"</span>"),i.push("	</td>"),i.push('	<td class="w170" nowrap="nowrap"><input type="text" id="pn_'+n+'" size="2" value="'+s.currentPage+'" class="page-num" onkeydown="if(event.keyCode==13){$.hz.page.jumpPage(\''+n+'\');return false;}" value="'+s.currentPage+'">'),i.push('	<span style="padding-right: 6px;">/'+s.totalPages+"</span></td>"),i.push('	<td class="w70 tc" nowrap="nowrap">'),i.push(s.isHaveNextPage?'<a href="javascript:void(0);" onclick="$.hz.page.gotoNext(\''+n+"');return false;\">"+a.i18n.prop("pagebar.next")+"</a></td>":'<span class="gray">'+a.i18n.prop("pagebar.next")+"</span>"),i.push("	</td>"),i.push('	<td class="w20">&nbsp;</td>'),i.push("	</tr></tbody></table>"),a("#"+n).append(i.join(""))}}}}(jQuery),$.i18n.properties({name:["pagebar"],path:com.ue.global.path+"/sys/i18n/ajaxGetI18nValues.do?bundleName=",mode:"map",callback:function(){}});
;