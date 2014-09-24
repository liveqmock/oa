/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.maintenance.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

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
public class AddOrgSMCodeServlet extends ServletBase{
	
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		String orgId = request.getParameter("zuzhiId");
		String sMCode = request.getParameter("shortMessCode");
		List list=null;
		DuanxinShortmappingVO smVO=new  DuanxinShortmappingVO();
		int flag=0;
		try{
			conn = getConnection(Globals.DATASOURCEJNDI);
			ShortMessageOrgCodeHandler handler=new ShortMessageOrgCodeHandler(conn);
			list=handler.getOrgIdSMCodeList();
			if(!(list.isEmpty())){
				Iterator iter=list.iterator();
				//compare with "DEPID" and "SMCODE" existed in table of datebase
				while(iter.hasNext()){
					smVO=(DuanxinShortmappingVO)iter.next();
					if(orgId.equals(smVO.getDepid())||sMCode.equals(smVO.getSmCode())){
						flag=1;
						break;
					}
					
				}
				
				
				if(flag!=1){
					
					smVO.setDepid(orgId);
					smVO.setSmCode(sMCode);
					handler.addSMCode(smVO);					
				}
							
			}
			else{
				smVO.setDepid(orgId);
				smVO.setSmCode(sMCode);
				handler.addSMCode(smVO);
				
			}
			
			this.forward(request,response,"/servlet/ShowShortMessageOrgCodeServlet?flag="+flag);
			
		}
		catch(Exception e){
			e.printStackTrace();
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
