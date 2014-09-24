/*
 * Created on 2004-8-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.phonebook.vo.PhoneJobVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdatePhoneJobServlet extends ServletBase{

	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	   throws ServletException, IOException {
	   	
		Connection conn = null;
		Integer jobId = new Integer(request.getParameter("jobId"));

		String jobName = request.getParameter("jobName");
		try {	
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UpdatePhoneJobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			
			if(jobName==null){
				String jName = phandler.GetJobName(jobId);
				Integer jLevel = phandler.GetJobLevel(jobId);
				this.forward(request,response,"/phonebook/updatePhoneJob.jsp?jobId="+jobId+"&jobLevel="+jLevel+"&jobName="+jName);
			}else{
				Integer oldJobLevel = new Integer(request.getParameter("oldJobLevel"));
				Integer jobLevel = new Integer(request.getParameter("jobLevel"));
				//更新一条记录
				phandler.UpdateJob(jobId,jobLevel,jobName);
				//判断用户是否修改了jobLevel
				if(oldJobLevel!=jobLevel){ //有修改
					//更新相关的电话记录的排序字段
					PhoneJobVO pvo = phandler.getVOByJobId(jobId);
					//phandler.setInfoJobid(pvo,"update");
				}
				this.forward(request,response,"/phonebook/addPhoneClose.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("UpdatePhoneJobServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
	}

}
