/*
 * Created on 2004-8-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OfficeDutyPeriodVO extends ValueObject {
	private Integer periodid;
	private String orguuid;
	private Long periodstart;
	private Long periodend;
	public Integer getPeriodid() {
		return periodid;
	}
	public void setPeriodid(Integer _periodid) {
		periodid = _periodid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
	public Long getPeriodstart() {
		return periodstart;
	}
	public void setPeriodstart(Long _periodstart) {
		periodstart = _periodstart;
	}
	public Long getPeriodend() {
		return periodend;
	}
	public void setPeriodend(Long _periodend) {
		periodend = _periodend;
	}
}