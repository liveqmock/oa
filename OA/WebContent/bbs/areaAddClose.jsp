<%@ page contentType="text/html; charset=GBK" %>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
</head>
<script>

function Update(isApply) 
{
	if(isApply!='1'){
		window.opener.location.reload();
	}else{
		alert("�ѳɹ��ύ����,��ȴ���׼!");
	}
	window.close();
}//Update


</script>

</head>
<body>

<SCRIPT LANGUAGE="JavaScript">
Update(<%=request.getParameter("isApply")%>);
</SCRIPT>
</body>
</html>