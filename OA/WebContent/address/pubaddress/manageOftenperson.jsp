<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<!--0413从地址簿中选择人员类型。和电话号码-->
<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>
<jsp:useBean id="handler1" class="com.icss.oa.address.handler.SysOrgpersonHandler"/>
<!--0413从地址簿中选择人员类型。和电话号码-->
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</HEAD>
<SCRIPT language=JavaScript>

function checkperson(){
	for (var i=0;i<document.personform.elements.length;i++)
   {
     var e = document.personform.elements[i];
	  if (e.name == 'personid' && e.checked){
		 return true;
		}
   }
   return false;
}

function _delPeople(){
	if(checkperson()){
		document.personform.action="<%=request.getContextPath()%>/servlet/DelOftenPersonServlet";
		document.personform.submit();
	}
	else{
		alert("请选择要删除的联系人！");
	}
}

</SCRIPT>
<BODY  bgcolor="#eff7ff">
<%

List personlist = request.getAttribute("personlist")==null?new ArrayList():(List)request.getAttribute("personlist");
//如果没有取到组织列表中的人员信息，则去取该人员的分组信息
if (!personlist.isEmpty()){
%>
<form name="personform" method="post" action="">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">常用联系人</td>
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
                        <td width="10%" ><div align="center" class="title-04">选择</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
						<td width="10%" ><div align="center" class="title-04">序号</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">姓名</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">用户类型</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" height="22"><div align="center" class="title-04">所属组织</div></td>                        
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" ><div align="center" class="title-04">电话</div></td>

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
						<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%

        Iterator result=personlist.iterator();
		int i = 0;
        while(result.hasNext()){
            Person person = (Person)result.next();
            String personuuid = person.getUuid();
			i++;
			String color = "#F2F9FF";
			if(i % 2 == 1)
				color = "#D8EAF8";
		
%>
			<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
				<td  align="center"  class="text-01"><input type=checkbox name="personid" value="<%=person.getLoginName()%>"></td>
				<td height="22"   class="text-01" align="center"><%=i%></td>
				<td height="22"   class="text-01" align="center"><%=StringUtil.escapeNull(person.getFullName())%></td>
				<td  class="text-01" align="center"><%=handler1.getJobFromPhonebyPeron(personuuid)%></td>
				<td class="text-01" align="center"><%=handler.getPersonJUPositionInJsp(personuuid)%></td>
				<td  class="text-01" align="center"><%=StringUtil.escapeNull(person.getOfficetel())%></td>
			</tr>
			  <tr>			  
				<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
				<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				 <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
			  </tr>
<%
			}//end while
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
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="../../include/defaultPageScrollBar.jsp"%>
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
<br>
<center><a href="javascript:_delPeople()" target="_self"><img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand"></a>
</center>
</form>
<%
}else{
%>
<br>
<p align="center" class="text-01">没有常用联系人</p>
<%
}//else

%>
</BODY>
</HTML>

