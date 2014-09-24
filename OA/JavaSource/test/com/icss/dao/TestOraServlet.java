/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test.com.icss.dao;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestOraServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("TestOraServlet");
			TestDAO dao = new TestDAO();
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			List list = null;
			try {
				list = factory.find(new TestVO());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("list", list);
			this.forward(request, response, "/oatestdb.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("TestOraServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
