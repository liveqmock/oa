<%@ page contentType="text/html; charset=gb2312" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.util.*" %>

<%@ page import="com.icss.oa.mutlimsg.vo.*" %>
<%
//System.out.println("++++++++++logmsglist++++++");
List list = (List)request.getAttribute("list");



%>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="index_files/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>
<SCRIPT src="index_files/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></SCRIPT>
</HEAD>

<body background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">
<form name="form1" method="post" action="">
 <div align="center">

  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"  class="title-05">多媒体文件类别列表</td>
      <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg">
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr  bgcolor="#FFFBEF">
                <td width="5%" height="22">   <div align="center"> 序号 </div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="5%" ><div align="center" class="title-04">名称</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="30%" ><div align="center" class="title-04">代码</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="15%" ><div align="center" class="title-04">标题</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td  ><div align="center" class="title-04">说明</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="10%" ><div align="center" class="title-04">备注</div></td>
                
              </tr>

              <%if(list==null||list.size()==0){ %>
               <tr>
                <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	           <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
           	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	       <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	        <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	        

             </tr>
              <tr>
              <td height="22" colspan="13">
                  当前没有多媒体文件类别 </td>
              </tr>
              
              <%} %>
			 <%
if(list != null&&list.size()>0){
int showFlag = 0;
Iterator Iterator = list.iterator();
while(Iterator.hasNext()){
	MultiTypeVO vo = (MultiTypeVO)Iterator.next();
%>
              <tr>
              		
                 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	             <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
           	 	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	       		 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	        	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	         	
             

             </tr>
	<% showFlag ++;%>
             <%if(showFlag%2==1){%>
                <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
	<%}else{%>
	<tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" >
	<%}%>
                <td height="22" class="text-01"><%=showFlag %><div align="center">
				<td  class="text-01"><div align="center"><a href="<%=request.getContextPath()%>/servlet/MultimsgListServlet?typeid=<%=vo.getTypeId() %>&user=manager">&nbsp;<%=vo.getTypeName() %></a></div></td>
                <td  class="text-01"><div align="left"> <%=vo.getTypeTitle() %>  </div></td>
                <td  class="text-01"><div align="center"><%=vo.getTypeCode() %></div></td>
                <td  class="text-01"><div align="center"><%=vo.getTypeDesc() %></div></td>
                <td  class="text-01"><div align="center"><%=vo.getTypeDesc() %></div></td>
                
              </tr>
<%
		}//while		
  	}     //if
%>
              <tr>		
                 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	             <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
           	 	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	       		 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	        	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	         	 
             </tr>            
		</table></td>  
          </tr>
      </table></td>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1%"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
      <td width="57%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01">
	  </td>
      <td width="40%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"><div align="right"> </div></td>
      <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
    </tr>
  </table>
  
  
  <br>
    <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="javascript:_add()" >&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onClick="javascript:_Update()">&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand" onClick="javascript:_Delete()" ><br>
  </div>
</form>



</body>

</html>


<script language="JavaScript">
 function _add(){

	
	form1.action="<%=request.getContextPath()%>/multimsg/multitype_add.jsp";
	
    form1.submit();
	
 }
function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}


</script>