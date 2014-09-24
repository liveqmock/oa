package com.icss.oa.zbs.duty.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.zbs.duty.handler.ZbsWorkInfoHandler;

/**
 * @version 	1.0
 * @author		wangsu
 */
public class MainDutyListServlet extends ServletBase implements Servlet {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List mainDutyList = new ArrayList();
		//		System.err.println("成功1");
		try {
			ConnLog.open("MainDutyListServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ZbsWorkInfoHandler handler = new ZbsWorkInfoHandler(conn);
			//			System.err.println("成功2");
			String SearchFlag = request.getParameter("SearchFlag");
			if ("1".equals(SearchFlag)) {
				String fromdate = request.getParameter("fromdate") == null ? "1901-01-01" : request.getParameter("fromdate");
				if ("".equals(fromdate)) {
					fromdate = "1901-01-01";
				}
				String todate = request.getParameter("todate") == null ? "2099-12-12" : request.getParameter("todate");
				if ("".equals(todate)) {
					todate = "2099-12-12";
				}
				String selectType = request.getParameter("selectType") == null ? "1" : request.getParameter("selectType");
				String dutyName = request.getParameter("dutyName") == null ? "" : request.getParameter("dutyName");

				mainDutyList =
					handler.getMainDutyListByClause(
						"1=1 AND WIT_CLASS='"
							+ selectType
							+ "' AND (WIT_LEADER like '%"
							+ dutyName
							+ "%' OR WIT_SECRET like '%"
							+ dutyName
							+ "%') AND WIT_DATE>to_date('"
							+ fromdate
							+ "','yyyy-mm-dd') AND WIT_DATE<to_date('"
							+ todate
							+ "','yyyy-mm-dd') ");

			} else {
				mainDutyList = handler.getMainDutyListByClause("1=1");
			}
			//			System.err.println("成功3");
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			String userId = userMsghandler.getUserId();

			request.setAttribute("userId", userId);
			request.setAttribute("mainDutyList", mainDutyList);
			//			System.err.println("成功4");
			this.forward(request, response, "/zbs/zbs_duty/dutyList.jsp");

		} catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("MainDutyListServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
