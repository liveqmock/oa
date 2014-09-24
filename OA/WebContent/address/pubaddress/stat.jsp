<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.icss.oa.util.*" %>
<%@taglib uri="/WEB-INF/stat.tld" prefix="stat" %>
<HTML>
	<HEAD>
	<TITLE>Stat</TITLE>
	</HEAD>
	<BODY>
<%
		String modulename = PropertyManager.getProperty("stat_module1");
%>
		<stat:stat modulename="<%=modulename%>" />
	</BODY>
</HTML> 