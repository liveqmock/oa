<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.oa.filetransfer.handler.FileTransferHandler"%>
<%@ page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler"%> 
<%@page import="com.icss.oa.util.CommUtil"%>

<%
String path = request.getContextPath();
String usersize = (String)request.getAttribute("usersize");
String totalsize = (String)request.getAttribute("totalsize");

String percent = (String)request.getAttribute("percent");

if(percent==null||"".equals(percent)){
	percent="0";
}
long realsize = Long.parseLong(usersize);
double mailnum = 0;
String mailMemory = "";
if(realsize>=1024*1024){
	mailnum = CommUtil.getDivision(realsize, 1024*1024, 2);
	mailMemory = Double.toString(mailnum)+"MB";
}else{
	mailnum = CommUtil.getDivision(realsize, 1024, 2);
	mailMemory = Double.toString(mailnum)+"KB";
}


%>

<html>
<head>
<title>空间使用情况</title>
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
</head>
<body>
	   <table>
    	<tr>
        <td width="274" valign="top" class="message_title">已用空间：<%=mailMemory%>（<%=percent%>%），总空间：<%=totalsize%>M&nbsp;</td>
        <td width="152" valign="middle" nowrap="nowrap" height="20">
		<% String bgColor = "";
             if(Double.parseDouble(percent)>=Double.parseDouble("80")){
                 bgColor = "red";
             }else{
                 bgColor = "#339933";
             }
        %>
        <div style="border-color:#B9DAF9;border-style:solid;border-width:1px;width:140px;height:8px" align="left">
        	<div style="width:<%=percent%>%;background-color:<%=bgColor%>;height:8px"></div>
        </div>        
		</td>
        </tr>

   	   </table>
</body>
</html>