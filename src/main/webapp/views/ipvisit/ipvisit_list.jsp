<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>服务器管理</title>
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
                            <a href="#">服务器列表</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 服务器列表
                            </div>
                            <div class="actions">
                                <a class="btn green repoActivity" id="creat_server_icon">
                                    <i class="icon-pencil"></i> 新增服务器
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="ipvisit_list_form" method="get">
                                    <!-- <div class="span2">
                                        <input type="text" name="blackWord" placeholder="请输入关键字" class="m-wrap span12">
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="adName" placeholder="请输入联系人" class="m-wrap span12" id="linkedName">
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="adName" placeholder="请输入联系电话" class="m-wrap span12" id="nameText_search">
                                    </div>
                                    <div class="span2">
                                        <select class="m-wrap span6" name="enabled">
                                            <option value="">是否有效</option>
                                            <option value="0">无效</option>
                                            <option value="1">有效</option>
                                        </select>
                                    </div>
                                    <div class="span2">
                                        <button type="button" class="btn blue mgleft10" id="btn_search1">查找</button>
                                    </div> -->
                                    <input type="hidden" name="pageNum" id="pageNum" value="1">
                                </form>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="ipvisit_List">
                                    <thead class="cf">
                                    <tr>
                                        <th>服务器IP</th>
                                        <th>端口号</th>
                                        <th>域名</th>
                                        <th>开始使用时间</th>
                                        <th>功能描述</th>
                                        <th>状态</th>
                                        <th>操作</th>
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
    <!-- modal 添加 begin -->
    <form class="form-horizontal" id="add_server_form">
        <div class="modal fade hide" id="addServerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">新增服务器</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id">
                        <div class="control-group">
                            <label class="control-label">服务器外网ip: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="ip" id="ip">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">端口号: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="port" id="port">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">域名访问: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="domain" id="domain">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开始日期：<span class="required">*</span></label>
                            <div class="controls">
                                <div class="input-append date">
                                    <input data-laydate="start" class="m-wrap span12" type="text" value="" name="createTime" id="createTime" readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">功能描述: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="functionDesc" id="functionDesc">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否启用: <span class="required">*</span></label>
                            <div class="controls">
                                <label class="radio">
                                    <input type="radio" value="0" name="status" > 未启用
                                </label>
                                <label class="radio">
                                    <input type="radio" value="1" name="status" checked> 启用
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary green" id="btn_add_server">提交</button>
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
<!-- build:js scripts/build.js -->
<!-- endbuild -->
<script src="${pageContext.request.contextPath}/js/ipvisit/ipvisit.js"></script>
</body>
</html>
