package com.icss.oa.folder.vo;

import com.icss.j2ee.vo.ValueObject;

public class FolderShareaccessVO extends ValueObject {
	private Integer fscId;
	private Integer fsId;
	private String fscPersonid;
	private String fscAccessright;
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