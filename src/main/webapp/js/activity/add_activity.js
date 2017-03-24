
$(function() {
	//防止页面后退
	history.pushState(null, null, document.URL);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.URL);
	});

	//获取当前的活动类型
	var acType=getUrlParam("acType");
	$('#acType').val(acType);
	//获取当前的活动标签
	getLabelList();
	//根据当前的活动类型展示HTML
	getActivityHtml();
	//下一步按钮操作设置
	setNextBtn();
	//须知设置
	isRegular();
	//活动流程上传功能自定义
	ActivityFlow();
	//上传下载包
	fileupload();
	$('#add_active,#add_active2').on('click',function(){
		//if(acType != 1){
		//
		//}
		//活动流程拼接
		ActivityFlowInfo();

		getActiveInfo();
		//非空验证
    	var b = $('#act_add_form').valid();//true false
    	if(b){
    		activityAdd();
    	}else{
    		return false;
    	}		
	})
	
	/**
     *  功能描述：添加验证
     */ 

    $('#act_add_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
            activityNme:{
                required:true
            },
            acDesc:{
            	required:true
            },
            // startTime:{
            // 	required:true
            // },
            // endTime:{
            // 	required:true
            // }
            acUrl:{
				required: true
			}
        },
        messages:{
            activityNme:{
                required: "活动名称不能为空"
            },
            acDesc:{
            	required: "活动简介不能为空"
            },
            acUrl:{
				required: "不能为空，请填写或上传"
			}
            // startTime:{
            //     required: "请选择开始时间"
            // },
            // endTime:{
            //     required: "请选择结束时间"
            // }
        },
        invalidHandler:function(event,validator){
            $('.alert-success').hide();
            $('.alert-error').show();
        },
        highlight:function(element){
            $(element).closest('.help-inline').removeClass('ok');
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        unhighlight:function(element){
            $(element).closest('.control-group').removeClass('error');
        },
        success:function(label){
            label.addClass('valid').addClass('help-inline ok').closest('.control-group').removeClass('error').addClass('success');
        },
        submitHandler:function(form){
            $('.alert-success').show();
            $('.alert-error').hide();
        }
    }); 
	
	/*
		功能描述：添加/修改活动
		请求方式：POST
		请求地址：/api/activity/add
		请求参数
	*/
	function activityAdd(){
		var temp = '';
		$.ajax({
			url: '/api/activity/add',
			type: 'POST',
			dataType: 'json',
			data: $('#act_add_form').serialize(), //通过表单id进行序列化提交
			beforeSend:function(){
	            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
	        },
			success:function(data){
				console.log(data);
	            if(data.status == 0){
	                $.toast('添加成功',3000);
	                setTimeout(function(){
	                    window.location.href = 'all_activity.html?acType='+acType;
	                },3000);
	            }else{
	            	 $.toast(data.msg,3000);
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


	//添加须知textarea
	function isRegular(){
		var $regularType = $('#act_regularbox').find('input[name=isFillNotice]');
	 	$regularType.on('click',function(){
	 		var type = $(this).val();
	 		if(type == 0){
	 			$('#act_regular').fadeOut();
	 			$regularType.prop("checked",false);
	 			$('input[name=isFillNotice]:eq(0)').prop("checked",true);
	 		}else{
	 			$('#act_regular').fadeIn();
	 			$regularType.prop("checked",false);
	 			$('input[name=isFillNotice]:eq(1)').prop("checked",true);
	 		}
	 	})
	}
 	
 	
 	//获取标签列表
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
	        		$.each(list,function(index,item){
	        			temp += '<label class="radio"><div class="radio"><span><input type="radio" value="'+item.id+'"></span></div>'+item.tagName+'</label>';
	        		})	                                               	                                            
	        		$('#tagId').html(temp);
	        		var tagRadio = $('#tagId').find('input');
	        		var tagSpan = $('#tagId span');
 					tagRadio.eq(0).prop("checked",true).parent().addClass('checked');
 					$('#tagIds').val(tagRadio.eq(0).val());
 					tagRadio.on('click',function(){
 						var type = $(this).val();
 						console.log(type);
 						tagSpan.removeClass('checked');
 						$(this).parent().addClass('checked');
 						$('#tagIds').val(type);
 					})
	        	}
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            $.toast('服务器未响应,请稍候重试', 5000);
	        }
	    });
	}


	//设置和处理活动类型
	function getActivityHtml(){
	    if(acType ==0){
	    	$('#tab3').html($('#second_box').html());
	    	$('#activityNavType').text('微信关注');
	    }else if(acType ==3){
	    	$('#tab3').html("");
	    	$('.nav-pills li').eq(2).remove();
	    	$('.nav-pills li:eq(0),.nav-pills li:eq(1)').attr({'class':'span6'});
	    	$('.bar').width('50%');
	    	$('#activityNavType').text('无操作活动');
	    	$('#ac_next2').remove();
	    	$('#act_prev').after('<a href="javascript:;" class="btn green button-submit" id="add_active2">提交<i class="m-icon-swapup m-icon-white"></i></a>');
	    }else if(acType ==2){
	    	$('#tab3').html($('#first_box').html());
	    	$('#support0').get(0).style.display = 'none';
			$('#support1').get(0).style.display = 'none';
			$('#support2').find('span').addClass('checked');
			$('#support2').find('input').prop('checked',true);
	    	
	    	$("#addInvokeTask").click(function(){
	    		var opa = ''
                $('<div class="control-group"></div>').append('<label class="control-label">激活任务：<span class="required">*</span></label><div class="controls">第<input type="text" id="theDays" style="width:100px;" >天,打开应用并停留<select id="theMinute" style="width:100px;" >'+
                        '<option value="">请选择</option>'+
                        '<option value="1">1</option>'+
                        '<option value="2">2</option>'+
                        '<option value="3">3</option>'+
                        '</select>分钟,送<input type="text" id="integration" style="width:100px;" >斤口粮</div>').appendTo($("#taskBox"))
            });
            $('#activityNavType').text('装应用');    	
	    }else if(acType ==1){
	    	$('#tab3').html($('#third_box').html());
	    	$('#activityNavType').text('其它操作活动');
	    }
	}

	//设置btn
	function setNextBtn(){
		$('#act_next').on('click',function(){

			if($('#txtUrl').val() == ""){
				alert('未上传活动封面，请上传');
				return;
			}
			if($('#activityNme').val() == ""){
				alert('未填写活动名称，请填写');
				return;
			}
			if($('#activityDesc').val() == ""){
				alert('未填写活动简介，请填写');
				return;
			}
			$(this).attr('href','#tab2');
			if(acType == 1){
				$('.bar').width('100%');
			}else{
				$('.bar').width('50%');
			}
			$('.nav-pills li').removeClass('active');
			$('.nav-pills li').eq(1).addClass('active');

		})

		$('#ac_next2').on('click',function(){
			var isActiveFlow = $('#box').find('input').val();
			if(!isActiveFlow){
				alert('未上传填写动流程，请上传填写');
				return;
			}
			$(this).attr('href','#tab3');
			$('.bar').width('100%');
			$('.nav-pills li').removeClass('active');
			$('.nav-pills li').eq(2).addClass('active');
		})

		$('#act_prev').on('click',function(){
			$(this).attr('href','#tab1');
			$('.bar').width('33.33%');
			$('.nav-pills li').removeClass('active');
			$('.nav-pills li').eq(0).addClass('active');
		})

		$('.act_prev2').on('click',function(){
			$(this).attr('href','#tab2');
			$('.bar').width('66.66%');
			$('.nav-pills li').removeClass('active');
			$('.nav-pills li').eq(1).addClass('active');
		})

		$('#act_prev3').on('click',function(){
			$(this).attr('href','#tab1');
			$('.bar').width('50%');
			$('.nav-pills li').removeClass('active');
			$('.nav-pills li').eq(0).addClass('active');
		})

		// $('.nav-pills li:eq(0)').on('click',function(){
		// 	$('.nav-pills li').removeClass('active');
		// 	$(this).addClass('active');
		// 	$('.bar').width('33.33%');
		// });
		// $('.nav-pills li:eq(1)').on('click',function(){
		// 	$('.nav-pills li').removeClass('active');
		// 	$(this).addClass('active');
		// 	$('.bar').width('66.66%');
		// });
		// $('.nav-pills li:eq(2)').on('click',function(){
		// 	$('.nav-pills li').removeClass('active');
		// 	$(this).addClass('active');
		// 	$('.bar').width('100%');
		// });
	}

	//活动流程数据
	function ActivityFlowInfo(){
		//活动流程拼接字符串
		var images = [];
        $("#box>span").each(function(index,item){
            //将数据拼成对象放进image
            console.log(item);
            //var $array = $('[name="imageText"]',$(item));
            var image = {
                'md5':$("input",item).data('md5'),
                'desc':$("input",item).val(),
                'detail':$("textarea",item).val()
            };
            //将对象存进images数组
            images.push(image);
        });
        //数组转成JSON
        var json = JSON.stringify(images);
        //活动流程的参数放到隐藏域内 
        $('input[name="operationSteps"]').val(json);
	}



	//活动信息数据
	function getActiveInfo(){
		if(acType ==2){
			//选择下载包时激活任务对象循环
	        var invokeTask = [];
	        $("#taskBox>div").each(function(index,item){
	            var taskArrays = {
	                'theDays':$("input",item).eq(0).val(),
	                'theMinute':$("select",item).val(),
	                'integration':$("input",item).eq(1).val()
	            };
	           invokeTask.push(taskArrays);
	        });

	        var activeInfo = {
	                'name': $('#appName').val(),
	                'package': $('#package').val(),
	                'joinNum': $('#joinNum').val(),
	                'timeBucket':$('#timeBucket').val(),
	                'scores':$('#scores').val(),
	                'invokeTask':invokeTask
	            };
	        var json = JSON.stringify(activeInfo);
	        //活动信息
	        $('input[name="programStyleJson"]').val(json);
	    }
	}	

	//解析地址
	function fullPath(path) {
        if(/^http:\/\//.test(path)) return path;
        return location.host.replace(/^cms\.(.+)/,"http://img.$1/") + path;
	}

	// 活动流程自定义上传
	function ActivityFlow(){
		var $this = $(this);
		var $vt = $($this.data('value-target') || '');
        var $pt = $($this.data('preview-target') || '');
    	$('#box').on('click','a',function(){
            $(this).parent().remove();
        });		
        $("#fileBatch").uploadify({
                'swf'           : '/js/libs/uploadify/uploadify.swf',
                'uploader'		: fullPath('api/upload/file'),
                'fileObjName'   : 'file',
                'buttonText'    :'上传图片',
                'onUploadSuccess' : function(file, data, response) {
                    var json = JSON.parse(data);
                    var url = fullPath(json.data.md5);
                    console.log(url);
                    var remove = '<a class="mydelete">'+'<img src="/images/pic_Remove_btn_icon.png">'+'</a>';
                    var img = $('<img title="活动流程"/>').attr('src',url);
                     $('<span class="imagedesc"></span>')
                     	.append(remove)
                     	.append(img)
                     	.append($('<input type="text" placeholder="标题"/>')
                     	.data('md5',json.data.md5))
                     	.append('<textarea placeholder="简介" rows="3"></textarea>')
                     	.appendTo($("#box"));
            	},
                'onUploadProgress': function (file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
                        console.log(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
                },
                'onUploadError': function (file, errorCode, errorMsg, errorString) {
                        console.log('The file ' + file.name + ' could not be uploaded: ' + errorString);
                }
        });
	}


	// 文件上传
	function fileupload(){
		$("#folderUpload").uploadify({
            'swf'           : '/js/libs/uploadify/uploadify.swf',
            'uploader'      : fullPath('api/upload/file'),
            'fileObjName'   :'file',
            'method'        : 'post',
            'multi'         : false,
            'buttonText'    :'上传文件',
            'onUploadSuccess' : function(file, data, response) {
                var json = JSON.parse(data);
                var input = $('input[name="acUrl"]');
                input.val(json.data.md5);
            },
            'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
                $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
            },
            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        });
	}

});





