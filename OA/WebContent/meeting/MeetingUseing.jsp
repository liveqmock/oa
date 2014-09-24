<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.util.*"%>
<%@ page import= "com.icss.oa.meeting.vo.*"%>
<%@ page import="java.text.*" %>

<html>
<%
    MeetingRoominformationVO vo11 =(MeetingRoominformationVO)request.getAttribute("vo1");
%>

<script language="JavaScript">

function IsCheck(){

	if(document.form_list.MeetingId1.value  == "null"){
    	alert("请选择一个会议室!");
		return false;
	}
	return true;
}

function _commit(){

	if(IsCheck()){
		var doUrl = "window.top.opener._addMeeting('<%= vo11==null?"":vo11.getMeetName()%>')";
		eval(doUrl);
		window.top.close();
		}
}

function  _changeInserver(meetingId){
     
     document.form_list.MeetingId1.value   = meetingId;
     document.form_list.action="<%=request.getContextPath()%>/servlet/MeetingUseingServlet";
  	 document.form_list.submit();
  	 
} 

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">  
<form name="form_list" action="" method="post">
<input name="putId" type="hidden" value="null">
<input name="MeetingId1" type="hidden" value="<%= vo11==null?"null":vo11.getMeetingId().toString()%>">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td  valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr >
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif">
          <img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">会议室列表</td>
          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
    </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
			  
			  <tr class="text-01" bgcolor="#FFFBEF">
			    <td width="5%"  height="22" align="center" class="title-04"></td>
                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                <td width="28%"  height="22" align="center" class="title-04">会议室名称</td>
                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                <td width="5%"  height="22" align="center" class="title-04"></td>
                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                <td width="28%"  height="22" align="center" class="title-04">会议室名称</td>
                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td>
                <td width="5%"  height="22" align="center" class="title-04"></td>
                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                <td width="28%"  height="22" align="center" class="title-04">会议室名称</td>
                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
             
