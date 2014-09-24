<%@ page contentType="text/html; charset=GBK" %>

	<style type="text/css">
       <!--
         body {
	        margin-left: 0px;
	        margin-top: 0px;
	        margin-right: 0px;
	        margin-bottom: 0px;
	    }
	    div, td {
	        font-size: 12px;
	     }
        -->
        
       </style>
                

<div id="alertbox" style="position:absolute; width:196px; height:24px; z-index:1; left: 300px; top: 200px;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align=center>正在加载数据，请稍等...</td>
		</tr>
	</table>
</div>
<%
String resUUID = request.getParameter("resUUID");
if(resUUID == null) {
	resUUID = "";
}
%>
<script>
<!-- 
window.location.href="/oabase/servlet/MessageListServlet?type=system&folder=Inbox&search=no&resUUID=<%=resUUID%>";
// -->
</script>