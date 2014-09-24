package com.icss.regulation.vo;

import com.icss.j2ee.vo.ValueObject;

public class RegulationCountVO extends ValueObject {
	private String orgname;
	private Integer total;
	private Integer unvalid;
	
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getUnvalid() {
		return unvalid;
	}
	public void setUnvalid(Integer unvalid) {
		this.unvalid = unvalid;
	}
	
}