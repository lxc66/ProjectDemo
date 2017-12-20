<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<div class="box1 fl" boxTitle="基础JS及组件引入">
	
	<font class="green fb">include JS：</font>
	<pre class="syntax brush-html">
		&lt;%@include file="/platform/common/jsp/common-miniJs.jsp"%>
	</pre>
	
	<font class="green fb">include Component：</font>
	<pre class="syntax brush-javascript">
		$.hz.component.include("jqueryui,tree,validate,upload");
	</pre>
	
	<font class="green fb">include Component：</font>
	<pre class="syntax brush-javascript">
		 $.hz.component.include({
		 	names:"jqueryui,tree,validate,upload",
		 	sync:true //同步，解决子页面异步加载js不能正确载入依赖父页面资源问题
		 });
	</pre>

	<font class="green fb">常用API：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">参数列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>names</td>
		   <td>String</td>
		   <td></td>
		   <td>组件名称，多个组件以逗号分隔</td>
		</tr>
		<tr>
		   <td>isLoadCss</td>
		   <td>Boolean</td>
		   <td>true</td>
		   <td>就否加载组件CSS，主要配合组件的js与CSS分开加载时使用</td>
		</tr>
		<tr>
		   <td>sync</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>同步，解决子页面异步加载js不能正确载入依赖父页面资源问题</td>
		</tr>
	</table>
</div>
<!-- jqueryui tree layout ueditor validate upload progressBar page mask box datePicker accordion popupLayer -->
<div class="box1 fl" boxTitle="组件列表">
	<table class="cssTableBody">
		<tr>
		   <th width="100">编码</th>
		   <th>描述</th>
		</tr>
		<tr>
			<td>jqueryui</td>
			<td>jquery组件依赖的UI库，例如：手风琴/抽屉组件（accordion）</td>
		</tr>
		<tr>
			<td>tree</td>
			<td>树</td>
		</tr>
		<tr>
			<td>layout</td>
			<td>布局</td>
		</tr>
		<tr>
			<td>ueditor</td>
			<td>编辑器</td>
		</tr>
		<tr>
			<td>validate</td>
			<td>表单验证</td>
		</tr>
		<tr>
			<td>upload</td>
			<td>上传</td>
		</tr>
		<tr>
			<td>progressBar</td>
			<td>进度条</td>
		</tr>
		<tr>
			<td>page</td>
			<td>分页</td>
		</tr>
		<tr>
			<td>mask</td>
			<td>加载提示</td>
		</tr>
		<tr>
			<td>box</td>
			<td>伸缩盒子</td>
		</tr>
		<tr>
			<td>datePicker</td>
			<td>日期选择</td>
		</tr>
		<tr>
			<td>accordion</td>
			<td>手风琴/抽屉</td>
		</tr>
		<tr>
			<td>popupLayer</td>
			<td>弹出层</td>
		</tr>
		<tr>
			<td>tabs</td>
			<td>选项卡</td>
		</tr>
		<tr>
			<td>jqgrid</td>
			<td>数据表格</td>
		</tr>
		<tr>
			<td>autocomplete</td>
			<td>自动完成</td>
		</tr>
	</table>
</div>
<div class="box1 fl" boxTitle="配合Tomcat压缩参数，提高资源加载效率">
	
	<pre class="syntax brush-html">
			compression="on" 
			compressionMinSize="2048" 
			noCompressionUserAgents="gozilla, traviata" 
			compressableMimeType="text/html,text/xml,text/javascript,application/x-javascript,application/javascript,text/css,text/plain" 
			useBodyEncodingForURI="true" 
			URIEncoding="UTF-8"
	</pre>
</div>
<script>
$(document).ready(function(){
	$(".box1").box1();
});
</script>