/*
 * Created on 2004-6-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.statsite.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StatSiteIpnumberVO extends ValueObject {

	Integer id;

	String address;

	Long counter;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		id = _id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String _address) {
		address = _address;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long _counter) {
		counter = _counter;
	}

	
}
