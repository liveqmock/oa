<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage="" %>
<%@ page pageEncoding = "gb2312"%>
<%@ page import="java.util.*"%>
<%
List templateList = (List)request.getAttribute("list");
String s= (String)request.getAttribute("errorS");

%>
<HTML><HEAD><TITLE>提示</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<BODY background="<%=request.getContextPath()%>/images/bg-08.gif">
 <table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
 <tr><td height="20%"></td></tr> 
  <tr>
    <td height="30%">
      <table align="center" width="390"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">&#31995;&#32479;&#25552;&#31034;</td>
          <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>
      <table width="390"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0D69A6">
        <tr>
          <td bgcolor="D8EAF8"><div align="center">
            <table width="376"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="10"></td>
              </tr>
              <tr>
                <td><img src="<%=request.getContextPath()%>/images/bg-28.gif" width="376" height="6"></td>
              </tr>
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-29.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="25%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-32.gif" width="80" height="79" vspace="10"></div></td>
                      <td width="75%" class="text-06"><div align="center">
                        <p><%=s!=null?s:"请从左面导航栏选择信息栏目！"%></p>
                      </div>                        </td>
                    </tr>
                  </table>                  </td>
              </tr>
              <tr>
                <td><img src="<%=request.getContextPath()%>/images/bg-30.gif" width="376" height="6"></td>
              </tr>
              <tr>
                <td height="10"></td>
              </tr>
            </table>
          </div></td>
        </tr>
      </table>
      <table width="50%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><div align="right"><img src="<%=request.getContextPath()%>/images/bg-27.gif" width="190" height="10"></div></td>
        </tr>
      </table>
          </td>
  </tr>
  <tr><td height="33%"></td></tr>
</table>
</body>
</html>