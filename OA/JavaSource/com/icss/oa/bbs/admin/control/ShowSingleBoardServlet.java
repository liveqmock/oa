/*
 * Created on 2004-2-19
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.vo.BbsBoardVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowSingleBoardServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List boardList = null;
		Integer boardId = new Integer(request.getParameter("boardId"));
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowSingleBoardServlet");
			BBSHandler handler = new BBSHandler(conn);
			boardList = handler.getBoardListById(boardId);
			request.setAttribute("boardList", boardList);

			boolean isAudited = handler.isAuditBoard(boardId);
			if (isAudited) {
				this.forward(request, response, "/bbs/editBoard.jsp");
			} else { //´ýÉóÅú
				String areaName = handler.getAreaNameById(((BbsBoardVO) boardList.get(0)).getAreaid());
				request.setAttribute("areaName", areaName);
				this.forward(request, response, "/bbs/showBoardMsg.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowSingleBoardServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
