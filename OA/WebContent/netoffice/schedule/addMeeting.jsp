<%@ page contentType="text/html; charset=gbk" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.oa.netoffice.setNetoffice.handler.SetNetofficeHandler" %>

<%@ page import="java.sql.SQLException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.oa.meeting.vo.*"%>
<%@ page import="com.icss.common.log.ConnLog" %>

<% MeetingPutVO vo = (MeetingPutVO)request.getAttribute("vo");%>
<html>
<head>
<title>���¼� </title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script language=javascript>
<!-- 

function Check_values(url) {
	if(document.Compose.headline.value=="") {
		alert("�������¼�����!");
		document.Compose.headline.focus();
		return;
	}
	
	document.Compose.action=url+"/servlet/AddScheduleServlet";
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
// -->
</script>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" align="center">
<%
   long date1=System.currentTimeMillis();
   Calendar cal=Calendar.getInstance();
   Date date=new Date(date1);
   cal.setTime(date);
   int year=cal.get(Calendar.YEAR);
   int month=cal.get(Calendar.MONTH)+1;
   int day=cal.get(Calendar.DATE);

%>
<form method=POST name=Compose onSubmit="" action="" >
<input name=direct type=hidden value="<%=request.getParameter("direct")%>">
<input name=year type=hidden value="<%=request.getParameter("year")%>">
<input name=month type=hidden value="<%=request.getParameter("month")%>">
<input type=hidden name=year_date value="<%=year%>">
<input type=hidden name=month_date value="<%=month%>">
<input type=hidden name=day_date value="<%=day%>">
<input type=hidden name=date_sel value="<%=request.getParameter("date_sel")%>">
<%
  
  Connection conn =null;
  int hours=1;
  try{
     conn =DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
    ConnLog.open("addMeeting.jsp");
     SetNetofficeHandler sethandler=new SetNetofficeHandler(conn);
     Integer hours1=sethandler.getDefaultHours();
     hours=hours1.intValue();
 
     
  }
  catch(Exception e){
    out.println(e.getMessage());
  }
  finally {
				try {
					if (conn != null){
						conn.close();
						ConnLog.close("addMeeting.jsp");
						}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
  
%>

  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="2%" background="images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
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
              <input name="headline" type=text class=form value='<%= vo.getMeetingName()%>' size=30 maxlength=80>
          </td>
		  <td width="0" rowspan="10" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
          <td valign=top nowrap width="51%" bgcolor="#D8EAF8">�ࡡ�ͣ�
             <input name="type_select" type=text class=form value='����' size=30 maxlength=80>
          </td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="3"></td>
        </tr>
        <tr bgcolor=#EEF4FF>
          <td bgcolor="#D8EAF8">�ء��㣺
              <input type="text" name="place" class=form size="30" value='<%= vo.getMeetingRoom()%>'>
          </td>
          <td bgcolor="#D8EAF8">�ᡡǰ��
            <input name="tixing" type="text" size="10" value="<%=hours%>">
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
              <textarea name=notes class=textarea wrap=virtual rows=6 cols=30><%= vo.getPutMeno()==null?"":vo.getPutMeno()%></textarea>
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
 
  year_date();
  month_date();
  day_date();
   
// -->
</script>
</body>
</html>
