<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%
String isadmin = (String)request.getAttribute("isadmin");
//System.out.println("进入welcome,isadmin="+isadmin);
 %>



<html>
<head>
<title>管理制度系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<script language="javascript">
function _public(){
	
		myForm.action = "<%=request.getContextPath()%>/regulation/view.jsp";
		myForm.submit();
	
}

function _admin(){
	
		myForm.action = "<%=request.getContextPath()%>/servlet/AllRegulationServlet";
		myForm.submit();
	
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- ImageReady Slices (welcome（定）.gif) -->
<form method="POST" name="myForm">
<table id="__01" width="1024" height="768" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3">
			<img src="<%=request.getContextPath()%>/regulation/include/welcome_01.png" width="1024" height="601" alt=""></td>
	</tr>
	<tr>
		<td rowspan="4">
			<img src="<%=request.getContextPath()%>/regulation/include/welcome_02.png" width="796" height="167" alt=""></td>
		
		
		<td>
			<img onclick="javascript:_public()" style="cursor:hand" src="<%=request.getContextPath()%>/regulation/include/welcome_03.png" width="114" height="27" alt=""></td>
		
		<td rowspan="4">
			<img src="<%=request.getContextPath()%>/regulation/include/welcome_04.png" width="114" height="167" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="<%=request.getContextPath()%>/regulation/include/welcome_05.png" width="114" height="12" alt=""></td>
	</tr>
	
	<%if("1".equals(isadmin)) {//如果是管理员，则显示%>
	<tr>	
		<td>
			<img onclick="javascript:_admin()" style="cursor:hand" src="<%=request.getContextPath()%>/regulation/include/welcome_06.gif" width="114" height="27" alt=""></td>			
	
	</tr>
	<%}else{ %>
	<tr>	
		<td>
			<img  src="<%=request.getContextPath()%>/regulation/include/welcome_05.png" width="114" height="27" alt="" ></td>			
	
	</tr>
	<%} %>		
	
	<tr>	
		<td > 
			<img src="<%=request.getContextPath()%>/regulation/include/welcome_07.png" width="114" height="101" alt=""></td>
	</tr>
</table>
<!-- End ImageReady Slices -->
</form>
</body>
</html>