
$(function(){
    // 点击按钮提交
    $("#btn_login").click(function(){
        var b = $('#form_login').valid();
        if(b){
            login();
        }else{
            return false;
        }
    });
    //enter提交
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_login").click();
        }
    });

    $("#changeImage").click(function () {
        changeImg();
    });



 /**
 *  功能描述：表单验证
 **/ 


$('#form_login').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            userName:{
                required:true
            },
            passwd:{
                required:true
            },
            verifyCode:{
                maxlength:4,
                required:true
            }
        },
        messages:{
            userName:{
                required:'请输入用户名'
            },
            passwd:{
                required:'请输入密码'
            },
            verifyCode:{
                maxlength:'最多输入四个字符',
                required:'请输入验证码'
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

// 提交登录
function login(){
    var userName = $("#UserName").val();
    var password = $("#UserPass").val();
    var verifyCode = $("#veryCode").val();
    $.ajax({
        url:manage_path+'/api/user/login',
        type: 'POST',
        dataType: 'json',
        beforeSend:function(data){
              $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        data: {
            "userName":userName,
            "passwd":password,
            "verifyCode":verifyCode
        },
        success:function(data) {
            if(data.status == 0){
                window.location.href = manage_path+'/views/index.jsp';
            }else{
                changeImg();
                $.toast(data.msg,3000);
            }
        },
        complete:function(){
             $.progressBar().close();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
};

//获取项目路径
function getRootPath()
{
    var pathName = window.location.pathname.substring(1);

    var webName = pathName == '' ? '' : pathName.substring(0,pathName.indexOf('/'));

    var path = window.location.protocol + '//' + window.location.host + '/'+ webName ;

    return path;

}
//定义路径全局变量
var manage_path=getRootPath();


//刷新验证码
function changeImg(){
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上随机数
    document.getElementById("imgObj").src=manage_path+"/api/verify/code?t="+Math.random();
}

