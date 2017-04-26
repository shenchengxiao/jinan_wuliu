/**
 * 刷新呼出按钮状态
 * 
 * @param state
 *            状态码
 */
function refreshBtnCallOut(state)
{
    if ((state >= 200 && state < 300) || state == 105 || state == 106
            || state == 304 || state == 310 || (state >= 400 && state < 500))
    {
        $btnCallOut.removeAttr("disabled");
    }
    else
    {
        $btnCallOut.attr("disabled", "disabled");
    }
}

/**
 * 刷新按钮状态
 * @param state 状态码
 */
function refreshBtns(state)
{
    refreshBtnSignIn(state);
    refreshBtnSignOut(state);
    refreshBtnCallOut(state);
    refreshBtnSetIdle(state);
    refreshBtnSetBusy(state);
	refreshBtnHangup(state);
}

/**
 * 构造按钮对象
 */
function buildBtns()
{
    var $btnSignIn = $("#btnSignIn");
    var $btnSignOut = $("#btnSignOut");
    var $btnCallOut = $("#btnCallOut");// 呼出
    var $btnSetIdle = $("#btnSetIdle");
    var $btnSetBusy = $("#btnSetBusy");
	var $btnHangup  = $("#btnHangup");
	var $btnPhoneState = $("#btnPhoneState"); // 获取话机当前状态            
	var $btnFlashTime = $("#btnFlashTime"); // 设置闪断时间
	var $btnBroken = $("#btnBroken"); // 闪断
	var $btnStartRecording = $("#btnStartRecording"); //开始录音
	var $btnEndRecording = $("#btnEndRecording"); // 结束录音
}

/**
 * 添加消息内容
 * 
 * @param msg
 *            消息内容
 */
function addMessages(msg)
{
    $("#messages").prepend(
            $("<p></p").text(new Date().toLocaleString() + " " + msg));
}






	

/**
 * 点击呼出按钮
 
function clickCallOut()
{
	var UsbPhone = document.getElementById("UsbPhone");
    var callee = $.trim($("#callee").val());
	//alert("字符串"+callee);
    if (callee.length == 0)
    {
        alert("未输入呼出号码");
        return;
    }
    
    //callee = parseInt(callee, 8);
	//alert("转换成整数"+callee);
    if (isNaN(callee))
    {
        alert("呼出号码应为数字");
        return;
    }
    // 呼出
	var lAudioDeviceID = 6001;
	UsbPhone.Init();
	if (UsbPhone.Dial(lAudioDeviceID, callee) != 0)
    {
        addMessages("呼出失败");
    }else{
		 $btnCallOut.attr("disabled", "disabled");
		 $btnHangup.removeAttr("disabled");
		 $btnStartRecording.removeAttr("disabled");
		 addMessages("呼出成功");
	}
}*/

/**
*点击挂机按钮
*/
function clickHangup(){
	var lAudioDeviceID = 6001;
   if(UsbPhone.OnHand(lAudioDeviceID) != 0){
	    $btnHangup.attr("disabled", "disabled");
		$btnCallOut.removeAttr("disabled");
		addMessages("挂机成功=>" + "坐席号:" + lAudioDeviceID);
   }else{
	   addMessages("挂机失败=>" + "坐席号:" + lAudioDeviceID);
   }       

}

/**
 * 点击获取话机当前状态
 */
 function clickPhoneState(){
	var lAudioDeviceID = 6001;
	// GetPhoneState(long lAudioDeviceID);
	 if(UsbPhone.GetPhoneState(lAudioDeviceID) == 0){
		 addMessages("挂机状态");
	 }else if(UsbPhone.GetPhoneState(lAudioDeviceID) == 1){
		 addMessages("摘机状态");
	 }else{
		 addMessages("失败");
	 }
 }
 
 /**
 * 点击设置闪断时间      SetFlashTime  调用不成功 
 */
 function clickFlashTime(){
	var lAudioDeviceID = 6001;
	var options = $("#selectFlashTime option:selected");  //获取选中的项
	var nFlashTime = options.val();
	//alert(options.val());   //拿到选中项的值
	//alert(options.text());   //拿到选中项的文本
	//alert(nFlashTime);
	 //SetFlashTime(long lAudioDeviceID, short nFlashTime)
	 if(UsbPhone.SetFlashTime(lAudioDeviceID,nFlashTime) == 1){
		addMessages("闪断时间设置为" + nFlashTime); 
	 }else{
		 addMessages("闪断时间设置失败");
	 }
 }
 
 /**
 * 点击设置闪断时间                       没有说明方法调用取值范围 
6.Flash方法
函数原型：short Flash(long lAudioDeviceID, short nFlashTime);
功能说明：控制指定的终端设备进行拍叉操作并准备下一次通话。
参数说明：lAudioDeviceID->终端设备标识；
          nFlashTime->Flash操作的时间长度,取值为0--100ms,1--180ms,2--300ms,3--600ms,4--1000ms之间。
返回值： 成功则返回1, 否则返回0；
 */
function clickFlash(){
	if(UsbPhone.Flash(lAudioDeviceID,nFlashTime)){
		
	}
}


