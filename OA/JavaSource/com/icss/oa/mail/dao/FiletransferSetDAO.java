/*
 * Created on 2004-7-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.mail.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FiletransferSetDAO extends DAO {

	private Integer id;
	private Integer fsSize;
	private Integer fsPer;
	private String fsRule;
	private String fsUid;
	private String fsUuid;
	private String fsDeltag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public Integer getFsSize() {
		return fsSize;
	}
	public void setFsSize(Integer _fsSize) {
		firePropertyChange("fsSize", fsSize, _fsSize);
		fsSize = _fsSize;
	}
	public Integer getFsPer() {
		return fsPer;
	}
	public void setFsPer(Integer _fsPer) {
		firePropertyChange("fsPer", fsPer, _fsPer);
		fsPer = _fsPer;
	}
	public String getFsRule() {
		return fsRule;
	}
	public void setFsRule(String _fsRule) {
		firePropertyChange("fsRule", fsRule, _fsRule);
		fsRule = _fsRule;
	}
	public String getFsUid() {
		return fsUid;
	}
	public void setFsUid(String _fsUid) {
		firePropertyChange("fsUid", fsUid, _fsUid);
		fsUid = _fsUid;
	}
	public String getFsUuid() {
		return fsUuid;
	}
	public void setFsUuid(String _fsUuid) {
		firePropertyChange("fsUuid", fsUuid, _fsUuid);
		fsUuid = _fsUuid;
	}
	public String getFsDeltag() {
		return fsDeltag;
	}
	public void setFsDeltag(String _fsDeltag) {
		firePropertyChange("fsDeltag", fsDeltag, _fsDeltag);
		fsDeltag = _fsDeltag;
	}
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("fsSize", "FS_SIZE");
		addField("fsPer", "FS_PER");
		addField("fsRule", "FS_RULE");
		addField("fsUid", "FS_UID");
		addField("fsUuid", "FS_UUID");
		addField("fsDeltag", "FS_DELTAG");
		setTableName("FILETRANSFER_SET");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public FiletransferSetDAO(Connection conn) {
		super(conn);
	}
	public FiletransferSetDAO() {
		super();
	}
}
