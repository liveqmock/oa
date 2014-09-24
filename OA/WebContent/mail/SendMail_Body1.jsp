<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.icss.oa.mail.handler.TimeUtil"%>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupinfoVO"%>
<%@	page import="com.icss.oa.address.vo.SysPersonVO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.oa.util.PropertyManager"%>
<%
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日")
			+ TimeUtil.getCurrentWeek();
	SendFileBean sendFileBean = SendFileBean
			.getInstanceFromSession(session);
	String sun_flag = (String) request.getParameter("sun_flag");
	request.getSession().setAttribute("sun_flag",sun_flag);
	if (sun_flag == null) {
		sun_flag = "";
	}
	String newSendFile =(String)request.getAttribute("newSendFile");
	request.getSession().setAttribute("newSendFile",newSendFile);
	String broadcastnum = PropertyManager.getProperty("broadcastnum");
%>

<html>
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
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<link href="<%=path%>/mail/css/Attach.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/Style/css_grey.css" rel="stylesheet"
			type="text/css" id="homepagestyle" />
		<LINK href="<%=request.getContextPath()%>/include/address.css"
			rel=stylesheet>

		<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
		<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
		<SCRIPT language=JavaScript src="<%=path%>/include/AttachFile.js"></SCRIPT>
		<script type="text/javascript" src="<%=path%>/mail/js/Attach.js"></script>
		<script type="text/javascript" src="<%=path%>/mail/js/suggest.js"></script>

		<script type="text/javascript"
			src="<%=path%>/mail/js/jquery-1.2.6.pack.js"></script>
		<script type="text/javascript"
			src="<%=path%>/mail/js/jquery.autocomplete.js"></script>
		<link rel="stylesheet"
			href="<%=path%>/mail/css/jquery.autocomplete.css" type="text/css" />

		<script language="javascript">

var broadcastnum = '<%=broadcastnum%>';

function _load(){
	
window.location.href = "<%=request.getContextPath()%>/mail/SendMail_Body.jsp";
    

}



</script>

		<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
	</head>

	<body onLoad="_load()">
</body>
</html>
