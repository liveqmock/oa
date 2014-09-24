<%@ page contentType="text/html; charset=gbk" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.folder.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>

<%
  List list =(List)request.getAttribute("list");

%>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312"><LINK 
href="index_files/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>

<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>

<SCRIPT src="index_files/calendar.js"></SCRIPT>

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></SCRIPT>

</HEAD>
<BODY topMargin=0 background="<%=request.getContextPath()%>/images/folder/bg-08.gif">

    <form name="form" method="post" action="">          
<table width="75%" align="center">
	<tr>
		<td vAlign=top>
      <TABLE  width="90%">
        <TR>
          <TD align=left >
            
         
                      <!----------文件和目录列表显示start------------------------>
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-12.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/folder/bg-12.gif" class="title-05">共享文件夹信息</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>

            <table width="100%" border="0" align=center cellpadding="0" cellspacing="0">
              <tbody>
                
                <tr bgColor=#FFFBEF class="title-04"> 
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2 ></td>
                  <td width="10%" colspan=2 height="22"><div align="center">序号</div> </td>
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                  <td width="90%"><div align="center">名称</div></td>
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                </tr>
                <tr> 
                  <td height="0" background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                </tr>
<%
	
 if(list!=null){
 						 
 Iterator Itr = list.iterator();
 String DouSharename1 [][]=new String[1][2];
 int i=0;
 while(Itr.hasNext()){
	  DouSharename1 = (String[][])Itr.next();
	  
	  i++;

%>
	<%if(i%2==0){%>
                <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
<%}else{%>
				<tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" >
	<%}%>
                  
                  
                  
                  <td height="22"><div align="center" ><%= i%></div></td>
                  <td height="22"><div align="center" ></div></td>
                  <td height="22"><div align="center" >
                  <A  href="<%=request.getContextPath()%>/servlet/ShowShareRootFileServlet?folderId=<%= DouSharename1[0][1]%>&accessFlag=0&shareFlag=1&entrance=kk&shareName=<%= DouSharename1[0][0]%>">
                  <%= DouSharename1[0][0]%></a></div></td>
                </tr>
               
               
                <tr> 
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                </tr>
              
				<%
				}
				}%>
              </tbody>
            </table>
                        <!-- 翻页 begin -->
					  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
						  <td width="1%"><img src="<%=request.getContextPath()%>/images/folder/bg-21.jpg" width="10" height="21"></td>
						  <td width="55%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"> </td>
						  <td width="30%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"><div align="right"></div></td>
						  <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-22.jpg" width="11" height="21"></div></td>
						</tr>
					  </table>      
                          <!-- 翻页 end -->
                      <br>
                      
				
            <!-- Tail begin --></TD>
        </TR></TABLE>	  
		</td>
	</tr>
</table>
</form>
</BODY></HTML>