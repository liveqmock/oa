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
	 * 获得该用户的公共分组列表
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
			throw new RuntimeException("获得人员的公共分组出错！");
		}
		return list;
	}

	/**
	 * 获得该用户的公共根分组列表
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
			throw new RuntimeException("获得人员的公共分根组出错！");
		}
		return list;
	}

	/**
	 * 获得该用户的公共二级分组列表
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
			throw new RuntimeException("获得人员的公共二级分根组出错！");
		}
		return list;
	}

	/**
	 * 获得该用户的私有分组列表
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
			throw new RuntimeException("获得人员的私有分组出错！" + e);
		}
		return list;
	}

	/**
	 * 获得指定二级分组的所有者
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
			throw new RuntimeException("获得二级分组时出错！" + e);
		}
	}
	/**
	 * 得到个人用户公共分组sql
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
	 * 得到个人用户公共根分组分组sql
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
	 * 增加个人分组
	 * @param addressGroupVO
	 */
	public Integer addGroup(AddressGroupVO addressGroupVO) {
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setValueObject(addressGroupVO);
		addressGroupDAO.setConnection(conn);
		try {
			addressGroupDAO.create();
		} catch (DAOException e) {
			throw new RuntimeException("增加分组出错");
		}
		return addressGroupDAO.getId();
	}

	/**
	 * 修改个人分组
	 * @param addressGroupVO
	 */
	public void alterGroup(AddressGroupVO addressGroupVO) {
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setValueObject(addressGroupVO);
		addressGroupDAO.setConnection(conn);
		try {
			addressGroupDAO.update(true);
		} catch (DAOException e) {
			throw new RuntimeException("修改分组出错");
		}
	}

	/**
	 * 删除分组
	 * @param groupid
	 */
	public void delGroup(Integer groupid) {
		AddressGroupDAO addressGroupDAO = new AddressGroupDAO();
		addressGroupDAO.setId(groupid);
		addressGroupDAO.setConnection(conn);
		try {
			addressGroupDAO.delete();
		} catch (DAOException e) {
			throw new RuntimeException("删除分组出错");
		}
	}

	/**
	 * 将某个分组的使用权分给用户
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
			throw new RuntimeException("分组授权出错！");
		}
	}

	/**
	 * 判断是否分配过
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
			throw new RuntimeException("分组授权出错！");
		}
		if (list.size() == 0) {
			return false;
		} else
			return true;

	}

	/**
	 * 取得分组授权人员列表
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
			throw new RuntimeException("分组授权出错！");
		}
		return list;
	}
	/**
	 * 取得授权人员分组列表
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
			throw new RuntimeException("取得授权人员分组列表出错！");
		}
		return list;
	}

	/**
	 * 删除分组授权人
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
			throw new RuntimeException("删除分组授权人出错！" + e);
		}
	}

	/**
	 * 删除分组中所有的授权人
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
			throw new RuntimeException("删除分组中所有的授权人出错！" + e);
		}
	}

	/**
	 * 取得公共分组
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
			throw new RuntimeException("取得公共分组列表出错！");
		}
		return list;
	}

	/**
	 * 取得自己的已被授权和不需要授权的一级公共分组列表
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
			throw new RuntimeException("取得有权阅读的一级公共分组出错！");
		}
		return list;
	}

	/**
	 * 取得全部的二级公共分组
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
			throw new RuntimeException("取得全部的公共分组列表出错！");
		}
		return list;
	}

	/**
	 * 取得二级公共分组
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
			throw new RuntimeException("取得相应的二级公共分组列表出错！");
		}
		return list;
	}

	/**
	 * 取得自己的二级公共分组
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
			throw new RuntimeException("取得相应的二级公共分组列表出错！");
		}
		return list;
	}

	/**
	 * 取得他人的二级公共分组
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
			throw new RuntimeException("取得相应的二级公共分组列表出错！");
		}
		return list;
	}

	/**
	 * 增加公共分组
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
			throw new RuntimeException("增加公共分组出错！");
		}

		return dao.getId();
	}

	/**
	 * 修改公共分组
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
			throw new RuntimeException("修改公共分组出错！");
		}

	}

	/**
	 * 删除公共分组
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
			throw new RuntimeException("删除公共分组出错！");
		}
	}

	/**
	 * 为分组添加人员
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
			throw new RuntimeException("为分组添加人员出错！");
		}
	}

	/**
	 * 判断个人分组名称是否存在
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
			throw new RuntimeException("查找分组名称错误！");
		}
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 判断个人分组名称是否存在,出去修改个人分组描述的重名情况
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
			throw new RuntimeException("查找分组名称错误！");
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
	 * 添加一级公共和二级公共分组时判断公共分组名称是否存在
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
			throw new RuntimeException("查找分组名称错误！");
		}
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 修改一级公共和二级公共分组时判断公共分组名称是否存在
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
			throw new RuntimeException("查找分组名称错误！");
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
	 * 取得分组里的人员
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
	 * 取得分组里的人员
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
			throw new RuntimeException("查找分组内人员错误！");
		}
		return list;
	}*/
	
	/**
	 * 取得分组里的人员 add by ly
	 * @param groupID
	 * @param groupType
	 * @return
	 */
	public List personInGroup(Integer groupid,String grouptype){
		List addressgroupinfolist = new ArrayList();
		Statement st;
		try {
			st = conn.createStatement();
			//取分组人员时,过滤R1中已删除人员
			StringBuffer sb=new StringBuffer();
			sb.append("SELECT T.USERID,T.GROUPID,T.GROUPTYPE,C.DELTAG FROM ADDRESS_GROUPINFO T ");
			sb.append(" LEFT JOIN ROEEE.RO_PERSONACCOUNT C ON C.PERSONUUID=T.USERID ");
			sb.append(" WHERE T.GROUPID="+String.valueOf(groupid)+" AND C.DELTAG=0 AND T.GROUPTYPE='"+grouptype+"'");
			
			//执行查询
			ResultSet rs=st.executeQuery(sb.toString());
			while(rs.next()){
				AddressGroupinfoVO addressGroupinfoVO=new AddressGroupinfoVO();
				addressGroupinfoVO.setGroupid(rs.getInt("GROUPID"));
				addressGroupinfoVO.setGrouptype(rs.getString("GROUPTYPE"));
				addressGroupinfoVO.setUserid(rs.getString("USERID"));
				addressgroupinfolist.add(addressGroupinfoVO);
			}
			System.out.println("当前用户组:"+String.valueOf(groupid)+",实际发送人员为:"+addressgroupinfolist.size());
			rs.close();
			st.close();
		} catch (SQLException e1) {
			System.out.println("文件发送,获取用户组人员失败,请联系系统管理员"+e1);
		}
		return addressgroupinfolist;
	}

	/**
	 * 取得顶级分组里的二级组织
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
			throw new RuntimeException("查找分组内人员错误！");
		}
		return list;
	}
	/**
	 * 取得顶级分组里的二级组织,如果输入的是顶级组织则返回该顶级分组里的二级组织,如果输入的是二级组织id,则返回该组织
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
			throw new RuntimeException("查找分组内人员错误！");
		}
		return list;
	}
	/**
	 * 取得人员所分在组
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
			throw new RuntimeException("取得人员所分在组错误！" + e);
		}
		return list;
	}
	/**
	 * 删除分组人员
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
			throw new RuntimeException("删除分组内人员错误！" + e);
		}
	}
	
	/**
	 * 取得个人分组信息
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
			throw new RuntimeException("取得分组信息错误！");
		}
		return vo;
	}

	/**
	 * 取得公共分组信息
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
			throw new RuntimeException("取得分组信息错误！");
		}
		return vo;
	}

	/**
	 * 判断分组人员是否已存在
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
			throw new RuntimeException("判断分组人员是否存在错误！" + e);
		}
		if (list.size() == 0)
			return false;
		else
			return true;
	}

	/**
	 * 提供给文件传递的接口,将寄信人加入个人分组中,分组名字为寄信人的名字,分组中只有一个人即为寄信人
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
			addressGroupVO.setGroupdes(person.getFullName() + "给我发邮件后将其加入个人分组");
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
			throw new RuntimeException("根据人的uuid得到人的中文名出错！");
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
	 * 取得转权的人员列表
	 * @param GroupId（二级分组的ID）
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
			throw new RuntimeException("取得取得转权的人员列表出错！");
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
	 * 取得自己管理的二级分组
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
			throw new RuntimeException("取得自己管理的相应的二级公共分组列表出错！");
		}
		return list;
	}

	/**
	 * 判断该二级分组是否是自己可以管理的二级分组
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
			throw new RuntimeException("取得自己管理的相应的二级公共分组列表出错！");
		}

		if (list.size() > 0)
			return new Integer(1);
		else
			return new Integer(0);
	}

	/**
	 * 取得他人管理的二级分组
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
			throw new RuntimeException("取得他人管理相应的二级公共分组列表出错！");
		}
		return list;
	}

}
