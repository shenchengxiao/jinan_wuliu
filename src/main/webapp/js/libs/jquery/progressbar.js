/**
 * author foreverelement
 * description: 进度条插件 v1.0
 *  $("#button01").click(function () {
        /**
         * bae_progressbar({message:"",...})弹出进度条提示
         * message: 进度加载提示内容
         * modal: 是否为模态进度条。true：默认值，带有灰色蒙版。false：没有灰色蒙版
         * canCancel: 是否允许点击进度条取消进度，默认为false。
         * beforeShow: 显示进度条之前要调用函数
         * afterClose: 关闭进度条要调用的函数
         */
    //     // var handle = $.bae_progressbar({message:"<p>正在努力加载数据...</p><p>Loading...</p>",
    //         //是否为模态进度条。true：带有灰色蒙版，蒙版之后内容不可点击。false：没有灰色蒙版，内容可以点击。
    //         modal:true,
    //         //是否允许点击进度条取消进度。
    //         canCancel:true,
    //         beforeShow:function () {/*alert("将显示进度条")*/
    //         },
    //         afterClose:function () {/*alert("已隐藏进度条")*/
    //         }});
    //     //通过调用bae_progressbar对象的close()关闭当前正在显示的进度条。
    //     //setTimeout(function(){handle.close()},1000);
    // });

 // */

$(function(){
	$.progressBar = function(options){
		return new $.progressBar_inner(options);
	};
	$.progressBar_inner = function(options){
		var plugin = this;
		var defaults = {
			message:'',
			position:'center',
			modal:true,
			userAnimation:false,
			canCancel:false,
			beforeShow:undefined,
			afterClose:undefined
		};
		plugin.settings = $.extend({},defaults,options);
		var getSize = function(elem){
			var width = elem.offsetWidth,height = elem.offsetHeight;
			if(!width && !height){
				var style = elem.style;
				var cssShow = 'position:absolute;visibility:hidden;display:block;left:-9999px;top:-9999px;';
				var cssBack = 'position:'+style.position+';visibility:'+style.visibility+';display:'+style.display+';left:'+style.left+';top:'+style.top;
				elem.style.cssText = cssShow;
				width = elem.offsetWidth;
				height = elem.offsetHeight;
				elem.style.cssText = cssBack;
			}
			return {width:parseInt(width),height:parseInt(height)};
		};
		var nodeEle = document.getElementById('progress_box');
		plugin.init = function(){
			if(!nodeEle){
				nodeEle = document.createElement('div');
				nodeEle.setAttribute('id','progress_box');
				document.body.appendChild(nodeEle);
			}
			if(plugin.settings.modal){
				nodeEle.setAttribute('class',"progress_mask");
			}
			var content = '<div class="load"><div class="loader"></div><div class="message" name="msg"></div></div>';
			nodeEle.innerHTML = content;
            nodeEle.style.display = "block";
            nodeEle.addEventListener("touchmove",
            function(e) {
                e.preventDefault()
            },
            false);
            $(nodeEle).find("[name='msg']").html(plugin.settings.message);
            var winBody = nodeEle.children[0];
            if (plugin.settings.canCancel == false) {
                nodeEle.removeEventListener("touchstart", plugin.close, false);
                nodeEle.removeEventListener("click", plugin.close, false)
            } else {
                nodeEle.addEventListener("touchstart", plugin.close, false);
                nodeEle.addEventListener("click", plugin.close, false)
            }
            nodeEle.style.display = "block"
		};
		plugin.close = function() {
            try {
                nodeEle.style.display = "none"
            } catch(e) {}
            if (plugin.settings.afterClose) {
                plugin.settings.afterClose()
            }
        };
        if (plugin.settings.beforeShow) {
            plugin.settings.beforeShow()
        }
        plugin.init();
        return {
            close: function() {
                plugin.close()
            }
        }
	};
});
