/*
 * Created on 2004-4-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BoxListServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Context ctx = null;
		Connection conn = null;
		dirmanage mailhandler = null;

		List reList = new ArrayList();
		List userFolder = new ArrayList();
		List userFolderName = new ArrayList();
		long boxMemory = 0;
		long boxPercent = 0;

		try {
			
			
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			conn = getConnection(Globals.DATASOURCEJNDI);
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler =
				new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			
			mailhandler = MailhandlerFactory.getHandler(username);
		
			String[][] str = mailhandler.fileheadList("");

			FileTransferHandler fHandler = new FileTransferHandler(conn);
			//得到在jsp中要显示的内容
			reList = fHandler.getShowContent(str);
			
			
			 
			//得到用户自定义的文件夹
			String[] uFolder = mailhandler.dirList("");
			//得到用户所有自定义文件夹中的邮件列表(一个文件夹是一个LIST，一起放在一个大LIST中)

			if (uFolder.length > 0) {
				for (int i = 0; i < uFolder.length; i++) {
					String[][] userStr = mailhandler.fileheadList(uFolder[i]);
					userFolder.add(fHandler.getUserFolder(userStr));
				}
			}
			//用户文件夹名称
			for (int j = 0; j < uFolder.length; j++) {
				userFolderName.add(uFolder[j]);
			}

			//得到当前用户邮箱的大小
			boxMemory = fHandler.getMailBoxMemory(username);
			//用户邮箱报警的百分数
			boxPercent = fHandler.getMailBoxPercent(username);

		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {

			if (mailhandler != null)
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}

			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
		String boxMemoryStr = String.valueOf(boxMemory);
		String boxPercentStr = String.valueOf(boxPercent);

		request.setAttribute("reList", reList);
		request.setAttribute("userFolder", userFolder);
		request.setAttribute("userFolderName", userFolderName);
		request.setAttribute("boxMemory", boxMemoryStr);
		request.setAttribute("boxPercent", boxPercentStr);

		this.forward(request, response, "/mail/FloderManage_Body.jsp");
	}

}
