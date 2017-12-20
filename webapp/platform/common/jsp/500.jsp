<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>服务器内部错误</title>
	    <link rel="stylesheet" href="${path}/js/lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${path}/css/font-awesome.css?v=4.4.0">
    	<link rel="stylesheet" href="${path}/css/animate.css">
    	<link rel="stylesheet" href="${path}/css/style.css">
    	<style type="text/css">
			.error-box {
			    max-width: 800px;
			    z-index: 100;
			    margin: 0 auto;
			    padding-top: 40px;
			}
    		.img-500{
    			width: 100%; 
    			height: auto;
    			max-width: 100%;
    			display: block;
    		}
			.middle-box {
			    max-width: 400px;
			    z-index: 100;
			    margin: 0 auto;
 			    padding-top: 0px;
			}
    	</style>
	</head>
	<body>
    <div class="middle-box text-center animated fadeInDown">
		<img src="${path}/images/500.jpg" class="img-500"/>
        <h2 class="font-bold">服务器好像出错了...</h3>
        <div class="error-desc">
            	
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
