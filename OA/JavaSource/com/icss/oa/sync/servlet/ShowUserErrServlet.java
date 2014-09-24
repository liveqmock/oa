package com.icss.oa.sync.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.sync.dao.SynUserErrDAO;
import com.icss.oa.sync.vo.SynUserErrVO;

public class ShowUserErrServlet extends ServletBase {
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) {

		Connection conn = null;

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			SynUserErrDAO dao = new SynUserErrDAO();
			DAOFactory ft = new DAOFactory(conn);
			ft.setDAO(dao);
			List list = new ArrayList();
			list = ft.find(new SynUserErrVO());

			request.setAttribute("userlist", list);

			this.forward(request, response, "/syncperson/UserErr.jsp");

		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}