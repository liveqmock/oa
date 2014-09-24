<%@ page contentType="text/html; charset=GBK" %>

<%
Integer boardId = (Integer)request.getAttribute("boardId");
%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
</head>
<script>

function Update() 
{
	window.opener.location.href="<%=request.getContextPath()%>/servlet/ShowTopicServlet?boardId=<%=boardId%>&primeFlag=0&manageFlag=1";
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