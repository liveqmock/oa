package com.icss.oa.filetransfer.admin;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import java.io.*;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

public class SendFileMutiServlet extends HttpServlet implements Servlet {

	public SendFileMutiServlet() {
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dirmanage mailhandler = null;
		Context ctx = null;
		// java.sql.Connection conn = null;
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("/ccc.txt")));
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			// conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
			mailhandler = MailhandlerFactory.getHandler(user.getUserID());
			bw.write(" mailhandler.needFlush():" + mailhandler.needFlush());
			if (mailhandler.needFlush())
				mailhandler.flush();
			
			bw.close();
		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//add by lintl
			if (mailhandler != null)
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
				
		}
	}
}