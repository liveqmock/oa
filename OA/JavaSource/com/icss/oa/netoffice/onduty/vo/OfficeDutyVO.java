/*
 * Created on 2004-8-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OfficeDutyVO extends ValueObject {
	private Integer dutyid;
	private String personuuid;
	private String orguuid;
	private Long starttime;
	private Long endtime;
	private String dutyname;
	public Integer getDutyid() {
		return dutyid;
	}
	public void setDutyid(Integer _dutyid) {
		dutyid = _dutyid;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long _starttime) {
		starttime = _starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long _endtime) {
		endtime = _endtime;
	}
	public String getDutyname() {
		return dutyname;
	}
	public void setDutyname(String _dutyname) {
		dutyname = _dutyname;
	}
}