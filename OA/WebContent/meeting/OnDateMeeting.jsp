<%@ page contentType="text/html; charset=gb2312" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.vo.*" %>

<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>

<%
    
    //得到当前的年、月、星期
    int year = new Integer((String)request.getAttribute("year")).intValue();
    int month = new Integer((String)request.getAttribute("month")).intValue(); 
    int week = new Integer((String)request.getAttribute("week")).intValue();

	//得到当前时间所在星期的开始时间
	long weekStart = ((Long)request.getAttribute("weekStart")).longValue();

	String SundayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*1,1).trim().substring(5,10);
	String MondayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*2,1).trim().substring(5,10);
	String TuesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*3,1).trim().substring(5,10);
	String WednesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*4,1).trim().substring(5,10);
	String ThursdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*5,1).trim().substring(5,10);
	String FridayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*6,1).trim().substring(5,10);
	String SaturdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*7,1).trim().substring(5,10);
	EntityManager entityManager = EntityManager.getInstance();
	Person person = null;
%>
<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module9");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>
<html>
<head>
<title></title>

<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
<!--
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
	    document.CalendarForm.action = "<%=request.getContextPath()%>/servlet/ShowDateMeetingServlet";
	    document.CalendarForm.submit();
	}
	
function _PrintMeetingBookServlet(){
     document.location.href = "<%=request.getContextPath()%>/servlet/PrintMeetingBookServlet?type=a3";
	 // document.CalendarForm.action="<%=request.getContextPath()%>/servlet/PrintMeetingBookServlet";
  	 //document.CalendarForm.submit();
}
function _PrintMeetingBookServlet1(){
     document.location.href = "<%=request.getContextPath()%>/servlet/PrintMeetingBookServlet?type=a4";
	 // document.CalendarForm.action="<%=request.getContextPath()%>/servlet/PrintMeetingBookServlet";
  	 //document.CalendarForm.submit();
}
//-->
</script>

</head>
<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<div>
<center><br>
<table width="60%"  border="0" cellpadding="0" cellspacing="0">
   <form name="CalendarForm" method="post" action="">
   <input type="hidden" name="year" value="<%=year%>">
   <input type="hidden" name="month" value="<%=month%>">
   <input type="hidden" name="week" value="<%=week%>">
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
      <td width="">
       &nbsp;&nbsp;
       <img src="<%=request.getContextPath()%>/images/meeting/button-a3.gif" border=0 style="cursor:hand"  onClick="_PrintMeetingBookServlet()">&nbsp;
      </td>
      <td width="">
       &nbsp;&nbsp;
       <img src="<%=request.getContextPath()%>/images/meeting/button-a4.gif" border=0 style="cursor:hand"  onClick="_PrintMeetingBookServlet1()">&nbsp;
      </td>
   </tr>
   </form>
</table><br>

