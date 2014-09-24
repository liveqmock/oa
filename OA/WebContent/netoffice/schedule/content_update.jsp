<%@ page contentType="text/html; charset=gbk" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.schedule.vo.*" %>
<%@ page import="com.icss.oa.config.TypeConfig" %>
<html>
<head>
<title>日程修改 </title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script language=javascript>
<!-- 

function Check_values(url) {
	if(document.Compose.headline.value=="") {
		alert("请输入事件名称!");
		document.Compose.headline.focus();
		return;
	}
	var sid=document.Compose.os_id.value;

	document.Compose.action=url+"/servlet/UpdateScheduleServlet?sid="+sid;
	document.Compose.submit();
//	location.href='javascript:history.go(-1)';
//	else {
//		if ( document.Compose.DurHour.selectedIndex==0
//			&& document.Compose.DurMin.selectedIndex==0
//			&& document.Compose.eventtime[0].checked == false) {
//					alert("请选择持续时间!");
//					document.Compose.DurHour.focus();
//					return;
//		}
//	}
//  document.Compose.Jsubmit.value = "1"; 
//  document.Compose.SubmitSave.value = "1"; 
//  document.Compose.submit();
}
  //确定事件的类型（宴会或纪念日等等）
   function os_type_select(){
      var osType=document.Compose.os_type.value;
      var i=0;
      for(i;i<document.Compose.type_select.length;i++){
        if(document.Compose.type_select.options[i].value==osType){
           document.Compose.type_select.options[i].selected=true;
           return;
        }
      
      }
  
   }
   
   //确定事件的时间类型
   function timeType(){
    var SCHEDULE_TASK_ALLDAY="0";
    var SCHEDULE_TASK_SEGMENT="1";
    var timeType;
    timeType=document.Compose.timeType.value;
    
    if(timeType==SCHEDULE_TASK_ALLDAY){
      document.Compose.eventtime[0].checked=true;
    }
    else{
      document.Compose.eventtime[1].checked=true;
    }
  }
  
  //确定事件的日期
  function  year_date(){
    
      var year=document.Compose.year_date.value;
      var i=0;
      for(i;i<document.Compose.startyear.length;i++){
        if(document.Compose.startyear.options[i].value==year){
           document.Compose.startyear.options[i].selected=true;
           return;
        }
      
      }
  }
  
   function  month_date(){
    
      var month=document.Compose.month_date.value;
      var i=0;
      for(i;i<document.Compose.startmon.length;i++){
        if(document.Compose.startmon.options[i].value==month){
           document.Compose.startmon.options[i].selected=true;
           return;
        }
      
      }
  }
  
  function  day_date(){
    
      var day=document.Compose.day_date.value;
      var i=0;
      for(i;i<document.Compose.startday.length;i++){
        if(document.Compose.startday.options[i].value==day){
           document.Compose.startday.options[i].selected=true;
           return;
        }
      
      }
  }
  
  //确定定时事件的具体时间
  
  function StartHour(){
    var hour=document.Compose.startHour.value;
      var i=0;
      for(i;i<document.Compose.StartHour.length;i++){
        if(document.Compose.StartHour.options[i].value==hour){
           document.Compose.StartHour.options[i].selected=true;
           return;
        }
      
      }
 
  }
  
  function StartMin(){
    var minute=document.Compose.startMinute.value;
      var i=0;
      for(i;i<document.Compose.StartMin.length;i++){
        if(document.Compose.StartMin.options[i].value==minute){
           document.Compose.StartMin.options[i].selected=true;
           return;
        }
      
      }
 
  }
  
  function durHour(){
    var hour=document.Compose.durHour.value;
      var i=0;
      for(i;i<document.Compose.DurHour.length;i++){
        if(document.Compose.DurHour.options[i].value==hour){
           document.Compose.DurHour.options[i].selected=true;
           return;
        }
      
      }
 
  }
  function durMinute(){
    var hour=document.Compose.durMinute.value;
      var i=0;
      for(i;i<document.Compose.DurMin.length;i++){
        if(document.Compose.DurMin.options[i].value==hour){
           document.Compose.DurMin.options[i].selected=true;
           return;
        }
      
      }
 
  }
// -->
</script>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" align="center">
<%
   OfficeScheduleVO vo=(OfficeScheduleVO)request.getAttribute("vo");
  //具体日期
   Long date1=vo.getOsDate();
   
   Calendar cal=Calendar.getInstance();
   
   //cal.setTimeInMillis(date1.longValue());
   Date date=new Date(date1.longValue());
   cal.setTime(date);
   int year=cal.get(Calendar.YEAR);
   int month=cal.get(Calendar.MONTH)+1;
   int day=cal.get(Calendar.DATE);
   
   
   //全天事件
   
   String timeType=vo.getOsTimetype();
   
   String timeFlag="1";
  if(timeType.equals(TypeConfig.SCHEDULE_TASK_ALLDAY)){
     timeFlag="0";
  }
  
    //定时事件
    //处理定时事件的时间
    
       int beginTime=vo.getOsBegin().intValue();
     
       int endTime=vo.getOsEnd().intValue();
       int durTime=endTime-beginTime;
    
       int beginHour=beginTime/3600000;
       int beginMinute=(beginTime%3600000)/60000;
       int durHour=durTime/3600000;
       int durMinute=(durTime%3600000)/60000;
  
    
     //处理提示时间
     int alertHour=vo.getOsAlertbuffer().intValue();
    


