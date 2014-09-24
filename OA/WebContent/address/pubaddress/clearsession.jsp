<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%
//String doFunction = request.getParameter("doFunction");
String sessionname = request.getParameter("sessionname");
//List emptylist = new ArrayList();
HttpSession tempsession = request.getSession();
tempsession.removeAttribute(sessionname);
%>


<html>
<head>
<title>Untitled Document</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT LANGUAGE="JavaScript">
	window.top.close();
</SCRIPT>
</head>
<body>
</body>
</html>