<%
   // MeetingRoominformationVO vo11 =(MeetingRoominformationVO)request.getAttribute("vo1");
	List list = null;
	int j=0;
	list = (List)request.getAttribute("list");
	if(list!=null){
	Iterator it = list.iterator();
	while(it.hasNext()){
		j++;
		MeetingRoominformationVO vo = (MeetingRoominformationVO)it.next();
							if(j%2==1){
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
			
			
                <td   height="22" align="center" class="title-04">
                <input name="ipid" type="radio" value="<%= vo.getMeetName()%>" onClick='javascript:_changeInserver("<%= vo.getMeetingId()%>")' <%= vo11!=null?(vo11.getMeetingId().intValue()==vo.getMeetingId().intValue()?"checked":""):""%>></td> 
                <td  height="22" align="center" class="title-04"><%= vo.getMeetName()%></td>
                <%if(it.hasNext()){ vo = (MeetingRoominformationVO)it.next();%>
                <td  height="22" align="center" class="title-04">
                <input name="ipid" type="radio" value="<%= vo.getMeetName()%>" onClick='javascript:_changeInserver("<%= vo.getMeetingId()%>")' <%= vo11!=null?(vo11.getMeetingId().intValue()==vo.getMeetingId().intValue()?"checked":""):""%>></td> 
                <td   height="22" align="center" class="title-04"><%= vo.getMeetName()%></td> 
                  <%}else{%>
                <td   height="22" align="center" class="title-04"></td> 
                <td   height="22" align="center" class="title-04"></td>
                <%}%> 
                <%if(it.hasNext()){ vo = (MeetingRoominformationVO)it.next();%>
                <td   height="22" align="center" class="title-04">
                <input name="ipid" type="radio" value="<%= vo.getMeetName()%>" onClick='javascript:_changeInserver("<%= vo.getMeetingId()%>")' <%= vo11!=null?(vo11.getMeetingId().intValue()==vo.getMeetingId().intValue()?"checked":""):""%>></td> 
                <td  height="22" align="center" class="title-04"><%= vo.getMeetName()%></td> 
                  <%}else{%>
                <td   height="22" align="center" class="title-04"></td> 
                <td   height="22" align="center" class="title-04"></td>
                <%}%>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>   
<%
	}}
%>
            </tr>
        </table></td>
              
    </tr>
  </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
  <div align="center"><br>
    <img src="<%=request.getContextPath()%>/images/meeting/button-selectmeeting.gif" border=0 style="cursor:hand" onClick="_commit()">&nbsp;

  </div>

</form>
<form name="form_update" action="" method="post" enctype="multipart/form-data">
  	<input name="MeetingId" type="hidden" value="<%= (vo11!=null)?vo11.getMeetingId().toString():""%>">
	<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif">
          <img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">会议室信息</td>
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
                      <td width="100" height="22" class="text-01" align="right">名称：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingName" type="text" value="<%= (vo11!=null)?vo11.getMeetName():""%>" size="29"></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">房间号：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <input name="MeetingRoom" type="text" value="<%= (vo11!=null)?vo11.getBelongRoom():""%>" size="29">
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                   <tr>
                      <td width="100" height="22" class="text-01" align="right">所属大楼：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingBuilding" type="text" value="<%= (vo11!=null)?vo11.getBelongBuilding():""%>" size="29"></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" height="22" class="text-01" align="right">容纳的人数：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingMaxNum" type="text" value="<%= (vo11!=null)?vo11.getMeetingMaxnum().toString():""%>" size="29"></td>

                       </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                     <tr>
                      <td width="100" height="22" class="text-01" align="right">会议设施：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="300" bgcolor="F2F9FF" class="text-01">
                      <textarea name="MeetingUtil" cols="40" rows="5"><%= (vo11!=null)?(vo11.getMeetingUtil()!=null?vo11.getMeetingUtil():""):""%></textarea>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="100" height="22" class="text-01" align="right">以上传的&nbsp;&nbsp;&nbsp;<br>座位分布图：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
 					  <td width="319" bgcolor="F2F9FF" class="text-01" align="left">
                	  <!--img id="logourl" src="DownloadMeetingSittingServlet?meetingId=<%= (vo11!=null)?vo11.getMeetingId().toString():""%>" height="60"--><a href="<%=request.getContextPath()%>/servlet/DownloadMeetingSittingServlet?meetingId=<%= (vo11!=null)?vo11.getMeetingId().toString():""%>">查看座位图</a></td> 
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
  </form>
  
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td  valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
			          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif">
			          <img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
			          <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">会议室使用信息列表</td>
			          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
    </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
			  
			  <tr class="text-01" bgcolor="#FFFBEF">
	                <td width="45%"  height="22" align="center" class="title-04">会议名称</td>
	                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
	       			<td width="55%" class="text-01" align="center">举行会议时间</td>
					<td width="2"  rowspan="40" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td>
             </tr>
             
             <tr>
	                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
	                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
	                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
					<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
					<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
					<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
             
<%
   
	List list1 = null;
	int i=0;
	list1 = (List)request.getAttribute("list1");
	if(list1!=null){
	Iterator it1 = list1.iterator();
	while(it1.hasNext()){
		i++;
		MeetingPutVO vo1 = (MeetingPutVO)it1.next();
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
			
	                <td  height="22" align="center" class="title-04"><%= vo1.getMeetingName()%></td> 
	       			<td  class="text-01" align="center"><%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(vo1.getStarttimeday().longValue()))%>&nbsp;<%= vo1.getStartimehour()%>------<%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(vo1.getEndtimeday().longValue()))%>&nbsp;<%= vo1.getEndtimehour()%></td>
             </tr>
             <tr>
	                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
	                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
	                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
					<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>   
<%
	}}
%>
            </tr>
        </table></td>
              
    </tr>
  </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
	              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
	              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"></td>
	              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
            
          </table><br>
          <tr>
          	  <td align="center" class="text-01"><img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="window.close();" style="cursor:hand"></td>
          </tr>
  </body>
</html>