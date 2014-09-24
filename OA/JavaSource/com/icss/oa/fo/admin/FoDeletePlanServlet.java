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
 *ɾ���ƻ�
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FoDeletePlanServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		
		
		//Integer planPlanid = Integer.valueOf(request.getParameter("planPlanid"));
		Integer planPlanid = request.getParameter("planPlanid")==null?(new Integer(0)):(Integer.valueOf(request.getParameter("planPlanid")));
		
		System.out.println("++++++DeletePlanServlet++++++++++planPlanid="+planPlanid.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			
			FoLiuHandler handler = new FoLiuHandler(conn);
			//�ж��Ƿ�ӵ������ѡ����Ϣ
			if(handler.hasArticleOptions(planPlanid)){
				System.out.println("�˱���ӵ���ӱ������¼");
				handler.deleteArtical(planPlanid);
			}
			//ɾ���ƻ���¼
			handler.deletePlan(planPlanid);
			 this.forward(request, response, "/servlet/FoPlanManagerListServlet");
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
