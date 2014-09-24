<!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<table width="100%">
<tr>
<td nowrap width="42%" align="left"> 
<%
try{

String ptype = request.getParameter("type");
String pfolderSort = request.getParameter("folder");

int curPageNum = ((Integer)request.getAttribute("curPageNum")).intValue();         //当前页
int pagecount = ((Integer)request.getAttribute("pagecount")).intValue();     //共有多少页
int totalcount = ((Integer)request.getAttribute("totalcount")).intValue();     //共有多少条记录

int nextPageNum = curPageNum+1;
int priorPageNum = curPageNum-1;
int lastPageNum = pagecount;

	if(curPageNum != 1 && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=1&type=<%=ptype%>&folder=<%=pfolderSort%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>">首 页</a>&nbsp;&#9474;
<%
	}else{
%>
首 页&nbsp;&#9474;
<%
}
if(curPageNum > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=<%=priorPageNum%>&type=<%=ptype%>&folder=<%=pfolderSort%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>">上一页</a>&nbsp;&#9474;
<%
}else{
%>
上一页&nbsp;&#9474;
<%
}
if(curPageNum < pagecount){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=<%=nextPageNum%>&type=<%=ptype%>&folder=<%=pfolderSort%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>">下一页</a>&nbsp;&#9474;
<%
}else{
%>
下一页&nbsp;&#9474;
<%
}
if(curPageNum != pagecount && pagecount > 1){
%>
<a href="<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=<%=lastPageNum%>&type=<%=ptype%>&folder=<%=pfolderSort%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>">尾 页</a>
<%
}else{
%>
尾 页
<%
}
%>
</td>
<td nowrap width="18%">&nbsp; </td>
<td nowrap align="right" width="60%">
<select name ='ee' onChange="javaScript:_toPage(this.value)" style="width:50px;font-size:9.5px">
    <option value='0'>页 数</option>
<% for(int _fanyeNo=1;_fanyeNo<=pagecount;++_fanyeNo){%>
	<option value='<%= _fanyeNo%>'><%= _fanyeNo%></option>
<%}%>
</select>
第&nbsp;<%=curPageNum%>&nbsp;页&nbsp;共&nbsp;<%=pagecount%>&nbsp;页&nbsp;&nbsp;共有<%=totalcount%>条记录
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>
</td></tr></table>

