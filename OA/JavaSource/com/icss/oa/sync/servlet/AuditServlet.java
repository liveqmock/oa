package com.icss.oa.sync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.icss.common.log.ConnLog;
import com.icss.common.util.DateUtility;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.sync.dao.OrgSyncDAO;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.dao.PersonSyncDAO;
import com.icss.oa.sync.dao.SysPersonDAO;
import com.icss.oa.sync.handler.UserSyncHandler;
import com.icss.oa.sync.vo.OrgSyncVO;
import com.icss.oa.sync.vo.PersonSyncVO;
import com.icss.oa.sync.vo.PersonTempVO;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.oa.sync.vo.SysPersonVO;

/**
 * @author 范登勇
 *
 * 将审批后的临时表中的记录进行处理
 *  
 */

public class AuditServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method==null){
			method = "";
		}
		
		if(method.equals("audit")){
			audit(request,response);
		}
		
	}
	
	
	
	protected void audit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		//PrintWriter out = response.getWriter();
		String JNDI = "jdbc/ROEEE";
		int i;
		String type = request.getParameter("type");
		try {

			conn = this.getConnection(JNDI);
			ConnLog.open("AudiServlet");

			UserSyncHandler handler = new UserSyncHandler(conn);
			String[] syncuser = request.getParameterValues("syncuser");
			
			if (type != null && "no".equals(type)) {
				for (int j = 0; j < syncuser.length; j++) {
					// 将通过审批的记录取出来，调用handler的处理函数进行处理
					Integer id = new Integer(syncuser[j]);
					handler.updateFlag(id, -1);
				}

			} else {
			
			for (i = 0; i < syncuser.length; i++) {
				System.out.println("+++++++++++++syncuser"+syncuser[i]);
				//将通过审批的记录取出来，调用handler的处理函数进行处理
				Integer id = new Integer(syncuser[i]);
				
				PersonTempVO vo = handler.getPersonTempVOById(id);
				
				boolean flag=false;
				System.out.println("++++++++++++++++"+flag);
				flag = handler.OperateSyncUser(vo);
				if (flag) {
					System.out.println("++++++++++++++++"+id);

					//如果操作完成，则标志为已经通过
					handler.updateFlag(id,1);
					System.out.println("++++++++++++++++"+id);

					}
			}

			}
			response.sendRedirect("/oabase/servlet/GetSearchUserServlet");

		} catch (Exception e) {

			PrintWriter out = response.getWriter();
			out.println(e);

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("AudiServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		} 
		
	}
	
	
	

}
