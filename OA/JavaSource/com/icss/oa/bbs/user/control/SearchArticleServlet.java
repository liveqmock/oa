/*
 * Created on 2004-2-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.user.control;

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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsBoardVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchArticleServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		String userId = request.getParameter("userId");
		Integer boardId = new Integer(request.getParameter("boardId"));
		String quickSearchFlag = request.getParameter("quickSearchFlag");
		String articleName = request.getParameter("articleName");
		BbsBoardVO bvo = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SearchArticleServlet");
			BBSHandler handler = new BBSHandler(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			String currUserId = userMsghandler.getUserId();
			if (quickSearchFlag != null)
				userId = currUserId;
			bvo = handler.getBoardVO(boardId);
			list = handler.getUserArticleList(userId, boardId, quickSearchFlag, articleName);
			request.setAttribute("currUserId", currUserId);
			request.setAttribute("userArticleList", list);
			request.setAttribute("boardVO", bvo);
			this.forward(request, response, "/bbs/searchArticle.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SearchArticleServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
