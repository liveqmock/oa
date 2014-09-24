<%@ page contentType="text/html; charset=GBK" %>

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

function _choosePeople(){
	if(checkperson()){
		document.personform.action="<%=request.getContextPath()%>/servlet/ChoosePersonServlet#anchor";
		document.personform.submit();
	}
	else{
		alert("请选择人员！");
	}
}


</SCRIPT>
<BODY  bgcolor="#eff7ff">
<%
String sessionname = request.getParameter("sessionname");
//System.out.println("selectpersonjsp_sessionname="+sessionname);
String doFunction = request.getParameter("doFunction");
System.out.println("doFunction***************="+doFunction);
String isperson = "";
List personlist = new ArrayList();

//System.out.println("person="+(request.getAttribute("personlist")==null));
if(request.getAttribute("personlist")!=null){
	personlist = (List)request.getAttribute("personlist");
}
//System.out.println("personlist="+(personlist.isEmpty()));
//如果没有取到组织列表中的人员信息，则去取该人员的分组信息
if (!personlist.isEmpty()){
	isperson = "1";
%>
<form name="personform" method="post" action="" target="listFrame">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">查找人员结果列表</td>
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
                        <td width="15%" ><div align="center" class="title-04">姓名</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" ><div align="center" class="title-04">用户类型</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="40%" height="22"><div align="center" class="title-04">所属组织</div></td>
                  <!--  <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" height="22"><div align="center" class="title-04">私文地址</div></td> -->
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">电话</div></td>

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                   <!-- <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td> -->
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%

Organization organization = null;
Organization parentorganization = null;
String parentorgname = "";

EntityManager entityManager = EntityManager.getInstance();
        Iterator result=personlist.iterator();

		int i = 0;//循环变量
        while(result.hasNext()){
            SysOrgpersonVO sysorgpersonvo=(SysOrgpersonVO)result.next();
			i++;
			String color = "#F2F9FF";
			if(i % 2 == 1)
				color = "#D8EAF8";
			
			
			
		
/*		Person person = entityManager.findPersonByUuid(sysorgpersonvo.getPersonuuid());
		
try {
	organization = entityManager.findOrganizationByUuid(person.getPrimaryOrg().getOrgUuid());
	if(organization.getOrglevel().intValue()>3){
		parentorganization = organization.getParentOrg();
	}
}catch (Exception ex) {
	ex.printStackTrace();
}
parentorgname = "";
if(null!=parentorganization){
 	parentorgname = parentorganization.getName()+"->";
 }
*/		
		
%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td  align="center"  class="text-01"><input type=checkbox name="personid" value="<%=sysorgpersonvo.getUserid()%>"></td>
                        <td height="22"   class="text-01"><div align="center"><%=StringUtil.escapeNull(sysorgpersonvo.getCnname())%></div></td>
                        <td  class="text-01"><div align="center"><!--%=StringUtil.escapeNull(sysorgpersonvo.getJob())%--><%= handler1.getJobFromPhonebyPeron(sysorgpersonvo.getPersonuuid())%> </div></td>
                        <td class="text-01"><div align="center"><!--<!--%=parentorgname%--><!--%=StringUtil.escapeNull(person.getPrimaryOrg().getName())--%>--><%= handler.getPersonJUPositionInJsp(sysorgpersonvo.getPersonuuid())%></div></td>
                <!--    <td class="text-01"><div align="center"><%=StringUtil.escapeNull(sysorgpersonvo.getEnname())%></div></td> -->
                        <td  class="text-01"><div align="center"><!--%=StringUtil.escapeNull(sysorgpersonvo.getOfficetel())%-->
                        <% String officecode = handler1.GetOnePhone(sysorgpersonvo.getPersonuuid());%><%= officecode!=null?officecode:(StringUtil.escapeNull(sysorgpersonvo.getOfficetel()))%>
                        </div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                 <!--   <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td> -->
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
			parentorganization = null;
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
<input type=hidden name="isperson" value="<%=isperson%>">
<input type=hidden name="doFunction" value="<%=doFunction%>">
<input type=hidden name="sessionname" value="<%=sessionname%>">
<br>
<center>
<img src="<%=request.getContextPath()%>/images/button-select.gif" border=0 style="cursor:hand" onClick="javascript:_choosePeople()" >
</center>
</form>
<%
}else{
%>
<br>
<p align="center" class="text-01">没有找到符合条件的人员</p>
<%
}//else

%>
<%
if(request.getAttribute("personlist")==null){
%>
<br>
<p align="center" class="text-01">请从左边的导航栏选择</p>
<%
}
%>
</BODY>
</HTML>
