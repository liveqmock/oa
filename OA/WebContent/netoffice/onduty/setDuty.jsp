<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.onduty.vo.*" %>

<%@ page import="java.sql.Connection" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>

<%@ page import="com.icss.oa.netoffice.onduty.handler.DutyHandler" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<%
    //得到当前的年、月、星期
    int year = new Integer((String)request.getAttribute("year")).intValue();
    int month = new Integer((String)request.getAttribute("month")).intValue(); 
    int week = new Integer((String)request.getAttribute("week")).intValue();
    String orgUUid = (String)request.getAttribute("orgUUid");
    
    Iterator dItr = null;
    List periodList = (List)request.getAttribute("periodList");
    if(periodList.size()>0){
        dItr = periodList.iterator();
    }
	List periodStrList = (List)request.getAttribute("periodStrList");
	List periodDutyList = (List)request.getAttribute("periodDutyList");
	//得到当前时间所在星期的开始时间
	long weekStart = ((Long)request.getAttribute("weekStart")).longValue();
	String MondayStr = com.icss.oa.util.CommUtil.getTime(weekStart,1).trim().substring(5,10);
	String TuesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*1,1).trim().substring(5,10);
	String WednesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*2,1).trim().substring(5,10);
	String ThursdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*3,1).trim().substring(5,10);
	String FridayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*4,1).trim().substring(5,10);
	String SaturdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*5,1).trim().substring(5,10);
	String SundayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*6,1).trim().substring(5,10);
    Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
        ConnLog.open("setDuty.jsp");
        DutyHandler dHandler = new DutyHandler(conn);
%>
<html>
<head>
<title>排值班表</title>

<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
<!--
		
    function _selectPerson(periodStr,showTime,dutyName){
        document.addDutyform.periodStr.value = periodStr;
        document.addDutyform.showTime.value = showTime;
        document.addDutyform.dutyName.value = dutyName;
	    window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=dutyperson','addressbook','width=700,height=550,left=170,top=110,scrollbars=yes,resizable=yes');
    }
    
    function _addPerson(usernamestring){
	   document.addDutyform.action = "<%=request.getContextPath()%>/servlet/AddDutyPersonServlet";
	   document.addDutyform.submit();
	}
	
	//判断日期是合法，并生成日期
	function _getDate(){
	   if(document.SetDutyForm.time1.value==""||document.SetDutyForm.time2.value==""||document.SetDutyForm.time3.value==""||document.SetDutyForm.time4.value==""){
	       alert("请填写完整的信息！");
	       return false;
	   }else if((document.SetDutyForm.time1.value==document.SetDutyForm.time3.value)&&(document.SetDutyForm.time2.value==document.SetDutyForm.time4.value)){
	   	   alert("您填写的两个时间相同！请重新填写！");
	   	   return false;
	   }else if(checkHour(document.SetDutyForm.time1,"请填写正确的开始时间！")&checkHour(document.SetDutyForm.time3,"请填写正确的结束时间！")&checkMinute(document.SetDutyForm.time2,"请填写正确的开始时间！")&checkMinute(document.SetDutyForm.time4,"请填写正确的结束时间！")){
	       return true;
	   }else{		
	       return false;
	   }
	}
	
	function checkHour(obj,msg){
	    if(obj.value.length!=2){
	        alert(msg);
	        return false;
	    }else if(0<=obj.value&obj.value<=24){
	        return true;
	    }else{
	        alert(msg);
	        return false;
	    }
	}
	
	function checkMinute(obj,msg){
	    if(obj.value.length!=2){
	        alert(msg);
	        return false;
	    }else if(0<=obj.value&obj.value<60){
	        return true;
	    }else{
	        alert(msg);
	        return false;
	    }
	}
	
	function _AddNewPeriod(){
	   //判断日期是合法，并生成日期
	   if(_getDate()){
	       document.SetDutyForm.action = "<%=request.getContextPath()%>/servlet/AddPeriodServlet";
	       document.SetDutyForm.submit();
	   }
	}
	
	function _DelPeriod(url){
	    ok = confirm("确定你要删除这条记录吗？");
	    if(ok){
            document.location.href = url;
        }else{
            return false;
        }
	}
	
	function _DelDuty(orgUUid,dutyid,showtime){
	    ok = confirm("确定你要删除这条记录吗？");
	    if(ok){
            document.location.href = "<%=request.getContextPath()%>/servlet/DeleteDutyServlet?orgUUid="+orgUUid+"&dutyid="+dutyid+"&startDate="+showtime;
        }else{
            return false;
        }
	}
    
	function setCalendarBegin(){
		 var year=document.CalendarForm.year.value;
		 var month=document.CalendarForm.month.value;
		 var week=document.CalendarForm.week.value;
		 document.CalendarForm.year_select.selectedIndex=year-2001;
		 document.CalendarForm.month_select.selectedIndex=month-1;
		 document.CalendarForm.week_select.selectedIndex=week-1;
	}
	
	//选择日期后显示
	function _ShowDuty(){
	    document.CalendarForm.action = "<%=request.getContextPath()%>/servlet/ShowSetDutyServlet";
	    document.CalendarForm.submit();
	}
	
	function _editDutyName(orgUUid,weekStart){
	    if(document.SetDutyForm.dutyName.value==""){
	        alert("请输入新的值班表名称！");
	        return false;
	    }else{
	        var dutyName = document.SetDutyForm.dutyName.value;
	        document.location.href = "<%=request.getContextPath()%>/servlet/EditDutyNameServlet?dutyName="+dutyName+"&orgUUid="+orgUUid+"&weekStart="+weekStart;
	    }
	}
    
