/*
 * Created on 2004-5-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.log.admin;   

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;
import com.icss.oa.addressbook.vo.AddressbookTreeVO;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.vo.LogSysTreeVO;
import com.icss.oa.log.vo.LogSysVO;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class LoadLogSysTreeXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		StringBuffer outSB = new StringBuffer();

		outSB.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List list = null;
		List treeList = null;
		Integer parentId = request.getParameter("parentId")==null?new Integer(-1):new Integer(request.getParameter("parentId"));
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			LogHandler handler = new LogHandler(conn);
			System.out.println("::::::::::list1:::::::;"+parentId);
			if (new Integer(1).equals(parentId)) {
					list =	handler.getTopSysList();
				
					System.out.println("::::::::::list1:::::::;"+list.size());
				
			}else{
					list=handler.getSysList(parentId);
					System.out.println("::::::::::list2:::::::;"+list.size());
					
			}
			treeList = handler.buildXloadTreeList(list);

			Iterator it = treeList.iterator();
			
			while (it.hasNext()) {
				System.out.println("::::::::::treelist:::::::;"+treeList.size());
				LogSysTreeVO vo = (LogSysTreeVO) it.next();
				String isName = vo.getLogSysVO().getSysName();
				outSB.append("<tree text=\"");
					outSB.append(new String(isName.getBytes("GB2312"), "ISO8859_1"));
					outSB.append("\"");
					
					if (vo.isHasChild()) {
						outSB.append(" src=\"../servlet/LoadLogSysTreeXmlServlet.xml?parentId=");
						outSB.append(vo.getLogSysVO().getSysId());
							
						outSB.append("\"");
						System.out.println("++++++++there has child+++++++++");
					}
					
					outSB.append(
						" action=\"ShowSysMsgServlet?parentId="
							+ vo.getLogSysVO().getSysId()
							
							+ "\"");
					
				
				outSB.append("/>");
				
			}
			outSB.append("</tree>");
			System.out.println("========outSB=========>" + outSB);
			response.setContentType("text/xml");
			response.getWriter().write(outSB.toString());

		} catch (Exception e) {
			System.out.println("errormsg==========");
			e.printStackTrace();
		} finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	protected void performTask(
		HttpServletRequest arg0,
		HttpServletResponse arg1)
		throws ServletException, IOException {
	}
}