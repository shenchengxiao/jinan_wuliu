<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>信息日志</title>
    <!-- build:css css/main.css -->
    <%@ include file="/common/taglibs.jsp"%>
    <jsp:include page="/common/common.jsp"></jsp:include>
    <![endif]-->
	<style>
        #item_List td{
            text-align: center;
            vertical-align: middle;
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
                            <a href="../index.jsp">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">信息日志</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 发布信息日志
                            </div>
                            <!-- <div class="actions">
                                <a class="btn green repoActivity" id="creat_banner_icon">
                                    <i class="icon-pencil"></i> 新增黑词
                                </a>
                            </div> -->
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="item_list_form" method="get">
                                    <div class="span2">
                                        <input type="text" name="userNum" placeholder="请输入用户编号" class="m-wrap span12" id="userNum">
                                    </div>
                                    <div class="span2">
                                          <div class="input-append date">
                                              <input placeholder="请选择查询日期" data-laydate="start" class="m-wrap span12" type="text" value="" name="createTime"  readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                          </div>
                                    </div>
                                    <div class="span2">
                                            <select class="m-wrap span6" name="typeId" >
                                                <option value="0">车源</option>
                                                <option value="1" selected>货源</option>
                                            </select>
                                    </div>        
                                    <div class="span2">
                                        <button type="button" class="btn blue mgleft10" id="btn_search_item">查找</button>
                                    </div>
                                    
                                    <!-- <div class="span2">
                                        <input type="text" name="adName" placeholder="请输入联系电话" class="m-wrap span12" id="nameText_search">
                                    </div> -->
                                    <!-- <div class="span2">
                                        <select class="m-wrap span6" name="enabled">
                                            <option value="">是否有效</option>
                                            <option value="0">无效</option>
                                            <option value="1">有效</option>
                                        </select>
                                    </div> -->

                                    <input type="hidden" name="pageNum" id="pageNum" value="1">
                                </form>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="item_List">
                                    <thead class="cf">
                                    <tr>
                                        <th>用户编号</th>
                                        <th>用户电话</th>
                                        <th>类型</th>
                                        <th>内容</th>
                                        <th>发布时间</th>
                                        <th>状态</th>
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
    <!-- <form action="#" class="form-horizontal" id="add_blackword_form">
        <div class="modal fade hide" id="addBlackwordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">新增黑词</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="bWId">
                        <div class="control-group">
                            <label class="control-label">黑名词: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="blackWord" id="blackWord">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否有效: <span class="required">*</span></label>
                            <div class="controls">
                                <label class="radio">
                                    <input type="radio" value="0" name="enabled"> 无效
                                </label>
                                <label class="radio">
                                    <input type="radio" value="1" name="enabled" checked> 有效
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary green" id="btn_add_blackword">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </form> -->
    <!-- modal 添加 end -->
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<!-- build:js scripts/build.js -->
<!-- endbuild -->
<script src="${pageContext.request.contextPath}/js/log/push_item_log.js"></script>
</body>
</html>
