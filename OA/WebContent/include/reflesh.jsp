<!--��ҳ����˵��response����-->
<%@ page import="java.util.Date"%>
<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>��ʱˢ��ҳ��</title>
</head>
<body>
<b><font color="Black">��ʾ��ǰʱ��</font></b>
<br>
<b>��ǰʱ��Ϊ��</b>
<%
	response.setHeader("refresh","3");
%>
<%
	out.println(new Date());
%>
</body>
</html>