/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window&gt;Pref	erences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderShareVO;
import com.icss.oa.folder.vo.FolderShareaccessVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ShareServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		Integer folderId = new Integer(request.getParameter("folderId"));
		HttpSession session = request.getSession();
		List userList = (List) session.getAttribute("user");
		//		if (userList != null) {
		//			session.removeAttribute("user");
		//		}
		String parentId = request.getParameter("parentId");
		String write = request.getParameter("write");
		String shareName = request.getParameter("shareName");
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShareServlet");
			conn.setAutoCommit(false);
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
			AddressHelp adressHelp = new AddressHelp(conn);
			
			if(handler.checkSameShareName(userMsgHandler.getUserName()+ "的共享:"+shareName)
					&&!(folderId.intValue()==handler.getFolderIDByShareName(userMsgHandler.getUserName()+"的共享:"+shareName).intValue()))
			{	this.forward(
					request,
					response,
					"/folder/error.jsp?errormsg=有重名的共享名");}
			else{
			
			FolderShareVO shareVO = null;
			Integer fsId = null;
			if (userList != null) {
				userList = adressHelp.getperson(userList, request, "user");
			}


			String userId = userMsgHandler.getUserId();
			if (userList != null) {
				//取得文件柜id
				List fmList = handler.getUserList(userId);
				Iterator fmIt = fmList.iterator();
				FolderManagementVO fmvo = null;
				if (fmIt.hasNext()) {
					fmvo = (FolderManagementVO) fmIt.next();
				}
						//FolderShareVO shareVO = null;
						List shareFolderList = handler.getShareList(folderId);
						
						//如果文件已被共享,可以直接得到fsid
						if (shareFolderList != null
							&& shareFolderList.isEmpty() == false) {
							//shareVO = (FolderShareVO) shareFolderList.get(0);
							Iterator folderListIt = shareFolderList.iterator();
							if (folderListIt.hasNext()) {
								shareVO = (FolderShareVO) folderListIt.next();
								fsId = shareVO.getFsId();
							}
						
						} else { //文件从没被共享,共享该文件
							shareVO = new FolderShareVO();
							shareVO.setFpId(folderId);
							shareVO.setFsDate(
								new Long(System.currentTimeMillis()));
							shareVO.setFmId(fmvo.getFmId());
							
							if (shareName != null) {
								shareVO.setFsName(
									userMsgHandler.getUserName()
										+ "的共享:"
										+ shareName);
							} else {
								shareVO.setFsName(
									userMsgHandler.getUserName() + "的共享:");
							}
							
							fsId = handler.addShareFolder(shareVO);
						}
						
						Iterator it = userList.iterator();
						
						while (it.hasNext()) {
							SelectOrgpersonVO selectUserVO =
								(SelectOrgpersonVO) it.next();
							String shareUserId = selectUserVO.getUseruuid();
							FolderShareaccessVO shareAccVO =
								new FolderShareaccessVO();
							shareAccVO.setFsId(fsId);
							shareAccVO.setFscPersonid(shareUserId);
							if (write.equals("yes"))
								shareAccVO.setFscAccessright("1");
							else
								shareAccVO.setFscAccessright("0");
							handler.addShareAccess(shareAccVO);
						}
				}
				
			handler.updateShareName(folderId,(userMsgHandler.getUserName()
					+ "的共享:"
					+ shareName));
//			System.out.println("parentId================" +userId);
//			System.out.println("parentId================" +write);
//			System.out.println("parentId================" +folderId);
			
			handler.updateAccess(write,handler.getShareAccessID(userId,folderId));
			}
			
			conn.commit();
			//			System.out.println("parentId================" + parentId);
			if (parentId != null) {
				if (parentId.equals("1"))
					response.sendRedirect("ShowRootFolderServlet");
				else
					response.sendRedirect(
						"ShowFileListServlet?parentId=" + parentId);
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShareServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}