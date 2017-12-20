<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<div class="box1 fl" boxTitle="数据表格">
	<font color="green"><b>有分页条展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<table id="${pageId}list"  multiselect="true" pager="pager"  toolbar="true" class="jqgrid"  >
				<thead>
						<tr>
							<th name="id" hide="true">
			
							</th>
							<th sort="true" name="name" >姓名
							</th>
							<th name="email" >email
							</th>
						</tr>
				</thead>
		</table>
	</div>
	<font class="green fb">html代码：</font>
	<pre class="syntax brush-html">
		&lt;table id="&amp;{pageId}list"  multiselect="true" pager="pager"  toolbar="true" class="jqgrid"  >
				&lt;thead>
						&lt;tr>
							&lt;th name="id" hide="true">
			
							&lt;/th>
							&lt;th sort="true" name="name" >姓名
							&lt;/th>
							&lt;th name="email" >email
							&lt;/th>
						&lt;/tr>
				&lt;/thead>
		&lt;/table>
	</pre>
</div>
<div class="box1 fl" boxTitle="数据表格">
	<font color="green"><b>无分页条展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<table id="${pageId}list2"  multiselect="true"  toolbar="true" class="jqgrid"  >
				<thead>
						<tr>
							<th name="id" hide="true">
			
							</th>
							<th sort="true" name="name" >姓名
							</th>
							<th name="email" >email
							</th>
						</tr>
				</thead>
		</table>
	</div>
	<font class="green fb">html代码：</font>
	<pre class="syntax brush-html">
		&lt;table id="&amp;{pageId}list2"  multiselect="true"   toolbar="true" class="jqgrid"  >
				&lt;thead>
						&lt;tr>
							&lt;th name="id" hide="true">
			
							&lt;/th>
							&lt;th sort="true" name="name" >姓名
							&lt;/th>
							&lt;th name="email" >email
							&lt;/th>
						&lt;/tr>
				&lt;/thead>
		&lt;/table>
	</pre>
</div>

<div class="box1 fl" boxTitle="js代码">
	<pre class="syntax brush-html">
		$.hz.jqGrid.init("&amp;{pageId}list","${path}/dm/jqGrid/demo1");
			var opHtml=[];
			opHtml.push("<div class='fl ml10'>");
			opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='refreshTable()'><span class='fm f8 mr5'>+</span>同步所有</a>");
			opHtml.push("</div>");
			&amp;.hz.jqGrid.appendToolBarBtn("&amp;{pageId}list",opHtml);
	</pre>
	
</div>

<div class="box1 fl" boxTitle="js调用方法接口">
	
	<pre class="syntax brush-html">
		调用时统一在前面加上 $.hz.jqGrid前缀
		1.数据表格初始化：init(tableId,url,callback);  //提供表格初始化功能，其中tableId为&lt;table>标签的id属性  必须给出且唯一，url为要获取数据的访问地址。
		2.为表格添加头部工具条按钮：appendToolBarBtn(tableId,opHtml);  //opHtml是按js代码展示区给出的代码格式，拼接的html代码串。
		3.表格查询：searchTable(tableId, postData, callback);
		//行如：$.hz.jqGrid.searchTable("${pageId}list",{
			name:"无名1",
			email:"wuming@qq.com"
			},refreshTable());
		4.表格刷新：refreshTable(tableId);
		5.批量删除数据：batchDel(tableId, actionUrl);//删除选中的数据   actionUrl为要访问的后台方法地址
		6.获取单个选中的数据行id值：singleSelectValue(tableId);
		7.获取多个选中的数据行id值数组：multiSelectValue(tableId);
	</pre>
</div>
<div class="box1 fl" boxTitle="特殊属性说明">
	<pre class="syntax brush-html">
		该处所列的属性全部为&lt;table>标签中的自定义属性
		1.pager:控制是否显示分页条,如果有值,则显示 ,否则不显示。
		注意：当不使用分页条时，后台方法不可用page分页对象来生成json数据。
		2.tableWidth：表格宽度
		3.tableHeight:表格高度
		4.multiselect:是否多选
		5.toolbar:是否显示工具条
	</pre>
</div>


<script>
	$(document).ready(function(){
		$(".box1").box1();
	});
</script>
<script>
	
	function loadPage(type){
// 		$('#ajaxPage1').load("${path}/dm/page/ajaxDemo1/"+type,function(){});
	}
	$(document).ready(function() {
// 		$('#ajaxPage1').load("${path}/dm/page/ajaxDemo1/normal",function(){});
// 		$(".box1").box1();

		$.hz.jqGrid.init("${pageId}list","${path}/dm/jqGrid/demo1");
		var opHtml=[];
		opHtml.push("<div class='fl ml10'>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='searchTable()'>查询</a>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='refreshTable()'>刷新</a>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='singleSelectValue()'>单个选中</a>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='multiSelectValue()'>多个选中</a>");
		opHtml.push("</div>");
		$.hz.jqGrid.appendToolBarBtn("${pageId}list",opHtml);
		/////////
		$.hz.jqGrid.init("${pageId}list2","${path}/dm/jqGrid/demo2");
// 		var opHtml=[];
// 		opHtml.push("<div class='fl ml10'>");
// 		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='searchTable()'>查询</a>");
// 		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='refreshTable()'>刷新</a>");
// 		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='singleSelectValue()'>单个选中</a>");
// 		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='multiSelectValue()'>多个选中</a>");
// 		opHtml.push("</div>");
// 		$.hz.jqGrid.appendToolBarBtn("${pageId}list",opHtml);
		//$("#t_${pageId}list").append(opHtml.join(''));
		
	});
	function refreshTable(){
		$.hz.jqGrid.refreshTable("${pageId}list");
	};
	function searchTable(){
		$.hz.jqGrid.searchTable("${pageId}list",{
			name:"无名1",
			email:"wuming@qq.com"
		},refreshTable());
	};
	function singleSelectValue(){
		var id=$.hz.jqGrid.singleSelectValue("${pageId}list");
		alert("选中的值为"+id);
	};
	function multiSelectValue(){
		var ids=$.hz.jqGrid.multiSelectValue("${pageId}list");
		alert("选中的值共"+ids.length+"个");
	}
</script>
