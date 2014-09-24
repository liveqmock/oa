<%@ page contentType="text/html; charset=GB2312" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="java.util.*"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachHelper"%>
<%@page import="javax.mail.internet.MimeUtility"%>
<%@page import="com.icss.oa.util.StringUtility"%>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%
String path = request.getContextPath();
String time = TimeUtil.getCurrentDate("yyyy��MM��dd�� ")+TimeUtil.getCurrentWeek();
MimeMessage fileMessage = (MimeMessage)request.getAttribute("fileMsg");
String folder = (String)request.getAttribute("folder");
String content = (String)request.getAttribute("content");
String type = (String)request.getAttribute("type");
String mailName = (String)request.getAttribute("mailName");
String username = (String)request.getAttribute("username");

List tolist = (List)request.getAttribute("tolist");
List cclist = (List)request.getAttribute("cclist");
//�Ķ���¼����������
List readPersonlist = (List)request.getAttribute("readPersonlist");
//������ʼ��Ľ�����
String recePerson = (String)request.getAttribute("recePerson");

String _curPageNum = request.getParameter("curPageNum");
//�����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
String sortname = request.getParameter("sortname");
//����ʽ Ĭ��Ϊ����
String sorttype = request.getParameter("sorttype");

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
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/include/xmlextras.js"></SCRIPT>
<script language="javascript">
function changeStyle(id){//�л���ʽ
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

function _showperson(){
	window.open('<%=request.getContextPath()%>/servlet/GetPersonInfoServlet?from=<%=fileMessage.getFrom()[0].toString()%>')
}

function _showrec(){
	var rec=document.getElementById('rectr');
	var link=document.getElementById('reclink');
	rec.style.display = "block";
	link.style.display = "none";
}

function _hiderec(){
	var rec=document.getElementById('rectr');
	var link=document.getElementById('reclink');
	rec.style.display = "none";
	link.style.display = "block";
}
initstyle();
</script>
</head>

<body>
<form name="printheader" method="post">
<input  type="hidden"name="subject"  value="<%=StringUtility.replace(fileMessage.getSubject(),"\"","\'")%>">
<input type="hidden" name="folder" value="<%=folder%>">
<input type="hidden" name="mailName" value="<%=mailName%>">
<input type="hidden" name="messageid" value="<%=mailName%>">
<input type="hidden" name="content" value="<%=content%>">
<input type="hidden" name="from" value="<%=MimeUtility.decodeText(fileMessage.getFrom()[0].toString())%>">
<input type="hidden" name="type" value="<%=type%>">
<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
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
                            <td width="34%" height="20" class="message_zhuanti"><%=time%></td>
                          <td width="42%" align="right" class="content"><input name="searchfield2" type="text" class="biankuang" size="26" value="����������ؼ���"/></td>
                          <td width="9%" class="message_zhuanti" style="cursor:hand">&gt;&gt;����</td>
                          <td width="15%" class="content"><a href="searchList.html" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; �߼�����</a></td>
                      </tr>
                    </table>
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="���ѡ��ҳ��ɫ�ʣ�" bgcolor="#FFFFFF">
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
<!--��Ҫ����ȥ��-->
<table width="1003" border="0" cellspacing="0" cellpadding="0">

    <tr>
    	<td colspan="6" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
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
              <TD width="45" align="left" class="message_title_bold" style="cursor:hand;"><a href="<%=path%>/servlet/ShowNoReadServlet" class="message_title_bold" style="text-decoration:none">δ��<br />
                �ʼ�</a></TD>
              </tr>
              </table>
              <TD width="90" align="right" bgcolor="#FFFFFF"><table width="100%" cellspacing="5">
                <tr>
                  <td width="36" align="right" style="cursor:hand"><a href="SendMail1.html"><img src="<%=path%>/images/mail_send.JPG" width="26" height="26" border="0" /></a></td>
                  <td width="45" align="left" class="blue-12" style="cursor:hand;"><a href="<%=path%>/servlet/SendFileFileTransferServlet" style="text-decoration:none">д��</a></td>
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
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title">�����ļ���</td>
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
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">����ʱ���</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">��</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
            </table>            </td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"  height="30">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="131" align="right" bgcolor="#FFFFFF"><input name="textfield2222" type="text" class="biankuang-blue" size="15" value="����ؼ���"/></td>
                  <td width="47" align="center" nowrap="nowrap" bgcolor="#E0EDF8" class="message_title_boldold" style="cursor:hand;">����</td>
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
            <td width="196"><table width="100%" height="170" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="20" align="left"  bgcolor="#E0EDF8" class="block_title" colspan="3"> �ļ���</td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td width="55" align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_inbox.JPG" width="15" height="16" />&nbsp;</td>
                  <td width="134" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Inbox&search=no" style="cursor:hand" style="text-decoration:none">�ռ���</a>&nbsp;&nbsp;</td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_caogao.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Draft&search=no" style="cursor:hand" style="text-decoration:none">�ݸ���</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_outbox.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Sent&search=no" style="cursor:hand" style="text-decoration:none">������</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Junk&search=no" class="text" style="text-decoration:none">������</a>&nbsp;&nbsp;<span class="grap2-12"><a href="javascript:_ForeverDelete()" style="text-decoration:none">[���]</a></span></td>
                </tr>
                <tr>
                  <td height="25" colspan="3" bgcolor="#E0EDF8" class="block_title">�Զ����ļ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="BoxListServlet" style="text-decoration:none" class="message_title_bold">[����]</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_folder.JPG" width="19" height="17" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="index.html" style="text-decoration:none" class="message_title">�Զ���</a></td>
                </tr>
            </table>
            
            </td>
          </tr>
      </table></td>
      <td headers="11">&nbsp;</td>
      <td rowspan="2" valign="top"><table width="789" border="0" align="center" cellpadding="2" cellspacing="1" class="table_bgcolor">
          <tr>
            <td height="44" colspan="2" bgcolor="#FFFFFF">
            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                   	  <td><img src="<%=path%>/images/mail_replybutton.JPG" width="59" height="25" />&nbsp;<img src="<%=path%>/images/mail_resendbutton.JPG" width="59" height="25" />&nbsp;<img src="<%=path%>/images/mail_deletebutton.JPG" width="59" height="25" />&nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" width="59" height="25" /></td>
                      <td width="5%">&nbsp;</td>
                        <td width="11%">&nbsp;</td>
                	</tr>
                </table>
                </td>
          </tr>
          <tr>
          	<td colspan="2" bgcolor="#FFFFFF">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
   	      <tr>
                  <td width="12%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">�����ˣ�</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="107" class="message_title"><%=(String)request.getAttribute("sendMan")%></td>
                          <td width="37" align="right" class="blue-12">&nbsp;<img src="<%=path%>/images/mail_private.JPG" width="17" height="17" />&nbsp;</td>
                          <td width="89" class="message_title"><a href="#" onclick="javascript:_openPage('<%=request.getContextPath()%>/servlet/GetIndiGroupServlet?from=<%=fileMessage.getFrom()[0].toString()%>')" style="cursor:hand" alt="������˷���" style="text-decoration:none">������˷���</a></td>
                          <td width="34" align="right" class="blue-12"><img src="<%=path%>/images/mail_personinfo.JPG" width="17" height="17" />&nbsp;</td>
                          <td width="250" class="message_title"><a href="javascript:_showperson()" style="cursor:hand" alt="�鿴��������ϸ��Ϣ" style="text-decoration:none">��������ϸ��Ϣ</a></td>
                          <td width="35" class="blue-12">&nbsp;</td>
                          <td width="138" class="blue-12">&nbsp;</td>
                      </tr>
                  </table></td>      </tr>

				 <tr style="display:none" id="rectr">
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title"><a href="#" onclick="javascript:_hiderec()" alt="�����ռ���" style="text-decoration:none">�ռ���</a>��</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title"><%=com.icss.oa.util.CommUtil.getTime(fileMessage.getSentDate().getTime())%></td>
                 </tr>

          
                  <tr  id="reclink"> 
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                    <td width="3%" bgcolor="#FFFFFF" class="message_title"><img src="<%=path%>/images/mail_show.jpg" /></td>
                    <td width="77%" bgcolor="#FFFFFF" class="message_title"><span><a href="#" onclick="javascript:_showrec()" alt="��ʾ�ռ���" style="text-decoration:none">��ʾ�ռ���</a></span></td>
                    <td width="8%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">�ļ����⣺</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title_bold"><%=fileMessage.getSubject().substring(0,fileMessage.getSubject().length()-13)%></td>
                  </tr>
                  
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">�������ڣ�</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title"><%=com.icss.oa.util.CommUtil.getTime(fileMessage.getSentDate().getTime())%></td>
                  </tr>

				 <tr>
				    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">�ļ����ݣ�</td>
                    <td colspan="3"  bgcolor="#FFFFFF" class="message_title" ><%=request.getAttribute("content")%></td>
                  </tr>
                 </table>

            
                 
              <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr><td height="1" bgcolor="#B9DAF9" width="100%"></td></tr>
              </table>
                 <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">

                    <tr>
                      <td></td>
                    <td height="15"></td></tr>
