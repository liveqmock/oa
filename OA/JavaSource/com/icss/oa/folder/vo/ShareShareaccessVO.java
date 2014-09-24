package com.icss.oa.folder.vo;

import com.icss.j2ee.vo.ValueObject;

public class ShareShareaccessVO extends ValueObject {
	private Integer fmId;
	private Integer fpId;
	private String fsName;
	private Long fsDate;
	private Integer fscId;
	private Integer fsId;
	private String fscPersonid;
	private String fscAccessright;
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
	public Integer getFscId() {
		return fscId;
	}
	public void setFscId(Integer _fscId) {
		fscId = _fscId;
	}
	public Integer getFsId() {
		return fsId;
	}
	public void setFsId(Integer _fsId) {
		fsId = _fsId;
	}
	public String getFscPersonid() {
		return fscPersonid;
	}
	public void setFscPersonid(String _fscPersonid) {
		fscPersonid = _fscPersonid;
	}
	public String getFscAccessright() {
		return fscAccessright;
	}
	public void setFscAccessright(String _fscAccessright) {
		fscAccessright = _fscAccessright;
	}
}