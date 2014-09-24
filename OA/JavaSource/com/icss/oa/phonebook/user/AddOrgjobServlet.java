/*
 * Created on 2004-12-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddOrgjobServlet extends ServletBase{
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		/*
		 * orguuid ��֯id
		 * pd ҳ��������ֶ�---��add��
		 * */
		
		Connection conn = null;
		String orguuid = request.getParameter("orguuid");
		String pd = request.getParameter("pd");
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddOrgjobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			/*
			 *  jobIdְ����Id
			 *  nameְλ������
			 * */
			if("add".equals(pd)){
				Integer jobId = new Integer(request.getParameter("jobid"));
				String name = request.getParameter("name");
				phandler.addOrgjob(name,jobId,orguuid);
				this.forward(request,response,"/phonebook/addPhoneClose.jsp");
			}else{
				//���е�ְ����ID
				List jobidlist = phandler.GetAllJobGeneral();
				request.setAttribute("jobidList",jobidlist);
				//�õ�����������ְ�������
				List jobnamelist = phandler.getOrgjobname(orguuid);
				request.setAttribute("jobnameList",jobnamelist);
				
				request.setAttribute("orguuid",orguuid);
				this.forward(request,response,"/phonebook/newOrgjob.jsp");
			}
			
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
			
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("AddOrgjobServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
