'use strict'
$(document).ready(function () {
    $('#btn_addProduct').on('click',function(){
            ProductInfo();
            operateProduct();
    });
});

//定义全局变量 获取主键ID
var upstreamAccountInfoId=getUrlParam("upstreamAccountInfoId");
var belongNum = getUrlParam("belongType");
var belongType = parseInt(belongNum);
var CheckedArr = [];
var checkjson = {};
var box_id;
//获取上游name
var CHANNELNAME = getcookie('channelName');

//查询上游折扣
showDiscount();

//获取运行商name和流量包大小
setBelongAndPackage();



//创建模板和铺数据
$('#packageSizeCheck').on('change','input[type="checkbox"]',function(){

     var Discount = $('#Discount').val();
     $('.form-actions').show();
     var $this = $(this);
     var parthis = $this.parent(); 
     box_id = $this.attr('id').replace('checkbox', 'box');
     if($this.is(':checked')){
        //选中操作
        checkjson = {
            //包大小/编号
            'checkedSize': $('input',parthis).eq(0).val(),
            //基准价
            'checkedPrice':$('input',parthis).eq(1).val(),
            //产品名称/产品描述
            'channelName':CHANNELNAME,
            //折扣价
            'discount':Discount
         };
         productModle();
         if($('#checkbox0').is(':checked') && $('#box0 input[name="standardPrice"]').val() == ""){

            $('#box0 p').text('自定义');
            $("#box0 input:not('.upstreamAccountInfoId')").val('');
            //监听自定义时的原价自定义
            setcustom();
        }
     }else{
        //未选中操作
       $('#'+box_id).remove();
     }
});


//数据模板
function productModle(){
    var tabtit  = "";
    var tabbox = "";
    $.each(checkjson,function(index,item){
        var disPrice = (Number(checkjson.checkedPrice) * Number(checkjson.discount)).toFixed(4);
        // tabtit += '<li><a href="javascript:;">'+item.checkedSize+'M</a></li>';
        tabbox = '<div id="'+box_id+'">'
               +  '<p style="color:red;font-size: 18px;">'+checkjson.checkedSize+'M</p>'
               +  '<input type="hidden" name="upstreamAccountInfoId" class="upstreamAccountInfoId">'
               +  '<div class="control-group">'
               +  '<label class="control-label">产品名称<span class="required">*</span></label>'
               +  '<div class="controls"><input type="text" class="span6 m-wrap" placeholder="请输入产品名称" name="productName" style="width: 180px;" value="'+checkjson.channelName+checkjson.checkedSize+'M"/></div></div>'
               +  '<div class="control-group"><label class="control-label">产品描述<span class="required">*</span></label><div class="controls"><input type="text" class="span6 m-wrap" style="width: 180px;"  placeholder="请输入描述" name="productDesc" value="'+checkjson.channelName+checkjson.checkedSize+'M"/></div></div>'
               +  '<div class="control-group" style="margin-left: 10px;"><label class="control-label">产品编号<span class="required">*</span></label><div class="controls"><input type="text" class="span6 m-wrap" placeholder="请输入编号" name="productCode" value="'+checkjson.checkedSize+'"/></div></div>'
               +  '<div class="control-group"><label class="control-label">基准价<span class="required">*</span></label><div class="controls"><input type="text" class="span6 m-wrap" style="width: 180px;" placeholder="请输入基准价" name="standardPrice" value="'+checkjson.checkedPrice+'"/></div></div>'       
               +  '<div class="control-group"><label class="control-label">折扣价<span class="required">*</span></label><div class="controls"><input type="text" class="span6 m-wrap"  style="width: 180px;" placeholder="请输入折扣价" name="discountPrice" value="'+disPrice+'"/> </div></div>' 
               +  '<div class="control-group" style="margin-left: 10px;"><label class="control-label">流量包大小<span class="required">*</span></label><div class="controls"> <input type="text" class="span6 m-wrap" placeholder="请输入包大小" name="packageSize" value="'+checkjson.checkedSize+'"/></div></div><i class="line"></i></div>';
    });
    // var $tit = $(tabtit);
    // $('#nav-tabs').html($tit);
    var $tabbox = $(tabbox);
    $('#modle-box').append($(tabbox));
    //上游账户主键ID 用于提交表单
    $("input[name='upstreamAccountInfoId']").val(upstreamAccountInfoId);
}

