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
String _page_num=request.getParameter("_page_num");
if(_page_num==null)
    _page_num="1";

%>
<%
List personlist = (List)request.getAttribute("personlist");
if (!personlist.isEmpty()){
%>

				  <table>
					  <tr bgcolor="EEF4FF">
						<td width="10%" align="center">����</td>
						<td width="5%" align="center">�Ա�</td>
						<td width="12%" align="center">ְ��</td>
						<td width="12%" align="center">����</td>
						<td width="15%" align="center">���ĵ�ַ</td>
						<td width="15%" align="center">˽�ĵ�ַ</td>
						<td width="12%" align="center">�绰</td>

					</tr>
<%

        Iterator result=personlist.iterator();

        while(result.hasNext()){
            Person personVO=(Person)result.next();

%>
					<tr>
						<td align="center"><%=StringUtil.escapeNull(personVO.getFullName())%></td>
						<td align="center"><%=personVO.getSex()%> </td>
						<td align="center"><%=StringUtil.escapeNull(personVO.getPosition().getPname())%> </td>
						<td align="center"><%=StringUtil.escapeNull(personVO.getPrimaryOrg().getName())%> </td>
						<td align="center"><%=StringUtil.escapeNull(personVO.getEnName())%> </td>
						<td align="center"><%=StringUtil.escapeNull(personVO.getEnName())%> </td>
						<td align="center"><%=StringUtil.escapeNull(personVO.getOfficetel())%> </td>
					</tr>
<%
        }//while
}else{
%>
<BR>
û����Ա
<%
}
%>
				<TR>
				  <TD colspan="8"  align="center">
					<%@ include file="defaultPageScrollBar.jsp" %>
				  </TD>
				</TR>
				</table>
	
</BODY>
</HTML>
