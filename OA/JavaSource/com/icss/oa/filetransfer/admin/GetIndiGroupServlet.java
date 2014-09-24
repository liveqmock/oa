/*
 * Created on 2005-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.admin;

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
import com.icss.oa.address.handler.Group;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetIndiGroupServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException{
	// TODO Auto-generated method stub
	
	Connection conn = null;
	Context ctx = null;

	try {
		
		conn = this.getConnection(Globals.DATASOURCEJNDI);
		ConnLog.open("GetIndiGroupServlet");
		ctx = Context.getInstance();
		UserInfo curUser = ctx.getCurrentLoginInfo();
		String curUserId = curUser.getUserID();
		String curUserName = curUser.getCnName();
	
		String fromPerson = request.getParameter("from");
	
		int index1 = fromPerson.indexOf("<");
		int index2 = fromPerson.indexOf("@");
		String userid = fromPerson.substring(index1+1,index2);

		//依邮箱设置表，由邮箱的用户名得到数据库中通用的用户名，
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
		String senduuid = ftsHandler.getAllUUid(userid);
		String result = "";

		Group group1 = new Group(conn);
		List indiGroupList = group1.individualGroupList(curUser.getPersonUuid());
		request.setAttribute("indiGroupList",indiGroupList);
		request.setAttribute("personUUid",senduuid);
		this.forward(request,response,"/mail/ListIndiGroup.jsp");
		
	} catch (ServiceLocatorException e) {
		e.printStackTrace();
	} catch (EntityException e) {
		e.printStackTrace();
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally{
		try {
			if(conn!=null){
				conn.close();
				ConnLog.close("GetIndiGroupServlet");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}

}
