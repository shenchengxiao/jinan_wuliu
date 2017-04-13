$(function(){

    getOnlineUserList();//先执行一次获取列表信息；
    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getOnlineUserList();//获取列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });

});

/**
 *  功能描述：获取在线用户列表信息
 *  请求方式：GET
 *  请求地址：/api/user_manage/online_user
 *  函数名称：getOnlineUserList
 */

function getOnlineUserList(){

    $.ajax({
        url: manage_path+'/api/user_manage/online_user',
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
                    $.each(list, function (index, item) {
                        var enabled = item.isAbled;
                        if (enabled == 0) {
                            enabled = "无效";
                        } else {
                            enabled = "有效";
                        }

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
                            + '</tr>';
                    })
                    $('#user_manage_list tbody').html(temp);
                    // $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    $("[data-toggle='modal']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
                    // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getOnlineUserList, '#pageNum');

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

function kickOutUser() {
    
}



