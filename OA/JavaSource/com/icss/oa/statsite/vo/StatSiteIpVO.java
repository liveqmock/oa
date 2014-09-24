/*
 * Created on 2004-7-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.statsite.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatSiteIpVO extends ValueObject{

	Integer id;

	String ipadress;

	Long number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		id = _id;
	}

	public String getIpadress() {
		return ipadress;
	}

	public void setIpadress(String _ipadress) {
		ipadress = _ipadress;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long _number) {
		number = _number;
	}

}
