'use strict'
$(function(){
    $('#btn_search').on('click',function(){
        getChannelList();
        $("#pageNum").val(1);
    }).click();

    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });

    //通道选择
    $('#account_select').multipleSelect({
        placeholder: "请选择商家",
        filter: true,
        multiple: true,
        multipleWidth:250,
    });

    $('#addAccount').on('shown.bs.modal', function () {
        $('#addAccount .modal-body').css('height','300px');
    });

    $('#btn_add_account').on('click',function(){
        addUpAndDownRelation();
        $('#addAccount').modal('hide'); //关闭
    });

    $("#refresh_btn").on('click',function(){
        getRefresh();
    })
    //操作按钮hover显示详情
    $("[data-toggle='tooltip']").tooltip();

});
/**
 *  功能描述：通道管理
 *  请求方式：GET
 *  请求地址：/api/upstream/account/page
 *  函数名称：getChannelList
 */
function getChannelList(){
    $.ajax({
        url:"/api/upstream/account/page",
        type:"get",
        dataType:"json",
        data:$('#pipe_channel_list').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                console.log(json);
                var list = json.result;
                var temp = '';
                var operate = '';
                $.each(list,function(index,item){
                    var belongType = item.belongType;
                    switch(belongType)
                    {
                        case 1:
                            belongType='移动'
                            break;
                        case 2:
                            belongType='联通'
                            break;
                        case 3:
                            belongType='电信'
                            break;
                        case 4:
                            belongType='虚拟运营商'
                            break;
                    }
                    var roamType = item.roamType;
                    switch(roamType)
                    {
                        case 0:
                            roamType='全国'
                            break;
                        case 1:
                            roamType='本地漫游'
                            break;
                        case 2:
                            roamType='本地不可漫游'
                            break;
                    }
                    var channelType = item.channelType;
                    switch(channelType)
                    {
                        case 0:
                            channelType='普通通道'
                            break;
                        case 1:
                            channelType='流量池通道'
                            break;
                    }
                    var level = item.level;
                    switch(level)
                    {
                        case 0:
                            level='最高优先级'
                            break;
                        case 1:
                            level='中高优先级'
                            break;
                        case 2:
                            level='中低优先级'
                            break;
                        case 3:
                            level='最低优先级'
                            break;
                    }
                    var discountType = item.discountType;
                    switch(discountType)
                    {
                        case 0:
                            discountType='统一折扣'
                            break;
                        case 1:
                            discountType='自定义折扣'
                            break;
                    }
                    var chargeServiceType = item.charge_service_type;
                        switch(chargeServiceType)
                        {
                            case 0:
                                chargeServiceType='充值'
                                break;
                            case 1:
                                chargeServiceType='活动'
                                break;
                        }
                    var isUsed = item.isUsed;
                        switch(isUsed)
                        {
                            case 0:
                                isUsed='禁用'
                                break;
                            case 1:
                                isUsed='启用'
                                break;
                        }
                   

                    if (item.isUsed == 1){
                        operate = '<a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="updateStatus('+item.id+',2)"><i class="icon-ban-circle"></i></a>&nbsp;';
                    }else{
                        operate = '<a class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="updateStatus('+item.id+',1)"><i class="icon-ok"></i></a>&nbsp;';
                    }

                    temp +='<tr>'+
                                '<td data-title="通道名称" class="Chaining"><a href="pipe_product_list.html?id='+item.id+'&&belongType='+item.belongType+'" class="ChainName" onclick="setchannelName(this)">'+item.channelName+'</a></td>'+
                                '<td data-title="折扣">'+item.discount+'</td>'+
                                '<td data-title="折扣类型">'+discountType+'</td>'+
                                '<td data-title="运营商类型">'+belongType+'</td>'+
                                '<td data-title="漫游范围">'+roamType+'</td>'+
                                '<td data-title="通道类型">'+channelType+'</td>'+
                                '<td data-title="充值类型">'+chargeServiceType+'</td>'+
                                '<td data-title="优先级">'+level+'</td>'+
                                '<td data-title="评分">'+item.score+'</td>'+
                                '<td data-title="状态">'+isUsed+'</td>'+
                                '<td data-title="操作">'+operate+
                                    //'<a class="btn mini blue" data-toggle="modal" href="/html/upstream/edit_pipe.html?id='+item.id+'" >修改</a>'+
                                '<a data-toggle="modal" href="/html/upstream/edit_pipe.html?id='+item.id+'" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑"><i class="icon-edit icon-white"></i></a>&nbsp'+
                                '<a class="btn mini purple" data-toggle="tooltip" data-placement="top" title="查看" onclick="addDownAccount('+item.id+')"><i class="icon-tasks"></i></a>'
                                '</td>'+
                            '</tr>';
                });
                
                $('#channel_list tbody').html(temp);
                $("[data-toggle='tooltip']").tooltip();
                $("[data-toggle='modal']").tooltip();
                // 表格分页
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getChannelList,'#pageNum');
            }else{
                $.toast(data.msg,3000);
                $('#channel_list tbody').html('');
                if($('#pagination').html().length > 0){
                    $('#pagination').jqPaginator('destory');
                }
            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
}

/**
 *  功能描述：启用禁用
 *  请求方式：POST
 *  请求地址：/api/upstream/account/editor
 *  函数名称：updateStatus
 */

function updateStatus(id,modifyType){
    $.ajax({
        url:'/api/upstream/account/editor',
        type:'post',
        dataType:'json',
        data:{
            'id':id,
            'modifyType':modifyType
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            //console.log(data);
            if(data.status == 0){
                $.toast(data.msg,3000);
                getChannelList();
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
 *  功能描述：获取所有上下游渠道关系
 *  请求方式：GET
 *  请求地址：/api/relation/list
 *  函数名称：getAllRelation
 */
function getAllRelation(upstreamAccountInfoId){
    $.ajax({
        url:"/api/relation/list?upstreamAccountInfoId="+upstreamAccountInfoId,
        type:"get",
        dataType:"json",
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                console.log(data);
                var json = data.data;
                var accountIds = [];
                $.each(json,function(index,item){
                    accountIds.push(item.downstreamAccountInfoId);
                });
                getAllAccount(accountIds);
            }else{
                $.toast('没有查到数据！',3000);

            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试',5000);
        }
    })
};

/**
 *  功能描述：获取下游所有账户
 *  请求方式：GET
 *  请求地址：/api/downstream/account/all_account
 *  函数名称：getAllAccount
 */
function getAllAccount(downstream_ids) {
    $('#account_select').html('');
    $.ajax({
        url: '/api/downstream/account/all_account',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.status == 0) {
                var json = data.data;
                console.log(json);
                $.each(json, function (index, item) {
                    $('#account_select').append(
                        $('<option ' +($.inArray(item.id,downstream_ids)>=0?'selected="selected"':'')+'></option>').text(item.contactName).val(item.id)
                    );
                });
                $('#account_select').multipleSelect('refresh');
            } else {
                $.toast('没有查到数据！', 3000);
            }
        },
        complete: function () {
            $.progressBar().close();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试', 5000);
        }
    });
}

/**
 *  功能描述：分配账户
 *  请求方式：POST
 *  请求地址：/api/relation/add
 *  函数名称：addUpAndDownRelation
 */
function addUpAndDownRelation() {
    $.ajax({
        url: "/api/relation/add",
        type: "post",
        dataType: "json",
        data: $('#add_account_for_pipe').serialize(),
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力加载数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            console.log(data);
            if(data.status == 0){
                $.toast('添加成功',1000);
                setTimeout(function(){
                    window.location.href = 'pipe_manage.html';
                },1000);
            }else{
                $.toast(data.msg,1000);
            }
        },
        complete: function () {
            $.progressBar().close();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试', 5000);
        }
    })
}

function addDownAccount(id){
    $('#addAccount').modal('show');
    $('#upstreamId').val(id);
    getAllRelation(id);
}

/*
    
    功能描述：获取所有上游渠道账户刷新缓存
    请求方式：POST
    请求地址：/api/upstream/account/refresh
    请求参数:  
*/

function getRefresh(){
    var cacheNum = $('#cacheNum').val();
    $.ajax({
        url: "/api/upstream/account/refresh",
        type: "POST",
        dataType: "json",
        data: {'cacheNum':cacheNum},
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力加载数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            console.log(data);
            if(data.status == 0){
                $.toast('添加成功',1000);
                getChannelList();
            }else{
                $.toast(data.msg,1000);
            }
        },
        complete: function () {
            $.progressBar().close();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试', 5000);
        }
    })
}

//用cookiec存通道名称
 function setchannelName(obj){
    var $this = $(obj);
    console.log($this.html());
    var channelName_pid = $this.html()||'';
    setCookie('channelName',channelName_pid,{path:'/'}); 
}