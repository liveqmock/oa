<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachHelper"%>
<%

SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
String folder = sendFileBean.getfolder();
String type = sendFileBean.getType();

%>
<HTML><HEAD><TITLE>�ļ�����</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/address.js"></SCRIPT>
<script>
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

function check(){
	if(!setPersonToSend()){
		alert("����д�ռ���");
		return false;
	}
	if(document.sendForm.topic.value == ""){
		if(confirm("��û����ӱ��⣡��ȷ��ʹ��ȱʡ���⻹���Լ���д����")==true)
		{ document.sendForm.topic.value = "������";
		  return true;};
		
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

function _sendFile(){
	if(check()){
	    //�������ڷ��͵���ʾ��
		_showAlertBox('alertbox1','','show');
		
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFile1Servlet";
		document.sendForm.submit();
	}
}

function attachfile(){
	getSendHtml();
	document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFileAttachFileServlet?donext=4";
	document.sendForm.submit();
}

function _toShowmessage(){
	document.location.href="<%=request.getContextPath()%>/servlet/MessageListServlet?type=<%=type%>&folder=Draft";
}

function _load(){
	var sendStr = "";
	var ccStr ="";
	var bccStr = "";

	sendStr = document.all("sendHtml").value;
	ccStr =document.all("ccHtml").value;
	bccStr =  document.all("bccHtml").value;


//�����ͽ��д���
	if (showChar){
		document.getElementById(sendToId).innerHTML = sendStr.replaceByString("<img src=\"/oabase/images/person.gif\">","<font face=\"Webdings\" color=\"#009900\">m</font>");
		document.getElementById(sendccId).innerHTML = ccStr.replaceByString("<img src=\"/oabase/images/person.gif\">","<font face=\"Webdings\" color=\"#009900\">m</font>");
		document.getElementById(sendbccId).innerHTML = bccStr.replaceByString("<img src=\"/oabase/images/person.gif\">","<font face=\"Webdings\" color=\"#009900\">m</font>");
	}else{
		document.getElementById(sendToId).innerHTML = sendStr;
		document.getElementById(sendccId).innerHTML = ccStr;
		document.getElementById(sendbccId).innerHTML = bccStr;
	}
	<%
	 if (sendFileBean.getisRe() != null && "checked1".equals(sendFileBean.getisRe())){
	%>
		document.all("isRe").checked = true;
	<%
	}else{
	%>
		document.all("isRe").checked = false;
	<%}%>
	<%
	 if (sendFileBean.getisSent() != null && "checked2".equals(sendFileBean.getisSent())){
	%>
		document.all("isSent").checked = true;
	<%
	}else{
	%>
		document.all("isSent").checked = false;
	<%}%>
	//<%=request.getAttribute("newSendFile")%>
	getAllPerson(0);
	getAllPerson(1);
	getAllPerson(2);

	topic = "";
	<%if(!("".equals(sendFileBean.getTopic()))&&sendFileBean.getTopic()!=null){%>
	   	topic = "<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-13)%>";
	<%}%>
	document.sendForm.topic.value = topic;
	
	//�����ļ�����
	document.all("content").value = document.all("contentOld").value.replaceByString("<br>","\n");
}


//�޸��Ժ�ĳ���
//������span��ID
var urlPath = "<%=request.getContextPath()%>";
</SCRIPT>

</head>
<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif" onload="_load()">
<form name="sendForm" method="post" action="">
<!--����Ϊ����Ӹ�����ʱ�򴫵��ռ�����Ϣ��-->

<% 
	if ("1".equals(request.getParameter("atta"))){
%>

<textarea name="sendHtml" style="display:none"><%=session.getAttribute("SendFileSendHtml")%></textarea><!-- �ռ��� -->
<textarea name="ccHtml" style="display:none"><%=session.getAttribute("SendFileCcHtml")%></textarea><!-- ���� -->
<textarea name="bccHtml" style="display:none"><%=session.getAttribute("SendFileBccHtml")%></textarea><!-- ���� --> 
<%
}else{
%>    
<textarea name="sendHtml" style="display:none"><%=request.getParameter("sendHtml")%></textarea><!-- �ռ��� -->
<textarea name="ccHtml" style="display:none"><%=request.getParameter("ccHtml")%></textarea><!-- ���� -->
<textarea name="bccHtml" style="display:none"><%=request.getParameter("bccHtml")%></textarea><!-- ���� --> 
<%
}
%>
<input type="hidden" name="addPerson_person">
<input type="hidden" name="addPerson_group">
<input type="hidden" name="addPerson_org">
<input type="hidden" name="addcc_person">
<input type="hidden" name="addcc_group">
<input type="hidden" name="addcc_org">
<input type="hidden" name="addbcc_person">
<input type="hidden" name="addbcc_group">
<input type="hidden" name="addbcc_org">


<input type="hidden" name="sendType" value="">
<!-- ���ʼ��ķ�ʽ -->
<input type="hidden" name="sendMail" value="1">
<input type="hidden" name="AttFiles">
<div id="alertbox1" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align=center>���ڷ����ļ������Ե�......</td>
		</tr>
	</table>
</div>      
<div align="center" >
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">�����ļ�</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td nowrap width="20%" height="22" class="text-01"><div align="right">�ռ��ˣ�</div></td>
                          <td width="2" rowspan="18" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../userweb/images/bg-18.gif" width="2" height="2"></td>
                          <td nowrap width="80%" height="22" bgcolor="F2F9FF" class="text-01">
                              <table border="0" cellspacing="0" cellpadding="0"><tr><td style="width:500px"><span id="sendto" class="sendToText" onselectstart="return(false)" state="0">
							  </span></td><td valign="bottom" nowrap style="width:100%"><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(0)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯" style="cursor: hand">&nbsp;<img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,0,'')" alt="չ��" title="չ���ռ����б�" style="cursor: hand">&nbsp;
                              <img src="<%=request.getContextPath()%>/images/filetransfer/button-rece.gif" style="cursor:hand" align="middle"  onClick="javascript:_selectPerson()">
                          	</td></tr></table>
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">���⣺</div></td>
                          <td nowrap bgcolor="F2F9FF" >
                              <input type="text" name="topic"  size="50" class=txt2>&nbsp;&nbsp;
                              <input type="checkbox" name="isRe" value="checked1" checked>�Ƿ���Ҫ��ִ&nbsp;&nbsp;
                              <input type="checkbox" name="isSent" value="checked2" checked>���浽������
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr>
                          <td nowrap width="20%" height="22" class="text-01"><div align="right">���ͣ�</div></td>
                          <td nowrap width="80%" height="22" bgcolor="F2F9FF" class="text-01">
                        <table border="0" cellspacing="0" cellpadding="0" ><tr><td><span id="sendcc" class="sendToText" onselectstart="return(false)" state="0">
							  </span></td><td valign="bottom" nowrap><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(1)" alt="ɾ��" title="ɾ��������ѡ����˻���֯" style="cursor: hand"><a name="bccBook">&nbsp;</a><img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,1,'ccBook')" alt="չ��" title="չ�������б�" style="cursor: hand">&nbsp;
                              <img src="<%=request.getContextPath()%>/images/filetransfer/button-cc.gif" style="cursor:hand" align="middle"  onClick="javascript:_selectcc()">
                          </td></tr></table>   
						</td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr>
                          <td nowrap width="20%" height="22" class="text-01"><div align="right">���ͣ�</div></td>
                          <td nowrap width="80%" height="22" bgcolor="F2F9FF" class="text-01">
                              <table border="0" cellspacing="0" cellpadding="0" ><tr><td><span id="sendbcc" class="sendToText" onselectstart="return(false)" state="0">
							  </span></td><td valign="bottom" nowrap><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(2)" alt="ɾ��" title="ɾ��������ѡ����˻���֯" style="cursor: hand">&nbsp;<img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,2,'bccBook')" alt="չ��" title="չ�������б�" style="cursor: hand">&nbsp;
                              <img src="<%=request.getContextPath()%>/images/filetransfer/button-bcc.gif" style="cursor:hand" align="middle"  onClick="javascript:_selectbcc()">
                          </td></tr></table>
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">���ݸ�Ҫ&#65306;<br>( <font color="red">500������</font> )</div></td>
                          <td bgcolor="F2F9FF" class="text-01">
                          	<textarea name="contentOld" cols="70" rows="14" class=txt2 style="display:none"><%=sendFileBean.getContent()%></textarea>
                          	<textarea name="content" cols="70" rows="14" class=txt2></textarea>
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">�����ļ���</div></td>
                          <td nowrap height="22" bgcolor="F2F9FF" class="text-01">
                            <table width="90%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="100%" class="lineheigh">
<%
for(int index = 0; index < sendFileBean.filenumber(); index ++){
	AttachFileBean atachFileBean = sendFileBean.getAttachFile(index);
%>
	<nobr><%=atachFileBean.getFileOriginName()%>(<%=AttachHelper.getFileSize(atachFileBean.getFilesize())%>)&nbsp;</nobr>
<%
}
%>
                                </td>
                              </tr>
                              
                          </table></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">�ļ���С��</div></td>
                          <td nowrap height="22" bgcolor="F2F9FF" class="text-01">
                            <table width="90%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="3%"></td>
                                <td width="17%" align="left"><%=AttachHelper.getFileSize(sendFileBean.getTotleFilesize())%></td>
                                <td width="45%"><img src="<%=request.getContextPath()%>/images/filetransfer/button-addattach.gif" onclick="javascript:attachfile()" style="cursor:hand"></td>
                                <td width="35%"></td>
                              </tr>
                              </table></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
						<tr>
							<td colspan="3" height="35" align="center">
								<img src="<%=request.getContextPath()%>/images/botton-doc_send.gif" style="cursor:hand"  onclick="javascript:_sendFile();">
								<img src="<%=request.getContextPath()%>/images/filetransfer/cancel.gif" style="cursor:hand"  onclick="javascript:_toShowmessage();">
							</td>
						</tr>
                        <tr>
                          <td colspan="3" background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
            </table>
            </td></tr>
          </table><br>

          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">���н�����</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="20%" height="22" align="right">�����ˣ�</td>
							<td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../userweb/images/bg-18.gif" width="2" height="2"></td>
							<td width="80%" class="text-01" id="bottomto"></td>
						</tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
						<tr>
							<td align="right" height="22">�����ˣ�</td>
							<td class="text-01" id="bottomcc"></td>
						</tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
						<tr>
							<td align="right" height="22">�����ˣ�</td>
							<td class="text-01" id="bottombcc"></td>
						</tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
					</table>
					</td></tr>
				</table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="center"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
            </table>
            </td></tr>
          </table><br><br>
</td></tr>
</table>                

</div>
</form>
</body>

</html>