package com.icss.regulation.vo;

import com.icss.j2ee.vo.ValueObject;

public class RegulationOrgVO extends ValueObject {
	private String orgname;
	private String orguuid;
	private Integer sequence;
	private String memo;
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String orguuid) {
		this.orguuid = orguuid;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}