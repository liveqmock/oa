<%@ page contentType="text/html; charset=GB2312" %>
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
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>

<%
HttpSession receSession = request.getSession();
List recePersonList = (List)receSession.getAttribute("recePersonList");
String path = request.getContextPath();
String time = TimeUtil.getCurrentDate("yyyy��MM��dd�� ")+TimeUtil.getCurrentWeek();
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
	    //�������ڷ��͵���ʾ��
		_showAlertBox('alertbox1','','show');
		
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFromDraftServlet";
		document.sendForm.submit();
}

function _goback(type){
	document.location.href="<%=request.getContextPath()%>/servlet/MessageListServlet?type="+type+"&folder=<%=folder%>";
}

function _modify(){
    location.href = "<%=request.getContextPath()%>/mail/FromDraftSendModify.jsp";
}

function attachfile(){
	document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFileAttachFileServlet?donext=5";
	document.sendForm.submit();
}
to.innerHTML="13213132";
alert(document.all.to);
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

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body>
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



<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
    <input type="hidden" name="donext" value=""/>
    <table border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="537" class="top_logo" height="85"></td>
    <td class="top_back" height="85" width="121">&nbsp;</td>
    <td width="325" class="top_back">
    	<table border="0" cellpadding="0" cellspacing="0">
        	<tr>
        	  <td class="top_function" width="390" height="31"></td>
        	</tr>
            <tr>
              <td class="top_left" width="390" height="54"></td>
            </tr>
        </table>    
    </td>
    </tr>
    </table>
    </td>
    <td width="10" >&nbsp;</td>
  </tr>
</table>

<table width="1003" height="29" border="0" cellpadding="0" align="center" cellspacing="0">
	<tr>
    	<td width="10"></td>
        <td>
        	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="top_back">
            	<tr>
                	<td width="134" class="top_left_buttom" height="29"></td>
                  <td width="60%" class="top_back_buttom">
               	  <table width="98%">
                        <tr>
                            <td width="34%" height="20" class="message_zhuanti"><%=time %></td>
                          <td width="42%" align="right" class="content"><input name="searchfield2" type="text" class="biankuang" size="26" value="����������ؼ���"/></td>
                          <td width="9%" class="message_zhuanti" style="cursor:hand">&gt;&gt;����</td>
                          <td width="15%" class="content"><a href="searchList.html" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; �߼�����</a></td>
                      </tr>
                    </table>
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="???��??????????????" bgcolor="#FFFFFF">
                      <tr>
                        <td width="30"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="red" style="cursor:hand;" onClick="changeStyle('red')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#d0e9cb" style="cursor:hand;" onClick="changeStyle('lake')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ffd4ad" onClick="changeStyle('orange')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ddc99d" onClick="changeStyle('brown')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#afd5ae" onClick="changeStyle('green')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#CCCCCC" onClick="changeStyle('grey')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
          </table>
        </td>
        <td width="10"></td>
    </tr>
</table>
 <form name="frm" mothed="post" action="">
