<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.icss.oa.mail.handler.TimeUtil"%>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupinfoVO"%>
<%@	page import="com.icss.oa.address.vo.SysPersonVO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.oa.util.PropertyManager"%>
<%
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy��MM��dd��")
			+ TimeUtil.getCurrentWeek();
	SendFileBean sendFileBean = SendFileBean
			.getInstanceFromSession(session);

	String sun_flag = (String)request.getSession().getAttribute("sun_flag");
	if (sun_flag == null) {
		sun_flag = "";
	}
	String broadcastnum = PropertyManager.getProperty("broadcastnum");
%>

<html>
	<head>

		<title>�»���칫��Ϣ��ϵͳ</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<link href="<%=path%>/mail/css/Attach.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/Style/css_grey.css" rel="stylesheet"
			type="text/css" id="homepagestyle" />
		<LINK href="<%=request.getContextPath()%>/include/address.css"
			rel=stylesheet>

		<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
		<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
		<SCRIPT language=JavaScript src="<%=path%>/include/AttachFile.js"></SCRIPT>
		<script type="text/javascript" src="<%=path%>/mail/js/Attach.js"></script>
		<script type="text/javascript" src="<%=path%>/mail/js/suggest.js"></script>

		<script type="text/javascript"
			src="<%=path%>/mail/js/jquery-1.2.6.pack.js"></script>
		<script type="text/javascript"
			src="<%=path%>/mail/js/jquery.autocomplete.js"></script>
		<link rel="stylesheet"
			href="<%=path%>/mail/css/jquery.autocomplete.css" type="text/css" />

		<script language="javascript">

var broadcastnum = '<%=broadcastnum%>';

function _updown(id,tr){
	 var obj=document.getElementById(id);
	 var visableobj=document.getElementById(tr);
   if(searchStr(obj.src,"mail_up.jpg")){
			 obj.src='<%=path%>/images/mail_down.jpg';
			visableobj.style.display="block";
	 }else{
		  obj.src='<%=path%>/images/mail_up.jpg';
		  visableobj.style.display="none";
	 }
	frameautoheight();
}

function searchStr(str1,str2){   
   if(str1.search(str2)>=0)   
        return   true;      
        else   
        return   false;    
}   



function _selectOpen(){
	var swidth = window.screen.width;
	var sheight = window.screen.height;
	var width = 800;
	var height = 600;
	var top = (sheight-height)/2 - 30;
	if (top <0){
		top = 0;
	}
	var left = (swidth-width)/2;
	window.open('<%=request.getContextPath()%>/address/sendfile/selectPersonFrame.jsp','addressbook','width='+width+',height='+height+',left='+left+',top='+top+',scrollbars=yes,resizable=yes');
}
function _selectPerson(){
	document.sendForm.sendType.value = "0";
	_selectOpen();
}
function _selectcc(){
	document.sendForm.sendType.value = "1";
	_selectOpen();
}

function _selectbcc(){
	document.sendForm.sendType.value = "2";
	_selectOpen();
}

function _addPerson_hidden(usernamestring){
	document.sendForm.sendto.value = usernamestring;
	changepnum();
	return true;	
}

function _addcc(usernamestring){
	document.sendForm.sendcc.value = usernamestring;
	changepnum();
	return true;	
}

function _addbcc(usernamestring){
	document.sendForm.sendbcc.value = usernamestring;
	changepnum();
	return true;
}

function _addPerson_hidden1(user_uuid){
	document.sendForm.addPerson.value = user_uuid;
}
function _addcc1(user_uuid){
	document.sendForm.addcc.value = user_uuid;
}

function _addbcc1(user_uuid){
	document.sendForm.addbcc.value = user_uuid;
}

   



function changepnum(){
	var obj = document.getElementById("sendto");
	var obj1 = document.getElementById("sendcc");
	var obj2 = document.getElementById("sendbcc");
	var sendnum = obj.getElementsByTagName("span").length;
	var ccnum = obj1.getElementsByTagName("span").length;
	var bccnum = obj2.getElementsByTagName("span").length;
	var showto = document.getElementById("totitle");
	var showcc = document.getElementById("cctitle");
	var showbcc = document.getElementById("bcctitle");
	if(sendnum != 0){
		showto.innerHTML =  "("+sendnum+")";
	}else{
		showto.innerHTML = "";
	}
	if(ccnum != 0){
		showcc.innerHTML = "("+ccnum+")";
	}else{
		showcc.innerHTML = "";
	}
	if(bccnum != 0){
		showbcc.innerHTML = "("+bccnum+")";
	}else{
		showbcc.innerHTML = "";
	}
	
}

