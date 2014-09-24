/*
 * Created on 2004-4-7
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.journal.handler.JournalHandler;
import com.icss.oa.netoffice.journal.vo.OfficeJournalVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ContentShowServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		String id = request.getParameter("jid");

		int j_id = Integer.parseInt(id);
		Integer id_riji = new Integer(j_id);

		OfficeJournalVO jvo = null;

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			JournalHandler jHandler = new JournalHandler(conn);
			jvo = jHandler.getById(id_riji);
			request.setAttribute("vo", jvo);
			String choice = request.getParameter("doChoice");
			if (choice.equals("content")) {
				this.forward(
					request,
					response,
					"/netoffice/journal/content_riji.jsp");
			} else {
				this.forward(
					request,
					response,
					"/netoffice/journal/content_update.jsp");
			}

		} catch (Exception e) {
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
