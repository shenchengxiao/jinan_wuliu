'use strict'
$(function(){
	var orderId = getUrlParam('id');
	getOrderDetail(orderId);
});
/**
 *  功能描述：获取订单详情
 *  请求方式：GET
 *  请求地址：/api/upstream/order/page
 *  函数名称：getOrderDetail
 *  请求参数：orderId
 */

function getOrderDetail(id){
    
    $.ajax({
        url:"/api/upstream/order/page",
        type:"get",
        dataType:"json",
        data:{
            id:id
        },
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                var result = json.result[0];
                $('h3[name=channelName]').html(result.channelName);
                $('span[name=orderId]').html(result.orderId);
                $('span[name=primaryOrderId]').html(result.primaryOrderId);
                $('span[name=busiOrderId]').html(result.busiOrderId);
                $('span[name=outOrderId]').html(result.outOrderId);
                $('span[name=phoneNum]').html(result.phoneNum);
                $('span[name=packageSize]').html(result.packageSize);
                $('span[name=packagePrice]').html(result.packagePrice);
                $('span[name=resCode]').html(result.resCode);
                $('span[name=resDesc]').html(result.resDesc);
                $('span[name=resCode]').html(result.resCode);
                var status = result.exchangeStatus;
                switch(status){
                    case 0:
                        status='待充值'
                        break;
                    case 1:
                        status='充值中'
                        break;
                    case 2:
                        status='成功'
                        break;
                    case 3:
                        status='失败'
                        break;
                }
                $('span[name=exchangeStatus]').html(status);
                $('span[name=realRoamType]').html(result.realRoamType);
                $('span[name=exchangeTime]').html(result.exchangeTime);
                $('span[name=exchangedTime]').html(result.exchangedTime);
                $('span[name=createTime]').html(result.createTime);
                $('span[name=provinceName]').html(result.provinceName);
                $('span[name=cityName]').html(result.cityName);
                $('span[name=channelName]').html(result.channelName);
                $('span[name=customerName]').html(result.customerName);
                $('span[name=downstreamAccountId]').html(result.downstreamAccountId);
                $('span[name=productName]').html(result.productName);
                $('span[name=productCode]').html(result.productCode);
                $('span[name=standardPrice]').html(result.standardPrice);
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