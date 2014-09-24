/**
 * 
 */
package com.icss.oa.sync.vo;

/**
 * @author zhouyi
 *
 * @param <PersonTempDAO>
 */
import java.sql.Connection;

import com.icss.j2ee.util.UUID;
import com.icss.j2ee.vo.ValueObject;

public class OrgSyncSearchVO extends ValueObject {
	
	private String id;
	private String operatetype;
	private String groupcode;
	private String parentgroupcode;
	private String groupname;
	private String contact;
	private String serialindex;
	private String memo;
	private String old_groupcode;
	private String old_parentgroupcode;
	private String old_groupname;
	private String old_contact;
	private String old_serialindex;
	private String old_memo;
    private String jsonstring;
    private String opratetime;
    private String approved;
    
    
    private String oldgroupcode;
	private String oldgroupname;
	private String oldcontact;
	private String oldserialindex;
	private String oldmemo;
    
    
    public void setId(String _id){
        id = _id;
	} 
    
    public String getId(){
        return id;
	}
    
    public void setOperatetype(String _operatetype){
        operatetype = _operatetype;
	}
    
    public String getOperatetype(){
        return operatetype;
	}
    
    public void setGroupcode(String _groupcode){
        groupcode = _groupcode;
	}
    
    public String getGroupcode(){
        return groupcode;
	}
    
    
    public void setParentgroupcode(String _parentgroupcode){
        parentgroupcode = _parentgroupcode;
	}
    
    public String getParentgroupcode(){
        return parentgroupcode;
	}
    
    
    public void setGroupname(String _groupname){
        groupname = _groupname;
	}
    
    public String getGroupname(){
        return groupname;
	}
    
    
    public void setContact(String _contact){
        contact = _contact;
	}
    
    public String getContact(){
        return contact;
	}
    
    
    public void setSerialindex(String _serialindex){
        serialindex = _serialindex;
	}
    
    public String getSerialindex(){
        return serialindex;
	}
    
    public void setMemo(String _memo){
        memo = _memo;
	}
    
    public String getMemo(){
        return memo;
	}
    
    
    public void setOld_groupcode(String _old_groupcode){
        old_groupcode = _old_groupcode;
	}
    
    public String getOld_groupcode(){
        return old_groupcode;
	}
    
    
    public void setOld_parentgroupcode(String _old_parentgroupcode){
        old_parentgroupcode = _old_parentgroupcode;
	}
    
    public String getOld_parentgroupcode(){
        return old_parentgroupcode;
	}
    
    public void setOld_groupname(String _old_groupname){
        old_groupname = _old_groupname;
	}
    
    public String getOld_groupname(){
        return old_groupname;
	}
    
    public void setOld_contact(String _old_contact){
        old_contact = _old_contact;
	}
    
    public String getOld_contact(){
        return old_contact;
	}
    
    public void setOld_serialindex(String _old_serialindex){
        old_serialindex = _old_serialindex;
	}
    
    public String getOld_serialindex(){
        return old_serialindex;
	}
    
    
    public void setOld_memo(String _old_memo){
        old_memo = _old_memo;
	}
    
    public String getOld_memo(){
        return old_memo;
	}
    
    public void setJsonstring(String _jsonstring){
        jsonstring = _jsonstring;
	}
    
    public String getJsonstring(){
        return jsonstring;
	}
    
    public void setApproved(String _approved){
        approved = _approved;
	}
    
    public String getApproved(){
        return approved;
	}
    
    public void setOpratetime(String _opratetime){
    	opratetime = _opratetime;
	}
    
    public String getOpratetime(){
        return opratetime;
	}
    
    
    
    
    public void setOldgroupcode(String _oldgroupcode){
        oldgroupcode = _oldgroupcode;
	}
    
    public String getOldgroupcode(){
        return oldgroupcode;
	}
    
    
    public void setOldgroupname(String _oldgroupname){
        oldgroupname = _oldgroupname;
	}
    
    public String getOldgroupname(){
        return oldgroupname;
	}
    
    
    public void setOldcontact(String _oldcontact){
        oldcontact = _oldcontact;
	}
    
    public String getOldcontact(){
        return oldcontact;
	}
    
    
    public void setOldserialindex(String _oldserialindex){
        oldserialindex = _oldserialindex;
	}
    
    public String getOldserialindex(){
        return oldserialindex;
	}
    
    
    public void setOldmemo(String _oldmemo){
        oldmemo = _oldmemo;
	}
    
    public String getOldmemo(){
        return oldmemo;
	}
    
    
    
}