%>

<form method=POST name=Compose onSubmit="" action= >
   <input type=hidden name=date_sel value="<%=request.getParameter("date_sel")%>">
   <input type=hidden name=os_id value="<%=vo.getOsId()%>">
   <input type=hidden name=os_type value="<%=vo.getOsType()%>">
   <input type=hidden name=timeType value="<%=timeType%>">
   <input type=hidden name=year_date value="<%=year%>">
   <input type=hidden name=month_date value="<%=month%>">
   <input type=hidden name=day_date value="<%=day%>">
   <input type=hidden name=timeFlag value="<%=timeFlag%>">
   <input type=hidden name=startHour value="<%=beginHour%>">
   <input type=hidden name=startMinute value="<%=beginMinute%>">
   <input type=hidden name=durHour value="<%=durHour%>">
   <input type=hidden name=durMinute value="<%=durMinute%>">
  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1%" background="images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
    <td width="96%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">日程安排</td>
    <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
  </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
    <td >
      <table width="100%"  border="0" align="center" cellpadding="1" cellspacing="0">
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">名　称：
              <input name="headline" type=text class=form value='<%=vo.getOsTopic()%>' size=30 maxlength=80>
          </td>
		  <td width="0" rowspan="10" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
          <td valign=top nowrap width="51%" bgcolor="#D8EAF8">类　型：
              <select name="type_select" >
                <option value="纪念日" selected>纪念日</option>
                <option value="约会" >约会</option>
                <option value="生日" >生日</option>
                <option value="会议" >会议</option>
                <option value="会见" >会见</option>
                <option value="宴会" >宴会</option>
                <option value="婚礼" >婚礼</option>
                <option value="同学会" >同学会</option>
                <option value="出差" >出差</option>
                <option value="假期" >假期</option>
                <option value="网络事件" >网络事件</option>
                <option value="支付帐单" >支付帐单</option>
                <option value="电视节目" >电视节目</option>
                <option value="电影" >电影</option>
                <option value="体育赛事" >体育赛事</option>
                <option value="展览会" >展览会</option>
                <option value="其它" >其它</option>
              </select>
          </td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="3"></td>
        </tr>
        <tr bgcolor=#EEF4FF>
          <td bgcolor="#D8EAF8">地　点：
              <input type="text" name="place" class=form size="30" value='<%=vo.getOsPlace()%>'>
          </td>
          <td bgcolor="#D8EAF8">提　前：
            <input name="tixing" type="text" size="10" value="<%=alertHour%>">
            小时提醒</td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="3"></td>
        </tr>
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">日期和时间：
              <select name="startyear" >
                <option value="1999" >1999 年</option>
                <option value="2000" >2000 年</option>
                <option value="2001" >2001 年</option>
                <option value="2002" >2002 年</option>
                <option value="2003" >2003 年</option>
                <option value="2004" selected>2004 年</option>
                <option value="2005" >2005 年</option>
                <option value="2006" >2006 年</option>
                <option value="2007" >2007 年</option>
                <option value="2008" >2008 年</option>
                <option value="2009" >2009 年</option>
                <option value="2010" >2010 年</option>
              </select>
              <select name="startmon" >
                <option value="1" selected>1 月</option>
                <option value="2" >2 月</option>
                <option value="3" >3 月</option>
                <option value="4" >4 月</option>
                <option value="5" >5 月</option>
                <option value="6" >6 月</option>
                <option value="7" >7 月</option>
                <option value="8" >8 月</option>
                <option value="9" >9 月</option>
                <option value="10" >10 月</option>
                <option value="11" >11 月</option>
                <option value="12" >12 月</option>
              </select>
              <select name="startday" >
                <option value="1" >1 日</option>
                <option value="2" >2 日</option>
                <option value="3" >3 日</option>
                <option value="4" >4 日</option>
                <option value="5" selected>5 日</option>
                <option value="6" >6 日</option>
                <option value="7" >7 日</option>
                <option value="8" >8 日</option>
                <option value="9" >9 日</option>
                <option value="10" >10 日</option>
                <option value="11" >11 日</option>
                <option value="12" >12 日</option>
                <option value="13" >13 日</option>
                <option value="14" >14 日</option>
                <option value="15" >15 日</option>
                <option value="16" >16 日</option>
                <option value="17" >17 日</option>
                <option value="18" >18 日</option>
                <option value="19" >19 日</option>
                <option value="20" >20 日</option>
                <option value="21" >21 日</option>
                <option value="22" >22 日</option>
                <option value="23" >23 日</option>
                <option value="24" >24 日</option>
                <option value="25" >25 日</option>
                <option value="26" >26 日</option>
                <option value="27" >27 日</option>
                <option value="28" >28 日</option>
                <option value="29" >29 日</option>
                <option value="30" >30 日</option>
                <option value="31" >31 日</option>
              </select>
          </td>
          <td valign=top nowrap rowspan="2" width="51%" bgcolor="#D8EAF8"> 备　注：<br>
              <textarea name=notes class=textarea wrap=virtual rows=6 cols=30><%=vo.getOsDes()%></textarea>
          </td>
        </tr>
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">
            <input type=radio name="eventtime" value="0" checked>
            全天事件<br>
            <input type=radio name="eventtime" value="1" >
            定时事件&nbsp;&nbsp;
            <select name="StartHour" >
              <option value="0" selected>0</option>
              <option value="1" >1</option>
              <option value="2" >2</option>
              <option value="3" >3</option>
              <option value="4" >4</option>
              <option value="5" >5</option>
              <option value="6" >6</option>
              <option value="7" >7</option>
              <option value="8" >8</option>
              <option value="9" >9</option>
              <option value="10" >10</option>
              <option value="11" >11</option>
              <option value="12" >12</option>
              <option value="13" >13</option>
              <option value="14" >14</option>
              <option value="15" >15</option>
              <option value="16" >16</option>
              <option value="17" >17</option>
              <option value="18" >18</option>
              <option value="19" >19</option>
              <option value="20" >20</option>
              <option value="21" >21</option>
              <option value="22" >22</option>
              <option value="23" >23</option>
              <!--
                              <option value="0" >0</option>
                              <option value="1" >1</option>
                              <option value="2" >2</option>
                              <option value="3" >3</option>
                              <option value="4" >4</option>
                              <option value="5" >5</option>
                              <option value="6" >6</option>
                              <option value="7" >7</option>
                              <option value="8" >8</option>
                              <option value="9" >9</option>
                              <option value="10" >10</option>
                              <option value="11" >11</option>
                              <option value="12" >12</option>
                              <option value="13" >13</option>
                              <option value="14" >14</option>
                              <option value="15" >15</option>
                              <option value="16" >16</option>
                              <option value="17" >17</option>
                              <option value="18" >18</option>
                              <option value="19" >19</option>
                              <option value="20" >20</option>
                              <option value="21" >21</option>
                              <option value="22" >22</option>
                              <option value="23" >23</option>
