package com.icss.regulation.dao;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class RegulationOrgDAO extends DAO {

	private String orgname;
	private String orguuid;
	private Integer sequence;
	private String memo;


	public void setSequence(java.lang.Integer _sequence) {
		firePropertyChange("sequence", sequence, _sequence);
		sequence = _sequence;
	}

	public java.lang.Integer getSequence() {
		return sequence;
	}


	public void setOrgname(String _orgname) {
		firePropertyChange("orgname", orgname, _orgname);
		orgname = _orgname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrguuid(java.lang.String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}

	public java.lang.String getOrguuid() {
		return orguuid;
	}

	public void setMemo(java.lang.String _memo) {
		firePropertyChange("memo", memo, _memo);
		memo = _memo;
	}

	public java.lang.String getMemo() {
		return memo;
	}
	

	protected void setupFields() throws DAOException {
		addField("orgname", "ORGNAME");
		addField("orguuid", "ORGUUID");
		addField("sequence", "SEQUENCE");
		addField("memo", "MEMO");
		setTableName("REGULATION_ORG");
		addKey("ORGUUID");
	}

}