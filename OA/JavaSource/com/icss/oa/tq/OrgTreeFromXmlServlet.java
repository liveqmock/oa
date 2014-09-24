package com.icss.oa.tq;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.tq.handler.TQHandler;
import com.icss.oa.tq.vo.TqOrgpersonVO;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgTreeVO;


public class OrgTreeFromXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuffer outSB = new StringBuffer();
		String orgId = request.getParameter("orgId");
		outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List list = null;
		List name=null;
		OrgHandler handler = null;
		TQHandler tqhandler =null;
		String hwnd=request.getParameter("hwnd");
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OrgTreeFromXmlServlet");
			handler = new OrgHandler(conn);
			list = handler.getOrgTreeList(orgId);
			
			//取人员列表
			tqhandler = new TQHandler(conn);
			name = tqhandler.getOrgPerson(orgId);
			
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("OrgTreeFromXmlServlet");
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
					outSB.append(" src=\"../servlet/OrgTreeFromXmlServlet.xml?orgId=").append(corgId).append("&amp;hwnd=").append(hwnd).append("\"");
				}
				else{
					outSB.append(" src=\"../servlet/OrgLastNodeXmlServlet.xml?orgId=").append(corgId).append("&amp;hwnd=").append(hwnd).append("\"");
				}
				outSB.append(" action=\"").append("\"");
				outSB.append("/>");
			}
		}
		if (name != null) {

			HttpSession session = request.getSession();
			List userlist = (List) session.getAttribute("userlist");

			Iterator it = name.iterator();
			while (it.hasNext()) {
				TqOrgpersonVO vo = (TqOrgpersonVO) it.next();
				String corgName = vo.getCnname();
				Integer tqid = vo.getTqid() == null ? 0 : vo.getTqid();
				String text = new String(corgName.getBytes("GBK"), "ISO8859_1");
				outSB.append("<tree text=\"").append(text).append("\"");
				outSB.append(" action=\"").append("javascript:doubleClick(")
						.append(hwnd).append(",").append(tqid).append(",'")
						.append(text).append("')").append("\"");
				if (userlist != null && userlist.contains(tqid.toString())) {
					outSB.append(" icon=\"").append(
							"../images/tq/msn_online.gif").append("\"");
				} else {
					outSB.append(" icon=\"").append(
							"../images/tq/msn_offline.gif").append("\"");
				}
				outSB.append("/>");
			}

			outSB.append("</tree>");

			response.setContentType("text/xml");
			PrintWriter write = response.getWriter();
			write.write(outSB.toString());
			write.flush();
			write.close();
		}
	}
	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	}
}