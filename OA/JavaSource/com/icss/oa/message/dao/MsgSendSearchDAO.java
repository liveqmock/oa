/*
 * Created on 2004-9-18
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
public class MsgSendSearchDAO extends SearchDAO {
	private String cnname;
	private String ocnname;
	private Integer msMid;
	private Integer msId;
	private String msPersonuuid;
	private String msOrguuid;
	private Long msDate;
	private String msContent;
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	public String getOcnname() {
		return ocnname;
	}
	public void setOcnname(String _ocnname) {
		firePropertyChange("ocnname", ocnname, _ocnname);
		ocnname = _ocnname;
	}
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
		addField("cnname", "PCNNAME");
		addField("ocnname", "OCNNAME");
		addField("msMid", "MSMID");
		addField("msId", "MSID");
		addField("msPersonuuid", "MSPERSONUUID");
		addField("msOrguuid", "MSORGUUID");
		addField("msDate", "MSDATE");
		addField("msContent", "MSCONTENT");
	}
	private String sql;
	public void SetSearvhSQL(String _sql){
		sql=_sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public MsgSendSearchDAO() {
		super();
	}
}
