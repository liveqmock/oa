// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FolderSizeServlet.java

package com.icss.oa.filetransfer.admin;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

public class FolderSizeServlet extends ServletBase {

	public FolderSizeServlet() {
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		dirmanage mailhandler;
		Context ctx = null;
		conn = null;
		mailhandler = null;
		try {
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			conn = getConnection("java:comp/env/ResourceOne/DataSource");
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			mailhandler = MailhandlerFactory.getHandler(username);
			long usersize = mailhandler.getDirSize("");
			long totalsize = ftsHandler.getFolderSize(user.getPersonUuid());
			double percent = ((double) (usersize / 0x100000L) * 100D)
					/ (double) totalsize;
			DecimalFormat df = new DecimalFormat("#0.00");
			String a = df.format(percent);
			request.setAttribute("usersize", String.valueOf(usersize));
			request.setAttribute("totalsize", String.valueOf(totalsize));
			request.setAttribute("percent", a);
			forward(request, response, "/mail/FolderSize.jsp");
			
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
	}
}