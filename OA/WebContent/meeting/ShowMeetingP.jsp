<%@ page contentType="text/html; charset=gbk" %>

<%@ page import="com.icss.oa.meeting.vo.*" %>


<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" marginwidth="0" 
marginheight="0">
<br />
<form name="formriji"  action='' method='POST'>
<%
   MeetingPutVO vo=(MeetingPutVO)request.getAttribute("vo");
%>
  <table width="75%" border="0" align="center" cellpadding="2" cellspacing="1">
    <tr>
      <td align="center">
	    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td width="95%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05" >关于[<%=vo.getMeetingName()%>]的管理员的备注</td>
          <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>
	  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="2" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
			  <tr valign="top" bgcolor="#F2F9FF">
                <td height=150 colspan="2" align=left><div align="center">
                  <table width="100%"  border="0" cellspacing="0" cellpadding="8">
                    <tr>
                      <td class="unnamed1">&nbsp;&nbsp;<%= vo.getMeetingConcept()!=null?vo.getMeetingConcept():"还没有关于本次申请的管理员的备注" %> </td>
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
						  <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
						  
						  <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
						</tr>
	    </table>
	  
	  </td>
    </tr>
	
	<tr>
	<td align="center">
	  <img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="window.close();" style="cursor:hand">
	</td>
	</tr>
	
    
  
</table>
</form>
</body>
</html>
