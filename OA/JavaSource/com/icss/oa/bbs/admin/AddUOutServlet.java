package com.icss.oa.bbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.BBSBoardHandler;
import com.icss.oa.bbs.vo.BbsOutVO;

public class AddUOutServlet extends ServletBase {

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BBSBoardHandler handler = new BBSBoardHandler(conn);

			if (request.getParameter("sendType") != null) {

				String s1 = request.getParameter("addPerson_person");
				String s2 = request.getParameter("addPerson_group");
				String s3 = request.getParameter("addPerson_org");
				String s4 = request.getParameter("addPerson_oagroup");

				Calendar rightNow = Calendar.getInstance();
				rightNow.add(Calendar.MONTH, 2);
				
				BbsOutVO vo = new BbsOutVO();
				Timestamp _time = new Timestamp(rightNow.getTimeInMillis());

				List<String> uuidlist = handler.getSendtoUUID(s1, s2, s3, s4);
				System.out.println(uuidlist.toString());

				for (String uuid : uuidlist) {
					String name = handler.getPersonName(uuid);
					if (name != null) {
						vo.setCnname(name);
						vo.setPersonuuid(uuid);
						vo.setTime(_time);
						handler.insertOutVO(vo);
					}
				}

			}
			response.sendRedirect("/oabase/servlet/UserOutServlet");
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

	}

}