//自定义监听折扣价价格
function setcustom(){
   var costPrize = $('#box0 input[name="standardPrice"]');
   console.log($('#Discount').val())
   costPrize.on('keyup',function(){
       var num = (Number(costPrize.val()) * Number($('#Discount').val())).toFixed(4);
       $('#box0 input[name="discountPrice"]').val(num);
   });
}
//运营商流量大小
function setBelongAndPackage(){
    var belongName = "";
    switch(belongType){
        case 1:
            belongName = '移动'
        break;
        case 2:
            belongName = '联通'
        break;
        case 3:
            belongName = '电信'
        break;
        case 4:
            belongName = '虚拟运营商'
        break;
    }
    $('#belongName').text(belongName);
    $.ajax({
        url: '/api/downstream/package/all_list',
        type: 'GET',
        dataType: 'json',
        data: {
            belongType: belongNum
        },
        success: function (data) {
            //console.log(data);
            if (data.status == 0){
                var temp = "";
                var list = data.data;
                $.each(list, function(i, item) {
                    temp += '<label class="checkbox"><div><span>'
                         +  '<input type="checkbox" name="packageSize" id="checkbox'+item.packageSize+'" value="'+item.packageSize+'">'
                         +  '<input type="hidden" name="packagePrice" value="'+item.packagePrice+'"/>'
                         +  '<input type="hidden" name="id" value="'+item.id+'"/></span></div>'+item.packageSize+'M | </label>';
                });
                var operate = '<label class="checkbox" style="color:red"><div><span>'
                         +  '<input type="checkbox" name="packageSize" id="checkbox0" value="">'
                         +  '<input type="hidden" name="packagePrice" value=""/></span></div>自定义</label>';
                $('#packageSizeCheck').html(temp+operate);
            } else {
                $.toast('没有查到数据！', 3000);
            }
        },
        complete: function () {
            $.progressBar().close();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应，请稍候重试', 5000);
        }
    });
}

//查询上游折扣 计算折扣价格
function showDiscount(){
    $.ajax({
        url: '/api/upstream/account/detail',
        type: 'GET',
        dataType: 'json',
        data: {
            id: upstreamAccountInfoId
        },
        success: function (data) {
            //console.log(data);
            if (data.status == 0){
                var json = data.data;
                var discount = json.discount;
                //setCookie('discount',discount,{path:'/'}); 
                $('#Discount').val(discount);
            } else {
                $.toast('没有查到数据！', 3000);
            }
        },
        complete: function () {
            $.progressBar().close();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应，请稍候重试', 5000);
        }
    });
}

//批量添加产品数据
function ProductInfo(){
    //活动流程拼接字符串
    var Products = [];
    $("#modle-box>div").each(function(index,item){
        //将数据拼成对象放进product
        var product = {
            'upstreamAccountInfoId':$("input[name='upstreamAccountInfoId']",item).val(),
            'productName':$("input[name='productName']",item).val(),
            'productDesc':$("input[name='productDesc']",item).val(),
            'productCode':$("input[name='productCode']",item).val(),
            'standardPrice':$("input[name='standardPrice']",item).val(),
            'discountPrice':$("input[name='discountPrice']",item).val(),
            'packageSize':$("input[name='packageSize']",item).val(),
        };
        //将对象存进Products数组
        Products.push(product);
    });
    //数组转成JSON
    var json = JSON.stringify(Products);
    //活动流程的参数放到隐藏域内 
    $('input[name="channelPackageReqList"]').val(json);
}

/**
 *  功能描述：添加上游渠道产品
 *  请求方式：POST
 *  请求地址：/api/upstream/package/editor
 *  函数名称：addProduct
 */
function operateProduct(){
    $.ajax({
        url: '/api/upstream/package/batch_add',
        type: "post",
        dataType: "json",
        data: {'channelPackageReqList':$('#channelPackageReqList').val()},
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力加载数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            //console.log(data);
            if(data.status == 0){
                $.toast('操作成功',1000);
                setTimeout(function(){
                    window.location.href = 'pipe_product_list.html?id='+upstreamAccountInfoId+'&&belongType='+belongType;
                },1000);
            }else{
                $.toast(data.msg,1000);
            }
        },
        complete: function () {
            $.progressBar().close();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试', 5000);
        }
    })
}

/**
 * 获取cookie
 */
function getcookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null) return unescape(arr[2]); return '';
}