/**

  特色铃声                                不支持
7.RingSet方法
函数原型：short RingSet(long lAudioDeviceID, short nRing);
功能说明：控制指定终端设备进行特色铃声的设定，检测到设备连接时要使用该方法；
参数说明：lAudioDeviceID->坐席号；
          nRing->1:打开特色铃声；
                 0:关闭特色铃声；
返回值：成功则返回1，否则返回0；
*/
function clickRingSet(){
	var nRing = 1;
	var lAudioDeviceID = 6001;
	if(UsbPhone.RingSet(6001,1) == 1){
		addMessages("设置特色铃声成功");
	}
}

/**
    打开通话录音IO口

9.SetTalkRecIO方法
函数原型：short SetTalkRecIO(long lAudioDeviceID, short nStatus);
功能说明：控制指定终端设备进行通话录音的io口设定，仅在摘机状态改变，方有用；
参数说明：lAudioDeviceID->坐席号；
          nStatus->1:打开通话录音io口；
                   0:关闭通话录音io口；
返回值：成功则返回1，否则返回0；
*/
function clickTalkRecIO(){
	var UsbPhone = document.getElementById("UsbPhone");
	var lAudioDeviceID = 6001;
	var nStatus = 1;
	if(UsbPhone.SetTalkRecIO(lAudioDeviceID,nStatus) == 1){
		addMessages("打开通话录音io口");
	}
}

/**
      开始录音

19.StartRecordFile方法
函数原型：short StartRecordFile(long lAudioDeviceID, LPCTSTR strFileName, short nType);
功能说明：在指定的终端设备上开始录音操作；
参数说明：lAudioDeviceID->坐席号；
          strFileName->录音文件名，可以是文件名,也可以是完整的路径,如:"sound.wav"或"C:\\record\\sound.wav"。
                       当没有指定路径时,录音文件保存在当前目录下；
          nType->录音类型：0:本地录音；1:通话录音；2:留言录音；
返回值：成功则返回1，否则返回0；
*/
function clickRecordFile(){
	var lAudioDeviceID = 6001;
	var strFileName = "E:\\record\\sound.wav";
	var nType = 0;
	if(UsbPhone.StartRecordFile(lAudioDeviceID, strFileName,nType) != 0){
		$btnEndRecording.removeAttr("disabled");
		addMessages("开始录音");
	}
	
}

/**
      结束录音

20.StopRecord方法
函数原型：short StopRecord(long lAudioDeviceID);
功能说明：在指定的终端设备上停止录音；
参数说明：lAudioDeviceID->坐席号；
返回值：成功则返回1，否则返回0；
*/
function clickStopRecord(){
	var lAudioDeviceID = 6001;
	if(UsbPhone.StopRecord(lAudioDeviceID) != 0){
		addMessages("结束录音");
	}
	
}

/**
    设置出局码
18.SetOutcode方法
函数原型：short SetOutcode(long lAudioDeviceID, LPCTSTR outcode);
功能说明：在指定的终端设备上设置出局码，检测到设备连接时要使用该方法；
参数说明：lAudioDeviceID->坐席号；
          outcode->出局码，最多 3位，没有出局码就是空字符串；
返回值：成功则返回1，否则返回0；
*/
function clickOutcode(){
	
	var lAudioDeviceID = 6001;
	var outcode = $("#outcode").val();  //获取选中的项
	//alert(outcode);
	if(UsbPhone.SetOutcode(lAudioDeviceID,outcode) != 0){
		addMessages("设置出局码成功");
	}
}


/**
   设置自动接听
16.SetAutoAnswer方法 
函数原型：short SetAutoAnswer(long lAudioDeviceID, short bOn);
功能说明：在指定的终端设备上设置自动接听，检测到设备连接时要使用该方法；
参数说明：lAudioDeviceID->坐席号；
          bOn->1:开启自动接听；
               0:关闭自动接听；
返回值：成功则返回1，否则返回0；
*/
function clickAutoAnswerOpen(){
	var lAudioDeviceID = 6001;
	var bOn = 1;
	UsbPhone.Init();
	if(UsbPhone.SetAutoAnswer(lAudioDeviceID, bOn) != 0){
		addMessages("开启自动接听");
	}
}

/**
      打开留言放音IO口
10.SetLiuyanRecIO方法
函数原型：short SetLiuyanRecIO(long lAudioDeviceID, short nStatus);
功能说明：控制指定终端设备进行留言放音、录音的io口设定，仅在摘机状态改变，方有用；
参数说明：lAudioDeviceID->坐席号；
          nStatus->1:打开留言放音、录音io口；
                   0:关闭留言放音、录音io口；
返回值：成功则返回1，否则返回0；
*/
function clickLiuyanRecIO(){
	var lAudioDeviceID = 6001;
	var nStatus = 1;
	UsbPhone.Init();
	if(UsbPhone.SetLiuyanRecIO(lAudioDeviceID, nStatus) != 0){
		addMessages("打开留言放音、录音io口");
	}
}

