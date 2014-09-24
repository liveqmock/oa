/*
 * Created on 2004-7-27
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookSearchDAO extends SearchDAO {

	Integer abfId;
	Integer addAbfId;
	Integer addAbmId;
	String abfName;
	String abfDescript;
	Long abfCreatetime;
	Long abfUpdatetime;
	String abfFlag;
	Integer abmId;
	String abmOwner;
	Long abmCretetime;
	Long abmUpdatetime;
	public Integer getAbfId() {
		return abfId;
	}
	public void setAbfId(Integer _abfId) {
		firePropertyChange("abfId", abfId, _abfId);
		abfId = _abfId;
	}
	public Integer getAddAbfId() {
		return addAbfId;
	}
	public void setAddAbfId(Integer _addAbfId) {
		firePropertyChange("addAbfId", addAbfId, _addAbfId);
		addAbfId = _addAbfId;
	}
	public Integer getAddAbmId() {
		return addAbmId;
	}
	public void setAddAbmId(Integer _addAbmId) {
		firePropertyChange("addAbmId", addAbmId, _addAbmId);
		addAbmId = _addAbmId;
	}
	public String getAbfName() {
		return abfName;
	}
	public void setAbfName(String _abfName) {
		firePropertyChange("abfName", abfName, _abfName);
		abfName = _abfName;
	}
	public String getAbfDescript() {
		return abfDescript;
	}
	public void setAbfDescript(String _abfDescript) {
		firePropertyChange("abfDescript", abfDescript, _abfDescript);
		abfDescript = _abfDescript;
	}
	public Long getAbfCreatetime() {
		return abfCreatetime;
	}
	public void setAbfCreatetime(Long _abfCreatetime) {
		firePropertyChange("abfCreatetime", abfCreatetime, _abfCreatetime);
		abfCreatetime = _abfCreatetime;
	}
	public Long getAbfUpdatetime() {
		return abfUpdatetime;
	}
	public void setAbfUpdatetime(Long _abfUpdatetime) {
		firePropertyChange("abfUpdatetime", abfUpdatetime, _abfUpdatetime);
		abfUpdatetime = _abfUpdatetime;
	}
	public String getAbfFlag() {
		return abfFlag;
	}
	public void setAbfFlag(String _abfFlag) {
		firePropertyChange("abfFlag", abfFlag, _abfFlag);
		abfFlag = _abfFlag;
	}
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
	protected void setupFields() throws DAOException {
		addField("abfId", "ADDRESSBOOK_FOLDER.ABF_ID");
		addField("addAbfId", "ADDRESSBOOK_FOLDER.ADD_ABF_ID");
		addField("addAbmId", "ADDRESSBOOK_FOLDER.ADD_ABM_ID");
		addField("abfName", "ADDRESSBOOK_FOLDER.ABF_NAME");
		addField("abfDescript", "ADDRESSBOOK_FOLDER.ABF_DESCRIPT");
		addField("abfCreatetime", "ADDRESSBOOK_FOLDER.ABF_CREATETIME");
		addField("abfUpdatetime", "ADDRESSBOOK_FOLDER.ABF_UPDATETIME");
		addField("abfFlag", "ADDRESSBOOK_FOLDER.ABF_FLAG");
		addField("abmId", "ADDRESSBOOK_MANAGER.ABM_ID");
		addField("abmOwner", "ADDRESSBOOK_MANAGER.ABM_OWNER");
		addField("abmCretetime", "ADDRESSBOOK_MANAGER.ABM_CRETETIME");
		addField("abmUpdatetime", "ADDRESSBOOK_MANAGER.ABM_UPDATETIME");
	}
	String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public AddressbookSearchDAO() {
		super();
	}
}
