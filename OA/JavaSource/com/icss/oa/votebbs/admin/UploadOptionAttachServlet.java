/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.votebbs.admin;        

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

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;


import com.icss.oa.util.FileTypeUtil;
import com.icss.oa.votebbs.handler.AttachFileBean;
import com.icss.oa.votebbs.handler.OptionUploadBean;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 * 
 * �ϴ�����
 */
public class UploadOptionAttachServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer mainid=request.getParameter("mainid")==null?new Integer(-1):new Integer(request.getParameter("mainid"));
        
		Connection conn = null;
		try {
			
			String url = ""; 
				
			HttpSession session = request.getSession();
			OptionUploadBean optionuploads =
				OptionUploadBean.getInstanceFromSession(session);
			
			try{
				url = getUploadFileFullName(request, "attfile");
				System.out.println("mail_url  "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/log/attachFile.jsp?donext="
							+ request.getParameter("returnback")+"&error=2");
			}
			// �жϸ����ܺ��Ƿ����10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "attfile");
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
//			System.out.println("++++++++++UploadOptionAttachServlet+++filename===+++++++"+filename);
			if (filename != null) {
//				System.out.println("++++++++++UploadOptionAttachServlet+++filename000000000===+++++++"+filename);
					// �õ����ص��ļ�����
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						// ��ʱ�Ĵ���
						// String extname = fileutil.getFileExtension(filename);
						// int length = 0;
						// if(filename.length()>20+1+extname.length()){
						// filename =
						// filename.substring(0,16).concat("(�ض�)").concat(".").concat(extname);
						// }
					}
//					System.out.println("++++++++++UploadOptionAttachServlet+++filename0===+++++++"+filename);
					// ----------�����ص��ļ�ת�Ƶ�ָ����Ŀ¼��----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"votebbs"+sep+"uploadfile"+sep));
//					System.out.println("++++++++++UploadOptionAttachServlet+++path1===+++++++"+path);
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					String	username=user.getEnName();
					path.append(username).append(System.currentTimeMillis()).append(File.separator);
					System.out.println("++++++++++UploadLogMsgAttachServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();
					}
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));
					System.out.println("++++++++++UploadLogMsgAttachServlet++++++++++filepath=======++"+filepath);
					
					// ----------------------����Ϣװ��bean---------------------------
					AttachFileBean attachFileBean = new AttachFileBean();
					attachFileBean.setFileOriginName(filename);
					// System.out.println("the uploadpath is:....."+path.toString());
					attachFileBean.setUploadUrl(path.toString());
					attachFileBean.setFilesize(file.length());
					attachFileBean.setFileMimetype(
							fileutil.getMimeType(fileutil.getFileExtension(filename)));

					optionuploads.setAttachFile(attachFileBean);
					System.out.println("++++++++++UploadLogMsgAttachServlet++++++++++����Ϣװ��bean-=======++");
					
					
					if(ins!= null){
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
					
					response.sendRedirect(
							request.getContextPath() + "/votebbs/addOption.jsp?mainid="+mainid);
					 
				}else{   // file isn't exist
					
					response.sendRedirect(
							request.getContextPath() + "/votebbs/addOption.jsp?mainid="+mainid);
					
				}
				
			// else isn't bigger than 10M

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally{
			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		
	}
	
	
	
}


