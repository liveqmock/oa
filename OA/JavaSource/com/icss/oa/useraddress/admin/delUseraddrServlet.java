package com.icss.oa.useraddress.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.useraddress.handler.UserAddressHandler;
import com.icss.oa.useraddress.vo.OaaddListVO;

public class delUseraddrServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			Integer odid = new Integer(request.getParameter("odid"));
					
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("delUseraddrServlet");		
				UserAddressHandler handler = new UserAddressHandler(conn);
				OaaddListVO vo = new OaaddListVO();
				
				handler.delete(odid);
				response.sendRedirect("OaaddListServlet?_page_num="+request.getParameter("_page_num"));
				
			} catch (Exception e) {
				e.printStackTrace();
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("delUseraddrServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

	}

}
