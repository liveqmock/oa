/*
 * Created on 2004-7-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookAllSearchDAO extends SearchDAO {

	Integer abcId;
	Integer addAbfcId;
	String abcName;
	String abcCompanyaddress;
	String abcFamilyaddress;
	String abcCompanytel;
	String abcFamilytel;
	String abcCellphone;
	String abcEmail;
	String abcEmailsec;
	String abcCompany;
	String abcLever;
	Long abcCreatetime;
	Long abcUpdatetime;
	String abcMemo;
	Integer abfId;
	Integer addAbfId;
	Integer addAbmId;
	String abfName;
	String abfDescript;
	Long abfCreatetime;
	Long abfUpdatetime;
	String abfFlag;
	Integer abmId;
	String abmOwner;
	Long abmCretetime;
	Long abmUpdatetime;
	public Integer getAbcId() {
		return abcId;
	}
	public void setAbcId(Integer _abcId) {
		firePropertyChange("abcId", abcId, _abcId);
		abcId = _abcId;
	}
	public Integer getAddAbfcId() {
		return addAbfcId;
	}
	public void setAddAbfcId(Integer _addAbfcId) {
		firePropertyChange("addAbfcId", addAbfcId, _addAbfcId);
		addAbfcId = _addAbfcId;
	}
	public String getAbcName() {
		return abcName;
	}
	public void setAbcName(String _abcName) {
		firePropertyChange("abcName", abcName, _abcName);
		abcName = _abcName;
	}
	public String getAbcCompanyaddress() {
		return abcCompanyaddress;
	}
	public void setAbcCompanyaddress(String _abcCompanyaddress) {
		firePropertyChange(
			"abcCompanyaddress",
			abcCompanyaddress,
			_abcCompanyaddress);
		abcCompanyaddress = _abcCompanyaddress;
	}
	public String getAbcFamilyaddress() {
		return abcFamilyaddress;
	}
	public void setAbcFamilyaddress(String _abcFamilyaddress) {
		firePropertyChange(
			"abcFamilyaddress",
			abcFamilyaddress,
			_abcFamilyaddress);
		abcFamilyaddress = _abcFamilyaddress;
	}
	public String getAbcCompanytel() {
		return abcCompanytel;
	}
	public void setAbcCompanytel(String _abcCompanytel) {
		firePropertyChange("abcCompanytel", abcCompanytel, _abcCompanytel);
		abcCompanytel = _abcCompanytel;
	}
	public String getAbcFamilytel() {
		return abcFamilytel;
	}
	public void setAbcFamilytel(String _abcFamilytel) {
		firePropertyChange("abcFamilytel", abcFamilytel, _abcFamilytel);
		abcFamilytel = _abcFamilytel;
	}
	public String getAbcCellphone() {
		return abcCellphone;
	}
	public void setAbcCellphone(String _abcCellphone) {
		firePropertyChange("abcCellphone", abcCellphone, _abcCellphone);
		abcCellphone = _abcCellphone;
	}
	public String getAbcEmail() {
		return abcEmail;
	}
	public void setAbcEmail(String _abcEmail) {
		firePropertyChange("abcEmail", abcEmail, _abcEmail);
		abcEmail = _abcEmail;
	}
	public String getAbcEmailsec() {
		return abcEmailsec;
	}
	public void setAbcEmailsec(String _abcEmailsec) {
		firePropertyChange("abcEmailsec", abcEmailsec, _abcEmailsec);
		abcEmailsec = _abcEmailsec;
	}
	public String getAbcCompany() {
		return abcCompany;
	}
	public void setAbcCompany(String _abcCompany) {
		firePropertyChange("abcCompany", abcCompany, _abcCompany);
		abcCompany = _abcCompany;
	}
	public String getAbcLever() {
		return abcLever;
	}
	public void setAbcLever(String _abcLever) {
		firePropertyChange("abcLever", abcLever, _abcLever);
		abcLever = _abcLever;
	}
	public Long getAbcCreatetime() {
		return abcCreatetime;
	}
	public void setAbcCreatetime(Long _abcCreatetime) {
		firePropertyChange("abcCreatetime", abcCreatetime, _abcCreatetime);
		abcCreatetime = _abcCreatetime;
	}
	public Long getAbcUpdatetime() {
		return abcUpdatetime;
	}
	public void setAbcUpdatetime(Long _abcUpdatetime) {
		firePropertyChange("abcUpdatetime", abcUpdatetime, _abcUpdatetime);
		abcUpdatetime = _abcUpdatetime;
	}
	public String getAbcMemo() {
		return abcMemo;
	}
	public void setAbcMemo(String _abcMemo) {
		firePropertyChange("abcMemo", abcMemo, _abcMemo);
		abcMemo = _abcMemo;
	}
	public Integer getAbfId() {
		return abfId;
	}
	public void setAbfId(Integer _abfId) {
		firePropertyChange("abfId", abfId, _abfId);
		abfId = _abfId;
	}
	public Integer getAddAbfId() {
		return addAbfId;
	}
	public void setAddAbfId(Integer _addAbfId) {
		firePropertyChange("addAbfId", addAbfId, _addAbfId);
		addAbfId = _addAbfId;
	}
	public Integer getAddAbmId() {
		return addAbmId;
	}
	public void setAddAbmId(Integer _addAbmId) {
		firePropertyChange("addAbmId", addAbmId, _addAbmId);
		addAbmId = _addAbmId;
	}
	public String getAbfName() {
		return abfName;
	}
	public void setAbfName(String _abfName) {
		firePropertyChange("abfName", abfName, _abfName);
		abfName = _abfName;
	}
	public String getAbfDescript() {
		return abfDescript;
	}
	public void setAbfDescript(String _abfDescript) {
		firePropertyChange("abfDescript", abfDescript, _abfDescript);
		abfDescript = _abfDescript;
	}
	public Long getAbfCreatetime() {
		return abfCreatetime;
	}
	public void setAbfCreatetime(Long _abfCreatetime) {
		firePropertyChange("abfCreatetime", abfCreatetime, _abfCreatetime);
		abfCreatetime = _abfCreatetime;
	}
	public Long getAbfUpdatetime() {
		return abfUpdatetime;
	}
	public void setAbfUpdatetime(Long _abfUpdatetime) {
		firePropertyChange("abfUpdatetime", abfUpdatetime, _abfUpdatetime);
		abfUpdatetime = _abfUpdatetime;
	}
	public String getAbfFlag() {
		return abfFlag;
	}
	public void setAbfFlag(String _abfFlag) {
		firePropertyChange("abfFlag", abfFlag, _abfFlag);
		abfFlag = _abfFlag;
	}
	public Integer getAbmId() {
		return abmId;
	}
	public void setAbmId(Integer _abmId) {
		firePropertyChange("abmId", abmId, _abmId);
		abmId = _abmId;
	}
	public String getAbmOwner() {
		return abmOwner;
	}
	public void setAbmOwner(String _abmOwner) {
		firePropertyChange("abmOwner", abmOwner, _abmOwner);
		abmOwner = _abmOwner;
	}
	public Long getAbmCretetime() {
		return abmCretetime;
	}
	public void setAbmCretetime(Long _abmCretetime) {
		firePropertyChange("abmCretetime", abmCretetime, _abmCretetime);
		abmCretetime = _abmCretetime;
	}
	public Long getAbmUpdatetime() {
		return abmUpdatetime;
	}
	public void setAbmUpdatetime(Long _abmUpdatetime) {
		firePropertyChange("abmUpdatetime", abmUpdatetime, _abmUpdatetime);
		abmUpdatetime = _abmUpdatetime;
	}
	protected void setupFields() throws DAOException {
		addField("abcId", "ADDRESSBOOK_CONTENT.ABC_ID");
		addField("addAbfcId", "ADDRESSBOOK_CONTENT.ADD_ABFC_ID");
		addField("abcName", "ADDRESSBOOK_CONTENT.ABC_NAME");
		addField("abcCompanyaddress", "ADDRESSBOOK_CONTENT.ABC_COMPANYADDRESS");
		addField("abcFamilyaddress", "ADDRESSBOOK_CONTENT.ABC_FAMILYADDRESS");
		addField("abcCompanytel", "ADDRESSBOOK_CONTENT.ABC_COMPANYTEL");
		addField("abcFamilytel", "ADDRESSBOOK_CONTENT.ABC_FAMILYTEL");
		addField("abcCellphone", "ADDRESSBOOK_CONTENT.ABC_CELLPHONE");
		addField("abcEmail", "ADDRESSBOOK_CONTENT.ABC_EMAIL");
		addField("abcEmailsec", "ADDRESSBOOK_CONTENT.ABC_EMAILSEC");
		addField("abcCompany", "ADDRESSBOOK_CONTENT.ABC_COMPANY");
		addField("abcLever", "ADDRESSBOOK_CONTENT.ABC_LEVER");
		addField("abcCreatetime", "ADDRESSBOOK_CONTENT.ABC_CREATETIME");
		addField("abcUpdatetime", "ADDRESSBOOK_CONTENT.ABC_UPDATETIME");
		addField("abcMemo", "ADDRESSBOOK_CONTENT.ABC_MEMO");
		addField("abfId", "ADDRESSBOOK_FOLDER.ABF_ID");
		addField("addAbfId", "ADDRESSBOOK_FOLDER.ADD_ABF_ID");
		addField("addAbmId", "ADDRESSBOOK_FOLDER.ADD_ABM_ID");
		addField("abfName", "ADDRESSBOOK_FOLDER.ABF_NAME");
		addField("abfDescript", "ADDRESSBOOK_FOLDER.ABF_DESCRIPT");
		addField("abfCreatetime", "ADDRESSBOOK_FOLDER.ABF_CREATETIME");
		addField("abfUpdatetime", "ADDRESSBOOK_FOLDER.ABF_UPDATETIME");
		addField("abfFlag", "ADDRESSBOOK_FOLDER.ABF_FLAG");
		addField("abmId", "ADDRESSBOOK_MANAGER.ABM_ID");
		addField("abmOwner", "ADDRESSBOOK_MANAGER.ABM_OWNER");
		addField("abmCretetime", "ADDRESSBOOK_MANAGER.ABM_CRETETIME");
		addField("abmUpdatetime", "ADDRESSBOOK_MANAGER.ABM_UPDATETIME");
	}
	String sql = null;
	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}
	protected String getSearchSQL() {
		return sql;
	}
	public AddressbookAllSearchDAO() {
		super();
	}
}
