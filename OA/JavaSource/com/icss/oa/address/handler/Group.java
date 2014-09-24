/*
 * Created on 2004-4-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.address.dao.AddressCommongroupAddressGrouprightSearchDAO;
import com.icss.oa.address.dao.AddressCommongroupAddressTransferSearchDAO;
import com.icss.oa.address.dao.AddressCommongroupDAO;
import com.icss.oa.address.dao.AddressCommongroupSearchDAO;
import com.icss.oa.address.dao.AddressGroupDAO;
import com.icss.oa.address.dao.AddressGroupinfoDAO;
import com.icss.oa.address.dao.AddressGroupinfoSysPersonSearch1DAO;
import com.icss.oa.address.dao.AddressTransferDAO;
import com.icss.oa.address.dao.CommongroupRightSearchDAO;
import com.icss.oa.address.dao.GrouprightDAO;
import com.icss.oa.address.dao.SysPersonDAO;
import com.icss.oa.address.vo.AddressCommongroupAddressGrouprightVO;
import com.icss.oa.address.vo.AddressCommongroupAddressTransferVO;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.oa.address.vo.AddressGroupinfoSysPersonSearch1VO;
import com.icss.oa.address.vo.AddressGroupinfoVO;
import com.icss.oa.address.vo.AddressTransferVO;
import com.icss.oa.address.vo.CommongroupRightVO;
import com.icss.oa.address.vo.GrouprightVO;
import com.icss.oa.address.vo.SysPersonVO;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.sdk.right.AppRole;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Group {
	private Connection conn;
	public Group(Connection connection) {
		this.conn = connection;
	}

	public Group() {
	}

	/**
	 * ��ø��û��Ĺ��������б�
	 * @param userid
	 * @return
	 */
	public List commonGroupList(String userid) {
		DAOFactory factory = new DAOFactory(conn);
		CommongroupRightSearchDAO dao = new CommongroupRightSearchDAO();
		dao.setSearchSQL(searchPersonGroupSql(userid));
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new CommongroupRightVO());
		} catch (DAOException e) {
			throw new RuntimeException("�����Ա�Ĺ����������");
		}
		return list;
	}

	/**
	 * ��ø��û��Ĺ����������б�
	 * @param userid
	 * @return
	 */
	public List commonRootGroupList(String userid) {
		DAOFactory factory = new DAOFactory(conn);
		CommongroupRightSearchDAO dao = new CommongroupRightSearchDAO();
		dao.setSearchSQL(searchRootGroupSql(userid));
		factory.setDAO(dao);
		List list = null;
		try {

			list = factory.find(new CommongroupRightVO());
		} catch (DAOException e) {
			throw new RuntimeException("�����Ա�Ĺ����ָ������");
		}
		return list;
	}

	/**
	 * ��ø��û��Ĺ������������б�
	 * @param userid
	 * @return
	 */
	public List commonTwoGroupList(String userid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		dao.setOwner(userid);
		dao.setLevels(new Integer(1));
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("�����Ա�Ĺ��������ָ������");
		}
		return list;
	}

	/**
	 * ��ø��û���˽�з����б�
	 * @param userid
	 * @return
	 */
	public List individualGroupList(String userid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupDAO dao = new AddressGroupDAO();
		dao.setGroupuser(userid);
		dao.addOrderBy("groupname", true);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressGroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("�����Ա��˽�з������" + e);
		}
		return list;
	}

	/**
	 * ���ָ�����������������
	 * @param groupid
	 * @return
	 */
	public String getCommonTwoOwner(Integer Groupid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		dao.setId(Groupid);
		factory.setDAO(dao);
		//List list = null;
		try {
			dao = (AddressCommongroupDAO) factory.findByPrimaryKey();
			return dao.getOwner();
		} catch (DAOException e) {
			throw new RuntimeException("��ö�������ʱ����" + e);
		}
	}
	/**
	 * �õ������û���������sql
	 * @param userid
	 * @return
	 */
	public String searchPersonGroupSql(String userid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.Seq as seq ");
		sql.append("FROM ADDRESS_COMMONGROUP,ADDRESS_GROUPRIGHT ");
		sql.append("where ADDRESS_COMMONGROUP.ID = ADDRESS_GROUPRIGHT.GROUPID ");
		sql.append("and ADDRESS_COMMONGROUP.LEVELS = 0 ");
		sql.append("and ADDRESS_GROUPRIGHT.USERID = '");
		sql.append(userid);
		sql.append("'");
		sql.append(" union ");
		sql.append("SELECT DISTINCT ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.Seq as seq  ");
		sql.append("FROM ADDRESS_COMMONGROUP ");
		sql.append("where ADDRESS_COMMONGROUP.NEEDACCREDIT <> '1' ");
		sql.append("and ADDRESS_COMMONGROUP.LEVELS = 0 order by seq ");

		//		sql.append("SELECT DISTINCT ");
		//		sql.append(
		//			"ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.ID ");
		//		sql.append(",ADDRESS_GROUPRIGHT.USERID ");
		//		sql.append("FROM ");
		//		sql.append("ADDRESS_COMMONGROUP,ADDRESS_GROUPRIGHT ");
		//		sql.append("where ");
		//		sql.append("(ADDRESS_COMMONGROUP.NEEDACCREDIT <> '1'" );
		//		sql.append("or ");
		//		sql.append("ADDRESS_COMMONGROUP.ID = ADDRESS_GROUPRIGHT.GROUPID) ");
		//		sql.append("and ADDRESS_GROUPRIGHT.USERID = '");
		//		sql.append(userid);
		//		sql.append("'");
		return sql.toString();
	}

	/**
	 * �õ������û��������������sql
	 * @param userid
	 * @return
	 */
	public String searchRootGroupSql(String userid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.ID ");
		sql.append("FROM ADDRESS_COMMONGROUP,ADDRESS_GROUPRIGHT ");
		sql.append("where ADDRESS_COMMONGROUP.ID = ADDRESS_GROUPRIGHT.GROUPID ");
		sql.append(" and ADDRESS_COMMONGROUP.LEVELS = 0 ");
		sql.append("and ADDRESS_GROUPRIGHT.USERID = '");
		sql.append(userid);
		sql.append("'");
		sql.append(" union ");
		sql.append("SELECT DISTINCT ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.ID ");
		sql.append("FROM ADDRESS_COMMONGROUP ");
		sql.append(" where ADDRESS_COMMONGROUP.NEEDACCREDIT <> '1' ");
		sql.append(" and  ADDRESS_COMMONGROUP.LEVELS = 0 ");
		return sql.toString();
	}

	/**
	 * ���Ӹ��˷���
	 * @param addressGroupVO
	 */
	public Integer addGroup(AddressGroupVO addressGroupVO) {
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setValueObject(addressGroupVO);
		addressGroupDAO.setConnection(conn);
		try {
			addressGroupDAO.create();
		} catch (DAOException e) {
			throw new RuntimeException("���ӷ������");
		}
		return addressGroupDAO.getId();
	}

	/**
	 * �޸ĸ��˷���
	 * @param addressGroupVO
	 */
	public void alterGroup(AddressGroupVO addressGroupVO) {
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setValueObject(addressGroupVO);
		addressGroupDAO.setConnection(conn);
		try {
			addressGroupDAO.update(true);
		} catch (DAOException e) {
			throw new RuntimeException("�޸ķ������");
		}
	}

	/**
	 * ɾ������
	 * @param groupid
	 */
	public void delGroup(Integer groupid) {
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setId(groupid);
		addressGroupDAO.setConnection(conn);
		try {
			addressGroupDAO.delete();
		} catch (DAOException e) {
			throw new RuntimeException("ɾ���������");
		}
	}

	/**
	 * ��ĳ�������ʹ��Ȩ�ָ��û�
	 * @param groupid
	 * @param userid
	 */
	public void accreditGroup2Person(Integer groupid, String userid) {
		GrouprightDAO grouprightDAO = new GrouprightDAO();
		grouprightDAO.setGroupid(groupid);
		grouprightDAO.setUserid(userid);
		grouprightDAO.setConnection(conn);
		try {
			grouprightDAO.create();
		} catch (DAOException e) {
			throw new RuntimeException("������Ȩ����");
		}
	}

	/**
	 * �ж��Ƿ�����
	 * @param groupid
	 * @param userid
	 * @return
	 */
	public boolean isAccreded(Integer groupid, String userid) {
		DAOFactory factory = new DAOFactory(conn);
		GrouprightDAO grouprightDAO = new GrouprightDAO();
		grouprightDAO.setGroupid(groupid);
		grouprightDAO.setUserid(userid);
		factory.setDAO(grouprightDAO);
		List list = null;
		try {
			list = factory.find(new GrouprightVO());
		} catch (DAOException e) {
			throw new RuntimeException("������Ȩ����");
		}
		if (list.size() == 0) {
			return false;
		} else
			return true;

	}

	/**
	 * ȡ�÷�����Ȩ��Ա�б�
	 * @param groupid
	 * @return
	 */
	public List getAccreditList(Integer groupid) {
		DAOFactory factory = new DAOFactory(conn);
		GrouprightDAO dao = new GrouprightDAO();
		factory.setDAO(dao);
		dao.setGroupid(groupid);
		List list = null;
		try {
			list = factory.find(new GrouprightVO());
		} catch (DAOException e) {
			throw new RuntimeException("������Ȩ����");
		}
		return list;
	}
	/**
	 * ȡ����Ȩ��Ա�����б�
	 * @param groupid
	 * @return
	 */
	public List getAccreditGroupList(String userid) {
		DAOFactory factory = new DAOFactory(conn);
		GrouprightDAO dao = new GrouprightDAO();
		factory.setDAO(dao);
		dao.setUserid(userid);
		List list = null;
		try {
			list = factory.find(new GrouprightVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ����Ȩ��Ա�����б����");
		}
		return list;
	}

	/**
	 * ɾ��������Ȩ��
	 * @param groupid
	 * @param userid
	 */
	public void delAccreditPerson(Integer groupid, String userid) {
		GrouprightDAO dao = new GrouprightDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setGroupid(groupid);
		dao.setUserid(userid);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw new RuntimeException("ɾ��������Ȩ�˳���" + e);
		}
	}

	/**
	 * ɾ�����������е���Ȩ��
	 * @param groupid
	 * @param userid
	 */
	public void delAllAccreditPerson(Integer groupid) {
		GrouprightDAO dao = new GrouprightDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setGroupid(groupid);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw new RuntimeException("ɾ�����������е���Ȩ�˳���" + e);
		}
	}

	/**
	 * ȡ�ù�������
	 * @param addressGroupVO
	 * @param userid
	 */
	public List listCommonGroup() {
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setLevels(new Integer(0));
		dao.addOrderBy("groupname", true);
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ�ù��������б����");
		}
		return list;
	}

	/**
	 * ȡ���Լ����ѱ���Ȩ�Ͳ���Ҫ��Ȩ��һ�����������б�
	 * @param
	 * @param parentid
	 */
	public List listCommonTwoGroupbyPower(String personuuid, boolean flag) {

		AddressCommongroupAddressGrouprightSearchDAO dao = new AddressCommongroupAddressGrouprightSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		StringBuffer sql = new StringBuffer();
		sql.append(
			" SELECT ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.NEEDACCREDIT,ADDRESS_COMMONGROUP.OWNER,ADDRESS_COMMONGROUP.LEVELS,ADDRESS_COMMONGROUP.ROOTID,ADDRESS_COMMONGROUP.PARENTID,ADDRESS_GROUPRIGHT.USERID,ADDRESS_GROUPRIGHT.GROUPID ");

		sql.append(" FROM  ADDRESS_COMMONGROUP,ADDRESS_GROUPRIGHT");

		sql.append(" where  ( ADDRESS_COMMONGROUP.ID = ADDRESS_GROUPRIGHT.GROUPID and ADDRESS_COMMONGROUP.LEVELS = 0 and ADDRESS_COMMONGROUP.NEEDACCREDIT = 1 ");

		if (flag) {
			sql.append(" and ADDRESS_GROUPRIGHT.USERID = '");
			sql.append(personuuid + " ' ");
		}

		sql.append(" ) or (");

		sql.append(" ADDRESS_COMMONGROUP.LEVELS = 0 and ADDRESS_COMMONGROUP.NEEDACCREDIT = 0 )");

		dao.setSearchSQL(sql.toString());
		List list = null;
		try {
			list = factory.find(new AddressCommongroupAddressGrouprightVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ����Ȩ�Ķ���һ�������������");
		}
		return list;
	}

	/**
	 * ȡ��ȫ���Ķ�����������
	 * @param addressGroupVO
	 * @param userid
	 */
	public List listCommonTwoGroup() {
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setLevels(new Integer(1));
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ��ȫ���Ĺ��������б����");
		}
		return list;
	}

	/**
	 * ȡ�ö�����������
	 * @param
	 * @param parentid
	 */
	public List listCommonTwoGroup(Integer parentid) {
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setParentid(parentid);
		dao.setLevels(new Integer(1));
		dao.addOrderBy("groupname", true);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());

		} catch (DAOException e) {
			throw new RuntimeException("ȡ����Ӧ�Ķ������������б����");
		}
		return list;
	}

	/**
	 * ȡ���Լ��Ķ�����������
	 * @param
	 * @param parentid
	 */
	public List listCommonTwoGroup1(Integer parentid, String uuid) {

		AddressCommongroupSearchDAO dao = new AddressCommongroupSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		StringBuffer sql = new StringBuffer();
		sql.append(
			"SELECT ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.NEEDACCREDIT,ADDRESS_COMMONGROUP.OWNER,ADDRESS_COMMONGROUP.LEVELS,ADDRESS_COMMONGROUP.ROOTID,ADDRESS_COMMONGROUP.PARENTID ");
		sql.append("FROM ADDRESS_COMMONGROUP ");
		sql.append(" where ADDRESS_COMMONGROUP.LEVELS = 1 ");
		sql.append(" and ADDRESS_COMMONGROUP.PARENTID = ");
		sql.append(parentid);
		sql.append(" and ADDRESS_COMMONGROUP.OWNER = '");
		sql.append(uuid);
		dao.setSearvhSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ����Ӧ�Ķ������������б����");
		}
		return list;
	}

	/**
	 * ȡ�����˵Ķ�����������
	 * @param
	 * @param parentid
	 */
	public List listCommonTwoGroup2(Integer parentid, String uuid) {

		AddressCommongroupSearchDAO dao = new AddressCommongroupSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		StringBuffer sql = new StringBuffer();
		sql.append(
			"SELECT ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.NEEDACCREDIT,ADDRESS_COMMONGROUP.OWNER,ADDRESS_COMMONGROUP.LEVELS,ADDRESS_COMMONGROUP.ROOTID,ADDRESS_COMMONGROUP.PARENTID ");
		sql.append(" FROM ADDRESS_COMMONGROUP ");
		sql.append(" where ADDRESS_COMMONGROUP.LEVELS = 1 ");
		sql.append(" and ADDRESS_COMMONGROUP.PARENTID = ");
		sql.append(parentid);
		sql.append(" and ADDRESS_COMMONGROUP.OWNER <> '");
		sql.append(uuid);
		sql.append("'");
		dao.setSearvhSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ����Ӧ�Ķ������������б����");
		}
		return list;
	}

	/**
	 * ���ӹ�������
	 * @param addressGroupVO
	 * @param userid
	 */
	public Integer addCommonGroup(AddressCommongroupVO addressCommongroupVO) {
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		dao.setValueObject(addressCommongroupVO);
		dao.setConnection(conn);
		try {

			dao.create();
		} catch (DAOException e) {
			throw new RuntimeException("���ӹ����������");
		}

		return dao.getId();
	}

	/**
	 * �޸Ĺ�������
	 * @param addressGroupVO
	 * @param userid
	 */
	public void alterCommonGroup(AddressCommongroupVO addressCommongroupVO) {
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		dao.setValueObject(addressCommongroupVO);
		dao.setConnection(conn);
		try {
			dao.update(true);
		} catch (DAOException e) {
			throw new RuntimeException("�޸Ĺ����������");
		}

	}

	/**
	 * ɾ����������
	 * @param addressGroupVO
	 * @param userid
	 */
	public void delCommonGroup(Integer groupid) {
		AddressCommongroupDAO dao = new AddressCommongroupDAO();
		dao.setId(groupid);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			throw new RuntimeException("ɾ�������������");
		}
	}

	/**
	 * Ϊ���������Ա
	 * @param userid
	 * @param groupid
	 */
	public void appendGroupWithPerson(String userid, Integer groupid, String grouptype) {
		AddressGroupinfoDAO addressGroupinfoDAO = new AddressGroupinfoDAO();
		addressGroupinfoDAO.setUserid(userid);
		addressGroupinfoDAO.setGroupid(groupid);
		addressGroupinfoDAO.setGrouptype(grouptype);
		addressGroupinfoDAO.setConnection(conn);
		try {
			addressGroupinfoDAO.create();
		} catch (DAOException e) {
			throw new RuntimeException("Ϊ���������Ա����");
		}
	}

	/**
	 * �жϸ��˷��������Ƿ����
	 * @param groupName
	 * @return
	 */
	public boolean isIndiGroupNameExist(String groupName, String owner_uuid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setGroupname(groupName);
		addressGroupDAO.setGroupuser(owner_uuid);
		factory.setDAO(addressGroupDAO);
		List list = null;
		try {
			list = factory.find(new AddressGroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("���ҷ������ƴ���");
		}
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * �жϸ��˷��������Ƿ����,��ȥ�޸ĸ��˷����������������
	 * @param groupName
	 * @return
	 */
	public boolean isIndiGroupNameExist1(String groupName, String owner_uuid, Integer Id) {

		boolean flag = true;
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setGroupname(groupName);
		addressGroupDAO.setGroupuser(owner_uuid);
		factory.setDAO(addressGroupDAO);
		List list = null;
		try {
			list = factory.find(new AddressGroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("���ҷ������ƴ���");
		}
		if (list.size() == 0) {
			return false;
		} else {
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					AddressGroupVO vo = (AddressGroupVO) it.next();
					if (Id.intValue() == vo.getId().intValue()) {
						flag = false;
					}
				}
			}
			return flag;
		}

	}

	/**
	 * ���һ�������Ͷ�����������ʱ�жϹ������������Ƿ����
	 * @param groupName,parentID
	 * @return
	 */
	public boolean isCommonGroupNameExist(String groupName, Integer parentID) {
		DAOFactory factory = new DAOFactory(conn);
		AddressCommongroupDAO addressCommonGroupDAO = new AddressCommongroupDAO();
		addressCommonGroupDAO.setGroupname(groupName);
		addressCommonGroupDAO.setParentid(parentID);
		factory.setDAO(addressCommonGroupDAO);
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("���ҷ������ƴ���");
		}
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * �޸�һ�������Ͷ�����������ʱ�жϹ������������Ƿ����
	 * @param groupName,parentID,Group
	 * @return
	 */
	public boolean isCommonGroupNameExist1(String groupName, Integer parentID, Integer GroupID) {

		boolean flag = true;
		DAOFactory factory = new DAOFactory(conn);
		AddressCommongroupDAO addressCommonGroupDAO = new AddressCommongroupDAO();
		addressCommonGroupDAO.setGroupname(groupName);
		addressCommonGroupDAO.setParentid(parentID);
		factory.setDAO(addressCommonGroupDAO);
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("���ҷ������ƴ���");
		}
		if (list.size() == 0) {
			return false;
		} else {
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					AddressCommongroupVO vo = (AddressCommongroupVO) it.next();
					if (GroupID.intValue() == vo.getId().intValue()) {
						flag = false;
					}
				}
			}
			return flag;
		}
	}

	/**
	 * ȡ�÷��������Ա
	 * @param groupid
	 * @return
	 */
	public List personInGroupbyName(Integer groupid, String grouptype) {

		AddressGroupinfoSysPersonSearch1DAO dao = new AddressGroupinfoSysPersonSearch1DAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("ADDRESS_GROUPINFO.USERID,ADDRESS_GROUPINFO.GROUPID,ADDRESS_GROUPINFO.GROUPTYPE,SYS_PERSON.PERSONUUID ");
		sql.append("FROM ");
		sql.append("ADDRESS_GROUPINFO,SYS_PERSON ");
		sql.append(" WHERE ");
		sql.append("SYS_PERSON.PERSONUUID=ADDRESS_GROUPINFO.USERID AND ADDRESS_GROUPINFO.GROUPTYPE= '" + grouptype + "'");
		sql.append("AND  SYS_PERSON.DELTAG='0' AND  ADDRESS_GROUPINFO.GROUPID=" + groupid);
		sql.append(" order by SYS_PERSON.USERID");
		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new AddressGroupinfoSysPersonSearch1VO());
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean personInGroupbyNameIsNull(Integer groupid, String grouptype) throws HandlerException {

		Connection conn = null;

		try {
			conn = DBConnectionLocator.getInstance().getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			ConnLog.open("Group.personInGroupbyNameIsNull");
			boolean flag = false;
			AddressGroupinfoSysPersonSearch1DAO dao = new AddressGroupinfoSysPersonSearch1DAO();
			dao.setConnection(conn);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("ADDRESS_GROUPINFO.USERID,ADDRESS_GROUPINFO.GROUPID,ADDRESS_GROUPINFO.GROUPTYPE,SYS_PERSON.PERSONUUID ");
			sql.append("FROM ");
			sql.append("ADDRESS_GROUPINFO,SYS_PERSON ");
			sql.append(" WHERE ");
			sql.append("SYS_PERSON.PERSONUUID=ADDRESS_GROUPINFO.USERID AND ADDRESS_GROUPINFO.GROUPTYPE= '" + grouptype + "'");
			sql.append("AND ADDRESS_GROUPINFO.GROUPID=" + groupid);
			sql.append(" order by SYS_PERSON.USERID");
			dao.setSearchSQL(sql.toString());

			List list = null;

			list = factory.find(new AddressGroupinfoSysPersonSearch1VO());
			if (list.size() > 0)
				flag = true;
			return flag;
		} catch (Exception e) {
			return false;
		} finally {

			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("Group.personInGroupbyNameIsNull");
				} catch (SQLException e1) {
					e1.printStackTrace();
					throw new HandlerException(e1);
				}
			}
		}

	}

	/**
	 * ȡ�÷��������Ա
	 * @param groupid
	 * @return
	 */
	/*
	public List personInGroup(Integer groupid, String grouptype) {
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupinfoDAO addressGroupinfoDAO = new AddressGroupinfoDAO();
		addressGroupinfoDAO.setGroupid(groupid);
		addressGroupinfoDAO.setGrouptype(grouptype);

		factory.setDAO(addressGroupinfoDAO);
		List list = null;
		try {
			list = factory.find(new AddressGroupinfoVO());
		} catch (DAOException e) {
			throw new RuntimeException("���ҷ�������Ա����");
		}
		return list;
	}*/
	
	/**
	 * ȡ�÷��������Ա add by ly
	 * @param groupID
	 * @param groupType
	 * @return
	 */
	public List personInGroup(Integer groupid,String grouptype){
		List addressgroupinfolist = new ArrayList();
		Statement st;
		try {
			st = conn.createStatement();
			//ȡ������Աʱ,����R1����ɾ����Ա
			StringBuffer sb=new StringBuffer();
			sb.append("SELECT T.USERID,T.GROUPID,T.GROUPTYPE,C.DELTAG FROM ADDRESS_GROUPINFO T ");
			sb.append(" LEFT JOIN ROEEE.RO_PERSONACCOUNT C ON C.PERSONUUID=T.USERID ");
			sb.append(" WHERE T.GROUPID="+String.valueOf(groupid)+" AND C.DELTAG=0 AND T.GROUPTYPE='"+grouptype+"'");
			
			//ִ�в�ѯ
			ResultSet rs=st.executeQuery(sb.toString());
			while(rs.next()){
				AddressGroupinfoVO addressGroupinfoVO=new AddressGroupinfoVO();
				addressGroupinfoVO.setGroupid(rs.getInt("GROUPID"));
				addressGroupinfoVO.setGrouptype(rs.getString("GROUPTYPE"));
				addressGroupinfoVO.setUserid(rs.getString("USERID"));
				addressgroupinfolist.add(addressGroupinfoVO);
			}
			System.out.println("��ǰ�û���:"+String.valueOf(groupid)+",ʵ�ʷ�����ԱΪ:"+addressgroupinfolist.size());
			rs.close();
			st.close();
		} catch (SQLException e1) {
			System.out.println("�ļ�����,��ȡ�û�����Աʧ��,����ϵϵͳ����Ա"+e1);
		}
		return addressgroupinfolist;
	}

	/**
	 * ȡ�ö���������Ķ�����֯
	 * @param groupid
	 * @return
	 */
	public List getGroupByParentGroup(Integer groupid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressCommongroupDAO addresscommongroupDAO = new AddressCommongroupDAO();
		addresscommongroupDAO.setParentid(groupid);
		factory.setDAO(addresscommongroupDAO);
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("���ҷ�������Ա����");
		}
		return list;
	}
	/**
	 * ȡ�ö���������Ķ�����֯,���������Ƕ�����֯�򷵻ظö���������Ķ�����֯,���������Ƕ�����֯id,�򷵻ظ���֯
	 * @param groupid
	 * @return
	 */
	public List getGroupByParentGroupOrGroup(Integer groupid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressCommongroupDAO addresscommongroupDAO = new AddressCommongroupDAO();
		addresscommongroupDAO.setWhereClause(" parentid=" + groupid + " or id=" + groupid + " and levels=1 ");
		factory.setDAO(addresscommongroupDAO);
		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("���ҷ�������Ա����");
		}
		return list;
	}
	/**
	 * ȡ����Ա��������
	 * @param groupid
	 * @return
	 */
	public List personGroup(String userid, String grouptype) {
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupinfoDAO addressGroupinfoDAO = new AddressGroupinfoDAO();
		addressGroupinfoDAO.setUserid(userid);
		addressGroupinfoDAO.setGrouptype(grouptype);
		factory.setDAO(addressGroupinfoDAO);
		List list = null;
		try {
			list = factory.find(new AddressGroupinfoVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ����Ա�����������" + e);
		}
		return list;
	}
	/**
	 * ɾ��������Ա
	 * @param userid
	 * @param groupid
	 */
	public void delPersonInGroup(String userid, Integer groupid, String grouptype) {
		AddressGroupinfoDAO addressGroupinfoDAO = new AddressGroupinfoDAO();
		if (userid != null) {
			addressGroupinfoDAO.setUserid(userid);

		}
		addressGroupinfoDAO.setGroupid(groupid);
		addressGroupinfoDAO.setConnection(conn);

		addressGroupinfoDAO.setGrouptype(grouptype);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(addressGroupinfoDAO);
		try {
			factory.batchDelete();
		} catch (DAOException e) {
			throw new RuntimeException("ɾ����������Ա����" + e);
		}
	}
	
	/**
	 * ȡ�ø��˷�����Ϣ
	 * @param groupid
	 * @return
	 * @throws RuntimeException
	 */
	public AddressGroupVO getIndiGroup(Integer groupid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setId(groupid);
		factory.setDAO(addressGroupDAO);
		AddressGroupVO vo = null;
		try {
			vo = (AddressGroupVO) factory.findByPrimaryKey(new AddressGroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ�÷�����Ϣ����");
		}
		return vo;
	}

	/**
	 * ȡ�ù���������Ϣ
	 * @param groupid
	 * @return
	 * @throws RuntimeException
	 */
	public AddressCommongroupVO getCommonGroup(Integer groupid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressCommongroupDAO addressCommongroupDAO = new AddressCommongroupDAO();
		addressCommongroupDAO.setId(groupid);
		factory.setDAO(addressCommongroupDAO);
		AddressCommongroupVO vo = null;
		try {
			vo = (AddressCommongroupVO) factory.findByPrimaryKey(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ�÷�����Ϣ����");
		}
		return vo;
	}

	/**
	 * �жϷ�����Ա�Ƿ��Ѵ���
	 * @param userid
	 * @param groupid
	 * @param grouptype
	 * @return
	 */
	public boolean isPersonInGroup(String userid, Integer groupid, String grouptype) {
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupinfoDAO addressGroupinfoDAO = new AddressGroupinfoDAO();
		addressGroupinfoDAO.setUserid(userid);
		addressGroupinfoDAO.setGroupid(groupid);
		addressGroupinfoDAO.setGrouptype(grouptype);
		factory.setDAO(addressGroupinfoDAO);
		List list = null;
		try {
			list = factory.find(new AddressGroupinfoVO());
		} catch (DAOException e) {
			throw new RuntimeException("�жϷ�����Ա�Ƿ���ڴ���" + e);
		}
		if (list.size() == 0)
			return false;
		else
			return true;
	}

	/**
	 * �ṩ���ļ����ݵĽӿ�,�������˼�����˷�����,��������Ϊ�����˵�����,������ֻ��һ���˼�Ϊ������
	 * @param userid
	 * @param groupid
	 * @param grouptype
	 * @return
	 * @throws EntityException
	 */

	public Integer AddPersonGroupByFileTran(String sendPerson_uuid, String my_uuid) throws EntityException {

		Integer flag = new Integer(0);
		EntityManager entityManager = EntityManager.getInstance();
		Person person = entityManager.findPersonByUuid(sendPerson_uuid);

		if (isIndiGroupNameExist(person.getFullName(), my_uuid)) {
		} else {
			AddressGroupVO addressGroupVO = new AddressGroupVO();
			addressGroupVO.setGroupname(person.getFullName());
			addressGroupVO.setGroupdes(person.getFullName() + "���ҷ��ʼ����������˷���");
			addressGroupVO.setGroupuser(my_uuid);
			flag = addGroup(addressGroupVO);
		}
		return flag;
	}

	public String getCnnamebyUUID(String personuuid) throws HandlerException {

		Connection conn = null;
		List list = null;
		String cnname = null;

		try {
			conn = DBConnectionLocator.getInstance().getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			ConnLog.open("Group.getCnnamebyUUID");
			DAOFactory factory = new DAOFactory(conn);
			SysPersonDAO dao = new SysPersonDAO();
			dao.setPersonuuid(personuuid);
			factory.setDAO(dao);

			list = factory.find(new SysPersonVO());
		} catch (DAOException e) {
			throw new RuntimeException("�����˵�uuid�õ��˵�����������");
		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
			throw new HandlerException(e);
		}finally{
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("Group.getCnnamebyUUID");
				} catch (SQLException e1) {
					e1.printStackTrace();
					throw new HandlerException(e1);
				}
			}
		}

		if (list != null) {
			if (list.size() > 0) {
				SysPersonVO vo = (SysPersonVO) list.get(0);
				cnname = vo.getCnname();
			}
		}



		return cnname;
	}

	public Integer addTransfer(AddressTransferVO vo) {

		AddressTransferDAO dao = new AddressTransferDAO();
		dao.setCommongroup(vo.getCommongroup());
		dao.setPersonuuid(vo.getPersonuuid());
		dao.setConnection(conn);

		try {
			conn.setAutoCommit(true);
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.getId();
	}

	public boolean isTransfer(Integer id) {

		AddressTransferDAO dao = new AddressTransferDAO();
		dao.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressTransferVO());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list != null) {
			if (list.size() > 0)
				return true;
		}
		return false;
	}

	public void delTransfer(Integer Id) {

		AddressTransferDAO dao = new AddressTransferDAO();
		dao.setId(Id);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTransfer(AddressTransferVO vo) {

		DAOFactory factory = new DAOFactory(conn);
		AddressTransferDAO dao = new AddressTransferDAO();

		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setId(vo.getId());

		try {
			dao = (AddressTransferDAO) factory.findByPrimaryKey();
			dao.setCommongroup(vo.getCommongroup());
			dao.setPersonuuid(vo.getPersonuuid());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ȡ��תȨ����Ա�б�
	 * @param GroupId�����������ID��
	 */
	public List getTransfList(Integer GroupId) {
		AddressTransferDAO dao = new AddressTransferDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setCommongroup(GroupId);
		List list = null;
		try {
			list = factory.find(new AddressTransferVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ��ȡ��תȨ����Ա�б����");
		}
		return list;
	}

	public List getAppRolePersonList(String roleCode) {

		List list = null;
		try {
			AppRole role = new AppRole();
			role.setRolecode(roleCode);
			list = role.getPrivilegedPersons();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ȡ���Լ�����Ķ�������
	 * @param
	 * @param parentid
	 */
	public List listCommonTwoGroupbyMY(Integer parentid, String uuid) {

		AddressCommongroupAddressTransferSearchDAO dao = new AddressCommongroupAddressTransferSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		StringBuffer sql = new StringBuffer();
		sql.append(
			"SELECT ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.NEEDACCREDIT,ADDRESS_COMMONGROUP.OWNER,ADDRESS_COMMONGROUP.LEVELS,ADDRESS_COMMONGROUP.ROOTID,ADDRESS_COMMONGROUP.PARENTID,ADDRESS_TRANSFER.PERSONUUID,ADDRESS_TRANSFER.COMMONGROUP ");
		sql.append("FROM ADDRESS_COMMONGROUP,ADDRESS_TRANSFER ");
		sql.append(" where ADDRESS_COMMONGROUP.LEVELS = 1 ");
		sql.append(" and ADDRESS_COMMONGROUP.PARENTID = ");
		sql.append(parentid);
		sql.append(" and ADDRESS_COMMONGROUP.Id = ADDRESS_TRANSFER.COMMONGROUP");
		sql.append(" and ADDRESS_TRANSFER.PERSONUUID = '");
		sql.append(uuid);
		sql.append("'");
		sql.append(" order by ADDRESS_COMMONGROUP.GROUPNAME ");
		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new AddressCommongroupAddressTransferVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ���Լ��������Ӧ�Ķ������������б����");
		}
		return list;
	}

	/**
	 * �жϸö��������Ƿ����Լ����Թ���Ķ�������
	 * @param
	 * @param parentid
	 */
	public Integer IsPowerCommonTwoGroupbyMY(Integer groupid, String uuid) {

		AddressCommongroupAddressTransferSearchDAO dao = new AddressCommongroupAddressTransferSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		StringBuffer sql = new StringBuffer();
		sql.append(
			"SELECT ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.NEEDACCREDIT,ADDRESS_COMMONGROUP.OWNER,ADDRESS_COMMONGROUP.LEVELS,ADDRESS_COMMONGROUP.ROOTID,ADDRESS_COMMONGROUP.PARENTID,ADDRESS_TRANSFER.PERSONUUID,ADDRESS_TRANSFER.COMMONGROUP ");
		sql.append("FROM ADDRESS_COMMONGROUP,ADDRESS_TRANSFER ");
		sql.append(" where ADDRESS_COMMONGROUP.LEVELS = 1 ");
		sql.append(" and ADDRESS_COMMONGROUP.Id = ");
		sql.append(groupid);
		sql.append(" and ADDRESS_COMMONGROUP.Id = ADDRESS_TRANSFER.COMMONGROUP");
		sql.append(" and ADDRESS_TRANSFER.PERSONUUID = '");
		sql.append(uuid);
		sql.append("'");
		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new AddressCommongroupAddressTransferVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ���Լ��������Ӧ�Ķ������������б����");
		}

		if (list.size() > 0)
			return new Integer(1);
		else
			return new Integer(0);
	}

	/**
	 * ȡ�����˹���Ķ�������
	 * @param
	 * @param parentid
	 */
	public List listCommonTwoGroupByYour(Integer parentid, String str) {

		AddressCommongroupSearchDAO dao = new AddressCommongroupSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		StringBuffer sql = new StringBuffer();
		sql.append(
			"SELECT ADDRESS_COMMONGROUP.ID,ADDRESS_COMMONGROUP.GROUPNAME,ADDRESS_COMMONGROUP.GROUPDES,ADDRESS_COMMONGROUP.NEEDACCREDIT,ADDRESS_COMMONGROUP.OWNER,ADDRESS_COMMONGROUP.LEVELS,ADDRESS_COMMONGROUP.ROOTID,ADDRESS_COMMONGROUP.PARENTID ");
		sql.append(" FROM ADDRESS_COMMONGROUP ");
		sql.append(" where ADDRESS_COMMONGROUP.LEVELS = 1 ");
		sql.append(" and ADDRESS_COMMONGROUP.PARENTID = ");
		sql.append(parentid);
		if (!"".equals(str)) {
			sql.append(" and ADDRESS_COMMONGROUP.ID NOT IN (");
			sql.append(str);
			sql.append(")");
		}
		sql.append(" order by ADDRESS_COMMONGROUP.GROUPNAME ");

		dao.setSearvhSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new AddressCommongroupVO());
		} catch (DAOException e) {
			throw new RuntimeException("ȡ�����˹�����Ӧ�Ķ������������б����");
		}
		return list;
	}

}
