<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%

String boardId = request.getParameter("boardId");
String boardName = request.getParameter("boardName");

%>
<HTML><HEAD><TITLE>新华社论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/include/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript>
writeDIV();//启动日历javascript
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--

function _delete(){
	if(check()){
		document.workForm.action = "<%=request.getContextPath()%>/servlet/DeleteWorkServlet";
		document.workForm.submit();
	}
	else{
		alert("请选择要删除的工作！");
	}
}

function check(){
	for (var i=0;i<document.workForm.elements.length;i++)
   {
     var e = document.workForm.elements[i];
	  if (e.name == 'workid' && e.checked){
		 return true;
		}
   }
   return false;
}

-->
</SCRIPT>
</head>

<body  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">


<div align="center">
	 <FORM name=form1 method=post>
<TABLE width="95%" border=0 cellPadding=1 cellSpacing=1 bgcolor="#62a1c2" class="text-01">
  <TBODY>
  <TR>
    <TD class="text-01" align=left bgColor=#f7f7f7 height=23>
      <img src="<%=request.getContextPath()%>/images/bbs/bbshome.gif" style="cursor:hand" onclick="javascript:_gohome('<%=request.getContextPath()%>');" title="到交流园地首页">
      <a href="<%=request.getContextPath()%>/servlet/ShowIndexServlet" title="到交流园地首页">论坛首页</A> ==&gt; <A class="text-01"
      href="#"onClick="javascript:_topic('<%=request.getContextPath()%>','<%=boardId%>')"><%=boardName%></A> ==&gt; 
     <a class="text-01"> 批量删除 </a></TD>
   </TR></TBODY></TABLE></div><FONT 
size=1>&nbsp;</FONT><BR>

  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">批量删除『<%=boardName%>』的帖子</td>
      <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr> 
                  <td></td>
                  <td width="2" rowspan="7" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"></td>
                  <td></td>
                </tr>
                <tr> 
                  <td height="22" class="text-01"><div align="right">时间：</div></td>
                  <td bgcolor="F2F9FF" class="text-01">从 
                    <input type="text" name="startTime" size="16" class="txt2" readonly > 
                    <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" width="18" height="18" border=0 align="absmiddle" alt="点击 弹出日历下拉菜单" onclick="showhidecalendar('show','startTime','form1')"> 
                    到 
                    <input type="text" name="endTime" size="16" class="txt2" readonly> 
                    <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" width="18" height="18" border=0 align="absmiddle" alt="点击 弹出日历下拉菜单" onclick="showhidecalendar('show','endTime','form1')"></td>
                </tr>
                <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                </tr>
                <tr> 
                  <td height="22"><div align="right" class="text-01">是否包含精华贴：</div></td>
                  <td bgcolor="F2F9FF" class="text-01"><input type="radio" name="includePrime" value="yes">
                    是 
                    <input name="includePrime" type="radio" value="no" checked>
                    否</td>
                </tr>
				 <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                </tr>
                <tr> 
                  <td height="22"><div align="right" class="text-01"></div></td>
                  <td bgcolor="F2F9FF" class="text-01"><div align="left">&nbsp; &nbsp;<a class="text-01" href="#"onClick="javascript:_del('<%=request.getContextPath()%>','<%=boardId%>')">删 除</a></div></td>
                </tr>
                <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                </tr>
              </table></td>
          </tr>
      </table></td>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1%"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
      <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
      <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
    </tr>
  </table>
  <p></p>
  </form>

</body>

</html>
<script language="JavaScript">

 function _topic(url,boardId){

	
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
 }

  function _del(url,boardId){

	
	document.form1.action=url+"/servlet/BatchDeleteServelt?boardId="+boardId;
    document.form1.submit();
 }

 </script >