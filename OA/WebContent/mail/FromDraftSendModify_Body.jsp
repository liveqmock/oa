 <%@ page contentType="text/html; charset=GBK" %>
 <%@ page pageEncoding="GBK" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupinfoVO"%>
<%@	page import="com.icss.oa.address.vo.SysPersonVO"%>
<%@ page import="java.util.List"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.oa.util.PropertyManager"%>
<%@ page import="java.io.File"%>
<%

	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy��MM��dd��")+TimeUtil.getCurrentWeek();
	SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
	String sun_flag= (String)request.getParameter("sun_flag");
	if (sun_flag == null){
		sun_flag = "";
	}
	String broadcastnum = PropertyManager.getProperty("broadcastnum");
	String sendHtml = (String)session.getAttribute("sendHtml");
	String ccHtml = (String)session.getAttribute("ccHtml");
	String bccHtml = (String)session.getAttribute("bccHtml");

	
%>

<html xmlns="http://www.w3.org/1999/xhtml">
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
<link href="<%=path%>/mail/css/Attach.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/mail/js/Attach.js"></script>
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
	return true;	
}

function _addcc(usernamestring){
	document.sendForm.sendcc.value = usernamestring;
	return true;	
}

function _addbcc(usernamestring){
	document.sendForm.sendbcc.value = usernamestring;
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
		showto.innerHTML = "ѡ���ռ���("+sendnum+")��";
	}else{
		showto.innerHTML = "ѡ���ռ��ˣ�";
	}
	if(ccnum != 0){
		showcc.innerHTML = "ѡ������("+ccnum+")��";
	}else{
		showcc.innerHTML = "ѡ�����ˣ�";
	}
	if(bccnum != 0){
		showbcc.innerHTML = "ѡ��������("+bccnum+")��";
	}else{
		showbcc.innerHTML = "ѡ�������ˣ�";
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
	var obj = document.getElementById("sendto");
	var obj1 = document.getElementById("sendcc");
	var obj2 = document.getElementById("sendbcc");
	var num = obj.getElementsByTagName("span").length + obj1.getElementsByTagName("span").length + obj2.getElementsByTagName("span").length;
	if(num > <%=broadcastnum%>){
		alert("Ⱥ���������ܴ���<%=broadcastnum%>");
		return false;
	}
	if(document.sendForm.topic.value == ""){
		if(confirm("��û�����ӱ��⣡��ȷ���ԡ��ޱ��⡯��Ϊ���ı�����"))
		{ 
			document.sendForm.topic.value = "������";
		  	return true;
		};
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
		
		//20110420 dyt
		var mail_title;
		var mail_context;
		mail_context=document.sendForm.content.value;		
		mail_title=document.sendForm.topic.value;
		setCookie("mail_title",mail_title);
		setCookie("mail_context",mail_context);		
		
		document.sendForm.action = "<%=path%>/servlet/SendFile1Servlet?sun_flag=<%= sun_flag%>";
		document.sendForm.submit();
	}
}

//20110420 dyt
function setCookie(name,value){
		
 var expire = ""; 
var hours = 100;
  if(hours != null) { 
    expire = new Date((new Date()).getTime() + hours * 3600000); 
    expire = "; expires=" + expire.toGMTString(); 
  }

if("mail_title"==name||"mail_context"==name){
		document.cookie=name+"="+escape(value)+"quqs"+expire ;
		}else{
                 document.cookie=name+"="+value+expire ;
                }


}

function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf(name)>=0){
			var cook1 = unescape(cook.substring(cook.indexOf(name)));
			var value;
			if("mail_title"==name||"mail_context"==name){
			value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf("quqs;")));
			}else{value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";")));}
			alert("get:"+name+"="+value);
			return value;
		}else if("xhsstyle"==name){
			return "grey";
			}else
			{return "";}
		
}


