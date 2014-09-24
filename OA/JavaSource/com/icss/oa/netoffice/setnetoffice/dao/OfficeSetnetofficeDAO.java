/*
 * Created on 2004-6-1
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.setnetoffice.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeSetnetofficeDAO extends DAO {
	Integer setId;
	Integer setType;
	Integer monthsReserve;
	Integer hoursRemind;
	
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer _setId) {
		firePropertyChange("setId", setId, _setId);
		setId = _setId;
	}
	public Integer getSetType() {
		return setType;
	}
	public void setSetType(Integer _setType) {
		firePropertyChange("setType", setType, _setType);
		setType = _setType;
	}
	public Integer getMonthsReserve() {
		return monthsReserve;
	}
	public void setMonthsReserve(Integer _monthsReserve) {
		firePropertyChange("monthsReserve", monthsReserve, _monthsReserve);
		monthsReserve = _monthsReserve;
	}
	public Integer getHoursRemind() {
		return hoursRemind;
	}
	public void setHoursRemind(Integer _hoursRemind) {
		firePropertyChange("hoursRemind", hoursRemind, _hoursRemind);
		hoursRemind = _hoursRemind;
	}
	
	
	protected void setupFields() throws DAOException {
		addField("setId", "SET_ID");
		addField("setType", "SET_TYPE");
		addField("monthsReserve", "MONTHS_RESERVE");
		addField("hoursRemind", "HOURS_REMIND");
		
		setTableName("OFFICE_SETNETOFFICE");
		addKey("SET_ID");
		this.setAutoIncremented("SET_ID");
	}
	public OfficeSetnetofficeDAO(Connection conn) {
		super(conn);
	}
	public OfficeSetnetofficeDAO() {
		super();
	}
}
