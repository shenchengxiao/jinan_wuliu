'use strict'
$(function(){
    $('#btn_search').on('click',function(){
        $("#pageNum").val(1);
        getProductList();
    }).click();
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });
});
/**
 *  功能描述：通道管理
 *  请求方式：GET
 *  请求地址：/api/upstream/package/page
 *  函数名称：getProductList
 */
var upstreamAccountInfoId=getUrlParam("id");
var belongType = getUrlParam("belongType");
function getProductList(){

    $.ajax({
        url:"/api/upstream/package/page?upstreamAccountInfoId="+upstreamAccountInfoId,
        type:"get",
        dataType:"json",
        data:$('#pipe_product_list').serialize(),
        beforeSend:function(data){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                console.log(json);
                var list = json.result;
                var temp = '';
                var operate = '';
                $.each(list,function(index,item){
                    var isUsed = item.isUsed;
                    switch(isUsed)
                    {
                        case 0:
                            isUsed='禁用'
                            break;
                        case 1:
                            isUsed='启用'
                            break;
                    }
                    // if (item.isUsed == 1){
                    //     operate = '<button type="button" class="btn mini grey" onclick="updateStatus('+item.id+',2)">禁用</button>&nbsp;';
                    // }else if (item.isUsed == 0){
                    //     operate = '<button type="button" class="btn mini red" onclick="updateStatus('+item.id+',1)">启用</button>&nbsp;';
                    // }
                    if (item.isUsed == 1){
                        operate = '<a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="updateStatus('+item.id+',2)"><i class="icon-ban-circle"></i></a>&nbsp;';
                    }else{
                        operate = '<a class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="updateStatus('+item.id+',1)"><i class="icon-ok"></i></a>&nbsp;';
                    }

                    temp +='<tr>'+
                        '<td data-title="产品名称">'+item.productName+'</a></td>'+
                        '<td data-title="产品描述">'+item.productDesc+'</td>'+
                        '<td data-title="包大小">'+item.packageSize+'</td>'+
                        '<td data-title="基准价">'+item.standardPrice+'</td>'+
                        '<td data-title="折扣价">'+item.discountPrice+'</td>'+
                        '<td data-title="创建时间">'+item.createTime+'</td>'+
                        '<td data-title="状态">'+isUsed+'</td>'+
                        '<td data-title="操作">'+operate+
                        //'<a class="btn mini blue" data-toggle="modal" href="/html/upstream/edit_pipe_product.html?id='+item.id+'&upstreamAccountInfoId='+item.upstreamAccountInfoId+'" >修改</a>'+
                        '<a data-toggle="modal" href="/html/upstream/edit_pipe_product.html?id='+item.id+'&upstreamAccountInfoId='+item.upstreamAccountInfoId+'" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑"><i class="icon-edit icon-white"></i></a>&nbsp'+
                        '</td>'+
                        '</tr>';
                });

                $('#product_list tbody').html(temp);
                $("[data-toggle='tooltip']").tooltip();
                $("[data-toggle='modal']").tooltip();
                // 表格分页
                page('#pagination',json.pagecount,json.pageindex,json.pagesize,getProductList,'#pageNum');
            }else{
                $.toast(data.msg,3000);
                $('#product_list tbody').html('');
                if($('#pagination').html().length > 0){
                    $('#pagination').jqPaginator('destory');
                }
            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.toast('服务器未响应,请稍候重试',5000);
        }
    });
}

/**
 *  功能描述：启用禁用
 *  请求方式：POST
 *  请求地址：/api/upstream/package/editor
 *  函数名称：updateStatus
 */

function updateStatus(id,modifyType){
    $.ajax({
        url:'/api/upstream/package/editor',
        type:'post',
        dataType:'json',
        data:{
            'id':id,
            'modifyType':modifyType
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            console.log(data);
            if(data.status == 0){
                $.toast(data.msg,3000);
                getProductList();
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
    });
}

//将ID传过去
function transferId(){
    window.location.href="add_pipe_product.html?upstreamAccountInfoId="+upstreamAccountInfoId+"&&belongType="+belongType;
}