//-->
</script>

</head>

<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<div>
<center><br>
<table width="40%"  border="0" cellpadding="0" cellspacing="0">
   <form name="CalendarForm" method="post" action="">
   <input type="hidden" name="year" value="<%=year%>">
   <input type="hidden" name="month" value="<%=month%>">
   <input type="hidden" name="week" value="<%=week%>">
   <input type="hidden" name="orgUUid" value="<%=orgUUid%>">
   <tr>
      <td width="20%">
          <select name="year_select">
              <option value="2001">2001</option>
              <option value="2002">2002</option>
              <option value="2003">2003</option>
              <option value="2004">2004</option>
              <option value="2005">2005</option>
              <option value="2006">2006</option>
              <option value="2007">2007</option>
              <option value="2008">2008</option>
              <option value="2009">2009</option>
              <option value="2010">2010</option>
              <option value="2011">2011</option>
              <option value="2012">2012</option>
              <option value="2013">2013</option>
              <option value="2014">2014</option>
              <option value="2015">2015</option>
          </select>年
      </td>
      <td width="20%">&nbsp;
          <select name="month_select">
              <option value="1">1</option>
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
          </select>月
      </td>
      <td>
          &nbsp;&nbsp;
      </td>
      <td width="30%">第
          <select name="week_select">
              <option value="1">一</option>
              <option value="2">二</option>
              <option value="3">三</option>
              <option value="4">四</option>
              <option value="5">五</option>
              <% 
                if(((Long)request.getAttribute("weekth")).longValue()>5){
              %>
              <option value="6">六</option>
              <% } %>
          </select>周
      </td>
      <td>
          &nbsp;&nbsp;
      </td>
      <td width="30%">
         <A href="#" onclick="_ShowDuty();"><img src="<%=request.getContextPath()%>/images/botton-01.gif" width="59" height="19" border="0"></A>
      </td>
   </tr>
   </form>
</table><br>

<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"><%=request.getAttribute("dutyName")%></td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
           <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg">
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>               
                      <td width="16%" bgcolor="#FFFBEF" height="30" class="title-04"><div align="center">时间段\星期</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期一<br>（<%=MondayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期二<br>（<%=TuesdayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期三<br>（<%=WednesdayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期四<br>（<%=ThursdayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期五<br>（<%=FridayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期六<br>（<%=SaturdayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期日<br>（<%=SundayStr%>）</div></td>                                      
				    </tr>
                    <tr>
                      <td height="2"  background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>              
                    </tr>
                    <%  
                       int m = 0;
                       int i=0;
                       if(periodList.size()>0){
                           while(dItr.hasNext()){
                                dItr.next();
                                String bgcolor = "";
                           		if(i%2==0){
                           		    bgcolor = "#D8EAF8";
                           		}else{
                           		    bgcolor = "#F2F9FF";
                           		}
                           		String periodStr = periodStrList.get(i).toString();
                                i++;
                     %>
                     <tr>
                         <td bgcolor="<%=bgcolor%>" height="30" class="title-04">
                             <div align="center">
                                 <%=periodStr%>
                                 <a href="#" onclick="javascript:_DelPeriod('<%=request.getContextPath()%>/servlet/DelPeriodServlet?periodStr=<%=periodStr%>&orgUUid=<%=orgUUid%>&weekStart=<%=com.icss.oa.util.CommUtil.getTime(weekStart)%>')" title="删除此时间段"><img src="<%=request.getContextPath()%>/images/delFile.gif" border="0"></a>
                             </div>
                         </td>
                           	<%	
                           	    long loopDate = weekStart;
                           		for(int j=0;j<7;j++){  //七天
                           		    String showTime = com.icss.oa.util.CommUtil.getTime(loopDate,1);
                            %>
                         <td bgcolor="<%=bgcolor%>" class="title-04" nowrap>
                            <div align="center">
                           	<%
                           		    List onedayList = (List)periodDutyList.get(m);
                           		    m++;  //periodDutyList的序号
                           		    if(onedayList.size()>0){
                           		        for(int k=0;k<onedayList.size();k++){   //一天可能值多次班
                           		            if(k!=0)  out.print("<br>");  //不只一个要换行
                           		            OfficeDutyVO dVO = (OfficeDutyVO)onedayList.get(k);
                           		            String personUUid = dVO.getPersonuuid();
                           		            String personName = dHandler.getUserName(personUUid);
                           		            out.print(personName);
                           %>
                           		            <a href="#" title="删除此值班记录" onclick="javascript:_DelDuty('<%=orgUUid%>','<%=dVO.getDutyid()%>','<%=showTime%>')"><img src="<%=request.getContextPath()%>/netoffice/image/Junk.gif" border=0></a>
                           <%
                           		        }
                           		    }
                           %>
                                    <br><a href="#" title="给此时间段添加新的值班人" onclick="javascript:_selectPerson('<%=periodStr%>','<%=showTime%>','<%=request.getAttribute("dutyName")%>')"><img src="<%=request.getContextPath()%>/netoffice/image/compose.gif" border=0></a>
                           <%
                                    loopDate = loopDate + 86400000;
                           %>
                             </div>
                         </td>
                           <%	  } //for %>
                    </tr>
                    <tr>
                        <td height="2"  background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>              
                    </tr>
                                
                     <%    
                          } //while
                         
                      }  //if
                     %>
               </table></td>
             </tr>
          </table>
          </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
     </tr>
