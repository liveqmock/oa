<%@ page contentType="text/html; charset=GBK" %>
<HTML><HEAD><TITLE>发送失败</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
		<script>
function _back(){
	window.location.href="<%=request.getContextPath()%>/servlet/SendSMSServlet";
}
</script>
</head>

<body>
		<div align="center">
			<br>
			<form name="historyForm" method="post" action="">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">

					<tr>
						<td bgcolor="#FFFFFF">
							&nbsp;
						</td>
						<td valign="top">
							<table width="100%" border="0" cellpadding="2" cellspacing="1"
								bgcolor="#B9DAF9">
								<tr>
								<td width="100%" height="24"  colspan="6" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white-12-b">
										发送失败
								</tr>
								
								<tr bgcolor="#FFFFFF">
									<td height="24" width="100%" align="center" class="blue3-12">
									<b>连接不到短信服务器，请稍候再试！</b>
								</tr>

								<tr>
        						<td height="26" colspan="6" bgcolor="#E0EDF8">
        						</td>
        						</tr>
        					
        						

							</table>
							
						</td>
						<td>
							&nbsp;
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
			    <img src="<%=request.getContextPath()%>/images/bt_back.gif" style="cursor:hand" onclick="_back()">
			
			</form>
		</div>
	</body>

</html>