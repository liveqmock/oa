/*
 * Created on 2004-3-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.log.admin;

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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.vo.LogSysVO;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookContentVO;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddLogSysServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;

		Integer parentId = request.getParameter("parentId")==null?new Integer(-1):new Integer(request.getParameter("parentId"));
		String sysname = request.getParameter("sysname");
		String syscode = request.getParameter("syscode");
		String sysorder = request.getParameter("sysorder");
		String sysmemo = request.getParameter("sysmemo");
		LogSysVO	logsysvo=new LogSysVO();
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);

			LogHandler handler = new LogHandler(conn);
			//if(!new Integer(-1).equals(parentId)){
			logsysvo.setLogSysId(parentId);
			//}
			logsysvo.setSysName(sysname);
			logsysvo.setSysCode(syscode);
			logsysvo.setSysOrder(sysorder);
			logsysvo.setSysMemo(sysmemo);
			handler.addSys(logsysvo);
			this.forward(request, response, "/servlet/ShowSysMsgServlet?parentId="+parentId);
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
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
