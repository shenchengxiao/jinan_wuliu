
$(function(){

    getUpgradeDetail();

    $('#btn_edit_upgrade').on('click',function(){
        var validFlag = $('#edit_upgrade_form').valid();
        if (validFlag) {
            editUpgradeInfo();
        }else{
            return false;
        }

    });


    /**
     *  功能描述：添加用户验证
     */

    $('#edit_upgrade_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            clientName:{
                required:true
            },
            version: {
                required: true
            },
            packageUrl:{
                required: true
            }
        },
        messages:{

            clientName:{
                required:'请输入名称'
            },
            version:{
                required:'请输入版本号'
            },
            packageUrl:{
                required:'请先上传下载包'
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
 *  功能描述：修改版本信息
 *  请求方式：POST
 *  请求地址：/api/upgrade/edit
 *  函数名称：addUpgradeInfo
 */
function editUpgradeInfo(){

    $.ajax({
        url:manage_path+'/api/upgrade/edit',
        type:'POST',
        dataType:'json',
        data:$('#edit_upgrade_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',1000);
                setTimeout(function(){
                    window.location.href = 'upgrade_list.jsp';
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

var upgrade_id  = getUrlParam("id");
/**
 *  功能描述：获取详情
 *  请求方式：GET
 *  请求地址：/api/upgrade/detail
 *  函数名称：getUpgradeDetail
 *  参数：id:ID
 */

function getUpgradeDetail(){

    $.ajax({
        url:manage_path+'/api/upgrade/detail',
        type:'GET',
        dataType:'json',
        data:{
            id:upgrade_id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                $('input[name=id]').val(json.id);
                $('input[name=clientName]').val(json.name);
                $('input[name=version]').val(json.version);
                $('input[name=packageUrl]').val(json.packageUrl);
                $('#upgradeDesc').val(json.upgradeDesc);
                // $("input[name=platformType]").eq(json.platformType).parent().addClass('checked');

                $('input[name=platformType]').parent().removeClass('checked');
                $('input[name=platformType]').eq(json.platformType).parent().addClass('checked');

            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $.toast('服务器未响应，请稍候重试',5000);
        }
    })
}


/**
 * 文件上传
 */
$(function () {
    $("#file").uploadify({
        'swf': '../../js/libs/uploadify/uploadify.swf',
        'uploader': manage_path+'/api/banner/upload',
        'fileObjName': 'file',
        'multi': false,
        'buttonText': '上传文件',
        'onUploadSuccess': function (file, data) {
            var json = JSON.parse(data);
            var input = $('input[name="packageUrl"]');
            input.val(json.fileInfo.path);
        },
        'onUploadProgress': function (file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
        },
        'onUploadError': function (file, errorCode, errorMsg, errorString) {
            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        }
    });
});




