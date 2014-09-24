/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.memo.handler.MemoHandler;
import com.icss.oa.netoffice.setnetoffice.handler.SetNetofficeHandler;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CalendarChoiceMemoServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		String date1 = request.getParameter("date");
		if (date1 == null) {
			date1 = (String) request.getAttribute("date");
		}
		request.setAttribute("date", date1);

		Connection conn = null;
		//OfficeMemoVO mvo = null;
		Integer months = null;

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			long date2 = MemoHandler.getLongByTime2(date1);
			long date3 = date2 + 86400000;
			Long date22 = new Long(date2);
			Long date33 = new Long(date3);

			//MemoHandler mHandler=new MemoHandler(conn);
//			String time2 = MemoHandler.getTime(date2);
//			String time3 = MemoHandler.getTime(date3);

			MemoHandler mHandler = new MemoHandler(conn);
			String personid = mHandler.getUserId();
			List mList = mHandler.getListInTimeSegment(date22, date33, personid);

			request.setAttribute("list", mList);

			//取办公室设置的备忘录保留月份数目
			SetNetofficeHandler setHandler = new SetNetofficeHandler(conn);
			months = setHandler.getMonthsByType(TypeConfig.MEMO_TYPE);
			//System.out.println("months is...............="+months);

			this.forward(
				request,
				response,
				"/netoffice/memo/memoInTimeSect.jsp?months=" + months);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			handleError(e);

		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}
