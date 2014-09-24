package com.icss.oa.filetransfer.vo;

import com.icss.j2ee.vo.ValueObject;

public class FiletransferTimeVo extends ValueObject{
	private Integer id;
	private String senduserid;
	private String receiveuserid;
	private long time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReceiveuserid() {
		return receiveuserid;
	}
	public void setReceiveuserid(String receiveuserid) {
		this.receiveuserid = receiveuserid;
	}
	public String getSenduserid() {
		return senduserid;
	}
	public void setSenduserid(String senduserid) {
		this.senduserid = senduserid;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
