package com.icss.oa.zbs.xtsduty.admin;

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
import com.icss.oa.zbs.xtsduty.handler.XtsWorkInfoHandler;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfomainVO;

/**
 * @version 1.0
 * @author liupei
 */
public class XtsEditFCKReturnServlet extends ServletBase implements Servlet {

	/**
	 * @see com.icss.j2ee.servlet.ServletBase#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			ConnLog.open("XtsEditFCKReturnServlet");
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);						

			/*������
			Date now = new Date();
			System.out.println("����YjsEditFCKReturnServlet,ʱ��="+new Timestamp(now.getTime()));*/
			
			XtsWorkInfoHandler handler = new XtsWorkInfoHandler(conn);
			String mainId = "";
			TbXtsWorkinfomainVO vo = new TbXtsWorkinfomainVO();

			mainId = request.getParameter("mainId");
			vo = handler.getMainDutyById(mainId);
			
			vo.setFlag("0");//��ʾ�༭��ϣ���flag��Ϊ0
			Integer id = handler.editDutyMainInfo(vo);//����workinfomainvo��wim_id					
			
			
			//System.out.println("�ڽ���YjsEditFCKReturnServlet�У�����ת��YjsMainDutyListServlet");
			response.sendRedirect(request.getContextPath()
					+ "/servlet/XtsMainDutyListServlet" );
			
		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("XtsEditFCKReturnServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}