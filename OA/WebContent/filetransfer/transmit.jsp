<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler" %>
<%
SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
//用于删除待办
String topic = sendFileBean.getTopic();
topic = topic.substring(0,topic.length()-13);
FiletransferSetHandler ftsHandler = new FiletransferSetHandler();
String folderSort = sendFileBean.getfolder();
//为inbox时表示是搜索或查询
if(!("inbox".equals(folderSort))&&!("Inbox".equals(folderSort))&&!("Sent".equals(folderSort))&&!("Draft".equals(folderSort))&&!("Junk".equals(folderSort))){
	folderSort = ftsHandler.decodeBase64(folderSort);
}
%>
<HTML><HEAD><TITLE>文件传递</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script>
function _selectPerson(){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=sendtoperson','addressbook','width=700,height=550,left=170,top=110,scrollbars=yes,resizable=yes');
}

function _addPerson(usernamestring){
	document.sendForm.sendto.value = usernamestring;
	window.bottomto.innerHTML = usernamestring;
	return true;
	
}
function check(){
	if(document.sendForm.sendto.value == ""){
		alert("请填写接收人");
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
		//弹出正在发送的提示框
		_showAlertBox('alertbox3','','show');
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/TransmitServlet";
		document.sendForm.submit();
	}
}

function _modify(){
    var receperson = document.sendForm.sendto.value;
    location.href = "<%=request.getContextPath()%>/filetransfer/transmitAfterModify.jsp?receperson="+receperson;
}

function _goback(){
	location.href = "<%=request.getContextPath()%>/servlet/ShowMailServlet?type=<%=sendFileBean.getmailtype()%>&folder=<%=folderSort%>&mailName=<%=sendFileBean.getMailName()%>";
}
<!--
function changeRadioSelected(radioName,radioValue){
	for (var i=0;i<document.sendForm.elements.length;i++) {
		var e=document.sendForm.elements[i];
		if (e.name==radioName){
			if(radioValue == e.value){
				e.click();
			}
		}
	}
}
-->

</SCRIPT>

</head>
<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif">
<form name="sendForm" method="post" action="">

<div id="alertbox3" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align=center>正在转发文件，请稍等......</td>
		</tr>
	</table>
</div> 

<div align="center" >
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">转发文件</td>
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
                          <td nowrap width="20%" height="22" class="text-01"><div align="right">接收人(<font color='red'>*</font>)：</div></td>
                          <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../userweb/images/bg-18.gif" width="2" height="2"></td>
                          <td nowrap width="80%" height="22" bgcolor="F2F9FF" class="text-01"><textarea name="sendto" cols="40" rows="1" class=txt2 readonly></textarea>
&nbsp;&nbsp;
<img src="<%=request.getContextPath()%>/images/filetransfer/button-rece.gif" style="cursor:hand" align="absmiddle"  onClick="javascript:_selectPerson()">
<input type="checkbox" name="isRe" value="checked1" checked>是否需要回执&nbsp;
<input type="checkbox" name="isSent" value="checked2" checked>保存到发件箱
                </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
						<tr>
							<td colspan="3" height="35" align="center">
								<img src="<%=request.getContextPath()%>/images/filetransfer/button-transmit.gif" style="cursor:hand" onclick="javascript:_sendFile();">
								<img src="<%=request.getContextPath()%>/images/filetransfer/button-modifytransmit.gif" style="cursor:hand" onclick="javascript:_modify();">
								<img src="<%=request.getContextPath()%>/images/botton-return.gif" style="cursor:hand" onclick="javascript:_goback()">
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
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table><br>

          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">所有接收人</td>
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
							<td width="20%" height="22" align="right">接收人：</td>
							<td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../userweb/images/bg-18.gif" width="2" height="2"></td>
							<td width="80%" class="text-01" id="bottomto"></td>
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