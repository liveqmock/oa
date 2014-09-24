package com.icss.oa.tq;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.config.AddressConfig;
import com.icss.oa.tq.handler.TQHandler;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.Organization;

public class TqOrgServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Context ctx = null;
		try {
				ctx = Context.getInstance();
				String uuid  = ctx.getCurrentPersonUuid();
				
			//long a = System.currentTimeMillis();
			//System.out.println("#######begin"+a);
			// conn=this.getConnection(Globals.DATASOURCEJNDI);
			if ((this.getServletContext().getInitParameter(
					AddressConfig.ADDRESSJNDI) == null)
					|| (this.getServletContext().getInitParameter(
							AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("TqOrgServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(
						this.getServletContext().getInitParameter(
								AddressConfig.ADDRESSJNDI));
				ConnLog.open("TqOrgServlet");
			}
			OrgHandler handler = new OrgHandler(conn);
			SysOrgVO topOrgVO = handler.getTopOrg();
			List list = handler.getOrgTreeList(topOrgVO.getOrguuid());
			String hwnd = request.getParameter("hwnd");
			// System.out.println("!!!!!!!!!!!!!hwnd="+hwnd);
			TQHandler handler1 = new TQHandler(conn);

			//取得本部门人员
			//Integer level = handler.getContextUserOrgLevel(user.getPersonUuid());
//			Organization contextorg = handler.getContextUserOrg(uuid);
//			SysOrgVO topDepVO = handler.getOrgByCurrentUser(contextorg, 3);
			com.icss.oa.address.vo.SysOrgVO dvo = handler1.getDpt(uuid);
			List deplist  =handler1.getDptPerson(dvo.getOrglevelcode());
//			List list1 = handler.getOrgTreeList(topOrgVO.getOrguuid());
			

			// 取得在线人员
			List userlist = handler1.getOnlineUser();

			HttpSession session = request.getSession();
			session.setAttribute("userlist", userlist);
			request.setAttribute("deplist", deplist);
			request.setAttribute("topDepVO", dvo);

			request.setAttribute("list", list);
			request.setAttribute("topOrgVO", topOrgVO);
			request.setAttribute("hwnd", hwnd);
			//System.out.println("#######end"+(System.currentTimeMillis()-a));

			this.forward(request, response, "/tq/orgTree.jsp");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("TqOrgServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}
