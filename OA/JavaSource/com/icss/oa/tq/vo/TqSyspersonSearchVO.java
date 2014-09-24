package com.icss.oa.tq.vo;

import com.icss.j2ee.vo.ValueObject;


public class TqSyspersonSearchVO extends ValueObject {

	private String personuuid;

	private String cnname;
	
	private Integer tqid;
	
	private String userid;

	private Integer groupid;

	private String grouptype;

	public void setPersonuuid(String _personuuid) {
		personuuid = _personuuid;
	}

	public String getPersonuuid() {
		return personuuid;
	}
	
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}

	public String getCnname() {
		return cnname;
	}

	public void setTqid(Integer _tqid) {
		tqid = _tqid;
	}

	public Integer getTqid() {
		return tqid;
	}

	public void setUserid(String _userid) {
		userid = _userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setGroupid(Integer _groupid) {
		groupid = _groupid;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGrouptype(String _grouptype) {
		grouptype = _grouptype;
	}

	public String getGrouptype() {
		return grouptype;
	}

	
}