<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">会议室安排表</td>
          <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
        </tr>
      </table>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
           <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>               
                      <td width="16%" bgcolor="#FFFBEF" height="30" class="title-04"><div align="center">会议室\星期</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期一<br>（<%=SundayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期二<br>（<%=MondayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期三<br>（<%=TuesdayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期四<br>（<%=WednesdayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期五<br>（<%=ThursdayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期六<br>（<%=FridayStr%>）</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" bgcolor="#FFFBEF"><div align="center" class="title-04">星期日<br>（<%=SaturdayStr%>）</div></td>                                            
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
						List list = (List)request.getAttribute("List");
						Iterator it = list.iterator();
						int i=0;
						while(it.hasNext()){
							++i;
							MeetingRoominformationVO vo = (MeetingRoominformationVO)it.next();	
							if(i%2==1){
%>
                    <tr bgColor=#D6E7F7 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D6E7F7';">
<%
		}
		else{
%>
					<tr bgcolor="#F2F9FF"; onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';">
<%
		}
%> 
                        <td width="16%" height="30" class="title-04" align="center"><%= vo.getMeetName()%></td>
                         <td width="12%"  >
                            <% String kk0 = vo.getMeetName()+"1";
							List list0 = (List)request.getAttribute(kk0);
							Iterator it0 = list0.iterator();
							while(it0.hasNext()){
								MeetingPutVO vo0 = (MeetingPutVO)it0.next();
								person = entityManager.findPersonByUuid(vo0.getMeetingPutperson());%>
						          <%= person.getFullName()%>&nbsp;<%= vo0.getMeetingPutdep() %>&nbsp;<%= vo0.getStartimehour() %>--<%= vo0.getEndtimehour() %><p>  
						           <%}%>
                        </td>
						<td width="12%" >
						<% String kk1 = vo.getMeetName()+"2";
							List list1 = (List)request.getAttribute(kk1);
							Iterator it1 = list1.iterator();
							while(it1.hasNext()){
								MeetingPutVO vo1 = (MeetingPutVO)it1.next();
								person = entityManager.findPersonByUuid(vo1.getMeetingPutperson());%>
						          <%= person.getFullName()%>&nbsp;<%= vo1.getMeetingPutdep()%>&nbsp;<%= vo1.getStartimehour() %>--<%= vo1.getEndtimehour() %><p>
						           <%}%></td>
						<td width="12%">
						<% String kk2 = vo.getMeetName()+"3";
							List list2 = (List)request.getAttribute(kk2);
							Iterator it2 = list2.iterator();
							while(it2.hasNext()){
								MeetingPutVO vo2 = (MeetingPutVO)it2.next();
								person = entityManager.findPersonByUuid(vo2.getMeetingPutperson());%>
						          <%= person.getFullName()%>&nbsp;<%= vo2.getMeetingPutdep()%>&nbsp;<%= vo2.getStartimehour() %>--<%= vo2.getEndtimehour() %><p>
						         <%  }%></td>
						<td width="12%" >
						<% String kk3 = vo.getMeetName()+"4";
							List list3 = (List)request.getAttribute(kk3);
							Iterator it3 = list3.iterator();
							while(it3.hasNext()){
								MeetingPutVO vo3 = (MeetingPutVO)it3.next();
								person = entityManager.findPersonByUuid(vo3.getMeetingPutperson());%>
						           <%= person.getFullName()%>&nbsp;<%= vo3.getMeetingPutdep()%>&nbsp;<%= vo3.getStartimehour() %>--<%= vo3.getEndtimehour() %><p>
						          <% }%></td>
						<td width="12%" >
						<% String kk4 = vo.getMeetName()+"5";
							List list4 = (List)request.getAttribute(kk4);
							Iterator it4 = list4.iterator();
							while(it4.hasNext()){
								MeetingPutVO vo4 = (MeetingPutVO)it4.next();
								person = entityManager.findPersonByUuid(vo4.getMeetingPutperson());%>
						         <%= person.getFullName()%>&nbsp;<%=  vo4.getMeetingPutdep()%>&nbsp;<%= vo4.getStartimehour() %>--<%= vo4.getEndtimehour() %><p>
						          <%}%></td>
						<td width="12%">
						<% String kk5 = vo.getMeetName()+"6";
							List list5 = (List)request.getAttribute(kk5);
							Iterator it5 = list5.iterator();
							while(it5.hasNext()){
								MeetingPutVO vo5 = (MeetingPutVO)it5.next();
								person = entityManager.findPersonByUuid(vo5.getMeetingPutperson());%>
						           <%= person.getFullName()%>&nbsp;<%= vo5.getMeetingPutdep()%>&nbsp;<%= vo5.getStartimehour() %>--<%= vo5.getEndtimehour() %><p>
						           <%}%></td>
						<td width="12%" >
						<% String kk6 = vo.getMeetName()+"7";
							List list6 = (List)request.getAttribute(kk6);
							Iterator it6 = list6.iterator();
							while(it6.hasNext()){
								MeetingPutVO vo6 = (MeetingPutVO)it6.next();
								person = entityManager.findPersonByUuid(vo6.getMeetingPutperson());%>
						           <%= person.getFullName()%>&nbsp;<%= vo6.getMeetingPutdep()%>&nbsp;<%= vo6.getStartimehour() %>--<%= vo6.getEndtimehour() %><p>
						          <% }%></td>                                            
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
                    <%}%>
                                
                    
               </table></td>
             </tr>
          </table>
          </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
     </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right">&nbsp;</div></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
</table>
</center>

</div>

<SCRIPT language=JavaScript>
<!--
	 setCalendarBegin();		
//-->
</SCRIPT>  
  
</body>
</html>
