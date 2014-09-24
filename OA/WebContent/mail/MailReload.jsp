<%@ page contentType="text/html; charset=GBK" %>
<%
	String path = request.getContextPath();
	String nextpage = (String)request.getAttribute("nextpage");
%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
</head>
<script>

function Update() 
	{
	window.top.leftfrm.location.reload();
	window.location.href = '<%=path%><%=nextpage%>';
	}//Update


</script>

</head>
<body>

<SCRIPT LANGUAGE="JavaScript">
Update();
</SCRIPT>

</body>
</html>