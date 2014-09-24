package com.icss.oa.folder.vo;

import java.io.InputStream;

import com.icss.j2ee.vo.ValueObject;

public class FolderDbfileVO extends ValueObject {
	private Integer fdbfId;
	private Integer fpId;
	private InputStream fdbfAccessory;
	public Integer getFdbfId() {
		return fdbfId;
	}
	public void setFdbfId(Integer _fdbfId) {
		fdbfId = _fdbfId;
	}
	public Integer getFpId() {
		return fpId;
	}
	public void setFpId(Integer _fpId) {
		fpId = _fpId;
	}
	public InputStream getFdbfAccessory() {
		return fdbfAccessory;
	}
	public void setFdbfAccessory(InputStream _fdbfAccessory) {
		fdbfAccessory = _fdbfAccessory;
	}
}