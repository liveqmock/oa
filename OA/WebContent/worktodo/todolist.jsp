
<%@page import="org.json.JSONArray"%><%@page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@page import="org.json.JSONObject"%>
<%@page import="com.icss.oa.intendwork.vo.OfficePendingVO"%>
<%@page import="com.icss.oa.intendwork.handler.IntendWork"%>
<%@page import="com.icss.resourceone.sdk.framework.Context"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.icss.j2ee.util.Globals"%>
<%@page import="com.icss.j2ee.services.DBConnectionLocator"%>

<%
	Connection con = null;

	Context ctx = Context.getInstance();
	String userid = ctx.getCurrentUserid();
	try {
		if (userid == null && "".equals(userid)) {
			out.println();
		} else {
			con = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			IntendWork intendWork = new IntendWork(con);
			String sql = "PERSONID='" + userid + "' ";
            java.util.List workList = intendWork.searchWork(sql);
            JSONArray json = new JSONArray();
           
            for(Iterator it =workList.iterator();it.hasNext();){
            	OfficePendingVO vo = (OfficePendingVO)it.next();
            	JSONObject j = new JSONObject();
            	j.put("source",vo.getOpSource());
            	j.put("topic",vo.getOpTopic());
            	j.put("id",vo.getOpId());
            	j.put("url",vo.getOpUrl());
            	json.put(j);
	        }
            out.println(json);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (con != null) {
			con.close();
		}
	}
%>
