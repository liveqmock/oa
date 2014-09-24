<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.statsite.vo.*" %> 
<%@ page import="com.icss.oa.statsite.handler.*" %>  

<%  
	List logList = (List)request.getAttribute("list");
	Iterator  logIterator= null;   
	if(logList!=null){     
 		logIterator = logList.iterator();   
 	}
 	//Long pp=(Long)request.getAttribute("pp");
%>



<SCRIPT language=JavaScript>
	function _updateSchedule(url){
		document.journalForm.action=url+"/servlet/QueryInfoServlet";
	    document.journalForm.submit();  	
	}	

</SCRIPT>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">  
<FORM name=journalForm >  
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
  <tr><td width="" valign="top" >
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">当日网站访问查询</td>
          <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
        </tr>
      </table>
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">  
				  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="171" height="22" class="text-01" align="right">浏览人员：</td>
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                		<input name="person" type="text" value="<%=(request.getParameter("person")!=null)?(request.getParameter("person")):""%>" size="20"></td>
                   
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">IP地址：</div></td>
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01"><input name="ip" type="text" value="<%=(request.getParameter("ip")!=null)?(request.getParameter("ip")):""%>" size="20">
					         
                       </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td height="22" class="text-01"><div align="right">访问模块：</div></td>
                      <td bgcolor="F2F9FF" class="text-01"><input name="mold" type="text" value="<%=(request.getParameter("mold")!=null)?(request.getParameter("mold")):""%>" size="20"></td>
                      <td class="text-01"></td>
                      <td bgcolor ="F2F9FF" class="text-01"></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
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
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
        </tr>
      </table>
    </tr>
  </table> 
  </form>
  <table border="0" cellpadding="0" cellspacing="0" height="45" align="center">
     <tr><td><div align="center"><span class="text-01"><img onClick="javascript:_updateSchedule('<%=request.getContextPath()%>')" src="<%=request.getContextPath()%>/images/botton-search.gif"style="cursor:hand"></span></div></td>
	 </tr></table>
  </table>   
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td height="266" valign="top">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
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
            <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">网站访问统计列表    今天的时间是：<%=time1 %>     星期<%= str %></td>
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
                        <td width="10%" height="22" align="center" class="title-04">浏览位数</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                        <td width="10%" height="22" align="center" class="title-04">浏览人员</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" nowrap></td> 
                        <td width="17%" align="center" class="title-04">物理地址</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" align="center" class="title-04">IP地址</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" align="center" class="title-04">访问日期</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>         
                        <td width="10%" height="22" align="center" class="title-04">访问时间</td>
                        <td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="27%" height="22" align="center" class="title-04">进入系统模块名称</td>
                      </tr>
                      <tr>
                        <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
	 	StatSiteVO  vo = (StatSiteVO)logIterator.next();
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
                <td height="22" class="text-01" align="center"><%=vo.getId()%></td>
                <td height="22" class="text-01" align="center"><%=vo.getUserid()%></td>
       			<td class="text-01" align="center"><%=vo.getAddress()%></td>
                <td class="text-01" align="center" ><%=vo.getIp()%></td>
                <td class="text-01" align="center"><%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(vo.getTime().longValue()))%></td>
				<td  class="text-01" align="center"><%=new SimpleDateFormat("HH:mm").format(new java.util.Date(vo.getTime().longValue()))%></td>
                <td  class="text-01" align="center"><%=vo.getModuleid()%></td>
             </tr>
             <tr>
                <td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
              <%@ include file="../include/defaultPageScrollBar.jsp" %></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          
        <div align="center"></div></td>
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