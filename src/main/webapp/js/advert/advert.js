
$(function(){

    getAdvertList();//先执行一次获取列表信息；
    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getAdvertList();//获取列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });
    //创建modal弹出层class="modal"
    $('#creat_advert_icon').on('click',function(){
        clearModal();//清空modal弹出层里面的参数；
        $('#addAdvertModal').modal('show');//modle层显示

    });

    $('#btn_add_advert').on('click',function(){
        //非空验证
        var b = $('#add_advert_form').valid();//true false
        if(b){
            addAdvert();//添加新广告
            $('#addAdvertModal').modal('hide');//modle层隐藏
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
     *  功能描述：添加广告验证
     */

    $('#add_advert_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            startTime:{
                required:true
            },
            endTime: {
                required: true
            },
            price:{
                required: true
            },
            linkedName:{
                required: true
            },
            phoneNumber:{
                required: true,
                isMobile : true
            },
            advertTitle:{
                required: true
            },
            content:{
                required: true
            },
            linkUrl:{
                required: true
            }
        },
        messages:{
            startTime:{
                required:'请输入开始时间'
            },
            endTime:{
                required:'请输入结束时间'
            },
            price:{
                required:'请输入价格'
            },
            linkedName:{
                required:'请输入联系人'
            },
            phoneNumber:{
                required:'请输入手机号码',
                isMobile : "请正确填写手机号码"
            },
            advertTitle:{
                required: "请输入广告标题"
            },
            content:{
                required:'请选择广告内容'
            },
            linkUrl:{
                required:'链接地址不能为空'
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
 *  功能描述：获取广告列表信息
 *  请求方式：GET
 *  请求地址：/api/advert/list
 *  函数名称：getAdvertList
 */

function getAdvertList(){
    var temp = "";
    $.ajax({
        url: manage_path+'/api/advert/list',
        type: 'GET',
        dataType: 'json',
        data: $('#advert_list_form').serialize(), //通过表单id进行序列化提交
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0) {
                var json = data.data;
                var list = json.result;
                if (list != null && list.length>0){
                    var operation, upDown = ''; //操作按钮
                    $.each(list, function (index, item) {
                        var adStatus = item.beUsed;//banner状态
                        if (adStatus == 0) {
                            adStatus = "无效";
                            upDown = ' <a href="#" class="btn mini green" data-toggle="tooltip" data-placement="top" title="上架" onclick="modifyStatus(' + item.id + ',1)"><i class="icon-ok-circle"></i></a>';
                        } else {
                            adStatus = "有效";
                            upDown = ' <a href="#" class="btn mini grey" data-toggle="tooltip" data-placement="top" title="下架" onclick="modifyStatus(' + item.id + ',0)"><i class="icon-ban-circle"></i></a>';
                        }

                        var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="deleteAdvert(' + item.id + ')"><i class="icon-remove icon-white"></i></a>';
                        //操作按钮拼接
                        operation = upDown + ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" onclick="getAdvertDetail(' + item.id + ')"><i class="icon-edit icon-white"></i></a> ' + Deleted;

                        //截取字符串
                        var advert_content = item.content.toString();
                        if (advert_content.length > 10 && advert_content != 0){
                            advert_content = advert_content.substring(0,7)+"...";
                        }else {
                            advert_content
                        }


                        temp += '<tr>'
                            + '<td data-title="开始时间">' + item.startTime + '</td>'
                            + '<td data-title="结束时间">' + item.endTime + '</td>'
                            + '<td data-title="价格">' + item.price + '</td>'
                            + '<td data-title="是否有效">' + adStatus + '</td>'
                            + '<td data-title="广告内容" style="color:#0b94ea;max-width:200px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.content+'" >'
                            +  advert_content
                            +  '</td>'
                            + '<td data-title="操作">' + operation + '</td>'
                            + '</tr>';
                    })
                    $('#advert_list tbody').html(temp);
                    $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    $("[data-toggle='modal']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
                    // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getAdvertList, '#pageNum');

                }else{
                    $.toast("没有查到数据",3000);
                    $('#advert_list tbody').html('');
                    if($('#pagination').html().length > 0){
                        $('#pagination').jqPaginator('destory');
                    }
                }
            }else {
                $.toast(data.message,3000);
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
 *  功能描述：失效有效
 *  请求方式：POST
 *  请求地址：/api/advert/modify
 *  函数名称：modifyStatus
 *  参数：id:主键ID;  beUsed:是否失效;
 */

function modifyStatus(id,beUsed){
    $.ajax({
        url:manage_path+ '/api/advert/modify',
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
                $.toast(data.msg,3000);
                getAdvertList();
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


/**
 *  功能描述：删除
 *  请求方式：POST
 *  请求地址：/api/advert/delete
 *  函数名称：deleteAdvert
 *  参数：id:主键ID;
 */

function deleteAdvert(id){
    if(!confirm("确定删除吗?")) return;
    $.ajax({
        url:manage_path+ '/api/advert/delete',
        type: 'POST',
        dataType: 'json',
        data: {'id':id},
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getAdvertList();
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

/**
 *  功能描述：获取详情
 *  请求方式：POST
 *  请求地址：/api/advert/detail
 *  函数名称：getAdvertDetail
 *  参数：id:ID
 */

function getAdvertDetail(id){
    $('#addAdvertModal').modal('show');
    $.ajax({
        url:manage_path+'/api/advert/detail',
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
                $('#myModalLabel').text('广告修改');
                var json = data.data;

                $('input[name=id]').val(json.id);
                $('#startTime').val(json.startTime);
                $('#endTime').val(json.endTime);
                $('#price').val(json.price);
                $('#linkedName').val(json.linkedName);
                $('#phoneNumber').val(json.phoneNumber);
                $('#content').val(json.content);
                $('#linkUrl').val(json.linkUrl);
                $('#advertTitle').val(json.advertTitle);

                //先清空在获取
                $('input[name=beUsed]').parent().removeClass('checked');
                $('input[name=beUsed]').eq(json.beUsed).parent().addClass('checked');

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




/**
 *  功能描述：添加广告
 *  请求方式：POST
 *  请求地址：/api/advert/editor
 *  函数名称：addAdvert
 */
function addAdvert(){

    $.ajax({
        url:manage_path+'/api/advert/editor',
        type:'POST',
        dataType:'json',
        data:$('#add_advert_form').serialize(),
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


//清空modal里面的参数
function clearModal(){
    $('input[name=price]').val('');
    //是否有效 先清空在选中
    $('input[name=beUsed]').parent().removeClass('checked');
    $('input[name=beUsed]').eq(1).parent().addClass('checked');
    $('#content').val('');
    $('input[name=phoneNumber]').val('');
    $('input[name=linkedName]').val('');
    $('input[name=id]').val('');

    $('#startTime').val('');
    $('#endTime').val('');
    $('#linkUrl').val('');

    $('#myModalLabel').text('新增广告');
}


