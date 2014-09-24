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
public class StatSiteDateVO extends ValueObject {
	Integer id;
	Integer visNumber;
	Long visDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		
		id = _id;
	}
	public Integer getVisNumber() {
		return visNumber;
	}
	public void setVisNumber(Integer _visNumber) {
		
		visNumber = _visNumber;
	}
	public Long getVisDate() {
		return visDate;
	}
	public void setVisDate(Long _visDate) {
		
		visDate = _visDate;
	}
	
}
