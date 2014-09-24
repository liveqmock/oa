package com.icss.oa.folder.vo;

import com.icss.j2ee.vo.ValueObject;

public class ManagementPackageVO extends ValueObject {
	private String fmPersonid;
	private String fmStoretype;
	private String fmStorepath;
	private Long fmCreatedate;
	private Long fmModifydate;
	private Integer fpId;
	private Integer fmId;
	private Integer folFpId;
	private String fpName;
	private String fpDesc;
	private Long fpCreatedate;
	private Long fpModifydate;
	private Integer fpSize;
	private String fpMimeType;
	private String fpIsfile;
	private String fpLevel;
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
	public Integer getFpId() {
		return fpId;
	}
	public void setFpId(Integer _fpId) {
		fpId = _fpId;
	}
	public Integer getFmId() {
		return fmId;
	}
	public void setFmId(Integer _fmId) {
		fmId = _fmId;
	}
	public Integer getFolFpId() {
		return folFpId;
	}
	public void setFolFpId(Integer _folFpId) {
		folFpId = _folFpId;
	}
	public String getFpName() {
		return fpName;
	}
	public void setFpName(String _fpName) {
		fpName = _fpName;
	}
	public String getFpDesc() {
		return fpDesc;
	}
	public void setFpDesc(String _fpDesc) {
		fpDesc = _fpDesc;
	}
	public Long getFpCreatedate() {
		return fpCreatedate;
	}
	public void setFpCreatedate(Long _fpCreatedate) {
		fpCreatedate = _fpCreatedate;
	}
	public Long getFpModifydate() {
		return fpModifydate;
	}
	public void setFpModifydate(Long _fpModifydate) {
		fpModifydate = _fpModifydate;
	}
	public Integer getFpSize() {
		return fpSize;
	}
	public void setFpSize(Integer _fpSize) {
		fpSize = _fpSize;
	}
	public String getFpMimeType() {
		return fpMimeType;
	}
	public void setFpMimeType(String _fpMimeType) {
		fpMimeType = _fpMimeType;
	}
	public String getFpIsfile() {
		return fpIsfile;
	}
	public void setFpIsfile(String _fpIsfile) {
		fpIsfile = _fpIsfile;
	}
	public String getFpLevel() {
		return fpLevel;
	}
	public void setFpLevel(String _fpLevel) {
		fpLevel = _fpLevel;
	}
}