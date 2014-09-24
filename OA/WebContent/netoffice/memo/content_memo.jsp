<%@ page contentType="text/html; charset=gbk" %>


<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.memo.vo.*" %>


<html xmlns="http:/www.w3.org/1999/xhtml">
<head>
<title>备忘录 </title>
<SCRIPT language=JavaScript>
<!--

/-->
</SCRIPT>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" marginwidth="0" 
marginheight="0">
<br />
<form name="formmemo"  action='' method='POST'>
<%
   OfficeMemoVO vo=(OfficeMemoVO)request.getAttribute("vo");
   Long date1=vo.getMemoTime();
   long date2=date1.longValue();
  // out.println(date2%(24*3600*1000));
   Date date=new Date(date2);

   SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd ' 'HH:mm  ");
   String formatTime = formatter.format(date);
   //int year=date.getYear()+1900;
   //int month=date.getMonth()+1;
   //int day=date.getDate();
    
   String cont=vo.getMemoContent();
 %>
  <table width="75%" border="0" align="center" cellpadding="2" cellspacing="1">
    <tr>
      <td align="center">
	    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td width="95%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05" >备忘录</td>
          <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>
	  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="2" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr bgcolor="#D8EAF8">
                <td width="50%" height="22" bgcolor="#FFFBEF">&nbsp;时间：<%=formatTime%></td>
                <td height="22" bgcolor="#FFFBEF">&nbsp;</td>
              </tr>
			  <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  <tr>
                <td height="22" bgcolor="#FFFBEF">&nbsp;主题：<%=vo.getMemoHeadline()%></td>
				<td width="50%" bgcolor="#FFFBEF">&nbsp;</td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
			  <tr valign="top" bgcolor="#F2F9FF">
                <td height=150 colspan="2" align=left><div align="center">
                  <table width="100%"  border="0" cellspacing="0" cellpadding="8">
                    <tr>
                      <td class="unnamed1">&nbsp;&nbsp;<%=cont%> </td>
                    </tr>
                    
                  </table>
                </div></td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="2" colspan="2"></td>
              </tr>
			  
            </table>		  </td>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="2" height="4"></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
						  <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
						  <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;                             </td>
						  
						  <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
						</tr>
	    </table>
	  
	  </td>
    </tr>
	
	<tr>
	<td align="center">
	  <img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand">
	</td>
	</tr>
	
    
  
</table>
</form>
</body>
</html>
