<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<html lang="en">
<head>
    <title>案例详情</title>
    <meta name="msapplication-TileImage" content="Images/logo16.png">
    <meta http-equiv="content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="GENERATOR" content="MSHTML 11.00.9600.18125">
    <meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="renderer" content="webkit"><!-- Set render engine for 360 browser -->
    <link rel="stylesheet" href="/demo/module/app/AppCss/font-awesome.css">
    <link rel="stylesheet" href="/demo/module/app/AppCss/sj.css">
    <script type="text/javascript" src="/js/jquery-2.2.1.js"></script>
    <script src="/demo/module/app/app.js"></script>
    
<!--     <script type="text/javascript"> -->
<!-- 	$(function() { -->
<!-- 		var opts = { -->
<!-- 			lines : 9, // The number of lines to draw -->
<!-- 			length : 0, // The length of each line -->
<!-- 			width : 10, // The line thickness -->
<!-- 			radius : 15, // The radius of the inner circle -->
<!-- 			corners : 1, // Corner roundness (0..1) -->
<!-- 			rotate : 0, // The rotation offset -->
<!-- 			color : '#000', // #rgb or #rrggbb -->
<!-- 			speed : 1, // Rounds per second -->
<!-- 			trail : 60, // Afterglow percentage -->
<!-- 			shadow : false, // Whether to render a shadow -->
<!-- 			hwaccel : false, // Whether to use hardware acceleration -->
<!-- 			className : 'spinner', // The CSS class to assign to the spinner -->
<!-- 			zIndex : 2e9, // The z-index (defaults to 2000000000) -->
<!-- 			top : 'auto', // Top position relative to parent in px -->
<!-- 			left : 'auto' // Left position relative to parent in px -->
<!-- 		}; -->
<!-- 		var target = document.getElementById('foo'); -->
<!-- // 		var spinner = new Spinner(opts).spin(target); -->
<!-- 		$("#cancle").bind("click", function() { -->
<!-- 			$("#foo").css("display", "block"); -->
<!-- 			setTimeout(function() { -->
<!-- 				$("#foo").css("display", "none"); -->
<!-- 				bac.style.display = "none"; -->
<!-- 			}, 2000) -->
<!-- 		}) -->
<!-- 	}) -->
<!-- </script> -->
<!-- <style> -->
<!-- .cancle { -->
<!-- 	position: fixed; -->
<!-- 	top: 60%; -->
<!-- 	left: 20%; -->
<!-- 	height: 40px; -->
<!-- 	width: 80px; -->
<!-- 	z-index: 999 -->
<!-- } -->

<!-- .order { -->
<!-- 	position: fixed; -->
<!-- 	top: 60%; -->
<!-- 	left: 60%; -->
<!-- 	height: 40px; -->
<!-- 	width: 80px; -->
<!-- 	z-index: 999 -->
<!-- } -->
<!-- </style> -->
</head>
<body style="-webkit-overflow-scrolling: touch; -webkit-text-size-adjust: none;" id="ww">
<!-- <div id="foo" -->
<!-- 		style="width: 100px; height: 200px; margin-left: 100px; display: none; position: absolute; top: 60%; left: 30%;"></div> -->
	<input id="type" name="type" type="hidden" value="${sessionScope.usrid}">

