<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.regulation.handler.RegulationHandler" %>
<%@ page import="com.icss.regulation.vo.RegulationVO" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler" %>


<%
	Connection conn = null;
	Connection access_conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	RegulationVO vo = new RegulationVO();
	String org ="";
	
		try {
		conn = DBConnectionLocator.getInstance().getConnection("jdbc/OABASE");
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      	String strurl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=/tmp/Regulation.mdb";
      	access_conn = DriverManager.getConnection(strurl, null, null);
      	String getDept = "select * from regulation";
      	stmt = access_conn.createStatement();
      	rs = stmt.executeQuery(getDept);
      	while(rs.next()){
      		out.println(rs.getString("wodate"));
      		out.println(rs.getDate("wodate"));
      	}
		//RegulationHandler handler = new RegulationHandler(conn);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception sqle) {
				sqle.printStackTrace();
			}
			try {
				if (stmt != null) {
					rs.close();
				}
			} catch (Exception sqle) {
				sqle.printStackTrace();
			}
			try {
				if (access_conn != null) {
					access_conn.close();
				}
			} catch (Exception sqle) {
				sqle.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception sqle) {
				sqle.printStackTrace();
			}
		}
	
%>
