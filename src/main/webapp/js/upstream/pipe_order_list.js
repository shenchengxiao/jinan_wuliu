/**
 * Created by shenchengxiao on 16/9/23.
 */
'use strict'
$(function(){
    //获取所有商家
    getAllBusiness();
    //设置时间初始值  开始时间为当前时间的前一天；结束时间为当前时间
   
    function toDou(iNum){
        return iNum<10?'0'+iNum:iNum;
    }
    function GetDateStr(AddDayCount) {
        var oDate = new Date();
        oDate.setDate(oDate.getDate()+AddDayCount);//获取AddDayCount天后的日期
        var y = oDate.getFullYear();
        var m = oDate.getMonth()+1;//获取当前月份的日期
        var d = oDate.getDate();
        return toDou(y)+"-"+toDou(m)+"-"+toDou(d);
    }
    var startTime = GetDateStr(-1);
    var endTime = GetDateStr(0);
    $('#startTime').val(endTime);
    $('#endTime').val(endTime);

    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getOrderList();
    }).click();

    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });
    //判断Excel是否可以导出
    $('#btn_order_export').click(function(){
        if(oList){
           clearModal(); //清空modal弹出层里面的参数；
           $('#MessageModal').modal('show'); //modle层显示 
            //exportOrderExcel();
        }else{
            alert('暂无数据，请重新查询后，导出Excel');
        }
    });

    $('#report_form').validate({
        errorElement: 'span',
        errorClass: 'help-inline',
        focusInvalid: false,
        ignore: '',
        rules: {
          message_title: {
            required: true
          },
          message_content: {
            required: true
          }
        },
        messages: {
          message_title: {
            required: '请输入标题'
          },
          message_content: {
            required: '请输入内容'
          }
        },
        invalidHandler: function(event, validator) {
          $('.alert-success').hide();
          $('.alert-error').show();
        },
        highlight: function(element) {
          $(element).closest('.help-inline').removeClass('ok');
          $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        unhighlight: function(element) {
          $(element).closest('.control-group').removeClass('error');
        },
        success: function(label) {
          label.addClass('valid').addClass('help-inline ok').closest('.control-group').removeClass('error').addClass('success');
        },
        submitHandler: function(form) {
          $('.alert-success').show();
          $('.alert-error').hide();
        }
      });

      $('#btn_message').on('click', function() {
        //非空验证
        var b = $('#report_form').valid(); //true false
        if (b) {
          exportOrderExcel(); //添加新的banner;
        } else {
          return false;
        }
      });

});

/**
 *  功能描述：上游订单
 *  请求方式：GET
 *  请求地址：/api/upstream/order/page
 *  函数名称：getOrderList
 */
var oList;
function getOrderList(){
    $.ajax({
        url:"/api/upstream/order/page",
        type:"get",
        dataType:"json",
        data:$('#order_list_form').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                console.log(json);
                var list = json.result;
                var temp = '';
                var operate='';

                oList = list.length;
                $.each(list,function(index,item){

                    var status = item.exchangeStatus;
                    switch(status){
                        case 0:
                            status='待充值';
                            operate='<a class="btn mini green" data-toggle="tooltip" data-placement="top" title="设置成功" onclick="simulatepush('+item.id+',1)"><i class="icon-ok-sign"></i></a>&nbsp'
                                    + '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="设置失败" onclick="simulatepush('+item.id+',0)"><i class="icon-minus-sign"></i></a>';
                            break;
                        case 1:
                            status='充值中';
                            operate='<a class="btn mini green" data-toggle="tooltip" data-placement="top" title="设置成功" onclick="simulatepush('+item.id+',1)"><i class="icon-ok-sign"></i></a>&nbsp'
                                    + '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="设置失败" onclick="simulatepush('+item.id+',0)"><i class="icon-minus-sign"></i></a>';
                            break;
                        case 2:
                            status = '成功';
                            if (item.orderIndex == 0){
                                operate='<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="推送状态" onclick="callbackpush('+item.orderInfoId+')"><i class="icon-upload"></i></a>&nbsp';
                            }else {
                                operate='';
                            }
                            break;
                        case 3:
                            status = '失败';
                            if (item.orderIndex == 0){
                                operate='<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="推送状态" onclick="callbackpush('+item.orderInfoId+')"><i class="icon-upload"></i></a>&nbsp';
                            }else {
                                operate='';
                            }
                            break;
                    }

                    var resDesc = item.resDesc;
                    if(resDesc == "null"){
                        resDesc = "";
                    }
                    //截取字符串
                    var orderId = item.orderId.toString();
                    orderId = orderId.substring(0,7)+"...";
                    var primaryOrderId = item.primaryOrderId.toString();
                    primaryOrderId = primaryOrderId.substring(0,7)+"...";
                    temp +='<tr>'+
                        '<td data-title="上游订单" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.orderId+'"><a href="pipe_order_detail.html?id='+item.id+'">'+orderId+'</a></td>'+
                        '<td data-title="下游订单" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.primaryOrderId+'">'+primaryOrderId+'</td>'+
                        '<td data-title="手机号码">'+item.phoneNum+'</td>'+
                        '<td data-title="渠道名称">'+item.channelName+'</td>'+
                        '<td data-title="SP名称">'+item.customerName+'</td>'+
                        '<td data-title="省份名称" style="white-space: nowrap;">'+item.provinceName+'</td>'+
                        '<td data-title="城市名称" style="white-space: nowrap;">'+item.cityName+'</td>'+
                        '<td data-title="提交时间">'+item.exchangeTime+'</td>'+
                        '<td data-title="成功时间">'+item.exchangedTime+'</td>'+
                        '<td data-title="漫游范围">'+item.realRoamType+'</td>'+
                        '<td data-title="流量包大小">'+item.packageSize+'</td>'+
                        // '<td data-title="上游返回码">'+item.resCode+'</td>'+
                        // '<td data-title="上游返回消息">'+item.resDesc+'</td>'+
                        '<td data-title="状态描述" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+resDesc+'">'+status+'</td>'+
                        '<td data-title="操作">'+operate+'</td>'+
                        '</tr>';

                });
                $('#order_list tbody').html(temp);
                $('#total>span').text(json.total);
                $("[data-toggle='popover']").popover();
                //操作按钮hover显示详情
                $("[data-toggle='tooltip']").tooltip();
                // 表格分页
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getOrderList,'#pageNum');
            }else{
                $.toast(data.msg,3000);
                $('#order_list tbody').html('');
                if($('#pagination').html().length > 0){
                    $('#pagination').jqPaginator('destory');
                }
            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
}

/**
 *  功能描述：获取商家
 *  请求方式：GET
 *  请求地址：/api/merchant/all_business
 *  函数名称：getAllBusiness
 */
function getAllBusiness() {
    $('#downstreamAccount_id').html('<option value="">全部商家</option>');
    $.ajax({
        url: '/api/merchant/all_business',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.status == 0) {
                var json = data.data;
                $.each(json, function (index, item) {
                    $('<option value="' + item.enterpriseAccount + '">' + item.businessName + '</option>').appendTo($('#downstreamAccount_id'));                });
            } else {
                $.toast('没有查到数据！', 3000);
            }
        },
        complete: function () {
            $.progressBar().close();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试', 5000);
        }
    });
}

