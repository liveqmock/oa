package com.icss.oa.bbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.BBSBoardHandler;
import com.icss.oa.bbs.vo.BbsOutVO;

public class UserOutServlet extends ServletBase {

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BBSBoardHandler handler = new BBSBoardHandler(conn);
			
			List list = handler.getOutList();
			for(Iterator it = list.iterator();it.hasNext();){
				BbsOutVO vo = (BbsOutVO)it.next();
				if(vo.getTime().before(new Date())){
					handler.delUserOUT(vo.getPersonuuid());
				}
			}
			
			list = handler.getOutList();
			request.setAttribute("userlist", list);
			this.forward(request, response, "/bbs/userout.jsp");

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