/*
 * Created on 2004-2-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.user.control.InitializeUser;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
/** 
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NewBoardServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String boardName = request.getParameter("boardName");
		String boardDes = request.getParameter("boardDes");
		Integer areaId = new Integer(request.getParameter("areaId"));
		String audit = request.getParameter("audit");
		String permit = request.getParameter("permit");
		String isApply = request.getParameter("isApply");
		BbsBoardVO vo = new BbsBoardVO();
		vo.setBoarddes(boardDes);
		vo.setBoardname(boardName);
		vo.setAreaid(areaId);
		vo.setArticlenum(new Integer(0));

		vo.setTopicnum(new Integer(0));
		vo.setCreattime(new Long(System.currentTimeMillis()));
		vo.setIsaudit("0");
		vo.setIslimited("0");
		vo.setIsview("0");
		vo.setIswork("0");
		if (audit.equals("yes"))
			vo.setIsaudit("1");
		else
			vo.setIsaudit("0");
		if (permit.equals("yes")) //只让选中的人进入
			vo.setPermit("1");
		else
			vo.setPermit("0");
		//只让选中的人不进入

		HttpSession session = request.getSession();
		List managerList = (List) session.getAttribute("manager");
		if (managerList != null) {
			//			System.out.println("managerList======="+managerList);
			session.removeAttribute("manager");

		}
		List userList = (List) session.getAttribute("user");
		if (userList != null) {
			//			System.out.println("userList======="+userList);
			session.removeAttribute("user");
		}
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("NewBoardServlet");
			conn.setAutoCommit(false);

			BBSHandler handler = new BBSHandler(conn);
			UserMsgHandler uhandler = new UserMsgHandler(conn);
			AddressHelp adressHelp = new AddressHelp(conn);
			if (managerList != null) {
				managerList = adressHelp.getperson(managerList, request, "manager");
			}
			if (userList != null) {
				userList = adressHelp.getperson(userList, request, "user");
				//有权限限制
				vo.setIslimited("1");
			}
			if ("1".equals(isApply)) {
				//标记为是申请板块
				vo.setApplyflag("0");
				vo.setApplypersonuuid(uhandler.getUserId());
			} else {
				vo.setApplyflag("1");
			}

			UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
			Integer boardId = handler.newBoard(vo);
			if (managerList != null || userList != null) {
				if (managerList != null) {
					BbsUserinfoVO userVO = null;
					Iterator it = managerList.iterator();
					while (it.hasNext()) {
						SelectOrgpersonVO selectUserVO = (SelectOrgpersonVO) it.next();
						String userId = selectUserVO.getUseruuid();
						userVO = userMsgHandler.getUserVO(userId);
						String lastip = request.getRemoteAddr();
						if (userVO == null) {
							InitializeUser initializeUser = new InitializeUser(conn);
							userVO = initializeUser.initialize(selectUserVO.getUseruuid(), selectUserVO.getName(), lastip);
						}
						handler.newManager(boardId, userId);
					}
				}
				if (userList != null) {
					BbsUserinfoVO userVO = null;
					Iterator it = userList.iterator();
					while (it.hasNext()) {
						SelectOrgpersonVO selectUserVO = (SelectOrgpersonVO) it.next();
						String userId = selectUserVO.getUseruuid();
						userVO = userMsgHandler.getUserVO(userId);
						String lastip = request.getRemoteAddr();
						if (userVO == null) {
							InitializeUser initializeUser = new InitializeUser(conn);
							userVO = initializeUser.initialize(selectUserVO.getUseruuid(), selectUserVO.getName(), lastip);
						}
						handler.newRight(boardId, userId);
					}

				}
			} else {
				handler.newManager(boardId, userMsgHandler.getUserId());
			}
			conn.commit();

			this.forward(request, response, "/bbs/areaAddClose.jsp");

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("NewBoardServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
