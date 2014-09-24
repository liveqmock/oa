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
import com.icss.oa.phonebook.vo.PhoneJobnameVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateOrgjobServlet extends ServletBase{
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			
		//pd页面的隐藏参数----update
		String pd = request.getParameter("pd");
		//职位Id
		Integer nameId = new Integer(request.getParameter("nameId"));
		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UpdateOrgjobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			/*判断是否点击修改
			 * oldjobId页面的隐藏参数---修改前的jobId
			 * jobname职级的名字
			 * jobid 职级的id
			 * */
			if("update".equals(pd)){
				Integer oldjobId = new Integer(request.getParameter("oldjobId"));
				String jobname = request.getParameter("name");
				Integer jobid = new Integer(request.getParameter("jobid"));
				if(oldjobId==jobid){  //用户没有改变职位的职级
					phandler.updateOrgjob(nameId,jobname,jobid);
				}else{       //用户改变了职位的职级
					phandler.updateOrgjob(nameId,jobname,jobid);
					//更新电话记录表,得新的排序(NOTEORDER)
					PhoneJobnameVO vo = phandler.getOrgjobVO(nameId);
					phandler.setNewNameidsForInfo(vo,"update");
				}
				this.forward(request,response,"/phonebook/addPhoneClose.jsp");
			}else{
				PhoneJobnameVO pvo = phandler.getOrgjobVO(nameId);
				Integer nameid = pvo.getNameid();
				Integer jobId = pvo.getJobid();
				String name = pvo.getName();
				
				List jobidlist = phandler.GetAllJobGeneral();
				request.setAttribute("jobidList",jobidlist);
				String orguuid = (phandler.getOrgjobVO(nameId)).getOrguuid();
				//得到本部门所有职务的名称
				List jobnamelist = phandler.getOrgjobname(orguuid);
				request.setAttribute("jobnameList",jobnamelist);
				this.forward(request,
							response,
							"/phonebook/updateOrgjob.jsp?nameId="+nameId.toString()+"&jobId="+jobId.toString()+"&name="+name);
			}
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("UpdateOrgjobServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
