/*
 * Created on 2004-5-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.control;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.ShareShareaccessVO;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class LoadShareRootXmlServlet extends ServletBase {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		StringBuffer outSB = new StringBuffer();

		outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List shareList = null;
		List treeList = null;

		try {
			//System.out.println("------------");
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("LoadShareRootXmlServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			shareList = handler.getShareList(userId);

			//System.out.println("-------" + shareList.toString());

			Iterator it = shareList.iterator();

			String DouSharename[] = new String[200];

			int i = 0;

			while (it.hasNext()) {
				ShareShareaccessVO vo = (ShareShareaccessVO) it.next();
				String isName = vo.getFsName();

				//System.out.println("---------" + isName);

				int nPosition = 0;
				nPosition = isName.lastIndexOf(":");
				DouSharename[i] = isName;
				i = i + 1;

				boolean Douflag = false;

				for (int j = 0; j < i - 1; j++) {
					if(DouSharename[j].length()>nPosition){
					if ((isName.substring(0, nPosition)).equals(DouSharename[j]
							.substring(0, nPosition))) {
						Douflag = true;
						break;
					}
					}
				}

				if (Douflag) {
					continue;
				}

				outSB.append("<tree text=\"").append(
						new String((isName.substring(0, nPosition))
								.getBytes("GBK"), "ISO8859_1")).append("\"");

				outSB.append(
						" src=\"../servlet/LoadShareXmlServlet.xml?folderId=")
						.append(vo.getFpId()).append("&amp;accessFlag=")
						.append(vo.getFscAccessright()).append(
								"&amp;shareFlag=1").append("&amp;shareName=")
						// .append(isName.substring(nPosition+1))
						.append(
								new String((isName.substring(0, nPosition))
										.getBytes("GBK"), "ISO8859_1")).append(
								"\"");
				outSB.append(
						" action=\"ShowShareRootFileServlet?folderId="
								+ vo.getFpId()
								+ "&amp;accessFlag="
								+ vo.getFscAccessright()
								+ "&amp;shareFlag=1"
								+ "&amp;entrance=kk"
								+ "&amp;shareName="
								+ new String((isName.substring(0, nPosition))
										.getBytes("GBK"), "ISO8859_1") + "\"")
						.append("/>");
				// bw.write("e:"+outSB.toString()+"\n");
				// bw.flush();
				//System.out.println("^^^^^^^" + outSB.toString());

			}
			outSB.append("</tree>");

			response.setContentType("text/xml");
			response.getWriter().write(outSB.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("LoadShareRootXmlServlet");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
	}
}