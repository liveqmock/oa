/*
 * Created on 2004-6-1
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeScheduleDAO extends DAO {
	Integer osId;
	String osTopic;
	String osType;
	String osPlace;
	String osTimetype;
	Integer osBegin;
	Integer osEnd;
	String osDes;
	Long osDate;
	Integer osAlertbuffer;
	String personid;
	String isreminded;
	public Integer getOsId() {
		return osId;
	}
	public void setOsId(Integer _osId) {
		firePropertyChange("osId", osId, _osId);
		osId = _osId;
	}
	public String getOsTopic() {
		return osTopic;
	}
	public void setOsTopic(String _osTopic) {
		firePropertyChange("osTopic", osTopic, _osTopic);
		osTopic = _osTopic;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String _osType) {
		firePropertyChange("osType", osType, _osType);
		osType = _osType;
	}
	public String getOsPlace() {
		return osPlace;
	}
	public void setOsPlace(String _osPlace) {
		firePropertyChange("osPlace", osPlace, _osPlace);
		osPlace = _osPlace;
	}
	public String getOsTimetype() {
		return osTimetype;
	}
	public void setOsTimetype(String _osTimetype) {
		firePropertyChange("osTimetype", osTimetype, _osTimetype);
		osTimetype = _osTimetype;
	}
	public Integer getOsBegin() {
		return osBegin;
	}
	public void setOsBegin(Integer _osBegin) {
		firePropertyChange("osBegin", osBegin, _osBegin);
		osBegin = _osBegin;
	}
	public Integer getOsEnd() {
		return osEnd;
	}
	public void setOsEnd(Integer _osEnd) {
		firePropertyChange("osEnd", osEnd, _osEnd);
		osEnd = _osEnd;
	}
	public String getOsDes() {
		return osDes;
	}
	public void setOsDes(String _osDes) {
		firePropertyChange("osDes", osDes, _osDes);
		osDes = _osDes;
	}
	public Long getOsDate() {
		return osDate;
	}
	public void setOsDate(Long _osDate) {
		firePropertyChange("osDate", osDate, _osDate);
		osDate = _osDate;
	}
	public Integer getOsAlertbuffer() {
		return osAlertbuffer;
	}
	public void setOsAlertbuffer(Integer _osAlertbuffer) {
		firePropertyChange("osAlertbuffer", osAlertbuffer, _osAlertbuffer);
		osAlertbuffer = _osAlertbuffer;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		personid = _personid;
	}

	public String getIsreminded() {
		return isreminded;
	}
	public void setIsreminded(String _isreminded) {
		firePropertyChange("isreminded", isreminded, _isreminded);
		isreminded = _isreminded;
	}
	protected void setupFields() throws DAOException {
		addField("osId", "OS_ID");
		addField("osTopic", "OS_TOPIC");
		addField("osType", "OS_TYPE");
		addField("osPlace", "OS_PLACE");
		addField("osTimetype", "OS_TIMETYPE");
		addField("osBegin", "OS_BEGIN");
		addField("osEnd", "OS_END");
		addField("osDes", "OS_DES");
		addField("osDate", "OS_DATE");
		addField("osAlertbuffer", "OS_ALERTBUFFER");
		addField("personid", "PERSONID");
		addField("isreminded", "ISREMINDED");
		setTableName("OFFICE_SCHEDULE");
		addKey("OS_ID");
		this.setAutoIncremented("OS_ID");
	}
	public OfficeScheduleDAO(Connection conn) {
		super(conn);
	}
	public OfficeScheduleDAO() {
		super();
	}
}
