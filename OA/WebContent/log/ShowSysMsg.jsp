<%@ page contentType="text/html; charset=gb2312" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.log.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.log.admin.*" %>   



<%

List list = (List)request.getAttribute("list");

String parentId = request.getParameter("parentId")==null?"-1":request.getParameter("parentId").toString();
System.out.println("++++++++++showsysmsg++++++"+parentId);
String path = (String)request.getAttribute("path");
//out.print("shareFlag="+shareFlag+" accessFlag="+accessFlag);
%>

<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module14");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>

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



<br>

<form name="form" method="post" action="">     
   		 
   		   
  <table width="100%"  cellSpacing=0 cellPadding=0 align=center border=0>
    <tr> 
      <td vAlign=top> <div align="center"> 
          <!----------文件和目录列表显示start------------------------>
		  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-12.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/folder/bg-12.gif" class="title-05">版块信息</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="100%" border="0" align=center cellpadding="0" cellspacing="0">
            <tbody>
              <tr bgColor=#FFFBEF class="title-04"> 
                <td width=2 rowspan="99" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2 ></td>
                <td width="4%" height=25><div align="center">选择</div></td>
                <td width=2 rowspan="99" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="4%" height=25><div align="center">序号</div></td>
                <td width=2 rowspan="99" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="14%"><div align="center">版块名称</div></td>
                 <td width=2 rowspan="99" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="12%"><div align="center">系统代码</div></td>
                <td width=2 rowspan="99" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="13%"><div align="center">系统说明</div></td>
                <td width=2 rowspan="99" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
               	<td width=""><div align="center">备注</div></td>
                <td width=2 rowspan="99" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
              
              </tr>
              <tr> 
                <td height="0" background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
               <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
              </tr>
			    <%
						  int i=0;

 Iterator Itr = list.iterator();
 LogSysVO vo = null;
 while(Itr.hasNext()){
	  vo = (LogSysVO)Itr.next();
	  i++;
	  

%>
      
              <%if(i%2==0){%>
              <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}else{%>
              <tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}%>
                <td height="22" class="text-01" align="center"><input name="selectsysid" type="checkbox" value="<%=vo.getSysId() %>" ></td>                    
				<td width="4%"><div align="center"><%=i%></div></td>
                <td width="14%"><div align="center"><%=vo.getSysName() %></div></td>
                <td width="12%"><div align="center"><%=vo.getSysCode() %></div></td>
               	<td width="13%"><div align="center"><%=vo.getSysDetail() %></div></td>
				<td width=""><div align="center"><%=vo.getSysMemo() %></div></td>
              </tr>
           
              <tr> 
              	<td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                 <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
               
              </tr>
          <%}%>
            </tbody>
          </table>
           <!-- 翻页 begin -->
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="1%"><img src="<%=request.getContextPath()%>/images/folder/bg-21.jpg" width="10" height="21"></td>
              <td width="55%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"> 
                <%@ include file= "/include/defaultPageScrollBar.jsp" %>
              </td>
              <td width="30%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          <!-- 翻页 end -->
         <br>
          <table width=800 border=0>
            <tbody>
              <tr> 
                <td> <div align=center> 
                    
                    <img src="<%=request.getContextPath()%>/images/addressbook/button-addgroup.gif" onclick="javascript:_addlogsys('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;
                    <img src="<%=request.getContextPath()%>/images/addressbook/button-delete.gif"   onclick="javascript:_dellogsys('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    </div></td>
                    
			  </tr>
            </tbody>
          </table>
          <!-- Tail begin -->
        </div></TD>
    </TR>
  </TABLE>	  
		</td>
	</tr>
</table>
</form>
</BODY></HTML>

<script language="JavaScript">






function _checkdelete(){
    if(document.form.selectsysid == undefined){
        alert("没有可删除的版块！");
        return false;
    }
}


 function _addlogsys(url,parentId){

	
	form.action=url+"/log/addlogsys.jsp?parentId="+parentId;
	
    form.submit();
	
 }
 

 
function _dellogsys(url,parentId){

	//if(_checkdelete()){	
		
		form.action=url+"/servlet/DeleteLogSysServlet?parentId="+parentId;
    	form.submit();
		window.top.leftFrame.location.reload();
	//}
 }


</script>