<%-- 	<c:if test="${ss != '1'}"> --%>
<%-- 		<c:if test="${entity.ynMoney != '1'}"> --%>
<%-- 			<c:if test="${entity.money != '' }"> --%>
<!-- 				<button class="cancle">取消</button> -->
<%-- 				<button class="cancle" id="cancle" style="left: 36%" >${entity.money}/元(人民币)</button> --%>
<!-- 				<div id="bac" -->
<!-- 					style="position: absolute; top: 50%; left: 0; height: 110%; width: 100%; background-image: url(/images/111.jpg); opacity: 0.96"></div> -->
<!-- 				<script type="text/javascript"> -->
<!-- 					var cancle = document.getElementsByClassName("cancle"); -->
<!-- 					var order = document.getElementsByClassName("order"); -->
<!-- 					var bac = document.getElementById("bac"); -->
<!-- 					cancle[0].onclick = function() { -->
<!-- 						var i = $("#type").val(); -->
<!-- 						if (i != "" && i != null) { -->

<!-- 							var aa = $("#id").val(); -->

<!-- 							$.ajax({ -->
<!-- 								type : "POST", -->
<!-- 								url : "/notoken/addMdirect", -->
<!-- 								data : { -->
<!-- 									"caseId" : aa, -->
<!-- 									"directid" : i -->
<!-- 								}, -->
<!-- 								success : function(msg) { -->
<!-- 									alert("支付成功"); -->
<!-- 									cancle[0].style.display = "none"; -->
<!-- 									bac.style.display = "none"; -->
<!-- 									$("#footerMsgCon").val(""); -->

<!-- 									$("#aass").load(listUrl); -->
<!-- 								}, -->
<!-- 								error : function(msg) { -->
<!-- 									alert("支付失败"); -->
<!-- 									cancle[0].style.display = "none"; -->
<!-- 									bac.style.display = "none"; -->
<!-- 								} -->
<!-- 							}); -->

<!-- 						} else { -->
<!-- 							alert("您未登录，请登录"); -->
<!-- 							// 	$.ajax({ -->
<!-- 							// 		type: "POST", -->
<!-- 							// 	  	url: "/notoken/detailsApp", -->
<!-- 							// 	  	data:{"id":i,"add":2}, -->
<!-- 							// 	  	success: function(msg){ -->

<!-- 							// 	  	} -->

<!-- 							// 	}); -->
<!-- 							location.reload(); -->
<!-- 						} -->
<!-- 					} -->
<!-- 				</script> -->
<%-- 			</c:if> --%>
<%-- 		</c:if> --%>
<%-- 	</c:if> --%>

<div class="header">
<input type="text" name="type" id="id" style="display: none;" value="${entity.id}">
<input type="text" name="neirong" id="neirong" style="display: none;" value="${neirong}">
<%--     <h1>${entity.titleLabel}</h1> --%>
<!--     <div class="title"> -->
<%--         <div><img src="${user.userProfile}" alt=""><span>用户名：${user.userName}</span><span>类型：<span>${entity.typeView}</span></span></div> --%>
<%--         <div>发布时间：<span>${entity.releaseTime}</span></div> --%>
<!-- <!--         <div>资讯来源：<span>（凤凰国际iMarkets）</span></div> --> 
<!--     </div> -->
    
    <div class="title">
    <div><img src="${user.userprofile}" alt=""><span>${user.name}</span></div>
    </div>
    <h1>${entity.titleLabel}</h1>
    <div class="title">
<%--         <div><span style="margin-left:15px">类型：<span>${entity.typeView}</span></span></div> --%>
        <div>类型：<span style="margin-right:10px">${entity.typeView}</span></div>
    </div>
    
</div>

<div class="body">


