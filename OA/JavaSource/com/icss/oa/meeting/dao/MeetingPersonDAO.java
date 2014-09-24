/*
 * Created on 2004-9-21
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
public class MeetingPersonDAO extends DAO {

	private Integer id;

	private Integer meetingId;

	private String person;

	private String baoming;

	private String daohui;

	private String person1;

	private Integer order1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer _meetingId) {
		firePropertyChange("meetingId", meetingId, _meetingId);
		meetingId = _meetingId;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String _person) {
		firePropertyChange("person", person, _person);
		person = _person;
	}

	public String getBaoming() {
		return baoming;
	}

	public void setBaoming(String _baoming) {
		firePropertyChange("baoming", baoming, _baoming);
		baoming = _baoming;
	}

	public String getDaohui() {
		return daohui;
	}

	public void setDaohui(String _daohui) {
		firePropertyChange("daohui", daohui, _daohui);
		daohui = _daohui;
	}

	public String getPerson1() {
		return person1;
	}

	public void setPerson1(String _person1) {
		firePropertyChange("person1", person1, _person1);
		person1 = _person1;
	}

	public Integer getOrder1() {
		return order1;
	}

	public void setOrder1(Integer _order1) {
		firePropertyChange("order1", order1, _order1);
		order1 = _order1;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("meetingId", "MEETING_ID");
		addField("person", "PERSON");
		addField("baoming", "BAOMING");
		addField("daohui", "DAOHUI");
		addField("person1", "PERSON1");
		addField("order1", "ORDER1");
		setTableName("MEETING_PERSON");
		addKey("ID");
		setAutoIncremented("ID");
	}

	public MeetingPersonDAO(Connection conn) {
		super(conn);
	}

	public MeetingPersonDAO() {
		super();
	}
}
