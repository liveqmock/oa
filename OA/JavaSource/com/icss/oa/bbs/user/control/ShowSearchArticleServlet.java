/*
 * Created on 2004-4-8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.user.control;

import java.io.IOException;
import java.util.*;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.dao.BbsArticleDAO;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.BoardArticleUpdate;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsArticleVO;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowSearchArticleServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		// Integer topicId = new Integer(request.getParameter("topicId"));
		String stopicId = request.getParameter("topicId");
		Integer topicId = new Integer(stopicId);
		String boardId = request.getParameter("boardId");
		String hitFlag = request.getParameter("hitFlag");
		BbsBoardVO vo = new BbsBoardVO();
		String isview = request.getParameter("isview");
		BbsUserinfoVO userVO = null;
		List rightList = new ArrayList();
		String userId = request.getParameter("userId");
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShowSearchArticleServlet");
			BBSHandler handler = new BBSHandler(conn);
			BoardArticleUpdate update = new BoardArticleUpdate(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			String maintopic = handler.getMainTopic(topicId);
			userVO = userMsghandler.getUserVO(userId);
			list = handler.getArticleList(topicId, isview);
			rightList = handler.getRightList(userId);
			vo = handler.getBoardVO(new Integer(boardId));
			boolean a = this.getIsLock(topicId, conn);
			List managerList = handler.getManagerList();

			if (hitFlag.equals("1"))
				update.updateArticleByHit(topicId);
			Integer numperpage = new Integer(this
					.getInitParameter("_rowcount_per_page"));
			// request.setAttribute("numperpage", numperpage);
			// request.setAttribute("maintopic", maintopic);
			// request.setAttribute("articleList", list);
			// request.setAttribute("boardVO", vo);
			// request.setAttribute("userVO", userVO);
			// request.setAttribute("rightList", rightList);
			//			
			request.setAttribute("numperpage", numperpage);
			request.setAttribute("articleList", list);
			request.setAttribute("maintopic", maintopic);
			request.setAttribute("boardVO", vo);
			request.setAttribute("stopicId", stopicId);
			request.setAttribute("userVO", userVO);
			request.setAttribute("rightList", rightList);
			request.setAttribute("isUnlock", a);
			request.setAttribute("managerList", managerList);

			this.forward(request, response, "/bbs/article.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ShowSearchArticleServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private boolean getIsLock(Integer articleid, Connection conn)
			throws DAOException {
		boolean a = false;
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setArticleid(articleid);
		factory.setDAO(dao);
		List list = factory.find(new BbsArticleVO());
		if (list != null && !list.isEmpty()) {
			BbsArticleVO vo = (BbsArticleVO) list.get(0);
			String b = vo.getArticlelock();
			if (b != null && "0".equals(b))
				a = true;
		}

		return a;

	}
}
