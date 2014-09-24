<!--本页用来说明response对象-->
<%@ page import="java.util.Date"%>
<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>定时刷新页面</title>
</head>
<body>
<b><font color="Black">显示当前时间</font></b>
<br>
<b>当前时间为：</b>
<%
	response.setHeader("refresh","3");
%>
<%
	out.println(new Date());
%>
</body>
</html>