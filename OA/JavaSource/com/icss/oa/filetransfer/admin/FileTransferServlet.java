/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FileTransferServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		Connection conn = null;
		Context ctx = null;
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("FileTransferServlet");
			FileTransferHandler handler = new FileTransferHandler(conn);
			//取得发件人信息
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String userid = "";
			userid = ftsHandler.getUserid(user.getPersonUuid());
			if(userid==null){
				this.forward(request,response,"/filetransfer/noMailBox.jsp");
			}

			//每次进入发送文件功能时，重新添加session
			HttpSession session = request.getSession();
			SendFileBean.removeSession(session);
			SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
			SendFileBean.saveToSession(session, sendFileBean);
			this.forward(request, response, "/filetransfer/sendFile.jsp");
			
		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();
			
		} catch (EntityException e) {
			e.printStackTrace();
			
		} finally {
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("FileTransferServlet");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			
		}
		
		
	}

	
}

