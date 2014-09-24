<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>人员选择</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>


<SCRIPT LANGUAGE="JavaScript">
	var urlPath = "<%=request.getContextPath()%>";
	var color = "#F2F9FF";
	var color0 = "#F2F9FF";
	var color1 = "#D8EAF8";
	var color2 = "#8CC0E8";
</SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/sendfile.js"></SCRIPT>
</head>
<body onload="setPersons(window.top.selectPersonsArray);" scroll="no" topmargin="10" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<table border="0" width="100%" cellspacing="0" cellpadding="0" height="100%">
	<tr>
		<td valign="top"><div align="center" style = "width:100%;height:100%;overflow:auto;">
		<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img id="s" src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">已选中人员或组</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%" id="data" background="<%=request.getContextPath()%>/images/bg-09.jpg">
					<!--这里存放着选择的人-->
	          </td>
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
		</div></td>
	</tr>
	<tr>
		<td height="30px" valign="bottom" nowrap align="center">
			<img src="<%=request.getContextPath()%>/images/botton-delete2.gif" border=0 style="cursor:hand" onClick="javascript:setNonePerson()" >
			<img src="<%=request.getContextPath()%>/images/button-delallperson2.gif" border=0 style="cursor:hand" onClick="javascript:removeSelect()" >
			<img src="<%=request.getContextPath()%>/images/botton-ok.gif" border=0 style="cursor:hand;z-index: 0"  name="commitSelect" onClick="javascript:selectToCommit()" >
			<img src="<%=request.getContextPath()%>/images/button-closeaddress.gif" border=0 style="cursor:hand" onClick="javascript:closeMe()" >
		</td>
	</tr>
</table>
</body>
</html>
