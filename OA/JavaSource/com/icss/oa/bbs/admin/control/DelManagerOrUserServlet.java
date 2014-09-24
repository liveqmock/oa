/*
 * Created on 2004-6-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelManagerOrUserServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		String type = request.getParameter("type");
		String perid[] = request.getParameterValues("perid");
		List boardList = null;
		Integer boardId = new Integer(request.getParameter("boardId"));
		try {
			//conn=this.getConnection(Globals.DATASOURCEJNDI);
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelManagerOrUserServlet");
			BBSHandler handler = new BBSHandler(conn);
			for (int j = 0; j < perid.length; j++) {
				String pid = perid[j];
				if (type.equals("1")) { //为板主
					handler.delManager(boardId, pid);
				} else { //为限制人员
					handler.delUserRight(boardId, pid);
				}
			}

			boardList = handler.getBoardListById(boardId);
			request.setAttribute("boardList", boardList);
			this.forward(request, response, "/bbs/editBoard.jsp");

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelManagerOrUserServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
