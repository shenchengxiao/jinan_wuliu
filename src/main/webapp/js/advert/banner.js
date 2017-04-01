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
            adName:{
                required:true
            },
            start_time:{
                required:function(){
                    if($('#adType').val() == 7){
                        return true;
                    }else{
                        return false;
                    }
                }
            },
            end_time:{
                required:function(){
                    if($('#adType').val() == 7){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        },
        messages:{
            adName:{
                required:'请输入banner名称'
            },
            start_time:{
                required:'请选择开始时间'
            },
            end_time:{
                required:'请选择结束时间'
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
 *  功能描述：获取banner列表信息
 *  请求方式：GET
 *  请求地址：/api/banner/list
 *  函数名称：getBannerList
 */

function getBannerList(){
    var temp = "";
    $.ajax({
        url: '/api/banner/list',
        type: 'GET',
        dataType: 'json',
        data: $('#banner_list_form').serialize(), //通过表单id进行序列化提交
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                var list = json.result;
                var operation,upDown = ''; //操作按钮
                $.each(list,function(index,item){
                    var adStatus =  item.status;//banner状态
                    //banner status状态：0下架 1上架
                    if(adStatus == 0){
                        adStatus = "已下架";
                        upDown = ' <a href="#" class="btn mini green" data-toggle="tooltip" data-placement="top" title="上架" onclick="modifyStatus('+item.id+','+item.adType+',1)"><i class="icon-ok-circle"></i></a>';
                    }else{
                        adStatus = "已上架";
                        upDown = ' <a href="#" class="btn mini grey" data-toggle="tooltip" data-placement="top" title="下架" onclick="modifyStatus('+item.id+','+item.adType+',0)"><i class="icon-ban-circle"></i></a>';
                    }

                    var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="modifyStatus_remove('+item.id+','+item.adType+',1)"><i class="icon-remove icon-white"></i></a>';
                    //操作按钮拼接
                    operation = upDown + ' <a href="#" class="btn purple mini"  data-toggle="tooltip" data-placement="top" title="置顶"   onclick="modifyStatus_stick('+item.id+','+item.adType+',1)"><i class="icon-circle-arrow-up"></i></a> <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" onclick="editBanner('+item.id+','+item.adType+')"><i class="icon-edit icon-white"></i></a> '+Deleted;

                    var adType_list= item.adType;

                    temp += '<tr>'
                        +          '<td data-title="Banner链接" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.linkUrl+'" >'+item.name+'</td>'
                        +          '<td data-title="Banner状态">'+adStatus+'</td>'
                        +          '<td data-title="PV"></td>'
                        +          '<td data-title="UV"></td>'
                        +          '<td data-title="平台属性">'+item.supportPlatform+'</td>'
                        +          '<td data-title="操作">'+operation+'</td>'
                        +  '</tr>';

                })
                $('#banner_List tbody').html($row);
                $("[data-toggle='popover']").popover();
                //操作按钮hover显示详情
                $("[data-toggle='tooltip']").tooltip();
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getBannerList,'#pageNum');
            }else{
                $.toast('系统错误！', 3000);
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
 *  功能描述：上架，下架
 *  请求方式：POST
 *  请求地址：/api/banner/modify_status
 *  函数名称：modifyStatus
 *  参数：id:banner主键ID; adType:banner类型; beUsed:上架下架;
 */

function modifyStatus(id,adType,beUsed){
    $.ajax({
        url: '/api/banner/modify_status',
        type: 'POST',
        dataType: 'json',
        data: {
            'id':id,
            'adType':adType,
            'beUsed':beUsed
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getBannerList();
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
 *  功能描述：置顶
 *  请求方式：POST
 *  请求地址：/api/banner/modify_status
 *  函数名称：modifyStatus_stick·
 *  参数：id:banner主键ID; adType:banner类型; beUsed:上架下架; stick:置顶;
 */

function modifyStatus_stick(id,adType,stick){
    if(!confirm("确定置顶吗?")) return;
    $.ajax({
        url: '/api/banner/modify_status',
        type: 'POST',
        dataType: 'json',
        data: {
            'id':id,
            'adType':adType,
            'stick':stick
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getBannerList();
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
 *  请求地址：/api/banner/modify_status
 *  函数名称：modifyStatus_remove
 *  参数：id:banner主键ID; adType:banner类型; beUsed:上架下架; stick:置顶;isDeleted:删除;
 */

function modifyStatus_remove(id,adType,isDeleted){
    if(!confirm("确定删除吗?")) return;
    $.ajax({
        url: '/api/banner/modify_status',
        type: 'POST',
        dataType: 'json',
        data: {
            'id':id,
            'adType':adType,
            'isDeleted':isDeleted
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getBannerList();
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
 *  功能描述：修改banner
 *  请求方式：POST
 *  请求地址：/api/banner/edit
 *  函数名称：editBanner
 *  参数：id:bannerID; adType:banner类型;
 */

function editBanner(id,adType){
    $('#addBannerModal').modal('show');
    $.ajax({
        url:'/api/banner/edit',
        type:'POST',
        dataType:'json',
        data:{
            id:id,
            adType:adType
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            console.log(data);
            if(data.status == 0){
                $('#myModalLabel').text('Banner编辑');
                var json = data.data;
                var supportPlatType;
                //banner类型
                checkBox_adType(json.adType);
                $('input[name=id]').val(json.id);
                //banner名称
                $('.name_text_adName').val(json.name);

                $("input[name='supportPlatform']")
                    .removeAttr('checked')
                    .parent().removeClass('checked');

                //banner平台类型
                if(json.supportPlatform == "全平台"){
                    supportPlatType = 0;
                }else if(json.supportPlatform == "ios"){
                    supportPlatType = 1;
                }else if(json.supportPlatform == "android"){
                    supportPlatType = 2;
                }

                $("input[name='supportPlatform']")
                    .eq(supportPlatType)
                    .attr('checked',"checked")
                    .parent().addClass('checked');

                //通过返回的imageMd5,添加到图片;
                $('#txtUrl').val(json.imageMd5).trigger('change');
                //链接
                $('input[name=linkUrl]').val(json.linkUrl);
                if(json.adType == 7){
                    $('#adTime').show();
                    $('#startTime').val(json.startTime);
                    $('#endTime').val(json.endTime);
                }else{
                    $('#adTime').hide();
                }
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
    $('input[name=adName]').val('');
    //终端全平台
    var aCh = $('input[name=supportPlatform]').parent();
    aCh.removeClass('checked');
    //aCh.eq(0).addClass('checked');
    //上传图片置空
    $('#imgIcon').attr({
        'src': ''
    });
    $('#txtUrl').val('');
    //链接置空
    $('input[name=linkUrl]').val('');
    $('input[name=id]').val('');
    $('#adType').val($('#adtypelist').val());
    $('#startTime').val('');
    $('#endTime').val('');
}




