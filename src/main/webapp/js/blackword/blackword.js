$(function(){

    getBlackwordList();//先执行一次获取banner列表信息；
    
    $('#btn_search1').on('click',function(){
        $("#pageNum").val(1);
        getBlackwordList();//获取banner列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
        	$('#btn_search1').click();
        }
    });
    
    //创建modal弹出层class="modal"
    $('#creat_banner_icon').on('click',function(){
        clearModal();//清空modal弹出层里面的参数；
        $('#addBlackwordModal').modal('show');//modle层显示
    });

    $('#btn_add_blackword').on('click',function(){
        //非空验证
        var b = $('#add_blackword_form').valid();//true false
        if(b){
        	addBlackword();//添加黑词
            $('#addBlackwordModal').modal('hide');//modle层隐藏
        }else{
            return false;
        }
    });

    /**
     *  功能描述：添加黑词验证
     */

    $('#add_blackword_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            blackWord:{
                required: true
            }
        },
        messages:{
            blackWord:{
                required:'请输入黑词'
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
 *  功能描述：添加黑词
 *  请求方式：POST
 *  请求地址：/api/blackword/add
 *  函数名称：addBlackword
 */
function addBlackword(){
    $.ajax({
        url:manage_path+'/api/blackword/add',
        type:'POST',
        dataType:'json',
        data:$('#add_blackword_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',5000);
                getBlackwordList();
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

//清空modal里面的参数
function clearModal(){
	$('input[name=bWId]').val('');
    $('input[name=blackWord]').val('');
    //是否有效 先清空在选中
    $('input[name=enabled]').parent().removeClass('checked');
    $('input[name=enabled]').eq(1).parent().addClass('checked');
    
    $('#myModalLabel').text('新增广告');
}

/**
 *  功能描述：获取广告列表信息
 *  请求方式：GET
 *  请求地址：/api/blackword/list
 *  函数名称：getBlackwordList
 */

function getBlackwordList(){
    var temp = "";
    var enabled="";
    $.ajax({
        url: manage_path+'/api/blackword/list',
        type: 'GET',
        cache:false,
        dataType: 'json',
        data: $('#blackword_list_form').serialize(), //通过表单id进行序列化提交
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0) {
                var json = data.data;
                var list = json.result;
                var operation, upDown = ''; //操作按钮
                if (list != null && list.length>0){
	                $.each(list, function (index, item) {
	                    var adStatus = item.enabled;//banner状态
	//                    console.log(item.endTime);
	                    var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="modifyStatus_remove(' + item.bWId + ')"><i class="icon-remove icon-white"></i></a>';
	                    //操作按钮拼接
	                    operation = upDown + ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" onclick="getBlackwordDetail(' + item.bWId + ')"><i class="icon-edit icon-white"></i></a> ' + Deleted;
	                    if(item.enabled == 1){
	                    	enabled = '<input id="enabled" type="checkbox" onclick="isChecked('+item.bWId+','+item.enabled+')" checked="checked">';
	                    }else{
	                    	enabled = '<input id="enabled" type="checkbox" onclick="isChecked('+item.bWId+','+item.enabled+')">';
	                    }
	                    
	                    temp += '<tr>'
	                        + '<td data-title="黑名词">' + item.blackWord + '</td>'
	                        + '<td data-title="创建时间">' + DateHandle(item.createTime) + '</td>'
	                        + '<td data-title="是否有效">' + enabled + '</td>'
	                        + '<td data-title="操作">' + operation + '</td>'
	                        + '</tr>';
	                    
	                });
	                $('#blackword_List tbody').html(temp);
	                $("[data-toggle='popover']").popover();
	                //操作按钮hover显示详情
	                $("[data-toggle='tooltip']").tooltip();
	                // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
	                // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
	                page('#pagination', json.pagecount, json.pageindex, json.pagesize, getBlackwordList, '#pageNum');
	                
                }else{
                    $.toast("没有查到数据",3000);
                    $('#blackword_List tbody').html('');
                    if($('#pagination').html().length > 0){
                        $('#pagination').jqPaginator('destroy');
                    }
                }
            }

        },
        complete:function(){
            $.progressBar().close();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            $.toast('服务器未响应,请稍候重试',5000);
        }
    })
}

function modifyStatus_remove(id){
    if(!confirm("确定删除吗?")) return;
    $.ajax({
        url: manage_path+'/api/blackword/delete',
        type: 'POST',
        dataType: 'json',
        data: {
            'id':id
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
    })

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
 *  请求方式：GET
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
        cache:false,
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
                getBlackwordList();
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









