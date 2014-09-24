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

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MVoteTableServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		System.out.println("进入MVoteTableServlet");
		Connection conn = null;
		//List list_art = null;
		//List list_item = null;
		Integer tableId=null;
		Integer planPlanid=request.getParameter("planPlanid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planPlanid"));
		System.out.println("planPlanid="+planPlanid.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			VoteLiuHandler handler = new VoteLiuHandler(conn);
//			System.out.println("+++++++++++ArticleOptionListServlet++++++++"+mainid);
			//是否有投票表样
			if(handler.hasVoteTable(planPlanid)){//如果有表样
//				得到表样的tableId值
				tableId = handler.getVoteTableId(planPlanid);
				//得到文章的list和投票项目的list
				//list_art=handler.getarticalOptionList(tableId);
				//list_item=handler.getvoteItemList(tableId);
				//request.setAttribute("list_art",list_art);
				//request.setAttribute("list_item",list_item);	
				//System.out.println("+++++++++++MVoteTableServlet++++++list_art++"+list_art.size());
				//System.out.println("+++++++++++MVoteTableServlet++++++list_item++"+list_item.size());
				this.forward(request, response, "/servlet/MVotePlanArticleListServlet?tableId="+tableId);	
				//this.forward(request, response, "/multivote/mVotePlanArticleList.jsp?tableId="+tableId);
			}else{
				//生成新表样
				this.forward(request, response, "/servlet/MVoteAddTableServlet?planPlanid="+planPlanid);
			}
			
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
