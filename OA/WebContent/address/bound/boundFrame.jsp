<%@ page contentType="text/html; charset=GBK" %>
<%
String sessionname = request.getParameter("sessionname");
System.out.println("selectPersonFramejsp_sessionname="+sessionname);
String doFunction = request.getParameter("doFunction");
%>
<html>
<head>
<title>按范围选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
	<frameset rows="300,6,*" cols="*" frameborder="NO" border="0" framespacing="0">
	 <frame src="<%=request.getContextPath()%>/servlet/ListBoundServlet" name="upFrame" noresize>
  	 <frame src="border.jsp" name="border2Frame" scrolling="NO" noresize>
	 <frame src="<%=request.getContextPath()%>/servlet/SelectOrgpersonServlet" name="downFrame" noresize>
	</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
