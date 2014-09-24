/*
 * Created on 2004-6-1
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.setnetoffice.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeSetnetofficeVO extends ValueObject {
	Integer setId;
	Integer setType;
	Integer monthsReserve;
	Integer hoursRemind;
	
	public void setSetId(Integer _setId) {
		setId = _setId;
	}
	public Integer getSetId() {
		return setId;
	}
	public void setSetType(Integer _setType) {
		setType = _setType;
	}
	public Integer getSetType() {
		return setType;
	}
	public void setMonthsReserve(Integer _monthsReserve) {
		monthsReserve = _monthsReserve;
	}
	public Integer getMonthsReserve() {
		return monthsReserve;
	}
	public void setHoursRemind(Integer _hoursRemind) {
		hoursRemind = _hoursRemind;
	}
	public Integer getHoursRemind() {
		return hoursRemind;
	}
	
}
