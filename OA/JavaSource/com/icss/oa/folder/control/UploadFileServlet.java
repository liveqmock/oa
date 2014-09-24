/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
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
import com.icss.oa.config.Config;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderDbfileVO;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderPackageVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UploadFileServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		//��ȡҳ�����
		//	
		String fileDes = request.getParameter("fileDes");
		Integer folderId = new Integer(request.getParameter("folderId"));

		FolderPackageVO vo = new FolderPackageVO();

		InputStream inputStream = null;
		String url = "";
		//ȡ���ϴ��ļ���
		String oldFileName = getUploadOldFileName(request, "file");
		String fileType = "";
		if (oldFileName != null) {
			int index = 0;
			index = oldFileName.lastIndexOf("\\");  
			if (index != -1)
				oldFileName = oldFileName.substring(index + 1);
			//ȡ���ļ���׺��
			index = oldFileName.lastIndexOf(".");
			if (index != -1)
				fileType = oldFileName.substring(index + 1);
		}
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UploadFileServlet");
			conn.setAutoCommit(false);

			FolderHandler handler = new FolderHandler(conn);
			//ȡ���ļ���vo
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List fmList = handler.getUserList(userId);
			Iterator it = fmList.iterator();
			FolderManagementVO fmvo = null;
			if (it.hasNext()) {
				fmvo = (FolderManagementVO) it.next();
			}
			//�ϴ�����
			boolean hasSameName = handler.checkSameName(oldFileName, folderId, fmvo.getFmId());
			if (hasSameName == true){ //���������������
				url = "RefreshServlet?errortype=1" ;
				//response.sendRedirect("RefreshServlet?errormsg=" + "���ϴ����ļ�����,����������!");
			}else if (hasSameName == false) { //û������
				if (oldFileName != null) {
					String mime = FileType.getInstance().getMimeType(fileType);

					//����ļ�·����
					String fileFillName = getUploadFileFullName(request, "file");
					inputStream = new FileInputStream(fileFillName);
					File file = new File(fileFillName);
					long fileSize = file.length();

					//�õ��û���ռ�ռ�
					Integer userFileSize = new Integer(0);
					if (fmvo != null) {
						userFileSize = handler.contSize(fmvo.getFmId());
					}
					//����ʣ��ռ䲻��,����ɾ�������ļ�
					
					if ((userFileSize.longValue() + fileSize) > Config.FOLDER_SPACE * 1024 * 1024) {
						url = "RefreshServlet?errortype=2";
					} else {

						vo.setFolFpId(folderId);
						//�����ļ������ȵĽ�ȡ
						String ext_name = oldFileName;
						if (oldFileName.length() > 30) {
							ext_name = oldFileName.substring(oldFileName.length() - 30);
						}

						vo.setFpName(ext_name);
						vo.setFpDesc(fileDes);
						vo.setFpMimeType(mime);
						vo.setFpCreatedate(new Long(System.currentTimeMillis()));
						vo.setFpModifydate(new Long(System.currentTimeMillis()));
						vo.setFpIsfile("0");
						vo.setFpSize(new Long(fileSize));
						vo.setFmId(fmvo.getFmId());

						Integer fp_id = handler.addFolder(vo);

						//����ļ�
						FolderDbfileVO fileVO = new FolderDbfileVO();
						fileVO.setFpId(fp_id);
						fileVO.setFdbfAccessory(inputStream);
						handler.addFile(fileVO);
						//�ر������� 
						inputStream.close();
					}
				}
				conn.commit();
				if (folderId.intValue() == 1){
					url = "ShowRootFolderServlet";
				}else{
					url = "ShowFileListServlet?parentId=" + folderId;
				}		
			}
			response.sendRedirect(url);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("UploadFileServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
