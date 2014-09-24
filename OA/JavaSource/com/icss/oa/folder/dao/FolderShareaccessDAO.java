/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FolderShareaccessDAO extends DAO {

	private Integer fscId;
	private Integer fsId;
	private String fscPersonid;
	private String fscAccessright;
	public Integer getFscId() {
		return fscId;
	}
	public void setFscId(Integer _fscId) {
		firePropertyChange("fscId", fscId, _fscId);
		fscId = _fscId;
	}
	public Integer getFsId() {
		return fsId;
	}
	public void setFsId(Integer _fsId) {
		firePropertyChange("fsId", fsId, _fsId);
		fsId = _fsId;
	}
	public String getFscPersonid() {
		return fscPersonid;
	}
	public void setFscPersonid(String _fscPersonid) {
		firePropertyChange("fscPersonid", fscPersonid, _fscPersonid);
		fscPersonid = _fscPersonid;
	}
	public String getFscAccessright() {
		return fscAccessright;
	}
	public void setFscAccessright(String _fscAccessright) {
		firePropertyChange("fscAccessright", fscAccessright, _fscAccessright);
		fscAccessright = _fscAccessright;
	}
	protected void setupFields() throws DAOException {
		addField("fscId", "FSC_ID");
		addField("fsId", "FS_ID");
		addField("fscPersonid", "FSC_PERSONID");
		addField("fscAccessright", "FSC_ACCESSRIGHT");
		setTableName("FOLDER_SHAREACCESS");
		addKey("FSC_ID");
		setAutoIncremented("FSC_ID");
	}
	public FolderShareaccessDAO(Connection conn) {
		super(conn);
	}
	public FolderShareaccessDAO() {
		super();
	}
}
