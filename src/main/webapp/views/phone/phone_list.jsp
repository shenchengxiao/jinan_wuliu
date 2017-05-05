<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>话务管理</title>
    <!-- build:css css/main.css -->
    <jsp:include page="/common/common.jsp"></jsp:include>
    <style>
        .table-advance th.highlight td.success {
            border-left: 2px solid #66ee66;
        }
        .row-fluid {
            width: 100%;
            overflow-x: hidden;
        }
        @media only screen and (max-width: 1500px) and (min-width: 801px){
            .no-more-tables table ,
            .no-more-tables thead ,
            .no-more-tables tbody {white-space: nowrap;}
            .no-more-tables thead tr ,
            .no-more-tables th { font-size:12px}
            .no-more-tables tbody tr ,
            .no-more-tables td { font-size:12px}
            .my_div {overflow-x: auto;width: auto}
            .table-condensed th,
            .table-condensed td {
                padding:4px 3px;
            }
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
                            <a href="#">话务管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 话务量统计
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="row-fluid">
                                <form id="phone_list_form" method="get">
                                    <!-- <div class="span2">
                                        <input type="text" name="custId" placeholder="请输入用户编号" class="m-wrap span12">
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="inPhone" placeholder="请输入打入电话" class="m-wrap span12" >
                                    </div>
                                    <div class="span2">
                                        <input type="text" name="serviceNum" placeholder="请输入客服编号" class="m-wrap span12" >
                                    </div>
                                     -->
									<div class="span2">
                                          <div class="input-append date">
                                             <input placeholder="请选择打入时间" data-laydate="start" class="m-wrap span12" type="text" value="" name="inTime"  readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
                                          </div>
                                    </div>
                                    <div class="span2">
                                          <div class="input-append date">
		                                    <input placeholder="请选择断开时间" data-laydate="start" class="m-wrap span12" type="text" value="" name="outTime"  readonly="true" /><span class="add-on"><i class="icon-calendar"></i></span>
		                                  </div>
                                    </div>
                                    <input type="hidden" name="pageNum" id="pageNum" value="1">
                                    <div class="span2">
                                        <button type="button" class="btn blue mgleft10" id="btn_search">查找</button>
                                    </div>
                                </form>
                                <div class="span11" align="right">
                                    <div class="controls">
										总数量：<span id="total"></span>&nbsp;&nbsp;&nbsp;&nbsp;
										总时长(秒)：<span id="totaltime"></span>
                                    </div>
                                </div>
                        </div>
                        <div class="box">
                            <div class="portlet-body no-more-tables">
                                <div class="my_div">
                                <table class="table-bordered table-striped table-condensed cf" id="phone_list">
                                    <thead class="cf">
                                    <tr>
                                        <th>用户编号</th>
                                        <th>打入电话</th>
                                        <th>打入时间</th>
                                        <th>断开时间</th>
                                        <th>时长(秒)</th>
                                        <th>客服编号</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                    <tfoot>
                                    </tfoot>
                                </table>
                                </div>
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
    
    <!-- modal 添加 end -->
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<!-- build:js scripts/build.js -->
<!-- endbuild -->
<script src="${pageContext.request.contextPath}/js/phone/phone_list.js"></script>

</body>

</html>
