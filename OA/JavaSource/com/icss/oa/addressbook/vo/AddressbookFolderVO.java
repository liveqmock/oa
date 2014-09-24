/*
 * Created on 2004-7-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookFolderVO extends ValueObject {
	Integer abfId;
	Integer addAbfId;
	Integer addAbmId;
	String abfName;
	String abfDescript;
	Long abfCreatetime;
	Long abfUpdatetime;
	String abfFlag;
	public Integer getAbfId() {
		return abfId;
	}
	public void setAbfId(Integer _abfId) {
		abfId = _abfId;
	}
	public Integer getAddAbfId() {
		return addAbfId;
	}
	public void setAddAbfId(Integer _addAbfId) {
		addAbfId = _addAbfId;
	}
	public Integer getAddAbmId() {
		return addAbmId;
	}
	public void setAddAbmId(Integer _addAbmId) {
		addAbmId = _addAbmId;
	}
	public String getAbfName() {
		return abfName;
	}
	public void setAbfName(String _abfName) {
		abfName = _abfName;
	}
	public String getAbfDescript() {
		return abfDescript;
	}
	public void setAbfDescript(String _abfDescript) {
		abfDescript = _abfDescript;
	}
	public Long getAbfCreatetime() {
		return abfCreatetime;
	}
	public void setAbfCreatetime(Long _abfCreatetime) {
		abfCreatetime = _abfCreatetime;
	}
	public Long getAbfUpdatetime() {
		return abfUpdatetime;
	}
	public void setAbfUpdatetime(Long _abfUpdatetime) {
		abfUpdatetime = _abfUpdatetime;
	}
	public String getAbfFlag() {
		return abfFlag;
	}
	public void setAbfFlag(String _abfFlag) {
		abfFlag = _abfFlag;
	}
}