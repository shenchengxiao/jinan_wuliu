<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>用户管理</title>
    <jsp:include page="../../common/common.jsp"></jsp:include>
    <style>
        .checker{
            display:inline-block!important;
        }
    </style>

</head>
<body class="page-header-fixed">
<!-- 头部 begin -->
<jsp:include page="../../include/header.jsp"></jsp:include>
<!-- 头部 end -->
<!-- 页面主体 begin -->
<div class="page-container row-fluid">
    <!-- 左侧菜单 begin-->
    <jsp:include page="../../include/left_sidebar.jsp"></jsp:include>
    <!-- 左侧菜单 end-->
    <!-- 内容区域 begin -->
    <div class="page-content">
        <div class="container-fluid ">
            <div class="row-fluid">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="../index.html">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">用户管理</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row-fluid">
                <p id="manageuser"></p>
            </div>

            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i>
                                用户列表
                            </div>
                            <div class="actions">
                                <a data-toggle="modal" href="#addUserModal" class="btn green repoActivity" id="btn_addRole_modal">
                                    <i class="icon-pencil"></i>
                                    添加用户
                                </a>
                            </div>
                        </div>
                        <form id="kl_user_form" method="get">
                            <input type="hidden" name="pageNum" id="pageNum" value="1"  >
                            <input type="hidden" name="userName" id="businessAccountId" value="6"  >
                        </form>

                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table table-bordered table-striped table-condensed cf table-hover" id="jn_user_list">
                                    <thead class="cf">
                                    <tr>
                                        <th>用户名</th>
                                        <th>手机号</th>
                                        <th>管理权限</th>
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
    <form id="add_role_form" class="form-horizontal">
        <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">添加用户</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-error hide">
                            <button class="close" data-dismiss="alert"></button>
                            您的输入有错，请您检查后再提交!
                        </div>
                        <div class="alert alert-success hide">
                            <button class="close" data-dismiss="alert"></button>
                            恭喜您，您输入的内容完全正确！
                        </div>
                        <div class="control-group">
                            <label class="control-label">用户名<span class="required">*</span></label>
                            <div class="controls">
                                <input type="hidden" name="id">
                                <input type="text" name="userName" class="span8 m-wrap">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">手机号码<span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" name="phoneNum" class="span8 m-wrap">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">分配权限<span class="required">*</span></label>
                            <div class="controls">
                                <label>
                                    <input type="checkbox" value="16" name="roleArr">
                                    超级管理员
                                </label>
                                <label>
                                    <input type="checkbox" value="32" name="roleArr">
                                    客服人员
                                </label>
                                <span class="help-block">请至少分配一项权限</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">密码<span class="required">*</span></label>
                            <div class="controls">
                                <input type="password" name="passwd" class="span8 m-wrap">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary green" id="btn_add_role">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!-- modal 添加 end -->
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="../../include/footer.jsp"></jsp:include>
<!-- 页尾 end -->

<script src="/js/user/user.js"></script>
</body>
</html>