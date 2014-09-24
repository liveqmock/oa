package com.icss.oa.sync.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.dao.SysOrgDAO;
import com.icss.oa.filetransfer.vo.SysOrgVO;
import com.icss.resourceone.org.dao.OrgDAO;
import com.icss.resourceone.org.model.OrgVO;

public class UserHandler {
	private Connection conn;

	public UserHandler() {
	}

	public UserHandler(Connection conn) {
		this.conn = conn;
	}

	public static String getOrgName(String deptcode) {

		Integer orglevel = new Integer(3);
		String position = "";
		Connection conn1 = null;

		try {
			conn1 = DBConnectionLocator.getInstance().getConnection(
					"jdbc/ROEEE");

			OrgDAO dao = new OrgDAO();
			dao.setOrgcode(deptcode);
			dao.setDeltag("0");
			dao.setStatus(0);

			DAOFactory factory = new DAOFactory(conn1);
			factory.setDAO(dao);

			List list = factory.find(new OrgVO());
			if (!list.isEmpty()) {
				OrgVO vo = (OrgVO) list.get(0);
				orglevel = vo.getOrglevel();

				if (orglevel.intValue() == 3 || orglevel.intValue() == 0
						|| orglevel.intValue() == 1 || orglevel.intValue() == 2) {
					position = vo.getCnname();
				}

				if (orglevel.intValue() > 3) {
					String parentOrgName = "";

					DAOFactory factory1 = new DAOFactory(conn1);
					SysOrgDAO dao1 = new SysOrgDAO();
					dao1.setOrguuid(vo.getParentorguuid());
					factory1.setDAO(dao1);

					List list1 = factory1.find(new SysOrgVO());
					if (!list1.isEmpty()) {
						SysOrgVO vo1 = (SysOrgVO) list1.get(0);
						parentOrgName = vo1.getCnname();
						position = parentOrgName + "->" + vo.getCnname();
					}
				}
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			position = "Î´Öª×éÖ¯";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (conn1 != null) {
				try {
					conn1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return position;

	}
}