package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.oa.statsite.vo.*;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;

public class ShowMoldList extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		Integer months = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowMoldList");
			StatSiteHandler logHandler = new StatSiteHandler(conn);
			list1 = logHandler.getMoldList();
			list2 = logHandler.getMoldList();

			long num1 = 0;

			Iterator logIterator = null;
			if (list2 != null) {
				logIterator = list2.iterator();
			}

			while (logIterator.hasNext()) {
				StatSiteMoldVO vo = (StatSiteMoldVO) logIterator.next();
				num1 += vo.getVisNumber().intValue();
			}

			request.setAttribute("list", list1);
			request.setAttribute("count", new Long(num1));
//			System.out.println("get list success!...........");
			this.forward(request, response, "/statsite/showMoldList.jsp");
		} catch (Exception e) {
			System.out.println(e.toString());
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowMoldList");
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

}
