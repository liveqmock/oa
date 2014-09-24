/*
 * Created on 2004-4-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 * 
 * 给左边的树加入用户自定义的文件夹
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LeftListServlet extends ServletBase {


	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
			Connection conn = null;
			Context ctx = null;
			dirmanage mailhandler = null;
			List userFolderName = new ArrayList();

			try{
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("LeftListServlet");
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
				String username = ftsHandler.getUserid(user.getPersonUuid());
				if(username!=null){

					String domain = PropertyManager.getProperty("archivesdomain");
					String ip = PropertyManager.getProperty("archivesip");
					
													
					mailhandler = MailhandlerFactory.getHandler(username);
					String[][] str = mailhandler.fileheadList("");

					//FileTransferHandler fHandler = new FileTransferHandler();

					//得到用户自定义的文件夹
					String[] uFolder = mailhandler.dirList("");
					//用户文件夹名称
					for(int j=0;j<uFolder.length;j++){
						userFolderName.add(uFolder[j]);
					}
					request.setAttribute("userFolderName",userFolderName);
				}
				
				this.forward(request, response, "/filetransfer/leftList.jsp");
				
			}catch(Exception ex){
				ex.printStackTrace();
				System.err.println(ex.getMessage());
				
			}finally{
				if(mailhandler!=null)
					try {
						mailhandler.disconnect();
					} catch (LdapException e1) {
						e1.printStackTrace();
					}
				if (conn != null)
				try {
					conn.close();
					ConnLog.close("LeftListServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

	}

}
