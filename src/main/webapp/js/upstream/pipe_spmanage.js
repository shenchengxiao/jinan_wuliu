$(function(){

    $('#inquire').on('click',function(){
        $("#pageNum").val(1);
        getspManageList();
    }).click();
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#inquire").click();
        }
    });

});

// 获取角色权限
var str = getRoleValue(App.req.user.role,Role);
var roleArr = str.split(',');

/**
 *  功能描述：商家管理
 *  请求方式：GET
 *  请求地址：/api/merchant/list
 *  函数名称：getspManageList
 */
function getspManageList(){
    $.ajax({
        url:"/api/merchant/list",
        type:"get",
        dataType:"json",
        data: $('#business_form').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                var list = json.result;
                //console.log(list);
                var temp = '';
                var
                    operate,
                    btnUser,
                    btnUsed,
                    btnEdit,
                    btnDetail = '';
                // if(findInArr(Role.ChannelAdmin.Value,roleArr)){
                //     alert('通道管理员');
                // }else{
                //     alert('不是通道管理员');
                // }
                $.each(list,function(index,item){
                    var Btype = item.merchantType;
                    switch(Btype)
                    {
                        case 0:
                            Btype='口粮'
                            break; //口粮商家不能被管理
                        case 1:
                            Btype='商家SP'
                            break;
                        case 2:
                            Btype='合伙人'
                    };
                    listStatus = item.beUsed;
                    switch(listStatus)
                    {
                        case 0:
                            listStatus='禁用'
                            break;
                        case 1:
                            listStatus='启用'
                            break;
                    };
                    // if (item.beUsed == 1){
                    //     btnUsed = '<a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="updateStatus('+item.id+',0,'+item.enterpriseAccount+')"><i class="icon-ban-circle"></i></a>&nbsp;';
                    // }else{
                    //     btnUsed = '<a class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="updateStatus('+item.id+',1,'+item.enterpriseAccount+')"><i class="icon-ok"></i></a>&nbsp;';
                    // }
                    // btnUser = '<a href="/html/spmanage/sp_manage_user.html?userName='+item.businessName+'&businessAccountId='+item.id+'" class="btn mini red" data-toggle="tooltip" data-placement="top" title="用户列表"><i class="icon-list-alt"></i></a>&nbsp;';
                    // btnEdit = '<a href="/html/spmanage/edit_sp.html?id='+item.id+'" class="btn mini blue" data-toggle="tooltip" data-placement="top" title="编辑"><i class="icon-edit icon-white"></i></a>&nbsp;';
                    btnDetail = '<a class="btn mini purple" data-toggle="tooltip" data-placement="top" title="查看" onclick="findAccountByCode(\''+encodeURIComponent(item.enterpriseAccount)+'\')"><i class="icon-tasks"></i></a>&nbsp;'
                    // 相关的操作
                    operate = btnDetail;
                    // 判断如果登录的角色是通道管理，只显示查看按钮
                    // if(App.req.user.role == 32){
                    //     operate = btnDetail;
                    // }
                    temp+='<tr><td data-title="商家名称">'+item.businessName+'</td>'+
                            '<td data-title="联系人名称">'+item.contactName+'</td>'+
                            '<td data-title="联系方式">'+item.phoneNumber+'</td>'+
                            '<td data-title="邮箱">'+item.email+'</td>'+
                            '<td data-title="企业经营范围">'+item.enterpriseOperateDesc+'</td>'+
                            '<td data-title="商家充值描述">'+item.rechargeDesc+'</td>'+
                            '<td data-title="操作">'+operate+'</td>' +
                        ' </tr>'
                });
                $('#getspManageList tbody').html(temp);
                $("[data-toggle='tooltip']").tooltip();
                // 表格分页
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getspManageList,'#pageNum');
            }else{
                $.toast('没有查到数据！',3000);
                $('#getspManageList tbody').html('');
                if($('#pagination').html().length > 0 ){
                    $('#pagination').jqPaginator("destroy");
                }
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
 *  功能描述：启用禁用
 *  请求方式：POST
 *  请求地址：/api/merchant/modify
 *  函数名称：updateStatus
 */
function updateStatus(id,beUsed,businessCode){
    $.ajax({
        url: "/api/merchant/modify",
        type: "POST",
        dataType: "json",
        data:{
            'id':id,
            'beUsed':beUsed,
            'business_code':businessCode
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getspManageList();
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
 *  功能描述：根据CODE查询下游账户
 *  请求方式：GET
 *  请求地址：/api/downstream/account/list
 *  函数名称：findAccountByCode
 */
function findAccountByCode(code){
    $.ajax({
        url:"/api/downstream/account/list",
        type:"get",
        dataType:"json",
        data:{
            userAccount:code
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                console.log(json);
                var list = json.result[0];
                if (list != null){
                    var id = list.id;
                    window.location.href = "sp_manage_detail.html?code="+code+"&id="+id;
                }else {
                    window.location.href = "add_account.html?code="+code;
                }
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
    });

}