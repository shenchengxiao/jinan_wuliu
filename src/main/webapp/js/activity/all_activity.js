
	$(function(){
		var acType=getUrlParam("acType");


		if(acType == 0){
			$('#type').val("0");
			$('.nav').find('li').removeClass('active');
			$('#tab_0').addClass('active');
			getActivityList();
		}else if(acType == 1){
			$('#type').val("1");
			$('.nav').find('li').removeClass('active');
			$('#tab_1').addClass('active');
			getActivityList();
		}else if(acType == 2){
			$('#type').val("2");
			$('.nav').find('li').removeClass('active');
			$('#tab_2').addClass('active');
			getActivityList();
		}else if(acType == 3){
			$('#type').val("3");
			$('.nav').find('li').removeClass('active');
			$('#tab_3').addClass('active');
			getActivityList();
		}else{
			getActivityList();
		}
		//微信关注		
		$('#tab_0').on('click',function(){
			$('#type').val("0");
			$("#pageNum").val(1);
			getActivityList();
		});
		//其他操作活动
		$('#tab_1').on('click',function(){
			$('#type').val("1");
			$("#pageNum").val(1);
			getActivityList();
		});
		//装应用
		$('#tab_2').on('click',function(){
			$('#type').val("2");
			$("#pageNum").val(1);
			getActivityList();
		});
		//无操作活动
		$('#tab_3').on('click',function(){
			$('#type').val("3");
			$("#pageNum").val(1);
			getActivityList();
		});
		
		$('#btn_search').on('click',function(){
	    	$("#pageNum").val(1);
	        getActivityList();
	    });
	    $(document).keydown(function(event){
	        if(event.keyCode==13){
	            $("#btn_search").click();
	        }
	    });
	    selectVal();
	});
	/**
		第一步：获取活动列表 封装一个函数

	 *	功能描述：获取活动列表信息
	 *  请求方式：GET
	 *  请求地址：/api/activity/list
	 *  函数名称：getActivityList
	 */

	var selectNum;//类型val();
	function getActivityList(){
		var temp = '';
		$.ajax({
			url: '/api/activity/list',
			type: 'GET',
			dataType: 'json',
			data: $('#act_list_form').serialize(), //通过表单id进行序列化提交
			//{
				// 'tagId':$('#tagId').val(),
				// 'type':type,
				// 'pageNum':$('#pageNum').val(),
				// 'pagesize':$('#pagesize').val(),
			//},
			beforeSend:function(){
	            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
	        },
			success:function(data){
				if(data.status == 0){
					getLabelList();
					$('#tagId').val(selectNum);
					console.log(data);
					var json = data.data;
					var list = json.result;
					var acTotol = json.total;
					//操作按钮
					var operation = "";
					//上架下架
	                var upDown = "";
	                //推荐 取消推荐
	                var acRecommendInfoHtm = "";
	                //点击数
	                var clickNum = '';
	                $('#pagesize').val(json.pagesize);
	                $('#activity_total').text(acTotol);

	                $.each(list,function(index,item){
	                	/**
						* 功能描述：统计活动点击人数
						* 请求方式：GET
					    * 请求地址：/api/activity/click_num
					    * 请求参数: activity_id:id;
						**/
	                	$.ajax({
					        url: '/api/activity/click_num',
					        type: 'GET',
					        dataType: 'json',
					        async:false,
					        data: {
					            'activity_id':item.id,
					        },
					        beforeSend: function (data) {
					            $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
					        },
					        success:function (data) {
					        	//console.log(data);
					        	if(data.status == 0){
					        		clickNum = data.data.clickNum;
					      			//console.log(data.data.clickNum);
					      		}
					        },
					        complete: function () {
					            $.progressBar().close();
					        },
					        error: function (XMLHttpRequest, textStatus, errorThrown) {
					            $.toast('服务器未响应,请稍候重试', 5000);
					        }
					    });


	                	var acType =  item.activityType;//活动类型
	               		var acStatus = item.acStatus;//活动状态
	               		
	               		if(acStatus == 1){
	               			acStatus = "进行中";
	               		}else if(acStatus == 2){
	               			acStatus = "已结束";
	               		}else if(acStatus == 3){
	               			acStatus = "未开始";
	               		}

	               		var act = item.supportPlatform;
	                    switch(act){
	                        case 0:
	                            act = '全平台'
	                        break;
	                        case 1:
	                            act = 'IOS'
	                        break;
	                        case 2:
	                            act = '安卓'
	                        break;
	                    }

	                    var acUpDown =  item.isDownOrUp;
	                	//isDownOrUp状态：1下架 0上架
	                	                	
	                	if(acUpDown == 0){
	                		upDown = ' <a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="下架" onclick="modifyState('+item.id+',1,'+acType+',0)"><i class="icon-ban-circle"></i></a>&nbsp;';
	                	}else{
	                		upDown = ' <a class="btn mini green" data-toggle="tooltip" data-placement="top" title="上架" onclick="modifyState('+item.id+',0,'+acType+',0)"><i class="icon-ok-circle"></i></a>&nbsp;';
	                	}
	                	var acRecomment =  item.isRecomment;
	                	
	                	//isDownOrUp状态：0、已推荐 1、推荐	     
	               		if (acRecomment == 0) {//已推荐
                        	acRecommendInfoHtml = '<a class="btn mini purple" data-toggle="tooltip" data-placement="top" title="推荐" onclick="modifyState('+item.id+',1,'+acType+',1)"><i class="icon-thumbs-up"></i></a>&nbsp';
                    	} else if(acRecomment == 1) {
                        	acRecommendInfoHtml = '<a class="btn mini purple" data-toggle="tooltip" data-placement="top" title="取消推荐" onclick="modifyState('+item.id+',0,'+acType+',1)"><i class="icon-thumbs-down"></i></a>&nbsp';
                    	}
                    	var acModify = '<a href="/html/activity/edit_activity.html?id=' + item.id + '&acType='+item.activityType+ '" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑"><i class="icon-edit icon-white"></i></a>&nbsp';
	                	var acRemove = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="delmodifyState('+item.id+',3,'+acType+',1)"><i class="icon-remove icon-white"></i></a>';
	                	operation = acModify+upDown+acRecommendInfoHtml+acRemove;
	                	temp += '<tr>'
	                         +          '<td data-title="活动名称" data-container="body">'+item.acName+'</td>'
	                         +          '<td data-title="活动状态">'+acStatus+'</td>'
	                         +          '<td data-title="点击人数">'+clickNum+'</td>'
	                         +          '<td data-title="参加人数">'+item.acJoinNum+'</td>'
	                         +          '<td data-title="实际人数">'+item.acRealNum+'</td>'
	                         +			'<td data-title="所属平台">'+act+'</td>'
	                         +			'<td data-title="活动标签">'+item.tagName+'</td>'
	                         +          '<td data-title="排序"><input name="sort_weight" id="sortWeight_' + item.id + '" type="number" value="' + item.sort_weight + '" style="width: 100px;">&nbsp;<a data-toggle="tooltip" data-placement="top" title="确定" href="javascript:updateActivityRecommend(' + item.id + ')" class="btn mini blue"><i class="icon-ok-sign"></i></a></td>'
	                         +          '<td data-title="操作">'+operation+'</td>'
	                         +  '</tr>'; 

	                })
            		$('#activity_list tbody').html(temp); 
            		//发布新的活动
            		var acTypeNow = $('#type').val();//当前的类型
            		$('#add_activebtn').on('click',function(){
            			$(this).attr('href','add_activity.html?acType='+acTypeNow);
            		})
                    //操作按钮hover显示详情
    				$("[data-toggle='tooltip']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏	<input type="hidden" name="pageNum" id="pageNum" value="1"  >
        			// 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
    				page('#pagination',json.pagecount,json.pageindex,json.pagesize,getActivityList,'#pageNum'); 
				 }else{
				 	$.toast(data.msg,3000);
	                $('#activity_list tbody').html('');
	                	if($('#pagination').html().length > 0){
	                   	 	$('#pagination').jqPaginator('destroy');
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


	/**
	 *	功能描述：更新活动状态（上架下架、是否推荐、是否删除）
	 *  请求方式：POST
	 *  请求地址：/api/activity/modify
	 *  函数名称：modifyState
	 *  函数参数：activityId：id; 
	 			status:状态 0、上架或不推荐 1、下架或推荐; 
	 			activityOperatorType: 操作类型 0、上架下架 1、是否推荐 3、是否删除;
	 			acType 活动类型 0微信 1其它 2装应用 3无需操作 4链接
	 */

	 function modifyState(activityId,status,acType,activityOperatorType){
	 	$.ajax({
	        url: '/api/activity/modify',
	        type: 'POST',
	        dataType: 'json',
	        data: {
	            'activityId':activityId,
	            'status':status,
	            'activityOperatorType':activityOperatorType,
	            'acType':acType
	        },
	        beforeSend: function (data) {
	            $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
	        },
	        success: function (data) {
	            if (data.status == 0) {
	                $.toast('操作成功！', 3000);
	                getActivityList();
	            } else {
	                $.toast('操作失败！', 3000);
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
	 *	功能描述：更新活动状态（上架下架、是否推荐、是否删除）
	 *  请求方式：POST
	 *  请求地址：/api/activity/modify
	 *  函数名称：modifyState
	 *  函数参数：activityId：id; 
	 			status:状态 0、上架或不推荐 1、下架或推荐; 
	 			activityOperatorType: 操作类型 0、上架下架 1、是否推荐 3、是否删除;
	 			acType 活动类型 0微信 1其它 2装应用 3无需操作 4链接
	 */

	 function delmodifyState(activityId,activityOperatorType,acType,beDelete){
	 	if(!confirm("确定删除吗?")) return;
	 	$.ajax({
	        url: '/api/activity/modify',
	        type: 'POST',
	        dataType: 'json',
	        data: {
	            'activityId':activityId,
	            'activityOperatorType':activityOperatorType,
	            'beDelete':beDelete,
	            'acType':acType
	        },
	        beforeSend: function (data) {
	            $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
	        },
	        success: function (data) {
	            if (data.status == 0) {
	                $.toast('删除成功！', 3000);
	                getActivityList();
	            } else {
	                $.toast('删除失败！', 3000);
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
		功能描述：获取标签列表
		请求方式：GET
		请求地址：/api/label/all_used
		函数名称：getLabelList
		请求参数   无
	**/

	function getLabelList(){
		$.ajax({
	        url: '/api/label/all_used',
	        type: 'GET',
	        async:false,
	        dataType: 'json',
	        success:function (data) {
	        	if (data.status == 0) {
	        		console.log(data);
	        		var list = data.data;
	        		var temp = "";
	        		var operate = "";
	        		var temp_first = '<option value="">标签类型</option>';
	        		$.each(list,function(index,item){
	        			temp += '<option value="'+item.id+'">'+item.tagName+'</option>';
	        			
	        		})
	        		operate = temp_first+temp;
	        		$('#tagId').html(operate);
	        	}
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            $.toast('服务器未响应,请稍候重试', 5000);
	        }
	    });
	}


	/*
		监听select的value值
	*/
	
	
	function selectVal(){
		$("#tagId").change(function() {
			selectNum = $("#tagId").val(); 
			//alert(selectNum);
		});
	}


/**
 *  功能描述：更新活动排序
 *  请求方式：post
 *  请求地址：/api/activity/sort_weight
 *  函数名称：updateActivityRecommend
 */
function updateActivityRecommend(id) {
    var sortWeight = $('#sortWeight_' + id).val();
    if(sortWeight == '' || sortWeight.trim().length==0){
        sortWeight = 0;
    }
    $.ajax({
        url: '/api/activity/sort_weight',
        type: 'POST',
        dataType: 'json',
        data: {
            activity_id: id,
            sort_weight:sortWeight
        },
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            if (data.status == 0) {
                $.toast('修改成功！', 3000);
                getActivityList();
            } else {
                $.toast('修改失败！', 3000);
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
