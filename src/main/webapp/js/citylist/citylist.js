'use strict'
var xunhuan_flag = "1";
$(function(){
	getUserDetail();
	
	$.fn.typeahead.Constructor.prototype.blur = function() {
	      var that = this;
	      setTimeout(function () { that.hide() }, 250);
	};
	 
   $('#userName').typeahead({
      source: function(query, process) {
    	  var parameter = {username: query};
    	  var data = '';
          $.post(manage_path+'/api/user_manage/usernames', parameter, function (data) {
              process(data.data);
          });
      }
   });
	
    $('#btn_add_citylist').on('click',function(){
        //非空验证
        var b = $('#add_citylist_form').valid();//true false
        if(b){
        	addcitylist();//定制城市
        }else{
            return false;
        }
    });
    
    $(".tree").mouseenter(function(){
        xunhuan_flag="0";
    });
    $(".tree").mouseout(function(){
        xunhuan_flag="0";
    });
    
    $('#add_citylist_form').validate({
        errorElement:'span',
        errorClass:'help-inline',
        focusInvalid:false,
        ignore:'',
        rules:{
        	userName:{
                required: true
            }
        },
        messages:{
        	userName:{
                required:'请输入账户'
            }
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


});

/**
 * 反选
 * @param id
 */	
function invet(id){
	var cityTree = $("#"+id).combotree("getValues");
	var s = new Array();
	var tt = $("#"+id).combotree("tree");
	var childNode = tt.tree("getChildren");
	for(var i= 0;i<childNode.length;i++)
    {
		var cid = childNode[i].id;
		if(cid.indexOf("c") == 0){
			s.push(cid);
		}
			
    }
	//var temp = $.merge(s, cityTree); 
	var new_arr=[];
	for(var i=0;i<s.length;i++) {
	    var items=s[i];
	   // 判断元素是否存在于cityTree中，如果不存在则插入到new_arr的最后
	    if(items != 0){
	    	if($.inArray(items,cityTree) == -1) {
	    	      new_arr.push(items);
	    	}
	    }
	   
	}
	$("#"+id).combotree("setValues",new_arr);
}

function treeChecked(selected, treeMenu) {  
    var roots = $('#' + treeMenu).tree('getRoots');//返回tree的所有根节点数组  
    if (selected.checked) {  
        for ( var i = 0; i < roots.length; i++) {  
            var node = $('#' + treeMenu).tree('find', roots[i].id);//查找节点  
            $('#' + treeMenu).tree('check', node.target);//将得到的节点选中  
        }  
    } else {  
        for ( var i = 0; i < roots.length; i++) {  
            var node = $('#' + treeMenu).tree('find', roots[i].id);  
            $('#' + treeMenu).tree('uncheck', node.target);  
        }  
    }  
}

/**
 *  功能描述：定制城市
 *  请求方式：POST
 *  请求地址：/api/citylist/add
 *  函数名称：addcitylist
 */
function addcitylist(){
	$("#cityTree").combotree("setValues",$("#cityTree").combotree("getText"));
	$("#sendCity").combotree("setValues",$("#sendCity").combotree("getText"));
    $.ajax({
        url:manage_path+'/api/citylist/add',
        type:'POST',
        dataType:'json',
        data:$('#add_citylist_form').serialize(),
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                $.toast('操作成功',5000);
                setTimeout(function(){
                	window.location.href = 'user_list.jsp';
                },1000);
            }else if(data.status == '500002'){
            	$.toast('该账号不存在',5000);
            }else {
                $.toast('操作失败,系统错误',1000);
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

var fold = true;

/**
 * 展开
 * @param id
 */
function isfold(id){
	if(fold == true){
		$('#'+id).combotree('tree').tree('expandAll');
		fold = false;
	}else{
		var tree = $('#'+id).combotree('tree');
		var roots = tree.tree('getRoots');
		tree.tree('collapseAll');
		tree.tree('expand', roots[0].target); 
		fold = true;
	}
	
	
}

function loadTree(row, data) {
    var tree = $('#cityTree').combotree('tree');
    var roots=tree.tree('getRoots');
    tree.tree('collapseAll');
    tree.tree('expand', roots[0].target); 
}

/**
 *  功能描述：获取详情
 *  请求方式：GET
 *  请求地址：/api/user_manage/detail
 *  函数名称：getUserDetail
 *  参数：id:ID
 */

function getUserDetail(){
    var id=getUrlParam("id");
    $.ajax({
        url:manage_path+'/api/user_manage/detail',
        type:'GET',
        cache:false,
        dataType:'json',
        data:{
            id:id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                $('input[name=id]').val(json.id);
                $('input[name=userName]').val(json.userName);
                $('input[name=userNum]').val(json.userNum);
                
                $("#cityTree").combotree("setValues",json.receiveCity);
            	$("#sendCity").combotree("setValues",json.sendCity);
            	if(json.receiveCity != ''){
            		textSelected('cityTree',json.receiveCity);
            	}
            	if(json.sendCity != ''){
            		textSelected('sendCity',json.sendCity);
            	}
            	
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

function textSelected(id,val){
	var s = new Array();
	var arrVal = val.split(",");
	var tree = $("#"+id).combotree('tree');
	var childNode = tree.tree("getChildren");
	for(var j= 0;j<arrVal.length;j++)
    {
		for(var i= 0;i<childNode.length;i++)
	    {
			var text = childNode[i].text;
			
			if(text == arrVal[j]){
				s.push(childNode[i].id);
			}
				
	    }
    }
	$("#"+id).combotree("setValues",s);
	
}



