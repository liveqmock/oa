<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.icss.regulation.handler.EscapeUnescape"%>
<%@page import="com.icss.regulation.vo.RegulationVO"%>
<%@page import="org.json.JSONObject"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler"%>

<%
	request.setCharacterEncoding("GBK");
	Connection conn = null;
	List RList = new ArrayList();

	Integer pages = request.getParameter("page")==null?new Integer(1):new Integer(request.getParameter("page"));
	Integer rows = request.getParameter("rows")==null?new Integer(10):new Integer(request.getParameter("rows"));
	String sidx = request.getParameter("sidx")==null?" id ":(String)request.getParameter("sidx");
	String sord = request.getParameter("sord")==null?" desc ":(String)request.getParameter("sord");
	
	StringBuilder sb = new StringBuilder();
	String key = request.getParameter("key")==null?"":(String)request.getParameter("key");
	String org = request.getParameter("org")==null?"allorg":(String) request.getParameter("org");
	org = EscapeUnescape.unescape(org).trim();
	key = EscapeUnescape.unescape(key).trim();

	String time = request.getParameter("time");
	String timeo = request.getParameter("timeo");
	String timef = request.getParameter("timef");
	String timee = request.getParameter("timee");
	String fanwei = request.getParameter("fanwei");
	String youxiao = request.getParameter("youxiao");
	sb.append(" 1=1 ");

	if(!"allorg".equals(org)){
		sb.append(" AND ORG='").append(org).append("'");
	}
	if (!"".equals(key.trim())) {
		if ("title".equalsIgnoreCase(fanwei)) {
			sb.append(" AND TITLE like '%").append(key).append("%'");
		} else if ("content".equalsIgnoreCase(fanwei)) {
			sb.append(" AND CONTENT like '%").append(key).append("%'");
		}else if ("rcNO".equalsIgnoreCase(fanwei)) {
			sb.append(" AND RECORD_NO like '%").append(key).append("%'");
		}  
		else {
			sb.append(" AND (TITLE like '%").append(key).append(
					"%' OR CONTENT like '%").append(key).append("%' OR RECORD_NO like '%").append(key)
					.append("%' )");
		}
	}

	if ("yes".equalsIgnoreCase(youxiao)) {
		sb.append(" AND FLAG=0");
	} else if ("no".equalsIgnoreCase(youxiao)) {
		sb.append(" AND FLAG=1");
	}

	if ("only".equalsIgnoreCase(time)) {
		sb.append(" AND to_char(creat_time,'yyyy-mm-dd')='").append(
				timeo).append("'");
	} else if ("from".equalsIgnoreCase(time)) {
		sb
				.append(" AND CREAT_TIME >=to_date('")
				.append(timef)
				.append(
						"','yyyy-mm-dd') AND to_char(creat_time,'yyyy-mm-dd')<='")
				.append(timee).append("'");
	}
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				Globals.DATASOURCEJNDI);
		RegulationHandler handler = new RegulationHandler(conn);
		
		String sql = "select id,title,org from regulation_record where "+sb.toString()+ " order by "+ sidx +" "+ sord;
		
		//取得总数
		int records = handler.querySumPages(sql);

		int total =0;
		if( records >0 ) {
			total= records/rows.intValue();
		} else {
			total = 0;	
		}	
		
		if (pages.intValue() > total) page= new Integer(total);
		
		RList= handler.queryPage(sql,pages.intValue(),rows.intValue());
		//RList = handler.getRNListByClause(sb.toString());
		System.out.println("##regulation where = "+sb.toString());

		Map pageInfo = new HashMap();
		List mapList = new ArrayList();
		for (int i = 0; i < RList.size(); i++) {
			RegulationVO vo = (RegulationVO) RList.get(i);
			Map cellMap = new HashMap();
			cellMap.put("id", vo.getId());
			cellMap.put("cell", new Object[] { vo.getId(),
				vo.getTitle(), handler.getROrgName(vo.getOrg())});
			mapList.add(cellMap);
		}
	
		pageInfo.put("page", pages);
		pageInfo.put("records", new Integer(records));
		pageInfo.put("total", new Integer(total));
		pageInfo.put("rows", mapList);

		JSONObject jobject = new JSONObject(pageInfo);
		out.println(jobject.toString());
		
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
