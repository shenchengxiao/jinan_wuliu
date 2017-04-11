<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>添加用户</title>

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

    <!-- 内容区域 begin-->
    <div class="page-content">
        <div class="container-fluid ">
            <div class="row-fluid">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="${ctx}/index.html">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>

                        <li>
                            <a href="#">新增用户</a>
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
                                新增用户
                            </div>
                        </div>

                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form id="add_user_info_form" class="form-horizontal">
                                <%--<div class="alert alert-error hide">--%>
                                    <%--<button class="close" data-dismiss="alert"></button>--%>
                                    <%--您的输入有错，请您检查后再提交!--%>
                                <%--</div>--%>
                                <%--<div class="alert alert-success hide">--%>
                                    <%--<button class="close" data-dismiss="alert"></button>--%>
                                    <%--恭喜您，您输入的内容完全正确！--%>
                                <%--</div>--%>
                                <fieldset>
                                    <div class="control-group">
                                        <label class="control-label">用户名称<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" id="userName" name="userName"/></div>
                                        <label class="control-label">用户编号<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" id="userNum" name="userNum" /></div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">用户密码<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" id="password" name="password" /></div>
                                        <label class="control-label">确认密码<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="passwordVerify" /></div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">电话号码<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="phoneNumber" /></div>
                                        <label class="control-label">省市县<span class="required">*</span></label>
                                        <div class="span4" data-toggle="distpicker">
                                                <select name="province" class="m-wrap span4" ></select>
                                                <select name="city" class="m-wrap span4" ></select>
                                                <select name="county" class="m-wrap span4"></select>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">选项<span class="required">*</span></label>
                                        <div class="span4">
                                            <select class="m-wrap span3" name="isAbled" >
                                                <option value="0">无效</option>
                                                <option value="1" selected>有效</option>
                                            </select>
                                            <select class="m-wrap span3" name="isManager" >
                                                <option value="0">不是管理员</option>
                                                <option value="1" selected>是管理员</option>
                                            </select>
                                            <select class="m-wrap span3" name="isSync" >
                                                <option value="0">不同步</option>
                                                <option value="1" selected>同步</option>
                                            </select>
                                        </div>

                                        <label class="control-label">邮箱:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="userEmail" /></div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">邮编:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="postCode" /></div>
                                        <label class="control-label">公司名称:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="companyName"/></div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">注册IP<span class="required">*</span></label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="registerIp" /></div>
                                        <label class="control-label">最后登录IP:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="loginIp" /></div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">服务开始日期<span class="required">*</span></label>
                                        <div class="span4">
                                            <div class="input-append date">
                                                <input data-laydate="start" class="m-wrap span12" type="text" value="" name="startTime"  readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                            </div>
                                        </div>

                                        <label class="control-label">服务结束日期<span class="required">*</span></label>
                                        <div class="span4">
                                            <div class="input-append date">
                                                <input data-laydate="end" class="m-wrap span12" type="text" value="" name="endTime"  readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">身份证号:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="identityNum" /></div>
                                        <label class="control-label">地址:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="address" /></div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">上次退出编号:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="lastQuitNum" /></div>
                                        <label class="control-label">本次下载编号:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="thisLoadNum" /></div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">硬盘号:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="hardNum" /></div>
                                        <label class="control-label">网卡号:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="networkNum" /></div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">临时硬盘网卡号:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="temporaryCard" />
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">查看限额:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="checkLimit" /></div>
                                        <label class="control-label">查看次数:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="checkNum" /></div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">选项<span class="required">*</span></label>
                                        <div class="span6">
                                            <select class="m-wrap span3" name="isSend" >
                                                <option value="0">不能发布信息</option>
                                                <option value="1" selected>可以发布信息</option>
                                            </select>
                                            <select class="m-wrap span3" name="isReceive" >
                                                <option value="0">不能接收信息</option>
                                                <option value="1" selected>可以接收信息</option>
                                            </select>
                                            <select class="m-wrap span3" name="isReceiveSelf" >
                                                <option value="0">不接受自己发布信息</option>
                                                <option value="1" selected>接受自己发布信息</option>
                                            </select>
                                            <select class="m-wrap span3" name="isBinding" >
                                                <option value="0">不绑定电脑</option>
                                                <option value="1" selected>绑定电脑</option>
                                            </select>
                                        </div>


                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">可发布城市:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text"  name="sendCity" /></div>
                                        <label class="control-label">可接收城市:</label>
                                        <div class="span4">
                                            <input class="m-wrap span12" type="text" name="receiveCity" /></div>
                                    </div>

                                </fieldset>
                                <fieldset>
                                <div class="form-actions" align="center">
                                    <a href="javascript:;" class="btn green" id="btn_addUserInfo">提交</a>
                                    <a href="user_list.jsp" class="btn grey">取消</a>
                                </div>
                                </fieldset>
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
<script src="${pageContext.request.contextPath}/js/libs/jquery/distpicker.data.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/jquery/distpicker.js"></script>
<script src="${pageContext.request.contextPath}/js/background/user_add.js"></script>
</body>
</html>