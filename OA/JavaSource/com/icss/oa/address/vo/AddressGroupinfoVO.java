package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

public class AddressGroupinfoVO extends ValueObject {
	private Integer groupid;
	private String userid;
	private String grouptype;
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer _groupid) {
		groupid = _groupid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	/**
	 * @return
	 */
	public String getGrouptype() {
		return grouptype;
	}

	/**
	 * @param string
	 */
	public void setGrouptype(String _grouptype) {
		grouptype = _grouptype;
	}

}