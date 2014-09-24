/**
 * 
 */
package com.icss.oa.sync.dao;

/**
 * @author fdy
 *
 * @param <PersonTempDAO>
 */
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class PersonTempDAO extends DAO {
	
	private Integer id;
    private String operateid;
    private String personname;
    private String orgid;
    private String deptid;
    private String userid;
    private String operatetype;
    private Integer isuniq;
    private Integer isright;
    private Integer approved;
    private Integer tohr;
    private Integer totq;
    private Integer tomail;
    private String origin;
    private String purpose;
    private String describe;
    private String hrid;
    private String alterfield;
    private String mobilephone;
    private String serialindex;
    private String time;

    public PersonTempDAO(Connection conn){
        super(conn);
    }

    public PersonTempDAO(){
        super();
    }
    
    public void setId(Integer _id){ 
        firePropertyChange("id",id,_id);
        id = _id;
	}

    public Integer getId(){
        return id;
	}


    public void setOperateid(java.lang.String _operateid){ 
        firePropertyChange("operateid",operateid,_operateid);
        operateid = _operateid;
	}

    public java.lang.String getOperateid(){
        return operateid;
	}


    public void setPersonname(java.lang.String _personname){ 
        firePropertyChange("personname",personname,_personname);
        personname = _personname;
	}

    public java.lang.String getPersonname(){
        return personname;
	}


    public void setOrgid(java.lang.String _orgid){ 
        firePropertyChange("orgid",orgid,_orgid);
        orgid = _orgid;
	}

    public java.lang.String getOrgid(){
        return orgid;
	}


    public void setDeptid(java.lang.String _deptid){ 
        firePropertyChange("deptid",deptid,_deptid);
        deptid = _deptid;
	}

    public java.lang.String getDeptid(){
        return deptid;
	}


    public void setUserid(java.lang.String _userid){ 
        firePropertyChange("userid",userid,_userid);
        userid = _userid;
	}

    public java.lang.String getUserid(){
        return userid;
	}


    public void setOperatetype(java.lang.String _operatetype){ 
        firePropertyChange("operatetype",operatetype,_operatetype);
        operatetype = _operatetype;
	}

    public java.lang.String getOperatetype(){
        return operatetype;
	}


    public void setIsuniq(java.lang.Integer _isuniq){ 
        firePropertyChange("isuniq",isuniq,_isuniq);
        isuniq = _isuniq;
	}

    public java.lang.Integer getIsuniq(){
        return isuniq;
	}


    public void setIsright(java.lang.Integer _isright){ 
        firePropertyChange("isright",isright,_isright);
        isright = _isright;
	}

    public java.lang.Integer getIsright(){
        return isright;
	}


    public void setApproved(java.lang.Integer _approved){ 
        firePropertyChange("approved",approved,_approved);
        approved = _approved;
	}

    public java.lang.Integer getApproved(){
        return approved;
	}


    public void setTohr(java.lang.Integer _tohr){ 
        firePropertyChange("tohr",tohr,_tohr);
        tohr = _tohr;
	}

    public java.lang.Integer getTohr(){
        return tohr;
	}


    public void setTotq(java.lang.Integer _totq){ 
        firePropertyChange("totq",totq,_totq);
        totq = _totq;
	}

    public java.lang.Integer getTotq(){
        return totq;
	}


    public void setTomail(java.lang.Integer _tomail){ 
        firePropertyChange("tomail",tomail,_tomail);
        tomail = _tomail;
	}

    public java.lang.Integer getTomail(){
        return tomail;
	}


    public void setOrigin(java.lang.String _origin){ 
        firePropertyChange("origin",origin,_origin);
        origin = _origin;
	}

    public java.lang.String getOrigin(){
        return origin;
	}


    public void setPurpose(java.lang.String _purpose){ 
        firePropertyChange("purpose",purpose,_purpose);
        purpose = _purpose;
	}

    public java.lang.String getPurpose(){
        return purpose;
	}


    public void setDescribe(java.lang.String _describe){ 
        firePropertyChange("describe",describe,_describe);
        describe = _describe;
	}

    public java.lang.String getDescribe(){
        return describe;
	}


    public void setHrid(java.lang.String _hrid){ 
        firePropertyChange("hrid",hrid,_hrid);
        hrid = _hrid;
	}

    public java.lang.String getHrid(){
        return hrid;
	}


    public void setAlterfield(java.lang.String _alterfield){ 
        firePropertyChange("alterfield",alterfield,_alterfield);
        alterfield = _alterfield;
	}

    public java.lang.String getAlterfield(){
        return alterfield;
	}

    public void setMobilephone(java.lang.String _mobilephone){ 
        firePropertyChange("mobilephone",mobilephone,_mobilephone);
        mobilephone = _mobilephone;
	}

    public java.lang.String getMobilephone(){
        return mobilephone;
	}
    
    public void setSerialindex(java.lang.String _serialindex){ 
        firePropertyChange("serialindex",serialindex,_serialindex);
        serialindex = _serialindex;
	}

    public java.lang.String getSerialindex(){
        return serialindex;
	}
    
    public void setTime(java.lang.String _time){ 
        firePropertyChange("time",time,_time);
        time = _time;
	}

    public java.lang.String getTime(){
        return time;
	}

    protected void setupFields() throws DAOException {
        addField("id","ID");
        addField("operateid","OPERATEID");
        addField("personname","PERSONNAME");
        addField("orgid","ORGID");
        addField("deptid","DEPTID");
        addField("userid","USERID");
        addField("operatetype","OPERATETYPE");
        addField("isuniq","ISUNIQ");
        addField("isright","ISRIGHT");
        addField("approved","APPROVED");
        addField("tohr","TOHR");
        addField("totq","TOTQ");
        addField("tomail","TOMAIL");
        addField("origin","ORIGIN");
        addField("purpose","PURPOSE");
        addField("describe","DESCRIBE");
        addField("hrid","HRID");
        addField("alterfield","ALTERFIELD");
        addField("mobilephone","MOBILEPHONE");
        addField("serialindex","SERIALINDEX");
        addField("time","TIME");

        setTableName("PERSON_TEMP");
        addKey("ID");
        
        this.setAutoIncremented("ID");
    }

}