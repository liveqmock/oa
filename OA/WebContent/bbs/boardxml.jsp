<?xml version="1.0" encoding="gb2312" ?>
<%@ page contentType="text/xml; charset=GBK" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%                      
Connection conn = null;
Integer areaid = new Integer(request.getParameter("areaid"));
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
		ConnLog.open("boardxml.jsp");
%>

<Board_List>
<%
		BBSHandler handler = new BBSHandler(conn);
		List boardlist = handler.getBoardByAreaidList(areaid);
		if(boardlist!=null&&boardlist.size()>0){
			Iterator BItr = boardlist.iterator();
			while(BItr.hasNext()){
				BbsBoardVO bVO = (BbsBoardVO)BItr.next();
%>

	<Oneboard>
		<boardid><%=bVO.getBoardid()%></boardid>
		<boardname><%=bVO.getBoardname()%></boardname>
	</Oneboard>
<%
			}
		}
%>
<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("boardxml.jsp");
					}
			} catch (Exception e) {
		}
	}
%>  
</Board_List>