/*
 * Created on 2004-8-16
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MeetingPersonmeetDAO extends DAO {

	private Integer id;

	private String meetingName;

	private Long starttime;

	private Long endtime;

	private String start1;

	private String end1;

	private String meetingType;

	private String meetingConcept;

	private String person;

	private String meetingRoom;

	private String meetingUtil;

	private String meetingPutperon;

	private String meetingPutdep;

	private Integer meetingPutid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String _meetingName) {
		firePropertyChange("meetingName", meetingName, _meetingName);
		meetingName = _meetingName;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long _starttime) {
		firePropertyChange("starttime", starttime, _starttime);
		starttime = _starttime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long _endtime) {
		firePropertyChange("endtime", endtime, _endtime);
		endtime = _endtime;
	}

	public String getStart1() {
		return start1;
	}

	public void setStart1(String _start1) {
		firePropertyChange("start1", start1, _start1);
		start1 = _start1;
	}

	public String getEnd1() {
		return end1;
	}

	public void setEnd1(String _end1) {
		firePropertyChange("end1", end1, _end1);
		end1 = _end1;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String _meetingType) {
		firePropertyChange("meetingType", meetingType, _meetingType);
		meetingType = _meetingType;
	}

	public String getMeetingConcept() {
		return meetingConcept;
	}

	public void setMeetingConcept(String _meetingConcept) {
		firePropertyChange("meetingConcept", meetingConcept, _meetingConcept);
		meetingConcept = _meetingConcept;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String _person) {
		firePropertyChange("person", person, _person);
		person = _person;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(String _meetingRoom) {
		firePropertyChange("meetingRoom", meetingRoom, _meetingRoom);
		meetingRoom = _meetingRoom;
	}

	public String getMeetingUtil() {
		return meetingUtil;
	}

	public void setMeetingUtil(String _meetingUtil) {
		firePropertyChange("meetingUtil", meetingUtil, _meetingUtil);
		meetingUtil = _meetingUtil;
	}

	public String getMeetingPutperon() {
		return meetingPutperon;
	}

	public void setMeetingPutperon(String _meetingPutperon) {
		firePropertyChange("meetingPutperon", meetingPutperon, _meetingPutperon);
		meetingPutperon = _meetingPutperon;
	}

	public String getMeetingPutdep() {
		return meetingPutdep;
	}

	public void setMeetingPutdep(String _meetingPutdep) {
		firePropertyChange("meetingPutdep", meetingPutdep, _meetingPutdep);
		meetingPutdep = _meetingPutdep;
	}

	public Integer getMeetingPutid() {
		return meetingPutid;
	}

	public void setMeetingPutid(Integer _meetingPutid) {
		firePropertyChange("meetingPutid", meetingPutid, _meetingPutid);
		meetingPutid = _meetingPutid;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("meetingName", "MEETING_NAME");
		addField("starttime", "STARTTIME");
		addField("endtime", "ENDTIME");
		addField("start1", "START1");
		addField("end1", "END1");
		addField("meetingType", "MEETING_TYPE");
		addField("meetingConcept", "MEETING_CONCEPT");
		addField("person", "PERSON");
		addField("meetingRoom", "MEETING_ROOM");
		addField("meetingUtil", "MEETING_UTIL");
		addField("meetingPutperon", "MEETING_PUTPERON");
		addField("meetingPutdep", "MEETING_PUTDEP");
		addField("meetingPutid", "MEETING_PUTID");
		setTableName("MEETING_PERSONMEET");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public MeetingPersonmeetDAO(Connection conn) {
		super(conn);
	}

	public MeetingPersonmeetDAO() {
		super();
	}
}
