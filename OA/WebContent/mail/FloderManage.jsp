<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.oa.filetransfer.handler.FileTransferHandler"%>
<%@ page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy��MM��dd�� ")+TimeUtil.getCurrentWeek();
	//SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
%>
<html>
<head>

<%

List showContent = (List)request.getAttribute("reList");

//Ԫ��Ϊ:�ļ�����δ���ļ������ļ���ռ�ܿռ�
List ReceContent = new ArrayList();
List SentContent = new ArrayList();
List DraftContent = new ArrayList();
List JunkContent = new ArrayList();
int sysTotalnum=0,sysnoReadnum=0;
long sysTotalmemory=0;
if(showContent.size()>0){
	
    ReceContent = (List)showContent.get(0);  //�ռ���
    SentContent = (List)showContent.get(1);  //������
    DraftContent = (List)showContent.get(2); //�ݸ���
    JunkContent = (List)showContent.get(3);  //������
    sysTotalnum = Integer.parseInt(ReceContent.get(0).toString()) + Integer.parseInt(SentContent.get(0).toString()) 
             + Integer.parseInt(DraftContent.get(0).toString()) + Integer.parseInt(JunkContent.get(0).toString());
    sysnoReadnum = Integer.parseInt(ReceContent.get(1).toString()) + Integer.parseInt(SentContent.get(1).toString()) 
              + Integer.parseInt(DraftContent.get(1).toString()) + Integer.parseInt(JunkContent.get(1).toString());
    sysTotalmemory = Long.parseLong(ReceContent.get(2).toString())+Long.parseLong(SentContent.get(2).toString())
                +Long.parseLong(DraftContent.get(2).toString())+Long.parseLong(JunkContent.get(2).toString());
}
//�û��Զ����ļ��д���
List userFolder = (List)request.getAttribute("userFolder");
Iterator userFolderItr = userFolder.iterator();
int userTotalNum = 0;
int userNoReadnum = 0;
long userTotalMemory = 0;
List oneList = new ArrayList();
for(int m=0;m<userFolder.size();m++){
    oneList = (List)userFolder.get(m);
    userTotalNum+=Integer.parseInt(oneList.get(0).toString());
    userNoReadnum+=Integer.parseInt(oneList.get(1).toString());
    userTotalMemory+=Long.parseLong(oneList.get(2).toString());
}

List userFolderName = (List)request.getAttribute("userFolderName");
Iterator userFolderNameItr = userFolderName.iterator();
long boxMemory = Long.parseLong((String)request.getAttribute("boxMemory"));
long boxPercent = Long.parseLong((String)request.getAttribute("boxPercent"));

FileTransferHandler fHandler = new FileTransferHandler();
FiletransferSetHandler handler = new FiletransferSetHandler();

int totalNum = sysTotalnum + userTotalNum;
int noReadnum = sysnoReadnum + userNoReadnum;
long totalmemo = sysTotalmemory + userTotalMemory;
String totalMemory = fHandler.getMailMemory(totalmemo);
double mailnum = CommUtil.getDivision(totalmemo*100, 1024*1024*boxMemory, 2);
String percent = Double.toString(mailnum);

%>


<title>�ļ���Ŀ¼�б�</title>
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
<script language="JavaScript" src="<%=path%>/include/common.js"></script>
<script language="javascript">

function fPopUpCalendarDlg(ctrlobj){
<!--
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=path%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
//-->
}

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

function _ForeverDelete()
{
    ok=confirm("ȷ��Ҫ�����������");
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type=system&folder=Junk";
        return true;
    }else{
        return false;
    }
}
function _NewFolder()
{
    if(document.dofolder.foldername.value==""){
		alert("��������Ҫ�½����ļ�������");
        return false;
    }else{
        //�õ��û��Զ�����ļ���
		var nameArr=new Array(<%=userFolderName.size()%>);
		<%
			for(int x=0;x<userFolderName.size();x++){ %>
			    nameArr[<%=x%>] = "<%=handler.decodeBase64((String)userFolderName.get(x))%>";
		<%  }
		%>
		var que = 0;
		for(var i=0;i<<%=userFolderName.size()%>;i++){
			if(document.dofolder.foldername.value==nameArr[i]){
			    que = 1;
			    break;
			}
		}
		if(que==1){
		    alert("�����ļ���������������������");
			return false;
		}else{
			alert(1);
            document.dofolder.action="<%=request.getContextPath()%>/servlet/NewFolderServlet";
            document.dofolder.submit();
            return true;
        }
    }
}