function _attachfile(){
	getSendHtml();
	document.sendForm.action="<%=path%>/servlet/SendFileAttachFileServlet?"
	document.sendForm.donext.value="4";
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

function _return(){
	location.href = "<%=path%>/servlet/BoxListServlet";
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

function _choosespan(obj){
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
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"���ص�ί\" uuid=\"\" person=\"jiguandangwei\"  title=\"������֯��&#13;&#10;���ص�ί\"><font face=\"Webdings\" color=\"#009900\">m</font>���ص�ί,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"���ص�ί\" uuid=\"\"  person=\"jiguandangwei\"  title=\"������֯��&#13;&#10;���ص�ί\"><img src=\"/oabase/images/person.gif\">���ص�ί,</span>";
	}
	document.getElementById(sendToId).innerHTML = sendHtml;  
<%}else if("hr".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���¾�->�˲ſ�������\" personName=\"�ճɷ�\" uuid=\"\" person=\"suchengfang\" onclick=\"selectName(this,0);\" title=\"������֯��&#13;&#10;���¾�->�˲ſ�������\"><font face=\"Webdings\" color=\"#009900\">m</font>�ճɷ�,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"���¾�->�˲ſ�������\" personName=\"�ճɷ�\" uuid=\"\" person=\"suchengfang\" onclick=\"selectName(this,0);\" title=\"������֯��&#13;&#10;���¾�->�˲ſ�������\"><img src=\"/oabase/images/person.gif\">�ճɷ�,</span>";
	}
<%}else{
	if ("1".equals(request.getParameter("atta"))){
%>
     document.getElementById(sendToId).innerHTML = document.all("SendFileSendHtml").value;
<%}}%>

	var topic = "";
	<%if(sendFileBean.getTopic()!=null && !("".equals(sendFileBean.getTopic()))){%>
	   topic = "<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-14)%>";
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
	 if (sendFileBean.getisSent() == null && request.getAttribute("newSendFile") == null){
	%>
		document.all("isSent").checked = false;
	<%
	}else{
	%>
		document.all("isSent").checked = true;
	<%}%>
	//<%=request.getAttribute("newSendFile")%>
    
	<%
		for(int index = 0; index < sendFileBean.filenumber();index ++ ){
			AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
			File sizef = new File(attachFileBean.getUploadUrl());
			long size = sizef.length();
	%>
		existfilenode('<%=attachFileBean.getFileOriginName()%>','<%=attachFileBean.getUploadUrl()%>','<%=size%>');
	<%}%>
}
</script>

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body onLoad="_load()">
<form method="post" name="sendForm" ENCTYPE="multipart/form-data">
 <!--����Ϊ�����ʼ���ʱ��Ϊ���˻���� -->
	<input type="hidden" name="addPerson_person">
	<input type="hidden" name="addPerson_oagroup">
	<input type="hidden" name="addPerson_group">
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
<input type="hidden" name="AttFiles" value ="">
<!-- ѡ���ռ��ˡ����͡�����Ϊ0��1��2 -->
<input type="hidden" name="sendType" value="">
<!-- ���ʼ��ķ�ʽ -->
<input type="hidden" name="sendMail" value="1">
<!--����Ϊ�����Ӹ�����ʱ�򴫵��ռ�����Ϣ��-->
<textarea name="sendHtml" style="display:none"></textarea><!-- �ռ��� -->
<textarea name="ccHtml" style="display:none"></textarea><!-- ���� -->
<textarea name="bccHtml" style="display:none"></textarea><!-- ���� --> 
<textarea name="SendFileSendHtml" style="display:none"><%=session.getAttribute("SendFileSendHtml")%></textarea><!-- �ռ��� -->
<textarea name="SendFileCcHtml" style="display:none"><%=session.getAttribute("SendFileCcHtml")%></textarea><!-- ���� -->
<textarea name="SendFileBccHtml" style="display:none"><%=session.getAttribute("SendFileBccHtml")%></textarea><!-- ���� --> 
<div id="alertbox1" style="position:absolute; width:250px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align="center" id="showAlert"></td>
		</tr>
	</table>
