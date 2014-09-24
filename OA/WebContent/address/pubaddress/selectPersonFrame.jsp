<%@ page contentType="text/html; charset=GBK" %>
<%
String sessionname = request.getParameter("sessionname");
System.out.println("selectPersonFramejsp_sessionname="+sessionname);
String doFunction = request.getParameter("doFunction");
String sendMail = request.getParameter("sendMail")==null?"0":request.getParameter("sendMail");

HttpSession tempsession = request.getSession();
tempsession.removeAttribute(sessionname);
%>
<script language="JavaScript">
	var swidth = window.screen.width;
	var sheight = window.screen.height;
	var width = 800;
	var height = 600;
	var top = (sheight-height)/2;
	var left = (swidth-width)/2;
	self.moveTo(top,left);
	self.resizeTo(width,height)
</script>
<html>
<head>
<title>从地址簿选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<frameset cols="194,*" frameborder=1 border="0" framespacing="3"  bordercolor="#AED4EE">
  <frame src="<%=request.getContextPath()%>/servlet/OrgTree2Servlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>&sendMail=<%=sendMail%>" name="leftFrame" scrolling="auto" >
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
