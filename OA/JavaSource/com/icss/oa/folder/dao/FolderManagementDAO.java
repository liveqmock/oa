/*
 * Created on 2004-4-20
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
public class FolderManagementDAO extends DAO {

	private Integer fmId;
	private String fmPersonid;
	private String fmStoretype;
	private String fmStorepath;
	private Long fmCreatedate;
	private Long fmModifydate;
	public Integer getFmId() {
		return fmId;
	}
	public void setFmId(Integer _fmId) {
		firePropertyChange("fmId", fmId, _fmId);
		fmId = _fmId;
	}
	public String getFmPersonid() {
		return fmPersonid;
	}
	public void setFmPersonid(String _fmPersonid) {
		firePropertyChange("fmPersonid", fmPersonid, _fmPersonid);
		fmPersonid = _fmPersonid;
	}
	public String getFmStoretype() {
		return fmStoretype;
	}
	public void setFmStoretype(String _fmStoretype) {
		firePropertyChange("fmStoretype", fmStoretype, _fmStoretype);
		fmStoretype = _fmStoretype;
	}
	public String getFmStorepath() {
		return fmStorepath;
	}
	public void setFmStorepath(String _fmStorepath) {
		firePropertyChange("fmStorepath", fmStorepath, _fmStorepath);
		fmStorepath = _fmStorepath;
	}
	public Long getFmCreatedate() {
		return fmCreatedate;
	}
	public void setFmCreatedate(Long _fmCreatedate) {
		firePropertyChange("fmCreatedate", fmCreatedate, _fmCreatedate);
		fmCreatedate = _fmCreatedate;
	}
	public Long getFmModifydate() {
		return fmModifydate;
	}
	public void setFmModifydate(Long _fmModifydate) {
		firePropertyChange("fmModifydate", fmModifydate, _fmModifydate);
		fmModifydate = _fmModifydate;
	}
	protected void setupFields() throws DAOException {
		addField("fmId", "FM_ID");
		addField("fmPersonid", "FM_PERSONID");
		addField("fmStoretype", "FM_STORETYPE");
		addField("fmStorepath", "FM_STOREPATH");
		addField("fmCreatedate", "FM_CREATEDATE");
		addField("fmModifydate", "FM_MODIFYDATE");
		setTableName("FOLDER_MANAGEMENT");
		addKey("FM_ID");
		setAutoIncremented("FM_ID");
	}
	public FolderManagementDAO(Connection conn) {
		super(conn);
	}
	public FolderManagementDAO() {
		super();
	}
}
