'use strict'
$(document).ready(function () {
    //获取省份信息
    getProvince();

    //获取详情
    fetchPipeDetail();
    //获取通道列表
    getAllversion();
    
    $('#btn_editPipe').on('click',function(){

        var validFlag = $('#edit_pipe_form').valid();
        if (validFlag) {
            addPipe();
        }else{
            return false;
        }
    });

    $('.disC1').on('click',function(){
        $('input[name="discount"]').attr('readonly',false);
    });
    $('.disC2').on('click',function(){
        $('input[name="discount"]').attr('readonly',true);
        $('input[name="discount"]').val(1);
    });

    $('#edit_pipe_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            channelName:{
                required:true
            },
            identify:{
                required:true
            },
            provinceId:{
                required:true
            },
            discount:{
                required:true
            }
        },
        messages:{
            channelName:{
                required:"请输入通道名称"
            },
            identify:{
                required:"请输入通道标识"
            },
            provinceId:{
                required:'请输入省份'
            },
            discount:{
                required:"请输入折扣"
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


/**
 *  功能描述：修改上游渠道账户
 *  请求方式：POST
 *  请求地址：/api/upstream/account/editor
 *  函数名称：addPipe
 */
function addPipe() {
    $.ajax({
        url: "/api/upstream/account/editor",
        type: "post",
        dataType: "json",
        data: $('#edit_pipe_form').serialize(),
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力加载数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            console.log(data);
            if(data.status == 0){
                $.toast('修改成功',1000);
                setTimeout(function(){
                    window.location.href = 'pipe_manage.html';
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
 *  功能描述：获取上游渠道账户详情
 *  请求方式：POST
 *  请求地址：/api/upstream/account/editor
 *  函数名称：addPipe
 */
function fetchPipeDetail(){
    var id=getUrlParam("id");
    $.ajax({
        url:'/api/upstream/account/detail',
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
                $('input[name="channelName"]').val(json.channelName);
                $('#identify').val(json.identify).selected;
                $('input[name="discount"]').val(json.discount);
                $('input[name="origin_discount"]').val(json.discount);
                $("#belongType_id").val(json.belongType).selected;
                $("#level_id").val(json.level).selected;
                provinceId = json.provinceId;
                $("#province").val(json.provinceId).selected;
                $('input[name=discountType]').eq(json.discountType).parent().addClass('checked');

                if (json.discountType == 1){
                    $('input[name="discount"]').attr('readonly',true);
                    $('input[name="discount"]').val(1);
                }
                $('input[name=roamType]').eq(json.roamType).parent().addClass('checked');
                $('input[name=channelType]').eq(json.channelType).parent().addClass('checked');
                $('input[name=charge_service_type]').eq(json.charge_service_type).parent().addClass('checked');
                $('input[name="score"]').val(json.score);
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

/**
 *  功能描述：获取通道参数配置列表
 *  请求方式：GET
 *  请求地址：/api/parameter/conf/list
 *  函数名称：getAllversion
 */
function getAllversion(){
    $('#identify').html('<option value="">选择通道标识</option>');
    $.ajax({
        url: '/api/parameter/conf/list',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (data) {
            console.log(data);
            if (data.status == 0) {
                var json = data.data;
                $.each(json, function (index, item) {
                    $('<option value="' + item.upstream_version+'">' + item.upstream_name + '</option>').appendTo($('#identify'));                
                });
            }else{
                $.toast(data.msg, 3000);
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


