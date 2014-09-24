package com.icss.oa.sendfile.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.config.AddressConfig;
/**
 * �õ���֯��uuid,������֯�������˵���Ϣ������session��
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SendFileChooseChildOrgsPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		String orguuid = "";
		if (request.getParameter("orguuid") != "") {
			orguuid = request.getParameter("orguuid");
		}

		List selectorgpersonlist = new ArrayList();
		SysOrgpersonVO sysorgpersonvo = new SysOrgpersonVO();
		Connection conn = null;

		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("ChooseChildOrgsPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("ChooseChildOrgsPersonServlet");
			}
			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			List personlist = handler.getChildOrgsPersonList(orguuid);
			Iterator personiterator = personlist.iterator();
			while (personiterator.hasNext()) {
				sysorgpersonvo = (SysOrgpersonVO) personiterator.next();

				SelectOrgpersonVO selectorgpersonvo = new SelectOrgpersonVO();
				//��voд��id��Ϣ
				selectorgpersonvo.setUserid(sysorgpersonvo.getUserid());
				//��voд��name��Ϣ
				selectorgpersonvo.setName(sysorgpersonvo.getCnname());
				//��voд��useruuid��Ϣ
				selectorgpersonvo.setUseruuid(sysorgpersonvo.getPersonuuid());
				selectorgpersonvo.setIsperson("1");
				selectorgpersonlist.add(selectorgpersonvo);

			} //while

			request.setAttribute("selectorgpersonlist", selectorgpersonlist);
			//������ȡ������ʾList

			//ȡ��֯�б���Ϣ
			String orgname = request.getParameter("orgname");

			super.forward(request, response, "/servlet/SendFileSelectPersonServlet?orgid=" + orguuid + "&orgname=" + orgname);

		} catch (IllegalStateException e) {
			e.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();
			handleError(ex);

		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ChooseChildOrgsPersonServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//
		}
	}
}
