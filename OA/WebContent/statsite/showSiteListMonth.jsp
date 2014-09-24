<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.statsite.vo.*" %>  
<%@ page import="com.icss.oa.util.*" %>

<%  
	Long count1 = (Long)request.getAttribute("count");
	long count =count1.longValue();
	List logList = (List)request.getAttribute("list");
	Iterator  logIterator= null;   
	if(logList!=null){     
 		logIterator = logList.iterator();   
 	}
 	Long flag = (Long) request.getAttribute("flag");
 	String date2 = (String) request.getAttribute("date2");
 	int date3 = Integer.parseInt(date2);
 	Long count4 [] = new Long[25];
 	count4 = (Long []) request.getAttribute("count4");
 	
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>日志查询</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">  
<FORM name=journalForm method=post>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td height="266" valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">网站访问按月统计列表 访问总数：<%=count1%>  &nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/servlet/ShowAllMonthList"> 查看全部月分析</a></td>  
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
                        <td width="20%" height="22" align="center" class="title-04">访问日期</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                        <td width="31%" align="center" class="title-04">访问人数</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="27%" align="center" class="title-04">百分比</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" align="center" class="title-04">图示</td>
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
	 	StatSiteDateVO  vo = (StatSiteDateVO)logIterator.next();
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
                <td height="22" class="text-01" align="center"><%=new SimpleDateFormat("yyyy-MM-dd").format(new Date(vo.getVisDate().longValue()))%></td>
       			<% long kk=vo.getVisNumber().longValue();%>
       			<%if(flag.longValue()==1&&date3==i){ kk=vo.getVisNumber().intValue()+count4[24].longValue();}%>
       			<td class="text-01" align="center"><%=kk%></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(kk*100, count, 1);
       			 %>
                <td class="text-01" align="center" ><%=userFileSize%>%</td>
                <td  height=10 > <div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%=userFileSize%>%"; height=10></div></td>			
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%}%>
<%
	if(flag.longValue()==1){
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
		long time1=System.currentTimeMillis();
		java.util.Date date=new java.util.Date(time1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatTime = formatter.format(date);
%>
                <td height="22" class="text-01" align="center"><%= formatTime %></td>
       			
       			<% long kk = count4[24].longValue();%>
       			<td class="text-01" align="center"><%=kk%></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(kk*100, count, 1);
       			 %>
                <td class="text-01" align="center" ><%=userFileSize%>%</td>
                <td  height=10 > <div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%=userFileSize%>%"; height=10></div></td>			
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%}%>
			        
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
              <%@ include file="../include/defaultPageScrollBar.jsp" %></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          
       &nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
  </table>
  
</form>
</body>

</html>