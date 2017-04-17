'use strict'

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
        istime: true,
        format: 'YYYY-MM-DD hh:mm:ss', //自动生成的时间格式
        min: '1900-01-01 00:00:00', //创建当前日期为最小日期
        max: '2099-06-16 23:59:59', //创建最大日期
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        istime: true,
        format: 'YYYY-MM-DD hh:mm:ss', //自动生成的时间格式
        min: '1900-01-01 00:00:00',
        max: '2099-06-16 23:59:59', //创建最大日期
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };

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