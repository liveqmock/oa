<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<title>审批同步人员</title>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.anylinkcss {
	position: absolute;
	visibility: hidden;
	z-index: 100;
}
</style>
		<link href="../Style/css_lake.css" id="homepagestyle" rel="stylesheet"
			type="text/css" />

<script language="javascript">
    function checkuser(userid)
	{
	  	 
		window.open("/oabase/servlet/AuditAjaxServlet?result=login&LTPAToken=d2VibWFzdGVyKjEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDEyMzQqMSpTdHJpbmc=&userid="+userid,"_blank","left=400,top=250,width=270,height=50,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");

	}

	function submit1()
	{
        frm.submit();
		window.opener.location.reload();
	}
</script>
</head>

	<body>

			<%
				String operateid=request.getParameter("operateid");
				if(operateid==null||operateid.equals(""))
				{
				   out.println("出错了");
				   return;
				}
				%>


		<form name="frm" method="post"
			action="/oabase/servlet/AlertUserIdServlet">
			<input type="hidden" name="operateid" value=<%=operateid%> />
		<table border="0" cellpadding="0" cellspacing="1" width="100%"
				class="table_bgcolor">
			<tr class="block_title">
						<td colspan="10">
							修改用户名
						</td>
			</tr>
				<tr>

					<td width="20%" bgcolor="#FFFFFF" class="table_title">
						用户名
					</td>
					<td width="40%" bgcolor="#FFFFFF">
						<input name="usercode" type="text" id="usercode" />
					</td>

					<td bgcolor="#FFFFFF" class="table_title">
						<input type="button" name="check"
							onclick='checkuser(usercode.value)' value="查询是否可用" />
					</td>
				</tr>

				<tr>
					<td align="center">
						<input type="button" onclick='submit1()' value="确定" />
					</td>
				</tr>
		
		</table>


</form>






	</body>
</html>


