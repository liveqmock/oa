/**
* bodyonload用来设置页面需要在BODY的onload事件里执行的程序，
* 当页面引入此js文件时，要求页面的BODY标签中不能设置onload事件，
* 需要在此事件中执行的JS程序请设置到此变量中。程序将在页面的onload事件中执行这些代码。
* 例如：
* <script language="javascript" src="runFormVerify.js"></script>
* <script language="javascript">
* 	bodyonload = "alert('onload事件中运行的程序！')";
* </script>
*/
var allHtml = "B,BIG,APPLET,ABBR,ACRONYM,ADDRESS,BASEFONT,BDO,BGSOUND,BIG,BLINK,BLOCKQUOTE,BR,BUTTON,CENTER,CITE,CITE,CODE,DEL,DFN,DIR,DIV,DL,EM,EMBED,FIELDSET,FONT,H1,H6,H2,H3,H4,H5,HR,I,IFRAME,IMG,INS,,KBD,LABEL,MAP,MARQUEE,MENU,NOBR,NOFRAMES,NOSCRIPT,OBJECT,OL,P,PRE,Q,S,SAMP,SCRIPT,SELECT,SMALL,SPAN,STRIKE,STRONG,SUB,SUP,TABLEtrtd,TEXTAREA,TT,U,UL,VAR,WBR";
var allHtml1 = "INPUT,ISINDEX";
 	var htmlChar1 = allHtml1.split(",");
	var htmlChar = allHtml.split(",");
var formTitle = "formTitle"; 
var allMsg="";
var allHtmlMsg="";
//为了提示错误以后移向第一个不符合规则的页面
var errObj = null;
var gifResource = new Array();
gifResource["info"] = "/examine/images/MessageInfo.gif";
gifResource["infoColor"] = "#009900";
gifResource["debug"] = "/examine/images/MessageDebug.gif";
gifResource["debugColor"] = "#0066FF";
gifResource["warn"] = "/examine/images/MessageWarn.gif";
gifResource["warnColor"] = "#0066FF";
gifResource["error"] = "/examine/images/MessageError.gif";
gifResource["errorColor"] = "#FF0000";
gifResource["fatal"] = "/examine/images/MessageFatal.gif";
gifResource["fatalColor"] = "#0066FF";
var gifPath=gifResource["info"];
var color=gifResource["infoColor"];

/*
var exp = window.onload;
window.onload = function() {
	var submit = new Array();
	for (var i = 0; i < document.forms.length; i++) {
		submit[i] = document.forms(i).submit;
		document.forms(i).submit[i] = function(verify,index) {
			if (verify == false || runFormVerify(this)) {
				submit();
				return true;
			}
			return false;
		};

		
		var onsubmit = document.forms(i).onsubmit;
		document.forms(i).onsubmit = function() {
			if (runFormVerify(this)) {
				if (onsubmit == null) {
					return true;
				}
				return onsubmit();
				
			}
			return false;
		};
	}
	if (exp != null) {
		exp();
	}
	if (bodyonload != null && bodyonload.trim() != "") {
		eval(bodyonload);
	}
};
*/
/**
 * 页面提交
 */
function pageshow(form,verify){
	if (_check(form)){
		form.submit();
	}
}

