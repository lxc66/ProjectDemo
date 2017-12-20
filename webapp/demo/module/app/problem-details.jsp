<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
	
<html>
<head>
    <title>问答详情</title>
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
</head>
<body style="-webkit-overflow-scrolling: touch; -webkit-text-size-adjust: none;" id="ww">


	<input id="type" name="type" type="hidden" value="${sessionScope.usrid}">
	<input type="text" name="type" id="id" style="display: none;" value="${wenda.problemid}">	
    <input type="text" name="neirong" id="neirong" style="display: none;" value="${neirong}">

<div class="header">
<%--    <c:if test="${wenda.questionTitle != ''}"> --%>
<%-- 		<c:if test="${wenda.questionTitle != null}"> --%>
<%--     <h1>${wenda.questionTitle}</h1> --%>
<%-- 	     </c:if> --%>
<%--    </c:if>     --%>
<!--     <div class="title"> -->
<%--         <div><img src="${user.userProfile}" alt=""><span>用户名：${user.userName}</span></div> --%>
<!--     </div> -->
    
     <div class="title">
    <div><img src="${user.userprofile}" alt=""><span>${user.name}</span></div>
    </div>
    
     <c:if test="${wenda.questionTitle != ''}">
		<c:if test="${wenda.questionTitle != null}">
    <h1>${wenda.questionTitle}</h1>
	     </c:if>
   </c:if>   
    
<%--     <h1>${entity.titleLabel}</h1> --%>
<!--     <div class="title"> -->
<%--         <div><span style="margin-left:15px">类型：<span>${entity.typeView}</span></span></div> --%>
<%--         <div>资讯来源：<span style="margin-right:10px">${entity.csource}</span><span style="margin-left:15px;float: right;">类型：<span>${entity.typeView}</span></span></div> --%>
<!--     </div> -->
    
</div>

<div class="body">

  <c:if test="${not empty list }"> 
    
		<div class="images" style="text-align: center;">
		<c:forEach var="m" items="${list}">
		  <c:if test="${not empty m}"> <img src="/${m}"></c:if>
		</c:forEach> 
		</div>
   
 </c:if> 


    <div class="cont">
        <p>
        <br>
        ${wenda.contentLabel}
        </br>
        </p>
    </div>
    
    <div class="title">
    <div style="text-align:right;"><span>${wenda.releaseTime}</span></div>
    </div>

    <div class="money">
        <span style="font-size: 16px">
        <span id="fa" style="font-size: 20px;" class="fa fa-thumbs-o-up" value="${wenda.problemid}"></span>
        <span style="font-size: 16px;" class="add">${wenda.clickUpNumber}</span>
            </span>
        <script type="text/javascript">

    	var neirong = $("#neirong").val();

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
    								if (tokens.trim() != null
    										&& tokens.trim() != "") {

    									$.ajax({
    										type : "POST",
    										url : "/notoken/clickUpWenda",
    										data : {
    											"id" : aa,
    											"userId" : tokens
    										},
    										success : function(msg) {
    										},
    										error : function(msg) {
    										}
    									});

    									var left = parseInt($(this).offset().left), top = parseInt($(
    											this).offset().top) - 10, obj = $(this);
    									$(this).after(
    											'<div id="zhan"><b>+1<\/b></\div>');

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
    									var Num = parseInt($(this).siblings('.add')
    											.text());
    									Num++;
    									$(this).siblings('.add').text(Num);
    									$(this).removeClass("fa-thumbs-o-up");

    									$(this).addClass("fa-thumbs-up");

    								} else {
    									alert("您未登录,请登录....");
    								}

    							} else {

    								var aa = $(this).attr("value");

    								var tokens = $("#type").val();

    								// 										     	alert(aa);
    								$.ajax({
    									type : "POST",
    									url : "/notoken/deleteclickUpWenda",
    									data : {
    										"id" : aa,
    										"userId" : tokens
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
<%--             <div>收到${ids}人${mType}元的打赏红包</div> --%>
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
<!-- 														url : "/notoken/dashangWenDa", -->
<!-- 														data : { -->
<!-- 															"plice" : bb, -->
<!-- 															"problemid" : aa, -->
<!-- 															"userId" : token1 -->
<!-- 														}, -->
<!-- 														success : function() { -->

<!-- 															var aa = $("#id") -->
<!-- 																	.val(); -->

<!-- 															var listUrl = "/houtai/tconsultation/detailsApp?id=" -->
<!-- 																	+ aa -->
<!-- 																	+ "&add=3&userId=" -->
<!-- 																	+ token1; -->
<!-- 															$("#ww").load( -->
<!-- 																	listUrl); -->
<!-- 															alert("打赏成功"); -->
<!-- 															//                 			location.reload(); -->

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
    <div id="fotpl" style="display: none;text-align: right">
        <textarea name="" id="footerMsgCon" class="footerMsgCon"></textarea>
        <button id="sub"  class="btn btn-xs btn-success" style="text-align: right;display: block;float: right;">提交</button>
    </div>

    <ul id="aass" class="footpl">

<!--         <li> -->
<!--             <p class="username"> -->
<!--                 <img class="tx" src="http://123.206.94.48:8080/upload/timg1.jpg" alt="">话好说</p> -->
<!--             <p></p> -->
<!--             <p class="time"> -->
<!--                 2017-04-14 10:42:04 -->
<!--                 <span class="pldj"> -->
<!--                     <span id="" class="fa fa-thumbs-up fa2" value="265"></span> -->
<!--                     <span class="add">1</span> -->
<!--                 </span> -->
<!--             </p> -->
<!--             <p>5434</p> -->
<!--         </li> -->


        <!-- 		</ul> -->

    </ul>
</div>
<div style="height: 49px;">

</div>
<script type="text/javascript">
var aa = $("#id").val();
var userid = $("#type").val();
var listUrl = "/houtai/tconsultation/queryAnswer?problemId=" + aa+"&UserID="+userid;
$("#aass").load(listUrl);
/**
 * Created by admin on 2017/2/27.
 */
var footerMsgCon = document.getElementById("footerMsgCon");
var msg = document.getElementById("msg");
var sub = document.getElementById("sub");

var fotpl = document.getElementById("fotpl");
msg.onclick = function() {
	fotpl.style.display = "block";
	footerMsgCon.style.display = "block";
	sub.style.display = "block";
	msg.style.display = "none";
}
sub.onclick = function() {
	footerMsgCon.style.display = "none";
	sub.style.display = "none";
	msg.style.display = "block";

	var texts = $("#footerMsgCon").val();
	var token = $("#type").val();
	// 			alert(token);
	// 			alert(55);

	if (texts.trim() != null && texts.trim() != "") {

		var aa = $("#id").val();
		var texts = $("#footerMsgCon").val();
		var userid = $("#type").val();
		var listUrl = "/houtai/tconsultation/queryAnswer?problemId=" + aa+"&UserID="+userid;

		if (token.trim() != null && token.trim() != "") {
			$.ajax({
				type : "POST",
				url : "/notoken/answer",
				data : {
					"answerDescription" : texts,
					"problemId" : aa,
					"UserID" : token
				},
				success : function(message) {
					alert(message);
					$("#footerMsgCon").val("");
					$("#aass").load(listUrl);

				},
				error : function(message) {
					alert(message);
				}
			});
		} else {
			alert("您未登录,请登录....");
		}

	} else {
		alert("您未发表任何评论");
	}

}
</script>
</body>
</html>


