package com.icss.oa.multivote.vo;

import com.icss.j2ee.vo.ValueObject;

public class VoteValueVO extends ValueObject {
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
	
		voteId = _voteId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer _personId) {
	
		personId = _personId;
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer _artId) {
		
		artId = _artId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer _itemId) {
		
		itemId = _itemId;
	}

	public Integer getVoteValue() {
		return voteValue;
	}

	public void setVoteValue(Integer _voteValue) {
		
		voteValue = _voteValue;
	}

	public String getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(String _voteDate) {
		
		voteDate = _voteDate;
	}

	public String getVoteSeason() {
		return voteSeason;
	}

	public void setVoteSeason(String _voteSeason) {
	
		voteSeason = _voteSeason;
	}

}