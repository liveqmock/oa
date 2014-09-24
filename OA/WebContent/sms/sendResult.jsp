<%@ page contentType="text/html; charset=GBK" %>
<HTML><HEAD><TITLE>发送结果</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link href="../Style/css_grey.css" rel="stylesheet" type="text/css" />

<script>
function _back(){
	window.location.href="<%=request.getContextPath()%>/servlet/SendSMSServlet";
}
</script>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<%
String phonenums =request.getParameter("phonenums");
String unsendNames=(String)request.getAttribute("unsendNames");
String sendedNames=(String)request.getAttribute("sendedNames");

%>
</head>

<body>
		<div align="center">
			
			<form name="historyForm" method="post" action="">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td height="10"></td></tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">

					<tr>
						<td bgcolor="#FFFFFF">&nbsp;
							
						</td>
						<td valign="top">
							<table width="100%" border="0" cellpadding="2" cellspacing="1"
								bgcolor="#B9DAF9">
								<tr>
								<td width="100%" height="24"  colspan="6" align="center" class="block_title">
								短信发送结果								</tr>
								
								<tr bgcolor="#FFFFFF">
									<td height="24" width="20%" align="right" class="message_title_bold">
									<b>成功发送到：</b>
									<td align="left" class="blue3-12">
									<%=sendedNames%><%=phonenums %>									</tr>
								<%if(unsendNames!=null && unsendNames.length()>0){ %>
								<tr bgcolor="#FFFFFF">
									<td height="24" align="right" class="message_title_bold">
									<b>下列用户没有手机号：</b>
									<td align="left" class="blue3-12">
									<%=unsendNames%>	
									<%
									} 
									%>								</tr>
							</table>
							
					  </td>
						<td>&nbsp;
							
						</td>
					</tr>
					
					<tr>
						<td width="11" bgcolor="#FFFFFF">
							<img src="<%=request.getContextPath()%>/images/kongbai.gif"
								width="11" height="11" />
						</td>
						<td valign="top">
							<div align="left"></div>
						</td>
						<td width="11">
							<img src="<%=request.getContextPath()%>/images/kongbai.gif"
								width="11" height="11" />
						</td>
					</tr>
				</table>
			    <img src="<%=request.getContextPath()%>/images/bt_back.gif" style="cursor:hand" onClick="_back()">
			
			</form>
		</div>
	</body>
</html>