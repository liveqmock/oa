<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.filetransfer.vo.*"%>

<HTML>
<HEAD>
<TITLE>”Õœ‰¥Û–°…Ë÷√</TITLE>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
</HEAD>

<BODY marginheight="0" marginwidth="0">
<table width="1003" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td align="center">
			<jsp:include page="/include/top.jsp"></jsp:include>
		</td>
    </tr>
</table>
	<table width="1003" border="0" align="center">
		<tr height=480>
			<td width="200" valign="top">
				<iframe name="leftfrm" src="<%=request.getContextPath()%>/servlet/OrgTreeServlet?nodeUrl=SetSizeServlet" border="0" width="100%" height="100%" scrolling="yes" frameborder="0" framespacing="0"></iframe>
			</td>
			<td  width="800" valign="top">
				<iframe name="mainfrm" src="<%=request.getContextPath()%>/servlet/SetSizeServlet" border="0" width="100%"  height="100%" scrolling="yes" frameborder="0" framespacing="0"></iframe>
			</td>
		</tr>
	</table>
</BODY>