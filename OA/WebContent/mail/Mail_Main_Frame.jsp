 <%@ page contentType="text/html; charset=GBK" %>
 <%@ page pageEncoding="GBK" %>

<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.oa.filetransfer.handler.FileTransferHandler"%>
<%@ page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo" %>
<%@ page import="com.icss.resourceone.sdk.framework.Context" %>
<%@ page import="com.icss.resourceone.sdk.framework.EntityException" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日 ")+TimeUtil.getCurrentWeek();
	//SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);



%>

<html>
<head>
<title>文件传递</title>
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
	<SCRIPT language=JavaScript
			src="<%=request.getContextPath()%>/include/counter.js">
	</SCRIPT>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}


</style>
<%
	String nextsrc = null;
	String nexttree = path+"/servlet/FolderListServlet";
	String next=(String)request.getParameter("next");
	String type = (String)request.getParameter("type");
	String folder = request.getParameter("folder")==null ? "Inbox" :(String)request.getParameter("folder");
	String mailName = (String)request.getParameter("mailName");
	String curPageNum = (String)request.getParameter("curPageNum");
	String sortname = (String)request.getParameter("sortname");
	String sorttype = request.getParameter("sorttype")==null?"DSC":(String)request.getParameter("sorttype");
	if("receive".equals(next)){
		nextsrc = path+"/servlet/MessageListServlet?type=system&folder=Inbox&search=no";
    }else if("send".equals(next)){
		nextsrc = path+"/servlet/MessageListServlet?type=system&folder=Sent&search=no";
	}else if("write".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet";	
	}else if("box".equals(next)){
		nextsrc = path+"/servlet/BoxListServlet";	
	}else if("dangyuan".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag=party";	
	}else if("hr".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag=hr";	
	}else if("dev".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag=dev";	
	}else if("kuanglecheng".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag=kuanglecheng";
	}else if("bgt".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag=bgt";
	}else if("shitang".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag=shitang";	
	}else if("zjkg".equals(next)){
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag=zjkg";
	}else if("folder".equals(next)){
		nextsrc = path+"/servlet/ShowRootFolderServlet";
		nexttree = path+"/servlet/ShowFolderListServlet";
	}else if("read".equals(next)){
		nextsrc = path+
		"/servlet/ShowFile1Servlet?search=no&type="+type+"&folder="+folder+"&mailName="+mailName+"&curPageNum="+curPageNum+"&sortname="+sortname+"&sorttype="+sorttype;
	}else{
		nextsrc = path+"/servlet/SendFileFileTransferServlet?sun_flag="+next;	
	}
%>

<form name="frm" target="_blank">
<table width="1003" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td align="center">
			<jsp:include page="/include/top.jsp"></jsp:include>
		</td>
    </tr>
</table>

<table width="1003"  border="0" align="center">
<tr>
   <td width="1003" align="center">
        <table width="993" border="0" align="center">
           <tr height=450>
             <td width="188" id=menu valign="top">
                  <iframe name="leftfrm" src="<%=nexttree%>" border="0" width="100%" height="450" scrolling="auto" frameborder="0" framespacing="0"></iframe>
             </td>
    
             <td width="801" id=main valign="top">
	            <iframe name="bodyfrm" src="<%=nextsrc%>" border="0" width="100%" height="550" scrolling="auto" frameborder="0" framespacing="0"></iframe>
             </td>
           </tr>
        </table>
     </td>
<tr>
<tr>
<td align='center'>
<jsp:include flush="true" page="/mail/Mail_Bottom.jsp"></jsp:include>
</td>
</tr>
</table>

</form>
		<SCRIPT LANGUAGE="JavaScript">
			<% if("write".endsWith(next)){%>
				_counter('发送邮件');
			<%}else if("receive".endsWith(next)){%>
				_counter('接收邮件');
			<%}%>
		</SCRIPT>
</html>
