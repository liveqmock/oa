// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   AddFolderServlet.java

package com.icss.oa.folder.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderPackageVO;
import java.io.*;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFolderServlet extends ServletBase {

	public AddFolderServlet() {
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Integer parentId = new Integer(request.getParameter("parentId"));
		String folderName = request.getParameter("folderName");
		String folderDes = request.getParameter("folderDes");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("/cc.txt")));
		if (folderName.equals(""))
			forward(request, response, "/folder/addFolder.jsp?parentId="
					+ parentId + "&folderName=" + folderName + "&folderDes"
					+ folderDes);
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					"java:comp/env/ResourceOne/DataSource");
			ConnLog.open("AddFolderServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			bw.write("userId:" + userId);
			bw.flush();
			List fmList = handler.getUserList(userId);
			Iterator it = fmList.iterator();
			FolderManagementVO fmvo = null;
			if (it.hasNext())
				fmvo = (FolderManagementVO) it.next();
			bw.write("folderName:" + folderName);
			bw.write("parentId:" + parentId);
			bw.write("fmvo.getFmId():" + fmvo);
			bw.flush();
			boolean hasSameName = handler.checkSameName(folderName, parentId,
					fmvo.getFmId());
			if (hasSameName)
				forward(request, response, "/folder/AddFolder.jsp?parentId="
						+ parentId + "&folderName=" + folderName + "&folderDes"
						+ folderDes);
			else if (!hasSameName) {
				FolderPackageVO vo = new FolderPackageVO();
				vo.setFolFpId(parentId);
				vo.setFpName(folderName);
				vo.setFpDesc(folderDes);
				vo.setFpCreatedate(new Long(System.currentTimeMillis()));
				vo.setFpModifydate(new Long(System.currentTimeMillis()));
				vo.setFpIsfile("1");
				vo.setFmId(fmvo.getFmId());
				handler.addFolder(vo);
				String nextpage = "";
				if (parentId.intValue() == 1)
					nextpage = "/servlet/ShowRootFolderServlet";
				else
					nextpage = "/servlet/ShowFileListServlet?parentId="
							+ parentId;
				request.setAttribute("nextpage", nextpage);
				forward(request, response, "/mail/MailReload.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("AddFolderServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}