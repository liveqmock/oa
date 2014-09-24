<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachHelper"%>
<%@page import="javax.mail.internet.*"%>

<%
HttpSession receSession = request.getSession();
List recePersonList = (List)receSession.getAttribute("recePersonList");
List tolist = (List)recePersonList.get(0);
List cclist = (List)recePersonList.get(1);
List bcclist = (List)recePersonList.get(2);
MimeMessage fileMessage = (MimeMessage)recePersonList.get(3);
//MimeMessage fileMessage = (MimeMessage)request.getAttribute("fileMsg");
SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
String folder = sendFileBean.getfolder();
String type = sendFileBean.getType();
String mailName = sendFileBean.getMailName();
%>
<HTML><HEAD><TITLE>文件传递</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<STYLE>
.mailText {
	/*收件人显示框样式*/
	width:510px;
	height:100%;
}
</STYLE>
<script>

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
	    //弹出正在发送的提示框
		_showAlertBox('alertbox1','','show');
		
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFromDraftServlet";
		document.sendForm.submit();
}

function _goback(type){
	document.location.href="<%=request.getContextPath()%>/servlet/MessageListServlet?type="+type+"&folder=<%=folder%>";
}

function _modify(){
    location.href = "<%=request.getContextPath()%>/filetransfer/fromDraftSendModify.jsp";
}

function attachfile(){
	document.sendForm.action = "<%=request.getContextPath()%>/servlet/AttachFileServlet?donext=5";
	document.sendForm.submit();
}

function updateInfo(){
   var foldername = "<%=sendFileBean.getfolder()%>";
   <%
		StringBuffer tostr = new StringBuffer();
		StringBuffer ccstr = new StringBuffer();
		StringBuffer bccstr = new StringBuffer();
        if(tolist!=null||tolist.size()!=0){
            for(int i = 0; i < tolist.size(); i ++){
                if(i!=0) tostr.append(",");
	            tostr.append(tolist.get(i));
            }
   %>
			to.innerHTML = "<%=tostr.toString()%>";
   <%
       }
       if(cclist!=null||cclist.size()!=0){
            for(int i = 0; i < cclist.size(); i ++){
                if(i!=0) ccstr.append(",");
	            ccstr.append(cclist.get(i));
            }
   %>
			cc.innerHTML = "<%=ccstr.toString()%>";
			if(cc.innerHTML!=""){
				ccTr1.style.display = "block";
				ccTr2.style.display = "block";			
			}
   <%            
       }
       if(bcclist!=null||bcclist.size()!=0){
            for(int i = 0; i < bcclist.size(); i ++){
                if(i!=0) bccstr.append(",");
	            bccstr.append(bcclist.get(i));
            }
   %>
			bcc.innerHTML = "<%=bccstr.toString()%>";
			if(bcc.innerHTML!=""){
				bccTr1.style.display = "block";
				bccTr2.style.display = "block";			
			}			
   <%            
       }           		
	%>
	topic = "";
	<%if(!("".equals(sendFileBean.getTopic()))&&sendFileBean.getTopic()!=null){%>
	   	topic = "<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-13)%>";
	<%}%>
	document.sendForm.topic.value = topic;
}
</SCRIPT>

</head>
<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif">
<form name="sendForm" method="post" action="">
      
<div id="alertbox1" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align=center>正在发送文件，请稍等......</td>
		</tr>
	</table>
</div>      
      
<div align="center" >
<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">发送文件</td>
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
                          <td nowrap width="20%" height="22" class="text-01"><div align="right"><strong>收件人： </strong></div></td>
                          <td width="2" rowspan="18" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../userweb/images/bg-18.gif" width="2" height="2"></td>
                          <td width="80%" height="22" bgcolor="F2F9FF" class="text-01"><span id="to" class="mailText"></span></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">主题：</div></td>
                          <td nowrap bgcolor="F2F9FF" >
                              <input type="text" name="topic"  size="50" class=txt2>&nbsp;&nbsp;
                              <input type="checkbox" name="isRe" value="checked1" checked>是否需要回执&nbsp;&nbsp;
                              <input type="checkbox" name="isSent" value="checked2" checked>保存到发件箱
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                       		<tr id="ccTr1" style="display:none">
                        	<td height="22" bgcolor="D8EAF8" class="text-01"><div align="right" class="title-04"> <strong>抄送： </strong> </div></td>
                        	<td bgcolor="F2F9FF" class="text-01"><span id="cc" class="mailText"></span></td>
                       	</tr>
                       	<tr id="ccTr2" style="display:none">
                        	<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        	<td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      	</tr>
                      	<tr id="bccTr1" style="display:none">
                        	<td height="22" bgcolor="D8EAF8" class="text-01"><div align="right" class="title-04"> <strong>密送： </strong> </div></td>
                        	<td bgcolor="F2F9FF" class="text-01"><span id="bcc" class="mailText"></span></td>
                      	</tr>
                      	<tr id="bccTr2" style="display:none">
                        	<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        	<td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      	</tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">内容概要&#65306;<br>( <font color="red">500字以内</font> )</div></td>
                          <td bgcolor="F2F9FF" class="text-01">
                          	<textarea name="content" cols="70" rows="10" class=txt2><%=sendFileBean.getContent()%></textarea>
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        </tr>                        
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">附加文件：</div></td>
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
                          <td nowrap height="22"><div align="right" class="text-01">文件大小：</div></td>
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
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
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
          </table>
</td></tr>
</table>                
          <p>
			<a href="javascript:_sendFile()"><img src="<%=request.getContextPath()%>/images/botton-doc_send.gif" style="cursor:hand" border="0"></a>&nbsp;&nbsp;
			<a href="javascript:_modify()"><img src="<%=request.getContextPath()%>/images/filetransfer/button-modifysend.gif" style="cursor:hand" border="0"></a>&nbsp;&nbsp;
			<a href="javascript:_goback('<%=type%>')"><img src="<%=request.getContextPath()%>/images/botton-return.gif" border="0"></a>
          </p>
</div>
</form>
</body>
<script>
updateInfo();
</script>
</html>