/**
       闭音
13.Mute方法
函数原型：short Mute(long lAudioDeviceID, short bOn);
功能说明：在指定的终端设备上进行闭音操作；
参数说明：lAudioDeviceID->坐席号；
          bOn->1:开启闭音；
               0:关闭闭音；
返回值：成功则返回1，否则返回0；
*/
function clickMute(){
	var lAudioDeviceID = 6001;
	var bOn = 1;
	UsbPhone.Init();
	if(UsbPhone.Mute(lAudioDeviceID, bOn) != 0){
		addMessages("开启闭音");
	}
}

/**
        保留

14.Hold方法
函数原型：short Hold(long lAudioDeviceID, short bOn);
功能说明：在指定的终端设备上进行保留操作；
参数说明：lAudioDeviceID->坐席号；
          bOn->1:开启保留；
               0:关闭保留；
返回值：成功则返回1，否则返回0；
*/
function clickHold(){
	var lAudioDeviceID = 6001;
	var bOn = 1;
	UsbPhone.Init();
	if(UsbPhone.Hold(lAudioDeviceID,bOn) != 0 ){
		addMessages("开启保留");
	}
}

/**
        设置拨号音
15.SetDialTone方法
函数原型：short SetDialTone(long lAudioDeviceID, short bOn);
功能说明：在指定的终端设备上设置拨号音，检测到设备连接时要使用该方法；
参数说明：lAudioDeviceID->坐席号；
          bOn->1:有拨号音；
               0:关闭拨号音；
返回值：成功则返回1，否则返回0；
*/
function  clickDialTone(){
	var lAudioDeviceID = 6001;
	var bOn = 1;
	UsbPhone.Init();
	if(UsbPhone.SetDialTone(lAudioDeviceID, bOn) != 0 ){
		addMessages("有拨号音");
	}
}

/**
               转拨                       这里存在问题.   转拨 是先调用转拨的接口还是 拨出的接口
21.ZhuanBo方法
函数原型：short ZhuanBo(long lAudioDeviceID, short nZhuanboTime);
功能说明：控制指定的终端设备进行拍叉操作。
参数说明：lAudioDeviceID->终端设备标识；
          nZhuanboTime->转拨操作的时间长度,取值为0--100ms,1--180ms,2--300ms,3--600ms,4--1000ms之间。
返回值： 成功则返回1, 否则返回0；
*/
function clickZhuanBo(){
	var lAudioDeviceID = 6001;
	var nZhuanboTime = 180ms;
	UsbPhone.Init();
	if(UsbPhone.ZhuanBo(lAudioDeviceID, nZhuanboTime) != 0 ){
		if (UsbPhone.Dial(lAudioDeviceID, callee) != 0){
			addMessages("呼出失败");
		}else{
			$btnCallOut.attr("disabled", "disabled");
			$btnHangup.removeAttr("disabled");
			$btnStartRecording.removeAttr("disabled");
			addMessages("呼出成功");
		}
	}
	
}


/**
        静音
7.OnMuteKey事件
OnMuteKey(long lAudioDeviceID, short bStatus);
功能说明：指定的终端设备按“静音”键；
参数说明：lAudioDeviceID->坐席号；
          bStatus为1->“静音”键开启，
          bStatus为0->“静音”键关闭；
*/
//function OnMuteKey(lAudioDeviceID, bStatus){
//	addMessages("静音");
//}
function OnMuteKey(){
	var lAudioDeviceID = 6001;
	var bStatus = 1;
	UsbPhone.Init();
	UsbPhone.OnMuteKey(lAudioDeviceID, bStatus);
	addMessages("静音开启");	
}



 /**
 
 1.OnIncomingPhone事件
函数原型：void OnIncomingPhone(long lAudioDeviceID, LPCTSTR IncomingNum);
功能说明：指定的终端设备收到新来电；
参数说明：lAudioDeviceID->坐席号；
          IncomingNum->收到的来电号码；
          普通的号码长度>1；如果长度是1，号码是2，那代表“出局”，号码是1，那代表“保密”。
 
 */
 function OnIncomingPhone(lAudioDeviceID,IncomingNum){
	 addMessages("指定的终端设备收到新来电");
 }




$(function()
{
    buildBtns();
   // registerAINFEvents();
   var lAudioDeviceID = 6001;
   if(UsbPhone.OffHand(lAudioDeviceID) != 0){
	   addMessages("摘机失败");
   }else{
	   $btnHangup.removeAttr("disabled");
	   $btnCallOut.attr("disabled", "disabled");
	   addMessages("摘机成功");
   }
   
});


/**
SetFlashTime 调用不成功   Flash 闪断这个方法调用的时候需要
						Flash操作的时间长度,取值为0--100ms,1--180ms,2--300ms,3--600ms,4--1000ms之间。
						没有具体说明 , 在文档中也没有具体说明 方法调用的取值范围				

3.6、设置闪断时间：DEMO界面上放置一个下拉框（列出FLASH闪断时间选项），对应函数是SetFlashTime；
3.7、闪断：DEMO界面上放置一个按钮，实现拍叉操作，对应函数是Flash；

*/










