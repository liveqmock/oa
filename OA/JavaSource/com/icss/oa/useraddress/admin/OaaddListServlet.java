package com.icss.oa.useraddress.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.useraddress.handler.UserAddressHandler;

public class OaaddListServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List userinfoList=null;
		try{	
        
		conn = this.getConnection(Globals.DATASOURCEJNDI);	
		ConnLog.open("OaaddListServlet");
		
		
		UserAddressHandler userinfohandler = new UserAddressHandler(conn);
		
		userinfoList=userinfohandler.getUserInfoList();
//		List userwordlist = userinfohandler.getUserRegionList();
		List deptlist=userinfohandler.getUserDeptList();
		request.setAttribute("userinfoList", userinfoList);
		List regionlist=new ArrayList();
		regionlist=userinfohandler.getUserRegionList();
		request.setAttribute("regionlist",regionlist);
//		request.setAttribute("userwordlist", userwordlist);
		request.setAttribute("deptlist",deptlist);


		this.forward(request, response, "/useraddress/showDetailList.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
				
					conn.close();
					ConnLog.close("OaaddListServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
