/*
 * Created on 2004-9-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MeetingPersonVO extends ValueObject {

	private Integer id;

	private Integer meetingId;

	private String person;

	private String baoming;

	private String daohui;

	private String person1;

	private Integer order1;

	public void setId(Integer _id) {
		id = _id;
	}

	public Integer getId() {
		return id;
	}

	public void setMeetingId(Integer _meetingId) {
		meetingId = _meetingId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setPerson(String _person) {
		person = _person;
	}

	public String getPerson() {
		return person;
	}

	public void setBaoming(String _baoming) {
		baoming = _baoming;
	}

	public String getBaoming() {
		return baoming;
	}

	public void setDaohui(String _daohui) {
		daohui = _daohui;
	}

	public String getDaohui() {
		return daohui;
	}

	public void setPerson1(String _person1) {
		person1 = _person1;
	}

	public String getPerson1() {
		return person1;
	}

	public void setOrder1(Integer _order1) {
		order1 = _order1;
	}

	public Integer getOrder1() {
		return order1;
	}
}
