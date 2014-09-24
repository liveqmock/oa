/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.votebbs.handler.BbsVoteHandler;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ArticleOptionListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
//		System.out.println("+++++++++++ArticleOptionListServlet++++++++");
		Connection conn = null;
		List list = null;
		Integer mainid=request.getParameter("mainid")==null?new Integer(-1):Integer.valueOf(request.getParameter("mainid"));
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
//			System.out.println("+++++++++++ArticleOptionListServlet++++++++"+mainid);
			//得到显示列表
			list=handler.getarticalOptionList(mainid);
			request.setAttribute("list",list);
				
//			System.out.println("+++++++++++ArticleOptionListServlet++++++list++"+list.size());
				
			this.forward(request, response, "/votebbs/option_manager.jsp");
//			
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
