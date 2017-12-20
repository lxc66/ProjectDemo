<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>页面未找到</title>
	    <link rel="stylesheet" href="${path}/js/lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${path}/css/font-awesome.css?v=4.4.0">
    	<link rel="stylesheet" href="${path}/css/animate.css">
    	<link rel="stylesheet" href="${path}/css/style.css">
    	<style type="text/css">
    		.img-404{
    			width: 100%; 
    			height: auto;
    			max-width: 100%; 
    			display: block;
    		}
    	</style>
	</head>
	<body>
    <div class="middle-box text-center animated fadeInDown">
		<img src="${path}/images/404.gif" class="img-404"/>
        <h2 class="font-bold">页面未找到！</h2>

        <div class="error-desc">
            	抱歉，页面好像去火星了~
<!--             <form class="form-inline m-t" role="form"> -->
<!--                 <div class="form-group"> -->
<!--                     <input type="email" class="form-control" placeholder="请输入您需要查找的内容 …"> -->
<!--                 </div> -->
<!--                 <button type="submit" class="btn btn-primary">搜索</button> -->
<!--             </form> -->
        </div>
    </div>
	</body>
</html>
