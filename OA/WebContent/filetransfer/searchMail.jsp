<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="com.icss.oa.filetransfer.handler.MessageHandler" %>
<%@page import="com.icss.oa.config.FileTransferConfig"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<%

//�û��Զ����ļ�����
List userFolderName = (List)request.getAttribute("userFolderName");
Iterator userFolderNameItr = userFolderName.iterator();

List reList = (List)request.getAttribute("reList");
Iterator reIter = null;
if(reList!=null){
   reIter = reList.iterator();
}

FiletransferSetHandler handler = new FiletransferSetHandler();

String startTime = request.getParameter("startTime");
String endTime = request.getParameter("endTime");
String folder = request.getParameter("folder");
String subject = request.getParameter("subject");
String isRead = request.getParameter("isRead");

%>
<HTML><HEAD><TITLE>δ���ʼ�</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script language="JavaScript" src="<%=request.getContextPath()%>/include/common.js"></script>

<SCRIPT language=JavaScript>

function fPopUpCalendarDlg(ctrlobj){
<!--
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=request.getContextPath()%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
//-->
}
</SCRIPT>
</head>

<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif">
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
<form name="searchform" method="post" action="">
  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
      <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">�ļ�����</td>
      <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
      <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
      		<table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td nowrap width="25%" height="22" class="text-01"><div align="right">����ʱ�䣨�ɲ����</div></td>
                  <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                  <td nowrap colspan="9" width="75%" class="text-01">�� 
                      <input type="text" name="startTime" 
                         <%if(startTime!=null) out.print("value=\""+startTime+"\"");%> size="16" class="txt2" readonly > 
                      <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="��� ��������" onclick="fPopUpCalendarDlg(startTime)"> 
                  �� 
                      <input type="text" name="endTime" 
                         <%if(endTime!=null) out.print("value=\""+endTime+"\"");%> size="16" class="txt2" readonly> 
                      <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="��� ��������" onclick="fPopUpCalendarDlg(endTime)"> 
                  </td>
                </tr>
                <tr>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                  <td width="2" rowspan="3" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                  <td width="2" rowspan="6" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                  <td width="2" rowspan="6" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                  <td width="2" rowspan="6" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                </tr>
                <tr>
                  <td nowrap width="25%" height="11"><div align="right" class="text-01">�����ļ��У�</div></td>
                  <td nowrap width="10%" class="text-01"><select name="folder" >
                  	  <option value="0" >��Ŀ¼</option>
                      <option value="Inbox" <%if("Inbox".equals(folder)) out.print("selected");%>>�ռ���</option>
                      <option value="Sent" <%if("Sent".equals(folder)) out.print("selected");%>>������</option>
                      <option value="Draft" <%if("Draft".equals(folder)) out.print("selected");%>>�ݸ���</option>
                      <option value="Junk" <%if("Junk".equals(folder)) out.print("selected");%>>������</option>
                      <% while(userFolderNameItr.hasNext()){
                             String foldername = userFolderNameItr.next().toString();
                      %>
                      <option value="<%=handler.decodeBase64(foldername)%>" <%if(foldername.equals(folder)) out.print("selected");%>><%=handler.decodeBase64(foldername)%></option>
                      <%
                         }
                      %>
                  </select></td>
                  <td nowrap width="15%" height="22" class="text-01"><div align="right">��    �⣺</div></td>
                  <td nowrap width="20%" class="text-01"> 
                     <input name="subject" type="text" <%if(subject!=null) out.print("value=\""+subject+"\"");%> size="35" class="txt2">
                  </td>
                  <td nowrap width="15%" height="11"><div align="right" class="text-01">�Ƿ��Ѷ���</div></td>
                  <td nowrap width="15%" class="text-01"><select name="isRead" >
                  	  <option value="0">ȫ��</option>
                      <option value="1" <%if("1".equals(isRead)) out.print("selected");%>>δ���ļ�</option>
                      <option value="2" <%if("2".equals(isRead)) out.print("selected");%>>�Ѷ��ļ�</option>
                  </select></td>
                </tr>
                <tr>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td colspan="9" background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                </tr>
            </table>
      </td>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
    </tr>
  </table>
  
    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
      <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center"><img src="<%=request.getContextPath()%>/images/botton-search_in.gif" style="cursor:hand" onclick="javascript:search('<%=request.getContextPath()%>')" ></td>
      <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
    </tr>
  </table>
<br>
 </form> 
  
