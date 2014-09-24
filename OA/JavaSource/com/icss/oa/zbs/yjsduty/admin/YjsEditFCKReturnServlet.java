package com.icss.oa.zbs.yjsduty.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.zbs.yjsduty.handler.YjsWorkInfoHandler;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfomainVO;

/**
 * @version 1.0
 * @author liupei
 */
public class YjsEditFCKReturnServlet extends ServletBase implements Servlet {

	/**
	 * @see com.icss.j2ee.servlet.ServletBase#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			ConnLog.open("YjsEditFCKReturnServlet");
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);						

			/*测试用
			Date now = new Date();
			System.out.println("进入YjsEditFCKReturnServlet,时间="+new Timestamp(now.getTime()));*/
			
			YjsWorkInfoHandler handler = new YjsWorkInfoHandler(conn);
			String mainId = "";
			TbYjsWorkinfomainVO vo = new TbYjsWorkinfomainVO();

			mainId = request.getParameter("mainId");
			vo = handler.getMainDutyById(mainId);
			
			vo.setFlag("0");//表示编辑完毕，将flag置为0
			Integer id = handler.editDutyMainInfo(vo);//返回workinfomainvo的wim_id					
			
			
			//System.out.println("在进入YjsEditFCKReturnServlet中，即将转入YjsMainDutyListServlet");
			response.sendRedirect(request.getContextPath()
					+ "/servlet/YjsMainDutyListServlet" );
			
		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("YjsEditFCKReturnServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
