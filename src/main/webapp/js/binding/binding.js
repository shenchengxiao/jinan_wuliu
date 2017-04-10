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

function DateHandle(objDate)  
{  
            objDate=new Date(objDate); //创建一个日期对象表示当前时间     
            var year=objDate.getFullYear();   //四位数字年     
            var month=objDate.getMonth()+1;   //getMonth()返回的月份是从0开始的，还要加1     
            var date=objDate.getDate();     
            var hours=objDate.getHours();     
            var minutes=objDate.getMinutes();     
            var seconds=objDate.getSeconds();     
            var date = year+"-"+month+"-"+date; 
            //+" "+hours+":"+minutes+":"+seconds 
            return date;   
}

//清空modal里面的参数
function clearModal(){
	$('input[name=userName]').val('');
	$('input[name=hardpanNum]').val('');
	$('input[name=networkCard]').val('');
    $('input[name=temporaryCard]').val('');
    //是否有效 先清空在选中
    $('#isBinding').parent().removeClass('checked');
}

function isChecked(id,type){
	var enabled = 0;
	if(type == 0){
		enabled = 1;
	}
	$.ajax({
        url: manage_path+'/api/blackword/update_status',
        type: 'POST',
        dataType: 'json',
        data: {
            'bWId':id,
            'enabled':enabled
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getBlackwordList();
            }else{
                $.toast(data.msg,3000);
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
 *  请求方式：POST
 *  请求地址：/api/blackword/detail
 *  函数名称：getBlackwordDetail
 *  参数：id:ID
 */

function getBlackwordDetail(id){
	clearModal();
    $('#addBlackwordModal').modal('show');
    $.ajax({
        url:manage_path+'/api/blackword/detail',
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
                $('#myModalLabel').text('黑词修改');
                var json = data.data;

                $('input[name=bWId]').val(json.bWId);
                $('#blackWord').val(json.blackWord);

                //先清空在获取
                $('input[name=enabled]').parent().removeClass('checked');
                $('input[name=enabled]').eq(json.enabled).parent().addClass('checked');
                
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









