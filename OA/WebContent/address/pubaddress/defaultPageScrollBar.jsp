<!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<%
try{
	int curPageNum = com.icss.j2ee.util.PageScrollUtil.getPageNum();
	int pageCount = com.icss.j2ee.util.PageScrollUtil.getPageCount();
	int tiao  = com.icss.j2ee.util.PageScrollUtil.getRowCount();

	if(curPageNum != 1 && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getFirstPageUrl() %>">第一页</a>
<%
	}else{
%>
第一页
<%
}
if(curPageNum > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getPriorPageUrl() %>">上一页</a>
<%
}else{
%>
上一页
<%
}
if(curPageNum < pageCount){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getNextPageUrl() %>">下一页</a>
<%
}else{
%>
下一页
<%
}
if(curPageNum != pageCount && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>">最后页</a>
<%
}else{
%>
最后页
<%
}
%>
&nbsp;&nbsp;
第<%=curPageNum%>页/共<%=pageCount%>页
&nbsp;&nbsp;
共<%=tiao%>条
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>