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

<form name="form1" method="post" action="">
<div align="center">
<br>
<BR>
<BR>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">配置我的邮箱参数</td>
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
	
<%
			
			List list = (List)request.getAttribute("list");
			Iterator result=list.iterator();
			FiletransferSetVO filetransetvo = null;
			if(result.hasNext()){
				filetransetvo=(FiletransferSetVO)result.next();
	
	
	
%>
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF">
                        <td width="20%" height="22"></td>
                        <td width="60%" height="22"><div align="left" class="title-04">当邮箱使用达到<input name="per" type="text" size="5" value="<%=filetransetvo.getFsPer()%>">%时提醒我</div></td>
                        <td width="20%" height="22"></td>
					  </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
				<!--  <tr  bgcolor="#FFFBEF">
                        <td width="20%" height="22"></td>
                        <td width="60%" height="22"><div align="left" class="title-04">当邮箱已满时
						<SELECT name=rule> 
							<OPTION value=1>在待办信息中提醒我</OPTION> 
							<OPTION value=2>删除最早的邮件</OPTION> 
						</SELECT> 
						</div></td>
                        <td width="20%" height="22"></td>
					  </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>-->
<%

			}//if
%>
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
				  <input name="id" type="hidden" value="<%=filetransetvo.getId()%>">
				  <input name="rule" type="hidden"  value="1">
    <img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onclick="javascript:_Update()">&nbsp;
          
             
<BR>
<BR>
<BR>
</div>
</form>
</BODY>
</HTML>

<script language="JavaScript">
 function _CheckForm(){
  if(!isDigital(document.form1.per.value)){
	alert("百分数须为整数！");
	document.form1.per.focus;
	return false;
  }
  if((document.form1.per.value > 100)||(document.form1.per.value < 0)){
	alert("百分数须为0到100之间的整数！");
	document.form1.per.focus;
	return false;
  }
   return true;
 }

 function _Update()
 {
  if(!_CheckForm()){
    return false
  }
   document.form1.action="<%=request.getContextPath()%>/servlet/SetAttributeServlet";
   document.form1.submit();
	//window.top.leftFrame.location.reload();
  }
</script>