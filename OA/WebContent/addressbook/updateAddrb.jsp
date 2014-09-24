<%@ page contentType="text/html; charset=gb2312" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);  
%>

<%
String parentId = request.getParameter("parentId");
String abcid = request.getParameter("abcid");
//out.print(parentId);
//out.print(abcid);

%>


<html>
<head>
<title>通讯录修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>


 <FORM name=form1 action=""  method=post>
 <input name="abcid" type="hidden" value="<%=request.getParameter("abcid")%>"> 
 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
 <tr>
      <td width="" valign="top">
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            		<td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">&#26032;&#22686;&#36890;&#35759;&#24405;</td>
            		<td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
          	</tr>
        	</table>
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            		<td width="100%">
              				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                			<tr>
                  					<td background="<%=request.getContextPath()%>/images/bg-09.jpg">       						  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="100" height="22" class="text-01" align="right">姓名：</td>
                                        <td width="2" rowspan="13" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcname" type="text" value="<%=request.getParameter("abcname")%>" size="29"></td>
                                        <td width="2" rowspan="11" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" height="22" class="text-01" align="right">单位：</td>
                                        <td width="2" rowspan="11" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abccompany" type="text" value="<%=request.getParameter("abccompany")%>" size="29"></td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                      <tr>
                                        <td width="100" height="22" class="text-01" align="right">单位地址：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abccompanyaddress" type="text" value="<%=request.getParameter("abccompanyaddress")%>" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">家庭住址：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcfamilyaddress" type="text" value="<%=request.getParameter("abcfamilyaddress")%>" size="29"></td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                                                            <tr>
                                        <td width="100" height="22" class="text-01" align="right">单位电话：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abccompanytel" type="text" value="<%=request.getParameter("abccompanytel")%>" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">住宅电话：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcfamilytel" type="text" value="<%=request.getParameter("abcfamilytel")%>" size="29"></td>
                                      </tr>
                                       <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                                                            <tr>
                                        <td width="100" height="22" class="text-01" align="right">手机：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abccellphone" type="text" value="<%=request.getParameter("abccellphone")%>" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">电子邮件：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcemail" type="text" value="<%=request.getParameter("abcemail")%>" size="29"></td>
                                      </tr>
                                       <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                                                            <tr>
                                        <td width="100" height="22" class="text-01" align="right">职务级别</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abclevel" type="text" value="<%=request.getParameter("abclevel")%>" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">备用电子邮件：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcemailsec" type="text" value="<%=request.getParameter("abcemailsec")%>" size="29"></td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                     
          								<tr>
                                        <td width="100" height="22"  class="text-01" align="right">备注</td>
                                        <td bgcolor="F2F9FF" colspan="5" class="text-01"><input name="abcmemo" type="text" value="<%=request.getParameter("abcmemo")%>" size="29">
                                        </td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                    </table></td>
                			</tr>
            				</table>
					</td>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
          	</tr>
        	</table>
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
            		<td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
          	</tr>
        	</table>
    </tr>
  	</table>
  	
  			<br>
		 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td> 
			    <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-update.gif"  onclick="javascript:_updateAddressbook('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;&nbsp;
                <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()"  style="cursor:hand"> 
              </div></td>  
          </tr>
        </table>
  	
  	
</form>


</body>
</html>

<script language="JavaScript">
 function _updateAddressbook(url,parentId){

	document.form1.action=url+"/servlet/UpdateAddressbookServlet?parentId="+parentId;
    document.form1.submit();
	window.top.leftFrame.location.reload();
	
 }
 </script>