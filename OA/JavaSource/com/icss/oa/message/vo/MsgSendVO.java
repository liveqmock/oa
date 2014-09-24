/*
 * Created on 2004-9-17
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
public class MsgSendVO extends ValueObject {
	private Integer msMid;
	private Integer msId;
	private String msPersonuuid;
	private String msOrguuid;
	private Long msDate;
	private String msContent;
	public void setMsMid(Integer _msMid) {
		msMid = _msMid;
	}
	public Integer getMsMid() {
		return msMid;
	}
	public void setMsId(Integer _msId) {
		msId = _msId;
	}
	public Integer getMsId() {
		return msId;
	}
	public void setMsPersonuuid(String _msPersonuuid) {
		msPersonuuid = _msPersonuuid;
	}
	public String getMsPersonuuid() {
		return msPersonuuid;
	}
	public void setMsOrguuid(String _msOrguuid) {
		msOrguuid = _msOrguuid;
	}
	public String getMsOrguuid() {
		return msOrguuid;
	}
	public void setMsDate(Long _msDate) {
		msDate = _msDate;
	}
	public Long getMsDate() {
		return msDate;
	}
	public void setMsContent(String _msContent) {
		msContent = _msContent;
	}
	public String getMsContent() {
		return msContent;
	}
}
