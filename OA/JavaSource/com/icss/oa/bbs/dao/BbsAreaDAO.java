/*
 * Created on 2004-3-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BbsAreaDAO extends DAO {

	private Integer areaid;
	private String areades;
	private String areaname;
	private String arearight;
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer _areaid) {
		firePropertyChange("areaid", areaid, _areaid);
		areaid = _areaid;
	}
	public String getAreades() {
		return areades;
	}
	public void setAreades(String _areades) {
		firePropertyChange("areades", areades, _areades);
		areades = _areades;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String _areaname) {
		firePropertyChange("areaname", areaname, _areaname);
		areaname = _areaname;
	}
	public java.lang.String getArearight(){
		return arearight;
	}
	public void setArearight(String _arearight){
		firePropertyChange("arearight", arearight, _arearight);
		this.arearight = _arearight;
	}
	protected void setupFields() throws DAOException {
		addField("areaid", "AREAID");
		addField("areades", "AREADES");
		addField("areaname", "AREANAME");
		addField("arearight","AREARIGHT");
		setTableName("BBS_AREA");
		addKey("AREAID");
		setAutoIncremented("AREAID");
	}
	public BbsAreaDAO(Connection conn) {
		super(conn);
	}
	public BbsAreaDAO() {
		super();
	}
}
