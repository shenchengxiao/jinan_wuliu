'use strict'

$(function(){

    getCleanItenLogList();//先执行一次获取banner列表信息；
    /*$('#btn_search_item_log').on('click',function(){
        $("#pageNum").val(1);
        getCleanItenLogList();//获取banner列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search_item_log").click();
        }
    });*/

});


function getCleanItenLogList() {

    $.ajax({
        url: manage_path+'/api/item/clean_notes',
        type: 'GET',
        cache:false,
        dataType: 'json',
        data: $('#item_log_list_form').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0) {
                var json = data.data;
                var list = json.result;
                var temp = "";
                if (list != null && list.length>0){
                    $.each(list, function (index, item) {

                       /* var _typeId = item.typeId;
                        if(_typeId == 0){
                            _typeId = "车源";
                        }else{
                            _typeId = "货源";
                        }

                        //截取字符串
                        var item_content = item.content.toString();
                        if (item_content.length > 10 && item_content != 0){
                            item_content = item_content.substring(0,40)+"...";
                        }else {
                            item_content
                        }

                        var item_status = item.status;
                        if(item_status == 1){
                            item_status = "已成交";
                        }else{
                            item_status = "";
                        }

                        var num = item.userNum;
                        if(num == null){
                            num = "";
                        }*/

                        temp += '<tr>'
                            + '<td data-title="操作时间">' + timestampFormat(item.createtime) + '</td>'
                            + '<td data-title="操作内容" style="color:#0b94ea">' + "服务器清空信息表" + '</td>'
                            + '<td data-title="操作数量">' + item.sumcount + '</td>'
                            + '<td data-title="操作结果">' + item.results + '</td>'
                            + '</tr>';
                    })
                    $('#clean_item_list tbody').html(temp);
                    $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    $("[data-toggle='modal']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
                    // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getCleanItenLogList, '#pageNum');

                }else{
                    $.toast("没有查到数据",3000);
                    $('#clean_item_list tbody').html('');
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











