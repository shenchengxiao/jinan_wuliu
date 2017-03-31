<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/animate.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/animate.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/ui-form.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/css/default.css" rel="stylesheet" type="text/css"/>

<body class="page-header-fixed"> 
		<!-- 头部 begin -->
		<!--@@include('../inclheader.jsphtml')-->
		<jsp:include page="/include/header.jsp"></jsp:include>
		<!-- 头部 end -->

		<!-- 页面主体 begin --> 
		<div class="page-container row-fluid">
		<!-- 左侧菜单 begin-->
			<jsp:include page="/include/left_sidebar.jsp"></jsp:include>
		<!-- 左侧菜单 end-->
			<!-- 内容区域 begin-->
			<div class="page-content">
				<div class="row-fluid" style="padding-top: 180px;">
					<p style="color:#686969;font-size:50px;text-align:center;">济南物流后台管理</p>
				</div>
			</div>
			<!-- 内容区域 end -->
		</div>
		<!-- 页面主体 end -->
		<!-- 页尾 begin -->
		<!--@@include('../inclfooter.jsphtml')-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
		<!-- 页尾 end -->
<!-- build:js scripts/index-build.js -->
<script src="${ctx}/js/libs/jquery/jquery-1.10.1.min.js"></script>
<script src="${ctx}/js/libs/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/js/app.js"></script>
<script src="${ctx}/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$('.page-sidebar-menu li').removeClass("active");

	});
</script>
<!-- endbuild -->
</body>
</html>