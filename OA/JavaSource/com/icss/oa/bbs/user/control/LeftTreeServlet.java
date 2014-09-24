/*
 * Created on 2004-12-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.user.control;

import java.io.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LeftTreeServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		List list = new ArrayList();
		List areaNamelist = new ArrayList();
		try {
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String personuuid = user.getPersonUuid();

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("LeftTreeServlet");
			BBSHandler handler = new BBSHandler(conn);
			list = handler.getAllareaList(personuuid);
			request.setAttribute("list", list);
			request.setAttribute("personuuid", personuuid);
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			this.forward(request, response, "/bbs/leftTree.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("LeftTreeServlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}

}
