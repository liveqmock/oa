/*
 * Created on 2004-8-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhoneCustomsqlVO extends ValueObject {
	private Integer sqlId;
	private String userid;
	private String orguuid;
	private String sqlContent;
	private String sqlTitle;
	public Integer getSqlId() {
		return sqlId;
	}
	public void setSqlId(Integer _sqlId) {
		sqlId = _sqlId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String _userid) {
		userid = _userid;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		orguuid = _orguuid;
	}
	public String getSqlContent() {
		return sqlContent;
	}
	public void setSqlContent(String _sqlContent) {
		sqlContent = _sqlContent;
	}
	public String getSqlTitle() {
		return sqlTitle;
	}
	public void setSqlTitle(String _sqlTitle) {
		sqlTitle = _sqlTitle;
	}
}