function _FolderRename()
{
    if(document.dofolder.newfoldername.value==""){
		    alert("�������µ��ļ�������");
        return false;
    }else{
        if(document.dofolder.newfoldername.value=="Inbox"||document.dofolder.newfoldername.value=="Sent"||
           document.dofolder.newfoldername.value=="Draft"||document.dofolder.newfoldername.value=="Junk"||
           document.dofolder.newfoldername.value=="system"){
             alert("�ļ�������ϵͳ�ļ������ظ���������������");
             return false;
        }else{
            //�õ��û��Զ�����ļ���
			var nameArr=new Array(<%=userFolderName.size()%>);
			<%for(int x=0;x<userFolderName.size();x++){ %>
			        nameArr[<%=x%>] = "<%=handler.decodeBase64((String)userFolderName.get(x))%>";
			<%}
			%>
			var que = 0;
			for(var i=0;i<<%=userFolderName.size()%>;i++){
			    if(document.dofolder.newfoldername.value==nameArr[i]){
			        que = 1;
			        break;
			    }
			}
			if(que==1){
			    alert("�����ļ���������������������");
			    return false;
			}
		}
	}
    if(document.dofolder.mytarget.value==""){
        alert("��ѡ��һ���Զ����ļ��У�");
        return false;
    }else{
        document.dofolder.action="<%=request.getContextPath()%>/servlet/RenameFolderServlet";
        document.dofolder.submit();
        return true;
    }
}

function _DelFolder(foldername)
{
   var userfolder = foldername;
   ok = confirm("ȷʵҪɾ������ļ�����");
   if(ok!="0"){
       document.location.href = "<%=request.getContextPath()%>/servlet/DelFolderServlet?folder="+userfolder;
       return true;
   }else{
       return false;
   }
      
}
function _DeleteMail(foldername)
{
    var userfolder = foldername;
    if(userfolder=="Junk"){
         type = "system";
         ok = confirm("ȷʵҪ�����������");
    }else{
         type = "user";
         ok=confirm("ȷʵҪ������ļ����е��ʼ�ɾ������������");
    }
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type="+type+"&folder="+userfolder;
        return true;
    }else{
        return false;
    }
}


initstyle();
</script>
</head>

