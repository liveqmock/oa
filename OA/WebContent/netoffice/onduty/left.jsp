<%@page contentType="text/html; charset=GBK" %>
<%@page import="java.util.*"%>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<HTML><HEAD><TITLE>文件传递</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/eyou_mainmenu.css" type="text/css">

<style type="text/css">
<!--
body {
	background-color: #336699;
}
-->
</style></HEAD>

<BODY topmargin="0" leftmargin="5"  background="<%=request.getContextPath()%>/images/bg-24.jpg" >

<br>
<%@ include file= "/include/intendWork.jsp" %>
<br>

</BODY>
</HTML>
