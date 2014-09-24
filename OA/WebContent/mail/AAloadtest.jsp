<%@ page contentType="text/html; charset=GB2312" %>
<%
	String path = request.getContextPath();
%>
<html>
<head></head>

<body>
<form action="<%=path%>/servlet/SendFileServlet" method="post" ENCTYPE="multipart/form-data">
	<input type="file" name="upfile"/>
	<input type="submit" value="ÉÏ´«"/>
</form>
</body>
</html>