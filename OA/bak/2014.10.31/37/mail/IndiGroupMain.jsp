<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>个人分组管理</title>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<body>
<form name="frm" target="_blank">
<table width="1003" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td align="center">
			<jsp:include page="/include/top.jsp"></jsp:include>
		</td>
    </tr>
</table>
<table width="1003"  border="0" align="center">
<tr>
   <td width="1003" align="center">
        <table width="993" border="0" align="center">
           <tr height=450>
             <td width="250" id=menu valign="top">
                <iframe name="leftfrm" src="/oabase/servlet/IndiLeftServlet" border="0" width="100%" height="450" scrolling="auto" frameborder="0" framespacing="0"></iframe>
             </td>
             <td width="750" id=main valign="top">
	            <iframe name="bodyfrm" src="/oabase/servlet/ListGroupServlet" border="0" width="100%" height="450" scrolling="auto" frameborder="0" framespacing="0"></iframe>
             </td>
           </tr>
        </table>
     </td>
     <tr>
</table>
<jsp:include flush="true" page="/mail/Mail_Bottom.jsp"></jsp:include>
</form>
</body>
</html>
