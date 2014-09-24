/*
 * Created on 2004-8-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.dao;

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MeetingRoominformationDAO extends DAO {

	private Integer meetingId;

	private String meetName;

	private String belongBuilding;

	private String belongRoom;

	private Integer meetingMaxnum;

	private String meetingUtil;

	private InputStream meetingSitting;

	private String sittingName;

	private Integer num;

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer _meetingId) {
		firePropertyChange("meetingId", meetingId, _meetingId);
		meetingId = _meetingId;
	}

	public String getMeetName() {
		return meetName;
	}

	public void setMeetName(String _meetName) {
		firePropertyChange("meetName", meetName, _meetName);
		meetName = _meetName;
	}

	public String getBelongBuilding() {
		return belongBuilding;
	}

	public void setBelongBuilding(String _belongBuilding) {
		firePropertyChange("belongBuilding", belongBuilding, _belongBuilding);
		belongBuilding = _belongBuilding;
	}

	public String getBelongRoom() {
		return belongRoom;
	}

	public void setBelongRoom(String _belongRoom) {
		firePropertyChange("belongRoom", belongRoom, _belongRoom);
		belongRoom = _belongRoom;
	}

	public Integer getMeetingMaxnum() {
		return meetingMaxnum;
	}

	public void setMeetingMaxnum(Integer _meetingMaxnum) {
		firePropertyChange("meetingMaxnum", meetingMaxnum, _meetingMaxnum);
		meetingMaxnum = _meetingMaxnum;
	}

	public String getMeetingUtil() {
		return meetingUtil;
	}

	public void setMeetingUtil(String _meetingUtil) {
		firePropertyChange("meetingUtil", meetingUtil, _meetingUtil);
		meetingUtil = _meetingUtil;
	}

	public InputStream getMeetingSitting() {
		return meetingSitting;
	}

	public void setMeetingSitting(InputStream _meetingSitting) {
		firePropertyChange("meetingSitting", meetingSitting, _meetingSitting);
		meetingSitting = _meetingSitting;
	}

	public String getSittingName() {
		return sittingName;
	}

	public void setSittingName(String _sittingName) {
		firePropertyChange("sittingName", sittingName, _sittingName);
		sittingName = _sittingName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer _num) {
		firePropertyChange("num", num, _num);
		num = _num;
	}

	protected void setupFields() throws DAOException {
		addField("meetingId", "MEETING_ID");
		addField("meetName", "MEET_NAME");
		addField("belongBuilding", "BELONG_BUILDING");
		addField("belongRoom", "BELONG_ROOM");
		addField("meetingMaxnum", "MEETING_MAXNUM");
		addField("meetingUtil", "MEETING_UTIL");
		addField("meetingSitting", "MEETING_SITTING");
		addField("sittingName", "SITTING_NAME");
		addField("num", "NUM");
		setTableName("MEETING_ROOMINFORMATION");
		addKey("MEETING_ID");
		setAutoIncremented("MEETING_ID");
	}

	public MeetingRoominformationDAO(Connection conn) {
		super(conn);
	}

	public MeetingRoominformationDAO() {
		super();
	}
}
