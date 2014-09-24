<%@ page contentType="text/html; charset=GBK" %>
<html>
<head><%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module53");
		String ip=request.getRemoteAddr();		
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>
<title>信息搜索</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<frameset rows="90,*" cols="*" frameborder="NO" border="0" framespacing="0">
	 <frame src="<%=request.getContextPath()%>/message/msgSearchTop.jsp" name="searchTopFrame" scrolling="no" noresize>
	 <frame src="<%=request.getContextPath()%>/servlet/SearchShortMsgServlet" name="searchResultFrame" scrolling="auto" noresize>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>