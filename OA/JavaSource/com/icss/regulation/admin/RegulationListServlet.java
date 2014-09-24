package com.icss.regulation.admin;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.regulation.handler.EscapeUnescape;
import com.icss.regulation.handler.Json;
import com.icss.regulation.handler.RegulationHandler;
import com.icss.regulation.vo.RegulationVO;

public class RegulationListServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		List RList = new ArrayList();
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			RegulationHandler handler = new RegulationHandler(conn);
			String key = (String) request.getParameter("key") == null ? ""
					: (String) request.getParameter("key");
			String org = (String) request.getParameter("org") == null ? ""
					: (String) request.getParameter("org");
			org = EscapeUnescape.unescape(org).trim();
			key = EscapeUnescape.unescape(key).trim();

			String time = request.getParameter("time");

			String timeo = request.getParameter("timeo");
			String timef = request.getParameter("timef");
			String timee = request.getParameter("timee");

			String fanwei = request.getParameter("fanwei");
			String youxiao = request.getParameter("youxiao");

			String fw = "";
			if ("title".equalsIgnoreCase(fanwei)) {
				fw = "TITLE like '%" + key + "%'";
			} else if ("content".equalsIgnoreCase(fanwei)) {
				fw = "CONTENT like '%" + key + "%'";
			} else {
				fw = "(TITLE like '%" + key + "%' or CONTENT like '%" + key
						+ "%')";
			}

			String yx = "";

			if ("yes".equalsIgnoreCase(youxiao)) {
				yx = "FLAG=0";
			} else if ("no".equalsIgnoreCase(youxiao)) {
				yx = "FLAG=1";
			} else {
				yx = "1=1";
			}

			String SDate = "";
			if ("only".equalsIgnoreCase(time)) {
				SDate = " to_char(creat_time,'yyyy-mm-dd')='" + timeo + "'";
			} else if ("from".equalsIgnoreCase(time)) {
				SDate = " CREAT_TIME >=to_date('"
						+ timef
						+ "','yyyy-mm-dd') AND to_char(creat_time,'yyyy-mm-dd')<='"
						+ timee + "'";
			} else {
				SDate = " 1=1 ";
			}

			RList = handler.getRListByClause("1=1 AND " + fw + " AND " + SDate
					+ " AND " + yx + " AND ORG like '%" + org + "%'");
			Iterator rs = RList.iterator();

			Json json = new Json();
			json.reSet();
			json.setSuccess(true);
			while (rs.hasNext()) {
				RegulationVO vo = (RegulationVO) rs.next();

				json.addItem("id", vo.getId().toString());
				json.addItem("title", vo.getTitle());
				json.addItem("org", handler.getROrgName(vo.getOrg()));
				json.addItemOk();
			}

			PrintWriter out = response.getWriter();

			// StringBuffer s = new StringBuffer();
			// s.append("ok111111111" + key + org + time + "3333a" + timeo +
			// timef
			// + timee);
			// System.out.println(json.ToString());
			// out.println(s);
			out.println(json.ToString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}