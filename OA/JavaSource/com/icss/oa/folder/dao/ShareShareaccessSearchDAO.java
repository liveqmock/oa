/*
 * Created on 2004-4-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShareShareaccessSearchDAO extends SearchDAO {

	private Integer fmId;
	private Integer fpId;
	private String fsName;
	private Long fsDate;
	private Integer fscId;
	private Integer fsId;
	private String fscPersonid;
	private String fscAccessright;
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
		addField("fmId", "FOLDER_SHARE.FM_ID");
		addField("fpId", "FOLDER_SHARE.FP_ID");
		addField("fsName", "FOLDER_SHARE.FS_NAME");
		addField("fsDate", "FOLDER_SHARE.FS_DATE");
		addField("fscId", "FOLDER_SHAREACCESS.FSC_ID");
		addField("fsId", "FOLDER_SHAREACCESS.FS_ID");
		addField("fscPersonid", "FOLDER_SHAREACCESS.FSC_PERSONID");
		addField("fscAccessright", "FOLDER_SHAREACCESS.FSC_ACCESSRIGHT");
	}
	String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public ShareShareaccessSearchDAO() {
		super();
	}
}
