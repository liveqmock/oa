var sendToId = "sendto";
var sendccId = "sendcc";
var sendbccId = "sendbcc";
//����ǰ����ַ�(true)����ͼ��(false)
var showChar = false;

var selSendValue = new Array();
var selccValue = new Array();
var selbccValue = new Array();
//�ֱ���ѡ���з��ͣ����ͣ�������ѡ���
var selSend = new Array();
var selcc = new Array();
var selbcc =new Array();

//�õ�����ѡ����Ϣ
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
//�������ѡ����Ϣ
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
 * ȡ��ѡ��
 */
function removeSel(obj,type){
	var selIndex = getArray(obj,type);
	if (selIndex != -1){
		getSelObj(type)[selIndex] = "";
	}
}
//ѡ��
function addSel(obj,type){
	if (getArray(obj,type) == -1){
		getSelObj(type)[getSelObj(type).length] = obj;
	}
}
//ɾ��ѡ���һ����
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
//�õ�ѡ�����͵Ķ��󣨷��ͣ����ͣ����ͣ�
function getSelObj(type){
	if (type == 1){
		return selcc;
	}else if (type ==2){
		return selbcc;
	}else{
		return selSend;
	}
}
//�õ��ƶ��Ķ���
function getArray(obj,type){
	for(var i=0;i<getSelObj(type).length;i++){
		if (getSelObj(type)[i] == obj){
			return i;
		}
	}
	return -1;
}
//����ѡ�������
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
		var ok = confirm("Ⱥ���������ܴ���"+broadcastnum+",����ȡǰ"+broadcastnum+"����");
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
	 	//allName += nameValue + "ͼ|";
	 	/*brSize = tempStr.split("<br>").length ;
	 	if ((getByte(allName)/70)>brSize && (i+1)<selValue.length){
	 		tempStr +="<br>";
	 	}*/
	 	if (showChar){
	 		var char = "m";
	 		if (type == "00"){
				char = "B";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"������֯��&#13;&#10;" + selValue[i]["department"] + "\"><font face=\"Webdings\" color=\"#009900\">" + char + "</font>" + nameValue + "</span>";
			}else if (type == "01"){
				char = "H";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"������֯��&#13;&#10;" + selValue[i]["department"] + "\"><font face=\"Webdings\" color=\"#009900\">" + char + "</font>" + nameValue + "</span>";
			}else{
		 		tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["uuid"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"������֯��&#13;&#10;" + selValue[i]["department"] + "\"><font face=\"Webdings\" color=\"#009900\">" + char + "</font>" + nameValue + "</span>";
	 		}
	 	}else{
		 	if (type == "00"){
				typeImg = "commongroup.gif";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"��������\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
			}else if (type == "01"){
				typeImg = "indigroup.gif";
				tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"���˷���\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
			}else{
		 		tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" uuid=\"" + selValue[i]["uuid"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"������֯��&#13;&#10;" + selValue[i]["department"] + "\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
			}
		}
		//����ʾ��ѡ����׼������
		if (i>0){
			showName += ",";
		}
		showName += selValue[i]["name"];
	}
	//setShow(sendType,showName);

		ele.innerHTML = tempStr;
	
	
	changepnum();
}
//���¼�������
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
	 	allName += nameValue + "ͼ|";
	 	brSize = tempStr.split("<br>").length ;
	 	if ((getByte(allName)/70)>brSize && (i+1)<selValue.length){
	 		tempStr +="<br>";
	 	}
	 	if (type == "00"){
			typeImg = "commongroup.gif";
			tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"��������\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
		}else if (type == "01"){
			typeImg = "indigroup.gif";
			tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"���˷���\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
		}else{
	 		tempStr +="<span class=\"send\" personType=\"" + selValue[i]["type"] + "\" department=\"" + selValue[i]["department"] + "\" personName=\"" + selValue[i]["name"] + "\"  person=\"" + selValue[i]["value"] + "\" onclick=\"selectName(this," + sendType + ");\" title=\"������֯��&#13;&#10;" + selValue[i]["department"] + "\"><img src=\"" + urlPath + "/images/" + typeImg + "\">" + nameValue + "</span>";
		}*/
		//����ʾ��ѡ����׼������
		if (i>0){
			showName += ",";
		}
		showName += selValue[i]["name"];
	}
	setShow(sendType,showName);
	//ele.innerHTML = tempStr;
	
}
/**
 * �õ������ƶ����͵����ݣ����޸����޸ĵ�����
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
 * �õ������ƶ�
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
 * �������˵���Ϣ����������
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
 * �����ݷŵ�������Ȼ���ύ
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
 * ͬ����ʾѡ����ˣ�ҳ�����·���
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
 * ����ַ������ֽ���
 */
