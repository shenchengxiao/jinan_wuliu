<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>系统消息</title>
    <!-- build:css css/main.css -->
    <jsp:include page="/common/common.jsp"></jsp:include>
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
                            <a href="#">系统消息</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 发送系统消息
                            </div>
                        </div>
                        <form class="form-horizontal" id="send_sys_form">
					            <div class="modal-dialog">
					                <div class="modal-content">
					                    <div class="modal-body">
					                        <div class="control-group">
					                            <label class="control-label">系统消息内容: <span class="required">*</span></label>
					                            <div class="controls">
					                                <textarea class="span6 m-wrap" id="content" name="content" rows="6" style="resize:none;"></textarea>
					                            </div>
					                        </div>
					                    </div>
					                    <div class="modal-footer">
					                        <button type="button" class="btn btn-default" id="btn_clear">重置</button>
					                        <button type="button" class="btn btn-primary green" id="btn_send_sys">提交</button>
					                    </div>
					                </div>
					            </div>
					    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 内容区域 end -->
    
    <!-- modal 添加 end -->
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<script src="../../js/background/send_sys_message.js"></script>
<!-- endbuild -->
</body>

</html>
