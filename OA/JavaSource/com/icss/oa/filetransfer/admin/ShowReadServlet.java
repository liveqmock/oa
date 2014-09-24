/*
 * Created on 2005-8-23
 *
 * 
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;

public class ShowReadServlet extends ServletBase {

	private List readPersonlist;

	private String recePerson;

	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		Connection conn = null;
		String readPerson = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		String recordMailid1 = arg0.getParameter("recordMailid");
		Integer recordMailid = null;

		if (recordMailid1 != null) {
			recordMailid = new Integer(recordMailid1);
		}

		FileTransferHandler handler = new FileTransferHandler(conn);
		if (recordMailid != null) { //ÐèÒªÔÄ¶Á¼ÇÂ¼
			readPerson = handler.getReadPersonName1(recordMailid);
		}
		this.forward(arg0, arg1, "/filetransfer/ShowRead.jsp?readPerson="
				+ readPerson);

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
