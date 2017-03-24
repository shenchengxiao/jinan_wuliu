'use strict'
$(document).ready(function () {

    $('#btn_addProduct').on('click',function(){
        var validFlag = $('#add_product_form').valid();
        if (validFlag) {
            operateProduct();
        }else{
            return false;
        }
    });

    $('#btn_editProduct').on('click',function(){
        var validFlag = $('#edit_product_form').valid();
        if (validFlag) {
            operateProduct();
        }else{
            return false;
        }
    });

    //修改
    if(id != null){
        //获取详情
        fetchProductDetail();
    }

    $('#add_product_form,#edit_product_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            productName:{
                required:true
            },
            productDesc:{
                required:true
            },
            productCode:{
                required:true
            },
            standardPrice:{
                required:true
            },
            discountPrice:{
                required:true
            },
            packageSize:{
                required:true
            }
        },
        messages:{
            productName:{
                required:"请输入产品名称"
            },
            productDesc:{
                required:"请输入描述"
            },
            productCode:{
                required:"请输入编号"
            },
            standardPrice:{
                required:"请输入基准价"
            },
            discountPrice:{
                required:"请输入折扣价"
            },
            packageSize:{
                required:"请输入包大小"
            }
        },
        invalidHandler:function(event,validator){
            $('.alert-success').hide();
            $('.alert-error').show();
        },
        highlight:function(element){
            $(element).closest('.help-inline').removeClass('ok');
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        unhighlight:function(element){
            $(element).closest('.control-group').removeClass('error');
        },
        success:function(label){
            label.addClass('valid').addClass('help-inline ok').closest('.control-group').removeClass('error').addClass('success');
        },
        submitHandler:function(form){
            $('.alert-success').show();
            $('.alert-error').hide();
        }
    });

});

//定义全局变量 获取主键ID
var id=getUrlParam("id");
var upstreamAccountInfoId=getUrlParam("upstreamAccountInfoId");

//上游账户主键ID 用于提交表单
$("#upstreamAccountInfoId").val(upstreamAccountInfoId);

//查询上游折扣 计算折扣价格
function showDiscount()
{
    var standardPrice=document.getElementById("standardPrice").value;
    $.ajax({
        url: '/api/upstream/account/detail',
        type: 'GET',
        dataType: 'json',
        data: {
            id: upstreamAccountInfoId
        },
        success: function (data) {
            console.log(data);
            if (data.status == 0){
                var json = data.data;
                var discount = json.discount;
                var num = (Number(standardPrice) * Number(discount)).toFixed(4);
                $("#discountPrice").val(num);
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
/**
 *  功能描述：添加上游渠道产品
 *  请求方式：POST
 *  请求地址：/api/upstream/package/editor
 *  函数名称：addProduct
 */
function operateProduct() {

    var data = '';
    if (id == null){
        data = $('#add_product_form').serialize();
    }else {
        data = $('#edit_product_form').serialize()
    }
    $.ajax({
        url: "/api/upstream/package/editor",
        type: "post",
        dataType: "json",
        data: data,
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力加载数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            console.log(data);
            if(data.status == 0){
                $.toast('操作成功',1000);
                setTimeout(function(){
                    window.location.href = 'pipe_product_list.html?id='+upstreamAccountInfoId;
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
 *  功能描述：获取上游渠道产品详情
 *  请求方式：POST
 *  请求地址：/api/upstream/package/detail
 *  函数名称：fetchProductDetail
 */
function fetchProductDetail(){
    $.ajax({
        url:'/api/upstream/package/detail',
        type:'GET',
        dataType:'json',

        data:{
            id:id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            console.log(data);
            if(data.status == 0){
                var json = data.data;
                $('#id').val(id);
                $('#modifyType').val(0);
                $('#productName').val(json.productName);
                $('#productDesc').val(json.productDesc);
                $('#productCode').val(json.productCode);
                $('#standardPrice').val(json.standardPrice);
                $('#is_modify_stprice').val(json.standardPrice);

                $('#discountPrice').val(json.discountPrice);
                $('#is_modify_disprice').val(json.discountPrice);

                $('#packageSize').val(json.packageSize);
            }else {
                $.toast('没有查到数据！', 3000);
            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $.toast('服务器未响应，请稍候重试',5000);
        }
    });
}

