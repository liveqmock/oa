/*
 * Created on 2004-7-1
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.personInfoHandler;
import com.icss.resourceone.sdk.framework.*;

/**
 * @author firecoral
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PersonInfoServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
			
		Connection conn = null;
		Context ctx = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("PersonInfoServlet");
			String PersonUUID = request.getParameter("personuuid");

			EntityManager entityManager = EntityManager.getInstance();
			Person person = entityManager.findPersonByUuid(PersonUUID);

			personInfoHandler handler = new personInfoHandler(conn);
			String orguuid = handler.getOrgUUID(person.getUuid());
			StringBuffer org = new StringBuffer();

			while (true) {
				if (handler.getOrgLevel(orguuid).intValue() == 3) {

					org.append(handler.getOrgName(orguuid));

					break;
				} else {
					org.append(handler.getOrgName(orguuid) + "--");
					orguuid = handler.getParentUUID(orguuid);
				}
			}

			request.setAttribute("person", person);
			request.setAttribute("dep", org.toString());

			this.forward(request, response, "/include/personInfo.jsp");

		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally{
			
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("PersonInfoServlet");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}

	}

}
