<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta charset="utf-8" />
<script src="${path }/platform/common/js/common-mini.js"></script>
<script type="text/javascript">
	//注册产品全局变量方法集
	Namespace.register('com.ue.global');
	com.ue.global = {
		path : '<%=path%>',
		basePath : '<%=basePath%>',
		jsessionid : '${pageContext.session.id}',//sessionId用于flash中提交文件无法使用当前session
		theme : 'default',
		productTitle:''
	}
</script>
<script src="${path }/platform/common/js/component.load.js"></script>