<table width="1003" border="0" cellspacing="0" cellpadding="0">

    <tr>
    	<td colspan="6" height="5"><img src="<%=path%>/images/kongbai.jpg" width="11" height="5" /></td>
    </tr>
    <tr>
      <td height="30">&nbsp;</td>
      <td colspan="3" rowspan="2" valign="top">
        <TABLE width="180" height="57" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
            <TR>
              <TD width="90" align="right" bgcolor="#FFFFFF">
              <table width="100%" cellspacing="5">
                <tr><td width="36" align="right" style="cursor:hand;">
              <img src="<%=path%>/images/mail_receive.JPG" width="26" height="26" /></TD>
              <TD width="45" align="left" class="message_title_bold" style="cursor:hand;"><a href="index.html" style="text-decoration:none" class="message_title_bold">δ��<br />
                �ʼ�</a></TD>
              </tr>
              </table>
              <TD width="90" align="right" bgcolor="#FFFFFF"><table width="100%" cellspacing="5">
                <tr>
                  <td width="36" align="right" style="cursor:hand"><a href="SendMail1.html"><img src="<%=path%>/images/mail_send.JPG" width="26" height="26" border="0" /></a></td>
                  <td width="45" align="left" style="cursor:hand;"><a href="SendMail1.html" class="message_title_bold" style="text-decoration:none"><a href="<%=path%>/servlet/SendFileFileTransferServlet">д��</a></td>
                </tr>
              </table></TD>
            </TR>
        </TABLE>
        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="<%=path%>/images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>
        <TABLE width="180" height="30" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
       	  <tr>
            <td bgcolor="#FFFFFF">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="60" height="30" align="right" bgcolor="#FFFFFF" class="message_title">�����ļ���</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<select name="select" style="width:90px;">
                    <option>ȫ��</option>
                    <option>�ռ���</option>
                    <option>������</option>
                    <option>�ݸ���</option>
                    <option>������</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="60" height="30" align="right" bgcolor="#FFFFFF" class="message_title">�Ƿ��Ѷ�</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<select name="select" style="width:90px;">
                    <option>ȫ��</option>
                    <option>δ���ʼ�</option>
                    <option>�Ѷ��ʼ�</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="60" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">����ʱ���</td>
                  <td width="95" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
                <tr>
                  <td width="60" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">��</td>
                  <td width="95" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
            </table>            </td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"  height="30">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="131" align="right" bgcolor="#FFFFFF"><input name="textfield2222" type="text" class="biankuang-blue" size="15" value="����ؼ���"/></td>
                  <td width="47" align="center" nowrap="nowrap" bgcolor="#E0EDF8" class="block_title" style="cursor:hand;">����</td>
                </tr>
            </table>            </td>
          </tr>
        </TABLE>
        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="<%=path%>/images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>
        <table width="180" border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
          <tr>
            <td width="196"><table width="100%" height="48" border="0" cellpadding="0" cellspacing="0" class="table_bgcolor">
                <tr>
                  <td height="20" align="left"  bgcolor="#E0EDF8" class="block_title" colspan="3"> &nbsp;&nbsp;�ļ���</td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td width="55" align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_inbox.JPG" width="15" height="16" />&nbsp;</td>
                  <td width="134" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Inbox&search=no" style="cursor:hand">�ռ���</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_caogao.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title">�ݸ���</td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_outbox.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Sent&search=no" style="cursor:hand">������</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title">������&nbsp;&nbsp;<span class="grap2-12">[���]</span></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_folder.JPG" width="19" height="17" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title">�Զ���</td>
                </tr>
                <tr>
                  <td height="25" colspan="3" bgcolor="#E0EDF8" class="block_title">&nbsp;&nbsp;�Զ����ļ���&nbsp;&nbsp;&nbsp;&nbsp;<a href="BoxListServlet" style="text-decoration:none" class="message_title_bold" style="cursor:hand">[����]</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_folder.JPG" width="19" height="17" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title">�Զ���</td>
                </tr>
            </table></td>
          </tr>
      </table></td>
      <td headers="11">&nbsp;</td>
      <td rowspan="2" valign="top"><table width="789" border="0" align="center" cellpadding="2" cellspacing="1" class="table_bgcolor">
          <tr>
            <td height="44" colspan="2" bgcolor="#FFFFFF"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" onclick="javascript:mailsend()"/> <img src="<%=path%>/images/mail_save.JPG" width="102" height="25" onclick="javascript:savetodraft()" /></td>
            <td width="197" rowspan="3" valign="top" bgcolor="#FFFFFF" class="blue3-12">
            <table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
            	<tr>
                	<td class="block_title">
            	&nbsp;&nbsp;����ͨѶ¼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[����]<br />
            	<table width="100%" height="400" border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
               	  <tr>
                  	<td bgcolor="#FFFFFF" valign="top">
                    	<div style="overflow:auto;height:400px">
                  		<table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                        	<tr>
                            	<td height="25" width="30" align="right" valign="middle"><img id="contactImg" src="<%=path%>/images/mail_down.jpg" />&nbsp;</td>
                                <td class="table_title"><a href="javascript:_updown('contactImg')">������ϵ��</a></td>
                            </tr>
                            <tr>
                            	<td height="25" width="30" align="right" valign="middle"><img id="kaoheImg" src="<%=path%>/images/mail_up.jpg" />&nbsp;</td>
                                <td class="table_title"><a href="javascript:_updown('kaoheImg')">������Ŀ��</a></td>
                            </tr>
                            <tr>
                            	<td height="20" width="30" align="right" valign="middle">&nbsp;</td>
                                <td class="foot_message">&nbsp;<span class="black-12">??????</span>(??????>>????????)</td>
                            </tr>
                            <tr>
                            	<td height="20" width="30" align="right" valign="middle">&nbsp;</td>
                                <td class="foot_message">&nbsp;<span class="black-12">?????��</span>(??????>>????????)</td>
                            </tr>
                            <tr>
                            	<td height="20" width="30" align="right" valign="middle">&nbsp;</td>
                                <td class="foot_message">&nbsp;<span class="black-12">??????</span>(????????>>??????)</td>
                            </tr>
                            
                            <tr>
                            	<td height="20" width="30" align="right" valign="middle">&nbsp;</td>
                                <td class="foot_message">&nbsp;<span class="black-12">??????</span>(?��????>>??????)</td>
                            </tr>
                            <tr>
                            	<td height="20" width="30" align="right" valign="middle">&nbsp;</td>
                                <td class="foot_message">&nbsp;<span class="black-12">???��??</span>(??????��?����??>>�㨬????)</td>
                            </tr>
                            <tr>
                            	<td height="25" width="30" align="right" valign="middle"><img id="OAImg" src="<%=path%>/images/mail_down.jpg" />&nbsp;</td>
                                <td class="table_title"><a href="javascript:_updown('OAImg')">OAϵͳ��</a></td>
                            </tr>
                        </table>
                        </div>
                        </td>
               	  </tr>
                </table>                </td>
                </tr>
                </table>
                	<input type="hidden" name="addto"/><input type="hidden" name="addcc"/><input type="hidden" name="addbcc"/>
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
                          <td width="490" style="width:500px"><span id="to" class="mailText"></span></td>
                        </tr>
                    </table></td>
                </tr>
                    		  <tr style="display:none">
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">������</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490"><span id="cc" class="mailText"></span></td>
                        </tr>
                    </table></td>
                </tr>
                      		  <tr style="display:none">
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">�ܳ���</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490"><span id="bcc" class="mailText"></span></td>
                        </tr>
                    </table></td>
                </tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">����(<span class="STYLE1">*</span>)</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><input name="topic" type="text" class="biankuang-blue" size="68" value=""/></td>
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
                      <td width="503"><textarea name="content" cols="68" rows="10" class="biankuang-blue"><%=sendFileBean.getContent()%></textarea></td>
                    </tr>
                    <tr>
                      <td height="33"></td>
                      <td class="message_title"><input name="isSent" type="checkbox" value="checked2" checked/>
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
                    <tr>
                    	<td height="44" align="right" class="message_title">��Ӹ���</td>
                   	  <td class="message_title"><img src="<%=path%>/images/icon_attachment.gif" width="16" height="16"/><a href="javascript:attachfile()">�����Ӹ���</a></td>
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
            <td height="48" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="blue3-12"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" onclick="javascript:_sendFile()" style="cursor:hand"/> <img src="<%=path%>/images/mail_save.JPG" width="102" height="25" onclick="javascript:_modify()" style="cursor:hand"/></td>
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

	  
<table width="1013" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="55"><div align="center" class="foot_message">��ý�����ݿ�ϵͳ����ý�������ϵͳ��eNewsϵͳ���������ű༭ϵͳ�����ڷ����ý��༭ϵͳ�������༭ϵͳ����Ƶ����ϵͳ��������ò�ѯϵͳ����Ϣ����ý��ɱ�ϵͳ<br />
    ȫ�����ǹ���ϵͳ��ͼ��/����ͼ��ϵͳ��ͨ�Ź���ϵͳ������������ϵͳ��ͼƬ/ͼ��༭ϵͳ��Ӫ������ϵͳ������������ϵͳ</div></td>
  </tr>
  <tr>
    <td height="48" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 ��Ȩ���С���<br />
������λ���»��缼���֡������������ҳ�����齫������ʾ���ķֱ��ʵ�Ϊ1024*768��<br />
    </div></td>
  </tr>
</table>
<script>
updateInfo();
</script>
</form>
</body>
</html>
