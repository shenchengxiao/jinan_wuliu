
$(function(){

    getUserDetail();

    $('#btn_editUserInfo').on('click',function(){
        var validFlag = $('#edit_user_info_form').valid();
        if (validFlag) {
            editUserInfo();
        }else{
            return false;
        }

    });


    //自定义校验规则
    $.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

    /**
     *  功能描述：添加用户验证
     */

    $('#edit_user_info_form').validate({
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
                required: true,
                isMobile : true
            },
            registerIp:{
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
                required:'请输入手机号码',
                isMobile : "请正确填写手机号码"
            },
            registerIp:{
                required:'请输入注册IP',
            },
            startTime:{
                required:'请输入开始时间'
            },
            endTime:{
                required:'请输入结束时间'
            },
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
 *  功能描述：修改用户
 *  请求方式：POST
 *  请求地址：/api/user_manage/edit_user
 *  函数名称：addUserInfo
 */
function editUserInfo(){

    $.ajax({
        url:manage_path+'/api/user_manage/edit_user',
        type:'POST',
        dataType:'json',
        data:$('#edit_user_info_form').serialize(),
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


/**
 *  功能描述：获取详情
 *  请求方式：GET
 *  请求地址：/api/user_manage/detail
 *  函数名称：getUserDetail
 *  参数：id:ID
 */

function getUserDetail(){
    var id=getUrlParam("id");
    $.ajax({
        url:manage_path+'/api/user_manage/detail',
        type:'GET',
        dataType:'json',
        data:{
            id:id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                $('input[name=id]').val(json.id);
                $('input[name=userName]').val(json.userName);
                $('input[name=userNum]').val(json.userNum);
                $('input[name=password]').val(json.password);
                $('input[name=passwordVerify]').val(json.passwordVerify);
                $('input[name=phoneNumber]').val(json.phoneNumber);
                $('input[name=inLine]').val(json.inLine);
                
                //获取省份值并选中，省份下城市获取改变
                $('#province_id option').each(function () {
                    if ($(this).val() == json.province) {
                        $(this).attr('selected', true);
                        $('#province_id').trigger('change');
                    }
                });
                //获取城市值并选中，城市下县区获取改变
                $('#city_id option').each(function () {
                    if ($(this).val() == json.city) {
                        $(this).attr('selected', true);
                        $('#city_id').trigger('change');
                    }
                });
                //获取县区值并选中
                $('#county_id').val(json.county).selected;


                $('#is_abled_id').val(json.isAbled).selected;
                $('#is_manager_id').val(json.isManager).selected;
                $('#is_sync_id').val(json.isSync).selected;

                $('input[name=userEmail]').val(json.userEmail);
                $('input[name=postCode]').val(json.postCode);
                $('input[name=companyName]').val(json.companyName);
                $('input[name=registerIp]').val(json.registerIp);
                $('input[name=loginIp]').val(json.loginIp);
                $('input[name=startTime]').val(timestampFormat(json.startTime));
                $('input[name=endTime]').val(timestampFormat(json.endTime));
                $('input[name=identityNum]').val(json.identityNum);
                $('input[name=address]').val(json.address);
                $('input[name=lastQuitNum]').val(json.lastQuitNum);
                $('input[name=thisLoadNum]').val(json.thisLoadNum);
                $('input[name=hardNum]').val(json.hardNum);
                $('input[name=networkNum]').val(json.networkNum);
                $('input[name=temporaryCard]').val(json.temporaryCard);
                $('input[name=checkLimit]').val(json.checkLimit);
                $('input[name=checkNum]').val(json.checkNum);

                $('#is_send_id').val(json.isSend).selected;
                $('#is_receive_id').val(json.isReceive).selected;
                $('#is_receive_self').val(json.isReceiveSelf).selected;
                $('#is_binding_id').val(json.isBinding).selected;

                $('input[name=sendCity]').val(json.sendCity);
                $('input[name=receiveCity]').val(json.receiveCity);

                $('#platformType').val(json.platformType).selected;
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



