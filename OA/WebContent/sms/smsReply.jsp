<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<%
	String id = request.getParameter("id");
	String fromNo = request.getParameter("fromNo");
	String name = request.getParameter("name");
	String time = request.getParameter("time");
	String content = request.getParameter("content");
%>
<html>
<head>
<title>短信回复</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">

<script language="JavaScript" type="text/JavaScript">
  function _send()
    {
    if(_check()){
    document.form1.action="<%=request.getContextPath()%>/sms/smsR.jsp";
    document.form1.submit();
	}
	}
  function _check(){
  	if(""==document.form1.content.value)
	{
	alert('请输入短信内容');
	document.form1.content.focus();
	return false;
	}
	return true;
  }
  
</script>
 
</head>


<body>
<form name=form1 action="" method=post>
<input type=hidden name=phone value=<%=fromNo %>>
<input type=hidden name=name value=<%=name %>>

<center>
<p>&nbsp;</p>
		  <table width="95%" border="1" cellpadding="1" cellspacing="0"  bgcolor="#EEF4FF">
            <tr bgcolor="#A6D0F2" height=30px>
          <td colspan="2" >
            <div align="center">短信回复</div>
          </td>
        </tr>
		 <tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td height=26px width="22%">接收人:</td>
            <td width="78%"><%=name%></td>
         </tr>
         
         <tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td height=26px >短信内容:</td>
            <td ><%=content%></td>
         </tr>
         
		<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td height=26px>回复短信:</td>
            <td><textarea name='content' id='content' cols="50" rows="8" ></textarea></td>
         </tr>
		
		<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
          <td colspan="2" height=30px>
            <div align="center">
              <img src="<%=request.getContextPath()%>/images/send.gif" border="0" style="cursor:hand" onclick="_send()">
            </div>
          </td>
        </tr>
      </table>
      <div align="center"> </div>
</center>
</form>
</body>
</html>
