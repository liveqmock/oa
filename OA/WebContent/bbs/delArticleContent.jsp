
<%@page import="java.util.List"%>
<%@page import="com.icss.oa.bbs.vo.BbsArticleDelVO"%>
<%@page import="com.icss.j2ee.dao.DAOFactory"%>
<%@page import="com.icss.oa.bbs.dao.BbsArticleDelDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.icss.j2ee.util.Globals"%>
<%@page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@page import="com.icss.j2ee.services.DBConnectionLocatorException"%><%@ page
	contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>删除帖子列表</title>
<link href="<%=request.getContextPath()%>/Style/css_grey.css"
	id=homepagestyle rel="stylesheet" type="text/css" />

</head>

<body>
<%
	String id = request.getParameter("id");
	Connection conn = null;
	try {
		if (id != null) {

			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);

			BbsArticleDelDAO dao = new BbsArticleDelDAO();
			DAOFactory factory = new DAOFactory(conn);
			dao.setArticleid(Integer.valueOf(id));
			factory.setDAO(dao);
			List list = factory.find(new BbsArticleDelVO());

			if (!list.isEmpty()) {
				BbsArticleDelVO vo = (BbsArticleDelVO) list.get(0);
%>
<table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor"
	width="100%">
	<tr>
		<td align="center" class="block_title">删除帖子内容</td>
	</tr>
	<tr>
		<td class="message_title"><%=vo.getArticlecontent() %></td>
	</tr>
</table>


<%
			}

		}
%>

<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();

	} finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
%>
</body>

</html>