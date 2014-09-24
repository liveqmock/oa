/**
 * 
 */
package com.icss.oa.sync.dao;

/**
 * @author fdy
 *
 * @param <PersonDAO>
 */
import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class PersonDAO extends DAO {

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
    private InputStream signature;
    private String aliasname;
    private String personext1;
    private String personext2;
    private Integer tqid;
    private String hrid;
    private String xinhuaid;

    public PersonDAO(Connection conn){
        super(conn);
    }

    public PersonDAO(){
        super();
    }

    public void setPersonuuid(java.lang.String _personuuid){ 
        firePropertyChange("personuuid",personuuid,_personuuid);
        personuuid = _personuuid;
	}

    public java.lang.String getPersonuuid(){
        return personuuid;
	}


    public void setCnname(java.lang.String _cnname){ 
        firePropertyChange("cnname",cnname,_cnname);
        cnname = _cnname;
	}

    public java.lang.String getCnname(){
        return cnname;
	}


    public void setEnname(java.lang.String _enname){ 
        firePropertyChange("enname",enname,_enname);
        enname = _enname;
	}

    public java.lang.String getEnname(){
        return enname;
	}


    public void setPersoncode(java.lang.String _personcode){ 
        firePropertyChange("personcode",personcode,_personcode);
        personcode = _personcode;
	}

    public java.lang.String getPersoncode(){
        return personcode;
	}


    public void setFirstname(java.lang.String _firstname){ 
        firePropertyChange("firstname",firstname,_firstname);
        firstname = _firstname;
	}

    public java.lang.String getFirstname(){
        return firstname;
	}


    public void setLastname(java.lang.String _lastname){ 
        firePropertyChange("lastname",lastname,_lastname);
        lastname = _lastname;
	}

    public java.lang.String getLastname(){
        return lastname;
	}


    public void setIdnum(java.lang.String _idnum){ 
        firePropertyChange("idnum",idnum,_idnum);
        idnum = _idnum;
	}

    public java.lang.String getIdnum(){
        return idnum;
	}


    public void setCardcode(java.lang.String _cardcode){ 
        firePropertyChange("cardcode",cardcode,_cardcode);
        cardcode = _cardcode;
	}

    public java.lang.String getCardcode(){
        return cardcode;
	}


    public void setSex(java.lang.Integer _sex){ 
        firePropertyChange("sex",sex,_sex);
        sex = _sex;
	}

    public java.lang.Integer getSex(){
        return sex;
	}


    public void setMarrycode(java.lang.String _marrycode){ 
        firePropertyChange("marrycode",marrycode,_marrycode);
        marrycode = _marrycode;
	}

    public java.lang.String getMarrycode(){
        return marrycode;
	}


    public void setPcode(java.lang.String _pcode){ 
        firePropertyChange("pcode",pcode,_pcode);
        pcode = _pcode;
	}

    public java.lang.String getPcode(){
        return pcode;
	}


    public void setHometel(java.lang.String _hometel){ 
        firePropertyChange("hometel",hometel,_hometel);
        hometel = _hometel;
	}

    public java.lang.String getHometel(){
        return hometel;
	}


    public void setOfficetel(java.lang.String _officetel){ 
        firePropertyChange("officetel",officetel,_officetel);
        officetel = _officetel;
	}

    public java.lang.String getOfficetel(){
        return officetel;
	}


    public void setHomefax(java.lang.String _homefax){ 
        firePropertyChange("homefax",homefax,_homefax);
        homefax = _homefax;
	}

    public java.lang.String getHomefax(){
        return homefax;
	}


    public void setOfficefax(java.lang.String _officefax){ 
        firePropertyChange("officefax",officefax,_officefax);
        officefax = _officefax;
	}

    public java.lang.String getOfficefax(){
        return officefax;
	}


    public void setMobile(java.lang.String _mobile){ 
        firePropertyChange("mobile",mobile,_mobile);
        mobile = _mobile;
	}

    public java.lang.String getMobile(){
        return mobile;
	}


    public void setPager(java.lang.String _pager){ 
        firePropertyChange("pager",pager,_pager);
        pager = _pager;
	}

    public java.lang.String getPager(){
        return pager;
	}


    public void setEmail1(java.lang.String _email1){ 
        firePropertyChange("email1",email1,_email1);
        email1 = _email1;
	}

    public java.lang.String getEmail1(){
        return email1;
	}


    public void setEmail2(java.lang.String _email2){ 
        firePropertyChange("email2",email2,_email2);
        email2 = _email2;
	}

    public java.lang.String getEmail2(){
        return email2;
	}


    public void setCountry(java.lang.String _country){ 
        firePropertyChange("country",country,_country);
        country = _country;
	}

    public java.lang.String getCountry(){
        return country;
	}


    public void setProvinceid(java.lang.String _provinceid){ 
        firePropertyChange("provinceid",provinceid,_provinceid);
        provinceid = _provinceid;
	}

    public java.lang.String getProvinceid(){
        return provinceid;
	}


    public void setCityid(java.lang.String _cityid){ 
        firePropertyChange("cityid",cityid,_cityid);
        cityid = _cityid;
	}

    public java.lang.String getCityid(){
        return cityid;
	}


    public void setConnectaddr(java.lang.String _connectaddr){ 
        firePropertyChange("connectaddr",connectaddr,_connectaddr);
        connectaddr = _connectaddr;
	}

    public java.lang.String getConnectaddr(){
        return connectaddr;
	}


    public void setZip(java.lang.String _zip){ 
        firePropertyChange("zip",zip,_zip);
        zip = _zip;
	}

    public java.lang.String getZip(){
        return zip;
	}


    public void setEducode(java.lang.String _educode){ 
        firePropertyChange("educode",educode,_educode);
        educode = _educode;
	}

    public java.lang.String getEducode(){
        return educode;
	}


    public void setDegreecode(java.lang.String _degreecode){ 
        firePropertyChange("degreecode",degreecode,_degreecode);
        degreecode = _degreecode;
	}

    public java.lang.String getDegreecode(){
        return degreecode;
	}


    public void setOtherinfo(java.lang.String _otherinfo){ 
        firePropertyChange("otherinfo",otherinfo,_otherinfo);
        otherinfo = _otherinfo;
	}

    public java.lang.String getOtherinfo(){
        return otherinfo;
	}


    public void setSequenceno(java.lang.Integer _sequenceno){ 
        firePropertyChange("sequenceno",sequenceno,_sequenceno);
        sequenceno = _sequenceno;
	}

    public java.lang.Integer getSequenceno(){
        return sequenceno;
	}


    public void setJob(java.lang.String _job){ 
        firePropertyChange("job",job,_job);
        job = _job;
	}

    public java.lang.String getJob(){
        return job;
	}


    public void setSignature(InputStream inputstream)
    {
        firePropertyChange("signature", signature, inputstream);
        signature = inputstream;
    }

    public InputStream getSignature()
    {
        return signature;
    }


    public void setAliasname(java.lang.String _aliasname){ 
        firePropertyChange("aliasname",aliasname,_aliasname);
        aliasname = _aliasname;
	}

    public java.lang.String getAliasname(){
        return aliasname;
	}


    public void setPersonext1(java.lang.String _personext1){ 
        firePropertyChange("personext1",personext1,_personext1);
        personext1 = _personext1;
	}

    public java.lang.String getPersonext1(){
        return personext1;
	}


    public void setPersonext2(java.lang.String _personext2){ 
        firePropertyChange("personext2",personext2,_personext2);
        personext2 = _personext2;
	}

    public java.lang.String getPersonext2(){
        return personext2;
	}


    public void setTqid(java.lang.Integer _tqid){ 
        firePropertyChange("tqid",tqid,_tqid);
        tqid = _tqid;
	}

    public java.lang.Integer getTqid(){
        return tqid;
	}


    public void setHrid(java.lang.String _hrid){ 
        firePropertyChange("hrid",hrid,_hrid);
        hrid = _hrid;
	}

    public java.lang.String getHrid(){
        return hrid;
	}
    
    
    public void setXinhuaid(java.lang.String _xinhuaid){ 
        firePropertyChange("xinhuaid",xinhuaid,_xinhuaid);
        xinhuaid = _xinhuaid;
	}

    public java.lang.String getXinhuaid(){
        return xinhuaid;
	}
    
    
    


    protected void setupFields() throws DAOException {
        addField("personuuid","PERSONUUID");
        addField("cnname","CNNAME");
        addField("enname","ENNAME");
        addField("personcode","PERSONCODE");
        addField("firstname","FIRSTNAME");
        addField("lastname","LASTNAME");
        addField("idnum","IDNUM");
        addField("cardcode","CARDCODE");
        addField("sex","SEX");
        addField("marrycode","MARRYCODE");
        addField("pcode","PCODE");
        addField("hometel","HOMETEL");
        addField("officetel","OFFICETEL");
        addField("homefax","HOMEFAX");
        addField("officefax","OFFICEFAX");
        addField("mobile","MOBILE");
        addField("pager","PAGER");
        addField("email1","EMAIL1");
        addField("email2","EMAIL2");
        addField("country","COUNTRY");
        addField("provinceid","PROVINCEID");
        addField("cityid","CITYID");
        addField("connectaddr","CONNECTADDR");
        addField("zip","ZIP");
        addField("educode","EDUCODE");
        addField("degreecode","DEGREECODE");
        addField("otherinfo","OTHERINFO");
        addField("sequenceno","SEQUENCENO");
        addField("job","JOB");
        addField("signature","SIGNATURE");
        addField("aliasname","ALIASNAME");
        addField("personext1","PERSONEXT1");
        addField("personext2","PERSONEXT2");
        addField("tqid","TQID");
        addField("hrid","HRID");
        addField("xinhuaid","XINHUAID");
        
        
        setTableName("RO_PERSON");
        addKey("PERSONUUID");
    }

}