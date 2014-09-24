/*
 * Created on 2004-12-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.user.control;

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
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.vo.BbsBoardVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ShowTreeOfAreaServlet extends ServletBase {

	public ShowTreeOfAreaServlet() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer outSB = new StringBuffer();
		Integer areaId = new Integer(request.getParameter("areaId"));
		String personuuid = request.getParameter("personuuid");
		String nodeUrl = request.getParameter("nodeUrl");
		outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		outSB.append("<tree>");
		Connection conn = null;
		List list = null;
		BBSHandler handler = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowTreeOfAreaServlet");
			handler = new BBSHandler(conn);
			list = handler.getBoardByAreaidList(areaId, personuuid);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowTreeOfAreaServlet");
				}
			} catch (SQLException sqlexception) {
			}
		}
		for (Iterator it = list.iterator(); it.hasNext(); outSB.append("/>")) {
			BbsBoardVO vo = (BbsBoardVO) it.next();
			String boardName = vo.getBoardname();
			String text = new String(boardName.getBytes("GBK"), "ISO8859_1");
			outSB.append("<tree text=\"");
			outSB.append(text);
			outSB.append("\"");
			//	         outSB.append(" src=\"../servlet/MsgViewTreeRootXmlServlet2.xml?orgId=").append(vo.getOrguuid()).append("\"");
			outSB.append(" action=\"");
			outSB.append(nodeUrl + "?boardId=" + vo.getBoardid().toString() + "&amp;primeFlag=0");
			outSB.append("\"");

			outSB.append(" icon=\"");
			outSB.append(request.getContextPath() + "/images/bbs/tree/board.gif");
			outSB.append("\"");

			outSB.append(" openIcon=\"");
			outSB.append(request.getContextPath() + "/images/bbs/tree/area.gif");
			outSB.append("\"");

		}

		outSB.append("</tree>");
		response.setContentType("text/xml");

		response.getWriter().write(outSB.toString());
	}

	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

	}

}
