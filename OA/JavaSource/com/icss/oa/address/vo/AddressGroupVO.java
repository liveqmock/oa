package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

public class AddressGroupVO extends ValueObject {
	private Integer id;
	private String groupuser;
	private String groupname;
	private String groupdes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public String getGroupuser() {
		return groupuser;
	}
	public void setGroupuser(String _groupuser) {
		groupuser = _groupuser;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String _groupname) {
		groupname = _groupname;
	}
	public String getGroupdes() {
		return groupdes;
	}
	public void setGroupdes(String _groupdes) {
		groupdes = _groupdes;
	}
}