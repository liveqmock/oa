<%@ page contentType="text/html; charset=gb2312" %>


<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>д�ռ� </title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/include/common.js" type="text/JavaScript"></script>
<SCRIPT language=JavaScript>
<!--


function checkform(url)
{
	if (document.formriji.subject.value == "")
	{			
		alert('����д�����Ա��Ժ��Ķ�!');
		document.formriji.subject.focus();
		return false;
	}
	if (document.formriji.content.value == "")
	{			
		alert('����д�����Ա��Ժ��Ķ�!');
		document.formriji.content.focus();
		return false;
	}
 
    jlen = document.formriji.content.value;
	if (getLength(jlen) > 1000)
	{			
		alert('��д����ռ����ݹ�����');
		return false;
	}
	document.formriji.action=url+"/servlet/AddJournalServlet";
	document.formriji.submit();
	//location.href='javascript:history.go(-1)';
}

function rewrite(){
	
	document.formriji.subject.value="";
	document.formriji.content.value="";
	return false;
}

//-->
</SCRIPT>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" marginwidth="0" 
marginheight="0">
<br>
<form name="formriji"  action='' method='POST'>
<%
  String formatTime = request.getParameter("_curdate");
  if(formatTime==null){
      Date now=new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      formatTime = formatter.format(now);
  }
  //int year=now.getYear()+1900;
  //int month=now.getMonth()+1;
  //int day=now.getDate();
  String _page_num=request.getParameter("_page_num");
  //out.println("ppppppppppp"+_page_num);
%>
  <input name=time type=hidden value="<%=formatTime%>">
  <table width="75%" border="0" align="center" cellpadding="2" cellspacing="1">
    <tr>
      <td align="center">
	    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="3%" background="images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="28" height="23"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05" >д�ռ�</td>
          <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="16" height="23"></div></td>
        </tr>
      </table>
	  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="2" height="4"></td>
          <td width="100%">
		    <table width="100%"  border="0" align="center" cellpadding="1" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg" bgcolor="#D8EAF8" colspan="2">���ڣ�<%=formatTime%></td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg" bgcolor="#D8EAF8">���⣺<input name="subject" type="text"  size="50" /></td>
				<td width="35%" bgcolor="#D8EAF8"> ���� 
            <select name="weather" >
              <option value="��" selected>��</option>
              <option value="��" >��</option>
              <option value="����" >����</option>
              <option value="��ת����" >��ת����</option>
              <option value="С��" >С��</option>
              <option value="����" >����</option>
              <option value="����" >����</option>
              <option value="����" >����</option>
              <option value="Сѩ" >Сѩ</option>
              <option value="��ѩ" >��ѩ</option>
              <option value="��ѩ" >��ѩ</option>
              <option value="����" >����</option>
              <option value="���" >���</option>
            </select>
          </td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
			  <tr><td colspan="2" bgcolor="#D8EAF8">
                <table border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                <td width="100%" bgcolor="#D8EAF8" align="left">
	              <textarea name="content" cols=84 rows=16></textarea> 
	              <br>
                </td>
                </tr>
                <tr>
                <td width="100%" align="left" bgcolor="#D8EAF8">&nbsp;&nbsp;�ռ����ݳ������ƣ����Ĳ��ܳ���2000�֣�Ӣ�Ĳ�����4000���ַ�</td>
                </tr>
                <tr height="10">
                <td width="100%" align="left" bgcolor="#D8EAF8"></td>
                </tr>
                </table>
              </td></tr>
			  <tr>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
            </table>
		  </td>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="2" height="4"></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
						  <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
						  <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center"></td>
						  <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
						</tr>
			    </table>
	  
	  </td>
    </tr>
	<tr>
    <td align="center">
	  <img src="<%=request.getContextPath()%>/images/baocun.gif" onclick="checkform('<%=request.getContextPath()%>');" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
	  <img src="<%=request.getContextPath()%>/images/clear.gif" onclick="rewrite();" style="cursor:hand"> &nbsp;&nbsp;&nbsp;&nbsp;
	  <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand"> 
	</td>
    
  </tr>
    
  
</table>
</form>
</body>
</html>
