/*
 * Created on 2004-5-13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.shortmessage.powerassign.handler.DXPowerHandler;
import com.icss.oa.shortmessage.powerassign.vo.DuanxinShortaccessVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DelSMPowerServlet extends ServletBase{
	
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		String smId[]=request.getParameterValues("smChe");
		
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelSMPowerServlet");
			DXPowerHandler dxHandler=new DXPowerHandler(conn);
			DuanxinShortaccessVO smvo=dxHandler.getVObyId(new Integer(smId[0]));
			String orgid=smvo.getDepid();
			
			for(int j=0;j<smId.length;j++){
			  Integer smid =  Integer.valueOf(smId[j]);
			 
			  dxHandler.deletesmPower(smid);
			}
			
			
		this.forward(request,response,"/servlet/ListOrgSMpersonServlet?orgid="+orgid);
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelSMPowerServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
}
