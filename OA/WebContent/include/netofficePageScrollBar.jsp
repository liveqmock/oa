<%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<table width="100%">
<tr>
<td width="28%" align="left"> &nbsp;</td>
<td width="36%" align="left"> 
<%
try{
	int curPageNum = com.icss.j2ee.util.PageScrollUtil.getPageNum();
	int pageCount = com.icss.j2ee.util.PageScrollUtil.getPageCount();
	int tiao  = com.icss.j2ee.util.PageScrollUtil.getRowCount();
%>
<%
	if(curPageNum != 1 && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getFirstPageUrl() %>">�� ҳ</a>&nbsp;&#9474;
<%
	}else{
%>
�� ҳ&nbsp;&#9474;
<%
}
if(curPageNum > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getPriorPageUrl() %>">��һҳ</a>&nbsp;&#9474;
<%
}else{
%>
��һҳ&nbsp;&#9474;
<%
}
if(curPageNum < pageCount){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getNextPageUrl() %>">��һҳ</a>&nbsp;&#9474;
<%
}else{
%>
��һҳ&nbsp;&#9474;
<%
}
if(curPageNum != pageCount && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>">β ҳ</a>
<%
}else{
%>
β ҳ
<%
}
%>
</td>
<td align="right" width="36%">
��&nbsp;<%=curPageNum%>&nbsp;ҳ&nbsp;��&nbsp;<%=pageCount%>&nbsp;ҳ&nbsp;&nbsp;����<%=tiao%>����¼
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>
</td></tr></table>