function getByte(str){
    var intCount = 0;
    for(var i = 0;i < str.length;i ++){
        // Ascii�����255��˫�ֽڵ��ַ�
        var ascii = str.charCodeAt(i).toString(16);
        var byteNum = ascii.length / 2.0;
        intCount += (byteNum + (ascii.length % 2) / 2);
    }
    return intCount;
}
/**
 * չ��/�۵��ʼ���Ա�б�
 */
function listState(obj,type,toBook){

	var str = "�ռ����б�";
	var sendId = sendToId;
	if (type == 1){
		sendId = sendccId;
		str = "�����б�";
	}else if (type == 2){
		sendId = sendbccId;
		str = "�����б�";
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
		obj.alt = "չ��";
		obj.title = "չ��" + str;
		obj.src = urlPath + "/images/outspread.gif";
	}else{
		if (ele.innerHTML == ""){
			ele.style.height = "22px";
		}else{
			ele.style.height = "100%";
		}
		
		ele.setAttribute("state","1");
		obj.alt = "����";
		obj.title = "����" + str;
		obj.src = urlPath + "/images/pucker.gif";
	}
	document.location.href = "#" + toBook;
}





















//ϵͳ����������ַ���
var specialChars = /[!@#\$%\^&\*\(\)\{\}\[\]<>_\+\|~`-]|[=\/\\\?;:,����#��%������*��������������������'"]/g;

/******************************************************
*�����Ƕ��ַ�������String������չ�������κ�String���󶼿�
*ʹ����Щ���������磺
var str = " dslf dsf  sfd  ";
alert(str.trim());  //��ʾ�������ַ���"dslf dsf  sfd"
alert(str.deleteSpace());  //��ʾ�������ַ���"dslfdsfsfd"
******************************************************/
 
/*
        function:���ַ�����߲�ָ�����ַ���
        parameter��
                countLen:����ַ����ĳ���
                addStr:Ҫ���ӵ��ַ���
        return:�������ַ���
*/
String.prototype.lpad = function(countLen,addStr)
{
        // ���countLen�������֣���������
        if(isNaN(countLen))return this;

        // ��ʼ�ַ������ȴ���ָ���ĳ��ȣ����账��
        if(initStr.length >= countLen)return this;

        var initStr = this;        // ��ʼ�ַ���
        var tempStr = new String();        // ��ʱ�ַ���

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
        function:���ַ����ұ߲�ָ�����ַ���
        parameter��
                countLen:����ַ����ĳ���
                addStr:Ҫ���ӵ��ַ���
        return:�������ַ���
*/
String.prototype.rpad = function(countLen,addStr)
{
        // ���countLen�������֣���������
        if(isNaN(countLen))return this;

        // ��ʼ�ַ������ȴ���ָ���ĳ��ȣ��򲻴�����
        if(initStr.length >= countLen)return this;

        var initStr = this;        // ��ʼ�ַ���

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
        function: ȥ���ַ��������еĿո�
        return: �������ַ���
*/
String.prototype.atrim = function()
{
    // ��������ʽ���ұ߿ո��ÿ��ַ��������
    return this.replace(/(\s+)|(��+)/g, "");
}

// ����һ����Ϊ trim �ĺ�����Ϊ
// String ���캯����ԭ�Ͷ����һ��������
String.prototype.trim = function()
{
    // ��������ʽ��ǰ��ո��ÿ��ַ��������
    return this.replace(/(^\s+)|(\s+$)|(^��+)|(��+$)/g, "");
}

/*
        function:ȥ���ַ�����ߵĿո�
        return:�������ַ���
*/
String.prototype.ltrim = function()
{
        return this.replace(/(^\s+)|(^��+)/g,"");
}


/*
        function:ȥ���ַ����ұߵĿո�
        return:�������ַ���
*/
String.prototype.rtrim = function()
{
        return this.replace(/(\s+$)|(��+$)/g,"");
}


/*
        function:����ַ������ֽ���
        return:�ֽ���
        example:"test����".getByteֵΪ8
*/
String.prototype.getByte = function()
{
        var intCount = 0;
        for(var i = 0;i < this.length;i ++)
        {
            // Ascii�����255��˫�ֽڵ��ַ�
            var ascii = this.charCodeAt(i).toString(16);
            var byteNum = ascii.length / 2.0;
            intCount += (byteNum + (ascii.length % 2) / 2);
        }
        return intCount;
}

/*
        function: ָ���ַ�������ַ�ȫ��ת��Ϊ��Ӧ��ȫ���ַ�
        parameter:
                dbcStr: Ҫת���İ���ַ�����
        return: ת������ַ���
*/
String.prototype.dbcToSbc = function(dbcStr)
{
        var resultStr = this;

        for(var i = 0;i < this.length;i ++)
        {
                switch(dbcStr.charAt(i))
                {
                        case ",":
                                resultStr = resultStr.replace(/\,/g,"��"); 
                                break;
                        case "!":
                                resultStr = resultStr.replace(/\!/g,"��"); 
                                break;
                        case "#":
                                resultStr = resultStr.replace(/\#/g,"��"); 
                                break;
                        case "|":
                                resultStr = resultStr.replace(/\|/g,"|"); 
                                break;
                        case ".":
                                resultStr = resultStr.replace(/\./g,"��"); 
                                break;
                        case "?":
                                resultStr = resultStr.replace(/\?/g,"��"); 
                                break;
                        case ";":
                                resultStr = resultStr.replace(/\;/g,"��"); 
                                break;
                }
        }
        return resultStr;
}

//��ȡ�ַ������ֽ���ָ�����ִ�
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

//�ж��ַ����Ƿ��������ַ����������򷵻�true�����򷵻�false
String.prototype.isNumber = function() {
	return (this.isInt() || this.isFloat());
}
//�ж��ַ����Ƿ��Ǹ������ַ����������򷵻�true�����򷵻�false
String.prototype.isFloat = function() {
	return /^(?:-?|\+?)\d*\.\d+$/g.test(this);
}
//�ж��ַ����Ƿ��������ַ����������򷵻�true�����򷵻�false
String.prototype.isInt = function() {
	return /^(?:-?|\+?)\d+$/g.test(this);
}
//�ж��ַ����Ƿ��������ַ��������������򷵻�true�����򷵻�false
String.prototype.isPlus = function() {
	return this.isPlusInt() || this.isPlusFloat();
}
//�ж��ַ����Ƿ������������ַ��������������򷵻�true�����򷵻�false
String.prototype.isPlusFloat = function() {
	return /^\+?\d*\.\d+$/g.test(this);
}
//�ж��ַ����Ƿ����������ַ��������������򷵻�true�����򷵻�false
String.prototype.isPlusInt = function() {
	return /^\+?\d+$/g.test(this);
}
//�ж��ַ����Ƿ��Ǹ����ַ��������������򷵻�true�����򷵻�false
String.prototype.isMinus = function() {
	return this.isMinusInt() || this.isMinusFloat();
}
//�ж��ַ����Ƿ��Ǹ��������ַ��������������򷵻�true�����򷵻�false
String.prototype.isMinusFloat = function() {
	return /^-\d*\.\d+$/g.test(this);
}
//�ж��ַ����Ƿ��Ǹ������ַ��������������򷵻�true�����򷵻�false
String.prototype.isMinusInt = function() {
	return /^-\d+$/g.test(this);
}

//�ж��ַ����Ƿ�ֻ���������ַ��������򷵻�true�����򷵻�false
String.prototype.isLeastCharSet = function() {
	return !(/[^A-Za-z0-9_]/g.test(this));
}
//�ж��ַ����Ƿ���Email�ַ����������򷵻�true�����򷵻�false
String.prototype.isEmail = function() {
	return /^\w+@.+\.\w+$/g.test(this);
}
//�ж��ַ����Ƿ������������ַ����������򷵻�true�����򷵻�false
String.prototype.isZip = function() {
	return /^\d{6}$/g.test(this);
}
//�ж��ַ����Ƿ��ǹ̶��绰�����ַ����������򷵻�true�����򷵻�false
String.prototype.isFixedTelephone = function() {
	return /^(\d{2,4}-)?((\(\d{3,5}\))|(\d{3,5}-))?\d{3,18}(-\d+)?$/g.test(this);
}
//�ж��ַ����Ƿ����ֻ��绰�����ַ����������򷵻�true�����򷵻�false
String.prototype.isMobileTelephone = function() {
	return /^13\d{9}$/g.test(this);
}
//�ж��ַ����Ƿ��ǵ绰�����ַ����������򷵻�true�����򷵻�false
String.prototype.isTelephone = function() {
	return this.isMobileTelephone() || this.isFixedTelephone();
}
//�ж��ַ����Ƿ��������ַ����������򷵻�true�����򷵻�false
String.prototype.isDate = function() {
	return /^\d{1,4}-(?:(?:(?:0?[1,3,5,7,8]|1[0,2])-(?:0?[1-9]|(?:1|2)[0-9]|3[0-1]))|(?:(?:0?[4,6,9]|11)-(?:0?[1-9]|(?:1|2)[0-9]|30))|(?:(?:0?2)-(?:0?[1-9]|(?:1|2)[0-9])))$/g.test(this);
}
//�ж��ַ����Ƿ���ʱ���ַ����������򷵻�true�����򷵻�false
String.prototype.isTime = function() {
	return /^(?:(?:0?|1)[0-9]|2[0-3]):(?:(?:0?|[1-5])[0-9]):(?:(?:0?|[1-5])[0-9]).(?:\d{1,3})$/g.test(this);
}
//�ж��ַ����Ƿ�������ʱ���ַ����������򷵻�true�����򷵻�false
String.prototype.isDateTime = function() {
	return /^\d{1,4}-(?:(?:(?:0?[1,3,5,7,8]|1[0,2])-(?:0?[1-9]|(?:1|2)[0-9]|3[0-1]))|(?:(?:0?[4,6,9]|11)-(?:0?[1-9]|(?:1|2)[0-9]|30))|(?:(?:0?2)-(?:0?[1-9]|(?:1|2)[0-9]))) +(?:(?:0?|1)[0-9]|2[0-3]):(?:(?:0?|[1-5])[0-9]):(?:(?:0?|[1-5])[0-9]).(?:\d{1,3})$/g.test(this);
}
//�Ƚ������ַ�����������򷵻�0�����򷵻ص�ǰ�����ַ�����Ŀ���ַ���֮�����ĺ�������������һ���ַ������������ڻ�����ʱ���ʽ���򷵻�null
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
//�ж������ַ���ָ����ʱ���Ƿ��ǵ�ǰ���ڣ������򷵻�true�����򷵻�false
String.prototype.isToday = function() {
	return this.trim().split(' ')[0].compareDate(getSysDate()) == 0;
}
//�ж������ַ���ָ����ʱ���Ƿ��ǵ�ǰ����֮ǰ�������򷵻�true�����򷵻�false
String.prototype.isBeforeDate = function(baseDate) {
	if (baseDate == null) {
		baseDate = getSysDate();
	}
	return this.trim().split(' ')[0].compareDate(baseDate.trim().split(' ')[0]) < 0;
}
//�ж������ַ���ָ����ʱ���Ƿ��ǵ�ǰ����֮�������򷵻�true�����򷵻�false
String.prototype.isAfterDate = function(baseDate) {
	if (baseDate == null) {
		baseDate = getSysDate();
	}
	return this.trim().split(' ')[0].compareDate(baseDate.trim().split(' ')[0]) > 0;
}

//�ж�����ʱ���ַ���ָ����ʱ���Ƿ���ָ������ʱ��֮ǰ�������򷵻�true�����򷵻�false
String.prototype.isBeforeDateTime = function(baseDateTime) {
	if (baseDateTime == null) {
		baseDateTime = getSysDateTime();
	}
	return this.trim().compareDate(baseDateTime.trim()) < 0;
}
//�ж�����ʱ���ַ���ָ����ʱ���Ƿ���ָ������ʱ��֮�������򷵻�true�����򷵻�false
String.prototype.isAfterDateTime = function(baseDateTime) {
	if (baseDateTime == null) {
		baseDateTime = getSysDateTime();
	}
	return this.trim().compareDate(baseDateTime.trim()) > 0;
}



//�ж��ַ������Ƿ��������ַ��������򷵻�true�����򷵻�false
String.prototype.hasSpecialChar = function() {
	specialChars.test('');
	return specialChars.test(this);
}
//ɾ���ַ����еĿո�
String.prototype.deleteSpace = function() {
	return this.replace(/( +)|(��+)/g, '');
}
//ɾ���ַ�����ָ�����ַ���
String.prototype.remove = function(str) {
	if (str == null || str == '') {
		return this;
	}
	return this.replace(str.toRegExp('g'), '');
}
//���ַ����а�����find�ַ����滻��target�ַ����������滻��Ľ���ַ���
String.prototype.replaceByString = function(find, target) {
	return this.replace(find.toRegExp('g'), target);
}
//���ַ���ת������Ӧ��������ʽ
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
//���ַ���ת����Date����Ҫ���ַ�������������ڻ�����ʱ���ʽ�����򷵻�null
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
//���ַ�����HTML��Ҫ�ı��뷽ʽ����
String.prototype.encodeHtml = function() {
	var strToCode = this.replace(/</g,"&lt;");
	strToCode = strToCode.replace(/>/g,"&gt;");
	return strToCode;
}
//���ַ�����HTML��Ҫ�ı��뷽ʽ����
String.prototype.decodeHtml = function() {
	var strToCode = this.replace(/&lt;/g,"<");
	strToCode = strToCode.replace(/&gt;/g,">");
	return strToCode;
}
/*********************************************
*�ַ�������String����չ��������
*********************************************/