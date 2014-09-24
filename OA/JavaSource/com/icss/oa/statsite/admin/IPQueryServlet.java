package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.ipManagerHandler;

public class IPQueryServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			String ip = request.getParameter("ip");
			
			StringTokenizer st_StartIP = new StringTokenizer(ip,".");
			StringBuffer sb_StartIP = new StringBuffer();
			while(st_StartIP.hasMoreTokens()){				
				String sb1 =st_StartIP.nextToken();
				int sbLength = sb1.length();
				switch (sbLength){
				  case 1:
				  	sb_StartIP.append("00");
				  	sb_StartIP.append(sb1);
				      	break;
				  case 2:
				  	sb_StartIP.append("0");
				  	sb_StartIP.append(sb1);
			      		break;  
				  case 3:
		      	    sb_StartIP.append(sb1);
		      			break;  
				}
			}		
		//	System.out.println("dddddddddddddddddd              "+sb_StartIP.toString());
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);		
				ConnLog.open("IPQueryServlet");
				ipManagerHandler handler = new ipManagerHandler(conn);
				if((Long.parseLong(sb_StartIP.toString()))>255255255256L||(Long.parseLong(sb_StartIP.toString()))<1255255255L)
				{
					this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址有误，请从新输入!");
					}
				List list =handler.getIPList1(handler.IsAdress1(sb_StartIP.toString()));
				request.setAttribute("list",list);
				List list1 = handler.getIPList();
				request.setAttribute("list1",list1);
				List list2 = handler.getIPList1();
				request.setAttribute("list2",list2);

				this.forward(request,response,"/statsite/IPmanager.jsp");
				
			} catch (Exception e) {
				//this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址有误，请从新输入!");
				handleError(e);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				try {
					this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址有误，请从新输入!");
					if (conn != null) {
						conn.close();
						ConnLog.close("IPQueryServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

	}

}