function checkmax(){
	var obj = document.getElementById("sendto");
	var obj1 = document.getElementById("sendcc");
	var obj2 = document.getElementById("sendbcc");
	var num = obj.getElementsByTagName("span").length + obj1.getElementsByTagName("span").length + obj2.getElementsByTagName("span").length;
	if(num == <%=broadcastnum%>){
		alert("Ⱥ���������ܴ���<%=broadcastnum%>");
		return false;
	}
	return true;
}


function check(){
	if(!setPersonToSend()){
		alert("����д�ռ���");
		return false;
	}
	
	if(document.sendForm.topic.value == ""){
		if(confirm("��û����ӱ��⣡��ȷ���ԡ��ޱ��⡯��Ϊ���ı�����"))
		{ 
			document.sendForm.topic.value = "������";
		  	return true;
		};
		return false;
	} 
	if(document.sendForm.content.value.length>500){
		alert("����������500����");
		return false;
	}
	return true;
}

function MM_findObj(n, d) { //v4.01
	var p,i,x;  
	if(!d) d=document; 
	if((p=n.indexOf("?"))>0&&parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
	}
	if(!(x=d[n])&&d.all) x=d.all[n]; 
	for (i=0;!x&&i<d.forms.length;i++) 
		x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
		x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); 
	return x;
}

function _showAlertBox() { //v6.0
	var i,p,v,obj,
	args=_showAlertBox.arguments;
	for (i=0; i<(args.length-2); i+=3) {
		if ((obj=MM_findObj(args[i]))!=null) { 
			v=args[i+2];
			if (obj.style) { 
				obj=obj.style; 
				v=(v=='show')?'visible':(v=='hide')?'hidden':v; 
			}
			obj.visibility=v; 
		}
	}
}

function mailsend(){
	if(check()){
		window.showAlert.innerHTML = "���ڷ����ļ������Ե�......";
		_showAlertBox('alertbox1','','show');
		var mail_title;
		var mail_context;
		mail_context=document.sendForm.content.value;
		
		mail_title=document.sendForm.topic.value;
		setCookie("mail_title",mail_title);
		setCookie("mail_context",mail_context);

		document.sendForm.action = "<%=path%>/servlet/SendFile1Servlet?sun_flag = <%=sun_flag%>";
		document.sendForm.submit();
	}
}

function _attachfile(){
	getSendHtml();
	document.sendForm.action="<%=path%>/servlet/SendFileAttachFileServlet?"
	document.sendForm.donext.value="1";
	document.sendForm.submit();
}

function savetodraft(){
	if(check()){
		window.showAlert.innerHTML = "���ڱ����ļ������Ե�......";
		_showAlertBox('alertbox1','','show');
		document.sendForm.action = "<%=path%>/servlet/SaveToDraft1Servlet";
		document.sendForm.submit();
	}
}



function _showccspan(){
	var cctr =document.getElementById("cctr");
	var cchref =document.getElementById("cchref");
	var sendcc =document.getElementById("sendcc");
	cctr.style.display = "block";
	cchref.style.display = "none";
	bound();
	_choosespan(sendcc);
	frameautoheight();
}

function _hideccspan(){
	var cctr =document.getElementById("cctr");
	var cchref =document.getElementById("cchref");
	cctr.style.display = "none";
	cchref.style.display = "block";
	bound();
	frameautoheight();
}
function _showbccspan(){
	var bcctr =document.getElementById("bcctr");
	var bcchref =document.getElementById("bcchref");
	var sendbcc =document.getElementById("sendbcc");
	bcctr.style.display = "block";
	bcchref.style.display = "none";
	bound();
	_choosespan(sendbcc);
	frameautoheight();
}

function _hidebccspan(){
	var bcctr =document.getElementById("bcctr");
	var bcchref =document.getElementById("bcchref");
	bcctr.style.display = "none";
	bcchref.style.display = "block";
	bound();
	frameautoheight();
}

function bound(){
	var cctr =document.getElementById("cctr");
	var bcctr =document.getElementById("bcctr");
	var bound =document.getElementById("bound");
	if(cctr.style.display == "none" && bcctr.style.display == "none"){
		bound.style.display = "block"
	}else{
		bound.style.display = "none"
	}
}
var selectSpan = "sendto";

