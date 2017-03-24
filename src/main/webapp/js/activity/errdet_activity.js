/**
 * Created by xuwenjie on 2016/12/12.
 */
$(function() {
    var activity_id = getUrlParam('id');
    $('#activity_id').val(activity_id);
    getErrorDetList();
});

/**
 *  功能描述：获取纠错活动列表
 *  请求方式：get
 *  请求地址：/api/activity/error_detail
 *  函数名称：getErrorDetList
 */
function getErrorDetList(){
    $.ajax({
        url:"/api/activity/error_detail",
        type:"get",
        dataType:"json",
        data: {
            activity_id:$('#activity_id').val()
        },
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
             console.log(data);
            if(data.status == 0){
                var json = data.data;
                var list = json.result;
                var temp = "";
                var num;
                var numStr = "";
                var errorTit = "";
                //循环铺数据
                $.each(list,function(index,item){
                    if(index == 0){
                        numStr = '最新投诉人';
                    }else{
                        num = json.total - index;
                        numStr = '第'+num+'号投诉人';
                    }
                    
                    var errorStatus = item.error_status;
                    switch(errorStatus){
                        case 1:
                            errorTit = '<span style="color:red;font-size: 16px;" class="span3">新投诉！</span>';
                            errorStatus = '<a href="#" class="btn purple mini pull-right" style="margin-right:15px;" onclick="setmodifyState('+item.id+',2)">更新举报状态</a>';
                            break;
                        case 2:
                            errorTit = '<span style="color:rgb(187, 163, 11);font-size: 16px;" class="span3">已阅</span>';
                            errorStatus = '<a href="#" class="btn grey mini pull-right" style="margin-right:15px;" onclick="alertmsg()">已处理</a>';
                            break;
                    }
                    temp += '<ul>'
                         +      '<li style="margin-top:10px;margin-left: 25px;">'
                         +      '<span class="span3">'+numStr+'：<i>'+item.user_name+'</i></span>' 
                         // +      '<span class="span2">id：<i>'+item.id+'</i></span>'  
                         +      '<span class="span3">手机号码：<i>'+item.phone_number+'</i></span>'
                         +      '<span class="span3">投诉时间：<i>'+item.create_time+'</i></span></li>'
                         +      '<li style="margin-top:10px;" class="span10"><span >投诉内容:</span></li>'
                         +      '<li style="margin-top:10px;" class="span10"><i style="word-break:break-all">'+item.error_content+'</i></li>'
                         +      '<li style="margin-top:10px;" class="span10">'
                         // +      '<span style="color:red;" class="span3">共被投诉：<b>'+json.total+'</b>次</span>'
                         +      errorTit
                         +      '<a href="#" class="btn red mini pull-right" onclick="delmodifyState('+item.id+',0)">删除该条举报</a>&nbsp;&nbsp;'
                         +      errorStatus
                         +      '</li></ul><hr/>';
                });

                $('#error_list_id').html(temp);
                //按钮hover效果
                $("[data-toggle='tooltip']").tooltip();
                // 表格分页
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getErrorDetList,'#pageNum');
            }else{
                $.toast(data.msg,3000);
                $('#integral_list_id tbody').html('');
                if($('#pagination').html().length > 0){
                    $('#pagination').jqPaginator('destory');
                }
            }
        },complete:function(){
            $.progressBar().close();
        },error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
}


    /**
     *  功能描述：更新纠错状态和删除
     *  请求方式：POST
     *  请求地址：/api/activity/error_modify
     *  函数名称：delmodifyState
     */
     function delmodifyState(id,error_status){
        if(!confirm("确定删除吗?")) return;
        $.ajax({
            url: '/api/activity/error_modify',
            type: 'POST',
            dataType: 'json',
            data: {
                'id':id,
                'error_status':error_status
            },
            beforeSend: function (data) {
                $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
            },
            success: function (data) {
                if (data.status == 0) {
                    $.toast('删除成功！', 3000);
                    getErrorDetList();
                } else {
                    $.toast('删除失败！', 3000);
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
     *  功能描述：更新纠错状态和删除
     *  请求方式：POST
     *  请求地址：/api/activity/error_modify
     *  函数名称：modifyState
     */
     function setmodifyState(id,error_status){
        if(!confirm("确定设置已处理吗?")) return;
        $.ajax({
            url: '/api/activity/error_modify',
            type: 'POST',
            dataType: 'json',
            data: {
                'id':id,
                'error_status':error_status
            },
            beforeSend: function (data) {
                $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
            },
            success: function (data) {
                if (data.status == 0) {
                    $.toast('处理成功！', 3000);
                    getErrorDetList();
                } else {
                    $.toast('处理失败！', 3000);
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

     function alertmsg(){
        alert("该条举报已处理");
     }


