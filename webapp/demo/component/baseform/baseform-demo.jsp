<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script type="text/javascript">
// $.hz.component.debug=true;
// $.hz.component.include({
// 	names:"datePicker,tree,popupLayer",
// 	sync:true
// });
</script>

<div class="box1 fl" boxTitle="2、Tag使用方法">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
	    <uc:dateSelect id="123" eid="456"  name="ss" required="true" styleClass="input" dateFmt="yyyy-MM-dd"></uc:dateSelect>
		<uc:dateSelect id="456" sid="123"  maxDate="2015-3-25" name="ss" required="true" styleClass="input" dateFmt="yyyy-MM-dd"></uc:dateSelect>
		<uc:textfield name="model.name"></uc:textfield>
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		&lt;uc:textfield name="model.name">&lt;/uc:textfield>
	</pre>
	
	<font class="green fb">标签属性：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">属性列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>id</td>
		   <td>String</td>
		   <td></td>
		   <td>唯一ID</td>
		</tr>
		<tr>
		   <td>name</td>
		   <td>String</td>
		   <td></td>
		   <td>表单提交Name</td>
		</tr>
	</table>
</div>

<script>
	
	$(document).ready(function(){
		$(".box1").box1();
		$.hz.component.include("datePicker");
	});
</script>