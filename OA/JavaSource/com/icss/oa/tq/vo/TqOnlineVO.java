package com.icss.oa.tq.vo;

import com.icss.j2ee.vo.ValueObject;

public class TqOnlineVO extends ValueObject {
	public String tqid;
	public String cnname;
	public String orgname;

	public String getTqid() {
		return tqid;
	}

	public void setTqid(String tqid) {
		this.tqid = tqid;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

}
