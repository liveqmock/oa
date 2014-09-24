<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*"%>

<% 
	Date date=new Date(System.currentTimeMillis());
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String formatTime = formatter.format(date);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<LINK  href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language="JavaScript" src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
</style>
</head>
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

     if(getLength(document.form_update.MeetingName.value)>100){
    	alert("正题名过长（最大长度为50个汉字或100个英文字母）！");
    	return true;
    }

  	if(document.form_update.MeetingName.value==""){
    	alert("请填写会议名称名称！");
    	return true;
    }
 	if(document.form_update.MeetingStartTime.value==""){
    	alert("请填写会议开始的时间");
    	return true;
    }
    
    var timeflag1 = Compare_Date('<%= formatTime%>',document.form_update.MeetingStartTime.value);
    
    if(timeflag1){
    	alert("请填写会议开始不能小于今天");
    	return true;
    }
    
    if(document.form_update.MeetingEndTime.value==""){
    	alert("请填写会议结束的时间");
    	return true;
    }
    
    var timeflag = Compare_Date(document.form_update.MeetingStartTime.value,document.form_update.MeetingEndTime.value);
    if(timeflag) {
    	alert("你填的开会的时间开始和结束时间相反!");
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
			document.form_update.action="<%=request.getContextPath()%>/servlet/AddMeetingPutServlet";
			document.form_update.submit();
		}
}

function  _changefile(){
  if(document.form_update.access.value!="null"){
  		document.form_update.access1.value=document.form_update.access.value;
  		document.form_update.logourl.src = "file:\\"+document.form_update.access.value;
  }
}

function _MeetingUse(){

	window.open("<%=request.getContextPath()%>/servlet/MeetingUseingServlet?doFuntion=_addMeeting","","width=800,height=600,scrollbars=yes,resizable=yes,left=100,top=110");
}

function _addMeeting(meetingstring){
    
    document.form_update.MeetingRoom.value = meetingstring;
	return true;
}

function  _changefile(){
  if(document.form_update.access.value!="null"){
  		document.form_update.access1.value=document.form_update.access.value;
  		document.form_update.logourl.src = "file:\\"+document.form_update.access.value;
  }
}
</script>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">
 <form name="form_update" action="" method="post" enctype="multipart/form-data">
  	<input name="MeetingId" type="hidden" value="null">
	<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> 
        添加会议室信息</td>
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
                	  <input name="MeetingName" type="text" value="" size="29"></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议类型：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <select name="MeetingType">
  					  <option value="普通会议">普通会议</option>
  					  <option value="保密会议">保密会议</option>
  					  <option value="电话会议">电话会议</option>
  					  <option value="重要会议">重要会议</option>
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
                      <td width="100" height="22" class="text-01" align="right">会议开始时间：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingStartTime" type="text" value="" size="10" readonly>
                	  <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历" onclick="fPopUpCalendarDlg(MeetingStartTime)">
                      
                      <select name="MeetingStartTimeHour">
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
  <option value="13">13</option>
  <option value="14">14</option>
  <option value="15">15</option>
  <option value="16">16</option>
  <option value="17">17</option>
  <option value="18">18</option>
  <option value="19">19</option>
  <option value="20">20</option>
  <option value="21">21</option>
  <option value="22">22</option>
  <option value="23">23</option>
  <option value="24">24</option>
</select>
                    
                      <select name="MeetingStartTimeMinit">
  <option value="00">00</option>
  <option value="05">05</option>
  <option value="10">10</option>
  <option value="15">15</option>
  <option value="20">20</option>
  <option value="25">25</option>
  <option value="30">30</option>
  <option value="35">35</option>
  <option value="40">40</option>
  <option value="45">45</option>
  <option value="50">50</option>
  <option value="55">55</option>
</select>
                      </td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议结束时间：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <input name="MeetingEndTime" size="10" type="text" readonly>
					  <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历" onclick="fPopUpCalendarDlg(MeetingEndTime)">					         
                     
                      <select name="MeetingEndTimeHour">
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
  <option value="13">13</option>
  <option value="14">14</option>
  <option value="15">15</option>
  <option value="16">16</option>
  <option value="17">17</option>
  <option value="18">18</option>
  <option value="19">19</option>
  <option value="20">20</option>
  <option value="21">21</option>
  <option value="22">22</option>
  <option value="23">23</option>
  <option value="24">24</option>
</select>
                     
                      <select name="MeetingEndTimeMinit">
  <option value="00">00</option>
  <option value="05">05</option>
  <option value="10">10</option>
  <option value="15">15</option>
  <option value="20">20</option>
  <option value="25">25</option>
  <option value="30">30</option>
  <option value="35">35</option>
  <option value="40">40</option>
  <option value="45">45</option>
  <option value="50">50</option>
  <option value="55">55</option>
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
                	  <input name="MeetingRoom" type="text" value="" size="29" readonly=yew><img  src="<%=request.getContextPath()%>/images/meeting/button-meetingroom.gif" onClick="_MeetingUse()" style="cursor:hand"></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td width="100" class="text-01"><div align="right"></div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
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
                      <textarea name="MeetingUtil" cols="40" rows="5"></textarea>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="100" height="22" class="text-01" align="right">会议概要：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td  bgcolor="F2F9FF" class="text-01">
                      <textarea name="MeetingMeno" cols="40" rows="5"></textarea>
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
  	<img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand">
  </div>

</form>
</body>
</html>
