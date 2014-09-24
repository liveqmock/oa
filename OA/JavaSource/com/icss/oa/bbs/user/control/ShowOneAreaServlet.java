/*
 * Created on 2004-12-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.user.control;

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
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsUserinfoVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ShowOneAreaServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		List boardList = null;
		List subareaList = null;
		List rightList = null;
		List managerList = null;
		BbsUserinfoVO userVO = null;

		Integer areaId = new Integer(request.getParameter("areaId"));
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowOneAreaServlet");
			BBSHandler handler = new BBSHandler(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			String userId = userMsghandler.getUserId(); //�õ���ǰ��¼�û���UUID
			userVO = userMsghandler.getUserVO(userId); //�õ�����user���ж�Ӧ�ļ�¼
			request.setAttribute("userVO", userVO);
			//�õ���ר�������еİ��
			boardList = handler.getBoardByAreaidList(areaId, "");
			subareaList = handler.getSubareaList();
			rightList = handler.getRightList(userId);
			managerList = handler.getManagerList();
			request.setAttribute("boardList", boardList);
			request.setAttribute("areaId", areaId.toString());
			request.setAttribute("rightList", rightList);
			request.setAttribute("managerList", managerList);
			request.setAttribute("serverTime", new Long(System.currentTimeMillis()));
			this.forward(request, response, "/bbs/oneArea.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowOneAreaServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
