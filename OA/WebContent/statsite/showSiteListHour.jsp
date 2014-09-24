<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.statsite.vo.*" %> 
<%@ page import="com.icss.oa.util.*" %>

<%  
    long count = ((Long)request.getAttribute("count")).longValue();
	List list = (List)request.getAttribute("list");
	Iterator  logIterator= null;   
	if(list!=null){     
 		logIterator = list.iterator();   
 	}
 	
 	String time = (String)request.getAttribute("time");
 	
 	StatSiteDate1VO  vo = new StatSiteDate1VO();
     while(logIterator.hasNext()){
	 	vo = (StatSiteDate1VO)logIterator.next();
	    }
%>



<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">  
<FORM name=journalForm method=post>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td  valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              
            <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">系统在<%=time%>&nbsp;&nbsp;访问次数为:<%= request.getAttribute("count")%>  
 </td>  
              <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
			   <tr  bgcolor="#FFFBEF">
                        <td width="20%" height="22" align="center" class="title-04">小时</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                        <td width="31%" align="center" class="title-04">访问人数</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="27%" align="center" class="title-04">百分比</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" align="center" class="title-04">图示</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
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
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>

       			<td  class="text-01" align="center"><%= vo.getH0()==null?new Long(0):vo.getH0() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH0().longValue()*100, count, 1);
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
             </tr>      
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00</td>

       			<td  class="text-01" align="center"><%= vo.getH1()==null?new Long(0):vo.getH1() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH1().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00 </td>

       			<td  class="text-01" align="center"><%= vo.getH2()==null?new Long(0):vo.getH2() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH2().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00 </td>

       			<td  class="text-01" align="center"><%= vo.getH3()==null?new Long(0):vo.getH3() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH3().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH4()==null?new Long(0):vo.getH4() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH4().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td class="text-01" align="center"><%= vo.getH5()==null?new Long(0):vo.getH5() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH5().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH6()==null?new Long(0):vo.getH6() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH6().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH7()==null?new Long(0):vo.getH7() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH7().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH8()==null?new Long(0):vo.getH8() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH8().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH9()==null?new Long(0):vo.getH9() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH9().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td w class="text-01" align="center"><%= vo.getH10()==null?new Long(0):vo.getH10() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH10().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH11()==null?new Long(0):vo.getH11() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH11().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>

       			<td  class="text-01" align="center"><%= vo.getH12()==null?new Long(0):vo.getH12() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH12().longValue()*100, count, 1);
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
             </tr>      
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00 </td>

       			<td class="text-01" align="center"><%= vo.getH13()==null?new Long(0):vo.getH13() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH13().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00 </td>

       			<td  class="text-01" align="center"><%= vo.getH14()==null?new Long(0):vo.getH14() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH14().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00 </td>

       			<td  class="text-01" align="center"><%= vo.getH15()==null?new Long(0):vo.getH15() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH15().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH16()==null?new Long(0):vo.getH16() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH16().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH17()==null?new Long(0):vo.getH17() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH17().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH18() ==null?new Long(0):vo.getH18()%></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH18().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH19()==null?new Long(0):vo.getH19() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH19().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH20()==null?new Long(0):vo.getH20() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH20().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH21()==null?new Long(0):vo.getH21() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH21().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH22()==null?new Long(0):vo.getH22() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH22().longValue()*100, count, 1);
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
             </tr>
<%
	}
	i++; 
%>
<%
     if(i<24){
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
		%>      <td  bgcolor="#FFFBEF" height="22" align="center" class="title-04"> <%= i%>:00-<%= (i+1)%>:00

</td>

       			<td  class="text-01" align="center"><%= vo.getH23()==null?new Long(0):vo.getH23() %></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(vo.getH23().longValue()*100, count, 1);
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
       </td>
    </tr>
  </table>
</form>
</body>

</html>