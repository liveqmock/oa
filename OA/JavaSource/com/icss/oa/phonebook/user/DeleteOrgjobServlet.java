/*
 * Created on 2004-12-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;

import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.phonebook.vo.PhoneJobnameVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteOrgjobServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		String nameId[] = request.getParameterValues("nameId");

		try {
			String errorMsg = null;

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DeleteOrgjobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			for (int j = 0; j < nameId.length; j++) {
				Integer nameid = new Integer(nameId[j]);
				PhoneJobnameVO pVO = phandler.getOrgjobVO(nameid);
				//修改相应的电话记录
				//phandler.setNewNameidsForInfo(pVO,"delete");
				//删除此职务
				if (phandler.canDelete(nameid)) {
					
                
					errorMsg = "此职务不能被删除！";

				} else {

					phandler.deleteOrgjob(nameid);
					//errorMsg = "此职务被删除！";

				}
			}

			request.setAttribute("nameId", nameId);
			request.setAttribute("errorMsg", errorMsg);

			super.forward(request, response, "/servlet/OneOrgPhoneJobServlet");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DeleteOrgjobServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
