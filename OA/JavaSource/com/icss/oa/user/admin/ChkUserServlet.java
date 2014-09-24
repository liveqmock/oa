package com.icss.oa.user.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.UnixPwdCrypt;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.user.dao.PersonAccountDAO;

public class ChkUserServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String JNDI = "jdbc/ROEEE";

		PrintWriter out = response.getWriter();

		try {
			conn = this.getConnection(JNDI);
			ConnLog.open("ChkUserServlet");

			Context ctx = Context.getInstance();
			String uuid = ctx.getCurrentPersonUuid();

			String password = request.getParameter("Password").trim();

			PersonAccountDAO personaccountdao = new PersonAccountDAO();
			personaccountdao.setPersonuuid(uuid);
			DAOFactory daofactory = new DAOFactory(conn);
			daofactory.setDAO(personaccountdao);
			PersonAccountDAO personaccountdao1 = (PersonAccountDAO) daofactory
					.findByPrimaryKey();
			if (UnixPwdCrypt
					.validate(password, personaccountdao1.getPassword())) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (Exception e) {
			out.println(e);
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ChkUserServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
