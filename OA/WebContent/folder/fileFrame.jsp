<%@ page contentType="text/html; charset=gb2312" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>网络文件夹</title>
</head>

<frameset rows="*" cols="16%,*" framespacing="1" frameborder="no" border="0" bordercolor="#99CCFF">
  <frame src="<%=request.getContextPath()%>/servlet/ShowFolderListServlet" name="leftFrame" scrolling="NO" noresize>
  <frame src="<%=request.getContextPath()%>/servlet/ShowFileListServlet?parentId=1" name="mainFrame">
</frameset>
<noframes><body>

</body></noframes>
</html>
