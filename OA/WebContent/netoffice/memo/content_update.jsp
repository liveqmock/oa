<%@ page contentType="text/html; charset=gbk" %>


<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.memo.vo.*" %>
<%@ page import="com.icss.oa.util.CommUtil" %>


<html xmlns="http:/www.w3.org/1999/xhtml">
<head>
<title>修改备忘录 </title>
<SCRIPT language=JavaScript>
<!--
function checkform(url)
{
	if (document.formmemo.subject.value == "")
	{			
		alert('请填写主题以便以后阅读!');
		document.formmemo.subject.focus();
		return false;
	}
	if (document.formmemo.content.value == "")
	{			
		alert('请填写内容以便以后阅读!');
		document.formmemo.content.focus();
		return false;
	}
	else{
	  document.formmemo.action=url+"/servlet/UpdateMemoServlet";
	  document.formmemo.submit();
	}
	 
	//location.href='javascript:history.go(-1)';
}

function rewrite(){
	
	document.formmemo.subject.value="";
	document.formmemo.content.value="";
	return false;
}
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
   Date date=new Date(date2);
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd ' 'HH:mm  ");
   String formatTime = formatter.format(date);
   
   String content=vo.getMemoContent();
   String cont=CommUtil.unformathtm(content);
   
   String _page_num=request.getParameter("_page_num");
%>
  <input type=hidden name="_page_num" value="<%=_page_num%>">
  <input type=hidden name="id1" value="<%=vo.getMemoId()%>">
  <input type=hidden name="date1" value="<%=vo.getMemoTime()%>">
  
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
          <td width="100%" background="<%=request.getContextPath()%>/images_new/bg-09.jpg" bgcolor="#D8EAF8">
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr bgcolor="#D8EAF8">
                <td width="50%" height="22" bgcolor="#FFFBEF">&nbsp;时间：<%=formatTime%></td>
                <td height="22" bgcolor="#FFFBEF">&nbsp;</td>
              </tr>
			  <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  <tr>
                <td height="22" bgcolor="#FFFBEF">&nbsp;主题：<input name="subject" type="text"  size="40" value="<%=vo.getMemoHeadline()%>"/></td>
				<td width="50%" bgcolor="#FFFBEF"> &nbsp;</td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
			  <tr valign="top" bgcolor="#F2F9FF">
                <td bgcolor="#D8EAF8" align="left"><div>
                    <textarea name="content" cols=74 rows=8>&nbsp;&nbsp;<%=cont%></textarea>
                        <br>
                </div></td>
              </tr>
              <tr>
				<td width="" bgcolor="#D8EAF8" align="left" valign="middle">&nbsp;&nbsp;说明：备忘录内容不能超过200字</td>              
              </tr>
              <tr>
              <td height="10"></td>
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
	  <img src="<%=request.getContextPath()%>/images/botton-ok.gif" onclick="checkform('<%=request.getContextPath()%>');" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
	  <img src="<%=request.getContextPath()%>/images/clear.gif" onclick="rewrite();" style="cursor:hand"> &nbsp;&nbsp;&nbsp;&nbsp;
	  <img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand">
	</td>
	</tr>
	
    
  
</table>
</form>
</body>
</html>
