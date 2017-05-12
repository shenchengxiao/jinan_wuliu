$(function(){

    getItemList();//先执行一次获取banner列表信息；
    $('#btn_search_item').on('click',function(){
        $("#pageNum").val(1);
        getItemList();//获取banner列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search_item").click();
        }
    });
    
  //创建modal弹出层class="modal"
//    $('#btn_edit').on('click',function(){
//    		
//    		clearModal();//清空modal弹出层里面的参数；
//    		$('#editCustomModal').modal('show');//modle层显示
//    	
//    });

    $('#btn_edit_custom').on('click',function(){
        //非空验证
        var b = $('#edit_custom_form').valid();//true false
        if(b){
        	updateUserCustom();
            $('#editCustomModal').modal('hide');//modle层隐藏
        }else{
            return false;
        }
    });
    
    $('#edit_custom_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
        	username:{
                required:true
            },
            platformType: {
                required: true
            }
        },
        messages:{

        	username:{
                required:'用户名称不能为空'
            },
            platformType:{
                required:'平台类型不能为空'
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

function updateCustom(id){
	
	clearModal();//清空modal弹出层里面的参数；
	$('#editCustomModal').modal('show');//modle层显示
	//获取该用户的所有权限信息回显数据
	getUserCustomById(id)
}
function getUserCustomById(id){
	/*if(!confirm("确定修改信息状态为已成交吗?")) return;*/
	$.ajax({
		url: manage_path+'/api/custom/getById',
		type: 'GET',
		dataType: 'json',
		data: {
			'id':id
		},
		beforeSend:function(){
			$.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
		},
		success:function(data){
			if(data.status == 0){
				var json = data.data;
				$('input[name=id]').val(json.id);
				$('#user_name').val(json.username);
				$('#isManager').val(json.isManager).selected;
				$('input[name=platformType]').val(json.platformType);
				$('#isBinding').val(json.isBinding).selected;
				$('#isPhoneLimit').val(json.isPhoneLimit).selected;
				$('#isReceiveCar').val(json.isReceiveCar).selected;
				$('#isReceiveGoods').val(json.isReceiveGoods).selected;
				$('#isSendCar').val(json.isSendCar).selected;
				$('#isSendGoods').val(json.isSendGoods).selected;
				$('#isLookPhone').val(json.isLookPhone).selected;
				
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
	$('input[name=username]').val('');
	$('input[name=platformType]').val('');
    /*$('input[name=ids]').val('');*/
    
//    $('#myModalLabel').text('发送消息通知');
}
function change(type){
	if(type == "是"){
		type = 1;
	}else{
		type = 0;
	}
	return type;
//    $('#myModalLabel').text('发送消息通知');
}

function getItemList(){
    var temp = "";
    var enabled="";
    var arr = [];
   $.ajax({
        url: manage_path+'/api/custom/userCustomList',
        type: 'GET',
        dataType: 'json',
        data: $('#item_list_form').serialize(), //通过表单id进行序列化提交
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
	                    
//	                    var num = item.userNum;
//	                    if(num == null){
//	                    	num = "";
//	                    }
	                  //截取字符串
                        var advert_content = item.receiveCity.toString();
                        if (advert_content.length > 10 && advert_content != 0){
                            advert_content = advert_content.substring(0,12)+"...";
                        }else {
                            advert_content
                        }
                        var advert_content2 = item.sendCity.toString();
                        if (advert_content2.length > 10 && advert_content2 != 0){
                        	advert_content2 = advert_content2.substring(0,12)+"...";
                        }else {
                        	advert_content2
                        }
//	                    console.log(item.endTime);
//	                    var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="modifyStatus_remove(' + item.itemId + ')"><i class="icon-remove icon-white"></i></a>';
	                    //操作按钮拼接
	                    operation = upDown + ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="修改权限" onclick="updateCustom(' + item.id + ')"><i class="icon-edit icon-white"></i></a> ';
	                    var citylist ='<a href="city_list.jsp?id='+item.id+'"></a>'
	                    temp += '<tr>'
	                        + '<td data-title="用户名称">' + item.username + '</td>'
	                        + '<td data-title="用户角色">' + item.isManager + '</td>'
	                        + '<td data-title="平台类型">' + item.platformType + '</td>'
	                        + '<td data-title="绑定设备">' + item.isBinding + '</td>'
	                        + '<td data-title="手机平台限制">' + item.isPhoneLimit + '</td>'
	                        + '<td data-title="接收车源">' + item.isReceiveCar + '</td>'
	                        + '<td data-title="接收货源">' + item.isReceiveGoods + '</td>'
	                        + '<td data-title="发布车源">' + item.isSendCar + '</td>'
	                        + '<td data-title="发布货源">' + item.isSendGoods + '</td>'
	                        + '<td data-title="接收城市" style="color:#0b94ea;max-width:200px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.receiveCity+'">'
	                        + '<a href="city_list.jsp?id='+item.userId+'">' + advert_content + '</a>' + '</td>'
	                        + '<td data-title="发布城市" style="color:#0b94ea;max-width:200px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.sendCity+'">'
	                        + '<a href="city_list.jsp?id='+item.userId+'">' + advert_content2 + '</a>' + '</td>'
	                        + '<td data-title="查看电话">' + item.isLookPhone + '</td>'
	                        + '<td data-title="操作">' + operation + '</td>'
	                        + '</tr>';
	                    
	                });
	                $('#item_List tbody').html(temp);
	                $("[data-toggle='popover']").popover();
	                //操作按钮hover显示详情
	                $("[data-toggle='tooltip']").tooltip();
	                // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
	                // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
	                page('#pagination', json.pagecount, json.pageindex, json.pagesize, getItemList, '#pageNum');
	                
                }else{
                    $.toast("没有查到数据",3000);
                    $('#item_List tbody').html('');
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
    });
}

function updateUserCustom(){
	/*if(!confirm("确定修改信息状态为已成交吗?")) return;*/
	$.ajax({
		url: manage_path+'/api/custom/update',
		type: 'POST',
		dataType: 'json',
		data: $('#edit_custom_form').serialize(),
		beforeSend:function(){
			$.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
		},
		success:function(data){
			if(data.status == 0){
				$.toast(data.msg,3000);
				getItemList();
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
            var date = year+"-"+month+"-"+date 
            +" "+hours+":"+minutes+":"+seconds; 
            return date;   
}











