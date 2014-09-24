package com.icss.oa.filetransfer.admin;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.filetransfer.handler.*;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.util.PropertyManager;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

public class ShowRecServlet extends ServletBase {

	public ShowRecServlet() {
	}

	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		Connection conn = null;
		dirmanage mailHandler = null;
		try {
			conn = getConnection("java:comp/env/ResourceOne/DataSource");

			FiletransferSetHandler handler1 = new FiletransferSetHandler(conn);
			FileTransferHandler handler = new FileTransferHandler(conn);
			String type = arg0.getParameter("type");
			System.out.println("type  " + type);
			String mailName = arg0.getParameter("mailName");
			System.out.println("mailName  " + mailName);
			String username = arg0.getParameter("username");
			System.out.println("username  " + username);
			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");
			System.out.println("domain  " + domain);
			System.out.println("ip  " + ip);
			byte mailContent[] = (byte[]) null;

			mailHandler = MailhandlerFactory.getHandler(username);
			if ("system".equals(type))
				try {
					mailContent = mailHandler.viewmail(mailName);
					System.out.println("mailContent  " + mailContent);
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			MessageHandler messageHandler = new MessageHandler();
			MimeMessage fileMessage = null;
			String recePerson = null;

			fileMessage = messageHandler.getContentMessage(mailContent);
			javax.mail.Address toArray[] = fileMessage
					.getRecipients(javax.mail.Message.RecipientType.TO);
			recePerson = handler1.getAddressToString(toArray);

			forward(arg0, arg1, "/filetransfer/ShowRec.jsp?recePerson="
					+ recePerson);

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (MessagingException e2) {
			e2.printStackTrace();
		} finally {
			//add by lintl
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (mailHandler != null)
				try {
					mailHandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
		}
	}

	// private List readPersonlist;
	// private String recePerson;
}