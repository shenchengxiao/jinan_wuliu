

/**
 * 构造按钮对象
 */
/*function buildBtns()
{*/
    var $btnSignIn = $("#btnSignIn");
    var $btnSignOut = $("#btnSignOut");
    var $btnCallOut = $("#btnCallOut");// 呼出
    var $btnSetIdle = $("#btnSetIdle");//摘机
    var $btnSetBusy = $("#btnSetBusy");
    var $btnHangup  = $("#btnHangup");    //挂机
    var $btnPhoneState = $("#btnPhoneState"); // 获取话机当前状态            
    var $btnFlashTime = $("#btnFlashTime"); // 设置闪断时间
    var $btnBroken = $("#btnBroken"); // 闪断
    var $btnStartRecording = $("#btnStartRecording"); //开始录音
    var $btnEndRecording = $("#btnEndRecording"); // 结束录音
    var $btnMute = $("#btnMute");//静音
    var $btnCloseSound = $("#btnCloseSound"); //闭音
    var $btnAnswer = $("#btnAnswer");//开启自动接听
    var $btnTalkRecIO = $("#btnTalkRecIO"); //打开通话录音io口
    var $btnLeave = $("#btnLeave");//打开留言放音io口
    var $btnKeep = $("#btnKeep");//开启保留
    var $btnDialTone = $("#btnDialTone");//设置拨号音
    var $btnBill = $("#btnBill");//打开特色铃声;  
    var $btnVOIPIO = $("#btnVOIPIO"); // 打开PC
//}

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
1.OnIncomingPhone事件
函数原型：void OnIncomingPhone(long lAudioDeviceID, LPCTSTR IncomingNum);
功能说明：指定的终端设备收到新来电；
参数说明：lAudioDeviceID->坐席号；
          IncomingNum->收到的来电号码；
          普通的号码长度>1；如果长度是1，号码是2，那代表“出局”，号码是1，那代表“保密”。
*/
function OnIncomingPhone(lAudioDeviceID,IncomingNum)
{
	var lAudioDeviceID = 0;
	var callphone = $('#callee');
	
	addMessages("新来电呼入:" + '工号:' + lAudioDeviceID + '电话' + IncomingNum);
	document.getElementById("callee").value = IncomingNum;  // 来电显示复制给文本框
	//$btnSetIdle.removeAttr("disabled");
	//$btnHangup.removeAttr("disabled");
}

/**
4.OnDeviceDetect事件
函数原型：void OnDeviceDetect事件(short bState, long lAudioDeviceID);
功能说明：指定的终端设备所连接的线路当前状态发生改变；
参数说明： lAudioDeviceID->坐席号；
          bState为1->设备插入，此时lAudioDeviceID是可操作的终端设备标识（大于等于0）；
          bState为0->设备拔出，此时lAudioDeviceID是被拔出的设备标识；

		  
function OnDeviceDetect(bState,lAudioDeviceID)
{
	var bsta;
	if(!bState){
		bsta = "设备插入";
		UsbPhone.Init();
		addMessages('设备初始化成功');
	}else{
		bsta = "设备拔出";
	}
	addMessages('坐席号:'+ lAudioDeviceID + "状态:" + bsta);
	
	//alert('设备状态:'+ bState + '工号:' + lAudioDeviceID);
}
		*/  
		  
/**		
function OnDeviceDetect(bState,lAudioDeviceID)
{
	if(bState == 1){
		addMessages("设备已连接");
		console.log("状态:" + bState + '坐席号:'+ lAudioDeviceID);
		addMessages("状态:" + bState + '坐席号:'+ lAudioDeviceID);
	}else if(bState == 0){
		var bState = 0;
		OnDeviceDetect(bState,lAudioDeviceID)
		addMessages('设备已断开');
		addMessages("状态:" + bState + '坐席号:'+ lAudioDeviceID);
	}
	//alert('设备状态:'+ bState + '工号:' + lAudioDeviceID);
}
*/ 