<div align="left"> 
<form name="listmail" id="listmail" method="POST"  action="/cgi-bin/movemail">
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">�������</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg">
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="5%" bgcolor="#FFFBEF">&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="5%" align="center" bgcolor="#FFFBEF">&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="38%" bgcolor="#FFFBEF"><div align="center" class="title-04">����</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="17%" bgcolor="#FFFBEF"><div align="center" class="title-04">����</div></td>
                      <td width="0%" rowspan="100" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="25%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">������</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="10%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">��С</div></td>
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
<%
if(!reIter.hasNext()){
%>
<tr bgColor='#D8EAF8'>
   <td height="52" class="text-01" colspan=11><div align="center">
                <br><br>û���ʼ���<br><br>
                        </div></td>
                       
                      </tr>
                      
                      <tr>
                        <td colspan=11 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        
                      </tr>
<%
}else{
Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
ConnLog.open("searchMail.jsp");
MessageHandler mHandler = new MessageHandler();
FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
//�����Ŀ¼�µ�δ���ʼ�
int i=0;
while(reIter.hasNext()){
    List listOfMessages = (List)reIter.next();
    String[] mailArray = new String[4];
    for(int j=0;j<listOfMessages.size();j++){
        mailArray[j] = listOfMessages.get(j).toString();
    }
    String folderSort = mailArray[0];  //�������ļ���
    long result = Long.parseLong(mailArray[1]);    //�õ��ʼ���С
    double mailnum = 0;
    String mailMemory = "";  //����ҳ����ʾ
    if(result>=1024*1024){
        mailnum = CommUtil.getDivision(result, 1024*1024, 2);
        mailMemory = Double.toString(mailnum)+"MB";
    }else{
        mailnum = CommUtil.getDivision(result, 1024, 2);
        mailMemory = Double.toString(mailnum)+"KB";
    }
    String mailHead = mailArray[2];   //�õ��ʼ�ͷ
    String mailName = mailArray[3];   //�õ��ʼ���
	
	try{
		String mailFrom = ftsHandler.getCName(mHandler.getFrom(mailHead).substring(0,mHandler.getFrom(mailHead).indexOf("@")));
		Date reDate = mHandler.getReceiveDate(mailHead);
		String  mailSubject = mHandler.getSubject(mailHead);
	String contentType = mHandler.getContentType(mailHead);
	String color = "#F2F9FF";
	if(i % 2 == 0)
		color = "#D8EAF8";
%>
    <tr bgColor=<%=color%> onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='<%=color%>';">
       <td nowrap class="text-01" align="center"><input type="checkbox" name="messageid" value="<%=mailName%>"></td>
       <td nowrap class="text-01" align="center">
<%
       String secondword = mailName.substring(1,2);
       if(FileTransferConfig.NEW_FLAG.equals(secondword)){   //Ϊδ���ʼ�
%>
  	       <img alt="δ���ʼ����" src="<%=request.getContextPath()%>/images/noread.gif" width="15" height="10">
<%     }  %>
	   </td>
       <td nowrap>
	       <div align="center" class="text-01">
	       <div align="left"><a href="<%=request.getContextPath()%>/servlet/ShowMailServlet?type=searchmail&folder=<%=folderSort%>&mailName=<%=mailName%>"> ��<%if(FileTransferConfig.NEW_FLAG.equals(secondword)){out.print("<b>");}%><%=mailSubject%></a></div>
	       </div>
       </td> 
       <td nowrap class="text-01"><div align=     "center"><% if(reDate != null) out.print(com.icss.oa.util.CommUtil.getTime(reDate.getTime()));%></div></td>
       <td nowrap height="22" class="text-01" align="center"><%=mailFrom%></td>

       <td nowrap height="22"><div align="center"><%=mailMemory%> <%if(!(contentType.trim().equals("text/plain"))) {%><img src="<%=request.getContextPath()%>/images/attachsign.gif" alt="���ʼ�������" width="8" height="15" align="absmiddle"><%}%></div></td>
                          
    </tr>
    <tr>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
        <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
    </tr>
<%
	}catch(Exception ex){
		System.out.println("there is a mail error��һ���Ƿ����ʼ���....");
	}
   i++;
}//end while

	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("searchMail.jsp");
					}
			} catch (Exception e) {
		}
	}

}//else
%>
                </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
	           <%@ include file = "SearchPageScrollBar.jsp" %>
	      </td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg">
              <div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div>
          </td>
        </tr>
</table> 
<br>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
     <tr>
         <td align="left" width="52%"><input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><font class="tblitemfont"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">ѡ�����е��ļ�</a></font> <font class="tblcautionfont">��<a href="#" onclick="javascript:_ForeverDelete()">[���������]</a></font>
         <img src="<%=request.getContextPath()%>/images/botton-delete.gif" width="70" height="22" hspace="10" border=0 style="cursor:hand" onClick="javascript:_Delete()" ></td>
         <td width="1%" align="right">&nbsp;</td>                 
     </tr>
</table>
<input type="hidden" name="folder" value="searchmail">
</form>
</div>
</td></tr>
</table>
<br><br><br>
</body>
<script language="JavaScript">

<!--

function CheckAll()
  {
    for (var i=0;i<document.listmail.elements.length;i++)
    {
      var e = document.listmail.elements[i];
      if (e.name != 'allbox')
        e.checked = document.listmail.allbox.checked;
    }
  }
  
function _Delete()
{
     if(!listmail.messageid){
		alert("û���ʼ���");
        return false;
    }
    if(!IsChecked(listmail.messageid,"��ѡ���ʼ���")){
      return false;
    }
    ok=confirm("��Ҫѡ������ɾ����Щ�ļ���");
    if(ok){
     document.listmail.action="<%=request.getContextPath()%>/servlet/DelMailServlet";
     document.listmail.submit();
     return true;
    }else{
      return false;
    }
}

function _ForeverDelete()
{
    ok=confirm("ȷ��Ҫ�����������");
    if(ok){
        document.href="<%=request.getContextPath()%>/servlet/DelMailServlet?folder=noread";
        return true;
    }else{
        return false;
    }
}

function search(path){
    var stime = document.searchform.startTime.value;
    var etime = document.searchform.endTime.value;
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
		    document.searchform.action = path + "/servlet/SearchMailServlet";
	        document.searchform.submit();
	        return true;
		}
    }
}
//-->

</script>
</html>