$(function() {
	getLabelList();

	$('#add_label_btn').on('click',function(){
		addlabel();
		$('#listModal').modal('hide');
	})
});

	/*
		功能描述：获取标签列表
		请求方式：GET
		请求地址：/api/label/list
		函数名称：getLabelList
	*/

	function getLabelList(){
		var temp = "";
		$.ajax({
			url: '/api/label/list',
			type: 'GET',
			dataType: 'json',
			data: $('#label_form').serialize(), //通过表单id进行序列化提交
			
			beforeSend:function(){
	            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
	        },
			success:function(data){
				if(data.status == 0){
					console.log(data);
					var json = data.data;
					var list = json.result;
					//启用禁用按钮
					var isable = "";
	                $.each(list,function(index,item){
	               		var lab = item.supportPlatform;
	                    switch(lab){
	                        case 0:
	                            lab = '全平台'
	                        break;
	                        case 1:
	                            lab = 'ios'
	                        break;
	                        case 2:
	                            lab = 'android'
	                        break;
	                    }

	                   // 0、装应用 1、微信关注 2、其它操作活动 4、链接活动
	                   var labStatus =  item.tagStatus;//标签状态
	          
	                   switch(labStatus){
	                        case 0:
	                            labStatus = '已禁用';
	                        	isable = ' <a class="btn mini green" data-toggle="tooltip" data-placement="top" title="启用" onclick="modifyState('+item.id+',1)"><i class="icon-ok-circle"></i></a>&nbsp;';
	                        break;
	                        case 1:
	                            labStatus = '已启用';
	                            isable = ' <a class="btn mini grey" data-toggle="tooltip" data-placement="top" title="禁用" onclick="modifyState('+item.id+',0)"><i class="icon-ban-circle"></i></a>&nbsp;';
	                        break;
	                 
	                    }

	                	var acModify = '<a href="javascript:;" class="btn blue mini" data-toggle="tooltip" data-placement="top" title="编辑" data-target="#listModal" data-toggle="modal" onclick="editLabel('+item.id+')"><i class="icon-edit icon-white"></i></a>&nbsp';
	                	var labRemove = '<a class="btn mini red" data-toggle="tooltip" data-placement="top" title="删除" onclick="modifyState('+item.id+',-1)"><i class="icon-remove icon-white"></i></a>';
	                	temp += '<tr>'
	                         +          '<td data-title="标签名称" data-container="body">'+item.tagName+'</td>'
	                         +          '<td data-title="标签状态">'+labStatus+'</td>'
	                         +			'<td data-title="平台属性">'+lab+'</td>'
	                 		 +          '<td data-title="排序"><input name="sortWeight" id="sortWeight_' + item.id + '" type="number" value="' + item.sortIndex + '" style="width: 80px;">&nbsp;<a data-toggle="tooltip" data-placement="top" title="确定" href="javascript:updateRecommend(' + item.id + ')" class="btn mini blue"><i class="icon-ok-sign"></i></a></td>'
                       		 +          '<td data-title="操作">'+acModify+isable+labRemove+'</td>';
	                         +  '</tr>'; 

	                })
            		$('#label_list tbody').html(temp); 
                    //操作按钮hover显示详情
    				$("[data-toggle='tooltip']").tooltip();
    				$("[data-toggle='modal']").tooltip();
                    // 数据分页  #pageNum 为页面隐藏	<input type="hidden" name="pageNum" id="pageNum" value="1"  >
        			// 当没有条件查询时，必须也要加默认的第一页#pageNum  value = 1
    				page('#pagination',json.pagecount,json.pageindex,json.pagesize,getLabelList,'#pageNum'); 
				 }else{
				 	$.toast(data.msg,3000);
	                $('#acRecomment_list tbody').html('');
	                	if($('#pagination').html().length > 0){
	                   	 	$('#pagination').jqPaginator('destory');
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
 	功能描述：更新排序
	请求方式：POST
	请求地址：/api/recommend/rank
	请求参数: id:标签主键ID  sortIndex:标签排序号
	函数名称: updateRecommend
 */
function updateRecommend(id) {
    var sortWeight = $('#sortWeight_' + id).val();
    if(sortWeight == '' || sortWeight.trim().length==0){
        sortWeight = 0;
    }
    $.ajax({
        url: '/api/label/modify',
        type: 'POST',
        dataType: 'json',
        data: {
            'id': id,
            'sortIndex':sortWeight
        },
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            if (data.status == 0) {
                $.toast('修改成功！', 3000);
                //刷新页面list
               	getLabelList();
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



/**
 	功能描述：修改标签状态启用禁用删除
	请求方式：POST
	请求地址：/api/recommend/rank
	请求参数: 标签状态：是（-1、删除 0、禁用 1、启用）
	函数名称: updateRecommend
 */
function modifyState(id,tagStatus) {
    $.ajax({
        url: '/api/label/modify',
        type: 'POST',
        dataType: 'json',
        data: {
            'id': id,
            'tagStatus':tagStatus
        },
        beforeSend: function (data) {
            $.progressBar({message: '<p>正在努力处理数据...</p>', modal: true, canCancel: true});
        },
        success: function (data) {
            if (data.status == 0) {
                $.toast('修改成功！', 3000);
                //刷新页面list
               	getLabelList();
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


	/*
		功能描述：添加/修改标签
		请求方式：POST
		请求地址：/api/label/add
		函数名称：addlabel
	*/
	function addlabel(){
		$.ajax({
	        url:'/api/label/add',
	        type:'POST',
	        dataType:'json',
	        data:$('#add_label_form').serialize(),
	        beforeSend:function(){
	            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
	        },
	        success:function(data){
	            if(data.status == 0){
	            	$.toast('添加成功',5000);
	                getLabelList();
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




	/**
		功能描述：获取标签详细信息
		请求方式：GET
		请求地址：/api/label/detail
		请求参数: id
	 */
	
	function editLabel(id){
	    $.ajax({
	        url:'/api/label/detail',
	        type:'GET',
	        dataType:'json',
	        data:{
	            id:id
	        },
	        beforeSend:function(){
	            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
	        },
	        success:function(data){
	        	console.log(data);
	            if(data.status == 0){
	            	var supportPlatType;
	            	var json = data.data;
	            	clearModal();
	            	$('#myModalLabel').text('标签编辑');
	            	$('#listModal').modal('show');
	            	//赋值
	            	$('#labId').val(json.id)
	            	$('input[name=coverImageMd5]').val(json.imageLogoMd5);
	            	$('#txtUrl').val(json.imageLogoMd5).trigger('change');
	                $('#tagName').val(json.tagName);
	                if(json.supportPlatform == "全平台"){
	                	supportPlatType = 0;
	                }else if(json.supportPlatform == "ios"){
	                	supportPlatType = 1;
	                }else if(json.supportPlatform == "android"){
	                	supportPlatType = 2;
	                }
	                $("input[name='supportPlatform']")
		                .eq(supportPlatType)
		                .attr('checked',"checked")
		                .parent().addClass('checked');
	            }
	        },
	        complete:function(){
	            $.progressBar().close();
	        },
	        error:function(XMLHttpRequest,textStatus,errorThrown){
	            $.toast('服务器未响应，请稍候重试',5000);
	        }
	    });
	}


	//清空modal的内容

	function clearModal(){
		//上传图片置空
		$('input[name=coverImageMd5]').val('');
	  	$('#imgIcon').attr({	
        	'src': ''
    	});
    	$('#tagName').val('');
	   	//终端全平台
		 var aCh = $('input[name=supportPlatform]').parent();
		 aCh.removeClass('checked');
		//aCh.eq(0).addClass('checked');
	  	
    	   
	}	