<!--     <div class="images"> -->
<!--         <img src="img/jj.jpg"> -->
<!--     </div> -->
    <c:if test="${not empty split }"> 
    
		<div class="images" style="text-align: center;">
		<c:forEach var="m" items="${split}">
		  <c:if test="${not empty m}"> <img src="/${m}"></c:if>
		</c:forEach> 
		</div>
   
   </c:if> 
    

    <div class="cont">
        <p>
        <br>
        ${entity.contentLabel}
        </br>
        </p>
    </div>
    
    <div class="title">
    <div style="text-align:right;"><span>${entity.releaseTime}</span></div>
    </div>

    <div class="money">
        <span style="font-size: 16px">
        <span id="fa" style="font-size: 20px;" class="fa fa-thumbs-o-up" value="${entity.id}"></span>
        <span style="font-size: 16px;" class="add">${entity.clickUpNumber}</span>
            </span>
        <script type="text/javascript">
            var neirong = $("#neirong").val()

            if (neirong == 1) {
                $("#fa").removeClass("fa-thumbs-o-up");
                $("#fa").addClass("fa-thumbs-up");
            }


            $("#fa")
                .click(
                    function() {

                        // 							alert("点击");
                        if ($(this).hasClass("fa-thumbs-o-up")) {

                        	
                        	var tokens = $("#type").val();
							// 								alert(tokens);

							var aa = $(this).attr("value");
							// 								alert(aa);

							//		     	alert(aa);
							if (tokens.trim() != null && tokens.trim() != "") {

								$
										.ajax({
											type : "POST",
											url : "/notoken/clickUpNumberAl",
											data : {
												"id" : aa,
												"UserID" : tokens
											},
											success : function(msg) {
											},
											error : function(msg) {
											}
										});
                        	
                                var left = parseInt($(this).offset().left), top = parseInt($(
                                        this).offset().top) - 10, obj = $(this);
                                $(this)
                                    .after(
                                        '<div id="zhan"><b>+1<\/b><\/div>');

                                $('#zhan').css({
                                    'position' : 'absolute',
                                    'z-index' : '1',
                                    'color' : '#C30',
                                    'font-size' : '12px',
                                    'left' : left + 'px',
                                    'top' : top + 'px'
                                });
                                $('#zhan').animate({
                                    top : top - 20,
                                    opacity : 0
                                }, 1500, function() {
                                    $(this).remove();
                                });
                                var Num = parseInt($(this).siblings(
                                    '.add').text());
                                Num++;
                                $(this).siblings('.add').text(Num);
                                $(this).removeClass("fa-thumbs-o-up");

                                $(this).addClass("fa-thumbs-up");


							}else{
								alert("您未登录,请登录....");
							}
                                

                        } else {


                        	var aa = $(this).attr("value");

							var tokens = $("#type").val();

							// 										     	alert(aa);
							$.ajax({
										type : "POST",
										url : "/notoken/delclickUpNumberAl",
										data : {
											"id" : aa,
											"UserID" : tokens
										},
										success : function(msg) {
										},
										error : function(msg) {
										}
									});

                            var Num = parseInt($(this).siblings('.add')
                                .text());
                            Num--;
                            $(this).siblings('.add').text(Num);
                            $(this).removeClass("fa-thumbs-up");
                            $(this).addClass("fa-thumbs-o-up");

                        }
                    })
        </script>
<!--         <div class="dashang"> -->
<!--             <div> -->
<!--                 写的太好了<img id="dshangimg" src="/images/dshang.png">送个红包吧 -->
<!--             </div> -->
<%--             <div>收到${ids}人${moneyCount}元的打赏红包</div> --%>
<!--             <div id="dsBox" style="display: none;"> -->
<!--                 <input id="dsIpt" type="text" placeholder="打赏人民币" maxlength="5"> -->
<!--                 <button id="dsBtn" class="btn btn-xs btn-danger">打赏</button> -->
<!--             </div> -->
<!--             <script type="text/javascript"> -->
<!--             $(function() { -->

<!-- 				$("#dshangimg").click(function() { -->

<!-- 					var token1 = $("#type").val(); -->
<!-- 					if (token1 != null && token1 != "") { -->

<!-- 						$("#dsBox").css("display", "block") -->
<!-- 					} else { -->
<!-- 						alert("您未登陆，请登录"); -->
<!-- 					} -->

<!-- 				}) -->
<!-- 				$("#dsBtn") -->
<!-- 						.click( -->
<!-- 								function() { -->
<!-- 									var yanzheng = function(id) { -->
<!-- 										txt = document.getElementById(id).value; -->
<!-- 										function TestRgexp(re, s) { // 参数说明 re 为正则表达式 s 为要判断的字符 -->
<!-- 											return re.test(s) -->
<!-- 										} -->

<!-- 										var re = /^\d+(?=\.{0,1}\d+$|$)/; -->
<!-- 										//判断是否是正整数 注： 程序中正则表达式格式： /上面的正则表达式(不带双引号)/ -->
<!-- 										var result = TestRgexp(re, txt); //测试 返回true或false -->
<!-- 										if (result) { -->

<!-- 											var token1 = $("#type").val(); -->
<!-- 											var aa = $("#id").val(); -->
<!-- 											var bb = $("#dsIpt").val(); -->

<!-- 											$ -->
<!-- 													.ajax({ -->
<!-- 														type : "POST", -->
<!-- 														url : "/notoken/dashangAl", -->
<!-- 														data : { -->
<!-- 															"plice" : bb, -->
<!-- 															"caseid" : aa, -->
<!-- 															"userId" : token1 -->
<!-- 														}, -->
<!-- 														success : function(msg) { -->
<!-- 															//                             alert(msg.id.val()); -->
<!-- 															var aa = $("#id") -->
<!-- 																	.val(); -->
<!-- 															//                             $.ajax({ -->
<!-- 															//                             type: "POST", -->
<!-- 															// 						  	url: "/notoken/detailsApp", -->
<!-- 															// 						  	data:{"id":aa,"add":2,"UserID":token1}, -->
<!-- 															// 						  	success: function(){ -->

<!-- 															// 						  	} -->

<!-- 															// 						  	}); -->
<!-- 															//                 			var listUrl = "/notoken/detailsApp?id="+aa+"&add=2&UserID="+token1; -->
<!-- 															//                 			$("#dasahngid").load(msg); -->

<!-- 															//                             location.reload(); -->

<!-- 															//             		  		$("#footerMsgCon").val(""); -->
<!-- 															alert("打赏成功"); -->
<!-- 															var listUrl = "/houtai/tconsultation/detailsApp?id=" -->
<!-- 																	+ aa -->
<!-- 																	+ "&add=2&userId=" -->
<!-- 																	+ token1; -->
<!-- 															$("#ww").load( -->
<!-- 																	listUrl); -->
<!-- 														} -->
<!-- 													}); -->

<!-- 											$("#dsBox").css("display", "none") -->
<!-- 										} else { -->
<!-- 											alert("请输入正确的人民币") -->
<!-- 										} -->
<!-- 										; -->
<!-- 									} -->

<!-- 									yanzheng("dsIpt"); -->
<!-- 								}) -->
<!-- 			}) -->
<!--             </script> -->
<!--         </div> -->
    </div>

