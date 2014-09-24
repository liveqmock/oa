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
public class MsgSendDAO extends DAO {
	private Integer msMid;
	private Integer msId;
	private String msPersonuuid;
	private String msOrguuid;
	private Long msDate;
	private String msContent;
	public Integer getMsMid() {
		return msMid;
	}
	public void setMsMid(Integer _msMid) {
		firePropertyChange("msMid", msMid, _msMid);
		msMid = _msMid;
	}
	public Integer getMsId() {
		return msId;
	}
	public void setMsId(Integer _msId) {
		firePropertyChange("msId", msId, _msId);
		msId = _msId;
	}
	public String getMsPersonuuid() {
		return msPersonuuid;
	}
	public void setMsPersonuuid(String _msPersonuuid) {
		firePropertyChange("msPersonuuid", msPersonuuid, _msPersonuuid);
		msPersonuuid = _msPersonuuid;
	}
	public String getMsOrguuid() {
		return msOrguuid;
	}
	public void setMsOrguuid(String _msOrguuid) {
		firePropertyChange("msOrguuid", msOrguuid, _msOrguuid);
		msOrguuid = _msOrguuid;
	}
	public Long getMsDate() {
		return msDate;
	}
	public void setMsDate(Long _msDate) {
		firePropertyChange("msDate", msDate, _msDate);
		msDate = _msDate;
	}
	public String getMsContent() {
		return msContent;
	}
	public void setMsContent(String _msContent) {
		firePropertyChange("msContent", msContent, _msContent);
		msContent = _msContent;
	}
	protected void setupFields() throws DAOException {
		addField("msMid", "MS_MID");
		addField("msId", "MS_ID");
		addField("msPersonuuid", "MS_PERSONUUID");
		addField("msOrguuid", "MS_ORGUUID");
		addField("msDate", "MS_DATE");
		addField("msContent", "MS_CONTENT");
		setTableName("MSG_SEND");
		addKey("MS_MID");
		setAutoIncremented("MS_MID");
	}
	public MsgSendDAO(Connection conn) {
		super(conn);
	}
	public MsgSendDAO() {
		super();
	}
}
