<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.oa.util.PropertyManager"%>
<%@ page import="java.io.File"%>
<%
	//无页面请求参数
	//获得输出数据
	//out.println("come###");
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日")+TimeUtil.getCurrentWeek();
	
	SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
	String sun_flag = (String)request.getSession().getAttribute("sun_flag");
	if (sun_flag == null){
		sun_flag = "";
	}
	String broadcastnum = PropertyManager.getProperty("broadcastnum");
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>新华社办公信息化系统</title>
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
	document.all("sendType").value = "0";
	_selectOpen();
}
function _selectcc(){
	document.all("sendType").value = "1";
	_selectOpen();
}

function _selectbcc(){
	document.all("sendType").value = "2";
	_selectOpen();
}

function _addPerson_hidden(usernamestring){
	document.all("sendto").value = usernamestring;
	changepnum();
	return true;	
}

function _addcc(usernamestring){
	document.all("sendcc").value = usernamestring;
	changepnum();
	return true;	
}

function _addbcc(usernamestring){
	document.all("sendbcc").value = usernamestring;
	changepnum();
	return true;
}

function _addPerson_hidden1(user_uuid){
	document.all("addPerson").value = user_uuid;
}
function _addcc1(user_uuid){
	document.all("addcc").value = user_uuid;
}

function _addbcc1(user_uuid){
	document.all("addbcc").value = user_uuid;
}

   
function changeStyle(id){
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	document.cookie=name+"="+value+",";
}
function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		var value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(","));
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
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
		alert("群发数量不能大于<%=broadcastnum%>");
		return false;
	}
	return true;
}