</table>
<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right">&nbsp;</div></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
</table></center>
</div>

<SCRIPT language=JavaScript>
<!--
	 setCalendarBegin();		
//-->
</SCRIPT>

<br>
<div><center>
<form name="addDutyform" method="post" action="">
    <input type="hidden" name="orgUUid" value="<%=orgUUid%>">
    <input type="hidden" name="periodStr">
    <input type="hidden" name="showTime">
    <input type="hidden" name="dutyName">
</form>
<form name="SetDutyForm" method="post" action="">
  <input type="hidden" name="orgUUid" value="<%=orgUUid%>">
  <input type="hidden" name="startDate" value="<%=com.icss.oa.util.CommUtil.getTime(weekStart)%>">
  <table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">值班表设置</td>
          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
  </table>
  <table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		   <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td colspan="3" height="22" align="left" bgcolor="#D8EAF8"><div>&nbsp;&nbsp;&nbsp;&nbsp;<b>自定义值班表名</div></td> 
                    </tr>
                    <tr>
                      <td height="2" colspan="3" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    </tr>
                    <tr bgcolor="#F2F9FF">
                        <td width="35%" height="22" bgcolor="#F2F9FF"><div align="right">自定义名称：</div></td>
                        <td width="2" rowspan="10" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="65%" bgcolor="#F2F9FF"><div><input name="dutyName" size="20" class=txt2>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/button-changedutyname.gif" onclick="javascript:_editDutyName('<%=orgUUid%>','<%=com.icss.oa.util.CommUtil.getTime(weekStart)%>')"></div></td>
                    </tr>
                    <tr>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr>
                        <td colspan="3" height="22" align="left" bgcolor="#D8EAF8"><div>&nbsp;&nbsp;&nbsp;&nbsp;<b>添加值班时间段</div></td> 
                    </tr>
                    <tr>
                      <td height="2" colspan="3" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    </tr>
                    <tr bgcolor="#F2F9FF">
                        <td width="35%" height="22" class="text-01" bgcolor="#F2F9FF"><div align="right">时间段开始时间：</div></td>
                        <td width="65%" class="text-01" bgcolor="#F2F9FF">
                            <div>
                              <input type="text" name="time1" size="3" class="txt2">：</b>
                              <input type="text" name="time2" size="3" class="txt2">
                              （24小时制，格式如：19:06或07:12） 
                            </div>
                        </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr>
                        <td height="22" class="text-01" bgcolor="#F2F9FF"><div align="right">时间段结束时间：</div></td>
                        <td class="text-01" bgcolor="#F2F9FF">
                          <div>
                              <input type="text" name="time3" size="3" class="txt2">：</b>
                              <input type="text" name="time4" size="3" class="txt2"> 
                          </div>
                        </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                  </table>
                  </td>
               </tr>
            </table>
          </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
  </table>
  <table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
  </table> 
  <br><center>
  <table width=80% border=0>
      <tbody>
         <tr> 
           <td>
              <div align=center> 
               <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="javascript:_AddNewPeriod()">&nbsp;
              </div>
           </td>     
		</tr>
      </tbody>
  </table>
</from>
</div>

</body>
</html>
	<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("setDuty.jsp");
					}
			} catch (Exception e) {
		}
	}
%>
