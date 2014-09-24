/*
 * Created on 2004-12-23
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
public class PhoneJobVO extends ValueObject {
	Integer jobId;
	String jobName;
	Integer jobLevel;
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer _jobId) {
		jobId = _jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String _jobName) {
		jobName = _jobName;
	}
	public Integer getJobLevel() {
		return jobLevel;
	}
	public void setJobLevel(Integer _jobLevel) {
		jobLevel = _jobLevel;
	}
}