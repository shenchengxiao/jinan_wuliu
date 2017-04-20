<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>城市定制</title>
    <!-- build:css css/main.css -->
    <jsp:include page="/common/common.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
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
                            <a href="#">城市定制</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 城市定制
                            </div>
                        </div>
                        <form class="form-horizontal" id="add_citylist_form">
					            <div class="modal-dialog">
					                <div class="modal-content">
					                    <div class="modal-body">
					                        <div class="control-group">
					                            <label class="control-label">账户: <span class="required">*</span></label>
					                            <div class="controls">
					                                <input type="text" class="span8 m-wrap" name="userName" id="userName" data-provide="typeahead" readonly="readonly">
					                            </div>
					                        </div>
					                        <div class="control-group">
					                            <label class="control-label">可接收城市: &nbsp;&nbsp;</label>
					                            <div class="controls">
					                            <select id="cityTree" name="receiveCity" class="easyui-combotree" 
					                            data-options="url:'../../tree/tree_data1.json',multiple : true,onLoadSuccess: loadTree,cascadeCheck:true" style="width:66%;height: 100%;"></select>
										        <button type="button" class="btn btn-primary green" onclick="invet('cityTree')">反选</button>
										        <button type="button" class="btn btn-primary green" onclick="isfold('cityTree')">展开/折叠</button>
					                            </div>
					                        </div>
					                        <div class="control-group">
					                            <label class="control-label">可发布城市: &nbsp;&nbsp;</label>
					                            <div class="controls">
					                            <select id="sendCity" name="sendCity" class="easyui-combotree" 
					                            data-options="url:'../../tree/tree_data1.json',multiple : true,cascadeCheck:true,
					                            onLoadSuccess: function (row, data) {
										            var tree = $('#sendCity').combotree('tree');
												    var roots=tree.tree('getRoots');
												    tree.tree('collapseAll');
												    tree.tree('expand', roots[0].target); 
										        }"
					                            style="width:66%;height: 100%;"></select>
					                            <button type="button" class="btn btn-primary green" onclick="invet('sendCity')">反选</button>
					                            <button type="button" class="btn btn-primary green" onclick="isfold('sendCity')">展开/折叠</button>
					                            </div>
					                        </div>
					                    </div>
					                    <div class="form-actions" align="center">
	                                        <a href="javascript:;" class="btn green" id="btn_add_citylist">提交</a>
	                                        <a href="user_list.jsp" class="btn grey">取消</a>
	                                    </div>
					                    <!-- <div class="modal-footer">
					                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					                        <button type="button" class="btn btn-primary green" id="btn_add_citylist">提交</button>
					                    </div> -->
					                </div>
					            </div>
					    </form>
                    </div>
                </div>
            </div>
    	</div>
    </div>
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!-- 页尾 end -->
<script src="../../js/citylist/citylist.js"></script>
<!-- endbuild -->
</body>
</html>