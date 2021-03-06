/**
 * 
 */
package com.icss.oa.sync.vo;

/**
 * @author fdy
 *
 * @param <PersonTempDAO>
 */
import java.sql.Connection;

import com.icss.j2ee.util.UUID;
import com.icss.j2ee.vo.ValueObject;

public class PersonSyncSearchVO extends ValueObject {
	
	private String id;
    private String operatetype;
    private String xinhuaid;
    private String hrid;
    private String username;
    private String userpwd;
    private String groupid;
    private String gender;
    private String mobilephone;
    private String renyuanxh;
    private String truename;
    private String position;
    private String version;
    private String opratetime;
    private String old_groupid;
    private String old_gender;
    private String old_mobilephone;
    private String old_renyuanxh;
    private String old_truename;
    private String old_position;
    private String old_email;
    private String jsonstring;
    private String approved;
    
    private String oldmobile;
    private String oldtruename;
    private String oldsex;
    private String oldorgcode;
    
    public void setId(String _id){
        id = _id;
	}

    public String getId(){
        return id;
	}
     
    
    
    public void setXinhuaid(java.lang.String _xinhuaid){ 
        xinhuaid = _xinhuaid;
	}

    public java.lang.String getXinhuaid(){
        return xinhuaid;
	}
    
    
    public void setHrid(java.lang.String _hrid){ 
        hrid = _hrid;
	}

    public java.lang.String getHrid(){
        return hrid;
	}
    
    
    
    public void setUsername(java.lang.String _username){ 
        username = _username;
	}

    public java.lang.String getUsername(){
        return username;
	}
    
    
    public void setUserpwd(java.lang.String _userpwd){ 
        userpwd = _userpwd;
	}

    public java.lang.String getUserpwd(){
        return userpwd;
	}
    
    
    public void setGroupid(java.lang.String _groupid){ 
        groupid = _groupid;
	}

    public java.lang.String getGroupid(){
        return groupid;
	}
    
    
    
    public void setGender(java.lang.String _gender){ 
        gender = _gender;
	}

    public java.lang.String getGender(){
        return gender;
	}
    
    
    public void setMobilephone(java.lang.String _mobilephone){ 
        mobilephone = _mobilephone;
	}

    public java.lang.String getMobilephone(){
        return mobilephone;
	}
    
    
    public void setRenyuanxh(java.lang.String _renyuanxh){ 
        renyuanxh = _renyuanxh;
	}

    public java.lang.String getRenyuanxh(){
        return renyuanxh;
	}
    
    
    
    public void setTruename(java.lang.String _truename){ 
        truename = _truename;
	}

    public java.lang.String getTruename(){
        return truename;
	}
    
    
    public void setPosition(java.lang.String _position){ 
        position = _position;
	}

    public java.lang.String getPosition(){
        return position;
	}
    
    
    public void setVersion(java.lang.String _version){ 
        version = _version;
	}

    public java.lang.String getVersion(){
        return version;
	}
    
    
    
    public void setOpratetime(java.lang.String _opratetime){ 
        opratetime = _opratetime;
	}

    public java.lang.String getOpratetime(){
        return opratetime;
	}
    
    
    
    
    
    public void setOld_groupid(java.lang.String _old_groupid){ 
        old_groupid = _old_groupid;
	}

    public java.lang.String getOld_groupid(){
        return old_groupid;
	}
    
    
    
    public void setOld_gender(java.lang.String _old_gender){ 
        old_gender = _old_gender;
	}

    public java.lang.String getOld_gender(){
        return old_gender;
	}
    
    
    
    
    public void setOld_mobilephone(java.lang.String _old_mobilephone){ 
        old_mobilephone = _old_mobilephone;
	}

    public java.lang.String getOld_mobilephone(){
        return old_mobilephone;
	}
    
    
    public void setOld_renyuanxh(java.lang.String _old_renyuanxh){ 
        old_renyuanxh = _old_renyuanxh;
	}

    public java.lang.String getOld_renyuanxh(){
        return old_renyuanxh;
	}
   
    
    
    public void setOld_truename(java.lang.String _old_truename){ 
        old_truename = _old_truename;
	}

    public java.lang.String getOld_truename(){
        return old_truename;
	}
    
    
    
    public void setOld_position(java.lang.String _old_position){ 
        old_position = _old_position;
	}

    public java.lang.String getOld_position(){
        return old_position;
	}
    
    public void setOld_email(java.lang.String _old_email){ 
    	old_email = _old_email;
	}

    public java.lang.String getOld_email(){
        return old_email;
	}
    
    
    
    
    
    
    
    
    
    public void setJsonstring(java.lang.String _jsonstring){ 
        jsonstring = _jsonstring;
	}

    public java.lang.String getJsonstring(){
        return jsonstring;
	}
    
    
    
    public void setOperatetype(java.lang.String _operatetype){ 
        operatetype = _operatetype;
	}

    public java.lang.String getOperatetype(){
        return operatetype;
	}
    
    
    
    
    
    
    public void setApproved(java.lang.String _approved){ 
        approved = _approved;
	}

    public java.lang.String getApproved(){
        return approved;
	}
    
    
    
    
    
    
    public void setOldmobile(java.lang.String _oldmobile){ 
        oldmobile = _oldmobile;
	}

    public java.lang.String getOldmobile(){
        return oldmobile;
	} 
    
    
    
    public void setOldtruename(java.lang.String _oldtruename){ 
    	oldtruename = _oldtruename;
	}

    public java.lang.String getOldtruename(){
        return oldtruename;
	} 
    
    public void setOldsex(java.lang.String _oldsex){ 
    	oldsex = _oldsex;
	}

    public java.lang.String getOldsex(){
        return oldsex;
	} 
    
    
    
    public void setOldorgcode(java.lang.String _oldorgcode){ 
        oldorgcode = _oldorgcode;
	}

    public java.lang.String getOldorgcode(){
        return oldorgcode;
	}
    
    
    
    
}