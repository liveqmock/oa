package com.icss.oa.sync.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

public class PersonTempSearchDAO extends SearchDAO {
	Integer id;
	String operateid;
	String hrid;
	String personname;
	String orgid;
	String deptid;
	String userid;
	String operatetype;
	Integer isuniq;
	Integer isright;
	Integer approved;
	Integer tohr;
	Integer totq;
	Integer tomail;
	String origin;
	String purpose;
	String describe;
	String alterfield;
	String mobilephone;
	String serialindex;
	String time;
	String sql;

	 public void setId(Integer _id){ 
	        firePropertyChange("id",id,_id);
	        id = _id;
		}

	    public Integer getId(){
	        return id;
		}
	    
	public String getOperateid() {
		return operateid;
	}

	public void setOperateid(String _operateid) {
		firePropertyChange("operateid", operateid, _operateid);
		operateid = _operateid;
	}

	public String getHrid() {
		return hrid;
	}

	public void setHrid(String _hrid) {
		firePropertyChange("hrid", hrid, _hrid);
		hrid = _hrid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		personname = _personname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String _orgid) {
		firePropertyChange("orgid", orgid, _orgid);
		orgid = _orgid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String _deptid) {
		firePropertyChange("deptid", deptid, _deptid);
		deptid = _deptid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public String getOperatetype() {
		return operatetype;
	}

	public void setOperatetype(String _operatetype) {
		firePropertyChange("operatetype", operatetype, _operatetype);
		operatetype = _operatetype;
	}

	public Integer getIsuniq() {
		return isuniq;
	}

	public void setIsuniq(Integer _isuniq) {
		firePropertyChange("isuniq", isuniq, _isuniq);
		isuniq = _isuniq;
	}

	public Integer getIsright() {
		return isright;
	}

	public void setIsright(Integer _isright) {
		firePropertyChange("isright", isright, _isright);
		isright = _isright;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer _approved) {
		firePropertyChange("approved", approved, _approved);
		approved = _approved;
	}

	public Integer getTohr() {
		return tohr;
	}

	public void setTohr(Integer _tohr) {
		firePropertyChange("tohr", tohr, _tohr);
		tohr = _tohr;
	}

	public Integer getTotq() {
		return totq;
	}

	public void setTotq(Integer _totq) {
		firePropertyChange("totq", totq, _totq);
		totq = _totq;
	}

	public Integer getTomail() {
		return tomail;
	}

	public void setTomail(Integer _tomail) {
		firePropertyChange("tomail", tomail, _tomail);
		tomail = _tomail;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String _origin) {
		firePropertyChange("origin", origin, _origin);
		origin = _origin;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String _purpose) {
		firePropertyChange("purpose", purpose, _purpose);
		purpose = _purpose;

	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String _describe) {
		firePropertyChange("describe", describe, _describe);
		describe = _describe;

	}

	public String getAlterfield() {
		return alterfield;
	}

	public void setAlterfield(String _alterfield) {
		firePropertyChange("alterfield", alterfield, _alterfield);
		alterfield = _alterfield;

	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String _mobilephone) {
		firePropertyChange("mobilephone", mobilephone, _mobilephone);
		mobilephone = _mobilephone;

	}

	public String getSerialindex() {
		return serialindex;
	}

	public void setSerialindex(String _serialindex) {
		firePropertyChange("serialindex", serialindex, _serialindex);
		serialindex = _serialindex;

	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String _time) {
		firePropertyChange("time", time, _time);
		time = _time;

	}
	protected void setupFields() throws DAOException {
        addField("id","ID");
		addField("operateid", "OPERATEID");
		addField("hrid", "HRID");
		addField("personname", "PERSONNAME");
		addField("orgid", "ORGID");
		addField("deptid", "DEPTID");
		addField("userid", "USERID");
		addField("operatetype", "OPERATETYPE");
		addField("isuniq", "ISUNIQ");
		addField("isright", "ISRIGHT");
		addField("approved", "APPROVED");
		addField("tohr", "TOHR");
		addField("totq", "TOTQ");
		addField("tomail", "TOMAIL");
		addField("describe", "DESCRIBE");
		addField("origin", "ORIGIN");
		addField("purpose", "PURPOSE");
		addField("alterfield", "ALTERFIELD");
		addField("mobilephone", "MOBILEPHONE");
		addField("serialindex", "SERIALINDEX");
		addField("time", "TIME");

		
		setTableName("PERSON_TEMP");
		addKey("ID");
		

        this.setAutoIncremented("ID");

	}

	public String getSearchSQL() {
		return sql;
	}

	public void setSearchSQL(String _sql) {
		this.sql = _sql;
	}

	public PersonTempSearchDAO() {
		super();
	}

}
