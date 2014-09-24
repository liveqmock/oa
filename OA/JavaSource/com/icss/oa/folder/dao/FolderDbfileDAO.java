/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.dao;

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
public class FolderDbfileDAO extends DAO {

	private Integer fdbfId;
	private Integer fpId;
	private InputStream fdbfAccessory;
	public Integer getFdbfId() {
		return fdbfId;
	}
	public void setFdbfId(Integer _fdbfId) {
		firePropertyChange("fdbfId", fdbfId, _fdbfId);
		fdbfId = _fdbfId;
	}
	public Integer getFpId() {
		return fpId;
	}
	public void setFpId(Integer _fpId) {
		firePropertyChange("fpId", fpId, _fpId);
		fpId = _fpId;
	}
	public InputStream getFdbfAccessory() {
		return fdbfAccessory;
	}
	public void setFdbfAccessory(InputStream _fdbfAccessory) {
		firePropertyChange("fdbfAccessory", fdbfAccessory, _fdbfAccessory);
		fdbfAccessory = _fdbfAccessory;
	}
	protected void setupFields() throws DAOException {
		addField("fdbfId", "FDBF_ID");
		addField("fpId", "FP_ID");
		addField("fdbfAccessory", "FDBF_ACCESSORY");
		setTableName("FOLDER_DBFILE");
		addKey("FDBF_ID");
		setAutoIncremented("FDBF_ID");
	}
	public FolderDbfileDAO(Connection conn) {
		super(conn);
	}
	public FolderDbfileDAO() {
		super();
	}
}
