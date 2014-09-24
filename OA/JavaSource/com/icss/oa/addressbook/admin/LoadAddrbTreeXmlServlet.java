/*
 * Created on 2004-5-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.addressbook.admin;   

import java.io.IOException;
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
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;
import com.icss.oa.addressbook.vo.AddressbookTreeVO;
import com.icss.oa.bbs.handler.UserMsgHandler;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class LoadAddrbTreeXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		StringBuffer outSB = new StringBuffer();

		outSB.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List list = null;
		List treeList = null;
		Integer folderId = new Integer(request.getParameter("folderId"));
		//String accessFlag = request.getParameter("accessFlag");
		//String shareFlag = request.getParameter("shareFlag");
		//if (accessFlag == null) {
		//	accessFlag = new String("1");
		//}
		//if (shareFlag == null) {
		//	shareFlag = new String("0");
		//}
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("LoadAddrbTreeXmlServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);
			//System.out.println("::::::::::list1:::::::;"+folderId);
			if (folderId.intValue() == 1) {
				
				UserMsgHandler userHandler = new UserMsgHandler(conn);
				String userId = userHandler.getUserId();
				List abmList = handler.getUserList(userId);
				Iterator fit = abmList.iterator();
				AddressbookManagerVO abmvo = null;
				if (fit.hasNext()) {
					abmvo = (AddressbookManagerVO) fit.next();
				}
				if (abmvo != null) {

					list =
							handler.getFolderList(new Integer(1), abmvo.getAbmId());
				
					//System.out.println("::::::::::list1:::::::;"+list.size());
				}
			} 	else {
					list = 	handler.getFileList(folderId);
				//System.out.println("::::::::::list2:::::::;"+list.size());
			}
			treeList = handler.buildXloadTreeList(list);

			Iterator it = treeList.iterator();
			
			while (it.hasNext()) {
				//System.out.println("::::::::::treelist:::::::;"+treeList.size());
				AddressbookTreeVO vo = (AddressbookTreeVO) it.next();
				if (vo.getAddressbookFolderVO().getAbfFlag().equals("1")) {
					String isName = vo.getAddressbookFolderVO().getAbfName();
					outSB.append("<tree text=\"");
					outSB.append(new String(isName.getBytes("GB2312"), "ISO8859_1"));
					outSB.append("\"");
					
					if (vo.getHasChild()) {
						outSB.append(" src=\"../servlet/LoadAddrbTreeXmlServlet.xml?folderId=");
						outSB.append(vo.getAddressbookFolderVO().getAbfId());
							//.append("&amp;accessFlag=")
							//.append(accessFlag)
							//.append("&amp;shareFlag=")
							//.append(shareFlag)
						outSB.append("\"");
							//System.out.println("++++++++there has child+++++++++");
					}
					
					outSB.append(
						" action=\"ShowAddressbookListServlet?parentId="
							+ vo.getAddressbookFolderVO().getAbfId()
							//+ "&amp;accessFlag="
							//+ accessFlag
							//+ "&amp;shareFlag="
							//+ shareFlag
							+ "\"");
					
				//	if (shareFlag.equals("0") && vo.getIsShare()) {
					//	outSB.append(
					//		" icon=\"../folder/tree/images/shareClose.gif\"");
					//	outSB.append(
					//		" openIcon=\"../folder/tree/images/shareOpen.gif\"");
				//	}
					outSB.append("/>");
				}
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
					ConnLog.close("LoadAddrbTreeXmlServlet");
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