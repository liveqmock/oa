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
public class MsgBackDAO extends DAO {
	private Integer mbId;
	private Integer msMid;
	private String msPersonuuid;
	private String msPhone;
	private String msContent;
	private Integer msMode;
	private Long msDate;
	private String msPower;
	public Integer getMbId() {
		return mbId;
	}
	public void setMbId(Integer _mbId) {
		firePropertyChange("mbId", mbId, _mbId);
		mbId = _mbId;
	}
	public Integer getMsMid() {
		return msMid;
	}
	public void setMsMid(Integer _msMid) {
		firePropertyChange("msMid", msMid, _msMid);
		msMid = _msMid;
	}
	public String getMsPersonuuid() {
		return msPersonuuid;
	}
	public void setMsPersonuuid(String _msPersonuuid) {
		firePropertyChange("msPersonuuid", msPersonuuid, _msPersonuuid);
		msPersonuuid = _msPersonuuid;
	}
	public String getMsPhone() {
		return msPhone;
	}
	public void setMsPhone(String _msPhone) {
		firePropertyChange("msPhone", msPhone, _msPhone);
		msPhone = _msPhone;
	}
	public String getMsContent() {
		return msContent;
	}
	public void setMsContent(String _msContent) {
		firePropertyChange("msContent", msContent, _msContent);
		msContent = _msContent;
	}
	public Integer getMsMode() {
		return msMode;
	}
	public void setMsMode(Integer _msMode) {
		firePropertyChange("msMode", msMode, _msMode);
		msMode = _msMode;
	}
	public Long getMsDate() {
		return msDate;
	}
	public void setMsDate(Long _msDate) {
		firePropertyChange("msDate", msDate, _msDate);
		msDate = _msDate;
	}
	public String getMsPower() {
		return msPower;
	}
	public void setMsPower(String _msPower) {
		firePropertyChange("msPower", msPower, _msPower);
		msPower = _msPower;
	}
	protected void setupFields() throws DAOException {
		addField("mbId", "MB_ID");
		addField("msMid", "MS_MID");
		addField("msPersonuuid", "MS_PERSONUUID");
		addField("msPhone", "MS_PHONE");
		addField("msContent", "MS_CONTENT");
		addField("msMode", "MS_MODE");
		addField("msDate", "MS_DATE");
		addField("msPower", "MS_POWER");
		setTableName("MSG_BACK");
		addKey("MB_ID");
		setAutoIncremented("MB_ID");
	}
	public MsgBackDAO(Connection conn) {
		super(conn);
	}
	public MsgBackDAO() {
		super();
	}
}
