/*
 * Created on 2004-4-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;    

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressTransferVO;

/**
 * @author sunchuanting
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class AddTransfServlet extends ServletBase {    

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);  
				ConnLog.open("AddTransfServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String groupid = request.getParameter("groupid");
			Group group = new Group(conn);
			
			String checkid = request.getParameter("paraid");
			
			AddressTransferVO vo = new AddressTransferVO();
			
		
			vo.setPersonuuid(checkid);
			vo.setCommongroup(new Integer(groupid));	  			
			group.addTransfer(vo);
		
		    this.forward(request, response, "/servlet/ListGroupTransfServlet");
		    
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
					ConnLog.close("AddTransfServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				//throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}
}
