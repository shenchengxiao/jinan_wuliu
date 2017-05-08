$(function(){
	
	var phone = getUrlParam("phoneNumber");
	
	if(phone != ''){
		$("#phoneNumber").val(phone);
	}

    getUserList();//先执行一次获取列表信息；
    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getUserList();//获取列表信息；
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
        var arrId = [];
        $.each(idsArr,function(index,item){
            var IDs = {
                id:item
            };
            arrId.push(IDs);
        });

        var json = JSON.stringify(arrId);
        $('#ids').val(json);
    }



    //全选||非全选
    $('#btn_chooseAll').on('click',function(){
        if(clicknum%2){    
            //第一次点击
            $('input[name="idArr"]').each(function(i){
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


    //绑定所有tbody下的tr,不包括最后一列
    $('#user_manage_list tbody').on('click','tr td:not(:last-child)',function(){
        //找到本列本行其它列
        var check = $(this).parent().find("input[type='checkbox']");
        var thisID = $(this).parent().find("input[name=idArr]").val();
        if(check){
            var flag = check[0].checked;
            console.log(check[0]);
            if(flag){
                check[0].checked = false;
                (thisID);
                console.log(idsArr);
                setIdsInfo();
            }else{
                check[0].checked = true;
                idsArr.push(thisID);
                console.log(idsArr);
                setIdsInfo();

            }
        }

    });


    //防止冒泡事件
    $('#user_manage_list tbody').on('click','input[name="chooseTag"]',function(event) {
        event.stopImmediatePropagation();
        var thisID = $(this).next('input').val();
        if($(this).is(':checked')){
            idsArr.push(thisID);
            console.log(idsArr);
            setIdsInfo();
        }else{
            removeInArr(thisID);
            console.log(idsArr);
            setIdsInfo();
        }
    })



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


  //创建modal弹出层 发布消息
    $('#btn_send').on('click',function(){
    	if(idsArr == null || idsArr.length == 0){
            alert("请先选择用户");
    	}else{
            clearModal();//清空modal弹出层里面的参数；
            $('#sendUserMessageModal').modal('show');//modle层显示
    	}
    	
    });

    $('#btn_send_usermessage').on('click',function(){
        //非空验证
        var b = $('#send_usermessagee_form').valid();//true false
        if(b){
        	sendsysmessage();
            $('#sendUserMessageModal').modal('hide');//modle层隐藏
        }else{
            return false;
        }
    });
    
    $('#btn_clear').on('click',function(){
    	
    	$("#content").val("");
    });


    //创建modal弹出层 重置密码
    $('#btn_password').on('click',function(){
        //判断是否选中用户且只能选择一个用户
        if(idsArr == null || idsArr.length == 0 ){
            alert("请先选择一个用户");
        }else if (idsArr.length > 1){
            alert("暂不支持批量修改");
        }else {
            for(var i=0;i<idsArr.length;i++) {
                var id = idsArr[i];
                getUserDetail(id);
            }
            clearModal();
            $('#resetPasswordModal').modal('show');//modle层显示
        }
    });

    //提交重置信息
    $('#btn_reset_password').on('click',function(){
        //非空验证
        var flag = $('#reset_password_form').valid();//true false
        if(flag){
            resetUserInfo();
            $('#resetPasswordModal').modal('hide');//modle层隐藏
        }else{
            return false;
        }
    });


    //创建modal弹出层 修改到期时间
    $('#btn_postpone').on('click',function(){
        //判断是否选中用户且只能选择一个用户
        if(idsArr == null || idsArr.length == 0 ){
            alert("请先选择一个用户");
        }else if (idsArr.length > 1){
            alert("暂不支持批量修改");
        }else {
            for(var i=0;i<idsArr.length;i++) {
                var id = idsArr[i];
                getExpireDate(id);
            }
            clearModal();
            $('#resetExpireDateModal').modal('show');//modle层显示;
        }
    });


    //提交修改日期信息
    $('#btn_reset_expireDate').on('click',function(){
        //非空验证
        var flag = $('#reset_expireDate_form').valid();//true false
        if(flag){
            resetUserInfo();
            $('#resetExpireDateModal').modal('hide');//modle层隐藏
        }else{
            return false;
        }
    });


    //解绑
    $('#btn_unbind').on('click',function(){
        //判断是否选中用户且只能选择一个用户
        if(idsArr == null || idsArr.length == 0 ){
            alert("请先选择一个用户");
        }else if (idsArr.length > 1){
            alert("暂不支持批量修改");
        }else {
            for(var i=0;i<idsArr.length;i++) {
                var id = idsArr[i];
                userUnbind(id);
            }
        }
    });

    /**
     * 校验重置密码参数
     */
    $('#reset_password_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            password:{
                required: true
            },
            passwordVerify:{
                required: true,
                equalTo: "#reset_password"
            }
        },
        messages:{
            password:{
                required:'请输入新密码'
            },
            passwordVerify:{
                required:'请输入确认密码',
                equalTo: "两次输入密码不一致"
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


    /**
     *  功能描述：验证
     */

    $('#send_usermessagee_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            content:{
                required: true
            }
        },
        messages:{
        	content:{
                required:'请输入消息内容'
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

function sendsysmessage(){
    $.ajax({
        url:manage_path+'/api/message/send',
        type:'POST',
        dataType:'json',
        data:$('#send_usermessagee_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('发送成功',5000);
                location.reload();
            }else{
            	$.toast('发送失败',5000);
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
	$('textarea[name=content]').val('');

    $('#myModalLabel').text('发送消息通知');

    //清空修改密码参数
    $('#reset_password').val('');
    $('#reset_passwordVerify').val('');



}

/**
 *  功能描述：获取用户列表信息
 *  请求方式：GET
 *  请求地址：/api/user_manage/list
 *  函数名称：getUserList
 */

function getUserList(){

    $.ajax({
        url: manage_path+'/api/user_manage/list',
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
                    var operation, upDown = ''; //操作按钮
                    $.each(list, function (index, item) {
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
                        var enabled = item.isAbled;//banner状态
                        if (enabled == 0) {
                            enabled = "无效";
                            upDown = ' <a href="#" class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="modifyStatus(' + item.id + ',1)"><i class="icon-ok-circle"></i></a>';
                        } else {
                            enabled = "有效";
                            upDown = ' <a href="#" class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="modifyStatus(' + item.id + ',0)"><i class="icon-ban-circle"></i></a>';
                        }

                        var Deleted ='<a href="user_detail.jsp?id='+item.id+'" class="btn mini purple" data-toggle="tooltip" data-placement="top" title="查看" ><i class="icon-tasks"></i></a>&nbsp;'

                        var push ='<a href="user_push.jsp?id='+item.id+'" class="btn yellow mini" data-toggle="tooltip" data-placement="top" title="发布" ><i class="icon-hand-right"></i></a>&nbsp;'

                        var citylist ='<a href="city_list.jsp?id='+item.id+'" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="定制城市" ><i class="icon-globe"></i></a>&nbsp;'

                        //操作按钮拼接
                        operation = upDown + ' <a href="user_edit.jsp?id='+item.id+'" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" ><i class="icon-edit icon-white"></i></a> '
                        	+ Deleted + push + citylist;

                        temp += '<tr>'
                        	+'<td data-title="">' +'<input type="checkbox" name="chooseTag" ><input type="hidden" name="idArr" value="'+item.id+'"/>'+ '</td>'
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
                            + '<td data-title="状态">'+  enabled +  '</td>'
                            + '<td data-title="平台">'+  platform +  '</td>'
                            + '<td data-title="操作">' + operation + '</td>'
                            + '</tr>';
                    })
                    $('#user_manage_list tbody').html(temp);
                    // $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    $("[data-toggle='modal']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
                    // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getUserList, '#pageNum');

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




/**
 *  功能描述：启用、禁用
 *  请求方式：POST
 *  请求地址：/api/user_manage/modify_status
 *  函数名称：modifyStatus
 *  参数：id:banner主键ID; beUsed:启用禁用;
 */

function modifyStatus(id,beUsed){
    $.ajax({
        url:manage_path+ '/api/user_manage/modify_status',
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
                    window.location.href = 'user_list.jsp';
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
 *  功能描述：获取用户密码信息
 *  请求方式：GET
 *  请求地址：/api/user_manage/detail
 *  函数名称：getUserDetail
 *  参数：id:ID
 */

function getUserDetail(id){
    $.ajax({
        url:manage_path+'/api/user_manage/detail',
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
                var json = data.data;

                $('#reset_id').val(json.id);
                $('#reset_userName').val(json.userName);

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
 *  功能描述：获取用户到期时间信息
 *  请求方式：GET
 *  请求地址：/api/user_manage/detail
 *  函数名称：getExpireDate
 *  参数：id:ID
 */

function getExpireDate(id){
    $.ajax({
        url:manage_path+'/api/user_manage/detail',
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
                var json = data.data;

                $('#resetExpireDate_id').val(json.id);
                $('#reset_expireDate').val(json.userName);
                $('input[name=startTime]').val(timestampFormat(json.startTime));
                $('input[name=endTime]').val(timestampFormat(json.endTime));

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
 *  功能描述：重置用户信息
 *  请求方式：POST
 *  请求地址：/api/user_manage/reset
 *  函数名称：resetUserInfo
 */
function resetUserInfo() {
    var passVal = $('#reset_password').val();
    var data;
    if (passVal != null && passVal != ""){
        data = $('#reset_password_form').serialize();
    }else {
        data = $('#reset_expireDate_form').serialize()
    }

    $.ajax({
        url:manage_path+'/api/user_manage/reset',
        type:'POST',
        dataType:'json',
        data: data,
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',1000);
                setTimeout(function(){
                    window.location.href = 'user_list.jsp';
                },1000);
            }else {
                $.toast('操作失败,系统错误',1000);
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
 *  功能描述：用户设备解绑
 *  请求方式：POST
 *  请求地址：/api/user_manage/reset
 *  函数名称：resetUserInfo
 */
function userUnbind(id) {
    $.ajax({
        url:manage_path+'/api/binding/unbind',
        type:'POST',
        dataType:'json',
        data: {userId:id},
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',1000);
                setTimeout(function(){
                    window.location.href = 'user_list.jsp';
                },1000);
            }else {
                $.toast('操作失败,系统错误',1000);
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


