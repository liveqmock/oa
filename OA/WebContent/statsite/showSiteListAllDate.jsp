<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="com.icss.oa.util.*" %>

<%  
	
	Long alldate []= new Long[25];
	alldate = (Long [])request.getAttribute("alldate");
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
              
<td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">网站访问次数  总次数为：<%=alldate [24]%></td>  
              <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
			    <tr  bgcolor="#FFFBEF">
                        <td width="20%" height="22" align="center" class="title-04">小时</td>
                        <td width="2" rowspan="60" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                        <td width="21%" align="center" class="title-04">访问人数</td>
                        <td width="2" rowspan="60" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="27%" align="center" class="title-04">百分比</td>
                        <td width="2" rowspan="60" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" align="center" class="title-04">图示</td>
                        <td width="2" rowspan="80" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td>
                      </tr>
                      <tr>
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
		%>   <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>    <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>    <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>      <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>    <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
		%>     <td width="25%" bgcolor="#FFFBEF" height="22" align="center" class="title-04"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td width="25%" class="text-01" align="center"><%= alldate [i] %></td>
				<% 
       			   double userFileSize =
				CommUtil.getDivision(alldate [i].longValue()*100, alldate [24].longValue(), 1);
       			 %>
				<td width="25%" class="text-01" align="center"><%= userFileSize %>%</td>
				<td width="25%" class="text-01" align="center"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%= userFileSize%>%"; height=10></div></td>
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
</td></td></table>
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
</form>
</body>

</html>