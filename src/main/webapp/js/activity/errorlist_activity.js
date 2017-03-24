/**
 * Created by xuwenjie on 2016/12/12.
 */
$(function() {
    getErrorList();
});

/**
 *  功能描述：获取纠错活动列表
 *  请求方式：get
 *  请求地址：/api/activity/error_list
 *  函数名称：getErrorList
 */
function getErrorList(){
    $.ajax({
        url:"/api/activity/error_list",
        type:"get",
        dataType:"json",
        data:$('#error_list_id').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },success:function(data){
             console.log(data);
            if(data.status == 0){
                var json = data.data;
                var list = json.result;
                var temp = "";
                var operate = "";
                var operation = "";
                //上架下架
                var upDown = "";
                //推荐 取消推荐
                var acRecommendInfoHtm = "";
                //设置投诉已处理
                var acerrorStatus = "";
                //循环铺数据
                $.each(list,function(index,item){
                    var errorStatus = item.error_correctioninfo_resp.error_status;
                    switch(errorStatus){
                        case 1:
                            errorStatus = '<td data-title="状态" style="color:red;">新投诉!</td>';
                            acerrorStatus = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="未处理" onclick="setmodifyState('+item.error_correctioninfo_resp.id+',2)"><i class="icon-cog icon-white"></i></a>';
                            break;
                        case 2:
                            errorStatus = '<td data-title="状态" style="color:rgb(187, 163, 11);">已阅</td>';
                            acerrorStatus = '<a class="btn mini yellow" data-toggle="tooltip" data-placement="top" title="已处理" onclick="alertmsg()"><i class="icon-cog icon-white"></i></a>';
                            break;
                    }
                    //按钮状态
                    var acUpDown =  item.activity_status;
                    //isDownOrUp状态：1下架 0上架
                                        
                    if(acUpDown == 0){
                        upDown = ' <a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="下架" onclick="modifyState('+item.activity_id+',1,'+item.activity_type+',0)"><i class="icon-ban-circle"></i></a>&nbsp;';
                    }else{
                        upDown = ' <a class="btn mini green" data-toggle="tooltip" data-placement="top" title="上架" onclick="modifyState('+item.activity_id+',0,'+item.activity_type+',0)"><i class="icon-ok-circle"></i></a>&nbsp;';
                    }
                    var acRecomment =  item.is_recommended;
                    //isDownOrUp状态：0、已推荐 1、推荐        
                    if (acRecomment == 0) {//已推荐
                        acRecommendInfoHtml = '<a class="btn mini purple" data-toggle="tooltip" data-placement="top" title="推荐" onclick="modifyState('+item.activity_id+',1,'+item.activity_type+',1)"><i class="icon-thumbs-up"></i></a>&nbsp';
                    } else if(acRecomment == 1) {
                        acRecommendInfoHtml = '<a class="btn mini black" data-toggle="tooltip" data-placement="top" title="取消推荐" onclick="modifyState('+item.activity_id+',0,'+item.activity_type+',1)"><i class="icon-thumbs-down"></i></a>&nbsp';
                    }
                    var acModify = '<a href="/html/activity/edit_activity.html?id=' + item.activity_id + '&acType='+item.activity_type + '" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑"><i class="icon-edit icon-white"></i></a>&nbsp';
                    var acRemove = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除该条举报" ><i class="icon-remove icon-white"></i></a>';
                    operation = acModify+upDown+acRecommendInfoHtml+'<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除该条活动" onclick="delmodifyState('+item.activity_id+',3,'+item.activity_type+',1)"><i class="icon-remove icon-white"></i></a>';

                    operate = '<a href="/html/activity/errdet_activity.html?id=' + item.activity_id + '" class="btn green mini" data-toggle="tooltip" data-placement="top" title="查看所有投诉"><i class="icon-align-justify  icon-white"></i></a>&nbsp<a href="javascript:;" class="btn yellow mini" data-toggle="tooltip" data-placement="top" title="撤销所有投诉" onclick="delAllmodifyState('+item.activity_id+',0)"><i class="icon-cogs icon-white"></i></a>';  
                    var activity_name = item.activity_name;
                    if(item.is_has_new_complain){
                        activity_name = activity_name + '&nbsp;<sup style="color:red;font-size:12px;">新！</sup>';
                    }else{
                        activity_name = item.activity_name;
                    }

                    temp += '<tr>'
                        +          '<td data-title="活动名称">'+activity_name+'</a></td>'
                        +          '<td data-title="实际人数">'+item.real_join_peoples+'</td>'
                        +          '<td data-title="虚拟人数">'+item.vir_join_peoples+'</td>'
                        +          '<td data-title="纠错总数">'+item.num+'</td>'
                        +          '<td data-title="纠错内容" style="max-width:60px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis; cursor:pointer" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.error_correctioninfo_resp.error_content+'" >'+item.error_correctioninfo_resp.error_content+'</td>'
                        +          errorStatus
                        +          '<td data-title="提交时间">'+item.error_correctioninfo_resp.create_time+'</td>'
                        +          '<td data-title="用户名">'+item.error_correctioninfo_resp.user_name+'</td>'
                        +          '<td data-title="用户手机号">'+item.error_correctioninfo_resp.phone_number+'</td>'
                        +          '<td data-title="活动操作">'+operation+'</td>'
                        +          '<td data-title="投诉操作">'+operate+'</td>'
                        +  '</tr>';
                });

                $('#error_list_id tbody').html(temp);
                //按钮hover效果
                $("[data-toggle='tooltip']").tooltip();
                $("[data-toggle='popover']").popover();
                // 表格分页
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getErrorList,'#pageNum');
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
     *  功能描述：更新活动状态（上架下架、是否推荐、是否删除）
     *  请求方式：POST
     *  请求地址：/api/activity/modify
     *  函数名称：modifyState
     *  函数参数：activityId：id; 
                status:状态 0、上架或不推荐 1、下架或推荐; 
                activityOperatorType: 操作类型 0、上架下架 1、是否推荐 3、是否删除;
                acType 活动类型 0微信 1其它 2装应用 3无需操作 4链接
     */

     function modifyState(activityId,status,acType,activityOperatorType){
        $.ajax({
            url: '/api/activity/modify',
            type: 'POST',
            dataType: 'json',
            data: {
                'activityId':activityId,
                'status':status,
                'activityOperatorType':activityOperatorType,
                'acType':acType
            },
            beforeSend: function (data) {
                $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
            },
            success: function (data) {
                if (data.status == 0) {
                    $.toast('操作成功！', 3000);
                    getErrorList();
                } else {
                    $.toast('操作失败！', 3000);
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
     *  函数名称：delmodifyState
     */
     function delmodifyState(activityId,activityOperatorType,acType,beDelete){
        if(!confirm("确定删除该条活动吗?")) return;
        $.ajax({
            url: '/api/activity/modify',
            type: 'POST',
            dataType: 'json',
            data: {
                'activityId':activityId,
                'activityOperatorType':activityOperatorType,
                'beDelete':beDelete,
                'acType':acType
            },
            beforeSend: function (data) {
                $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
            },
            success: function (data) {
                if (data.status == 0) {
                    $.toast('删除成功！', 3000);
                    getErrorList();
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
                    getErrorList();
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

     /**
     *  功能描述：更新纠错状态和删除
     *  请求方式：POST
     *  请求地址：/api/activity/error_modify
     *  函数名称：delmodifyState
     */
     function delAllmodifyState(activity_id,error_status){
        if(!confirm("确定撤销所有投诉吗?")) return;
        $.ajax({
            url: '/api/activity/error_modify',
            type: 'POST',
            dataType: 'json',
            data: {
                'activity_id':activity_id,
                'error_status':error_status
            },
            beforeSend: function (data) {
                $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
            },
            success: function (data) {
                if (data.status == 0) {
                    $.toast('删除成功！', 3000);
                    getErrorList();
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
     function alertmsg(){
        alert("该条举报已处理");
     }


