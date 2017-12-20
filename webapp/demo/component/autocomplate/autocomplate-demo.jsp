<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<div class="box1 fl" boxTitle="自动完成">
	<div class="box1 fl" boxTitle="1、普通文本输入框">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
			<div id="autocomplate1"></div>
			<div id="autocomplate1_view"></div>
		</div>
		
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<div id="autocomplate1"></div>
			<div id="autocomplate1_view"></div>
		</pre>
		
		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
		var data=[{id:"01",formatItem:"<b style='color: blue;'>苹果</b>",		formatMatch:"苹果pingguopg",		formatResult:"苹果"},
		          {id:"02",formatItem:"<b style='color: blue;'>香蕉</b>",		formatMatch:"香蕉xiangjiaoxj",	formatResult:"香蕉"},
		          {id:"03",formatItem:"<b style='color: blue;'>橙子</b>",		formatMatch:"橙子chengzicz",		formatResult:"橙子"},
		          {id:"04",formatItem:"<b style='color: blue;'>鸭梨</b>",		formatMatch:"鸭梨yaliyl",			formatResult:"鸭梨"},
		          {id:"05",formatItem:"<b style='color: blue;'>柚子</b>",		formatMatch:"柚子youziyz",		formatResult:"柚子"},
		          {id:"06",formatItem:"<b style='color: blue;'>榴莲</b>",		formatMatch:"榴莲liulianll",		formatResult:"榴莲"},
		          {id:"07",formatItem:"<b style='color: blue;'>荔枝</b>",		formatMatch:"荔枝lizhilz",		formatResult:"荔枝"},
		          {id:"08",formatItem:"<b style='color: blue;'>葡萄</b>",		formatMatch:"葡萄putaopt",		formatResult:"葡萄"},
		          {id:"09",formatItem:"<b style='color: blue;'>龙眼</b>",		formatMatch:"龙眼longyanly",		formatResult:"龙眼"},
		          {id:"10",formatItem:"<b style='color: blue;'>乌梅</b>",		formatMatch:"乌梅wumeiwm",		formatResult:"乌梅"}];
		$.hz.autocomplate.init("autocomplate1",{
			data:data,
			hiddenValue:"09",
			blurCallback:function(inputValue,hiddenValue){
				$("#autocomplate1_view").empty().append("inputValue:"+inputValue+"<br>hiddenValue:"+hiddenValue);
			}
		});
		</pre>
	</div>
	<div class="box1 fl" boxTitle="2、文本域">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
			<div id="autocomplate2"></div>
			<div id="autocomplate2_view"></div>
		</div>
		
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<div id="autocomplate2"></div>
			<div id="autocomplate2_view"></div>
		</pre>
		
		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
		var data=[{id:"01",formatItem:"<b style='color: blue;'>苹果</b>",		formatMatch:"苹果pingguopg",		formatResult:"苹果"},
		          {id:"02",formatItem:"<b style='color: blue;'>香蕉</b>",		formatMatch:"香蕉xiangjiaoxj",	formatResult:"香蕉"},
		          {id:"03",formatItem:"<b style='color: blue;'>橙子</b>",		formatMatch:"橙子chengzicz",		formatResult:"橙子"},
		          {id:"04",formatItem:"<b style='color: blue;'>鸭梨</b>",		formatMatch:"鸭梨yaliyl",			formatResult:"鸭梨"},
		          {id:"05",formatItem:"<b style='color: blue;'>柚子</b>",		formatMatch:"柚子youziyz",		formatResult:"柚子"},
		          {id:"06",formatItem:"<b style='color: blue;'>榴莲</b>",		formatMatch:"榴莲liulianll",		formatResult:"榴莲"},
		          {id:"07",formatItem:"<b style='color: blue;'>荔枝</b>",		formatMatch:"荔枝lizhilz",		formatResult:"荔枝"},
		          {id:"08",formatItem:"<b style='color: blue;'>葡萄</b>",		formatMatch:"葡萄putaopt",		formatResult:"葡萄"},
		          {id:"09",formatItem:"<b style='color: blue;'>龙眼</b>",		formatMatch:"龙眼longyanly",		formatResult:"龙眼"},
		          {id:"10",formatItem:"<b style='color: blue;'>乌梅</b>",		formatMatch:"乌梅wumeiwm",		formatResult:"乌梅"}];
		$.hz.autocomplate.init("autocomplate2",{
			data:data,
			inputType:"textarea",
			multiple:true,
			multipleSeparator:";",
			hiddenValue:"01,03,09",
			width:430,
			formatItem: function(row, i, max) {
				if(i%2==0){
					return "<b style='color: green;'>"+row.formatResult+"</b>";
				}
				return row.formatItem;
			},
			formatMatch: function(row, i, max) {
				return row.formatMatch;
			},
			formatResult: function(row) {
				return row.formatResult;
			},
			blurCallback:function(inputValue,hiddenValue){
				$("#autocomplate2_view").empty().append("inputValue:"+inputValue+"<br>hiddenValue:"+hiddenValue);
			}
		});
		</pre>
	</div>
	<font class="green fb">常用API：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">参数列表(options)</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>data</td>
		   <td>JSON</td>
		   <td></td>
		   <td>自动完成组件的JSON数据对象</td>
		</tr>
		<tr>
		   <td>url</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件的数据请求地址，与"data"参数二选一</td>
		</tr>
		<tr>
		   <td>urlParams</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件的数据请求地址的参数</td>
		</tr>
		<tr>
		   <td>inputType</td>
		   <td>String</td>
		   <td>text</td>
		   <td>输入框的类型，可选项:text、textarea</td>
		</tr>
		<tr>
		   <td>inputId</td>
		   <td>String</td>
		   <td>自动生成</td>
		   <td>自动完成组件输入框的唯一ID</td>
		</tr>
		<tr>
		   <td>inputName</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件输入框的Name</td>
		</tr>
		<tr>
		   <td>inputValue</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件输入框的回显值。注意：如果此值不设置则会根据hiddenValue计算，数据量大会有效率问题</td>
		</tr>
		<tr>
		   <td>hiddenId</td>
		   <td>String</td>
		   <td>自动生成</td>
		   <td>自动完成组件隐藏域的唯一ID</td>
		</tr>
		<tr>
		   <td>hiddenName</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件隐藏域的Name</td>
		</tr>
		<tr>
		   <td>hiddenValue</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件隐藏域的回显值。</td>
		</tr>
		<tr>
		   <td>inputClass</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件输入框的Class属性</td>
		</tr>
		<tr>
		   <td>inputStyle</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件输入框的Style属性</td>
		</tr>
		<tr>
		   <td>rows</td>
		   <td>String</td>
		   <td>3</td>
		   <td>自动完成组件输入框（文本域）行数</td>
		</tr>
		<tr>
		   <td>cols</td>
		   <td>String</td>
		   <td>50</td>
		   <td>自动完成组件输入框（文本域）列数</td>
		</tr>
		<tr>
		   <td>placeholder</td>
		   <td>String</td>
		   <td></td>
		   <td>自动完成组件输入框的提示占位符</td>
		</tr>
		<tr>
		   <td>minChars</td>
		   <td>Integer</td>
		   <td>1</td>
		   <td>在触发autoComplete前用户至少需要输入的字符数</td>
		</tr>
		<tr>
		   <td>width</td>
		   <td>Integer</td>
		   <td>150</td>
		   <td>指定下拉框的宽度</td>
		</tr>
		<tr>
		   <td>max</td>
		   <td>Integer</td>
		   <td>10</td>
		   <td>autoComplete下拉显示项目的个数 </td>
		</tr>
		<tr>
		   <td>multiple</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>是否允许输入多个值即多次使用autoComplete以输入多个值</td>
		</tr>
		<tr>
		   <td>multipleSeparator</td>
		   <td>String</td>
		   <td>;</td>
		   <td>如果是多选时,用来分开各个选择的字符</td>
		</tr>
		<tr>
		   <td>mustMatch</td>
		   <td>Boolean</td>
		   <td>true</td>
		   <td>如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框</td>
		</tr>
		<tr>
		   <td>matchContains</td>
		   <td>Boolean</td>
		   <td>true</td>
		   <td>决定比较时是否要在字符串内部查看匹配,如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用</td>
		</tr>
		<tr>
		   <td>autoFill</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>要不要在用户选择时自动将用户当前鼠标所在的值填入到input框</td>
		</tr>
		<tr>
		   <td>scroll</td>
		   <td>Boolean</td>
		   <td>true</td>
		   <td>当结果集大于默认高度时是否使用卷轴显示</td>
		</tr>
		<tr>
		   <td>scrollHeight</td>
		   <td>Integer</td>
		   <td>300</td>
		   <td>自动完成提示的卷轴高度用像素大小</td>
		</tr>
		<tr>
		   <td>formatItem</td>
		   <td>Function</td>
		   <td></td>
		   <td>作用在于可以格式化列表中的条目,Autocompleter会提供三个参数(row, i, max)</td>
		</tr>
		<tr>
		   <td>formatMatch</td>
		   <td>Function</td>
		   <td></td>
		   <td>对每一行数据使用此函数格式化需要查询的数据格式使之匹配原始数据,返回值是给内部搜索算法使用的,Autocompleter会提供三个参数(row, i, max)</td>
		</tr>
		<tr>
		   <td>formatResult</td>
		   <td>Function</td>
		   <td></td>
		   <td>是定义最终返回的数据，比如我们还是要返回原始数据，而不是formatItem过的数据</td>
		</tr>
		<tr>
		   <td>blurCallback</td>
		   <td>Function</td>
		   <td></td>
		   <td>输入完成后失去焦点的回调，提供两个参数(inputValue,hiddenValue)</td>
		</tr>
	</table>
	<br/>
	<table class="cssTableBody">
		<tr>
			<th colspan="2" align="center">方法列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>init</td>
		   <td>初始化自动完成组件<br/>示例：$.hz.tabs.autocomplate("autocomplateId",options);</td>
		</tr>
	</table>
	<b style="color: blue;"></b>
