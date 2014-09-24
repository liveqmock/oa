<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.oa.sms.handler.SMSHandler"%>
<%@ page import="com.icss.oa.sms.util.SMSSend"%>
<%@ page import="com.icss.oa.sms.vo.SMSPersonRoleSearchVO"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%
	Connection conn = null;
	Context ctx = null;
	Statement st = null;
	ResultSet rs = null;
	try {

		// ���н��յ��ֻ���
		StringBuilder phonenums = new StringBuilder();

		//conn = getConnection(Globals.DATASOURCEJNDI);
		conn = com.icss.j2ee.services.DBConnectionLocator.getInstance()
				.getConnection(Globals.DATASOURCEJNDI);

		String sql = request.getParameter("sendtoperson");
		System.out.println(sql);
		String content = request.getParameter("content");

		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			if (rs.getString(1) != null && rs.getString(1).length() > 1)
				phonenums.append(rs.getString(1)).append(",");
		}
		System.out.println("@@@@@#@@@" + phonenums);

		//��������Ϣ
		ctx = Context.getInstance();
		UserInfo user = ctx.getCurrentLoginInfo();
		String senderuuid = user.getPersonUuid();
		String sendername = user.getCnName();

		SMSHandler handler = new SMSHandler(conn);
		//String orgname = handler.getOrgName(senderuuid);
		//���ݼ�β
		//content = content + "[" + sendername + "]" + "[" + orgname + "]";
		//out.println("###########" + content);

		//����ID
		String smsid = handler.getSMSIdbyUUID(senderuuid);
		if (phonenums != null) {
			SMSSend sd = new SMSSend();
			//System.out.println("!!!!!!!"+phonenums+content);
			sd.toSend(phonenums.toString(), content, smsid);
			// �������ݿ�
			handler.setHistory(senderuuid, sendername, sql, content);
		}
		response.setContentType("text/html; charset=gbk");
		response.sendRedirect("/oabase/sms/public.jsp");

	} catch (Exception e) {
		System.out.println("���Ӳ���FTP������");
		response.sendRedirect("/oabase/sms/sendFalse.jsp");
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
%>
