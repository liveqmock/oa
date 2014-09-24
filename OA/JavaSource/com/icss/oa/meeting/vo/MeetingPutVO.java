/*
 * Created on 2004-8-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.vo;

import java.io.InputStream;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MeetingPutVO extends ValueObject {

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

	public void setPutId(Integer _putId) {
		putId = _putId;
	}

	public Integer getPutId() {
		return putId;
	}

	public void setMeetingName(String _meetingName) {
		meetingName = _meetingName;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingPeoplenum(Integer _meetingPeoplenum) {
		meetingPeoplenum = _meetingPeoplenum;
	}

	public Integer getMeetingPeoplenum() {
		return meetingPeoplenum;
	}

	public void setStarttimeday(Long _starttimeday) {
		starttimeday = _starttimeday;
	}

	public Long getStarttimeday() {
		return starttimeday;
	}

	public void setEndtimeday(Long _endtimeday) {
		endtimeday = _endtimeday;
	}

	public Long getEndtimeday() {
		return endtimeday;
	}

	public void setMeetingType(String _meetingType) {
		meetingType = _meetingType;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingRoom(String _meetingRoom) {
		meetingRoom = _meetingRoom;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingPutperson(String _meetingPutperson) {
		meetingPutperson = _meetingPutperson;
	}

	public String getMeetingPutperson() {
		return meetingPutperson;
	}

	public void setMeetingPutdep(String _meetingPutdep) {
		meetingPutdep = _meetingPutdep;
	}

	public String getMeetingPutdep() {
		return meetingPutdep;
	}

	public void setPutTime(Long _putTime) {
		putTime = _putTime;
	}

	public Long getPutTime() {
		return putTime;
	}

	public void setMeetingNeedingutil(String _meetingNeedingutil) {
		meetingNeedingutil = _meetingNeedingutil;
	}

	public String getMeetingNeedingutil() {
		return meetingNeedingutil;
	}

	public void setMeetingConcept(String _meetingConcept) {
		meetingConcept = _meetingConcept;
	}

	public String getMeetingConcept() {
		return meetingConcept;
	}

	public void setMeetingSitting(InputStream _meetingSitting) {
		meetingSitting = _meetingSitting;
	}

	public InputStream getMeetingSitting() {
		return meetingSitting;
	}

	public void setMeetingSatus(String _meetingSatus) {
		meetingSatus = _meetingSatus;
	}

	public String getMeetingSatus() {
		return meetingSatus;
	}

	public void setPutMeno(String _putMeno) {
		putMeno = _putMeno;
	}

	public String getPutMeno() {
		return putMeno;
	}

	public void setMeetingEnd(Integer _meetingEnd) {
		meetingEnd = _meetingEnd;
	}

	public Integer getMeetingEnd() {
		return meetingEnd;
	}

	public void setContactingPerson(String _contactingPerson) {
		contactingPerson = _contactingPerson;
	}

	public String getContactingPerson() {
		return contactingPerson;
	}

	public void setMeetingPizhu(Integer _meetingPizhu) {
		meetingPizhu = _meetingPizhu;
	}

	public Integer getMeetingPizhu() {
		return meetingPizhu;
	}

	public void setStartimehour(String _startimehour) {
		startimehour = _startimehour;
	}

	public String getStartimehour() {
		return startimehour;
	}

	public void setEndtimehour(String _endtimehour) {
		endtimehour = _endtimehour;
	}

	public String getEndtimehour() {
		return endtimehour;
	}

	public void setSittingName(String _sittingName) {
		sittingName = _sittingName;
	}

	public String getSittingName() {
		return sittingName;
	}
}
