/*
 * Created on 2004-5-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.handler;

import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MailLocator {
	private static InitialContext ctx;
	private MailLocator() {

	}

	public static Session getInstance() throws NamingException {
		try {
			if (ctx == null) {
				try {
					ctx = new InitialContext();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
			return (javax.mail.Session) ctx.lookup("mail/webmail");
		} catch (NamingException e) {
			throw new NamingException("not find" + e);
		}
	}
}
