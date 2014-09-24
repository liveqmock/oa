<%@ page contentType="text/html; charset=GBK" %>
<%
String sessionname = request.getParameter("sessionname");
System.out.println("selectPersonFramejsp_sessionname="+sessionname);
String doFunction = request.getParameter("doFunction");
String orguuid = request.getParameter("orguuid");
HttpSession tempsession = request.getSession();
tempsession.removeAttribute(sessionname);
%>
<script language="JavaScript">
self.moveTo(100,50)
self.resizeTo(800,650)
</script>
<html>
<head>
<title>从地址簿选择2</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<!--frameset cols="194,6,*" frameborder="NO" border="0" framespacing="0" >
  <frame src="<%=request.getContextPath()%>/servlet/OrgTree4Servlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" name="leftFrame" scrolling="auto" noresize>
  <frame src="border1.jsp" name="border1Frame" scrolling="NO"  >
	<frameset rows="400,6,*" cols="*" frameborder="NO" border="0" framespacing="0">
	 <frame src="<%=request.getContextPath()%>/servlet/SelectPersonServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" name="mainFrame" noresize>
  	 <frame src="border2.jsp" name="border2Frame" scrolling="NO" noresize>
	 <frame src="<%=request.getContextPath()%>/servlet/SelectOrgpersonServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" name="listFrame" noresize>
	</frameset>
</frameset-->
<frameset cols="194,*" frameborder=1 border="0" framespacing="3"  bordercolor="#AED4EE">
  <frame src="<%=request.getContextPath()%>/servlet/OrgTree4Servlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>&&orguuid=<%=orguuid%>" name="leftFrame" scrolling="auto">
	<frameset rows="400,*" cols="*" frameborder=1 border="0" framespacing="3" bordercolor="#AED4EE">
	 <frame src="<%=request.getContextPath()%>/servlet/SelectPersonServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" name="mainFrame">
	 <frame src="<%=request.getContextPath()%>/servlet/SelectOrgpersonServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" name="listFrame">
	</frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
