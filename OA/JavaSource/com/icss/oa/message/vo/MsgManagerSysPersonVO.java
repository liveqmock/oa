/*
 * Created on 2004-9-16
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MsgManagerSysPersonVO extends ValueObject {
	private Integer mmId;
	private String mmOrguuid;
	private String mmPersonuuid;
	private String cnname;
	private String job;
	public Integer getMmId() {
		return mmId;
	}
	public void setMmId(Integer _mmId) {
		mmId = _mmId;
	}
	public String getMmOrguuid() {
		return mmOrguuid;
	}
	public void setMmOrguuid(String _mmOrguuid) {
		mmOrguuid = _mmOrguuid;
	}
	public String getMmPersonuuid() {
		return mmPersonuuid;
	}
	public void setMmPersonuuid(String _mmPersonuuid) {
		mmPersonuuid = _mmPersonuuid;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String _job) {
		job = _job;
	}
}