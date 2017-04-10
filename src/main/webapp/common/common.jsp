<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title></title>

    <!-- build:css css/index-build.css -->
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/animate.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/ui-form.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/date.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/js/libs/uploadify/uploadify.css" rel="stylesheet" type="text/css"/>
    <![endif]-->
    <style>
        .table-bordered th,
        .table-bordered td {
            border-left: 1px solid #ddd;
            border-top: 1px solid #ddd;
        }
        #adTime{
            display: none;
        }
    </style>
</head>

<!-- build:js scripts/build.js -->
<script src="${ctx}/js/libs/jquery/jquery-1.10.1.min.js"></script>
<script src="${ctx}/js/libs/jquery/jquery.validate.min.js"></script>
<script src="${ctx}/js/libs/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/js/libs/jquery/ui-jqueryui.js"></script>
<script src="${ctx}/js/libs/jquery/progressbar.js"></script>
<script src="${ctx}/js/libs/jquery/toast.js"></script>
<script src="${ctx}/js/libs/jquery/jqPaginator.js"></script>
<script src="${ctx}/js/libs/laydate.dev.js"></script>
<script src="${ctx}/js/app.js"></script>
<script src="${ctx}/js/common.js"></script>

<!-- 页面访问js-->
<%--<script src="${ctx}/js/login.js"></script>--%>
<script src="${ctx}/js/admin/admin.js"></script>

<!-- endbuild -->