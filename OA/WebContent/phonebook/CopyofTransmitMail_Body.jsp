<%@ page contentType="text/html; charset=GB2312" %>

<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%
	//��ҳ���������
	//����������
	String path = request.getContextPath();
	SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
	String sun_flag = (String)request.getParameter("sun_flag");
	if (sun_flag == null){
		sun_flag = "";
	}
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
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
<script language="javascript">
function _updown(id){
	 var obj=document.getElementById(id);
   if(searchStr(obj.src,"mail_up.jpg")){
			 obj.src='<%=path%>/images/mail_down.jpg';
	 }else{
		  obj.src='<%=path%>/images/mail_up.jpg';
	 }
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
	alert('sadfasfasf');
	//window.bottomto.innerHTML = usernamestring;
	return true;	
}

function _addcc(usernamestring){
	document.sendForm.sendcc.value = usernamestring;
	//window.bottomcc.innerHTML = usernamestring;
	return true;	
}

function _addbcc(usernamestring){
	document.sendForm.sendbcc.value = usernamestring;
	//window.bottombcc.innerHTML = usernamestring;
	return true;
}

function _addPerson_hidden1(user_uuid){
	alert(user_uuid);
	document.sendForm.addPerson.value = user_uuid;
	//alert("document.sendForm.addPerson.value "+document.sendForm.addPerson.value);  
}
function _addcc1(user_uuid){
	//alert(user_uuid);
	document.sendForm.addcc.value = user_uuid;
	//alert("document.sendForm.addcc.value "+document.sendForm.addcc.value);
}

function _addbcc1(user_uuid){
	//alert(user_uuid);
	document.sendForm.addbcc.value = user_uuid;
	//alert("document.sendForm.addbcc.value "+document.sendForm.addbcc.value);
}




function changeStyle(id){
	//alert("dddd");
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
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(",")); 
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
initstyle();

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
	    //�������ڷ��͵���ʾ��
		//_showAlertBox('alertbox1','','show');
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFile1Servlet?sun_flag=<%= sun_flag%>";
		document.sendForm.submit();
	}
}

function _attachfile(){
	getSendHtml();
	document.sendForm.action="<%=path%>/servlet/SendFileAttachFileServlet?"
	document.sendForm.donext.value="3";
	document.sendForm.submit();
}

function _load(){
<% if("party".equals(sun_flag)) {%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"���ص�ί\" uuid=\"\" person=\"jiguandangwei\" onclick="selectName(this,0);" title=\"������֯��&#13;&#10;���ص�ί\"><font face=\"Webdings\" color=\"#009900\">m</font>���ص�ί,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"���ص�ί\" personName=\"���ص�ί\" uuid=\"\"  person=\"jiguandangwei\" onclick="selectName(this,0);" title=\"������֯��&#13;&#10;���ص�ί\"><img src=\"/oabase/images/person.gif\">���ص�ί,</span>";
	}
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
<%	}
}
%>

	topic = "";
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
	 if (sendFileBean.getisSent() == null && request.getAttribute("newSendFile") == null){
	%>
		document.all("isSent").checked = false;
	<%
	}else{
	%>
		document.all("isSent").checked = true;
	<%}%>
	//<%=request.getAttribute("newSendFile")%>

}
</script>

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body onload="_load()">
<form method="post" name="sendForm" >
 <!--����Ϊ�����ʼ���ʱ��Ϊ���˻���� -->
	<input type="hidden" name="addPerson_person">
	<input type="hidden" name="addPerson_group">
	<input type="hidden" name="addPerson_org">
	<input type="hidden" name="addcc_person">
	<input type="hidden" name="addcc_group">
	<input type="hidden" name="addcc_org">
	<input type="hidden" name="addbcc_person">
	<input type="hidden" name="addbcc_group">
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
<!-- ѡ���ռ��ˡ����͡�����Ϊ0��1��2 -->
<input type="hidden" name="sendType" value="">
<!-- ���ʼ��ķ�ʽ -->
<input type="hidden" name="sendMail" value="1">
<!--����Ϊ����Ӹ�����ʱ�򴫵��ռ�����Ϣ��-->
<textarea name="sendHtml" style="display:none"></textarea><!-- �ռ��� -->
<textarea name="ccHtml" style="display:none"></textarea><!-- ���� -->
<textarea name="bccHtml" style="display:none"></textarea><!-- ���� --> 
<textarea name="SendFileSendHtml" style="display:none"><%=session.getAttribute("SendFileSendHtml")%></textarea><!-- �ռ��� -->
<textarea name="SendFileCcHtml" style="display:none"><%=session.getAttribute("SendFileCcHtml")%></textarea><!-- ���� -->
<textarea name="SendFileBccHtml" style="display:none"><%=session.getAttribute("SendFileBccHtml")%></textarea><!-- ���� --> 
<div id="alertbox1" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align="center" id="showAlert"></td>
		</tr>
	</table>
