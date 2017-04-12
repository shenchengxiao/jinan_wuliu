'use strict'

$(function(){
	
    $('#btn_send_sys').on('click',function(){
        //非空验证
        var b = $('#send_sys_form').valid();//true false
        if(b){
        	sendsysmessage();//修改电脑绑定
        }else{
            return false;
        }
    });
    $('#btn_clear').on('click',function(){
    	
    	$("#content").val("");
    });
    
    /**
     *  功能描述：添加黑词验证
     */

    $('#send_sys_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
        	content:{
                required: true
            }
        },
        messages:{
        	content:{
                required:'请输入消息内容'
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
 *  功能描述：修改电脑绑定
 *  请求方式：POST
 *  请求地址：/api/binding/add
 *  函数名称：addBlackword
 */
function sendsysmessage(){
    $.ajax({
        url:manage_path+'/api/message/sendsys',
        type:'POST',
        dataType:'json',
        data:{
        	'content':$("#content").val()
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('发送成功',5000);
                location.reload();
            }else{
            	$.toast('发送失败',5000);
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










