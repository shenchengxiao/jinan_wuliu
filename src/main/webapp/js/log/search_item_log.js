'use strict'

$(function(){

    getSearchLogList();//先执行一次获取banner列表信息；
    $('#btn_search_query').on('click',function(){
        $("#pageNum").val(1);
        getSearchLogList();//获取banner列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search_query").click();
        }
    });

});


function getSearchLogList() {

    $.ajax({
        url: manage_path+'/api/query/list',
        type: 'GET',
        dataType: 'json',
        data: $('#query_list_form').serialize(),
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
                        temp += '<tr>'
                            + '<td data-title="用户名称">' + item.userName + '</td>'
                            + '<td data-title="搜索内容">' + item.searchContent + '</td>'
                            + '<td data-title="查询时间">' + timestampFormat(item.createTime) + '</td>'
                            + '</tr>';
                    })
                    $('#query_list tbody').html(temp);
                    // $("[data-toggle='popover']").popover();
                    //操作按钮hover显示详情
                    $("[data-toggle='tooltip']").tooltip();
                    $("[data-toggle='modal']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
                    // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
                    page('#pagination', json.pagecount, json.pageindex, json.pagesize, getSearchLogList, '#pageNum');

                }else{
                    $.toast("没有查到数据",3000);
                    $('#query_list tbody').html('');
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