</div>  




 <form name="frm" mothed="post" action="">
<table width="750" border="0" cellspacing="0" cellpadding="0">

    <tr>
    	<td colspan="6" height="5"><img src="<%=path%>/images/kongbai.jpg" width="11" height="5" /></td>
    </tr>
    <tr>
      <td height="30">&nbsp;</td>
      <td colspan="3" rowspan="2" valign="top">
        </td>
      <td headers="11">&nbsp;</td>
      <td rowspan="2" valign="top"><table width="789" border="0" align="center" cellpadding="2" cellspacing="1" class="table_bgcolor">
          <tr>
            <td height="44" colspan="2" bgcolor="#FFFFFF"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" onclick="javascript:mailsend()"/> <img src="<%=path%>/images/mail_save.JPG" width="102" height="25" /></td>
            <td width="197" rowspan="3" valign="top" bgcolor="#FFFFFF" class="blue3-12">
            <iframe name="userlistfrm" src="<%=path%>/servlet/MailGroupListServlet" width="92%" height="100%" scrolling="no" frameborder="0" framespacing="0"></iframe>

            </td>
          </tr>
          

          <tr>
          	<td colspan="2" bgcolor="#FFFFFF">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <input type="hidden" name="sendType" />
      		  <tr>
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">�ռ���</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490" style="width:500px"><span id="sendto" class="sendToText" onselectstart="return(false)" state="0"></span><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(0)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand">&nbsp;<img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,0,'')" alt="չ��" title="չ���ռ����б�" style="cursor: hand"><input type="button" style="cursor:hand" class="message_title" value="ѡ���ռ���" onClick="javascript:_selectPerson()"></td>
                        </tr>
                    </table></td>
                </tr>
                    		  <tr>
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">������</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490"><span id="sendcc" class="sendToText" onselectstart="return(false)" state="0"></span><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(1)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand">&nbsp;<img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,1,'ccbook')" alt="չ��" title="չ���ռ����б�" style="cursor: hand"><input type="button" style="cursor:hand" class="message_title" value="ѡ������" onClick="javascript:_selectcc()"></td>
                        </tr>
                    </table></td>
                </tr>
                      		  <tr>
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">�ܳ���</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490"><span id="sendbcc" class="sendToText" onselectstart="return(false)" state="0"></span><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(2)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand">&nbsp;<img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,2,'bccbook')" alt="չ��" title="չ���ռ����б�" style="cursor: hand"><input type="button" style="cursor:hand" class="message_title" value="ѡ���ܳ���" onClick="javascript:_selectbcc()"></td>
                        </tr>
                    </table></td>
                </tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">����(<span class="STYLE1">*</span>)</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><input name="topic" type="text" class="biankuang-blue" size="68" /></td>
                        </tr>
                    </table></td>
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
                   	  <td width="78" align="right" valign="top" class="message_title">���ݸ�Ҫ<br />
               	      (<span class="STYLE1">500������</span>)</td>
                      <td width="503"><textarea name="content" cols="68" rows="10" class="biankuang-blue"><%=CommUtil.unformathtm(sendFileBean.getContent())%></textarea></td>
                    </tr>
                    <tr>
                      <td height="33"></td>
                      <td class="message_title"><input name="isSent" type="checkbox" value="" checked/>
                        ����һ���ڷ�����&nbsp;&nbsp;
                        <input name="isRe" type="checkbox" value="" />
                      �յ��ʼ�ʱ���һظ� </td>
                   </tr>
                    <tr>
                    	<td colspan="5">
                        	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                                <tr><td height="1" class="table_bgcolor" width="100%"></td></tr>
                             </table>                        </td>
                    </tr>
                    <tr>
                    	<td height="44" align="right" class="message_title">��Ӹ���</td>
                   	  <td class="message_title"><img src="<%=path%>/images/icon_attachment.gif" width="16" height="16"/><a href="javascript:_attachfile()">�����Ӹ���</a></td>
                   </tr>
                   <%
												for(int index = 0; index < sendFileBean.filenumber();index ++ ){
												AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
					%>
									<tr>
                    	<td  align="left" class="message_title"><%=attachFileBean.getFileOriginName()%></td>
                   	  
                   </tr>
									<%
												}//end for
									%>
                   
                 </table>            </td>
                 
          </tr>
	  
          <tr>
            <td height="48" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="blue3-12"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" onclick="javascript:mailsend()" /> <img src="<%=path%>/images/mail_save.JPG" width="102" height="25" /></td>
          </tr>
   </form>         
      </table></td>
        
    </tr>
    <tr>
    	<td width="11">&nbsp;</td>
        <td width="14" headers="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
        
    </tr>
    
    
    <tr>
    	<td colspan="6" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 

	  

</form>
<script>
function frameautoheight(){
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
}
frameautoheight();
</script>
</body>
</html>