<%
	ArrayList fileList = (ArrayList)request.getAttribute("attachList");
	if(fileList != null && fileList.size() > 0){ //��������
%>
					<tr>
                      <td>&nbsp;</td>
                    	<td class="message_title">����:</td>
                    </tr>
<%
		for(int i = 0; i < fileList.size(); i ++){	
		AttachFileBean attachFileBean = (AttachFileBean)fileList.get(i);
%>

      <tr>
         <td>&nbsp;</td>
            <td><span class="table_title"><strong>   <a href="<%=path%>/servlet/DownloadAttachServlet?url=<%=attachFileBean.getDownloadUrl()%>&filename=<%=attachFileBean.getFileOriginName()%>"><font color="red"><b>�������&nbsp;<%=attachFileBean.getFileOriginName()%>(<%=AttachHelper.getFileSize(attachFileBean.getFilesize())%>)</font></a></strong></span> <span class="message_title_bold">&nbsp;&nbsp;<a href="#" onclick="window.open('<%=request.getContextPath()%>/servlet/SaveAccessServlet?url=<%=attachFileBean.getDownloadUrl()%>&filename=<%=attachFileBean.getFileOriginName()%>','','width=500,height=300,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=252,top=110')" title="[�����ѡ��Ҫ���浽�����ļ����е�λ�á�ȷ���򱣴�,ȡ���������]"><b>��ӵ������ļ���</a><br></span>
		</td>
     </tr>
                   
<%
	}//end for
}//end if
%>   

                 </table>
            </td>
          </tr>
          
          <tr>
            <td height="48" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="message_title"><img src="<%=path%>/images/mail_replybutton.JPG" onclick="javascript:reply()" width="59" height="25" />&nbsp;<img src="<%=path%>/images/mail_resendbutton.JPG" onclick="javascript:transmit()" width="59" height="25" />&nbsp;<img src="<%=path%>/images/mail_deletebutton.JPG" onclick="javascript:_Delete()" width="59" height="25" />&nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" onclick="javascript:_goback('<%=type%>')" width="59" height="25" /></td>
          </tr>
          
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
    <td width="1013" height="54"><div align="center" class="foot_message">��ý�����ݿ�ϵͳ����ý�������ϵͳ��eNewsϵͳ���������ű༭ϵͳ�����ڷ����ý��༭ϵͳ�������༭ϵͳ����Ƶ����ϵͳ��������ò�ѯϵͳ����Ϣ����ý��ɱ�ϵͳ<br />
    ȫ�����ǹ���ϵͳ��ͼ��/����ͼ��ϵͳ��ͨ�Ź���ϵͳ������������ϵͳ��ͼƬ/ͼ��༭ϵͳ��Ӫ������ϵͳ������������ϵͳ</div></td>
  </tr>
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 ��Ȩ���С���<br />
      ������λ���»��缼���֡������������ҳ�����齫������ʾ���ķֱ��ʵ�Ϊ1024*768��<br />
    </div></td>
  </tr>
