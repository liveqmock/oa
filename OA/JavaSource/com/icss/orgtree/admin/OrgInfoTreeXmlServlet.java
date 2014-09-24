/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.orgtree.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgTreeVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OrgInfoTreeXmlServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuffer outSB = new StringBuffer();
		String orgId = request.getParameter("orgId");
		outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List list = null;
		OrgHandler handler = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OrgInfoTreeXmlServlet");
			handler = new OrgHandler(conn);
			list = handler.getOrgTreeList(orgId);
		} catch (Exception e) {
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("OrgInfoTreeXmlServlet");
				}
			} catch (SQLException sqle) {
			}
		}
		if (list != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				SysOrgTreeVO vo = (SysOrgTreeVO) it.next();
				String corgId = vo.getVO().getOrguuid();
				String corgName = vo.getVO().getCnname();
				String text = new String(corgName.getBytes("GBK"), "ISO8859_1");
				outSB.append("<tree text=\"").append(text).append("\"");
				outSB.append("/>");
			}
		}
		outSB.append("</tree>");

		response.setContentType("text/xml");

		//lizb edit add write.close()
		PrintWriter write = response.getWriter();
		write.write(outSB.toString());
		write.flush();
		write.close();

	}
	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	}
}