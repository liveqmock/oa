/*
 * Created on 2004-4-5
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.userinfo.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsUserinfoVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EditUserInfoServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String userId = request.getParameter("userId");
		String mail = request.getParameter("mail");
		String homePage = request.getParameter("homePage");
		String face = request.getParameter("face");
		System.out.println("the face is:" + face);
		String underWrite = request.getParameter("underWrite");
		String oicq = request.getParameter("oicq");
		String currUserId = request.getParameter("currUserId");
		BbsUserinfoVO vo = new BbsUserinfoVO();
		vo.setUserid(userId);
		if (!mail.equals(""))
			vo.setMail(mail);
		if (!homePage.equals(""))
			vo.setHomepage(homePage);
		if (!underWrite.equals(""))
			vo.setUnderwrite(underWrite);
		if (!oicq.equals(""))
			vo.setOicq(oicq);
		vo.setUserpic(face);
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("EditUserInfoServlet");
			UserMsgHandler handler = new UserMsgHandler(conn);
			handler.updateUserBaseInfo(vo);
			List list = handler.getUserListById(userId);
			//			String currUserId = handler.getUserId();
			//			request.setAttribute("userList", list);
			//			request.setAttribute("currUserId",currUserId);
			//			this.forward(request, response, "/jsp/userMsg.jsp");
			response.sendRedirect("ShowUserMsgServlet?userId=" + userId + "&currUserId=" + currUserId);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("EditUserInfoServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
