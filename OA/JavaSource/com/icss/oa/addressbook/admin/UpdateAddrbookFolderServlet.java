/*
 * Created on 2004-3-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateAddrbookFolderServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		Integer parentId = new Integer(request.getParameter("parentId"));
		Integer abfId = new Integer(request.getParameter("abfid"));
		String abfName = request.getParameter("abfname");
		String abfDesc = request.getParameter("abfdesc");

		if (abfName.equals("")) //如果重名，报出错
			this.forward(request, response, "/addressbook/updateAddrbFolder.jsp?parentid=" + parentId + "&abfid=" + abfId + "&abfname=" + abfName + "&abfdesc" + abfDesc);

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UpdateAddrbookFolderServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List abmList = handler.getUserList(userId);
			Iterator it = abmList.iterator();
			AddressbookManagerVO abmvo = null;

			if (it.hasNext()) {
				abmvo = (AddressbookManagerVO) it.next();
			}

			AddressbookFolderVO vo = new AddressbookFolderVO();
			//修改
			vo.setAbfName(abfName);
			vo.setAbfDescript(abfDesc);
			vo.setAbfUpdatetime(new Long(System.currentTimeMillis()));
			handler.updateAddrbFolder(vo, abfId);
			if (parentId.intValue() == 1)
				this.forward(request, response, "/servlet/ShowAddrbookRootFolderServlet");
			else
				this.forward(request, response, "/servlet/ShowAddressbookListServlet?parentId=" + parentId);

		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("UpdateAddrbookFolderServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
