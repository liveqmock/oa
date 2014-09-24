package com.icss.oa.tq.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class TqMsgDAO extends DAO {
	
	public Integer id;
	public String sender;
	public String title;
	public String digest;
	public String body;
	public String endday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getSender() {
		return sender;
	}

	public void setSender(String _sender) {
		firePropertyChange("sender", sender, _sender);
		sender = _sender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		title = _title;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String _digest) {
		firePropertyChange("digest", digest, _digest);
		digest = _digest;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String _body) {
		firePropertyChange("body", body, _body);
		body = _body;
	}

	public String getEndday() {
		return endday;
	}

	public void setEndday(String _endday) {
		firePropertyChange("endday", endday, _endday);
		endday = _endday;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("sender", "SENDER");
		addField("title", "TITLE");
		addField("digest", "DIGEST");
		addField("body", "BODY");
		addField("endday", "ENDDAY");
		setTableName("TQ_MSG");
		
		addKey("ID");
		this.setAutoIncremented("ID");
	}

	public TqMsgDAO() {
		super();
	}

	public TqMsgDAO(Connection conn) {
		super(conn);
	}
}