$(function(){
    // 获取用户列表
    getUserList();
    /**
     *  功能描述：添加用户表单验证
     *  函数名称：FormValidation
     */

    $('#add_role_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            userName:{
                // minlength:,
                required:true
            },
            roleArr:{
                minlength:1,
                required:true
            },
            passwd:{
                minlength:6,
                required:true
            }

        },
        messages:{
            userName:{
                // minlength:'用户名至少需要6位',
                required:'请输入用户名'
            },
            roleArr:{
                required:'请分配角色'
            },
            passwd:{
                minlength:'密码不能少于6',
                required:'请输入密码'
            }
        },
        errorPlacement: function (error, element) { // render error placement for each input type
            if (element.attr("name") == "roleArr") { // for uniform checkboxes, insert the after the given container
                error.addClass("no-left-padding").insertAfter("#form_2_service_error");
            } else {
                error.insertAfter(element); // for other inputs, just perform default behavoir
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

    // 添加修改角色
    $('#btn_add_role').click(function(){
        var b = $('#add_role_form').valid();
        if(b){
            addRole();
            $('#addUserModal').modal('hide')
        }else{
            return false;
        }
    });


    $('#btn_addRole_modal').on('click',function(){
        //清空model
        $('input[name=id]').val('');
        $('input[name=userName]').val('');
        $('input[name=passwd]').val('').data("original","");
        $('input[name=roleArr]').parent().removeClass('checked');
        $('#myModalLabel').text('添加用户');
        $('#addUserModal').modal('show')
    });

});


// 设置角色选中状态
function checkBox(typeStr) {
    var roleArr = typeStr.split(",");
    var aCh = $('input[name=roleArr]');
    console.log(aCh);
    for(var i=0;i<aCh.length;i++){
        aCh[i].defaultChecked = false;
    }
    for (var i = 0; i < roleArr.length; i++) {
        for (var j = 0; j < aCh.length; j++) {
            if (roleArr[i] == aCh[j].value ) {
                aCh[j].defaultChecked = true;
                var index = j;
                $('input[name=roleArr]').eq(index).parent().addClass('checked');//父类选中
                $('input[name=roleArr]').eq(index).prop("checked",true);  //本身选中
            }
        }
    }
}

/**
 *  功能描述：添加用户
 *  请求方式：GET
 *  请求地址：/api/user/create
 *  函数名称：addRole
 */
function addRole(){

    var data = $('#add_role_form').serialize();
    $.ajax({
        url:manage_path+'/api/user/add',
        type:'post',
        dataType:'json',
        data:data,
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('提交成功',1000);
                setTimeout(function(){
                    window.location.reload();
                },1000)
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
 *  功能描述：获取用户详情
 *  请求方式：GET
 *  请求地址：/api/user/detail
 *  函数名称：editRole
 *  @param ：id
 */
function getUserDetail(id){
    $('#addUserModal').modal('show');
    $.ajax({
        url:manage_path+'/api/user/detail',
        type:'GET',
        cache:false,
        dataType:'json',
        data:{
            id:id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $('#myModalLabel').text('修改用户');
                var json = data.data;
                $('input[name=id]').val(id);
                $('input[name=userName]').val(json.userName);
                $('input[name=passwd]').val(json.passwd).data('original',json.passwd);

                //先清空
                $('input[name=roleArr]').parent().removeClass('checked');
                checkBox(getRoleValue(json.role,Role));
                // checkBox('32');
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


1

/**
 *  功能描述：获取用户列表
 *  请求方式：GET
 *  请求地址：/api/user/lis
 *  函数名称：getUserList
 */
function getUserList(){
    $.ajax({
        url:manage_path+"/api/user/list",
        type:"get",
        cache:false,
        dataType:"json",
        data: $('#jn_user_list').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0) {
                var arow = '';
                var json = data.data;
                var list = data.data.result;
                if (list != null && list.length > 0) {
                    var operate = '';
                    for (var i = 0; i < list.length; i++) {
                        //角色分配
                        var role = list[i].role;
                        var roleName = '';
                        for (var r in Role) {
                            if (Role[r].Value & role) {
                                console.log(Role[r].Value & role);
                                roleName += Role[r].Name + '+';
                            }
                        }
                        if (roleName.length > 0) {
                            roleName = roleName.substring(0, roleName.length - 1);
                        }
                        //转换状态值
                        var accountStatus = list[i].beUsed;
                        if (accountStatus == 1) {
                            accountStatus = "启用";
                            operate = '<a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="updateStatus(' + list[i].id + ',0)"><i class="icon-ban-circle"></i></a>';
                        } else {
                            accountStatus = "禁用";
                            operate = '<a class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="updateStatus(' + list[i].id + ',1)"><i class="icon-ok"></i></a>';
                        }
                        var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="removeUser(' + list[i].id + ')"><i class="icon-remove icon-white"></i></a>';
                        arow += '<tr><td data-title="用户名">' + list[i].userName + '</td>' +
                            // '<td data-title="手机号">'+list[i].phoneNum+'</td>'+
                            '<td data-title="管理权限">' + getRoleName(list[i].role, Role) + '</td>' +
                            '<td data-title="状态">' + accountStatus + '</td>' +
                            '<td data-title="操作">' +
                            '<a class="btn mini blue" onclick="getUserDetail(' + list[i].id + ')" data-toggle="tooltip" data-placement="top" title="编辑"><i class="icon-edit icon-white"></i></a>&nbsp;' + operate + Deleted
                        '</td> </tr>';
                    }
                    $('#jn_user_list tbody').html(arow);
                    //按钮hover详情
                    $("[data-toggle='tooltip']").tooltip();
                    $("[data-toggle='modal']").tooltip();
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getUserList, '#pageNum');
                } else {
                    $.toast('没有查到数据！', 3000);
                    $('#jn_user_list tbody').html('');
                    if ($('#pagination').html().length > 0) {
                        $('#pagination').jqPaginator("destroy");
                    }
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
 *  请求地址：/api/user/modify_status
 *  函数名称：updateStatus
 */
function updateStatus(id,accountStatus){
    $.ajax({
        url:manage_path+ "/api/user/modify_status",
        type: "POST",
        dataType: "json",
        data:{
            'id':id,
            'status':accountStatus
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getUserList();
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
 *  请求地址：/api/user/delete
 *  函数名称：removeUser
 *  参数：id: 主键ID;
 */

function removeUser(id){
    if(!confirm("确定删除吗?")) return;
    $.ajax({
        url:manage_path+ '/api/user/delete',
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
                $.toast(data.msg,3000);
                getUserList();
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



