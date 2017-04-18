<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>广告管理</title>
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
                            <a href="#">广告管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 广告管理
                            </div>
                            <div class="actions">
                                <a class="btn green repoActivity" id="creat_advert_icon">
                                    <i class="icon-pencil"></i> 新增广告
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="advert_list_form" method="get">
                                    <div class="span2">
                                        <input type="text" name="price" placeholder="请输入价格" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="linkedName" placeholder="请输入联系人" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="phoneNumber" placeholder="请输入联系电话" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <select class="m-wrap span6" name="beUsed" id="be_used">
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
                                <table class="table-bordered table-striped table-condensed cf" id="advert_list">
                                    <thead class="cf">
                                    <tr>
                                        <th>开始时间</th>
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
    <!-- modal 添加 begin -->
    <form class="form-horizontal" id="add_advert_form">
        <div class="modal fade hide" id="addAdvertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">新增广告</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id">
                        <!--开始时间-->
                        <div class="control-group">
                            <label class="control-label">开始日期：<span class="required">*</span></label>
                            <div class="controls">
                                <div class="input-append date">
                                    <input data-laydate="start" class="m-wrap input-small start-time mh_date" type="text" value="" name="startTime" id="startTime" readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <!--结束时间-->
                        <div class="control-group">
                            <label class="control-label">结束日期：<span class="required">*</span></label>
                            <div class="controls">
                                <div class="input-append date">
                                    <input data-laydate="end" class="m-wrap input-small end-time mh_date" type="text" value="" name="endTime" id="endTime" readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">价格: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="price" id="price">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">联系人: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="linkedName" id="linkedName">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">联系电话: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="phoneNumber" id="phoneNumber">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否有效: <span class="required">*</span></label>
                            <div class="controls">
                                <label class="radio">
                                    <input type="radio" value="0" name="beUsed" > 无效
                                </label>
                                <label class="radio">
                                    <input type="radio" value="1" name="beUsed" checked> 有效
                                </label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">链接地址: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="linkUrl" id="linkUrl">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">广告标题: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="advertTitle" id="advertTitle">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">广告内容: <span class="required">*</span></label>
                            <div class="controls">
                                <textarea class="span8 m-wrap" rows="5" name="content" id="content"></textarea>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary green" id="btn_add_advert">提交</button>
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
<script src="../../js/advert/advert.js"></script>
<!-- endbuild -->
</body>

</html>
