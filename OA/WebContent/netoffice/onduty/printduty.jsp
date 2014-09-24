<%@ page contentType="text/html; charset=gb2312" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%
String orgUUid = request.getParameter("orgUUid");
String orgname = request.getParameter("orgname");
%>
<html>
<head>
<title>打印值班表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">
<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
<script language=javascript>
function _printduty(orgUUid){
    document.location.href = "<%=request.getContextPath()%>/servlet/PrintDutyServlet?orgUUid="+orgUUid;
}
</script>
</head>
<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100%" align="center">
     <a href="#" onclick="_printduty('<%=orgUUid%>')">点击这里下载    <font color=red><%=orgname%></font></a>
    </td>
  </tr>
</table>
</body>
</html>
