/*
 * Created on 2004-8-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EntrustDAO extends DAO {

	Integer id;
	String entrustUid;
	String substituteUid;
	Long starttime;
	Long endtime;
	String entrustUuid;
	String substituteUuid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getEntrustUid() {
		return entrustUid;
	}
	public void setEntrustUid(String _entrustUid) {
		firePropertyChange("entrustUid", entrustUid, _entrustUid);
		entrustUid = _entrustUid;
	}
	public String getSubstituteUid() {
		return substituteUid;
	}
	public void setSubstituteUid(String _substituteUid) {
		firePropertyChange("substituteUid", substituteUid, _substituteUid);
		substituteUid = _substituteUid;
	}
	public Long getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Long _starttime)
	{
		firePropertyChange("starttime", starttime, _starttime);
		starttime = _starttime;
	}

	public Long getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Long _endtime)
	{
		firePropertyChange("endtime", endtime, _endtime);
		endtime = _endtime;
	}
	public String getEntrustUuid() {
		return entrustUuid;
	}
	public void setEntrustUuid(String _entrustUuid) {
		firePropertyChange("entrustUuid", entrustUuid, _entrustUuid);
		entrustUuid = _entrustUuid;
	}
	public String getSubstituteUuid() {
		return substituteUuid;
	}
	public void setSubstituteUuid(String _substituteUuid) {
		firePropertyChange("substituteUuid", substituteUuid, _substituteUuid);
		substituteUuid = _substituteUuid;
	}
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("entrustUid", "ENTRUST_UID");
		addField("substituteUid", "SUBSTITUTE_UID");
		addField("starttime", "STARTTIME");
		addField("endtime", "ENDTIME");
		addField("entrustUuid", "ENTRUST_UUID");
		addField("substituteUuid", "SUBSTITUTE_UUID");
		setTableName("ENTRUST");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public EntrustDAO(Connection conn) {
		super(conn);
	}
	public EntrustDAO() {
		super();
	}
}
