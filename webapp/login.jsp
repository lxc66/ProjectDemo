<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
<head>
    <title>登录</title>
<!--     <link rel="icon" href="/favicon.ico"> -->
    <link rel="icon" href="images/huiwenTitle.png">
    <link rel="stylesheet" href="${path}/js/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/css/font-awesome.css?v=4.4.0">
    <link rel="stylesheet" href="${path}/css/animate.css">
    <link rel="stylesheet" href="${path}/css/style.css">
<!--     <link rel="icon" type="image/png" href="/images/huiwen.png"> -->
<!--     <link rel="icon" sizes="192x192" href="/images/huiwen.png"> -->
<!--     <link rel="apple-touch-icon-precomposed" href="/images/huiwen.png"> -->
</head>
<body>

	<div class="error"></div>
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name"><img src="images/huiwen.png" alt=""></h1>

            </div>
			<form action="" method="post" class="m-t" role="form">
                <div class="form-group">
                    <input id ="name" type="text" class="form-control" placeholder="用户名" required="" name="username" value="">
                </div>
                <div class="form-group">
                    <input id="pas" type="password" class="form-control" placeholder="密码" required="" name="password" value="">
                </div> 
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-danger text-center" id="tishi">
                	${loginError}
                </p>
			</form>
        </div>
    </div>

	<script src="${path}/js/lib/jquery/jquery.js"></script>
    <script src="${path}/js/lib/bootstrap/bootstrap.min.js"></script>
</body>
<script type="text/javascript">

				$("#name").blur(function(){
				var name=$("#name").val().trim();
				$.ajax({
					type: "POST",
					url:"/sys/user/ajaxCheckLoginName",
					data:{"loginName":name},
					processData:true,
				    success:function(data){
				    	if(name==null||name==""){
				    		$('#tishi').html('');
				    		return;
				    	}
				    	if(data=="true"){
				    		$('#tishi').html('用户名错误！');
// 				    		alert("用户名错误");		
//				    		$("#name").val("");
// 				    		$("#name").attr("value","");
                            $("#pas").val("");


				    	}
				        else{	  
				    	   $('#tishi').html('');	
				    	   return;
			    	    }
				    }
				});
				});
				
				
</script>
</html>