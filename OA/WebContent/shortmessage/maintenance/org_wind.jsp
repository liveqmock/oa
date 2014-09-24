<%@ page contentType="text/html; charset=gb2312" %>

<html>
<head>
<title>选择组织</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<FRAMESET border=0 frameSpacing=0 rows=0,* frameBorder=NO cols=*>
	<FRAME name=topFrame src="" noResize scrolling=no height="0%">
	<FRAME name=mainFrame src="<%=request.getContextPath()%>/orgtree/orgTree_shormess.jsp?nodeUrl=<%=request.getParameter("nodeUrl")%>">
	
</FRAMESET><noframes></noframes>

</html>
