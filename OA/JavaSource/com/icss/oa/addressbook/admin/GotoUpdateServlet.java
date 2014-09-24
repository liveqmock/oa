/*
 * Created on 2004-11-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.addressbook.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookContentVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GotoUpdateServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String abcId = request.getParameter("abcid");

		Connection conn = null;

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("GotoUpdateServlet");
			AddressbookHandler ahandler = new AddressbookHandler(conn);

			AddressbookContentVO avo = ahandler.getOneAddressBook(new Integer(abcId));
			request.setAttribute("abcvo", avo);

			this.forward(request, response, "/addressbook/addressbookDetail.jsp");

		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("GotoUpdateServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
