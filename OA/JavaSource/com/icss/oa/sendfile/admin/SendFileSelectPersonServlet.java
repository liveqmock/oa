package com.icss.oa.sendfile.admin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.config.AddressConfig;
/**
 * 
 * @author lizb
 *
 * 为要选择的页面放入数据
 */
public class SendFileSelectPersonServlet extends ServletBase {
	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws  javax.servlet.ServletException,java.io.IOException {
		
		
		//取组织列表信息
		String orgname = request.getParameter("orgname");
		String orguuid = request.getParameter("orgid");
		String all = request.getParameter("all");
		System.out.println("1111111:"+orgname+","+orguuid+","+all);
		String[] messageid = request.getParameterValues("allperson");
		String addressjndi = getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI);
		Connection conn = null;
		try {
			if ( addressjndi == null || "".equals(addressjndi) ) {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SelectPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(addressjndi);
				ConnLog.open("SelectPersonServlet");
			}
			//-------------------------------------------------------------------------------------
			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			
			List list = null;
			
			if(messageid!=null&&"All Person".equals(messageid[0])){
				 list = handler.getList1(orguuid);
			}else{
				 list = handler.getList(orguuid);
			}
			System.out.println("2222:"+list.size());
			//-------------------------------------------------------------------------------------
			request.setAttribute("personlist", list);
			request.setAttribute("orgname",  orgname);
			request.setAttribute("orguuid",  orguuid);
			//forward(request, response, "/address/sendfile/selectperson.jsp?orguuid=" + orguuid + "&orgname=" + orgname);
			this.getServletContext().getRequestDispatcher("/address/sendfile/selectperson.jsp?orguuid=" + orguuid).forward(request,response);
		} catch (IOException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (javax.servlet.ServletException e) {
			e.printStackTrace();
			handleError(e);
			
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SelectPersonServlet");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}


