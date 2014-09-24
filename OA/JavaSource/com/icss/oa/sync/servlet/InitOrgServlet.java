package com.icss.oa.sync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.sync.handler.InitPersonHandler;
import com.icss.oa.sync.vo.PersonVO;

public class InitOrgServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		try {

			conn = this.getConnection(JNDI);
			ConnLog.open("InitOrgServlet");
			//ȡ��OA������Ա 
			InitPersonHandler handler = new InitPersonHandler(conn);
			List list = handler.getAllOAPerson();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				PersonVO vo = (PersonVO) it.next();
				//����HRIDȡ��DEPTCODE
				String deptcode =handler.getDeptCode(vo.getHrid());
				//����DEPTCODEȡ��ORGUUID 
				String orguuid =handler.getOrguuid(deptcode);
				//������Ա��֯��ϵ
				handler.updOrg(vo.getPersonuuid(),orguuid);
			}
			this.forward(request, response, "/syncperson/inithrid.jsp");

		} catch (Exception e) {

			PrintWriter out = response.getWriter();
			out.println(e);
			e.printStackTrace();

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("InitOrgServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
