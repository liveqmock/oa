<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%
String contextPath = request.getContextPath();
%>
<html>
<head>
<title>[主要功能]</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" href="../include/style.css">
<style type="text/css">
</style>
<script type="text/javascript" src="../include/judge.js"></script>
<SCRIPT src="/resourceone/common/common.js"></SCRIPT>
<script language="JavaScript" src="<%=request.getContextPath()%>/include/formVerify.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/include/runFormVerify.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/include/extendString.js"></script>
<script language="javascript">
        function _vote(){		           
        		document.myForm.action = "<%=contextPath%>/servlet/YjsMainDutyListServlet";
			    document.myForm.submit();
		}
		function _voteview(){		           
        		document.myForm.action = "<%=contextPath%>/servlet/YjsMainDutyManageListServlet";
			    document.myForm.submit();
		}
		function _creatpwd(){		      
		   if(confirm("您确定要重置所有密码吗？这样原来设置的所有密码将失效！")){   
        		document.myForm.action = "<%=contextPath%>/servlet/CreatPassWordServlet";
			    document.myForm.submit();
			    }
		}
		function _addperson(){
		        document.myForm.action = "<%=contextPath%>/servlet/YjsDutyTypeManageListServlet";
		        document.myForm.submit();
		
		
		}
		

		
</script>
</head>

<BODY text="#000000" leftMargin="0" topMargin="10">
<form name="myForm" method="post">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="text">
	<tr>
		<TD width="53%">
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD width="2%" background=../images/bg-12.gif><IMG height=22 src="../images/bg-10.gif" width=14></TD>
				<TD class=title-05 background=../images/bg-12.gif>
				<div align="center" class="style1">
				<div align="left">主要功能11</div>
				</div>
				</TD>
				<TD width="1%">
				<DIV align=right><IMG height=22 src="../images/bg-11.gif" width=20></DIV>
				</TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>

				<TD width="100%">
				<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=0 bgcolor="#0055A2">
					<TR>
						<TD background=../images/bg-09.jpg class="dot-border">
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TR class=title-04>
								<TD height="22" colspan="1" align=center valign="middle" noWrap bgColor=#fffbef class="dot-right">&nbsp;</TD>
							</TR>
							<TR bgColor=#f2f9ff>
								<TD align=center valign="top" noWrap class=dot-right>
								<table width="60%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="30%">&nbsp;</td>
										<td width="70%">&nbsp;</td>
									</tr>
									<tr>
										<td>
										<div align="left"><a href="<%=contextPath%>/servlet/DepartmentListServlet" target=_blank>研究室值班日志</a></div>
										</td>
										<td align="center"><input type="button" class="word7" value="研究室值班日志" onClick="_vote()"></td>
									</tr>
									<tr>
										<td width="30%">&nbsp;</td>
										<td width="70%">&nbsp;</td>
									</tr>
									<tr>
										<td>
										<div align="left"><a href="<%=contextPath%>/servlet/DepartmentStatListServlet" target=_blank>值班日志管理</a></div>
										</td>
										<td align="center"><input type="button" class="word7" value="值班日志管理" onClick="_voteview()"></td>
									</tr>
									<tr>
										<td width="30%">&nbsp;</td>
										<td width="70%">&nbsp;</td>
									</tr>
									<tr>
										<td>
										<div align="left">值班日志类型管理</div>
										</td>
										<td align="center"><input type="button" class="word7" value="类型管理" onClick="_addperson()"></td>
									</tr>
									<tr>
										<td width="30%">&nbsp;</td>
										<td width="70%">&nbsp;</td>
									</tr>
									
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
								</TD>
							</TR>
						</TABLE>
						</td>
					</tr>
				</table>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
					<TR>
						<TD width="1%" background=../images/bg-23.jpg><IMG height=21 src="../images/bg-21.jpg" width=10></TD>
						<TD class=text-01 width="97%" background=../images/bg-23.jpg></TD>
						<TD width="2%" background=../images/bg-23.jpg>
						<DIV align=right><IMG height=21 src="../images/bg-22.jpg" width=11></DIV>
						</TD>
					</TR>
				</TABLE>
				</TD>
			</tr>
		</TABLE>
		</td>
	</tr>
</table>
</form>
</body>
</html>
