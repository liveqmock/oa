/*
 * Created on 2004-8-16
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MeetingPersonmeetVO extends ValueObject {

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

	public void setId(Integer _id) {
		id = _id;
	}

	public Integer getId() {
		return id;
	}

	public void setMeetingName(String _meetingName) {
		meetingName = _meetingName;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setStarttime(Long _starttime) {
		starttime = _starttime;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setEndtime(Long _endtime) {
		endtime = _endtime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setStart1(String _start1) {
		start1 = _start1;
	}

	public String getStart1() {
		return start1;
	}

	public void setEnd1(String _end1) {
		end1 = _end1;
	}

	public String getEnd1() {
		return end1;
	}

	public void setMeetingType(String _meetingType) {
		meetingType = _meetingType;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingConcept(String _meetingConcept) {
		meetingConcept = _meetingConcept;
	}

	public String getMeetingConcept() {
		return meetingConcept;
	}

	public void setPerson(String _person) {
		person = _person;
	}

	public String getPerson() {
		return person;
	}

	public void setMeetingRoom(String _meetingRoom) {
		meetingRoom = _meetingRoom;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingUtil(String _meetingUtil) {
		meetingUtil = _meetingUtil;
	}

	public String getMeetingUtil() {
		return meetingUtil;
	}

	public void setMeetingPutperon(String _meetingPutperon) {
		meetingPutperon = _meetingPutperon;
	}

	public String getMeetingPutperon() {
		return meetingPutperon;
	}

	public void setMeetingPutdep(String _meetingPutdep) {
		meetingPutdep = _meetingPutdep;
	}

	public String getMeetingPutdep() {
		return meetingPutdep;
	}

	public void setMeetingPutid(Integer _meetingPutid) {
		meetingPutid = _meetingPutid;
	}

	public Integer getMeetingPutid() {
		return meetingPutid;
	}
}
