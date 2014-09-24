/*
 * Created on 2005-1-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.intendwork.vo;    

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InfoStopVO extends ValueObject {

	private Integer id1;

	private String personuuid;

	private String stopbuffer;

	public void setId1(Integer _id1) {
		id1 = _id1;
	}

	public Integer getId1() {
		return id1;
	}

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setStopbuffer(String _stopbuffer) {
		stopbuffer = _stopbuffer;
	}

	public String getStopbuffer() {
		return stopbuffer;
	}
}
