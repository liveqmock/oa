<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="com.icss.oa.statsite.vo.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.statsite.vo.*" %> 
<%@ page import="com.icss.oa.statsite.handler.*" %>  

<% 
	String starttime = (String)request.getAttribute("starttime");
	Long counterdate = (Long)request.getAttribute("counterdate");
	Long counter = (Long)request.getAttribute("counter");
	Long count[] = new Long[25];
	count = (Long [])request.getAttribute("count");
	monthVO month = new monthVO();
	month = (monthVO)request.getAttribute("month");
	dateVO date = new dateVO();	
	date = (dateVO)request.getAttribute("date");
	HourVO hour = new HourVO();
	hour = (HourVO)request.getAttribute("hour");
	String maxAddress = (String)request.getAttribute("maxAddress");
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">  
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td  valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <%
               Connection conn = null;
               long time = System.currentTimeMillis();
			   StatSiteHandler logHandler = new StatSiteHandler(conn);
			   String time1 = logHandler.getTimeByLong(new Long(time));
               %>
 
             <%  
                 String str="";
                 int num = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
                 switch(num){
								      case 1:  	str="一"; break;
								      case 2:  	str="二"; break;
								      case 3:  	str="三"; break;
								      case 4:  	str="四"; break;
								      case 5:  	str="五"; break;
								      case 6:  	str="六"; break;
								      case 7:  	str="天"; break;
								      default:  	str=""; break;
							}	      
                %>
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              
            <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">网站综合统计信息       今天的时间是：<%=time1 %>     星期<%= str %></td>  
              <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
			  
			  <tr class="text-01" bgcolor="#F2F9FF">
                <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">名称</td>
                <td width="2"  rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
       			<td width="70%" class="text-01" align="center">统计结果</td>
				<td width="2"  rowspan="40" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
             
<%
	 int i=0;
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">总统计天数</td> 
       			<td width="70%" class="text-01" align="center"><%= counterdate %></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>      
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"> 开始统计日期 </td>
       			<td width="70%" class="text-01" align="center"><%=starttime%></td> 
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"> 总访问量 </td>
       			<td width="70%" class="text-01" align="center"><%=counter%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"> 平均日访量 </td>
       			<td width="70%" class="text-01" align="center"><%=counter.longValue()/counterdate.longValue()%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">今日访问量</td> 
       			<td width="70%" class="text-01" align="center"><%=count[24]%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">最高月访量</td>
       			<td width="70%" class="text-01" align="center"><%=month.getcount()%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"> 最高月访量月份</td> 
       			<td width="70%" class="text-01" align="center"><%=month.getmonth().substring(0,7)%></td> 
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">最高日访量</td>
       			<td width="70%" class="text-01" align="center"><%=date.getcount()%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"> 最高日访量日期</td> 
       			<td width="70%" class="text-01" align="center"><%=date.getdate()%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">最高时访量</td>
       			<td width="70%" class="text-01" align="center"><%=hour.getcount()%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">最高时访量时间</td> 
       			<td width="70%" class="text-01" align="center"><%=hour.getdate()%>&nbsp;&nbsp;<%= hour.gethour()%>:00-<%= (hour.gethour().longValue()+1)%>:00</td>
				  
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<14){
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
		%>      <td width="30%" bgcolor="#FFFBEF" height="22" align="center" class="title-04">访问最多的地区</td> 
       			<td width="70%" class="text-01" align="center"><%= maxAddress %></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             </tr>
<%
	}
	i++; 
%>
 </table></td>
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
</body>

</html>

<%
   try {
     if(conn!=null){
         conn.close();
     }
} catch (Exception e) {
	e.printStackTrace();
}

%>