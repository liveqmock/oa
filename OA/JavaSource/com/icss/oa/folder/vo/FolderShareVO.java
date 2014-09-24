package com.icss.oa.folder.vo;

import com.icss.j2ee.vo.ValueObject;

public class FolderShareVO extends ValueObject {
	private Integer fsId;
	private Integer fmId;
	private Integer fpId;
	private String fsName;
	private Long fsDate;
	public Integer getFsId() {
		return fsId;
	}
	public void setFsId(Integer _fsId) {
		fsId = _fsId;
	}
	public Integer getFmId() {
		return fmId;
	}
	public void setFmId(Integer _fmId) {
		fmId = _fmId;
	}
	public Integer getFpId() {
		return fpId;
	}
	public void setFpId(Integer _fpId) {
		fpId = _fpId;
	}
	public String getFsName() {
		return fsName;
	}
	public void setFsName(String _fsName) {
		fsName = _fsName;
	}
	public Long getFsDate() {
		return fsDate;
	}
	public void setFsDate(Long _fsDate) {
		fsDate = _fsDate;
	}
}