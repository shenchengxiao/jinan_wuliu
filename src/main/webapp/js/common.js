'use strict'
//upload
;(function ($) {
    function fullPath(path) {
        if(/^http:\/\//.test(path)) return path;
        return location.host.replace(/^cms\.(.+)/,"http://img.$1/") + path;
    }
    $.extend($.fn, {
        fileUpload: function () {
            //上传控件处理
            $(this).each(function () {
                var $this = $(this);
                var filesizelimit = $(this).data('filesizelimit') || "100KB";
                var multi = $this.data('multi') || false;
                var filters = $this.data('filters') || '*.gif;*.jpg;*.jpeg;*.png';
                var $vt = $($this.data('value-target') || '');
                var $pt = $($this.data('preview-target') || '');

                function previewRender(path) {
                    var url = fullPath(path);
                    var values = multi ? ($this.data('values') || []) : [];
                    values.push(path);
                    $this.data('values', values);
                    $vt.val(values.join(','));

                    $pt.is('img') && $pt.attr('src', url);
                    if (multi && $pt.is('div')) {
                        var remove = function () {
                            var $item = $(this).parents('span');
                            var index = $('span', $pt).index($item);
                            $item.fadeOut().remove();
                            values.splice(index, 1);
                            $vt.val(values.join(','));
                            return false;
                        }
                        $('<img style="width:150px;height:150px"/>').attr('src', url)
                            .wrap('<span class="pic_Show_box"></span>').parent()
                            .append($('<a href="javascript:void(0)" class="pic_Remove_btn"></a>').on('click', remove))
                            .appendTo($pt);
                    }
                }

                $vt.on('change', function () {
                    $this.data('values', null);
                    $(this).val().split(',').forEach(function (v) {
                        previewRender(v);
                    })
                });

                $this.uploadify({
                    'swf': '/js/libs/uploadify/uploadify.swf',
                    'uploader': fullPath('api/upload/file'),
                    'buttonText': '浏览',
                    'fileObjName': 'file',
                    'fileTypeExts': filters,
                    'fileSizeLimit':filesizelimit,
                    'multi': multi,
                    'removeCompleted': true,
                    'onUploadSuccess': function (file, data) {
                        var json = JSON.parse(data);
                        previewRender(json.data.md5);
                    },
                    'onUploadProgress': function (file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
                        console.log(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
                    },
                    'onUploadError': function (file, errorCode, errorMsg, errorString) {
                        console.log('The file ' + file.name + ' could not be uploaded: ' + errorString);
                    }
                });
            });
        }
    });
})($);

var FILE_URL  = '';
var provinceId = 0;


$(function () {
    App.init();
    //设置用户名


    $("#username").html(App.req.user.uname);
    $("#logout").click(logout); //退出登录

    $('input[data-laydate="start"]').click(function(){
        laydate(start);
    });
    $('input[data-laydate="end"]').click(function(){
        laydate(end);
    });

    /**
     *  日期插件初始化
     */
    var start = {
        format: 'YYYY-MM-DD', //自动生成的时间格式
        min: '2000-01-01', //创建当前日期为最小日期
        max: '2099-06-16', //创建最大日期
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        format: 'YYYY-MM-DD', //自动生成的时间格式
        min: '2000-01-01',
        max: '2099-06-16', //创建最大日期
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };

    //图片加载错误处理,3次重试机会
    $('div.page-content').on('error','img',function () {
        var $this = $(this);
        var try_times = $this.data('try-times') || 0;
        $this.data('try-times',++try_times);
        if(try_times>3) return;

        setTimeout(function(){
            var src = $this.attr('src');
            if(/^group1\/M00\/00\/01/.test(src)){
                src = location.host.replace(/^cms\.(.+)/,"http://img.$1/") + src;
            }
            $this.attr('src',src);
        },500);
    });
});

// 获取角色 
var Role = {
    SuperAdmin:{Name:"超级管理员",Value:16},
    CustomerServer:{Name:"客服人员",Value:32}
}

function getRoleName(roleNum,Role){
    //角色分配
    var role = roleNum; //-这里是从服务器端获取的值，比如是160  
    var roleName = '';
    for(var r in Role){
        if(Role[r].Value & role) 
        {
            roleName += Role[r].Name + ',';
        }
    }
    if (roleName.length > 0)
    {
        roleName = roleName.substring(0,roleName.length-1);
    }
    return roleName;
}


// 拆分role 
function getRoleValue(roleNum,Role){
    var role = roleNum;
    var roleValue = '';
    for(var r in Role){
        if(Role[r].Value & role){
            roleValue += Role[r].Value + ',';
        }
    }
    if(roleValue.length > 0 ){
        roleValue = roleValue.substring(0,roleValue.length-1);
    }
    return roleValue;
}

// 文件上传
$('input[data-file="upload"]').change(function(){
    $('.fileupload-preview').html('');
    if ($(this).outerHTML) {
        $(this).outerHTML = $(this).outerHTML;
    } else { // FF(包括3.5)
        $(this).value = "";
    }
    $('.fileupload-preview').html($(this).val());
    $('#upload_msgBox').modal('show');
});
function uploadFile(fileId,callBack){
    var fileTarget = "#" + fileId;
    //var errorTarget = "#" + errorid;
    var file = $(fileTarget).val();
    if (file != null && file != '') {
        //$(errorTarget).html("上传中，请稍后");
        $.ajaxFileUpload({
            url : FILE_URL+'/api/web/upload/submit',
            secureuri : false,
            fileElementId : fileId,
            dataType : 'json',
            success : function(data, textStatus) {
                console.log(data);
                if (data.code == 200) {
                    $.toast(data.message,3000);
                    $.globalEval(callBack(data.fileInfo));
                }else{
                    $.toast(data.message,3000);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                $.toast('服务器未响应,请稍候重试',5000);
            }
        });
    }else{
        $.toast('请选择上传文件',3000);
    }
}

/**
 * 上传文件回调
 */
function uploadCallBack(fileInfo) {

    $("#excelInfo").append('<input type="hidden" name="path" value="' + fileInfo.path + '">');
}

/**
 * 获取url参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(encodeURI(r[2]));
    return null; //返回参数值
};


//退出登录
function logout() {
    $.ajax({
        url: manage_path+'/api/user/logout',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            sessionStorage.clear();
            window.location.href =manage_path+ '/views/login.jsp';
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });
}

/**
 * 设置分页
 */
function page(pageId, totalPage, pageNo, pageSize, func, pageNum) {
    $(pageNum).val(pageNo);
    if (parseFloat(totalPage) > 1) {
        $(pageId).jqPaginator({
            totalPages: totalPage,
            visiblePages: pageSize,
            currentPage: pageNo,
            first: '<li class="first"><a href="javascript:;">首页</a></li>',
            prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
            next: '<li class="next"><a href="javascript:;">下一页</a></li>',
            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
            last: '<li class="last"><a href="javascript:;">尾页</a></li>',
            onPageChange: function (num, type) {
                if (type == 'init') {
                    return;
                }
                $(pageNum).val(num);
                func();
            }
        });
    } else {
        if ($(pageId).html().length > 1) {
            $(pageId).jqPaginator("destroy");
        }
    }
}
/**
 * 检测是否为空
 */
function isNull(str) {
    if (null == str || '' == str || undefined == str || 'unknown' == str || "null" == str) {
        return true;
    } else {
        return false;
    }
}

/**
 * 在数组中查找某个数
 */
function findInArr(item,arr){
    for(var i=0;i<arr.length;i++){
        if(item == arr[i]){
            return true;
        }
    }
    return false;
}




// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 时间格式
 * @param timestamp
 * @returns {*}
 */
function timestampFormat(timestamp) {
    return (new Date(timestamp)).Format('yyyy-MM-dd hh:mm:ss');
}

/**
 *  功能描述：获取省份
 *  请求方式：GET
 *  请求地址：/api/channel/province
 *  函数名称：getProvince
 */
function getProvince() {
    $('#province').html('<option value="">全部区域</option>');
    $.ajax({
        url: '/api/channel/province',
        type: 'get',
        dataType: 'json',
        //beforeSend: function (data) {
        //    $.progressBar({message: '<p>正在努力加载数据...</p>', modal: true, canCancel: true});
        //},
        success: function (data) {
            if (data.status == 0) {
                var json = data.data;
                $.each(json, function (index, item) {
                    $('<option '+ (item.province_id == provinceId?'selected="selected"':'') +' value="' + item.province_id + '">' + item.province_name + '</option>').appendTo($('#province'));                });
            } else {
                $.toast('没有查到数据！', 3000);
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
 * 当数为负数时，返回
 */
function getPositiveNum(num) {
    return num < 0 ? 0 : num;
}

/**
 * 获取cookie
 */
function getCookie(name, defaultValue) {
        var re = new RegExp(name + '=([^;]*);?', 'gi');
        var v = typeof defaultValue == "undefined" ? null : defaultValue;
        var r = re.exec(document.cookie) || [];
}
/**
 * 设置cookie
 */
function setCookie(name, value, option) {
    var str = name + '=' + escape(value);
    if (option) {
        if (option.expireHours) {
            var d = new Date();
            d.setTime(d.getTime() + option.expireHours * 3600 * 1000);
            str += '; expires=' + d.toGMTString();
        }
        if (option.path) str += '; path=' + option.path;
        else str += '; path=/';
        if (option.domain) str += '; domain=' + option.domain;
        if (option.secure) str += '; true';
    }
    document.cookie = str;
}