<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.statsite.vo.*" %> 
<%@ page import="com.icss.oa.util.*" %>

<%  
	Long count1 = (Long)request.getAttribute("count");
	long count =count1.longValue();
	
	String qq1[] =new String[13];
	qq1 =(String [])request.getAttribute("qq1");
	
	String qq [] = new String[13];
	qq =(String [])request.getAttribute("qq");
	
	Long flag = (Long) request.getAttribute("flag");
 	String month2 = (String) request.getAttribute("month2");
 	int month3 = Integer.parseInt(month2);
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
              
            <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">网站访问按年统计列表 访问总数：<%=count1%></td>  
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
	 
     for(int i=1;i<13;i++){ 
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
                <td height="22" class="text-01" align="center"><%= qq1[i].substring(0,4)%>年<%= qq1[i].substring(5,7)%>月</td>
       			<% long kk=Long.parseLong(qq[i]);%>
       			<%if(flag.longValue()==1&&month3==i){ kk=Long.parseLong(qq[i])+count4[24].longValue();}%>
       			<td class="text-01" align="center"><%= kk%></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(kk*100, count, 1);
       			 %>
                <td class="text-01" align="center" ><%= userFileSize%>%</td>
                <td  height=10 > <div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%=userFileSize%>"; height=10></div></td>			
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