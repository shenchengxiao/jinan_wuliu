'use strict'

$(function(){

    getBannerList();//先执行一次获取banner列表信息；
    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getBannerList();//获取banner列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });
    //创建modal弹出层class="modal"
    $('#creat_banner_icon').on('click',function(){
        clearModal();//清空modal弹出层里面的参数；
        $('#addBannerModal').modal('show');//modle层显示
    });

    $('#btn_add_banner').on('click',function(){
        //非空验证
        var b = $('#add_banner_form').valid();//true false
        if(b){
            addBanner();//添加新的banner;
            $('#addBannerModal').modal('hide');//modle层隐藏
        }else{
            return false;
        }
    });




    /**
     *  功能描述：添加banner验证
     */

    $('#add_banner_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            bannerName:{
                required:true
            }
        },
        messages:{
            bannerName:{
                required:'请输入banner名称'
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

//获取IP和端口号
var ip_port_path = window.location.protocol + '//' + window.location.host+'/';
/**
 *  功能描述：获取banner列表信息
 *  请求方式：GET
 *  请求地址：/api/banner/list
 *  函数名称：getBannerList
 */

function getBannerList(){

    $.ajax({
        url:manage_path+ '/api/banner/list',
        type: 'GET',
        dataType: 'json',
        data: $('#banner_list_form').serialize(), //通过表单id进行序列化提交
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0) {
                var json = data.data;
                var list = json.result;
                var temp = "";
                var operation, upDown = ''; //操作按钮
                if (list != null && list.length > 0) {
                    $.each(list, function (index, item) {
                        var adStatus = item.status;//banner状态

                        if (adStatus == 1) {
                            adStatus = "启用";
                            upDown = ' <a href="#" class="btn mini green" data-toggle="tooltip" data-placement="top" title="禁用" onclick="modifyStatus(' + item.id + ',0)"><i class="icon-ok-circle"></i></a>';
                        } else {
                            adStatus = "禁用";
                            upDown = ' <a href="#" class="btn mini grey" data-toggle="tooltip" data-placement="top" title="启用" onclick="modifyStatus(' + item.id + ',1)"><i class="icon-ban-circle"></i></a>';
                        }

                        var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="deleteBanner(' + item.id + ')"><i class="icon-remove icon-white"></i></a>';
                        //操作按钮拼接
                        operation = upDown + ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" onclick="getBannerDetail(' + item.id + ')"><i class="icon-edit icon-white"></i></a> ' + Deleted;
                        temp += '<tr>'
                            + '<td data-title="Banner名称" >' + item.bannerName + '</td>'
                            + '<td data-title="Banner图片"><img src="' + ip_port_path + item.imageUrl + '" style="width: 50px;height: 50px;"></td>'
                            + '<td data-title="状态">' + adStatus + '</td>'
                            + '<td data-title="操作">' + operation + '</td>'
                            + '</tr>';

                    })
                    $('#banner_List tbody').html(temp);
                    $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getBannerList, '#pageNum');
                } else {
                    $.toast("没有查到数据", 3000);
                    $('#banner_List tbody').html('');
                    if ($('#pagination').html().length > 0) {
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
 *  请求地址：/api/banner/modify
 *  函数名称：modifyStatus
 *  参数：id:banner主键ID; beUsed:启用禁用;
 */

function modifyStatus(id,beUsed){
    $.ajax({
        url:manage_path+ '/api/banner/modify',
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
                    getBannerList();
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

/**
 *  功能描述：删除
 *  请求方式：POST
 *  请求地址：/api/banner/delete
 *  函数名称：deleteBanner
 *  参数：id:banner主键ID
 */

function deleteBanner(id){
    if(!confirm("确定删除吗?")) return;
    $.ajax({
        url:manage_path+ '/api/banner/delete',
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
                $.toast('操作成功',3000);
                setTimeout(function(){
                    getBannerList();
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

/**
 *  功能描述：获取详情
 *  请求方式：GET
 *  请求地址：/api/banner/detail
 *  函数名称：getBannerDetail
 *  参数：id:bannerID
 */

function getBannerDetail(id){
    $('#addBannerModal').modal('show');
    $.ajax({
        url:manage_path+'/api/banner/detail',
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
                $('#myModalLabel').text('修改Banner');
                var json = data.data;
                $('input[name=id]').val(json.id);
                $('input[name=bannerName]').val(json.bannerName);
                $('input[name=imageUrl]').val(json.imageUrl);
                $('#logoImg').attr('src', ip_port_path + json.imageUrl);

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
 *  功能描述：添加banner
 *  请求方式：POST
 *  请求地址：/api/banner/editor
 *  函数名称：addBanner
 *  参数：id:bannerID
 */
function addBanner(){
    $.ajax({
        url:manage_path+'/api/banner/editor',
        type:'POST',
        dataType:'json',
        data:$('#add_banner_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',3000);
                setTimeout(function(){
                    // getBannerList();
                    window.location.reload();
                },500)
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
    $('input[name=bannerName]').val('');
    $('#logoImg').removeAttr('src');
    $('input[name=imageUrl]').val('');
    $('#myModalLabel').text('新增Banner');
}




