<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="com.icss.oa.hr.admin.HRGroupWebservice"%>

<%
	//��������ͬ�� ����д��servlet�У�����servletÿ�ε��ö���ͬ��������
	out.println("��������ͬ���ӿ�,�ȴ���...");
	
	HRGroupWebservice hr = new HRGroupWebservice();
	hr.GetSyncOrgInfo();
	out.println("ͬ�����,��ת��...");
	
	response.sendRedirect(request.getContextPath()+"/servlet/GetSyncOrgServlet");
 %>

