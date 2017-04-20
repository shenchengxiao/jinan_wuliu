<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- 顶部导航 -->

		<div class="navbar-inner">
			<div class="container-fluid">

				<!-- LOGO -->

				<a class="brand animated tada" href="#" style="width:30px; display: block;margin-top:3px;">
					<!-- <img src="/images/logo.png" alt="logo" width="90" height="30"/> -->
					<i class="icon-bar-chart"></i>
				</a> <strong class="spTitle">济南后台管理</strong>

				<!-- LOGO -->

				<!-- 导航菜单 -->

				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
					<img src="${ctx}/images/menu-toggler.png" alt=""/>
				</a>

				<!-- 导航菜单结束 -->

				<!-- 顶部导航菜单开始 -->

				<ul class="nav pull-right">

					<!-- 账户下拉列表开始 -->

					<li class="dropdown user" style="margin-top: 5px;">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<!--<img alt="" src="images/avatar1_small.jpg"/>-->
							<span id="username" class="username">admin</span>&nbsp;&nbsp;&nbsp;
							<span id="role" class="username">超级管理员</span>
							<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a id="logout" href="javascript:;">
									<i class="icon-key"></i>
									注销
								</a>
							</li>
						</ul>
					</li>

					<!-- 用户下拉列表结束 -->

				</ul>

				<!-- 顶部导航结束 -->

			</div>
		</div>

		<!-- 导航条结束 -->

	</div>