<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="com.icss.oa.meeting.vo.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<LINK  href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</style>
</head>
<% 
   MeetingPutVO vo = (MeetingPutVO)request.getAttribute("vo");
   int nPosition1,nPosition2 = 0;
   nPosition1 = vo.getStartimehour().lastIndexOf(":");
   nPosition2 = vo.getEndtimehour().lastIndexOf(":");
   %>
<script language="JavaScript">

function fPopUpCalendarDlg(ctrlobj){
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=request.getContextPath()%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
}

function IsFormItemEmpty(){
  	if(document.form_update.MeetingName.value==""){
    	alert("请填写会议室名称名称！");
    	return true;
    }
 	if(document.form_update.MeetingStartTime.value==""){
    	alert("请填写会议开始的时间");
    	return true;
    }
    if(document.form_update.MeetingEndTime.value==""){
    	alert("请填写会议结束的时间");
    	return true;
    }
    if(document.form_update.MeetingRoom.value==""){
    	alert("请填写申请的会议室");
    	return true;
    }
	return false;
}

function _Update(){

     	if(!IsFormItemEmpty()){
			document.form_update.action="<%=request.getContextPath()%>/servlet/EditMeetingPutSevlet";
			document.form_update.submit();
		}
}

function _MeetingUse(){

	window.open("<%=request.getContextPath()%>/servlet/MeetingUseingServlet?doFuntion=_addMeeting","","width=800,height=600,scrollbars=yes,resizable=yes,left=100,top=110");
}

function _addMeeting(meetingstring){
    
    document.form_update.MeetingRoom.value = meetingstring;  
	return true;
}

function _infoputMeeting(){

     	if(!IsFormItemEmpty()){
     		
		     	    document.form_update.flag.value = "会议信息发表";
					document.form_update.action="<%=request.getContextPath()%>/servlet/InfoputMeetingSevlet";
					document.form_update.submit();  
				
		
		}
}

function _infoputMeeting1(){

     	if(!IsFormItemEmpty()){
     	    document.form_update.flag.value = "会议开始";
			document.form_update.action="<%=request.getContextPath()%>/servlet/InfoputMeetingSevlet";
			document.form_update.submit();
		}
}
function _infoputMeeting2(){

     	if(!IsFormItemEmpty()){
            document.form_update.flag.value = "会议结束";
			document.form_update.action="<%=request.getContextPath()%>/servlet/InfoputMeetingSevlet";
			document.form_update.submit();
		}
}

function  _changefile(){
  if(document.form_update.access.value!="null"){
  		document.form_update.access1.value=document.form_update.access.value;
  }
}

</script>


