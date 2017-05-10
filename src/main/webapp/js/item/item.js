'use strict'

$(function(){

    getItemList();//先执行一次获取banner列表信息；
    $('#btn_search_item').on('click',function(){
        $("#pageNum").val(1);
        getItemList();//获取banner列表信息；
    });
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#btn_search").click();
        }
    });



var clicknum = 1;
var idsArr  = [];
//ids 传递ids的集合对象
function setIdsInfo(){
    var arrId = [];
    $.each(idsArr,function(index,item){
        var IDs = {
            id:item
        };
        arrId.push(IDs);
    });

    var json = JSON.stringify(arrId);
    $('#ids').val(json);
}



//全选||非全选
$('#btn_chooseAll').on('click',function(){
    if(clicknum%2){    
        //第一次点击
        $('input[name="idArr"]').each(function(i){
            idsArr.push($(this).val());
        });
        setIdsInfo();
        console.log(idsArr);
        $('#btn_chooseAll').text('取消全选');
        $('input[name="chooseTag"]').prop({
            checked: 'checked'
        }).parent().css({
            color:'green'
        });
    }else{
        //第二次点击
        idsArr.length=0;
        setIdsInfo();
        $('#btn_chooseAll').text('全选');
        $('input[name="chooseTag"]').prop({
            checked: ''
        }).parent().css({
            color:'#000'
        });
        console.log(idsArr);
    }
    clicknum++; 
});


$('#item_List').on('change','input[name="chooseTag"]',function(){
    /*$this = $(this);*/
    var thisID = $(this).next('input').val();
    if($(this).is(':checked')){
        idsArr.push(thisID);
        setIdsInfo(); 
        $(this).parent().css({
            color:'green'
        });
        console.log(idsArr);
    }else{
        removeInArr(thisID);
        setIdsInfo();
        $(this).parent().css({
            color:'#000'
        });
        console.log(idsArr);
    }
});

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


$('#btn_remove').on('click',function(){
    delmodifyState(idsArr);
});

});

function delmodifyState(idsArr){
	if(idsArr == null || idsArr.length == 0){
        alert("请先选择信息");
	}else{
    if(!confirm("确定删除多条吗?")) return;
    $.ajax({
        url: manage_path+'/api/item/deleteItems',
        type: 'POST',
        dataType: 'json',
        data: {
        	'ids':$("#ids").val()
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getItemList();
                $('#ids').val("");
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
}
setTimeout(function(){location.reload()},30000);
function getItemList(){
    var temp = "";
    var enabled="";
    var arr = [];
    $.ajax({
        url: manage_path+'/api/item/list',
        type: 'GET',
        dataType: 'json',
        data: $('#item_list_form').serialize(), //通过表单id进行序列化提交
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
	                    var _typeId = item.typeId;
	                    if(_typeId == 0){
	                    	_typeId = '<img src="che.png"/>';
	                    }else if(_typeId == 1){
	                    	_typeId = '<img src="huo.png"/>';
	                    }else{
	                    	_typeId = "";
	                    }
	                    
	                    var item_statue = item.status;
	                    if(item_statue == 1){
	                    	item_statue = "已成交";
	                    }else{
	                    	item_statue = "未成交";
	                    }
	                    
	                    var num = item.userNum;
	                    if(num == null){
	                    	num = "";
	                    }
	                  //截取字符串
                        var advert_content = item.content.toString();
                        if (advert_content.length > 10 && advert_content != 0){
                            advert_content = advert_content.substring(0,40)+"...";
                        }else {
                            advert_content
                        }
	//                    console.log(item.endTime);
	                    var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="modifyStatus_remove(' + item.itemId + ')"><i class="icon-remove icon-white"></i></a>';
	                    //操作按钮拼接
	                    operation = upDown + ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="更改状态为已成交" onclick="updateItemStatue(' + item.itemId + ')"><i class="icon-edit icon-white"></i></a> ' + Deleted;
	                    
	                    temp += '<tr>'
	                    	+'<td data-title="">' +'<input type="checkbox" name="chooseTag"><input type="hidden" name="idArr" value="'+item.itemId+'"/>'+ '</td>'
	                        + '<td data-title="用户编号">' + num + '</td>'
	                        + '<td data-title="用户电话">' + item.userPhones + '</td>'
	                        + '<td data-title="类型">' + _typeId + '</td>'
	                        + '<td data-title="内容" style="color:#0b94ea;max-width:200px;white-space:nowrap; overflow:hidden; text-overflow:ellipsis" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="'+item.content+'">'
	                        + advert_content + '</td>'
	                        + '<td data-title="发布时间">' + DateHandle(item.createTime) + '</td>'
	                        + '<td data-title="状态">' + item_statue + '</td>'
	                        + '<td data-title="操作">' + operation + '</td>'
	                        + '</tr>';
	                    
	                });
	                $('#item_List tbody').html(temp);
	                $("[data-toggle='popover']").popover();
	                //操作按钮hover显示详情
	                $("[data-toggle='tooltip']").tooltip();
	                // 数据分页  #pageNum 为页面隐藏 <input type="hidden" name="pageNum" id="pageNum" value="1"  >
	                // 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
	                page('#pagination', json.pagecount, json.pageindex, json.pagesize, getItemList, '#pageNum');
	                
                }else{
                    $.toast("没有查到数据",3000);
                    $('#item_List tbody').html('');
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

function modifyStatus_remove(id){
    if(!confirm("确定删除吗?")) return;
    $.ajax({
        url: manage_path+'/api/item/delete',
        type: 'POST',
        dataType: 'json',
        data: {
            'id':id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getItemList();
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
function updateItemStatue(id){
	/*if(!confirm("确定修改信息状态为已成交吗?")) return;*/
	$.ajax({
		url: manage_path+'/api/item/update',
		type: 'POST',
		dataType: 'json',
		data: {
			'id':id
		},
		beforeSend:function(){
			$.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
		},
		success:function(data){
			if(data.status == 0){
				$.toast(data.msg,3000);
				getItemList();
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

function DateHandle(objDate)  
{  
            objDate=new Date(objDate); //创建一个日期对象表示当前时间     
            var year=objDate.getFullYear();   //四位数字年     
            var month=objDate.getMonth()+1;   //getMonth()返回的月份是从0开始的，还要加1     
            var date=objDate.getDate();     
            var hours=objDate.getHours();     
            var minutes=objDate.getMinutes();     
            var seconds=objDate.getSeconds();     
            var date = year+"-"+month+"-"+date 
            +" "+hours+":"+minutes+":"+seconds; 
            return date;   
}











