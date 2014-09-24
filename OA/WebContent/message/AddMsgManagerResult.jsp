<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.message.vo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" /><link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function _return(){
	this.location="<%=request.getContextPath()%>/servlet/ListMsgManagerServlet?_page_num=<%=request.getParameter("_page_num")%>&orgid=<%=request.getParameter("orgid")%>&orgname=<%=request.getParameter("orgname")%>";
}
</script>
<title>添加短信发送人结果列表</title>  
</head>
<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td>
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">添加短信发送人结果列表</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
	</table>
    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
<%
	List list = (List)request.getAttribute("resultList");
	if(list!=null){
	Iterator it = list.iterator();
	int i=0;
	while(it.hasNext()){
		++i;
		String result = (String)it.next();
		if(i%2==1){
%>
                    <tr bgColor=#D6E7F7 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D6E7F7';">
<%
		}
		else{
%>
					<tr bgcolor="#F2F9FF"; onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';">
<%
		}
%>
                        <td nowrap height="22" align="center" class="text-01">&nbsp;<%=result%>&nbsp;</td>
                     </tr>
                     <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     </tr>
<% 
 	}
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
          		<td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"></td>
          		<td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        	</tr>
		</table>
	</td></tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="69%" id="AutoNumber2" height="65" align="center">
          	<tr>
            <td align="center" class="text-01"><img src="<%=request.getContextPath()%>/images/botton-return.gif" onClick="_return()" hspace="10" style="cursor:hand"></td>
          </tr>
</table>
</body>
</html>
