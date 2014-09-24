<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.hr.vo.HRGroupVO"%>

<HTML>
<HEAD>
<TITLE>人事分组信息</TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/sendfile.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/jpage.js"></SCRIPT>


<SCRIPT language=JavaScript>
function _back(parentid){
	alert(parentid);
   window.location.href = "<%=request.getContextPath()%>/servlet/SendFileSelectCommonTwoLevelGroupServlet?groupid="+parentid;
}

function CheckAll()
	 {
	   for (var i=0;i<document.commongroupform.elements.length;i++)
	   {
	     var e = document.commongroupform.elements[i];
		  if (e.name == 'personid')
			 e.checked = document.commongroupform.allbox.checked;
	   }
	 }

window.onload = function(){
window.top.moveFrame()
page = new Page(22,'table1','group_one'); 
}

</SCRIPT>

</HEAD>
<BODY  bgcolor="#eff7ff" >
<%
		List parentlist =(List)session.getAttribute("parentlist");
		
		
	if (request.getAttribute("commonTwoGroupList")!=null){
	//取该人员的公共分组信息
	List commonTwoGroupList = (List)request.getAttribute("commonTwoGroupList");
	%>
		<form name="commongroupform" method="post" action="" target="listFrame">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">人事分组信息</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
              
				  <table width=100%  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                    
                    
				  <table  width="100%"  id= table1  border="1" cellspacing="0" cellpadding="0">
				  
					  <tr  bgcolor="#FFFBEF">
                        <td width="35%"  height="22"><div align="center" class="title-04">选择</div></td>
                        
                        <td width="65%" ><div align="center" class="title-04">组名</div></td>
					</tr>
					
                    <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    
                    <tbody id=group_one>
   
   <%
			String parentid = "";
			Iterator commongroupiterator=commonTwoGroupList.iterator();
			
			int i = 0;//循环变量
			while(commongroupiterator.hasNext()){
				HRGroupVO vo=(HRGroupVO)commongroupiterator.next();
				
				//parentid = vo.getParentid();
				i++;
				String color = "#F2F9FF";
				if(i % 2 == 1)
					color = "#D8EAF8";
		%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td align="center" ><input type=checkbox name="personid" value="<%=vo.getGroupid()%>ш<%=StringUtil.escapeNull(vo.getOrgname())%>"></td>
                        <td height="22"   class="text-01" ><div align="center">
                        <%if(parentlist.contains(vo.getGroupid())){%><a href="SendFileSelectCommonTwoLevelGroupServlet?groupid=<%=vo.getGroupid()%>">
                        <%}else{%><a href="SendFileSelectCommonPersonServlet?groupid=<%=vo.getGroupid()%>"><%}%><%=StringUtil.escapeNull(vo.getOrgname())%></a></div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
		<%
				}//while
		%>
			</tbody>
					
			</table>
					
					</td>
                  </tr>
              </table>
              </td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
         
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
              <TABLE width="100%">
              <tr>
			<TD align=center width="50%">
			<A href="#" onclick="page.firstPage();">首页</A>&nbsp;│ <A href="#" onclick="page.prePage();">上一页</A>&nbsp;│<A href="#" onclick="page.nextPage();">下一页</A>&nbsp;│ <A href="#" onclick="page.lastPage();">尾页</A> </TD>
			<TD align=right width="50%">
			<span id="pageindex"></span></TD>
			</tr>
			</TABLE>
			</TD>

             <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
          
<input type=hidden name="isperson" value="00">
<br>
<center>
<input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">本页全选</a></label>
<img src="<%=request.getContextPath()%>/images/button-select.gif" border=0 style="cursor:hand" onClick="javascript:getSearchOrg()" >
<!-- <img src="<%=request.getContextPath()%>/images/botton-return.gif" border=0 style="cursor:hand" onClick="javascript:_back(<%=parentid %>)" >
 -->
</center>
</form>        
	<%
	}else{
	
	
%>
<br/>
<p align="center" class="text-01">请从左边的导航栏选择</p>
<%
}
 %>
</BODY>
</HTML>