<body  background="<%=request.getContextPath()%>/images/bg-08.gif">
 <form name="form_update" action="" method="post" enctype="multipart/form-data">
  	<input name="putId" type="hidden" value="<%= request.getParameter("putId")%>">
  	<input name="status" type="hidden" value="<%= vo.getMeetingSatus()%>">
  	<input name="flag" type="hidden" value="">
	<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> 
        修改会议申请信息</td>
          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
  </table>  
	  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                   <tr>
                      <td width="100" height="22" class="text-01" align="right">会议名称：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingName" type="text" value="<%= vo.getMeetingName()%>" size="29"></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议类型：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <select name="MeetingType">
  					  <option value="普通会议" <%= "普通会议".equals(vo.getMeetingType())?"selected":""%> >普通会议</option>
  					  <option value="保密会议" <%= "保密会议".equals(vo.getMeetingType())?"selected":""%> >保密会议</option>
  					  <option value="电话会议" <%= "电话会议".equals(vo.getMeetingType())?"selected":""%> >电话会议</option>
  					  <option value="重要会议" <%= "重要会议".equals(vo.getMeetingType())?"selected":""%> >重要会议</option>
					  </select>
                       </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td width="110" height="22" class="text-01" align="left">会议申请(联系人)：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                      <%
                         EntityManager entityManager = EntityManager.getInstance();
	                     Person person = entityManager.findPersonByUuid(vo.getMeetingPutperson()); %>
                      <%= person.getFullName()%>&nbsp;&nbsp;&nbsp;联系电话：<%= person.getOfficetel()%>
                	  
                      </td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议申请部门：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <%= vo.getMeetingPutdep()%>
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                   <tr>
                      <td width="100" height="22" class="text-01" align="right">会议开始时间：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingStartTime" type="text" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(vo.getStarttimeday().longValue()))%>" size="10"  readonly>
                	  <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历" onclick="fPopUpCalendarDlg(MeetingStartTime)">

                      <select name="MeetingStartTimeHour">
  <option value="1" <%= vo.getStartimehour().substring(0,nPosition1).equals("1")?"selected":""%>>1</option>
  <option value="2" <%= vo.getStartimehour().substring(0,nPosition1).equals("2")?"selected":""%>>2</option>
  <option value="3" <%= vo.getStartimehour().substring(0,nPosition1).equals("3")?"selected":""%>>3</option>
  <option value="4" <%= vo.getStartimehour().substring(0,nPosition1).equals("4")?"selected":""%>>4</option>
  <option value="5" <%= vo.getStartimehour().substring(0,nPosition1).equals("5")?"selected":""%>>5</option>
  <option value="6" <%= vo.getStartimehour().substring(0,nPosition1).equals("6")?"selected":""%>>6</option>
  <option value="7" <%= vo.getStartimehour().substring(0,nPosition1).equals("7")?"selected":""%>>7</option>
  <option value="8" <%= vo.getStartimehour().substring(0,nPosition1).equals("8")?"selected":""%>>8</option>
  <option value="9" <%= vo.getStartimehour().substring(0,nPosition1).equals("9")?"selected":""%>>9</option>
  <option value="10" <%= vo.getStartimehour().substring(0,nPosition1).equals("10")?"selected":""%>>10</option>
  <option value="11" <%= vo.getStartimehour().substring(0,nPosition1).equals("11")?"selected":""%>>11</option>
  <option value="12" <%= vo.getStartimehour().substring(0,nPosition1).equals("12")?"selected":""%>>12</option>
  <option value="13" <%= vo.getStartimehour().substring(0,nPosition1).equals("13")?"selected":""%>>13</option>
  <option value="14" <%= vo.getStartimehour().substring(0,nPosition1).equals("14")?"selected":""%>>14</option>
  <option value="15" <%= vo.getStartimehour().substring(0,nPosition1).equals("15")?"selected":""%>>15</option>
  <option value="16" <%= vo.getStartimehour().substring(0,nPosition1).equals("16")?"selected":""%>>16</option>
  <option value="17" <%= vo.getStartimehour().substring(0,nPosition1).equals("17")?"selected":""%>>17</option>
  <option value="18" <%= vo.getStartimehour().substring(0,nPosition1).equals("18")?"selected":""%>>18</option>
  <option value="19" <%= vo.getStartimehour().substring(0,nPosition1).equals("19")?"selected":""%>>19</option>
  <option value="20" <%= vo.getStartimehour().substring(0,nPosition1).equals("20")?"selected":""%>>20</option>
  <option value="21" <%= vo.getStartimehour().substring(0,nPosition1).equals("21")?"selected":""%>>21</option>
  <option value="22" <%= vo.getStartimehour().substring(0,nPosition1).equals("22")?"selected":""%>>22</option>
  <option value="23" <%= vo.getStartimehour().substring(0,nPosition1).equals("23")?"selected":""%>>23</option>
  <option value="24" <%= vo.getStartimehour().substring(0,nPosition1).equals("24")?"selected":""%>>24</option>
