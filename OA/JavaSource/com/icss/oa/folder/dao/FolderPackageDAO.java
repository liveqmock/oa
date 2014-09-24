/*
 * Created on 2004-4-12
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
public class FolderPackageDAO extends DAO {

	Integer fpId;
	Integer fmId;
	Integer folFpId;
	String fpName;
	String fpDesc;
	Long fpCreatedate;
	Long fpModifydate;
	Long fpSize;
	String fpMimeType;
	String fpIsfile;
	String fpLevel;
	public Integer getFpId() {
		return fpId;
	}
	public void setFpId(Integer _fpId) {
		firePropertyChange("fpId", fpId, _fpId);
		fpId = _fpId;
	}
	public Integer getFmId() {
		return fmId;
	}
	public void setFmId(Integer _fmId) {
		firePropertyChange("fmId", fmId, _fmId);
		fmId = _fmId;
	}
	public Integer getFolFpId() {
		return folFpId;
	}
	public void setFolFpId(Integer _folFpId) {
		firePropertyChange("folFpId", folFpId, _folFpId);
		folFpId = _folFpId;
	}
	public String getFpName() {
		return fpName;
	}
	public void setFpName(String _fpName) {
		firePropertyChange("fpName", fpName, _fpName);
		fpName = _fpName;
	}
	public String getFpDesc() {
		return fpDesc;
	}
	public void setFpDesc(String _fpDesc) {
		firePropertyChange("fpDesc", fpDesc, _fpDesc);
		fpDesc = _fpDesc;
	}
	public Long getFpCreatedate() {
		return fpCreatedate;
	}
	public void setFpCreatedate(Long _fpCreatedate) {
		firePropertyChange("fpCreatedate", fpCreatedate, _fpCreatedate);
		fpCreatedate = _fpCreatedate;
	}
	public Long getFpModifydate() {
		return fpModifydate;
	}
	public void setFpModifydate(Long _fpModifydate) {
		firePropertyChange("fpModifydate", fpModifydate, _fpModifydate);
		fpModifydate = _fpModifydate;
	}
	public Long getFpSize() {
		return fpSize;
	}
	public void setFpSize(Long _fpSize) {
		firePropertyChange("fpSize", fpSize, _fpSize);
		fpSize = _fpSize;
	}
	public String getFpMimeType() {
		return fpMimeType;
	}
	public void setFpMimeType(String _fpMimeType) {
		firePropertyChange("fpMimeType", fpMimeType, _fpMimeType);
		fpMimeType = _fpMimeType;
	}
	public String getFpIsfile() {
		return fpIsfile;
	}
	public void setFpIsfile(String _fpIsfile) {
		firePropertyChange("fpIsfile", fpIsfile, _fpIsfile);
		fpIsfile = _fpIsfile;
	}
	public String getFpLevel() {
		return fpLevel;
	}
	public void setFpLevel(String _fpLevel) {
		firePropertyChange("fpLevel", fpLevel, _fpLevel);
		fpLevel = _fpLevel;
	}
	protected void setupFields() throws DAOException {
		addField("fpId", "FP_ID");
		addField("fmId", "FM_ID");
		addField("folFpId", "FOL_FP_ID");
		addField("fpName", "FP_NAME");
		addField("fpDesc", "FP_DESC");
		addField("fpCreatedate", "FP_CREATEDATE");
		addField("fpModifydate", "FP_MODIFYDATE");
		addField("fpSize", "FP_SIZE");
		addField("fpMimeType", "FP_MIME_TYPE");
		addField("fpIsfile", "FP_ISFILE");
		addField("fpLevel", "FP_LEVEL");
		setTableName("FOLDER_PACKAGE");
		addKey("FP_ID");
		setAutoIncremented("FP_ID");
	}
	public FolderPackageDAO(Connection conn) {
		super(conn);
	}
	public FolderPackageDAO() {
		super();
	}
}
