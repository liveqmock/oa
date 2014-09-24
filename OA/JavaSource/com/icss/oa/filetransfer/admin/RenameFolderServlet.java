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
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RenameFolderServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	       throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;

		try{
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("RenameFolderServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			String OldFolderName = request.getParameter("mytarget");
			String NewFolderName = request.getParameter("newfoldername");
			OldFolderName = ftsHandler.encodeBase64(OldFolderName);
			NewFolderName = ftsHandler.encodeBase64(NewFolderName);
		
			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");
			
			mailhandler = MailhandlerFactory.getHandler(username);
			
			boolean pd = mailhandler.dirRename(OldFolderName,NewFolderName);
			request.setAttribute("nextpage", "/servlet/BoxListServlet");
            if(pd==true){
            	this.forward(request,response,"/mail/MailReload.jsp");
            }else{
            	System.out.println("rename failed!!!!!");
            }
			
            
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(mailhandler!=null)
					try {
						mailhandler.disconnect();
					} catch (LdapException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    }
			if (conn != null)
			try {
				conn.close();
				ConnLog.close("RenameFolderServlet");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
	    }

}
