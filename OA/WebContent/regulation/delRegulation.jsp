<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler"%>

<%
	String id[] = request.getParameterValues("rid");
	Connection conn = null;

	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				Globals.DATASOURCEJNDI);
		RegulationHandler handler = new RegulationHandler(conn);

		for (int i = 0; i < id.length; i++) {
			handler.delRegulation(Integer.valueOf(id[i]));
		}
		
		response.sendRedirect("/oabase/servlet/AllRegulationServlet");

	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();

	} finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}
%>
