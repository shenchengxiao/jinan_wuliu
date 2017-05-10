<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>用户登录日志</title>
    <!-- build:css css/main.css -->
    <%@ include file="/common/taglibs.jsp"%>
    <jsp:include page="/common/common.jsp"></jsp:include>

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
                            <a href="#">用户登录日志</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 登录日志列表
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="ipvisit_list_form" method="get">
                                    <div class="span4" align="right">
                                            <div class="controls">
                                                <a href="javascript:;" class="btn green" id="btn_chooseAll">
                                                                                                                                                                              全选
                                                    <i class="icon-down"></i>
                                                </a>&nbsp;&nbsp;
                                                <a href="javascript:;" class="btn red" id="btn_remove">
                                                                                                                                                                              删除
                                                    <i class="icon-down"></i>
                                                </a>&nbsp;&nbsp;
                                                <input type="hidden" name="" value="" id="ids">
                                            </div>
                                        </div>
                                    <input type="hidden" name="pageNum" id="pageNum" value="1">
                                </form>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="ipvisit_List">
                                    <thead class="cf">
                                    <tr>
                                        <th></th>
                                        <th>用户编号</th>
                                        <th>用户ip</th>
                                        <th>用户端口</th>
                                        <th>登录时间</th>
                                        <th>登录地点</th>
                                        <th>登录类型</th>
                                        <th>硬件信息</th>
                                    </tr>
                                    </thead>
                                    <tbody style="text-align: center;">
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
    <!-- 内容区域 end -->
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<!-- build:js scripts/build.js -->
<!-- endbuild -->
<script src="${pageContext.request.contextPath}/js/log/login_log.js"></script>
</body>
</html>
