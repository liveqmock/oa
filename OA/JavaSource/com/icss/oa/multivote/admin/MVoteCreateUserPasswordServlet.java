/*
 * Created on 2007-6-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.multivote.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VotePersonVO;




/**
 *É¾³ý¼Æ»®
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MVoteCreateUserPasswordServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		
		
		//Integer planPlanid = Integer.valueOf(request.getParameter("planPlanid"));
		Integer planid = request.getParameter("planid")==null?(new Integer(-1)):(Integer.valueOf(request.getParameter("planid")));
		
		System.out.println("++++++MVoteCreateUserPasswordServlet++++++++++planid="+planid.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			
			VoteHandler handler = new VoteHandler(conn);
			List list=null;
			list=handler.getUserManagerList();
			String	password="";
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				VotePersonVO vo=(VotePersonVO) itr.next();
				password=handler.creatRandomString();
				vo.setPassword(password);
				handler.updatePersonVO(vo);
			}
			handler.creatRandomString();
			
			// this.forward(request, response, "/servlet/FoUserManagerListServlet?planid="+planid);
			response.sendRedirect(request.getContextPath()+"/servlet/MVoteUserManagerListServlet?planid="+planid);

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