function _check(form){
	allHtmlMsg = "";
	allMsg = "";
//不管验证不验证，将input的text和textarea
	//判断input
	var els = form.tags("input");
	var isHtml = false;
	for (var i = 0; i < els.length; i++) {
		var inputValue = els[i].value.toUpperCase();
		var inputValueNoSpace = inputValue.atrim();
		for (var j=0;j<htmlChar1.length;j++){
			if(htmlChar1[j].atrim() != ""){
				if (inputValueNoSpace.indexOf("<" + htmlChar1[j] + ">")!=-1 || inputValue.indexOf(htmlChar1[j] + " ") != -1 || inputValueNoSpace.indexOf(htmlChar[j] + ">")!=-1 || (inputValueNoSpace.indexOf("<" + htmlChar1[j]) + htmlChar1[j].length + 1 == inputValueNoSpace.length)){
					if (inputValueNoSpace.indexOf("<" + htmlChar1[j])!=-1){
						showCheckInfo(els[i],"不能包含<" + htmlChar1[j] + ">节点！");
						isHtml = true;
						if (errObj == null){
							errObj = els[i];
						}
					}
				}
			}
		}
		for (var j=0;j<htmlChar.length;j++){
			if(htmlChar[j].atrim() != ""){
				if (inputValueNoSpace.indexOf("<" + htmlChar[j] + ">")!=-1 || inputValue.indexOf(htmlChar[j] + " ") != -1 || inputValueNoSpace.indexOf(htmlChar[j] + ">")!=-1 || (inputValueNoSpace.indexOf("<" + htmlChar[j]) + htmlChar[j].length + 1 == inputValueNoSpace.length)){
					if (inputValueNoSpace.indexOf("<" + htmlChar[j])!=-1){
						showCheckInfo(els[i],"不能包含<" + htmlChar[j] + ">节点！");
						isHtml = true;
						if (errObj == null){
							errObj = els[i];
						}
					}
				}
				if (inputValueNoSpace.indexOf("</" + htmlChar[j] + ">")!=-1){
					showCheckInfo(els[i],"不能包含</" + htmlChar[j] + ">节点！");
					isHtml = true;
					if (errObj == null){
						errObj = els[i];
					}
				}
			}
		}
	}
	//判断textarea
	var els = form.tags("textarea");
	for (var i = 0; i < els.length; i++) {
		var inputValue = els[i].value.toUpperCase();
		var inputValueNoSpace = inputValue.atrim();
		if (inputValue.indexOf("TEXTAREA ") != -1 || inputValueNoSpace.indexOf("TEXTAREA>") != -1){
			if (inputValueNoSpace.indexOf("<TEXTAREA") != -1){
				showCheckInfo(els[i],"不能包含<textArea>节点！");
				isHtml = true;
				if (errObj == null){
					errObj = els[i];
				}
			}
		}
		if (inputValueNoSpace.indexOf("</TEXTAREA>") != -1){
			showCheckInfo(els[i],"不能包含</textArea>节点！");
			isHtml = true;
			if (errObj == null){
				errObj = els[i];
			}
		}
	}
	if (runFormVerify(form,isHtml)){
		return true;
	}
	onFocus();
	return false;

}


function onFocus(){
	try{
		if (errObj == null){
			return false;
		}
		var tagName = errObj.tagName.toLowerCase();
		if ((tagName == "input" && (errObj.type == "text" || errObj.type == "password")) || tagName == "textarrea") {
			//errObj.focus();
			errObj.select();
		}
	}catch(e){}
}
function pageClose(form,winObj){
		
		if (runFormVerify(form)){
			winObj.close();
		}
}
function runFormVerify(form,isHtml) {
	//allMsg="";
	//var form = document.forms.item(formI);
	var result = true;
	var els = form.tags("input");
	for (var i = 0; i < els.length; i++) {

		if (!checkVerify(els[i])) {
			result = false;
		}
	}
	var els = form.tags("textarea");
	for (var i = 0; i < els.length; i++) {

		if (!checkVerify(els[i])) {
			result = false;
		}
	}
	var els = form.tags("select");
	for (var i = 0; i < els.length; i++) {

		if (!checkVerify(els[i])) {
			result = false;
		}
	}
	if (isHtml){
		result = false;
	}
	if (result == false) {
		outputMsg();
	}
	return result;
}

