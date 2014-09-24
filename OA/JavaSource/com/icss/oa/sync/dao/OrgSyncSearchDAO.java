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

public class OrgSyncSearchDAO extends SearchDAO {
	
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
	private String approved;
	private String opratetime;
	
	
	private String oldgroupcode;
	private String oldgroupname;
	private String oldcontact;
	private String oldserialindex;
	private String oldmemo;
	
	
    
    
	public void setId(String _id){
        firePropertyChange("id",id,_id);
        id = _id;
	}
    
    public String getId(){
        return id;
	}
    
    public void setOperatetype(String _operatetype){
        firePropertyChange("operatetype",operatetype,_operatetype);
        operatetype = _operatetype;
	}
    
    public String getOperatetype(){
        return operatetype;
	}
    
    public void setGroupcode(String _groupcode){
        firePropertyChange("groupcode",groupcode,_groupcode);
        groupcode = _groupcode;
	}
    
    public String getGroupcode(){
        return groupcode;
	}
    
    
    public void setParentgroupcode(String _parentgroupcode){
        firePropertyChange("parentgroupcode",parentgroupcode,_parentgroupcode);
        parentgroupcode = _parentgroupcode;
	}
    
    public String getParentgroupcode(){
        return parentgroupcode;
	}
    
    
    public void setGroupname(String _groupname){
        firePropertyChange("groupname",groupname,_groupname);
        groupname = _groupname;
	}
    
    public String getGroupname(){
        return groupname;
	}
    
    
    public void setContact(String _contact){
        firePropertyChange("contact",contact,_contact);
        contact = _contact;
	}
    
    public String getContact(){
        return contact;
	}
    
    
    public void setSerialindex(String _serialindex){
        firePropertyChange("serialindex",serialindex,_serialindex);
        serialindex = _serialindex;
	}
    
    public String getSerialindex(){
        return serialindex;
	}
    
    public void setMemo(String _memo){
        firePropertyChange("memo",memo,_memo);
        memo = _memo;
	}
    
    public String getMemo(){
        return memo;
	}
    
    
    public void setOld_groupcode(String _old_groupcode){
        firePropertyChange("old_groupcode",old_groupcode,_old_groupcode);
        old_groupcode = _old_groupcode;
	}
    
    public String getOld_groupcode(){
        return old_groupcode;
	}
    
    
    public void setOld_parentgroupcode(String _old_parentgroupcode){
        firePropertyChange("old_parentgroupcode",old_parentgroupcode,_old_parentgroupcode);
        old_parentgroupcode = _old_parentgroupcode;
	}
    
    public String getOld_parentgroupcode(){
        return old_parentgroupcode;
	}
    
    public void setOld_groupname(String _old_groupname){
        firePropertyChange("old_groupname",old_groupname,_old_groupname);
        old_groupname = _old_groupname;
	} 
    
    public String getOld_groupname(){
        return old_groupname;
	} 
    
    public void setOld_contact(String _old_contact){
        firePropertyChange("old_contact",old_contact,_old_contact);
        old_contact = _old_contact;
	}
    
    public String getOld_contact(){
        return old_contact;
	}
    
    public void setOld_serialindex(String _old_serialindex){
        firePropertyChange("old_serialindex",old_serialindex,_old_serialindex);
        old_serialindex = _old_serialindex;
	}
    
    public String getOld_serialindex(){
        return old_serialindex;
	}
    
    
    public void setOld_memo(String _old_memo){
        firePropertyChange("old_memo",old_memo,_old_memo);
        old_memo = _old_memo;
	}
    
    public String getOld_memo(){
        return old_memo;
	}
    
    
    public void setJsonstring(String _jsonstring){
        firePropertyChange("jsonstring",jsonstring,_jsonstring);
        jsonstring = _jsonstring;
	}
    
    public String getJsonstring(){
        return jsonstring;
	}
    
    public void setApproved(String _approved){
        firePropertyChange("approved",approved,_approved);
        approved = _approved;
	}
    
    public String getApproved(){
        return approved;
	}
    
    public void setOpratetime(String _opratetime){
        firePropertyChange("opratetime",opratetime,_opratetime);
        opratetime = _opratetime;
	}
    
    public String getOpratetime(){
        return opratetime;
	}
    
    
    
    
    
    
    
    public void setOldgroupcode(String _oldgroupcode){
        firePropertyChange("oldgroupcode",oldgroupcode,_oldgroupcode);
        oldgroupcode = _oldgroupcode;
	}
    
    public String getOldgroupcode(){
        return oldgroupcode;
	}
    
    
    public void setOldgroupname(String _oldgroupname){
        firePropertyChange("oldgroupname",oldgroupname,_oldgroupname);
        oldgroupname = _oldgroupname;
	}
    
    public String getOldgroupname(){
        return oldgroupname;
	}
    
    
    public void setOldcontact(String _oldcontact){
        firePropertyChange("oldcontact",oldcontact,_oldcontact);
        oldcontact = _oldcontact;
	}
    
    public String getOldcontact(){
        return oldcontact;
	}
    
    
    public void setOldserialindex(String _oldserialindex){
        firePropertyChange("oldserialindex",oldserialindex,_oldserialindex);
        oldserialindex = _oldserialindex;
	}
    
    public String getOldserialindex(){
        return oldserialindex;
	}
    
    
    public void setOldmemo(String _oldmemo){
        firePropertyChange("oldmemo",oldmemo,_oldmemo);
        oldmemo = _oldmemo;
	}
    
    public String getOldmemo(){
        return oldmemo;
	}
    
    
    
    

    protected void setupFields() throws DAOException {
        addField("id","ORG_SYNC.ID"); 
        addField("operatetype","ORG_SYNC.OPERATETYPE");
        addField("groupcode","ORG_SYNC.GROUPCODE");
        addField("parentgroupcode","ORG_SYNC.PARENTGROUPCODE");
        addField("groupname","ORG_SYNC.GROUPNAME");
        addField("contact","ORG_SYNC.CONTACT");
        addField("serialindex","ORG_SYNC.SERIALINDEX");
        addField("memo","ORG_SYNC.MEMO");
        addField("old_groupcode","ORG_SYNC.OLD_GROUPCODE");
        addField("old_parentgroupcode","ORG_SYNC.OLD_PARENTGROUPCODE");
        addField("old_groupname","ORG_SYNC.OLD_GROUPNAME");
        addField("old_contact","ORG_SYNC.OLD_CONTACT");
        addField("old_serialindex","ORG_SYNC.OLD_SERIALINDEX"); 
        addField("old_memo","ORG_SYNC.OLD_MEMO");
        addField("jsonstring","ORG_SYNC.JSONSTRING");
        addField("approved","ORG_SYNC.APPROVED");
        addField("opratetime","ORG_SYNC.OPRATETIME");
        
        addField("oldgroupcode","SYS_ORG.ORGCODE2");
        addField("oldgroupname","SYS_ORG.CNNAME2");
        addField("oldcontact","SYS_ORG.CONTACT2");
        addField("oldserialindex","SYS_ORG.SERIALINDEX2");
        addField("oldmemo","SYS_ORG.MEMO2");
    }
    
    
    String sql=null;
    
    public String getSearchSQL() {
		return sql;
	} 

	public void setSearchSQL(String _sql) { 
		this.sql = _sql;
	}

	public OrgSyncSearchDAO() {
		super();
	}
    

}