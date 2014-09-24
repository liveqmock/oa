package com.icss.oa.message.handler;

import org.apache.regexp.RE;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.message.api.MSGSender;
import com.icss.oa.message.api.MsgContent;
import com.icss.oa.message.dao.*;
import com.icss.oa.message.util.MsgConfig;
import com.icss.oa.message.util.MsgMark;
import com.icss.oa.message.vo.*;
import com.icss.oa.util.CurrentUser;
import com.icss.oa.util.RoleControl;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.sdk.right.RightException;


public class MsgHandler {
	
	
	public static final int SEND_NOPHONE = 1;
	public static final int SEND_NORETURN = 2;
	public static final int SEND_RECEWIVE = 3;
	public static final int SEND_RETURN = 4;
	
	private static final String replace_n = "--replace--n";
	
	private static final String replace_r = "--replace--r";
	
	private Connection conn;
	
	public MsgHandler(Connection conn) {
		this.conn = conn;
	}
	
	
	public List getMsgManagerList(String orguuid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"MSG_MANAGER.MM_ID,MSG_MANAGER.MM_ORGUUID,MSG_MANAGER.MM_PERSONUUID,SYS_PERSON.CNNAME,SYS_PERSON.JOB ");
		sql.append("FROM ");
		sql.append("MSG_MANAGER,SYS_PERSON ");
		sql.append("WHERE MSG_MANAGER.MM_PERSONUUID=SYS_PERSON.PERSONUUID ");
		sql.append("AND MSG_MANAGER.MM_ORGUUID='" + orguuid + "' ");
		MsgManagerSysPersonSearchDAO dao = new MsgManagerSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		try {
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			return factory.find(new MsgManagerSysPersonVO());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String addMsgManager(String orguuid, String personuuid) {
		addMsgCode(orguuid);
		String personName = this.getPersonName(personuuid);
		if (hasMsgManager(orguuid, personuuid)) {
			return "添加短信发送人：[" + personName + "]失败：此人员已经是此组织的短信发送人。";
		}
		MsgManagerDAO dao = new MsgManagerDAO();
		dao.setConnection(conn);
		dao.setMmOrguuid(orguuid);
		dao.setMmPersonuuid(personuuid);
		try {
			dao.create();
			RoleControl.dispatchRole(personuuid, RoleControl.ROLE_oa_message);
			return "添加短信发送人：[" + personName + "]成功。";
		} catch (RightException e) {
			try {
				DAOFactory factory = new DAOFactory(conn);
				factory.setDAO(dao);
				factory.batchDelete();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			return "添加管理员：[" + personName + "]失败:" + e.toString();
		} catch (DAOException e) {
			e.printStackTrace();
			return "添加管理员：[" + personName + "]失败:" + e.toString();
		}
	}
	
	
	public boolean hasMsgManager(String orgid, String Personuuid) {
		MsgManagerDAO dao = new MsgManagerDAO();
		dao.setConnection(conn);
		dao.setMmOrguuid(orgid);
		dao.setMmPersonuuid(Personuuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find();
			if (list == null || list.size() == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public String getPersonName(String personuuid) {
		try {
			EntityManager em = EntityManager.getInstance();
			Person person = em.findPersonByUuid(personuuid);
			return person.getFullName();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void addMsgCode(String orguuid) {
		MsgCodeDAO dao = new MsgCodeDAO(conn);
		dao.setMcOrguuid(orguuid);
		try {
			dao.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List getManageOrgList(String personuuid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"MSG_MANAGER.MM_ID,MSG_MANAGER.MM_ORGUUID,MSG_MANAGER.MM_PERSONUUID,SYS_ORG.CNNAME,SYS_ORG.DELTAG ");
		sql.append("FROM ");
		sql.append("MSG_MANAGER,SYS_ORG ");
		sql.append(
			"WHERE MSG_MANAGER.MM_ORGUUID=SYS_ORG.ORGUUID AND SYS_ORG.DELTAG='0' ");
		sql.append("AND MSG_MANAGER.MM_PERSONUUID='" + personuuid + "' ");
		MsgManagerSysOrgSearchDAO dao = new MsgManagerSysOrgSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			return factory.find(new MsgManagerSysOrgVO());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public void deleteMsgManager(String orgid, String personuuid) {
		MsgManagerDAO dao = new MsgManagerDAO();
		dao.setConnection(conn);
		dao.setWhereClause(
			"MM_ORGUUID='"
				+ orgid
				+ "' AND MM_PERSONUUID='"
				+ personuuid
				+ "' ");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dao = (MsgManagerDAO) it.next();
				factory.setDAO(dao);
				factory.batchDelete();
				List mlist = this.getManageOrgList(personuuid);
				if (mlist == null || mlist.size() == 0) {
					RoleControl.deleteRolePerson(
						personuuid,
						RoleControl.ROLE_oa_message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public PhoneInfoSysPersonVO getPhone(String personuuid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"PHONE_INFO.MOBILEPHONE,SYS_PERSON.PERSONUUID,SYS_PERSON.CNNAME ");
		sql.append("FROM ");
		sql.append(
			"SYS_PERSON LEFT JOIN PHONE_INFO ON SYS_PERSON.PERSONUUID=PHONE_INFO.USERUUID WHERE SYS_PERSON.PERSONUUID='"
				+ personuuid
				+ "' ");
		PhoneInfoSysPersonSearchDAO dao = new PhoneInfoSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find(new PhoneInfoSysPersonVO());
			if (list == null || list.size() < 1)
				return null;
			else {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					PhoneInfoSysPersonVO vo = (PhoneInfoSysPersonVO) it.next();
					if (vo.getMobilephone() != null
						&& !"".equals(vo.getMobilephone())) {
						return vo;
					}
				}
				return (PhoneInfoSysPersonVO) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String sendMsg(List list, String content, String orgid) {
		String resultStr = "";
		List phoneInfoList = new ArrayList();
		List phoneList = new ArrayList();
		if (list != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				SelectOrgpersonVO vo = (SelectOrgpersonVO) it.next();
				PhoneInfoSysPersonVO pvo = getPhone(vo.getUseruuid());
				if (pvo != null) {
					phoneInfoList.add(pvo);
					String phone = pvo.getMobilephone();
					if (phone != null && !"".equals(phone)) {
						phoneList.add(phone);
					}
				} else {
					pvo = new PhoneInfoSysPersonVO();
					pvo.setPersonuuid(vo.getUseruuid());
					pvo.setCnname(vo.getName());
					phoneInfoList.add(pvo);
				}
			}
		}
		if (list == null
			|| phoneInfoList == null
			|| phoneList == null
			|| list.size() == 0
			|| phoneInfoList.size() == 0
			|| phoneList.size() == 0) {
			resultStr = "短信发送失败：无法找到可发送人员.";
		} else {
			int msgnum = MsgMark.getNum();
			Long sendTime =
				MSGSender.send(content, phoneList, MsgMark.numToString(msgnum));
			if (sendTime == null || sendTime.longValue() <= 0) {
				resultStr = "短信发送失败.";
			} else {
				MsgSendVO vo = new MsgSendVO();
				vo.setMsContent(content);
				vo.setMsDate(sendTime);
				vo.setMsId(new Integer(msgnum));
				vo.setMsOrguuid(orgid);
				vo.setMsPersonuuid(new CurrentUser().getId());
				recordMsg(vo, phoneInfoList);
				resultStr = "";
			}
		}
		return resultStr;
	}
	
	
	public void recordMsg(MsgSendVO msg, List phoneInfoList) {
		MsgSendDAO dao = new MsgSendDAO(conn);
		dao.setMsContent(msg.getMsContent());
		dao.setMsDate(msg.getMsDate());
		dao.setMsId(msg.getMsId());
		dao.setMsOrguuid(msg.getMsOrguuid());
		dao.setMsPersonuuid(msg.getMsPersonuuid());
		try {
			dao.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (phoneInfoList != null) {
			Iterator it = phoneInfoList.iterator();
			while (it.hasNext()) {
				PhoneInfoSysPersonVO vo = (PhoneInfoSysPersonVO) it.next();
				MsgBackDAO bakdao = new MsgBackDAO(conn);
				bakdao.setMsMid(dao.getMsMid());
				bakdao.setMsDate(dao.getMsDate());
				bakdao.setMsPersonuuid(vo.getPersonuuid());
				bakdao.setMsPower("1");
				String phone = vo.getMobilephone();
				if (phone != null && !"".equals(phone)) {
					bakdao.setMsPhone(phone);
					bakdao.setMsMode(new Integer(MsgHandler.SEND_NORETURN));
					bakdao.setMsContent("短信已发出");
				} else {
					bakdao.setMsPhone(phone);
					bakdao.setMsMode(new Integer(MsgHandler.SEND_NOPHONE));
					bakdao.setMsContent("无法从地址簿查到手机号码");
				}
				try {
					bakdao.create();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void receiveMsg(MsgContent msg) {
		
		Integer mid = getMsgId(msg.getCode());
		
		if (mid != null) {
			
			String personuuid = getPersonByPhone(msg.getPhone());
			
			MsgBackDAO dao = new MsgBackDAO(conn);
			dao.setMsContent(msg.getContent());
			dao.setMsDate(msg.getDate());
			dao.setMsMode(new Integer(msg.getMode()));
			dao.setMsPhone(msg.getPhone());
			dao.setMsMid(mid);
			dao.setMsPersonuuid(personuuid);
			
			if (personuuid == null || "".equals(personuuid)) {
			
				dao.setMsPower("0");
			
			} else {
			
				if (hasPower(personuuid, mid)) {
					dao.setMsPower("1");
				} else {
					dao.setMsPower("0");
				}
			
			}
			
			try {
				conn.setAutoCommit(false);
				deleteReMsg(personuuid, mid);
				dao.create();
				conn.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			} finally {
				try {
					conn.setAutoCommit(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	private void deleteReMsg(String personuuid, Integer mid)
		throws DAOException {
		if (personuuid == null)
			return;
		MsgBackDAO dao = new MsgBackDAO(conn);
		dao.setWhereClause(
			"MS_MID="
				+ mid
				+ " AND MS_PERSONUUID='"
				+ personuuid
				+ "' AND (MS_MODE="
				+ MsgHandler.SEND_NOPHONE
				+ " OR MS_MODE="
				+ MsgHandler.SEND_NORETURN
				+ "  OR MS_MODE="
				+ MsgHandler.SEND_RECEWIVE
				+ ") ");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		factory.batchDelete();
	}
	private boolean hasPower(String personuuid, Integer mid) {
		MsgBackDAO dao = new MsgBackDAO(conn);
		dao.setWhereClause(
			"MS_MID="
				+ mid
				+ " AND MS_PERSONUUID='"
				+ personuuid
				+ "' AND MS_POWER='1' ");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find();
			if (list != null && list.size() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	private Integer getMsgId(Integer code) {
		Statement state = null;
		ResultSet res = null;
		try {
			state = conn.createStatement();

			StringBuffer sql = new StringBuffer();

			sql.append(
				"select max(MS_DATE) as MAXDATE from MSG_SEND WHERE MS_ID="
					+ code
					+ " ");

			res = state.executeQuery(sql.toString());

			if (res.next()) {
				long maxdate = res.getLong("MAXDATE");
				res.close();
				sql = new StringBuffer();
				sql.append(
					"select MS_MID from MSG_SEND where MS_ID="
						+ code
						+ " AND MS_DATE="
						+ maxdate);
				res = state.executeQuery(sql.toString());
				if (res.next()) {
					return new Integer(res.getInt("MS_MID"));
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (state != null) {
				try {
					state.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String getPersonByPhone(String phone) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"PHONE_INFO.MOBILEPHONE,SYS_PERSON.PERSONUUID,SYS_PERSON.CNNAME ");
		sql.append("FROM ");
		sql.append(
			"SYS_PERSON,PHONE_INFO WHERE SYS_PERSON.PERSONUUID=PHONE_INFO.USERUUID AND PHONE_INFO.MOBILEPHONE='"
				+ phone
				+ "' ");
		PhoneInfoSysPersonSearchDAO dao = new PhoneInfoSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find(new PhoneInfoSysPersonVO());
			if (list == null || list.size() < 1)
				return null;
			else {
				Iterator it = list.iterator();
				if (it.hasNext()) {
					PhoneInfoSysPersonVO vo = (PhoneInfoSysPersonVO) it.next();
					return vo.getPersonuuid();
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static Long getLongByTime(String time) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition pos = new ParsePosition(0);
			java.util.Date date = formatter.parse(time, pos);
			if (date == null)
				return null;
			return new Long(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List searchShortMsg(
		String content,
		Long startTime,
		Long endTime,
		boolean sOld) {
		MsgSendDAO dao = new MsgSendDAO(conn);
		StringBuffer wCl = new StringBuffer();
		wCl.append("MS_PERSONUUID='" + new CurrentUser().getId() + "' ");
		if (content != null && !"".equals(content)) {
			wCl.append("AND MS_CONTENT LIKE '%" + content + "%' ");
		}
		if (startTime != null) {
			wCl.append("AND MS_DATE>=" + startTime + " ");
		}
		if (endTime != null) {
			wCl.append("AND MS_DATE<" + endTime + " ");
		}
		if (sOld) {
			dao.setTableName("MSG_SEND_OLD");
		}
		dao.addOrderBy("msDate", false);
		dao.setWhereClause(wCl.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			return factory.find(new MsgSendVO());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List searchShortMsgEx(
		String content,
		String personname,
		String orgname,
		Long startTime,
		Long endTime,
		boolean sOld) {
		String tableName = "MSG_SEND";
		if (sOld) {
			tableName = "MSG_SEND_OLD";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"SYS_PERSON.CNNAME AS PCNNAME,SYS_ORG.CNNAME AS OCNNAME,"
				+ tableName
				+ ".MS_MID AS MSMID,"
				+ tableName
				+ ".MS_ID AS MSID,"
				+ tableName
				+ ".MS_PERSONUUID AS MSPERSONUUID,"
				+ tableName
				+ ".MS_ORGUUID AS MSORGUUID,"
				+ tableName
				+ ".MS_DATE AS MSDATE,"
				+ tableName
				+ ".MS_CONTENT AS MSCONTENT ");
		sql.append("FROM ");
		sql.append(
			tableName
				+ " LEFT JOIN SYS_ORG ON "
				+ tableName
				+ ".MS_ORGUUID=SYS_ORG.ORGUUID LEFT JOIN SYS_PERSON ON "
				+ tableName
				+ ".MS_PERSONUUID=SYS_PERSON.PERSONUUID ");
		sql.append("ORDER BY " + tableName + ".MS_DATE DESC ");

		boolean hasS = false;
		if (content != null && !"".equals(content)) {
			if (!hasS) {
				sql.append("WHERE ");
			} else {
				sql.append("AND ");
			}
			hasS = true;
			sql.append(tableName + ".MS_CONTENT LIKE '%" + content + "%' ");
		}
		if (personname != null && !"".equals(personname)) {
			if (!hasS) {
				sql.append("WHERE ");
			} else {
				sql.append("AND ");
			}
			hasS = true;
			sql.append("SYS_PERSON.CNNAME LIKE '%" + personname + "%' ");
		}
		if (orgname != null && !"".equals(orgname)) {
			if (!hasS) {
				sql.append("WHERE ");
			} else {
				sql.append("AND ");
			}
			hasS = true;
			sql.append("SYS_ORG.CNNAME LIKE '%" + orgname + "%' ");
		}
		if (startTime != null) {
			if (!hasS) {
				sql.append("WHERE ");
			} else {
				sql.append("AND ");
			}
			hasS = true;
			sql.append(tableName + ".MS_DATE>=" + startTime + " ");
		}
		if (endTime != null) {
			if (!hasS) {
				sql.append("WHERE ");
			} else {
				sql.append("AND ");
			}
			hasS = true;
			sql.append(tableName + ".MS_DATE<" + endTime + " ");
		}
		MsgSendSearchDAO dao = new MsgSendSearchDAO();
		dao.SetSearvhSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			return factory.find(new MsgSendSearchVO());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List getBackMsgList(Integer msgMid, boolean sOld) {
		if (msgMid == null)
			return null;
		String tableName = "MSG_BACK";
		if (sOld) {
			tableName = "MSG_BACK_OLD";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			tableName
				+ ".MB_ID AS MBID,"
				+ tableName
				+ ".MS_MID AS MSMID,"
				+ tableName
				+ ".MS_PERSONUUID AS MSPERSONUUID,"
				+ tableName
				+ ".MS_PHONE AS MSPHONE,"
				+ tableName
				+ ".MS_CONTENT AS MSCONTENT,"
				+ tableName
				+ ".MS_MODE AS MSMODE,"
				+ tableName
				+ ".MS_DATE AS MSDATE,"
				+ tableName
				+ ".MS_POWER AS MSPOWER,SYS_PERSON.CNNAME ");
		sql.append("FROM ");
		sql.append(
			tableName
				+ " LEFT JOIN SYS_PERSON ON "
				+ tableName
				+ ".MS_PERSONUUID=SYS_PERSON.PERSONUUID ");
		sql.append("WHERE " + tableName + ".MS_MID=" + msgMid);
		sql.append("ORDER BY " + tableName + ".MS_DATE DESC ");

		MsgBackSearchDAO dao = new MsgBackSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			return factory.find(new MsgBackSearchVO());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String getBackMsgModeString(int mode) {
		switch (mode) {
			case MsgHandler.SEND_NOPHONE :
				return "发送失败";
			case MsgHandler.SEND_NORETURN :
				return "发送成功";
			case MsgHandler.SEND_RECEWIVE :
				return "已收到";
			case MsgHandler.SEND_RETURN :
				return "回复";
			default :
				return "不明状态";
		}
	}
	
	
	public static String getBackMsgModeString(Integer mode) {
		if (mode == null)
			return "不明状态";
		else
			return getBackMsgModeString(mode.intValue());
	}
	public static String getContent(String str) {
		int l = str.length();
		if (!"~".equals(str.substring(l - 1, l))) {
			return str;
		} else {
			String tmp = str.substring(0, l - 1);
			int i = tmp.lastIndexOf("~");
			if (i < 0) {
				return str;
			} else {
				return str.substring(0, i);
			}
		}
	}
	
	
	public static String getContentOrg(String str) {
		int l = str.length();
		if (!"~".equals(str.substring(l - 1, l))) {
			return "";
		} else {
			String tmp = str.substring(0, l - 1);
			int i = tmp.lastIndexOf("~");
			if (i < 0) {
				return "";
			} else {
				return str.substring(i + 1, l - 1);
			}
		}
	}
	
	
	public static String cutContent(String content) {
		if (content == null)
			return "";
		else if (content.length() <= MsgConfig.MSG_CONTENTLENGTH) {
			return content;
		} else {
			return content.substring(0, 15) + "……";
		}
	}
	
	
	public static String replaceContent(String content, boolean bBlank) {
		String tn = MsgHandler.replace_n;
		String tr = MsgHandler.replace_r;
		if (bBlank) {
			tn = "";
			tr = "";
		}
		RE rn = new RE("\n");
		content = rn.subst(content, tn);
		RE rr = new RE("\r");
		content = rr.subst(content, tr);
		return content;
	}
	
	
	public static String replaceBackContent(String content) {
		String tn = MsgHandler.replace_n;
		String tr = MsgHandler.replace_r;
		RE rn = new RE(tn);
		content = rn.subst(content, "\n");
		RE rr = new RE(tr);
		content = rr.subst(content, "\r");
		return content;
	}
	
	

}

