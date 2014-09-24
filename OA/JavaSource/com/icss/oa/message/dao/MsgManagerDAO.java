/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MsgManagerDAO extends DAO {
	private Integer mmId;
	private String mmOrguuid;
	private String mmPersonuuid;
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
	protected void setupFields() throws DAOException {
		addField("mmId", "MM_ID");
		addField("mmOrguuid", "MM_ORGUUID");
		addField("mmPersonuuid", "MM_PERSONUUID");
		setTableName("MSG_MANAGER");
		this.setAutoIncremented("MM_ID");
	}
	public MsgManagerDAO(Connection conn) {
		super(conn);
	}
	public MsgManagerDAO() {
		super();
	}
}
