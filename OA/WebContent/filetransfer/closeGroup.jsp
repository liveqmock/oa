<%@ page contentType="text/html; charset=GBK" %>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
</head>
<script>

function Update(isApply) 
{
	window.opener.location.href = "<%=request.getContextPath()%>/servlet/ListGroupServlet";
	window.close();
}//Update


</script>

</head>
<body>

<SCRIPT LANGUAGE="JavaScript">
Update();
</SCRIPT>
</body>
</html>