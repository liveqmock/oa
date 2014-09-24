<%@ page contentType="text/html; charset=GBK"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.common.log.ConnLog"%>
<%@ page import="com.icss.oa.tq.handler.TQHandler"%>
<%@ page import="com.icss.regulation.handler.Json"%>
<%@ page import="com.icss.oa.tq.vo.TqOnlineVO"%>
<%@ page import="com.icss.regulation.handler.EscapeUnescape"%>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>

<%
	Connection conn = null;
	String orgname = request.getParameter("orgname");
	if (orgname == null || "".equals(orgname)) {
		out.println("×éÖ¯Îª¿Õ£¡");
	} else {
		try {
			Context ctx = Context.getInstance();
			String uuid =ctx.getCurrentPersonUuid();
		
			orgname = EscapeUnescape.unescape(orgname);
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("orgonline.jsp");
			TQHandler handler = new TQHandler(conn);
			Integer tqid = handler.getTqid(uuid);
			List plist = handler.getOLByOrgname(orgname);
			Iterator rs = plist.iterator();
			Json json = new Json();
			json.reSet();
			json.setSuccess(true);
			while (rs.hasNext()) {
				TqOnlineVO vo = (TqOnlineVO) rs.next();
				json.addItem("tqid", vo.getTqid());
				json.addItem("cnname", vo.getCnname());
				json.addItem("id", tqid.toString());
				json.addItemOk();
			}
			
			out.print(json.ToString());
%>

<%
	} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("orgonline.jsp");
				}
			} catch (Exception e) {
			}
		}
	}
%>