-->
            </select>
            <select name="StartMin" >
              <option value="0" selected >:00</option>
              <option value="5" >:05</option>
              <option value="10" >:10</option>
              <option value="15" >:15</option>
              <option value="20" >:20</option>
              <option value="25" >:25</option>
              <option value="30" >:30</option>
              <option value="35" >:35</option>
              <option value="40" >:40</option>
              <option value="45" >:45</option>
              <option value="50" >:50</option>
              <option value="55" >:55</option>
              <!--
                              <option value="0" >:00</option>
                              <option value="1" >:30</option>
-->
            </select>
            <blockquote> 延续时间：
                <select name="DurHour">
                  <option value="0"  selected>0</option>
                  <option value="1" >1</option>
                  <option value="2" >2</option>
                  <option value="3" >3</option>
                  <option value="4" >4</option>
                  <option value="5" >5</option>
                  <option value="6" >6</option>
                  <option value="7" >7</option>
                  <option value="8" >8</option>
                  <!--
                                <option value="0" selected>0</option>
                                <option value="1" >1</option>
                                <option value="2" >2</option>
                                <option value="3" >3</option>
                                <option value="4" >4</option>
                                <option value="5" >5</option>
                                <option value="6" >6</option>
                                <option value="7" >7</option>
                                <option value="8" >8</option>
-->
                </select>
              小时
              <select name="DurMin">
                <option value="0" selected>:00</option>
                <option value="5" >:05</option>
                <option value="10" >:10</option>
                <option value="15" >:15</option>
                <option value="20" >:20</option>
                <option value="25" >:25</option>
                <option value="30" >:30</option>
                <option value="35" >:35</option>
                <option value="40" >:40</option>
                <option value="45" >:45</option>
                <option value="50" >:50</option>
                <option value="55" >:55</option>
                <!--
                                <option value="0" >:00</option>
                                <option value="1" >:30</option>
-->
              </select>
              分钟 </blockquote></td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="3"></td>
        </tr>
    </table></td>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
  </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
    <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center"> 　　 </td>
    <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
  </tr>
</table>
<p align="center"><a href="#"  onClick="Check_values('<%=request.getContextPath()%>')"><img  src="<%=request.getContextPath()%>/images/botton-ok.gif" width="70" height="22" hspace="10" border=0> </a><img  src="<%=request.getContextPath()%>/images/botton-return.gif" width="70" height="22" hspace="10" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand"></p>
</form>
<script language=javascript>
<!-- 
  os_type_select();
  
  timeType();
  year_date();
  month_date();
  day_date();
  if(document.Compose.timeFlag.value=="1"){
    StartHour();
    StartMin();
    durHour();
    durMinute();
  }
  
// -->
</script>
</body>
</html>
