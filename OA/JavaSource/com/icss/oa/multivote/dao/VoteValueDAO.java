package com.icss.oa.multivote.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class VoteValueDAO extends DAO {

	private Integer voteId;

	private Integer personId;

	private Integer artId;

	private Integer itemId;

	private Integer voteValue;

	private String voteDate;

	private String voteSeason;

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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer _itemId) {
		firePropertyChange("itemId", itemId, _itemId);
		itemId = _itemId;
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

	public String getVoteSeason() {
		return voteSeason;
	}

	public void setVoteSeason(String _voteSeason) {
		firePropertyChange("voteSeason", voteSeason, _voteSeason);
		voteSeason = _voteSeason;
	}

	protected void setupFields() throws DAOException {
		addField("voteId", "VOTE_ID");
		addField("personId", "PERSON_ID");
		addField("artId", "ART_ID");
		addField("itemId", "ITEM_ID");
		addField("voteValue", "VOTE_VALUE");
		addField("voteDate", "VOTE_DATE");
		addField("voteSeason", "VOTE_SEASON");
		setTableName("VOTE_VALUE");
		addKey("VOTE_ID");
		setAutoIncremented("VOTE_ID");
	}

	public VoteValueDAO(Connection conn) {
		super(conn);
	}

	public VoteValueDAO() {
		super();
	}

}
