/*
 * Created on 2004-4-23
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
public class ReNameServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		Integer folderId = new Integer(request.getParameter("folderId"));
		Connection conn = null;
		String folderName = request.getParameter("folderName");
		String folderDes = request.getParameter("folderDes");
		String parentId = request.getParameter("parentId");
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ReNameServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
            String userId = userHandler.getUserId();
			List fmList = handler.getUserList(userId);
			Iterator it = fmList.iterator();
			FolderManagementVO fmvo = null;
			
			if (it.hasNext()) {
				fmvo = (FolderManagementVO) it.next();
			}

			boolean hasSameName = handler.checkSameName(folderName, new Integer(parentId),fmvo.getFmId());
			
			if (hasSameName == true) //如果重名，
				this.forward(request,response,"/folder/error.jsp?errormsg=重名");
            else{
			FolderPackageVO vo = new FolderPackageVO();
			vo.setFpId(folderId);
			vo.setFpName(folderName);
			vo.setFpDesc(folderDes);
			handler.updateFolder(vo);
			String nextpage = "";
		
				if (parentId.equals("1")){
					nextpage ="/servlet/ShowRootFolderServlet";
				}
				else{
					nextpage = "/servlet/ShowFileListServlet?parentId=" + parentId;
				}	
				request.setAttribute("nextpage",nextpage);
				this.forward(request, response, "/mail/MailReload.jsp");
            }

			
			
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ReNameServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}