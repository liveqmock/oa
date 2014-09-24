/*
 * Created on 2004-4-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.addressbook.admin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowAddrbookRootFolderServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List addressbookList = null;
		List folderList = null;
		List abmList = null;
		Iterator it = null;
		Integer parentId = new Integer(1);
		//Integer fileSize = new Integer(0);
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowAddrbookRootFolderServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			abmList = handler.getUserList(userId);

			it = abmList.iterator();

			AddressbookManagerVO abmvo = null;
			if (it.hasNext()) {
				abmvo = (AddressbookManagerVO) it.next();
			}

			if (abmvo != null) {
				addressbookList = handler.getAddrbFolderList(parentId, abmvo.getAbmId());
			} else {
				addressbookList = new ArrayList();
			}

			String path = "/我的通讯录";

			request.setAttribute("path", path);
			request.setAttribute("fileList", addressbookList);
			request.setAttribute("parentId", "1");

			this.forward(request, response, "/addressbook/addressbookList.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowAddrbookRootFolderServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
