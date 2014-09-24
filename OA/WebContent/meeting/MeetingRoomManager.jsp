<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.meeting.vo.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<LINK  href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</style>
</head>
<% MeetingRoominformationVO vo1 = (MeetingRoominformationVO)request.getAttribute("vo");%>
<script language="JavaScript" src="<%=request.getContextPath()%>/include/common.js" type="text/JavaScript"></script>
<script language="JavaScript">
function _checkPage(){
<%
		int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
		int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
		if(curPageNum1>pageCount1){
			curPageNum1=pageCount1;
			String url=request.getContextPath()+"/servlet/MeetingRoomManagerServlet?_page_num="+curPageNum1;
%>
		this.location="<%=url%>";
<%
		}
%>
}

function IsCheck(){
	if(document.form_update.MeetingId.value  == ""){
    	alert("请选择一条记录！");
		return false;
	}
	return true;
}
function IsFormItemEmpty(){
  	if(document.form_update.MeetingName.value==""){
    	alert("请填写会议室名称名称！");
    	return true;
    }
 	if(document.form_update.MeetingBuilding.value==""){
    	alert("请填写会议室所属大楼");
    	return true;
    }
    if(document.form_update.MeetingMaxNum.value==""){
    	alert("请填写会议室的容量");
    	return true;
    }
    
    if(!isDigital(document.form_update.MeetingMaxNum.value)){
        alert("请填写会议室的容量为数字");
    	return true;
    }
    
    if(document.form_update.MeetingRoomNum.value==""){
    	alert("请填写会议室的序号");
    	return true;
    }
	return false;
} 
function _Add(){
	if(!IsFormItemEmpty()){
    	document.form_update.action="<%=request.getContextPath()%>/servlet/AddMeetingRoomServlet";  
  		document.form_update.submit();
     }
}
function _Update(){
	 if(IsCheck()){
     	if(!IsFormItemEmpty()){
			document.form_update.action="<%=request.getContextPath()%>/servlet/EditMeetingRoomSevlet";
			document.form_update.submit();
		}
    }
}

function _Delete(){
    if(IsCheck()){
		document.form_update.action="<%=request.getContextPath()%>/servlet/DelMeetingRoomServlet";
    	document.form_update.submit();
    }
}

function UpdateSubSystem(meetingId,meetingname,meetingbuilding,meetingroom,meetingutil,meetingmaxnum)
{	
	document.form_update.MeetingName.value   = meetingname;
    document.form_update.MeetingId.value   = meetingId;
    document.form_update.MeetingBuilding.value   = meetingbuilding;
	document.form_update.MeetingRoom.value = meetingroom;
	document.form_update.MeetingUtil.value = meetingutil;
	document.form_update.MeetingMaxNum.value   = meetingmaxnum;
	document.form_update.logourl.src = "DownloadMeetingSittingServlet?meetingId="+meetingId;
	if(document.form_update.MeetingUtil.value=="null" ){document.form_update.MeetingUtil.value =" ";}
	if(document.form_update.MeetingRoom.value=="null" ){document.form_update.MeetingRoom.value =" ";}
	
}

function  _changefile(){
  if(document.form_update.access.value!="null"){
  		document.form_update.access1.value=document.form_update.access.value;
  		document.form_update.logourl.src = "file:\\"+document.form_update.access.value;
  }
}

function  _changeInserver(meetingId){

     document.form_list.MeetingId1.value   = meetingId;
     document.form_list.action="<%=request.getContextPath()%>/servlet/MeetingRoomManagerServlet";
  	 document.form_list.submit();
}

function _AddDrawing(){

	window.open("<%=request.getContextPath()%>/servlet/DownloadMeetingSittingServlet?meetingId=<%= (vo1!=null)?vo1.getMeetingId().toString():""%>","","width=500,height=600,scrollbars=yes,resizable=yes,left=300,top=110");
}

function _PrintMeetingBookServlet(){

	 document.form_list.action="<%=request.getContextPath()%>/servlet/PrintMeetingBookServlet";
  	 document.form_list.submit();
}

</script>


