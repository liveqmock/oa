/*
 * Created on 2004-4-28
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.message.handler.MsgHandler;
import com.icss.oa.message.vo.MsgManagerSysOrgVO;
import com.icss.oa.util.CurrentUser;

/** 
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EditMsgServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		String _tmp = request.getParameter("_tmp");
		if ("true".equals(_tmp)) {
			this.forward(request, response, "/message/msgEdit.jsp");
		} else {
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("EditMsgServlet");
				MsgHandler handler = new MsgHandler(conn);
				String personuuid = new CurrentUser().getId();
				List list = handler.getManageOrgList(personuuid);

				if (list == null || list.size() == 0) {
					this.forward(
						request,
						response,
						"/include/errorString.jsp?errorS=您没有可以发送短信的组织！");
				} else if (list.size() == 1) {
					MsgManagerSysOrgVO vo = (MsgManagerSysOrgVO) list.get(0);
					String orgname = vo.getCnname();
					String orguuid = vo.getMmOrguuid();
					this.forward(
						request,
						response,
						"/message/msgEdit.jsp?orgid="
							+ orguuid
							+ "&orgname="
							+ orgname);
				} else {
					request.setAttribute("list", list);
					this.forward(
						request,
						response,
						"/message/manageOrgList.jsp");
				}
			} catch (Exception e) {
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("EditMsgServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}
}
