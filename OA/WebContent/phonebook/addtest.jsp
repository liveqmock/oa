<%@ page contentType="text/html; charset=GBK" language="java"
	errorPage=""%>
<%@ page import="com.icss.oa.tq.Webservice.TQUser"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@page import="java.sql.ResultSet"%>
<html>
<head>
<title>添加新华通人员</title>
</head>
<body>
<%
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String uin = "";
	String userid = request.getParameter("userid");
	String username=request.getParameter("username");
	String password="ROTDqyBNVFSxE";
	String sex=request.getParameter("sex");
	
	try {
		if (userid != null && !"".equals(userid.trim())) {
			TQUser tquser = new TQUser();
			uin = tquser.oneUserRegister(userid, password, sex,
						username, "", "", "2", "");
		 out.println("addUser.jsp中，uin="+uin);
			
		} else {
			out.println("请在链接后面加上userid<br/>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		if (rs != null)
			rs.close();
		if (pst != null)
			pst.close();
		if (conn != null)
			conn.close();
	}
%>
alert(uin);
</body>
</html>