function _choosespan(obj){
    selectSpan = obj.id;
    //alert(selectSpan);
	if(obj.className == "sendToText"){
		obj.className = "selectText";
		var to = document.getElementById("sendto");
		var cc = document.getElementById("sendcc");
		var bcc = document.getElementById("sendbcc");

		if(to!=obj && to.className == "selectText"){
			to.className = "sendToText";
			return;
		}
		if(cc!=obj && cc.className == "selectText"){
			cc.className = "sendToText";
			return;
		}
		if(bcc!=obj && bcc.className == "selectText"){
			bcc.className = "sendToText";
			return;
		}
	} 
  
}

function _load(){
	
<% if("party".equals(sun_flag)) {%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"���ص�ί\" uuid=\"5b99f307-10257ea8062-e600d7d60b5229058fb97f42553082d6\" person=\"jiguandangwei\"  title=\"������֯��&#13;&#10;���ص�ί\"><font face=\"Webdings\" color=\"#009900\">m</font>���ص�ί,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"���ص�ί\" uuid=\"5b99f307-10257ea8062-e600d7d60b5229058fb97f42553082d6\"  person=\"jiguandangwei\"  title=\"������֯��&#13;&#10;���ص�ί\"><img src=\"/oabase/images/person.gif\">���ص�ί,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  
<%}else if("dwgzjb".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"�»�����\" uuid=\"c398578-11227651554-9849ddd9cda567c722b51e4369c3577a\" person=\"DWGZJB\"  title=\"������֯��&#13;&#10;���ص�ί\"><font face=\"Webdings\" color=\"#009900\">m</font>�»�����,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"�»�����\" uuid=\"c398578-11227651554-9849ddd9cda567c722b51e4369c3577a\"  person=\"DWGZJB\"  title=\"������֯��&#13;&#10;���ص�ί\"><img src=\"/oabase/images/person.gif\">�»�����,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  
<%}else if("jsjzgxs".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"������\" personName=\"ְ������\" uuid=\"5e3c5e3c-129a536f138-1a426a6cdb8e100f49f6a31924b1b927\" person=\"jsjzgxs\"  title=\"������֯��&#13;&#10;������\"><font face=\"Webdings\" color=\"#009900\">m</font>ְ������,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"������\" personName=\"ְ������\" uuid=\"5e3c5e3c-129a536f138-1a426a6cdb8e100f49f6a31924b1b927\" person=\"jsjzgxs\"  title=\"������֯��&#13;&#10;������\"><img src=\"/oabase/images/person.gif\">ְ������,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml; 
<%}else if("hr".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���¾�->�˲ſ�������\" personName=\"����\" uuid=\"fbb7cb-fdb66efd64-bc7c288dcde03ce304bd1b2e370841e8\" person=\"wangfan\"  title=\"������֯��&#13;&#10;���¾�->�˲ſ�������\"><font face=\"Webdings\" color=\"#009900\">m</font>����,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"���¾�->�˲ſ�������\" personName=\"����\" uuid=\"fbb7cb-fdb66efd64-bc7c288dcde03ce304bd1b2e370841e8\" person=\"wangfan\"  title=\"������֯��&#13;&#10;���¾�->�˲ſ�������\"><img src=\"/oabase/images/person.gif\">����,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  
<%}else if("dev".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"������->�칫��Ϣ����\" personName=\"OAϵͳ����Ա\" uuid=\"7deef758-fdb75f4693-8581bd2af19e660037d144f55ca4fb1e\" person=\"dev\"  title=\"������֯��&#13;&#10;������->�칫��Ϣ����\"><font face=\"Webdings\" color=\"#009900\">m</font>OAϵͳ����Ա,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"������->�칫��Ϣ����\" personName=\"OAϵͳ����Ա\" uuid=\"7deef758-fdb75f4693-8581bd2af19e660037d144f55ca4fb1e\" person=\"dev\"  title=\"������֯��&#13;&#10;������->�칫��Ϣ����\"><img src=\"/oabase/images/person.gif\">OAϵͳ����Ա,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  
<%}else if("kuanglecheng".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"�칫��->��ί���°칫��\" personName=\"���ֳ�\" uuid=\"fbb7cb-fdb66ecd83-bc7c288dcde03ce304bd1b2e370841e8\" person=\"kuanglecheng\"  title=\"������֯��&#13;&#10;�칫��->��ί���°칫��\"><font face=\"Webdings\" color=\"#009900\">m</font>���ֳ�,</span>";
	}else{
    		sendHtml = "<span class=\"send\" personType=\"0\" department=\"�칫��->��ί���°칫��\" personName=\"���ֳ�\" uuid=\"fbb7cb-fdb66ecd83-bc7c288dcde03ce304bd1b2e370841e8\" person=\"kuanglecheng\"  title=\"������֯��&#13;&#10;�칫��->��ί���°칫��\"><img src=\"/oabase/images/person.gif\">���ֳ�,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  

<%}else if("bgt".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"�칫��->��ֵ����\" personName=\"��ֵ����\" uuid=\"fbb7cb-fdb66ed72c-bc7c288dcde03ce304bd1b2e370841e8\" person=\"zongzhibanshi\"  title=\"������֯��&#13;&#10;�칫��->��ֵ����\"><font face=\"Webdings\" color=\"#009900\">m</font>��ֵ����,</span>";
	}else{
    		sendHtml = "<span class=\"send\" personType=\"0\" department=\"�칫��->��ֵ����\" personName=\"��ֵ����\" uuid=\"fbb7cb-fdb66ed72c-bc7c288dcde03ce304bd1b2e370841e8\" person=\"zongzhibanshi\"  title=\"������֯��&#13;&#10;�칫��->��ֵ����\"><img src=\"/oabase/images/person.gif\">��ֵ����,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  

<%}else if("shitang".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ع����������\" personName=\"���ڷ�������\" uuid=\"68d368d3-1302ee4c145-837f84975ee09664d890917866b9261b\" person=\"hqfwxx\"  title=\"������֯��&#13;&#10;���ع����������\"><font face=\"Webdings\" color=\"#009900\">m</font>���ڷ�������,</span>";
	}else{
    		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ع����������\" personName=\"���ڷ�������\" uuid=\"68d368d3-1302ee4c145-837f84975ee09664d890917866b9261b\" person=\"hqfwxx\"  title=\"������֯��&#13;&#10;���ع����������\"><img src=\"/oabase/images/person.gif\">���ڷ�������,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  
<%}else if("rfzx".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"�ܱ���->�ںϷ�չ����\" personName=\"�ںϷ�չ����\" uuid=\"a310a31-14781543da7-1a426a6cdb8e100f49f6a31924b1b927\" person=\"rfzx\"  title=\"������֯��&#13;&#10;�ܱ���->�ںϷ�չ����\"><font face=\"Webdings\" color=\"#009900\">m</font>�ںϷ�չ����,</span>";
	}else{
    		sendHtml = "<span class=\"send\" personType=\"0\" department=\"�ܱ���->�ںϷ�չ����\" personName=\"�ںϷ�չ����\" uuid=\"a310a31-14781543da7-1a426a6cdb8e100f49f6a31924b1b927\" person=\"rfzx\"  title=\"������֯��&#13;&#10;�ܱ���->�ںϷ�չ����\"><img src=\"/oabase/images/person.gif\">�ںϷ�չ����,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  	
<%}else if("jsfs".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���շ���->���絳��\" personName=\"�����\" uuid=\"fbb7cb-fdb66ef389-bc7c288dcde03ce304bd1b2e370841e8\" person=\"kuanglecheng\"  title=\"������֯��&#13;&#10;���շ���->���絳��\"><font face=\"Webdings\" color=\"#009900\">m</font>�����,</span>";
	}else{
    		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���շ���->���絳��\" personName=\"�����\" uuid=\"fbb7cb-fdb66ef389-bc7c288dcde03ce304bd1b2e370841e8\" person=\"kuanglecheng\"  title=\"������֯��&#13;&#10;���շ���->���絳��\"><img src=\"/oabase/images/person.gif\">�����,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  


<%}else{
	if ("1".equals(request.getParameter("atta"))){
%>
     document.getElementById(sendToId).innerHTML = document.all("SendFileSendHtml").value;
<%}}%>

	var topic = "";
	<%if(sendFileBean.getTopic()!=null && !("".equals(sendFileBean.getTopic()))){%>
	   topic = "<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-13)%>";
	<%}
		if ("1".equals(request.getParameter("atta"))){
	%>
	document.getElementById(sendccId).innerHTML = document.all("SendFileCcHtml").value;
	document.getElementById(sendbccId).innerHTML = document.all("SendFileBccHtml").value;
	<%}%>
	document.sendForm.topic.value = topic;
	<%
	 if (sendFileBean.getisRe() == null){
	%>
		document.all("isRe").checked = false;
	<%
	}else{
	%>
		document.all("isRe").checked = true;
	<%}%>
	<%
	 if (sendFileBean.getisSent() == null && request.getSession().getAttribute("newSendFile") == null){
	%>
		document.all("isSent").checked = false;
	<%
	}else{
	%>
		document.all("isSent").checked = true;
	<%}%>
	<%String result = (String)request.getSession().getAttribute("failResult");
	request.getSession().removeAttribute("failResult");
	
	if(result!=null){%>
       var mail_context = getCookie("mail_context");
       var mail_title = getCookie("mail_title"); 
       //setCookie("mail_title","");  
       //setCookie("mail_context",""); 
   	 document.sendForm.content.value=String(mail_context); 
   	 document.sendForm.topic.value=String(mail_title); 	

   	 <%}%>

}

