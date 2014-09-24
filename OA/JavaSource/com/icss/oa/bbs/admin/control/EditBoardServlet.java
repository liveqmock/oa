/*
 * Created on 2004-2-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.BoardArticleUpdate;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.user.control.InitializeUser;
import com.icss.oa.bbs.vo.BbsBoardaccVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.oa.bbs.vo.ManagerUserinfoVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EditBoardServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Integer boardId = new Integer(request.getParameter("boardId"));
		String boardName = request.getParameter("boardName");
		String boardDes = request.getParameter("boardDes");
		String isaudit = request.getParameter("audit");
		if ("yes".equals(isaudit)) {
			isaudit = "1";
		} else {
			isaudit = "0";
		}
		Integer areaId = new Integer(request.getParameter("areaId"));
		String managerId = request.getParameter("managerId");
		//String managerAddFlag = request.getParameter("managerAddFlag");   //已删
		String userAddFlag = request.getParameter("userAddFlag");

		HttpSession session = request.getSession();
		List managerList = (List) session.getAttribute("manager");

		List userList = (List) session.getAttribute("user");
		//------------------------------
		//BbsBoardVO boardVO = null;
		//boardVO.getBoardname()
		//------------------------------

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("EditBoardServlet");
			BBSHandler handler = new BBSHandler(conn);
			BoardArticleUpdate update = new BoardArticleUpdate(conn);
			handler.editBoard(boardId, boardName, boardDes, areaId, isaudit);
			UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
			AddressHelp adressHelp = new AddressHelp(conn);
			if (managerList != null) {
				managerList = adressHelp.getperson(managerList, request, "manager");
			}
			if (userList != null) {
				userList = adressHelp.getperson(userList, request, "user");
			}
			//1为增加,2为重设
			//if (managerAddFlag.equals("2")) {
			//	handler.delManager(boardId);
			//}
			if (managerList != null) {
				List mlist = handler.getManagerList(boardId);
				Iterator mit = mlist.iterator();
				BbsUserinfoVO userVO = null;
				Iterator it = managerList.iterator();
				while (it.hasNext()) {
					SelectOrgpersonVO selectUserVO = (SelectOrgpersonVO) it.next();
					String userId = selectUserVO.getUseruuid();
					boolean pd1 = false;
					while (mit.hasNext()) {
						ManagerUserinfoVO mVO = (ManagerUserinfoVO) mit.next();
						if (userId.equals(mVO.getUserid()))
							pd1 = true; //添加用户是否重复
					}
					if (!pd1) {
						userVO = userMsgHandler.getUserVO(userId);
						String lastip = request.getRemoteAddr();
						if (userVO == null) {
							InitializeUser initializeUser = new InitializeUser(conn);
							userVO = initializeUser.initialize(selectUserVO.getUserid(), selectUserVO.getName(), lastip);
						}
						handler.newManager(boardId, userId);
					}
				}

			}

			//1为增加,2为重设
			//if (userAddFlag.equals("2")) {
			//	handler.delUserRight(boardId);
			//}
			if (userList != null) {
				List ulist = handler.getBoardAccList(boardId);
				Iterator uit = ulist.iterator();
				BbsUserinfoVO userVO = null;
				Iterator it = userList.iterator();
				while (it.hasNext()) {
					boolean pd2 = false;
					SelectOrgpersonVO selectUserVO = (SelectOrgpersonVO) it.next();
					String userId = selectUserVO.getUseruuid();
					while (uit.hasNext()) {
						BbsBoardaccVO uVO = (BbsBoardaccVO) uit.next();
						if (userId.equals(uVO.getUserid()))
							pd2 = true;
					}
					if (!pd2) {
						userVO = userMsgHandler.getUserVO(userId);
						String lastip = request.getRemoteAddr();
						if (userVO == null) {
							InitializeUser initializeUser = new InitializeUser(conn);
							userVO = initializeUser.initialize(selectUserVO.getUseruuid(), selectUserVO.getName(), lastip);
						}
						handler.newRight(boardId, userId);
					}
				}
				//设置权限限制标志
				update.updateBoardLimited(boardId);

			}

			this.forward(request, response, "/bbs/areaAddClose.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("EditBoardServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
