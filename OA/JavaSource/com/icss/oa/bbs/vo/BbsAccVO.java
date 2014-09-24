package com.icss.oa.bbs.vo;

import java.io.InputStream;

import com.icss.j2ee.vo.ValueObject;

public class BbsAccVO extends ValueObject {
	private Integer accid;
	private Integer articleid;
	private String accname;
	private InputStream acclns;
	public Integer getAccid() {
		return accid;
	}
	public void setAccid(Integer _accid) {
		accid = _accid;
	}
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer _articleid) {
		articleid = _articleid;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String _accname) {
		accname = _accname;
	}
	public InputStream getAcclns() {
		return acclns;
	}
	public void setAcclns(InputStream _acclns) {
		acclns = _acclns;
	}
}