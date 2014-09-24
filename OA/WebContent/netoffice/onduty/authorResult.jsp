<%@ page contentType="text/html; charset=GBK" %>

<html>
<head>
<title>排值班表授权结果</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function _return(){
	document.location.href = "<%=request.getContextPath()%>/servlet/ListAuthorServlet?orgid=<%=request.getAttribute("orgUUid")%>";
}
</script>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>
<br>
	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr><td valign="top">
   <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">排值班表权限授权结果</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                	<%
                	if(request.getAttribute("authorSuccess")!=null){
                	%>
                    <tr bgColor=#D6E7F7>
                      <td nowrap width="15%" height="25" align="right" align="center" class="title-04">成功授权给：</td>
                      <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td nowrap align="left" width="85%" align="center" class="title-04"><%=request.getAttribute("authorSuccess")%></td>                      
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <%
                    }
                    %> 
                	<%
                	if(request.getAttribute("notAuthor")!=null){
                	%>
                    <tr bgcolor="#F2F9FF">
                      <td nowrap height="25" width="15%" align="right" align="center" class="title-04">曾经授权过：</td>
                      <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td nowrap align="left" width="85%" align="center" class="title-04"><%=request.getAttribute("notAuthor")%></td>                      
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <%
                    }
                    %>                     
                </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
</table>
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
</table>
</td></tr></table>
<table border="0" cellpadding="0" cellspacing="0" width="69%" height="40" align="center">
          <tr>
            <td valign="bottom" nowrap align="center" class="text-01">
            	<img src="<%=request.getContextPath()%>/images/botton-return.gif" hspace="10"  border="0" style="cursor:hand;" onclick='javascript:_return()'>            	
            </td>
          </tr>
  </table>
</body>
</html>

