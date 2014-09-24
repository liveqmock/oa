<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>

<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>

<BODY>
<%
String orgid = request.getParameter("orgid");

EntityManager entitymanager = EntityManager.getInstance();
List personList = entitymanager.findPersonsRecursivelyByOrgUuid(orgid);

Organization organization=new Organization();
organization = entitymanager.findOrganizationByUuid(orgid);

%>
<BR><BR><BR>
<BR>
组织id为：<%=orgid%>
<BR>
组织名为：<%=organization.getName()%>


<BR>
<%
if (!personList.isEmpty()){
%>

				  <table>
					  <tr bgcolor="EEF4FF">
						<td width="30%" align="center">姓名</td>
						<td width="60%" align="center">Uuid</td>

					</tr>
<%

        Iterator result=personList.iterator();

        while(result.hasNext()){
            Person personVO=(Person)result.next();

%>
					<tr>
						<td><%=StringUtil.escapeNull(personVO.getFullName())%></td>
						<td><%=StringUtil.escapeNull(personVO.getUuid())%> </td>
					</tr>
<%
        }//while
}else{
%>
<BR>
<%=organization.getName()%>下没有人员
<%
}
%>
				</table>
	
</BODY>
</HTML>
