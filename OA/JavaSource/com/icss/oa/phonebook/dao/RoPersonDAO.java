/*
 * Created on 2004-7-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoPersonDAO extends DAO {  

	private String personuuid;
	private String cnname;
	private String enname;
	private String personcode;
	private String firstname;
	private String lastname;
	private String idnum;
	private String cardcode;
	private Integer sex;
	private String marrycode;
	private String pcode;
	private String hometel;
	private String officetel;
	private String homefax;
	private String officefax;
	private String mobile;
	private String pager;
	private String email1;
	private String email2;
	private String country;
	private String provinceid;
	private String cityid;
	private String connectaddr;
	private String zip;
	private String educode;
	private String degreecode;
	private String otherinfo;
	private Integer sequenceno;
	private String job;
	private String aliasname;
	private String personext1;
	private String personext2;
	public String getPersonuuid() {
		return personuuid;
	}
	
	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
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
	public String getPersoncode() {
		return personcode;
	}
	public void setPersoncode(String _personcode) {
		firePropertyChange("personcode", personcode, _personcode);
		personcode = _personcode;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String _firstname) {
		firePropertyChange("firstname", firstname, _firstname);
		firstname = _firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String _lastname) {
		firePropertyChange("lastname", lastname, _lastname);
		lastname = _lastname;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String _idnum) {
		firePropertyChange("idnum", idnum, _idnum);
		idnum = _idnum;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String _cardcode) {
		firePropertyChange("cardcode", cardcode, _cardcode);
		cardcode = _cardcode;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer _sex) {
		firePropertyChange("sex", sex, _sex);
		sex = _sex;
	}
	public String getMarrycode() {
		return marrycode;
	}
	public void setMarrycode(String _marrycode) {
		firePropertyChange("marrycode", marrycode, _marrycode);
		marrycode = _marrycode;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String _pcode) {
		firePropertyChange("pcode", pcode, _pcode);
		pcode = _pcode;
	}
	public String getHometel() {
		return hometel;
	}
	public void setHometel(String _hometel) {
		firePropertyChange("hometel", hometel, _hometel);
		hometel = _hometel;
	}
	public String getOfficetel() {
		return officetel;
	}
	public void setOfficetel(String _officetel) {
		firePropertyChange("officetel", officetel, _officetel);
		officetel = _officetel;
	}
	public String getHomefax() {
		return homefax;
	}
	public void setHomefax(String _homefax) {
		firePropertyChange("homefax", homefax, _homefax);
		homefax = _homefax;
	}
	public String getOfficefax() {
		return officefax;
	}
	public void setOfficefax(String _officefax) {
		firePropertyChange("officefax", officefax, _officefax);
		officefax = _officefax;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String _mobile) {
		firePropertyChange("mobile", mobile, _mobile);
		mobile = _mobile;
	}
	public String getPager() {
		return pager;
	}
	public void setPager(String _pager) {
		firePropertyChange("pager", pager, _pager);
		pager = _pager;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String _email1) {
		firePropertyChange("email1", email1, _email1);
		email1 = _email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String _email2) {
		firePropertyChange("email2", email2, _email2);
		email2 = _email2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String _country) {
		firePropertyChange("country", country, _country);
		country = _country;
	}
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String _provinceid) {
		firePropertyChange("provinceid", provinceid, _provinceid);
		provinceid = _provinceid;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String _cityid) {
		firePropertyChange("cityid", cityid, _cityid);
		cityid = _cityid;
	}
	public String getConnectaddr() {
		return connectaddr;
	}
	public void setConnectaddr(String _connectaddr) {
		firePropertyChange("connectaddr", connectaddr, _connectaddr);
		connectaddr = _connectaddr;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String _zip) {
		firePropertyChange("zip", zip, _zip);
		zip = _zip;
	}
	public String getEducode() {
		return educode;
	}
	public void setEducode(String _educode) {
		firePropertyChange("educode", educode, _educode);
		educode = _educode;
	}
	public String getDegreecode() {
		return degreecode;
	}
	public void setDegreecode(String _degreecode) {
		firePropertyChange("degreecode", degreecode, _degreecode);
		degreecode = _degreecode;
	}
	public String getOtherinfo() {
		return otherinfo;
	}
	public void setOtherinfo(String _otherinfo) {
		firePropertyChange("otherinfo", otherinfo, _otherinfo);
		otherinfo = _otherinfo;
	}
	public Integer getSequenceno() {
		return sequenceno;
	}
	public void setSequenceno(Integer _sequenceno) {
		firePropertyChange("sequenceno", sequenceno, _sequenceno);
		sequenceno = _sequenceno;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String _job) {
		firePropertyChange("job", job, _job);
		job = _job;
	}
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String _aliasname) {
		firePropertyChange("aliasname", aliasname, _aliasname);
		aliasname = _aliasname;
	}
	public String getPersonext1() {
		return personext1;
	}
	public void setPersonext1(String _personext1) {
		firePropertyChange("personext1", personext1, _personext1);
		personext1 = _personext1;
	}
	public String getPersonext2() {
		return personext2;
	}
	public void setPersonext2(String _personext2) {
		firePropertyChange("personext2", personext2, _personext2);
		personext2 = _personext2;
	}
	protected void setupFields() throws DAOException {
		addField("personuuid", "PERSONUUID");
		addField("cnname", "CNNAME");
		addField("enname", "ENNAME");
		addField("personcode", "PERSONCODE");
		addField("firstname", "FIRSTNAME");
		addField("lastname", "LASTNAME");
		addField("idnum", "IDNUM");
		addField("cardcode", "CARDCODE");
		addField("sex", "SEX");
		addField("marrycode", "MARRYCODE");
		addField("pcode", "PCODE");
		addField("hometel", "HOMETEL");
		addField("officetel", "OFFICETEL");
		addField("homefax", "HOMEFAX");
		addField("officefax", "OFFICEFAX");
		addField("mobile", "MOBILE");
		addField("pager", "PAGER");
		addField("email1", "EMAIL1");
		addField("email2", "EMAIL2");
		addField("country", "COUNTRY");
		addField("provinceid", "PROVINCEID");
		addField("cityid", "CITYID");
		addField("connectaddr", "CONNECTADDR");
		addField("zip", "ZIP");
		addField("educode", "EDUCODE");
		addField("degreecode", "DEGREECODE");
		addField("otherinfo", "OTHERINFO");
		addField("sequenceno", "SEQUENCENO");
		addField("job", "JOB");
		addField("aliasname", "ALIASNAME");
		addField("personext1", "PERSONEXT1");
		addField("personext2", "PERSONEXT2");
		setTableName("RO_PERSON");
		addKey("PERSONUUID");
	}
	public RoPersonDAO(Connection conn) {
		super(conn);
	}
	public RoPersonDAO() {
		super();
	}
}
