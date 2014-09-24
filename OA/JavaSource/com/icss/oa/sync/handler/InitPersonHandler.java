package com.icss.oa.sync.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.util.UUID;
import com.icss.oa.filetransfer.handler.MailUserHandler;
import com.icss.oa.hr.dao.HRPersonTempDAO;
import com.icss.oa.hr.vo.HRPersonTempVO;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.resourceone.common.login.dao.OrgPersonDAO;
import com.icss.resourceone.org.dao.OrgDAO;
import com.icss.resourceone.org.model.OrgVO;
import com.icss.resourceone.user.dao.PersonAccountDAO;
import com.icss.resourceone.user.model.PersonAccountVO;

public class InitPersonHandler {
	private Connection conn;

	public InitPersonHandler() {
	}

	public InitPersonHandler(Connection conn) {
		this.conn = conn;
	}

	/*
	 * 取得所有未同步的用户
	 */
	public List getAllPerson() throws DAOException {
		List list = new ArrayList();

		HRPersonTempDAO dao = new HRPersonTempDAO();
		dao.setFlag(new Integer(0));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		list = factory.find(new HRPersonTempVO());
		return list;
	}

	/*
	 * 新建用户
	 */
	public boolean newperson(HRPersonTempVO vo) throws DAOException,
			HandlerException {

		Connection oaconn = null;
		String cnname = vo.getUsername();
		String userid = CnToSpell.getFullSpell2(cnname);

		if (!StringUtils.isAsciiPrintable(userid)) {
			// 翻译不成功
			return false;
		}

		// 用户名存在
		String tempuserid = userid;
		int i = 1;
		while (UserIsExist(tempuserid)) {
			tempuserid = userid + i;
			i++;
		}

		userid = tempuserid;

		String hrid = vo.getHrid();
		String uuid = (new UUID()).toString();

		PersonAccountDAO personaccountdao = new PersonAccountDAO();
		personaccountdao.setConnection(conn);
		PersonDAO persondao = new PersonDAO();
		persondao.setConnection(conn);

		personaccountdao.setUserid(userid);
		personaccountdao.setPersonuuid(uuid);
		personaccountdao.setFlag(new Integer(2));
		personaccountdao.setPassword("ROdyR8mRC9Jfc");
		personaccountdao.setAccountstat(new Integer(0));
		personaccountdao.setLoginfailnum(new Integer(0));
		personaccountdao.setLastloginip("0.0.0.0");
		personaccountdao.setLastlogindate(new Timestamp(System
				.currentTimeMillis()));
		personaccountdao.setTtlflag(new Integer(0));
		personaccountdao.setAccountttl(new Integer(0));
		personaccountdao
				.setCreatetime(new Timestamp(System.currentTimeMillis()));
		personaccountdao.setPassquestion("password");
		personaccountdao.setPassanswer("pass");
		personaccountdao.setDeltag("0");

		persondao.setPersonuuid(uuid);

		persondao.setEnname(userid);
		persondao.setCnname(cnname);
		persondao.setHrid(hrid);

		personaccountdao.create();
		persondao.create();

		// 创建邮箱帐号
//		try {
//			System.out.println("##########################创建 邮箱帐号开始!"
//					+ System.currentTimeMillis());
//			oaconn = DBConnectionLocator.getInstance().getConnection(
//					Globals.DATASOURCEJNDI);
//			MailUserHandler mailhandler = new MailUserHandler(oaconn);
//			mailhandler.createUser(userid, uuid, "");
//
//			System.out.println("##########################创建 邮箱帐号结束!"
//					+ System.currentTimeMillis());
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new HandlerException("创建" + cnname + " 邮箱帐号 失败！！！");
//
//		} finally {
//			if (oaconn != null) {
//				try {
//					oaconn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		return true;
	}

	/**
	 * 判断用户名是否已经存在
	 */
	public boolean UserIsExist(String userid) throws DAOException {
		boolean result = false;
		PersonAccountDAO account = new PersonAccountDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(account);

		account.setUserid(userid);
		List list = factory.find(new PersonAccountVO());
		if (!list.isEmpty()) {
			result = true;
		}
		return result;
	}

	public void updFlag(String hrid) throws DAOException {

		HRPersonTempDAO dao = new HRPersonTempDAO();
		dao.setFlag(new Integer(1));
		dao.setConnection(conn);
		dao.setWhereClause(" hrid = '" + hrid + "' ");
		dao.update(false);

	}

	/*
	 * 取得OA中hrid不是空的帐号
	 */
	public List getAllOAPerson() throws DAOException {

		PersonDAO dao = new PersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setWhereClause("hrid is not null");
		factory.setDAO(dao);
		List list = new ArrayList();
		list = factory.find(new PersonVO());
		return list;
	}

	public String getDeptCode(String hrid) throws DAOException {
		HRPersonTempDAO dao = new HRPersonTempDAO();
		dao.setHrid(hrid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new HRPersonTempVO());
		HRPersonTempVO vo = (HRPersonTempVO) list.get(0);
		if (vo.getDeptcode() != null && vo.getDeptcode().length() > 0) {
			return vo.getDeptcode();
		} else {
			return vo.getOrgcode();
		}
	}

	public String getOrguuid(String deptcode) throws DAOException {
		String result = "未知组织";
		OrgDAO org = new OrgDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(org);

		org.setOrgcode(deptcode);
		org.setDeltag("0");
		org.setStatus(0);
		List list = factory.find(new OrgVO());
		if (!list.isEmpty()) {
			OrgVO orgvo = (OrgVO) list.get(0);
			return orgvo.getOrguuid();
		}
		return result;
	}

	public void updOrg(String personuuid, String orguuid) throws DAOException {
		OrgPersonDAO dao = new OrgPersonDAO();
		dao.setPersonuuid(personuuid);
		dao.setOrguuid(orguuid);
		dao.setIsbelong("1");
		dao.setConnection(conn);
		dao.create();
	}

}