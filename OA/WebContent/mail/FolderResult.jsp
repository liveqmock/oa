<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

String failResult = (String)request.getAttribute("failResult");
String title1 = (String)request.getAttribute("title1");
String title2 = (String)request.getAttribute("title2");

%>

<HTML><HEAD><TITLE><%=title1%></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<LINK href="<%=request.getContextPath()%>/Style/css_grey.css" rel=stylesheet>
<script>
function _back(){
    window.location.href = "<%=request.getContextPath()%>/servlet/ShowRootFolderServlet";
}
</SCRIPT>

</head>
<BODY leftmargin="10" >
<div align="center" >
          
          <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
          	<tr><td colspan="3" class="block_title"><%=title2%></td></tr>
            <tr>

              <td width="100%" bgcolor="#FFFFFF">
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                  <tr>
                    <td bgcolor="#FFFFFF">
                    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td height="80" class="text-01" bgcolor="#FFFFFF"><div align="center"><%=failResult%></div></td>
                        </tr>
                        <tr>
                          <td height="2"  bgcolor="#FFFFFF" class="text-01"></td>
                        </tr>
     
                    </table></td>
                  </tr>
              </table></td>

            </tr>
          </table>
          
          <p>
			<img src="<%=request.getContextPath()%>/images/botton-return.gif" style="cursor:hand" onClick="javascript:_back();">
          </p>

</div>
</body>

</html>