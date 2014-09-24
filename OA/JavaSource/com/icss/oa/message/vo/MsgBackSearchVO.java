/*
 * Created on 2004-9-20
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
public class MsgBackSearchVO extends ValueObject {
	private Integer mbId;
	private Integer msMid;
	private String msPersonuuid;
	private String msPhone;
	private String msContent;
	private Integer msMode;
	private Long msDate;
	private String msPower;
	private String cnname;
	public Integer getMbId() {
		return mbId;
	}
	public void setMbId(Integer _mbId) {
		mbId = _mbId;
	}
	public Integer getMsMid() {
		return msMid;
	}
	public void setMsMid(Integer _msMid) {
		msMid = _msMid;
	}
	public String getMsPersonuuid() {
		return msPersonuuid;
	}
	public void setMsPersonuuid(String _msPersonuuid) {
		msPersonuuid = _msPersonuuid;
	}
	public String getMsPhone() {
		return msPhone;
	}
	public void setMsPhone(String _msPhone) {
		msPhone = _msPhone;
	}
	public String getMsContent() {
		return msContent;
	}
	public void setMsContent(String _msContent) {
		msContent = _msContent;
	}
	public Integer getMsMode() {
		return msMode;
	}
	public void setMsMode(Integer _msMode) {
		msMode = _msMode;
	}
	public Long getMsDate() {
		return msDate;
	}
	public void setMsDate(Long _msDate) {
		msDate = _msDate;
	}
	public String getMsPower() {
		return msPower;
	}
	public void setMsPower(String _msPower) {
		msPower = _msPower;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
}