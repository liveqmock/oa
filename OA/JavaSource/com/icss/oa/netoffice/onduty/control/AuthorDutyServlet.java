/*
 * Created on 2004-12-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.netoffice.onduty.control;

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
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.config.OndutyConfig;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.resourceone.sdk.right.RightException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AuthorDutyServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;
		String orguuid = request.getParameter("orgUUid");
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AuthorDutyServlet");
			HttpSession session = request.getSession();
			AddressHelp helper = new AddressHelp(conn);
			List authorPerson = helper.getperson((List)session.getAttribute("selectForDuty"),request,"selectForDuty");
			Iterator Itr = authorPerson.iterator();
			String personuuid = "";
			DutyHandler dhandler = new DutyHandler(conn);
			StringBuffer authorSuccess = new StringBuffer();
			StringBuffer notAuthor = new StringBuffer();
			while(Itr.hasNext()){
				SelectOrgpersonVO vo = (SelectOrgpersonVO)Itr.next();
				personuuid = vo.getUseruuid();
				if(dhandler.hasRole(personuuid,OndutyConfig.AUTHORDUTY_ROLE)){
					notAuthor.append(",").append(vo.getName());
				}else{
					dhandler.dispatchRole(personuuid,OndutyConfig.AUTHORDUTY_ROLE);
					//写入权限表
					dhandler.addDutyRight(orguuid,personuuid);
					authorSuccess.append(",").append(vo.getName());
				}				
			}
			if(!("".equals(authorSuccess.toString()))){
				request.setAttribute("authorSuccess",authorSuccess.toString().substring(1));
			}
			if(!("".equals(notAuthor.toString()))){
				request.setAttribute("notAuthor",notAuthor.toString().substring(1));
			}
			request.setAttribute("orgUUid",orguuid);			
			this.forward(request,response,"/netoffice/onduty/authorResult.jsp");
			
		}catch (ServiceLocatorException e) {		
			e.printStackTrace();
			
		} catch (HandlerException e) {
			e.printStackTrace();
			
		} catch (RightException e) {
			e.printStackTrace();
			
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("AuthorDutyServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