/**
5.OnPhoneRing事件
函数原型：void OnPhoneRing(long lAudioDeviceID, short bState);
功能说明：指定的终端设备所连接的线路当前铃声状态发生改变；
参数说明：lAudioDeviceID->坐席号；
          bState为1->响铃声，
          bState为0->无铃声；
*/
function OnPhoneRing(lAudioDeviceID,bState)
{
	//alert('坐席号:'+ lAudioDeviceID + '状态:' + bState );
	
	var btnSetIdle = document.getElementById("btnSetIdle");
	
	if(bState != 0)
	{
		addMessages('正在响铃');
		$btnSetIdle.removeAttr("disabled");
		//btnSetIdle.style.background="url(../../images/hion/Uc3_p1_27.jpg)";
		
		$btnCallOut.attr("disabled", "disabled");
	}
}
/**
6.OnStopLiuyan事件
函数原型：void OnStopLiuyan(long lAudioDeviceID);
功能说明：指定的终端设备按“摘机”键停止留言录音；
参数说明：lAudioDeviceID->坐席号；
*/
function OnStopLiuyan(lAudioDeviceID)
{
	addMessages('指定的终端设备按“摘机”键停止留言录音:'+ lAudioDeviceID);
}
/**
7.OnMuteKey事件
OnMuteKey(long lAudioDeviceID, short bStatus);
功能说明：指定的终端设备按“静音”键；
参数说明：lAudioDeviceID->坐席号；
          bStatus为1->“静音”键开启，
          bStatus为0->“静音”键关闭；
*/
function OnMuteKey(lAudioDeviceID,bStatus)
{
	var bsta;
	if(bStatus == 1){
		bsta = "终端静音键开启";
	}else if(bStatus == 0){
		bsta = "终端静音键关闭";
	}
	addMessages('静音坐席:'+ lAudioDeviceID + "状态:" + bsta);
}
/**
8.OnHoldKey事件
OnHoldKey(long lAudioDeviceID, short param);
功能说明：指定的终端设备按“保留”键；
参数说明：lAudioDeviceID->坐席号；
          param为1->“保留”键开启，
          param为0->“保留”键关闭
*/
function OnHoldKey(lAudioDeviceID,param)
{
	//var lAudioDeviceID = 6001;
	var para;
	if(param==1){
		para = "终端保留键开启";
	}else if(param==0){
		para = "终端保留键关闭";
	}
	addMessages('保留坐席:'+ lAudioDeviceID + "状态:" + para);
	//alert('保留坐席:'+ lAudioDeviceID + "状态:" + param);
}
/**
3.OnPhoneStateChange事件
函数原型：void OnPhoneStateChange(long lAudioDeviceID, short bState);
功能说明：指定的终端设备所连接的线路当前状态发生改变；
参数说明：lAudioDeviceID->坐席号；
          bState为2->终端在保留的状态下按免提键退出了保留，
          bState为1->改变后的状态为摘机，
          bState为0->改变后的状态为挂机；
*/
function OnPhoneStateChange(lAudioDeviceID,bState){
	var sta;
	if(bState==0){
		sta="挂机";
		$btnHangup.attr("disabled", "disabled");
		$btnCallOut.removeAttr("disabled");
		//$btnSetIdle.removeAttr("disabled");
		$btnTalkRecIO.attr("disabled", "disabled");
		$btnStartRecording.attr("disabled", "disabled");
		$btnLeave.attr("disabled", "disabled");
		$btnCloseSound.attr("disabled", "disabled");
		$btnKeep.attr("disabled", "disabled");
	}else if(bState==1){
		sta="摘机";
		$btnVOIPIO.attr("disabled", "disabled");// PC
		$btnCallOut.attr("disabled", "disabled");
		$btnSetIdle.removeAttr("disabled");
		$btnTalkRecIO.removeAttr("disabled");
		$btnCloseSound.removeAttr("disabled");
		$btnKeep.removeAttr("disabled");
		$btnLeave.removeAttr("disabled");
		document.getElementById("btnSetIdle").style.background="url(../.../../imagess/Uc3_p1_21.jpg)";
		
	}else if(bState==2){
		sta="终端在保留的状态下按免提键退出了保留";
	}
	addMessages('状态改变坐席:'+ lAudioDeviceID + "状态:" + sta);
}


