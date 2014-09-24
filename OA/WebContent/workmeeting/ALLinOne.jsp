<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>

<title>工作会议</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<frameset rows="300,*" cols="*" frameborder="NO" border="0" framespacing="0">
	 <frame src="<%=request.getContextPath()%>/servlet/GetWorkPlanServlet" name="searchTopFrame" scrolling="yes" noresize>
	 <frame src="<%=request.getContextPath()%>/servlet/GetWorkPlanRequestServlet" name="searchResultFrame" scrolling="yes" noresize>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>