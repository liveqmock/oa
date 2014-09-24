/*
 * Created on 2004-12-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.blog.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EverydayBlogDAO extends DAO {
	private Integer id;
	private String blogname;
	private String blogcontent;
	private String blogdata;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public String getBlogname() {
		return blogname;
	}
	public void setBlogname(String _blogname) {
		firePropertyChange("blogname", blogname, _blogname);
		blogname = _blogname;
	}
	public String getBlogcontent() {
		return blogcontent;
	}
	public void setBlogcontent(String _blogcontent) {
		firePropertyChange("blogcontent", blogcontent, _blogcontent);
		blogcontent = _blogcontent;
	}
	public String getBlogdata() {
		return blogdata;
	}
	public void setBlogdata(String _blogdata) {
		firePropertyChange("blogdata", blogdata, _blogdata);
		blogdata = _blogdata;
	}
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("blogname", "BLOGNAME");
		addField("blogcontent", "BLOGCONTENT");
		addField("blogdata", "BLOGDATA");
		setTableName("RO_EVERYDAYBLOG");
		addKey("ID");
		//setAutoIncremented("ID");
	}
	public EverydayBlogDAO(Connection conn) {
		super(conn);
	}
	public EverydayBlogDAO() {
		super();
	}
}