function check(){
	if(!setPersonToSend()){
		alert("请填写收件人");
		return false;
	}
	if(document.all("topic").value == ""){
		if(confirm("你没有添加标题！请确认以‘无标题’作为您的标题吗？"))
		{ 
			document.all("topic").value = "无主题";
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

		window.showAlert.innerHTML = "正在发送文件，请稍等......";
	    //弹出正在发送的提示框
		_showAlertBox('alertbox1','','show');
		//20110420 dyt
		var mail_title;
		var mail_context;
		mail_context=document.all("content").value;
		
		mail_title=document.all("topic").value;
		setCookie("mail_title",mail_title);
		setCookie("mail_context",mail_context);
		
		
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFile1Servlet?sun_flag=<%= sun_flag%>";
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


function savetodraft(){
	if(check()){
		window.showAlert.innerHTML = "正在保存文件，请稍等......";
		_showAlertBox('alertbox1','','show');
		document.sendForm.action = "<%=path%>/servlet/SaveToDraft1Servlet";
		document.sendForm.submit();
	}
}

function _attachfile(){
	getSendHtml();
	document.sendForm.action="<%=path%>/servlet/SendFileAttachFileServlet?"
	document.all("donext").value="3";
	document.sendForm.submit();
}


function _showccspan(){
	var cctr =document.getElementById("cctr");
	var cchref =document.getElementById("cchref");
	var sendcc =document.getElementById("sendcc");
	cctr.style.display = "";
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
	bcctr.style.display = "";
	bcchref.style.display = "none";
	bound();
	_choosespan(sendbcc);
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
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"机关党委\" personName=\"机关党委\" uuid=\"\" person=\"jiguandangwei\" onclick="selectName(this,0);" title=\"所属组织：&#13;&#10;机关党委\"><font face=\"Webdings\" color=\"#009900\">m</font>机关党委,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"机关党委\" personName=\"机关党委\" uuid=\"\"  person=\"jiguandangwei\" onclick="selectName(this,0);" title=\"所属组织：&#13;&#10;机关党委\"><img src=\"/oabase/images/person.gif\">机关党委,</span>";
	}
<%}else if("hr".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"人事局->人才开发服务处\" personName=\"苏成芳\" uuid=\"\" person=\"suchengfang\" onclick=\"selectName(this,0);\" title=\"所属组织：&#13;&#10;人事局->人才开发服务处\"><font face=\"Webdings\" color=\"#009900\">m</font>苏成芳,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"人事局->人才开发服务处\" personName=\"苏成芳\" uuid=\"\" person=\"suchengfang\" onclick=\"selectName(this,0);\" title=\"所属组织：&#13;&#10;人事局->人才开发服务处\"><img src=\"/oabase/images/person.gif\">苏成芳,</span>";
	}
<%}else{
	if ("1".equals(request.getParameter("atta"))){
%>
     document.getElementById(sendToId).innerHTML = document.all("SendFileSendHtml").value;
<%	}
}
%>

	topic = "";
	<%if(sendFileBean.getTopic()!=null && !("".equals(sendFileBean.getTopic()))){%>
	   topic = "<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-14)%>";
	<%}
		if ("1".equals(request.getParameter("atta"))){
	%>
	document.getElementById(sendccId).innerHTML = document.all("SendFileCcHtml").value;
	document.getElementById(sendbccId).innerHTML = document.all("SendFileBccHtml").value;
	<%}%>
	document.all("topic").value = topic;
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

<body onLoad="frameautoheight();_load();">
<form method="post" name="sendForm" ENCTYPE="multipart/form-data">
 <!--以下为发送邮件的时候为传人或分组 -->
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
<input type="hidden" name="donext">
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
<!-- 选择收件人、抄送、密送为0，1，2 -->
<input type="hidden" name="sendType" id=sendType value="">
<!-- 发邮件的方式 -->
<input type="hidden" name="sendMail" id="sendMail" value="1">
<!--以下为了添加附件的时候传递收件人信息用-->
<textarea name="sendHtml" style="display:none"></textarea><!-- 收件人 -->
<textarea name="ccHtml" style="display:none"></textarea><!-- 抄送 -->
<textarea name="bccHtml" style="display:none"></textarea><!-- 密送 --> 
<textarea name="SendFileSendHtml" style="display:none"><%=session.getAttribute("SendFileSendHtml")%></textarea><!-- 收件人 -->
<textarea name="SendFileCcHtml" style="display:none"><%=session.getAttribute("SendFileCcHtml")%></textarea><!-- 抄送 -->
<textarea name="SendFileBccHtml" style="display:none"><%=session.getAttribute("SendFileBccHtml")%></textarea><!-- 密送 --> 
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



 <form name="frm" mothed="post" action="">
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
									<font color="#FF0000">提示：收件人不能多于<%=broadcastnum%>人。发布广而告之的信息请通过信息上载实现。</font>
								</td>
							</tr>
							<tr>
								<td colspan="2" bgcolor="#FFFFFF">

									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="14%" height="26" align="right" bgcolor="#FFFFFF"
												nowrap class="message_title">
												<span onClick="javascript:_selectPerson()"
													style="text-decoration: none; cursor: hand"><img
														src="<%=path%>/images/mail_selectreceiver.jpg" width="67"
														height="22" border="0">&nbsp;</span><span
													style="text-decoration: none; cursor: pointer" id="totitle"
													class="message_title"></span>
											</td>
											<td width="86%" colspan="3" bgcolor="#FFFFFF"
												class="blue3-12-b">
												<table border="0" cellspacing="0" cellpadding="0"
													width="100%">
													<tr>
														<td>
															<div id="sendto" class="selectText" style="width:430px;overflow:hidden"
																onselectstart="return(false)" state="0"
																onClick="_choosespan(this)"></div>
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/delPerson.gif"
																onClick="javascript:delSel(0)" alt="删除"
																title="删除收件人中选择的人或组织" style="cursor: hand">
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/outspread.gif"
																onClick="javascript:listState(this,0,'')" alt="展开"
																title="展开收件人列表" style="cursor: hand">
														</td>

														<td>
															<!--<input type="button" style="cursor:hand" class="message_title" value="选择收件人" onClick="javascript:_selectPerson()">-->
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
												<!--_hideccspan()<input type="button" style="cursor:hand" class="message_title" value="选择抄送人" onClick="javascript:_selectcc()">-->
											</td>
											<td width="86%" colspan="3" bgcolor="#FFFFFF"
												class="blue3-12-b">
												<table border="0" cellspacing="0" cellpadding="0"
													width="100%">
													<tr>
														<td>
															<div id="sendcc" class="sendToText" style="width:430px;overflow:hidden"
																onselectstart="return(false)" state="0"
																onClick="_choosespan(this)"></div>
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/delPerson.gif"
																onClick="javascript:delSel(1)" alt="删除"
																title="删除收件人中选择的人或组织" style="cursor: hand">
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/outspread.gif"
																onClick="javascript:listState(this,1,'ccbook')" alt="展开"
																title="展开收件人列表" style="cursor: hand">
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
												<!--_hidebccspan()<input type="button" style="cursor:hand" class="message_title" value="选择密抄人" onClick="javascript:_selectbcc()">-->
											</td>
											<td width="86%" colspan="3" bgcolor="#FFFFFF"
												class="blue3-12-b">
												<table border="0" cellspacing="0" cellpadding="0"
													width="100%">
													<tr>
														<td>
															<div id="sendbcc" class="sendToText" style="width:430px;overflow:hidden"
																onselectstart="return(false)" state="0"
																onClick="_choosespan(this)"></div>
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/delPerson.gif"
																onClick="javascript:delSel(2)" alt="删除"
																title="删除收件人中选择的人或组织" style="cursor: hand">
														</td>

														<td>
															<img
																src="<%=request.getContextPath()%>/images/outspread.gif"
																onClick="javascript:listState(this,2,'bccbook')"
																alt="展开" title="展开收件人列表" style="cursor: hand">
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
																class="message_title">选抄送人</span>
														</td>
														<td bgcolor="#FFFFFF" class="message_title">
															<span id="bound"> | </span>
														</td>
														<td bgcolor="#FFFFFF" class="message_title">
															<span id="bcchref" class="message_title"
																onClick="_showbccspan()"
																style="text-decoration: none; cursor: hand"> 选密抄人</span>
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
												输入收件人：
											</td>
											<td>
												<input type="text" style="width: 100px;" value="" id="inputAddress" class="ac_input"/>
												&nbsp;<span class='message_title'> (支持中文名和用户名)</span>
											</td>
										</tr>


										<tr>
											<td height="26" align="right" bgcolor="#FFFFFF"
												class="message_title" nowrap>
												主题(
												<span class="STYLE1">*</span>)：
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
													<a id="container1" class="addfile" href="#" onclick="document.getElementById('file_0').click();"><input id="file_0"
															name="file_0" type="file" class="addfile"
															onchange="createnew();" /> </a>
												</div>
											</td>
											<td colspan="3" bgcolor="#FFFFFF">
												<div id="container2"
													style="width: 460px;height:21px;background: #CCFFFF"></div>
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
													内容概要(
													<span class="STYLE1">500字以内</span>)：
												</p>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center" valign="top"
												class="message_title">
												<textarea name="content" id="content" cols="70" rows="10"
													class="biankuang-blue"><%=CommUtil.unformathtm(sendFileBean.getContent())%></textarea>
											</td>
										</tr>
										<tr>
											<td width="78" height="33"></td>
											<td width="503" class="message_title">
												<input name="isSent" type="checkbox" value="checked2"
													checked />
												保留一份在发件箱&nbsp;&nbsp;
												<input name="isRe" type="checkbox" value="checked1" />
												收到邮件时给我回复
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
                            <td height="44" align="right" class="message_title">添加附件</td>
                          <td class="message_title">

                          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                            	<tr>
                                	<td width="19%">
                          <img src="<%=path%>/images/icon_attachment.gif" width="16" height="16"/><a href="javascript:_attachfile()" class="message_title"  style="cursor:hand">点击添加附件</a>                          			</td>
                          <td width="79%">
							<table>
						<%
							for(int index = 0; index < sendFileBean.filenumber();index ++ ){
								AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
						%>
						 <tr>
							 <td  align="left" class="message_title"><IMG SRC="<%=path%>/images/attachfile.gif" BORDER="0" ALT="添加附件">&nbsp;<%=attachFileBean.getFileOriginName()%></td>
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


	  

</form>
<script>
changepnum();
function _ForeverDelete(folder)
{
	var foldername = "";
	if(folder == "Rec"){
		foldername = "收件箱";
	}else if(folder == "Sent"){
		foldername = "发件箱";
	}else if(folder == "Draft"){
		foldername = "草稿箱";
	}else if(folder == "Junk"){
		foldername = "垃圾箱";
	}else{
		foldername = folder;
	}
    ok=confirm("确定要清空"+foldername+"吗？");
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type=system&folder="+folder;
        return true;
    }else{
        return false;
    }
}
function frameautoheight(){
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight+20;
}

</script>


<script type="text/javascript">
function findValue(li) {
//if (document.getElementById('inputAddress').value=="") return false;
if(li==null || li.extra[0]==null ) 
{
return alert("没有匹配的人名!");
}else{
//if( !!li.extra ) var sValue = li.extra[0];
//else var sValue = li.selectValue;
//alert("The value you selected was: " + sValue);
var type = 0;
if (selectSpan == 'sendcc') type = 1;
if (selectSpan == 'sendbcc') type = 2;
var sendHtml = "<span class='send' onclick=selectName(this," + type + ");  personType='0' department='"+li.extra[0]+"' personName='"+li.selectValue+"' uuid='"+li.extra[1]+"' person='"+li.extra[2]+"'  title='所属组织：&#13;&#10;"+li.extra[0]+"'><img src='/oabase/images/person.gif'>"+li.selectValue+",</span>";
var span = document.createElement("span");
var div = document.getElementById(selectSpan);
div.appendChild(span);
span.outerHTML = sendHtml;
if (_firstChild(_nextsibling(_nextsibling(div.parentNode))).alt === '收缩') 
		div.style.height = div.scrollHeight;
document.getElementById('inputAddress').value="";
document.getElementById('inputAddress').focus();
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
</body>
</html>
