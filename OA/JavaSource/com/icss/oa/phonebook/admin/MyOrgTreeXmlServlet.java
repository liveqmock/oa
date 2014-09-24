/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.phonebook.admin;

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
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.HandlerException;
import com.icss.oa.phonebook.handler.OrgHandler;
import com.icss.oa.phonebook.vo.SysOrgTreeVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MyOrgTreeXmlServlet extends ServletBase { 
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuffer outSB = new StringBuffer();
		String orgId = request.getParameter("orgId");
		String nodeUrl = request.getParameter("nodeUrl");
		outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List list = null;
		OrgHandler handler = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OrgTreeXmlServlet");
			handler = new OrgHandler(conn);
			list = handler.getOrgTreeList(orgId);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (HandlerException e) {
			e.printStackTrace();
		} finally{
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("OrgTreeXmlServlet");
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
				if (vo.getHasChild()) {
					outSB.append(" src=\"../servlet/OrgTreeXmlServlet.xml?orgId=").append(corgId).append("&amp;nodeUrl=").append(nodeUrl).append("\"");
				}
				outSB.append(" action=\"").append(OrgHandler.realUrl(nodeUrl, corgId, text)).append("\"");
				outSB.append("/>");
			}
		}
		outSB.append("</tree>");
		response.setContentType("text/xml");
		//lizb add write.close();
		PrintWriter write = response.getWriter();
		write.write(outSB.toString());
		write.flush();
		write.close();
	}
	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	}
}