</select>

                      <select name="MeetingStartTimeMinit">
  <option value="00" <%= vo.getStartimehour().substring(nPosition1+1).equals("00")?"selected":""%>>00</option>
  <option value="05" <%= vo.getStartimehour().substring(nPosition1+1).equals("05")?"selected":""%>>05</option>
  <option value="10" <%= vo.getStartimehour().substring(nPosition1+1).equals("10")?"selected":""%>>10</option>
  <option value="15" <%= vo.getStartimehour().substring(nPosition1+1).equals("15")?"selected":""%>>15</option>
  <option value="20" <%= vo.getStartimehour().substring(nPosition1+1).equals("20")?"selected":""%>>20</option>
  <option value="25" <%= vo.getStartimehour().substring(nPosition1+1).equals("25")?"selected":""%>>25</option>
  <option value="30" <%= vo.getStartimehour().substring(nPosition1+1).equals("30")?"selected":""%>>30</option>
  <option value="35" <%= vo.getStartimehour().substring(nPosition1+1).equals("35")?"selected":""%>>35</option>
  <option value="40" <%= vo.getStartimehour().substring(nPosition1+1).equals("40")?"selected":""%>>40</option>
  <option value="45" <%= vo.getStartimehour().substring(nPosition1+1).equals("45")?"selected":""%>>45</option>
  <option value="50" <%= vo.getStartimehour().substring(nPosition1+1).equals("50")?"selected":""%>>50</option>
  <option value="55" <%= vo.getStartimehour().substring(nPosition1+1).equals("55")?"selected":""%>>55</option>
</select>
                      </td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议结束时间：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <input name="MeetingEndTime" size="10" type="text" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(vo.getEndtimeday().longValue()))%>" readonly>
					  <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历" onclick="fPopUpCalendarDlg(MeetingEndTime)">					         
                      <select name="MeetingEndTimeHour">
  <option value="1" <%= vo.getEndtimehour().substring(0,nPosition1).equals("1")?"selected":""%>>1</option>
  <option value="2" <%= vo.getEndtimehour().substring(0,nPosition1).equals("2")?"selected":""%>>2</option>
  <option value="3" <%= vo.getEndtimehour().substring(0,nPosition1).equals("3")?"selected":""%>>3</option>
  <option value="4" <%= vo.getEndtimehour().substring(0,nPosition1).equals("4")?"selected":""%>>4</option>
  <option value="5" <%= vo.getEndtimehour().substring(0,nPosition1).equals("5")?"selected":""%>>5</option>
  <option value="6" <%= vo.getEndtimehour().substring(0,nPosition1).equals("6")?"selected":""%>>6</option>
  <option value="7" <%= vo.getEndtimehour().substring(0,nPosition1).equals("7")?"selected":""%>>7</option>
  <option value="8" <%= vo.getEndtimehour().substring(0,nPosition1).equals("8")?"selected":""%>>8</option>
  <option value="9" <%= vo.getEndtimehour().substring(0,nPosition1).equals("9")?"selected":""%>>9</option>
  <option value="10" <%= vo.getEndtimehour().substring(0,nPosition1).equals("10")?"selected":""%>>10</option>
  <option value="11" <%= vo.getEndtimehour().substring(0,nPosition1).equals("11")?"selected":""%>>11</option>
  <option value="12" <%= vo.getEndtimehour().substring(0,nPosition1).equals("12")?"selected":""%>>12</option>
  <option value="13" <%= vo.getEndtimehour().substring(0,nPosition1).equals("13")?"selected":""%>>13</option>
  <option value="14" <%= vo.getEndtimehour().substring(0,nPosition1).equals("14")?"selected":""%>>14</option>
  <option value="15" <%= vo.getEndtimehour().substring(0,nPosition1).equals("15")?"selected":""%>>15</option>
  <option value="16" <%= vo.getEndtimehour().substring(0,nPosition1).equals("16")?"selected":""%>>16</option>
  <option value="17" <%= vo.getEndtimehour().substring(0,nPosition1).equals("17")?"selected":""%>>17</option>
  <option value="18" <%= vo.getEndtimehour().substring(0,nPosition1).equals("18")?"selected":""%>>18</option>
  <option value="19" <%= vo.getEndtimehour().substring(0,nPosition1).equals("19")?"selected":""%>>19</option>
  <option value="20" <%= vo.getEndtimehour().substring(0,nPosition1).equals("20")?"selected":""%>>20</option>
  <option value="21" <%= vo.getEndtimehour().substring(0,nPosition1).equals("21")?"selected":""%>>21</option>
  <option value="22" <%= vo.getEndtimehour().substring(0,nPosition1).equals("22")?"selected":""%>>22</option>
  <option value="23" <%= vo.getEndtimehour().substring(0,nPosition1).equals("23")?"selected":""%>>23</option>
  <option value="24" <%= vo.getEndtimehour().substring(0,nPosition1).equals("24")?"selected":""%>>24</option>
