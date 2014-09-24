/*
 * Created on 2004-5-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
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

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderTreeVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class LoadTreeXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		StringBuffer outSB = new StringBuffer();

		outSB.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List list = null;
		List treeList = null;
		Integer folderId = new Integer(request.getParameter("folderId"));
		String accessFlag = request.getParameter("accessFlag");
		String shareFlag = request.getParameter("shareFlag");
		if (accessFlag == null) {
			accessFlag = new String("1");
		}
		if (shareFlag == null) {
			shareFlag = new String("0");
		}
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("LoadTreeXmlServlet");
			FolderHandler handler = new FolderHandler(conn);
			if (folderId.intValue() == 1) {
				
				UserMsgHandler userHandler = new UserMsgHandler(conn);
				String userId = userHandler.getUserId();
				List fmList = handler.getUserList(userId);
				Iterator fit = fmList.iterator();
				FolderManagementVO fmvo = null;
				if (fit.hasNext()) {
					fmvo = (FolderManagementVO) fit.next();
				}
				if (fmvo != null) {

					list =
							handler.getFolderList(new Integer(1), fmvo.getFmId());
				}
			} 	else {
					list = 	handler.getFileList(folderId);
			}
			treeList = handler.buildXloadTreeList(list);

			Iterator it = treeList.iterator();
			
			while (it.hasNext()) {
				
				FolderTreeVO vo = (FolderTreeVO) it.next();
				if (vo.getFolderPackageVO().getFpIsfile().equals("1")) {
					String isName = vo.getFolderPackageVO().getFpName();
					outSB
						.append("<tree text=\"")
						.append(
							new String(isName.getBytes("GB2312"), "ISO8859_1"))
						.append("\"");
					
					if (vo.getHasChild()) {
						outSB
							.append(" src=\"../servlet/LoadTreeXmlServlet.xml?folderId=")
							.append(vo.getFolderPackageVO().getFpId())
							.append("&amp;accessFlag=")
							.append(accessFlag)
							.append("&amp;shareFlag=")
							.append(shareFlag)
							.append("\"");
					}
					
					outSB.append(
						" action=\"ShowFileListServlet?parentId="
							+ vo.getFolderPackageVO().getFpId()
							+ "&amp;accessFlag="
							+ accessFlag
							+ "&amp;shareFlag="
							+ shareFlag
							+ "\"");
					
					if (shareFlag.equals("0") && vo.getIsShare()) {
						outSB.append(
							" icon=\"../folder/tree/images/shareClose.gif\"");
						outSB.append(
							" openIcon=\"../folder/tree/images/shareOpen.gif\"");
					}
					outSB.append("/>");
				}
			}
			outSB.append("</tree>");
			System.out.println("outSB=========>" + outSB);
			response.setContentType("text/xml");
			response.getWriter().write(outSB.toString());

		} catch (Exception e) {
			System.out.println("errormsg==========");
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("LoadTreeXmlServlet");
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