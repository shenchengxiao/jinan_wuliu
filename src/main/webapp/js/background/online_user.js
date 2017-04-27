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

    var clicknum = 1;
    var idsArr  = [];
    //ids 传递ids的集合对象
    function setIdsInfo(){
        $('#userIds').val(idsArr);
    }

    //全选||非全选
    $('#btn_chooseAll').on('click',function(){
        if(clicknum%2){
            //第一次点击
            $('input[name="userIdArry"]').each(function(i){
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


    $('#btn_kick_out').on('click',function(){
        var userIds = $('#userIds').val();
        if (userIds != null && userIds.length > 0){
            kickOutUser();
        }else {
            alert("请至少选择一名用户！");
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

                        temp += '<tr>'
                            +'<td data-title="">' +'<input type="checkbox" name="chooseTag"><input type="hidden" name="userIdArry" value="'+item.id+'"/>'+ '</td>'
                            + '<td data-title="用户名称">' + item.userName + '</td>'
                            + '<td data-title="用户编号">' + item.userNum + '</td>'
                            + '<td data-title="用户密码">' + item.password + '</td>'
                            + '<td data-title="联系电话">' + item.phoneNumber + '</td>'
                            + '<td data-title="省份">' + item.province + '</td>'
                            + '<td data-title="城市">' + item.city + '</td>'
                            + '<td data-title="县区">' + item.county + '</td>'
                            + '<td data-title="服务开始时间">' + timestampFormat(item.startTime) + '</td>'
                            + '<td data-title="服务结束时间">' + timestampFormat(item.endTime) + '</td>'
                            + '<td data-title="设备码">' + item.hardNum + '</td>'
                            + '<td data-title="话机码">' + item.networkNum + '</td>'
                            + '<td data-title="平台">'+  platform +  '</td>'
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
            }else{
                $.toast(data.msg,3000);
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
 *  功能描述：踢出在线用户
 *  请求方式：POST
 *  请求地址：/api/user_manage/kick_out
 *  函数名称：kickOutUser
 */
function kickOutUser() {
    if(!confirm("确定踢出用户吗?")) return;
    $.ajax({
        url: manage_path+'/api/user_manage/kick_out',
        type: 'POST',
        dataType: 'json',
        data: {
            'userIdsArray':$("#userIds").val()
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast("操作成功",3000);
                getOnlineUserList();
                $('#ids').val("");
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