/**
 注册接口事件
*/
function registerUsbPhoneEvents()
{
    var buffer = new Array();
    buffer.push("function UsbPhone::OnIncomingPhone(lAudioDeviceID,IncomingNum)");
	buffer.push("{");
	buffer.push("   OnIncomingPhone(lAudioDeviceID,IncomingNum);");
	buffer.push("};");
	buffer.push("function UsbPhone::OnDeviceDetect(bState,lAudioDeviceID)");
	buffer.push("{");
	//buffer.push("   OnDeviceDetect(bState,lAudioDeviceID);");
	buffer.push("};");
	buffer.push("function UsbPhone::OnPhoneRing(lAudioDeviceID,bState)");
	buffer.push("{");
	buffer.push("   OnPhoneRing(lAudioDeviceID,bState);");
	buffer.push("};");
	buffer.push("function UsbPhone::OnStopLiuyan(lAudioDeviceID)");
	buffer.push("{");
	buffer.push("   OnStopLiuyan(lAudioDeviceID);");
	buffer.push("};");
	buffer.push("function UsbPhone::OnMuteKey(lAudioDeviceID,bStatus)");
	buffer.push("{");
	buffer.push("   OnMuteKey(lAudioDeviceID,bStatus);");
	buffer.push("};");
	buffer.push("function UsbPhone::OnHoldKey(lAudioDeviceID,param)");
	buffer.push("{");
	buffer.push("   OnHoldKey(lAudioDeviceID,param);");
	buffer.push("};");
	buffer.push("function UsbPhone::OnPhoneStateChange(lAudioDeviceID,bState)");
	buffer.push("{");
	buffer.push("   OnPhoneStateChange(lAudioDeviceID,bState);");
	buffer.push("};");
    eval(buffer.join(""));
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
* myocx
  呼出
*/
function clickCallOut()
{
	var lAudioDeviceID = 0;
	var OutNum = $.trim($("#callee").val());
	UsbPhone.Init();
	if(UsbPhone.Dial(lAudioDeviceID, OutNum) != 0 )
	{
//		alert('呼出成功');
		$("#btnCallOut").attr("disabled", "disabled");
		$("#btnSetIdle").removeAttr("disabled");
		$("#btnTalkRecIO").removeAttr("disabled");
		$("#btnCloseSound").removeAttr("disabled");
		$("#btnKeep").removeAttr("disabled");
		$("#btnLeave").removeAttr("disabled");
		document.getElementById("btnSetIdle").style.background="url(../../images/hion/Uc3_p1_21.jpg)";
		addMessages("呼出成功:" + "坐席:" +lAudioDeviceID + "号码:" + OutNum );
		//alert('呼出成功!!!!!!');
	}
	else
	{
		addMessages("呼叫失败:" + "坐席:" +lAudioDeviceID + "号码:" + OutNum );
	}
}
/**
 * 点击设置闪断时间       
 */
 function clickFlashTime(){
	var lAudioDeviceID = 0;
	var options = $("#selectFlashTime option:selected");  //获取选中的项
	var nFlashTime = options.val();
	var ntime = options.text();
	//alert(options.val());   //拿到选中项的值
	//alert(options.text());   //拿到选中项的文本
	//alert(nFlashTime);
	 //SetFlashTime(long lAudioDeviceID, short nFlashTime)
	 UsbPhone.Init();
	 if(UsbPhone.SetFlashTime(lAudioDeviceID,nFlashTime) == 1){
		addMessages("闪断时间设置为" + ntime); 
	 }else{
		 addMessages("闪断时间设置失败");
	 }
 }

/**
*点击挂机按钮
*/
function clickHangup()
{
	var lAudioDeviceID = 0;
	UsbPhone.Init();
    if(UsbPhone.OnHand(lAudioDeviceID) != 0)
	{
	    $btnHangup.attr("disabled", "disabled");
		$btnCallOut.removeAttr("disabled");
		$btnSetIdle.attr("disabled", "disabled");
		$btnVOIPIO.removeAttr("disabled");
		addMessages("挂机成功=>" + "坐席:" +lAudioDeviceID);
		
    }
	else
	{
	    addMessages("挂机失败=>" + "坐席:" +lAudioDeviceID);
    }       
}
/**
 * 点击获取话机当前状态
 */
 function clickPhoneState()
 {
	var lAudioDeviceID = 0;
	if(UsbPhone.GetPhoneState(lAudioDeviceID) == 0)
	{
		addMessages("挂机状态");
	}
	else if(UsbPhone.GetPhoneState(lAudioDeviceID) == 1)
	{
		addMessages("摘机状态");
	}
	else
	{
		addMessages("失败");
	}
 }
 
 /**
6.OnStopLiuyan事件
函数原型：void OnStopLiuyan(long lAudioDeviceID);
功能说明：指定的终端设备按“摘机”键停止留言录音；
参数说明：lAudioDeviceID->坐席号；
*/
function OnStopLiuyan(lAudioDeviceID)
{
	//alert('指定的终端设备按“摘机”键停止留言录音:'+ lAudioDeviceID);
	addMessages('停止留言录音:'+ lAudioDeviceID);
}
 
 /**
 摘机
 */
function clickOffHand()
{
	var SetIdle = document.getElementById('btnSetIdle');

	var dizhi = SetIdle.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//alert(VOIP);
	var lAudioDeviceID = 0;
	
	if(VOIP == "../../images/hion/Uc3_p1_27.jpg"){
		UsbPhone.Init();
		var res = UsbPhone.OffHand(lAudioDeviceID);
		if(res != 0){
			
			//alert(' 进入摘机方法');
			$("#btnCallOut").attr("disabled", "disabled");//拨号
			$("#btnVOIPIO").attr("disabled", "disabled");// PC
			$("#btnTalkRecIO").removeAttr("disabled"); // 通话录音IO口
			$("#btnCloseSound").removeAttr("disabled");//闭音
			$("#btnKeep").removeAttr("disabled");//保留
			$("#btnLeave").removeAttr("disabled");//留言放音IO口
			SetIdle.style.background="url(../.../../imagess/Uc3_p1_21.jpg)";
			//OnStopLiuyan(lAudioDeviceID);
			addMessages("摘机成功=>"  + "坐席:" +lAudioDeviceID);
			//alert('摘机完成');
			
		}
		else
		{
			addMessages("摘机失败");
		}
		
	}else if(VOIP == "../../images/hion/Uc3_p1_21.jpg"){
		UsbPhone.Init();
		var rese = UsbPhone.OnHand(lAudioDeviceID);
		if(rese != 0)
		{
			$("#btnCallOut").removeAttr("disabled");
			$("#btnVOIPIO").removeAttr("disabled");
			$("#btnSetIdle").attr("disabled", "disabled");
			$("#btnTalkRecIO").attr("disabled", "disabled"); // 通话录音IO口
			$("#btnCloseSound").attr("disabled", "disabled");//闭音
			$("#btnKeep").attr("disabled", "disabled");//保留
			$("#btnLeave").attr("disabled", "disabled");//留言放音IO口
			
			//document.getElementById("btnSetIdle").style.background="url(../../images/hion/Uc3_p1_27.jpg)";//摘机
			document.getElementById("btnTalkRecIO").style.background="url(../../images/hion/Uc3_p1_5.jpg)";// 通话录音io口
			
			document.getElementById("btnStartRecording").style.background="url(../../images/hion/Uc3_p1_14.jpg)";//开始录音
			document.getElementById("btnCloseSound").style.background="url(../../images/hion/Uc3_p1_15.jpg)";//闭音
			document.getElementById("btnKeep").style.background="url(../../images/hion/Uc3_p1_12.jpg)";// 保留
			document.getElementById("btnLeave").style.background="url(../../images/hion/Uc3_p1_10.jpg)";//留言放音
			
			SetIdle.style.background="url(../../images/hion/Uc3_p1_27.jpg)";
			addMessages("挂机成功=>" + "坐席:" +lAudioDeviceID);
		}
		else
		{
			addMessages("挂机失败=>" + "坐席:" +lAudioDeviceID);
		}   
	}
} 	
	
	
   
	/**
		if(UsbPhone.OffHand(lAudioDeviceID) != 0)
		{
			addMessages("摘机成功");
			OnStopLiuyan(lAudioDeviceID);
			$btnSetIdle.attr("disabled", "disabled");
			$btnHangup.removeAttr("disabled");
			
			$btnStartRecording.removeAttr("disabled");
			$btnVOIPIO.attr("disabled", "disabled");
			//$btnTalkRecIO.
		}
		else
		{
			addMessages("摘机失败");
		}
		*/


/**
6.Flash方法
函数原型：short Flash(long lAudioDeviceID, short nFlashTime);
功能说明：控制指定的终端设备进行拍叉操作并准备下一次通话。
参数说明：lAudioDeviceID->终端设备标识；
          nFlashTime->Flash操作的时间长度,取值为0--100ms,1--180ms,2--300ms,3--600ms,4--1000ms之间。
返回值： 成功则返回1, 否则返回0；
*/
function clickFlash(){
	var lAudioDeviceID = 0;
	var nFlashTime = 3;
	UsbPhone.Init();
	if(UsbPhone.Flash(lAudioDeviceID,nFlashTime)!=0){
		addMessages("闪断成功");
	}else{
		addMessages("闪断失败");
	}
}
/**

  特色铃声                             
7.RingSet方法
函数原型：short RingSet(long lAudioDeviceID, short nRing);
功能说明：控制指定终端设备进行特色铃声的设定，检测到设备连接时要使用该方法；
参数说明：lAudioDeviceID->坐席号；
          nRing->1:打开特色铃声；
                 0:关闭特色铃声；
返回值：成功则返回1，否则返回0；
*/
function clickRingSet(){
	
	var Bill = document.getElementById('btnBill');
	//alert(dd.style.background);
	var dizhi = Bill.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//alert(dizhi.substring(5,dizhi.length-2)); ivalue.style.background="url(../../images/hion/Uc3_p1_1.jpg)";
	
	var lAudioDeviceID = 0;
	var ivalue = $btnBill.val();
	if(VOIP == "../../images/hion/Uc3_p1_9.jpg"){
		var nRing = 1;
		UsbPhone.Init();
		var res = UsbPhone.RingSet(lAudioDeviceID,nRing);
		if(res != 0){
			addMessages("打开特色铃声");
			//$btnBill.val("关闭特色铃声");
			Bill.style.background="url(../../images/hion/Uc3_p1_8.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_8.jpg"){
		var nRing = 0;
		UsbPhone.Init();
		var res = UsbPhone.RingSet(lAudioDeviceID,nRing);
		if(res != 0){
			addMessages("关闭特色铃声");
			//$btnBill.val("打开特色铃声");
			Bill.style.background="url(../../images/hion/Uc3_p1_9.jpg)";
		}
	}

}
/**
9.SetTalkRecIO方法
函数原型：short SetTalkRecIO(long lAudioDeviceID, short nStatus);
功能说明：控制指定终端设备进行通话录音的io口设定，仅在摘机状态改变，方有用；
参数说明：lAudioDeviceID->坐席号；
          nStatus->1:打开通话录音io口；
                   0:关闭通话录音io口；
返回值：成功则返回1，否则返回0；
*/
function clickTalkRecIO()
{
	var Recording = document.getElementById('btnTalkRecIO');
	//alert(dd.style.background);
	var dizhi = Recording.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//alert(dizhi.substring(5,dizhi.length-2));
	
	
	
	var ivalue = $btnTalkRecIO.val();
	var lAudioDeviceID = 0;
	if(VOIP == "../../images/hion/Uc3_p1_5.jpg"){
		var nStatus = 1;
		UsbPhone.Init();
		var res = UsbPhone.SetTalkRecIO(lAudioDeviceID,nStatus);
		if(res != 0){
			addMessages("打开通话录音io口");
			$("#btnStartRecording").removeAttr("disabled");
			//$btnTalkRecIO.val("关闭通话录音IO口");
			Recording.style.background="url(../../images/hion/Uc3_p1_3.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_3.jpg"){
		var nStatus = 0;
		UsbPhone.Init();
		var res = UsbPhone.SetTalkRecIO(lAudioDeviceID,nStatus);
		if(res != 0){
			addMessages("关闭通话录音io口");
			//$btnStartRecording.attr("disabled","disabled");
			//$btnTalkRecIO.val("打开通话录音IO口");
			Recording.style.background="url(../../images/hion/Uc3_p1_5.jpg)";
		}
	}
	
}
/**
19.StartRecordFile方法
函数原型：short StartRecordFile(long lAudioDeviceID, LPCTSTR strFileName, short nType);
功能说明：在指定的终端设备上开始录音操作；
参数说明：lAudioDeviceID->坐席号；
          strFileName->录音文件名，可以是文件名,也可以是完整的路径,如:"sound.wav"或"C:\\record\\sound.wav"。
                       当没有指定路径时,录音文件保存在当前目录下；
          nType->录音类型：0:本地录音；1:通话录音；2:留言录音；
返回值：成功则返回1，否则返回0；
*/

//var js=document.scripts;
//var jsPath;
//for(var i=js.length;i>0;i--){
// if(js[i-1].src.indexOf("call.js")>-1){
 //  jsPath=js[i-1].src.substring(0,js[i-1].src.lastIndexOf("/")+1);
// }
//}
//alert(jsPath);
//alert(jsPath.length);
//var recid = jsPath.substring(8,(jsPath.length-3));
//alert(jsPath.substring(8,(jsPath.length-3)));
//alert(str.substring(0,10));---------"0123456789"

var url = window.location.href;
//alert(url);
var pathfile = url.substring(21,(url.length-16));
//alert(pathfile);

var strFolder = pathfile + "Record";
//strFolder = "E:\\record";

//var strFolder = recid + "Record";
//alert(strFolder);

var objFSO = new ActiveXObject("Scripting.FileSystemObject");
// 检查文件夹是否存在
if (!objFSO.FolderExists(strFolder)){
  // 创建文件夹
  var strFolderName = objFSO.CreateFolder(strFolder);
  //document.write("创建文件夹: " + strFolderName + "<br>"); 
}




function clickRecordFile()
{
	var Recording = document.getElementById('btnStartRecording');
	//alert(dd.style.background);
	var dizhi = Recording.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//alert(dizhi.substring(5,dizhi.length-2));
	
	var oldtime = new Date().getTime();
	
	var lAudioDeviceID = 0;
	var strFileName = strFolder + "\\" + oldtime +".mp3";
	//var strFileName = "sound.mp3";
	//addMessages('录音文件地址:' + strFileName);
	var nType = 1;
	UsbPhone.Init();
	if(VOIP == "../../images/hion/Uc3_p1_14.jpg"){
		//alert(strFileName);
		console.log("--strFileName--" + strFileName);
		var res = UsbPhone.StartRecordFile(lAudioDeviceID,strFileName,nType);
		//alert(res);
		console.log("--res--1");
		if(res != 0)
		{
			console.log("--res--2");
			addMessages('开始录音');
			//$btnStartRecording.attr("disabled","disabled");
			//$btnEndRecording.removeAttr("disabled");
			Recording.style.background="url(../../images/hion/Uc3_p1_20.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_20.jpg"){
		var res = UsbPhone.StopRecord(lAudioDeviceID);
		if(res != 0){
			addMessages('停止录音');
			Recording.style.background="url(../../images/hion/Uc3_p1_14.jpg)";
		}
	}
}

/**
20.StopRecord方法
函数原型：short StopRecord(long lAudioDeviceID);
功能说明：在指定的终端设备上停止录音；
参数说明：lAudioDeviceID->坐席号；
返回值：成功则返回1，否则返回0；
*/
function clickStopRecord()
{
	var lAudioDeviceID = 0;
	UsbPhone.Init();
	if(UsbPhone.StopRecord(lAudioDeviceID) != 0)
	{
		addMessages('停止录音');
		$btnEndRecording.attr("disabled", "disabled");
		$btnStartRecording.removeAttr("disabled");
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

	var lAudioDeviceID = 0;
	var outcode = $("#outcode").val();  //获取选中的项
	//alert(outcode);
	UsbPhone.Init();
	if(UsbPhone.SetOutcode(lAudioDeviceID,outcode) != 0){
		addMessages("设置出局码成功,出局码:" + outcode);
	}else {
		addMessages("设置出局码失败");
	}
}

/**

13.Mute方法
函数原型：short Mute(long lAudioDeviceID, short bOn);
功能说明：在指定的终端设备上进行闭音操作；
参数说明：lAudioDeviceID->坐席号；
          bOn->1:开启闭音；
               0:关闭闭音；
返回值：成功则返回1，否则返回0；
*/
function clickCloseSound()
{
	var CloseSound = document.getElementById('btnCloseSound');
	//alert(dd.style.background);
	var dizhi = CloseSound.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	
	
	debugger;
	var ivalue = $("#btnCloseSound").val();
	var lAudioDeviceID = 0;
	if(VOIP == "../../images/hion/Uc3_p1_15.jpg"){
		var bOn = 1;
		UsbPhone.Init();
		var res = UsbPhone.Mute(lAudioDeviceID,bOn);
		if(res != 0){
			addMessages("开启闭音");
			//$btnCloseSound.val("关闭闭音");
			CloseSound.style.background="url(../../images/hion/Uc3_p1_16.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_16.jpg"){
		var bOn = 0;
		UsbPhone.Init();
		var res = UsbPhone.Mute(lAudioDeviceID,bOn);
		if(res != 0){
			addMessages("关闭闭音");
			//$btnCloseSound.val("开启闭音");
			CloseSound.style.background="url(../../images/hion/Uc3_p1_15.jpg)";
		}
	}
}
/**
        保留
函数原型：short Hold(long lAudioDeviceID, short bOn);
功能说明：在指定的终端设备上进行保留操作；
参数说明：lAudioDeviceID->坐席号；
          bOn->1:开启保留；
               0:关闭保留；
返回值：成功则返回1，否则返回0；
*/
function clickHold(){
	
	var Keep = document.getElementById('btnKeep');
	//alert(dd.style.background);
	var dizhi = Keep.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//Keep.style.background="url(../../images/hion/Uc3_p1_11.jpg)";
	
	//var ivalue = $btnKeep.val();
	var lAudioDeviceID = 0;
	if(VOIP == "../../images/hion/Uc3_p1_12.jpg"){
		
		var bOn = 1;
		UsbPhone.Init();
		var res = UsbPhone.Hold(lAudioDeviceID,bOn);
		if(res != 0){
			addMessages("开启保留");
			//$btnKeep.val("关闭保留");
			Keep.style.background="url(../../images/hion/Uc3_p1_11.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_11.jpg"){
		var bOn = 0;
		UsbPhone.Init();
		var res = UsbPhone.Hold(lAudioDeviceID,bOn);
		if(res != 0){
			addMessages("关闭保留");
			//$btnKeep.val("开启保留");
			Keep.style.background="url(../../images/hion/Uc3_p1_12.jpg)";
		}
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
function clickAutoAnswerOpen()
{
	var Answer = document.getElementById('btnAnswer');
	//alert(dd.style.background);
	var dizhi = Answer.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//Keep.style.background="url(../../images/hion/Uc3_p1_11.jpg)";
	
	var lAudioDeviceID = 0;
	var ivalue = $btnAnswer.val();
	if(VOIP == "../../images/hion/Uc3_p1_4.jpg"){
		var bOn = 1;
		UsbPhone.Init();
		var res = UsbPhone.SetAutoAnswer(lAudioDeviceID, bOn);
		if(res != 0){
			addMessages("开启自动接听");
			//$btnAnswer.val("取消自动接听");
			Answer.style.background="url(../../images/hion/Uc3_p1_2.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_2.jpg"){
		var bOn = 0;
		UsbPhone.Init();
		var res = UsbPhone.SetAutoAnswer(lAudioDeviceID, bOn);
		if(res != 0){
			addMessages("取消自动接听");
			//$btnAnswer.val("开启自动接听");
			Answer.style.background="url(../../images/hion/Uc3_p1_4.jpg)";
		}
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
	
	var Leave = document.getElementById('btnLeave');
	//alert(dd.style.background);
	var dizhi = Leave.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//Keep.style.background="url(../../images/hion/Uc3_p1_11.jpg)";
	
	
	
	//var ivalue = $btnLeave.val();
	var lAudioDeviceID = 0;
	if(VOIP == "../../images/hion/Uc3_p1_10.jpg"){
		var nStatus = 1;
		UsbPhone.Init();
		var res = UsbPhone.SetLiuyanRecIO(lAudioDeviceID,nStatus);
		if(res != 0){
			addMessages("打开留言放音IO口");
			//$btnLeave.val("关闭留言放音IO口");
			Leave.style.background="url(../../images/hion/Uc3_p1_7.jpg)";
			UsbPhone.OffHand(lAudioDeviceID);
			var strFileName = "liuyan.mp3";
			var nType = 2;
			UsbPhone.Init();
			if(UsbPhone.StartRecordFile(lAudioDeviceID,strFileName,nType) != 0)
			{
				addMessages('开始留言录音');
			}
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_7.jpg"){
		var nStatus = 0;
		UsbPhone.Init();
		var res = UsbPhone.SetLiuyanRecIO(lAudioDeviceID,nStatus);
		if(res != 0){
			if(UsbPhone.StopRecord(lAudioDeviceID) != 0)
			{
				if(UsbPhone.OnHand(lAudioDeviceID) != 0)
				{
					addMessages('挂机成功');
				}
				addMessages('停止留言录音');
			}
			addMessages("关闭留言放音IO口");
			//$btnLeave.val("打开留言放音IO口");
			Leave.style.background="url(../../images/hion/Uc3_p1_10.jpg)";
		}
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
	
	var DialTone = document.getElementById('btnDialTone');
	//alert(dd.style.background);
	var dizhi = DialTone.style.background;
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//Keep.style.background="url(../../images/hion/Uc3_p1_11.jpg)";
	
	var ivalue = $btnDialTone.val();
	var lAudioDeviceID = 0;
	if(VOIP == "../../images/hion/Uc3_p1_22.jpg"){
		var bOn = 1;
		UsbPhone.Init();
		var res = UsbPhone.SetDialTone(lAudioDeviceID,bOn);
		if(res != 0){
			addMessages("设置拨号音");
			//$btnDialTone.val("取消拨号音");
			DialTone.style.background="url(../../images/hion/Uc3_p1_13.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_13.jpg"){
		var bOn = 0;
		UsbPhone.Init();
		var res = UsbPhone.SetDialTone(lAudioDeviceID,bOn);
		if(res != 0){
			addMessages("取消拨号音");
			//$btnDialTone.val("设置拨号音");
			DialTone.style.background="url(../../images/hion/Uc3_p1_22.jpg)";
		}
	}
}
/**
               转拨                   
21.ZhuanBo方法
函数原型：short ZhuanBo(long lAudioDeviceID, short nZhuanboTime);
功能说明：控制指定的终端设备进行拍叉操作。
参数说明：lAudioDeviceID->终端设备标识；
          nZhuanboTime->转拨操作的时间长度,取值为0--100ms,1--180ms,2--300ms,3--600ms,4--1000ms之间。
返回值： 成功则返回1, 否则返回0；
*/
function clickZhuanBo(){
	var lAudioDeviceID = 0;
	var nFlashTime = 0;
	var nZhuanboTime = 0;
	var callee = $("#zhuanbo").val();
	//alert(callee);
	UsbPhone.Init();
	//if(UsbPhone.Flash(lAudioDeviceID, nFlashTime) != 0)
	//{
		if(UsbPhone.ZhuanBo(lAudioDeviceID, nZhuanboTime) != 0 ){
			
			if (UsbPhone.Dial(lAudioDeviceID, callee) != 0){
				$btnCallOut.attr("disabled", "disabled");
				$btnHangup.removeAttr("disabled");
				$btnStartRecording.removeAttr("disabled");
				addMessages("转拨成功");
				
			}else{
				addMessages("呼出失败");
			}
		}
	//}
}
/**
	打开PC 和 关闭PC
8.SetVOIPIO方法
函数原型：short SetVOIPIO(long lAudioDeviceID, short nStatus);
功能说明：控制指定终端设备进行VOIP录音的io口设定，仅在挂机状态改变，方有用，启动关闭pc也是这个函数；
参数说明：lAudioDeviceID->坐席号；
          nStatus->1:打开VOIP 放音、录音io口；
                   0:关闭VOIP 放音、录音io口；
返回值：成功则返回1，否则返回0；
*/
function clickVOIPIO()
{
	var VOIPIO = document.getElementById('btnVOIPIO');
	//alert(VOIPIO.style.background);
	var dizhi = VOIPIO.style.background;
	//alert(dizhi);
	var VOIP = dizhi.substring(5,dizhi.length-2);
	//alert(VOIP);// ivalue.style.background="url(../../images/hion/Uc3_p1_1.jpg)";
	
	
	var ivalue = document.getElementById("btnVOIPIO");
	var lAudioDeviceID = 0;

	if(VOIP == "../../images/hion/Uc3_p1_6.jpg")
	{
		//alert(11111111);
		var nStatus = 1;
		var res = UsbPhone.SetVOIPIO(lAudioDeviceID,nStatus);
		if(res != 0)
		{
			addMessages("打开VOIP 放音、录音io口");
			//$btnVOIPIO.val("关闭PC");
			ivalue.style.background="url(../../images/hion/Uc3_p1_1.jpg)";
		}
	}else if(VOIP == "../../images/hion/Uc3_p1_1.jpg")
	{
		//alert(2222222);
		var nStatus = 0;
		var res = UsbPhone.SetVOIPIO(lAudioDeviceID,nStatus);
		if(res != 0)
		{
			addMessages("关闭VOIP 放音、录音io口");
			//$btnVOIPIO.val("打开PC");
			ivalue.style.background="url(../../images/hion/Uc3_p1_6.jpg)";
		}
	}
}
/**
$(function()
{
	UsbPhone.Init(); //设备初始化
	//addMessages('设备初始化成功');
	buildBtns();
    registerUsbPhoneEvents();
});
*/



