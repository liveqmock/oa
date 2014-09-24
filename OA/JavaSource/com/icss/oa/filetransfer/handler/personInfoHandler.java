/*
 * Created on 2004-6-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.filetransfer.dao.SysOrgDAO;
import com.icss.oa.filetransfer.dao.SysOrgSysOrgpersonSysPersonSearchDAO;
import com.icss.oa.filetransfer.dao.SysOrgpersonDAO;
import com.icss.oa.filetransfer.vo.SysOrgSysOrgpersonSysPersonVO;
import com.icss.oa.filetransfer.vo.SysOrgVO;
import com.icss.oa.filetransfer.vo.SysOrgpersonVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class personInfoHandler {

	private Connection conn;

	public personInfoHandler(Connection _conn) {
		this.conn = _conn;
	}

	public personInfoHandler() {

	}

	public String getOrgUUID(String personuuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonDAO dao = new SysOrgpersonDAO();
		factory.setDAO(dao);
		dao.setPersonuuid(personuuid);
		String orguuid = null;

		try {
			List list = factory.find(new SysOrgpersonVO());
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SysOrgpersonVO vo = (SysOrgpersonVO) it.next();
					orguuid = vo.getOrguuid();
				}
			}

			return orguuid;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public String getParentUUID(String uuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(uuid);

		String orguuid = null;

		try {
			List list = factory.find(new SysOrgVO());
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SysOrgVO vo = (SysOrgVO) it.next();
					orguuid = vo.getParentorguuid();
				}
			}

			return orguuid;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public Integer getOrgLevel(String uuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(uuid);

		Integer level = new Integer(100);

		try {
			List list = factory.find(new SysOrgVO());
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SysOrgVO vo = (SysOrgVO) it.next();
					level = vo.getOrglevel();
				}
			}

			return level;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public String getOrgName(String uuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(uuid);

		String name = "";

		try {
			List list = factory.find(new SysOrgVO());
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SysOrgVO vo = (SysOrgVO) it.next();
					name = vo.getCnname();
				}
			}

			return name;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public String getPersonJUPosition(String personUUID)
		throws HandlerException {
		StringBuffer sql = new StringBuffer();
		List list = null;
		Iterator it = null;
		String position = null;
		Integer orglevel = new Integer(3);

		sql.append("select ");
		sql.append(
			"SYS_PERSON.PERSONUUID,SYS_ORG.ORGUUID,SYS_ORG.CNNAME,SYS_ORG.ORGLEVEL ");
		sql.append("from ");
		sql.append("SYS_PERSON,SYS_ORG,SYS_ORGPERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.PERSONUUID = SYS_ORGPERSON.PERSONUUID and ");
		sql.append("SYS_ORG.ORGUUID= SYS_ORGPERSON.ORGUUID and ");
		sql.append("SYS_ORGPERSON.ISBELONG= '1' and ");
		sql.append("SYS_PERSON.PERSONUUID= '" + personUUID + "' ");

		DAOFactory factory = new DAOFactory(conn);
		SysOrgSysOrgpersonSysPersonSearchDAO dao =
			new SysOrgSysOrgpersonSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);

		SysOrgSysOrgpersonSysPersonVO vo = new SysOrgSysOrgpersonSysPersonVO();

		try {
			list = factory.find(new SysOrgSysOrgpersonSysPersonVO());
			if (list != null) {
				it = list.iterator();
			}
			if (it != null) {
				while (it.hasNext()) {
					vo = (SysOrgSysOrgpersonSysPersonVO) it.next();
					orglevel = vo.getOrglevel();
				}
			}

			if (orglevel.intValue() == 3
				|| orglevel.intValue() == 0
				|| orglevel.intValue() == 1
				|| orglevel.intValue() == 2) {
				position = vo.getCnname();
			}

			if (orglevel.intValue() == 4) {
				String parentOrgName =
					this.getOrgName(this.getParentUUID(vo.getOrguuid()));
				position = parentOrgName + "->" + vo.getCnname();
			}

			return position;

		} catch (Exception e) {
			position = "未知单位";
			e.printStackTrace();
			throw new HandlerException(e);

		}

	}

	public String getPersonJUPositionInJsp(String personUUID)
		throws HandlerException {
		StringBuffer sql = new StringBuffer();
		List list = null;
		Iterator it = null;
		String position = null;
		Integer orglevel = new Integer(3);

		sql.append("select ");
		sql.append("SYS_PERSON.PERSONUUID,SYS_ORG.ORGUUID,SYS_ORG.CNNAME,SYS_ORG.ORGLEVEL ");
		sql.append("from ");
		sql.append("SYS_PERSON,SYS_ORG,SYS_ORGPERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.PERSONUUID = SYS_ORGPERSON.PERSONUUID and ");
		sql.append("SYS_ORG.ORGUUID= SYS_ORGPERSON.ORGUUID and ");
		sql.append("SYS_ORGPERSON.ISBELONG= '1' and ");
		sql.append("SYS_PERSON.PERSONUUID= '" + personUUID + "' ");
		try {
			conn = DBConnectionLocator.getInstance().getConnection( com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			ConnLog.open("personInfoHandler.getPersonJUPositionInJsp");
			DAOFactory factory = new DAOFactory(conn);
			SysOrgSysOrgpersonSysPersonSearchDAO dao = new SysOrgSysOrgpersonSysPersonSearchDAO();
			dao.setSearchSQL(sql.toString());
			factory.setDAO(dao);
			SysOrgSysOrgpersonSysPersonVO vo = new SysOrgSysOrgpersonSysPersonVO();
			list = factory.find(new SysOrgSysOrgpersonSysPersonVO());
			
			
			if (list != null) {
				it = list.iterator();
			}
			
			if (it != null) {
				while (it.hasNext()) {
					vo = (SysOrgSysOrgpersonSysPersonVO) it.next();
					orglevel = vo.getOrglevel();
				}
			}

			if (orglevel.intValue() == 3 || orglevel.intValue() == 0 || orglevel.intValue() == 1 || orglevel.intValue() == 2) {
				position = vo.getCnname();
			}else if(orglevel.intValue() == 4) {
				String parentOrgName = this.getOrgName(this.getParentUUID(vo.getOrguuid()));
				position = parentOrgName + "->" + vo.getCnname();
			}else{
				String parentOrgName = this.getOrgName(this.getParentUUID(vo.getOrguuid()));
				String grandOrgName = this.getOrgName(getParentUUID(getParentUUID(vo.getOrguuid())));
				position = grandOrgName + "->" + parentOrgName + "->" + vo.getCnname();
			}

			return position;

		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
			throw new HandlerException(e);
			
		} catch (Exception e) {
			position = "未知单位";
			e.printStackTrace();
			throw new HandlerException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("personInfoHandler.getPersonJUPositionInJsp");
				} catch (SQLException e1) {
					e1.printStackTrace();
					throw new HandlerException(e1);
				}
			}
		}
		
		
	}
	
	

}