function checkVerify(el) {

	var tagName = el.tagName.toLowerCase();
	
	var notNull = el.getAttribute("notnull");
	if (notNull != null && notNull.trim() != "") {
		if (el.value == null || el.value.trim() == "") {
			showCheckInfo(el, notNull);
			if (errObj == null){
				errObj = el;
			}
			return false;
		}
	}
	if ((tagName == "input" && el.type == "text") || tagName == "textarea") {
		var len = el.getAttribute("maxlength");
		if (len != null && !isNaN(parseInt(len)) && parseInt(len) > 0) {
			if (el.value.getByte() > parseInt(len)) {
				showCheckInfo(el, "输入的内容过长！最大长度为：" + len + "字符！");
				if (errObj == null){
					errObj = el;
				}
				return false;
			}
		}
	}
	var verifys = el.getAttribute("restriction");
	if (tagName == "select" 
		|| verifys == null 
		|| verifys.trim() == "" 
		|| el.value == null
		|| el.value.trim() == "") {
		if (errObj == null){
			errObj = el;
		}
		return true;
	}
	var verifyValue = true;
	var verify = verifys.split("|");
	for(var j=0;j<verify.length;j++){
		var paras = verify[j].split(",");
		var program = paras[0] + "Verify(el";
		for (var i = 1; i < paras.length; i++) {
			var para = paras[i];
			program += (", \"" + para + "\"");
		}
		program += ");";
		try{
			var checkResult = eval(program);
			if (success != checkResult) {
				showCheckInfo(el, checkResult);
				verifyValue = false;
			}
		}catch(e){
			alert("校验实效！\n错误信息：\n  " + e);
		}
	}

	if (verifyValue) {
		return true;
	}
	if (errObj == null){
		errObj = el;
	}
	return false;
}

function showCheckInfo(el, msg) {
	var atitle = el.getAttribute(formTitle);
	if (atitle == null){
		atitle = el.title;
	}
	if (atitle == null){
		atitle = "";
	}
	if (allMsg!=""){
		allMsg+="\n";
	}
	if (allHtmlMsg!=""){
		allHtmlMsg+="<br>";
	}
	if (atitle==""){
		allMsg+=atitle + msg;
		allHtmlMsg+= "&nbsp;&nbsp;<IMG  src="+gifPath+" ><font color="+color+">" + atitle + msg.encodeHtml()+"</font>";
	}else{
		allMsg+="[" + atitle + "]" + msg;
		allHtmlMsg+= "&nbsp;&nbsp;<IMG  src="+gifPath+" ><font color="+color+">["+atitle + "]" + msg.encodeHtml()+"</font>";
	}
}



function msgClick(msgdiv) {
	var msgname = msgdiv.id.replace(new RegExp("^(.*)" + SUF_MSGDIV_ID + "$"), "$1");
	var index = 0;

	msgdiv.style.display = "none";
	try {
		document.getElementsByName(msgname)[index].focus();
	}
	catch (e) {}
}

//获取某个Html元素在运行时的绝对位置信息
function GetAbsoluteLocationEx(element) 
{ 
	if ( arguments.length != 1 || element == null ) { 
		return null; 
	} 
	var elmt = element; 
	var offsetTop = elmt.offsetTop; 
	var offsetLeft = elmt.offsetLeft; 
	var offsetWidth = elmt.offsetWidth; 
	var offsetHeight = elmt.offsetHeight; 
	while( elmt = elmt.offsetParent ) { 
		// add this judge 
		if ( elmt.style.position == 'absolute' || elmt.style.position == 'relative'  
			|| ( elmt.style.overflow != 'visible' && elmt.style.overflow != '' ) ) { 
			break; 
		}  
		offsetTop += elmt.offsetTop; 
		offsetLeft += elmt.offsetLeft; 
	} 
	return { absoluteTop: offsetTop, absoluteLeft: offsetLeft, 
		offsetWidth: offsetWidth, offsetHeight: offsetHeight }; 
}
/**
 * 校验信息的输出
 */ 
