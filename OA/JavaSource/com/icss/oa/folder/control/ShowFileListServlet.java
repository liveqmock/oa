/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.folder.control;
import java.io.IOException;
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
//import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderPackageVO;
import com.icss.oa.util.CommUtil;
import com.icss.oa.config.Config;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ShowFileListServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		List fileList = null;
		List folderList = null;
		Integer parentId = new Integer(request.getParameter("parentId"));
		String shareFlag = request.getParameter("shareFlag");
		String accessFlag = request.getParameter("accessFlag");
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShowFileListServlet");
			FolderHandler handler = new FolderHandler(conn);
			fileList = handler.getFileList(parentId);
			String path = "";
			if (shareFlag == null || shareFlag.equals("0")) {
				StringBuffer bpath = new StringBuffer();
				bpath = handler.getFolderPath(parentId, bpath);
				bpath.insert(0, "/个人文件夹");
				path = bpath.toString();
			} else {
				FolderPackageVO folderVO = handler.getFolderVO(parentId);
				if(folderVO == null){
					String alert = new String("该共享文件夹可能已经删除！".getBytes("GBK"),"ISO-8859-1");
					forward(request,response,"/include/errorString.jsp?errorS="+alert);
				}
				path = "共享文件夹:" + folderVO.getFpName();
			}
			
			//得到用户所占空间
			Integer fileSize = new Integer(0);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List fmList = handler.getUserList(userId);
			Iterator it = fmList.iterator();
			FolderManagementVO fmvo = null;
			if (it.hasNext()) {
				fmvo = (FolderManagementVO) it.next();
			}
			if (fmvo != null) {
				fileSize = handler.contSize(fmvo.getFmId());
			}
			double userPercnet =
				CommUtil.getDivision(
					fileSize.longValue() * 100,
					1024 * 1024 * Config.FOLDER_SPACE,
					1);
			
			System.out.println("userPercnet==============" + userPercnet);
			double userFileSize = CommUtil.getDivision(fileSize.longValue(), 1024*1024, 1);
			
			request.setAttribute("totalsize", String.valueOf(Config.FOLDER_SPACE));
			System.out.println("totalsize:"+String.valueOf(Config.FOLDER_SPACE));
			request.setAttribute("path", path);
			request.setAttribute("fileList", fileList);
			request.setAttribute("parentId", parentId.toString());
			request.setAttribute("shareFlag", shareFlag);
			request.setAttribute("accessFlag", accessFlag);
			request.setAttribute(
				"userPercnet",
				new Double(userPercnet).toString());
			request.setAttribute(
				"userSpace",
				new Double(userFileSize).toString());
			this.forward(request, response, "/mail/NetFolder_Body.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowFileListServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
