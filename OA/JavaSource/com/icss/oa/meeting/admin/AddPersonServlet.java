/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;
import com.icss.oa.meeting.vo.MeetingPersonVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddPersonServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		HttpSession session = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String putId = request.getParameter("putId");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			session = request.getSession();
			AddressHelp helper = new AddressHelp(conn);
			List list =
				helper.getperson(
					(List) session.getAttribute("selectorgperson"),
					request,
					"selectorgperson");

			this.addPerson(putId ,list,handler);

			this.forward(request, response, "/servlet/ListMeetingPersonServlet");
			
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
			handleError(ne);
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (session != null) {
				session.setAttribute(
					"selectorgperson",
					new java.util.ArrayList(0));
			}
		}
	}
	
	private void addPerson(String putid, List list ,MeetingroomManagerHandler handler) throws HandlerException {
		
		MeetingPersonVO vo = new MeetingPersonVO();
		
		if (list != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) it.next();
				vo.setMeetingId(new Integer(putid));
				vo.setPerson(selectOrgpersonVO.getUseruuid());
				vo.setPerson1(selectOrgpersonVO.getUserid());
				vo.setBaoming("未报名");
				vo.setDaohui("未到会");
				vo.setOrder1(new Integer(10000));
				if(!handler.isExist(new Integer(putid),selectOrgpersonVO.getUseruuid()))
				           handler.addMeetingPerson(vo);
			}
		}
	}
	
	

}
