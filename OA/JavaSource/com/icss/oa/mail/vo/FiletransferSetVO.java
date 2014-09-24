/*
 * Created on 2004-7-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.mail.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FiletransferSetVO extends ValueObject {
	private Integer id;
	private Integer fsSize;
	private Integer fsPer;
	private String fsRule;
	private String fsUid;
	private String fsUuid;
	private String fsDeltag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public Integer getFsSize() {
		return fsSize;
	}
	public void setFsSize(Integer _fsSize) {
		fsSize = _fsSize;
	}
	public Integer getFsPer() {
		return fsPer;
	}
	public void setFsPer(Integer _fsPer) {
		fsPer = _fsPer;
	}
	public String getFsRule() {
		return fsRule;
	}
	public void setFsRule(String _fsRule) {
		fsRule = _fsRule;
	}
	public String getFsUid() {
		return fsUid;
	}
	public void setFsUid(String _fsUid) {
		fsUid = _fsUid;
	}
	public String getFsUuid() {
		return fsUuid;
	}
	public void setFsUuid(String _fsUuid) {
		fsUuid = _fsUuid;
	}
	public String getFsDeltag() {
		return fsDeltag;
	}
	public void setFsDeltag(String _fsDeltag) {
		fsDeltag = _fsDeltag;
	}
}