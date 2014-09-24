/*
 * Created on 2004-8-4
 *
 * 
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
 * 
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MeetingPutDAO extends DAO {

	private Integer putId;

	private String meetingName;

	private Integer meetingPeoplenum;

	private Long starttimeday;

	private Long endtimeday;

	private String meetingType;

	private String meetingRoom;

	private String meetingPutperson;

	private String meetingPutdep;

	private Long putTime;

	private String meetingNeedingutil;

	private String meetingConcept;

	private InputStream meetingSitting;

	private String meetingSatus;

	private String putMeno;

	private Integer meetingEnd;

	private String contactingPerson;

	private Integer meetingPizhu;

	private String startimehour;

	private String endtimehour;

	private String sittingName;

	public Integer getPutId() {
		return putId;
	}

	public void setPutId(Integer _putId) {
		firePropertyChange("putId", putId, _putId);
		putId = _putId;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String _meetingName) {
		firePropertyChange("meetingName", meetingName, _meetingName);
		meetingName = _meetingName;
	}

	public Integer getMeetingPeoplenum() {
		return meetingPeoplenum;
	}

	public void setMeetingPeoplenum(Integer _meetingPeoplenum) {
		firePropertyChange("meetingPeoplenum", meetingPeoplenum, _meetingPeoplenum);
		meetingPeoplenum = _meetingPeoplenum;
	}

	public Long getStarttimeday() {
		return starttimeday;
	}

	public void setStarttimeday(Long _starttimeday) {
		firePropertyChange("starttimeday", starttimeday, _starttimeday);
		starttimeday = _starttimeday;
	}

	public Long getEndtimeday() {
		return endtimeday;
	}

	public void setEndtimeday(Long _endtimeday) {
		firePropertyChange("endtimeday", endtimeday, _endtimeday);
		endtimeday = _endtimeday;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String _meetingType) {
		firePropertyChange("meetingType", meetingType, _meetingType);
		meetingType = _meetingType;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(String _meetingRoom) {
		firePropertyChange("meetingRoom", meetingRoom, _meetingRoom);
		meetingRoom = _meetingRoom;
	}

	public String getMeetingPutperson() {
		return meetingPutperson;
	}

	public void setMeetingPutperson(String _meetingPutperson) {
		firePropertyChange("meetingPutperson", meetingPutperson, _meetingPutperson);
		meetingPutperson = _meetingPutperson;
	}

	public String getMeetingPutdep() {
		return meetingPutdep;
	}

	public void setMeetingPutdep(String _meetingPutdep) {
		firePropertyChange("meetingPutdep", meetingPutdep, _meetingPutdep);
		meetingPutdep = _meetingPutdep;
	}

	public Long getPutTime() {
		return putTime;
	}

	public void setPutTime(Long _putTime) {
		firePropertyChange("putTime", putTime, _putTime);
		putTime = _putTime;
	}

	public String getMeetingNeedingutil() {
		return meetingNeedingutil;
	}

	public void setMeetingNeedingutil(String _meetingNeedingutil) {
		firePropertyChange("meetingNeedingutil", meetingNeedingutil, _meetingNeedingutil);
		meetingNeedingutil = _meetingNeedingutil;
	}

	public String getMeetingConcept() {
		return meetingConcept;
	}

	public void setMeetingConcept(String _meetingConcept) {
		firePropertyChange("meetingConcept", meetingConcept, _meetingConcept);
		meetingConcept = _meetingConcept;
	}

	public InputStream getMeetingSitting() {
		return meetingSitting;
	}

	public void setMeetingSitting(InputStream _meetingSitting) {
		firePropertyChange("meetingSitting", meetingSitting, _meetingSitting);
		meetingSitting = _meetingSitting;
	}

	public String getMeetingSatus() {
		return meetingSatus;
	}

	public void setMeetingSatus(String _meetingSatus) {
		firePropertyChange("meetingSatus", meetingSatus, _meetingSatus);
		meetingSatus = _meetingSatus;
	}

	public String getPutMeno() {
		return putMeno;
	}

	public void setPutMeno(String _putMeno) {
		firePropertyChange("putMeno", putMeno, _putMeno);
		putMeno = _putMeno;
	}

	public Integer getMeetingEnd() {
		return meetingEnd;
	}

	public void setMeetingEnd(Integer _meetingEnd) {
		firePropertyChange("meetingEnd", meetingEnd, _meetingEnd);
		meetingEnd = _meetingEnd;
	}

	public String getContactingPerson() {
		return contactingPerson;
	}

	public void setContactingPerson(String _contactingPerson) {
		firePropertyChange("contactingPerson", contactingPerson, _contactingPerson);
		contactingPerson = _contactingPerson;
	}

	public Integer getMeetingPizhu() {
		return meetingPizhu;
	}

	public void setMeetingPizhu(Integer _meetingPizhu) {
		firePropertyChange("meetingPizhu", meetingPizhu, _meetingPizhu);
		meetingPizhu = _meetingPizhu;
	}

	public String getStartimehour() {
		return startimehour;
	}

	public void setStartimehour(String _startimehour) {
		firePropertyChange("startimehour", startimehour, _startimehour);
		startimehour = _startimehour;
	}

	public String getEndtimehour() {
		return endtimehour;
	}

	public void setEndtimehour(String _endtimehour) {
		firePropertyChange("endtimehour", endtimehour, _endtimehour);
		endtimehour = _endtimehour;
	}

	public String getSittingName() {
		return sittingName;
	}

	public void setSittingName(String _sittingName) {
		firePropertyChange("sittingName", sittingName, _sittingName);
		sittingName = _sittingName;
	}

	protected void setupFields() throws DAOException {
		addField("putId", "PUT_ID");
		addField("meetingName", "MEETING_NAME");
		addField("meetingPeoplenum", "MEETING_PEOPLENUM");
		addField("starttimeday", "STARTTIMEDAY");
		addField("endtimeday", "ENDTIMEDAY");
		addField("meetingType", "MEETING_TYPE");
		addField("meetingRoom", "MEETING_ROOM");
		addField("meetingPutperson", "MEETING_PUTPERSON");
		addField("meetingPutdep", "MEETING_PUTDEP");
		addField("putTime", "PUT_TIME");
		addField("meetingNeedingutil", "MEETING_NEEDINGUTIL");
		addField("meetingConcept", "MEETING_CONCEPT");
		addField("meetingSitting", "MEETING_SITTING");
		addField("meetingSatus", "MEETING_SATUS");
		addField("putMeno", "PUT_MENO");
		addField("meetingEnd", "MEETING_END");
		addField("contactingPerson", "CONTACTING_PERSON");
		addField("meetingPizhu", "MEETING_PIZHU");
		addField("startimehour", "STARTIMEHOUR");
		addField("endtimehour", "ENDTIMEHOUR");
		addField("sittingName", "SITTING_NAME");
		setTableName("MEETING_PUT");
		addKey("PUT_ID");
		setAutoIncremented("PUT_ID");
	}

	public MeetingPutDAO(Connection conn) {
		super(conn);
	}

	public MeetingPutDAO() {
		super();
	}
}
