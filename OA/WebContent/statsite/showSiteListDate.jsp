<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.icss.oa.util.*" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.statsite.vo.*" %> 
<%@ page import="com.icss.oa.statsite.handler.*" %>

<%  
	Long count[] = new Long[25];
	count = (Long [])request.getAttribute("count");
%>

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

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">  
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td height="266" valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">网站访问按日统计列表 访问总数：<%=count[24]%>     今天的时间是：<%=time1 %>     星期<%= str %>&nbsp;&nbsp;&nbsp;</td>  
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
                        <td width="20%" height="22" align="center" class="title-04">小时</td>
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
	 
     for(int i=0;i<24;i++){ 
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
                <td height="22" class="text-01" align="center"><%= i%>:00-<%= (i+1)%>:00</td>
       			<td class="text-01" align="center"><%=count[i]%></td>
       			<% 
       			   double userFileSize =
				CommUtil.getDivision(count[i].longValue()*100, count[24].longValue(), 1);
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