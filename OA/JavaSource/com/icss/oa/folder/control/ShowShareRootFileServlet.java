/*
 * Created on 2004-5-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.control;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.config.Config;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderPackageVO;
import com.icss.oa.folder.vo.ShareShareaccessVO;
import com.icss.oa.util.CommUtil;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowShareRootFileServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List fileList = new ArrayList();
		;
		List folderList = null;
		List shareList = null;
		FolderPackageVO vo = null;
		String path = "";

		Integer folderId = new Integer(request.getParameter("folderId"));
		String shareFlag = request.getParameter("shareFlag");
		String accessFlag = request.getParameter("accessFlag");
		String shareName = request.getParameter("shareName");

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowShareRootFileServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			shareList = handler.getShareList(userId);
			Iterator it1 = shareList.iterator();
			String shareName1[] = new String[100];
			int shareNameOpe = 0;

			while (it1.hasNext()) {

				ShareShareaccessVO vo1 = (ShareShareaccessVO) it1.next();
				String isShareName = vo1.getFsName();
				Integer folderId1 = vo1.getFpId();
				FolderPackageVO folderVO = handler.getFolderVO(folderId1);

				int nPosition = 0;
				nPosition = isShareName.lastIndexOf(":");
				String ss = isShareName.substring(nPosition + 1);

				if ((isShareName.substring(0, nPosition)).equals(shareName))

					//System.out.println("isShareName1kkkkk"+isShareName.substring(nPosition+1));
					//System.out.println("shareName1"+shareName);
					{
					shareName1[shareNameOpe] = ss;
					vo = handler.getFolderVO(folderId1);
					fileList.add(vo);
					path = "共享文件夹:" + vo.getFpName();
					shareNameOpe++;

				}
			}
			//得到用户所占空间
			Integer fileSize = new Integer(0);
			//UserMsgHandler userHandler = new UserMsgHandler(conn);
			//String userId = userHandler.getUserId();
			List fmList = handler.getUserList(userId);
			Iterator it = fmList.iterator();
			FolderManagementVO fmvo = null;
			if (it.hasNext()) {
				fmvo = (FolderManagementVO) it.next();
			}
			if (fmvo != null) {
				fileSize = handler.contSize(fmvo.getFmId());
			}
			double userPercnet = CommUtil.getDivision(fileSize.longValue() * 100, 1024 * 1024 * Config.FOLDER_SPACE, 1);
			double userFileSize = CommUtil.getDivision(fileSize.longValue(), 1024 * 1024, 1);

			request.setAttribute("totalsize", String.valueOf(Config.FOLDER_SPACE));
			request.setAttribute("path", path);
			request.setAttribute("fileList", fileList);
			request.setAttribute("parentId", folderId.toString());
			request.setAttribute("shareFlag", shareFlag);
			request.setAttribute("accessFlag", accessFlag);
			request.setAttribute("shareName1", shareName1);

			request.setAttribute("userPercnet", new Double(userPercnet).toString());
			request.setAttribute("userSpace", new Double(userFileSize).toString());
			this.forward(request, response, "/mail/ShareFileList_Body.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ShowShareRootFileServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
