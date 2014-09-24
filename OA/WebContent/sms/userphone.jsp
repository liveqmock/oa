<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.oa.sms.handler.SMSHandler"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.oa.sms.vo.HRSMSMobileVO"%>
<%
Connection conn = null;
String uuid = request.getParameter("uuid")==null?"":(String)request.getParameter("uuid");
if(uuid==null||"".equals(uuid)){
	out.println("未知");
}else{
try {
	conn = DBConnectionLocator.getInstance().getConnection("jdbc/OABASE");
	SMSHandler handler = new SMSHandler(conn);
	HRSMSMobileVO vo = handler.getHRSMSMobileVO(uuid);
	if("社领导".equals(vo.getDeptname())){
		out.print("");
	}else{
		out.println(vo.getMobilephone());
	}
	
} catch (Exception ex) {
	ex.printStackTrace();
} finally {
	if (conn != null) {
		conn.close();
	}
}
}
%>