 <%@ page contentType="text/html; charset=GBK" %>
 <%@ page pageEncoding="GBK" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.MessageHandler" %>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler" %>
<%@page import="com.icss.oa.config.FileTransferConfig"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>

<%
String path = request.getContextPath();
String time = TimeUtil.getCurrentDate("yyyy年MM月dd日 ")+TimeUtil.getCurrentWeek();
Iterator messageIter = null;
List reList = (List)request.getAttribute("listOfMessages");
//String curPageNum= (String)request.getAttribute("curPageNum");
if(reList!=null){
    messageIter = reList.iterator();
}

String typename = "文件夹";
String folderName = "未读邮件";

%>

<html>
<head>

<title><%=folderName%></title>
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

</head>

<body>
<form name="listmail" method="POST">
<input type="hidden" name="folder" value="noread">

<table width="750" border="0" cellspacing="0" cellpadding="0">

    <tr>
      <td colspan="3" rowspan="2" valign="top"></td>
      <td width="93" height="30" valign="middle" class="message_title"></td>
      <td width="27" height="30" align="right" valign="middle"><img src="<%=path%>/images/mail_delete.JPG" width="17" height="17" /></td>
      <td width="32" valign="middle" class="foot_message"><a href="javascript:_Delete()" style="text-decoration:none">删除</a></td>
      <td width="34" height="30" align="right" valign="middle"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" /></td>
      <td width="121" height="30" valign="middle" class="foot_message"><a href="javascript:_ForeverDelete('Junk')" style="text-decoration:none">清空垃圾箱</a></td>
     <td width="440" height="30" align="center" valign="top">
        
      <iframe name="foldersizefrm" src="<%=path%>/servlet/FolderSizeServlet" width="100%" height="100%" scrolling="no" frameborder="0" framespacing="0"></iframe>	   </td>
    </tr>
    <tr>
   	  <td colspan="6" valign="top">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
        <tr>
          <td nowrap="nowrap" width="5%" height="26" class="block_title" align="center">
              <input type="checkbox" name="allbox" value="checkbox" onClick="javascript:CheckAll()"/>
            全选</td>
          <td width="5%" class="block_title" align="center">&nbsp;</td>
          <td width="45%" colspan="4" class="block_title" align="center">主题</td>
          <td width="20%" class="block_title" align="center">日期</td>
          <td width="15%" class="block_title" align="center">发件人</td>
          <td width="10%" class="block_title" align="center">大小</td>
        </tr>
<%
if(!(messageIter.hasNext())){
%>
     <tr bgColor='#D8EAF8'>
         <td height="52" class="text-01" colspan=11>
             <div align="center"><br><br>没有邮件！<br><br></div>         </td>             
     </tr>            
     <tr>
         <td colspan=11 height="2" background="<%=path%>/images/bg-13.gif" class="text-01"></td>
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
	
	  String secondword = mailName.substring(1,2);
       if(FileTransferConfig.NEW_FLAG.equals(secondword))
%>

        <tr>
          <td height="26" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="messageid" value="<%=mailName%>" />
          </div></td>
          <td bgcolor="#FFFFFF"><div align="center"><img src="<%=path%>/images/email.gif" width="16" height="16" hspace="3" vspace="3" /></div></td>
          <td colspan="4" bgcolor="#FFFFFF" class="message_title_bold" >
			<a href="<%=path%>/servlet/ShowFile1Servlet?type=noread&folder=<%=folderSort%>&mailName=<%=mailName%>"  class="message_title_bold" style="text-decoration:none"><%=mailSubject.substring(0,mailSubject.length()-1)%></a>		  </td>
          <td bgcolor="#FFFFFF" class="message_title_bold"><div align="center"><% if(reDate != null) out.print(com.icss.oa.util.CommUtil.getTime(reDate.getTime()));%></div></td>
          <td bgcolor="#FFFFFF" class="message_title_bold"><div align="center"><%=mailFrom!=null?mailFrom:""%></div></td>
          <td bgcolor="#FFFFFF" class="message_title_bold"><table border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="message_title_bold"><%=mailMemory%></td>
                <%if(!(contentType.trim().equals("text/plain"))) {%>
				<td><img src="<%=path%>/images/icon_attachment.gif" width="16" height="16" hspace="5" /></td>
				<%}%>
              </tr>
          </table></td>
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

        
        <tr>
          <td height="26" colspan="9" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>

				 <%@ include file = "./NoReadPageScrollBar.jsp" %>
              </tr>
          </table></td>
        </tr>
      </table>      </td>
    </tr>
    
    
    <tr>
    	<td colspan="9" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
<script>

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
    }
    if(!IsChecked(listmail.messageid,"请选中邮件！")){
      return false;
    }
    ok=confirm("您要选择永久删除这些文件吗？");
    if(ok){
     document.listmail.action="<%=request.getContextPath()%>/servlet/DelMailServlet";
     document.listmail.submit();
     //window.top.leftFrame.location.reload();
     return true;
    }else{
      return false;
    }
}



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
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
}
frameautoheight();
parent.leftfrm.location.reload();

</script>
	  

</form>
</body>
</html>
