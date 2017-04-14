<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>添加版本</title>

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

    <!-- 内容区域 begin-->
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
                            <a href="#">新增版本</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey" id="form_wizard_1">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i>
                                新增版本
                            </div>
                        </div>

                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form class="form-horizontal" id="add_upgrade_form">
                                    <div class="modal-content">
                                            <div class="control-group">
                                                <label class="control-label">名称: <span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" class="span6 m-wrap" name="clientName" >
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">版本号: <span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" class="span6 m-wrap" name="version" >
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">上传下载包: <span class="required">*</span></label>
                                                <div class="controls">

                                                    <input id="txtUrl" type="text" name="packageUrl" class="span6 m-wrap" readonly />
                                                    <input class="uploadify" type="file" id="file" >

                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">升级内容描述: </label>
                                                <div class="controls">
                                                    <textarea class="span6 m-wrap" rows="6" id="upgradeDesc" name="upgradeDesc"></textarea>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">平台类型: </label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" name="platformType" value="0" checked="checked">
                                                        PC
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="platformType" value="2">
                                                        Android
                                                    </label>
                                                </div>
                                            </div>
                                        <div class="form-actions">
                                            <a href="upgrade_list.jsp" class="btn grey">取消</a>
                                            <button type="button" class="btn btn-primary green" id="btn_add_upgrade">提交</button>
                                        </div>
                                </div>
                            </form>
                            <!-- END FORM-->
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
<script src="${pageContext.request.contextPath}/js/clientupgrade/upgrade_add.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/uploadify/jquery.uploadify.min.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/jquery/ajaxfileupload.js"></script>
</body>
</html>