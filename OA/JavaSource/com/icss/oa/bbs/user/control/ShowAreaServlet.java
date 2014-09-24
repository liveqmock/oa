/*
 * Created on 2003-12-26
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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowAreaServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List boardList = null;
		List subareaList = null;
		List rightList = null;
		List managerList = null;
		BbsUserinfoVO userVO = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowAreaServlet");
			BBSHandler handler = new BBSHandler(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			//			String userid = userMsghandler.getUser();
			//			boolean ispublic;
			//			ispublic = handler.isPublicUserid(userid);
			//			if(!ispublic){  //���ǹ����˺�
			String userId = userMsghandler.getUserId(); //�õ���ǰ��¼�û���UUID
			userVO = userMsghandler.getUserVO(userId); //�õ�����user���ж�Ӧ�ļ�¼
			request.setAttribute("userVO", userVO);

			//	boardList = handler.getMulitBoardList();
			boardList = handler.getBoardList();
			subareaList = handler.getSubareaList();
			rightList = handler.getRightList(userId);
			managerList = handler.getManagerList();
			request.setAttribute("boardList", boardList);
			request.setAttribute("subareaList", subareaList);
			request.setAttribute("rightList", rightList);
			request.setAttribute("managerList", managerList);
			request.setAttribute("serverTime", new Long(System.currentTimeMillis()));
			this.forward(request, response, "/bbs/index.jsp");
			//			}else{   //�ù����˺ŵ�¼
			//				this.forward(request,response,"/bbs/loginDeny.jsp");				
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowAreaServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
