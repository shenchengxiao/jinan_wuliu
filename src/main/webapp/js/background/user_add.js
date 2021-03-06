
$(function(){

    $('#btn_addUserInfo').on('click',function(){
        var validFlag = $('#add_user_info_form').valid();
        if (validFlag) {
            addUserInfo();
        }else{
            return false;
        }

    });

    //用户编号监听事件
    $('#userName').bind('input propertychange', function() {
        $('#userNum').val($(this).val());
    });



    //自定义校验规则
    // $.validator.addMethod("isMobile", function(value, element) {
    //     var length = value.length;
    //     var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    //     return this.optional(element) || (length == 11 && mobile.test(value));
    // }, "请正确填写您的手机号码");

    /**
     *  功能描述：添加用户验证
     */

    $('#add_user_info_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            userName:{
                required:true
            },
            userNum: {
                required: true
            },
            password:{
                required: true,
                rangelength:[6,18]
            },
            passwordVerify:{
                required: true,
                equalTo: "#password"
            },
            phoneNumber:{
                required: true
            },
            startTime:{
                required: true
            },
            endTime:{
                required: true
            }
        },
        messages:{

            userName:{
                required:'请输入用户名'
            },
            userNum:{
                required:'请输入用户编号'
            },
            password:{
                required:'请输入用户密码',
                rangelength:'用户密码不能小于6个字符'
            },
            passwordVerify:{
                required:'请输入确认密码',
                equalTo: "两次输入密码不一致"
            },
            phoneNumber:{
                required:'请输入手机号码'
            },
            startTime:{
                required:'请输入开始时间'
            },
            endTime:{
                required:'请输入结束时间'
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
 *  功能描述：添加用户
 *  请求方式：POST
 *  请求地址：/api/user_manage/edit_user
 *  函数名称：addUserInfo
 */
function addUserInfo(){

    $.ajax({
        url:manage_path+'/api/user_manage/edit_user',
        type:'POST',
        dataType:'json',
        data:$('#add_user_info_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',1000);
                setTimeout(function(){
                    window.location.href = 'user_list.jsp';
                },1000);
            }else {
                $.toast('操作失败,系统错误',1000);
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

//校验用户是否已存在
$("#userName").blur(function(){
    $.ajax({
        url:manage_path+'/api/user_manage/verify',
        type:'GET',
        cache:false,
        dataType:'json',
        data:{userName:$('#userName').val()},
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                if (data.data.length > 0) {
                    var txt = "此用户已存在,请重新输入！";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
                    $('#userName').val('');
                    $('#userNum').val('');
                }
            }else {
                $.toast('操作失败,系统错误',1000);
            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
})


