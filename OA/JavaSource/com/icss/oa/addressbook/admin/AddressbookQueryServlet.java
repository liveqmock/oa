/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.addressbook.admin;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;
import com.icss.oa.bbs.handler.UserMsgHandler;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class AddressbookQueryServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List fileList = null;
		List folderList = null;
		List addressbookList = null;
		Integer parentId = new Integer(request.getParameter("queryparentId"));
		String queryname = request.getParameter("queryname");
		String querycompany = request.getParameter("querycompany");
		String querycompanyaddress = request.getParameter("querycompanyaddress");
		String queryfamilyaddress = request.getParameter("queryfamilyaddress");
		String querycompanytel = request.getParameter("querycompanytel");
		String queryfamilytel = request.getParameter("queryfamilytel");
		String querycellphone = request.getParameter("querycellphone");
		String queryemail = request.getParameter("queryemail");
		String querymemo = request.getParameter("querymemo");
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddressbookQueryServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);
			fileList = handler.getFileList(parentId);
			String path = "/搜索我的通讯录";

			//	StringBuffer bpath = new StringBuffer();
			//	bpath = handler.getFolderPath(parentId, bpath);
			//	bpath.insert(0, "");
			//	path = bpath.toString();

			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List abmList = handler.getUserList(userId);
			Iterator it = abmList.iterator();
			AddressbookManagerVO abmvo = null;
			if (it.hasNext()) {
				abmvo = (AddressbookManagerVO) it.next();
			}
			if (abmvo != null) {
				addressbookList =
					handler.getUserSearchList(
						abmvo.getAbmId(),
						queryname,
						querycompany,
						querycompanyaddress,
						queryfamilyaddress,
						querycompanytel,
						queryfamilytel,
						querycellphone,
						queryemail,
						querymemo);
				//fileSize = handler.contSize(fmvo.getFmId());

			} else {
				addressbookList = new ArrayList();
			}

			request.setAttribute("path", path);
			request.setAttribute("fileList", addressbookList);
			request.setAttribute("parentId", "1");

			this.forward(request, response, "/addressbook/addressbookList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("AddressbookQueryServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
