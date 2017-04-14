$(function () {
    getUpgradeList();//先执行一次获取banner列表信息；
    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getUpgradeList();//获取banner列表信息；
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

/**
 *  功能描述：获取版本列表信息
 *  请求方式：GET
 *  请求地址：/api/upgrade/list
 *  函数名称：getUpgradeList
 */

function getUpgradeList(){

    $.ajax({
        url:manage_path+ '/api/upgrade/list',
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
                if (list != null && list.length > 0) {
                    $.each(list, function (index, item) {

                        //字符串截取
                        var up_Desc = item.upgradeDesc.toString();
                        up_Desc = up_Desc.substring(0,10)+"...";
                        var operation =  ' <a href="upgrade_edit.jsp?id='+item.id+'" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" ><i class="icon-edit icon-white"></i></a> '
                        //操作按钮拼接
                        temp += '<tr>'
                            + '<td data-title="名称" >' + item.name + '</td>'
                            + '<td data-title="版本号">'+ item.version +'</td>'
                            + '<td data-title="升级描述" data-container="body" data-toggle="popover" data-trigger="click" data-placement="top" data-content="'+item.upgradeDesc+'" style="width:200px;overflow:hidden;color:#0b94ea;" >' + up_Desc + '</td>'
                            + '<td data-title="平台类型">' + item.platformType + '</td>'
                            + '<td data-title="创建时间">' + timestampFormat(item.createTime) + '</td>'
                            + '<td data-title="操作">' + operation + '</td>'
                            + '</tr>';

                    })
                    $('#upgrade_list tbody').html(temp);
                    $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getUpgradeList, '#pageNum');
                } else {
                    $.toast("没有查到数据", 3000);
                    $('#upgrade_list tbody').html('');
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
