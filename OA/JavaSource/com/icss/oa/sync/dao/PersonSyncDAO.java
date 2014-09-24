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
import com.icss.j2ee.util.UUID;

public class PersonSyncDAO extends DAO {
	
	private String id;
    private String operatetype;
    private String xinhuaid;
    private String hrid;
    private String username;
    private String userpwd;
    private String groupid;
    private String gender;
    private String mobilephone;
    private String email;
    private String renyuanxh;
    private String truename;
    private String position; 
    private String version;
    private String opratetime;
    private String objgroupid;
    private String old_groupid;
    private String old_gender;
    private String old_mobilephone;
    private String old_email;
    private String old_renyuanxh;
    private String old_truename;
    private String old_position;
    private String updcontent;
    private String targetcoapp;
    private String jsonstring;
    private String approved;
    private String ro_pwd;

    public PersonSyncDAO(Connection conn){
        super(conn);
    }

    public PersonSyncDAO(){ 
        super();
    }   
    
    
    
    public void setId(String _id){ 
        firePropertyChange("id",id,_id);
        id = _id;
	}

    public String getId(){
        return id;
	}
     
    
    
    public void setXinhuaid(java.lang.String _xinhuaid){ 
        firePropertyChange("xinhuaid",xinhuaid,_xinhuaid);
        xinhuaid = _xinhuaid;
	}

    public java.lang.String getXinhuaid(){
        return xinhuaid;
	}
    
    
    public void setHrid(java.lang.String _hrid){ 
        firePropertyChange("hrid",hrid,_hrid);
        hrid = _hrid;
	}

    public java.lang.String getHrid(){
        return hrid;
	}
    
    
    
    public void setUsername(java.lang.String _username){ 
        firePropertyChange("username",username,_username);
        username = _username;
	}

    public java.lang.String getUsername(){
        return username;
	}
    
    
    public void setUserpwd(java.lang.String _userpwd){ 
        firePropertyChange("userpwd",userpwd,_userpwd);
        userpwd = _userpwd;
	}

    public java.lang.String getUserpwd(){
        return userpwd;
	}
    
    
    public void setGroupid(java.lang.String _groupid){ 
        firePropertyChange("groupid",groupid,_groupid);
        groupid = _groupid;
	}

    public java.lang.String getGroupid(){
        return groupid;
	}
    
    
    
    public void setGender(java.lang.String _gender){ 
        firePropertyChange("gender",gender,_gender);
        gender = _gender;
	}

    public java.lang.String getGender(){
        return gender;
	}
    
    
    public void setMobilephone(java.lang.String _mobilephone){ 
        firePropertyChange("mobilephone",mobilephone,_mobilephone);
        mobilephone = _mobilephone;
	}

    public java.lang.String getMobilephone(){
        return mobilephone;
	}
    
    
    public void setEmail(java.lang.String _email){ 
        firePropertyChange("email",email,_email);
        email = _email;
	}

    public java.lang.String getEmail(){
        return email;
	}
    
    
    public void setRenyuanxh(java.lang.String _renyuanxh){ 
        firePropertyChange("renyuanxh",renyuanxh,_renyuanxh);
        renyuanxh = _renyuanxh;
	}

    public java.lang.String getRenyuanxh(){
        return renyuanxh;
	}
    
    
    
    public void setTruename(java.lang.String _truename){ 
        firePropertyChange("truename",truename,_truename);
        truename = _truename;
	}

    public java.lang.String getTruename(){
        return truename;
	}
    
    
    public void setPosition(java.lang.String _position){ 
        firePropertyChange("position",position,_position);
        position = _position;
	}

    public java.lang.String getPosition(){
        return position;
	}
    
    
    public void setVersion(java.lang.String _version){ 
        firePropertyChange("version",version,_version);
        version = _version;
	}

    public java.lang.String getVersion(){
        return version;
	}
    
    
    
    public void setOpratetime(java.lang.String _opratetime){ 
        firePropertyChange("opratetime",opratetime,_opratetime);
        opratetime = _opratetime;
	}

    public java.lang.String getOpratetime(){
        return opratetime;
	}
    
    
    
    public void setObjgroupid(java.lang.String _objgroupid){ 
        firePropertyChange("objgroupid",objgroupid,_objgroupid);
        objgroupid = _objgroupid;
	}

    public java.lang.String getObjgroupid(){
        return objgroupid;
	}
    
    
    public void setOld_groupid(java.lang.String _old_groupid){ 
        firePropertyChange("old_groupid",old_groupid,_old_groupid);
        old_groupid = _old_groupid;
	}

    public java.lang.String getOld_groupid(){
        return old_groupid;
	}
    
    
    
