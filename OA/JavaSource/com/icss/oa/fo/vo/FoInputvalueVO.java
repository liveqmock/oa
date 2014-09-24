/*
 * Created on 2007-6-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.fo.vo;

import java.sql.Connection;


import com.icss.j2ee.vo.ValueObject;

public class FoInputvalueVO extends ValueObject {

	private Integer invalueId;

	private Integer artId;

	private Integer invoteId;

	private Integer invalueValue;

	public Integer getInvalueId() {
		return invalueId;
	}

	public void setInvalueId(Integer _invalueId) {
		
		invalueId = _invalueId;
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer _artId) {
		
		artId = _artId;
	}

	public Integer getInvoteId() {
		return invoteId;
	}

	public void setInvoteId(Integer _invoteId) {
		
		invoteId = _invoteId;
	}

	public Integer getInvalueValue() {
		return invalueValue;
	}

	public void setInvalueValue(Integer _invalueValue) {
		
		invalueValue = _invalueValue;
	}

	
}
