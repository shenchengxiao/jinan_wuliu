	$(function() {
		//防止页面后退
		history.pushState(null, null, document.URL);
		window.addEventListener('popstate', function () {
		    history.pushState(null, null, document.URL);
		});
		//获取当前的活动类型
		var acType = getUrlParam("acType");
		var activityId = getUrlParam("id");
		$('#acType').val(acType);
		//获取当前的活动标签
		getLabelList();
		//根据当前的活动类型展示HTML
		getActivityHtml();
		//下一步按钮操作设置sh
		setNextBtn();
		//须知设置
		isRegular();
		//活动流程上传功能自定义
		ActivityFlow();
		//当前活动数据
		getActivityInfo();
		$('#add_active,#add_active2').on('click', function() {
			// if (acType != 1) {
            ActivityFlowInfo();
			// }
			getActiveInfo();
			//非空验证
			var b = $('#act_add_form').valid(); //true false
			if (b) {
				activityAdd();
			} else {
				return false;
			}
		})

		//处理图片地址
		function fullPath(path) {
			if (/^http:\/\//.test(path)) return path;
			return location.host.replace(/^cms\.(.+)/, "http://img.$1/") + path;
		}
		/**
		 *  功能描述：添加验证
		 */

		$('#act_add_form').validate({
			errorElement: 'span',
			errorClass: 'help-inline',
			focusInvalid: false,
			ignore: '',
			rules: {
				activityNme: {
					required: true
				},
				acDesc: {
					required: true
				},
				acUrl:{
					required:true
				}
				// startTime:{
				// 	required:true
				// },
				// endTime:{
				// 	required:true
				// }
			},
			messages: {
				activityNme: {
					required: "活动名称不能为空"
				},
				acDesc: {
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
			invalidHandler: function(event, validator) {
				$('.alert-success').hide();
				$('.alert-error').show();
			},
			highlight: function(element) {
				$(element).closest('.help-inline').removeClass('ok');
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			unhighlight: function(element) {
				$(element).closest('.control-group').removeClass('error');
			},
			success: function(label) {
				label.addClass('valid').addClass('help-inline ok').closest('.control-group').removeClass('error').addClass('success');
			},
			submitHandler: function(form) {
				$('.alert-success').show();
				$('.alert-error').hide();
			}
		});

		/*
			功能描述：获取活动详情
			请求方式：GET
			请求地址：/api/activity/detail
			函数名称：getActivityInfo
		*/

		function getActivityInfo() {
			if (!activityId) {
				return;
			}
			$.ajax({
				url: '/api/activity/detail',
				type: 'GET',
				dataType: 'json',
				data: {
					'activityId': activityId,
					'activityType': acType
				},
				beforeSend: function(data) {
					$.progressBar({
						message: '<p>正在努力加载数据...</p>',
						modal: true,
						canCancel: true
					});
				},
				success: function(data) {
					if (data.status == 0) {
						var json = data.data;
						
						//详情
						setActivityInfo(json);
					}
				},
				complete: function() {
					$.progressBar().close();
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.toast('服务器未响应,请稍候重试', 5000);
				}
			})
		}

	//给修改添加获取到的数据
	function setActivityInfo(json) {
		
		$('#acId').val(json.id);
		$('#acType').val(json.taskType);
		$('#txtUrl').val(json.imageLogoMd5).trigger('change');
		$('#activityNme').val(json.programName);
		$('#acDesc').val(json.programDesc);
		var $regularType = $('#act_regularbox').find('input[name=isFillNotice]');
		if (json.programRuleDesc != null) {
			$('#act_regular').fadeIn();
			$('input[name=isFillNotice]').prop("checked", false);
			$('input[name=isFillNotice]').parent().removeClass('checked');
			$('input[name=isFillNotice]:eq(1)').prop("checked", true);
			$('input[name=isFillNotice]:eq(1)').parent().addClass('checked');
			$('#regular').val(json.programRuleDesc);
		}
		var startTime = timestampFormat(json.startTime);
		var endTime = timestampFormat(json.closeTime);
		$('#startTime').val(startTime);
		$('#endTime').val(endTime);
		//标签类型
		var tagRadio = $('#tagId').find('input');
		var tagSpan = $('#tagId span');
		//设置标签的状态，拿到当前的tagIds
		var tag = json.windataTagInfoDtos;
		var tagListId = tag[0].tagManageInfoId;
		var num;
		//设置当前的tagIds
		$('#tagIds').val(tagListId);
		//通过value值的对比返回当前的下标
		tagRadio.each(function(index) {
			var _this = $(this);
			if (tagListId == _this.val()) {
				num = index;
			}
		});
		tagRadio.eq(num)
			.prop("checked", true)
			.parent().addClass('checked');

		tagRadio.on('click', function() {
			var type = $(this).val();
			console.log(type);
			tagSpan.removeClass('checked');
			$(this).parent().addClass('checked');
			$('#tagIds').val(type);
		})
		var supportPlatType;
		if (json.supportPlatform == "全平台") {
			supportPlatType = 0;
		} else if (json.supportPlatform == "ios") {
			supportPlatType = 1;
		} else if (json.supportPlatform == "android") {
			supportPlatType = 2;
		}
		$("input[name='supportPlatform']")
			.prop('checked', false)
			.parent().removeClass('checked');

		$("input[name='supportPlatform']")
			.eq(supportPlatType)
			.prop('checked', true)
			.parent().addClass('checked');
		var resourceType;
		if (json.platformProgramType == "口粮活动") {
			resourceType = 0;
		} else if (json.platformProgramType == "网络抓取") {
			resourceType = 1;
		}
		$("input[name='resource']")
			.prop('checked', false)
			.parent().removeClass('checked');
		$("input[name='resource']")
			.eq(resourceType)
			.prop('checked', true)
			.parent().addClass('checked');

		if (acType == 2) {
			var temp = "";
			var result = "";
			var activeInfo = json.programSequenceInfo;

			$.each(activeInfo, function(index, item) {
				var imgSrc = fullPath(item.imgSeq);
				temp += '<span class="imagedesc"><a class="mydelete"><img src="/images/pic_Remove_btn_icon.png"></a><img title="活动流程" src="' + imgSrc + '"><input type="text" data-md5="' + item.imgSeq + '" value="' + item.description + '"><textarea rows="3">' + item.contentDesc + '</textarea></span>'
			})
			$("#box").append(temp);
			$('#acUrl').val(json.targetUrl);
			$('#fileupload_preview').text(json.targetUrl);
			//programStyleJson在活动信息不为空的情况下执行
			if (json.programStyleJson != null) {
				var jsonPro = JSON.parse(json.programStyleJson);
				$('#appName').val(jsonPro.name);
				$('#package').val(jsonPro.package);
				$('#joinNum').val(jsonPro.joinNum);
				$('#timeBucket').val(jsonPro.timeBucket);
				$('#scores').val(jsonPro.scores);
				var act_list = jsonPro.invokeTask || [];
				console.log(act_list);
				$.each(act_list, function(index, item) {
					//var thMin = item.theMinute;
					result += '<div class="control-group"><label class="control-label">激活任务：<span class="required">*</span></label><div class="controls">第<input type="text" id="theDays" style="width:100px;" value="' + item.theDays + '">天,打开应用并停留<select id="theMinute" class="theMinute" style="width:100px;">' +
						'<option value="">请选择</option>' +
						'<option value="1">1</option>' +
						'<option value="2">2</option>' +
						'<option value="3">3</option>' +
						'</select>分钟,送<input type="text" id="integration" style="width:100px;" value="' + item.integration + '">斤口粮</div></div>';
					//$('#theMinute').children("option[value='"+item.theMinute+"']").attr("selected","selected");	

				})
				$("#taskBox").append(result);
				$.each(act_list, function(index, item) {
					$('.theMinute').eq(index).val(item.theMinute);
				})
			}
		} else if (acType == 0) {
			var temp = "";
			var activeInfo = json.programStyleJson;
			$.each(json.programSequenceInfo, function(index, item) {
				var imgSrc = fullPath(item.imgSeq);
				temp += '<span class="imagedesc"><a class="mydelete"><img src="/images/pic_Remove_btn_icon.png"></a><img title="活动流程" src="' + imgSrc + '"><input type="text" data-md5="' + item.imgSeq + '" value="' + item.description + '"><textarea rows="3">' + item.contentDesc + '</textarea></span>'
			})
			$("#box").append(temp);
			$('#acUrl').val(json.targetUrl);
		} else if (acType == 1) {
			var temp = "";
			var activeInfo = json.programStyleJson;
			$.each(json.programSequenceInfo, function(index, item) {
				var imgSrc = fullPath(item.imgSeq);
				temp += '<span class="imagedesc"><a class="mydelete"><img src="/images/pic_Remove_btn_icon.png"></a><img title="活动流程" src="' + imgSrc + '"><input type="text" data-md5="' + item.imgSeq + '" value="' + item.description + '"><textarea rows="3" >' + item.contentDesc + '</textarea></span>'
			})
			$("#box").append(temp);
			$('#acUrl').val(json.targetUrl);
		} else if (acType == 3) {
			var temp = "";
			var activeInfo = json.programStyleJson;
			$.each(json.programSequenceInfo, function(index, item) {
				var imgSrc = fullPath(item.imgSeq);
				temp += '<span class="imagedesc"><a class="mydelete"><img src="/images/pic_Remove_btn_icon.png"></a><img title="活动流程" src="' + imgSrc + '"><input type="text" data-md5="' + item.imgSeq + '" value="' + item.description + '"><textarea rows="3">' + item.contentDesc + '</textarea></span>'
			})
			$("#box").append(temp);
		}
	}

		/*
			功能描述：添加/修改活动
			请求方式：POST
			请求地址：/api/activity/add
			请求参数
		*/
		function activityAdd() {
			var temp = '';
			$.ajax({
				url: '/api/activity/add',
				type: 'POST',
				dataType: 'json',
				data: $('#act_add_form').serialize(), //通过表单id进行序列化提交
				beforeSend: function() {
					$.progressBar({
						message: '<p>正在努力加载数据...</p>',
						modal: true,
						canCance: true
					});
				},
				success: function(data) {
					console.log(data);
					if (data.status == 0) {
						$.toast('修改成功', 3000);
						setTimeout(function() {
							window.location.href = 'all_activity.html?acType='+acType;
						}, 3000);
					} else {
						$.toast(data.msg, 3000);
					}
				},
				complete: function() {
					$.progressBar().close();
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.toast('服务器未响应,请稍候重试', 5000);
				}

			})
		}


		//添加须知textarea
		function isRegular() {
			var $regularType = $('#act_regularbox').find('input[name=isFillNotice]');
			$regularType.on('click', function() {
				var type = $(this).val();
				if (type == 0) {
					$('#act_regular').fadeOut();
					$regularType.prop("checked", false);
					$('input[name=isFillNotice]:eq(0)').prop("checked", true);
				} else {
					$('#act_regular').fadeIn();
					$regularType.prop("checked", false);
					$('input[name=isFillNotice]:eq(1)').prop("checked", true);
				}
			})
		}

		//获取标签列表
		function getLabelList() {
			$.ajax({
				url: '/api/label/all_used',
				type: 'GET',
				async: false,
				dataType: 'json',
				success: function(data) {
					if (data.status == 0) {
						console.log(data);
						var list = data.data;
						var temp = "";
						$.each(list, function(index, item) {
							temp += '<label class="radio"><div class="radio"><span><input type="radio" value="' + item.id + '"></span></div>' + item.tagName + '</label>';
						})
						$('#tagId').html(temp);

					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.toast('服务器未响应,请稍候重试', 5000);
				}
			});
		}


		//设置和处理活动类型
		function getActivityHtml() {
			if (acType == 2) {
				$('#tab3').html($('#first_box').html());
					$('#support0').get(0).style.display = 'none';
					$('#support1').get(0).style.display = 'none';
					$('#support2').find('span').addClass('checked');
					$('#support2').find('input').prop('checked',true);

				$("#addInvokeTask").click(function() {
					var opa = ''
					$('<div class="control-group"></div>').append('<label class="control-label">激活任务：<span class="required">*</span></label><div class="controls">第<input type="text" id="theDays" style="width:100px;" >天,打开应用并停留<select id="theMinute" style="width:100px;" >' +
						'<option value="">请选择</option>'+
						'<option value="1">1</option>' +
						'<option value="2">2</option>' +
						'<option value="3">3</option>' +
						'</select>分钟,送<input type="text" id="integration" style="width:100px;" >斤口粮</div>').appendTo($("#taskBox"))
				});
				$('#activityNavType').text('装应用');
			} else if (acType == 0) {
				$('#tab3').html($('#second_box').html());
				$('#activityNavType').text('微信关注');
			} else if (acType == 1) {
				$('#tab3').html($('#third_box').html());
				$('#activityNavType').text('其它操作活动');
			} else if (acType == 3) {
				$('#tab3').html("");
		    	$('.nav-pills li').eq(2).remove();
		    	$('.nav-pills li:eq(0),.nav-pills li:eq(1)').attr({'class':'span6'});
		    	$('.bar').width('50%');
		    	$('#activityNavType').text('无操作活动');
		    	$('#ac_next2').remove();
		    	$('#act_prev').after('<a href="javascript:;" class="btn green button-submit" id="add_active2">提交<i class="m-icon-swapup m-icon-white"></i></a>');
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
			if(acType == 4){
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
		// 	$(this).addClass('active')
		// 	$(this).find('a').attr('href','#tab1');
		// 	$('.bar').width('33.33%');
		// });
		// $('.nav-pills li:eq(1)').on('click',function(){
		// 	$('.nav-pills li').removeClass('active');
		// 	$(this).find('a').addClass('active');
		// 	$('.bar').width('66.66%');
		// });
		// $('.nav-pills li:eq(2)').on('click',function(){
		// 	$('.nav-pills li').removeClass('active');
		// 	$(this).find('a').addClass('active');
		// 	$('.bar').width('100%');
		// });
	}

		//活动流程数据
		function ActivityFlowInfo() {
			//活动流程拼接字符串
			var images = [];
			$("#box>span").each(function(index, item) {
				//将数据拼成对象放进image
				var image = {
					'md5': $("input", item).data('md5'),
					'desc': $("input", item).val(),
					'detail': $("textarea", item).val()
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
		function getActiveInfo() {
			if (acType == 2) {
				//选择下载包时激活任务对象循环
				var invokeTask = [];
				$("#taskBox>div").each(function(index, item) {
					var taskArrays = {
						'theDays': $("input", item).eq(0).val(),
						'theMinute': $("select", item).val(),
						'integration': $("input", item).eq(1).val()
					};
					invokeTask.push(taskArrays);
				});

				var activeInfo = {
					'name': $('#appName').val(),
					'package': $('#package').val(),
					'joinNum': $('#joinNum').val(),
					'timeBucket': $('#timeBucket').val(),
					'scores': $('#scores').val(),
					'invokeTask': invokeTask
				};
				var json = JSON.stringify(activeInfo);
				//活动信息
				$('input[name="programStyleJson"]').val(json);
			}
		}


		// 活动流程自定义上传

		function ActivityFlow() {
			var $this = $(this);
			var $vt = $($this.data('value-target') || '');
			var $pt = $($this.data('preview-target') || '');

			function fullPath(path) {
				if (/^http:\/\//.test(path)) return path;
				return location.host.replace(/^cms\.(.+)/, "http://img.$1/") + path;
			}
			$('#box').on('click', 'a', function() {
				$(this).parent().remove();
			});
			$("#fileBatch").uploadify({
				'swf': '/js/libs/uploadify/uploadify.swf',
				'uploader': fullPath('api/upload/file'),
				'fileObjName': 'file',
				'buttonText': '上传图片',
				'onUploadSuccess': function(file, data, response) {
					var json = JSON.parse(data);
					var url = fullPath(json.data.md5);
					console.log(url);
					var remove = '<a class="mydelete">' + '<img src="/images/pic_Remove_btn_icon.png">' + '</a>';
					var img = $('<img title="活动流程"/>').attr('src', url);
					$('<span class="imagedesc"></span>')
						.append(remove)
						.append(img)
						.append($('<input type="text" placeholder="标题"/>')
							.data('md5', json.data.md5))
						.append('<textarea placeholder="简介" rows="3"></textarea>')
						.appendTo($("#box"));
				},
				'onUploadProgress': function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
					console.log(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
				},
				'onUploadError': function(file, errorCode, errorMsg, errorString) {
					console.log('The file ' + file.name + ' could not be uploaded: ' + errorString);
				}
			});
		}

		// 文件上传
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


	});