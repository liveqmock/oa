<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ page import="com.icss.j2ee.util.PageScrollUtil"%>
<table width="100%"><tr><td width="50%" align="left"> 
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>

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
<td align="right" width="50%">
<select name ='ee' onChange="javaScript:_toPage(this.value)" style="width:60px;font-size:12px">
    <option value='0'>ҳ ��  </option>
<% for(int _fanyeNo=1;_fanyeNo<=pageCount;++_fanyeNo){%>
	<option value='<%= _fanyeNo%>'><%= _fanyeNo%></option>
<%}%>
</select>
��&nbsp;<%=curPageNum%>&nbsp;ҳ&nbsp;��&nbsp;<%=pageCount%>&nbsp;ҳ&nbsp;&nbsp;����<%=tiao%>����¼
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>
</td></tr></table>

<SCRIPT language=JavaScript>
function _toPage(pageno){
    
    if(pageno!='0'){
	    var str = '<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>';
	    var a1=str.lastIndexOf("=");
	    var a3=str.substr(0,a1);
	    var a4=a3+'='+pageno;
		window.top.bodyfrm.location.href = a4;}
}


</SCRIPT>