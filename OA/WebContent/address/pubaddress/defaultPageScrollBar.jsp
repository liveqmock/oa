<!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<%
try{
	int curPageNum = com.icss.j2ee.util.PageScrollUtil.getPageNum();
	int pageCount = com.icss.j2ee.util.PageScrollUtil.getPageCount();
	int tiao  = com.icss.j2ee.util.PageScrollUtil.getRowCount();

	if(curPageNum != 1 && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getFirstPageUrl() %>">��һҳ</a>
<%
	}else{
%>
��һҳ
<%
}
if(curPageNum > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getPriorPageUrl() %>">��һҳ</a>
<%
}else{
%>
��һҳ
<%
}
if(curPageNum < pageCount){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getNextPageUrl() %>">��һҳ</a>
<%
}else{
%>
��һҳ
<%
}
if(curPageNum != pageCount && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>">���ҳ</a>
<%
}else{
%>
���ҳ
<%
}
%>
&nbsp;&nbsp;
��<%=curPageNum%>ҳ/��<%=pageCount%>ҳ
&nbsp;&nbsp;
��<%=tiao%>��
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>