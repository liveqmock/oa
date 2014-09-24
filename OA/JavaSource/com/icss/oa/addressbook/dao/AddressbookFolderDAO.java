/*
 * Created on 2004-7-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookFolderDAO extends DAO {

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
	protected void setupFields() throws DAOException {
		addField("abfId", "ABF_ID");
		addField("addAbfId", "ADD_ABF_ID");
		addField("addAbmId", "ADD_ABM_ID");
		addField("abfName", "ABF_NAME");
		addField("abfDescript", "ABF_DESCRIPT");
		addField("abfCreatetime", "ABF_CREATETIME");
		addField("abfUpdatetime", "ABF_UPDATETIME");
		addField("abfFlag", "ABF_FLAG");
		setTableName("ADDRESSBOOK_FOLDER");
		addKey("ABF_ID");
		setAutoIncremented("ABF_ID");
	}
	public AddressbookFolderDAO(Connection conn) {
		super(conn);
	}
	public AddressbookFolderDAO() {
		super();
	}
}