function outputMsg(){
/**
	try{
		document.getElementById("outputMsg").innerHTML=allHtmlMsg;
		document.getElementById("msgWindow").style.display="";
		window.location="#";
	}catch(e){
		alert(allMsg);
	}
	**/
	alert(allMsg);
	allHtmlMsg = "";
	allMsg = "";
}
/**
 * 页面的特殊校验
 */ 
function outputOtherMsg(msg,msgType){
/**	try{
		var gifMsgPath;
		var colorMsg;
		if (msgType == null){ 
			gifMsgPath = gifResource["info"];
			colorMsg = gifResource["infoColor"];
		}else{
			gifMsgPath = gifResource[msgType];
			colorMsg = gifResource[msgType + "Color"];
		}

		var htmlMsg = "&nbsp;&nbsp;<IMG  src="+gifMsgPath+" ><font color="+colorMsg+">" + msg+"</font>";
		document.getElementById("outputMsg").innerHTML=htmlMsg;
		document.getElementById("msgWindow").style.display="";
		window.location="#";
	}catch(e){
	**/
		alert(msg);
//	}
}
/**
 * 除去所有错误
 */ 
function removeMsg(){
	try{
		document.getElementById("outputMsg").innerHTML="";
		document.getElementById("msgWindow").style.display="none";
		window.location="#";
	}catch(e){
	}
}
/**
 * 输出所有的错误
 */ 
function outputMsgs(msg,msgType){
/**	try{
		var gifMsgPath;
		var colorMsg;
		if (msgType == null){ 
			gifMsgPath = gifResource["info"];
			colorMsg = gifResource["infoColor"];
		}else{
			gifMsgPath = gifResource[msgType];
			colorMsg = gifResource[msgType + "Color"];
		}

		var htmlMsg = "&nbsp;&nbsp;<IMG  src="+gifMsgPath+" ><font color="+colorMsg+">" + msg+"</font>";
		 
		var msgObj=document.getElementById("outputMsg");
		if (msgObj.innerHTML==""){
			msgObj.innerHTML=htmlMsg;
		}else{
			msgObj.innerHTML+="<br>" + htmlMsg;
		}
		document.getElementById("msgWindow").style.display="";
		window.location="#";
	}catch(e){
		alert(msg);
	}
	*/
	alert(msg);
}
//单选
function hasSelectedOne(inputName){
	if (hasSelectedValue(inputName)==1){
		return true;
	}else{
		return false;
	}
}
function hasSelected(inputName){
	if (hasSelectedValue(inputName,true)>0){
		return true;
	}else{
		return false;
	}
}
function hasSelectedValue(inputName,isOne){
	if (isOne == null){
		isOne = false;
	}
	var objs = document.getElementsByName(inputName);
	if (objs == null){
		return 0;
	}
	var sel = 0;
	for (var i=0;i<objs.length;i++){
		if (objs[i].checked){
			sel ++;
			if (isOne){
				return sel;
			}
		}
	}
	return sel;
}
function getStat(name){
	var radios = document.getElementsByName(name);
	var str = "";
	var isSel = false;
	for(var i=0;i<radios.length;i++){
		if (radios[i].checked){
			isSel = true;
			if (radios[i].getAttribute("stat")!="0"){
				str += "[" + radios[i].title + "]数据表已经创建,不能删除!\n";
				radios[i].checked = false;
			}
		}
	}
	if (isSel == false){
		str = "请选择要删除的数据表";
	}
	return str;
}
//function getStat1(name){
//	var radios = document.getElementsByName(name);
//	var str = "";
//	var isSel = false;
//	for(var i=0;i<radios.length;i++){
//		if (radios[i].checked){
//			isSel = true;
//			if (radios[i].getAttribute("stat")！="1"){
//				str += "[" + radios[i].title + "]数据表尚未创建或已停用,不能停用!\n";
//				radios[i].checked = false;
//			}
//		}
//	}
//	if (isSel == false){
//		str = "请选择要停用的数据表";
//	}
//	return str;
//}