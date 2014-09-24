<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.filetransfer.vo.*"%>


<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</HEAD>
<BODY text=#eeeeee bgColor=#ffffff leftMargin=0 background="<%=request.getContextPath()%>/images/bg-08.gif" topMargin=0 marginheight="0" marginwidth="0">
<%
			session = request.getSession();
%>
<form name="form1" method="post" action="">
<div align="center">
<br>
<BR>
<BR>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">设置我的代理人</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          
           <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">	

				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF">
                        <td width="20%" height="22"></td>
                        <td width="60%" height="22" align="center">现在把我的工作暂时交给<input name="substituteUid" type="text" size="10" readonly>处理&nbsp;&nbsp;
							<img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" style="cursor:hand" align="absmiddle"  onClick="javascript:_selectPerson()">
						</td>
                        <td width="20%" height="22"></td>
					  </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>

				</table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
    <br>
    <br>
			<input name="flag" type="hidden" value="add">
    <img src="<%=request.getContextPath()%>/images/botton-ok.gif" border=0 style="cursor:hand" onclick="javascript:_Update()">&nbsp;
          
             
<BR>
<BR>
<BR>
</div>
</form>
</BODY>
</HTML>

<script language="JavaScript">
 function _CheckForm(){
	if(document.form1.substituteUid.value == ""){
		alert("请填写代理人");
		return false;
	}
	if(document.form1.substituteUid.value.indexOf(",") != -1){
		alert("只能够选择一个代理人");
		return false;
	}
   return true;
 }

 function _Update()
 {
  if(!_CheckForm()){
    return false
  }
   document.form1.action="<%=request.getContextPath()%>/servlet/SetEntrustServlet";
   document.form1.submit();
	//window.top.leftFrame.location.reload();
  }
function _selectPerson(){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=entrust','addressbook','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addPerson(usernamestring){
	document.form1.substituteUid.value = usernamestring;
	return true;
	
}
</script>