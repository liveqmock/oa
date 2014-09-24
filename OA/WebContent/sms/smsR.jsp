<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.oa.sms.handler.SMSHandler" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>
<%@ page import="com.icss.oa.sms.util.SMSSend"%>


<%
String name = request.getParameter("name");
String phone = request.getParameter("phone");
String content = request.getParameter("content");
Connection conn = null;
Context ctx = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection("jdbc/OABASE");
	ctx = Context.getInstance();
	
	UserInfo user = ctx.getCurrentLoginInfo();
	String senderuuid = user.getPersonUuid();
	String sendername = user.getCnName();
	
	SMSHandler handler = new SMSHandler(conn);
	String orgname = handler.getOrgName(senderuuid);
	//内容加尾
	
	content =content+"["+sendername+"]"+"["+orgname+"]";
	SMSSend sd = new SMSSend();
	//System.out.println("!!!!!!!"+phonenums+content);
	String smsid = handler.getSMSIdbyUUID(senderuuid);
	sd.toSend(phone+",", content,smsid);
	// 存入数据库
	
	handler.setHistory(senderuuid, sendername, name,content);
	out.println("<script>alert(\"发送成功！\");window.close();</script>");
} catch (Exception ex) {
	out.println("<script>alert(\"发送失败，请和管理员联系！\")</script>");
	ex.printStackTrace();
} finally {
	if (conn != null) {
		conn.close();
	}
}

%>