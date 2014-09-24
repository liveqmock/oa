/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.fo.admin;

import java.io.IOException;
import java.sql.Connection;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.fo.handler.FoLiuHandler;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FoPlanArticalListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		System.out.println("进入FoPlanArticalListServlet");
		Connection conn = null;
		List list = null;
		Integer planPlanid=request.getParameter("planPlanid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planPlanid"));
		System.out.println("planPlanid="+planPlanid.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			FoLiuHandler handler = new FoLiuHandler(conn);
//			System.out.println("+++++++++++ArticleOptionListServlet++++++++"+mainid);
			//得到文章显示列表
			list=handler.getarticalOptionList(planPlanid);
			request.setAttribute("list",list);
				
			System.out.println("+++++++++++FoPlanArticalListServlet++++++list++"+list.size());
				
			this.forward(request, response, "/fo/FoPlanArticleList.jsp?planPlanid="+planPlanid);
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
