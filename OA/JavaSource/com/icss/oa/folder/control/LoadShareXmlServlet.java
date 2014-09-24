/*
 * Created on 2004-5-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderPackageVO;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.vo.ShareShareaccessVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoadShareXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		Integer folderId = new Integer(request.getParameter("folderId"));
		String accessFlag = request.getParameter("accessFlag");
		String shareName1 = request.getParameter("shareName");
		String entrance = request.getParameter("entrance");

		StringBuffer outSB = new StringBuffer();
		outSB.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		outSB.append("<tree>");
		Connection conn = null;

		List shareList = null;
		List treeList = null;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("LoadShareXmlServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			shareList = handler.getShareList(userId);
			Iterator it = shareList.iterator();

			//FolderPackageVO folderVO = handler.getFolderVO(folderId);

			while (it.hasNext()) {
				ShareShareaccessVO vo = (ShareShareaccessVO) it.next();
				String isShareName = vo.getFsName();
				Integer folderId1 = vo.getFpId();
				FolderPackageVO folderVO = handler.getFolderVO(folderId1);

				int nPosition = 0;
				nPosition = isShareName.lastIndexOf(":");

				if (folderVO.getFpIsfile().equals("1")) {
					String isName = folderVO.getFpName();

					boolean Douflag = false;
					if (!(shareName1)
						.equals(isShareName.substring(0, nPosition))) {
						Douflag = true;
					}
					if (Douflag) {
						continue;
					}

					outSB
						.append("<tree text=\"")
						.append(
							new String(
								(
									isShareName.substring(
										nPosition + 1)).getBytes(
									"GB2312"),
								"ISO8859_1"))
						.append("\"");

					if (handler.hasChild(folderVO.getFpId())) {
						System.out.println(
							"*****folderID****q    " + folderVO.getFpId());

						outSB
							.append(" src=\"../servlet/LoadTreeXmlServlet.xml?folderId=")
							.append(folderVO.getFpId())
							.append("&amp;accessFlag=")
							.append(vo.getFscAccessright())
							.append("&amp;shareFlag=")
							.append("1")
							.append("\"");
					}

					outSB.append(
						" action=\"ShowFileListServlet?parentId="
							+ folderVO.getFpId()
							+ "&amp;accessFlag="
							+ vo.getFscAccessright()
							+ "&amp;shareFlag=1"
							+ "&amp;entrance=kk"
							+ "\"");
					outSB.append("/>");
				}
			}
			outSB.append("</tree>");
			
			response.setContentType("text/xml");
			response.getWriter().write(outSB.toString());
			

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("LoadShareXmlServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
	}

	protected void performTask(
		HttpServletRequest arg0,
		HttpServletResponse arg1)
		throws ServletException, IOException {
	}
}