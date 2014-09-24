<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.icss.oa.filetransfer.dao.FiletransferReadrecordBakDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.icss.j2ee.services.DBConnectionLocator"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<%	
Connection conn = null;
conn = DBConnectionLocator.getInstance().getConnection("jdbc/OABASE");  
FiletransferReadrecordBakDAO frbdao = new FiletransferReadrecordBakDAO(conn);
frbdao.setMailid(new Integer(99999999));
frbdao.setReadpersonuuid("7deef758-fdb75f4693-8581bd2af19e660037d144f55ca4fb1e");
frbdao.setIsread("1");
frbdao.create();
conn.close();
 %>
</body>
</html>