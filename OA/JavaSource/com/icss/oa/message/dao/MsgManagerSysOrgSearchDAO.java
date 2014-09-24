/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MsgManagerSysOrgSearchDAO extends SearchDAO {
	private Integer mmId;
	private String mmOrguuid;
	private String mmPersonuuid;
	private String cnname;
	private String deltag;
	public Integer getMmId() {
		return mmId;
	}
	public void setMmId(Integer _mmId) {
		firePropertyChange("mmId", mmId, _mmId);
		mmId = _mmId;
	}
	public String getMmOrguuid() {
		return mmOrguuid;
	}
	public void setMmOrguuid(String _mmOrguuid) {
		firePropertyChange("mmOrguuid", mmOrguuid, _mmOrguuid);
		mmOrguuid = _mmOrguuid;
	}
	public String getMmPersonuuid() {
		return mmPersonuuid;
	}
	public void setMmPersonuuid(String _mmPersonuuid) {
		firePropertyChange("mmPersonuuid", mmPersonuuid, _mmPersonuuid);
		mmPersonuuid = _mmPersonuuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String _deltag) {
		firePropertyChange("deltag", deltag, _deltag);
		deltag = _deltag;
	}
	protected void setupFields() throws DAOException {
		addField("mmId", "MSG_MANAGER.MM_ID");
		addField("mmOrguuid", "MSG_MANAGER.MM_ORGUUID");
		addField("mmPersonuuid", "MSG_MANAGER.MM_PERSONUUID");
		addField("cnname", "SYS_ORG.CNNAME");
		addField("deltag", "SYS_ORG.DELTAG");
	}
	private String sql;
	public void setSearchSQL(String _sql){
		sql=_sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public MsgManagerSysOrgSearchDAO() {
		super();
	}
}