</div>  
 <input type="hidden" name="donext" value=""/>


<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
  
  <tr>
      <td valign="top">
      <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="table_bgcolor">
          <tr>
            <td height="48" colspan="2" align="left" bgcolor="#FFFFFF" class="blue3-12" valign="top"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" onClick="javascript:mailsend()" style="cursor:hand" /> &nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" width="65" height="25" onClick="javascript:_return()" style="cursor:hand"/></td>
            <td width="222" rowspan="3" valign="top" bgcolor="#FFFFFF" class="blue3-12"><iframe name="userlistfrm" src="<%=path%>/servlet/MailGroupListServlet" width="100%" height="440" scrolling="auto" frameborder="0" framespacing="0"></iframe></td>
    </tr>
          <tr>
          	<td colspan="2" bgcolor="#FFFFFF" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <input type="hidden" name="sendType" />
      		  <tr>
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" nowrap class="message_title"><span onClick="javascript:_selectPerson()" class="message_title" style="text-decoration:none;cursor:hand" id="totitle">ѡ���ռ��ˣ�</span></td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><span id="sendto" class="selectText" onselectstart="return(false)" state="0" onClick="_choosespan(this)"><%=sendHtml%></span></td>
						  
						  <td><img src="<%=request.getContextPath()%>/images/delPerson.gif" onClick="javascript:delSel(0)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand"></td>
						  
						  <td><img src="<%=request.getContextPath()%>/images/outspread.gif" onClick="javascript:listState(this,0,'')" alt="չ��" title="չ���ռ����б�" style="cursor: hand"></td>
						  
						  <td><!--<input type="button" style="cursor:hand" class="message_title" value="ѡ���ռ���" onClick="javascript:_selectPerson()">--></td>
                        </tr>
                    </table></td>
                </tr>


                <tr id="cctr" style="display:none">
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" nowrap class="message_title"><span onClick="javascript:_selectcc()" style="text-decoration:none;cursor:hand" id="cctitle">ѡ�����ˣ�</span><!--_hideccspan()<input type="button" style="cursor:hand" class="message_title" value="ѡ������" onClick="javascript:_selectcc()">-->                    </td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><span id="sendcc" class="sendToText" onselectstart="return(false)" state="0" onClick="_choosespan(this)"><%=ccHtml%></span></td>
						  
						  <td><img src="<%=request.getContextPath()%>/images/delPerson.gif" onClick="javascript:delSel(1)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand"></td>
						  
						  <td><img src="<%=request.getContextPath()%>/images/outspread.gif" onClick="javascript:listState(this,1,'ccbook')" alt="չ��" title="չ���ռ����б�" style="cursor: hand"></td>
						  
						  <td></td>
                      </tr>
                    </table></td>
                </tr>
                      		  
				<tr id="bcctr" style="display:none">
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" nowrap class="message_title"><span onClick="javascript:_selectbcc()" style="text-decoration:none;cursor:hand" id="bcctitle">ѡ���ܳ��ˣ�</span><!--_hidebccspan()<input type="button" style="cursor:hand" class="message_title" value="ѡ���ܳ���" onClick="javascript:_selectbcc()">--></td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><span id="sendbcc" class="sendToText" onselectstart="return(false)" state="0" onClick="_choosespan(this)"><%=bccHtml%></span></td>
						  
						  <td><img src="<%=request.getContextPath()%>/images/delPerson.gif" onClick="javascript:delSel(2)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand"></td>
						  
						  <td><img src="<%=request.getContextPath()%>/images/outspread.gif" onClick="javascript:listState(this,2,'bccbook')" alt="չ��" title="չ���ռ����б�" style="cursor: hand"></td>
						  
						  <td></td>
                        </tr>
                    </table></td>
                </tr>

				<tr><td></td><td align="left">
				<table><tr>
				<td  bgcolor="#FFFFFF" class="message_title"><span id="cchref" onClick="_showccspan()"  style="text-decoration:none;cursor:hand" class="message_title" >ѡ������</span></td> <td  bgcolor="#FFFFFF" class="message_title"><span id="bound"> | </span></td> <td  bgcolor="#FFFFFF" class="message_title"><span id="bcchref" class="message_title" onClick="_showbccspan()" style="text-decoration:none;cursor:hand"> ѡ���ܳ���</span></td>
				
				</tr></table>
				</td><td></td><td></td><tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title" nowrap>����(<span class="STYLE1">*</span>)��</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><input name="topic" type="text" class="biankuang-blue" size="64" /></td>
                        </tr>
                    </table></td>
                  </tr>
                  
				   <tr>
                        <td height="26" align="center" bgcolor="#FFFFFF" class="message_title" nowrap> 
						<INPUT TYPE="hidden" NAME="filenum">
						<INPUT TYPE="hidden" NAME="realnum">
						<div>
							<a id="container1" class="addfile">
							<input id="File1" name="file_0" type="file" class="addfile" onchange="createnew();"  />
							</a>
							</div>
						</td>
                        <td colspan="3" bgcolor="#FFFFFF">
                        <div id="container2" style=" width:460px;background:#CCFFFF"></div>
						</td>
                      </tr>
                  
                  <tr style="display:none">
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title"></td>
                    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr style="display:none">
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title"></td>
                    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
              </table>
                 
          <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr><td height="1" class="table_bgcolor" width="100%"></td></tr>
                 </table>
                 <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr><td height="10" colspan="2" bgcolor="#FFFFFF"></td></tr>
                    <tr>
                      <td colspan="2" align="left" valign="top" class="message_title"><p>���ݸ�Ҫ(<span class="STYLE1">500������</span>)��</p>                      </td>
                    </tr>
                    <tr>
                   	  <td colspan="2" align="center" valign="top" class="message_title">                   	    
               	        <textarea name="content" cols="70" rows="10" class="biankuang-blue"><%=CommUtil.unformathtm(sendFileBean.getContent())%></textarea></td>
                    </tr>
                    <tr>
                      <td width="78" height="33"></td>
                      <td width="503" class="message_title"><input name="isSent" type="checkbox" value="checked2" checked/>
                        ����һ���ڷ�����&nbsp;&nbsp;
                        <input name="isRe" type="checkbox" value="checked1" />
                      �յ��ʼ�ʱ���һظ� </td>
                   </tr>
                    <tr>
                    	<td colspan="5">
                        	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                                <tr><td height="1" class="table_bgcolor" width="100%"></td></tr>
                             </table>                        </td>
                    </tr>
                   <!-- <tr>
                    	<td height="44" align="right" class="message_title">���Ӹ���</td>
                   	  <td class="message_title">
                      
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            	<tr>
                                	<td width="19%">
                          <img src="<%=path%>/images/icon_attachment.gif" width="16" height="16"/><a href="javascript:_attachfile()" class="message_title"  style="cursor:hand">������Ӹ���</a>                          			</td>
                          <td width="79%" align="center">
							<table>
						<%
							for(int index = 0; index < sendFileBean.filenumber();index ++ ){
								AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
						%>
						 <tr>
							 <td  align="left" class="message_title"><IMG SRC="<%=path%>/images/attachfile.gif" BORDER="0" ALT="���Ӹ���">&nbsp;<%=attachFileBean.getFileOriginName()%></td>
						</tr>
						<%
												}//end for
						%>
							</table>
						  <td width="2%">                                </tr>
								  
								
                              </table>
	   </tr>-->
                     </table>               </td>
        </tr>
  
  
          <tr >
            <td height="48" colspan="2" align="left" bgcolor="#FFFFFF" class="blue3-12" valign="top"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" onClick="javascript:mailsend()" style="cursor:hand" /> &nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" width="65" height="25" onClick="javascript:_return()" style="cursor:hand"/></td>
          </tr>        
      </table></td>
    </tr>
    <tr>
    	<td width="14" headers="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
    
    
    <tr>
    	<td colspan="5" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
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
</html>