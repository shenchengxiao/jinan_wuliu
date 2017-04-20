$(function(){

    getUserList();//先执行一次获取列表信息；
    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getUserList();//获取列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });
    
    
    var clicknum = 1;
    var idsArr  = [];
    //ids 传递ids的集合对象
    function setIdsInfo(){
        var arrId = [];
        $.each(idsArr,function(index,item){
            var IDs = {
                id:item
            };
            arrId.push(IDs);
        });

        var json = JSON.stringify(arrId);
        $('#ids').val(json);
    }



    //全选||非全选
    $('#btn_chooseAll').on('click',function(){
        if(clicknum%2){    
            //第一次点击
            $('input[name="idArr"]').each(function(i){
                idsArr.push($(this).val());
            });
            setIdsInfo();
            console.log(idsArr);
            $('#btn_chooseAll').text('取消全选');
            $('input[name="chooseTag"]').prop({
                checked: 'checked'
            }).parent().css({
                color:'green'
            });
        }else{
            //第二次点击
            idsArr.length=0;
            setIdsInfo();
            $('#btn_chooseAll').text('全选');
            $('input[name="chooseTag"]').prop({
                checked: ''
            }).parent().css({
                color:'#000'
            });
            console.log(idsArr);
        }
        clicknum++; 
    });


    $('#user_manage_list').on('change','input[name="chooseTag"]',function(){
        /*$this = $(this);*/
        var thisID = $(this).next('input').val();
        if($(this).is(':checked')){
            idsArr.push(thisID);
            setIdsInfo(); 
            $(this).parent().css({
                color:'green'
            });
            console.log(idsArr);
        }else{
            removeInArr(thisID);
            setIdsInfo();
            $(this).parent().css({
                color:'#000'
            });
            console.log(idsArr);
        }
    });

    //查找某个值在数组中的位置
    function indexOfInArr(val) {
        for (var i = 0; i < idsArr.length; i++) {
            if (idsArr[i] == val) return i;
        }
        return -1;
    };

    //定义一个remove的方法
    function removeInArr(val) {
        var index = indexOfInArr(val);
        if (index > -1) {
            idsArr.splice(index, 1);
        }
    };


  //创建modal弹出层class="modal"
    $('#btn_send').on('click',function(){
    	var ids = $('#ids').val();
    	if(ids != null && ids.length > 0){
    		
    		clearModal();//清空modal弹出层里面的参数；
    		$('#sendUserMessageModal').modal('show');//modle层显示
    	}else{
    		alert("请先选择用户");
    	}
    	
    });

    $('#btn_send_usermessage').on('click',function(){
        //非空验证
        var b = $('#send_usermessagee_form').valid();//true false
        if(b){
        	sendsysmessage();//添加黑词
            $('#sendUserMessageModal').modal('hide');//modle层隐藏
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

    $('#send_usermessagee_form').validate({
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

function sendsysmessage(){
    $.ajax({
        url:manage_path+'/api/message/send',
        type:'POST',
        dataType:'json',
        data:$('#send_usermessagee_form').serialize(),
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

//清空modal里面的参数
function clearModal(){
	$('textarea[name=content]').val('');
    /*$('input[name=ids]').val('');*/
    
    $('#myModalLabel').text('发送消息通知');
}

/**
 *  功能描述：获取用户列表信息
 *  请求方式：GET
 *  请求地址：/api/user_manage/list
 *  函数名称：getUserList
 */

function getUserList(){

    $.ajax({
        url: manage_path+'/api/user_manage/list',
        type: 'GET',
        dataType: 'json',
        data: $('#user_list_form').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0) {
                var json = data.data;
                var list = json.result;
                var temp = "";
                if (list != null && list.length>0){
                    var operation, upDown = ''; //操作按钮
                    $.each(list, function (index, item) {
                        var platform = item.platformType;
                        switch (platform){
                            case 0:
                                platform = 'windows'
                                break;
                            case 1:
                                platform = 'iOS'
                                break;
                            case 2:
                                platform = 'Andoird'
                                break;
                        }
                        var enabled = item.isAbled;//banner状态
                        if (enabled == 0) {
                            enabled = "无效";
                            upDown = ' <a href="#" class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="modifyStatus(' + item.id + ',1)"><i class="icon-ok-circle"></i></a>';
                        } else {
                            enabled = "有效";
                            upDown = ' <a href="#" class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="modifyStatus(' + item.id + ',0)"><i class="icon-ban-circle"></i></a>';
                        }

                        var Deleted ='<a href="user_detail.jsp?id='+item.id+'" class="btn mini purple" data-toggle="tooltip" data-placement="top" title="查看" ><i class="icon-tasks"></i></a>&nbsp;'

                        var push ='<a href="user_push.jsp?id='+item.id+'" class="btn yellow mini" data-toggle="tooltip" data-placement="top" title="发布" ><i class="icon-hand-right"></i></a>&nbsp;'

                        var citylist ='<a href="city_list.jsp?id='+item.id+'" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="定制城市" ><i class="icon-globe"></i></a>&nbsp;'
                        
                        //操作按钮拼接
                        operation = upDown + ' <a href="user_edit.jsp?id='+item.id+'" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" ><i class="icon-edit icon-white"></i></a> ' 
                        	+ Deleted + push + citylist;

                        temp += '<tr>'
                        	+'<td data-title="">' +'<input type="checkbox" name="chooseTag"><input type="hidden" name="idArr" value="'+item.id+'"/>'+ '</td>'
                            + '<td data-title="用户名称">' + item.userName + '</td>'
                            + '<td data-title="用户编号">' + item.userNum + '</td>'
                            + '<td data-title="用户密码">' + item.password + '</td>'
                            + '<td data-title="联系电话">' + item.phoneNumber + '</td>'
                            + '<td data-title="省份">' + item.province + '</td>'
                            + '<td data-title="城市">' + item.city + '</td>'
                            + '<td data-title="县区">' + item.county + '</td>'
                            + '<td data-title="服务开始时间">' + timestampFormat(item.startTime) + '</td>'
                            + '<td data-title="服务结束时间">' + timestampFormat(item.endTime) + '</td>'
                            + '<td data-title="硬盘号">' + item.hardNum + '</td>'
                            + '<td data-title="网卡号">' + item.networkNum + '</td>'
                            + '<td data-title="状态">'+  enabled +  '</td>'
                            + '<td data-title="平台">'+  platform +  '</td>'
                            + '<td data-title="操作">' + operation + '</td>'
                            + '</tr>';
                    })
                    $('#user_manage_list tbody').html(temp);
                    // $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    $("[data-toggle='modal']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
                    // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getUserList, '#pageNum');

                }else{
                    $.toast("没有查到数据",3000);
                    $('#user_manage_list tbody').html('');
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
 *  功能描述：启用、禁用
 *  请求方式：POST
 *  请求地址：/api/user_manage/modify_status
 *  函数名称：modifyStatus
 *  参数：id:banner主键ID; beUsed:启用禁用;
 */

function modifyStatus(id,beUsed){
    $.ajax({
        url:manage_path+ '/api/user_manage/modify_status',
        type: 'POST',
        dataType: 'json',
        data: {
            'id':id,
            'status':beUsed
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',3000);
                setTimeout(function(){
                    getUserList();
                },500)
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
