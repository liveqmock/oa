/*
 * Created on 2004-4-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.control;
import java.io.IOException;
import com.icss.j2ee.util.PageScrollUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.util.*;
import com.icss.oa.config.Config;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowRootFolderServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
	
		Connection conn = null;
		List fileList = null;
		//List folderList = null;
		Integer parentId = new Integer(1);
		Integer fileSize = new Integer(0);
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowRootFolderServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List fmList = handler.getUserList(userId);
				     
			Iterator it = fmList.iterator();
			FolderManagementVO fmvo = null;
			if (it.hasNext()) {
				fmvo = (FolderManagementVO) it.next();
			}

			if (fmvo != null) {
				fileList = handler.getFolderList(parentId, fmvo.getFmId());
				fileSize = handler.contSize(fmvo.getFmId());
			} else {
				fileList = new ArrayList();
			}
			String path = "/我的文件夹";
			//用户空间
			double userPercnet =
				CommUtil.getDivision(
					fileSize.longValue() * 100,
					1024 * 1024 * Config.FOLDER_SPACE,
					1);
			double userFileSize =
				CommUtil.getDivision(fileSize.longValue(), 1024 * 1024, 1);

			request.setAttribute("totalsize", String.valueOf(Config.FOLDER_SPACE));
			System.out.println("totalsize:"+String.valueOf(Config.FOLDER_SPACE));
			request.setAttribute("path", path);
			request.setAttribute("fileList", fileList);
			request.setAttribute("parentId", "1");
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
					ConnLog.close("ShowRootFolderServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
