package com.icss.oa.address.admin;

import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.vo.*;

/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DelOrgpersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		String doFunction = "";
		if (request.getParameter("doFunction") != "") {
			doFunction = request.getParameter("doFunction");
		}
		System.out.println("servlet_doFunction=" + doFunction);
		String sessionname = "";
		if (request.getParameter("sessionname") != "") {
			sessionname = request.getParameter("sessionname");
		}
		System.out.println("ChooseCommonOrgServlet_sessionname=" + sessionname);

		int tempflag = 1;
		List selectorgpersonlist = new ArrayList();
		SelectOrgpersonVO vo = new SelectOrgpersonVO();
		HttpSession session = request.getSession();
		selectorgpersonlist = (List) session.getAttribute(sessionname);
		if (selectorgpersonlist == null)
			selectorgpersonlist = new ArrayList();
		Connection conn = null;

		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			ConnLog.open("DelOrgpersonServlet");
			if (request.getParameterValues("personid") != null) {
				String[] userid = request.getParameterValues("personid");
				System.out.println("userid.length=" + userid.length);
				for (int i = 0; i < userid.length; i++) {
					tempflag = 1;
					Iterator iterator = selectorgpersonlist.iterator();
					while (iterator.hasNext()) {
						vo = (SelectOrgpersonVO) iterator.next();
						System.out.println("userid[" + i + "]=" + userid[i]);
						System.out.println("vo.getUserid()=" + vo.getUserid());
						if (userid[i].equals(vo.getUserid())) {
							tempflag = 0;//检查是否有相同的userid
							break;
						}//if
					}//while
					if (tempflag == 0) {
						selectorgpersonlist.remove(vo);
					}
				}//for
			}
			request.setAttribute(sessionname, selectorgpersonlist);
			session.setAttribute(sessionname, selectorgpersonlist);
			this.forward(request, response,
					"/address/pubaddress/selected.jsp?doFunction=" + doFunction
							+ "&sessionname=" + sessionname);
		} catch (Exception ex) {
			ex.printStackTrace();
			handleError(ex);
			
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelOrgpersonServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			//
		}
	}

}
