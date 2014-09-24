// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ShowArticleServlet.java

package com.icss.oa.bbs.user.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.dao.BbsArticleDAO;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.vo.BbsArticleVO;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowArticleServlet extends ServletBase {

	public ShowArticleServlet() {
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String stopicId;
		Integer topicId;
		Integer boardId;
		String hitFlag;
		String isview;
		conn = null;
		List list = null;
		stopicId = request.getParameter("topicId");
		// System.err.println((new StringBuilder("!!!!stopicId="))
		// .append(stopicId).toString());
		topicId = new Integer(stopicId);
		String sboardId = request.getParameter("boardId");
		boardId = new Integer(sboardId);
		hitFlag = request.getParameter("hitFlag");
		BbsBoardVO vo = new BbsBoardVO();
		isview = request.getParameter("isview");
		com.icss.oa.bbs.vo.BbsUserinfoVO userVO = null;
		List rightList = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					"java:comp/env/ResourceOne/DataSource");
			ConnLog.open("ShowArticleServlet");
			BBSHandler handler = new BBSHandler(conn);
			
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			int day = handler.getOutDay(uuid);
			if(day > 0 ){
			request.setAttribute("day", day);
			this.forward(request, response, "/bbs/noLogin.jsp");
			}
			
			boolean a = this.getIsLock(topicId, conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			BoardArticleUpdate update = new BoardArticleUpdate(conn);
			userVO = userMsghandler.getUserVO(userMsghandler.getUserId());
			list = handler.getArticleList(topicId, isview);
			vo = handler.getBoardVO(boardId);
			String maintopic = handler.getMainTopic(topicId);
			if ("1".equals(hitFlag)) {
				update.updateArticleByHit(topicId);
			}
			List managerList = handler.getManagerList();
			Integer numperpage = new Integer(
					getInitParameter("_rowcount_per_page"));
			request.setAttribute("numperpage", numperpage);
			request.setAttribute("articleList", list);
			request.setAttribute("maintopic", maintopic);
			request.setAttribute("boardVO", vo);
			request.setAttribute("stopicId", stopicId);
			request.setAttribute("userVO", userVO);
			request.setAttribute("rightList", rightList);
			request.setAttribute("isUnlock", a);
			request.setAttribute("managerList", managerList);
			forward(request, response, "/bbs/article.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ShowArticleServlet");
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