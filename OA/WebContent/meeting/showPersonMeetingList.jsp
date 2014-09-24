<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.vo.*" %>
<%  
	List logList = (List)request.getAttribute("list");
	Iterator  logIterator= null;   
	if(logList!=null){     
 		logIterator = logList.iterator();   
 	}
%>

<html>
<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module13");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language="JavaScript" src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language=JavaScript>
		
	function _updateJournal(){
	    var j;
	    if(document.journalForm.jc == undefined){
			alert("没有可删除对象");		
	    }
	    else if( document.journalForm.jc.length == undefined){
        	if(document.journalForm.jc.checked){
				document.journalForm.action= "<%=request.getContextPath()%>/servlet/DelPersonMeetingServlet";
				document.journalForm.submit();	
				return;
			}
		}
		else{
	    	for(j=0;j<document.journalForm.jc.length;j++){
	        	if(document.journalForm.jc[j].checked){
					document.journalForm.action= "<%=request.getContextPath()%>/servlet/DelPersonMeetingServlet";
					document.journalForm.submit();	
					return;
				}
			}
		}
		alert("未选中删除项！");  
	}
	
	function _updateSchedule(){
    	document.journalForm.action="<%=request.getContextPath()%>/servlet/QueryPersonMeetingServlet";
  		document.journalForm.submit();
}
</SCRIPT>
</head>


<body  background="<%=request.getContextPath()%>/images/bg-08.gif"> 
<FORM name=journalForm method=post>

<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
  
    <td width="" valign="top">
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">个人会议查询</td>
          <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
        </tr>
      </table>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="171" height="22" class="text-01" align="right">会议主题：</td>
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                		<input name="content" type="text" value="<%=(request.getParameter("content")!=null)?(request.getParameter("content")):""%>" size="29"></td>
                   
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议类型：</div></td>
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <select name="MeetingType">
  					  <option value="普通会议">普通会议</option>
  					  <option value="保密会议">保密会议</option>
  					  <option value="电话会议">电话会议</option>
  					  <option value="重要会议">重要会议</option>
  					  <option value="全部会议">全部会议</option>
					  </select>
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                  </table>
				</td>
              </tr>
            </table>
		  </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
      </table>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
        </tr>
      </table>
    </tr>
  </table> 
  
  <table border="0" cellpadding="0" cellspacing="0" height="45" align="center" >
     <tr><td><div align="center"><span class="text-01"><img onClick="javascript:_updateSchedule()" src="<%=request.getContextPath()%>/images/botton-search.gif"style="cursor:hand"></span></div></td>
	 </tr></table>
  </table>   
</div>
<input type="hidden" name="_page_num" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">


<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td height="266" valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">个人会议列表</td>
              <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr  bgcolor="#FFFBEF">
	                        <td width="5%" height="22"></td>
	                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td>
	                      
	                        <td width="40%" align="center" class="title-04">会议主题</td>
	                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
	                        <td width="16%" align="center" class="title-04">开会时间</td>
	                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
	                        <td width="22%" align="center" class="title-04">结束时间</td>					
	                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>                
	                        <td width="20%" height="22" align="center" class="title-04">会议类型</td>
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
	 int i=0;
     while(logIterator.hasNext()){
	 	MeetingPersonmeetVO  vo = (MeetingPersonmeetVO)logIterator.next();
	    i++; 
        if(i%2!=0){
%>
		     <tr onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#D8EAF8';" bgcolor="#D8EAF8">
<%
		}
		else{
%>
			<tr class="text-01" onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';" bgcolor="#F2F9FF">
<%
		}
%>
	                <td height="22" class="text-01" align="center"><input type="checkbox" name="jc" value="<%= vo.getId() %>"></td>
	       			<td class="text-01" align="center">
	                <a href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/servlet/ShowMeetingServlet?putId=<%= vo.getMeetingPutid()%>','','width=800,height=400,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=50,top=20');"><%= vo.getMeetingName()%></a></td> 
	                <td align="center"><%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(vo.getStarttime().longValue()))%>&nbsp;<%= vo.getStart1()%></td>
	                <td class="text-01" align="center"><%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(vo.getEndtime().longValue()))%>&nbsp;<%= vo.getEnd1()%></td>
					<td  class="text-01" align="center"><%= vo.getMeetingType()%></td>

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
	}
%>
			        
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>  
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
	            <tr>
		              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
		              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="/include/defaultPageScrollBar.jsp" %></td>
		              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
	            </tr>
          </table>
	          <table border="0" cellpadding="0" cellspacing="0" width="69%" id="AutoNumber2" height="45" align="center">
		          <tr>
			          	<td align="center" class="text-01">
			          	<img onClick="javascript:_updateJournal()" src="<%=request.getContextPath()%>/images/botton-delete.gif" style="cursor:hand">
			          	</td>
				  </tr>
	          </table>
          </td>
    </tr>
  </table>

</form>
</body>

</html>