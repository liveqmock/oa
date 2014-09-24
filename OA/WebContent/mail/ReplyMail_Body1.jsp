<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日")+TimeUtil.getCurrentWeek();
	
	SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
	String sun_flag = (String)request.getParameter("sun_flag");
	request.getSession().setAttribute("sun_flag",sun_flag);
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>新华社办公信息化系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="<%=path%>/mail/css/Attach.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/mail/js/Attach.js"></script>
<script language="javascript">
function _load(){
window.location.href = "<%=request.getContextPath()%>/mail/ReplyMail_Body.jsp";

}
</script>

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body onLoad="_load();">



</body>
</html>
