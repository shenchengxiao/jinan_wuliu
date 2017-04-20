<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>在线用户</title>
    <!-- build:css css/main.css -->
    <jsp:include page="/common/common.jsp"></jsp:include>
    <![endif]-->
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
                            <a href="#">在线用户</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 在线用户
                            </div>
                            <div class="actions">

                                <a href="javascript:;" class="btn green" id="btn_chooseAll">
                                    全选
                                    <i class="icon-down"></i>
                                </a>&nbsp;&nbsp;

                                <a  href="javascript:;" class="btn green repoActivity" id="btn_kick_out">
                                    踢出用户
                                </a>
                                <input type="hidden" name="" value="" id="userIds">
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="user_list_form" method="get">
                                    <div class="span2">
                                        <input type="text" name="userName" placeholder="请输入用户名称" class="m-wrap span12">
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="userNum" placeholder="请输入用户编号" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="phoneNumber" placeholder="请输入联系电话" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <select class="m-wrap span6" name="isUsed" >
                                            <option value="">请选择</option>
                                            <option value="0">无效</option>
                                            <option value="1">有效</option>
                                        </select>
                                    </div>
                                    <div class="span2">
                                        <button type="button" class="btn blue mgleft10" id="btn_search">查找</button>
                                    </div>
                                    <input type="hidden" name="pageNum" id="pageNum" value="1">
                                </form>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="user_manage_list">
                                    <thead class="cf">
                                    <tr>
                                        <th></th>
                                        <th>用户名称</th>
                                        <th>用户编号</th>
                                        <th>用户密码</th>
                                        <th>联系电话</th>
                                        <th>省份</th>
                                        <th>城市</th>
                                        <th>县区</th>
                                        <th>服务开始时间</th>
                                        <th>服务结束时间</th>
                                        <th>硬盘号</th>
                                        <th>网卡号</th>
                                        <th>平台</th>
                                        <th>状态</th>
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
    <!-- 内容区域 end -->
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<!-- build:js scripts/build.js -->
<!-- endbuild -->
<script src="${pageContext.request.contextPath}/js/background/online_user.js"></script>

</body>

</html>
