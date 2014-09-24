<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="org.json.*" %>
<%@ page import="com.icss.oa.tq.handler.TQHandler"%>
<%@ page import="com.icss.oa.filetransfer.handler.personInfoHandler" %>
<%@ page import="com.icss.regulation.handler.EscapeUnescape" %>

	<%
		//long a = System.currentTimeMillis();
		//System.out.println("开始时间 ="+a);
		Connection conn = null;
		String keyword = request.getParameter("q");
		if(keyword ==null || "".equals(keyword))
		{
		 	out.println("未找到此人");
		}else{
		try {
			keyword = EscapeUnescape.unescape(keyword);
			//System.out.println("keyword"+keyword);
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			//JSONArray jsonArray = new JSONArray();  
			StringBuffer sb = new StringBuffer();
			TQHandler handler = new TQHandler(conn); 
			personInfoHandler handler1 = new personInfoHandler(conn);
			List list = handler.getUserbyName(keyword,10);
			//long b = System.currentTimeMillis();
			//System.out.println("查询时间 ="+(b-a));
			Iterator result = list.iterator();
			int i = 0;//循环变量
			while (result.hasNext()) {
				SysOrgpersonVO sysorgpersonvo = (SysOrgpersonVO) result
						.next();
				sb.append(StringUtil.escapeNull(sysorgpersonvo.getCnname()));
				
				//String cnname = StringUtil.escapeNull(sysorgpersonvo.getCnname());
				sb.append("|").append(handler1.getPersonJUPositionInJsp(sysorgpersonvo
								.getPersonuuid())).append("|").append(StringUtil.escapeNull(sysorgpersonvo.getPersonuuid())).append("|").append(sysorgpersonvo.getUserid()).append("\n");
				//String personJUPosition = handler1.getPersonJUPositionInJsp(sysorgpersonvo.getPersonuuid());
				//jsonArray.put(cnname+"("+personJUPosition+")");
				i++;
			}
			// keyword=EscapeUnescape.unescape(keyword);  
			// List<SearchSuggestion> results = service.getSearchSuggestionsByKeyword(keyword);  

			//PrintWriter out = response.getWriter();  
			//JSONArray jsonArray = JSONArray.fromObject(results);  
			//out.print(jsonArray.toString());  
			//keyword = "[\"a H50\",\"ab\"]";
			//long c = System.currentTimeMillis();
			//System.out.println("结束时间 ="+(c-a));
			out.print(sb.toString());
			// out.flush();  
			//out.close();  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		}
	%>