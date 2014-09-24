/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.popuporgtree.admin;

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
import com.icss.popuporgtree.handler.OrgHandler;
import com.icss.popuporgtree.vo.SysOrgVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PopupOrgTreeServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn=null;
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("PopupOrgTreeServlet");
			OrgHandler handler=new OrgHandler(conn);
			SysOrgVO topOrgVO=handler.getTopOrg();
			List list=handler.getOrgTreeList(topOrgVO.getOrguuid());
			request.setAttribute("list",list);
			request.setAttribute("topOrgVO",topOrgVO);

			this.forward(request, response,"/popuporgtree/orgTree.jsp");
		}catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally { 
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("PopupOrgTreeServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}
