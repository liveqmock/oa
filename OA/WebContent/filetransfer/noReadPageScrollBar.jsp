<!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<table width="100%">
<tr>
<td nowrap width="42%" align="left"> 
<%
try{

int curPageNum = ((Integer)request.getAttribute("curPageNum")).intValue();         //��ǰҳ
int pagecount = ((Integer)request.getAttribute("pagecount")).intValue();     //���ж���ҳ
int totalcount = ((Integer)request.getAttribute("totalcount")).intValue();     //���ж�������¼

int nextPageNum = curPageNum+1;
int priorPageNum = curPageNum-1;
int lastPageNum = pagecount;

	if(curPageNum != 1 && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=1">�� ҳ</a>&nbsp;&#9474;
<%
	}else{
%>
�� ҳ&nbsp;&#9474;
<%
}
if(curPageNum > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=<%=priorPageNum%>">��һҳ</a>&nbsp;&#9474;
<%
}else{
%>
��һҳ&nbsp;&#9474;
<%
}
if(curPageNum < pagecount){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=<%=nextPageNum%>">��һҳ</a>&nbsp;&#9474;
<%
}else{
%>
��һҳ&nbsp;&#9474;
<%
}
if(curPageNum != pagecount && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=<%=lastPageNum%>">β ҳ</a>
<%
}else{
%>
β ҳ
<%
}
%>
</td>
<td nowrap width="18%" align="left"> &nbsp;</td>
<td nowrap align="right" width="40%">
��&nbsp;<%=curPageNum%>&nbsp;ҳ&nbsp;��&nbsp;<%=pagecount%>&nbsp;ҳ&nbsp;&nbsp;����<%=totalcount%>����¼
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>
</td></tr></table>