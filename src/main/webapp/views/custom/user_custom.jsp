<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>用户权限列表</title>
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
                            <a href="#">用户权限列表</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 用户权限列表
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
                                        <input type="text" name="username" placeholder="请输入用户名称" class="m-wrap span12" id="username">
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
                                    <input type="text" name="pageNum" id="pageNum" value="1" style="display: none;">
                                </form>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="item_List">
                                    <thead class="cf">
                                    <tr>
                                        <th>用户名称</th>
                                        <th>用户角色</th>
                                        <th>平台类型</th>
                                        <th>绑定设备</th>
                                        <th>手机平台限制</th>
                                        <th>接收车源</th>
                                        <th>接收货源</th>
                                        <th>发布车源</th>
                                        <th>发布货源</th>
                                        <th>接收城市</th>
                                        <th>发布城市</th>
                                        <th>查看电话</th>
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
    <form action="#" class="form-horizontal" id="edit_custom_form">
        <div class="modal fade hide" id="editCustomModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">修改权限</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id">
                        <div class="control-group">
                            <label class="control-label">用户名称: <span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="username" id="user_name" readonly>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">用户角色: <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span5" name="isManager" id="isManager" >
                                    <option value="1">系统管理员</option>
                                    <option value="2" >工作人员</option>
                                    <option value="3" >车主</option>
                                    <option value="4" >货主</option>
                                    <option value="5" >未认证用户</option>
                                </select>
                            </div>
                        </div>
	                   <div class="control-group">
	                          <label class="control-label">平台类型: <span class="required">*</span></label>
	                          <div class="controls">
	                              <input type="text" class="span8 m-wrap" name="platformType" id="platformType" readonly>
	                          </div>
	                   </div>
	                    <div class="control-group">
                            <label class="control-label">是否绑定设备: <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span3" name="isBinding" id="isBinding" >
                                    <option value="0">否</option>
                                    <option value="1" >是</option>
                                </select>
                            </div>
                        </div>
	                    <div class="control-group">
                            <label class="control-label">是否受安卓、苹果限制 <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span3" name="isPhoneLimit" id="isPhoneLimit" >
                                    <option value="0">否</option>
                                    <option value="1" >是</option>
                                </select>
                            </div>
                        </div>
	                    <div class="control-group">
                            <label class="control-label">是否接收车源信息 <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span3" name="isReceiveCar" id="isReceiveCar" >
                                    <option value="0">否</option>
                                    <option value="1" >是</option>
                                </select>
                            </div>
                        </div>
	                    <div class="control-group">
                            <label class="control-label">是否接收货源信息 <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span3" name="isReceiveGoods" id="isReceiveGoods" >
                                    <option value="0">否</option>
                                    <option value="1" >是</option>
                                </select>
                            </div>
                        </div>
	                    <div class="control-group">
                            <label class="control-label">是否发布车源信息 <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span3" name="isSendCar" id="isSendCar" >
                                    <option value="0">否</option>
                                    <option value="1" >是</option>
                                </select>
                            </div>
                        </div>
	                    <div class="control-group">
                            <label class="control-label">是否发布货源信息 <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span3" name="isSendGoods" id="isSendGoods" >
                                    <option value="0">否</option>
                                    <option value="1" >是</option>
                                </select>
                            </div>
                        </div>
	                    <div class="control-group">
                            <label class="control-label">是否查看电话 <span class="required">*</span></label>
                            <div class="controls">
                                <select class="m-wrap span3" name="isLookPhone" id="isLookPhone" >
                                    <option value="0">否</option>
                                    <option value="1" >是</option>
                                </select>
                            </div>
                        </div>
	                   
	                   
                    </div>
                    
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary green" id="btn_edit_custom">提交</button>
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
<script src="${pageContext.request.contextPath}/js/custom/user_custom.js"></script>
</body>
</html>
