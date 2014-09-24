/*
 * Created on 2004-8-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.phonebook.vo.PhoneJobVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeletePhoneJobServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		String jobId[] = request.getParameterValues("jobId");

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DeletePhoneJobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			for (int j = 0; j < jobId.length; j++) {
				Integer jid = new Integer(jobId[j]);
				PhoneJobVO pVO = phandler.getVOByJobId(jid);
				//修改相应的电话记录
				//phandler.setInfoJobid(pVO,"delete");
				//删除此职务
				phandler.DeleteJob(jid);
			}

			this.forward(request, response, "/servlet/PhoneJobServlet");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("DeletePhoneJobServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
