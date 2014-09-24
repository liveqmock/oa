/*
 * 创建日期 2005-8-24
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.oftenperson.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class FiletransferStatVO extends ValueObject {
	private Integer statid;
	private String senderuserid;
	private String receiveruserid;
	private Integer times;
	public Integer getStatid() {
		return statid;
	}
	public void setStatid(Integer _statid) {
		statid = _statid;
	}
	public String getSenderuserid() {
		return senderuserid;
	}
	public void setSenderuserid(String _senderuserid) {
		senderuserid = _senderuserid;
	}
	public String getReceiveruserid() {
		return receiveruserid;
	}
	public void setReceiveruserid(String _receiveruserid) {
		receiveruserid = _receiveruserid;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer _times) {
		times = _times;
	}
}