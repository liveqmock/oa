/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.addressbook.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.statsite.admin.AddStatSite;
import com.icss.oa.util.StatPropertyManager;
import com.icss.oa.util.StatSiteControl;
/*以上代码是站点统计的扩展标记需要加入的类*/;

/**
 *删除文件
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelAddressbookServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		//Integer abcfId = new Integer(request.getParameter("abcfid"));
		String abcfId = null;
		String abcpId = null;
		String[] abc = request.getParameterValues("selectabfid");
		String[] abp = request.getParameterValues("selectabpid");

		String parentId = request.getParameter("parentId");
		/*以下代码是站点统计的扩展标记*/
		try {
			if (("1").equals(StatSiteControl.geSwitch())) {
				String modulename = StatPropertyManager.getString("stat_module12");
				String ip = request.getRemoteAddr();
				AddStatSite addstat = new AddStatSite();
				addstat.setIp(ip);
				addstat.setModulename(modulename);
				int tag = addstat.doStartTag();
			}
		} catch (JspException e) {
			e.printStackTrace();
		}
		/*以上代码是站点统计的扩展标记*/
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelAddressbookServlet");
			conn.setAutoCommit(false);
			AddressbookHandler handler = new AddressbookHandler(conn);

			AddressbookFolderVO abfVO = new AddressbookFolderVO();
			if (abc != null) {
				for (int i = 0; i < abc.length; i++) {
					abcfId = abc[i];
					handler.delAddressbook(new Integer(abcfId));
				}
			}

			if (abp != null) {
				for (int j = 0; j < abp.length; j++) {
					abcpId = abp[j];
					handler.delAddressbook(new Integer(abcpId));
				}
			}
			conn.commit();
			System.out.println("the parentId is:........" + parentId);
			if (parentId.equals("1"))
				this.forward(request, response, "/servlet/ShowAddrbookRootFolderServlet");
			else
				this.forward(request, response, "/servlet/ShowAddressbookListServlet?parentId=" + parentId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelAddressbookServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
