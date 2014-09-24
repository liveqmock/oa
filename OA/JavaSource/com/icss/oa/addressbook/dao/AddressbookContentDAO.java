/*
 * Created on 2004-7-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookContentDAO extends DAO {

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
	protected void setupFields() throws DAOException {
		addField("abcId", "ABC_ID");
		addField("addAbfcId", "ADD_ABFC_ID");
		addField("abcName", "ABC_NAME");
		addField("abcCompanyaddress", "ABC_COMPANYADDRESS");
		addField("abcFamilyaddress", "ABC_FAMILYADDRESS");
		addField("abcCompanytel", "ABC_COMPANYTEL");
		addField("abcFamilytel", "ABC_FAMILYTEL");
		addField("abcCellphone", "ABC_CELLPHONE");
		addField("abcEmail", "ABC_EMAIL");
		addField("abcEmailsec", "ABC_EMAILSEC");
		addField("abcCompany", "ABC_COMPANY");
		addField("abcLever", "ABC_LEVER");
		addField("abcCreatetime", "ABC_CREATETIME");
		addField("abcUpdatetime", "ABC_UPDATETIME");
		addField("abcMemo", "ABC_MEMO");
		setTableName("ADDRESSBOOK_CONTENT");
		addKey("ABC_ID");
		setAutoIncremented("ABC_ID");
	}
	public AddressbookContentDAO(Connection conn) {
		super(conn);
	}
	public AddressbookContentDAO() {
		super();
	}
}
