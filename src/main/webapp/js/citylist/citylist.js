'use strict'

$(function(){
	
    $('#btn_add_citylist').on('click',function(){
        //非空验证
        var b = $('#add_citylist_form').valid();//true false
        if(b){
        	addcitylist();//定制城市
        }else{
            return false;
        }
    });
    

    $('#add_citylist_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
        	userName:{
                required: true
            }
        },
        messages:{
        	userName:{
                required:'请输入账户'
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
 *  功能描述：定制城市
 *  请求方式：POST
 *  请求地址：/api/citylist/add
 *  函数名称：addcitylist
 */
function addcitylist(){
	$("#cityTree").combotree("setValues",$("#cityTree").combotree("getText"));
    $.ajax({
        url:manage_path+'/api/citylist/add',
        type:'POST',
        dataType:'json',
        data:$('#add_citylist_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',5000);
                setTimeout(function(){
                	location.reload();
                },1000);
            }else if(data.status == '500002'){
            	$.toast('该账号不存在',5000);
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










