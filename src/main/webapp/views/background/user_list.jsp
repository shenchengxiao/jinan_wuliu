<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>用户管理</title>
    <!-- build:css css/main.css -->
    <jsp:include page="/common/common.jsp"></jsp:include>
    <![endif]-->
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
                            <a href="#">用户管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 用户管理
                            </div>
                            <div class="actions">
                                <a href="user_add.jsp" class="btn green repoActivity">
                                    <i class="icon-pencil"></i>
                                    添加用户
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="user_list_form" method="get">
                                    <div class="span2">
                                        <input type="text" name="userName" placeholder="请输入用户名称" class="m-wrap span12">
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="userNum" placeholder="请输入用户编号" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="phoneNumber" placeholder="请输入联系电话" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <select class="m-wrap span6" name="isAbled" >
                                            <option value="">请选择</option>
                                            <option value="0">无效</option>
                                            <option value="1">有效</option>
                                        </select>
                                    </div>
                                    <div class="span2">
                                        <select class="m-wrap span6" name="platformType" >
                                            <option value="">请选择</option>
                                            <option value="0">windows</option>
                                            <option value="1">Ios</option>
                                            <option value="1">Android</option>
                                        </select>
                                    </div>
                                    <div class="span2">
                                        <button type="button" class="btn blue mgleft10" id="btn_search">查找</button>
                                    </div>
                                    <div class="span2" align="right">
                                            <div class="controls">
                                                <a href="javascript:;" class="btn green" id="btn_chooseAll">
                                                                                                                                                                              全选
                                                    <i class="icon-down"></i>
                                                </a>&nbsp;&nbsp;
                                                <a href="javascript:;" class="btn red" id="btn_send">
                                                    	发送消息     
                                                    <i class="icon-down"></i>
                                                </a>&nbsp;&nbsp;
                                                <!-- <input type="hidden" name="" value="" id="ids"> -->
                                            </div>
                                    </div>
                                    <input type="hidden" name="pageNum" id="pageNum" value="1">
                                </form>
                            </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <table class="table-bordered table-striped table-condensed cf" id="user_manage_list">
                                    <thead class="cf">
                                    <tr>
                                        <th></th>
                                        <th>用户名称</th>
                                        <th>用户编号</th>
                                        <th>用户密码</th>
                                        <th>联系电话</th>
                                        <th>省份</th>
                                        <th>城市</th>
                                        <th>县区</th>
                                        <th>服务开始时间</th>
                                        <th>服务结束时间</th>
                                        <th>硬盘号</th>
                                        <th>网卡号</th>
                                        <th>状态</th>
                                        <th>平台</th>
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
    <form action="#" class="form-horizontal" id="send_usermessagee_form">
            <div class="modal fade hide" id="sendUserMessageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">发送消息通知</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="userIds" value="" id="ids">
                        <div class="control-group">
                            <label class="control-label">消息内容: <span class="required">*</span></label>
                            <div class="controls">
                                <textarea class="span10 m-wrap" id="content" name="content" rows="6" style="resize:none;"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-default" id="btn_clear">重置</button>
                        <button type="button" class="btn btn-primary green" id="btn_send_usermessage">提交</button>
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
<script src="${pageContext.request.contextPath}/js/background/user_list.js"></script>

</body>

</html>
