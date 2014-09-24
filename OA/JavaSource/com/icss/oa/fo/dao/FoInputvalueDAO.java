/*
 * Created on 2007-6-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.fo.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FoInputvalueDAO extends DAO {

	private Integer invalueId;

	private Integer artId;

	private Integer invoteId;

	private Integer invalueValue;

	public Integer getInvalueId() {
		return invalueId;
	}

	public void setInvalueId(Integer _invalueId) {
		firePropertyChange("invalueId", invalueId, _invalueId);
		invalueId = _invalueId;
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer _artId) {
		firePropertyChange("artId", artId, _artId);
		artId = _artId;
	}

	public Integer getInvoteId() {
		return invoteId;
	}

	public void setInvoteId(Integer _invoteId) {
		firePropertyChange("invoteId", invoteId, _invoteId);
		invoteId = _invoteId;
	}

	public Integer getInvalueValue() {
		return invalueValue;
	}

	public void setInvalueValue(Integer _invalueValue) {
		firePropertyChange("invalueValue", invalueValue, _invalueValue);
		invalueValue = _invalueValue;
	}

	protected void setupFields() throws DAOException {
		addField("invalueId", "INVALUE_ID");
		addField("artId", "ART_ID");
		addField("invoteId", "INVOTE_ID");
		addField("invalueValue", "INVALUE_VALUE");
		setTableName("FO_INPUTVALUE");
		addKey("INVALUE_ID");
		setAutoIncremented("INVALUE_ID");
	}

	public FoInputvalueDAO(Connection conn) {
		super(conn);
	}

	public FoInputvalueDAO() {
		super();
	}

}
