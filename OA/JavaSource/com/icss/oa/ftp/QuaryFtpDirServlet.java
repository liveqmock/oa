package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.oa.address.dao.SysOrgDAO;
import com.icss.oa.address.dao.SysPersonDAO;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.address.vo.SysOrgVO;
import com.icss.oa.address.vo.SysPersonVO;

/**
 * Servlet implementation class for Servlet: QuaryFtpDirServlet
 * 
 */
public class QuaryFtpDirServlet extends com.icss.j2ee.servlet.ServletBase
		implements javax.servlet.Servlet {
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			FtpAdminVO favo = new FtpAdminVO();
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(fadao);
			List dirlist = factory.findAll(favo);
			List newlist = new ArrayList();
			for (int i = 0; i < dirlist.size(); i++) {
				FtpAdminVO temp = (FtpAdminVO) dirlist.get(i);
				String orgid = temp.getOrg();
				SysOrgDAO sodao = new SysOrgDAO(conn);
				sodao.setWhereClause("ORGUUID = '" + orgid + "'");
				factory.setDAO(sodao);
				List so = factory.find(new SysOrgVO());
				if (so.size() != 0) {
					temp.setOrg(((SysOrgVO) so.get(0)).getCnname());
				}
				// String adminids = temp.getOwener();
				//String adminnames = "";
				// StringTokenizer stz = new StringTokenizer(adminids,",");
				// while(stz.hasMoreElements()){
				// String tempid = (String)stz.nextElement();
				// SysPersonDAO spdao = new SysPersonDAO(conn);
				// spdao.setWhereClause("PERSONUUID ='"+ tempid +"'");
				// factory.setDAO(spdao);
				// List sp = factory.find(new SysPersonVO());
				// if(sp.size()!=0){
				// adminnames = adminnames + "," +
				// ((SysPersonVO)sp.get(0)).getCnname();
				// }
				// }
				// temp.setOwener(adminnames);
				newlist.add(temp);
			}

			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			List orglist = handler.getOrgByLevel1(3);
			request.setAttribute("dirlist", newlist);
			request.setAttribute("orglist", orglist);
			this.forward(request, response, "/ftp/ftpadmin.jsp");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (HandlerException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}