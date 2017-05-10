'use strict'

$(function(){
	
	getPhoneList();

	$('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getPhoneList();//获取banner列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });
});

/**
 *  功能描述：获取话务列表信息
 *  请求方式：GET
 *  请求地址：/api/phone/list
 *  函数名称：getPhoneList
 */

function getPhoneList(){
    var temp = "";
    var enabled="";
    $.ajax({
        url: manage_path+'/api/phone/list',
        type: 'GET',
        dataType: 'json',
        data: $('#phone_list_form').serialize(),
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
	                    
	                    temp += '<tr>'
	                        + '<td data-title="用户编号">' + item.custId + '</td>'
	                        + '<td data-title="打入电话">' + item.inPhone + '</td>'
	                        + '<td data-title="打入时间">' + DateHandle(item.inTime) + '</td>'
	                        + '<td data-title="断开时间">' + DateHandle(item.outTime) + '</td>'
	                        + '<td data-title="时长">' + item.duration + '</td>'
	                        + '<td data-title="客服编号">' + item.serviceNum + '</td>'
	                        + '</tr>';
	                    
	                });
	                var item = list[0];
	                var foot = '<tr>'
                        + '<td data-title="用户编号">总计</td>'
                        + '<td data-title="打入电话">'+ item.count +'</td>'
                        + '<td data-title="打入时间"></td>'
                        + '<td data-title="断开时间"></td>'
                        + '<td data-title="时长">' + item.sumduration + '</td>'
                        + '<td data-title="客服编号"></td>'
                        + '</tr>';
	                
	                $('#phone_list tbody').html(temp);
	                $('#phone_list tfoot').html(foot);
	                $("#total").html(item.total);
	                $("#totaltime").html(item.totaltime);
	                $("[data-toggle='popover']").popover();
	                //操作按钮hover显示详情
	                $("[data-toggle='tooltip']").tooltip();
	                // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
	                // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
	                page('#pagination', json.pagecount, json.pageindex, json.pagesize, getPhoneList, '#pageNum');
	                
                }else{
                    $.toast("没有查到数据",3000);
                    $('#phone_list tbody').html('');
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
