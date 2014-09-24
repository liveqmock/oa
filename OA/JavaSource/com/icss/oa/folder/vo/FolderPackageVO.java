package com.icss.oa.folder.vo;

import com.icss.j2ee.vo.ValueObject;

public class FolderPackageVO extends ValueObject {
	Integer fpId;
	Integer fmId;
	Integer folFpId;
	String fpName;
	String fpDesc;
	Long fpCreatedate;
	Long fpModifydate;
	Long fpSize;
	String fpMimeType;
	String fpIsfile;
	String fpLevel;
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
	public Long getFpSize() {
		return fpSize;
	}
	public void setFpSize(Long _fpSize) {
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