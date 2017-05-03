<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>权限菜单管理</title>
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
                            <a href="#">权限菜单管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i>
                                权限菜单
                            </div>

                        </div>
                        <div class="portlet-body form " >
                            <fieldset class="form-horizontal ">
                                <div class="control-group">
                                    <label class="control-label">用户角色<span class="required">*</span></label>
                                    <div class="controls">
                                        <select class="m-wrap span3" id="roleSelect">
                                            <option value="" selected>请选择</option>
                                            <option value="32" >客服</option>
                                            <option value="64" >工作人员</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="actions control-group">
                                    <div class="controls">
                                    <a data-toggle="modal" class="btn green repoActivity" id="btn_addRole_modal">
                                        <i class="icon-pencil"></i>
                                        分配菜单
                                    </a>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 内容区域 end -->
    <form id="add_role_for_menu">
        <div class="modal fade hide" id="addMenu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <input type="hidden" id="roleType" name="roleType">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">分配菜单</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row-fluid">
                            <div class="span12">
                                <select multiple="multiple" id="menu_select" name="menuInfoIds"
                                        class="multiselect span12">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary green" id="btn_add_menu">提交
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<script src="../../js/admin/role_menu_allot.js"></script>
<script src="../../js/libs/jquery/multiple-select.js"></script>

</body>
</html>