</div>

<script>
	var data=[{id:"01",formatItem:"<b style='color: blue;'>苹果</b>",		formatMatch:"苹果pingguopg",		formatResult:"苹果"},
	          {id:"02",formatItem:"<b style='color: blue;'>香蕉</b>",		formatMatch:"香蕉xiangjiaoxj",	formatResult:"香蕉"},
	          {id:"03",formatItem:"<b style='color: blue;'>橙子</b>",		formatMatch:"橙子chengzicz",		formatResult:"橙子"},
	          {id:"04",formatItem:"<b style='color: blue;'>鸭梨</b>",		formatMatch:"鸭梨yaliyl",			formatResult:"鸭梨"},
	          {id:"05",formatItem:"<b style='color: blue;'>柚子</b>",		formatMatch:"柚子youziyz",		formatResult:"柚子"},
	          {id:"06",formatItem:"<b style='color: blue;'>榴莲</b>",		formatMatch:"榴莲liulianll",		formatResult:"榴莲"},
	          {id:"07",formatItem:"<b style='color: blue;'>荔枝</b>",		formatMatch:"荔枝lizhilz",		formatResult:"荔枝"},
	          {id:"08",formatItem:"<b style='color: blue;'>葡萄</b>",		formatMatch:"葡萄putaopt",		formatResult:"葡萄"},
	          {id:"09",formatItem:"<b style='color: blue;'>龙眼</b>",		formatMatch:"龙眼longyanly",		formatResult:"龙眼"},
	          {id:"10",formatItem:"<b style='color: blue;'>乌梅</b>",		formatMatch:"乌梅wumeiwm",		formatResult:"乌梅"}];
	$(document).ready(function(){
		$.hz.autocomplate.init("autocomplate1",{
			data:data,
			hiddenValue:"09",
			blurCallback:function(inputValue,hiddenValue){
// 				alert(inputValue+":"+hiddenValue);
				$("#autocomplate1_view").empty().append("inputValue:"+inputValue+"<br>hiddenValue:"+hiddenValue);
			}
		});
		$.hz.autocomplate.init("autocomplate2",{
			data:data,
			inputType:"textarea",
			multiple:true,
			multipleSeparator:";",
			hiddenValue:"01,03,09",
			width:430,
			formatItem: function(row, i, max) {
				if(i%2==0){
					return "<b style='color: green;'>"+row.formatResult+"</b>";
// 					return "<table width='400px'><tr><td align='left'>" + row.formatResult + "</td><td align='right'><font style='color: #009933; font-family: 黑体; font-style: italic'>约" + 100 + "公斤 </font>&nbsp;&nbsp;</td></tr></table>";
				}
				return row.formatItem;
			},
			formatMatch: function(row, i, max) {
				return row.formatMatch;
			},
			formatResult: function(row) {
				return row.formatResult;
			},
			blurCallback:function(inputValue,hiddenValue){
// 				alert(inputValue+":"+hiddenValue);
				$("#autocomplate2_view").empty().append("inputValue:"+inputValue+"<br>hiddenValue:"+hiddenValue);
			}
		});
		$(".box1").box1();
	});
</script>