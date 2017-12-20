<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<div class="box1 fl" boxTitle="选项卡使用方法">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-left: 10px;margin-right: 10px;">
<!-- 		<div class="tabs-container" id="tabs1"></div> -->
		<div id="tabs1" style="width:700px; height: 500px;">
			<div title="页签1" uid="uid_1" closable="true">
				<c:forEach begin="1" end="20" step="1" var="index">
					页签1的内容${index}<br><br>
				</c:forEach>
			</div>
			<div title="页签2" uid="uid_2" selected="true">页签2</div>	
			<div title="页签3" uid="uid_3">页签3</div>	
			<div title="页签4" uid="uid_4">页签4</div>	
		</div>
		<br>
		<a href="javascript:;" onclick="tabAdd();">增加</a>
		<a href="javascript:;" onclick="tabClose();">关闭</a>
		<a href="javascript:;" onclick="tabSelectWithTitle('页签3');">选中页签3</a>
		<a href="javascript:;" onclick="tabSelectWithUid('uid_4');">选中页签4</a>
		<a href="javascript:;" onclick="tabUpdate();">更新标题</a>
<!-- 		<a href="javascript:;" onclick="resize();">调整大小</a> -->
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		<div id="tabs1" style="width:700px; height: 500px;">
			<div title="页签1" uid="uid_1" closable="true">
				页签1的内容1
					……	……
			</div>
			<div title="页签2" uid="uid_2" selected="true">页签2</div>	
			<div title="页签3" uid="uid_3">页签2</div>	
			<div title="页签4" uid="uid_4">页签4</div>	
		</div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$.hz.tabs.init("tabs1",{
			onSelect:function(title,uid){
			},
			onClose:function(uid){
				return true;
			}
		});
	</pre>
	
	<font class="green fb">常用API：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">选项卡内容DIV属性列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>uid</td>
		   <td>String</td>
		   <td></td>
		   <td>一组选项卡中每个选项卡的唯一标识</td>
		</tr>
		<tr>
		   <td>closable</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>选项卡是否显示关闭按钮</td>
		</tr>
		<tr>
		   <td>selected</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>选项卡是否默认选中</td>
		</tr>
	</table>
	<br/>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">初始化参数列表(options)</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>onClose</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		选项卡关闭前调用此方法<br>
		   		示例：$.hz.tabs.init("tabId",{
					onClose:function(uid){
						return true;
					}
				});
				<br> 如果返回false则选项卡不会关闭
		   </td>
		</tr>
		<tr>
		   <td>onSelect</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		选项卡选中后回调方法<br>
		   		示例：$.hz.tabs.init("tabId",{
					onSelect:function(title,uid){
					}
				});
			</td>
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
		   <td>初始化选项卡组件<br/>示例：$.hz.tabs.init("tabId",options);</td>
		</tr>
		<tr>
		   <td>add</td>
		   <td>增加一个选项卡<br/>
		   	示例：$.hz.tabs.add("tabId",{
				uid:"uid",
				title: '标题',
				content: '内容',
				closable: false,
				selected: true
			});
		</td>
		</tr>
		<tr>
		   <td>update</td>
		   <td>更新选项卡<br/>
			示例：$.hz.tabs.update("tabId",{
				uid:"uid",
				title:'更新标题',
				content:'更新内容'
			});
		   </td>
		</tr>
		<tr>
		   <td>close</td>
		   <td>关闭选项卡<br/>示例：$.hz.tabs.selectWithUid("tabId","uid");</td>
		</tr>
		<tr>
		   <td>selectWithTitle</td>
		   <td>根据标签选中<br/>示例：$.hz.tabs.selectWithTitle("tabId","title");</td>
		</tr>
		<tr>
		   <td>selectWithUid</td>
		   <td>根据uid选中<br/>示例：$.hz.tabs.selectWithUid("tabId","uid");</td>
		</tr>
	</table>
</div>

<script>
	var addIndex = 0;
	function tabAdd(){
		var index = ++addIndex;
		$.hz.tabs.add("tabs1",{
			uid:"uid"+index,
			title: '新增页签'+index,
			content: '新增页签内容'+index,
			closable: false,
			selected: true
		});
	}
	
	function tabClose(){
		if(addIndex==0)return;
		var uid = "uid"+addIndex--;
		$.hz.tabs.close("tabs1",uid);
		$.hz.tabs.selectWithUid("tabs1","uid"+addIndex);
	}
	
	function tabSelectWithTitle(title){
		$.hz.tabs.selectWithTitle("tabs1",title);
	}
	
	function tabSelectWithUid(uid){
		$.hz.tabs.selectWithUid("tabs1",uid);
	}
	
	function tabUpdate(){
		$.hz.tabs.update("tabs1",{
			uid:"uid_4",
			title: '页签4（更新标题）',
			content: "页签4（更新内容）"
		});
	}
	
	function resize(){
		$.hz.tabs.resize("tabs1");
	}
	
	$(document).ready(function(){
		$.hz.tabs.init("tabs1",{
			onSelect:function(title,uid){
// 				alert(" title:"+title+" uid:"+uid);
			},
			onClose:function(uid){
				alert(" uid:"+uid);
				return true;
			}
		});
// 		$('#tabs1').tabs();
		$(".box1").box1();
	});
</script>