</table>
</form>
</body>
<script>
//�ظ�
function reply(){
	document.printheader.action="<%=path%>/servlet/BuildReplyServlet?curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
	document.printheader.submit();
}

//ת��
function transmit(){
	document.printheader.action="<%=path%>/servlet/SendFileBuildTransmitServlet";
	document.printheader.submit();
}

//����
function _goback(type){
    if(type=="searchmail"){
	    document.location.href="<%=path%>/servlet/SearchMailServlet";
	}else if(type=="noread"){
	    document.location.href="<%=path%>/servlet/ShowNoReadServlet";
	}else{
	    document.location.href="<%=path%>/servlet/MessageListServlet?type="+type+"&folder=<%=folder%>&curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
        //alert(history.back());
        //document.location.href= "javascript: history.back(-2)";
	    //alert(history.back());
	}
}

//���ظ���
function _downloadattach(uploadurl,filename){
    document.location.href = "<%=request.getContextPath()%>/servlet/DownloadAttachServlet?url="+uploadurl+"&filename="+filename;
    //window.location.reload();
}

//ɾ��
function _Delete()
{
     if(confirm("��Ҫѡ������ɾ����Щ�ļ���")){
     document.printheader.action="<%=path%>/servlet/DelMailServlet?curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
     document.printheader.submit();
     return true;
    }else{
     // return false;
    }
}

function _ForeverDelete()
{
    ok=confirm("ȷ��Ҫ�����������");
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet";
        return true;
    }else{
        return false;
    }
}

</script>
</html>
