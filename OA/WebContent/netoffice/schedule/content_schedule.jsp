<%@ page contentType="text/html; charset=gbk" %>


<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.schedule.vo.*" %>

<html>
<head>
<title>���¼� </title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script language=javascript>
<!-- 

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
// -->
</script>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" align="center">
<form method=POST name=Compose onSubmit="" action= >
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
  <input type=hidden name=timeType value="<%=timeType%>">
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
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">&nbsp;�����ƣ�
             <%=vo.getOsTopic()%>
          </td>
          <td valign=top nowrap width="51%" bgcolor="#D8EAF8">�ࡡ�ͣ�<%=vo.getOsType()%>
                        </td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
        </tr>
        <tr bgcolor=#EEF4FF>
          <td bgcolor="#D8EAF8">&nbsp;�ء��㣺
              <%=vo.getOsPlace()%>
          </td>
          <td bgcolor="#D8EAF8">�ᡡǰ��
            <%=alertHour%>
            Сʱ����</td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
        </tr>
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">&nbsp;���ں�ʱ�䣺
              <%=year%>&nbsp;��&nbsp;<%=month%>&nbsp;��&nbsp;<%=day%>&nbsp;��
          </td>
          <td valign=top nowrap rowspan="2" width="51%" bgcolor="#D8EAF8"> ����ע��<br>
              <textarea name=notes  rows=6  cols=50><%if(vo.getOsDes()!=null) out.print(vo.getOsDes());%> </textarea>
    
          </td>
        </tr>
        <tr>
          <td valign=top nowrap width="49%" bgcolor="#D8EAF8">
            <input type=radio name="eventtime" value="0" checked>
            ȫ���¼�<br>
            <input type=radio name="eventtime" value="1" >
            ��ʱ�¼�&nbsp;&nbsp;<br>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��ʼ��:&nbsp;&nbsp;&nbsp;
              <% if(beginHour<10){
              %>
              0<%=beginHour%> :
              <% } else{%>
              <%=beginHour%> :
              <%} if(beginMinute<10){%>
              0<%=beginMinute%>
              <%} else{%>
              <%=beginMinute%>
              <%}%>
           
            <blockquote> ����ʱ�䣺
              <% if(durHour<10){
               %>
               0<%=durHour%> :
              <%}  else{ %>
              <%=durHour%> :
              <%} if(durMinute<10){%>
              0<%=durMinute%>
              <%} else{%>
              <%=durMinute%>
              <% }
                         
              %>
                 </blockquote></td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
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
<p align="center"><img  src="<%=request.getContextPath()%>/images/botton-return.gif" width="70" height="22" hspace="10" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand"></p>
</form>
<script language=javascript>
<!-- 
  timeType();
// -->
</script>
</body>
</html>
