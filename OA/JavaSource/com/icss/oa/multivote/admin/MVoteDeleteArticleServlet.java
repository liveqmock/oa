/*
 * Created on 2007-6-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.multivote.admin;

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
import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.log.handler.LogHandler;



/**
 *ɾ��article
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MVoteDeleteArticleServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		
	
		Integer artId = request.getParameter("artId")==null?(new Integer(-1)):(Integer.valueOf(request.getParameter("artId")));
		Integer tableId = request.getParameter("tableId")==null?(new Integer(-1)):(Integer.valueOf(request.getParameter("tableId")));
		System.out.println("++++++MVoteDeleteArticleServlet++++++++++artId="+artId.toString());
		System.out.println("++++++MVoteDeleteArticleServlet++++++++++tableId="+tableId.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			
			VoteLiuHandler handler = new VoteLiuHandler(conn);
			
			//ɾ��article��¼
			handler.deleteArticleByArtid(artId);

			 this.forward(request, response, "/servlet/MVoteUpdateArticleGetArticleListServlet?tableId="+tableId);
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