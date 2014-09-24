/*
 * Created on 2004-8-2
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.dao;

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookManagerDAO extends DAO {

	Integer abmId;
	String abmOwner;
	Long abmCretetime;
	Long abmUpdatetime;
	InputStream abmAccessory;
	public Integer getAbmId() {
		return abmId;
	}
	public void setAbmId(Integer _abmId) {
		firePropertyChange("abmId", abmId, _abmId);
		abmId = _abmId;
	}
	public String getAbmOwner() {
		return abmOwner;
	}
	public void setAbmOwner(String _abmOwner) {
		firePropertyChange("abmOwner", abmOwner, _abmOwner);
		abmOwner = _abmOwner;
	}
	public Long getAbmCretetime() {
		return abmCretetime;
	}
	public void setAbmCretetime(Long _abmCretetime) {
		firePropertyChange("abmCretetime", abmCretetime, _abmCretetime);
		abmCretetime = _abmCretetime;
	}
	public Long getAbmUpdatetime() {
		return abmUpdatetime;
	}
	public void setAbmUpdatetime(Long _abmUpdatetime) {
		firePropertyChange("abmUpdatetime", abmUpdatetime, _abmUpdatetime);
		abmUpdatetime = _abmUpdatetime;
	}
	public InputStream getAbmAccessory() {
		return abmAccessory;
	}
	public void setAbmAccessory(InputStream _abmAccessory) {
		firePropertyChange("abmAccessory", abmAccessory, _abmAccessory);
		abmAccessory = _abmAccessory;
	}
	protected void setupFields() throws DAOException {
		addField("abmId", "ABM_ID");
		addField("abmOwner", "ABM_OWNER");
		addField("abmCretetime", "ABM_CRETETIME");
		addField("abmUpdatetime", "ABM_UPDATETIME");
		addField("abmAccessory", "ABM_ACCESSORY");
		setTableName("ADDRESSBOOK_MANAGER");
		addKey("ABM_ID");
		setAutoIncremented("ABM_ID");
	}
	public AddressbookManagerDAO(Connection conn) {
		super(conn);
	}
	public AddressbookManagerDAO() {
		super();
	}
}
