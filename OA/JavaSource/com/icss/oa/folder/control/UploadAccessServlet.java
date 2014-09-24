/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderDbfileVO;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderPackageVO;
import com.icss.oa.config.Config;
import java.net.URLEncoder;
import java.net.URLDecoder;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UploadAccessServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		//获取页面参数
		//	
		String fileDes = request.getParameter("fileDes");
		Integer folderId = new Integer(request.getParameter("targetFolderId"));

		FolderPackageVO vo = new FolderPackageVO();

		InputStream inputStream1 = null;
		InputStream inputStream2 = null;

		//取得上传文件名

		HttpSession session = request.getSession();
		String oldFileName = (String) session.getAttribute("AccessName");
		//InputStream AccessInputStream = (InputStream)session.getAttribute("AccessInputStream");

		/*	String oldFileName = getUploadOldFileName(request, "file");
			String fileType = "";
			if (oldFileName != null) {
				int index = 0;
				index = oldFileName.lastIndexOf("\\");
				if (index != -1)
					oldFileName = oldFileName.substring(index + 1);
				//取得文件后缀名
				 */
		int index = 0;
		String fileType = "";
		index = oldFileName.lastIndexOf(".");
		if (index != -1)
			fileType = oldFileName.substring(index + 1);

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UploadAccessServlet");
			conn.setAutoCommit(false);

			FolderHandler handler = new FolderHandler(conn);
			//取得文件柜vo
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List fmList = handler.getUserList(userId);
			Iterator it = fmList.iterator();
			FolderManagementVO fmvo = null;
			if (it.hasNext()) {
				fmvo = (FolderManagementVO) it.next();
			}

			//上传附件
			boolean hasSameName = handler.checkSameName(oldFileName, folderId, fmvo.getFmId());
			if (hasSameName == true) {//如果重名，报出错
				String errorS = "你添加的附件在网络文件夹中有重名的文件！";
				request.setAttribute("errorS", errorS);
				this.forward(request, response, "/include/errorString1.jsp");
				
			}else if (hasSameName == false) { //没有重名
				if (oldFileName != null) {
					String mime = FileType.getInstance().getMimeType(fileType);

					//获得文件路径名
					inputStream1 = (InputStream) session.getAttribute("AccessInputStream");
					inputStream2 = new FileInputStream((String) session.getAttribute("path"));

					long fileSize = 0;
					byte[] bufbyte = new byte[2048];
					int templength = 0;
					while ((templength = inputStream2.read(bufbyte, 0, 2048)) != -1) {
						fileSize = fileSize + new Long(templength).longValue();
					}

					//得到用户所占空间
					Integer userFileSize = new Integer(0);
					if (fmvo != null) {
						userFileSize = handler.contSize(fmvo.getFmId());
					}
					//您的剩余空间不足,请先删除部分文件
					if ((userFileSize.longValue() + fileSize) > Config.FOLDER_SPACE * 1024 * 1024) {
						//response.sendRedirect("RefreshServlet?errormsg=空间不足,请删除多余文件！");
						String errorS = "空间不足,请删除多余文件";
						request.setAttribute("errorS", errorS);
						this.forward(request, response, "/include/errorString1.jsp");
					} else {

						vo.setFolFpId(folderId);
						vo.setFpName(oldFileName);
						vo.setFpDesc(fileDes);
						vo.setFpMimeType(mime);
						vo.setFpCreatedate(new Long(System.currentTimeMillis()));
						vo.setFpModifydate(new Long(System.currentTimeMillis()));
						vo.setFpIsfile("0");
						vo.setFpSize(new Long(fileSize));
						vo.setFmId(fmvo.getFmId());

						Integer fp_id = handler.addFolder(vo);
						//添加文件
						FolderDbfileVO fileVO = new FolderDbfileVO();
						fileVO.setFpId(fp_id);
						fileVO.setFdbfAccessory(inputStream1);
						vo.setFpSize(new Long(fileSize));
						handler.addFile(fileVO);
					}

					//关闭输入流 

					session.removeAttribute("AccessName");
					String errorS = "你已经将附件" + oldFileName + "成功添加到网络文件夹中！";
					request.setAttribute("errorS", errorS);
					this.forward(request, response,"/include/errorString1.jsp");

					conn.commit();
					/*if (folderId.intValue() == 1)
						response.sendRedirect("ShowRootFolderServlet");
					else
						response.sendRedirect(
							"ShowFileListServlet?parentId=" + folderId);*/
				}
			}
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("UploadAccessServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			if (inputStream1 != null) {
				try {
					inputStream1.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (inputStream2 != null) {
				try {
					inputStream2.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
}
