<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>广告banner</title>
    <!-- build:css css/main.css -->
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
                            <a href="#">Banner管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> Banner管理
                            </div>
                            <div class="actions">
                                <a class="btn green repoActivity" id="creat_banner_icon">
                                    <i class="icon-pencil"></i> 创建Banner
                                </a>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="banner_List">
                                    <thead class="cf">
                                    <tr>
                                        <th>Banner名称</th>
                                        <th>Banner图片</th>
                                        <th>状态</th>
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
    <!-- modal 添加 begin -->
    <form action="#" class="form-horizontal" id="add_banner_form">
        <div class="modal fade hide" id="addBannerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">新增Banner</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id">
                        <div class="control-group">
                            <label class="control-label">Banner名称:<span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap name_text_adName" name="bannerName" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">上传Banner图</label>
                            <div class="controls">

                                <input id="txtUrl" type="text" name="imageUrl" class="span6 m-wrap" readonly />
                                <input class="uploadify" type="file" name="imageFile" id="file" >

                                <div>
                                    <img id="logoImg" width="120px" height="120px"/>
                                </div>
                            </div>
                        </div>

                        <!--开始时间-->
                        <div class="control-group">
                            <label class="control-label">开始日期：<span class="required">*</span></label>
                            <div class="controls">
                                <div class="input-append date">
                                    <input data-laydate="start" class="span9 m-wrap input-small start-time mh_date" type="text" value="" name="startTime" id="startTime" readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <!--结束时间-->
                        <div class="control-group">
                            <label class="control-label">结束日期：<span class="required">*</span></label>
                            <div class="controls">
                                <div class="input-append date">
                                    <input data-laydate="end" class="span9 m-wrap input-small end-time mh_date" type="text" value="" name="endTime" id="endTime" readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">链接地址:</label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap name_text_adName" name="linkUrl" id="linkUrl">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary green" id="btn_add_banner">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!-- modal 添加 end -->
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<script src="${pageContext.request.contextPath}/js/libs/uploadify/jquery.uploadify.min.js"></script>
<script src="${pageContext.request.contextPath}/js/advert/banner.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/jquery/ajaxfileupload.js"></script>
<!-- 图片放大 -->
<script src="${pageContext.request.contextPath}/js/libs/jquery/zooming.js"></script>


</body>

</html>
