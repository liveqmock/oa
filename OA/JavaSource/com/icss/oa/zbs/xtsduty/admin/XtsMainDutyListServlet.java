package com.icss.oa.zbs.xtsduty.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.zbs.xtsduty.handler.XtsWorkInfoHandler;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfomainVO;

/**
 * @version 	1.0
 * @author		liupei
 */
public class XtsMainDutyListServlet extends ServletBase implements Servlet {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List mainDutyList = new ArrayList();
		//		System.err.println("成功1");
		
		/*Date now = new Date();
		System.out.println("进入YjsMainDutyListServlet,时间="+new Timestamp(now.getTime()));*/
		try {
			ConnLog.open("XtsMainDutyListServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			XtsWorkInfoHandler handler = new XtsWorkInfoHandler(conn);
			PhoneHandler phoneHandler = new PhoneHandler(conn);
			
			
			//			System.err.println("成功2");
			String SearchFlag = request.getParameter("SearchFlag");
			if ("1".equals(SearchFlag)) {
				
				String fromdate = request.getParameter("fromdate") == null ? "1901-01-01" : request.getParameter("fromdate");
				if ("".equals(fromdate)) {
					fromdate = "1901-01-01";
				}
				String todate = request.getParameter("todate") == null ? "2099-12-12" : request.getParameter("todate");
				if ("".equals(todate)) {
					todate = "2099-12-12";
				}
				//String selectType = request.getParameter("selectType") == null ? "1" : request.getParameter("selectType");
				String dutyName = request.getParameter("dutyName") == null ? "" : request.getParameter("dutyName");

				mainDutyList =
					handler.getMainDutyListByClause(
						"1=1 "
						//"1=1 AND WIT_CLASS='"
						//	+ selectType
							+ " AND (WIT_LEADER like '%"
							+ dutyName
							+ "%' OR WIT_SECRET like '%"
							+ dutyName
							+ "%') AND WIT_DATE>to_date('"
							+ fromdate
							+ "','yyyy-mm-dd') AND WIT_DATE<to_date('"
							+ todate
							+ "','yyyy-mm-dd') ");
				
				

			} else {
				mainDutyList = handler.getMainDutyListByClause("1=1");
			}
						
					
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			String userId = userMsghandler.getUserId();

			request.setAttribute("userId", userId);
			request.setAttribute("mainDutyList", mainDutyList);
			
			
			//把日志创建者翻译成人名，放在Map里传到dutyList中
			/*HashMap creatorMap = new HashMap();
			
			for (int i = 0; i < mainDutyList.size(); i++) {
				TbYjsWorkinfomainVO vo = (TbYjsWorkinfomainVO) mainDutyList.get(i);
				String createrName = phoneHandler.getUserName(vo.getWitCreater());
				creatorMap.put(vo.getWimId(), createrName);
			}
			
			request.setAttribute("creatorMap", creatorMap);*/
			
			//			System.err.println("成功4");
			this.forward(request, response, "/zbs/xts_duty/dutyList.jsp");

		} catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("XtsMainDutyListServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
