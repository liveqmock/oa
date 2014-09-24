/*
 * Created on 2004-8-12
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
public class MeetingRoominformationVO extends ValueObject {

	private Integer meetingId;

	private String meetName;

	private String belongBuilding;

	private String belongRoom;

	private Integer meetingMaxnum;

	private String meetingUtil;

	private InputStream meetingSitting;

	private String sittingName;

	private Integer num;

	public void setMeetingId(Integer _meetingId) {
		meetingId = _meetingId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetName(String _meetName) {
		meetName = _meetName;
	}

	public String getMeetName() {
		return meetName;
	}

	public void setBelongBuilding(String _belongBuilding) {
		belongBuilding = _belongBuilding;
	}

	public String getBelongBuilding() {
		return belongBuilding;
	}

	public void setBelongRoom(String _belongRoom) {
		belongRoom = _belongRoom;
	}

	public String getBelongRoom() {
		return belongRoom;
	}

	public void setMeetingMaxnum(Integer _meetingMaxnum) {
		meetingMaxnum = _meetingMaxnum;
	}

	public Integer getMeetingMaxnum() {
		return meetingMaxnum;
	}

	public void setMeetingUtil(String _meetingUtil) {
		meetingUtil = _meetingUtil;
	}

	public String getMeetingUtil() {
		return meetingUtil;
	}

	public void setMeetingSitting(InputStream _meetingSitting) {
		meetingSitting = _meetingSitting;
	}

	public InputStream getMeetingSitting() {
		return meetingSitting;
	}

	public void setSittingName(String _sittingName) {
		sittingName = _sittingName;
	}

	public String getSittingName() {
		return sittingName;
	}

	public void setNum(Integer _num) {
		num = _num;
	}

	public Integer getNum() {
		return num;
	}
}
