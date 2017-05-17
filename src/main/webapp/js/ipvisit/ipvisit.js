'use strict'

$(function(){
	
	getIpvisitList();
	//创建modal弹出层class="modal"
    $('#creat_server_icon').on('click',function(){
        clearModal();//清空modal弹出层里面的参数；
        $('#addServerModal').modal('show');//modle层显示

    });

    $('#btn_add_server').on('click',function(){
        //非空验证
        var b = $('#add_server_form').valid();//true false
        if(b){
            addServer();//添加新的服务器
            $('#addServerModal').modal('hide');//modle层隐藏
        }else{
            return false;
        }
    });
    
    /**
     *  功能描述：添加服务器验证
     */

    $('#add_server_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            ip:{
                required:true
            },
            port: {
                required: true
            },
            createTime:{
                required: true
            }
        },
        messages:{
        	ip:{
                required:'请输入服务器外网ip'
            },
            port:{
                required:'请输入服务器端口号'
            },
            createTime:{
                required:'请输入添加时间'
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
 *  功能描述：获取IP访问列表信息
 *  请求方式：GET
 *  请求地址：/api/ipvisit/list
 *  函数名称：getBlackwordList
 */

function getIpvisitList(){
    var temp = "";
    var enabled="";
    $.ajax({
        url: manage_path+'/api/ipvisit/list',
        type: 'GET',
        cache:false,
        dataType: 'json',
        data: $('#ipvisit_list_form').serialize(),
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
	                	var _typeId = item.status;//banner状态
	                    if(_typeId == 0){
	                    	_typeId = "未启用";
	                    }else if(_typeId == 1){
	                    	_typeId = "启用";
	                    }else{
	                    	_typeId = "";
	                    }
	                    var adStatus = item.enabled;//banner状态
	//                    console.log(item.endTime);
	                    var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="deleteServer(' + item.id + ')"><i class="icon-remove icon-white"></i></a>';
	                    
	                    var upd = ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" onclick="getBlackwordDetail(' + item.id + ')"><i class="icon-edit icon-white"></i></a> ';
	                    //操作按钮拼接
	                    operation = upDown + Deleted;
	                    
	                    temp += '<tr>'
	                        + '<td data-title="服务器IP">' + item.ip + '</td>'
	                        + '<td data-title="端口号">' + item.port + '</td>'
	                        + '<td data-title="域名">' + item.domain + '</td>'
	                        + '<td data-title="开始使用时间">' + DateHandle(item.createTime) + '</td>'
	                        + '<td data-title="功能描述">' + item.functionDesc + '</td>'
	                        + '<td data-title="状态">' + _typeId + '</td>'
	                        + '<td data-title="操作">' + operation + '</td>'
	                        + '</tr>';
	                    
	                });
	                $('#ipvisit_List tbody').html(temp);
	                $("[data-toggle='popover']").popover();
	                //操作按钮hover显示详情
	                $("[data-toggle='tooltip']").tooltip();
	                // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
	                // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
	                page('#pagination', json.pagecount, json.pageindex, json.pagesize, getIpvisitList, '#pageNum');
	                
                }else{
                    $.toast("没有查到数据",3000);
                    $('#ipvisit_List tbody').html('');
                    if($('#pagination').html().length > 0){
                        $('#pagination').jqPaginator('destory');
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


/**
 * 添加服务器
 */
function addServer(){

    $.ajax({
        url:manage_path+'/api/ipvisit/add',
        type:'POST',
        dataType:'json',
        data:$('#add_server_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',3000);
                setTimeout(function(){
                    window.location.reload();
                },500);
                // getAdvertList();
            }else {
                $.toast('操作失败,系统错误',3000);
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
 * 删除
 * @param id
 */
function deleteServer(id){
    if(!confirm("确定删除吗?")) return;
    $.ajax({
        url:manage_path+ '/api/ipvisit/delete',
        type: 'POST',
        dataType: 'json',
        data: {'id':id},
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getIpvisitList();
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
//清空modal里面的参数
function clearModal(){
    $('input[name=ip]').val('');
    //是否有效 先清空在选中
    $('input[name=status]').parent().removeClass('checked');
    $('input[name=status]').eq(1).parent().addClass('checked');
    $('input[name=port]').val('');
    $('input[name=domain]').val('');
    $('input[name=functionDesc]').val('');

    $('#createTime').val('');

    $('#myModalLabel').text('新增服务器');
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
            var date = year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;
            
            return date;   
}








