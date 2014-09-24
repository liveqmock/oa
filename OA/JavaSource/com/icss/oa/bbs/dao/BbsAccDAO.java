/*
 * Created on 2004-3-30
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.dao;

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BbsAccDAO extends DAO {

	private Integer accid;
	private Integer articleid;
	private String accname;
	private InputStream acclns;
	public Integer getAccid() {
		return accid;
	}
	public void setAccid(Integer _accid) {
		firePropertyChange("accid", accid, _accid);
		accid = _accid;
	}
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer _articleid) {
		firePropertyChange("articleid", articleid, _articleid);
		articleid = _articleid;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String _accname) {
		firePropertyChange("accname", accname, _accname);
		accname = _accname;
	}
	public InputStream getAcclns() {
		return acclns;
	}
	public void setAcclns(InputStream _acclns) {
		firePropertyChange("acclns", acclns, _acclns);
		acclns = _acclns;
	}
	protected void setupFields() throws DAOException {
		addField("accid", "ACCID");
		addField("articleid", "ARTICLEID");
		addField("accname", "ACCNAME");
		addField("acclns", "ACCLNS");
		setTableName("BBS_ACC");
		addKey("ACCID");
		setAutoIncremented("ACCID");
	}
	public BbsAccDAO(Connection conn) {
		super(conn);
	}
	public BbsAccDAO() {
		super();
	}
}