</select>

                      <select name="MeetingEndTimeMinit">
  <option value="00" <%= vo.getEndtimehour().substring(nPosition1+1).equals("00")?"selected":""%>>00</option>
  <option value="05" <%= vo.getEndtimehour().substring(nPosition1+1).equals("05")?"selected":""%>>05</option>
  <option value="10" <%= vo.getEndtimehour().substring(nPosition1+1).equals("10")?"selected":""%>>10</option>
  <option value="15" <%= vo.getEndtimehour().substring(nPosition1+1).equals("15")?"selected":""%>>15</option>
  <option value="20" <%= vo.getEndtimehour().substring(nPosition1+1).equals("20")?"selected":""%>>20</option>
  <option value="25" <%= vo.getEndtimehour().substring(nPosition1+1).equals("25")?"selected":""%>>25</option>
  <option value="30" <%= vo.getEndtimehour().substring(nPosition1+1).equals("30")?"selected":""%>>30</option>
  <option value="35" <%= vo.getEndtimehour().substring(nPosition1+1).equals("35")?"selected":""%>>35</option>
  <option value="40" <%= vo.getEndtimehour().substring(nPosition1+1).equals("40")?"selected":""%>>40</option>
  <option value="45" <%= vo.getEndtimehour().substring(nPosition1+1).equals("45")?"selected":""%>>45</option>
  <option value="50" <%= vo.getEndtimehour().substring(nPosition1+1).equals("50")?"selected":""%>>50</option>
  <option value="55" <%= vo.getEndtimehour().substring(nPosition1+1).equals("55")?"selected":""%>>55</option>
</select>
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td width="100" height="22" class="text-01" align="right">申请的会议室：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingRoom" type="text" value="<%= vo.getMeetingRoom()%>" size="29">

                	  <img  src="<%=request.getContextPath()%>/images/meeting/button-meetingroom.gif" onClick="_MeetingUse()" style="cursor:hand"></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td width="100" class="text-01"><div align="right">座位图：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <a href="<%=request.getContextPath()%>/servlet/DownloadMeetingServlet?meetingId=<%= (vo!=null)?vo.getPutId().toString():""%>">下载座位图</a>			         
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>  
                      <td width="100" height="22" class="text-01" align="right">服务要求：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                      <textarea name="MeetingUtil" cols="40" rows="5"><%= vo.getMeetingNeedingutil()==null?"":vo.getMeetingNeedingutil() %></textarea>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="100" height="22" class="text-01" align="right">会议概要：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td  bgcolor="F2F9FF" class="text-01">
                      <textarea name="MeetingMeno" cols="40" rows="5"><%= vo.getPutMeno()==null?"":vo.getPutMeno() %></textarea>
                      </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                       <td height="22"><div align="right" class="text-01">座位图&#65306;</div></td>
                       <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                       <td  colspan="5" bgcolor="F2F9FF" class="text-01">
                       <input name="access" type="file" size="52" class="txt2" onchange="_changefile()">
                       <input name="access1" type="hidden" value="default">
                       </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
              </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
  </table>
	   <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
  </table>
	  <div align="center"><br>
      <img src="<%=request.getContextPath()%>/images/botton-ok.gif" border=0 style="cursor:hand" onClick="_Update()">&nbsp;


      <%if(!("申请中").equals(vo.getMeetingSatus())&&!("未确认").equals(vo.getMeetingSatus())) {%>      
      <img src="<%=request.getContextPath()%>/images/meeting/button-public.gif" border=0 style="cursor:hand" onClick="_infoputMeeting()">&nbsp;
      <img src="<%=request.getContextPath()%>/images/meeting/button-start.gif" border=0 style="cursor:hand" onClick="_infoputMeeting1()">&nbsp;
      <img src="<%=request.getContextPath()%>/images/meeting/button-end.gif" border=0 style="cursor:hand" onClick="_infoputMeeting2()">&nbsp;
      <%}%>
	  <img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand">
  </div>

</form>
</body>
</html>
