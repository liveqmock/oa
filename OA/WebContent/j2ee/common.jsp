<%@ page contentType="text/html; charset=gb2312" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"> 
<%
	com.icss.j2ee.message.Message message = (com.icss.j2ee.message.Message)request.getAttribute("ecexpress_message");
	
%>
<title>
<%= message.getMsgName() %>
</title>
<link rel="stylesheet" href="/resourceone/include/style.css">

</head>

<body background="/resourceone/images/grid.gif">
<table width="100%"   border="0" cellpadding="2" cellspacing="1" bgcolor="#B3C4DB">
  <tr>
    <td bgcolor="EEF4FF" align="center"><font color="#FF0000">系统信息:<%= message.getMsgName() %></td>
  </tr>
  <tr>
    <td bgcolor="EEF4FF"  align="left">
	<%=message.getMsgDesc()%>
	</td>
  </tr>
</table>
</body>
</html>