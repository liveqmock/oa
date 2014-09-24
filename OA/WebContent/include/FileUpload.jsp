<%@ page contentType="text/html; charset=GB2312" %>
<%
	String path = request.getContextPath();
%>
<form mothed="post" ENCTYPE="multipart/form-data" name="frm" action="<%=path%>/servlet/SendFileUploadAttachServlet">
 <input type="file" id="upfile" name="upfile" style="display:none"/>
</form>