function changeStyle(id){
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}


function setCookie(name,value){
 var expire = ""; 
var hours = 100;
  if(hours != null) { 
    expire = new Date((new Date()).getTime() + hours * 3600000); 
    expire = "; expires=" + expire.toGMTString(); 
  }
			
if("mail_title"==name||"mail_context"==name){
		document.cookie=name+"="+escape(value)+"quqs"+expire;}
		else{document.cookie=name+"="+value+expire;}
		
}

function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf(name)>=0){
			var cook1 = unescape(cook.substring(cook.indexOf(name)));
			var value;
			if("mail_title"==name||"mail_context"==name){
			value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf("quqs;")));
			}else{value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";")));}
			return value;
		}else if("xhsstyle"==name){
			return "grey";
			}else
			{return "";}
		
}

function initstyle(){
	var style = getCookie("xhsstyle");
	
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
	
	
}
initstyle();
</script>

		<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
	</head>

	<body onLoad="_load()">
		<form method="post" name="sendForm" ENCTYPE="multipart/form-data">
			<!--����Ϊ�����ʼ���ʱ��Ϊ���˻���� -->
			<input type="hidden" name="addPerson_person">
			<input type="hidden" name="addPerson_group">
			<input type="hidden" name="addPerson_oagroup">
			<input type="hidden" name="addPerson_org">

			<input type="hidden" name="addcc_person">
			<input type="hidden" name="addcc_group">
			<input type="hidden" name="addcc_oagroup">
			<input type="hidden" name="addcc_org">

			<input type="hidden" name="addbcc_person">
			<input type="hidden" name="addbcc_group">
			<input type="hidden" name="addbcc_oagroup">
			<input type="hidden" name="addbcc_org">
			<!--
	<input type="text" name="addPerson_person" style="width:100%"><br>
	<input type="text" name="addPerson_group" style="width:100%"><br>
	<input type="text" name="addPerson_org" style="width:100%"><br>
	<input type="text" name="addcc_person" style="width:100%"><br>
	<input type="text" name="addcc_group" style="width:100%"><br>
	<input type="text" name="addcc_org" style="width:100%"><br>
	<input type="text" name="addbcc_person" style="width:100%"><br>
	<input type="text" name="addbcc_group" style="width:100%"><br>
	<input type="text" name="addbcc_org" style="width:100%"><br>-->
			<input type="hidden" name="AttFiles" value="">
			<!-- ѡ���ռ��ˡ����͡�����Ϊ0��1��2 -->
			<input type="hidden" name="sendType" value="">
			<!-- ���ʼ��ķ�ʽ -->
			<input type="hidden" name="sendMail" value="1">
			<!--����Ϊ����Ӹ�����ʱ�򴫵��ռ�����Ϣ��-->
			<textarea name="sendHtml" style="display: none"></textarea>
			<!-- �ռ��� -->
			<textarea name="ccHtml" style="display: none"></textarea>
			<!-- ���� -->
			<textarea name="bccHtml" style="display: none"></textarea>
			<!-- ���� -->
			<textarea name="SendFileSendHtml" style="display: none"><%=session.getAttribute("SendFileSendHtml")%></textarea>
			<!-- �ռ��� -->
			<textarea name="SendFileCcHtml" style="display: none"><%=session.getAttribute("SendFileCcHtml")%></textarea>
			<!-- ���� -->
			<textarea name="SendFileBccHtml" style="display: none"><%=session.getAttribute("SendFileBccHtml")%></textarea>
			<!-- ���� -->
			<div id="alertbox1"
				style="position: absolute; width: 250px; height: 24px; z-index: 1; left: 320px; top: 100px; visibility: hidden;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#000000">
					<tr bgcolor="#EEFFF7">
						<td height="25" align="center" id="showAlert"
							class="message_title"></td>
					</tr>
				</table>
			</div>
			<input type="hidden" name="donext" value="" />

			<table border="0" align="left" cellpadding="0" cellspacing="0"
				width="782">
				<tr>
					<td width="578" valign="top">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" class="table_bgcolor">
							<tr>
								<td width="183" height="36" bgcolor="#FFFFFF">
									<img src="<%=path%>/images/mail_sendbutton.JPG" width="57"
										height="23" onClick="javascript:mailsend()"
										style="cursor: hand" />
									<img src="<%=path%>/images/mail_save.JPG" width="102"
										height="23" onClick="javascript:savetodraft()"
										style="cursor: hand" />
								</td>
								<td width="395" height="36" bgcolor="#FFFFFF"
									class="message_title">
									<font color="#FF0000"><%=sun_flag%>��ʾ���ռ��˲��ܶ���<%=broadcastnum%>�ˡ����������֮����Ϣ��ͨ����Ϣ����ʵ�֡�</font>
								</td>
							</tr>
							<tr>
								<td colspan="2" bgcolor="#FFFFFF">

									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<input type="hidden" name="sendType" />
										<tr>
											<td width="14%" height="26" align="right" bgcolor="#FFFFFF"
												nowrap>
												<span onClick="javascript:_selectPerson()"
													class="message_title"
													style="text-decoration: none; cursor: hand"><img
														src="<%=path%>/images/mail_selectreceiver.jpg" width="67"
														height="22" border="0">&nbsp;</span><span
													style="text-decoration: none; cursor: hand" id="totitle"
													class="message_title"></span>
											</td>
											<td width="86%" colspan="3" bgcolor="#FFFFFF"
												class="blue3-12-b">
												<table border="0" cellspacing="0" cellpadding="0"
													width="100%">
													<tr>
														<td>
															<span id="sendto" class="selectText"
																onselectstart="return(false)" state="0"
																onClick="_choosespan(this)"></span>
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/delPerson.gif"
																onClick="javascript:delSel(0)" alt="ɾ��"
																title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand">
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/outspread.gif"
																onClick="javascript:listState(this,0,'')" alt="չ��"
																title="չ���ռ����б�" style="cursor: hand">
														</td>

														<td>
															<!--<input type="button" style="cursor:hand" class="message_title" value="ѡ���ռ���" onClick="javascript:_selectPerson()">-->
														</td>
													</tr>
												</table>
											</td>
										</tr>


										<tr id="cctr" style="display: none">
											<td width="14%" height="26" align="right" bgcolor="#FFFFFF"
												nowrap class="message_title">
												<span onClick="javascript:_selectcc()"
													style="text-decoration: none; cursor: hand"><img
														src="<%=path%>/images/mail_selectcc.jpg" width="67"
														height="22" border="0">&nbsp;</span><span
													style="text-decoration: none; cursor: hand"
													class="message_title" id="cctitle"></span>
												<!--_hideccspan()<input type="button" style="cursor:hand" class="message_title" value="ѡ������" onClick="javascript:_selectcc()">-->
											</td>
											<td width="86%" colspan="3" bgcolor="#FFFFFF"
												class="blue3-12-b">
												<table border="0" cellspacing="0" cellpadding="0"
													width="100%">
													<tr>
														<td>
															<span id="sendcc" class="sendToText"
																onselectstart="return(false)" state="0"
																onClick="_choosespan(this)"></span>
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/delPerson.gif"
																onClick="javascript:delSel(1)" alt="ɾ��"
																title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand">
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/outspread.gif"
																onClick="javascript:listState(this,1,'ccbook')" alt="չ��"
																title="չ���ռ����б�" style="cursor: hand">
														</td>

														<td></td>
													</tr>
												</table>
											</td>
										</tr>

										<tr id="bcctr" style="display: none">
											<td width="14%" height="26" align="right" bgcolor="#FFFFFF"
												nowrap class="message_title">
												<span onClick="javascript:_selectbcc()"
													style="text-decoration: none; cursor: hand"><img
														src="<%=path%>/images/mail_selectbcc.jpg" width="67"
														height="22" border="0">&nbsp;</span><span
													style="text-decoration: none; cursor: hand"
													class="message_title" id="bcctitle"></span>
												<!--_hidebccspan()<input type="button" style="cursor:hand" class="message_title" value="ѡ���ܳ���" onClick="javascript:_selectbcc()">-->
											</td>
											<td width="86%" colspan="3" bgcolor="#FFFFFF"
												class="blue3-12-b">
												<table border="0" cellspacing="0" cellpadding="0"
													width="100%">
													<tr>
														<td>
															<span id="sendbcc" class="sendToText"
																onselectstart="return(false)" state="0"
																onClick="_choosespan(this)"></span>
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/delPerson.gif"
																onClick="javascript:delSel(2)" alt="ɾ��"
																title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand">
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/outspread.gif"
																onClick="javascript:listState(this,2,'bccbook')"
																alt="չ��" title="չ���ռ����б�" style="cursor: hand">
														</td>

														<td></td>
													</tr>
												</table>
											</td>
										</tr>

										<tr>
											<td></td>
											<td align="left">
												<table>

													<tr>
														<td bgcolor="#FFFFFF" class="message_title">
															<span id="cchref" onClick="_showccspan()"
																style="text-decoration: none; cursor: hand"
																class="message_title">ѡ������</span>
														</td>
														<td bgcolor="#FFFFFF" class="message_title">
															<span id="bound"> | </span>
														</td>
														<td bgcolor="#FFFFFF" class="message_title">
															<span id="bcchref" class="message_title"
																onClick="_showbccspan()"
																style="text-decoration: none; cursor: hand"> ѡ�ܳ���</span>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td></td>
											<td></td>
										</tr>

										<tr>
											<td height="26" align="right" bgcolor="#FFFFFF"
												class="message_title" nowrap>
												�����ռ��ˣ�
											</td>
											<td>
												<input type="text" style="width: 100px;" value="" id="inputAddress" class="ac_input"/>
												&nbsp;<span class='message_title'> (֧�����������û���)</span>
											</td>
										</tr>


										<tr>
											<td height="26" align="right" bgcolor="#FFFFFF"
												class="message_title" nowrap>
												����(
												<span class="STYLE1">*</span>)��
											</td>
											<td colspan="3" bgcolor="#FFFFFF">
												<table border="0" cellspacing="0" cellpadding="0"
													width="100%">
													<tr>
														<td>
															<input name="topic" type="text" class="biankuang-blue"
																size="64" />
														</td>
													</tr>
												</table>
											</td>
										</tr>

										<tr>
											<td height="26" align="right" bgcolor="#FFFFFF"
												class="message_title" nowrap>
												<INPUT TYPE="hidden" NAME="filenum">
												<INPUT TYPE="hidden" NAME="realnum">
												<div align="right">
													<a id="container1" class="addfile"> <input id="File1"
															name="file_0" type="file" class="addfile"
															onchange="createnew();" /> </a>
												</div>
											</td>
											<td colspan="3" bgcolor="#FFFFFF">
												<div id="container2"
													style="width: 460px; background: #CCFFFF"></div>
											</td>
										</tr>

										<tr style="display: none">
											<td height="26" align="right" bgcolor="#FFFFFF"
												class="message_title"></td>
											<td colspan="3" bgcolor="#FFFFFF">
												&nbsp;
											</td>
										</tr>
										<tr style="display: none">
											<td height="26" align="right" bgcolor="#FFFFFF"
												class="message_title"></td>
											<td colspan="3" bgcolor="#FFFFFF">
												&nbsp;
											</td>
										</tr>
									</table>


									<table width="98%" border="0" cellpadding="0" cellspacing="0"
										align="center">
										<tr>
											<td height="1" class="table_bgcolor" width="100%"></td>
										</tr>
									</table>
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										align="center">
										<tr>
											<td height="10" colspan="2" bgcolor="#FFFFFF"></td>
										</tr>
										<tr>
											<td colspan="2" align="left" valign="top"
												class="message_title">
												<p>
													���ݸ�Ҫ(
													<span class="STYLE1">500������</span>)��
												</p>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center" valign="top"
												class="message_title">
												<textarea name="content"  cols="70" rows="10" maxlength="500"
													class="biankuang-blue"><%=sendFileBean.getContent()%></textarea>
											</td>
										</tr>
										<tr>
											<td width="78" height="33"></td>
											<td width="503" class="message_title">
												<input name="isSent" type="checkbox" value="checked2"
													checked />
												����һ���ڷ�����&nbsp;&nbsp;
												<input name="isRe" type="checkbox" value="checked1" />
												�յ��ʼ�ʱ���һظ�
											</td>
										</tr>

										<tr>
											<td colspan="5">
												<table width="98%" border="0" cellpadding="0"
													cellspacing="0" align="center">
													<tr>
														<td height="1" class="table_bgcolor" width="100%"></td>
													</tr>
												</table>
											</td>
										</tr>


										<!--  <tr>
                            <td height="44" align="right" class="message_title">��Ӹ���</td>
                          <td class="message_title">

                          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                            	<tr>
                                	<td width="19%">
                          <img src="<%=path%>/images/icon_attachment.gif" width="16" height="16"/><a href="javascript:_attachfile()" class="message_title"  style="cursor:hand">�����Ӹ���</a>                          			</td>
                          <td width="79%">
							<table>
						<%
							for(int index = 0; index < sendFileBean.filenumber();index ++ ){
								AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
						%>
						 <tr>
							 <td  align="left" class="message_title"><IMG SRC="<%=path%>/images/attachfile.gif" BORDER="0" ALT="��Ӹ���">&nbsp;<%=attachFileBean.getFileOriginName()%></td>
						</tr>
						<%
												}//end for
						%>
							</table>
						  <td width="2%">                                </tr>
								  
								
                              </table>                                    
                                
                       </tr>-->


									</table>
								</td>
							</tr>
							<tr>
								<td height="48" colspan="2" align="left" bgcolor="#FFFFFF"
									class="blue3-12" valign="top">
									<img src="<%=path%>/images/mail_sendbutton.JPG" width="57"
										height="23" onClick="javascript:mailsend()"
										style="cursor: hand" />
									<img src="<%=path%>/images/mail_save.JPG" width="102"
										height="23" onClick="javascript:savetodraft()"
										style="cursor: hand" />
								</td>
							</tr>
						</table>

					</td>
					<td width="200" valign="top">
						<iframe name="userlistfrm"
							src="<%=path%>/servlet/MailGroupListServlet" width="200"
							height="530" scrolling="auto" frameborder="0" framespacing="0"></iframe>
					</td>
				</tr>
			</table>

			<script>
