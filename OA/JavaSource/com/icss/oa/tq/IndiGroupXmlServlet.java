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
import com.icss.oa.tq.vo.TqSyspersonSearchVO;


public class IndiGroupXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuffer outSB = new StringBuffer();
		String groupid = request.getParameter("groupid");
		String hwnd=request.getParameter("hwnd");
		
		HttpSession session = request.getSession();
		List userlist =(List)session.getAttribute("userlist");
		
		outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("IndiGroupXmlServlet");
			
			//取个人分组列表信息
			TQHandler handler = new TQHandler(conn);
			List personlist = handler.personInGroupbyName(new Integer(groupid), "2");
			
			
		if (personlist != null) {
			Iterator it = personlist.iterator();
			while (it.hasNext()) {
				TqSyspersonSearchVO vo = (TqSyspersonSearchVO) it.next();
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
		outSB.append("</tree>");

		response.setContentType("text/xml");
		PrintWriter write = response.getWriter();
		write.write(outSB.toString());
		write.flush();
		write.close();

	} catch (ServiceLocatorException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally{
		try {
			if (conn != null) {
				conn.close();
				ConnLog.close("IndiGroupXmlServlet");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	}
	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	}
}