/*
 * Created on 2004-4-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

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
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderPackageVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MoveFolderServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		//response.setContentType("text/html;charset=GBK");
		Integer targetFolderId =
			new Integer(request.getParameter("targetFolderId"));
		//		HttpSession session = request.getSession(true);
		//		String[] folderId = (String[]) session.getAttribute("folderId");
		//		session.removeAttribute("targetFolderId");
		String sfolderId = request.getParameter("folderId");
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("MoveFolderServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List fmList = handler.getUserList(userId);
			Iterator fit = fmList.iterator();
			FolderManagementVO fmvo = null;
			if (fit.hasNext()) {
				fmvo = (FolderManagementVO) fit.next();
			}
			List folderId = handler.sringToList(sfolderId);

			for (int i = 0; i < folderId.size(); i++) {
				int errorFlag = 0;
				Integer sourceFolderId =
					new Integer(folderId.get(i).toString());

				FolderPackageVO sourceFolderVO =
					handler.getFolderVO(sourceFolderId);

				boolean hasSameName =
					handler.checkSameName(
						sourceFolderVO.getFpName(),
						targetFolderId,
						fmvo.getFmId());
				if (hasSameName == true) //如果重名，报出错
				{
					errorFlag = 1;
					this.forward(
						request,
						response,
						"/folder/error.jsp?errormsg=contain same name file");
				} else if (
					targetFolderId.equals(sourceFolderId)) { //如果移动到文件夹与原文件夹相同
					{
						errorFlag = 1;
						this.forward(
							request,
							response,
							"/folder/error.jsp?errormsg=contain same folder");
					}
				} else {
					//如果移动到文件夹是原文件夹子文件夹
					List folderList = handler.getFolderListById(sourceFolderId);
					Iterator it = folderList.iterator();
					while (it.hasNext()) {
						FolderPackageVO vo = (FolderPackageVO) it.next();
						if (vo.getFpId().equals(targetFolderId)) {
							errorFlag = 1;
							break;
						}
					}
					if (errorFlag == 1)
						this.forward(
							request,
							response,
							"/folder/error.jsp?errormsg=移动的文件夹是原文件夹子文件夹");
				}
				if (errorFlag == 0)
					handler.updateFolder(sourceFolderId, targetFolderId);
				//				if (targetFolderId.intValue() == 1)
				//					response.sendRedirect("ShowRootFolderServlet");
				//				else
				//					response.sendRedirect(
				//						"ShowFileListServlet?parentId=" + targetFolderId);

			}

			this.forward(request, response, "/mail/reload.jsp");

		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("MoveFolderServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
