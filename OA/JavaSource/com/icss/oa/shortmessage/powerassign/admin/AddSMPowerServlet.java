/*
 * Created on 2004-5-14
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.shortmessage.powerassign.handler.DXPowerHandler;
import com.icss.oa.shortmessage.powerassign.vo.DuanxinShortaccessVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddSMPowerServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn=null;
		String sessionname=request.getParameter("sessionname");
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddSMPowerServlet");
			HttpSession session=request.getSession();

			AddressHelp helper = new AddressHelp(conn);
			List sessionlist =
				helper.getperson(
					(List) session.getAttribute("sendtoperson"),
					request,
					"sendtoperson");
			Iterator iter=sessionlist.iterator();
			//List powerList=new ArrayList();
			String accessdepid=request.getParameter("depid");
			DXPowerHandler handler=new DXPowerHandler(conn);
			

			while(iter.hasNext()){
				SelectOrgpersonVO selectpersonVO=(SelectOrgpersonVO)iter.next();
				DuanxinShortaccessVO powerVO=new DuanxinShortaccessVO();
				String useruuid=selectpersonVO.getUseruuid();
				String orguuid=handler.getOrguuidBypersonuuid(useruuid);
				powerVO.setDepid(orguuid);
				powerVO.setPersonid(useruuid);
				powerVO.setAccessdepid(accessdepid);
				
				//System.out.println("name = "+selectpersonVO.getUserid());
				
				//把要插入表中的数据与表中已有数据进行比较
				List list2=handler.getSMPowerList();
				Iterator iter2=list2.iterator();
				int flag=1;
				while(iter2.hasNext()){
					DuanxinShortaccessVO vo2=(DuanxinShortaccessVO)iter2.next();
					if(vo2.getAccessdepid().equals(accessdepid)&&vo2.getPersonid().equals(useruuid))
					   	flag=0;				
				}
				if(flag==1)
				  handler.addPowerPerson(powerVO);
				
			}
			
			this.forward(request,response,"/servlet/ListOrgSMpersonServlet?orgid="+accessdepid);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("AddSMPowerServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
	
	
	
}
