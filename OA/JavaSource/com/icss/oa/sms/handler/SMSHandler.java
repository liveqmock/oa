package com.icss.oa.sms.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.address.vo.AddressGroupinfoVO;
import com.icss.oa.filetransfer.dao.SysOrgDAO;
import com.icss.oa.filetransfer.dao.SysOrgpersonDAO;
import com.icss.oa.filetransfer.vo.SysOrgVO;
import com.icss.oa.filetransfer.vo.SysOrgpersonVO;
import com.icss.oa.hr.dao.HRSysPersonDAO;
import com.icss.oa.hr.handler.HRGroupHandler;
import com.icss.oa.hr.vo.HRSysPersonVO;
import com.icss.oa.intendwork.dao.OfficePendingDAO;
import com.icss.oa.phonebook.dao.SysPersonDAO;
import com.icss.oa.phonebook.vo.SysPersonVO;
import com.icss.oa.sms.dao.HRSMSMobileSearchDAO;
import com.icss.oa.sms.dao.RoSIDDAO;
import com.icss.oa.sms.dao.SMSHistoryDAO;
import com.icss.oa.sms.dao.SMSIdDAO;
import com.icss.oa.sms.dao.SMSPersonRoleDAO;
import com.icss.oa.sms.dao.SMSPersonRoleSearchDAO;
import com.icss.oa.sms.dao.SMSPersonRoleSysPersonSearchDAO;
import com.icss.oa.sms.dao.SMSReceiveDAO;
import com.icss.oa.sms.dao.SMSRoleDAO;
import com.icss.oa.sms.util.SMSProperties;
import com.icss.oa.sms.vo.HRSMSMobileVO;
import com.icss.oa.sms.vo.SMSHistoryVO;
import com.icss.oa.sms.vo.SMSIdVO;
import com.icss.oa.sms.vo.SMSPersonRoleSearchVO;
import com.icss.oa.sms.vo.SMSPersonRoleSysPersonSearchVO;
import com.icss.oa.sms.vo.SMSPersonRoleVO;
import com.icss.oa.sms.vo.SMSReceiveVO;
import com.icss.oa.sms.vo.SMSRoleVO;

public class SMSHandler {
	private Connection conn;
	private static String MAX_SMSID = SMSProperties.readValue("MAX_SMSID");
	
	public SMSHandler(Connection conn) {
		this.conn = conn;
	}

