/*
 * Created on 2005-8-31
 */
package com.icss.oa.filetransfer.vo;

/**
 * @author SUNCHUANTING
 * 2005-8-31 18:59:02
 */
public class DraftPersonVO {
    
    /**
     * @return Returns the dep_name.
     */
    public String getDep_name() {
        return dep_name;
    }
    /**
     * @param dep_name The dep_name to set.
     */
    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
    /**
     * @return Returns the userid.
     */
    public String getUserid() {
        return userid;
    }
    /**
     * @param userid The userid to set.
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    /**
     * @return Returns the uuid.
     */
    public String getUuid() {
        return uuid;
    }
    /**
     * @param uuid The uuid to set.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    private String uuid;
    private String userid;
    private String dep_name;
    private String cnname;

	/**
	 * @return
	 */
	public String getCnname() {
		return cnname;
	}

	/**
	 * @param string
	 */
	public void setCnname(String string) {
		cnname = string;
	}

}
