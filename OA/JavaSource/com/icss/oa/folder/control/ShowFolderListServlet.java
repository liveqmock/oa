/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.folder.control;

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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */

public class ShowFolderListServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		List shareList = null;
		List treeList = null;
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShowFolderListServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			
			list = handler.getFolderList(userId);
			shareList = handler.getShareList(userId);
			//			System.out.println("shareList============="+shareList);
			//			if (shareList != null) {
			//				Iterator shareIt = shareList.iterator();
			//				List shareFolderList = new ArrayList();
			//				while (shareIt.hasNext()) {
			//					ShareShareaccessVO shareVO =
			//						(ShareShareaccessVO) shareIt.next();
			//					//取得共享文件夹目录list
			//					List folderList =
			//						handler.getFolderListById(shareVO.getFpId());
			//					//添加根节点
			//					FolderPackageVO folderVO =
			//						handler.getFolderVO(shareVO.getFpId());
			//					if (shareVO.getFsName() != null) {
			//						folderVO.setFpName(shareVO.getFsName());
			//					}
			//					folderVO.setFolFpId(new Integer(-2));
			//					folderList.add(folderVO);
			//					//设置vo
			//					ShareListVO shareListVO = new ShareListVO();
			//					shareListVO.setList(folderList);
			//					shareListVO.setShareAccess(shareVO.getFscAccessright());
			//					shareFolderList.add(shareListVO);
			//
			//				}
			//				request.setAttribute("shareFolderList", shareFolderList);
			//			}
			if (list != null && list.size() > 0) {
				request.setAttribute("hasFolder", "yes");
			} else {
				request.setAttribute("hasFolder", "no");
			}
			
			if (shareList != null && shareList.size() > 0) {
				request.setAttribute("hasShare", "yes");
			} else {
				request.setAttribute("hasShare", "no");
			}
			request.setAttribute("folderList", treeList);
			this.forward(request, response, "/mail/FileFolderTree.jsp");

		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowFolderListServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
