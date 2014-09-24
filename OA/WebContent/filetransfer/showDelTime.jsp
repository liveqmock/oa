<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
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
	function _updateSchedule(url){
	    if(document.journalForm.startTime.value==""||document.journalForm.endTime.value=="")
	    {alert("请输入完整的开始和接受时间!");}
	    else{
		document.journalForm.action=url+"/servlet/ManagerDelServlet";
	    document.journalForm.submit();  	
	    }
	}	
	
		
	function _ClearTime(){
		document.journalForm.startTime.value="";
		document.journalForm.endTime.value="";
	}
</SCRIPT>
</head>


<body  background="<%=request.getContextPath()%>/images/bg-08.gif" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0> 
<FORM name=journalForm method=post>
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
  <tr><td width="" valign="top" >
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">管理员删除邮件</td>
          <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
        </tr>
      </table>
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
               
                    <tr>
                      <td height="22" class="text-01"><div align="right">欲删除的邮件时间：</div></td>
                      <td bgcolor="F2F9FF" class="text-01">&nbsp;从 
                        <input type="text" name="startTime" value="<%=(request.getParameter("startTime")!=null)?(request.getParameter("startTime")):"" %>" size="10" class="txt2" readonly > 
                        <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历下拉菜单" onclick="fPopUpCalendarDlg(startTime)">&nbsp;到
                        <input type="text" name="endTime"  value="<%= (request.getParameter("endTime")!=null)?(request.getParameter("endTime")):"" %>" size="10" class="txt2" readonly > 
                        <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历下拉菜单" onclick="fPopUpCalendarDlg(endTime)">&nbsp;&nbsp;<img onClick="javascript:_ClearTime()" src="<%=request.getContextPath()%>/images/button_rewrite.gif" align="absmiddle" style="cursor:hand"></td>
                      
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                  </table>
				</td>
              </tr>
            </table>
		  </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
      </table>
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
        </tr>
      </table>
    </tr>
  </table> 
  <table border="0" cellpadding="0" cellspacing="0" height="45" align="center">
     <tr><td><div align="center"><span class="text-01"><img onClick="javascript:_updateSchedule('<%=request.getContextPath()%>')" src="<%=request.getContextPath()%>/images/botton-ok.gif"style="cursor:hand"></span></div></td>
	 </tr></table>
  </table> 
</form>
</body>

</html>