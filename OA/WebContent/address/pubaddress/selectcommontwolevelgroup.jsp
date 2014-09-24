<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>

<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</HEAD>
<SCRIPT language=JavaScript>


	function CheckAll()
	 {
	   for (var i=0;i<document.commongroupform.elements.length;i++)
	   {
	     var e = document.commongroupform.elements[i];
		  if (e.name == 'personid')
			 e.checked = document.commongroupform.allbox.checked;
	   }
	 }


function checkcommongroup(){
	for (var i=0;i<document.commongroupform.elements.length;i++)
   {
     var e = document.commongroupform.elements[i];
	  if (e.name == 'personid' && e.checked){
		 return true;
		}
   }
   return false;
}

function _chooseCommonGroup(){
	if(checkcommongroup()){
		document.commongroupform.action="<%=request.getContextPath()%>/servlet/ChooseCommonOrgServlet#anchor";
		document.commongroupform.submit();
	}
	else{
		alert("请选择二级公共分组！");
	}
}

function _allPeople(){
		document.personform.action="<%=request.getContextPath()%>/servlet/ChooseAllPersonServlet#anchor";
		document.personform.submit();
}

function _back(){
   window.location.href = "<%=request.getContextPath()%>/servlet/SelectCommonGroupServlet?doFunction=<%= request.getParameter("doFunction")%>&sessionname=<%= request.getParameter("sessionname")%>";
}

</SCRIPT>
<BODY  bgcolor="#eff7ff">
<%
String sessionname = request.getParameter("sessionname");
//System.out.println("selectpersonjsp_sessionname="+sessionname);
String doFunction = request.getParameter("doFunction");

	if (request.getAttribute("commonTwoGroupList")!=null){
	//取该人员的公共分组信息
	List commonTwoGroupList = (List)request.getAttribute("commonTwoGroupList");
	%>
		<form name="commongroupform" method="post" action="" target="listFrame">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">公共分组信息</td>
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
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF">
                        <td width="35%"  height="22"><div align="center" class="title-04">选择</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="65%" ><div align="center" class="title-04">组名</div></td>

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
	<%

			Iterator commongroupiterator=commonTwoGroupList.iterator();
			
			int i = 0;//循环变量
			while(commongroupiterator.hasNext()){
				AddressCommongroupVO addresscommongroupvo=(AddressCommongroupVO)commongroupiterator.next();
				i++;
				String color = "#F2F9FF";
				if(i % 2 == 1)
					color = "#D8EAF8";
%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td align="center" ><input type=checkbox name="personid" value="<%=addresscommongroupvo.getId()%>"></td>
                        <td height="22"   class="text-01"><div align="center"><a href="SelectCommonPersonServlet?groupid=<%=addresscommongroupvo.getId()%>&doFunction=<%=doFunction%>&sessionname=<%=sessionname%>&shouquan=<%=addresscommongroupvo.getNeedaccredit()%>&groupid1=<%= request.getParameter("groupid")%>"><%=StringUtil.escapeNull(addresscommongroupvo.getGroupname())%></a></div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
	<%
				}//while
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
              <%@ include file="../../include/defaultPageScrollBar.jsp"%>
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
<input type=hidden name="doFunction" value="<%=doFunction%>">
<input type=hidden name="sessionname" value="<%=sessionname%>">
<input type=hidden name="isperson" value="00">
<br>
<center>
<input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">本页全选</a></label>
<img src="<%=request.getContextPath()%>/images/button-select.gif" border=0 style="cursor:hand" onClick="javascript:_chooseCommonGroup()" >
<img src="<%=request.getContextPath()%>/images/botton-return.gif" border=0 style="cursor:hand" onClick="javascript:_back()" >
</center>
</form>        
	<%
	}
	%>
<%
//	System.out.println("commonGroup="+(request.getAttribute("commonGroup")==null));
//	System.out.println("*******"+((request.getParameter("orgname")==null)&&(request.getAttribute("commonGroup")==null)&&(request.getAttribute("indiGroup")==null)));
	if(request.getAttribute("commonTwoGroupList")==null){
%>
<br>
<p align="center" class="text-01">请从左边的导航栏选择</p>
<%
}
%>
</BODY>
</HTML>
