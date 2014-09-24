<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.vo.FiletransferCardVO" %>
<%@page import="com.icss.oa.util.CommUtil"%>

<%

List cardlist = (List)request.getAttribute("cardlist");
Iterator cardIter = cardlist.iterator();

%>
<HTML><HEAD><TITLE>读邮件</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script language="JavaScript" src="../include/common.js"></script>
</head>

<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif">
<div align="left"> 
<form name="listcard" method="POST"  action="/cgi-bin/movemail">
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
<table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">全部贺卡列表</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg">
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="10%" bgcolor="#FFFBEF">&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="38%" bgcolor="#FFFBEF"><div align="center" class="title-04">贺卡名称</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="22%" bgcolor="#FFFBEF"><div align="center" class="title-04">上传时间</div></td>
                      <td width="0%" rowspan="100" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="30%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">上传人</div></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
<%
if(!cardIter.hasNext()){
%>
     <tr bgColor='#D8EAF8'>
         <td height="52" class="text-01" colspan=11>
             <div align="center"><br><br>没有贺卡！<br><br></div>
         </td>             
     </tr>            
     <tr>
         <td colspan=11 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
     </tr>
<%
}else{

int i=0;
String cardid = "";
String cardname = "";
Long createtime = new Long(0);
String uploadperson = "";
while(cardIter.hasNext()){
    FiletransferCardVO cVO = (FiletransferCardVO)cardIter.next();
	
	cardid = cVO.getCardid().toString();
	cardname = cVO.getCardname();
	createtime = cVO.getCreattime();
	String timeStr = CommUtil.getTime(createtime.longValue());
	uploadperson = cVO.getUploadperson();
	
	String color = "#F2F9FF";
	if(i % 2 == 0){
		color = "#D8EAF8";
	}
%>
    <tr bgColor=<%=color%> onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='<%=color%>';">
       <td nowrap class="text-01" align="center"><input type="checkbox" name="cardid" value="<%=cardid%>"></td>
       <td nowrap>
	       <div align="center" class="text-01">
	           <a href="<%=request.getContextPath()%>/servlet/ShowCardServlet?cardid=<%=cardid%>"> ·<%=cardname%></a>
	       </div>
       </td> 
       <td nowrap class="text-01"><div align="center"><%=timeStr%></div></td>
       <td nowrap height="22" class="text-01" align="center"><%=uploadperson%></td>                 
    </tr>
    <tr>
        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
    </tr>
<%
   i++;
}//end while

}//else
%>
                </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
</table>
<table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
	           <%@ include file = "../../include/defaultPageScrollBar.jsp" %>
	      </td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg">
              <div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div>
          </td>
        </tr>
</table> 
<br>
<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EEEEEE">
       <tr align="center">
            <td width="100%">
				<a href='javascript:reply()'"><img src="<%=request.getContextPath()%>/images/filetransfer/botton-reply.gif" width="70" height="22" hspace="10"  border=0></a>
				<a href='javascript:upload()'"><img src="<%=request.getContextPath()%>/images/filetransfer/botton-upload.gif" width="70" height="22" hspace="10"  border=0></a>
            </td>
      </tr>
</table>
</td></tr>
</table>
</form>
</div>
</body>
</html>
<script language="JavaScript">

<!--

function CheckAll()
  {
    for (var i=0;i<document.listmail.elements.length;i++)
    {
      var e = document.listmail.elements[i];
      if (e.name != 'allbox')
        e.checked = document.listmail.allbox.checked;
    }
  }
  
//-->

</script>