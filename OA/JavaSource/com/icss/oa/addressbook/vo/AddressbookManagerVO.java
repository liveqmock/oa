/*
 * Created on 2004-8-2
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.vo;

import java.io.InputStream;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookManagerVO extends ValueObject {
	Integer abmId;
	String abmOwner;
	Long abmCretetime;
	Long abmUpdatetime;
	InputStream abmAccessory;
	public Integer getAbmId() {
		return abmId;
	}
	public void setAbmId(Integer _abmId) {
		abmId = _abmId;
	}
	public String getAbmOwner() {
		return abmOwner;
	}
	public void setAbmOwner(String _abmOwner) {
		abmOwner = _abmOwner;
	}
	public Long getAbmCretetime() {
		return abmCretetime;
	}
	public void setAbmCretetime(Long _abmCretetime) {
		abmCretetime = _abmCretetime;
	}
	public Long getAbmUpdatetime() {
		return abmUpdatetime;
	}
	public void setAbmUpdatetime(Long _abmUpdatetime) {
		abmUpdatetime = _abmUpdatetime;
	}
	public InputStream getAbmAccessory() {
		return abmAccessory;
	}
	public void setAbmAccessory(InputStream _abmAccessory) {
		abmAccessory = _abmAccessory;
	}
}