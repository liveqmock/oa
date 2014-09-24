/*
 * Created on 2004-2-25
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
import com.icss.oa.bbs.handler.BBSSearchHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;

		Integer boardId = new Integer(request.getParameter("boardId"));
		String articleMsg = request.getParameter("articleMsg");
		String order = request.getParameter("order");
		int time = new Integer(request.getParameter("_time")).intValue();
		String primeFlag = request.getParameter("primeFlag");
		String manageFlag = request.getParameter("manageFlag");
		BbsBoardVO boardVO = null;
		BbsUserinfoVO userVO = new BbsUserinfoVO();
		List managerList = null;
		List rightList = null;

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SearchServlet");
			BBSHandler handler = new BBSHandler(conn);
			List noticeList = handler.getNotice(boardId);

			BBSSearchHandler searchHandler = new BBSSearchHandler(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			boardVO = handler.getBoardVO(boardId);
			userVO = userMsghandler.getUserVO(userMsghandler.getUserId());

			managerList = handler.getManagerList(boardId);

			list = searchHandler.getTopicList(boardId, articleMsg, order, time, primeFlag);

			rightList = handler.getRightList(userVO.getUserid());
			request.setAttribute("noticeList", noticeList);
			request.setAttribute("topicList", list);
			request.setAttribute("boardVO", boardVO);
			request.setAttribute("userVO", userVO);
			request.setAttribute("managerList", managerList);
			request.setAttribute("rightList", rightList);

			//order ”√”⁄≈≈–Ú
			if (manageFlag == null)
				this.forward(request, response, "/bbs/topic.jsp");
			else
				this.forward(request, response, "/bbs/boardManage.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SearchServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
