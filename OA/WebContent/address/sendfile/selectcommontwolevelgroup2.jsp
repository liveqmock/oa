<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.AddressCommongroupVO"%>
<HTML>
<HEAD>
<TITLE>����������Ϣ</TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/sendfile.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/jpage.js"></SCRIPT>
<SCRIPT language=JavaScript>
function _back(parentid){
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
//page = new Page(22,'table1','group_one'); 
}

</SCRIPT>
<%
String p_num = request.getParameter("_page_num")==null?"1":request.getParameter("_page_num");
//request.setAttribute("p_num",p_num);
session.setAttribute("p_num",p_num);
%>
</HEAD>
<BODY  bgcolor="#eff7ff" >
<%
		
	if (request.getAttribute("commonTwoGroupList")!=null){
	//ȡ����Ա�Ĺ���������Ϣ
	List commonTwoGroupList = (List)request.getAttribute("commonTwoGroupList");
	%>
		<form name="commongroupform" method="post" action="" target="listFrame">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">����������Ϣ</td>
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
                    
                    
				  <table  width="100%"  id= table1  border="0" cellspacing="0" cellpadding="0">
				  
					  <tr  bgcolor="#FFFBEF">
                        <td width="35%"  height="22"><div align="center" class="title-04">ѡ��</div></td>
                        <td width="0" rowspan="100"   valign="top" background="/oabase/images/bg-24.gif"><img src="/oabase/images/bg-24.gif" width="2" height="2"></td>

                        <td width="65%" ><div align="center" class="title-04">����</div></td>
					</tr>
					
                    <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    
                  <!--  <tbody id=group_one> -->
   
   <%
			String parentid = "";
			Iterator commongroupiterator=commonTwoGroupList.iterator();
			
			int i = 0;//ѭ������
			while(commongroupiterator.hasNext()){
				AddressCommongroupVO vo=(AddressCommongroupVO)commongroupiterator.next();
				
				//parentid = vo.getParentid();
				i++;
				String color = "#F2F9FF";
				if(i % 2 == 1)
					color = "#D8EAF8";
		%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td align="center" ><input type=checkbox name="personid" value="<%=vo.getId()%>��<%=StringUtil.escapeNull(vo.getGroupname())%>"></td>
                        <td height="22"   class="text-01" ><div align="center">
                       <a href="SendFileSelectCommonPersonServlet?flag=oa&groupid=<%=vo.getId()%>&groupid1=<%=request.getAttribute("groupid") %>"><%=StringUtil.escapeNull(vo.getGroupname())%></a></div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
		<%
				}//while
		%>
			<!-- </tbody> -->
					
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
              <%@ include file="../../include/defaultPageScrollBar.jsp"%>
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
          
<input type=hidden name="isperson" value="02">
<br>
<center>
<input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">��ҳȫѡ</a></label>
<img src="<%=request.getContextPath()%>/images/button-select.gif" border=0 style="cursor:hand" onClick="javascript:getSearchOrg()" >
<!-- <img src="<%=request.getContextPath()%>/images/botton-return.gif" border=0 style="cursor:hand" onClick="javascript:_back(<%=parentid %>)" >
 -->
</center>
</form>        
	<%
	}else{
	
	
%>
<br/>
<p align="center" class="text-01">�����ߵĵ�����ѡ��</p>
<%
}
 %>
</BODY>
</HTML>
