/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.dao;

import java.io.InputStream;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DbfilePackageSearchDAO extends SearchDAO {

	private Integer fdbfId;
	private InputStream fdbfAccessory;
	private Integer fpId;
	private Integer fmId;
	private Integer folFpId;
	private String fpName;
	private String fpDesc;
	private Long fpCreatedate;
	private Long fpModifydate;
	private Integer fpSize;
	private String fpMimeType;
	private String fpIsfile;
	private String fpLevel;
	public Integer getFdbfId() {
		return fdbfId;
	}
	public void setFdbfId(Integer _fdbfId) {
		firePropertyChange("fdbfId", fdbfId, _fdbfId);
		fdbfId = _fdbfId;
	}
	public InputStream getFdbfAccessory() {
		return fdbfAccessory;
	}
	public void setFdbfAccessory(InputStream _fdbfAccessory) {
		firePropertyChange("fdbfAccessory", fdbfAccessory, _fdbfAccessory);
		fdbfAccessory = _fdbfAccessory;
	}
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
	public Integer getFpSize() {
		return fpSize;
	}
	public void setFpSize(Integer _fpSize) {
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
		addField("fdbfId", "FOLDER_DBFILE.FDBF_ID");
		addField("fdbfAccessory", "FOLDER_DBFILE.FDBF_ACCESSORY");
		addField("fpId", "FOLDER_PACKAGE.FP_ID");
		addField("fmId", "FOLDER_PACKAGE.FM_ID");
		addField("folFpId", "FOLDER_PACKAGE.FOL_FP_ID");
		addField("fpName", "FOLDER_PACKAGE.FP_NAME");
		addField("fpDesc", "FOLDER_PACKAGE.FP_DESC");
		addField("fpCreatedate", "FOLDER_PACKAGE.FP_CREATEDATE");
		addField("fpModifydate", "FOLDER_PACKAGE.FP_MODIFYDATE");
		addField("fpSize", "FOLDER_PACKAGE.FP_SIZE");
		addField("fpMimeType", "FOLDER_PACKAGE.FP_MIME_TYPE");
		addField("fpIsfile", "FOLDER_PACKAGE.FP_ISFILE");
		addField("fpLevel", "FOLDER_PACKAGE.FP_LEVEL");
	}
	String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public DbfilePackageSearchDAO() {
		super();
	}
}