    public void setOld_gender(java.lang.String _old_gender){ 
        firePropertyChange("old_gender",old_gender,_old_gender);
        old_gender = _old_gender;
	}

    public java.lang.String getOld_gender(){
        return old_gender;
	}
    
    
    
    
    public void setOld_mobilephone(java.lang.String _old_mobilephone){ 
        firePropertyChange("old_mobilephone",old_mobilephone,_old_mobilephone);
        old_mobilephone = _old_mobilephone;
	}

    public java.lang.String getOld_mobilephone(){
        return old_mobilephone;
	}
    
    
    
    public void setOld_email(java.lang.String _old_email){ 
        firePropertyChange("old_email",old_email,_old_email);
        old_email = _old_email;
	}

    public java.lang.String getOld_email(){
        return old_email;
	}
    
    
    
    
    public void setOld_renyuanxh(java.lang.String _old_renyuanxh){ 
        firePropertyChange("old_renyuanxh",old_renyuanxh,_old_renyuanxh);
        old_renyuanxh = _old_renyuanxh;
	}

    public java.lang.String getOld_renyuanxh(){
        return old_renyuanxh;
	}
   
    
    
    public void setOld_truename(java.lang.String _old_truename){ 
        firePropertyChange("old_truename",old_truename,_old_truename);
        old_truename = _old_truename;
	}

    public java.lang.String getOld_truename(){
        return old_truename;
	}
    
    
    
    public void setOld_position(java.lang.String _old_position){ 
        firePropertyChange("old_position",old_position,_old_position);
        old_position = _old_position;
	}

    public java.lang.String getOld_position(){
        return old_position;
	}
    
    
    
    public void setUpdcontent(java.lang.String _updcontent){ 
        firePropertyChange("updcontent",updcontent,_updcontent);
        updcontent = _updcontent;
	}

    public java.lang.String getUpdcontent(){
        return updcontent;
	}
    
    
    
    public void setTargetcoapp(java.lang.String _targetcoapp){ 
        firePropertyChange("targetcoapp",targetcoapp,_targetcoapp);
        targetcoapp = _targetcoapp;
	}

    public java.lang.String getTargetcoapp(){
        return targetcoapp;
	}
    
    
    
    public void setJsonstring(java.lang.String _jsonstring){ 
        firePropertyChange("jsonstring",jsonstring,_jsonstring);
        jsonstring = _jsonstring;
	}

    public java.lang.String getJsonstring(){
        return jsonstring;
	}
    
    
    
    public void setOperatetype(java.lang.String _operatetype){ 
        firePropertyChange("operatetype",operatetype,_operatetype);
        operatetype = _operatetype;
	}

    public java.lang.String getOperatetype(){
        return operatetype;
	}

    
    
    
    public void setApproved(java.lang.String _approved){ 
        firePropertyChange("approved",approved,_approved);
        approved = _approved;
	}

    public java.lang.String getApproved(){
        return approved;
	}
    
    public void setRo_pwd(java.lang.String _ro_pwd){ 
        firePropertyChange("ro_pwd",ro_pwd,_ro_pwd);
        ro_pwd = _ro_pwd;
	}

    public java.lang.String getRo_pwd(){
        return ro_pwd;
	}
    
    

    

    protected void setupFields() throws DAOException {
        addField("id","ID");
        addField("operatetype","OPERATETYPE");
        addField("xinhuaid","XINHUAID");
        addField("hrid","HRID");
        addField("username","USERNAME");
        addField("userpwd","USERPWD");
        addField("groupid","GROUPID");
        addField("gender","GENDER");  
        addField("mobilephone","MOBILEPHONE");
        addField("email","EMAIL");
        addField("renyuanxh","RENYUANXH");
        addField("truename","TRUENAME");
        addField("position","POSITION"); 
        addField("version","VERSION");
        addField("opratetime","OPRATETIME");
        addField("jsonstring","JSONSTRING"); 
        addField("approved","APPROVED");
        addField("ro_pwd","RO_PWD");
        
        addField("objgroupid","OBJGROUPID");
        addField("old_groupid","OLD_GROUPID");
        addField("old_gender","OLD_GENDER");
        addField("old_mobilephone","OLD_MOBILEPHONE");
        addField("old_email","OLD_EMAIL");
        addField("old_renyuanxh","OLD_RENYUANXH");
        addField("old_truename","OLD_TRUENAME");
        addField("old_position","OLD_POSITION");
        addField("updcontent","UPDCONTENT");
        addField("targetcoapp","TARGETCOAPP");
        
        
        setTableName("PERSON_SYNC");
        addKey("ID");
        //this.setAutoIncremented("ID");
    }

}