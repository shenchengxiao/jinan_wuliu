<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>查询日志</title>
    <!-- build:css css/main.css -->
    <%@ include file="/common/taglibs.jsp"%>
    <jsp:include page="/common/common.jsp"></jsp:include>

    <style>
        #query_list td{
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>

<body class="page-header-fixed">
<!-- 头部 begin -->
<jsp:include page="/include/header.jsp"></jsp:include>
<!-- 头部 end -->
<!-- 页面主体 begin -->
<div class="page-container row-fluid">
    <!-- 左侧菜单 begin-->
     <jsp:include page="/include/left_sidebar.jsp"></jsp:include>
    <!-- 左侧菜单 end-->
    <!-- 内容区域 begin -->
    <div class="page-content">
        <div class="container-fluid ">
            <div class="row-fluid">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="../index.jsp">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">查询日志</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 查询日志
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="query_list_form" method="get">
                                    <div class="span2">
                                        <input type="text" name="userName" placeholder="请输入用户名称" class="m-wrap span12">
                                    </div>
                                    <div class="span2">
                                        <div class="input-append date">
                                            <input data-laydate="appointedDay" name="appointedDay" class="m-wrap input-small start-time mh_date"
                                                   type="text"
                                                   value=""/><span class="add-on"><i class="icon-calendar"></i></span>
                                        </div>
                                    </div>
                                    <div class="span2">
                                        <button type="button" class="btn blue mgleft10" id="btn_search_query">查找</button>
                                    </div>
                                    <input type="hidden" name="pageNum" id="pageNum" value="1">
                                </form>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="query_list">
                                    <thead class="cf">
                                    <tr>
                                        <th>用户名称</th>
                                        <th>搜索内容</th>
                                        <th>查询时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                                <div class="row-fluid">
                                    <div class="span12">
                                        <div class="pull-right pagination">
                                            <ul id="pagination"></ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<!-- build:js scripts/build.js -->
<!-- endbuild -->
<script src="${pageContext.request.contextPath}/js/log/search_item_log.js"></script>
</body>
</html>
