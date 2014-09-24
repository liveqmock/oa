/*
 * Created on 2004-5-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.allrecord.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.shortmessage.allrecord.handler.AllDuanxinHistoryHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DelSMRecordServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		String smId[]=request.getParameterValues("smChe");
		String orgid=request.getParameter("orgid");
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelSMRecordServlet");
			AllDuanxinHistoryHandler handler=new AllDuanxinHistoryHandler(conn);
			for(int j=0;j<smId.length;j++){
				  Integer smid =  Integer.valueOf(smId[j]);
				  handler.deletesmRecord(smid);
			}
						
			this.forward(request,response,"/servlet/ListOrgSMRecordServlet?orgid="+orgid);
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelSMRecordServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	
	}
	
}
