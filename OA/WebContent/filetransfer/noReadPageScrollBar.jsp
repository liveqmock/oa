<!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<table width="100%">
<tr>
<td nowrap width="42%" align="left"> 
<%
try{

int curPageNum = ((Integer)request.getAttribute("curPageNum")).intValue();         //当前页
int pagecount = ((Integer)request.getAttribute("pagecount")).intValue();     //共有多少页
int totalcount = ((Integer)request.getAttribute("totalcount")).intValue();     //共有多少条记录

int nextPageNum = curPageNum+1;
int priorPageNum = curPageNum-1;
int lastPageNum = pagecount;

	if(curPageNum != 1 && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=1">首 页</a>&nbsp;&#9474;
<%
	}else{
%>
首 页&nbsp;&#9474;
<%
}
if(curPageNum > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=<%=priorPageNum%>">上一页</a>&nbsp;&#9474;
<%
}else{
%>
上一页&nbsp;&#9474;
<%
}
if(curPageNum < pagecount){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=<%=nextPageNum%>">下一页</a>&nbsp;&#9474;
<%
}else{
%>
下一页&nbsp;&#9474;
<%
}
if(curPageNum != pagecount && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/ShowNoReadServlet?curPageNum=<%=lastPageNum%>">尾 页</a>
<%
}else{
%>
尾 页
<%
}
%>
</td>
<td nowrap width="18%" align="left"> &nbsp;</td>
<td nowrap align="right" width="40%">
第&nbsp;<%=curPageNum%>&nbsp;页&nbsp;共&nbsp;<%=pagecount%>&nbsp;页&nbsp;&nbsp;共有<%=totalcount%>条记录
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>
</td></tr></table>