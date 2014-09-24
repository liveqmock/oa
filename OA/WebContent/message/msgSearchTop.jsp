<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<html>
<head>
<title>信息检索</title>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<SCRIPT language="JavaScript" src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language=JavaScript>
function fPopUpCalendarDlg(ctrlobj){
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=request.getContextPath()%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
}
function _search(){
	if(getLength(Form_SearchSort.content.value)>140){
    	alert("内容过长（最大长度为70个汉字或140个英文字母）！");
    	return;
    }
	Form_SearchSort.submit();
}
function _reset(){
	Form_SearchSort.content.value="";
	Form_SearchSort.msgDateStart.value="";
	Form_SearchSort.msgDateStop.value="";
}
</SCRIPT>


<body background="<%=request.getContextPath()%>/images/bg-08.gif" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>
<form name="Form_SearchSort" action="<%=request.getContextPath()%>/servlet/SearchShortMsgServlet"  target="searchResultFrame">
<br>
<table align="center" width="95%">
	<tr>
		<td>
        <table align="center" width="100%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">信息检索条件列表</td>
            <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
          </tr>
        </table>
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            <td width="100%">
            <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">             
                      <tr>
                        <td nowrap width="20%" align="right" class="text-01">内容：</td>
                        <td width="2" rowspan="100" align="center" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap width="30%" align="left" bgcolor="F2F9FF" class="text-01">
            				<input name="content" type="text" size="30">&nbsp;</td>
                        <td width="2" rowspan="100" align="center" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
           				<td nowrap width="20%" align="right" class="text-01">时&nbsp;&nbsp;间：</td>	
           				<td width="2" rowspan="100" align="center" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
           				<td nowrap width="30%" align="left" bgcolor="F2F9FF" class="text-01">
           					<input name="msgDateStart" type="text" size="9" readonly  >
							<img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历" onclick="fPopUpCalendarDlg(msgDateStart)">	
							到：<input name="msgDateStop" type="text" size="9" readonly  >
							<img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历" onclick="fPopUpCalendarDlg(msgDateStop)"></td>		
                      </tr>
                      <tr>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
                  </table></td>
                </tr>
            </table></td>
            <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
          </tr>
        </table>
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="1%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
            <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="center">
          					<input align="absmiddle" type="checkbox" name="searchOld" value="searchOld" >在历史纪录中搜索&nbsp;&nbsp;&nbsp;
          					<img align="absmiddle" src="<%=request.getContextPath()%>/images/leftbutton.gif" style="cursor:hand;"  onclick="javascript:_search()">&nbsp;&nbsp;&nbsp;
          					<img align="absmiddle" src="<%=request.getContextPath()%>/images/leftbutton_rewrite.gif" style="cursor:hand;"  onclick="javascript:_reset()"></div></td>
            <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
          </tr>
        </table>
</form>
       </table>
</body>
</html>