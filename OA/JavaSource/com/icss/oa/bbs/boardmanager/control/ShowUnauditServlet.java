/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.boardmanager.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSBoardHandler;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.BBSSearchHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.oa.bbs.vo.BbsManagerVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowUnauditServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
//		List noticeList = null;
//		Integer boardId = new Integer(request.getParameter("boardId"));
		BbsBoardVO boardVO = null;
		BbsUserinfoVO userVO = new BbsUserinfoVO();
		List managerList = null;
		List rightList = null;
		List mlist = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShowUnauditServlet");
			BBSHandler handler = new BBSHandler(conn);
			BBSSearchHandler handler2 = new BBSSearchHandler(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			BBSBoardHandler bhandler = new BBSBoardHandler(conn);
			String userid = userMsghandler.getUserId();
			// 取得此帐号的所有板块
			mlist = bhandler.getMlist(userid);
			if (mlist != null && !mlist.isEmpty()) {
				
				//取得所有未审核的帖子
				list = bhandler.getUnauditList(userid);
				
//				noticeList = handler.getNotice(boardId);
//				boardVO = handler.getBoardVO(boardId);
				userVO = userMsghandler.getUserVO(userid);

//				managerList = handler.getManagerList(boardId);

				rightList = handler.getRightList(userVO.getUserid());

//				request.setAttribute("noticeList", noticeList);
				request.setAttribute("topicList", list);
				request.setAttribute("boardVO", boardVO);
				request.setAttribute("managerList", managerList);
				request.setAttribute("rightList", rightList);
				request.setAttribute("userVO", userVO);
				this.forward(request, response, "/bbs/audit.jsp");
			} else {
				PrintWriter pw = response.getWriter();
				pw.print("你无权进入此页面！");

			}
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ShowUnauditServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
