/*
 * Created on 2004-5-19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.allrecord.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DXHistoryOrgPersonSearchDAO extends SearchDAO {
	Integer shId;
	String senderId;
	String shContent;
	Long shDate;
	String depId;
	String receiverId;
	String senderCnname;
	String orguuid;
	String cnname;
	String personuuid;
	String mobile;
	public Integer getShId() {
		return shId;
	}
	public void setShId(Integer _shId) {
		firePropertyChange("shId", shId, _shId);
		shId = _shId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String _senderId) {
		firePropertyChange("senderId", senderId, _senderId);
		senderId = _senderId;
	}
	public String getShContent() {
		return shContent;
	}
	public void setShContent(String _shContent) {
		firePropertyChange("shContent", shContent, _shContent);
		shContent = _shContent;
	}
	public Long getShDate() {
		return shDate;
	}
	public void setShDate(Long _shDate) {
		firePropertyChange("shDate", shDate, _shDate);
		shDate = _shDate;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String _depId) {
		firePropertyChange("depId", depId, _depId);
		depId = _depId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String _receiverId) {
		firePropertyChange("receiverId", receiverId, _receiverId);
		receiverId = _receiverId;
	}
	public String getSenderCnname() {
		return senderCnname;
	}
	public void setSenderCnname(String _senderCnname) {
		firePropertyChange("senderCnname", senderCnname, _senderCnname);
		senderCnname = _senderCnname;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String _mobile) {
		firePropertyChange("mobile", mobile, _mobile);
		mobile = _mobile;
	}
	protected void setupFields() throws DAOException {
		addField("shId", "DUANXIN_HISTORY.SH_ID");
		addField("senderId", "DUANXIN_HISTORY.SENDER_ID");
		addField("shContent", "DUANXIN_HISTORY.SH_CONTENT");
		addField("shDate", "DUANXIN_HISTORY.SH_DATE");
		addField("depId", "DUANXIN_HISTORY.DEP_ID");
		addField("receiverId", "DUANXIN_HISTORY.RECEIVER_ID");
		addField("senderCnname", "DUANXIN_HISTORY.SENDER_CNNAME");
		addField("orguuid", "SYS_ORG.ORGUUID");
		addField("cnname", "SYS_ORG.CNNAME");
		addField("personuuid", "SYS_PERSON.PERSONUUID");
		addField("mobile", "SYS_PERSON.MOBILE");
	}
	protected String getSearchSQL() {
		return sql;
	}
	public DXHistoryOrgPersonSearchDAO() {
		super();
	}
	public void setSearchDaoSql(String sql){
		this.sql=sql;
	}
	private String sql;
}
