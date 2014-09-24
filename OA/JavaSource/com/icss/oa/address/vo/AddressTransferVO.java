/*
 * Created on 2002-3-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author sunchuanting 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddressTransferVO extends ValueObject {

	private Integer id;

	private String personuuid;

	private Integer commongroup;

	public void setId(Integer _id) {
		id = _id;
	}

	public Integer getId() {
		return id;
	}

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}

	public void setCommongroup(Integer _commongroup) {
		commongroup = _commongroup;
	}

	public Integer getCommongroup() {
		return commongroup;
	}
}
