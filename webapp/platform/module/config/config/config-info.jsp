<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/common/jsp/taglibs.jsp"%>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="abs t5 b5 l5 r5">
	<table class="info_layout">
		<tr>
			<td class="tit fb">
				配置类型
			</td>
			<td>
				<c:if test="${configuration.kind=='0'}">用户配置</c:if>
				<c:if test="${configuration.kind=='1'}">模块配置</c:if>
				<c:if test="${configuration.kind=='2'}">全局配置</c:if>
			</td>
			<td class="tit fb">
				配置模式
			</td>
			<td>
				<c:if test="${configuration.mode=='0'}">输入</c:if>
				<c:if test="${configuration.mode=='1'}">单选</c:if>
				<c:if test="${configuration.mode=='2'}">复选</c:if>
			</td>
		</tr>

		<tr>
			<td class="tit fb">
				名称
			</td>
			<td>
				${configuration.name}
			</td>
			<td class="tit fb">
				编码
			</td>
			<td>
				${configuration.code}
			</td>
		</tr>


		<tr>
			<td class="tit fb">
				模块
			</td>
			<td>
				<c:if test="${ configuration.kind=='1'}">${configuration.module.name}</c:if>
			</td>
			<td class="tit fb">
				用户是否可见
			</td>
			<td>
				<c:if test="${configuration.visibleFlag=='0'}">不可见</c:if>
				<c:if test="${configuration.visibleFlag=='1'}">可见</c:if>
			</td>
		</tr>
		<tr>
			<td class="tit fb">
				描述
			</td>
			<td colspan="3">
				${configuration.description}
			</td>

		</tr>
	</table>
	<br />
	<br />
	<c:if test="${not empty configuration.configurationItems}">

		<table class="cssTableBody">
			<tr>
				<th class="tit">
					序号
				</th>
				<th class="tit">
					名称
				</th>
				<th class="tit">
					值
				</th>
				<th class="tit">
					是否是默认值
				</th>
				<th class="tit">
					是否禁用
				</th>
			</tr>
			<c:forEach items="${configuration.configurationItems}" var="item"
				varStatus="status">
				<tr>
					<td>
						${status.count}
					</td>
					<td>
						${item.name}
					</td>
					<td>
						${item.value}
					</td>
					<td>
						<c:if test="${item.defaultFlag!='1'}">否</c:if>
						<c:if test="${item.defaultFlag=='1'}">是</c:if>
					</td>
					<td>
						<c:if test="${item.enableFlag!='1'}">否</c:if>
						<c:if test="${item.enableFlag=='1'}">是</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
	</div>
</body>
</html>
