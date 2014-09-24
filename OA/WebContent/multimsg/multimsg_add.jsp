<%@ page contentType="text/html; charset=gb2312" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.mutlimsg.vo.*" %>
<%
//System.out.println("++++++++++logmsglist++++++");

Integer typeid = request.getParameter("typeid")==null?new Integer(-1):new Integer(request.getParameter("typeid"));



%>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="index_files/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>
<SCRIPT src="index_files/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></SCRIPT>
</HEAD>

<body background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">
<form name="form1" method="post" action="">
<input type="hidden" name="typeid" value=<%=typeid %>>
 <div align="center">

    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">
          多媒体文件</td>
          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
  </table>
	  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		   <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr  bgcolor="#FFFBEF">
                      <td width="20%" height="22" bgcolor="D8EAF8">
                        <div align="right">文件名称： </div></td>
                      <td width="2" rowspan="12" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="80%" bgcolor="#F2F9FF" >
                        <input name="multimsgname" type="text"  size="40" maxlength="128" class=txt2></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                                 
                    <tr >
                      <td height="22" class="text-01"><div align="right">多媒体文件说明：
                      </div></td>
                      <td bgcolor="#F2F9FF"  class="text-01">
                      <textarea name="multimsgdesc" cols=64 rows=5></textarea> 
                       
                    </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr >
                      <td height="22" class="text-01"><div align="right">多媒体文件链接：
                      </div></td>
                      <td bgcolor="#F2F9FF"  class="text-01">
                       <input name="multimsglink" type="text"   size="64" maxlength="128" class=txt2>
                    </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    
                    
                    <tr >
                      <td height="22" class="text-01"><div align="right">备注：
                      </div></td>
                      <td bgcolor="#F2F9FF"  class="text-01">
                       <input name="multimsgmemo" type="text"   size="64" maxlength="128" class=txt2>
                    </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
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
          <td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
  </table>
  
  <br>
  
 <br>
    <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="javascript:_Add()" >&nbsp;
    
    <img src="<%=request.getContextPath()%>/images/botton-return.gif" border=0 style="cursor:hand" onClick="javascript:_return()" >
    
 </div>
</form>



</body>

</html>


<script language="JavaScript">
 function _Add(){

	
	form1.action="<%=request.getContextPath()%>/servlet/MultimsgAddServlet";
	
    form1.submit();
	
 }
  function _return(){

	
	form1.action="<%=request.getContextPath()%>/servlet/MultimsgListServlet?typeid=<%=typeid%>&user=manager";
	
    form1.submit();
	
 }
function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}


</script>