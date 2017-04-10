<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>用户管理</title>
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
                            <a href="${ctx}/index.jsp">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">用户管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 用户管理
                            </div>
                            <div class="actions">
                                <a class="btn green repoActivity" id="creat_banner_icon">
                                    <i class="icon-pencil"></i> 新增用户
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="advert_list_form" method="get">
                                    <div class="span2">
                                        <input type="text" name="adName" placeholder="请输入价格" class="m-wrap span12" id="price">
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="adName" placeholder="请输入联系人" class="m-wrap span12" id="linkedName">
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="adName" placeholder="请输入联系电话" class="m-wrap span12" id="nameText_search">
                                    </div>
                                    <div class="span2">
                                        <select class="m-wrap span6" name="adType" id="adType">
                                            <option value=" ">请选择</option>
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
                                <table class="table-bordered table-striped table-condensed cf" id="banner_List">
                                    <thead class="cf">
                                    <tr>
                                        <th>用户名称</th>
                                        <th>结束时间</th>
                                        <th>价格</th>
                                        <th>联系人</th>
                                        <th>联系电话</th>
                                        <th>是否有效</th>
                                        <th>广告内容</th>
                                        <th>操作</th>
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
<script src="/js/advert/advert.js"></script>
<!-- endbuild -->
<script>
</script>
</body>

</html>
