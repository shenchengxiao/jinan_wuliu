/**
	 *	功能描述：获取省份
	 *  请求方式：GET
	 *  请求地址：/api/channel/province
	 *  函数名称：getProvince
	 */
	 $(function(){
	 	getProvince()
	 })
	function getProvince(){
		$('#city').html('<option value="">全部区域</option>');
		$.ajax({
			url:'/api/channel/province',
			type:'get',
			dataType:'json',
			beforeSend:function(data){
			    $.progressBar({message:'<p>正在努力加载数据...</p>',modal:false,canCancel:true});
			},
			success:function(data){
				if(data.status == 0){
					var json = data.data;
					$.each(json,function(index,item){
						$('<option value="'+item.province_id+'"name ="provinceId">'+item.province_name+'</option>').appendTo($('#city'));
					});
				}else{
					$.toast('没有查到数据！',3000);
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