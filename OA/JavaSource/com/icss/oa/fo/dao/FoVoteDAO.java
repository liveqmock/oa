package com.icss.oa.fo.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FoVoteDAO extends DAO {

	private Integer voteId;

	private Integer personId;

	private Integer artId;

	private Integer voteValue;

	

	private String voteDate;

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer _voteId) {
		firePropertyChange("voteId", voteId, _voteId);
		voteId = _voteId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer _personId) {
		firePropertyChange("personId", personId, _personId);
		personId = _personId;
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer _artId) {
		firePropertyChange("artId", artId, _artId);
		artId = _artId;
	}

	public Integer getVoteValue() {
		return voteValue;
	}

	public void setVoteValue(Integer _voteValue) {
		firePropertyChange("voteValue", voteValue, _voteValue);
		voteValue = _voteValue;
	}

	

	public String getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(String _voteDate) {
		firePropertyChange("voteDate", voteDate, _voteDate);
		voteDate = _voteDate;
	}

	protected void setupFields() throws DAOException {
		addField("voteId", "VOTE_ID");
		addField("personId", "PERSON_ID");
		addField("artId", "ART_ID");
		addField("voteValue", "VOTE_VALUE");
		
		addField("voteDate", "VOTE_DATE");
		setTableName("FO_VOTE");
		addKey("VOTE_ID");
		setAutoIncremented("VOTE_ID");
	}

	public FoVoteDAO(Connection conn) {
		super(conn);
	}

	public FoVoteDAO() {
		super();
	}

}
