/*
 * Created on 2004-2-19
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.bbs.handler.BBSHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowSingleAreaServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List areaList = null;
		Integer areaId = new Integer(request.getParameter("areaId"));
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowSingleAreaServlet");
			BBSHandler handler = new BBSHandler(conn);
			areaList = handler.getAreaListById(areaId);
			
			SysOrgpersonHandler orghandler = new SysOrgpersonHandler(conn);
			List orglist = orghandler.getOrgByLevel1(3);
			
			request.setAttribute("orglist", orglist);
			request.setAttribute("areaList", areaList);
			this.forward(request, response, "/bbs/editArea.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowSingleAreaServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
