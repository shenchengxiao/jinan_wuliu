$(function(){
    // 获取角色列表
    getKlUserList();
    /**
     *  功能描述：添加角色表单验证
     *  函数名称：FormValidation
     */

    $('#add_role_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            userName:{
                minlength:6,
                required:true
            },
            roleArr:{
                minlength:1,
                required:true
            },
            password:{
                minlength:6,
                required:true
            }

        },
        messages:{
            userName:{
                minlength:'用户名至少需要6位',
                required:'请输入用户名'
            },
            roleArr:{
                required:'请分配角色'
            },
            password:{
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

    // 添加角色
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
        $('input[name=id]').val('');
        $('input[name=userName]').val('').removeAttr('readonly');
        $('input[name=password]').val('').data("original","");
        var aCh = $('input[name=roleArr]');
        $.each(aCh,function(index,item){
            item.checked = false;
        });
    });

});


// 设置角色选中状态
function checkBox(typeStr) {
    var roleArr = typeStr.split(",");
    var aCh = $('input[name=roleArr]');
    for(var i=0;i<aCh.length;i++){
        aCh[i].checked = false;
    }
    for (var i = 0; i < roleArr.length; i++) {
        for (var j = 0; j < aCh.length; j++) {
            if (roleArr[i] == aCh[j].value ) {
                aCh[j].checked = true;
            }
        }
    }
}

/**
 *  功能描述：添加角色
 *  请求方式：GET
 *  请求地址：/api/user/create
 *  函数名称：addRole
 */
function addRole(){
    var aCh = $('input[name=roleArr]');
    var isChecked = false;
    for(var i=0;i<aCh.length;i++){
        if(aCh[i].checked){
            isChecked = true;
        }
    }
    //如果密码没有变化,则不提交
    var data = $('#add_role_form').serialize();
    var oriPassword = $('input[name=password]').data("original");
    if($('input[name=password]').val()==oriPassword){
        data = data.replace(/&password=[^&]*&/,"&");
    }
    // if(!isChecked){
    //     $.toast('请至少分配一个角色',2000);
    //     return false;
    // }
    $.ajax({
        url:'/api/user/create',
        type:'post',
        dataType:'json',
        data:data,
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('提交成功',3000);
                getKlUserList();
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
 *  功能描述：编辑角色
 *  请求方式：GET
 *  请求地址：/api/user/get_detail
 *  函数名称：editRole
 *  @param ：id
 */
function editRole(id){
    $.ajax({
        url:'/api/user/get_detail',
        type:'GET',
        dataType:'json',
        data:{
            id:id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            console.log(data);
            if(data.status == 0){
                var json = data.data;
                $('input[name=id]').val(id);
                $('input[name=userName]').val(json.businessAccount).attr('readonly',"readonly");
                $('input[name=password]').val(json.businessPasswd).data('original',json.businessPasswd);
                checkBox(getRoleValue(json.role,Role));
                // checkBox('32,64');
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
 *  功能描述：获取用户列表
 *  请求方式：GET
 *  请求地址：/api/user/lis
 *  函数名称：getKlUserList
 *  businessAccountId:6,商家类型ID固定为6
 */
function getKlUserList(){
    $.ajax({
        url:"/api/user/list",
        type:"get",
        dataType:"json",
        data: $('#kl_user_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var arow = '';
                var json = data.data;
                var list = data.data.result;
                var aList = list.length;
                var operate = '';
                var businessName = data.data.result[0].businessName;
                $("#manageuser").html(businessName);
                for(var i=0;i<list.length;i++){
                    //角色分配
                    var role = list[i].role; //-这里是从服务器端获取的值，比如是160
                    if(role & Role.SuperAdmin.Value) continue; //超级管理员隐藏

                    var roleName = '';
                    for(var r in Role){
                        if(Role[r].Value & role)
                        {
                            roleName += Role[r].Name + '+';
                        }
                    }
                    if (roleName.length > 0)
                    {
                        roleName = roleName.substring(0,roleName.length-1);
                    }

                    var accountStatus = list[i].accountStatus;
                    switch(accountStatus)
                    {
                        case 0:
                            accountStatus='禁用'
                            break;
                        case 1:
                            accountStatus='启用'
                            break;
                    };
                    if (list[i].accountStatus == 1){
                        operate = '<a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="updateStatus('+list[i].id+',0)"><i class="icon-ban-circle"></i></a>';
                    }else{
                        operate = '<a class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="updateStatus('+list[i].id+',1)"><i class="icon-ok"></i></a>';
                    }

                    arow+='<tr><td data-title="用户名">'+list[i].businessAccount+'</td>'+
                        '<td data-title="管理权限">'+getRoleName(list[i].role,Role)+'</td>'+
                        '<td data-title="状态">'+accountStatus+'</td>'+
                        '<td data-title="操作">'+
                        '<a class="btn mini blue" data-toggle="modal" href="#addUserModal" onclick="editRole('+list[i].id+')" data-toggle="tooltip" data-placement="top" title="编辑"><i class="icon-edit icon-white"></i></a>&nbsp;'+operate+
                        '</td> </tr>';
                }
                $('#kl_user_list tbody').html(arow);
                //按钮hover详情
                $("[data-toggle='tooltip']").tooltip();
                $("[data-toggle='modal']").tooltip();
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getKlUserList,'#pageNum');
            }else{
                $.toast('没有查到数据！',3000);
                $('#kl_user_list tbody').html('');
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
 *  请求地址：/api/user/modify_status
 *  函数名称：updateStatus
 */
function updateStatus(id,accountStatus){
    $.ajax({
        url: "/api/user/modify_status",
        type: "POST",
        dataType: "json",
        data:{
            'id':id,
            'accountStatus':accountStatus
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getKlUserList();
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


