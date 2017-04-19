<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>用户信息详情</title>

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
                            <a href="user_list.jsp">用户列表</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">用户详情</a>
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
                                用户详情
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form id="pipe_order_detail">
                                <div class="form-horizontal form-view">
                                    <h3 class="form-section">用户基本信息</h3>
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">用户名称:</label>
                                                <div class="controls">
                                                    <span class="text" name="user_name"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label" >用户编号:</label>
                                                <div class="controls">
                                                    <span class="text" name="user_number"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">电话号码:</label>
                                                <div class="controls">
                                                    <span class="text" name="phone_number"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">省级:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="province_name"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">市级:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="city_name"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">县级:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="county_name"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">邮箱:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="user_email"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">邮编:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="post_code"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">公司名称:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="company_name"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">注册IP:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="register_ip"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">最后登录IP:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="login_ip"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">服务开始时间:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="start_time"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">服务结束时间:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="end_time"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">身份证号:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="ID_card"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">地址:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="address"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">上次退出编号:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="last_quit_num"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">本次下载编号:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="this_load_num"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">设备码:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="hard_number"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">话机码:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="network_number"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">查看限额:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="limit_money"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">查看次数:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="limit_number"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">可发布城市:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="send_city"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <!--/row-->
                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">可接收城市:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="receive_city"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">是否可以发布信息:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="is_send_message"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>

                                    <div class="row-fluid">
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">是否可以接收:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="is_receive_message"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="span6 ">
                                            <div class="control-group">
                                                <label class="control-label">是否可以接收自己发布的:</label>
                                                <div class="controls">
                                                    <span class="text bold" name="is_receive_self_message"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>

                                </div>
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
<!-- endbuild -->
<script src="${pageContext.request.contextPath}/js/background/user_detail.js"></script>
</body>
</html>