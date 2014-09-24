<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.MessageHandler" %>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler" %>
<%@page import="com.icss.oa.config.FileTransferConfig"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%

Iterator messageIter = null;
List reList = (List)request.getAttribute("listOfMessages");
if(reList!=null){
    messageIter = reList.iterator();
}

String typename = "文件夹";
String folderName = "未读邮件";

%>
<HTML><HEAD><TITLE>未读邮件</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script language="JavaScript" src="../include/common.js"></script>
</head>

<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif">
<div align="left"> 
<form name="listmail" method="POST"  action="">
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"><%=typename%>-&gt;<%=folderName%></td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
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
                      <td width="38%" bgcolor="#FFFBEF"><div align="center" class="title-04">主题</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="17%" bgcolor="#FFFBEF"><div align="center" class="title-04">日期</div></td>
                      <td width="0%" rowspan="100" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="25%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">发件人</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="10%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">大小</div></td>
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
if(!(messageIter.hasNext())){
%>
<tr bgColor='#D8EAF8'>
   <td height="52" class="text-01" colspan=11><div align="center">
                <br><br>没有邮件！<br><br>
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
ConnLog.open("showNoRead.jsp");
MessageHandler mHandler = new MessageHandler();
FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
//处理根目录下的未读邮件
int i=0;
while(messageIter.hasNext()){
    List listOfMessages = (List)messageIter.next();
    String[] mailArray = new String[4];
    for(int j=0;j<listOfMessages.size();j++){
        mailArray[j] = listOfMessages.get(j).toString();
    }
    String folderSort = mailArray[0];         //所在文件夹，
    long result = Long.parseLong(mailArray[1]);    //得到邮件大小
    double mailnum = 0;
    String mailMemory = "";  //用于页面显示
    if(result>=1024*1024){
        mailnum = CommUtil.getDivision(result, 1024*1024, 2);
        mailMemory = Double.toString(mailnum)+"MB";
    }else{
        mailnum = CommUtil.getDivision(result, 1024, 2);
        mailMemory = Double.toString(mailnum)+"KB";
    }
    String mailHead = mailArray[2];   //得到邮件头
    String mailName = mailArray[3];   //得到邮件名
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
       if(FileTransferConfig.NEW_FLAG.equals(secondword))   //为未读邮件
%>
  	       <img alt="未读邮件标记" src="<%=request.getContextPath()%>/images/noread.gif" width="15" height="10">
	   </td>
       <td nowrap>
	       <div align="center" class="text-01">
	       <div align="left"><a href="<%=request.getContextPath()%>/servlet/ShowMailServlet?type=noread&folder=<%=folderSort%>&mailName=<%=mailName%>"> ·<%out.print("<b>");%><%=mailSubject%></a></div>
	       </div>
       </td> 
       <td nowrap class="text-01"><div align=     "center"><% if(reDate != null) out.print(com.icss.oa.util.CommUtil.getTime(reDate.getTime()));%></div></td>
       <td nowrap height="22" class="text-01" align="center"><%=mailFrom%></td>

       <td nowrap height="22"><div align="center"><%=mailMemory%> <%if(!(contentType.trim().equals("text/plain"))) {%><img src="<%=request.getContextPath()%>/images/attachsign.gif" alt="本邮件带附件" width="8" height="15" align="absmiddle"><%}%></div></td>
                          
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
		System.out.println("there is a mail error（一个非法的邮件）....");
	}
   i++;
}//end while

	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("showNoRead.jsp");
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
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
	           <%@ include file = "noReadPageScrollBar.jsp" %>
	      </td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg">
              <div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div>
          </td>
        </tr>
</table> 
<br>
<br>
<br>
            <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
                 <tr>
                   <td align="left" width="52%"><input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><font class="tblitemfont"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">选中所有的文件</a></font> <font class="tblcautionfont">　<a href="#" onclick="javascript:_ForeverDelete()">[清空垃圾箱]</a></font>
                    <img src="<%=request.getContextPath()%>/images/botton-delete.gif" width="70" height="22" hspace="10" border=0 style="cursor:hand" onClick="javascript:_Delete()" ></td>
                   <td width="1%" align="right">&nbsp;</td>                 
                 </tr>
    </table>
</td></tr>
</table>    
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
		alert("没有邮件！");
        return false;
    }
    if(!IsChecked(listmail.messageid,"请选中邮件！")){
      return false;
    }
    ok=confirm("您要选择永久删除这些文件吗？");
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
    ok=confirm("确定要清空垃圾箱吗？");
    if(ok){
        document.location.href="<%=request.getContextPath()%>/servlet/DelMailServlet";
        return true;
    }else{
        return false;
    }
}
//-->

</script>
<input type="hidden" name="folder" value="noread">
</form>
</div>
<br><br><br>
</body>
</html>