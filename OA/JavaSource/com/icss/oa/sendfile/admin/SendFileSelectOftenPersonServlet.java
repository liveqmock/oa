package com.icss.oa.sendfile.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.config.AddressConfig;
import com.icss.oa.oftenperson.handler.OftenHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
/**
 * 
 * @author lizb
 *
 * ѡ������ϵ��׼������
 */
public class SendFileSelectOftenPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("SelectIndiPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("SelectIndiPersonServlet");
			}

			//ȡ���˷����б���Ϣ
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String userid = user.getUserID();
			
			System.out.println("SelectOftenPersonServlet-->userid = " + userid);

			OftenHandler handler = new OftenHandler(conn);
			//ȡ�õ�ǰ�û�������ϵ��(Person����ļ���
			List personlist = handler.getOftenPerson(userid);

			request.setAttribute("personlist", personlist);
			this.forward(request, response, "/address/sendfile/selectOftenperson.jsp?");
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SelectIndiPersonServlet");
				}
			} catch (Exception e) {

			}
		}
	}
}
