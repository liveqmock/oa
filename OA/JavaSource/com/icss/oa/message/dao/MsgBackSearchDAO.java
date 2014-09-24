/*
 * Created on 2004-9-20
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
public class MsgBackSearchDAO extends SearchDAO {
	private Integer mbId;
	private Integer msMid;
	private String msPersonuuid;
	private String msPhone;
	private String msContent;
	private Integer msMode;
	private Long msDate;
	private String msPower;
	private String cnname;
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
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	protected void setupFields() throws DAOException {
		addField("mbId", "MBID");
		addField("msMid", "MSMID");
		addField("msPersonuuid", "MSPERSONUUID");
		addField("msPhone", "MSPHONE");
		addField("msContent", "MSCONTENT");
		addField("msMode", "MSMODE");
		addField("msDate", "MSDATE");
		addField("msPower", "MSPOWER");
		addField("cnname", "SYS_PERSON.CNNAME");
	}
	private String sql;
	public void setSearchSQL(String _sql){
		sql=_sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public MsgBackSearchDAO() {
		super();
	}
}
