<%@ page contentType="text/html; charset=gb2312" %>


<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.journal.vo.*" %>
<%@ page import="com.icss.oa.util.CommUtil" %>

<html xmlns="http:/www.w3.org/1999/xhtml">
<head>
<title>日记</title>
<script language="JavaScript" src="<%=request.getContextPath()%>/include/common.js" type="text/JavaScript"></script>
<SCRIPT language=JavaScript>
<!--
function checkform(url)
{
	if (document.formriji.subject.value == "")
	{			
		alert('请填写主题以便以后阅读!');
		document.formriji.subject.focus();
		return false;
	}
	else{
	  document.formriji.action=url+"/servlet/UpdateJournalServlet";
	  document.formriji.submit();
	}
	 
	//location.href='javascript:history.go(-1)';
}

function rewrite(){
	
	document.formriji.subject.value="";
	document.formriji.content.value="";
	return false;
}
/-->
</SCRIPT>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" marginwidth="0" 
marginheight="0">
<br />
<form name="formriji"  action='' method='POST'>
<%
   OfficeJournalVO vo=(OfficeJournalVO)request.getAttribute("vo");
   Long date1=vo.getRjDate();
   long date2=date1.longValue();
   Date date=new Date(date2);
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   String formatTime = formatter.format(date);
   String content=vo.getRjContent();
   String cont=CommUtil.unformathtm(content);

%>
  <input type=hidden name="_page_num" value="<%=request.getParameter("_page_num")%>">
  <input type=hidden name="id1" value="<%=vo.getRjId()%>">
  <input type=hidden name="date1" value="<%=vo.getRjDate()%>">
  <input type=hidden name="weather" value="<%=vo.getRjWeather()%>">
  <table width="75%" border="0" align="center" cellpadding="2" cellspacing="1">
    <tr>
      <td align="center">
	    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td width="95%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05" >日记</td>
          <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>
	  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="2" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr bgcolor="#D8EAF8">
                <td width="50%" height="22" bgcolor="#FFFBEF">&nbsp;日期：<%=formatTime%></td>
                <td height="22" bgcolor="#FFFBEF">&nbsp;</td>
              </tr>
			  <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  <tr>
                <td height="22" bgcolor="#FFFBEF">&nbsp;主题：<input name="subject" type="text"  size="40" value="<%=vo.getRjHeadline()%>"/></td>
				<td width="50%" bgcolor="#FFFBEF"> 天气：<%=vo.getRjWeather()%>
            
          </td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
			  <tr><td colspan="2" bgcolor="#D8EAF8">
                <table border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                <td width="100%" bgcolor="#D8EAF8" align="left">
	              <textarea name="content" cols=84 rows=16>&nbsp;&nbsp;<%=cont%></textarea> 
	              <br>
                </td>
                </tr>
                <tr>
                <td width="100%" align="left" bgcolor="#D8EAF8">&nbsp;&nbsp;日记内容长度限制：中文不能超过2000字，英文不超过4000个字符</td>
                </tr>
                <tr height="10">
                <td width="100%" align="left" bgcolor="#D8EAF8"></td>
                </tr>
                </table>                
              </td></tr>
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
