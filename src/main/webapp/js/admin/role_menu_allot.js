'use strict'
$(function(){

    getSecondMenuList();
    //菜单选择
    $('#menu_select').multipleSelect({
        placeholder: "请选择菜单",
        filter: true,
        multiple: true,
        multipleWidth:250,
    });


    $('#btn_addRole_modal').on('click',function(){
        if (role_value == '' || role_value == null){
            alert("请先选中一个角色再操作");
        }else{
            $('#addMenu').modal('show');//modle层显示
            getSelectMenuName(role_value);
        }
    });

    //设置模态框
    $('#addMenu').on('shown.bs.modal', function () {
        $('#addMenu .modal-body').css('height','300px');
    });


    $('#btn_add_menu').on('click',function(){
        var validFlag = $('#add_role_for_menu').valid();
        if (validFlag) {
            addMenuForRole();
        }else{
            return false;
        }

    });

    $('#add_role_for_menu').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            roleType:{
                required:true
            },
            menuInfoIds: {
                required: true
            }
        },
        messages:{
            roleType:{
                required:'请选择角色类型'
            },
            menuInfoIds:{
                required:'请选中菜单'
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
 *  功能描述：获取选中的菜单
 *  请求方式：GET
 *  请求地址：/api/menu/selected_menu
 *  函数名称：getSelectMenuName
 */
function getSelectMenuName(role_value){
    $.ajax({
        url:manage_path+"/api/menu/selected_menu?roleId="+role_value,
        type:"get",
        dataType:"json",
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data.menuInfoId;
                var menu_info_id = [];
                //将选中的菜单ID放入数组中
                $.each(json,function(index,item){
                    menu_info_id.push(item);
                });
                //让已选的菜单选中
                getSecondMenuList(menu_info_id);
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
 *  功能描述：获取所有二级菜单
 *  请求方式：GET
 *  请求地址：/api/menu/second_menu_list
 *  函数名称：getSecondMenuList
 */
function getSecondMenuList(menuInfo_ids) {
    $('#menu_select').html('');
    $.ajax({
        url: manage_path+'/api/menu/second_menu_list',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.status == 0) {
                var json = data.data;
                $.each(json, function (index, item) {
                    $('#menu_select').append(
                        $('<option ' +($.inArray(item.menuInfoId,menuInfo_ids)>=0?'selected="selected"':'')+'></option>').text(item.menuName).val(item.menuInfoId)
                    );
                });
                $('#menu_select').multipleSelect('refresh');
            } else {
                $.toast(data.message, 3000);
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
 *  功能描述：分配菜单
 *  请求方式：POST
 *  请求地址：/api/relation/add
 *  函数名称：addUpAndDownRelation
 */
function addMenuForRole() {
    $.ajax({
        url: manage_path+"/api/menu/batch_update",
        type: "post",
        dataType: "json",
        data: $('#add_role_for_menu').serialize(),
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力加载数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            console.log(data);
            if(data.status == 0){
                $.toast('操作成功',1000);
                setTimeout(function(){
                    window.location.href = 'role_menu_allot.jsp';
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

//定义全局变量存角色值
var role_value='';
//获取分配的角色值
$("#roleSelect").blur(function(){
    role_value  = $('#roleSelect').val();
    $('#roleType').val(role_value);
});