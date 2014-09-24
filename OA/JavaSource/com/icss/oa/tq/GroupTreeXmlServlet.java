package com.icss.oa.tq;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.hr.handler.HRGroupHandler;
import com.icss.oa.hr.vo.HRSysPersonVO;

public class GroupTreeXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		HttpSession session = request.getSession();
		List parentlist = (List) session.getAttribute("parentlist");
		String hrgroup = (String) session.getAttribute("hrgroup");
		String hwnd = request.getParameter("hwnd");
		Connection conn = null;

		StringBuffer outSB = new StringBuffer();
		String parentid = request.getParameter("ParentID");
		outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		outSB.append("<tree>");

		try {
			Document document = DocumentHelper.parseText(hrgroup);
			Element rootElement = document.getRootElement();

			for (Iterator i_pe = rootElement.elementIterator(); i_pe.hasNext();) {
				Element e_pe = (Element) i_pe.next();
				if (e_pe.element("PARENTID").getText().equals(parentid)) {
					String name = new String(e_pe.element("ORGNAME").getText()
							.getBytes("GBK"), "ISO8859_1");
					outSB.append("<tree text=\"").append(name).append("\"");
					if (parentlist.contains(e_pe.element("GROUPID").getText())) {
						outSB
								.append(
										" src=\"../servlet/GroupTreeXmlServlet.xml?ParentID=")
								.append(e_pe.element("GROUPID").getText())
								.append("\"");
						outSB.append(" action=\"");
						outSB.append("\"");
					} else {
						outSB
								.append(
										" src=\"../servlet/GroupLastNodeXmlServlet.xml?groupid=")
								.append(e_pe.element("GROUPID").getText())
								.append("&amp;hwnd=").append(hwnd).append("\"");

						outSB.append(" action=\"");
						outSB.append("\"");
					}

					outSB.append("/>");

				}else{
					//只有一层的公共分组
					//取公共分组列表信息
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					HRGroupHandler handler = new HRGroupHandler(conn);
					List personlist = handler.getAllUuidByGroupid(parentid);
					List userlist =(List)session.getAttribute("userlist");

				if (personlist != null) {
					Iterator it = personlist.iterator();
					while (it.hasNext()) {
						HRSysPersonVO vo = (HRSysPersonVO) it.next();
						String personname = vo.getCnname();
						Integer tqid = vo.getTqid()==null?0:vo.getTqid();
						
						String text = new String(personname.getBytes("GBK"), "ISO8859_1");
						outSB.append("<tree text=\"").append(text).append("\"");
						outSB.append(" action=\"").append("javascript:doubleClick(").append(hwnd).append(",").append(tqid).append(",'").append(text).append("')").append("\"");
						if(userlist!=null&&userlist.contains(tqid.toString())){
						outSB.append(" icon=\"").append("../images/tq/msn_online.gif").append("\"");
						}
						else{
						outSB.append(" icon=\"").append("../images/tq/msn_offline.gif").append("\"");
						}
						outSB.append("/>");
					}
				}
					
				}
			}

			outSB.append("</tree>");

			response.setContentType("text/xml");
			PrintWriter write = response.getWriter();
			write.write(outSB.toString());
			write.flush();
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
	}
}