<!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<table width="100%">
<tr>
<td nowrap width="42%" align="left"> 
<%
try{

String ptype = request.getParameter("type");
String pfolderSort = request.getParameter("folder");

int curPageNum = ((Integer)request.getAttribute("curPageNum")).intValue();         //��ǰҳ
int pagecount = ((Integer)request.getAttribute("pagecount")).intValue();     //���ж���ҳ
int totalcount = ((Integer)request.getAttribute("totalcount")).intValue();     //���ж�������¼

int nextPageNum = curPageNum+1;
int priorPageNum = curPageNum-1;
int lastPageNum = pagecount;

	if(curPageNum != 1 && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=1&type=<%=ptype%>&folder=<%=pfolderSort%>">�� ҳ</a>&nbsp;&#9474;
<%
	}else{
%>
�� ҳ&nbsp;&#9474;
<%
}
if(curPageNum > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=<%=priorPageNum%>&type=<%=ptype%>&folder=<%=pfolderSort%>">��һҳ</a>&nbsp;&#9474;
<%
}else{
%>
��һҳ&nbsp;&#9474;
<%
}
if(curPageNum < pagecount){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=<%=nextPageNum%>&type=<%=ptype%>&folder=<%=pfolderSort%>">��һҳ</a>&nbsp;&#9474;
<%
}else{
%>
��һҳ&nbsp;&#9474;
<%
}
if(curPageNum != pagecount && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=<%=lastPageNum%>&type=<%=ptype%>&folder=<%=pfolderSort%>">β ҳ</a>
<%
}else{
%>
β ҳ
<%
}
%>
</td>
<td nowrap width="18%"> &nbsp;</td>
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