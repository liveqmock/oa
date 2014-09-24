<%@ page contentType="text/html; charset=GBK" language="java" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.workmeeting.vo.*" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.oa.workmeeting.handler.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<%  
	Iterator  logIterator= null; 
	List logList = null; 
	try{
	    if(request.getAttribute("list")!=null)
		
		   { logList = (List)request.getAttribute("list");}
		 
		if(logList!=null){     
	 		logIterator = logList.iterator();    
	 	}
	 }catch(Exception e){
	   e.printStackTrace();
	 }
%>
<%  
    Connection conn = null;
    conn = com.icss.j2ee.services.DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("workplanrequest.jsp");
	Workinghandler handler=new Workinghandler(conn);
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>意见反馈列表</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>


<body  background="<%=request.getContextPath()%>/images/bg-08.gif"> 

<FORM name=journalForm method=post>

<table align="center" width="95%">
	<tr>
		<td>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td height="266" valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">意见反馈列表</td>
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
                        <td nowrap width="25%" align="center" class="title-04" height="22">日期</td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap width="25%" align="center" class="title-04">标题</td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap width="26%" align="center" class="title-04">上载人</td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
						<td nowrap width="26%" height="22" align="center" class="title-04">部门</td>
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
	 if(logIterator!=null){
     while(logIterator.hasNext()){
	 	GongzuowjVO  vo = (GongzuowjVO)logIterator.next();
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
       			<td nowrap height="22" class="text-01" align="center"><%= vo.getPublicDate()%></td>
                <td nowrap align="center">
                <a href="<%=request.getContextPath()%>/servlet/GetWorkPlanAttachServlet?id=<%= vo.getAttchId()%>" target="<%=vo.getWrNo()%>" title="查看附件">
                <%= vo.getTitle()%></a></td>
                <td nowrap class="text-01" align="center"><%= vo.getEdName()%></td>
				<td nowrap class="text-01" align="center"><%= handler.getPersonJU_Name(vo.getIssueName())%></td>
             </tr>
             <tr>
                
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
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
          
        <div align="center"></div></td>
    </tr>
  </table>
  </td></tr></table>
</form>
</body>

</html>

<%
   try {
     if(conn!=null){
         conn.close();
         ConnLog.close("workplanrequest.jsp");
     }
} catch (Exception e) {
	e.printStackTrace();
}

%>