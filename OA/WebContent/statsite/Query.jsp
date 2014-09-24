<%@ page contentType="text/html; charset=gb2312" %>

<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.statsite.vo.*" %> 
<%@ page import="com.icss.oa.statsite.handler.*" %>
<html>
<head>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language="JavaScript" src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath()%>/include/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript>
writeDIV();//启动日历javascript
function fPopUpCalendarDlg(ctrlobj){
<!--
  showx = event.screenX - event.offsetX +4 ; // + deltaX;
  showy = event.screenY - event.offsetY + 18; // + deltaY;
  newWINwidth = 210 + 4 + 18;
  retval = window.showModalDialog("<%=request.getContextPath()%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
  if( retval != null ){
    ctrlobj.value = retval;
  }
//-->
}
</SCRIPT>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<SCRIPT language=JavaScript>
	function _updateSchedule(url){
		document.yearForm.action=url+"/servlet/ShowYearsite";
	    document.yearForm.submit();	
	}
	
	function _updateJournal(url){
		document.monthForm.action=url+"/servlet/ShowMonthsite";
	    document.monthForm.submit();
	}
	
	function _updateMemo(url){
		document.dayForm.action=url+"/servlet/ShowDateList";
	    document.dayForm.submit();	
	}
	function _ClearTime(){
		document.dayForm.Time.value="";
	}
</SCRIPT>

</head>

<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<div align="center">
<table width="60%"  border="0">
  <tr>
    <td>
	  <div align="center">
                <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <%
               Connection conn = null;
               long time = System.currentTimeMillis();
			   StatSiteHandler logHandler = new StatSiteHandler(conn);
			   String time1 = logHandler.getTimeByLong(new Long(time));
               %>
 
             <%  
                 String str="";
                 int num = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
                 switch(num){
								      case 1:  	str="一"; break;
								      case 2:  	str="二"; break;
								      case 3:  	str="三"; break;
								      case 4:  	str="四"; break;
								      case 5:  	str="五"; break;
								      case 6:  	str="六"; break;
								      case 7:  	str="天"; break;
								      default:  	str=""; break;
							}	      
                %>
                  <tr>
                    <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
                    <td  width="97%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05" align="left">请选择你要查询的日期：     今天的时间是：<%=time1 %>     星期<%= str %></td>
                    <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
                  </tr>
                </table>
                <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="2" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="1"></td>
                    <td width="100%">
					  
                       <table width="100%" height="33%"  border="0" cellpadding="0" cellspacing="0" valign=middle>
                         <form action="" method="post" name="yearForm">
						 <tr>
                          <td bgcolor="#FFFBEF" align="left" height="22">&nbsp;&nbsp;年报表</td>
                         </tr>
						 <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height=""></td>
                        </tr>
                          <tr>
                           <td bgcolor="#F2F9FF">
						     <table width="100%" border="0" height="70%" align=center cellpadding="0" cellspacing="0">
                        <tr bgColor=#F2F9FF  class="title-04">
                          <td height="22" bgcolor="#F2F9FF"><table  width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="50%" align="center">
                                 <select id="selSchedule" name="onlyYear" size="1" class="txt2">
                                    <option value="2000" selected>2000</option>
                                    <option value="2001">2001</option>
                                    <option value="2002">2002</option>
                                    <option value="2003">2003</option>
                                    <option value="2004">2004</option>
									<option value="2005">2005</option>
                                </select>年的报表</td>
								<td height="30" align="right" valign="bottom">
					  <img onClick="javascript:_updateSchedule('<%=request.getContextPath()%>');"  src="<%=request.getContextPath()%>/images/botton-ok.gif" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
                                
                                
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height=""></td>
                        </tr>
                    </table>
					
					
						   </td>
                         </tr>
						</form>
                </table>
				
				
				<table width="100%" height="33%"  border="0" cellpadding="0" cellspacing="0">
                        <form name="monthForm" action="" method="post">
						 <tr>
                          <td bgcolor="#FFFBEF" align="left" height="22">&nbsp;&nbsp;月报表</td>
                         </tr>
						 <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                          <tr>
                           <td bgcolor="#F2F9FF">
						     <table width="100%" border="0" height="70%" align=center cellpadding="0" cellspacing="0">
                        <tr bgColor=#F2F9FF  class="title-04">
                          <td height="42" bgcolor="#F2F9FF"><table  border="0" cellspacing="0" cellpadding="0" width="100%">
                              <tr>
                                <td width="60%" align="center">
                                 <select id="selSchedule" name="monthYear" size="1" class="txt2">
                                    <option value="2000" selected>2000</option>
                                    <option value="2001">2001</option>
                                    <option value="2002">2002</option>
                                    <option value="2003">2003</option>
                                    <option value="2004">2004</option>
									<option value="2005">2005</option>
                                </select>年 
                                 <select id="selSchedule" name="MonthMonth" size="1" class="txt2">
                                    <option value="1" selected>1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
									<option value="12">12</option>
                                </select>月的报表 </td>
								<td height="30" align="right"  valign="bottom">
					  <img  onClick="javascript:_updateJournal('<%=request.getContextPath()%>');" src="<%=request.getContextPath()%>/images/botton-ok.gif" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                    </table>
						</td>
				  </form>
                </table>
				
				
				<table width="100%" height="34%"  border="0" cellpadding="0" cellspacing="0">
				<form action="" method="post" name="dayForm">
                         <tr>
                          <td height="22" align="left" bgcolor="#FFFBEF">&nbsp;&nbsp;日报表</td>
                         </tr>
						 <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                          <tr>
                           <td bgcolor="#F2F9FF">
						     <table width="100%" border="0" height="70%" align=center cellpadding="0" cellspacing="0">
                        <tr bgColor=#F2F9FF  class="title-04">
                          <td height="32" bgcolor="#F2F9FF"><table  border="0" cellspacing="0" cellpadding="0" width="100%">
                              <tr>
                                 <td bgcolor="F2F9FF" class="text-01" align="center"> 
                                 <input type="text" name="Time" value="" size="10" class="txt2" readonly style="cursor:hand;" onclick="fPopUpCalendarDlg(Time)"> 
                                 <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历下拉菜单" onclick="fPopUpCalendarDlg(Time)">
                                 <img onClick="javascript:_ClearTime()" src="<%=request.getContextPath()%>/images/button_rewrite.gif" align="absmiddle" style="cursor:hand"></td>
                              <td height="30" align="right" valign="bottom">
					  <img  onClick="javascript:_updateMemo('<%=request.getContextPath()%>');" src="<%=request.getContextPath()%>/images/botton-ok.gif" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
							  </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                    </table>
					
					       </td>
                          </tr>
				  </form>
                </table>
				
    
					</td>
                    <td width="2" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="1"></td>
                  </tr>
                </table>
                <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
                    <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center"> </td>
                    <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
                  </tr>
                </table>
                
              </div>
	 
	
	</td>
  </tr>
</table>
</div>
  
</body>
</html>

<%
   try {
     if(conn!=null){
         conn.close();
     }
} catch (Exception e) {
	e.printStackTrace();
}

%>