<body>
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
    	<td colspan="11" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
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
                  <td width="45" align="left" class="blue-12" style="cursor:hand;"><a href="SendMail1.html" class="message_title_bold" style="text-decoration:none"><a href="<%=path%>/servlet/SendFileFileTransferServlet" style="text-decoration:none">д��</a></a></td>
                </tr>
              </table></TD>
            </TR>
        </TABLE>
        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="<%=path%>/images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>


		<form name="searchmail" method="post" action="">
        <TABLE width="180" height="30" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
       	  <tr>
            <td bgcolor="#FFFFFF">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title">�����ļ���</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<select name="folder" >
                  	  <option value="0" >��Ŀ¼</option>
                      <option value="Inbox" >�ռ���</option>
                      <option value="Sent" >������</option>
                      <option value="Draft">�ݸ���</option>
                      <option value="Junk" >������</option>
                      <% while(userFolderNameItr.hasNext()){
                             String foldername4 = userFolderNameItr.next().toString();
                      %>
                      <option value="<%=handler.decodeBase64(foldername4)%>"><%=handler.decodeBase64(foldername4)%></option>
                      <%
                         }
                      %>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="60" height="30" align="right" bgcolor="#FFFFFF" class="message_title">�Ƿ��Ѷ�</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<select name="isRead" style="width:90px;">
                    <option value="0">ȫ��</option>
                    <option value="1">δ���ʼ�</option>
                    <option value="2">�Ѷ��ʼ�</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">����ʱ���</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="startTime" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" style="cursor:hand;" border=0 align="absmiddle" alt="��� ��������" onclick="fPopUpCalendarDlg(startTime)"/></td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">��</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="endTime" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" style="cursor:hand;" border=0 align="absmiddle" alt="��� ��������" onclick="fPopUpCalendarDlg(endTime)"/></td>
                </tr>
            </table>            </td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"  height="30">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="131" align="right" bgcolor="#FFFFFF"><input name="subject" type="text" class="biankuang-blue" size="15" value="����ؼ���" /></td>
                  <td width="47" align="center" nowrap="nowrap" bgcolor="#E0EDF8" class="message_title_bold" style="cursor:hand;" onclick="javascript:search('<%=path%>')">����</td>
                </tr>
            </table>            </td>
          </tr>
        </TABLE>
		</form>
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
                  <td width="134" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Inbox&search=no" style="cursor:hand">�ռ���</a>&nbsp;&nbsp;<span class="red2-12-b">(<%=noReadnum%>)</span></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_caogao.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Draft&search=no" style="cursor:hand">�ݸ���</a>&nbsp;&nbsp;<span class="red2-12-b">(<%=showContent.size()>0?DraftContent.get(1).toString():"0"%>)</span></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_outbox.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Sent&search=no" style="cursor:hand">������</a>&nbsp;&nbsp;<span class="red2-12-b">(<%=showContent.size()>0?SentContent.get(1).toString():"0"%>)</span></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Junk&search=no" class="text">������</a>&nbsp;&nbsp;<span class="grap2-12"><a href="javascript:_ForeverDelete()">[���]</a></span></td>
                </tr>
                <tr>
                  <td height="25" colspan="3" bgcolor="#E0EDF8" class="block_title">�Զ����ļ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="BoxListServlet" style="text-decoration:none" class="message_title_bold" style="cursor:hand">[����]</a></td>
                </tr>
				<%
					if(userFolder.size()>0){
						int i = 0;
						while(userFolderItr.hasNext()){
						//ÿ��ѭ����һ���Զ����ļ�����
						List oneOfList1 = (List)userFolderItr.next(); 
						String folderName1 = (String)userFolderName.get(i);
						folderName1 = handler.decodeBase64(folderName1);
						i++;
				%>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_folder.JPG" width="19" height="17" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=user&folder=<%=folderName1%>&search=no" class="text"><%=folderName1%></a></td>
                </tr>
				<%}} userFolderItr = userFolder.iterator();%>
            </table></td>
          </tr>
      </table></td>
      <td headers="11">&nbsp;</td>
      <td width="96" height="30" valign="middle" class="message_title">�ʼ�������<span class="message_title_bold"><%=totalNum%></span></td>
      <td width="166" height="30" align="left" valign="middle" class="message_title">δ���ʼ���<span class="message_title_bold"><%=noReadnum%></span></td>
      <td width="42" valign="middle" class="blue-12">&nbsp;</td>
      <td width="21" height="30" align="right" valign="middle">&nbsp;</td>
      <td width="18" height="30" valign="middle" class="blue-12">&nbsp;</td>
      <td width="438" height="30" align="right" valign="top">
        <table>
    	<tr>
        <td width="274" valign="top" class="message_title">���ÿռ䣺<%=totalMemory%>��<%=percent%>%�����ܿռ䣺100M&nbsp;</td>
        <td width="152" valign="middle">
		<% String bgColor = "";
             if(mailnum>=boxPercent){
                 bgColor = "red";
             }else{
                 bgColor = "#339933";
             }
        %>
        <div style="border-color:#B9DAF9;border-style:solid;border-width:1px;width:150px;height:10px" align="left">
        	<div style="width:<%=percent%>%;background-color:<%=bgColor%>;height:10px"></div>
        </div>        </td>
        </tr>
   	  </table>      </td>
    </tr>
    <tr>
    	<td width="11">&nbsp;</td>
        <td width="11" headers="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
      <td colspan="6" valign="top">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
        <tr>
          <td width="32%" height="24" align="center" nowrap="nowrap" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">�ļ���</td>
          <td width="21%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">�ļ���</td>
          <td width="20%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">δ���ļ�</td>
          <td width="16%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">ռ�ÿռ�</td>
          <td width="11%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">����</td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Inbox&search=no">�ռ���</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?ReceContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?ReceContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(ReceContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Sent&search=no" style="cursor:hand">������</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?SentContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?SentContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(SentContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Draft&search=no" style="cursor:hand">�ݸ���</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?DraftContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?DraftContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(DraftContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Junk&search=no" class="text">������</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?JunkContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?JunkContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(JunkContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="javascript:_ForeverDelete()">[���]</a></td>
        </tr>
          <%
               if(userFolder.size()>0){
                  int i = 0;
                  while(userFolderItr.hasNext()){
                   //ÿ��ѭ����һ���Զ����ļ�����
                  List oneOfList = (List)userFolderItr.next(); 
                  String folderName = (String)userFolderName.get(i);
                  folderName = handler.decodeBase64(folderName);
                  i++;
          %>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=user&folder=<%=folderName%>&search=no" class="text"><%=folderName%></a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=oneOfList.get(0).toString()%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=oneOfList.get(1).toString()%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=fHandler.getMailMemory(Long.parseLong(oneOfList.get(2).toString()))%></td>
          <%
                  //�ж��ļ����Ƿ�Ϊ��
                  if("0".equals(oneOfList.get(2).toString())){
          %>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="javascript:_DelFolder('<%=folderName%>')" >[ɾ��]</a></td>
		  <%}else{%>
		  <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="javascript:_DeleteMail('<%=folderName%>')" >[���]</a></td>
		  <%     }%>
        </tr>
		  <%
               } //while
          }
		  userFolderItr = userFolder.iterator();
          %>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title_bold">�ܼ�</td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold"><%=totalNum%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold"><%=noReadnum%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold"><%=totalMemory%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold">&nbsp;</td>
        </tr>
      </table>
	  </form>
      <br />
		<form name="dofolder" method="POST" action="">
      <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
      	<tr>
        	<td bgcolor="#FFFFFF" class="foot_message">��[���ļ���]���½�
        	  <input name="foldername" type="text" class="black-12" value="" size="10" />
       	    <img src="<%=path%>/images/button_xinjian.jpg" width="90" height="23" onclick="javascript:_NewFolder()" style="cursor:hand" title="�½��ļ���"/> ��
       	    <select name="mytarget" id="select">
       	      <option value="">��ѡ��</option>
			  	<%
					if(userFolder.size()>0){
						int i = 0;
						while(userFolderItr.hasNext()){
						//ÿ��ѭ����һ���Զ����ļ�����
						List oneOfList2 = (List)userFolderItr.next(); 
						String folderName2 = (String)userFolderName.get(i);
						folderName2 = handler.decodeBase64(folderName2);
						i++;
			  %>
       	      <option value="<%=folderName2%>"><%=folderName2%></option>
			  <%}}%>
     	      </select> 
       	    ������Ϊ
       	    <input name="newfoldername" type="text" class="black-12" value="" size="10" />
       	    <img src="<%=path%>/images/button_rename.jpg" width="90" height="23" onclick="javascript:_FolderRename()" style="cursor:hand" title="�������ļ���"/>       	    </td>
        </tr>
      </table>
	  </form>
      </td>
    </tr>
    
    
    <tr>
    	<td colspan="11" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
<script>
function search(path){
    var stime = document.all.startTime.value;
    var etime = document.all.endTime.value;
    if((stime==""&&etime!="")||(stime!=""&&etime=="")){
        alert("�������������Ĳ�ѯʱ�䣡");
        return false;
    }else{
    	var sr = stime.split("-");
		var er = etime.split("-");
		sr[1]=sr[1]-1;    //ϵͳĬ���·���0~11,����Ҫ��1
		er[1]=er[1]-1;
		var sd= new Date(sr[0],sr[1],sr[2]);
		var ed= new Date(er[0],er[1],er[2]);
		if(ed<=sd)       //��ֹʱ��С�ڵ��ڿ�ʼʱ��,��Ϊ���ʱ��ʾֻ��ĳһ��Ĺ����
		{
			alert("��ѡ��Ľ���ʱ��С�ڻ���ڿ�ʼʱ��!");
			return false;
		}else{
		    document.searchmail.action = path + "/servlet/SearchMailServlet";
	        document.searchmail.submit();
	        return true;
		}
    }
}

</script>
	  
<table width="1013" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="1013" height="54"><div align="center" class="foot_message">��ý�����ݿ�ϵͳ����ý�������ϵͳ��eNewsϵͳ���������ű༭ϵͳ�����ڷ����ý��༭ϵͳ�������༭ϵͳ����Ƶ����ϵͳ��������ò�ѯϵͳ����Ϣ����ý��ɱ�ϵͳ<br />
    ȫ�����ǹ���ϵͳ��ͼ��/����ͼ��ϵͳ��ͨ�Ź���ϵͳ������������ϵͳ��ͼƬ/ͼ���༭ϵͳ��Ӫ������ϵͳ������������ϵͳ</div></td>
  </tr>
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 ��Ȩ���С���<br />
      ������λ���»��缼���֡������������ҳ�����齫������ʾ���ķֱ��ʵ�Ϊ1024*768��<br />
    </div></td>
  </tr>
</table>

</body>
</html>