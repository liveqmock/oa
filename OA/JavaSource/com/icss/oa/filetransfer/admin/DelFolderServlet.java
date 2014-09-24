/*
 * Created on 2004-7-1
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 * ɾ���û��Զ����ļ���
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelFolderServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	     throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		dirmanage mailhandler = null;
		Context ctx = null;

		try{
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelFolderServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());

			String folderName = request.getParameter("folder");
			folderName = ftsHandler.encodeBase64(folderName);
		
			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");
			
			mailhandler = MailhandlerFactory.getHandler(username);
			
			String[][] userStr = mailhandler.fileheadList(folderName);
			FileTransferHandler handler = new FileTransferHandler(conn);
			String code = IntendWork.getCodeValue("oa_filetrans");
			for(int i=0;i<userStr[0].length;i++){
				String filehead = userStr[1][i];
				handler.delIntend(code,filehead,username);
			}
			boolean pd = mailhandler.deletedir(folderName,1);
			request.setAttribute("nextpage", "/servlet/BoxListServlet");
			
			if(pd==true){
				this.forward(request,response,"/mail/MailReload.jsp");
			}else{
				System.out.println("delete is failed!");
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally{
			if(mailhandler!=null){
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
			}
			if (conn != null)
			try {
				conn.close();
				ConnLog.close("DelFolderServlet");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		}
		
	}

}
