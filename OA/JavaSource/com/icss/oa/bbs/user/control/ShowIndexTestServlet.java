/*
 * Created on 2004-12-4
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
public class ShowIndexTestServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		List topicList = null;
		BbsUserinfoVO userVO = null;
		try {
			System.err.println("--------- in showindextestservlet ---------");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			System.err.println("--------- conn is "+conn);
			ConnLog.open("ShowIndexServlet");
			BBSHandler handler = new BBSHandler(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			
			String userId = userMsghandler.getUserId(); //得到当前登录用户的UUID
			System.err.println("-------- userid is "+userId);
			userVO = userMsghandler.getUserVO(userId); //得到其在user表中对应的记录
			System.err.println("-------- user vo is "+userVO);
			
			//得到显示列表
			System.out.println("------------ person uuid is "+userVO.getUserid());
			topicList = handler.getAccList(Integer.valueOf(userVO.getUserid()));//.getNewTopicList(userVO.getUserid());
			System.err.println("----------- topic list size is "+topicList.size());
			
			request.setAttribute("topicList", topicList);
			
			this.forward(request, response, "/bbs/privalIndexTest.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowIndexServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
