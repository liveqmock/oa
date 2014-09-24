/*
 * Created on 2004-4-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.addressbook.admin;

import java.io.IOException;
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
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;
import com.icss.oa.bbs.handler.UserMsgHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookInitializeServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddressbookInitializeServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			list = handler.getUserList(userId);
			if (list == null || list.isEmpty()) {
				//System.out.println("list length=============");
				//添加文件柜信息
				AddressbookManagerVO vo = new AddressbookManagerVO();
				vo.setAbmCretetime(new Long(System.currentTimeMillis()));
				vo.setAbmUpdatetime(new Long(System.currentTimeMillis()));

				vo.setAbmOwner(userId);
				//vo.setFmStoretype("1");
				Integer abmId = handler.addUser(vo);
				//System.out.println("**********************"+abmId);
				AddressbookFolderVO folderVO = new AddressbookFolderVO();
				//				//添加文件夹
				//				folderVO.setFolFpId(new Integer(1));
				//				folderVO.setFpName("个人文件夹");
				//				folderVO.setFpDesc("个人根文件夹");
				//				folderVO.setFpCreatedate(new Long(System.currentTimeMillis()));
				//				folderVO.setFpIsfile("1");
				//				folderVO.setFmId(fmId);
				//				handler.addFolder(folderVO);

				//				System.out.println("fmid============="+fmId);
			}
			//System.out.println("list length============="+list.size());
			response.sendRedirect("ShowAddrbfolderListServlet");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("AddressbookInitializeServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
