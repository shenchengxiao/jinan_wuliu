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

    var clicknum = 1;
    var idsArr  = [];
    //ids 传递ids的集合对象
    function setIdsInfo(){
        var arrId = [];
        $.each(idsArr,function(index,item){
            arrId.push(item);
        });
        $('#query_ids').val(arrId);
    }



    //全选||非全选
    $('#btn_chooseAll').on('click',function(){
        if(clicknum%2){
            //第一次点击
            $('input[name="idsArr"]').each(function(i){
                idsArr.push($(this).val());
            });
            setIdsInfo();
            $('#btn_chooseAll').text('取消全选');
            $('input[name="chooseTag"]').prop({
                checked: 'checked'
            })
        }else{
            //第二次点击
            idsArr.length=0;
            setIdsInfo();
            $('#btn_chooseAll').text('全选');
            $('input[name="chooseTag"]').prop({
                checked: ''
            })
        }
        clicknum++;
    });


    //绑定所有tbody下的tr
    $('#query_list tbody').on('click','tr',function(){
        var check = $(this).find("input[type='checkbox']");
        var thisID = $(this).find("input[name=idsArr]").val();
        if(check){
            var flag = check[0].checked;
            if(flag){
                check[0].checked = false;
                removeInArr(thisID);
                $(this).find("td").css({
                    backgroundColor: ''
                })
                setIdsInfo();
            }else{
                check[0].checked = true;
                idsArr.push(thisID);
                $(this).find("td").css({
                    backgroundColor: '#bee4ca'
                })
                setIdsInfo();
            }
        }

    });


    //防止冒泡事件
    $('#query_list tbody').on('click','input[name="chooseTag"]',function(event) {
        event.stopImmediatePropagation();
        var thisID = $(this).next('input').val();
        if($(this).is(':checked')){
            idsArr.push(thisID);
            setIdsInfo();
            $(this).parent().parent().find("td").css({
                backgroundColor: '#bee4ca'
            })
        }else{
            removeInArr(thisID);
            setIdsInfo();
            $(this).parent().parent().find("td").css({
                backgroundColor: ''
            })
        }
    })



    //查找某个值在数组中的位置
    function indexOfInArr(val) {
        for (var i = 0; i < idsArr.length; i++) {
            if (idsArr[i] == val) return i;
        }
        return -1;
    };

    //定义一个remove的方法
    function removeInArr(val) {
        var index = indexOfInArr(val);
        if (index > -1) {
            idsArr.splice(index, 1);
        }
    };


    $('#btn_delete').on('click',function(){
        if(idsArr == null || idsArr.length == 0 ){
            alert("请先选择一个用户");
        }else {
            batchDelete();
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
                        var type = item.type;
                        if (type == 0){
                            type= "车源";
                        }else if (type == 1){
                            type = "货源";
                        }else {
                            type = "";
                        }
                        temp += '<tr>'
                            +'<td data-title="">' +'<input type="checkbox" name="chooseTag" ><input type="hidden" name="idsArr" value="'+item.id+'"/>'+ '</td>'
                            + '<td data-title="用户名称">' + item.userName + '</td>'
                            + '<td data-title="搜索内容">' + item.searchContent + '</td>'
                            + '<td data-title="类型">' + type + '</td>'
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


function batchDelete(){
    if(!confirm("确定要删除吗?")) return;
    $.ajax({
        url: manage_path+'/api/query/batch_delete',
        type: 'POST',
        dataType: 'json',
        data: {
            'ids':$("#query_ids").val()
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getSearchLogList();
                $('#query_ids').val("");
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








