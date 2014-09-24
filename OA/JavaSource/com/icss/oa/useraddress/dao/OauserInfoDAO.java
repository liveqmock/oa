/*
 * Created on 2004-7-8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.useraddress.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OauserInfoDAO extends DAO {

	Integer odId;
	String odUsrname;
	String odDept;
	String odOffice;
	String odBuss;
	String odWord;
	String odRoomnum;
	String odTel;
	String odMachcircs;
	String odNetcircs;
	String odSys;
	String odMachid;
	String odRoomnod;
	String odIp;
	String odIpsec;
	String odMacnum;
	String odMacnumsec;
	String odMachname;
	String odOnflag;
	String odOpesys;
	String odMemo;
	public Integer getOdId() {
		return odId;
	}
	public void setOdId(Integer _odId) {
		firePropertyChange("odId", odId, _odId);
		odId = _odId;
	}
	public String getOdUsrname() {
		return odUsrname;
	}
	public void setOdUsrname(String _odUsrname) {
		firePropertyChange("odUsrname", odUsrname, _odUsrname);
		odUsrname = _odUsrname;
	}
	public String getOdDept() {
		return odDept;
	}
	public void setOdDept(String _odDept) {
		firePropertyChange("odDept", odDept, _odDept);
		odDept = _odDept;
	}
	public String getOdOffice() {
		return odOffice;
	}
	public void setOdOffice(String _odOffice) {
		firePropertyChange("odOffice", odOffice, _odOffice);
		odOffice = _odOffice;
	}
	public String getOdBuss() {
		return odBuss;
	}
	public void setOdBuss(String _odBuss) {
		firePropertyChange("odBuss", odBuss, _odBuss);
		odBuss = _odBuss;
	}
	public String getOdWord() {
		return odWord;
	}
	public void setOdWord(String _odWord) {
		firePropertyChange("odWord", odWord, _odWord);
		odWord = _odWord;
	}
	public String getOdRoomnum() {
		return odRoomnum;
	}
	public void setOdRoomnum(String _odRoomnum) {
		firePropertyChange("odRoomnum", odRoomnum, _odRoomnum);
		odRoomnum = _odRoomnum;
	}
	public String getOdTel() {
		return odTel;
	}
	public void setOdTel(String _odTel) {
		firePropertyChange("odTel", odTel, _odTel);
		odTel = _odTel;
	}
	public String getOdMachcircs() {
		return odMachcircs;
	}
	public void setOdMachcircs(String _odMachcircs) {
		firePropertyChange("odMachcircs", odMachcircs, _odMachcircs);
		odMachcircs = _odMachcircs;
	}
	public String getOdNetcircs() {
		return odNetcircs;
	}
	public void setOdNetcircs(String _odNetcircs) {
		firePropertyChange("odNetcircs", odNetcircs, _odNetcircs);
		odNetcircs = _odNetcircs;
	}
	public String getOdSys() {
		return odSys;
	}
	public void setOdSys(String _odSys) {
		firePropertyChange("odSys", odSys, _odSys);
		odSys = _odSys;
	}
	public String getOdMachid() {
		return odMachid;
	}
	public void setOdMachid(String _odMachid) {
		firePropertyChange("odMachid", odMachid, _odMachid);
		odMachid = _odMachid;
	}
	public String getOdRoomnod() {
		return odRoomnod;
	}
	public void setOdRoomnod(String _odRoomnod) {
		firePropertyChange("odRoomnod", odRoomnod, _odRoomnod);
		odRoomnod = _odRoomnod;
	}
	public String getOdIp() {
		return odIp;
	}
	public void setOdIp(String _odIp) {
		firePropertyChange("odIp", odIp, _odIp);
		odIp = _odIp;
	}
	public String getOdIpsec() {
		return odIpsec;
	}
	public void setOdIpsec(String _odIpsec) {
		firePropertyChange("odIpsec", odIpsec, _odIpsec);
		odIpsec = _odIpsec;
	}
	public String getOdMacnum() {
		return odMacnum;
	}
	public void setOdMacnum(String _odMacnum) {
		firePropertyChange("odMacnum", odMacnum, _odMacnum);
		odMacnum = _odMacnum;
	}
	public String getOdMacnumsec() {
		return odMacnumsec;
	}
	public void setOdMacnumsec(String _odMacnumsec) {
		firePropertyChange("odMacnumsec", odMacnumsec, _odMacnumsec);
		odMacnumsec = _odMacnumsec;
	}
	public String getOdMachname() {
		return odMachname;
	}
	public void setOdMachname(String _odMachname) {
		firePropertyChange("odMachname", odMachname, _odMachname);
		odMachname = _odMachname;
	}
	public String getOdOnflag() {
		return odOnflag;
	}
	public void setOdOnflag(String _odOnflag) {
		firePropertyChange("odOnflag", odOnflag, _odOnflag);
		odOnflag = _odOnflag;
	}
	public String getOdOpesys() {
		return odOpesys;
	}
	public void setOdOpesys(String _odOpesys) {
		firePropertyChange("odOpesys", odOpesys, _odOpesys);
		odOpesys = _odOpesys;
	}
	public String getOdMemo() {
		return odMemo;
	}
	public void setOdMemo(String _odMemo) {
		firePropertyChange("odMemo", odMemo, _odMemo);
		odMemo = _odMemo;
	}
	protected void setupFields() throws DAOException {
		addField("odId", "OD_ID");
		addField("odUsrname", "OD_USRNAME");
		addField("odDept", "OD_DEPT");
		addField("odOffice", "OD_OFFICE");
		addField("odBuss", "OD_BUSS");
		addField("odWord", "OD_WORD");
		addField("odRoomnum", "OD_ROOMNUM");
		addField("odTel", "OD_TEL");
		addField("odMachcircs", "OD_MACHCIRCS");
		addField("odNetcircs", "OD_NETCIRCS");
		addField("odSys", "OD_SYS");
		addField("odMachid", "OD_MACHID");
		addField("odRoomnod", "OD_ROOMNOD");
		addField("odIp", "OD_IP");
		addField("odIpsec", "OD_IPSEC");
		addField("odMacnum", "OD_MACNUM");
		addField("odMacnumsec", "OD_MACNUMSEC");
		addField("odMachname", "OD_MACHNAME");
		addField("odOnflag", "OD_ONFLAG");
		addField("odOpesys", "OD_OPESYS");
		addField("odMemo", "OD_MEMO");
		setTableName("OAADD_LIST");
		addKey("OD_ID");
	}
	public OauserInfoDAO(Connection conn) {
		super(conn);
	}
	public OauserInfoDAO() {
		super();
	}
}
