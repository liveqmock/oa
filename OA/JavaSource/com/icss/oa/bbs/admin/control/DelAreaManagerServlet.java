/*
 * Created on 2008-2-21
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSAreaHandler;

/**
 * @author wangsu
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelAreaManagerServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

//		String type = request.getParameter("type");
		String perid[] = request.getParameterValues("perid");
		List areaList = null;
		Integer areaId = new Integer(request.getParameter("areaId"));
		try {
			//conn=this.getConnection(Globals.DATASOURCEJNDI);
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelAreaManagerServlet");
			BBSAreaHandler handler = new BBSAreaHandler(conn);
			for (int j = 0; j < perid.length; j++) {
				String pid = perid[j];
				handler.delManagerRight(areaId, pid);
			}

			areaList = handler.getAreaListById(areaId);
			request.setAttribute("areaList", areaList);
			this.forward(request, response, "/bbs/editArea.jsp");

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelAreaManagerServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
