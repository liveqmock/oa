/*
 * Created on 2004-2-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.user.control;

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
public class ShowNoticeServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Integer boardId = new Integer(request.getParameter("boardId"));
		List list = null;
		BbsNoticeVO vo = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowNoticeServlet");
			BBSHandler handler = new BBSHandler(conn);
			list = handler.getNotice(boardId);
			if (list.size() > 0) {
				Iterator itr = list.iterator();
				if (itr.hasNext()) {
					vo = (BbsNoticeVO) itr.next();
				}
			}
			request.setAttribute("vo", vo);
			this.forward(request, response, "/bbs/showNotice.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowNoticeServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
