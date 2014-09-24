package com.icss.oa.statsite.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StatSiteMoldVO extends ValueObject {
	Integer id;
	String visMold;
	Integer visNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		
		id = _id;
	}
	public String getVisMold() {
		return visMold;
	}
	public void setVisMold(String _visMold) {
		
		visMold = _visMold;
	}
	public Integer getVisNumber() {
		return visNumber;
	}
	public void setVisNumber(Integer _visNumber) {
		
		visNumber = _visNumber;
	}
	
}
