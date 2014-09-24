/*
 * Created on 2004-5-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.maintenance.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.shortmessage.maintenance.handler.ShortMessageOrgCodeHandler;
import com.icss.oa.shortmessage.maintenance.vo.DuanxinShortmappingVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class UpdateOrgSMCodeServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		String smId=request.getParameter("radiobutton");
		String orgId=request.getParameter("zuzhiId");
		String smCode=request.getParameter("shortMessCode");
		DuanxinShortmappingVO smVO=new  DuanxinShortmappingVO();
		
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			smVO.setSmId(new Integer(smId));
			smVO.setDepid(orgId);
			smVO.setSmCode(smCode);
			ShortMessageOrgCodeHandler handler=new ShortMessageOrgCodeHandler(conn);
			handler.updateSMcode(smVO);
			
			this.forward(request,response,"/servlet/ShowShortMessageOrgCodeServlet");
		} 
		catch (Exception e) {
			//System.out.println(e.toString());
			handleError(e);
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}
