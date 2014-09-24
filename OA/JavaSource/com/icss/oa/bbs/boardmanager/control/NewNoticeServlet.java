/*
 * Created on 2004-2-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.boardmanager.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.vo.BbsNoticeVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NewNoticeServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String noticeName = request.getParameter("noticeName");
		String noticeContent = request.getParameter("noticeContent");
		Integer boardId = new Integer(request.getParameter("boardId"));

		BbsNoticeVO vo = new BbsNoticeVO();
		List list = null;

		vo.setBoardid(boardId);
		vo.setTitle(noticeName);
		vo.setContent(noticeContent);
		vo.setNoticedate(new Long(System.currentTimeMillis()));
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("NewNoticeServlet");
			BBSHandler handler = new BBSHandler(conn);
			BbsNoticeVO tvo = new BbsNoticeVO();
			list = handler.getNotice(boardId);
			Iterator Itr = list.iterator();
			if (Itr.hasNext()) {
				tvo = (BbsNoticeVO) Itr.next();
			}

			if (list.size() == 0)
				handler.newNotice(vo);
			else
				handler.updateNotice(vo, tvo);

			request.setAttribute("boardId", boardId);
			this.forward(request, response, "/bbs/newNoticeClose.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("NewNoticeServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
