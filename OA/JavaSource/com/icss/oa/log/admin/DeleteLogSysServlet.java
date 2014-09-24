/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.log.admin;

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
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.vo.LogSysVO;


/**
 *É¾³ýÎÄ¼þ
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteLogSysServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		String sysid = null;
		String[] selectsysid = request.getParameterValues("selectsysid");

		String parentId = request.getParameter("parentId")==null?"-1":request.getParameter("parentId");
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			conn.setAutoCommit(false);
			LogHandler handler = new LogHandler(conn);

			LogSysVO vo = new LogSysVO();
			//String parentLevel = null;

			for (int i = 0; i < selectsysid.length; i++) {
				sysid = selectsysid[i];
				System.out.println("+++++++++DeleteLogSysServlet++++++++++"+sysid);
				handler.delLogSys(new Integer(sysid));
			}
			conn.commit();
			response.sendRedirect(
					"ShowSysMsgServlet?parentId=" + parentId);

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
