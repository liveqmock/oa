 <%@ page contentType="text/html; charset=GBK" %>
 <%@ page pageEncoding="GBK" %>

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
<%@page import="com.icss.oa.util.CommUtil"%>
<%@ page import="java.io.File"%>
<%
String path = request.getContextPath();
String sendHtml = (String)request.getAttribute("sendHtml");
String ccHtml = (String)request.getAttribute("ccHtml");
String bccHtml = (String)request.getAttribute("bccHtml");
session.setAttribute("sendHtml",sendHtml);
session.setAttribute("ccHtml",ccHtml);
session.setAttribute("bccHtml",bccHtml);
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>新华社办公信息化系统</title>
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
<link href="<%=path%>/mail/css/Attach.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/mail/js/Attach.js"></script>
<script>
function _load(){
window.location.href = "<%=request.getContextPath()%>/mail/ShowDraft_Body.jsp";

}


</SCRIPT>

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body onLoad="_load();">

</body>
</html>
