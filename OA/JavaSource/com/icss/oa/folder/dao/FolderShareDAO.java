/*
 * Created on 2004-4-22
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
public class FolderShareDAO extends DAO {

	private Integer fsId;
	private Integer fmId;
	private Integer fpId;
	private String fsName;
	private Long fsDate;
	public Integer getFsId() {
		return fsId;
	}
	public void setFsId(Integer _fsId) {
		firePropertyChange("fsId", fsId, _fsId);
		fsId = _fsId;
	}
	public Integer getFmId() {
		return fmId;
	}
	public void setFmId(Integer _fmId) {
		firePropertyChange("fmId", fmId, _fmId);
		fmId = _fmId;
	}
	public Integer getFpId() {
		return fpId;
	}
	public void setFpId(Integer _fpId) {
		firePropertyChange("fpId", fpId, _fpId);
		fpId = _fpId;
	}
	
	public String getFsName() {
		return fsName;
	}
	public void setFsName(String _fsName) {
		firePropertyChange("fsName", fsName, _fsName);
		fsName = _fsName;
	}
	public Long getFsDate() {
		return fsDate;
	}
	public void setFsDate(Long _fsDate) {
		firePropertyChange("fsDate", fsDate, _fsDate);
		fsDate = _fsDate;
	}
	protected void setupFields() throws DAOException {
		addField("fsId", "FS_ID");
		addField("fmId", "FM_ID");
		addField("fpId", "FP_ID");
		addField("fsName", "FS_NAME");
		addField("fsDate", "FS_DATE");
		setTableName("FOLDER_SHARE");
		addKey("FS_ID");
		setAutoIncremented("FS_ID");
	}
	public FolderShareDAO(Connection conn) {
		super(conn);
	}
	public FolderShareDAO() {
		super();
	}
}
