/*
 * Created on 2004-12-4
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

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.multivote.vo.VoteTableVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MVotePlanArticleListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		System.out.println("进入MVotePlanArticleListServlet");
		Connection conn = null;
		List list_art = null;
		List list_item = null;
		VoteTableVO tablevo = null;
		Integer tableId=request.getParameter("tableId")==null?new Integer(-1):Integer.valueOf(request.getParameter("tableId"));
		
		System.out.println("tableId="+tableId.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			VoteLiuHandler handler = new VoteLiuHandler(conn);
			
			//得到文章的list和投票项目的list
			list_art=handler.getarticalOptionList(tableId);
			list_item=handler.getvoteItemList(tableId);
			request.setAttribute("list_art",list_art);
			request.setAttribute("list_item",list_item);
			
			//得到tablevo,并传入mVotePlanArticleList.jsp
			tablevo=handler.getTableVo(tableId);
			request.setAttribute("tablevo",tablevo);
			System.out.println("+++++++++++MVotePlanArticleListServlet++++++list_art++"+list_art.size());
			System.out.println("+++++++++++MVotePlanArticleListServlet++++++list_item++"+list_item.size());
			
		
			this.forward(request, response, "/multivote/mVotePlanArticleList.jsp?tableId="+tableId);
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
