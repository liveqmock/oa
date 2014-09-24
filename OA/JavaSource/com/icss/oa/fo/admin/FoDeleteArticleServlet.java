/*
 * Created on 2007-6-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.fo.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.log.handler.LogHandler;



/**
 *删除计划
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FoDeleteArticleServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		
		
		//Integer planPlanid = Integer.valueOf(request.getParameter("planPlanid"));
		Integer artId = request.getParameter("artId")==null?(new Integer(-1)):(Integer.valueOf(request.getParameter("artId")));
		
		System.out.println("++++++FoDeleteArticleServlet++++++++++artId="+artId.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			
			FoLiuHandler handler = new FoLiuHandler(conn);
			
			
			//删除计划记录
			handler.deleteArticleByArtid(artId);
			 this.forward(request, response, "/servlet/FoPlanArticalListServlet");
			//response.sendRedirect("ArticalOptionListServlet?mainid=" + mainid);

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