	public String getPersonName(String personuuid) {
		String name = "";
		HRSysPersonDAO dao = new HRSysPersonDAO();
		dao.setPersonuuid(personuuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find(new HRSysPersonVO());
			if (list != null) {
				HRSysPersonVO personVO = (HRSysPersonVO) list.get(0);
				name = personVO.getCnname();

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return name;
	}

	/**
	 * 从人事视图取得Mobile
	 * 
	 * @param personuuid
	 * @return
	 */
	public HRSMSMobileVO getHRSMSMobileVO(String personuuid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("SYS_PERSON.PERSONUUID,SYS_PERSON.CNNAME,SYS_PERSON.USERID,HRPERSON.MOBILEPHONE,HRPERSON.DEPTNAME,HRPERSON.ORGCODE  ");
		sql.append("FROM ");
		sql.append("SYS_PERSON,HRPERSON ");
		sql.append(" WHERE ");
		sql.append("SYS_PERSON.HRID=HRPERSON.HRID AND SYS_PERSON.PERSONUUID= '"
				+ personuuid + "' AND SYS_PERSON.DELTAG='0' ");
		// System.out.println(sql.toString());
		HRSMSMobileSearchDAO dao = new HRSMSMobileSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find(new HRSMSMobileVO());
			if (list != null && list.size() > 0) {
				HRSMSMobileVO personVO = (HRSMSMobileVO) list.get(0);
				return personVO;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	/**
	 * 取得发送的手机号码
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @param s4
	 * @return
	 */
	public List getSendtoPhone(String s1, String s2, String s3, String s4) {

		Integer selectedNum = 0;
		List legalAddressList = new ArrayList();
		List Addresslist = new ArrayList();
		StringBuffer legalString = new StringBuffer();
		StringBuffer illegalString = new StringBuffer();
		StringBuffer nameString = new StringBuffer();
		// 分别得到个人＆个人＆公共分组的字符串
		StringTokenizer addresslist = new StringTokenizer(s1, ",");
		// System.out.println("s1 =" + s1);
		StringTokenizer addresslist2 = new StringTokenizer(s2, ",");
		// System.out.println("s2 =" + s2);
		StringTokenizer addresslist3 = new StringTokenizer(s3, ",");

		StringTokenizer addresslist4 = new StringTokenizer(s4, ",");

		// System.out.println("公共分组。。。。s3 =" + s3);

		// 构造取得分组的handler
		Group group = new Group(conn);
		// 公共分组的handler
		HRGroupHandler handler = new HRGroupHandler(conn);

		// 对取得的个人信息进行解析
		while (addresslist.hasMoreTokens()) {

			selectedNum++;
			String uuid = addresslist.nextToken();
			// System.out.println("uuid=" + uuid);

			String phone = null;
			String name = "";
			try {
				HRSMSMobileVO vo = this.getHRSMSMobileVO(uuid);

				// System.out.println("phone!!!!" + phone);
				if (vo != null) {
					phone = vo.getMobilephone();
					name = vo.getUsername();
					// System.out.println("~~~~~~~~~~~" + phone + name);
					if (phone != null) {
						if (!legalAddressList.contains(phone)) {
							legalString.append(phone).append(",");
							nameString.append(name).append(",");
							legalAddressList.add(phone);
							// System.out.println(legalString + "" +
							// legalAddressList);
						}
					} else {
						illegalString.append(name).append(",");
					}
				} else {
					illegalString.append(this.getPersonName(uuid)).append(",");
				}
			} catch (Exception e) {
				phone = "";
				e.printStackTrace();
			}
		}

		// 对取得个人分组信息进行解析
		while (addresslist2.hasMoreTokens()) {

			List addressgroupinfolist = group.personInGroup(Integer
					.valueOf(addresslist2.nextToken()), "2");
			// System.out.println("addressgroupinfolist.size() ="+
			// addressgroupinfolist.size());
			Iterator addressgroupinfoiterator = addressgroupinfolist.iterator();
			// System.out.println("addressgroupinfolist.size()1111 ="+
			// addressgroupinfolist.size());
			while (addressgroupinfoiterator.hasNext()) {
				selectedNum++;
				String phone = null;
				String name = "";
				try {
					AddressGroupinfoVO addressgroupinfovo = (AddressGroupinfoVO) addressgroupinfoiterator
							.next();

					String uuid = addressgroupinfovo.getUserid();
					// System.out.println("uuid =" + uuid);
					HRSMSMobileVO vo = this.getHRSMSMobileVO(uuid);

					if (vo != null) {
						phone = vo.getMobilephone();
						name = vo.getUsername();
						if (phone != null) {
							if (!legalAddressList.contains(phone)) {
								legalString.append(phone).append(",");
								nameString.append(name).append(",");
								legalAddressList.add(phone);
							}
						} else {
							illegalString.append(name).append(",");
						}
					} else {

						illegalString.append(this.getPersonName(uuid)).append(
								",");
					}
				} catch (Exception e) {
					phone = "";
					e.printStackTrace();
				}

			}
		}

		// 对取得人事分组的信息进行解析

		while (addresslist3.hasMoreTokens()) {
			// List grouplist = group.getGroupByParentGroupOrGroup(new Integer(
			// addresslist3.nextToken()));
			List grouplist = handler.getAllUuidByGroupid(addresslist3
					.nextToken());
			Iterator groupiterator = grouplist.iterator();

			while (groupiterator.hasNext()) {

				HRSysPersonVO vo = (HRSysPersonVO) groupiterator.next();

				selectedNum++;
				String phone = null;
				String name = "";

				try {
					HRSMSMobileVO vo1 = this.getHRSMSMobileVO(vo
							.getPersonuuid());
					if (vo1 != null) {
						phone = vo1.getMobilephone();
						name = vo1.getUsername();
						if (phone != null) {
							if (!legalAddressList.contains(phone)) {
								legalString.append(phone).append(",");
								nameString.append(name).append(",");

								legalAddressList.add(phone);
							}
						} else {
							illegalString.append(name).append(",");
						}
					} else {
						illegalString.append(
								this.getPersonName(vo.getPersonuuid())).append(
								",");
					}
				} catch (Exception e) {
					phone = "";
					e.printStackTrace();
				}

			}
		}

		// 公共分组
		while (addresslist4.hasMoreTokens()) {

			List grouplist = group.getGroupByParentGroupOrGroup(new Integer(
					addresslist4.nextToken()));
			Iterator groupiterator = grouplist.iterator();

			while (groupiterator.hasNext()) {

				AddressCommongroupVO addresscommongroupvo = (AddressCommongroupVO) groupiterator
						.next();

				List addressgroupinfolist = group.personInGroup(
						addresscommongroupvo.getId(), "1");
				Iterator addressgroupinfoiterator = addressgroupinfolist
						.iterator();

				while (addressgroupinfoiterator.hasNext()) {

					selectedNum++;
					String phone = null;
					String name = "";
					try {
						AddressGroupinfoVO addressgroupinfovo = (AddressGroupinfoVO) addressgroupinfoiterator
								.next();

						String uuid = addressgroupinfovo.getUserid();
						// System.out.println("uuid =" + uuid);
						HRSMSMobileVO vo = this.getHRSMSMobileVO(uuid);

						if (vo != null) {
							phone = vo.getMobilephone();
							name = vo.getUsername();
							if (phone != null) {
								if (!legalAddressList.contains(phone)) {
									legalString.append(phone).append(",");
									nameString.append(name).append(",");
									legalAddressList.add(phone);
								}
							} else {
								illegalString.append(name).append(",");
							}
						} else {

							illegalString.append(this.getPersonName(uuid))
									.append(",");
						}
					} catch (Exception e) {
						phone = "";
						e.printStackTrace();
					}
				}
			}
		}

		// 全部发送的名字串
		Addresslist.add(0, nameString);
		// 全部可以发送的地址名称，例如：13810111011,13954121233
		Addresslist.add(1, legalString);
		// 可以发送的地址名称的集合，集合的每个元素是String类型的对象
		// 例如：
		// legalAddressList.get(0) = "13810111011"
		// legalAddressList.get(1) = "13954121233"
		Addresslist.add(2, legalAddressList);
		// 没有手机号的用户名
		Addresslist.add(3, illegalString);
		// 选择的人数
		Addresslist.add(4, selectedNum);
		return Addresslist;

	}

	/**
	 * 保存短信发送历史纪录到数据库
	 * 
	 * @param senderuuid
	 * @param sendername
	 * @param receivername
	 * @param content
	 */
	public void setHistory(String senderuuid, String sendername,
			String receivername, String content) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat timeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String time = timeFormat.format(calendar.getTime());
		SMSHistoryDAO dao = new SMSHistoryDAO();
		dao.setTime(time);
		dao.setReceivername(receivername);
		dao.setSendername(sendername);
		dao.setSenderuuid(senderuuid);
		dao.setContent(content);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 取得所有分组
	 * 
	 * @return
	 */
	public List getRole() {
		SMSRoleDAO dao = new SMSRoleDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("rolename", true);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new SMSRoleVO());

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 判断权限分组名称是否存在
	 * 
	 * @param rolename
	 * @return
	 */
	public boolean isRolenameExist(String rolename) {
		DAOFactory factory = new DAOFactory(conn);
		SMSRoleDAO roleDAO = new SMSRoleDAO();
		roleDAO.setRolename(rolename);
		factory.setDAO(roleDAO);
		List list = null;
		try {
			list = factory.find(new SMSRoleVO());
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
	 * 修改权限分组时，判断权限分组名称是否存在
	 * 
	 * @param rolename，id
	 * @return
	 */
	public boolean isRoleNameExist(String rolename, Integer id) {

		boolean flag = true;
		DAOFactory factory = new DAOFactory(conn);
		SMSRoleDAO roleDAO = new SMSRoleDAO();
		roleDAO.setRolename(rolename);
		factory.setDAO(roleDAO);
		List list = null;
		try {
			list = factory.find(new SMSRoleVO());
		} catch (DAOException e) {
			throw new RuntimeException("查找分组名称错误！");
		}
		if (list.size() == 0) {
			return false;
		} else {
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SMSRoleVO vo = (SMSRoleVO) it.next();
					if (id.intValue() == vo.getId().intValue()) {
						flag = false;
					}
				}
			}
			return flag;
		}

	}

	/**
	 * 增加权限
	 * 
	 * @param SMSRoleVO
	 */
	public void addRole(SMSRoleVO vo) {
		SMSRoleDAO roleDAO = new SMSRoleDAO();
		roleDAO.setValueObject(vo);
		roleDAO.setConnection(conn);
		try {
			roleDAO.create();
		} catch (DAOException e) {
			throw new RuntimeException("增加权限组出错");
		}

	}

	/**
	 * 修改权限
	 * 
	 * @param SMSRoleVO
	 */
	public void alterRole(SMSRoleVO vo) {
		SMSRoleDAO roleDAO = new SMSRoleDAO();
		roleDAO.setValueObject(vo);
		roleDAO.setConnection(conn);
		try {
			roleDAO.update(true);
		} catch (DAOException e) {
			throw new RuntimeException("修改权限组出错");
		}
	}

	/**
	 * 删除权限
	 * 
	 * @param id
	 */
	public void delRole(Integer id) {
		SMSRoleDAO roleDAO = new SMSRoleDAO();
		roleDAO.setId(id);
		roleDAO.setConnection(conn);
		try {
			roleDAO.delete();

		} catch (DAOException e) {
			throw new RuntimeException("删除权限组出错");
		}
	}

	/**
	 * 取得分组里的人员
	 * 
	 * @param id
	 * @return
	 */
	public List getPersonInRole(Integer id) {

		SMSPersonRoleSysPersonSearchDAO dao = new SMSPersonRoleSysPersonSearchDAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("TB_SMS_PERSONROLE.USERID,TB_SMS_PERSONROLE.SMSR_ID,SYS_PERSON.PERSONUUID ");
		sql.append("FROM ");
		sql.append("TB_SMS_PERSONROLE,SYS_PERSON ");
		sql.append(" WHERE ");
		sql
				.append("SYS_PERSON.PERSONUUID=TB_SMS_PERSONROLE.USERID AND TB_SMS_PERSONROLE.SMSR_ID= '"
						+ id + "'");
		sql.append(" order by TB_SMS_PERSONROLE.USERID");
		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new SMSPersonRoleSysPersonSearchVO());
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 判断分组人员是否已存在
	 * 
	 * @param userid
	 * @param id
	 * @param grouptype
	 * @return
	 */
	public boolean isPersonInRole(String userid, Integer id) {
		DAOFactory factory = new DAOFactory(conn);
		SMSPersonRoleDAO roleinfoDAO = new SMSPersonRoleDAO();
		roleinfoDAO.setUserid(userid);
		roleinfoDAO.setId(id);
		factory.setDAO(roleinfoDAO);
		List list = null;
		try {
			list = factory.find(new SMSPersonRoleVO());
		} catch (DAOException e) {
			throw new RuntimeException("判断分组人员是否存在错误！" + e);
		}
		if (list.size() == 0)
			return false;
		else
			return true;
	}

	/**
	 * 为分组添加人员
	 * 
	 * @param userid
	 * @param groupid
	 */
	public void appendRoleWithPerson(String userid, Integer id) {
		SMSPersonRoleDAO roleinfoDAO = new SMSPersonRoleDAO();
		roleinfoDAO.setUserid(userid);
		roleinfoDAO.setId(id);
		roleinfoDAO.setConnection(conn);
		try {
			roleinfoDAO.create();
		} catch (DAOException e) {
			throw new RuntimeException("为分组添加人员出错！");
		}
	}

	/**
	 * 删除分组人员
	 * 
	 * @param userid
	 * @param id
	 */
	public void delRolePerson(String userid, Integer id) throws DAOException {
		SMSPersonRoleDAO roleinfoDAO = new SMSPersonRoleDAO();
		roleinfoDAO.setUserid(userid);
		roleinfoDAO.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(roleinfoDAO);
		factory.batchDelete();
	}

	/**
	 * 取得人员的短信权限
	 * 
	 * @param personuuid
	 * @return
	 */
	public List getPersonSMSRole(String personuuid) {

		SMSPersonRoleSearchDAO dao = new SMSPersonRoleSearchDAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("TB_SMS_PERSONROLE.USERID,TB_SMS_ROLE.ISOUT,TB_SMS_ROLE.ISHISTORY,TB_SMS_ROLE.SENDNUMBER");
		sql.append(" FROM ");
		sql.append("TB_SMS_PERSONROLE,TB_SMS_ROLE ");
		sql.append(" WHERE ");
		sql
				.append("TB_SMS_PERSONROLE.SMSR_ID=TB_SMS_ROLE.SMSR_ID AND TB_SMS_PERSONROLE.USERID= '"
						+ personuuid + "'");
		sql.append(" order by TB_SMS_PERSONROLE.USERID");
		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new SMSPersonRoleSearchVO());
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 取得所有的短信历史纪录
	 * 
	 * @param
	 * @return
	 * @throws DAOException
	 */
	public List getAllHistory() throws DAOException {
		SMSHistoryDAO dao = new SMSHistoryDAO();
		dao.addOrderBy("id", false);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new SMSHistoryVO());
		return list;
	}

	/**
	 * 取得个人的短信历史纪录
	 * 
	 * @param userid
	 * @return
	 * @throws DAOException
	 */
	public List getPersonHistory(String userid) throws DAOException {
		SMSHistoryDAO dao = new SMSHistoryDAO();
		dao.setSenderuuid(userid);
		dao.addOrderBy("id", false);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new SMSHistoryVO());
		return list;
	}

	/**
	 * 取得某个ID的短信历史纪录
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public List getHistoryById(Integer id) throws DAOException {
		SMSHistoryDAO dao = new SMSHistoryDAO();
		dao.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new SMSHistoryVO());
		return list;
	}

	/**
	 * 删除某个ID的短信历史纪录
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public void delSMSHistory(Integer id) throws DAOException {
		SMSHistoryDAO dao = new SMSHistoryDAO();
		dao.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		factory.batchDelete();
	}

	/**
	 * 取得组织名
	 * 
	 * @param uuid
	 * @return
	 * @throws HandlerException
	 */
	public String getOrgName(String uuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonDAO dao1 = new SysOrgpersonDAO();
		dao1.setPersonuuid(uuid);
		factory.setDAO(dao1);
		String orguuid = "";
		try {
			List list1 = factory.find(new SysOrgpersonVO());
			if (!list1.isEmpty()) {
				orguuid = ((SysOrgpersonVO) list1.get(0)).getOrguuid();
			}
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);

		dao.setOrguuid(orguuid);
		String name = "";

		try {
			List list = factory.find(new SysOrgVO());
			Iterator it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					SysOrgVO vo = (SysOrgVO) it.next();
					Integer orglevel = vo.getOrglevel();
					if (orglevel.intValue() == 3 || orglevel.intValue() == 0
							|| orglevel.intValue() == 1
							|| orglevel.intValue() == 2) {
						name = vo.getCnname();
					}

					if (orglevel.intValue() == 4) {
						String parentOrgName = this.getOrgNameByuuid(vo
								.getParentorguuid());
						name = parentOrgName;
					}

				}
			}

			return name;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public String getOrgNameByuuid(String orguuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(orguuid);
		String name = "";

		try {
			List list = factory.find(new SysOrgVO());
			if (!list.isEmpty()) {

				SysOrgVO vo = (SysOrgVO) list.get(0);
				name = vo.getCnname();
			}
			return name;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * 根据短信ID找人
	 * 
	 * @param id
	 * @return
	 */
	public String getUuidBySMSID(String id) {

		DAOFactory factory = new DAOFactory(conn);
		SMSIdDAO dao = new SMSIdDAO();
		dao.setSmsid(id);
		factory.setDAO(dao);
		String uuid = "";

		try {
			List list = factory.find(new SMSIdVO());
			if (!list.isEmpty()) {
				uuid = ((SMSIdVO) list.get(0)).getUuid();
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uuid;

	}

	/**
	 * 存接收到的短信
	 * 
	 * @param vo
	 * @throws DAOException
	 */

	public Integer saveReSMS(SMSReceiveVO vo) throws DAOException {
		SMSReceiveDAO dao = new SMSReceiveDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		dao.create();
		return dao.getId();
	}

	/**
	 * 根据uuid查短信接收
	 * 
	 * @param uuid
	 * @return
	 * @throws DAOException
	 */
	public List getRList(String uuid) throws DAOException {
		SMSReceiveDAO dao = new SMSReceiveDAO();
		dao.setReceiver(uuid);
		dao.addOrderBy("id", false);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new SMSReceiveVO());
		return list;
	}

	/**
	 * 删除短信接收记录
	 * 
	 * @param id
	 * @throws DAOException
	 */

	public void delSMSRecieve(Integer id) throws DAOException {
		SMSReceiveDAO dao = new SMSReceiveDAO();
		dao.setId(id);
		dao.setConnection(conn);
		dao.delete();
	}

	/**
	 * 取得SMSID
	 * 
	 * @param senderuuid
	 * @return
	 * @throws DBConnectionLocatorException
	 */
	public String getSMSIdbyUUID(String uuid) {
		System.out.println("come-----------");
		List list = null;
		String id = "";
		try {
			RoSIDDAO dao = new RoSIDDAO();
			dao.setUuid(uuid);
			DAOFactory fa = new DAOFactory(conn);
			fa.setDAO(dao);
			list = fa.find(new SMSIdVO());
			if (!list.isEmpty()) {
				//System.out.println("come1-----------");
				id = ((SMSIdVO) list.get(0)).getSmsid();
				if (id == null || "".equals(id) || id.length() < 1) {
					//System.out.println("come2-----------");
					RoSIDDAO dao1 = new RoSIDDAO();
					dao1.addOrderBy("smsid", false);
					dao1.setWhereClause(" smsid is not null ");
					DAOFactory fa1 = new DAOFactory(conn);
					fa1.setDAO(dao1);
					List list1 = fa1.find(new SMSIdVO());

					String id1 = ((SMSIdVO) list1.get(0)).getSmsid();
					System.out.println("MAXSMSID="+id1);
					if(new Integer(id1).intValue()+1>=new Integer(MAX_SMSID).intValue()){
						throw new RuntimeException("SMSID已经分配完毕，请与管理员联系！");
					}else{
					// System.out.println(id1);
					// System.out.println(((SMSIdVO) list1.get(0)).getUuid());
					DecimalFormat df = new DecimalFormat("000");
					String newid = df.format(new Integer(id1).intValue() + 1);
					//String newid = (new Integer(id1).intValue() + 1) + "";

					RoSIDDAO dao2 = new RoSIDDAO();
					dao2.setSmsid(newid);
					dao2.setWhereClause(" personuuid = '" + uuid + "'");
					dao2.setConnection(conn);
					dao2.update(false);
					id = dao2.getSmsid();
					System.out.println(id);
					}
				}
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * 存入代辦
	 * 
	 * @param vo
	 */

	public void toDb(SMSReceiveVO vo)
	{
		try
		{
		long defaultLongTime = System.currentTimeMillis();
		OfficePendingDAO officePendingDAO = new OfficePendingDAO(conn);
		officePendingDAO.setOpTopic("新信息来自"+getUserByPhone(vo.getFromNo())+"，("+vo.getTime()+")");
		officePendingDAO.setOpDate(new Long(defaultLongTime));
		officePendingDAO.setOpFlag("2");
		officePendingDAO.setOpNavigate("/oabase/servlet/ViewRecieveServlet");
		officePendingDAO.setOpSource("短信");
		officePendingDAO.setOpType("2");
		officePendingDAO.setPersonid(getUseridByuuid(vo.getReceiver()));
		officePendingDAO.setOpModify(new Long(0L));
		officePendingDAO.setOpUrl("/oabase/servlet/ViewRecieveServlet");
		officePendingDAO.setOpCode("sms_"+vo.getId());
		
			officePendingDAO.create();
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("增加待办工作错误!");
		}
	}

	public static String getUserByPhone(String phone) {
		Connection con = null;
		String username = phone;
		try {
			con = DBConnectionLocator.getInstance()
					.getConnection("jdbc/OABASE");

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql
					.append("SYS_PERSON.PERSONUUID,SYS_PERSON.CNNAME,SYS_PERSON.USERID,HRPERSON.MOBILEPHONE  ");
			sql.append("FROM ");
			sql.append("SYS_PERSON,HRPERSON ");
			sql.append(" WHERE ");
			sql
					.append("SYS_PERSON.HRID=HRPERSON.HRID AND HRPERSON.MOBILEPHONE= '"
							+ phone + "'");
			// System.out.println(sql.toString());
			HRSMSMobileSearchDAO dao = new HRSMSMobileSearchDAO();
			dao.setSearchSQL(sql.toString());
			DAOFactory factory = new DAOFactory(con);
			factory.setDAO(dao);
			List list = factory.find(new HRSMSMobileVO());
			if (!list.isEmpty()) {
				HRSMSMobileVO personVO = (HRSMSMobileVO) list.get(0);
				username = personVO.getUsername();
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return username;

	}
	
	public String getUseridByuuid(String uuid) throws DAOException{
		String userid="";
		SysPersonDAO dao = new SysPersonDAO();
		dao.setPersonuuid(uuid);
		DAOFactory fa = new DAOFactory(conn);
		fa.setDAO(dao);
		List list = fa.find(new SysPersonVO());
		if(!list.isEmpty()){
			SysPersonVO vo = (SysPersonVO) list.get(0);
			userid =vo.getUserid();
		}
		return userid;
	}

}