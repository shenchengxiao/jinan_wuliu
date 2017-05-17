'use strict'

$(function(){
	
	getIpvisitList();


});

/**
 *  功能描述：获取IP访问列表信息
 *  请求方式：GET
 *  请求地址：/api/ipvisit/list
 *  函数名称：getBlackwordList
 */

function getIpvisitList(){
    var temp = "";
    var enabled="";
    $.ajax({
        url: manage_path+'/api/ipvisit/list2',
        type: 'GET',
        cache:false,
        dataType: 'json',
        data: $('#ipvisit_list_form').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0) {
                var json = data.data;
                var list = json.result;
                var operation, upDown = ''; //操作按钮
                if (list != null && list.length>0){
	                $.each(list, function (index, item) {
	                	var _typeId = item.statues;//banner状态
	                    if(_typeId == 0){
	                    	_typeId = "正常";
	                    }else if(_typeId == 1){
	                    	_typeId = "禁止登录";
	                    }else if(_typeId == 2){
	                    	_typeId = "列入黑名单";
	                    }else{
	                    	_typeId = "";
	                    }
	                    var adStatus = item.enabled;//banner状态
	//                    console.log(item.endTime);
	                    var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="modifyStatus_remove(' + item.bWId + ')"><i class="icon-remove icon-white"></i></a>';
	                    //操作按钮拼接
	                    operation = upDown + ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" onclick="getBlackwordDetail(' + item.bWId + ')"><i class="icon-edit icon-white"></i></a> '/* + Deleted*/;
	                    
	                    temp += '<tr>'
	                        + '<td data-title="用户编号">' + item.username + '</td>'
	                        + '<td data-title="用户ip">' + item.ipAddress + '</td>'
	                        + '<td data-title="用户端口">' + item.port + '</td>'
	                        + '<td data-title="访问时间">' + DateHandle(item.loginTime) + '</td>'
	                        + '<td data-title="状态">' + _typeId + '</td>'
	                        + '<td data-title="访问服务器">' + item.ip + '</td>'
	                        + '<td data-title="访问次数">' + item.visitNum + '</td>'
	                        + '<td data-title="操作">' + operation + '</td>'
	                        + '</tr>';
	                    
	                });
	                $('#ipvisit_List tbody').html(temp);
	                $("[data-toggle='popover']").popover();
	                //操作按钮hover显示详情
	                $("[data-toggle='tooltip']").tooltip();
	                // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
	                // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
	                page('#pagination', json.pagecount, json.pageindex, json.pagesize, getIpvisitList, '#pageNum');
	                
                }else{
                    $.toast("没有查到数据",3000);
                    $('#ipvisit_List tbody').html('');
                    if($('#pagination').html().length > 0){
                        $('#pagination').jqPaginator('destroy');
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

function DateHandle(objDate)  
{  
            objDate=new Date(objDate); //创建一个日期对象表示当前时间     
            var year=objDate.getFullYear();   //四位数字年     
            var month=objDate.getMonth()+1;   //getMonth()返回的月份是从0开始的，还要加1     
            var date=objDate.getDate();     
            var hours=objDate.getHours();     
            var minutes=objDate.getMinutes();     
            var seconds=objDate.getSeconds();     
            var date = year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;
            
            return date;   
}