<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module12");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>
<body  background="<%=request.getContextPath()%>/images/bg-08.gif">
<script language="javascript">
_checkPage()
</script>
<form name="form_list" action="" method="post">
<input name="MeetingId1" type="hidden" value="null">
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">新华社内会议室列表</td>
      <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
   	<tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
		  	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
            	<tr>
                      <td width="5%" bgcolor="#FFFBEF"></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="5%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">序号</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="20%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">会议室名称</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="15%" bgcolor="#FFFBEF"><div align="center" class="title-04">容纳人数</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="20%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">所在位置</div></td>
					  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">房间号</div></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>

<%
	List list = (List)request.getAttribute("list");
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
						
						<td height="22" class="text-01" align="center">  
						<input name="ipid" type="radio" value="radiobutton"
							 onClick='javascript:_changeInserver("<%=vo.getMeetingId()%>")' <%= vo1!=null?(vo1.getMeetingId().intValue()==vo.getMeetingId().intValue()?"checked":""):""%>></td>
                      	<td class="text-01" align="center"><%= vo.getNum()%></td>
                      	<td class="text-01" align="center"><%= vo.getMeetName()%></td>
                      	<td class="text-01" align="center"><%= vo.getMeetingMaxnum()%></td>
                      	<td class="text-01" align="center"><%= vo.getBelongBuilding()%></td>
                      	<td class="text-01" align="center"><%= vo.getBelongRoom()==null?"":vo.getBelongRoom()%></td>
                      	
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>

<%
		}
%>
              </table>
			</td>
           </tr>
        </table></td>
        <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
      </tr>
  </table>
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
        <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="/include/defaultPageScrollBar.jsp" %></td>
        <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
      </tr>
  </table>
  </form>
 <form name="form_update" action="" method="post" enctype="multipart/form-data">
  	<input name="MeetingId" type="hidden" value="<%= (vo1!=null)?vo1.getMeetingId().toString():""%>">
	<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> 
        添加/修改/删除 会议室信息</td>
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
                      <td width="100" height="22" class="text-01" align="right">会议室名称：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingName" type="text" value="<%= (vo1!=null)?vo1.getMeetName():""%>" size="29">*</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">房间号：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td width="319"  bgcolor="F2F9FF"  class="text-01">
					  <input name="MeetingRoom" type="text" value="<%= (vo1!=null)?vo1.getBelongRoom():""%>" size="29">*
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                   <tr>
                      <td width="100" height="22" class="text-01" align="right">所在位置：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingBuilding" type="text" value="<%= (vo1!=null)?vo1.getBelongBuilding():""%>" size="29">*</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" height="22" class="text-01" align="right">容纳的人数：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <input name="MeetingMaxNum" type="text" value="<%= (vo1!=null)?vo1.getMeetingMaxnum().toString():""%>" size="29">*</td>
                       </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                     <tr>
                      <td width="100" height="22" class="text-01" align="right">会议室现有资源：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                      <textarea name="MeetingUtil" cols="40" rows="5"><%= (vo1!=null)?(vo1.getMeetingUtil()!=null?vo1.getMeetingUtil():""):""%></textarea>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="100" height="22" class="text-01" align="right">座位图：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
 					  <td width="319" bgcolor="F2F9FF" class="text-01" align="left">
                	  <!--img id="logourl" src="DownloadMeetingSittingServlet?meetingId=<%= (vo1!=null)?vo1.getMeetingId().toString():""%>" height="60"--><a href="<%=request.getContextPath()%>/servlet/DownloadMeetingSittingServlet?meetingId=<%= (vo1!=null)?vo1.getMeetingId().toString():""%>">查看座位图</a></td> 
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
                     <td width="100" class="text-01"><div align="right">上传座位图：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td width="319" bgcolor="F2F9FF"  class="text-01">
					  <input name="access" size="50" type="file" class="txt2" onchange="_changefile()">	
					  <input name="access1" type="hidden" value="default">
					  <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">序号：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td width="319" bgcolor="F2F9FF"  class="text-01">
					  
					  <% String kk;
					     int kk1;
					     kk1 = ((Integer)request.getAttribute("num")).intValue()+1;
					     kk = new Integer(kk1).toString();
					     %>

					  <input name="MeetingRoomNum" type="text" value="<%= (vo1!=null)?vo1.getNum().toString():kk%>" size="2">*
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
    <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="_Add()" >&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onClick="_Update()">&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand"  onClick="_Delete()">&nbsp;

     
  </div>

</form>
</body>
</html>