/**
 *	功能描述：导出订单Excel
 *  请求方式：POST
 *  请求地址：/api/upstream/order/export
 *  函数名称：exportOrderExcel
 */
function exportOrderExcel(){
    var phoneNum = $('#phoneNum').val();
    var dataflowName = $('#dataflowName').val();
    var orderId = $('#orderId').val();
    var outOrderId = $('#outOrderId').val();
    var primaryOrderId = $('#primaryOrderId').val();
    var busiOrderId = $('#busiOrderId').val();
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    var exchangeStatus = $('#exchangeStatus').val();
    var downstreamAccount = $('#downstreamAccount_id').val();
    var message_title = $('#messageTitle').val();
    var message_content = $('#messageContent').val();
    $.ajax({
        url: '/api/upstream/order/export',
        type: 'post',
        dataType: 'json',
        data: {
          phoneNum: phoneNum,
          dataflowName: dataflowName,
          orderId: orderId,
          outOrderId: outOrderId,
          primaryOrderId: primaryOrderId,
          busiOrderId: busiOrderId,
          startTime: startTime,
          endTime: endTime,
          exchangeStatus: exchangeStatus,
          downstreamAccount:downstreamAccount,
          message_title: message_title,
          message_content: message_content
        },
        success: function(data) {
          if (data.status == 0) {
            $('#MessageModal').modal('hide'); //modle层隐藏
            $.toast('操作成功！', 3000);
          } else {
            $.toast('操作失败！', 3000);
          }
        },
        complete: function() {
          $.progressBar().close();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
          $.toast('服务器未响应,请稍候重试', 5000);
        }
      });  
}


/**
 *  功能描述：模拟上游回调，处理入库，充值中订单状态
 *  请求方式：POST
 *  请求地址：/api/upstream/order/simulatepush
 *  函数名称：simulatepush
 *  参数说明：id:订单主键id ;operateType:0:设置失败 1：设置成功
 */ 

function simulatepush(id,operateType){
    if(operateType == 0){
        if(!confirm("确定状态设置失败吗？")) return;
    }else{
        if(!confirm("确定状态设置成功吗？")) return;
    }
    $.ajax({
        url:'/api/upstream/order/simulatepush',
        type:'post',
        dataType:'json',
        data:{
            'id':id,
            'operateType':operateType
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            console.log(data);
            if(data.status == 0){
                $.toast(data.msg,3000);
                getOrderList();
            }else{
                $.toast(data.msg,3000);
            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
}


/**
 *  功能描述：给下游推送订单状态信息,只能对有充值状态的订单推送，即状态为失败或者成功。
 *  请求方式：POST
 *  请求地址：/api/downstream/order/callbackpush
 *  函数名称：callbackpush
 *  参数说明：id:订单主键id ;
 */

function callbackpush(orderInfoId){
    if(!confirm("确定推送状态吗？")) return;
    $.ajax({
        url:'/api/downstream/order/callbackpush',
        type:'post',
        dataType:'json',
        data:{
            'id':orderInfoId
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            console.log(data);
            if(data.status == 0){
                $.toast(data.msg,3000);
                getOrderList();
            }else{
                $.toast(data.msg,3000);
            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
}

function clearModal() {
  $('#messageTitle').val("");
  $('#messageContent').val("");
}







