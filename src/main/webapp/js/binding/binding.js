'use strict'

$(function(){
	
    $('#btn_add_binding').on('click',function(){
        //非空验证
        var b = $('#add_binding_form').valid();//true false
        if(b){
        	addbinding();//修改电脑绑定
        }else{
            return false;
        }
    });
    
    /**
     *  功能描述：添加黑词验证
     */

    $('#add_binding_form').validate({
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
 *  功能描述：修改电脑绑定
 *  请求方式：POST
 *  请求地址：/api/binding/add
 *  函数名称：addBlackword
 */
function addbinding(){
	if($("#isBinding").is(':checked')==true){
		$("#isBinding").val(1);
	}
    $.ajax({
        url:manage_path+'/api/binding/add',
        type:'POST',
        dataType:'json',
        data:$('#add_binding_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',5000);
                location.reload();
            }else{
            	$.toast('该账号不存在',5000);
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










