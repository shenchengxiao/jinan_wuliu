'use strict'

$(function(){
	
	getIpvisitList();
	
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
	
	//绑定所有tbody下的tr,不包括最后一列
    $('#ipvisit_List tbody').on('click','tr td:not(:last-child)',function(){
        //找到本列本行其它列
        var check = $(this).parent().find("input[type='checkbox']");
        var thisID = $(this).parent().find("input[name=idArr]").val();
        if(check){
            var flag = check[0].checked;
            if(flag){
                check[0].checked = false;
                removeInArr(thisID);
                $(this).parent().find("td").css({
                        backgroundColor: ''
                    })
                setIdsInfo();
            }else{
                check[0].checked = true;
                idsArr.push(thisID);
                $(this).parent().find("td").css({
                    backgroundColor: '#bee4ca'
                 })
                setIdsInfo();

            }
        }

    });
    
  //防止冒泡事件
    $('#ipvisit_List tbody').on('click','input[name="chooseTag"]',function(event) {
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


	$('#ipvisit_List').on('change','input[name="chooseTag"]',function(){
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
		delLoginLogs(idsArr);
	});

});

/**
 * 删除多条登录日志
 * @param idsArr
 */
function delLoginLogs(idsArr){
	if(idsArr == null || idsArr.length == 0){
        alert("请先选择要删除的记录");
	}else{
    if(!confirm("确定删除多条吗?")) return;
    $.ajax({
        url: manage_path+'/api/ipvisit/delLoginLogs',
        type: 'POST',
        dataType: 'json',
        data: {
        	'ids':$("#ids").val()
        },
        /*beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
        },*/
        success:function(data){
            if(data.status == 0){
                $.toast(data.msg,3000);
                getIpvisitList();
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
        url: manage_path+'/api/ipvisit/loginLog',
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
	                	var _typeId = item.platformType;//banner状态
	                    /*if(_typeId == 0){
	                    	_typeId = "windows";
	                    }else if(_typeId == 1){
	                    	_typeId = "Ios";
	                    }else if(_typeId == 2){
	                    	_typeId = "Android";
	                    }else{
	                    	_typeId = "";
	                    }*/
//	                    var adStatus = item.enabled;//banner状态
	//                    console.log(item.endTime);
//	                    var Deleted = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="modifyStatus_remove(' + item.bWId + ')"><i class="icon-remove icon-white"></i></a>';
	                    //操作按钮拼接
//	                    operation = upDown + ' <a href="javascript:;" id="btn_edit" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" onclick="getBlackwordDetail(' + item.bWId + ')"><i class="icon-edit icon-white"></i></a> '/* + Deleted*/;
	                    
	                    temp += '<tr>'
	                    	+'<td data-title="">' +'<input type="checkbox" name="chooseTag"><input type="hidden" name="idArr" value="'+item.loginLogId+'"/>'+ '</td>'
	                        + '<td data-title="用户编号">' + item.username + '</td>'
	                        + '<td data-title="用户ip">' + item.ipAddress + '</td>'
	                        + '<td data-title="用户端口">' + item.port + '</td>'
	                        + '<td data-title="登录时间">' + DateHandle(item.loginTime) + '</td>'
	                        + '<td data-title="登录地点">' + item.locateAddress + '</td>'
	                        + '<td data-title="登录类型">' + _typeId + '</td>'
	                        + '<td data-title="硬件信息">' + item.platformItem + '</td>'
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








