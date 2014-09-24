/*
 * Created on 2004-9-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysOrgDAO extends DAO {

	private String orguuid;

	private String cnname;

	private String enname;

	private String orgcode;

	private String contact;

	private String orggrade;

	private String orgprop;

	private Integer orglevel;

	private Integer serialindex;

	private String memo;

	private String parentorguuid;

	private Integer status;

	private String orglevelcode;

	private String deltag;

	private Integer sequenceno;

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

	public String getEnname() {
		return enname;
	}

	public void setEnname(String _enname) {
		firePropertyChange("enname", enname, _enname);
		enname = _enname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String _orgcode) {
		firePropertyChange("orgcode", orgcode, _orgcode);
		orgcode = _orgcode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String _contact) {
		firePropertyChange("contact", contact, _contact);
		contact = _contact;
	}

	public String getOrggrade() {
		return orggrade;
	}

	public void setOrggrade(String _orggrade) {
		firePropertyChange("orggrade", orggrade, _orggrade);
		orggrade = _orggrade;
	}

	public String getOrgprop() {
		return orgprop;
	}

	public void setOrgprop(String _orgprop) {
		firePropertyChange("orgprop", orgprop, _orgprop);
		orgprop = _orgprop;
	}

	public Integer getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(Integer _orglevel) {
		firePropertyChange("orglevel", orglevel, _orglevel);
		orglevel = _orglevel;
	}

	public Integer getSerialindex() {
		return serialindex;
	}

	public void setSerialindex(Integer _serialindex) {
		firePropertyChange("serialindex", serialindex, _serialindex);
		serialindex = _serialindex;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String _memo) {
		firePropertyChange("memo", memo, _memo);
		memo = _memo;
	}

	public String getParentorguuid() {
		return parentorguuid;
	}

	public void setParentorguuid(String _parentorguuid) {
		firePropertyChange("parentorguuid", parentorguuid, _parentorguuid);
		parentorguuid = _parentorguuid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer _status) {
		firePropertyChange("status", status, _status);
		status = _status;
	}

	public String getOrglevelcode() {
		return orglevelcode;
	}

	public void setOrglevelcode(String _orglevelcode) {
		firePropertyChange("orglevelcode", orglevelcode, _orglevelcode);
		orglevelcode = _orglevelcode;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String _deltag) {
		firePropertyChange("deltag", deltag, _deltag);
		deltag = _deltag;
	}

	public Integer getSequenceno() {
		return sequenceno;
	}

	public void setSequenceno(Integer _sequenceno) {
		firePropertyChange("sequenceno", sequenceno, _sequenceno);
		sequenceno = _sequenceno;
	}

	protected void setupFields() throws DAOException {
		addField("orguuid", "ORGUUID");
		addField("cnname", "CNNAME");
		addField("enname", "ENNAME");
		addField("orgcode", "ORGCODE");
		addField("contact", "CONTACT");
		addField("orggrade", "ORGGRADE");
		addField("orgprop", "ORGPROP");
		addField("orglevel", "ORGLEVEL");
		addField("serialindex", "SERIALINDEX");
		addField("memo", "MEMO");
		addField("parentorguuid", "PARENTORGUUID");
		addField("status", "STATUS");
		addField("orglevelcode", "ORGLEVELCODE");
		addField("deltag", "DELTAG");
		addField("sequenceno", "SEQUENCENO");
		setTableName("SYS_ORG");
	}

	public SysOrgDAO(Connection conn) {
		super(conn);
	}

	public SysOrgDAO() {
		super();
	}
}
