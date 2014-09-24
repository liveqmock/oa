<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.commsite.vo.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>top.gif</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<!--Fireworks MX 2004 Dreamweaver MX 2004 target.  Created Wed Mar 03 13:51:10 GMT+0800 2004-->
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
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">

</head>
<body text=#eeeeee bgColor=#d8eaf8 align=center  width=100% leftMargin=0 topMargin=0 marginheight="0" 
marginwidth="0">
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/01.gif" width="178" height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-00.gif" width="178" height="28"></td>
  </tr>
  <tr>
    <td background="<%=request.getContextPath()%>/images/07.gif">
		<table width="156"  border="0" align="center" cellpadding="0" cellspacing="0">
      		<tr><td height="6" colspan="2"></td></tr>
<%
	List list = (List)request.getAttribute("list");
	Iterator it = list.iterator();
	while(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
			<tr>
       			<td width=20></td> 
				<td height="22" colspan="2" class="left"><A HREF="<%=vo.getOcsLink()%>" target="mainFrame" class="text-01">·<%=vo.getOcsName()%></A>
         		</td>
        	</tr>
<%
		}
%>
    	</table>
	</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/08.gif" width="178" height="7"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td bgcolor="#D8EAF8">&nbsp;</td>
  </tr>
  <%@ include file="/include/searchLeft.jsp" %>
    </table>
</body>
</html>