changepnum();
function frameautoheight(){
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
}
frameautoheight();

function _ForeverDelete(folder)
{
	var foldername = "";
	if(folder == "Rec"){
		foldername = "�ռ���";
	}else if(folder == "Sent"){
		foldername = "������";
	}else if(folder == "Draft"){
		foldername = "�ݸ���";
	}else if(folder == "Junk"){
		foldername = "������";
	}else{
		foldername = folder;
	}
    ok=confirm("ȷ��Ҫ���"+foldername+"��");
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type=system&folder="+folder;
        return true;
    }else{
        return false;
    }
}
</script>
		</form>
	</body>

<script type="text/javascript">
function findValue(li) {
//if (document.getElementById('inputAddress').value=="") return false;
if(li==null || li.extra[0]==null ) 
{
return alert("û��ƥ�������!");
}else{
//if( !!li.extra ) var sValue = li.extra[0];
//else var sValue = li.selectValue;
//alert("The value you selected was: " + sValue);
var sendHtml = "<span class='send' onclick=selectName(this,0);  personType='0' department='"+li.extra[0]+"' personName='"+li.selectValue+"' uuid='"+li.extra[1]+"' person='"+li.extra[2]+"'  title='������֯��&#13;&#10;"+li.extra[0]+"'><img src='/oabase/images/person.gif'>"+li.selectValue+",</span>";

var a = document.getElementById(selectSpan).innerHTML;
document.getElementById(selectSpan).innerHTML = a + sendHtml;  
//alert(document.getElementById(selectSpan).value);
document.getElementById('inputAddress').value="";
document.sendForm.inputAddress.focus();
changepnum();
}
}

function selectItem(li) {
findValue(li);
}

function formatItem(row) {
return row[0] + "(" + row[1] + ")";
}


$(document).ready(function() {

$("#inputAddress").autocomplete(
"/oabase/mail/autocomplete.jsp",
{
width:250,
minChars:2,
//maxItemsToShow:10,
//mustMatch:1,
selectFirst:true,
onItemSelect:selectItem,
onFindValue:findValue,
formatItem:formatItem
//autoFill:true
}
);
});

</script>
</html>
