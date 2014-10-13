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
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getFirstPageUrl() %>">首 页</a>&nbsp;&#9474;
<%
	}else{
%>
首 页&nbsp;&#9474;
<%
}
if(curPageNum > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getPriorPageUrl() %>">上一页</a>&nbsp;&#9474;
<%
}else{
%>
上一页&nbsp;&#9474;
<%
}
if(curPageNum < pageCount){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getNextPageUrl() %>">下一页</a>&nbsp;&#9474;
<%
}else{
%>
下一页&nbsp;&#9474;
<%
}
if(curPageNum != pageCount && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>">尾 页</a>
<%
}else{ 
%>
尾 页
<%
}
%>

</td>
<td align="right" width="50%">
<select name ='ee' onChange="javaScript:_toPage(this.value)" style="width:60px;font-size:12px">
    <option value='0'>页 数  </option>
<% for(int _fanyeNo=1;_fanyeNo<=pageCount;++_fanyeNo){%>
	<option value='<%= _fanyeNo%>'><%= _fanyeNo%></option>
<%}%>
</select>
第&nbsp;<%=curPageNum%>&nbsp;页&nbsp;共&nbsp;<%=pageCount%>&nbsp;页&nbsp;&nbsp;共有<%=tiao%>条记录
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