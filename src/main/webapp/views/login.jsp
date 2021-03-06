<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title></title>
	<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/css/animate.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/css/default.css" rel="stylesheet" type="text/css"/>
</head>

<!-- BEGIN BODY -->

<body class="login">
<!-- canvas begin -->
<canvas id="Mycanvas"></canvas>
<!-- canvas end-->
<!-- BEGIN LOGO -->

<div class="logo animated bounceInLeft">
	<!-- <img src="../images/kouliang.png"> -->
	<p style="color:#686969;font-size:50px;text-align:center;">济南物流管理
	</p>
</div>

<!-- END LOGO -->

<!-- BEGIN LOGIN -->

<div class="content animated bounceInRight">
	<!-- BEGIN LOGIN FORM -->
	<form class="form-vertical login-form" id="form_login">
		<!-- <h3 class="form-title">登录你的账号</h3> -->
		<div class="control-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<div class="controls">
				<div class="input-icon left">
					<i class="icon-user"></i>
					<input id="UserName" class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="userName"/>
				</div>
			</div>
		</div>
		<div class="control-group normal">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="controls">
				<div class="input-icon left">
					<i class="icon-lock"></i>
					<input id="UserPass" class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="passwd"/>
				</div>
			</div>
		</div>
		<div class="control-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">验证码</label>
			<div class="controls">
				<div class="input-icon left">
					<img id="imgObj"  alt="" src="${ctx}/api/verify/code"/>
					<a href="#" id="changeImage">换一张</a>
					<input id="veryCode" class="m-wrap placeholder-no-fix" name="verifyCode" placeholder="请输入验证码" type="text"/>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<button id="btn_login" class="btn green btn-block">登录
				<i class="icon-user"></i>
			</button>
		</div>

	</form>

	<!-- END LOGIN FORM -->

</div>

<!-- END LOGIN -->

<!-- BEGIN COPYRIGHT -->

<div class="copyright animated bounceInUp">

	2017 &copy; Design by shencx

</div>
<script src="${ctx}/js/libs/jquery/jquery-1.10.1.min.js"></script>
<script src="${ctx}/js/libs/jquery/jquery.validate.min.js"></script>
<script src="${ctx}/js/libs/jquery/progressbar.js"></script>
<script src="${ctx}/js/libs/jquery/toast.js"></script>
<script src="${ctx}/js/libs/bootstrap/bootstrap.min.js"></script>
<!--<script src="/js/app.js"></script>-->
<script src="${ctx}/js/login.js"></script>

</body>

<!-- END BODY -->

</html>