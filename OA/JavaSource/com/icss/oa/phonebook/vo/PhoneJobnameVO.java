/*
 * Created on 2004-12-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PhoneJobnameVO extends ValueObject {
	Integer nameid;
	String name;
	Integer jobid;
	String orguuid;
	public Integer getNameid() {
		return nameid;
	}
	public void setNameid(Integer _nameid) {
		nameid = _nameid;
	}
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		name = _name;
	}
	public Integer getJobid() {
		return jobid;
	}
	public void setJobid(Integer _jobid) {
		jobid = _jobid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
}