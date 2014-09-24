/*
 * Created on 2005-6-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysPersonDAO2 extends DAO {

	private String personuuid;

	private Integer flag;

	private String userid;

	private Integer accountstat;

	private Integer loginfailnum;

	private String lastloginip;

	private String lastlogindate;

	private String passquestion;

	private String passanswer;

	private Integer ttlflag;

	private Integer accountttl;

	private String createtime;

	private String deltag;

	private String personcode;

	private String cnname;

	private String job;

	private String enname;

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

	private String signature;

	public String getPersonuuid() {
		return personuuid;
	}

	public void setPersonuuid(String _personuuid) {
		firePropertyChange("personuuid", personuuid, _personuuid);
		personuuid = _personuuid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer _flag) {
		firePropertyChange("flag", flag, _flag);
		flag = _flag;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public Integer getAccountstat() {
		return accountstat;
	}

	public void setAccountstat(Integer _accountstat) {
		firePropertyChange("accountstat", accountstat, _accountstat);
		accountstat = _accountstat;
	}

	public Integer getLoginfailnum() {
		return loginfailnum;
	}

	public void setLoginfailnum(Integer _loginfailnum) {
		firePropertyChange("loginfailnum", loginfailnum, _loginfailnum);
		loginfailnum = _loginfailnum;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String _lastloginip) {
		firePropertyChange("lastloginip", lastloginip, _lastloginip);
		lastloginip = _lastloginip;
	}

	public String getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(String _lastlogindate) {
		firePropertyChange("lastlogindate", lastlogindate, _lastlogindate);
		lastlogindate = _lastlogindate;
	}

	public String getPassquestion() {
		return passquestion;
	}

	public void setPassquestion(String _passquestion) {
		firePropertyChange("passquestion", passquestion, _passquestion);
		passquestion = _passquestion;
	}

	public String getPassanswer() {
		return passanswer;
	}

	public void setPassanswer(String _passanswer) {
		firePropertyChange("passanswer", passanswer, _passanswer);
		passanswer = _passanswer;
	}

	public Integer getTtlflag() {
		return ttlflag;
	}

	public void setTtlflag(Integer _ttlflag) {
		firePropertyChange("ttlflag", ttlflag, _ttlflag);
		ttlflag = _ttlflag;
	}

	public Integer getAccountttl() {
		return accountttl;
	}

	public void setAccountttl(Integer _accountttl) {
		firePropertyChange("accountttl", accountttl, _accountttl);
		accountttl = _accountttl;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String _createtime) {
		firePropertyChange("createtime", createtime, _createtime);
		createtime = _createtime;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String _deltag) {
		firePropertyChange("deltag", deltag, _deltag);
		deltag = _deltag;
	}

	public String getPersoncode() {
		return personcode;
	}

	public void setPersoncode(String _personcode) {
		firePropertyChange("personcode", personcode, _personcode);
		personcode = _personcode;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String _cnname) {
		firePropertyChange("cnname", cnname, _cnname);
		cnname = _cnname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String _job) {
		firePropertyChange("job", job, _job);
		job = _job;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String _enname) {
		firePropertyChange("enname", enname, _enname);
		enname = _enname;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String _signature) {
		firePropertyChange("signature", signature, _signature);
		signature = _signature;
	}

	protected void setupFields() throws DAOException {
		addField("personuuid", "PERSONUUID");
		addField("flag", "FLAG");
		addField("userid", "USERID");
		addField("accountstat", "ACCOUNTSTAT");
		addField("loginfailnum", "LOGINFAILNUM");
		addField("lastloginip", "LASTLOGINIP");
		addField("lastlogindate", "LASTLOGINDATE");
		addField("passquestion", "PASSQUESTION");
		addField("passanswer", "PASSANSWER");
		addField("ttlflag", "TTLFLAG");
		addField("accountttl", "ACCOUNTTTL");
		addField("createtime", "CREATETIME");
		addField("deltag", "DELTAG");
		addField("personcode", "PERSONCODE");
		addField("cnname", "CNNAME");
		addField("job", "JOB");
		addField("enname", "ENNAME");
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
		addField("signature", "SIGNATURE");
		setTableName("SYS_PERSON");
	}

	public SysPersonDAO2(Connection conn) {
		super(conn);
	}

	public SysPersonDAO2() {
		super();
	}
}
