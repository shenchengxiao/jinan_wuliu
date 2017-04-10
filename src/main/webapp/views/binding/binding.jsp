<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>绑定电脑</title>
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
                            <a href="#">修改绑定电脑</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 修改绑定电脑
                            </div>
                        </div>
                        <form class="form-horizontal" id="add_binding_form">
					            <div class="modal-dialog">
					                <div class="modal-content">
					                    <div class="modal-body">
					                        <div class="control-group">
					                            <label class="control-label">账户: <span class="required">*</span></label>
					                            <div class="controls">
					                                <input type="text" class="span8 m-wrap" name="userName" id="userName">
					                            </div>
					                        </div>
					                        <div class="control-group">
					                            <label class="control-label">硬盘号: </label>
					                            <div class="controls">
					                                <input type="text" class="span8 m-wrap" name="hardpanNum" id="hardpanNum">
					                            </div>
					                        </div>
					                        <div class="control-group">
					                            <label class="control-label">网卡号:</label>
					                            <div class="controls">
					                                <input type="text" class="span8 m-wrap" name="networkCard" id="networkCard">
					                            </div>
					                        </div>
					                        <div class="control-group">
					                            <label class="control-label">临时硬盘网卡号:</label>
					                            <div class="controls">
					                                <input type="text" class="span8 m-wrap" name="temporaryCard" id="temporaryCard">
					                            </div>
					                        </div>
					                        <div class="control-group">
					                            <label class="control-label">绑定电脑: </label>
					                            <div class="controls">
					                                <label class="checkbox">
					                                    <input type="checkbox" value="0" name="isBinding" id="isBinding">
					                                    <input type="hidden" name="isBinding" value="0" />
					                                </label>
					                            </div>
					                        </div>
					                    </div>
					                    <div class="modal-footer">
					                        <!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
					                        <button type="button" class="btn btn-primary green" id="btn_add_binding">提交</button>
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
<script src="../../js/binding/binding.js"></script>
<!-- endbuild -->
</body>

</html>
