/*
 * Created on 2004-6-1
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeScheduleVO extends ValueObject {
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
	public void setOsId(Integer _osId) {
		osId = _osId;
	}
	public Integer getOsId() {
		return osId;
	}
	public void setOsTopic(String _osTopic) {
		osTopic = _osTopic;
	}
	public String getOsTopic() {
		return osTopic;
	}
	public void setOsType(String _osType) {
		osType = _osType;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsPlace(String _osPlace) {
		osPlace = _osPlace;
	}
	public String getOsPlace() {
		return osPlace;
	}
	public void setOsTimetype(String _osTimetype) {
		osTimetype = _osTimetype;
	}
	public String getOsTimetype() {
		return osTimetype;
	}
	public void setOsBegin(Integer _osBegin) {
		osBegin = _osBegin;
	}
	public Integer getOsBegin() {
		return osBegin;
	}
	public void setOsEnd(Integer _osEnd) {
		osEnd = _osEnd;
	}
	public Integer getOsEnd() {
		return osEnd;
	}
	public void setOsDes(String _osDes) {
		osDes = _osDes;
	}
	public String getOsDes() {
		return osDes;
	}
	public void setOsDate(Long _osDate) {
		osDate = _osDate;
	}
	public Long getOsDate() {
		return osDate;
	}
	public void setOsAlertbuffer(Integer _osAlertbuffer) {
		osAlertbuffer = _osAlertbuffer;
	}
	public Integer getOsAlertbuffer() {
		return osAlertbuffer;
	}
	public void setPersonid(String _personid) {
		personid = _personid;
	}
	public String getPersonid() {
		return personid;
	}

	public void setIsreminded(String _isreminded) {
		isreminded = _isreminded;
	}
	public String getIsreminded() {
		return isreminded;
	}
}
