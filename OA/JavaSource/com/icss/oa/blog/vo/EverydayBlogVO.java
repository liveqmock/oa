/*
 * Created on 2004-12-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.blog.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EverydayBlogVO extends ValueObject {
	private Integer id;
	private String blogname;
	private String blogcontent;
	private String blogdata;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public String getBlogname() {
		return blogname;
	}
	public void setBlogname(String _blogname) {
		blogname = _blogname;
	}
	public String getBlogcontent() {
		return blogcontent;
	}
	public void setBlogcontent(String _blogcontent) {
		blogcontent = _blogcontent;
	}
	public String getBlogdata() {
		return blogdata;
	}
	public void setBlogdata(String _blogdata) {
		blogdata = _blogdata;
	}
}