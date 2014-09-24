/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;        

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.filetransfer.handler.AttachFileBean;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.util.FileTypeUtil;
import com.icss.oa.util.StringUtility;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * 上传附件
 */
public class UploadAttachServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		Connection conn = null;
		try {
			
			String url = ""; 
			HttpSession session = request.getSession();
			SendFileBean attachFile =
				SendFileBean.getInstanceFromSession(session);
			
			try{
				url = getUploadFileFullName(request, "attfile");
				System.out.println("mail_url  "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/filetransfer/attachFile.jsp?donext="
							+ request.getParameter("returnback")+"&error=2");
			}
			//判断附件总和是否大于10M
			File file = new File(url);
			if(attachFile.getTotleFilesize()+file.length()>10*1024*1024){
				response.sendRedirect(
						request.getContextPath()
							+ "/filetransfer/attachFile.jsp?donext="
							+ request.getParameter("returnback")+"&error=1");
			}else{
				String filename = getUploadOldFileName(request, "attfile");
//				System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT ="+filename);
				String donext = request.getParameter("donext");
				FileTypeUtil fileutil = FileTypeUtil.getInstance();
				if (filename != null) {
					//得到上载的文件名称
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						
//						System.out.println("qqqqq   the origin filename is:........" + filename);
						filename = com.icss.oa.util.StringUtility.CtoB(filename);
//						System.out.println("qqqqq   the origin filename is222222:........" + filename);
						
						//暂时的处理
//						String extname = fileutil.getFileExtension(filename);
//						int length = 0;
//						if(filename.length()>20+1+extname.length()){
//							filename = filename.substring(0,16).concat("(截断)").concat(".").concat(extname);
//						}
					}
					//----------把上载的文件转移到指定的邮件下载目录下----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ConnLog.open("UploadAttachServlet");
					
					
					//上传路径修改 2005-12-28
					//原有程序：					
					//ServletContext context = this.getServletContext();
					//char sep = File.separatorChar;
					//StringBuffer path = new StringBuffer(context.getRealPath(sep+"filetransfer"+sep+"uploadfile"+sep));
					//修改程序：
					StringBuffer path = new StringBuffer(FiletransferUtil.getUploadFilePath());
					
					
					
					
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
					FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
					String userid = ftsHandler.getUserid(user.getPersonUuid());
					//copy
					path.append(userid).append(System.currentTimeMillis()).append(File.separator);
					File uploadpath = new File(path.toString());
					//System.out.println("sunchuanting_path   "+path.toString());        
					if(!uploadpath.exists()){
						uploadpath.mkdirs();
					}
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));
					//----------------------把信息装入bean---------------------------
					AttachFileBean attachFileBean = new AttachFileBean();
					attachFileBean.setFileOriginName(filename);
//					System.out.println("the uploadpath is:....."+path.toString());
					attachFileBean.setUploadUrl(path.toString());
					attachFileBean.setFilesize(file.length());
					attachFileBean.setFileMimetype(
							fileutil.getMimeType(fileutil.getFileExtension(filename)));

					attachFile.addAttach(attachFileBean);
					
					if(ins!= null){
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
					
					if (donext.equals("next")) { //继续添加
						response.sendRedirect(
							request.getContextPath()
								+ "/filetransfer/attachFile.jsp?donext="
								+ request.getParameter("returnback"));
					} else if (donext.equals("1")) { //返回
						response.sendRedirect(
							request.getContextPath() + "/filetransfer/sendFile.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("2")) {
						response.sendRedirect(
							request.getContextPath() + "/filetransfer/replyFile.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("3")) {
						response.sendRedirect(
							request.getContextPath() + "/filetransfer/transmitAfterModify.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("4")) {
						response.sendRedirect(
								request.getContextPath() + "/filetransfer/fromDraftSendModify.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("5")) {
						response.sendRedirect(
								request.getContextPath() + "/filetransfer/fromDraftSend.jsp?donext=" + request.getParameter("returnback"));
					}
				}else{   //file isn't exist
//					response.sendRedirect(
//							request.getContextPath()
//								+ "/filetransfer/attachFile.jsp?donext="
//								+ request.getParameter("returnback")+"&error=2");
					if (donext.equals("next")) { //继续添加
						response.sendRedirect(
							request.getContextPath()
								+ "/filetransfer/attachFile.jsp?donext="
								+ request.getParameter("returnback"));
					} else if (donext.equals("1")) { //返回
						response.sendRedirect(
							request.getContextPath() + "/filetransfer/sendFile.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("2")) {
						response.sendRedirect(
							request.getContextPath() + "/filetransfer/replyFile.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("3")) {
						response.sendRedirect(
							request.getContextPath() + "/filetransfer/transmitAfterModify.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("4")) {
						response.sendRedirect(
								request.getContextPath() + "/filetransfer/fromDraftSendModify.jsp?donext=" + request.getParameter("returnback"));
					} else if (donext.equals("5")) {
						response.sendRedirect(
								request.getContextPath() + "/filetransfer/fromDraftSend.jsp?donext=" + request.getParameter("returnback"));
					}
				}
				
			}//else isn't bigger than 10M

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally{
			
			if(conn!=null){
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


