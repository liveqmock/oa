<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="com.icss.oa.hr.admin.HRGroupWebservice"%>

<%
	//触发人事同步 ，不写在servlet中，这样servlet每次调用都会同步，很慢
	out.println("连接人事同步接口,等待中...");
	
	HRGroupWebservice hr = new HRGroupWebservice();
	hr.GetSyncOrgInfo();
	out.println("同步完成,跳转中...");
	
	//response.sendRedirect(request.getContextPath()+"/servlet/GetSyncOrgServlet");
	//response.sendRedirect(request.getContextPath()+"/servlet/IdsSyncServlet?method=syncOrgList");
	response.sendRedirect("http://10.102.1.37/oabase/servlet/IdsSyncServlet?method=syncOrgList");
 %>

