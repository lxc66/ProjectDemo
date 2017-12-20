<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>问答详情</title>
<link rel="stylesheet" href="/demo/module/app/AppCss/sj.css">
<link rel="stylesheet" href="/demo/module/app/AppCss/font-awesome.css">
<script type="text/javascript" src="/js/jquery-2.2.1.js"></script>
<script src="/demo/module/app/app.js"></script>
</head>

<body id="aabb">

	<input id="type" name="type" type="hidden" value="${sessionScope.usrid}">
	<input type="text" name="type" id="id" style="display: none;" value="${wenda.problemid}">


	<c:forEach var="m" items="${list}" varStatus="mm">


		<li>
			<p class="username" style=" margin-top: 30px;">
				<img class="tx" src="${m.test}" alt="">${m.answerName}</p>
			<p>${m.platformTitle}</p>
			<p class="time">
				${m.problemTime}
				<span class="pldj">
				<c:if test="${m.test2 == '1'}">
<!-- 			<span class="pldj"> -->
				<span id="" class="fa fa-thumbs-up fa2" value="${m.responseId}"></span>
<!-- 			</span>	 -->
				</c:if>
				
				<c:if test="${m.test2 != '1'}">
				
				<span id="" class="fa fa-thumbs-o-up fa3" value="${m.responseId}"></span>
				</c:if>
<!-- 				<span class="pldj"> -->
				<span class="add">${m.dianzan}</span>
			    </span>
				<%--    ${m.tcomment}<span class="floor"> --%>
			</p>
			<p>${m.answerDescription}</p>
		</li>
		
<!-- 		<br> -->

	</c:forEach>
	<!-- 		</ul> -->


</body>
<script type="text/javascript">


$(".fa2")
.click(
		function() {

			if ($(this).hasClass("fa-thumbs-o-up")) {

				var tokens = $("#type").val();
				//     	alert(tokens);

				var aa = $(this).attr("value");

				//     	alert(aa);
				if (tokens.trim() != null && tokens.trim() != "") {
					$.ajax({
						type : "POST",
						url : "/notoken/dianZanWenda",
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
							'<div class="zhan"><b>+1<\/b></\div>');
					$('.zhan').css({
						'position' : 'absolute',
						'z-index' : '1',
						'color' : '#C30',
						'font-size' : '12px',
						'left' : left + 'px',
						'top' : top + 'px'
					});
					$('.zhan').animate({
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

				//     	alert(aa);
				$.ajax({
					type : "POST",
					url : "/notoken/deleteDianZanWenda",
					data : {
						"id" : aa,
						"userId" : tokens
					},
					success : function(msg) {
					},
					error : function(msg) {
					}
				});

				var Num = parseInt($(this).siblings('.add').text());
				Num--;
				$(this).siblings('.add').text(Num);
				$(this).removeClass("fa-thumbs-up");
				$(this).addClass("fa-thumbs-o-up");
			}
		})
		
		
		$(".fa3")
.click(
		function() {

			if ($(this).hasClass("fa-thumbs-o-up")) {

				var tokens = $("#type").val();
				//     	alert(tokens);

				var aa = $(this).attr("value");

				//     	alert(aa);
				if (tokens.trim() != null && tokens.trim() != "") {
					$.ajax({
						type : "POST",
						url : "/notoken/dianZanWenda",
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
							'<div class="zhan"><b>+1<\/b></\div>');
					$('.zhan').css({
						'position' : 'absolute',
						'z-index' : '1',
						'color' : '#C30',
						'font-size' : '12px',
						'left' : left + 'px',
						'top' : top + 'px'
					});
					$('.zhan').animate({
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

				//     	alert(aa);
				$.ajax({
					type : "POST",
					url : "/notoken/deleteDianZanWenda",
					data : {
						"id" : aa,
						"userId" : tokens
					},
					success : function(msg) {
					},
					error : function(msg) {
					}
				});

				var Num = parseInt($(this).siblings('.add').text());
				Num--;
				$(this).siblings('.add').text(Num);
				$(this).removeClass("fa-thumbs-up");
				$(this).addClass("fa-thumbs-o-up");
			}
		})
</script>
</html>