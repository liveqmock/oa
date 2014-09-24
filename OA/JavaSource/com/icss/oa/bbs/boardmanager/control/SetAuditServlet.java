/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.boardmanager.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.dao.BbsArticleDAO;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.BoardArticleUpdate;
import com.icss.oa.bbs.handler.UserMsgHandler;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SetAuditServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		// String boardId = request.getParameter("boardId");
		String[] stopicId = request.getParameterValues("topicId");
		if (stopicId != null) {
			try {

				conn = DBConnectionLocator.getInstance().getConnection(
						Globals.DATASOURCEJNDI);
				ConnLog.open("SetAuditServlet");
				conn.setAutoCommit(false);
				BBSHandler handler = new BBSHandler(conn);
				BoardArticleUpdate update = new BoardArticleUpdate(conn);
				// UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
				Integer articleId = null;
				BbsArticleDAO dao = new BbsArticleDAO();
				// String userName = userMsgHandler.getUserName();
				// String articleName = "";
				int topicNum = 0;
				int articleNum = 0;
				List<Integer> boardList = new ArrayList<Integer>();
				for (int i = 0; i < stopicId.length; i++) {
					articleId = new Integer(stopicId[i]);
					dao = handler.setArticleView(articleId, "0");
					Integer boardid = dao.getBoardid();
					if (!boardList.contains(boardid)) {
						boardList.add(boardid);
					}
					// 如果是主贴
					if (dao.getTopic().equals("1")) {
						topicNum++;

						update.updateBoard(boardid, 1, 1);
					} // 如果是回帖
					else if (dao.getTopic().equals("0")) {
						articleNum++;
						// 更新主贴记录(回帖数量)
						update.updateArticle(dao.getReid());
						// 更新主贴记录(最后发贴)
						update.adjustArticle(dao.getReid());

						// 更新板块记录(最后发贴)
						// 更新帖子数量和主贴数量
						// update.adjustBoard(boardid);
						update.updateBoard(boardid, 0, 1);
					}
				}

				// 更新板块记录(最后发贴)
				for (Integer i : boardList) {
					update.adjustBoard(i);
				}
				// if (topicNum != 0) {
				// //更新板块记录(最后发贴)
				// // update.adjustBoard(new Integer(boardId));
				// }
				// if (articleNum != 0 || topicNum != 0) {
				// //更新帖子数量和主贴数量
				// // update.updateBoard(new Integer(boardId), topicNum,
				// topicNum +
				// articleNum);
				// }

				conn.commit();
				response.sendRedirect("ShowUnauditServlet");
			} catch (Exception e) {
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("SetAuditServlet");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
