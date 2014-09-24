/*
 * Created on 2007-6-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.multivote.admin;

import java.io.IOException;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.votebbs.handler.BbsVoteHandler;


/**
 * @author liupei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MVotePlanManagerListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List list = null;
		
		request.setCharacterEncoding("GBK");
			
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			VoteLiuHandler handler = new VoteLiuHandler(conn);
			
			//得到显示列表
			list=handler.getPlanManagerList();
			request.setAttribute("list",list);
			
			System.out.println("MVotePlanManagerservlet已经取得list");
			System.out.println("进入稿件投票的管理页面list.size()="+list.size());	
			
			forward(request, response, "/multivote/mVotePlanList.jsp?");

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	

}
