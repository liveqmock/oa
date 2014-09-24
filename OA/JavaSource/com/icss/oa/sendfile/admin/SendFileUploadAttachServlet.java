/*
 * Created on 2004-5-9
 *
 */
 
package com.icss.oa.sendfile.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.AttachFileBean;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.util.FileTypeUtil;
import com.icss.oa.util.StringUtility;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * �ϴ�����
 */

public class SendFileUploadAttachServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		try {
			
			String url = "";
			
			HttpSession session = request.getSession();
			SendFileBean attachFile = SendFileBean.getInstanceFromSession(session);
			try {
				url = getUploadFileFullName(request, "attfile");
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/mail/AttachFile.jsp?donext=" + request.getParameter("returnback") + "&error=2");
			}
			//�жϸ����ܺ��Ƿ����10M
			File file = new File(url);
			if (attachFile.getTotleFilesize() + file.length() > 10 * 1024 * 1024) {
				response.sendRedirect(request.getContextPath() + "/mail/AttachFile.jsp?donext=" + request.getParameter("returnback") + "&error=1");
			} else {
				String filename = getUploadOldFileName(request, "attfile");
				
//				0907����
				
							
				String donext = request.getParameter("donext");
				FileTypeUtil fileutil = FileTypeUtil.getInstance();
				if (filename != null) {
					filename = StringUtility.CtoB(filename);
					//�õ����ص��ļ�����
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {
						filename = filename.substring(index + 1);
					}
					//----------�����ص��ļ�ת�Ƶ�ָ�����ʼ�����Ŀ¼��----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ConnLog.open("UploadAttachServlet");
					
					
					//�ϴ�·���޸� 2005-12-28
					//ԭ�г���
					//ServletContext context = this.getServletContext();
					//char sep = File.separatorChar;
					//StringBuffer path = new StringBuffer(context.getRealPath(sep + "filetransfer" + sep + "uploadfile" + sep));
					//�޸ĳ���
					StringBuffer path = new StringBuffer(FiletransferUtil.getUploadFilePath());
					
					
					
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					//���������ñ��õ�׼ȷ�������û������������������ַ
					FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
					String userid = ftsHandler.getUserid(user.getPersonUuid());
					//copy
					path.append(userid).append(System.currentTimeMillis()).append(File.separator);
					File uploadpath = new File(path.toString());
       
					if (!uploadpath.exists()) {
						uploadpath.mkdirs();
					}
					
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins, new File(filepath));
					//----------------------����Ϣװ��bean---------------------------
					AttachFileBean attachFileBean = new AttachFileBean();
					attachFileBean.setFileOriginName(filename);
					//					System.out.println("the uploadpath is:....."+path.toString());
					attachFileBean.setUploadUrl(path.toString());
					attachFileBean.setFilesize(file.length());
					attachFileBean.setFileMimetype(fileutil.getMimeType(fileutil.getFileExtension(filename)));

					attachFile.addAttach(attachFileBean);

					if (ins != null) {
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
					
					System.out.println("donextdonextdonextdonextdonextdonextdonextdonext    "+ donext);
					
					if (donext.equals("next")) { //�������
						response.sendRedirect(request.getContextPath() + "/mail/AttachFile.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("1")) { //����
						response.sendRedirect(request.getContextPath() + "/mail/SendMail_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					} else if (donext.equals("2")) {
						response.sendRedirect(request.getContextPath() + "/mail/ReplyMail_Body.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("3")) {
						response.sendRedirect(request.getContextPath() + "/mail/TransmitMail_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					} else if (donext.equals("4")) {
						response.sendRedirect(request.getContextPath() + "/mail/FromDraftSendModify_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					} else if (donext.equals("5")) {
						response.sendRedirect(request.getContextPath() + "/mail/ShowDraft_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					}
				} else {
					if (donext.equals("next")) { //�������
						response.sendRedirect(request.getContextPath() + "/mail/AttachFile.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("1")) { //����
						response.sendRedirect(request.getContextPath() + "/mail/SendMail_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					} else if (donext.equals("2")) {
						response.sendRedirect(request.getContextPath() + "/mail/ReplyMail_Body.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("3")) {
						response.sendRedirect(request.getContextPath() + "/mail/TransmitMail_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					} else if (donext.equals("4")) {
						response.sendRedirect(request.getContextPath() + "/mail/FromDraftSendModify_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					} else if (donext.equals("5")) {
						response.sendRedirect(request.getContextPath() + "/mail/ShowDraft_Body.jsp?atta=1&donext=" + request.getParameter("returnback"));
					}

				} 

			} //else isn't bigger than 10M
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {

			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("UploadAttachServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

	}

}
