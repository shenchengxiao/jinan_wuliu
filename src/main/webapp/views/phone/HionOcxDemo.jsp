<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>话机管理</title>
	<%@ include file="/common/taglibs.jsp"%>
    <jsp:include page="/common/common.jsp"></jsp:include>
	<script type="text/javascript" src="../../js/hion/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../../js/hion/css.css">
	<script type="text/javascript" src="../../js/hion/call.js"></script>
	<style type="text/css">
		
		#left{width:27%; height:550px; float:left}
		#messages{width:72%; height:550px; float:right}
		.btnDial{width:163px; height:39px; background:url('../../images/hions/hion/Uc3_p1_24.jpg');} <!--拨号-->
		
	</style>
</head>
<!---->
<object style="display: none;" id="UsbPhone"  border=1 width=400 height=500 codebase="/WEB-INF/lib/UsbPhoneV3.1.ocx"
	classid="CLSID:4CF8112F-57D5-474B-A4BC-70F72244BCD9">
</object>
<script language="javascript">
var myocx = document.getElementById("UsbPhone");
var idev;
/*background:#FF0000;  background:#00FF00;   no-repeat left top  
 * addEventListener:监听Dom元素的事件 
 *   
 *  target：监听对象 
 *  type：监听函数类型，如click,mouseover 
 *  func：监听函数 
 */
function addEventHandler(target,type,func){  
    if(target.addEventListener){  
        //监听IE9，谷歌和火狐  
        target.addEventListener(type, func, false);  
    }else if(target.attachEvent){  
        target.attachEvent("on" + type, func);  
    }else{  
        target["on" + type] = func;
    }
}
/* 
 * removeEventHandler:移除Dom元素的事件 
 *   
 *  target：监听对象 
 *  type：监听函数类型，如click,mouseover 
 *  func：监听函数 
 */
function removeEventHandler(target, type, func) {  
    if (target.removeEventListener){  
        //监听IE9，谷歌和火狐  
        target.removeEventListener(type, func, false);  
    } else if (target.detachEvent){  
        target.detachEvent("on" + type, func);  
    }else {  
        delete target["on" + type];  
    }  
}
/**
var OnIncomingPhone = function(lAudioDeviceID, IncomingNum){
	alert("Incoming Phone Number : "+IncomingNum);
}
*/
var OnDeviceDetect = function(bState, lAudioDeviceID){
	if(!bState)
	{
		addMessages("设备拔出!");
	}
	else
	{
		addMessages("设备插入!");
	}

}


function Init()
{
    idev = myocx.Init();
 	if(idev < 0){
	 	
 		addMessages("Init ERR!");
 	}else{
	 	
 		addMessages("Init OK!");
 	}
}


window.onload = function(){ 
	//addEventHandler(myocx,"IncomingPhone",OnIncomingPhone);
	addEventHandler(myocx,"DeviceDetect",OnDeviceDetect);
	Init();
	//buildBtns();
    registerUsbPhoneEvents();
	

    
}
//class="btnDial"  disabled="disabled"\]

function ceshi(){
	var del = document.getElementById("del");
	del.style.background = "url('../../images/hions/hion/Uc3_p1_21.jpg')";
	
	
}

function voip()
{
	document.getElementById("btnVOIPIO").style.background="url(../../images/hions/hion/Uc3_p1_1.jpg)";
}


function getUrl()
{
     var td  = document.getElementById('btnVOIPIO');
     var url = "";
     if( document.all )
     {
         url=td.currentStyle.backgroundImage;//IE
		// alert(url);
     }
     else
     {          
         url= document.defaultView.getComputedStyle(td,null).getPropertyValue('background-image');//FF
     }
   alert(url);
   var n = 20;
   alert(url.length);
   alert(url.substring(url.length-n,url.length));
   var dizhi = url.substring(url.length-n,url.length);
   alert(dizhi.substring(0,18));
}



</script>


<body class="page-header-fixed">
<!-- 头部 begin -->
<jsp:include page="/include/header.jsp"></jsp:include>
<!-- 头部 end -->
<!-- 页面主体 begin -->
<div class="page-container row-fluid">
    <!-- 左侧菜单 begin-->
    <jsp:include page="/include/left_sidebar.jsp"></jsp:include>
    <!-- 左侧菜单 end-->
    <!-- 内容区域 begin -->
    <div class="page-content">
        <div class="container-fluid ">
            <div class="row-fluid">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="../index.jsp">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">话机管理</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-reorder"></i> 话机管理
                            </div>

                        </div>
						<form name="CheckPrinter">
							<div id="left" style="border: black 1px solid;" >
							<!--
							<input name="" type="button"  style="width:163px; height:39px; background:url('../../images/hion/Uc3_p1_24.jpg')"  onclick="alert(11);"  /> 
							-->
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基本</p>
								<input id="callee" type="text" style="width:350px; height:39px;" /><br/>
								<input type="button" id="btnCallOut" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_24.jpg')" onClick="clickCallOut()" />
								<input type="button" id="btnSetIdle" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_27.jpg')" onClick="clickOffHand()" disabled="disabled" /><br/>
								<input type="button" id="btnTalkRecIO" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_5.jpg')" onclick="clickTalkRecIO()" disabled="disabled" />
								<input type="button" id="btnStartRecording" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_14.jpg')" onclick="clickRecordFile()"  /><br/>
								<input type="button" id="btnCloseSound" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_15.jpg')" onclick="clickCloseSound()" disabled="disabled"  />
								<input type="button" id="btnKeep" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_12.jpg')" onclick="clickHold()" disabled="disabled"  /><br/>
								<input type="button" id="btnLeave" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_10.jpg')" onclick="clickLiuyanRecIO()" disabled="disabled" />
								<input type="button" id="btnVOIPIO" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_6.jpg')" onclick="clickVOIPIO()" /><br/>
								
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;转拨</p>
								<input type="button" id="btnFlashTime" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_19.jpg')" onclick="clickFlashTime()" />
									<select name="selectFlashTime" id="selectFlashTime" style="width:100px; height:39px;" >   
											<option value="0">100ms</option>   
											<option value="1">180ms</option>   
											<option value="2">300ms</option>   
											<option value="3">600ms</option>   
											<option value="4">1000ms</option>   
									</select>   
									<input id="zhuanbo" type="text" style="width:70px; height:39px;" />	<br/>
								
								<input type="button" id="btnBroken" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_17.jpg')" onclick="clickFlash()" />
								<input type="button" id="btnZhuanBo" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_26.jpg')" onclick="clickZhuanBo()" /><br/>
								
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置</p>
								<input type="button" id="btnExitCode" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_25.jpg')" onclick="clickOutcode()"  />
								<input id="outcode" type="text" style="width:173px; height:39px;" />	<br/>
								<input type="button" id="btnDialTone" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_22.jpg')" onclick="clickDialTone()"  />
								<input type="button" id="btnBill" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_9.jpg')" onclick="clickRingSet()"  /><br/>
								<input type="button" id="btnAnswer" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_4.jpg')" onclick="clickAutoAnswerOpen()" />
								<input type="button" id="btnPhoneState" style="width:173px; height:39px;background:url('../../images/hion/Uc3_p1_18.jpg')" onClick="clickPhoneState()" /><br/>
							</div>
							<div id="messages" style="padding: 0px; margin: 0px; border: black 1px solid;overflow: auto;"></div>
						</form>
						
						</div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 页面主体 end -->
<!-- 页尾 begin -->
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>
