<%@ page contentType="text/html; charset=gbk" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.schedule.vo.*" %>
<%@ page import="com.icss.oa.config.TypeConfig" %>
<html>
<head>
<title>�ճ��޸� </title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script language=javascript>
<!-- 

function Check_values(url) {
	if(document.Compose.headline.value=="") {
		alert("�������¼�����!");
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
//					alert("��ѡ�����ʱ��!");
//					document.Compose.DurHour.focus();
//					return;
//		}
//	}
//  document.Compose.Jsubmit.value = "1"; 
//  document.Compose.SubmitSave.value = "1"; 
//  document.Compose.submit();
}
  //ȷ���¼������ͣ���������յȵȣ�
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
   
   //ȷ���¼���ʱ������
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
  
  //ȷ���¼�������
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
  
  //ȷ����ʱ�¼��ľ���ʱ��
  
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
  //��������
   Long date1=vo.getOsDate();
   
   Calendar cal=Calendar.getInstance();
   
   //cal.setTimeInMillis(date1.longValue());
   Date date=new Date(date1.longValue());
   cal.setTime(date);
   int year=cal.get(Calendar.YEAR);
   int month=cal.get(Calendar.MONTH)+1;
   int day=cal.get(Calendar.DATE);
   
   
   //ȫ���¼�
   
   String timeType=vo.getOsTimetype();
   
   String timeFlag="1";
  if(timeType.equals(TypeConfig.SCHEDULE_TASK_ALLDAY)){
     timeFlag="0";
  }
  
    //��ʱ�¼�
    //����ʱ�¼���ʱ��
    
       int beginTime=vo.getOsBegin().intValue();
     
       int endTime=vo.getOsEnd().intValue();
       int durTime=endTime-beginTime;
    
       int beginHour=beginTime/3600000;
       int beginMinute=(beginTime%3600000)/60000;
       int durHour=durTime/3600000;
       int durMinute=(durTime%3600000)/60000;
  
    
     //������ʾʱ��
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
    <td width="96%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">�ճ̰���</td>
    <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
  </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
    <td >
      <table width="100%"  border="0" align="center" cellpadding="1" cellspacing="0">
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">�����ƣ�
              <input name="headline" type=text class=form value='<%=vo.getOsTopic()%>' size=30 maxlength=80>
          </td>
		  <td width="0" rowspan="10" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
          <td valign=top nowrap width="51%" bgcolor="#D8EAF8">�ࡡ�ͣ�
              <select name="type_select" >
                <option value="������" selected>������</option>
                <option value="Լ��" >Լ��</option>
                <option value="����" >����</option>
                <option value="����" >����</option>
                <option value="���" >���</option>
                <option value="���" >���</option>
                <option value="����" >����</option>
                <option value="ͬѧ��" >ͬѧ��</option>
                <option value="����" >����</option>
                <option value="����" >����</option>
                <option value="�����¼�" >�����¼�</option>
                <option value="֧���ʵ�" >֧���ʵ�</option>
                <option value="���ӽ�Ŀ" >���ӽ�Ŀ</option>
                <option value="��Ӱ" >��Ӱ</option>
                <option value="��������" >��������</option>
                <option value="չ����" >չ����</option>
                <option value="����" >����</option>
              </select>
          </td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="3"></td>
        </tr>
        <tr bgcolor=#EEF4FF>
          <td bgcolor="#D8EAF8">�ء��㣺
              <input type="text" name="place" class=form size="30" value='<%=vo.getOsPlace()%>'>
          </td>
          <td bgcolor="#D8EAF8">�ᡡǰ��
            <input name="tixing" type="text" size="10" value="<%=alertHour%>">
            Сʱ����</td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="3"></td>
        </tr>
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">���ں�ʱ�䣺
              <select name="startyear" >
                <option value="1999" >1999 ��</option>
                <option value="2000" >2000 ��</option>
                <option value="2001" >2001 ��</option>
                <option value="2002" >2002 ��</option>
                <option value="2003" >2003 ��</option>
                <option value="2004" selected>2004 ��</option>
                <option value="2005" >2005 ��</option>
                <option value="2006" >2006 ��</option>
                <option value="2007" >2007 ��</option>
                <option value="2008" >2008 ��</option>
                <option value="2009" >2009 ��</option>
                <option value="2010" >2010 ��</option>
              </select>
              <select name="startmon" >
                <option value="1" selected>1 ��</option>
                <option value="2" >2 ��</option>
                <option value="3" >3 ��</option>
                <option value="4" >4 ��</option>
                <option value="5" >5 ��</option>
                <option value="6" >6 ��</option>
                <option value="7" >7 ��</option>
                <option value="8" >8 ��</option>
                <option value="9" >9 ��</option>
                <option value="10" >10 ��</option>
                <option value="11" >11 ��</option>
                <option value="12" >12 ��</option>
              </select>
              <select name="startday" >
                <option value="1" >1 ��</option>
                <option value="2" >2 ��</option>
                <option value="3" >3 ��</option>
                <option value="4" >4 ��</option>
                <option value="5" selected>5 ��</option>
                <option value="6" >6 ��</option>
                <option value="7" >7 ��</option>
                <option value="8" >8 ��</option>
                <option value="9" >9 ��</option>
                <option value="10" >10 ��</option>
                <option value="11" >11 ��</option>
                <option value="12" >12 ��</option>
                <option value="13" >13 ��</option>
                <option value="14" >14 ��</option>
                <option value="15" >15 ��</option>
                <option value="16" >16 ��</option>
                <option value="17" >17 ��</option>
                <option value="18" >18 ��</option>
                <option value="19" >19 ��</option>
                <option value="20" >20 ��</option>
                <option value="21" >21 ��</option>
                <option value="22" >22 ��</option>
                <option value="23" >23 ��</option>
                <option value="24" >24 ��</option>
                <option value="25" >25 ��</option>
                <option value="26" >26 ��</option>
                <option value="27" >27 ��</option>
                <option value="28" >28 ��</option>
                <option value="29" >29 ��</option>
                <option value="30" >30 ��</option>
                <option value="31" >31 ��</option>
              </select>
          </td>
          <td valign=top nowrap rowspan="2" width="51%" bgcolor="#D8EAF8"> ����ע��<br>
              <textarea name=notes class=textarea wrap=virtual rows=6 cols=30><%=vo.getOsDes()%></textarea>
          </td>
        </tr>
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">
            <input type=radio name="eventtime" value="0" checked>
            ȫ���¼�<br>
            <input type=radio name="eventtime" value="1" >
            ��ʱ�¼�&nbsp;&nbsp;
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
            <blockquote> ����ʱ�䣺
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
              Сʱ
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
              ���� </blockquote></td>
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
    <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center"> ���� </td>
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
