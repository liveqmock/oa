package com.icss.oa.folder.vo;

import com.icss.j2ee.vo.ValueObject;

public class FolderManagementVO extends ValueObject {

	private Integer fmId;
	private String fmPersonid;
	private String fmStoretype;
	private String fmStorepath;
	private Long fmCreatedate;
	private Long fmModifydate;
	
	public Integer getFmId() {
		return fmId;
	}
	public void setFmId(Integer _fmId) {
		fmId = _fmId;
	}
	public String getFmPersonid() {
		return fmPersonid;
	}
	public void setFmPersonid(String _fmPersonid) {
		fmPersonid = _fmPersonid;
	}
	public String getFmStoretype() {
		return fmStoretype;
	}
	public void setFmStoretype(String _fmStoretype) {
		fmStoretype = _fmStoretype;
	}
	public String getFmStorepath() {
		return fmStorepath;
	}
	public void setFmStorepath(String _fmStorepath) {
		fmStorepath = _fmStorepath;
	}
	public Long getFmCreatedate() {
		return fmCreatedate;
	}
	public void setFmCreatedate(Long _fmCreatedate) {
		fmCreatedate = _fmCreatedate;
	}
	public Long getFmModifydate() {
		return fmModifydate;
	}
	public void setFmModifydate(Long _fmModifydate) {
		fmModifydate = _fmModifydate;
	}
}