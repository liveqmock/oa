/*
 * Created on 2004-4-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.intendwork.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.intendwork.handler.Base64Util;
import com.icss.oa.intendwork.handler.IntendWork;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DoWorkServlet extends ServletBase {

	protected void performTask( 
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try { 
				conn = getConnection(Globals.DATASOURCEJNDI);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("ȡ�����ݿ����Ӵ���");
			}
			
			
			String url = request.getParameter("url");
			String fromIds = request.getParameter("fromIds");
			url = url.replace('*', '&');
			String workid = request.getParameter("workid");
			//System.out.println("*************+"+url);

			IntendWork intendWork = new IntendWork(conn);
			intendWork.deleteWork(new Integer(workid));
			
			//�����޸�,����Ǵ�ids�������Ĵ�����Ϣurl ���url��ids��֮ǰ�Ǿ���base64�����,������Ҫ����.
			if(fromIds!=null&&fromIds.equals("true")){
				url = Base64Util.decode(url);
				url += url.indexOf("?") == -1? "?fromIds=true" : "&fromIds=true";
			}
			
			
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e); 
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				//throw new RuntimeException("���ݿ����ӹرմ���");
			}
		}
	}
}