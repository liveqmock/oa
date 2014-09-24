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
public class MsgCodeDAO extends DAO {
	private String mcOrguuid;
	private Integer mcCode;
	public String getMcOrguuid() {
		return mcOrguuid;
	}
	public void setMcOrguuid(String _mcOrguuid) {
		firePropertyChange("mcOrguuid", mcOrguuid, _mcOrguuid);
		mcOrguuid = _mcOrguuid;
	}
	public Integer getMcCode() {
		return mcCode;
	}
	public void setMcCode(Integer _mcCode) {
		firePropertyChange("mcCode", mcCode, _mcCode);
		mcCode = _mcCode;
	}
	protected void setupFields() throws DAOException {
		addField("mcOrguuid", "MC_ORGUUID");
		addField("mcCode", "MC_CODE");
		setTableName("MSG_CODE");
		addKey("MC_ORGUUID");
		this.setAutoIncremented("MC_CODE");
	}
	public MsgCodeDAO(Connection conn) {
		super(conn);
	}
	public MsgCodeDAO() {
		super();
	}
}
