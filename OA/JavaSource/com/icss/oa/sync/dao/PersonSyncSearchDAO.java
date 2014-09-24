/**
 * 
 */
package com.icss.oa.sync.dao;

/**
 * @author zhouyi
 *
 * @param <PersonTempDAO>
 */
import java.sql.Connection;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;
import com.icss.j2ee.util.UUID;

public class PersonSyncSearchDAO extends SearchDAO {
	
	String id;
    String operatetype;
    String xinhuaid;
    String hrid;
    String username;
    String userpwd;
    String groupid;
    String gender;
    String mobilephone;
    String renyuanxh;
    String truename;
    String position;
    String version;
    String opratetime;
    String old_groupid;
    String old_gender;
    String old_mobilephone;
    String old_renyuanxh;
    String old_truename;
    String old_position;
    String old_email; 
    String jsonstring;
    String approved;
    

    String oldmobile;
    String oldtruename;
    String oldsex;
    String oldorgcode;
    
    
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
    
    
    public void setOld_email(java.lang.String _old_email){ 
        firePropertyChange("old_email",old_email,_old_email);
        old_email = _old_email;
	}

    public java.lang.String getOld_email(){
        return old_email;
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
    
    
    
    
    public void setOldmobile(java.lang.String _oldmobile){ 
        firePropertyChange("oldmobile",oldmobile,_oldmobile);
        oldmobile = _oldmobile;
	}

    public java.lang.String getOldmobile(){
        return oldmobile;
	}
    
    
    public void setOldtruename(java.lang.String _oldtruename){ 
        firePropertyChange("oldtruename",oldtruename,_oldtruename);
        oldtruename = _oldtruename;
	}

    public java.lang.String getOldtruename(){
        return oldtruename;
	}
    
    public void setOldsex(java.lang.String _oldsex){ 
        firePropertyChange("oldsex",oldsex,_oldsex);
        oldsex = _oldsex;
	}

    public java.lang.String getOldsex(){
        return oldsex;
	}
    
    
    public void setOldorgcode(java.lang.String _oldorgcode){ 
        firePropertyChange("oldorgcode",oldorgcode,_oldorgcode);
        oldorgcode = _oldorgcode;
	}

    public java.lang.String getOldorgcode(){
        return oldorgcode;
	}
    
    
    

    

    protected void setupFields() throws DAOException {
    	
        addField("id","PERSON_SYNC.ID"); 
        addField("operatetype","PERSON_SYNC.OPERATETYPE");
        addField("xinhuaid","PERSON_SYNC.XINHUAID");
        addField("hrid","PERSON_SYNC.HRID");
        addField("username","PERSON_SYNC.USERNAME");
        addField("userpwd","PERSON_SYNC.USERPWD");
        addField("groupid","PERSON_SYNC.GROUPID");
        addField("gender","PERSON_SYNC.GENDER");  
        addField("mobilephone","PERSON_SYNC.MOBILEPHONE");
        addField("renyuanxh","PERSON_SYNC.RENYUANXH");
        addField("truename","PERSON_SYNC.TRUENAME");
        addField("position","PERSON_SYNC.POSITION"); 
        addField("version","PERSON_SYNC.VERSION"); 
        addField("opratetime","PERSON_SYNC.OPRATETIME");
        addField("jsonstring","PERSON_SYNC.JSONSTRING");
        addField("approved","PERSON_SYNC.APPROVED");
        
        addField("oldmobile","SYS_PERSON.MOBILE");
        addField("oldtruename","SYS_PERSON.CNNAME");
        addField("oldsex","SYS_PERSON.SEX");
        addField("oldorgcode","SYS_PERSON.ORGCODE");
        
         
        addField("old_groupid","PERSON_SYNC.OLD_GROUPID");
        addField("old_gender","PERSON_SYNC.OLD_GENDER");
        addField("old_mobilephone","PERSON_SYNC.OLD_MOBILEPHONE");
        addField("old_renyuanxh","PERSON_SYNC.OLD_RENYUANXH");
        addField("old_truename","PERSON_SYNC.OLD_TRUENAME"); 
        addField("old_position","PERSON_SYNC.OLD_POSITION");
        addField("old_email","PERSON_SYNC.OLD_EMAIL");
        
        
        //setTableName("PERSON_SYNC");
        //addKey("ID");
        //this.setAutoIncremented("ID");
    }
    
    
    String sql=null;
    
    public String getSearchSQL() {
		return sql;
	} 

	public void setSearchSQL(String _sql) { 
		this.sql = _sql;
	}

	public PersonSyncSearchDAO() {
		super();
	}
    

}