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
public class AddAddrbookFolderServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		Integer parentId = new Integer(request.getParameter("parentId"));
		String folderName = request.getParameter("folderName");
		String folderDes = request.getParameter("folderDes");

		if (folderName.equals("")) //分组名为空
			this.forward(request, response, "/addressbook/addFolder.jsp?parentId=" + parentId);
		else {
			try {
				conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("AddAddrbookFolderServlet");
				AddressbookHandler handler = new AddressbookHandler(conn);
				UserMsgHandler userHandler = new UserMsgHandler(conn);
				String userId = userHandler.getUserId();
				List abmList = handler.getUserList(userId);
				Iterator it = abmList.iterator();
				AddressbookManagerVO abmvo = null;

				if (it.hasNext()) {
					abmvo = (AddressbookManagerVO) it.next();
				}

				boolean hasSameName = handler.checkSameName(folderName, parentId, abmvo.getAbmId());
				if (hasSameName == true) //如果重名，报出错
					this.forward(request, response, "/addressbook/addFolder.jsp?parentId=" + parentId + "&folderName=" + folderName + "&folderDes" + folderDes);

				else if (hasSameName == false) { //没有重名

					AddressbookFolderVO vo = new AddressbookFolderVO();
					//添加文件夹
					vo.setAddAbfId(parentId);
					vo.setAbfName(folderName);
					vo.setAbfDescript(folderDes);
					vo.setAbfCreatetime(new Long(System.currentTimeMillis()));
					vo.setAbfUpdatetime(new Long(System.currentTimeMillis()));
					vo.setAbfFlag("1");
					vo.setAddAbmId(abmvo.getAbmId());
					handler.addFolder(vo);
					if (parentId.intValue() == 1)
						response.sendRedirect("ShowAddrbookRootFolderServlet");
					else
						response.sendRedirect("ShowAddressbookListServlet?parentId=" + parentId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				handleError(e);
			} finally {
				try {
					if (conn != null){
						conn.close();
						ConnLog.close("AddAddrbookFolderServlet");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		}
	}
}
