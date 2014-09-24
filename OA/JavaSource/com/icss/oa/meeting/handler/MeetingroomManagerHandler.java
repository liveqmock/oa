/*
 * Created on 2004-7-27
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.icss.oa.meeting.handler;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.folder.vo.FolderPackageVO;
import com.icss.oa.meeting.dao.MeetingPersonDAO;
import com.icss.oa.meeting.dao.MeetingPersonmeetDAO;
import com.icss.oa.meeting.dao.MeetingPutDAO;
import com.icss.oa.meeting.dao.MeetingRoominformationDAO;
import com.icss.oa.meeting.dao.SysOrgSysOrgpersonSearchDAO;
import com.icss.oa.meeting.dao.SysPersonDAO;
import com.icss.oa.meeting.vo.MeetingPersonVO;
import com.icss.oa.meeting.vo.MeetingPersonmeetVO;
import com.icss.oa.meeting.vo.MeetingPutVO;
import com.icss.oa.meeting.vo.MeetingRoominformationVO;
import com.icss.oa.meeting.vo.SysOrgSysOrgpersonVO;
import com.icss.oa.meeting.vo.SysPersonVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MeetingroomManagerHandler {

	private Connection conn;
	public String filepath = null;

	public MeetingroomManagerHandler(Connection connection) {
		this.conn = connection;
	}

	public void CreatDir(String filepath) {

		// this.filepath = toFilePathManager.getString("toFile_path");
		//this.filepath = filepath;
		System.out.println("toFile_path is : " + filepath);
		File file = new File(filepath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	public Integer getPersonId(Integer id) throws HandlerException {
		try {

			DAOFactory factory = new DAOFactory(conn);
			MeetingPersonDAO dao = new MeetingPersonDAO();
			factory.setDAO(dao);
			dao.setId(id);
			dao.setConnection(conn);
			MeetingPersonVO vo = (MeetingPersonVO) factory.findByPrimaryKey(new MeetingPersonVO());
			Integer person_id = vo.getOrder1();
			return person_id;

		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	public void updatePersonId(Integer id, Integer personid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonDAO dao = new MeetingPersonDAO();
		factory.setDAO(dao);
		dao.setId(id);
		dao.setConnection(conn);

		try {
			dao = (MeetingPersonDAO) factory.findByPrimaryKey();
			dao.setOrder1(personid);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void addMeetingroom(MeetingRoominformationVO vo) throws HandlerException {

		MeetingRoominformationDAO dao = new MeetingRoominformationDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public void alterMeetingroom(MeetingRoominformationVO vo) throws HandlerException {

		try {
			MeetingRoominformationDAO dao = new MeetingRoominformationDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			dao.update(true);

		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}
	public MeetingRoominformationVO getMeetingroomVO(Integer MeetingID) throws HandlerException {

		try {

			DAOFactory factory = new DAOFactory(conn);
			MeetingRoominformationDAO dao = new MeetingRoominformationDAO();
			factory.setDAO(dao);
			dao.setMeetingId(MeetingID);
			dao.setConnection(conn);
			MeetingRoominformationVO vo = (MeetingRoominformationVO) factory.findByPrimaryKey(new MeetingRoominformationVO());
			return vo;
		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	public void delMeetingroom(Integer id) throws HandlerException {
		try {

			MeetingRoominformationDAO dao = new MeetingRoominformationDAO();
			dao.setConnection(conn);
			dao.setMeetingId(id);
			dao.delete();

		} catch (Exception e) {
			throw new HandlerException();
		}
	}

	public List getMeetingroomList() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingRoominformationDAO dao = new MeetingRoominformationDAO();
		factory.setDAO(dao);
		dao.addOrderBy("num", true);
		try {
			List list = factory.find(new MeetingRoominformationVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public InputStream getMeetingroomAccessory(Integer meetingID) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingRoominformationDAO dao = new MeetingRoominformationDAO();
		dao.setMeetingId(meetingID);
		factory.setDAO(dao);
		InputStream in = null;
		MeetingRoominformationVO vo = null;
		try {
			vo = (MeetingRoominformationVO) factory.findByPrimaryKey(new FolderPackageVO());
			in = vo.getMeetingSitting();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	public void addMeetingPut(MeetingPutVO vo) throws HandlerException {

		MeetingPutDAO dao = new MeetingPutDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getMeetingPutList() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingPutDAO dao = new MeetingPutDAO();
		factory.setDAO(dao);
		dao.addOrderBy("putTime", false);
		try {
			List list = factory.find(new MeetingPutVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getMeetingPutList1(String str) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingPutDAO dao = new MeetingPutDAO();
		factory.setDAO(dao);
		dao.addOrderBy("putTime", false);
		dao.setWhereClause(str);
		try {
			List list = factory.find(new MeetingPutVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getMeetingPutList(String uuid) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingPutDAO dao = new MeetingPutDAO();
		factory.setDAO(dao);
		dao.addOrderBy("putTime", false);
		dao.setMeetingPutperson(uuid);
		try {
			List list = factory.find(new MeetingPutVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getMeetingPutListByMeeting(String meetingroom) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPutDAO dao = new MeetingPutDAO();
		factory.setDAO(dao);
		dao.addOrderBy("starttimeday", false);
		dao.setWhereClause(
			"MEETING_SATUS IN ('已确认','发布中','会议开始') AND MEETING_ROOM ='" + meetingroom + "' AND STARTTIMEDAY >='" + this.getLongByTime(this.getTimeByLong(new Long(System.currentTimeMillis()))) + "'");

		try {
			List list = factory.find(new MeetingPutVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getMeetingPutListByMeeting(String meetingroom, Long starttime) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPutDAO dao = new MeetingPutDAO();
		factory.setDAO(dao);
		dao.addOrderBy("starttimeday", false);
		dao.setWhereClause(
			"MEETING_SATUS IN ('已确认','发布中','会议开始') AND MEETING_ROOM ='"
				+ meetingroom
				+ "' AND STARTTIMEDAY >= "
				+ starttime.longValue()
				+ " AND STARTTIMEDAY <= "
				+ (starttime.longValue() + 24 * 60 * 60 * 1000 - 1));

		try {
			List list = factory.find(new MeetingPutVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public MeetingPutVO getMeetingPutVO(Integer putID) throws HandlerException {

		try {

			DAOFactory factory = new DAOFactory(conn);
			MeetingPutDAO dao = new MeetingPutDAO();
			factory.setDAO(dao);
			dao.setPutId(putID);
			dao.setConnection(conn);
			MeetingPutVO vo = (MeetingPutVO) factory.findByPrimaryKey(new MeetingPutVO());
			return vo;
		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	public void delMeetingPut(Integer id) throws HandlerException {
		try {

			MeetingPutDAO dao = new MeetingPutDAO();
			dao.setConnection(conn);
			dao.setPutId(id);
			dao.delete();

		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	public InputStream getStream(Integer meetingId) {

		InputStream in = null;
		DAOFactory factory = new DAOFactory(conn);
		MeetingRoominformationDAO dao = new MeetingRoominformationDAO();
		factory.setDAO(dao);
		dao.setMeetingId(meetingId);
		MeetingRoominformationVO vo = null;
		try {
			vo = (MeetingRoominformationVO) factory.findByPrimaryKey(new MeetingRoominformationVO());
			in = vo.getMeetingSitting();
			return in;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public InputStream getStream1(Integer meetingId) {

		InputStream in = null;
		DAOFactory factory = new DAOFactory(conn);
		MeetingPutDAO dao = new MeetingPutDAO();
		factory.setDAO(dao);
		dao.setPutId(meetingId);
		MeetingPutVO vo = null;
		try {
			vo = (MeetingPutVO) factory.findByPrimaryKey(new MeetingPutVO());
			in = vo.getMeetingSitting();
			return in;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getLongByTime(String time) {
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
	//	将微秒（Long）的时间转换为字符串型形式
	public String getTimeByLong(Long time) {
		try {
			Date date = new Date(time.longValue());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String formatTime = formatter.format(date);
			return formatTime;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void alterMeetingPut(MeetingPutVO vo) throws HandlerException {

		try {
			MeetingPutDAO dao = new MeetingPutDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			dao.update(true);

		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	public List getMeetingPersonList(Integer putId) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonDAO dao = new MeetingPersonDAO();
		factory.setDAO(dao);
		dao.setMeetingId(putId);
		dao.addOrderBy("order1", true);

		try {
			List list = factory.find(new MeetingPersonVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	public void addMeetingPerson(MeetingPersonVO vo) throws HandlerException {

		MeetingPersonDAO dao = new MeetingPersonDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	public void delMeetingPerson(Integer id) throws HandlerException {
		try {

			MeetingPersonDAO dao = new MeetingPersonDAO();
			dao.setConnection(conn);
			dao.setId(id);
			dao.delete();

		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	public void attendMeetingPerson(Integer id) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonDAO dao = new MeetingPersonDAO();
		factory.setDAO(dao);
		dao.setId(id);
		dao.setConnection(conn);

		try {
			dao = (MeetingPersonDAO) factory.findByPrimaryKey();
			dao.setDaohui("已到会");
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void UnattendMeetingPerson(Integer id) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonDAO dao = new MeetingPersonDAO();
		factory.setDAO(dao);
		dao.setId(id);
		dao.setConnection(conn);

		try {
			dao = (MeetingPersonDAO) factory.findByPrimaryKey();
			dao.setDaohui("未到会");
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public int MeetingPersonNum(Integer id) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonDAO dao = new MeetingPersonDAO();
		factory.setDAO(dao);
		dao.setMeetingId(id);
		dao.setConnection(conn);
		List list = null;
		try {
			list = factory.find(new MeetingPersonVO());

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return list.size();
	}

	public void PutMeetingStatus(Integer id, String flag) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPutDAO dao = new MeetingPutDAO();
		factory.setDAO(dao);
		dao.setPutId(id);
		dao.setConnection(conn);

		try {
			dao = (MeetingPutDAO) factory.findByPrimaryKey();
			if ("会议信息发表".equals(flag)) {
				dao.setMeetingSatus("发布中");
			}
			if ("会议开始".equals(flag)) {
				dao.setMeetingSatus("会议开始");
			}
			if ("会议结束".equals(flag)) {
				dao.setMeetingSatus("会议结束");
			}

			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void BaomingMeetingPerson(Integer id, String UUID) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonDAO dao = new MeetingPersonDAO();
		factory.setDAO(dao);
		dao.setMeetingId(id);
		dao.setPerson(UUID);
		dao.setConnection(conn);
		try {
			List list = factory.find(new MeetingPersonVO());
			Iterator it = list.iterator();
			while (it.hasNext()) {
				MeetingPersonVO vo = (MeetingPersonVO) it.next();

				dao.setId(vo.getId());
				dao = (MeetingPersonDAO) factory.findByPrimaryKey();
				dao.setBaoming("已报名");
				dao.update(true);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * get user id
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getUserId() {
		UserInfo user = null;
		try {
			Context ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getPersonUuid();
		else
			return null;
	}

	public String getPeopleDepartment(String userId) {

		SysOrgSysOrgpersonSearchDAO dao = new SysOrgSysOrgpersonSearchDAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		String dep = "";
		SysOrgSysOrgpersonVO vo = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("SYS_ORG.ORGUUID,SYS_ORG.CNNAME,SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ISBELONG ");
		sql.append("FROM ");
		sql.append("SYS_ORG,SYS_ORGPERSON ");
		sql.append(" WHERE ");
		sql.append("SYS_ORG.ORGUUID=SYS_ORGPERSON.ORGUUID AND SYS_ORGPERSON.ISBELONG=1 ");
		sql.append("AND SYS_ORGPERSON.PERSONUUID='" + userId + "' ");

		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new SysOrgSysOrgpersonVO());
			Iterator it = list.iterator();
			while (it.hasNext()) {
				vo = (SysOrgSysOrgpersonVO) it.next();
			}
			dep = vo.getCnname();
			return dep;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getPersonCnname(String userId) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		SysPersonDAO dao = new SysPersonDAO();
		factory.setDAO(dao);
		dao.setPersonuuid(userId);
		String cnname = "";
		SysPersonVO vo = null;
		try {
			List list = factory.find(new SysPersonVO());
			Iterator it = list.iterator();
			while (it.hasNext()) {
				vo = (SysPersonVO) it.next();
			}
			cnname = vo.getCnname();
			return cnname;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public Integer addPersonMeeting(MeetingPersonmeetVO vo) throws HandlerException {

		MeetingPersonmeetDAO dao = new MeetingPersonmeetDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
			return dao.getId();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public Integer UpdatePersonMeeting(MeetingPersonmeetVO vo) throws HandlerException {

		MeetingPersonmeetDAO dao = new MeetingPersonmeetDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.update(true);
			return dao.getId();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public boolean isExist(Integer putid, String personUUID) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonDAO dao = new MeetingPersonDAO();
		factory.setDAO(dao);
		dao.setPerson(personUUID);
		dao.setMeetingId(putid);
		dao.setConnection(conn);
		List list = null;

		try {
			list = factory.find(new MeetingPersonVO());
			if (list.size() >= 1)
				return true;
			else
				return false;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List getPersonMeetingList(String uuid) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonmeetDAO dao = new MeetingPersonmeetDAO();
		factory.setDAO(dao);
		dao.setPerson(uuid);
		try {
			List list = factory.find(new MeetingPersonmeetVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getPersonMeetingList(Integer putid, String uuid) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonmeetDAO dao = new MeetingPersonmeetDAO();
		factory.setDAO(dao);
		dao.setMeetingPutid(putid);
		dao.setPerson(uuid);
		dao.setConnection(conn);
		try {
			List list = null;
			list = factory.find(new MeetingPersonmeetVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getPersonMeetingList1(String str) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		MeetingPersonmeetDAO dao = new MeetingPersonmeetDAO();
		factory.setDAO(dao);
		dao.setWhereClause(str);
		try {
			List list = factory.find(new MeetingPersonmeetVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	public void delPersonMeeting(Integer id) throws HandlerException {
		try {

			MeetingPersonmeetDAO dao = new MeetingPersonmeetDAO();
			dao.setConnection(conn);
			dao.setId(id);
			dao.delete();

		} catch (Exception e) {
			throw new HandlerException();
		}
	}

	public Integer GetMaxMeetingRoomNum() {
		String sql = " SELECT MAX( MEETING_ROOMINFORMATION.NUM) AS MEETING_MAX FROM  MEETING_ROOMINFORMATION ";

		Statement stmt = null;
		ResultSet rs = null;
		int meetingmaxNum = 0;

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs != null) {
				if (rs.next()) {
					meetingmaxNum = rs.getInt("MEETING_MAX");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

		return new Integer(meetingmaxNum);

	}

}