</div>

<div class="footer">
    <p class="footerTitle">
        -------<span>精选留言</span>-------
    </p>
    <p class="footerMsg">
        <a id="msg" style="display: block;">写留言</a>
    </p>
    <div id="fotpl"style="display: none;text-align: right">
        <textarea name="" id="footerMsgCon" class="footerMsgCon"></textarea>
        <button id="sub"  class="btn btn-xs btn-success">提交</button>
    </div>

    <ul id="aass" class="footpl">


    </ul>



        
</div>
<div style="height: 49px;">

</div>
<script type="text/javascript">
		var aa = $("#id").val();
		var userid = $("#type").val();
		var listUrl = "/houtai/tconsultation/tCaseAppNew?caseid=" + aa+"&UserID="+userid;
		$("#aass").load(listUrl);

		var cancle = document.getElementsByClassName("cancle");
		var order = document.getElementsByClassName("order");
		var bac = document.getElementById("bac");
		
		var fotpl = document.getElementById("fotpl");
	    var msg = document.getElementById("msg");
	    var sub = document.getElementById("sub");
		 msg.onclick = function() {
		        fotpl.style.display = "block";
		        msg.style.display = "none";
		    }
		sub.onclick = function() {
			fotpl.style.display = "none";
	        msg.style.display = "block";

	        var texts = $("#footerMsgCon").val();
	        var token = $("#type").val();

			if (texts.trim() != null && texts.trim() != "") {
				var aa = $("#id").val();
				var texts = $("#footerMsgCon").val();
				var userid = $("#type").val();
				var listUrl = "/houtai/tconsultation/tCaseAppNew?caseid=" + aa+"&UserID="+userid;

				if (token.trim() != null && token.trim() != "") {
					$.ajax({
						type : "POST",
						url : "/houtai/Tcase/addTcaseT",
						data : {
							"texts" : texts,
							"caseId" : aa,
							"UserID" : token
						},
						success : function() {

							$("#footerMsgCon").val("");

							$("#aass").load(listUrl);
						}
					});
				} else {
					alert("您未登录，请先登录....");
				}

			} else {
				alert("您未发表任何评论");
			}
		}
	</script>
</body>
</html>