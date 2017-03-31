<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/common/common.jsp"></jsp:include>

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
<%--<script src="/js/libs/jquery/jquery-1.10.1.min.js"></script>--%>
<%--<script src="/js/libs/bootstrap/bootstrap.min.js"></script>--%>
<%--<script src="/js/app.js"></script>--%>
<%--<script src="/js/common.js"></script>--%>
<script type="text/javascript">
	$(function() {
		$('.page-sidebar-menu li').removeClass("active");

	});
</script>
<!-- endbuild -->
</body>
</html>