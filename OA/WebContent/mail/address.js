var sendToId = "sendto";
var sendccId = "sendcc";
var sendbccId = "sendbcc";
//人名前面带字符(true)或者图标(false)
var showChar = false;

var selSendValue = new Array();
var selccValue = new Array();
var selbccValue = new Array();
//分别是选择中发送，抄送，密送中选择的
var selSend = new Array();
var selcc = new Array();
var selbcc =new Array();

//得到所有选择信息
function getSelect(type){
	if (type == 1){
		return selccValue;
	}else if (type ==2){
		return selbccValue;
	}else{
		return selSendValue;
	}
}
function addSelect(values,type){
	getSelect(type) = values;
}
//添加所有选择信息
function selectName(obj,type){
	var objClassName = obj.className;
	if (objClassName == "send"){
		obj.className = "sendSelect";
		addSel(obj,type);
	}else{
		obj.className ="send";
		removeSel(obj,type);
	}
}
/**
 * 取消选中
 */
function removeSel(obj,type){
	var selIndex = getArray(obj,type);
	if (selIndex != -1){
		getSelObj(type)[selIndex] = "";
	}
}
//选中
function addSel(obj,type){
	if (getArray(obj,type) == -1){
		getSelObj(type)[getSelObj(type).length] = obj;
	}
}
//删除选择的一个人
function delSel(type){
	var isDelete = false;
	for(var i=0;i<getSelObj(type).length;i++){
		if (getSelObj(type)[i] != ""){
			getSelObj(type)[i].outerHTML = "";
			isDelete = true;
		}
	}
	changepnum();
	if (isDelete){
		getAllPerson(type);
		var sendId = sendToId;
		if (type == 1){
			sendId = sendccId;
		}else if (type == 2){
			sendId = sendbccId;
		}
		//if (document.getElementById(sendId).innerHTML == ""){
		//	document.getElementById(sendId).style.height = "22px";
	//	}
		//var tempArray = getOurPerson(type);
		//if (tempArray.length<50){
		//	reloadPerson(type,tempArray);
		//}
	}

}
//得到选择类型的对象（发送，抄送，密送）
function getSelObj(type){
	if (type == 1){
		return selcc;
	}else if (type ==2){
		return selbcc;
	}else{
		return selSend;
	}
}
//得到制定的对象
function getArray(obj,type){
	for(var i=0;i<getSelObj(type).length;i++){
		if (getSelObj(type)[i] == obj){
			return i;
		}
	}
	return -1;
}
//加载选择的数据
function loadPerson(sendType){
	var sendId = sendToId;
	if (sendType == 1){
		sendId = sendccId;
	}else if (sendType == 2){
		sendId = sendbccId;
	}
	var ele = document.getElementById(sendId);
	var tempStr = "";
	var selValue = getSelect(sendType);
	//var allName = "";
	var showName = "";
	var length = "";
	if(selValue.length > broadcastnum){
		var ok = confirm("群发数量不能大于"+broadcastnum+",将截取前"+broadcastnum+"个人");
		if(ok){
			length = broadcastnum;
		}else{
			length = 0;
		}
	}else{
		length = selValue.length;
	}

	for(var i=0;i<length;i++){
		var typeImg = "person.gif";
		var type = selValue[i]["type"];
		var nameValue = selValue[i]["name"] + ",";
	 	//allName += nameValue + "图|";
	 	/*brSize = tempStr.split("<br>").length ;
	 	if ((getByte(allName)/70)>brSize && (i+1)<selValue.length){
	 		tempStr +="<br>";
	 	}*/
	 	if (showChar){
	 		var char = "m";
	 		if (type == "00"){
				char = "B";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"所属组织：&#13;&#10;" + selValue[i]["department"] + "\"><font face=\"Webdings\" color=\"#009900\">" + char + "</font>" + nameValue + "</span>";
			}else if (type == "01"){
				char = "H";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"所属组织：&#13;&#10;" + selValue[i]["department"] + "\"><font face=\"Webdings\" color=\"#009900\">" + char + "</font>" + nameValue + "</span>";
			}else{
		 		tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["uuid"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"所属组织：&#13;&#10;" + selValue[i]["department"] + "\"><font face=\"Webdings\" color=\"#009900\">" + char + "</font>" + nameValue + "</span>";
	 		}
	 	}else{
		 	if (type == "00"){
				typeImg = "commongroup.gif";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"公共分组\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
			}else if (type == "01"){
				typeImg = "indigroup.gif";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"个人分组\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
			}else{
		 		tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["uuid"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"所属组织：&#13;&#10;" + selValue[i]["department"] + "\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
			}
		}
		//给显示的选择人准备数据
		if (i>0){
			showName += ",";
		}
		showName += selValue[i]["name"];
	}
	//setShow(sendType,showName);

		ele.innerHTML = tempStr;
	
	
	changepnum();
}
//重新加载数据
function reloadPerson(sendType,selValue){
	var sendId = sendToId;
	if (sendType == 1){
		sendId = sendccId;
	}else if (sendType == 2){
		sendId = sendbccId;
	}
	var ele = document.getElementById(sendId);
	var tempStr = "";
	var allName = "";
	var showName = "";
	for(var i=0;i<selValue.length;i++){
		/*var typeImg = "person.gif";
		var type = selValue[i]["type"];

		var nameValue = selValue[i]["name"] + ",";
	 	allName += nameValue + "图|";
	 	brSize = tempStr.split("<br>").length ;
	 	if ((getByte(allName)/70)>brSize && (i+1)<selValue.length){
	 		tempStr +="<br>";
	 	}
	 	if (type == "00"){
			typeImg = "commongroup.gif";
			tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"公共分组\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
		}else if (type == "01"){
			typeImg = "indigroup.gif";
			tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"个人分组\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
		}else{
	 		tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"所属组织：&#13;&#10;" + selValue[i]["department"] + "\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
		}*/
		//给显示的选择人准备数据
		if (i>0){
			showName += ",";
		}
		showName += selValue[i]["name"];
	}
	setShow(sendType,showName);
	//ele.innerHTML = tempStr;
	
}
/**
 * 得到所有制定类型的数据，并修改所修改的数据
 */
function getAllPerson(sendType){
	var sendId = sendToId;
	if (sendType == 1){
		sendId = sendccId;
	}else if (sendType == 2){
		sendId = sendbccId;
	}
	var spans = document.getElementById(sendId).getElementsByTagName("span");
	var allName = "";
	for(var i=0;i<spans.length;i++){
		if (i>0){
			allName += ",";
		}
		var tempArray = new Array();
		allName += spans[i].getAttribute("personName");
		
	}
	setShow(sendType,allName);
}
/**
 * 得到所有制定
 */
function getOurPerson(sendType){
	var sendId = sendToId;
	if (sendType == "1"){
		sendId = sendccId;
	}else if (sendType == "2"){
		sendId = sendbccId;
	}
	var spans = document.getElementById(sendId).getElementsByTagName("span");
	var newPersons = new Array();
	for(var i=0;i<spans.length;i++){
		var tempArray = new Array();
		tempArray["value"] = spans[i].getAttribute("person");
		tempArray["name"] = spans[i].getAttribute("personName");
		tempArray["department"] = spans[i].getAttribute("department");
		tempArray["uuid"] = spans[i].getAttribute("uuid");
		tempArray["type"] = spans[i].getAttribute("personType");
		newPersons[newPersons.length] = tempArray;
	}
	return newPersons;
}
/**
 * 将发送人的信息放入隐藏域
 */
function getSendHtml(){
	var spans = document.getElementById(sendToId).getElementsByTagName("span");
	for(var i = 0 ;i<spans.length;i++){
		spans[i].className ="send";
	}
	spans = document.getElementById(sendccId).getElementsByTagName("span");
	for(var i = 0 ;i<spans.length;i++){
		spans[i].className ="send";
	}
	spans = document.getElementById(sendbccId).getElementsByTagName("span");
	for(var i = 0 ;i<spans.length;i++){
		spans[i].className ="send";
	}
	document.sendForm.sendHtml.value = document.getElementById(sendToId).innerHTML;
	document.sendForm.ccHtml.value = document.getElementById(sendccId).innerHTML;
	document.sendForm.bccHtml.value = document.getElementById(sendbccId).innerHTML;
}

/**
 * 将数据放到隐藏域然后提交
 */
function setPersonToSend(){
	var saveId = "uuid";
	var spans = document.getElementById(sendToId).getElementsByTagName("span");
	var sendNames_person = "";
	var sendNames_group = "";
	var sendNames_org = "";
	if (spans.length == 0){
		return false;
	}
	var isPerson = false;
	var isGroup = false;
	var isOrg = false;
	for(var i=0;i<spans.length;i++){
		var type = spans[i].getAttribute("personType");
		var personUid = spans[i].getAttribute(saveId);
		if (type == "0"){
			if (isPerson){
				sendNames_person += ",";
			}
			sendNames_person += personUid;
			isPerson = true;
		}else if (type == "00"){
			if (isOrg){
				sendNames_org += ",";
			}		
			sendNames_org += personUid;
			isOrg = true;
		}else if (type == "01"){
			if (isGroup){
				sendNames_group += ",";
			}		
			sendNames_group += personUid;
			isGroup = true;
		}else{
			if (isPerson){
				sendNames_person += ",";
			}		
			sendNames_person += personUid;
			isPerson = true;
		}
	}


	document.all("addPerson_person").value = sendNames_person;
		
	document.all("addPerson_group").value = sendNames_group;
	document.all("addPerson_org").value = sendNames_org;
	spans = document.getElementById(sendccId).getElementsByTagName("span");
	var ccNames_person = "";
	var ccNames_group = "";
	var ccNames_org = "";
	isPerson = false;
	isGroup = false;
	isOrg = false;
	for(var i=0;i<spans.length;i++){
		var type = spans[i].getAttribute("personType");
		var personUid = spans[i].getAttribute(saveId);
		if (type == "0"){
			if (isPerson){
				ccNames_person += ",";
			}
			ccNames_person += personUid;
			isPerson = true;
		}else if (type == "00"){
			if (isOrg){
				ccNames_org += ",";
			}
			ccNames_org += personUid;
			isOrg = true;
		}else if (type == "01"){
			if (isGroup){
				ccNames_group += ",";
			}		
			ccNames_group += personUid;
			isGroup = true;
		}else{
			if (isPerson){
				ccNames_person += ",";
			}		
			ccNames_person += personUid;
			isPerson = true;
		}
	}
	document.all("addcc_person").value = ccNames_person;

	document.all("addcc_group").value = ccNames_group;
	document.all("addcc_org").value = ccNames_org;
	spans = document.getElementById(sendbccId).getElementsByTagName("span");
	var bccNames_person = "";
	var bccNames_group = "";
	var bccNames_org = "";
	isPerson = false;
	isGroup = false;
	isOrg = false;
	for(var i=0;i<spans.length;i++){


		var type = spans[i].getAttribute("personType");
		var personUid = spans[i].getAttribute(saveId);
		if (type == "0"){
			if (isPerson){
				bccNames_person += ",";
			}
			bccNames_person += personUid;
			isPerson = true;
		}else if (type == "00"){
			if (isOrg){
				bccNames_org += ",";
			}
			bccNames_org += personUid;
			isOrg = true;
		}else if (type == "01"){
			if (isGroup){
				bccNames_group += ",";
			}
			bccNames_group += personUid;
			isGroup = true;
		}else{
			if (isPerson){
				bccNames_person += ",";
			}
			bccNames_person += personUid;
			isPerson = true;
		}
	}
	document.all("addbcc_person").value = bccNames_person;
	document.all("addbcc_group").value = bccNames_group;
	document.all("addbcc_org").value = bccNames_org;
	return true;
}
/**
 * 同步显示选择的人（页面最下方）
 */
function setShow(type,value){
	if (type == 1){
		document.all("bottomcc").innerHTML = value;
	}else if (type == 2){
		document.all("bottombcc").innerHTML = value;
	}else{
		document.all("bottomto").innerHTML = value;
	}
}
/**
 * 获得字符串的字节数
 */
function getByte(str){
    var intCount = 0;
    for(var i = 0;i < str.length;i ++){
        // Ascii码大于255是双字节的字符
        var ascii = str.charCodeAt(i).toString(16);
        var byteNum = ascii.length / 2.0;
        intCount += (byteNum + (ascii.length % 2) / 2);
    }
    return intCount;
}
/**
 * 展开/折叠邮件人员列表
 */
function listState(obj,type,toBook){

	var str = "收件人列表";
	var sendId = sendToId;
	if (type == 1){
		sendId = sendccId;
		str = "抄送列表";
	}else if (type == 2){
		sendId = sendbccId;
		str = "密送列表";
	}
	var ele = document.getElementById(sendId);
	var row = ele.innerHTML.split("<BR>").length ;
	if (row == 1){
		row = ele.innerHTML.split("<br>").length ;
	}
	row = row * 21;
	if (ele.getAttribute("state") == "1"){
		ele.style.height = "22px";
		ele.setAttribute("state","0");
		obj.alt = "展开";
		obj.title = "展开" + str;
		obj.src = urlPath + "/images/outspread.gif";
	}else{
		if (ele.innerHTML == ""){
			ele.style.height = "22px";
		}else{
			ele.style.height = "100%";
		}
		
		ele.setAttribute("state","1");
		obj.alt = "收缩";
		obj.title = "收缩" + str;
		obj.src = urlPath + "/images/pucker.gif";
	}
	document.location.href = "#" + toBook;
}





















//系统定义的特殊字符集
var specialChars = /[!@#\$%\^&\*\(\)\{\}\[\]<>_\+\|~`-]|[=\/\\\?;:,！·#￥%……—*（）——、《》，。？'"]/g;

/******************************************************
*以下是对字符串对象（String）的扩展函数，任何String对象都可
*使用这些函数，例如：
var str = " dslf dsf  sfd  ";
alert(str.trim());  //显示这样的字符串"dslf dsf  sfd"
alert(str.deleteSpace());  //显示这样的字符串"dslfdsfsfd"
******************************************************/
 
/*
        function:在字符串左边补指定的字符串
        parameter：
                countLen:结果字符串的长度
                addStr:要附加的字符串
        return:处理后的字符串
*/
String.prototype.lpad = function(countLen,addStr)
{
        // 如果countLen不是数字，不处理返回
        if(isNaN(countLen))return this;

        // 初始字符串长度大于指定的长度，则不需处理
        if(initStr.length >= countLen)return this;

        var initStr = this;        // 初始字符串
        var tempStr = new String();        // 临时字符串

        for(;;)
        {
                tempStr += addStr;
                if(tempStr.length >= countLen - initStr.length)
                {
                        tempStr = tempStr.substring(0,countLen - initStr.length);
                        break;
                }
        }
        return tempStr + initStr;
}


/*
        function:在字符串右边补指定的字符串
        parameter：
                countLen:结果字符串的长度
                addStr:要附加的字符串
        return:处理后的字符串
*/
String.prototype.rpad = function(countLen,addStr)
{
        // 如果countLen不是数字，不处理返回
        if(isNaN(countLen))return this;

        // 初始字符串长度大于指定的长度，则不处理返回
        if(initStr.length >= countLen)return this;

        var initStr = this;        // 初始字符串

        for(;;)
        {
                initStr += addStr;
                if(initStr.length >= countLen)
                {
                        initStr = initStr.substring(0,countLen);
                        break;
                }
        }
        return initStr;
}

/*
        function: 去掉字符串中所有的空格
        return: 处理后的字符串
*/
String.prototype.atrim = function()
{
    // 用正则表达式将右边空格用空字符串替代。
    return this.replace(/(\s+)|(　+)/g, "");
}

// 增加一个名为 trim 的函数作为
// String 构造函数的原型对象的一个方法。
String.prototype.trim = function()
{
    // 用正则表达式将前后空格用空字符串替代。
    return this.replace(/(^\s+)|(\s+$)|(^　+)|(　+$)/g, "");
}

/*
        function:去掉字符串左边的空格
        return:处理后的字符串
*/
String.prototype.ltrim = function()
{
        return this.replace(/(^\s+)|(^　+)/g,"");
}


/*
        function:去掉字符串右边的空格
        return:处理后的字符串
*/
String.prototype.rtrim = function()
{
        return this.replace(/(\s+$)|(　+$)/g,"");
}


/*
        function:获得字符串的字节数
        return:字节数
        example:"test测试".getByte值为8
*/
String.prototype.getByte = function()
{
        var intCount = 0;
        for(var i = 0;i < this.length;i ++)
        {
            // Ascii码大于255是双字节的字符
            var ascii = this.charCodeAt(i).toString(16);
            var byteNum = ascii.length / 2.0;
            intCount += (byteNum + (ascii.length % 2) / 2);
        }
        return intCount;
}

/*
        function: 指定字符集半角字符全部转变为对应的全角字符
        parameter:
                dbcStr: 要转换的半角字符集合
        return: 转换后的字符串
*/
String.prototype.dbcToSbc = function(dbcStr)
{
        var resultStr = this;

        for(var i = 0;i < this.length;i ++)
        {
                switch(dbcStr.charAt(i))
                {
                        case ",":
                                resultStr = resultStr.replace(/\,/g,"，"); 
                                break;
                        case "!":
                                resultStr = resultStr.replace(/\!/g,"！"); 
                                break;
                        case "#":
                                resultStr = resultStr.replace(/\#/g,"＃"); 
                                break;
                        case "|":
                                resultStr = resultStr.replace(/\|/g,"|"); 
                                break;
                        case ".":
                                resultStr = resultStr.replace(/\./g,"。"); 
                                break;
                        case "?":
                                resultStr = resultStr.replace(/\?/g,"？"); 
                                break;
                        case ";":
                                resultStr = resultStr.replace(/\;/g,"；"); 
                                break;
                }
        }
        return resultStr;
}

//获取字符串按字节数指定的字串
String.prototype.bytesubstr = function(index1,index2)
{
        var resultStr = "";
        var byteCount = 0;
        for(var i = index1;i < index2;i ++)
        {
                if(i > this.length)break;
                if(this.charCodeAt(i) > 255)byteCount += 2;
                else byteCount += 1;
                if(byteCount > (index2 - index1))break;
                resultStr += this.charAt(i);
        }
        return resultStr;
}

//判断字符串是否是数字字符串，若是则返回true，否则返回false
String.prototype.isNumber = function() {
	return (this.isInt() || this.isFloat());
}
//判断字符串是否是浮点数字符串，若是则返回true，否则返回false
String.prototype.isFloat = function() {
	return /^(?:-?|\+?)\d*\.\d+$/g.test(this);
}
//判断字符串是否是整数字符串，若是则返回true，否则返回false
String.prototype.isInt = function() {
	return /^(?:-?|\+?)\d+$/g.test(this);
}
//判断字符串是否是正数字符串，若是正数则返回true，否则返回false
String.prototype.isPlus = function() {
	return this.isPlusInt() || this.isPlusFloat();
}
//判断字符串是否是正浮点数字符串，若是正数则返回true，否则返回false
String.prototype.isPlusFloat = function() {
	return /^\+?\d*\.\d+$/g.test(this);
}
//判断字符串是否是正整数字符串，若是正数则返回true，否则返回false
String.prototype.isPlusInt = function() {
	return /^\+?\d+$/g.test(this);
}
//判断字符串是否是负数字符串，若是正数则返回true，否则返回false
String.prototype.isMinus = function() {
	return this.isMinusInt() || this.isMinusFloat();
}
//判断字符串是否是负浮点数字符串，若是正数则返回true，否则返回false
String.prototype.isMinusFloat = function() {
	return /^-\d*\.\d+$/g.test(this);
}
//判断字符串是否是负整数字符串，若是正数则返回true，否则返回false
String.prototype.isMinusInt = function() {
	return /^-\d+$/g.test(this);
}

//判断字符串是否只包含单词字符，若是则返回true，否则返回false
String.prototype.isLeastCharSet = function() {
	return !(/[^A-Za-z0-9_]/g.test(this));
}
//判断字符串是否是Email字符串，若是则返回true，否则返回false
String.prototype.isEmail = function() {
	return /^\w+@.+\.\w+$/g.test(this);
}
//判断字符串是否是邮政编码字符串，若是则返回true，否则返回false
String.prototype.isZip = function() {
	return /^\d{6}$/g.test(this);
}
//判断字符串是否是固定电话号码字符串，若是则返回true，否则返回false
String.prototype.isFixedTelephone = function() {
	return /^(\d{2,4}-)?((\(\d{3,5}\))|(\d{3,5}-))?\d{3,18}(-\d+)?$/g.test(this);
}
//判断字符串是否是手机电话号码字符串，若是则返回true，否则返回false
String.prototype.isMobileTelephone = function() {
	return /^13\d{9}$/g.test(this);
}
//判断字符串是否是电话号码字符串，若是则返回true，否则返回false
String.prototype.isTelephone = function() {
	return this.isMobileTelephone() || this.isFixedTelephone();
}
//判断字符串是否是日期字符串，若是则返回true，否则返回false
String.prototype.isDate = function() {
	return /^\d{1,4}-(?:(?:(?:0?[1,3,5,7,8]|1[0,2])-(?:0?[1-9]|(?:1|2)[0-9]|3[0-1]))|(?:(?:0?[4,6,9]|11)-(?:0?[1-9]|(?:1|2)[0-9]|30))|(?:(?:0?2)-(?:0?[1-9]|(?:1|2)[0-9])))$/g.test(this);
}
//判断字符串是否是时间字符串，若是则返回true，否则返回false
String.prototype.isTime = function() {
	return /^(?:(?:0?|1)[0-9]|2[0-3]):(?:(?:0?|[1-5])[0-9]):(?:(?:0?|[1-5])[0-9]).(?:\d{1,3})$/g.test(this);
}
//判断字符串是否是日期时间字符串，若是则返回true，否则返回false
String.prototype.isDateTime = function() {
	return /^\d{1,4}-(?:(?:(?:0?[1,3,5,7,8]|1[0,2])-(?:0?[1-9]|(?:1|2)[0-9]|3[0-1]))|(?:(?:0?[4,6,9]|11)-(?:0?[1-9]|(?:1|2)[0-9]|30))|(?:(?:0?2)-(?:0?[1-9]|(?:1|2)[0-9]))) +(?:(?:0?|1)[0-9]|2[0-3]):(?:(?:0?|[1-5])[0-9]):(?:(?:0?|[1-5])[0-9]).(?:\d{1,3})$/g.test(this);
}
//比较日期字符串，若相等则返回0，否则返回当前日期字符串和目标字符串之间相差的毫秒数，若其中一个字符串不符合日期或日期时间格式，则返回null
String.prototype.compareDate = function(target) {
	var thisDate = this.toDate();
	var targetDate = target.toDate();
	if (thisDate == null || targetDate == null) {
		return null;
	}
	else {
		return thisDate.getTime() - targetDate.getTime();
	}
}
//判断日期字符串指定的时期是否是当前日期，若是则返回true，否则返回false
String.prototype.isToday = function() {
	return this.trim().split(' ')[0].compareDate(getSysDate()) == 0;
}
//判断日期字符串指定的时期是否是当前日期之前，若是则返回true，否则返回false
String.prototype.isBeforeDate = function(baseDate) {
	if (baseDate == null) {
		baseDate = getSysDate();
	}
	return this.trim().split(' ')[0].compareDate(baseDate.trim().split(' ')[0]) < 0;
}
//判断日期字符串指定的时期是否是当前日期之后，若是则返回true，否则返回false
String.prototype.isAfterDate = function(baseDate) {
	if (baseDate == null) {
		baseDate = getSysDate();
	}
	return this.trim().split(' ')[0].compareDate(baseDate.trim().split(' ')[0]) > 0;
}

//判断日期时间字符串指定的时期是否是指定日期时间之前，若是则返回true，否则返回false
String.prototype.isBeforeDateTime = function(baseDateTime) {
	if (baseDateTime == null) {
		baseDateTime = getSysDateTime();
	}
	return this.trim().compareDate(baseDateTime.trim()) < 0;
}
//判断日期时间字符串指定的时期是否是指定日期时间之后，若是则返回true，否则返回false
String.prototype.isAfterDateTime = function(baseDateTime) {
	if (baseDateTime == null) {
		baseDateTime = getSysDateTime();
	}
	return this.trim().compareDate(baseDateTime.trim()) > 0;
}



//判断字符串中是否含有特殊字符，若有则返回true，否则返回false
String.prototype.hasSpecialChar = function() {
	specialChars.test('');
	return specialChars.test(this);
}
//删除字符串中的空格
String.prototype.deleteSpace = function() {
	return this.replace(/( +)|(　+)/g, '');
}
//删除字符串中指定的字符串
String.prototype.remove = function(str) {
	if (str == null || str == '') {
		return this;
	}
	return this.replace(str.toRegExp('g'), '');
}
//将字符串中包含的find字符串替换成target字符串，返回替换后的结果字符串
String.prototype.replaceByString = function(find, target) {
	return this.replace(find.toRegExp('g'), target);
}
//将字符串转换成相应的正则表达式
String.prototype.toRegExp = function(regType) {
	if (regType == null || regType.trim() == '') {
		regType = 'g';
	}
	var find = ['\\\\', '\\$', '\\(', '\\)', '\\*', '\\+', '\\.', '\\[', '\\]', '\\?', '\\^', '\\{', '\\}', '\\|', '\\/'];
	var str = this;
	for (var i = 0; i < find.length; i++) {
		str = str.replace(new RegExp(find[i], 'g'), find[i]);
	}
	return new RegExp(str, regType);
}
//将字符串转换成Date对象，要求字符串必须符合日期或日期时间格式，否则返回null
String.prototype.toDate = function() {
	if (this.isDate()) {
		var data = this.split('-');
		return new Date(parseInt(data[0].replace(/^0+/g, '')), parseInt(data[1].replace(/^0+/g, '')) - 1, parseInt(data[2].replace(/^0+/g, '')));
	}
	else if (this.isDateTime()) {
		var data = this.split(' ');
		var date = data[0].split('-');
		var time = data[1].split(".")[0].split(':');
		return new Date(parseInt(date[0].replace(/^0+/g, '')), parseInt(date[1].replace(/^0+/g, '')) - 1, parseInt(date[2].replace(/^0+/g, '')), 
			parseInt(time[0].replace(/^0+/g, '')), parseInt(time[1].replace(/^0+/g, '')), parseInt(time[2].replace(/^0+/g, '')));
	}
	else {
		return null;
	}
}
//将字符串按HTML需要的编码方式编码
String.prototype.encodeHtml = function() {
	var strToCode = this.replace(/</g,"&lt;");
	strToCode = strToCode.replace(/>/g,"&gt;");
	return strToCode;
}
//将字符串按HTML需要的编码方式解码
String.prototype.decodeHtml = function() {
	var strToCode = this.replace(/&lt;/g,"<");
	strToCode = strToCode.replace(/&gt;/g,">");
	return strToCode;
}
/*********************************************
*字符串对象（String）扩展函数结束
*********************************************/