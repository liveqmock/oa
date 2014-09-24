/*
 * Created on 2004-2-18
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
import com.icss.oa.bbs.vo.BbsBoardVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowSingleArticleServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		Integer articleId = new Integer(request.getParameter("articleId"));
		String boardId = request.getParameter("boardId");
		String topicId = request.getParameter("topicId");
		BbsBoardVO vo = new BbsBoardVO();
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowSingleArticleServlet");
			BBSHandler handler = new BBSHandler(conn);
			list = handler.getArticleListById(articleId);
			vo = handler.getBoardVO(new Integer(boardId));
			request.setAttribute("articleList", list);
			request.setAttribute("boardVO", vo);
			this.forward(request, response, "/bbs/editArticle.jsp?topicId=" + topicId);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowSingleArticleServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
