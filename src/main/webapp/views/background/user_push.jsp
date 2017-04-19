<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>发布</title>

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
                            <a href="#">发布</a>
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
                                发布
                            </div>
                        </div>

                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form id="push_item_form" class="form-horizontal">
                            	<input type="hidden" name="userId" id="userId"/>
                                <div class="alert alert-error hide">
                                <button class="close" data-dismiss="alert"></button>
                                您的输入有错，请您检查后再提交!
                                </div>
                                <div class="alert alert-success hide">
                                <button class="close" data-dismiss="alert"></button>
                                恭喜您，您输入的内容完全正确！
                                </div>
                                <fieldset>
                                    <input type="hidden" name="id">
                                    <div class="control-group">
                                        <!-- <label class="control-label">用户名称<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" id="userName" name="userName"/></div> -->
                                        <label class="control-label">用户编号<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" id="userNum" name="userNum" readonly="readonly"/></div>
                                    </div>
                                    <input type="hidden" name="userPhones">
                                    <!-- <div class="control-group">
                                        <label class="control-label">用户电话<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" id="user_phones" name="userPhones" /></div>
                                        <label class="control-label">确认密码<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="passwordVerify" /></div>
                                    </div> -->
                                    <div class="control-group">
                                        <label class="control-label">信息类型<span class="required">*</span></label>
                                        <div class="span4">
                                            <select class="m-wrap span3" name="typeId" id="type_id">
                                                <option value="0">车源</option>
                                                <option value="1" selected>货源</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">信息内容<span class="required">*</span></label>
                                        <div class="span4">
                                            <textarea class="span15 m-wrap" rows="5" name="content" id="content"></textarea>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-actions" align="center">
                                        <a href="javascript:;" class="btn green" id="btn_pushItem">提交</a>
                                        <a href="user_list.jsp" class="btn grey">取消</a>
                                    </div>
                                </fieldset>
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
<script src="${pageContext.request.contextPath}/js/libs/jquery/distpicker.data.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/jquery/distpicker.js"></script>
<script src="${pageContext.request.contextPath}/js/background/user_push.js"></script>
</body>
</html>